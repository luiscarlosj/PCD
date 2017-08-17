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
import jade.proto.ProposeInitiator;
import mineracao.Parametros;

/**
 *
 * @author LUIS
 */
public class PedirParametros extends SequentialBehaviour{

    public PedirParametros(Agent a) {
        super(a);
    }
    
    @Override
    public void onStart() 
    {
            ServiceDescription servico = new ServiceDescription();
            servico.setType("mineracao");
            servico.setName("clusteringManager");
 
            DFAgentDescription descripcion = new DFAgentDescription();
            descripcion.addLanguages("portugues");
            descripcion.addServices(servico); 
            
        try {
                DFAgentDescription[] resultados = DFService.search(myAgent, descripcion);
                 
                
                if (resultados.length <= 0) {
                    System.out.println("Não existem agentes com este tipo de serviço");
                } else {
                    
                    System.out.println("Resultado da pesquisa: tem " + resultados.length + " agentes com este serviço");
 
                    //Se cria uma mensagem PROPOSE. Se quer pedir permissão para sair da classe
                    ACLMessage mensagem = new ACLMessage(ACLMessage.PROPOSE);
                    mensagem.setProtocol(FIPANames.InteractionProtocol.FIPA_PROPOSE);
                                    
                    for (DFAgentDescription agente:resultados) {
                        
                        if(!agente.getName().equals(myAgent.getAID())) {
                            System.out.printf("%s para %s\n", myAgent.getAID().getLocalName(), agente.getName().getLocalName());
                            mensagem.addReceiver(agente.getName());
                        }
                    }
                    
                    mensagem.setContent("Vamos negociando parâmetros?");
                    
                    //Indicamos o tempo que esperaremos pelas ofertas.
                    //mensagem.setReplyByDate(new Date(System.currentTimeMillis() + 3000));
 
                    //cadastra o comportamento
                    addSubBehaviour(new ProtocoloProposePedirParametros( (MinerAgent) myAgent, mensagem));                   
                    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }   
    }
    
}

class ProtocoloProposePedirParametros extends ProposeInitiator{
    
    public ProtocoloProposePedirParametros(Agent a, ACLMessage msg) {
        super(a, msg); 
    }

    //Lidar com a resposta em caso de aceite: ACCEPT_PROPOSAL 
    @Override
    protected void handleAcceptProposal(ACLMessage aceitacao) {
        System.out.printf("%s: Negociação aceita.\n", this.myAgent.getLocalName());
        System.out.printf("%s: **********pediu parâmetros para %s**********\n", this.myAgent.getLocalName(), aceitacao.getSender().getLocalName());       
    }

    //Lidar com a resposta em caso de rejeição: REJECT_PROPOSAL
    @Override
    protected void handleRejectProposal(ACLMessage rejeicao) {
        System.out.printf("%s: negociação rejeitada. Agente %s já negociou parâmetros.\n", this.myAgent.getLocalName(), rejeicao.getSender().getLocalName());
        //System.out.printf("%s: Não recebendo parâmetros.\n", this.myAgent.getLocalName());
    }
    
    @Override
    protected void handleNotUnderstood(ACLMessage naoEntendeu){
        System.out.printf("%s: Minha mensagem não foi entendida por %s.\n", this.myAgent.getLocalName(), naoEntendeu.getSender().getLocalName());
    
    }
    
}


//dado[ 0 ] = new Dados ("word", 30,"texto");                    
                    //mensagem.setContentObject((Serializable) dado[0]);

/*
//System.out.printf("%s: **********pediu parâmetros para %s**********\n", this.getAgent().getLocalName(), aceitacao.getSender().getLocalName());

*/

/*p = (Parametros) aceitacao.getContentObject();
            //((MinerAgent) this.myAgent).setTemParametros(true);
            ((MinerAgent)this.getAgent()).setTemParametros(true);
            System.out.println(this.myAgent + "Pedir>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
                           + "TRUE se tem parametros e FALSE se não tem "+ ((MinerAgent)this.myAgent).isTemParametros());
                
            ((MinerAgent) this.myAgent).setP(p);
            p.imprimirParametros();

*/