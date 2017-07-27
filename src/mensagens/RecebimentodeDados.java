/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import agentes.HelperAgent;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import jade.proto.AchieveREResponder;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineracao.ClusterGlobal;
import mineracao.Clustering;
import mineracao.SecCluster;
import model.clustering.DataPoint;

/**
 *
 * @author LUIS
 */
public class RecebimentodeDados extends SequentialBehaviour{
    
    public RecebimentodeDados(Agent a) {
        super(a);
    }
    
    @Override
    public void onStart() 
     {
         System.out.println(myAgent.getLocalName() + ": Aguardando solicitação...");

         MessageTemplate protocolo = MessageTemplate.MatchProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
         MessageTemplate performativa = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
         MessageTemplate padrao = MessageTemplate.and(protocolo, performativa);

        try {
            addSubBehaviour(new ProtocoloRequestRecebimentoDados( (HelperAgent) myAgent, padrao));
        } catch (FIPAException ex) {
            Logger.getLogger(RecebimentodeDados.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}

class ProtocoloRequestRecebimentoDados extends AchieveREResponder {
                
                private static final long serialVersionUID = 1L;            
    
                ClusterGlobal cg = ClusterGlobal.getInstance();
                SecCluster sc = new SecCluster();            
                
                public ProtocoloRequestRecebimentoDados ( Agent a , MessageTemplate mt) throws FIPAException {
			super(a, mt) ;
		}

		@Override
		protected ACLMessage prepareResponse (ACLMessage request ) throws NotUnderstoodException , RefuseException 
                {

			System.out.println(myAgent.getLocalName () + ": recebeu uma solicitação de " + request.getSender().getLocalName() + " para envio de seu clustering parcial") ;
                       
                        if(request.getSender() != null) 
                        {
                            
                            try { 
                                this.sc = (SecCluster) request.getContentObject();
                            } catch (UnreadableException ex) {
                                Logger.getLogger(ProtocoloRequestRecebimentoDados.class.getName()).log(Level.SEVERE, null, ex);
                            }
                           
                            if(this.sc.getSecClusterMap() != null)
                            {
                                System.out.println( "Agent " + myAgent.getLocalName () + ": recebendo dados de "+request.getSender().getLocalName()) ;
                                System.out.println(this.sc.getSecClusterMap().keySet().size() + " clusters found with "+this.sc.getPoints()+ " data points.\n");
                                
                                this.cg.agrupaCluster(this.sc);
                                
                                System.out.println(this.sc.getSecClusterMap().keySet().size() + " clusters found with "+this.sc.getPoints()+ " data points.\n");                                                  
                                ACLMessage agree = request.createReply () ;
                                agree.setPerformative (ACLMessage.AGREE) ;
                                return agree; // envia mensagem AGREEE
                                
                            } else
                            {
                                //Envia Mensagem REFUSE com o motivo
                                System.out.println( myAgent.getLocalName () + ": não está recebendo arquivos no momento.") ;
                                throw new RefuseException ( "sem recebimento de arquivos no momento") ;
                            }
                        }
                        else
                        {
                            // envia mensagem NOT UNDERSTOOD	
                            throw new NotUnderstoodException ( "Agent %s não entendeu a mensagem" + myAgent.getLocalName ()) ;
                        }                   
                      
                }

		// Prepara resultado final, caso tenha enviado mensagem AGREE
                @Override
		protected ACLMessage prepareResultNotification (ACLMessage request, ACLMessage response ) throws FailureException 
                {
		    //if (this.contador != 0) 
                    if(this.sc.getSecClusterMap() != null)
                    {
                        
                       System.out.println(myAgent.getLocalName () + ":dados recebidos com sucesso.");                    
                       ACLMessage inform = request.createReply();
                       inform.setPerformative(ACLMessage.INFORM);

                       return inform; // envia mensagem INFORM
                    } else {
                        
                      System.out.println(myAgent.getLocalName () + ": abortou recebimento");
                      throw new FailureException("abortou recebimento de dados");
                    }
                }
}

/*
private void agrupaCluster(Map<Integer, List<DataPoint>> clusterLocal) 
                {
                    this.cg.agrupaCluster(clusterLocal); 
                     
                    if (this.contador == this.quantAgentes)
                    {
                        System.out.println("PRONTO!!!");
                        System.out.println(this.cg.getClusterGlobal().keySet().size() + " clusters found with");
                    }
                }  
                
                private int quantAgente() throws FIPAException
                {
                    ServiceDescription servico = new ServiceDescription();
                    servico.setType("mineracao");
                    servico.setName("clusteringMiner");
 
                    DFAgentDescription descripcion = new DFAgentDescription();
                    descripcion.addLanguages("portugues");
                    descripcion.addServices(servico);       
        
       
                    DFAgentDescription[] resultados = DFService.search(this.myAgent, descripcion);
                    
                    return resultados.length;
                }


*/

/*
 int quantidadeAgentesMiner = 4;
                
                String[] registroDeAgentes = new String[quantidadeAgentesMiner]; 
                
                public String[] getRegistroDeAgentes() {
                    return registroDeAgentes;
                }

                public void setRegistroDeAgentes(String[] registroDeAgentes) {
                    this.registroDeAgentes = registroDeAgentes;
                }
                
                public void imprimiAgentes(){
                     for (int i=0; i<quantidadeAgentesMiner; i++)
                         if(registroDeAgentes[i] != null) 
                            System.out.println(registroDeAgentes[i]);
                }                       
                
                public void adicionaAgente(String agente){
                   
                    for (int i=0; i<quantidadeAgentesMiner; i++)
                        if(registroDeAgentes[i] != null)
                            registroDeAgentes[i] = agente;
                }

                public boolean verificaAgente(String verifica){
                    
                    for (int i=0; i<quantidadeAgentesMiner; i++)
                        if(registroDeAgentes[i].equals(verifica))
                            return true;
                                      
                    return false;   
                }

*/