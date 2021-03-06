/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Map;

/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationRallyRowText extends javax.swing.JPanel {

    PanEvaluationRally p;
    boolean isAddClicked = false;

    /**
     * Creates new form PanEvaluationRallyRowText
     */
    public PanEvaluationRallyRowText(PanEvaluationRally p) {
        initComponents();
//        txtPlayer.setText(text);
        this.p = p;
    }

    public void setValues() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panShow = new javax.swing.JPanel();
        txtPlayer = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSkill = new javax.swing.JLabel();
        txtRate = new javax.swing.JLabel();

        panShow.setBackground(new java.awt.Color(255, 255, 255));

        txtPlayer.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtPlayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtPlayer.setText("-");
        txtPlayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPlayerMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-plus-20.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        txtSkill.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtSkill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtSkill.setText("-");
        txtSkill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtSkillMouseClicked(evt);
            }
        });

        txtRate.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        txtRate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtRate.setText("-");
        txtRate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRateMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panShowLayout = new javax.swing.GroupLayout(panShow);
        panShow.setLayout(panShowLayout);
        panShowLayout.setHorizontalGroup(
            panShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panShowLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtSkill, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(txtPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(txtRate, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addComponent(jLabel2)
                .addGap(2, 2, 2))
        );
        panShowLayout.setVerticalGroup(
            panShowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtSkill, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(txtRate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPlayerMouseClicked
        // TODO add your handling code here:
        show();
    }//GEN-LAST:event_txtPlayerMouseClicked

    public void show() {
        PanEvaluationRowDetail panEvaluationRowDetail = p.panRallyRow.get(this);

        p.panEvalDetail.removeAll();

        p.panEvalDetail.add(panEvaluationRowDetail, BorderLayout.CENTER);
        p.panEvalDetail.validate();
        p.panEvalDetail.repaint();
        this.panShow.setBackground(Color.WHITE);
//        this.txtPlayer.setForeground(new Color(57, 74, 108));
        this.txtPlayer.setForeground(Color.BLACK);
        this.txtSkill.setForeground(Color.BLACK);
        this.txtRate.setForeground(Color.BLACK);
        panEvaluationRowDetail.setIsNew(false);
        panEvaluationRowDetail.setButtonText();
        p.currentPanRow = this;

        for (Map.Entry<PanEvaluationRallyRowText, PanEvaluationRowDetail> entry : p.panRallyRow.entrySet()) {
            PanEvaluationRallyRowText jPanel = entry.getKey();
            if (jPanel != this) {
                jPanel.panShow.setBackground(new Color(57, 74, 108));
                jPanel.txtPlayer.setForeground(Color.WHITE);
                jPanel.txtSkill.setForeground(Color.WHITE);
                jPanel.txtRate.setForeground(Color.WHITE);
            }
        }
    }

    private void txtSkillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtSkillMouseClicked
        // TODO add your handling code here:
        show();
    }//GEN-LAST:event_txtSkillMouseClicked

    private void txtRateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRateMouseClicked
        // TODO add your handling code here:
        show();
    }//GEN-LAST:event_txtRateMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        isAddClicked = true;
        p.addToPosition(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        isAddClicked = true;
        p.removePosition();
    }//GEN-LAST:event_jLabel2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public javax.swing.JPanel panShow;
    public javax.swing.JLabel txtPlayer;
    public javax.swing.JLabel txtRate;
    public javax.swing.JLabel txtSkill;
    // End of variables declaration//GEN-END:variables
}
