/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineracao;


import control.clustering.KDEC_S;
import control.clustering.SecureEstimator;
import java.io.File;
import java.io.Serializable;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.clustering.DataPoint;
import model.clustering.DataSet;
import model.clustering.Grid;
import model.clustering.SecureDensityEstimate;
import persistence.DataSetDAO;
import persistence.DataSetDAOPlainFile;
import util.distances.DistanceFunction;
import util.distances.EuclideanDistance;
import util.kernels.GaussKernel;
import util.kernels.Kernel;

/**
 *
 * @author LUIS
 */
public final class Clustering {
    
        Map<Integer, java.util.List<DataPoint>> secClusterMap = null; 
        Parametros pm = Parametros.getInstance();
        private File currentFile = null;
        private DataSet ds;
        private File file = null;
        
        String SecLow;
        String SecHigh;
        String SecTau;
        String SecH;                
        String SecNeighborhood;  
        String SecStep;  
        String SecThreshold;           
        String SecKernel;   
        
    public DataSet getDs() {
        return ds;
    }
        
    public Map<Integer, List<DataPoint>> getSecClusterMap() {
        return secClusterMap;
    }

    public void setSecClusterMap(Map<Integer, List<DataPoint>> secClusterMap) {
        this.secClusterMap = secClusterMap;
    }
    
    public Clustering (){
        
    }
    
    public Clustering (File f){
        
        this.SecKernel = this.pm.getParametros()[0];        
        this.SecH = this.pm.getParametros()[1];
        this.SecNeighborhood = this.pm.getParametros()[2]; 
        this.SecStep = this.pm.getParametros()[3]; 
        this.SecLow = this.pm.getParametros()[4];
        this.SecHigh = this.pm.getParametros()[5];
        this.SecTau = this.pm.getParametros()[6];
        this.SecThreshold = this.pm.getParametros()[7]; 
        
        realizaClustering(f);
        
    }
    
    public int realizaClustering(File f) {
        
        //Initialization
        DistanceFunction distance = new EuclideanDistance();
        DataSetDAO nds = new DataSetDAOPlainFile(distance);

        try {
            
            this.file = f;
            
            ds = nds.load(true, this.file.getAbsolutePath());
            int dimension = ds.getDim();
            
            System.out.print(dimension + "-dimensinal data loaded.\n");
            //this.txtLog.append(dimension + "-dimensinal data loaded.\n");

            long[] startCorner = parseCorner(SecLow, dimension);  
            long[] endCorner = parseCorner(SecHigh, dimension);  
            long[][] corners = new long[][]{startCorner, endCorner};  
            double[] tau = parseTau(SecTau, dimension);  
            double h = parseDouble(SecH);  
            int neighborhood = parseInt(SecNeighborhood); 
            int step = parseInt(SecStep);  
            double threshold = parseDouble(SecThreshold); 
            // Radius wil be constant since it is used to create cluster-guides
            int radius = 1;

            double[] originValues = new double[startCorner.length];
            for (int d = 0; d < corners[0].length; d++) {
                originValues[d] = 0;
            }

            long[] startGridCorner = new long[dimension];
            long[] endGridCorner = new long[dimension];

            //Transform corners into grid corners            
            for (int d = 0; d < dimension; d++) {
                startGridCorner[d] = (long) Math.ceil(startCorner[d] / tau[d]);
                endGridCorner[d] = (long) Math.ceil(endCorner[d] / tau[d]);
            }
            long[][] gridCorners = new long[][]{startGridCorner, endGridCorner};

            Grid g = new Grid(new DataPoint(originValues, distance), gridCorners, tau);   // grid origin ,corners,tau (distance between steps in a given dimension

            Kernel k = null;
            if (SecKernel.equalsIgnoreCase("Gauss")) {
                k = new GaussKernel();
            }

            System.out.print(
                    "Kernel: " + SecKernel + "\n"
                    + "h: " + h + "\n"
                    + "Neighborhood: " + neighborhood + "\n"
                    + "Corners: Start (" + SecLow + ") and End (" + SecHigh + ")\n"
                    + "Tau: " + SecTau + "\n"
                    + "Step: " + SecStep + "\n"
                    + "Threshold:+" + threshold + "\n\n\n");

            SecureEstimator se = new SecureEstimator();
            
            int maxNumOfLevels = (int) g.getNumberOfPoints() / step; 
            
            double[] isoLevels = se.findIsoLevels(k, h, tau[0], step, maxNumOfLevels);
            
            //A segunda hipotese é que aqui é que deve ser realizado o clustering distribuído
            //"sde" seria a densidade local e o "secClusterMap" seria a densidade global
            //como juntas os "sde" calculados pelos agentes????
            SecureDensityEstimate sde = se.estimate(ds, h, g, k, neighborhood, isoLevels);
            
            KDEC_S secClt = new KDEC_S();

            //TODO Adaptive threshold
            double adaptThreshold = (threshold / k.calculate(0)) * isoLevels[isoLevels.length - 1];
            
            //A primeira hipotese é que aqui é que deve ser realizado o clustering distribuído
            this.secClusterMap = secClt.cluster(ds, g, neighborhood, sde, adaptThreshold);
            
            this.setSecClusterMap(secClusterMap);

            System.out.print("Adaptive threshold "+adaptThreshold+"\n");
        
            System.out.print("Done.\n " + secClusterMap.keySet().size() + " clusters found with " + ds.size() + " data points.\n");
           

        } catch (java.io.IOException e) {
            System.out.print("Error Opening: " + this.file.getName() + ".\n");
            e.printStackTrace();

        } catch (java.lang.Exception e) {
             System.out.print("Exception occurred!\n");
             e.printStackTrace();
        }
        
        return 0;
    }
    
