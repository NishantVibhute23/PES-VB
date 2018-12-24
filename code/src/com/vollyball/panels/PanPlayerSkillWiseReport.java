/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerReportBean;
import com.vollyball.controller.Controller;
import static com.vollyball.controller.Controller.panBestScorer;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.Skill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanPlayerSkillWiseReport extends javax.swing.JPanel {

    ReportDao reportDao = new ReportDao();
    TeamDao td = new TeamDao();
    DefaultTableModel model;
    CompetitionBean cb;

    List<JPanel> panMenuList = new ArrayList<>();
    LinkedHashMap<Integer, PlayerReportBean> playerId = new LinkedHashMap<Integer, PlayerReportBean>();
    String skillName;
    int skillId;
    PanTableSkillWiseReport panTableSkillWiseReport = new PanTableSkillWiseReport();
  

    /**
     * Creates new form PanPlayerSkillWiseReport
     */
    public PanPlayerSkillWiseReport(final CompetitionBean cb) {
        initComponents();
        this.cb = cb;

        panMenuList.add(panScorer);
        panMenuList.add(panServer);
        panMenuList.add(panAttacker);
        panMenuList.add(panBlocker);
        panMenuList.add(panSetter);
        panMenuList.add(panReceiver);
        panMenuList.add(panDefender);

        List<Player> playerList = td.getAlPlayers(cb.getId());
       Controller.panBestScorer = new PanBestScorer(cb, playerList);

        panSkillReports.add(Controller.panBestScorer , BorderLayout.CENTER);
        changeColor(panScorer);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        panServer = new javax.swing.JPanel();
        lblServer = new javax.swing.JLabel();
        panAttacker = new javax.swing.JPanel();
        lblAttacker = new javax.swing.JLabel();
        panBlocker = new javax.swing.JPanel();
        lblBlocker = new javax.swing.JLabel();
        panDefender = new javax.swing.JPanel();
        lblDefender = new javax.swing.JLabel();
        panReceiver = new javax.swing.JPanel();
        lblReceiver = new javax.swing.JLabel();
        panSetter = new javax.swing.JPanel();
        lblSetter = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panScorer = new javax.swing.JPanel();
        lblScorer = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panSkillReports = new javax.swing.JPanel();

        jPanel3.setVisible(false);
        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panServer.setBackground(new java.awt.Color(255, 255, 255));
        panServer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblServer.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServer.setText("BEST SERVER");
        lblServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblServerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panServerLayout = new javax.swing.GroupLayout(panServer);
        panServer.setLayout(panServerLayout);
        panServerLayout.setHorizontalGroup(
            panServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panServerLayout.setVerticalGroup(
            panServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblServer, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panAttacker.setBackground(new java.awt.Color(255, 255, 255));
        panAttacker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblAttacker.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblAttacker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttacker.setText("BEST ATTACKER");
        lblAttacker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAttackerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAttackerLayout = new javax.swing.GroupLayout(panAttacker);
        panAttacker.setLayout(panAttackerLayout);
        panAttackerLayout.setHorizontalGroup(
            panAttackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
        );
        panAttackerLayout.setVerticalGroup(
            panAttackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panBlocker.setBackground(new java.awt.Color(255, 255, 255));
        panBlocker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblBlocker.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblBlocker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlocker.setText("BEST BLOCKER");
        lblBlocker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBlockerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panBlockerLayout = new javax.swing.GroupLayout(panBlocker);
        panBlocker.setLayout(panBlockerLayout);
        panBlockerLayout.setHorizontalGroup(
            panBlockerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlocker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panBlockerLayout.setVerticalGroup(
            panBlockerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlocker, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panDefender.setBackground(new java.awt.Color(255, 255, 255));
        panDefender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDefender.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblDefender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDefender.setText("BEST DEFENDER");
        lblDefender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDefenderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDefenderLayout = new javax.swing.GroupLayout(panDefender);
        panDefender.setLayout(panDefenderLayout);
        panDefenderLayout.setHorizontalGroup(
            panDefenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panDefenderLayout.setVerticalGroup(
            panDefenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefender, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panReceiver.setBackground(new java.awt.Color(255, 255, 255));
        panReceiver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblReceiver.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblReceiver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReceiver.setText("BEST RECEIVER");
        lblReceiver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReceiverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panReceiverLayout = new javax.swing.GroupLayout(panReceiver);
        panReceiver.setLayout(panReceiverLayout);
        panReceiverLayout.setHorizontalGroup(
            panReceiverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panReceiverLayout.setVerticalGroup(
            panReceiverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panSetter.setBackground(new java.awt.Color(255, 255, 255));
        panSetter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSetter.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblSetter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetter.setText("BEST SETTER");
        lblSetter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSetterLayout = new javax.swing.GroupLayout(panSetter);
        panSetter.setLayout(panSetterLayout);
        panSetterLayout.setHorizontalGroup(
            panSetterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSetterLayout.setVerticalGroup(
            panSetterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetter, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(57, 74, 108));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("PLAYER");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panScorer.setBackground(new java.awt.Color(255, 255, 255));
        panScorer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblScorer.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblScorer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScorer.setText("SCORES");
        lblScorer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblScorerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panScorerLayout = new javax.swing.GroupLayout(panScorer);
        panScorer.setLayout(panScorerLayout);
        panScorerLayout.setHorizontalGroup(
            panScorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScorer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panScorerLayout.setVerticalGroup(
            panScorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScorer, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panBlocker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSetter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panDefender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panScorer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panScorer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panAttacker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panBlocker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDefender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));
        panSkillReports.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSkillReports, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblServerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Service.getId());
        panTableSkillWiseReport.lblReportHeading.setText(lblServer.getText());
        skillName = lblServer.getText();
        changeColor(panServer);
    }//GEN-LAST:event_lblServerMouseClicked

    private void lblAttackerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttackerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Attack.getId());
        panTableSkillWiseReport.lblReportHeading.setText(lblAttacker.getText());
        skillName = lblAttacker.getText();
        changeColor(panAttacker);
    }//GEN-LAST:event_lblAttackerMouseClicked

    private void lblBlockerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBlockerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Block.getId());
        panTableSkillWiseReport.lblReportHeading.setText(lblBlocker.getText());
        skillName = lblBlocker.getText();
        changeColor(panBlocker);
    }//GEN-LAST:event_lblBlockerMouseClicked

    private void lblDefenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDefenderMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Defence.getId());
        panTableSkillWiseReport.lblReportHeading.setText(lblDefender.getText());
        skillName = lblDefender.getText();
        changeColor(panDefender);
    }//GEN-LAST:event_lblDefenderMouseClicked

    private void lblReceiverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReceiverMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Reception.getId());
        panTableSkillWiseReport.lblReportHeading.setText(lblReceiver.getText());
        skillName = lblReceiver.getText();
        changeColor(panReceiver);
    }//GEN-LAST:event_lblReceiverMouseClicked

    private void lblSetterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetterMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Set.getId());
        panTableSkillWiseReport.lblReportHeading.setText(lblSetter.getText());
        skillName = lblSetter.getText();
        changeColor(panSetter);
    }//GEN-LAST:event_lblSetterMouseClicked

    private void lblScorerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScorerMouseClicked
        // TODO add your handling code here:

        changeColor(panScorer);
        panSkillReports.remove(panTableSkillWiseReport);
        panSkillReports.add(panBestScorer, BorderLayout.CENTER);
        this.validate();
        this.repaint();

    }//GEN-LAST:event_lblScorerMouseClicked

    public void changeColor(JPanel panIn) {
        for (JPanel pan : panMenuList) {
            if (pan == panIn) {
                pan.setBackground(new Color(204, 204, 204));
            } else {
                pan.setBackground(Color.WHITE);
            }
        }
    }

    public void setPlayerReport(int skillid) {
        panSkillReports.remove(panBestScorer);
        panSkillReports.add(panTableSkillWiseReport, BorderLayout.CENTER);
        this.validate();
        this.repaint();

        panTableSkillWiseReport.setPlayerReport(skillid, skillName, cb, playerId);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAttacker;
    private javax.swing.JLabel lblBlocker;
    private javax.swing.JLabel lblDefender;
    private javax.swing.JLabel lblReceiver;
    private javax.swing.JLabel lblScorer;
    private javax.swing.JLabel lblServer;
    private javax.swing.JLabel lblSetter;
    private javax.swing.JPanel panAttacker;
    private javax.swing.JPanel panBlocker;
    private javax.swing.JPanel panDefender;
    private javax.swing.JPanel panReceiver;
    private javax.swing.JPanel panScorer;
    private javax.swing.JPanel panServer;
    private javax.swing.JPanel panSetter;
    private javax.swing.JPanel panSkillReports;
    // End of variables declaration//GEN-END:variables
}
