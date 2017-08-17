/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desempenho;

import java.io.File;
import static junit.framework.TestCase.assertNotNull;
import mineracao.ClusterGlobal;
import mineracao.Parametros;
import mineracao.Resultado;

/**
 *
 * @author LUIS
 */
public class AgenteTest2 {
    
    Parametros pm;
    ClusterGlobal cg;
    Resultado rs;
    

    public AgenteTest2() {
        pm = Parametros.getInstance();
        cg = ClusterGlobal.getInstance();
        rs = Resultado.getInstance();
    }
    
   
    public void testParametros() {
        String[] param = {"0,0", "1000000,1000000", "10000,10000", "10000.0", "3", "1", "2.0", "Gauss"};
        pm.setParametros(param);

       
    }

    public void testJade() {

        int numMinerAgent = 3;
        int numDataAgent = 1;
        int numManagerAgent = 1;
        int numHelperAgent = 1;
        pm.setFileEscolhido(-1);

        String[] param = {"Gauss", "10000.0", "3", "1", "0,0", "1000000,1000000", "10000,10000", "2.0"};      

        File[] f = {new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_part1.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_part2.txt"),
            new File("C:\\Users\\LUIS\\Documents\\Algoritmos_Josenildo\\s1_part3.txt")};
        
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