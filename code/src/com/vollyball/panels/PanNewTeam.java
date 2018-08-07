/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.Team;
import com.vollyball.bean.TeamRowBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.PlayerPosition;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author nishant.vibhute
 */
public class PanNewTeam extends javax.swing.JPanel {

    List<Player> playerList;
    List<JComboBox> position = new ArrayList<>();
    List<JCheckBox> captainCheckBoxList = new ArrayList<>();

    List<TeamRowBean> teamRow = new ArrayList<>();
    List<JPanel> rowPanels = new ArrayList<>();
    List<JLabel> lblMinusList = new ArrayList<>();

    int i = 0, m = 6;

    String message;

    /**
     * Creates new form PanNewTeam
     */
    public PanNewTeam() {
        initComponents();

        position.add(cmbPositon1);
        position.add(cmbPositon2);
        position.add(cmbPositon3);
        position.add(cmbPositon4);
        position.add(cmbPositon5);
        position.add(cmbPositon6);
        position.add(cmbPositon7);
        position.add(cmbPositon8);
        position.add(cmbPositon9);
        position.add(cmbPositon10);
        position.add(cmbPositon11);
        position.add(cmbPositon12);
        position.add(cmbPositon13);
        position.add(cmbPositon14);

        captainCheckBoxList.add(captain1);
        captainCheckBoxList.add(captain2);
        captainCheckBoxList.add(captain3);
        captainCheckBoxList.add(captain4);
        captainCheckBoxList.add(captain5);
        captainCheckBoxList.add(captain6);
        captainCheckBoxList.add(captain7);
        captainCheckBoxList.add(captain8);
        captainCheckBoxList.add(captain9);
        captainCheckBoxList.add(captain10);
        captainCheckBoxList.add(captain11);
        captainCheckBoxList.add(captain12);
        captainCheckBoxList.add(captain13);
        captainCheckBoxList.add(captain14);

        lblMinusList.add(lblSub);

        lblMinusList.add(lblSub1);
        lblMinusList.add(lblSub2);
        lblMinusList.add(lblSub3);
        lblMinusList.add(lblSub4);

        lblMinusList.add(lblSub5);
        lblMinusList.add(lblSub6);
        lblMinusList.add(lblSub7);

        teamRow.add(new TeamRowBean(txtPlayerName1, txtChest1, cmbPositon1, captain1, lblSrNo1));
        teamRow.add(new TeamRowBean(txtPlayerName2, txtChest2, cmbPositon2, captain2, lblSrNo2));
        teamRow.add(new TeamRowBean(txtPlayerName3, txtChest3, cmbPositon3, captain3, lblSrNo3));
        teamRow.add(new TeamRowBean(txtPlayerName4, txtChest4, cmbPositon4, captain4, lblSrNo4));
        teamRow.add(new TeamRowBean(txtPlayerName5, txtChest5, cmbPositon5, captain5, lblSrNo5));
        teamRow.add(new TeamRowBean(txtPlayerName6, txtChest6, cmbPositon6, captain6, lblSrNo6));
        teamRow.add(new TeamRowBean(txtPlayerName7, txtChest7, cmbPositon7, captain7, lblSrNo7));
        teamRow.add(new TeamRowBean(txtPlayerName8, txtChest8, cmbPositon8, captain8, lblSrNo8));
        teamRow.add(new TeamRowBean(txtPlayerName9, txtChest9, cmbPositon9, captain9, lblSrNo9));
        teamRow.add(new TeamRowBean(txtPlayerName10, txtChest10, cmbPositon10, captain10, lblSrNo10));
        teamRow.add(new TeamRowBean(txtPlayerName11, txtChest11, cmbPositon11, captain11, lblSrNo11));
        teamRow.add(new TeamRowBean(txtPlayerName12, txtChest12, cmbPositon12, captain12, lblSrNo12));
        teamRow.add(new TeamRowBean(txtPlayerName13, txtChest13, cmbPositon13, captain13, lblSrNo13));
        teamRow.add(new TeamRowBean(txtPlayerName14, txtChest14, cmbPositon14, captain14, lblSrNo14));

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
        txtTeamName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCoachName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtAsstCoachName = new javax.swing.JTextField();
        txtAnalyzerName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtMedOffName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtrainerName = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtShortCode = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblCaptain = new javax.swing.JLabel();
        panRowShow = new javax.swing.JPanel();
        lblSrNo1 = new javax.swing.JTextField();
        txtPlayerName1 = new javax.swing.JTextField();
        txtChest1 = new javax.swing.JTextField();
        cmbPositon1 = new javax.swing.JComboBox();
        jPanel15 = new javax.swing.JPanel();
        captain1 = new javax.swing.JCheckBox();
        lblSrNo2 = new javax.swing.JTextField();
        txtPlayerName2 = new javax.swing.JTextField();
        txtChest2 = new javax.swing.JTextField();
        cmbPositon2 = new javax.swing.JComboBox();
        jPanel17 = new javax.swing.JPanel();
        captain2 = new javax.swing.JCheckBox();
        jPanel18 = new javax.swing.JPanel();
        captain3 = new javax.swing.JCheckBox();
        cmbPositon3 = new javax.swing.JComboBox();
        txtPlayerName3 = new javax.swing.JTextField();
        txtChest3 = new javax.swing.JTextField();
        lblSrNo3 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        captain4 = new javax.swing.JCheckBox();
        cmbPositon4 = new javax.swing.JComboBox();
        txtPlayerName4 = new javax.swing.JTextField();
        txtChest4 = new javax.swing.JTextField();
        lblSrNo4 = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        captain5 = new javax.swing.JCheckBox();
        cmbPositon5 = new javax.swing.JComboBox();
        txtPlayerName5 = new javax.swing.JTextField();
        txtChest5 = new javax.swing.JTextField();
        lblSrNo5 = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        captain6 = new javax.swing.JCheckBox();
        cmbPositon6 = new javax.swing.JComboBox();
        txtPlayerName6 = new javax.swing.JTextField();
        txtChest6 = new javax.swing.JTextField();
        lblSrNo6 = new javax.swing.JTextField();
        trRow7 = new javax.swing.JPanel();
        lblSrNo7 = new javax.swing.JTextField();
        txtPlayerName7 = new javax.swing.JTextField();
        txtChest7 = new javax.swing.JTextField();
        cmbPositon7 = new javax.swing.JComboBox();
        jPanel22 = new javax.swing.JPanel();
        captain7 = new javax.swing.JCheckBox();
        lblSub = new javax.swing.JLabel();
        trRow8 = new javax.swing.JPanel();
        lblSrNo8 = new javax.swing.JTextField();
        txtPlayerName8 = new javax.swing.JTextField();
        txtChest8 = new javax.swing.JTextField();
        cmbPositon8 = new javax.swing.JComboBox();
        jPanel23 = new javax.swing.JPanel();
        captain8 = new javax.swing.JCheckBox();
        lblSub1 = new javax.swing.JLabel();
        trRow9 = new javax.swing.JPanel();
        txtChest9 = new javax.swing.JTextField();
        txtPlayerName9 = new javax.swing.JTextField();
        lblSrNo9 = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        captain9 = new javax.swing.JCheckBox();
        cmbPositon9 = new javax.swing.JComboBox();
        lblSub2 = new javax.swing.JLabel();
        trRow10 = new javax.swing.JPanel();
        lblSrNo10 = new javax.swing.JTextField();
        txtPlayerName10 = new javax.swing.JTextField();
        txtChest10 = new javax.swing.JTextField();
        cmbPositon10 = new javax.swing.JComboBox();
        jPanel25 = new javax.swing.JPanel();
        captain10 = new javax.swing.JCheckBox();
        lblSub3 = new javax.swing.JLabel();
        trRow11 = new javax.swing.JPanel();
        lblSrNo11 = new javax.swing.JTextField();
        txtPlayerName11 = new javax.swing.JTextField();
        txtChest11 = new javax.swing.JTextField();
        cmbPositon11 = new javax.swing.JComboBox();
        jPanel26 = new javax.swing.JPanel();
        captain11 = new javax.swing.JCheckBox();
        lblSub4 = new javax.swing.JLabel();
        trRow12 = new javax.swing.JPanel();
        lblSrNo12 = new javax.swing.JTextField();
        txtPlayerName12 = new javax.swing.JTextField();
        txtChest12 = new javax.swing.JTextField();
        cmbPositon12 = new javax.swing.JComboBox();
        jPanel27 = new javax.swing.JPanel();
        captain12 = new javax.swing.JCheckBox();
        lblSub5 = new javax.swing.JLabel();
        trRow13 = new javax.swing.JPanel();
        lblSrNo13 = new javax.swing.JTextField();
        txtPlayerName13 = new javax.swing.JTextField();
        txtChest13 = new javax.swing.JTextField();
        cmbPositon13 = new javax.swing.JComboBox();
        jPanel28 = new javax.swing.JPanel();
        captain13 = new javax.swing.JCheckBox();
        lblSub6 = new javax.swing.JLabel();
        trRow14 = new javax.swing.JPanel();
        lblSrNo14 = new javax.swing.JTextField();
        txtPlayerName14 = new javax.swing.JTextField();
        txtChest14 = new javax.swing.JTextField();
        cmbPositon14 = new javax.swing.JComboBox();
        jPanel29 = new javax.swing.JPanel();
        captain14 = new javax.swing.JCheckBox();
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
        lblHeading.setText("New Team");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
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
        jLabel2.setText("Team Name*");

        txtTeamName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTeamName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTeamName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTeamNameKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(45, 62, 79));
        jLabel3.setText("Coach Name*");

