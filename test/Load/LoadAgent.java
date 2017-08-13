/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Load;

import com.clarkware.junitperf.ConstantTimer;
import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.TimedTest;
import com.clarkware.junitperf.Timer;
import junit.extensions.RepeatedTest;
import junit.framework.Test;
/**
 *
 * @author LUIS
 */
public class LoadAgent {
    
     public static Test suite() {
         
        int iteracoes = 10;
        int usuarios = 1;

        Timer tempo = new ConstantTimer(10000);

        Test testCase = new desempenho.AgenteTest("testJade");
        Test repeatedTest = new RepeatedTest(testCase, iteracoes);
        Test loadTest = new LoadTest(repeatedTest, usuarios, tempo);

        return loadTest;         
    }
}

