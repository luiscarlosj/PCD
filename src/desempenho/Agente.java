/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desempenho;

import jade.core.Agent;

/**
 *
 * @author LUIS
 */
public class Agente extends Agent{
    
    int a = 10;

    public int getA() {
        return a;
    }
    
    public boolean comparar(int b){
       return a==b;
    }
            
    
    @Override
    protected void setup(){
        
        System.out.println("Testando agente AQUI");
        addBehaviour(new ComportamentoT(this));
    }
    
}
