/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.panel;

import com.vollyball.controller.Controller;
import com.vollyball.enums.PlayerPosition;
import com.vollyball.training.bean.Batch;
import com.vollyball.training.bean.BatchRowBean;
import com.vollyball.training.bean.Trainee;
import com.vollyball.training.dao.BatchDao;
import com.vollyball.util.DateLabelFormatter;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanNewBatch extends javax.swing.JPanel {

    UtilDateModel modelStart = new UtilDateModel();
    JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart);
    UtilDateModel modelEnd = new UtilDateModel();
    JDatePanelImpl datePanelEnd = new JDatePanelImpl(modelEnd);
    JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanelStart, new DateLabelFormatter());
    JDatePickerImpl datePickerEnd = new JDatePickerImpl(datePanelEnd, new DateLabelFormatter());

    List<Trainee> traineeList;
    List<JComboBox> position = new ArrayList<>();
    List<JCheckBox> captainCheckBoxList = new ArrayList<>();

    List<BatchRowBean> batchRow = new ArrayList<>();
    List<JPanel> rowPanels = new ArrayList<>();
    List<JLabel> lblMinusList = new ArrayList<>();
    String ageGroup;
    int i = 0, m = 6;

    String message;

    /**
     * Creates new form PanNewTeam
     */
    public PanNewBatch() {
        initComponents();
        datePickerStart.setBounds(0, 0, 319, 28);
        JFormattedTextField textField = datePickerStart.getJFormattedTextField();
        textField.setBackground(Color.WHITE);
        datePickerStart.setButtonFocusable(false);
        textField.setBorder(null);
        panStartDate.add(datePickerStart);

        datePickerEnd.setBounds(0, 0, 319, 28);
        JFormattedTextField textField1 = datePickerEnd.getJFormattedTextField();
        textField1.setBackground(Color.WHITE);
        textField1.setBorder(null);
        panEndDate.add(datePickerEnd);

        lblMinusList.add(lblSub);

        lblMinusList.add(lblSub1);
        lblMinusList.add(lblSub2);
        lblMinusList.add(lblSub3);
        lblMinusList.add(lblSub4);

        lblMinusList.add(lblSub5);
        lblMinusList.add(lblSub6);
        lblMinusList.add(lblSub7);

        batchRow.add(new BatchRowBean(txtPlayerName1, lblSrNo1));
        batchRow.add(new BatchRowBean(txtPlayerName2, lblSrNo2));
        batchRow.add(new BatchRowBean(txtPlayerName3, lblSrNo3));
        batchRow.add(new BatchRowBean(txtPlayerName4, lblSrNo4));
        batchRow.add(new BatchRowBean(txtPlayerName5, lblSrNo5));
        batchRow.add(new BatchRowBean(txtPlayerName6, lblSrNo6));
        batchRow.add(new BatchRowBean(txtPlayerName7, lblSrNo7));
        batchRow.add(new BatchRowBean(txtPlayerName8, lblSrNo8));
        batchRow.add(new BatchRowBean(txtPlayerName9, lblSrNo9));
        batchRow.add(new BatchRowBean(txtPlayerName10, lblSrNo10));
        batchRow.add(new BatchRowBean(txtPlayerName11, lblSrNo11));
        batchRow.add(new BatchRowBean(txtPlayerName12, lblSrNo12));
        batchRow.add(new BatchRowBean(txtPlayerName13, lblSrNo13));
        batchRow.add(new BatchRowBean(txtPlayerName14, lblSrNo14));

        rowPanels.add(trRow7);
        rowPanels.add(trRow8);
        rowPanels.add(trRow9);
        rowPanels.add(trRow10);
        rowPanels.add(trRow11);
        rowPanels.add(trRow12);
        rowPanels.add(trRow13);
        rowPanels.add(trRow14);
//        panTeamRowList.setBounds(-1, -1, 900, 420);
        for (int i = 0; i < position.size(); i++) {
            ((JLabel) position.get(i).getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            for (PlayerPosition pp : PlayerPosition.values()) {
                position.get(i).addItem(pp.getName());
            }

            position.get(i).addItemListener(new ItemListener() {
                // Listening if a new items of the combo box has been selected.
                public void itemStateChanged(ItemEvent event) {
                    ((JComboBox) event.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
            });

        }

        for (JPanel p : rowPanels) {
            p.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBatchName = new javax.swing.JTextField();
        txtAnalyzerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMedOffName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtVenue = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panStartDate = new javax.swing.JPanel();
        panEndDate = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtrainerName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        cmbAgeGroup = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panRowShow = new javax.swing.JPanel();
        lblSrNo1 = new javax.swing.JTextField();
        txtPlayerName1 = new javax.swing.JTextField();
        lblSrNo2 = new javax.swing.JTextField();
        txtPlayerName2 = new javax.swing.JTextField();
        txtPlayerName3 = new javax.swing.JTextField();
        lblSrNo3 = new javax.swing.JTextField();
        txtPlayerName4 = new javax.swing.JTextField();
        lblSrNo4 = new javax.swing.JTextField();
        txtPlayerName5 = new javax.swing.JTextField();
        lblSrNo5 = new javax.swing.JTextField();
        txtPlayerName6 = new javax.swing.JTextField();
        lblSrNo6 = new javax.swing.JTextField();
        trRow7 = new javax.swing.JPanel();
        lblSrNo7 = new javax.swing.JTextField();
        txtPlayerName7 = new javax.swing.JTextField();
        lblSub = new javax.swing.JLabel();
        trRow8 = new javax.swing.JPanel();
        lblSrNo8 = new javax.swing.JTextField();
        txtPlayerName8 = new javax.swing.JTextField();
        lblSub1 = new javax.swing.JLabel();
        trRow9 = new javax.swing.JPanel();
        txtPlayerName9 = new javax.swing.JTextField();
        lblSrNo9 = new javax.swing.JTextField();
        lblSub2 = new javax.swing.JLabel();
        trRow10 = new javax.swing.JPanel();
        lblSrNo10 = new javax.swing.JTextField();
        txtPlayerName10 = new javax.swing.JTextField();
        lblSub3 = new javax.swing.JLabel();
        trRow11 = new javax.swing.JPanel();
        lblSrNo11 = new javax.swing.JTextField();
        txtPlayerName11 = new javax.swing.JTextField();
        lblSub4 = new javax.swing.JLabel();
        trRow12 = new javax.swing.JPanel();
        lblSrNo12 = new javax.swing.JTextField();
        txtPlayerName12 = new javax.swing.JTextField();
        lblSub5 = new javax.swing.JLabel();
        trRow13 = new javax.swing.JPanel();
        lblSrNo13 = new javax.swing.JTextField();
        txtPlayerName13 = new javax.swing.JTextField();
        lblSub6 = new javax.swing.JLabel();
        trRow14 = new javax.swing.JPanel();
        lblSrNo14 = new javax.swing.JTextField();
        txtPlayerName14 = new javax.swing.JTextField();
        lblSub7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        lblSave = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        lblCancel = new javax.swing.JLabel();
        panAddPlayer = new javax.swing.JPanel();
        lblAddNew = new javax.swing.JLabel();

        jLabel9.setText("jLabel9");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel14.setText("jLabel14");

        jLabel16.setText("jLabel16");

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        lblHeading.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblHeading.setText("New Batch");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(140, 140, 140))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblHeading)
                .addGap(10, 10, 10))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(45, 62, 79));
        jLabel2.setText("Batch Name*");

        txtBatchName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtBatchName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtBatchName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBatchNameKeyTyped(evt);
            }
        });

        txtAnalyzerName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtAnalyzerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAnalyzerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnalyzerNameKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(45, 62, 79));
        jLabel6.setText("Medical Officer");

        txtMedOffName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMedOffName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtMedOffName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtMedOffNameKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(45, 62, 79));
        jLabel7.setText("Analyzer  Name");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(45, 62, 79));
        jLabel10.setText("Venue");

        txtVenue.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtVenue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtVenue.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtVenueKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Start Date");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setText("End Date");

        panStartDate.setBackground(new java.awt.Color(255, 255, 255));
        panStartDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panStartDate.setPreferredSize(new java.awt.Dimension(0, 28));
        panStartDate.setLayout(new java.awt.BorderLayout());

        panEndDate.setBackground(new java.awt.Color(255, 255, 255));
        panEndDate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panEndDate.setLayout(new java.awt.BorderLayout());

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(45, 62, 79));
        jLabel19.setText("Trainer Name");

        txtrainerName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtrainerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtrainerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrainerNameKeyTyped(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Age group");

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "Open", "Under" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        cmbAgeGroup.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbAgeGroup.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select", "14", "16", "18", "21" }));
        cmbAgeGroup.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbAgeGroupItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtBatchName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(10, 10, 10)
                                .addComponent(txtAnalyzerName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel15))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(panStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMedOffName, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(txtVenue, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(txtrainerName)
                    .addComponent(panEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBatchName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel19)
                    .addComponent(txtrainerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMedOffName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtAnalyzerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtVenue, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbAgeGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panStartDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panEndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panStartDate.getAccessibleContext().setAccessibleDescription("");
        panStartDate.getAccessibleContext().setAccessibleParent(this);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("NAME *");
        jLabel8.setToolTipText("");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SR No.");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
        );

        panRowShow.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo1.setText("1");
        lblSrNo1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo1.setEnabled(false);
        lblSrNo1.setFocusable(false);

        txtPlayerName1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName1KeyTyped(evt);
            }
        });

        lblSrNo2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo2.setText("2");
        lblSrNo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo2.setEnabled(false);
        lblSrNo2.setFocusable(false);

        txtPlayerName2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName2KeyTyped(evt);
            }
        });

        txtPlayerName3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName3KeyTyped(evt);
            }
        });

        lblSrNo3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo3.setText("3");
        lblSrNo3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo3.setEnabled(false);
        lblSrNo3.setFocusable(false);

        txtPlayerName4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName4KeyTyped(evt);
            }
        });

        lblSrNo4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo4.setText("4");
        lblSrNo4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo4.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo4.setEnabled(false);
        lblSrNo4.setFocusable(false);

        txtPlayerName5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName5KeyTyped(evt);
            }
        });

        lblSrNo5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo5.setText("5");
        lblSrNo5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo5.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo5.setEnabled(false);
        lblSrNo5.setFocusable(false);

        txtPlayerName6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName6KeyTyped(evt);
            }
        });

        lblSrNo6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo6.setText("6");
        lblSrNo6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo6.setEnabled(false);
        lblSrNo6.setFocusable(false);

        trRow7.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo7.setText("7");
        lblSrNo7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo7.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo7.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo7.setEnabled(false);
        lblSrNo7.setFocusable(false);

        txtPlayerName7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName7KeyTyped(evt);
            }
        });

        lblSub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub.setToolTipText("Remove Row");
        lblSub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow7Layout = new javax.swing.GroupLayout(trRow7);
        trRow7.setLayout(trRow7Layout);
        trRow7Layout.setHorizontalGroup(
            trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName7, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        trRow7Layout.setVerticalGroup(
            trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow7Layout.createSequentialGroup()
                .addGroup(trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        trRow8.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo8.setText("8");
        lblSrNo8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo8.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo8.setEnabled(false);
        lblSrNo8.setFocusable(false);

        txtPlayerName8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName8KeyTyped(evt);
            }
        });

        lblSub1.setBackground(new java.awt.Color(255, 255, 255));
        lblSub1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub1.setToolTipText("Remove Row");
        lblSub1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow8Layout = new javax.swing.GroupLayout(trRow8);
        trRow8.setLayout(trRow8Layout);
        trRow8Layout.setHorizontalGroup(
            trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trRow8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName8, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        trRow8Layout.setVerticalGroup(
            trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow8Layout.createSequentialGroup()
                .addGroup(trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        trRow9.setBackground(new java.awt.Color(255, 255, 255));

        txtPlayerName9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName9KeyTyped(evt);
            }
        });

        lblSrNo9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo9.setText("9");
        lblSrNo9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo9.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo9.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo9.setEnabled(false);
        lblSrNo9.setFocusable(false);

        lblSub2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub2.setToolTipText("Remove Row");
        lblSub2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow9Layout = new javax.swing.GroupLayout(trRow9);
        trRow9.setLayout(trRow9Layout);
        trRow9Layout.setHorizontalGroup(
            trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName9, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trRow9Layout.setVerticalGroup(
            trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow9Layout.createSequentialGroup()
                        .addGroup(trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSrNo9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlayerName9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        trRow10.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo10.setText("10");
        lblSrNo10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo10.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo10.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo10.setEnabled(false);
        lblSrNo10.setFocusable(false);

        txtPlayerName10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName10KeyTyped(evt);
            }
        });

        lblSub3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub3.setToolTipText("Remove Row");
        lblSub3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow10Layout = new javax.swing.GroupLayout(trRow10);
        trRow10.setLayout(trRow10Layout);
        trRow10Layout.setHorizontalGroup(
            trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trRow10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName10, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        trRow10Layout.setVerticalGroup(
            trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSrNo10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPlayerName10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        trRow11.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo11.setText("11");
        lblSrNo11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo11.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo11.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo11.setEnabled(false);
        lblSrNo11.setFocusable(false);

        txtPlayerName11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName11KeyTyped(evt);
            }
        });

        lblSub4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub4.setToolTipText("Remove Row");
        lblSub4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow11Layout = new javax.swing.GroupLayout(trRow11);
        trRow11.setLayout(trRow11Layout);
        trRow11Layout.setHorizontalGroup(
            trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, trRow11Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName11, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        trRow11Layout.setVerticalGroup(
            trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow11Layout.createSequentialGroup()
                .addGroup(trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        trRow12.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo12.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo12.setText("12");
        lblSrNo12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo12.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo12.setEnabled(false);
        lblSrNo12.setFocusable(false);

        txtPlayerName12.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName12KeyTyped(evt);
            }
        });

        lblSub5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub5.setToolTipText("Remove Row");
        lblSub5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow12Layout = new javax.swing.GroupLayout(trRow12);
        trRow12.setLayout(trRow12Layout);
        trRow12Layout.setHorizontalGroup(
            trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow12Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName12, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        trRow12Layout.setVerticalGroup(
            trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblSrNo12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(txtPlayerName12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        trRow13.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo13.setText("13");
        lblSrNo13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo13.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo13.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo13.setEnabled(false);
        lblSrNo13.setFocusable(false);

        txtPlayerName13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName13KeyTyped(evt);
            }
        });

        lblSub6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub6.setToolTipText("Remove Row");
        lblSub6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow13Layout = new javax.swing.GroupLayout(trRow13);
        trRow13.setLayout(trRow13Layout);
        trRow13Layout.setHorizontalGroup(
            trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblSrNo13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName13, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        trRow13Layout.setVerticalGroup(
            trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow13Layout.createSequentialGroup()
                        .addGroup(trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSrNo13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPlayerName13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        trRow14.setBackground(new java.awt.Color(255, 255, 255));

        lblSrNo14.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        lblSrNo14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblSrNo14.setText("14");
        lblSrNo14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblSrNo14.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        lblSrNo14.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblSrNo14.setEnabled(false);
        lblSrNo14.setFocusable(false);

        txtPlayerName14.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlayerName14ActionPerformed(evt);
            }
        });
        txtPlayerName14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName14KeyTyped(evt);
            }
        });

        lblSub7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub7.setToolTipText("Remove Row");
        lblSub7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout trRow14Layout = new javax.swing.GroupLayout(trRow14);
        trRow14.setLayout(trRow14Layout);
        trRow14Layout.setHorizontalGroup(
            trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow14Layout.createSequentialGroup()
                .addComponent(lblSrNo14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPlayerName14, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lblSub7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        trRow14Layout.setVerticalGroup(
            trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow14Layout.createSequentialGroup()
                .addGroup(trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panRowShowLayout = new javax.swing.GroupLayout(panRowShow);
        panRowShow.setLayout(panRowShowLayout);
        panRowShowLayout.setHorizontalGroup(
            panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(trRow12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(trRow11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(trRow10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(trRow9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(trRow13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(trRow14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                        .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                    .addComponent(lblSrNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPlayerName2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panRowShowLayout.createSequentialGroup()
                                    .addComponent(lblSrNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPlayerName1))
                                .addComponent(lblSrNo4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                    .addComponent(lblSrNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPlayerName6))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                    .addComponent(lblSrNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtPlayerName5))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtPlayerName4, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                        .addComponent(lblSrNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPlayerName3, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(trRow7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(trRow8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panRowShowLayout.setVerticalGroup(
            panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(trRow7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(trRow14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panRowShow, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panRowShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("TRAINEE");

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("* Mandatory Fields");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(249, 249, 249)
                .addComponent(jLabel13)
                .addGap(151, 151, 151)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 606, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(57, 74, 108));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSave.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSave.setForeground(new java.awt.Color(255, 255, 255));
        lblSave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSave.setText("SAVE");
        lblSave.setToolTipText("");
        lblSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSave, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(57, 74, 108));
        jPanel11.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblCancel.setBackground(new java.awt.Color(57, 74, 108));
        lblCancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblCancel.setForeground(new java.awt.Color(255, 255, 255));
        lblCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCancel.setText("CANCEL");
        lblCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        panAddPlayer.setBackground(new java.awt.Color(102, 102, 102));
        panAddPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAddNew.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAddNew.setForeground(new java.awt.Color(255, 255, 255));
        lblAddNew.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddNew.setText("New Trainee");
        lblAddNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddNewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAddPlayerLayout = new javax.swing.GroupLayout(panAddPlayer);
        panAddPlayer.setLayout(panAddPlayerLayout);
        panAddPlayerLayout.setHorizontalGroup(
            panAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panAddPlayerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblAddNew, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        panAddPlayerLayout.setVerticalGroup(
            panAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(panAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSaveMouseClicked

        traineeList = new ArrayList<>();

        String msg = validateFields();
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this, msg);
        } else {
            Date selectedStartDate = (Date) datePickerStart.getModel().getValue();
            Date selectedEndDate = (Date) datePickerEnd.getModel().getValue();
            BatchDao batchDao = new BatchDao();
            Batch batch = new Batch();
            batch.setName(txtBatchName.getText());
            batch.setTrainer(txtrainerName.getText());
            batch.setMedicalOffice(txtMedOffName.getText());
            batch.setAnalyzer(txtAnalyzerName.getText());
            batch.setVenue(txtVenue.getText());
            batch.setAgeGroup(ageGroup);
            batch.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedStartDate));
            batch.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedEndDate));
            for (BatchRowBean tr : batchRow) {
                addTrainee(tr.getName().getText());
            }

            batch.setTraineeList(traineeList);

            int id = batchDao.insertBatch(batch);
            if (id != 0) {
                Controller.batchDialog.close();
//                Controller.panBatchBestScorer.setRow(null);
                Controller.panBatchList.refresh();
                JOptionPane.showMessageDialog(this, "Batch '" + txtBatchName.getText() + "' Created Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add Batch");
            }

        }
    }//GEN-LAST:event_lblSaveMouseClicked

    public String validate(JTextField jf) {
        String regexName = "^[a-zA-Z ]*$";
        String val = jf.getText();
        message = "";

        if (val.equalsIgnoreCase("")) {
            message = " cannot be Empty";

        } else if (!val.matches(regexName)) {
            message = " must contains only Letters";

        }
        return message;

    }

    public void checkCharacter(java.awt.event.KeyEvent evt) {

        ((JTextField) evt.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        char c = evt.getKeyChar();
        if (!Character.isSpaceChar(c)) {
            if (!(Character.isAlphabetic(c))) {
                evt.consume();
            }
        }
    }

    public void setNumber(java.awt.event.FocusEvent evt) {
        JTextField jf = (JTextField) evt.getSource();
        String val = jf.getText();
        if (val.length() == 1) {
            jf.setText("0" + val);
        }
    }

    public String validateFields() {
        String msg = "";
        int k = 1;

        if (!validate(txtBatchName).isEmpty()) {
            msg = msg + "Name" + message + "\n";
            txtBatchName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
        if (!validate(txtMedOffName).isEmpty()) {
            msg = msg + "Name" + message + "\n";
            txtMedOffName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
        if (!validate(txtrainerName).isEmpty()) {
            msg = msg + "Name" + message + "\n";
            txtrainerName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
        if (!validate(txtAnalyzerName).isEmpty()) {
            msg = msg + "Name" + message + "\n";
            txtAnalyzerName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
//        if (datePickerStart.getModel().getValue() == null) {
//            msg = msg + "Start Date cannot be Blank\n";
//        }
//        if (datePickerEnd.getModel().getValue() == null) {
//            msg = msg + "End Date cannot be Blank\n";
//        }

        for (BatchRowBean tr : batchRow) {
            if (k <= 1) {
                if (!validate(tr.getName()).isEmpty()) {
                    msg = msg + "Trainee Name " + k + " :" + message + "\n";
                    tr.getName().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                }

            }
            k++;

        }

        return msg;

    }

    private void lblCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCancelMouseClicked
        Controller.batchDialog.close();
    }//GEN-LAST:event_lblCancelMouseClicked

    private void txtBatchNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBatchNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtBatchNameKeyTyped

    private void txtMedOffNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedOffNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtMedOffNameKeyTyped

    private void lblAddNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddNewMouseClicked
        // TODO add your handling code here:
        if (i != 0) {
            lblMinusList.get(i - 1).setVisible(false);
        }
        rowPanels.get(i).setVisible(true);
        i++;
        m++;
        if (i == 8) {
            panAddPlayer.setVisible(false);
        }
    }//GEN-LAST:event_lblAddNewMouseClicked

    private void lblSub7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub7MouseClicked
        // TODO add your handling code here:
        hidePanel(7);
    }//GEN-LAST:event_lblSub7MouseClicked

    private void txtPlayerName14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName14KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName14KeyTyped

    private void lblSub6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub6MouseClicked
        // TODO add your handling code here:
        hidePanel(6);
    }//GEN-LAST:event_lblSub6MouseClicked

    private void txtPlayerName13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName13KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName13KeyTyped

    private void lblSub5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub5MouseClicked
        // TODO add your handling code here:
        hidePanel(5);
    }//GEN-LAST:event_lblSub5MouseClicked

    private void txtPlayerName12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName12KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName12KeyTyped

    private void lblSub4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub4MouseClicked
        // TODO add your handling code here:
        hidePanel(4);
    }//GEN-LAST:event_lblSub4MouseClicked

    private void txtPlayerName11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName11KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName11KeyTyped

    private void lblSub3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub3MouseClicked
        // TODO add your handling code here:
        hidePanel(3);
    }//GEN-LAST:event_lblSub3MouseClicked

    private void txtPlayerName10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName10KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName10KeyTyped

    private void lblSub2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub2MouseClicked
        // TODO add your handling code here:
        hidePanel(2);
    }//GEN-LAST:event_lblSub2MouseClicked

    private void txtPlayerName9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName9KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName9KeyTyped

    private void lblSub1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub1MouseClicked
        // TODO add your handling code here:
        hidePanel(1);
    }//GEN-LAST:event_lblSub1MouseClicked

    private void txtPlayerName8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName8KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName8KeyTyped

    private void lblSubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubMouseClicked
        // TODO add your handling code here:
        hidePanel(0);
    }//GEN-LAST:event_lblSubMouseClicked

    private void txtPlayerName7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName7KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName7KeyTyped

    private void txtPlayerName6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName6KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName6KeyTyped

    private void txtPlayerName5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName5KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName5KeyTyped

    private void txtPlayerName4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName4KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName4KeyTyped

    private void txtPlayerName3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName3KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName3KeyTyped

    private void txtPlayerName2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName2KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName2KeyTyped

    private void txtPlayerName1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName1KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName1KeyTyped

    private void txtPlayerName14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlayerName14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlayerName14ActionPerformed

    private void txtAnalyzerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnalyzerNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtAnalyzerNameKeyTyped

    private void txtVenueKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVenueKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVenueKeyTyped

    private void txtrainerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrainerNameKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrainerNameKeyTyped

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();

            if (item.equals("Open")) {
                ageGroup = "Open";
                cmbAgeGroup.setVisible(false);
            } else if (item.equals("Under")) {
                ageGroup = "";
                cmbAgeGroup.setVisible(true);
            } else {
                ageGroup = "select";
                cmbAgeGroup.setVisible(false);
            }
            // do something with object
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void cmbAgeGroupItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbAgeGroupItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            ageGroup = "Under " + item;
            // do something with object
        }
    }//GEN-LAST:event_cmbAgeGroupItemStateChanged

    public void hidePanel(int j) {
        if (j != 0) {
            lblMinusList.get(j - 1).setVisible(true);
        }
        panAddPlayer.setVisible(true);
        rowPanels.get(j).setVisible(false);
        i--;
        m--;
    }

    public void addTrainee(String name) {
        Trainee p = new Trainee();
        p.setName(name);
        traineeList.add(p);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbAgeGroup;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblAddNew;
    private javax.swing.JLabel lblCancel;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblSave;
    private javax.swing.JTextField lblSrNo1;
    private javax.swing.JTextField lblSrNo10;
    private javax.swing.JTextField lblSrNo11;
    private javax.swing.JTextField lblSrNo12;
    private javax.swing.JTextField lblSrNo13;
    private javax.swing.JTextField lblSrNo14;
    private javax.swing.JTextField lblSrNo2;
    private javax.swing.JTextField lblSrNo3;
    private javax.swing.JTextField lblSrNo4;
    private javax.swing.JTextField lblSrNo5;
    private javax.swing.JTextField lblSrNo6;
    private javax.swing.JTextField lblSrNo7;
    private javax.swing.JTextField lblSrNo8;
    private javax.swing.JTextField lblSrNo9;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSub1;
    private javax.swing.JLabel lblSub2;
    private javax.swing.JLabel lblSub3;
    private javax.swing.JLabel lblSub4;
    private javax.swing.JLabel lblSub5;
    private javax.swing.JLabel lblSub6;
    private javax.swing.JLabel lblSub7;
    private javax.swing.JPanel panAddPlayer;
    private javax.swing.JPanel panEndDate;
    private javax.swing.JPanel panRowShow;
    private javax.swing.JPanel panStartDate;
    private javax.swing.JPanel trRow10;
    private javax.swing.JPanel trRow11;
    private javax.swing.JPanel trRow12;
    private javax.swing.JPanel trRow13;
    private javax.swing.JPanel trRow14;
    private javax.swing.JPanel trRow7;
    private javax.swing.JPanel trRow8;
    private javax.swing.JPanel trRow9;
    private javax.swing.JTextField txtAnalyzerName;
    private javax.swing.JTextField txtBatchName;
    private javax.swing.JTextField txtMedOffName;
    private javax.swing.JTextField txtPlayerName1;
    private javax.swing.JTextField txtPlayerName10;
    private javax.swing.JTextField txtPlayerName11;
    private javax.swing.JTextField txtPlayerName12;
    private javax.swing.JTextField txtPlayerName13;
    private javax.swing.JTextField txtPlayerName14;
    private javax.swing.JTextField txtPlayerName2;
    private javax.swing.JTextField txtPlayerName3;
    private javax.swing.JTextField txtPlayerName4;
    private javax.swing.JTextField txtPlayerName5;
    private javax.swing.JTextField txtPlayerName6;
    private javax.swing.JTextField txtPlayerName7;
    private javax.swing.JTextField txtPlayerName8;
    private javax.swing.JTextField txtPlayerName9;
    private javax.swing.JTextField txtVenue;
    private javax.swing.JTextField txtrainerName;
    // End of variables declaration//GEN-END:variables
}