     private static long[] parseCorner(String s, int dim) throws java.io.IOException {
        StringReader r = new StringReader(s);
        StreamTokenizer st = new StreamTokenizer(r);
        long[] startCorner = new long[dim];
        int d = 0;
        while (st.ttype != StreamTokenizer.TT_EOF) {
            if (st.ttype == StreamTokenizer.TT_NUMBER) {
                startCorner[d] = (new Double(st.nval)).longValue();
                d++;
            }
            st.nextToken();
        }
        return startCorner;
    }
     
    private static double[] parseTau(String s, int dim) throws java.io.IOException {
        StringReader r = new StringReader(s);
        StreamTokenizer st = new StreamTokenizer(r);
        java.util.List<Double> vTau = new ArrayList<Double>();


        while (st.ttype != StreamTokenizer.TT_EOF) {
            if (st.ttype == StreamTokenizer.TT_NUMBER) {
                vTau.add(new Double(st.nval));

            }
            st.nextToken();
        }

        double[] tau = new double[vTau.size()];

        for (int i = 0; i < vTau.size(); i++) {
            tau[i] = ((Double) vTau.get(i)).doubleValue();
        }

        return tau;
    }

    private static double parseDouble(String s) throws java.io.IOException {
        StringReader r = new StringReader(s);
        StreamTokenizer st = new StreamTokenizer(r);
        double value = 0;

        while (st.ttype != StreamTokenizer.TT_EOF) {
            if (st.ttype == StreamTokenizer.TT_NUMBER) {
                value = (new Double(st.nval)).doubleValue();
                break;
            }
            st.nextToken();
        }
        return value;
    }

    private static int parseInt(String s) throws java.io.IOException {
        StringReader r = new StringReader(s);
        StreamTokenizer st = new StreamTokenizer(r);
        int value = 0;

        while (st.ttype != StreamTokenizer.TT_EOF) {
            if (st.ttype == StreamTokenizer.TT_NUMBER) {
                value = (new Double(st.nval)).intValue();
                break;
            }
            st.nextToken();
        }
        return value;
    } 
    
}


/*
public File escolherArquivo () {
       
       System.out.print("1"); 
       JFileChooser fl = new JFileChooser();
       System.out.print("2");
       File arquivo = null;
       System.out.print("3");
        //fl.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int x = fl.showSaveDialog(null);        
       System.out.print("4"); 
        //int x = fl.showOpenDialog(null);
                
        if (x == 1) {
            System.out.print("Fechada sem nenhum arquivo");
            //JtextFieldLocal.setText("");
        } else {
            System.out.print("5");
            arquivo = fl.getSelectedFile();
            System.out.print("6");
            System.out.print(arquivo.getPath());             
            //JtextFieldLocal.setText(arquivo.getPath());
        } 
        
        return arquivo;
        
        /*
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
        
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                this.file = fc.getSelectedFile();
                System.out.print("Selected file: " + this.file.getAbsolutePath() + ".\n");
                this.currentFile = file;
            } else {
                System.out.print("Open command was cancelled by user.\n");
            }
   }*/


/*
public Clustering (String SecLow1, String SecHigh1, 
                                      String SecTau1, String SecH1,String SecNeighborhood1, 
                                      String SecStep1,String SecThreshold1,String SecKernel1){
        this.SecLow = SecLow1;
        this.SecHigh = SecHigh1;
        this.SecTau = SecTau1;
        this.SecH = SecH1;                
        this.SecNeighborhood = SecNeighborhood1;  
        this.SecStep = SecStep1;  
        this.SecThreshold = SecThreshold1;           
        this.SecKernel = SecKernel1;
    
        }
    
    public Clustering (Parametros pm){
        
        this.SecKernel = pm.getParametros()[0];        
        this.SecH = pm.getParametros()[1];
        this.SecNeighborhood = pm.getParametros()[2]; 
        this.SecStep = pm.getParametros()[3]; 
        this.SecLow = pm.getParametros()[4];
        this.SecHigh = pm.getParametros()[5];
        this.SecTau = pm.getParametros()[6];
        this.SecThreshold = pm.getParametros()[7];  
    
    }

*/

/*
//System.out.println("CLUSTERING 2 >>> "+ a);
            
            //this.file = pm.getArqAgent(a);
            
            //this.file = pm.posicaoFile(parseInt(""+a.charAt(5))-1);  
            //bem aqui foi resolvido o problema de distribuição do arquivo
            
            //this.file = pm.getPrimeiroFile();    //aqui ele tem que pegar é o arquivo do agente 
*/

/*
/*this.SecLow = "0,0";
        this.SecHigh = "1000000,1000000";
        this.SecTau = "10000,10000";
        this.SecH = "10000.0";                
        this.SecNeighborhood = "3";  
        this.SecStep = "1";  
        this.SecThreshold = "2.0";           
        this.SecKernel = "Gauss";*/
/*
if (this.file == null){
                    System.out.println("CLUSTERING >>> ENCERRADO PORQUE O ARQUIVO É NULL");
            }else{
                    System.out.println("CLUSTERING >>> TEM ARQUIVO");
            }
*/

/*
public Clustering (String agente){
        
        this.SecKernel = pm.getParametros()[0];        
        this.SecH = pm.getParametros()[1];
        this.SecNeighborhood = pm.getParametros()[2]; 
        this.SecStep = pm.getParametros()[3]; 
        this.SecLow = pm.getParametros()[4];
        this.SecHigh = pm.getParametros()[5];
        this.SecTau = pm.getParametros()[6];
        this.SecThreshold = pm.getParametros()[7];  
        
        System.out.print("CLUSTERING 1 >>> "+ agente);
        
        //realizaClustering(agente);
        i++;
    
    }
*/