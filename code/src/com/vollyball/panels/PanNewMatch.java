/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.Team;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.Phase;
import com.vollyball.enums.SubPhase;
import com.vollyball.util.DateLabelFormatter;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanNewMatch extends javax.swing.JPanel {

    String team1, team2;
    UtilDateModel modelStart = new UtilDateModel();
    JDatePanelImpl datePanelStart = new JDatePanelImpl(modelStart);
    JDatePickerImpl datePickerStart = new JDatePickerImpl(datePanelStart, new DateLabelFormatter());
    LinkedHashMap<String, Integer> teamsMap;
    String phase;
    int matchId;

    /**
     * Creates new form PanNewMatch
     */
    public PanNewMatch() {
        initComponents();
        ((JLabel) cmbHH.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        ((JLabel) cmbMm.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        cmbMm.getEditor().getEditorComponent().setBackground(Color.WHITE);
        ((JLabel) team1combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        team1combo.getEditor().getEditorComponent().setBackground(Color.WHITE);
        ((JLabel) team2combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        team2combo.getEditor().getEditorComponent().setBackground(Color.WHITE);

        datePickerStart.setBounds(10, 5, 144, 28);

        JFormattedTextField textField = datePickerStart.getJFormattedTextField();
        textField.setBackground(Color.WHITE);
        datePickerStart.setButtonFocusable(false);
        textField.setBorder(null);
        jPanel3.add(datePickerStart);

        TeamDao teamDao = new TeamDao();
        List<Team> teams = teamDao.getTeams(Controller.competitionId);
        teamsMap = new LinkedHashMap<>();
        team1combo.addItem("Select");
        team2combo.addItem("Select");
        for (Team team : teams) {
            teamsMap.put(team.getName(), team.getId());
            team1combo.addItem(team.getName());
            team2combo.addItem(team.getName());
        }

        for (Phase dir : Phase.values()) {
            // do what you want
            cmbPhase.addItem(dir.getName());
            cmbSubPhase.setVisible(false);
        }
    }

    public PanNewMatch(int id) {
        initComponents();
        this.matchId = id;
        System.out.println("id panmatchnew" + id);
        MatchDao md = new MatchDao();
        MatchBean mb = md.getMatchesById(Controller.competitionId, matchId);
        ((JLabel) cmbHH.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        ((JLabel) cmbMm.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        cmbMm.getEditor().getEditorComponent().setBackground(Color.WHITE);
        ((JLabel) team1combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        team1combo.getEditor().getEditorComponent().setBackground(Color.WHITE);
        ((JLabel) team2combo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        team2combo.getEditor().getEditorComponent().setBackground(Color.WHITE);

        datePickerStart.setBounds(10, 5, 144, 28);

        JFormattedTextField textField = datePickerStart.getJFormattedTextField();
        textField.setBackground(Color.WHITE);
        datePickerStart.setButtonFocusable(false);
        textField.setBorder(null);
        jPanel3.add(datePickerStart);
        txtDayNum.getText();
        txtCity.setText(mb.getPlace());
        txtMatchNum.setText(Integer.toString(mb.getMatchNumber()));
        cmbHH.addItem(mb.getTime());

        TeamDao teamDao = new TeamDao();
        List<Team> teams = teamDao.getTeams(Controller.competitionId);
        teamsMap = new LinkedHashMap<>();
        team1combo.addItem(mb.getTeam1name());
        team2combo.addItem(mb.getTeam2name());

        for (Team team : teams) {
            teamsMap.put(team.getName(), team.getId());
            team1combo.addItem(team.getName());
            team2combo.addItem(team.getName());
            
        }
//        cmbPhase.addItem(mb.getPhase());
        for (Phase dir : Phase.values()) {
            // do what you want
            cmbPhase.addItem(dir.getName());
            //cmbSubPhase.setVisible(false);
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

        jPanel4 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        team1combo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        team2combo = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDayNum = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMatchNum = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cmbPhase = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        cmbSubPhase = new javax.swing.JComboBox<>();
        cmbHH = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cmbMm = new javax.swing.JComboBox<>();

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jCheckBox1.setText("jCheckBox1");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        team1combo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        team1combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                team1comboItemStateChanged(evt);
            }
        });
        team1combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team1comboActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Team 1*");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("V/s");

        team2combo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        team2combo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                team2comboItemStateChanged(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Team 2*");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Day Number*");

        txtDayNum.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Date*");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 33, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Time* (HH:MM)");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Phase*");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Place*");

        txtCity.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Match Number*");

        txtMatchNum.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtMatchNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMatchNumActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(57, 74, 108));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setBackground(new java.awt.Color(0, 153, 255));
        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("SAVE");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("New Match");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        cmbPhase.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cmbPhase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbPhase.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPhaseItemStateChanged(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        cmbSubPhase.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        cmbSubPhase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSubPhaseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmbSubPhase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(cmbSubPhase, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        cmbHH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24" }));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel10.setText(":");

        cmbMm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(163, 163, 163)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(team1combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(team2combo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(189, 189, 189)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtMatchNum, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)))
                            .addComponent(txtCity, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(141, 141, 141)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel7)
                                            .addComponent(cmbPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, 0)
                                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtDayNum, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE))
                                .addGap(57, 57, 57)
                                .addComponent(cmbHH, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(team1combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(team2combo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDayNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatchNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbHH, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void team1comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_team1comboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_team1comboActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:

        String msg = validateFields();

        if (msg.isEmpty()) {
            if (matchId == 0) {
                MatchDao matchDao = new MatchDao();
                MatchBean mb = new MatchBean();
                Date selectedStartDate = (Date) datePickerStart.getModel().getValue();
                mb.setTeam1(teamsMap.get(team1));
                mb.setTeam2(teamsMap.get(team2));
                mb.setDayNumber(Integer.parseInt(txtDayNum.getText()));
                mb.setMatchNumber(Integer.parseInt(txtMatchNum.getText()));
                mb.setDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedStartDate));
                mb.setTime(cmbHH.getSelectedItem() + ":" + (cmbMm.getSelectedItem()));
                phase = cmbPhase.getSelectedItem() + "" + (cmbSubPhase.getSelectedItem() == null ? "" : cmbSubPhase.getSelectedItem());
                mb.setPhase(phase);
                mb.setPlace(txtCity.getText());
                mb.setCompId(Controller.competitionId);
                int count = matchDao.insertMatch(mb);

                if (count != 0) {

                    Controller.matchDialog.close();
                    Controller.panMatchReport.Refresh();
                    JOptionPane.showMessageDialog(this, "Added New Match '" + teamsMap.get(team1) + "' vs '" + teamsMap.get(team2) + "'");

                } else {
                    JOptionPane.showMessageDialog(this, "Failed");
                }
            } else {
                MatchDao matchDao = new MatchDao();
                MatchBean mb = new MatchBean();
                mb.setId(matchId);
                Date selectedStartDate = (Date) datePickerStart.getModel().getValue();
                mb.setTeam1(teamsMap.get(team1));
                mb.setTeam2(teamsMap.get(team2));
                mb.setDayNumber(Integer.parseInt(txtDayNum.getText()));
                mb.setMatchNumber(Integer.parseInt(txtMatchNum.getText()));
                mb.setDate(new SimpleDateFormat("yyyy-MM-dd").format(selectedStartDate));
                mb.setTime(cmbHH.getSelectedItem() + ":" + (cmbMm.getSelectedItem()));
                phase = cmbPhase.getSelectedItem() + "" + (cmbSubPhase.getSelectedItem() == null ? "" : cmbSubPhase.getSelectedItem());
                mb.setPhase(phase);
                mb.setPlace(txtCity.getText());
                mb.setCompId(Controller.competitionId);
                int count = matchDao.updateMatch(mb);

                if (count != 0) {

//                    Controller.matchDialog.close();
                    Controller.panMatchReport.Refresh();
                    JOptionPane.showMessageDialog(this, "Updated New Match '" + teamsMap.get(team1) + "' vs '" + teamsMap.get(team2) + "'");

                } else {
                    JOptionPane.showMessageDialog(this, "Failed");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, msg);
        }

    }//GEN-LAST:event_jLabel11MouseClicked

    public String validateFields() {
        String msg = "";

        if (!team1.equalsIgnoreCase("Select") && !team2.equalsIgnoreCase("Select")) {

            if (team1.equals(team2)) {
                msg = msg + "Both team cannot be same\n";
            }
        } else {
            if (team1.equalsIgnoreCase("Select")) {
                msg = msg + "Please Select Team 1\n";
            }
            if (team2.equalsIgnoreCase("Select")) {
                msg = msg + "Please Select Team 2\n";
            }
        }

        if (txtDayNum.getText().equals("")) {
            msg = msg + "Day Num cannot be Blank\n";
        }

        if (txtMatchNum.getText().equals("")) {
            msg = msg + "Match Num cannot be Blank\n";
        }

        if (datePickerStart.getModel().getValue() == null) {
            msg = msg + "Date cannot be Blank\n";
        }
        if (cmbHH.getSelectedItem().equals("Select")) {
            msg = msg + "Select Time Hour\n";
        }

        if (cmbMm.getSelectedItem().equals("Select")) {
            msg = msg + "Select Time Minutes \n";
        }
        if (cmbPhase.getSelectedItem().equals("Select")) {
            msg = msg + "Select Phase\n";
        } else if (!cmbPhase.getSelectedItem().equals(Phase.FINAL.getName()) && !cmbPhase.getSelectedItem().equals(Phase.THIRDPLACE.getName())) {
            if (cmbSubPhase.getSelectedItem().equals("Select")) {
                msg = msg + "Select SubPhase\n";
            }
        }

        return msg;

    }

    private void team1comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_team1comboItemStateChanged
        // TODO add your handling code here:
        Object item = evt.getItem();
        team1 = "" + item;
    }//GEN-LAST:event_team1comboItemStateChanged

    private void team2comboItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_team2comboItemStateChanged
        // TODO add your handling code here:
        Object item = evt.getItem();
        team2 = "" + item;
    }//GEN-LAST:event_team2comboItemStateChanged

    private void txtMatchNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMatchNumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMatchNumActionPerformed

    private void cmbSubPhaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSubPhaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbSubPhaseActionPerformed

    private void cmbPhaseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPhaseItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {

            cmbSubPhase.removeAllItems();
            String item = (String) evt.getItem();

            if (item.equalsIgnoreCase(Phase.FINAL.getName()) || item.equalsIgnoreCase(Phase.THIRDPLACE.getName()) || item.equalsIgnoreCase("Select")) {
                cmbSubPhase.setVisible(false);
            } else {
                cmbSubPhase.setVisible(true);
                List<SubPhase> subPhases = SubPhase.getTypeByPhaseId(Phase.getIdByName(item).getId());
                cmbSubPhase.addItem("Select");
                for (SubPhase p : subPhases) {
                    cmbSubPhase.addItem(p.getName());
                }

            }
        }

    }//GEN-LAST:event_cmbPhaseItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbHH;
    private javax.swing.JComboBox<String> cmbMm;
    private javax.swing.JComboBox<String> cmbPhase;
    private javax.swing.JComboBox<String> cmbSubPhase;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    public javax.swing.JComboBox team1combo;
    public javax.swing.JComboBox team2combo;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtDayNum;
    private javax.swing.JTextField txtMatchNum;
    // End of variables declaration//GEN-END:variables
}
