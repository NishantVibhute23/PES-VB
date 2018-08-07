/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.bean.VollyCourtCoordinateBean;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillsDescCriteria;
import com.vollyball.util.CommonUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class PanCourtAllScoresPlayer extends javax.swing.JPanel {

    RallyDao rd = new RallyDao();
    int playerId;
    Skill sk;
    TeamDao td = new TeamDao();
    int matchId;
    String selectedName;
    String phase;

    /**
     * Creates new form PanCourtAllScoresPlayer
     *
     * @param playerId
     * @param sk
     * @param playerName
     * @param teamName
     */
    public PanCourtAllScoresPlayer(int playerId, Skill sk, String playerName, String teamName, int matchId, String selectedName, String phase) {
        initComponents();
        this.playerId = playerId;
        this.sk = sk;
        this.matchId = matchId;
        this.selectedName = selectedName;
        this.phase = phase;
        Player p = td.getPlayerById(playerId);
        lblPlayerName.setText(p.getName());
        lblChestNo.setText(p.getChestNo());
        lblTeamName.setText(p.getTeamName());
        lblMatch.setText(selectedName);
        lblPhase.setText(phase);
        lblSkillName.setText(sk.getType());
        setCourt(0);
    }

    public void setCourt(int rating) {
        panGraph.removeAll();
        List<RallyEvaluationSkillScore> rallyDetailsList = null;

        rallyDetailsList = rd.getRallyDetailsOfPlayer(playerId, sk.getId(), rating, matchId);

        int home = 0, opp = 0;
        String type = null;
        List<VollyCourtCoordinateBean> listCCB = new ArrayList<>();
        lblAttempt.setText("" + rallyDetailsList.size());
        if (!rallyDetailsList.isEmpty()) {
            for (RallyEvaluationSkillScore ress : rallyDetailsList) {

                int skillId = ress.getSkillId();
                String chestNum = ress.getChestNo();
                LinkedHashMap<Integer, String> Dig = ress.getDetailsValues();
                if (!Dig.isEmpty()) {
                    if (skillId == Skill.Service.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.ServiceD.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.ServiceE.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Attack.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.AttackE.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.AttackF.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Set.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.SetF.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.SetG.getId()));
                        type = Skill.getNameById(skillId).getType() + "H";
                    }

                    if (skillId == Skill.Reception.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.ReceptionC.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.ReceptionD.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Defence.getId()) {
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.DefenceH.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.DefenceI.getId()));
                        type = Skill.getNameById(skillId).getType();
                    }

                    if (skillId == Skill.Block.getId()) {

                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.BlockF.getId()));
                        opp = Integer.parseInt(Dig.get(SkillsDescCriteria.BlockG.getId()));
                        type = Skill.getNameById(skillId).getType() + "Attack";
                        Color c = CommonUtil.getColorONScore("" + ress.getScore());
                        VollyCourtCoordinateBean v = rd.getCordinates(type, home, opp);
                        v.setColor(c);
                        listCCB.add(v);

                        String court = Dig.get(SkillsDescCriteria.BlockM.getId());
                        home = Integer.parseInt(Dig.get(SkillsDescCriteria.BlockG.getId()));
                        if (court.equalsIgnoreCase("H")) {
                            type = Skill.getNameById(skillId).getType() + "RH";

                        } else {
                            type = Skill.getNameById(skillId).getType() + "RO";
                        }

                        String oppH = Dig.get(SkillsDescCriteria.BlockH.getId());

                        switch (oppH) {
                            case "LOC":
                                opp = 7;
                                break;
                            case "ROC":
                                opp = 8;
                                break;
                            case "BOC":
                                opp = 9;
                                break;
                            default:
                                opp = Integer.parseInt(oppH);
                                break;
                        }
                        VollyCourtCoordinateBean v1 = rd.getCordinates(type, home, opp);
                        listCCB.add(v1);
                    }

                    if (skillId != Skill.Block.getId()) {
                        Color c = CommonUtil.getColorONScore("" + ress.getScore());
                        VollyCourtCoordinateBean v = rd.getCordinates(type, home, opp);
                        v.setColor(c);
                        listCCB.add(v);
                        v.setChestNum(chestNum);
                    }
                    PanVolleyCourt panMatch = new PanVolleyCourt();
                    panMatch.setValues(listCCB, "Report");
                    panGraph.add(panMatch, BorderLayout.CENTER);
                } else {
                    panGraph.add(lblNoData, BorderLayout.CENTER);
                }

            }
        } else {
            panGraph.add(lblNoData, BorderLayout.CENTER);
        }

        validate();

        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblPlayerName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblChestNo = new javax.swing.JLabel();
        lblTeam = new javax.swing.JLabel();
        lblTeamName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblMatch = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbScore = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblPhase = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblSkillName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblAttempt = new javax.swing.JLabel();
        panGraph = new javax.swing.JPanel();
        lblNoData = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Player Name :");

        lblPlayerName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPlayerName.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerName.setText(" ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Chest No.     :");

        lblChestNo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblChestNo.setForeground(new java.awt.Color(255, 255, 255));
        lblChestNo.setText(" ");

        lblTeam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblTeam.setText("Team Name  :");

        lblTeamName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTeamName.setForeground(new java.awt.Color(255, 255, 255));
        lblTeamName.setText(" ");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Match          :");

        lblMatch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatch.setForeground(new java.awt.Color(255, 255, 255));
        lblMatch.setText(" ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Score     : ");

        cmbScore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbScore.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - 5", "1", "2", "3", "4", "5" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("VIEW");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phase          :");

        lblPhase.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPhase.setForeground(new java.awt.Color(255, 255, 255));
        lblPhase.setText(" ");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Skill            :");

        lblSkillName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSkillName.setForeground(new java.awt.Color(255, 255, 255));
        lblSkillName.setText(" ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Attempt :");

        lblAttempt.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAttempt.setForeground(new java.awt.Color(255, 255, 255));
        lblAttempt.setText(" ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTeam)
                        .addGap(5, 5, 5)
                        .addComponent(lblTeamName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblChestNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAttempt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSkillName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGap(34, 34, 34))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(lblPlayerName)
                                .addComponent(jLabel11)
                                .addComponent(lblSkillName)
                                .addComponent(cmbScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblChestNo))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTeam)
                            .addComponent(lblTeamName))
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblMatch)
                            .addComponent(jLabel2)
                            .addComponent(lblAttempt))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(lblPhase))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        panGraph.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panGraph.setLayout(new java.awt.BorderLayout());

        lblNoData.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblNoData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNoData.setText("No Data Available");
        panGraph.add(lblNoData, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panGraph, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        String item = "" + cmbScore.getSelectedItem();
        int rating = 0;

        if (!item.equalsIgnoreCase("1 - 5")) {
            rating = Integer.parseInt(item);
        }
        setCourt(rating);

    }//GEN-LAST:event_jLabel10MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbScore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAttempt;
    private javax.swing.JLabel lblChestNo;
    private javax.swing.JLabel lblMatch;
    private javax.swing.JLabel lblNoData;
    private javax.swing.JLabel lblPhase;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblSkillName;
    private javax.swing.JLabel lblTeam;
    private javax.swing.JLabel lblTeamName;
    private javax.swing.JPanel panGraph;
    // End of variables declaration//GEN-END:variables
}
