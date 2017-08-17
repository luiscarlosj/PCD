


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
import jade.proto.AchieveREInitiator;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import mineracao.Clustering;
import mineracao.Parametros;
import mineracao.Resultado;
import mineracao.SecCluster;
/**
 *
 * @author LUIS
 */
public class EnviodeDados extends SequentialBehaviour{
    
    Object [] args = null; 
    Clustering c;
    File fileMiner;
    Parametros param = Parametros.getInstance();
    Resultado  results = Resultado.getInstance();
    
    public EnviodeDados(Agent a,  Object [] arg) {
        super(a);
        this.args = arg;
    }
    
    @Override
    public void onStart() 
     {
        
        ServiceDescription servico = new ServiceDescription();
        servico.setType("mineracao");
        servico.setName("clusteringHelper");
 
        DFAgentDescription descripcion = new DFAgentDescription();
        descripcion.addLanguages("portugues");
        descripcion.addServices(servico);       
        
        try {
                DFAgentDescription[] resultados = DFService.search(myAgent, descripcion);
                
                if (resultados.length <= 0) {
                    System.out.println("Não existem agentes com este tipo de serviço: " + servico.getType());
                } else {
                    
                System.out.println("Resultado da pesquisa: tem " + resultados.length + " agentes com este serviço");
 
                ACLMessage mensagem = new ACLMessage(ACLMessage.REQUEST);
                  
                for (DFAgentDescription agente:resultados) {
                    
                        if(!agente.getName().equals(myAgent.getAID()) ){
                            System.out.printf("Agente %s envia para %s\n", myAgent.getAID().getLocalName(), agente.getName().getLocalName());
                            mensagem.addReceiver(agente.getName());}
                }
                    
                mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
                
                this.fileMiner = this.param.getArqAgent(this.myAgent.getAID().getLocalName()); 
                
                //AQUI ACONTECE A INTEGRAÇÃO DA ARQUITETURA COM O ALGORITMO DE CLUSTERING KDECS                
                this.c = new Clustering (this.fileMiner); 
                
                SecCluster sc = new SecCluster();
                
                sc.setSecClusterMap(this.c.getSecClusterMap());
                
                sc.setPoints(this.c.getDs().size());
                
                this.results.addResultAgent(myAgent.getAID().getLocalName(), 
                              Integer.toString(sc.getSecClusterMap().keySet().size())  + " clusters found with " 
                              + Integer.toString(sc.getPoints()) + " data points.\n");
                                
                mensagem.setContentObject((Serializable) sc);
                
                //Indicamos o tempo que esperaremos pelas ofertas.
                mensagem.setReplyByDate(new Date(System.currentTimeMillis() + 15000));
 
                //Adicionamos o comportamento da consulta
                addSubBehaviour (new ProtocoloRequestEnviodeDados( (MinerAgent) myAgent, mensagem));
                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
		
     }
    
}

class ProtocoloRequestEnviodeDados extends AchieveREInitiator {

		private static final long serialVersionUID = 1L;
                
                public ProtocoloRequestEnviodeDados(Agent a, ACLMessage msg) {
                   super(a, msg);
                }
                
                @Override
		protected void handleAgree (ACLMessage agree ) {
			System.out.println( "Agente " + agree.getSender().getLocalName() + " irá receber clustering_parcial") ;
                }
		
                @Override
		protected void handleRefuse (ACLMessage refuse ) {
			System.out.println( "Agente " + refuse.getSender().getLocalName()+ " que não está recebendo arquivo no momento");
		}
		
                @Override
		protected void handleNotUnderstood (ACLMessage notUnderstood ) {
			System.out.println( "Agente " + notUnderstood.getSender () . getLocalName () + 
					"por algum motivo não entendeu a solicitação") ;
		}
		
                @Override
		protected void handleFailure (ACLMessage failure ) {
			if ( failure.getSender().equals(myAgent.getAMS())){
				System.out.println( "Agente falhou em receber arquivo") ;
			}
		        else {
				System.out.println( "Falha no Agent " +
				failure.getSender().getName()+ ": " + failure.getContent().substring(1 , failure.getContent().length() - 1));
			}
		}

                @Override
		protected void handleInform (ACLMessage inform ) {
			System.out.println("Agente " + inform.getSender().getLocalName() + " informa que recebeu arquivo com sucesso");
                }
	}


/*
System.out.println("Solicitando envio de clustering_parcial...");

         ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);

         for (int i = 0; i < args.length; i++) {
             msg.addReceiver(new AID((String) args[i], AID.ISLOCALNAME));
         }

         msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
         msg.setContent("enviar clustering_parcial 4");                

	// adicionamos primeiro comportamento - Comportamento 1
        addSubBehaviour (new ProtocoloRequestEnviodeDados(myAgent, msg));

*/

/*
/*c = new Clustering (this.myAgent.getLocalName()); 
                
                SecCluster sc = new SecCluster();
                
                sc.setSecClusterMap(c.getSecClusterMap());
                
                sc.setPoints(c.getDs().size());
                
                Resultado r = Resultado.getInstance();
                
                r.addResultAgent(myAgent.getAID().getLocalName(), 
                              Integer.toString(sc.getSecClusterMap().keySet().size())  + " clusters found with " 
                              + Integer.toString(sc.getPoints()) + " data points.\n");*/
                
                //System.out.print("Done.\n " + secClusterMap.keySet().size() + " clusters found with " + ds.size() + " data points.\n");
           
                //((MinerAgent) this.myAgent).setSecClusterMap(c.getSecClusterMap()); aqui
                
                //mensagem.setContentObject((Serializable) this.c);
                //SecCluster sc = this.results.devolveSecClusterPorMiner(this.myAgent.getAID().getLocalName());

/*
System.out.println("RECUPERANDO NOME DO ARQUIVO 2: \n" + this.fileMiner.getAbsolutePath());
                
                if (this.fileMiner == null){
                    done();
                    System.out.println("ENCERRADO PORQUE O ARQUIVO É NULL");
                }
                else
                {
                   System.out.println("NÃO FOI ENCERRADO PORQUE TEM ARQUIVO NÃO FOI ENCERRADO PORQUE TEM ARQUIVO");
                }

*/

/*if (sc == null)
                    System.out.println("NULO NULO NULO NULO NULO NULO NULO NULO NULO NULO NULO NULO NULO NULO NULO");
                else
                    System.out.println("TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM TEM");
*/                
                