 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import agentes.ManagerAgent;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ProposeResponder;
import mineracao.Parametros;
/**
 *
 * @author LUIS
 */
public class ConfirmarParametros extends SequentialBehaviour{

    public ConfirmarParametros(Agent a) {
        super(a);
    }
    
    @Override
    public void onStart() 
    {
        //Nós criamos o modelo a ser usado apenas para receber mensagens com o protocolo FIPA_PROPOSE e performativa PROPOR
        MessageTemplate template = ProposeResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_PROPOSE);
 
        //Adicionamos o comportamento
        addSubBehaviour(new ProtocoloProposeConfirmarParametros((ManagerAgent) myAgent, template));    
    }
       
}

class ProtocoloProposeConfirmarParametros extends ProposeResponder {
    
        Parametros param = Parametros.getInstance();        
 
        public ProtocoloProposeConfirmarParametros(Agent agente, MessageTemplate template) {
            super(agente, template);
        }
        
        //Preparação de resposta. Recebe uma mensagem PROPOSE e, de acordo com o seu conteúdo, você aceita ou não.
        @Override
        protected ACLMessage prepareResponse(ACLMessage proposta)  throws NotUnderstoodException {
            
            System.out.printf("%s: proposição recebida de %s.\n", this.myAgent.getLocalName(), proposta.getSender().getLocalName());
             
            if (this.param.getParametros() != null) {
                
                System.out.printf("*********** %s: Temos parâmetros para mineração.\n", this.myAgent.getLocalName());               
                ACLMessage agree = proposta.createReply();               
                agree.setPerformative(ACLMessage.ACCEPT_PROPOSAL);
                
                return agree;
                
            } else {
 
                System.out.printf("%s: não tenho parâmetros.\n", this.myAgent.getLocalName()); 
                ACLMessage refuse = proposta.createReply();
                refuse.setPerformative(ACLMessage.REJECT_PROPOSAL); 
                return refuse;
            }
        }
    }

/*
try {
                    agree.setContentObject((Serializable) pm);
                } catch (IOException ex) {
                    Logger.getLogger(ProtocoloProposeConfirmarParametros.class.getName()).log(Level.SEVERE, null, ex);
                }

*/

/*
// System.out.printf("%s: Esperando pedido de parâmetros...\n", myAgent.getLocalName());       
*/