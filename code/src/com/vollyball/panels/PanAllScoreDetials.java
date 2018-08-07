/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerSkillScore;
import com.vollyball.chart.BarChart;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.DialogPlayerScoreGraph;
import com.vollyball.enums.Skill;
import java.awt.BorderLayout;
import java.awt.Color;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author nishant.vibhute
 */
public class PanAllScoreDetials extends javax.swing.JPanel {

    ReportDao reportDao = new ReportDao();
    String playerName;
    int compId, playerId, teamId;
    String teamName;
    TeamDao td = new TeamDao();
    int matchId, matchesPlayed;
    String phase;
    String matchName;

    /**
     * Creates new form PanAllScoreDetials
     */
    public PanAllScoreDetials(int compId, int playerId, String playerName, int matchesPlayed, String teamName, int matchId, int teamId) {
        initComponents();
        this.playerName = playerName;
        this.compId = compId;
        this.playerId = playerId;
        this.teamName = teamName;
        lblPlayerName.setText(playerName);
        lblMatchesPlayed.setText("" + matchesPlayed);
        lblTeamName.setText(teamName);
        this.matchId = matchId;
        this.matchesPlayed = matchesPlayed;
        this.teamId = teamId;

        Player p = td.getPlayerById(playerId);
        lblChestNo.setText(p.getChestNo());
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        PlayerSkillScore pservice = reportDao.getPlayerSkillWiseScoreReport(compId, playerId, Skill.Service.getId(), matchId, teamId);
        PlayerSkillScore pAttack = reportDao.getPlayerSkillWiseScoreReport(compId, playerId, Skill.Attack.getId(), matchId, teamId);
        PlayerSkillScore pBlock = reportDao.getPlayerSkillWiseScoreReport(compId, playerId, Skill.Block.getId(), matchId, teamId);
        PlayerSkillScore pSet = reportDao.getPlayerSkillWiseScoreReport(compId, playerId, Skill.Set.getId(), matchId, teamId);
        PlayerSkillScore pReception = reportDao.getPlayerSkillWiseScoreReport(compId, playerId, Skill.Reception.getId(), matchId, teamId);
        PlayerSkillScore pDefence = reportDao.getPlayerSkillWiseScoreReport(compId, playerId, Skill.Defence.getId(), matchId, teamId);

        dataset.addValue(pservice.getOne(), "One", "Service");
        dataset.addValue(pAttack.getOne(), "One", "Attack");
        dataset.addValue(pBlock.getOne(), "One", "Block");
        dataset.addValue(pSet.getOne(), "One", "Set");
        dataset.addValue(pReception.getOne(), "One", "Reception");
        dataset.addValue(pDefence.getOne(), "One", "Defence");

        dataset.addValue(pservice.getTwo(), "Two", "Service");
        dataset.addValue(pAttack.getTwo(), "Two", "Attack");
        dataset.addValue(pBlock.getTwo(), "Two", "Block");
        dataset.addValue(pSet.getTwo(), "Two", "Set");
        dataset.addValue(pReception.getTwo(), "Two", "Reception");
        dataset.addValue(pDefence.getTwo(), "Two", "Defence");

        dataset.addValue(pservice.getThree(), "Three", "Service");
        dataset.addValue(pAttack.getThree(), "Three", "Attack");
        dataset.addValue(pBlock.getThree(), "Three", "Block");
        dataset.addValue(pSet.getThree(), "Three", "Set");
        dataset.addValue(pReception.getThree(), "Three", "Reception");
        dataset.addValue(pDefence.getThree(), "Three", "Defence");

        dataset.addValue(pservice.getFour(), "Four", "Service");
        dataset.addValue(pAttack.getFour(), "Four", "Attack");
        dataset.addValue(pBlock.getFour(), "Four", "Block");
        dataset.addValue(pSet.getFour(), "Four", "Set");
        dataset.addValue(pReception.getFour(), "Four", "Reception");
        dataset.addValue(pDefence.getFour(), "Four", "Defence");

        dataset.addValue(pservice.getFive(), "Five", "Service");
        dataset.addValue(pAttack.getFive(), "Five", "Attack");
        dataset.addValue(pBlock.getFive(), "Five", "Block");
        dataset.addValue(pSet.getFive(), "Five", "Set");
        dataset.addValue(pReception.getFive(), "Five", "Reception");
        dataset.addValue(pDefence.getFive(), "Five", "Defence");

        JFreeChart chartFreeService = BarChart.createChart(dataset, "", "Skill", true);

        BarRenderer r = (BarRenderer) chartFreeService.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.RED);
        r.setSeriesPaint(1, Color.YELLOW);
        r.setSeriesPaint(2, Color.ORANGE);
        r.setSeriesPaint(3, Color.BLUE);
        r.setSeriesPaint(4, Color.GREEN);
        ChartPanel CPService = new ChartPanel(chartFreeService);

