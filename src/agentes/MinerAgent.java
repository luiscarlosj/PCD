/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import comportamentos.RegistroNaArquitetura;
import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.WakerBehaviour;
import java.io.File;
import java.util.List;
import java.util.Map;
import mensagens.AguardarChamamento;
import mensagens.ChamarAgentes;
import mensagens.ConfirmarParametros;
import mensagens.EnviodeDados;
import mensagens.PedirDataSet;
import mensagens.PedirParametros;
import mineracao.ClusterGlobal;
import mineracao.Parametros;
import model.clustering.DataPoint;

/**
 *
 * @author LUIS
 */
public class MinerAgent extends Agent{
    
        private static final long serialVersionUID = 1L;
        
        ClusterGlobal cg = ClusterGlobal.getInstance();
        
        private boolean participaSessao = false; //false se não participa da sessão de mineração
        private boolean temParametros; //false se ainda não foi negociado; true se já negociado
        private File meuArquivo;

        public File getMeuArquivo() {
          return meuArquivo;
        }

        public void setMeuArquivo(File meuArquivo) {
          this.meuArquivo = meuArquivo;
        }
        private Parametros param;
        
        Object [ ] args;

        public Object[] getArgs() {
           return args;
        }

        public void setArgs(Object[] args) {
          this.args = args;
        }
        
        String arg1;

        public boolean isTemParametros() {
            return temParametros;
        }

        public void setTemParametros(boolean temParametros) {
            this.temParametros = temParametros;
        }
        
        public Parametros getP() {
           return param;
        }

        public void setP(Parametros p) {
           this.param = p;
        }

        public void setParticipaSessao(boolean participaSessao) {
           this.participaSessao = participaSessao;
        }

        public boolean isParticipaSessao() {
           return participaSessao;
        }
        
        public MinerAgent() {
        
        }
        
        @Override
        protected void setup () {
           this.teste();            
        }    
        
        protected void teste(){
            
            long tempInicial = System.currentTimeMillis();
         
            args = getArguments ();  
            //args = getArgs();
            
		FSMBehaviour compFSM = new FSMBehaviour ( this ) {
			public int onEnd ( ) {
				System.out.println( "Comportamento FSM finalizado com sucesso!");
				return 0 ;
			}
		} ;
                
                //System.out.println("*********************FASE DO REGISTRO*********************\n\n\n");
                compFSM.registerFirstState(new OneShotBehaviour ( this ) {

			int c=0;

			public void action() {
				
                                addBehaviour (new RegistroNaArquitetura(this.myAgent, args));
				c++;
			}

			public int onEnd ( ) {
				return ( c>0 ? 0 : 1 );
			}
		} , "A" );
                
                //System.out.println("*********************PRIMEIRA FASE - CHAMAMENTO*********************\n\n\n");
		
		compFSM.registerState(new OneShotBehaviour ( this) {
                        int c=0;
                        
			long t0 = System.currentTimeMillis();
                        
                        public void action (){                            
                       
                        addBehaviour (new AguardarChamamento(this.getAgent()));
                        addBehaviour (new ChamarAgentes(this.getAgent()));
                        c++;
                        
                        }
                        
                        public int onEnd ( ) {
				return ( c>0 ? 0 : 1 );
			}
			
		} , "B");

                //System.out.println("*********************SEGUNDA FASE - PEDIDO DO DATASET*********************\n\n\n");
		
                compFSM.registerState(new OneShotBehaviour ( this) {

			public void action() {
				
                                addBehaviour (new PedirDataSet(this.myAgent));
				
			}
                        

		} , "C" );

                //System.out.println("*********************TERCEIRA FASE - PEDIDO DE PARÂMETROS*********************\n\n\n");
		
		compFSM.registerState(new OneShotBehaviour ( this ) {

			int c=0;

			public void action() {
				
                                addBehaviour (new PedirParametros(this.myAgent));
                                //addBehaviour (new ConfirmarParametros(this.myAgent));
				c++;
			}

			public int onEnd ( ) {
				return ( c>0 ? 0 : 1 );
			}
		} ,"D");
                
                
                
                compFSM.registerState(new OneShotBehaviour ( this ) {

			int c=0;

			public void action() {
				//System.out.println("*********************QUARTA FASE - ENVIO DE DADOS*********************\n\n\n");
                                addBehaviour (new EnviodeDados(this.myAgent, args));
				c++;
			}

			public int onEnd ( ) {
				return ( c>0 ? 0 : 1 );
                        }
                        
                        
                        
		} ,"E"); 
                
                compFSM.registerLastState(new OneShotBehaviour ( this ) {

			int c=0;

			public void action() {
                            
				long tempFinal = System.currentTimeMillis();
                                long dif = (tempFinal - tempInicial);
        
                                System.out.printf("%s >>>> %02d segundos %02d milisegundos \n\n", this.myAgent.getLocalName(), dif/1000, dif%1000);
			}

			public int onEnd ( ) {
				return ( c>0 ? 0 : 1 );
                        }          
                        
                        
		} ,"F");
                
                
                
                // definimos as transições
		compFSM.registerTransition( "A" , "B" , 0) ; //A --> B, caso onEnd() do A retorne 0
		compFSM.registerTransition( "B" , "C" , 0) ; //B --> C, caso onEnd() do B retorne 1
                compFSM.registerTransition( "C" , "D" , 0) ; //B --> C, caso onEnd() do B retorne 1
                compFSM.registerTransition( "D" , "E" , 0) ;
                compFSM.registerTransition( "E" , "F" , 0) ;
                //compFSM.registerTransition( "C" , "D" , 0) ;
                //compFSM.registerTransition( "C" , "D" , 0) ; //C --> D, caso onEnd() do D retorne 0
                //compFSM.registerTransition( "D" , "E" , 0) ; //C --> D, caso onEnd() do D retorne 0

		// definimos uma transi��o padr�o ( n�o importa tipo de retorno)
		//como a maquina � finita, temos que zerar os estados X e Z --> new String[] {"X" , "Z"} 
		
		compFSM.registerDefaultTransition( "B" , "C" , new String[] {"C","B"});

		// acionamos o comportamento
		addBehaviour(compFSM);
            
            
        }
}
