/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineracao;


import jade.util.leap.Serializable;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LUIS
 */
public class Parametros implements Serializable {
        
        private static Parametros uniqueInstance;  
        int fileEscolhido= -1; 
        
        private Parametros () {
	 
        }

	public static synchronized Parametros  getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new Parametros ();

		return uniqueInstance;
	}
        
        String [] param = new String [8];
        File [] file = null;          

        public File[] getFile() {
           return file;
        }

        public void setFile(File [] file) {
            this.file = file;
        }
        
        public int quantFile(){
           return this.file.length;
        }
        
        public File posicaoFile(int a) {
           return this.file[a];
        }
        
        public File getPrimeiroFile() {
           return this.file[0];
        }

         public String[] getParametros() {
            return param;
         }

         public void setParametros(String[] parametros) {
            this.param = parametros;
         }
         
         public void imprimirParametros(String[] parametros) {
            
            for(String pm : parametros)
                System.out.println(pm);
        }
         
         public void imprimirParametros() {
            
            for(String pm : param)
                System.out.println(pm);
        }
        
         public void imprimirFiles(File[] file) { 
            for (File fil : file)
            {
                System.out.println(fil.getAbsolutePath() +"\n");
            }
        }
         
        Map<String, File> arq = new HashMap();
        
        public Map<String, File> getArq() {
          return arq;
        }

        public void setArq(String a, File f) {
          this.arq.put(a, f);
        }
        
        public File getArqAgent(String a){
           return arq.get(a);
        } 
        
        public File getArqAgent(){
           this.fileEscolhido++;             
           return this.posicaoFile(this.fileEscolhido);
        } 
        
}
   

/*
Map<Integer, java.util.List<DataPoint>> secClusterMap = null;        

        public Map<Integer, List<DataPoint>> getSecClusterMap() {
          return secClusterMap;
        }

        public void setSecClusterMap(Map<Integer, List<DataPoint>> secClusterMap) {
           this.secClusterMap = secClusterMap;
        }

*/