        txtCoachName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCoachName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtCoachName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCoachNameKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(45, 62, 79));
        jLabel4.setText("Asst. Coach Name ");

        txtAsstCoachName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtAsstCoachName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtAsstCoachName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAsstCoachNameKeyTyped(evt);
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
        jLabel6.setText("Medical Officer Name");

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

        txtrainerName.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtrainerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtrainerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrainerNameKeyTyped(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(45, 62, 79));
        jLabel18.setText("Trainer Name");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(45, 62, 79));
        jLabel20.setText("Short Code*");

        txtShortCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtShortCode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtShortCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtShortCodeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addComponent(txtTeamName))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAnalyzerName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtShortCode, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtrainerName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMedOffName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAsstCoachName, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCoachName, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCoachName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel20)
                    .addComponent(txtShortCode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrainerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(txtAsstCoachName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMedOffName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtAnalyzerName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("NAME *");
        jLabel8.setToolTipText("");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("CHEST No. *");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("POSITION *");
        jLabel11.setToolTipText("");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("SR No.");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblCaptain.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        lblCaptain.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCaptain.setText("CAPTAIN *");
        lblCaptain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblCaptain, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblCaptain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        txtChest1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest1FocusLost(evt);
            }
        });
        txtChest1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest1KeyTyped(evt);
            }
        });

        cmbPositon1.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel15.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N

        captain1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        txtChest2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest2FocusLost(evt);
            }
        });
        txtChest2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest2KeyTyped(evt);
            }
        });

        cmbPositon2.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain2ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain2)
                .addGap(42, 42, 42))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain3ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain3)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbPositon3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPlayerName3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName3KeyTyped(evt);
            }
        });

        txtChest3.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest3FocusLost(evt);
            }
        });
        txtChest3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest3KeyTyped(evt);
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

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain4ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain4)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbPositon4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPlayerName4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName4KeyTyped(evt);
            }
        });

        txtChest4.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest4FocusLost(evt);
            }
        });
        txtChest4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest4KeyTyped(evt);
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

        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain5ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain5)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbPositon5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPlayerName5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName5KeyTyped(evt);
            }
        });

        txtChest5.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest5FocusLost(evt);
            }
        });
        txtChest5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest5KeyTyped(evt);
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

        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain6ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain6)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbPositon6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtPlayerName6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtPlayerName6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlayerName6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtPlayerName6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName6KeyTyped(evt);
            }
        });

        txtChest6.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest6FocusLost(evt);
            }
        });
        txtChest6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest6KeyTyped(evt);
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

        txtChest7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest7FocusLost(evt);
            }
        });
        txtChest7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest7KeyTyped(evt);
            }
        });

        cmbPositon7.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cmbPositon7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPositon7ActionPerformed(evt);
            }
        });

        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain7ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain7)
                .addGap(42, 42, 42))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain7, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon7, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        trRow7Layout.setVerticalGroup(
            trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow7Layout.createSequentialGroup()
                .addGroup(trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblSub, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow7Layout.createSequentialGroup()
                        .addGroup(trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(trRow7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSrNo7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPlayerName7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtChest7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbPositon7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)))
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

        txtChest8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest8.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest8FocusLost(evt);
            }
        });
        txtChest8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest8KeyTyped(evt);
            }
        });

        cmbPositon8.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain8ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain8)
                .addGap(42, 42, 42))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain8, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName8, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon8, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        trRow8Layout.setVerticalGroup(
            trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow8Layout.createSequentialGroup()
                .addGroup(trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(trRow8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        trRow9.setBackground(new java.awt.Color(255, 255, 255));

        txtChest9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest9FocusLost(evt);
            }
        });
        txtChest9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest9KeyTyped(evt);
            }
        });

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

        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain9ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain9)
                .addGap(42, 42, 42))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbPositon9.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName9, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon9, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow9Layout.setVerticalGroup(
            trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow9Layout.createSequentialGroup()
                        .addGroup(trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(trRow9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSrNo9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPlayerName9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtChest9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbPositon9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        txtChest10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest10.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest10FocusLost(evt);
            }
        });
        txtChest10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest10KeyTyped(evt);
            }
        });

        cmbPositon10.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain10ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain10)
                .addGap(42, 42, 42))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName10, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon10, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSub3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow10Layout.setVerticalGroup(
            trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, trRow10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPositon10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        txtChest11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest11.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest11FocusLost(evt);
            }
        });
        txtChest11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest11KeyTyped(evt);
            }
        });

        cmbPositon11.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain11ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain11)
                .addGap(42, 42, 42))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain11, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName11, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest11, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon11, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow11Layout.setVerticalGroup(
            trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow11Layout.createSequentialGroup()
                .addGroup(trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(trRow11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        txtChest12.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest12.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest12FocusLost(evt);
            }
        });
        txtChest12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest12KeyTyped(evt);
            }
        });

        cmbPositon12.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain12ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain12)
                .addGap(42, 42, 42))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName12, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSub5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        trRow12Layout.setVerticalGroup(
            trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, trRow12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSrNo12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPlayerName12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChest12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbPositon12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        txtChest13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest13.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest13FocusLost(evt);
            }
        });
        txtChest13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest13KeyTyped(evt);
            }
        });

        cmbPositon13.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain13ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain13)
                .addGap(42, 42, 42))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName13, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblSub6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        trRow13Layout.setVerticalGroup(
            trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(trRow13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSub6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(trRow13Layout.createSequentialGroup()
                        .addGroup(trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(trRow13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lblSrNo13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPlayerName13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtChest13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbPositon13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        txtPlayerName14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPlayerName14KeyTyped(evt);
            }
        });

        txtChest14.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        txtChest14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtChest14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtChest14.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtChest14FocusLost(evt);
            }
        });
        txtChest14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtChest14KeyTyped(evt);
            }
        });

        cmbPositon14.setFont(new java.awt.Font("Times New Roman", 0, 13)); // NOI18N
        cmbPositon14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        captain14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                captain14ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(captain14)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(captain14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                .addGap(0, 0, 0)
                .addComponent(txtPlayerName14, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtChest14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(cmbPositon14, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSub7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        trRow14Layout.setVerticalGroup(
            trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSub7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(trRow14Layout.createSequentialGroup()
                .addGroup(trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(trRow14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 4, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panRowShowLayout = new javax.swing.GroupLayout(panRowShow);
        panRowShow.setLayout(panRowShowLayout);
        panRowShowLayout.setHorizontalGroup(
            panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(trRow13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(trRow14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(trRow8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                        .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panRowShowLayout.createSequentialGroup()
                                    .addComponent(lblSrNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(txtPlayerName3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(txtChest3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(cmbPositon3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                        .addComponent(lblSrNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtPlayerName5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(txtChest5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(cmbPositon5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panRowShowLayout.createSequentialGroup()
                                        .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(panRowShowLayout.createSequentialGroup()
                                                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(panRowShowLayout.createSequentialGroup()
                                                        .addComponent(lblSrNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(txtPlayerName2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(txtChest2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(cmbPositon2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panRowShowLayout.createSequentialGroup()
                                                        .addComponent(lblSrNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(txtPlayerName1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(txtChest1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(0, 0, 0)
                                                        .addComponent(cmbPositon1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(panRowShowLayout.createSequentialGroup()
                                                .addComponent(lblSrNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(txtPlayerName4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(txtChest4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(cmbPositon4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, 0)
                                                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(4, 4, 4))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panRowShowLayout.createSequentialGroup()
                                    .addComponent(lblSrNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(txtPlayerName6, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(txtChest6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(cmbPositon6, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(trRow7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(trRow9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trRow12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trRow11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trRow10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panRowShowLayout.setVerticalGroup(
            panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRowShowLayout.createSequentialGroup()
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panRowShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSrNo6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPlayerName6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtChest6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmbPositon6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addComponent(trRow14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panRowShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRowShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("PLAYERS");

        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setText("* Mandatory Fields");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(178, 178, 178)
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)))
                .addGap(0, 0, 0))
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
                .addGap(233, 233, 233)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
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
        lblAddNew.setText("New Player");
        lblAddNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddNewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAddPlayerLayout = new javax.swing.GroupLayout(panAddPlayer);
        panAddPlayer.setLayout(panAddPlayerLayout);
        panAddPlayerLayout.setHorizontalGroup(
            panAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addContainerGap())
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
                .addGap(22, 22, 22)
                .addComponent(panAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSaveMouseClicked

        playerList = new ArrayList<>();

        String msg = validateFields();
        if (!msg.isEmpty()) {
            JOptionPane.showMessageDialog(this, msg);
        } else {
            msg = checkDuplicateChestNum();
            if (!msg.isEmpty()) {
                JOptionPane.showMessageDialog(this, msg);
            } else {
                TeamDao teamDao = new TeamDao();
                Team team = new Team();
                team.setName(txtTeamName.getText());
                team.setShortCode(txtShortCode.getText());
                team.setCoach(txtCoachName.getText());
                team.setAsstCoach(txtAsstCoachName.getText());
                team.setTrainer(txtrainerName.getText());
                team.setMedicalOffice(txtMedOffName.getText());
                team.setAnalyzer(txtAnalyzerName.getText());
                team.setCompId(Controller.competitionId);

                for (TeamRowBean tr : teamRow) {
                    addPlayer(tr.getName().getText(), tr.getChestnum().getText(), PlayerPosition.getIdByName(String.valueOf(tr.getPosition().getSelectedItem())).getId(), tr.getCaptain().isSelected());
                }

                team.setPlayerList(playerList);

                int id = teamDao.insertTeam(team);

                if (id != 0) {
                    Controller.teamDialog.close();
                    Controller.panTeamBestScorer.setRow(null);
                    JOptionPane.showMessageDialog(this, "Team '" + txtTeamName.getText() + "' Created Successfully");
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to add team");
                }
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

    public String validateNumber(JTextField jf) {
        String regexName = "^[0-9]*$";
        String val = jf.getText();
        message = "";

        if (val.equalsIgnoreCase("")) {
            message = " cannot be Empty";

        } else if (!val.matches(regexName)) {
            message = " must contains only Digits";

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

    public void checkNumber(java.awt.event.KeyEvent evt) {
        ((JTextField) evt.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c))) {
            evt.consume();
        }

    }

    public void setNumber(java.awt.event.FocusEvent evt) {
        JTextField jf = (JTextField) evt.getSource();
        String val = jf.getText();
        if (val.length() == 1) {
            jf.setText("0" + val);
        }
    }

    public String checkDuplicateChestNum() {
        String msg = "";
        List<String> chestNumList = new ArrayList<>();
        int k = 1;
        for (TeamRowBean tr : teamRow) {
            if (k <= m) {
                if (chestNumList.contains(tr.getChestnum().getText())) {
                    tr.getChestnum().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                    msg = "Chest Number must be unique";
                } else {
                    chestNumList.add(tr.getChestnum().getText());
                }

            }
            k++;
        }
        return msg;
    }

    public String validateFields() {
        String msg = "";
        boolean isCaptainSelected = false;
        int k = 1;

        if (!validate(txtTeamName).isEmpty()) {
            msg = msg + "Name" + message + "\n";
            txtTeamName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
        if (!validate(txtShortCode).isEmpty()) {
            msg = msg + "ShortCode" + message + "\n";
            txtShortCode.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
        }
        if (!validate(txtCoachName).isEmpty()) {
            msg = msg + "Coach Name" + message + "\n";
            txtCoachName.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));

        }

        for (TeamRowBean tr : teamRow) {
            if (k <= m) {
                if (!validate(tr.getName()).isEmpty()) {
                    msg = msg + "Player Name " + k + " :" + message + "\n";
                    tr.getName().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                }
                if (!validateNumber(tr.getChestnum()).isEmpty()) {
                    msg = msg + "Chest Num " + k + " : " + message + "\n";
                    tr.getChestnum().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                }
                if (tr.getPosition().getSelectedIndex() == 0) {
                    msg = msg + "Position " + k + " : Select position of player" + "\n";
                    tr.getPosition().setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
                }
                if (!isCaptainSelected) {
                    if (tr.getCaptain().isSelected()) {
                        isCaptainSelected = true;
                    }
                }
            }
            k++;

        }

        if (!isCaptainSelected) {

            msg = msg + "Select Atleast One Captain";
            lblCaptain.setForeground(Color.red);
        }
        return msg;

    }

    private void lblCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCancelMouseClicked
        Controller.teamDialog.close();
    }//GEN-LAST:event_lblCancelMouseClicked

    private void txtrainerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrainerNameKeyTyped

        checkCharacter(evt);
    }//GEN-LAST:event_txtrainerNameKeyTyped

    private void txtTeamNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTeamNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtTeamNameKeyTyped

    private void txtAsstCoachNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAsstCoachNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtAsstCoachNameKeyTyped

    private void txtAnalyzerNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnalyzerNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtAnalyzerNameKeyTyped

    private void txtMedOffNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMedOffNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtMedOffNameKeyTyped

    private void txtShortCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtShortCodeKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtShortCodeKeyTyped

    private void txtCoachNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCoachNameKeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtCoachNameKeyTyped

    private void txtChest1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest1FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest1FocusLost

    private void captain1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain1ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain1ItemStateChanged

    private void txtChest2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest2FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest2FocusLost

    private void captain2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain2ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain2ItemStateChanged

    private void captain3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain3ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain3ItemStateChanged

    private void txtChest3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest3FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest3FocusLost

    private void captain4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain4ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain4ItemStateChanged

    private void txtChest4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest4FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest4FocusLost

    private void captain5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain5ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain5ItemStateChanged

    private void txtChest5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest5FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest5FocusLost

    private void captain6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain6ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain6ItemStateChanged

    private void txtChest6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest6FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest6FocusLost

    private void captain7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain7ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain7ItemStateChanged

    private void txtChest7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest7FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest7FocusLost

    private void captain8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain8ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain8ItemStateChanged

    private void txtChest8FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest8FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest8FocusLost

    private void captain9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain9ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain9ItemStateChanged

    private void txtChest9FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest9FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest9FocusLost

    private void captain10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain10ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain10ItemStateChanged

    private void txtChest10FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest10FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest10FocusLost

    private void captain11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain11ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain11ItemStateChanged

    private void txtChest11FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest11FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest11FocusLost

    private void captain12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain12ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain12ItemStateChanged

    private void txtChest12FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest12FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest12FocusLost

    private void txtPlayerName1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName1KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName1KeyTyped

    private void txtPlayerName2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName2KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName2KeyTyped

    private void txtPlayerName3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName3KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName3KeyTyped

    private void txtPlayerName4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName4KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName4KeyTyped

    private void txtPlayerName5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName5KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName5KeyTyped

    private void txtPlayerName6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName6KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName6KeyTyped

    private void txtPlayerName7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName7KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName7KeyTyped

    private void txtPlayerName8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName8KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName8KeyTyped

    private void txtPlayerName9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName9KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName9KeyTyped

    private void txtPlayerName10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName10KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName10KeyTyped

    private void txtPlayerName11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName11KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName11KeyTyped

    private void txtPlayerName12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName12KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName12KeyTyped

    private void txtChest1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest1KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest1KeyTyped

    private void txtChest2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest2KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest2KeyTyped

    private void txtChest3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest3KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest3KeyTyped

    private void txtChest4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest4KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest4KeyTyped

    private void txtChest5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest5KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest5KeyTyped

    private void txtChest6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest6KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest6KeyTyped

    private void txtChest7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest7KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest7KeyTyped

    private void txtChest8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest8KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest8KeyTyped

    private void txtChest9KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest9KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest9KeyTyped

    private void txtChest10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest10KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest10KeyTyped

    private void txtChest11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest11KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest11KeyTyped

    private void txtChest12KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest12KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest12KeyTyped

    private void txtPlayerName13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName13KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName13KeyTyped

    private void txtChest13KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest13KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest13KeyTyped

    private void txtChest13FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest13FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest13FocusLost

    private void captain13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain13ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain13ItemStateChanged

    private void captain14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_captain14ItemStateChanged
        enabledDisabledCheckBox(evt);
    }//GEN-LAST:event_captain14ItemStateChanged

    private void txtChest14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChest14KeyTyped
        checkNumber(evt);
    }//GEN-LAST:event_txtChest14KeyTyped

    private void txtChest14FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtChest14FocusLost
        setNumber(evt);
    }//GEN-LAST:event_txtChest14FocusLost

    private void txtPlayerName14KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerName14KeyTyped
        checkCharacter(evt);
    }//GEN-LAST:event_txtPlayerName14KeyTyped

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

    private void lblSubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubMouseClicked
        // TODO add your handling code here:
        hidePanel(0);
    }//GEN-LAST:event_lblSubMouseClicked

    private void lblSub1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub1MouseClicked
        // TODO add your handling code here:
        hidePanel(1);
    }//GEN-LAST:event_lblSub1MouseClicked

    private void lblSub2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub2MouseClicked
        // TODO add your handling code here:
        hidePanel(2);

    }//GEN-LAST:event_lblSub2MouseClicked

    private void lblSub3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub3MouseClicked
        // TODO add your handling code here:
        hidePanel(3);

    }//GEN-LAST:event_lblSub3MouseClicked

    private void lblSub4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub4MouseClicked
        // TODO add your handling code here:
        hidePanel(4);

    }//GEN-LAST:event_lblSub4MouseClicked

    private void lblSub5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub5MouseClicked
        // TODO add your handling code here:
        hidePanel(5);

    }//GEN-LAST:event_lblSub5MouseClicked

    private void lblSub6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub6MouseClicked
        // TODO add your handling code here:
        hidePanel(6);

    }//GEN-LAST:event_lblSub6MouseClicked

    private void lblSub7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub7MouseClicked
        // TODO add your handling code here:
        hidePanel(7);

    }//GEN-LAST:event_lblSub7MouseClicked

    private void cmbPositon7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPositon7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPositon7ActionPerformed

    public void hidePanel(int j) {
        if (j != 0) {
            lblMinusList.get(j - 1).setVisible(true);
        }
        panAddPlayer.setVisible(true);
        rowPanels.get(j).setVisible(false);
        i--;
        m--;
    }

    public void enabledDisabledCheckBox(java.awt.event.ItemEvent evt) {
        lblCaptain.setForeground(Color.BLACK);
        String type = "";
        JCheckBox checkBox = (JCheckBox) evt.getSource();
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            type = "checked";
        } else {
            type = "unchecked";
        }

        if (type.equals("checked")) {
            for (JCheckBox chk : captainCheckBoxList) {
                if (chk != checkBox) {
                    chk.setEnabled(false);
                }
            }
        } else {
            for (JCheckBox chk : captainCheckBoxList) {
                if (chk != checkBox) {
                    chk.setEnabled(true);
                }
            }
        }
    }

    public void addPlayer(String name, String chestNum, int position, boolean isCaptain) {
        Player p = new Player();
        p.setName(name);
        p.setChestNo(chestNum);
        p.setPosition(position);
        p.setCaptain(isCaptain);
        playerList.add(p);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox captain1;
    private javax.swing.JCheckBox captain10;
    private javax.swing.JCheckBox captain11;
    private javax.swing.JCheckBox captain12;
    private javax.swing.JCheckBox captain13;
    private javax.swing.JCheckBox captain14;
    private javax.swing.JCheckBox captain2;
    private javax.swing.JCheckBox captain3;
    private javax.swing.JCheckBox captain4;
    private javax.swing.JCheckBox captain5;
    private javax.swing.JCheckBox captain6;
    private javax.swing.JCheckBox captain7;
    private javax.swing.JCheckBox captain8;
    private javax.swing.JCheckBox captain9;
    private javax.swing.JComboBox cmbPositon1;
    private javax.swing.JComboBox cmbPositon10;
    private javax.swing.JComboBox cmbPositon11;
    private javax.swing.JComboBox cmbPositon12;
    private javax.swing.JComboBox cmbPositon13;
    private javax.swing.JComboBox cmbPositon14;
    private javax.swing.JComboBox cmbPositon2;
    private javax.swing.JComboBox cmbPositon3;
    private javax.swing.JComboBox cmbPositon4;
    private javax.swing.JComboBox cmbPositon5;
    private javax.swing.JComboBox cmbPositon6;
    private javax.swing.JComboBox cmbPositon7;
    private javax.swing.JComboBox cmbPositon8;
    private javax.swing.JComboBox cmbPositon9;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblAddNew;
    private javax.swing.JLabel lblCancel;
    private javax.swing.JLabel lblCaptain;
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
    private javax.swing.JPanel panRowShow;
    private javax.swing.JPanel trRow10;
    private javax.swing.JPanel trRow11;
    private javax.swing.JPanel trRow12;
    private javax.swing.JPanel trRow13;
    private javax.swing.JPanel trRow14;
    private javax.swing.JPanel trRow7;
    private javax.swing.JPanel trRow8;
    private javax.swing.JPanel trRow9;
    private javax.swing.JTextField txtAnalyzerName;
    private javax.swing.JTextField txtAsstCoachName;
    private javax.swing.JTextField txtChest1;
    private javax.swing.JTextField txtChest10;
    private javax.swing.JTextField txtChest11;
    private javax.swing.JTextField txtChest12;
    private javax.swing.JTextField txtChest13;
    private javax.swing.JTextField txtChest14;
    private javax.swing.JTextField txtChest2;
    private javax.swing.JTextField txtChest3;
    private javax.swing.JTextField txtChest4;
    private javax.swing.JTextField txtChest5;
    private javax.swing.JTextField txtChest6;
    private javax.swing.JTextField txtChest7;
    private javax.swing.JTextField txtChest8;
    private javax.swing.JTextField txtChest9;
    private javax.swing.JTextField txtCoachName;
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
    private javax.swing.JTextField txtShortCode;
    private javax.swing.JTextField txtTeamName;
    private javax.swing.JTextField txtrainerName;
    // End of variables declaration//GEN-END:variables
}
