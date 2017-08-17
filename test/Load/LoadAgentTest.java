/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Load;


import com.clarkware.junitperf.ConstantTimer;
import com.clarkware.junitperf.LoadTest;
import com.clarkware.junitperf.Timer;
import desempenho.LoadAgent;
import junit.extensions.RepeatedTest;
import junit.framework.Test;
import junit.framework.TestSuite;
/**
 *
 * @author LUIS
 */
public class LoadAgentTest {
    
     public static Test suite() {
         
        int iteracoes = 1;
        int usuarios = 10;
        Timer tempo = new ConstantTimer(2000); 

        Test testCase = new desempenho.LoadAgent("testJade");
        Test repeatedTest = new RepeatedTest(testCase, iteracoes);
        Test loadTest = new LoadTest(repeatedTest, usuarios, tempo);
        
        return loadTest;
    }
     
}

/*
    int iteracoes = 10;
    Test testCase = new desempenho.LoadAgent("testJade");
    Test repeatedTest = new RepeatedTest(testCase, iteracoes);
       
    return repeatedTest; 
*/

/*
 Test testCase = new desempenho.LoadAgent("testJade");
        Test loadTest = new LoadTest(testCase, 1);  //com 100 usuários
        return loadTest;
*/

/*
int iteracoes = 10;
        int usuarios = 1;

        Timer tempo = new ConstantTimer(1000); //tempo para o Teste rodar

        Test testCase = new desempenho.LoadAgent("testJade"); //identifica qual metodo vai ser testado
        Test repeatedTest = new RepeatedTest(testCase, iteracoes);
        Test loadTest = new LoadTest(repeatedTest, usuarios, tempo);
        //Test loadTest = new LoadTest(testCase, usuarios);        
        //Test loadTest2 = new LoadTest(testCase, usuarios, iteracoes);

        return loadTest;

*/

/*
    Test timedTest = new TimedTest(testCase, 1000);//com tempo limite de 100ms
    Test loadTest = new LoadTest(timedTest, 100);

*/