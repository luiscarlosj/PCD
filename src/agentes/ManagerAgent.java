
package agentes;

import comportamentos.RegistroNaArquitetura;
import jade.core.Agent;
import mensagens.ConfirmarParametros;

/**
 *
 * @author LUIS
 */
public class ManagerAgent extends Agent{

        private static final long serialVersionUID = 1L;
        
        private boolean temParametros = false; //false se ainda não foi negociado; true se já negociado
        
        public boolean isTemParametros() {
            return temParametros;
        }

        public void setTemParametros(boolean temParametros) {
            this.temParametros = temParametros;
        }
        
        @Override
        protected void setup ( ) {            
                Object [ ] args = getArguments ();
            
                addBehaviour (new RegistroNaArquitetura(this, args));  
                
                //System.out.println("*********************FASE DO PEDIDO DE PARÂMETROS*********************\n\n\n");
                addBehaviour (new ConfirmarParametros(this)); 
         
        }
	
	//PROCURANDO UMA UTILIDADE PARA ESTE AGENTE
    
}
