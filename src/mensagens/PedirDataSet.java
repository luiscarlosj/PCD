/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import agentes.MinerAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineracao.Parametros;
//import protocolos.ProtocoloCfpChamarAgentes;
//import protocolos.ProtocoloQueryPedirDataSet;

/**
 *
 * @author LUIS
 */
public class PedirDataSet extends SequentialBehaviour{
    
    public PedirDataSet(Agent a) {
        super(a);
    }
    
    @Override
    public void onStart() 
    {
        
        ServiceDescription servico = new ServiceDescription();
        servico.setType("mineracao");
        servico.setName("clusteringData");
 
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.addLanguages("portugues");
        descripcion.addServices(servico);       
        
        try {
                DFAgentDescription[] resultados = DFService.search(myAgent, descripcion);
                
                if (resultados.length <= 0) 
                {
                    System.out.println("Não existem agentes com este tipo de serviço: " + servico.getType());
                } else 
                {
                    
                    System.out.println("Resultado da pesquisa: tem " + resultados.length + " agentes com este serviço");
 
                    // Criar a mensagem CFP (Call For Proposal) completando os seus parâmetros
                    ACLMessage mensagem = new ACLMessage(ACLMessage.QUERY_IF);
                    // ACLMessage mensagemCFP = new ACLMessage(ACLMessage.PROPOSE);
                  
                    for (DFAgentDescription agente:resultados) 
                    {
                        //((MinerAgent) this.myAgent).isTemParametros() == false
                        if(!agente.getName().equals(myAgent.getAID()) ){
                            System.out.printf("Agente %s pedi dataset para %s\n", myAgent.getAID().getLocalName(), agente.getName().getLocalName());
                            mensagem.addReceiver(agente.getName());}
                    }
                    
                    mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
                    //mensagemCFP.setProtocol(FIPANames.InteractionProtocol.FIPA_PROPOSE);
                    mensagem.setContent("Tens DataSet da mineração?");
                    
                    //Indicamos o tempo que esperaremos pelas ofertas.
                    mensagem.setReplyByDate(new Date(System.currentTimeMillis() + 15000));
 
                    //Adicionamos o comportamento da consulta
                    addSubBehaviour(new ProtocoloQueryPedirDataSet((MinerAgent) this.myAgent, mensagem));
                  
                    //testar this.addBehaviour
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
     }
}


class ProtocoloQueryPedirDataSet extends AchieveREInitiator {
    
        Parametros pm = Parametros.getInstance();
    
        public ProtocoloQueryPedirDataSet(Agent agente, ACLMessage mensagem) {
            super(agente, mensagem);
            
        }

        @Override
        protected void handleAgree(ACLMessage agree) {
            System.out.printf("O agente %s respondeu que tem dataset e pediu para aguardar.\n", agree.getSender().getLocalName());
        }
 
        @Override
        protected void handleRefuse(ACLMessage refuse) {
            System.out.printf("O agente %s disse estar ocupado. Não pode atender no momento.\n", refuse.getSender().getLocalName());
        }
 
        @Override
        protected void handleNotUnderstood(ACLMessage notUnderstood) {
            System.out.printf("Agente %s disse que não entendeu a mensagem.\n", notUnderstood.getSender().getLocalName());
        }
 
         @Override
        protected void handleInform(ACLMessage inform) {
            System.out.printf("O agente %s envio o arquivo.\n", inform.getSender().getLocalName(), inform.getContent());
            
            try {
                File f = (File) inform.getContentObject();  //***
                //System.out.println("endereço do arquivo: "+ ((File) inform.getContentObject()).getAbsolutePath());
                System.out.println("endereço do arquivo: "+ f.getAbsolutePath()); //***
                ((MinerAgent) this.myAgent).setMeuArquivo(f);
                System.out.println("endereço do arquivo: "+ ((MinerAgent) this.myAgent).getMeuArquivo().getAbsolutePath()); //***
                
                //pm.setArq(this.myAgent.getLocalName(), f);
                
                //System.out.println("RECEBEU DATASET AQUI endereço do arquivo: "+ pm.getArqAgent(this.myAgent.getLocalName()).getAbsolutePath());
                
                //pm.getArq().put(this.myAgent.getLocalName(), f);
                
            } catch (UnreadableException ex) {
                Logger.getLogger(ProtocoloQueryPedirDataSet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
        @Override
        protected void handleFailure(ACLMessage falha) {
            System.out.println(this.myAgent.getLocalName() + ": ocorreu uma falha.\n");
        }
    }



/*COMO ESTAVA PRIMEIRAMENTE
//Criamos a mensagem da consulta 
        ACLMessage mensagem = new ACLMessage(ACLMessage.QUERY_IF);
        mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_QUERY);
        mensagem.setContent("Tens DataSet da mineração?");
 
        
        //Aqui no pedirDataSet deveria fazer igual ao Chamamento: fazer uma busca por serviços em vez de fazer como ta abaixo
        AID id = new AID();
        id.setLocalName("Data");
        mensagem.addReceiver(id);

*/