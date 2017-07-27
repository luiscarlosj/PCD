/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensagens;

import agentes.DataSetAgent;
import jade.core.Agent;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.FIPAAgentManagement.FailureException;
import jade.domain.FIPAAgentManagement.NotUnderstoodException;
import jade.domain.FIPAAgentManagement.RefuseException;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.proto.AchieveREResponder;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import mineracao.Parametros;
//import protocolos.ProtocoloQueryEnviarDataSet;

/**
 *
 * @author LUIS
 */
public class EnviarDataSet extends SequentialBehaviour{

    public EnviarDataSet(Agent a) {
        super(a);
    }
    
    public void onStart() 
    {
        MessageTemplate template = AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_QUERY);
 
        addSubBehaviour(new ProtocoloQueryEnviarDataSet( (DataSetAgent) myAgent, template));
    }   
}

class ProtocoloQueryEnviarDataSet extends AchieveREResponder {
    
        Parametros pm = Parametros.getInstance();     
        int fileEscolhido= -1; 
        File fileParaMiner = null;
        
        
    
        public ProtocoloQueryEnviarDataSet(Agent agente, MessageTemplate template) {
            super(agente, template);
        }
 
        @Override
        protected ACLMessage handleRequest(ACLMessage request) throws NotUnderstoodException, RefuseException {
            
            System.out.printf("Recebemos uma chamade de %s solicitando um DataSet.\n", request.getSender().getLocalName());
            
            if (this.pm.quantFile() != 0) {
                
                System.out.println("Tenho dataset. Aguarde um momento...\n");
                ACLMessage agree = request.createReply();
                agree.setPerformative(ACLMessage.AGREE);
                return agree;
                
            } else {
                
                System.out.println(this.myAgent.getLocalName() + ": não tenho datasets no momento.\n");
                throw new RefuseException("Tentar mais tarde");
            }
        }
 
        @Override
        protected ACLMessage prepareResultNotification(ACLMessage request, ACLMessage response) throws FailureException {
            
            ACLMessage inform = request.createReply();
            inform.setPerformative(ACLMessage.INFORM);
 
            System.out.println("Enviando DataSet para " + request.getSender().getLocalName());
            
            try {
                this.fileParaMiner= pm.posicaoFile(parseInt(""+(request.getSender().getLocalName()).charAt(5))-1);
                
                inform.setContentObject(fileParaMiner);
                
                //AQUI SERVE PARA GUARDA A REFERENCIA DOS ARQUIVOS COM OS MINERAGENTS
                this.pm.setArq(request.getSender().getLocalName(), fileParaMiner);
                
            } catch (IOException ex) {
                Logger.getLogger(ProtocoloQueryEnviarDataSet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return inform;
        }
 
        
}


//File f = new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_part1.txt");
            //File [] fs = {f};
            //this.pm.setFile(fs);
            
            
            //Verifica se a solicitação é; aceita ou recusa
            //Aqui poderia verificar se o agente que solicita está realmente na arquitetura
            //Melhorar 
            //if (comprobarSolicitante(request.getSender().getLocalName())) {
            
            //if (((DataSetAgent) this.myAgent).isTemDataSet() == false) { 

/*
//método que faz a distribuição dos datasets
        //File fileParaMiner = this.escolhaDoDataSet();
        private File escolhaDoDataSet(){
            
            System.out.println("*************************************Quantidade de Arquivos: "+ pm.quantFile() + " valor da variável: "+ this.fileEscolhido);
            
            File f = null;
            
            if (pm.quantFile() == 0){
                System.out.println("Não tem arquivo");
                return f;
            }
            
            if(pm.quantFile() == 1) {
                
                f = pm.getPrimeiroFile();                
                return f;            
            }
            else{                
                this.fileEscolhido++;
                System.out.println("*************************************ENTROU AQUI. Valor = "+this.fileEscolhido);
                f = pm.posicaoFile(fileEscolhido);
                return f;              
            }
             
        }


*/