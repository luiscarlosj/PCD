/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desempenho;

import java.util.concurrent.TimeoutException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author LUIS
 */
public class AgenteTest2Test {
    
    /**
     * Test of testJade method, of class AgenteTest2.
     * @throws java.lang.InterruptedException
     * @throws java.util.concurrent.TimeoutException
     */
    
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
    
    
    
}
