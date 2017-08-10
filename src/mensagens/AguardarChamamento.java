/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import agentes.MinerAgent;
//import comportamentos.MinerAgent;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.ContractNetResponder;
//import protocolos.ProtocoloCfpAguardarChamamento;

/**
 *
 * @author LUIS
 */
public class AguardarChamamento extends SequentialBehaviour {
    
    public AguardarChamamento(Agent a) {
        super(a);
    }
    
    @Override
    public void onStart() 
    { 
        //Se cria um template que filtre as mensagens a receber
        MessageTemplate template = 
                ContractNetResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_CONTRACT_NET);
 
        //Adicionamos os comportamentos antes das mensagens recebidas
        addSubBehaviour(new ProtocoloCfpAguardarChamamento((MinerAgent) myAgent, template));
    }
}


class ProtocoloCfpAguardarChamamento  extends ContractNetResponder {
    
    public ProtocoloCfpAguardarChamamento(MinerAgent a, MessageTemplate cfp) {
        super(a, cfp);
    }

    @Override
    protected ACLMessage prepareResponse(ACLMessage cfp) throws NotUnderstoodException, RefuseException {
        
        //Verificamos se já participa da sessão de mineração
        if (((MinerAgent) this.myAgent).isParticipaSessao() == false) {
            ((MinerAgent) this.myAgent).setParticipaSessao(true); 
             
            ACLMessage oferta = cfp.createReply();
            oferta.setPerformative(ACLMessage.PROPOSE);
            oferta.setContent("aceito participar da mineração");
            return oferta;

        } else {
            ACLMessage oferta = cfp.createReply();
            oferta.setPerformative(ACLMessage.REFUSE);
            oferta.setContent("Recusado");
            return oferta;
        }
    }

    @Override
    protected ACLMessage prepareResultNotification(ACLMessage cfp, ACLMessage propose, ACLMessage accept) throws FailureException {
        //***System.out.printf("Agente %s: possivel proposta de mineração.\n", this.myAgent.getLocalName());

        if (accept.getContent().equals("Confirmado")) {
            
            System.out.printf("Agente %s: convite aceito para mineração.\n", this.myAgent.getLocalName());

            ACLMessage inform = accept.createReply();
            inform.setPerformative(ACLMessage.INFORM);
            return inform;
            
        } else {
            System.out.printf("Agente %s: Falha ao enviar confirmação.\n", this.myAgent.getLocalName());
            
            ACLMessage inform = accept.createReply();
            inform.setPerformative(ACLMessage.FAILURE);
            return inform;
            //throw new FailureException("Error.");
        }
    }

    @Override
    protected void handleRejectProposal(ACLMessage cfp, ACLMessage propose, ACLMessage reject) {
        //Oferta foi rejeitada
        System.out.printf("Agente %s: Chamamento rejeitado.\n", this.myAgent.getLocalName());
        
               
    }
}