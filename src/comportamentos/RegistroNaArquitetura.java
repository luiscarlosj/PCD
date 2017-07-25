/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comportamentos;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * @author LUIS
 */
public class RegistroNaArquitetura extends Behaviour{
    
    String serviceName;
    
    public RegistroNaArquitetura(Agent a, Object args []) {
	super(a); 
        serviceName = (String) args[0];
    }

    @Override
    public void action() {
        
        System.out.println("Registrando serviço de " + myAgent.getLocalName());
        
        ServiceDescription servico = new ServiceDescription();   // Descrição do Serviço
        servico.setType("mineracao");
        servico.setName(serviceName); 
        
        System.out.println(servico.getName());
        
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addLanguages("portugues");
        dfd.addServices(servico);
        
        try {
            DFService.register(myAgent, dfd);
            //System.out.println("Serviço registrado com sucesso");
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    public boolean done() {
        return true;
    }
  
}
