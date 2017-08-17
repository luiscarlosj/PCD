/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desempenho;

import java.io.File;
import mineracao.Parametros;

/**
 *
 * @author LUIS
 */
public class LoadAgent2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Parametros pm = Parametros.getInstance();
         
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
