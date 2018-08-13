package com.vollyball.panels;

import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.controller.Controller;
import com.vollyball.dao.RallyDao;
import com.vollyball.enums.HomeOpponent;
import com.vollyball.enums.Skill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationRally extends javax.swing.JPanel {

    RallyDao rallyDao = new RallyDao();
    PanEvaluationRowDetail panEvaluationRowDetail;
    GridBagConstraints gbc;
    int i = 0;
    public PanCompListValue panCompListValue = new PanCompListValue();
    int k;
    LinkedHashMap<PanEvaluationRallyRowText, PanEvaluationRowDetail> panRallyRow = new LinkedHashMap<>();
    PanEvaluationRallyRowText currentPanRow;
    int teamEvaluateId;
    int opponentId;
    RallyEvaluation rallyEvaluation = new RallyEvaluation();
    String startTime, endTime;
    int rallyNum;

    /**
     * Creates new form PanEvaluationRallyRow
     */
    public PanEvaluationRally(int teamEvaluateId, int opponentId, int rallyNum) {
        initComponents();
        this.rallyNum = rallyNum;
        this.teamEvaluateId = teamEvaluateId;
        this.opponentId = opponentId;
        panRallyList.add(panCompListValue, BorderLayout.CENTER);
        panCompListValue.add(true);
        lblRallyNum.setText("" + rallyNum);
    }

    public PanEvaluationRally(RallyEvaluation rallyEvaluation) {
        initComponents();
//        this.rallyNum = rallyNum;
//        this.teamEvaluateId = teamEvaluateId;
//        this.opponentId = opponentId;
//        panRallyList.add(panCompListValue, BorderLayout.CENTER);
//        panCompListValue.add(true);
        lblRallyNum.setText("" + rallyEvaluation.getRallyNum());
        lblRallyStartTime.setText("" + rallyEvaluation.getStartTime());
        lblRallyEndTime.setText("" + rallyEvaluation.getEndTime());
        lblResult.setText(rallyEvaluation.getHomeScore() + " : " + rallyEvaluation.getOpponentScore());
        panRallyList.add(panCompListValue, BorderLayout.CENTER);
        panCompListValue.addRallies(rallyEvaluation.getRallyEvaluationSkillScore());
    }

    public void save() {
        // TODO add your handling code here:

        RallyEvaluation rallyInsert = new RallyEvaluation();
        rallyInsert.setRallyNum(rallyNum);
        rallyInsert.setHomeScore(Controller.panMatchSet.homeScore);
        rallyInsert.setOpponentScore(Controller.panMatchSet.opponentScore);
        rallyInsert.setStartTime(startTime);
        rallyInsert.setEndTime(endTime);
        rallyInsert.setMatchEvaluationId(Controller.panMatchSet.matchEvaluationId);
        rallyInsert.setRallyPositionMap(Controller.panMatchSet.rallyPositionMap);

        for (RallyEvaluationSkillScore rallyEvaluationSkillScore : rallyEvaluation.getRallyEvaluationSkillScore()) {
            try {

                RallyEvaluationSkillScore rs = rallyEvaluationSkillScore;
                rs.setSkill(rallyEvaluationSkillScore.getSkill());

                if (rallyEvaluationSkillScore.getSkill().equals(Skill.OP.getType())) {
                    Controller.panMatchSet.op++;
                    Controller.panMatchSet.lblOp.setText("" + Controller.panMatchSet.op);

                } else if (rallyEvaluationSkillScore.getSkill().equals(Skill.TF.getType())) {
                    Controller.panMatchSet.tf++;
                    Controller.panMatchSet.lblTf.setText("" + Controller.panMatchSet.tf);

                }
                rs.setChestNo(rallyEvaluationSkillScore.getChestNo());
                rs.setPlayerId(rallyEvaluationSkillScore.getPlayerId());
                rallyInsert.setOp(Controller.panMatchSet.op);
                rallyInsert.setTf(Controller.panMatchSet.tf);
                rs.setSkillId(rallyEvaluationSkillScore.getSkillId());
                rs.setOrderNum(i + 1);
                rs.setScore(rallyEvaluationSkillScore.getScore());

//                if (panRallyEvaluationRow.isDetailed) {
//                    rs.setIsDetailed(true);
//                    rs.setDetailsValues(panRallyEvaluationRow.getDetailsValues());
//                }
                rallyInsert.getRallyEvaluationSkillScore().add(rs);
            } catch (Exception ex) {
                Logger.getLogger(PanRallyLiveEvaluation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        RallyEvaluationSkillScore panRallyEvaluationRowStart = rallyEvaluation.getRallyEvaluationSkillScore().get(0);
        RallyEvaluationSkillScore panRallyEvaluationRowEnd = rallyEvaluation.getRallyEvaluationSkillScore().get(rallyEvaluation.getRallyEvaluationSkillScore().size() - 1);

        if (panRallyEvaluationRowStart.getSkill().equals(Skill.Service.getType())) {
            rallyInsert.setStartby(HomeOpponent.HOME.getId());
        } else {
            rallyInsert.setStartby(HomeOpponent.OPPONENT.getId());
        }

        if (Integer.parseInt(String.valueOf(panRallyEvaluationRowEnd.getScore())) == 5) {
            rallyInsert.setWonby(HomeOpponent.HOME.getId());
        } else {
            rallyInsert.setWonby(HomeOpponent.OPPONENT.getId());
        }
        int idInserted = rallyDao.insertRally(rallyInsert);
        if (idInserted != 0) {
            Controller.panMatchSet.next();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save Rally");
        }

    }

    public class PanCompListValue extends JPanel {

        private JPanel mainList;
        JScrollPane s;

        public PanCompListValue() {
            k = 0;
            setLayout(new BorderLayout());
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            JPanel p = new JPanel();
            p.setBackground(new Color(57, 74, 108));
            mainList.add(p, gbc);
            mainList.setBackground(new Color(57, 74, 108));
            s = new JScrollPane(mainList);
            s.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            s.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
            s.getVerticalScrollBar().setBackground(Color.red);
            s.setBackground(Color.WHITE);
            s.setBorder(null);
            s.setSize(110, 500);
            add(s, BorderLayout.CENTER);
        }

        public void add(boolean isFirst) {

            PanEvaluationRallyRowText panel = new PanEvaluationRallyRowText(PanEvaluationRally.this);
            currentPanRow = panel;
            panEvalDetail.removeAll();
            PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(PanEvaluationRally.this, isFirst);
            panEvalDetail.add(panEvaluationRowDetail, BorderLayout.CENTER);
            panEvalDetail.validate();
            panEvalDetail.repaint();
            GridBagConstraints gbcRow = new GridBagConstraints();
            gbcRow.gridwidth = GridBagConstraints.REMAINDER;
            gbcRow.weightx = 1;
            gbcRow.gridheight = 2;
            gbcRow.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbcRow, k);
            validate();
            repaint();
            JScrollBar vertical = s.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
            panRallyRow.put(panel, panEvaluationRowDetail);
            k++;

            for (Map.Entry<PanEvaluationRallyRowText, PanEvaluationRowDetail> entry : panRallyRow.entrySet()) {
                PanEvaluationRallyRowText jPanel = entry.getKey();
                if (jPanel != panel) {
                    jPanel.panShow.setBackground(new Color(57, 74, 108));
                    jPanel.txtPlayer.setForeground(Color.WHITE);
                    jPanel.txtSkill.setForeground(Color.WHITE);
                    jPanel.txtRate.setForeground(Color.WHITE);

                }
            }

        }

        public void addRallies(List<RallyEvaluationSkillScore> rallyEvaluationSkillScore) {
            int k = 0;

            for (RallyEvaluationSkillScore rallyEvaluationSkillScore1 : rallyEvaluationSkillScore) {
                PanEvaluationRallyRowText panel = new PanEvaluationRallyRowText(PanEvaluationRally.this);
                currentPanRow = panel;
                panEvalDetail.removeAll();
                PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(PanEvaluationRally.this, false);
                panEvaluationRowDetail.setValues(rallyEvaluationSkillScore1);
                panEvalDetail.add(panEvaluationRowDetail, BorderLayout.CENTER);
                panEvalDetail.validate();
                panEvalDetail.repaint();
                GridBagConstraints gbcRow = new GridBagConstraints();
                gbcRow.gridwidth = GridBagConstraints.REMAINDER;
                gbcRow.weightx = 1;
                gbcRow.gridheight = 2;
                gbcRow.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(panel, gbcRow, k);
                validate();
                repaint();
                JScrollBar vertical = s.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
                panRallyRow.put(panel, panEvaluationRowDetail);
                k++;

                for (Map.Entry<PanEvaluationRallyRowText, PanEvaluationRowDetail> entry : panRallyRow.entrySet()) {
                    PanEvaluationRallyRowText jPanel = entry.getKey();
                    if (jPanel != panel) {
                        jPanel.panShow.setBackground(new Color(57, 74, 108));
                        jPanel.txtPlayer.setForeground(Color.WHITE);
                        jPanel.txtSkill.setForeground(Color.WHITE);
                        jPanel.txtRate.setForeground(Color.WHITE);

                    }
                }

            }

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

        panRallyList = new javax.swing.JPanel();
        panEvalDetail = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel43 = new javax.swing.JPanel();
        rallyPos4 = new javax.swing.JTextField();
        rallyPos3 = new javax.swing.JTextField();
        rallyPos2 = new javax.swing.JTextField();
        rallyPos5 = new javax.swing.JTextField();
        rallyPos6 = new javax.swing.JTextField();
        rallyPos1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        lblRallyStartTime = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblRallyEndTime = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblRallyNum = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        panRallyList.setBackground(new java.awt.Color(57, 74, 108));
        panRallyList.setLayout(new java.awt.BorderLayout());

        panEvalDetail.setBackground(new java.awt.Color(255, 255, 255));
        panEvalDetail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        panEvalDetail.setLayout(new java.awt.BorderLayout());

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rallyPos4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallyPos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rallyPos4.setEnabled(false);

        rallyPos3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallyPos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rallyPos3.setEnabled(false);

        rallyPos2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallyPos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rallyPos2.setEnabled(false);

        rallyPos5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallyPos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rallyPos5.setEnabled(false);

        rallyPos6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallyPos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rallyPos6.setEnabled(false);

        rallyPos1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallyPos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        rallyPos1.setEnabled(false);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(rallyPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rallyPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(rallyPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rallyPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rallyPos1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                    .addComponent(rallyPos2, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rallyPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rallyPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rallyPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rallyPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rallyPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rallyPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Start time : ");

        lblRallyStartTime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyStartTime.setText("00:00:00");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("End time : ");

        lblRallyEndTime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyEndTime.setText("00:00:00");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Result : ");

        lblResult.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblResult.setText("00 : 00");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Rally : ");

        lblRallyNum.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyNum.setText("0");

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Replace Libero");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRallyNum, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblRallyEndTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblRallyStartTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRallyStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRallyNum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRallyEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panEvalDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panRallyList, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panRallyList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panEvalDetail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel43;
    public javax.swing.JLabel lblRallyEndTime;
    private javax.swing.JLabel lblRallyNum;
    public javax.swing.JLabel lblRallyStartTime;
    public javax.swing.JLabel lblResult;
    public javax.swing.JPanel panEvalDetail;
    public javax.swing.JPanel panRallyList;
    private javax.swing.JTextField rallyPos1;
    private javax.swing.JTextField rallyPos2;
    private javax.swing.JTextField rallyPos3;
    private javax.swing.JTextField rallyPos4;
    private javax.swing.JTextField rallyPos5;
    private javax.swing.JTextField rallyPos6;
    // End of variables declaration//GEN-END:variables

    public int getTeamEvaluateId() {
        return teamEvaluateId;
    }

    public void setTeamEvaluateId(int teamEvaluateId) {
        this.teamEvaluateId = teamEvaluateId;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

}
