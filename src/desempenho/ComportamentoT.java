/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desempenho;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

/**
 *
 * @author LUIS
 */
public class ComportamentoT extends Behaviour{
    
    int i=0;
    
    public ComportamentoT(Agent a){
        super(a);
    }
    
    @Override
    public void action() {
        System.out.println("Testando agente"+i); 
        i++;
    }

    @Override
    public boolean done() {       
        return i>3;    
    }
    
    //done finaliza o comportamento se retornar true
    
    
}
