/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import java.io.File;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JFileChooser;
import mineracao.ClusterGlobal;  
import mineracao.Parametros;
import mineracao.Resultado;

/**
 *
 * @author LUIS
 */
public class JFileChooserDemo extends javax.swing.JFrame {
    Parametros pm = Parametros.getInstance();
    ClusterGlobal cg = ClusterGlobal.getInstance();
    Resultado rs = Resultado.getInstance();
    java.io.File file = null;
    
    /**
     * Creates new form JFileChooserDemo
     */
    public JFileChooserDemo() {
        initComponents();
        
    }
    
    public void imprimeTextArea(String s){
          this.textarea.append("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        fileChooser = new javax.swing.JFileChooser();
        dlgSecClusterParam = new javax.swing.JDialog();
        pnlSecEstimation = new javax.swing.JPanel();
        lblKernel1 = new javax.swing.JLabel();
        txtSecKernel = new javax.swing.JTextField();
        lblH1 = new javax.swing.JLabel();
        txtSecH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSecNeighborhood = new javax.swing.JTextField();
        txtSecStep = new javax.swing.JTextField();
        lblH2 = new javax.swing.JLabel();
        pnlSecGrid = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtSecLow = new javax.swing.JTextField();
        txtSecHigh = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSecTau = new javax.swing.JTextField();
        btnSecCluster = new javax.swing.JButton();
        btnSecCancel = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtSecThreshold = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dlgExecutar = new javax.swing.JDialog();
        pnlSecEstimation1 = new javax.swing.JPanel();
        lblMinerAgent = new javax.swing.JLabel();
        txtMinerAgent = new javax.swing.JTextField();
        lblManagerAgent = new javax.swing.JLabel();
        lblDataAgent = new javax.swing.JLabel();
        txtDataAgent = new javax.swing.JTextField();
        txtManagerAgent = new javax.swing.JTextField();
        lblHelperAgent = new javax.swing.JLabel();
        txtHelperAgent = new javax.swing.JTextField();
        pnlSecGrid1 = new javax.swing.JPanel();
        lblContainer = new javax.swing.JLabel();
        txtContainer = new javax.swing.JTextField();
        lblHost = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        btnSecCluster1 = new javax.swing.JButton();
        btnSecCancel1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Abrir = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        Sair = new javax.swing.JMenuItem();
        menuClustering = new javax.swing.JMenu();
        menuItemSecCluster = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        menuItemViewData = new javax.swing.JMenuItem();
        menuResults = new javax.swing.JMenu();
        menuViewSecResults = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        fileChooser.setDialogTitle("Esta é minha caixa de diálogo aberta");
        fileChooser.setFileFilter(new MyCustomFilter());
        fileChooser.setMultiSelectionEnabled(true);

        dlgSecClusterParam.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlgSecClusterParam.setTitle("KDEC-S Parametros");
        dlgSecClusterParam.setModal(true);
        dlgSecClusterParam.setName("Clustering Parameters"); // NOI18N
        dlgSecClusterParam.getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlSecEstimation.setBorder(javax.swing.BorderFactory.createTitledBorder("Estimation"));
        pnlSecEstimation.setName("Estimation"); // NOI18N
        pnlSecEstimation.setOpaque(false);
        pnlSecEstimation.setLayout(null);

        lblKernel1.setText("Kernel");
        pnlSecEstimation.add(lblKernel1);
        lblKernel1.setBounds(10, 20, 50, 14);

        txtSecKernel.setText("Gauss");
        pnlSecEstimation.add(txtSecKernel);
        txtSecKernel.setBounds(50, 20, 70, 25);

        lblH1.setText("IsoStep");
        pnlSecEstimation.add(lblH1);
        lblH1.setBounds(170, 50, 50, 14);

        txtSecH.setText("0.5");
        pnlSecEstimation.add(txtSecH);
        txtSecH.setBounds(190, 20, 100, 25);

        jLabel5.setText("Neighborhood");
        pnlSecEstimation.add(jLabel5);
        jLabel5.setBounds(10, 50, 100, 14);

        txtSecNeighborhood.setText("1");
        pnlSecEstimation.add(txtSecNeighborhood);
        txtSecNeighborhood.setBounds(90, 50, 70, 25);

        txtSecStep.setText("1");
        txtSecStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSecStepActionPerformed(evt);
            }
        });
        pnlSecEstimation.add(txtSecStep);
        txtSecStep.setBounds(220, 50, 70, 25);

        lblH2.setText("H");
        pnlSecEstimation.add(lblH2);
        lblH2.setBounds(170, 20, 20, 14);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 309;
        gridBagConstraints.ipady = 89;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgSecClusterParam.getContentPane().add(pnlSecEstimation, gridBagConstraints);

        pnlSecGrid.setBorder(javax.swing.BorderFactory.createTitledBorder("Grid"));
        pnlSecGrid.setLayout(null);

        jLabel6.setText("Lower");
        pnlSecGrid.add(jLabel6);
        jLabel6.setBounds(10, 20, 50, 20);

        txtSecLow.setText("0,0");
        pnlSecGrid.add(txtSecLow);
        txtSecLow.setBounds(50, 20, 90, 25);

        txtSecHigh.setText("100,100");
        pnlSecGrid.add(txtSecHigh);
        txtSecHigh.setBounds(190, 20, 100, 25);

        jLabel7.setText("Higher");
        jLabel7.setPreferredSize(new java.awt.Dimension(29, 14));
        pnlSecGrid.add(jLabel7);
        jLabel7.setBounds(150, 20, 45, 14);

        jLabel8.setText("Tau");
        pnlSecGrid.add(jLabel8);
        jLabel8.setBounds(10, 50, 30, 14);

        txtSecTau.setText("1,1");
        pnlSecGrid.add(txtSecTau);
        txtSecTau.setBounds(50, 50, 90, 25);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 309;
        gridBagConstraints.ipady = 89;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgSecClusterParam.getContentPane().add(pnlSecGrid, gridBagConstraints);

        btnSecCluster.setText("Secure Cluster");
        btnSecCluster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecClusterActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgSecClusterParam.getContentPane().add(btnSecCluster, gridBagConstraints);

        btnSecCancel.setText("Cancel");
        btnSecCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecCancelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgSecClusterParam.getContentPane().add(btnSecCancel, gridBagConstraints);

        jButton2.setText("Get Suggestion");
        jButton2.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgSecClusterParam.getContentPane().add(jButton2, gridBagConstraints);

        txtSecThreshold.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSecThreshold.setText("0.1");
        txtSecThreshold.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSecThresholdActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipadx = 66;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgSecClusterParam.getContentPane().add(txtSecThreshold, gridBagConstraints);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Threshold");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgSecClusterParam.getContentPane().add(jLabel9, gridBagConstraints);

        dlgExecutar.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        dlgExecutar.setTitle("Escolha de Agentes");
        dlgExecutar.setModal(true);
        dlgExecutar.setName("Clustering Parameters"); // NOI18N
        dlgExecutar.getContentPane().setLayout(new java.awt.GridBagLayout());

        pnlSecEstimation1.setBorder(javax.swing.BorderFactory.createTitledBorder("Agentes"));
        pnlSecEstimation1.setName("Agentes"); // NOI18N
        pnlSecEstimation1.setOpaque(false);
        pnlSecEstimation1.setLayout(null);

        lblMinerAgent.setText("MinerAgent");
        pnlSecEstimation1.add(lblMinerAgent);
        lblMinerAgent.setBounds(10, 20, 80, 14);

        txtMinerAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinerAgentActionPerformed(evt);
            }
        });
        pnlSecEstimation1.add(txtMinerAgent);
        txtMinerAgent.setBounds(100, 20, 70, 25);

        lblManagerAgent.setText("ManagerAgent");
        pnlSecEstimation1.add(lblManagerAgent);
        lblManagerAgent.setBounds(10, 80, 80, 14);

        lblDataAgent.setText("DataSetAgent");
        pnlSecEstimation1.add(lblDataAgent);
        lblDataAgent.setBounds(10, 50, 80, 14);

        txtDataAgent.setText("1");
        pnlSecEstimation1.add(txtDataAgent);
        txtDataAgent.setBounds(100, 50, 70, 25);

        txtManagerAgent.setText("1");
        txtManagerAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManagerAgentActionPerformed(evt);
            }
        });
        pnlSecEstimation1.add(txtManagerAgent);
        txtManagerAgent.setBounds(100, 80, 70, 25);

        lblHelperAgent.setText("HelperAgent");
        pnlSecEstimation1.add(lblHelperAgent);
        lblHelperAgent.setBounds(10, 110, 80, 14);

        txtHelperAgent.setText("1");
        txtHelperAgent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHelperAgentActionPerformed(evt);
            }
        });
        pnlSecEstimation1.add(txtHelperAgent);
        txtHelperAgent.setBounds(100, 110, 70, 25);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.ipadx = 309;
        gridBagConstraints.ipady = 150;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgExecutar.getContentPane().add(pnlSecEstimation1, gridBagConstraints);

        pnlSecGrid1.setBorder(javax.swing.BorderFactory.createTitledBorder("Container / Host"));
        pnlSecGrid1.setLayout(null);

        lblContainer.setText("Nome Conteiner");
        pnlSecGrid1.add(lblContainer);
        lblContainer.setBounds(10, 20, 100, 20);

        txtContainer.setText("Meu-Container");
        pnlSecGrid1.add(txtContainer);
        txtContainer.setBounds(110, 20, 90, 25);

        lblHost.setText("Nome Host");
        pnlSecGrid1.add(lblHost);
        lblHost.setBounds(10, 50, 70, 14);

        txtHost.setEditable(false);
        pnlSecGrid1.add(txtHost);
        txtHost.setBounds(110, 50, 90, 25);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.ipadx = 309;
        gridBagConstraints.ipady = 89;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgExecutar.getContentPane().add(pnlSecGrid1, gridBagConstraints);

        btnSecCluster1.setText("Executar");
        btnSecCluster1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecCluster1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 7;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgExecutar.getContentPane().add(btnSecCluster1, gridBagConstraints);

        btnSecCancel1.setText("Cancel");
        btnSecCancel1.setPreferredSize(new java.awt.Dimension(29, 23));
        btnSecCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSecCancel1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        dlgExecutar.getContentPane().add(btnSecCancel1, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplicação de demonstração");

        textarea.setEditable(false);
        textarea.setColumns(20);
        textarea.setRows(5);
        jScrollPane1.setViewportView(textarea);

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        Abrir.setText("Abrir");
        Abrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AbrirActionPerformed(evt);
            }
        });
        jMenu1.add(Abrir);

        jMenuItem1.setText("Executar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Limpar Log");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        Sair.setText("Sair");
        Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SairActionPerformed(evt);
            }
        });
        jMenu1.add(Sair);

        jMenuBar1.add(jMenu1);

        menuClustering.setText("Clustering");

        menuItemSecCluster.setText("KDEC-S");
        menuItemSecCluster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSecClusterActionPerformed(evt);
            }
        });
        menuClustering.add(menuItemSecCluster);

        jMenuBar1.add(menuClustering);

        menuView.setText("View");

        menuItemViewData.setText("Data");
        menuItemViewData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemViewDataActionPerformed(evt);
            }
        });
        menuView.add(menuItemViewData);

        menuResults.setText("Results");

        menuViewSecResults.setText("KDEC-S");
        menuViewSecResults.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuViewSecResultsActionPerformed(evt);
            }
        });
        menuResults.add(menuViewSecResults);

        menuView.add(menuResults);

        jMenuItem3.setText("Arquivo por Agente");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuView.add(jMenuItem3);

        jMenuBar1.add(menuView);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AbrirActionPerformed
        // TODO add your handling code here:
        int returnVal = fileChooser.showOpenDialog(this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            
            File[] files = fileChooser.getSelectedFiles();
            
            /*for (File fil : files)
            {
                textarea.append(fil.getAbsolutePath() +"\n");
            }*/
            
            for (int i=0; i < files.length; i++)
            {
                textarea.append(files[i].getAbsolutePath() +"\n");
            }
            
            textarea.append("\nQuantidade de arquivos: "+Integer.toString(files.length)+"\n");
            
            pm.setFile(files);
            
            /*try {
            // What to do with the file, e.g. display it in a TextArea
            textarea.read( new FileReader( file.getAbsolutePath() ), null );
            } catch (IOException ex) {
            System.out.println("problem accessing file"+file.getAbsolutePath());
            }*/
    } else {
        System.out.println("File access cancelled by user.");
    }
    }//GEN-LAST:event_AbrirActionPerformed

    private void SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SairActionPerformed
        // TODO add your handling code here:
        System.exit(0); 
    }//GEN-LAST:event_SairActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //ClassePrincipal cp= new ClassePrincipal();// TODO add your handling code here:
        this.dlgExecutar.pack();           
        this.dlgExecutar.setLocationRelativeTo(null);
        this.dlgExecutar.setVisible(true);        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtSecStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSecStepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSecStepActionPerformed

    private void btnSecClusterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecClusterActionPerformed
        // TODO add your handling code here:
            

            //long[] startCorner = parseCorner(this.txtSecLow.getText(), dimension);
            //long[] endCorner = parseCorner(this.txtSecHigh.getText(), dimension);
            //long[][] corners = new long[][]{startCorner, endCorner};
            //double[] tau = parseTau(this.txtSecTau.getText(), dimension);
            //int step = parseInt(this.txtSecStep.getText());            
            //String Kernel; Kernel = this.txtSecKernel.getText();
            
            //CARREGAMENTO DOS PARÂMETROS
            String [] param= new String [8];
            param[0] = this.txtSecKernel.getText();
            param[1] = this.txtSecH.getText();
            param[2] = this.txtSecNeighborhood.getText();
            param[3] = this.txtSecStep.getText();
            param[4] = this.txtSecLow.getText();
            param[5] = this.txtSecHigh.getText();
            param[6] = this.txtSecTau.getText();
            param[7] = this.txtSecThreshold.getText();            
            
            double h = parseDouble(this.txtSecH.getText());
            int neighborhood = parseInt(this.txtSecNeighborhood.getText());
            double threshold = parseDouble(this.txtSecThreshold.getText());

            this.textarea.append("\nPARÂMETROS:\n"
                    +"Kernel: " + this.txtSecKernel.getText() + "\n"
                    + "h: " + h + "\n"
                    + "Neighborhood: " + neighborhood + "\n"
                    + "Corners: Start (" + this.txtSecLow.getText() + ") and End (" + this.txtSecHigh.getText() + ")\n"
                    + "Tau: " + this.txtSecTau.getText() + "\n"
                    + "Step: " + this.txtSecStep.getText() + "\n"
                    + "Threshold:+" + threshold + "\n");
            
            pm.setParametros(param);    
            pm.setFileEscolhido(-1);
            
            cg.setPoints(0);
            
       
    }//GEN-LAST:event_btnSecClusterActionPerformed

    private void btnSecCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecCancelActionPerformed
        this.dlgSecClusterParam.dispose();
    }//GEN-LAST:event_btnSecCancelActionPerformed

    private void txtSecThresholdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSecThresholdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSecThresholdActionPerformed

    private void menuItemSecClusterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSecClusterActionPerformed
        this.dlgSecClusterParam.pack();
        this.dlgSecClusterParam.setLocationRelativeTo(null);
        this.dlgSecClusterParam.setVisible(true);
    }//GEN-LAST:event_menuItemSecClusterActionPerformed

    private void menuItemViewDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemViewDataActionPerformed
        // TODO add your handling code here:
        System.out.println("call DataView UI");
        
        //this.file = this.pm.getPrimeiroFile();
        
        if (this.pm.quantFile() != 0){        
            for (int i=0; i<this.pm.quantFile(); i++){
              DataViewUI dview = new DataViewUI(this.pm.posicaoFile(i));
              dview.setTitle(this.pm.posicaoFile(i).getAbsolutePath());
              dview.pack();
              dview.setVisible(true);
            }
        }else{
           this.textarea.append("Não há arquivo para plotar valores !!! Arquivo nulo.\n\n"); 
        }
    }//GEN-LAST:event_menuItemViewDataActionPerformed

    private void menuViewSecResultsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuViewSecResultsActionPerformed
        
        if(this.cg.getClusterGlobal() != null && this.cg.getClusterLocal() != null)
        {
          ResultsViewUI resultsView = new ResultsViewUI(this.cg.getClusterGlobal());
          resultsView.setTitle("KDEC-S Results Global");
          resultsView.pack();
          resultsView.setVisible(true);
          
          for (int i=1 ; i <= this.cg.getClusterLocal().keySet().size(); i++)
            {
                 resultsView = new ResultsViewUI(this.cg.getClusterLocal().get(i).getSecClusterMap());
                 resultsView.setTitle(" KDEC-S Results " + Integer.toString(i));
                 resultsView.pack();
                 resultsView.setVisible(true);
            } 
          
        }
        else{
          this.textarea.append("Nada para plotar!!!");
        }
        
        this.textarea.append("\n\nRESULTADO:\n\n");
        
        Map<String, String> rt = rs.imprimeResultados();
        
        Set<Map.Entry<String, String>> set = rt.entrySet();               
                
	Iterator it = set.iterator();
		
	while(it.hasNext()){
                    
            Map.Entry<String, String> entry = (Map.Entry)it.next();
                        
            //System.out.println(entry.getKey() + "\t\t"+entry.getValue());
            this.textarea.append(entry.getKey() + " >> " + "  "+entry.getValue());
                        
	}    
        
        //OUTRA FORMA DE MOSTRAR O RESULTADO
        /*for (int i=1 ; i <= this.cg.getClusterLocal().keySet().size(); i++)
        {
            this.textarea.append("\n\nLocal Result >>  "+ this.cg.getClusterLocal().get(i).getSecClusterMap().keySet().size()+ " clusters found with"
                + " >> " + "  "+ this.cg.getClusterLocal().get(i).getPoints() + " data points\n\n");
        }*/
        
        
        this.textarea.append("\n\nGlobal Result  >>  "+this.cg.getClusterGlobal().keySet().size() + " clusters found with"
                + " >> " + "  "+this.cg.getPoints() + " data points\n\n");
        
       
    }//GEN-LAST:event_menuViewSecResultsActionPerformed

    private void txtManagerAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManagerAgentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtManagerAgentActionPerformed

    private void btnSecCluster1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecCluster1ActionPerformed
        // TODO add your handling code here:
        
        this.cg.setContador(0);//Código para resetar os valores 
        this.cg.setContador2(0);//this.cg.getClusterGlobal().clear();
        //this.cg.getClusterLocal().clear();
        this.cg.setPoints(0);
        //this.rs = null;//this.rs.getResults().clear();
                 
        int numMinerAgent = parseInt(this.txtMinerAgent.getText());
        int numDataAgent = parseInt(this.txtDataAgent.getText());
        int numManagerAgent = parseInt(this.txtManagerAgent.getText());
        int numHelperAgent = parseInt(this.txtHelperAgent.getText());
        
        String container = this.txtContainer.getText();
        
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
        
        this.dlgExecutar.dispose();  
        
        this.textarea.append("");
        
        if (numMinerAgent !=0)
            this.textarea.append("\nInstanciados " +numMinerAgent+" MinerAgent para esta sessão de mineração\n");  
            
        if (numDataAgent !=0)
            this.textarea.append("Instanciados " +numDataAgent+" DataAgent para esta sessão de mineração\n");  
            
        if (numManagerAgent !=0)
            this.textarea.append("Instanciados " +numManagerAgent+" ManagerAgent para esta sessão de mineração\n");
            
        if (numHelperAgent !=0)
            this.textarea.append("Instanciados " +numHelperAgent+" Helper Agent para esta sessão de mineração\n"); 
    }//GEN-LAST:event_btnSecCluster1ActionPerformed

    private void btnSecCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSecCancel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSecCancel1ActionPerformed

    private void txtMinerAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinerAgentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinerAgentActionPerformed

    private void txtHelperAgentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHelperAgentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHelperAgentActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       this.textarea.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Map<String, File> arquivoDoAgente = this.pm.getArq();
        
        Set<Map.Entry<String, File>> set = arquivoDoAgente.entrySet();               
                
	Iterator it = set.iterator();
        
        this.textarea.append("\n\n");
		
	while(it.hasNext()){
                    
            Map.Entry<String, File> entry = (Map.Entry)it.next();
                        
            //System.out.println(entry.getKey() + "\t\t"+entry.getValue());
            this.textarea.append(entry.getKey() + "  >>" + "  "+entry.getValue().getAbsolutePath()+"\n");
                        
	} 
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFileChooserDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFileChooserDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFileChooserDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFileChooserDemo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFileChooserDemo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Abrir;
    private javax.swing.JMenuItem Sair;
    private javax.swing.JButton btnSecCancel;
    private javax.swing.JButton btnSecCancel1;
    private javax.swing.JButton btnSecCluster;
    private javax.swing.JButton btnSecCluster1;
    private javax.swing.JDialog dlgExecutar;
    private javax.swing.JDialog dlgSecClusterParam;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblContainer;
    private javax.swing.JLabel lblDataAgent;
    private javax.swing.JLabel lblH1;
    private javax.swing.JLabel lblH2;
    private javax.swing.JLabel lblHelperAgent;
    private javax.swing.JLabel lblHost;
    private javax.swing.JLabel lblKernel1;
    private javax.swing.JLabel lblManagerAgent;
    private javax.swing.JLabel lblMinerAgent;
    private javax.swing.JMenu menuClustering;
    private javax.swing.JMenuItem menuItemSecCluster;
    private javax.swing.JMenuItem menuItemViewData;
    private javax.swing.JMenu menuResults;
    private javax.swing.JMenu menuView;
    private javax.swing.JMenuItem menuViewSecResults;
    private javax.swing.JPanel pnlSecEstimation;
    private javax.swing.JPanel pnlSecEstimation1;
    private javax.swing.JPanel pnlSecGrid;
    private javax.swing.JPanel pnlSecGrid1;
    private javax.swing.JTextArea textarea;
    private javax.swing.JTextField txtContainer;
    private javax.swing.JTextField txtDataAgent;
    private javax.swing.JTextField txtHelperAgent;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtManagerAgent;
    private javax.swing.JTextField txtMinerAgent;
    private javax.swing.JTextField txtSecH;
    private javax.swing.JTextField txtSecHigh;
    private javax.swing.JTextField txtSecKernel;
    private javax.swing.JTextField txtSecLow;
    private javax.swing.JTextField txtSecNeighborhood;
    private javax.swing.JTextField txtSecStep;
    private javax.swing.JTextField txtSecTau;
    private javax.swing.JTextField txtSecThreshold;
    // End of variables declaration//GEN-END:variables
}
