/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import comportamentos.RegistroNaArquitetura;
import jade.core.Agent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mensagens.RecebimentodeDados;
import model.clustering.DataPoint;

/**
 *
 * @author LUIS
 */
public class HelperAgent extends Agent{
    
    Map<Integer, java.util.List<DataPoint>> clusterGlobal = new HashMap();
    
    Object [ ] args;

    public void setClusterGlobal(Map<Integer, List<DataPoint>> clusterGlobal) {
        this.clusterGlobal = clusterGlobal;
    }

    public Map<Integer, List<DataPoint>> getClusterGlobal() {
        return clusterGlobal;
    }

    @Override
    protected void setup ( ) {

                Object [ ] args = getArguments ();      
           
		addBehaviour (new RegistroNaArquitetura(this, args)); 
                
                //System.out.println("*********************QUARTA FASE - ENVIO DE DADOS*********************\n\n\n");
                addBehaviour (new RecebimentodeDados(this));
    }

}
