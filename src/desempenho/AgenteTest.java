/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desempenho;

import java.io.File;
import junit.framework.TestCase;
import mineracao.Parametros;
import org.junit.Test;

/**
 *
 * @author LUIS
 */
public class AgenteTest extends TestCase{
    
    private Agente agente;
    Parametros pm = Parametros.getInstance();
    
    public AgenteTest(String testName){
        super(testName);
    }
    
    @Override
    public void setUp() { 
       agente = new Agente();
    }
    
    @Test
    public void testParametros(){
        String [] param = {"0,0", "1000000,1000000", "10000,10000", "10000.0", "3", "1", "2.0", "Gauss"};  
        pm.setParametros(param);        
         
        assertNotNull(pm.getParametros());
    }
    
    @Test
    public void testDataset(){
        File[] f = {new File("C:/Users/LUIS/Documents/Algoritmos_Josenildo/s1_part1.txt"),
                    new File("C:/Users/LUIS/Documents/Algoritmos_Josenildo/s1_part2.txt"),
                    new File("C:/Users/LUIS/Documents/Algoritmos_Josenildo/s1_part3.txt"),};        
        
        pm.setFile(f);        
         
        assertNotNull(pm.getFile());
        
    }
    
    @Test
    public void testJade(){
        
        int numMinerAgent = 3;
        int numDataAgent = 1;
        int numManagerAgent = 1;
        int numHelperAgent = 1;
        
        String [] param = {"Gauss", "10000.0", "3", "1", "0,0", "1000000,1000000", "10000,10000", "2.0"};
        
        pm.setParametros(param);
        
        File[] f = {new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_part1.txt"),
                    new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_part2.txt"),
                    new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_part3.txt")};        
        
        pm.setFile(f); 
        pm.imprimirFiles(f);
        
        String container = "Meu-container";
        
        String agentes= "";
        
        for (int i=0; i< numMinerAgent; i++)
            agentes = agentes + "Miner" + Integer.toString(i+1) + ":agentes.MinerAgent(clusteringMiner);";
        
        for (int i=0; i< numDataAgent; i++)
            agentes = agentes + "DataSet" + Integer.toString(i+1) + ":agentes.DataSetAgent(clusteringData);";
            
        for (int i=0; i< numManagerAgent; i++)
            agentes = agentes + "Manager" + Integer.toString(i+1) + ":agentes.ManagerAgent(clusteringManager);";       
        
        for (int i=0; i< numHelperAgent; i++)
            agentes = agentes + "Helper" + Integer.toString(i+1) + ":agentes.HelperAgent(clusteringHelper);";
        
        String[] parametros = {"-gui"};		
        
	jade.Boot.main(parametros);                
                
        String[] novoContainer = {				
	"-container", "-container-name", container, agentes};	
        
        jade.Boot.main(novoContainer);
    }
    
    @Test
    public void testJade2(){        
         String[] parametros = {"-agents", "Agent1:desempenho2.Agente"};        
         jade.Boot.main(parametros); 
    }
    
}

//String [] param = {"Gauss", "0.5", "1", "1", "0,0", "100,100", "1,1", "0.1"};
//String [] param = {Kernel, H, Neighborhood, Step, Low, High, Tau, Threshold};
//assertEquals(3, pm.quantFile());
        