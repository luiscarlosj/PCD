/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineracao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.clustering.DataPoint;

/**
 *
 * @author LUIS
 */
public class ClusterGlobal implements Serializable {
    
        private static ClusterGlobal uniqueInstance;   
        private int contador = 0;
        private int contador2 = 0;
        
        private ClusterGlobal () {
	 
        }

	public static synchronized ClusterGlobal getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ClusterGlobal ();

		return uniqueInstance;
	}
    
        private Map<Integer, java.util.List<DataPoint>> clusterGlobal = new HashMap<Integer, java.util.List<DataPoint>>();
        
        private Map <Integer, SecCluster> clusterLocal = new HashMap();

        public Map<Integer, SecCluster> getClusterLocal() {
           return clusterLocal;
        }

        public Map<Integer, List<DataPoint>> getClusterGlobal() {
           return clusterGlobal;
        }

        public void setClusterGlobal(Map<Integer, List<DataPoint>> clusterGlobal) {
           this.clusterGlobal = clusterGlobal;
        }        
        
        public void guardarClusterLocal(SecCluster sc) 
        {
            this.contador2++;            
            this.clusterLocal.put(contador2, sc);            
                    
        }
        
        private Integer points = 0;

        public Integer getPoints() {
           return this.points;
        }

        public void setPoints(Integer points) {
           this.points = points;
        }
        
        public void somaPoints(Integer points){
              this.points = this.getPoints() + points;
        }
        
        public void agrupaCluster(SecCluster sc) 
        {
            this.contador++;
            
            this.somaPoints(sc.getPoints());
            this.guardarClusterLocal(sc);
            
            for (int i=0 ; i < sc.getSecClusterMap().keySet().size(); i++ )
            {
                 this.contador++;
                 
                 this.clusterGlobal.put(contador, sc.getSecClusterMap().get(i));
            } 
        }
        
        public void agrupaCluster(Map<Integer, List<DataPoint>> clusterLocal) 
        {
            this.contador++;
            
            for (int i=0 ; i < clusterLocal.keySet().size(); i++ )
            {
                 this.contador++;
                 
                 this.clusterGlobal.put(contador, clusterLocal.get(i));
            }            
                 
        }
    
}
