/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import comportamentos.RegistroNaArquitetura;
import jade.core.Agent;
import mensagens.EnviarDataSet;
import mineracao.Parametros;

/**
 *
 * @author LUIS
 */
public class DataSetAgent extends Agent{
    
    Parametros pm = Parametros.getInstance();
    
    // começa com false; assim que inicia o programa e ocorre a leitura do arquivo essa variável recebe true;

    @Override
        protected void setup () {
            
            Object [ ] args = getArguments ();      
           
	    addBehaviour (new RegistroNaArquitetura(this, args));           
            
            //System.out.println("*********************FASE DO PEDIDO DO DATASET*********************\n\n\n");
            addBehaviour (new EnviarDataSet(this));
        
        }
        
        //DESENVOLVER CÓDIGO PARA GERENCIAR OS DATASETS       
}
