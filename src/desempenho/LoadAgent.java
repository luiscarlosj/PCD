/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desempenho;

import java.io.File;
import java.util.concurrent.TimeoutException;
import junit.framework.TestCase;
import mineracao.ClusterGlobal;
import mineracao.Parametros;
import mineracao.Resultado;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author LUIS
 */
public class LoadAgent extends TestCase {

    Parametros pm;
    ClusterGlobal cg;
    Resultado rs;
    

    public LoadAgent(String testName) {
        super(testName);        
        pm = Parametros.getInstance();
        cg = ClusterGlobal.getInstance();
        rs = Resultado.getInstance();
    }
    
    @Test
    public void testParametros() {
        String[] param = {"0,0", "1000000,1000000", "10000,10000", "10000.0", "3", "1", "2.0", "Gauss"};
        pm.setParametros(param);

        assertNotNull(pm.getParametros());
    }

    @Test
    public void testDataset() {
        File[] f = {new File("C:/Users/LUIS/Documents/Algoritmos_Josenildo/s1.txt"),
            new File("C:/Users/LUIS/Documents/Algoritmos_Josenildo/s1.txt"),
            new File("C:/Users/LUIS/Documents/Algoritmos_Josenildo/s1.txt")};

        pm.setFile(f);
        assertNotNull(pm.getFile());
    }
    
    @Test
    public void testJade() {

        int numMinerAgent = 1;
        int numDataAgent = 1;
        int numManagerAgent = 1;
        int numHelperAgent = 1;
        pm.setFileEscolhido(-1);

        String[] param = {"Gauss", "10000.0", "3", "1", "0,0", "1000000,1000000", "10000,10000", "2.0"};      

        File[] f = {new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_100k.txt")};
        
        pm.setParametros(param);
        pm.setFile(f);

        String agentes = "";

        for (int i = 0; i < numMinerAgent; i++) {
            agentes = agentes + "Miner" + Integer.toString(i + 1) + ":agentes.MinerAgent(clusteringMiner);";
        }

        for (int i = 0; i < numDataAgent; i++) {
            agentes = agentes + "DataSet" + Integer.toString(i + 1) + ":agentes.DataSetAgent(clusteringData);";
        }

        for (int i = 0; i < numManagerAgent; i++) {
            agentes = agentes + "Manager" + Integer.toString(i + 1) + ":agentes.ManagerAgent(clusteringManager);";
        }

        for (int i = 0; i < numHelperAgent; i++) {
            agentes = agentes + "Helper" + Integer.toString(i + 1) + ":agentes.HelperAgent(clusteringHelper);";
        }

        String[] parametros = {"-agents", agentes};        
        jade.Boot.main(parametros);
    
        
    }

}


/*

String [] param = {"Gauss", "0.5", "1", "1", "0,0", "100,100", "1,1", "0.1"};

File[] f = {new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\spiral_part1.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\spiral_part2.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\spiral_part3.txt")};

String[] param = {"Gauss", "10000.0", "3", "1", "0,0", "1000000,1000000", "10000,10000", "2.0"};      

        File[] f = {new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_100k_part1.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_100k_part2.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_100k_part3.txt")};

//String [] param = {Kernel, H, Neighborhood, Step, Low, High, Tau, Threshold};

*/

//assertEquals(3, pm.quantFile());

/*

@Test
    public void testJade2() {
        String[] parametros = {"-agents", "Agent1:desempenho2.Agente"};
        jade.Boot.main(parametros);
    }

*/

/*
//String[] parametros = {"-gui"};      jade.Boot.main(parametros);
        
        //String container = "Meu-container"; 
        //String[] novoContainer = {"-container", "-container-name",container, agentes};
        //jade.Boot.main(novoContainer);

*/

/*
File[] f = {new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\spiral_part1.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\spiral_part2.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\spiral_part3.txt")};

*/


/*
@DisplayName("Repeat!")
    @Test
    @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    public void customDisplayName(TestInfo testInfo) {
		assertEquals(testInfo.getDisplayName(), "Repeat! 1/1");
    }
*/

/*
@Test
    public void testTestJade() throws InterruptedException, TimeoutException {
        System.out.println("testJade");
        
        Thread testThread;
        testThread = new Thread() {
            @Override
            public void run() {
                AgenteTest2 instance = new AgenteTest2();
                instance.testJade();
            }
        };
        
        testThread.start();        
        Thread.sleep(35000);
        testThread.interrupt();

        if (testThread.isInterrupted()) {
            throw new TimeoutException("the test took too long to complete");
        }
        
    }
*/