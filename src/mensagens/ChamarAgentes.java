/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import agentes.MinerAgent;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.ContractNetInitiator;
import java.util.Date;
import java.util.Vector;
//import protocolos.ProtocoloCfpChamarAgentes;

/**
 *
 * @author LUIS
 */
public class ChamarAgentes extends SequentialBehaviour{
    
    public ChamarAgentes(Agent a) {
        super(a);
    }
    
    @Override
    public void onStart() 
    {    
            ServiceDescription servico = new ServiceDescription();
            servico.setType("mineracao");
            servico.setName("clusteringMiner");
 
            DFAgentDescription descripcion = new DFAgentDescription();
            descripcion.addLanguages("portugues");
            descripcion.addServices(servico);       
        
        try {
                DFAgentDescription[] resultados = DFService.search(myAgent, descripcion);
                
                if (resultados.length <= 0) {
                    System.out.println("Não existem agentes com este tipo de serviço: " + servico.getName());
                } else {
                    
                    //***System.out.println("Resultado da pesquisa: tem " + resultados.length + " agentes com este serviço");
 
                  // Criar a mensagem CFP (Call For Proposal) completando os seus parâmetros
                  ACLMessage mensagemCFP = new ACLMessage(ACLMessage.CFP);
                    
                  for (DFAgentDescription agente:resultados) {
                        
                        if(!agente.getName().equals(myAgent.getAID()) ){
                            //***System.out.printf("Agente %s para %s ", agente.getName().getLocalName(), myAgent.getAID().getLocalName());
                            mensagemCFP.addReceiver(agente.getName());}
                    }
                    
                    mensagemCFP.setProtocol(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
                    mensagemCFP.setContent("Chamando Agentes");
 
                    //Indicamos o tempo que esperaremos pelas ofertas.
                    mensagemCFP.setReplyByDate(new Date(System.currentTimeMillis() + 15000));
 
                    //Se adiciona o comportamento que irá lidar com as ofertas.
                    addSubBehaviour(new ProtocoloCfpChamarAgentes((MinerAgent) myAgent, mensagemCFP));                    
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
}

class ProtocoloCfpChamarAgentes extends ContractNetInitiator{
    
        public ProtocoloCfpChamarAgentes(Agent a, ACLMessage cfp) {
           super(a, cfp);
        }
    
        //método que irá lidar com as proposições
        @Override
        protected void handlePropose(ACLMessage propostas, Vector aceite) {
            System.out.printf("%s: recebida proposta do agente %s que oferece %s.\n",
                this.myAgent.getLocalName(), propostas.getSender().getLocalName(), propostas.getContent());
        }
 
        //método que rejeita as proposições
        @Override
        protected void handleRefuse(ACLMessage rejeitada) {
            System.out.printf("O Agente %s: Agente %s recusa pois já participa da mineração.\n",
                this.myAgent.getLocalName(), rejeitada.getSender().getLocalName());
        }       
 
        //método de resposta em caso de falha
        @Override
        protected void handleFailure(ACLMessage fallo) {
            // Falha, portanto, nós não receberemos nenhuma resposta do agente
            
            if (fallo.getSender().equals(myAgent.getAMS())) { 
                //Esta notificaçao vem ambiente de execução JADE (não existe o receptor)
                System.out.println("AMS: Este servico nao existe ou nao e acessivel");
            } else {
                System.out.printf("%s: Agente %s sofreu uma falha.\n",
                    this.myAgent.getLocalName(), fallo.getSender().getLocalName());
            }          
            
        }
 
        //método coletivo chamado finalizar o tempo de espera ou receber todas as repsostas
        @Override
        protected void handleAllResponses(Vector respostas, Vector aceites) {
 
            ACLMessage aceite = null;
            
            for (Object resp:respostas) {
                
                ACLMessage mensagem = (ACLMessage) resp;
                
                if (mensagem.getPerformative() == ACLMessage.PROPOSE) {
                    
                    ACLMessage resposta = mensagem.createReply();
                    
                    //***System.out.printf("Confirmado!!! O agente %s participará da mineração com agente %s\n", this.myAgent.getLocalName(), mensagem.getSender().getLocalName());
                    
                    //*************************** bem aqui tava dando erro: Já resolvido
                    aceite = resposta;
                    aceite.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                    aceite.setContent("Confirmado");
                    aceites.add(resposta); 
                    
                }
                else{
                    
                    ACLMessage resposta = mensagem.createReply();
                
                    //***System.out.printf("%s chamou o agente %s, mas recusou pois já participar da mineração!!!\n", this.myAgent.getLocalName(), mensagem.getSender().getLocalName());
                    
                    aceite = resposta;
                    aceite.setPerformative(ACLMessage.REJECT_PROPOSAL);
                    aceite.setContent("Recusado");
                    aceites.add(resposta); 
                }
            } 
            
        }
 
        //método que ira tratar das mensagens INFORM
        @Override
        protected void handleInform(ACLMessage inform) {
            System.out.printf("Agnte %s chamou o Agente %s que está confirmando participaçao na mineração.\n",
                this.myAgent.getLocalName(), inform.getSender().getLocalName()); 
                
        }
    
}