/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineracao;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LUIS
 */
public class Resultado {
        
        private static Resultado uniqueInstance;   
        
        private Resultado () {
	 
        }

	public static synchronized Resultado getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Resultado();

		return uniqueInstance;
	}
        
        Map<String, String> results = new HashMap();
        
        public void addResultAgent(String nomeAgent, String resultado){
              results.put(nomeAgent,resultado);
        }
        
        public Map<String, String> imprimeResultados(){           
            return results;
        }
        
}

/* Map<String, SecCluster> SecClusterPorMiner = new HashMap();

        public Map<String, SecCluster> getSecClusterPorMiner() {
           return SecClusterPorMiner;
        }

        public void setSecClusterPorMiner(Map<String, SecCluster> SecClusterPorMiner) {
           this.SecClusterPorMiner = SecClusterPorMiner;
        }
        
        public void addSecClusterPorMiner(String nomeAgenteMiner, SecCluster secClusterMiner){        
            this.SecClusterPorMiner.put(nomeAgenteMiner, secClusterMiner);                
        }
        
        public SecCluster devolveSecClusterPorMiner(String nomeAgenteMiner){           
            return this.SecClusterPorMiner.get(nomeAgenteMiner);        
        }*/
