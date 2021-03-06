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
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREInitiator;
import java.io.File;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineracao.Parametros;
import mineracao.Resultado;

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
                    mensagem.setContent("Tens DataSet da mineração?");
                    
                    //Indicamos o tempo que esperaremos pelas ofertas.
                    //mensagem.setReplyByDate(new Date(System.currentTimeMillis() + 15000));
 
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
                File fileMiner = (File) inform.getContentObject();  //***
                
                if(fileMiner != null)
                System.out.println(this.myAgent.getLocalName()+" recebeu o arquivo: "+ fileMiner.getAbsolutePath()); //***
                
                                
            } catch (UnreadableException ex) {
                Logger.getLogger(ProtocoloQueryPedirDataSet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
        @Override
        protected void handleFailure(ACLMessage falha) {
            System.out.println(this.myAgent.getLocalName() + ": ocorreu uma falha.\n");
        }
    }

/*
Parametros param = Parametros.getInstance();
        Resultado results = Resultado.getInstance();
*/


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

/*
System.out.println("RECUPERANDO NOME DO ARQUIVO 1: \n" 
                   + (this.pm.getArqAgent(this.myAgent.getAID().getLocalName())).getAbsolutePath());
*/