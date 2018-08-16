/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import com.vollyball.dao.RallyDao;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTextField;

/**
 *
 * @author Supriya
 */
public class PanEvaluationReplaceLibero extends javax.swing.JPanel {

    List<JTextField> rallyPos = new ArrayList<>();
    PanEvaluationRally panEvaluationRally;
    RallyDao rallyDao = new RallyDao();

    /**
     * Creates new form PanEvaluationReplaceLibero
     */
    public PanEvaluationReplaceLibero(PanEvaluationRally panEvaluationRally) {

        initComponents();
        this.panEvaluationRally = panEvaluationRally;

        rallyPos.add(rallyPos1);
        rallyPos.add(rallyPos2);
        rallyPos.add(rallyPos3);
        rallyPos.add(rallyPos4);
        rallyPos.add(rallyPos5);
        rallyPos.add(rallyPos6);

        for (JTextField t : rallyPos) {

            t.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {

                    replace(me);
                }

                @Override
                public void mousePressed(MouseEvent me) {

                }

                @Override
                public void mouseReleased(MouseEvent me) {

                }

                @Override
                public void mouseEntered(MouseEvent me) {

                }

                @Override
                public void mouseExited(MouseEvent me) {

                }
            });

        }

        LinkedHashMap<Integer, Player> subStitutePositionMap = Controller.panMatchSet.substituePositionMap;
        LinkedHashMap<Integer, Player> rallyPositionMap = Controller.panMatchSet.rallyPositionMap;
        int i = 0;

        Player playerP = null;

        for (Map.Entry<Integer, Player> entrySub : subStitutePositionMap.entrySet()) {
            boolean found = false;
            Integer integer = entrySub.getKey();
            Player player = entrySub.getValue();
            if (integer != 7) {
                for (Map.Entry<Integer, Player> entryRally : rallyPositionMap.entrySet()) {
                    if (player.getChestNo().equals(entryRally.getValue().getChestNo())) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    playerP = player;
                }
            }
            i++;
        }

        int j = 0;
        for (Map.Entry<Integer, Player> entryRally : rallyPositionMap.entrySet()) {
            rallyPos.get(j).setText(entryRally.getValue().getChestNo());
            if (Controller.panMatchSet.initialPositionMap.get(7).getChestNo().equals(entryRally.getValue().getChestNo())) {
                rallyPos.get(j).setForeground(Color.red);
            }
            j++;
        }

        if (playerP == null) {
            if (lblLibero.getText().equals("")) {
                lblLibero.setText(Controller.panMatchSet.initialPositionMap.get(7).getChestNo());
                lblLibero.setForeground(Color.red);
            }
        } else {
            lblLibero.setText("" + playerP.getChestNo());
            if (Controller.panMatchSet.initialPositionMap.get(7).getChestNo().equals("" + playerP.getChestNo())) {
                lblLibero.setForeground(Color.red);
            }
        }

    }

    public void replace(MouseEvent me) {
        JTextField t = (JTextField) me.getSource();

        if (t.equals(rallyPos1)) {
            Controller.panMatchSet.rallyPositionMap.put(1, Controller.panMatchSet.ChestMap.get(lblLibero.getText()));
        } else if (t.equals(rallyPos2)) {
            Controller.panMatchSet.rallyPositionMap.put(2, Controller.panMatchSet.ChestMap.get(lblLibero.getText()));
        } else if (t.equals(rallyPos3)) {
            Controller.panMatchSet.rallyPositionMap.put(3, Controller.panMatchSet.ChestMap.get(lblLibero.getText()));
        } else if (t.equals(rallyPos4)) {
            Controller.panMatchSet.rallyPositionMap.put(4, Controller.panMatchSet.ChestMap.get(lblLibero.getText()));
        } else if (t.equals(rallyPos5)) {
            Controller.panMatchSet.rallyPositionMap.put(5, Controller.panMatchSet.ChestMap.get(lblLibero.getText()));
        } else if (t.equals(rallyPos6)) {
            Controller.panMatchSet.rallyPositionMap.put(6, Controller.panMatchSet.ChestMap.get(lblLibero.getText()));
        }

//        rallyDao.updateLatestOrder(Controller.panMatchSet.rallyPositionMap, ms.getId());
        this.panEvaluationRally.setRotation();
        this.panEvaluationRally.dialogReplaceLibero.close();

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
        jPanel43 = new javax.swing.JPanel();
        rallyPos4 = new javax.swing.JTextField();
        rallyPos3 = new javax.swing.JTextField();
        rallyPos2 = new javax.swing.JTextField();
        rallyPos5 = new javax.swing.JTextField();
        rallyPos6 = new javax.swing.JTextField();
        rallyPos1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblLibero = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        rallyPos4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        rallyPos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos4.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rallyPos3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        rallyPos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos3.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rallyPos2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        rallyPos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos2.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rallyPos5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        rallyPos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos5.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rallyPos6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        rallyPos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos6.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        rallyPos1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        rallyPos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rallyPos1.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(rallyPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rallyPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel43Layout.createSequentialGroup()
                        .addComponent(rallyPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(rallyPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rallyPos1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(rallyPos2)))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rallyPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rallyPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rallyPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rallyPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rallyPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rallyPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Select Player To replace with Libero player");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Select Player");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        lblLibero.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblLibero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLibero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblLibero, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("LIBERO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(133, 133, 133)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(13, 13, 13)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JLabel lblLibero;
    private javax.swing.JTextField rallyPos1;
    private javax.swing.JTextField rallyPos2;
    private javax.swing.JTextField rallyPos3;
    private javax.swing.JTextField rallyPos4;
    private javax.swing.JTextField rallyPos5;
    private javax.swing.JTextField rallyPos6;
    // End of variables declaration//GEN-END:variables
}