        panChart.add(CPService, BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panChart = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblPlayerName = new javax.swing.JLabel();
        lblMatches = new javax.swing.JLabel();
        lblMatchesPlayed = new javax.swing.JLabel();
        panPrint = new javax.swing.JPanel();
        lblPrint = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lblTeamName = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        lblChestNo = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        lblMatch = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        lblPhase = new javax.swing.JLabel();
        panPrint1 = new javax.swing.JPanel();
        lblPrint1 = new javax.swing.JLabel();
        panPrint2 = new javax.swing.JPanel();
        lblPrint2 = new javax.swing.JLabel();

        panChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panChart.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        lblPlayerName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPlayerName.setForeground(new java.awt.Color(255, 255, 255));

        lblMatches.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatches.setForeground(new java.awt.Color(255, 255, 255));
        lblMatches.setText("Matches Played :");

        lblMatchesPlayed.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatchesPlayed.setForeground(new java.awt.Color(255, 255, 255));

        panPrint.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrint.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/printer.png"))); // NOI18N
        lblPrint.setText("Print");
        lblPrint.setToolTipText("Print This Page");
        lblPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrintMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPrintLayout = new javax.swing.GroupLayout(panPrint);
        panPrint.setLayout(panPrintLayout);
        panPrintLayout.setHorizontalGroup(
            panPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panPrintLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
        );
        panPrintLayout.setVerticalGroup(
            panPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("Player Name : ");

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("Team Name  : ");

        lblTeamName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTeamName.setForeground(new java.awt.Color(255, 255, 255));

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 255, 255));
        jLabel52.setText("Chest No      : ");

        lblChestNo.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblChestNo.setForeground(new java.awt.Color(255, 255, 255));

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Match               :");

        lblMatch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatch.setForeground(new java.awt.Color(255, 255, 255));

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Phase                :");

        lblPhase.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPhase.setForeground(new java.awt.Color(255, 255, 255));

        panPrint1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrint1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPrint1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/chart_curve.png"))); // NOI18N
        lblPrint1.setText("Skillwise Chart");
        lblPrint1.setToolTipText("");
        lblPrint1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrint1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPrint1Layout = new javax.swing.GroupLayout(panPrint1);
        panPrint1.setLayout(panPrint1Layout);
        panPrint1Layout.setHorizontalGroup(
            panPrint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );
        panPrint1Layout.setVerticalGroup(
            panPrint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        panPrint2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrint2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPrint2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/chart_curve.png"))); // NOI18N
        lblPrint2.setText("Skillwise Diagram");
        lblPrint2.setToolTipText("");
        lblPrint2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrint2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPrint2Layout = new javax.swing.GroupLayout(panPrint2);
        panPrint2.setLayout(panPrint2Layout);
        panPrint2Layout.setHorizontalGroup(
            panPrint2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
        );
        panPrint2Layout.setVerticalGroup(
            panPrint2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMatches, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblMatchesPlayed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblPhase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel52)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblChestNo, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel50)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(190, 190, 190)
                            .addComponent(panPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panPrint2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panPrint1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPlayerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panPrint2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblChestNo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblPhase, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel51, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                                    .addComponent(lblTeamName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblMatchesPlayed, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblMatches, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))))
                        .addGap(22, 22, 22))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panChart, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintMouseClicked
        // TODO add your handling code here:
        panPrint.setVisible(false);

//        printComponenet(this);
        panPrint.setVisible(true);
    }//GEN-LAST:event_lblPrintMouseClicked

    private void lblPrint1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrint1MouseClicked
        // TODO add your handling code here:
        DialogPlayerScoreGraph createDialogPanMatchWiseReport = new DialogPlayerScoreGraph();
        createDialogPanMatchWiseReport.init(compId, playerId, teamName, matchesPlayed, teamName, matchId, teamId);
        createDialogPanMatchWiseReport.show();
    }//GEN-LAST:event_lblPrint1MouseClicked

    private void lblPrint2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrint2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPrint2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblChestNo;
    private javax.swing.JLabel lblMatch;
    private javax.swing.JLabel lblMatches;
    private javax.swing.JLabel lblMatchesPlayed;
    private javax.swing.JLabel lblPhase;
    private javax.swing.JLabel lblPlayerName;
    private javax.swing.JLabel lblPrint;
    private javax.swing.JLabel lblPrint1;
    private javax.swing.JLabel lblPrint2;
    private javax.swing.JLabel lblTeamName;
    private javax.swing.JPanel panChart;
    private javax.swing.JPanel panPrint;
    private javax.swing.JPanel panPrint1;
    private javax.swing.JPanel panPrint2;
    // End of variables declaration//GEN-END:variables
}
