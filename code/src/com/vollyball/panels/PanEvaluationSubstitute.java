/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationSubstitute extends javax.swing.JPanel {

    public LinkedHashMap<Integer, Player> initialPositionMap;
    public LinkedHashMap<Integer, Player> substituePositionMap;
    LinkedHashMap<JLabel, JPanel> mapInPlayerLabel = new LinkedHashMap<JLabel, JPanel>();
    LinkedHashMap<JLabel, JPanel> mapOutPlayerLabel = new LinkedHashMap<JLabel, JPanel>();
    public List<JLabel> inPlayerLable = new ArrayList<>();
    public List<JLabel> outPlayerLable = new ArrayList<>();
    RallyDao rallyDao = new RallyDao();
    String su1ChestNo, su2ChestNo, su3ChestNo, su4ChestNo, su5ChestNo, su6ChestNo;
    int position;
    String chestNo;
    String outChestNo;
    MatchSet ms;

    /**
     * Creates new form PanEvaluationSubstitute
     */
    public PanEvaluationSubstitute(MatchSet ms) {
        initComponents();
        initialPositionMap = new LinkedHashMap<>();
        substituePositionMap = new LinkedHashMap<>();

        this.ms = ms;
        LinkedHashMap<Integer, Player> latestPositionMap = rallyDao.getLatestMatchSetRotationOrder(ms.getId());

        inPlayerLable.add(lblIn1);
        inPlayerLable.add(lblIn2);
        inPlayerLable.add(lblIn3);
        inPlayerLable.add(lblIn4);
        inPlayerLable.add(lblIn5);
        inPlayerLable.add(lblIn6);
        inPlayerLable.add(lblIn7);

        outPlayerLable.add(lblOut1);
        outPlayerLable.add(lblOut2);
        outPlayerLable.add(lblOut3);
        outPlayerLable.add(lblOut4);
        outPlayerLable.add(lblOut5);
        outPlayerLable.add(lblOut6);
        outPlayerLable.add(lblOut7);
        outPlayerLable.add(lblOut8);

        mapInPlayerLabel.put(lblIn1, panIn1);
        mapInPlayerLabel.put(lblIn2, panIn2);
        mapInPlayerLabel.put(lblIn3, panIn3);
        mapInPlayerLabel.put(lblIn4, panIn4);
        mapInPlayerLabel.put(lblIn5, panIn5);
        mapInPlayerLabel.put(lblIn6, panIn6);
        mapInPlayerLabel.put(lblIn7, panIn7);

        mapOutPlayerLabel.put(lblOut1, panOut1);
        mapOutPlayerLabel.put(lblOut2, panOut2);
        mapOutPlayerLabel.put(lblOut3, panOut3);
        mapOutPlayerLabel.put(lblOut4, panOut4);
        mapOutPlayerLabel.put(lblOut5, panOut5);
        mapOutPlayerLabel.put(lblOut6, panOut6);
        mapOutPlayerLabel.put(lblOut7, panOut7);
        mapOutPlayerLabel.put(lblOut8, panOut8);

        for (Map.Entry<JLabel, JPanel> entry : mapInPlayerLabel.entrySet()) {
            entry.getValue().setVisible(false);
        }

        for (Map.Entry<JLabel, JPanel> entry : mapOutPlayerLabel.entrySet()) {
            entry.getValue().setVisible(false);
        }

        for (SetRotationOrder s : ms.getRotationOrder()) {
            initialPositionMap.put(s.getPosition(), Controller.panMatchSet.playerMap.get(s.getPlayerId()));
        }
        substituePositionMap.putAll(initialPositionMap);
        for (SetSubstitution s : ms.getSetSubstitutions()) {
            String cNo = s.getSubstitutePlayerId() == 0 ? "" : Controller.panMatchSet.playerMap.get(s.getSubstitutePlayerId()).getChestNo();
            Player p = s.getSubstitutePlayerId() == 0 ? null : Controller.panMatchSet.playerMap.get(s.getSubstitutePlayerId());
            if (!cNo.equals("")) {
                substituePositionMap.put(s.getPosition(), p);
            }
            String p1 = s.getPoint1();
            String p2 = s.getPoint2();
            switch (s.getPosition()) {
                case 1:
                    su1ChestNo = cNo;

                    su1.setText(cNo);
                    pt11.setText(p1);
                    pt21.setText(p2);
                    break;
                case 2:
                    su2ChestNo = cNo;
                    su2.setText(cNo);
                    pt12.setText(p1);
                    pt22.setText(p2);
                    break;
                case 3:
                    su3ChestNo = cNo;
                    su3.setText(cNo);
                    pt13.setText(p1);
                    pt23.setText(p2);
                    break;
                case 4:
                    su4ChestNo = cNo;
                    su4.setText(cNo);
                    pt14.setText(p1);
                    pt24.setText(p2);
                    break;
                case 5:
                    su5ChestNo = cNo;
                    su5.setText(cNo);
                    pt15.setText(p1);
                    pt25.setText(p2);
                    break;
                case 6:
                    su6ChestNo = cNo;
                    su6.setText(cNo);
                    pt16.setText(p1);
                    pt26.setText(p2);
                    break;
            }
        }

        ro1.setText(initialPositionMap.get(1).getChestNo());
        ro2.setText(initialPositionMap.get(2).getChestNo());
        ro3.setText(initialPositionMap.get(3).getChestNo());
        ro4.setText(initialPositionMap.get(4).getChestNo());
        ro5.setText(initialPositionMap.get(5).getChestNo());
        ro6.setText(initialPositionMap.get(6).getChestNo());

        int in = 0, out = 0;

        for (Player player : Controller.panMatchSet.playerList) {
            boolean exist = false;
            for (Map.Entry<Integer, Player> entry : substituePositionMap.entrySet()) {

                if (entry.getKey() != 7) {
                    Player pl = entry.getValue();
//                    Player plrally = latestPositionMap.get(entry.getKey());
                    if (player.getId() == pl.getId()) {
                        exist = true;
                        break;
                    }
                }
            }

            if (!exist) {
                if (player.getChestNo().equals(su1ChestNo)
                        || player.getChestNo().equals(su2ChestNo)
                        || player.getChestNo().equals(su3ChestNo)
                        || player.getChestNo().equals(su4ChestNo)
                        || player.getChestNo().equals(su5ChestNo)
                        || player.getChestNo().equals(su6ChestNo)) {
                    exist = true;
                }
            }
            if (!exist) {
                outPlayerLable.get(out).setText(player.getChestNo());
                mapOutPlayerLabel.get(outPlayerLable.get(out)).setVisible(true);

                out++;
            } else {
                inPlayerLable.get(in).setText(player.getChestNo());
                mapInPlayerLabel.get(inPlayerLable.get(in)).setVisible(true);
                in++;
            }
//

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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        panIn1 = new javax.swing.JPanel();
        lblIn1 = new javax.swing.JLabel();
        panIn2 = new javax.swing.JPanel();
        lblIn2 = new javax.swing.JLabel();
        panIn3 = new javax.swing.JPanel();
        lblIn3 = new javax.swing.JLabel();
        panIn4 = new javax.swing.JPanel();
        lblIn4 = new javax.swing.JLabel();
        panIn5 = new javax.swing.JPanel();
        lblIn5 = new javax.swing.JLabel();
        panIn6 = new javax.swing.JPanel();
        lblIn6 = new javax.swing.JLabel();
        panIn7 = new javax.swing.JPanel();
        lblIn7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panOut1 = new javax.swing.JPanel();
        lblOut1 = new javax.swing.JLabel();
        panOut2 = new javax.swing.JPanel();
        lblOut2 = new javax.swing.JLabel();
        panOut3 = new javax.swing.JPanel();
        lblOut3 = new javax.swing.JLabel();
        panOut4 = new javax.swing.JPanel();
        lblOut4 = new javax.swing.JLabel();
        panOut5 = new javax.swing.JPanel();
        lblOut5 = new javax.swing.JLabel();
        panOut6 = new javax.swing.JPanel();
        lblOut6 = new javax.swing.JLabel();
        panOut7 = new javax.swing.JPanel();
        lblOut7 = new javax.swing.JLabel();
        panOut8 = new javax.swing.JPanel();
        lblOut8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        pt11 = new javax.swing.JTextField();
        pt12 = new javax.swing.JTextField();
        pt13 = new javax.swing.JTextField();
        pt14 = new javax.swing.JTextField();
        pt15 = new javax.swing.JTextField();
        pt16 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        pt21 = new javax.swing.JTextField();
        pt22 = new javax.swing.JTextField();
        pt23 = new javax.swing.JTextField();
        pt24 = new javax.swing.JTextField();
        pt25 = new javax.swing.JTextField();
        pt26 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        su1 = new javax.swing.JTextField();
        su2 = new javax.swing.JTextField();
        su3 = new javax.swing.JTextField();
        su4 = new javax.swing.JTextField();
        su5 = new javax.swing.JTextField();
        su6 = new javax.swing.JTextField();
        ro1 = new javax.swing.JTextField();
        ro2 = new javax.swing.JTextField();
        ro5 = new javax.swing.JTextField();
        ro6 = new javax.swing.JTextField();
        ro3 = new javax.swing.JTextField();
        ro4 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        but1 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        but2 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        but3 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        but4 = new javax.swing.JPanel();
        but5 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        but6 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Substitute Player");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108)));

        panIn1.setBackground(new java.awt.Color(255, 255, 255));
        panIn1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIn1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIn1.setText("1");
        lblIn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIn1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panIn1Layout = new javax.swing.GroupLayout(panIn1);
        panIn1.setLayout(panIn1Layout);
        panIn1Layout.setHorizontalGroup(
            panIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panIn1Layout.setVerticalGroup(
            panIn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panIn2.setBackground(new java.awt.Color(255, 255, 255));
        panIn2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIn2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIn2.setText("1");
        lblIn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIn2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panIn2Layout = new javax.swing.GroupLayout(panIn2);
        panIn2.setLayout(panIn2Layout);
        panIn2Layout.setHorizontalGroup(
            panIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panIn2Layout.setVerticalGroup(
            panIn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panIn3.setBackground(new java.awt.Color(255, 255, 255));
        panIn3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIn3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIn3.setText("1");
        lblIn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIn3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panIn3Layout = new javax.swing.GroupLayout(panIn3);
        panIn3.setLayout(panIn3Layout);
        panIn3Layout.setHorizontalGroup(
            panIn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panIn3Layout.setVerticalGroup(
            panIn3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panIn4.setBackground(new java.awt.Color(255, 255, 255));
        panIn4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIn4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIn4.setText("1");
        lblIn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIn4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panIn4Layout = new javax.swing.GroupLayout(panIn4);
        panIn4.setLayout(panIn4Layout);
        panIn4Layout.setHorizontalGroup(
            panIn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panIn4Layout.setVerticalGroup(
            panIn4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panIn5.setBackground(new java.awt.Color(255, 255, 255));
        panIn5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIn5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIn5.setText("1");
        lblIn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIn5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panIn5Layout = new javax.swing.GroupLayout(panIn5);
        panIn5.setLayout(panIn5Layout);
        panIn5Layout.setHorizontalGroup(
            panIn5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panIn5Layout.setVerticalGroup(
            panIn5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panIn6.setBackground(new java.awt.Color(255, 255, 255));
        panIn6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIn6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIn6.setText("1");
        lblIn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIn6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panIn6Layout = new javax.swing.GroupLayout(panIn6);
        panIn6.setLayout(panIn6Layout);
        panIn6Layout.setHorizontalGroup(
            panIn6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panIn6Layout.setVerticalGroup(
            panIn6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panIn7.setBackground(new java.awt.Color(255, 255, 255));
        panIn7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIn7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIn7.setText("1");

        javax.swing.GroupLayout panIn7Layout = new javax.swing.GroupLayout(panIn7);
        panIn7.setLayout(panIn7Layout);
        panIn7Layout.setHorizontalGroup(
            panIn7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panIn7Layout.setVerticalGroup(
            panIn7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblIn7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panIn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panIn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panIn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panIn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panIn4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panIn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panIn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panIn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panIn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panIn3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panIn4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panIn5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panIn6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panIn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IN Players");

        panOut1.setBackground(new java.awt.Color(255, 255, 255));
        panOut1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut1.setText("1");
        lblOut1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut1Layout = new javax.swing.GroupLayout(panOut1);
        panOut1.setLayout(panOut1Layout);
        panOut1Layout.setHorizontalGroup(
            panOut1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut1Layout.setVerticalGroup(
            panOut1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panOut2.setBackground(new java.awt.Color(255, 255, 255));
        panOut2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut2.setText("1");
        lblOut2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut2Layout = new javax.swing.GroupLayout(panOut2);
        panOut2.setLayout(panOut2Layout);
        panOut2Layout.setHorizontalGroup(
            panOut2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut2Layout.setVerticalGroup(
            panOut2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut2, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panOut3.setBackground(new java.awt.Color(255, 255, 255));
        panOut3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut3.setText("1");
        lblOut3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut3Layout = new javax.swing.GroupLayout(panOut3);
        panOut3.setLayout(panOut3Layout);
        panOut3Layout.setHorizontalGroup(
            panOut3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut3Layout.setVerticalGroup(
            panOut3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panOut4.setBackground(new java.awt.Color(255, 255, 255));
        panOut4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut4.setText("1");
        lblOut4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut4Layout = new javax.swing.GroupLayout(panOut4);
        panOut4.setLayout(panOut4Layout);
        panOut4Layout.setHorizontalGroup(
            panOut4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut4Layout.setVerticalGroup(
            panOut4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut4, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panOut5.setBackground(new java.awt.Color(255, 255, 255));
        panOut5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut5.setText("1");
        lblOut5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut5Layout = new javax.swing.GroupLayout(panOut5);
        panOut5.setLayout(panOut5Layout);
        panOut5Layout.setHorizontalGroup(
            panOut5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut5Layout.setVerticalGroup(
            panOut5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panOut6.setBackground(new java.awt.Color(255, 255, 255));
        panOut6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut6.setText("1");
        lblOut6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut6Layout = new javax.swing.GroupLayout(panOut6);
        panOut6.setLayout(panOut6Layout);
        panOut6Layout.setHorizontalGroup(
            panOut6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut6Layout.setVerticalGroup(
            panOut6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut6, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panOut7.setBackground(new java.awt.Color(255, 255, 255));
        panOut7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut7.setText("1");
        lblOut7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut7Layout = new javax.swing.GroupLayout(panOut7);
        panOut7.setLayout(panOut7Layout);
        panOut7Layout.setHorizontalGroup(
            panOut7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut7Layout.setVerticalGroup(
            panOut7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut7, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panOut8.setBackground(new java.awt.Color(255, 255, 255));
        panOut8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblOut8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOut8.setText("1");
        lblOut8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOut8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOut8Layout = new javax.swing.GroupLayout(panOut8);
        panOut8.setLayout(panOut8Layout);
        panOut8Layout.setHorizontalGroup(
            panOut8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
        panOut8Layout.setVerticalGroup(
            panOut8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOut8, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panOut8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panOut7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panOut6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panOut5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panOut4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panOut3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panOut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panOut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panOut1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOut2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOut3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOut4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOut5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOut6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOut7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOut8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("OUT Players");

        jPanel7.setBackground(new java.awt.Color(57, 74, 108));
        jPanel7.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SAVE");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGap(51, 51, 51))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setText("RO");
        jTextField12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField12.setEnabled(false);
        jTextField12.setOpaque(false);

        jTextField22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setText("SU");
        jTextField22.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField22.setEnabled(false);
        jTextField22.setOpaque(false);

        jTextField29.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setText("PT");
        jTextField29.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField29.setEnabled(false);
        jTextField29.setOpaque(false);

        pt11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt11.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt11.setEnabled(false);
        pt11.setOpaque(false);

        pt12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt12.setEnabled(false);
        pt12.setOpaque(false);

        pt13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt13.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt13.setEnabled(false);
        pt13.setOpaque(false);

        pt14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt14.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt14.setEnabled(false);
        pt14.setOpaque(false);

        pt15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt15.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt15.setEnabled(false);
        pt15.setOpaque(false);

        pt16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt16.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt16.setEnabled(false);
        pt16.setOpaque(false);

        jTextField36.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField36.setText("PT");
        jTextField36.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField36.setEnabled(false);
        jTextField36.setOpaque(false);

        pt21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt21.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt21.setEnabled(false);
        pt21.setOpaque(false);

        pt22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt22.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt22.setEnabled(false);
        pt22.setOpaque(false);

        pt23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt23.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt23.setEnabled(false);
        pt23.setOpaque(false);

        pt24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt24.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt24.setEnabled(false);
        pt24.setOpaque(false);

        pt25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt25.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt25.setEnabled(false);
        pt25.setOpaque(false);

        pt26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt26.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt26.setEnabled(false);
        pt26.setOpaque(false);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        su1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su1.setEnabled(false);
        su1.setOpaque(false);

        su2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su2.setEnabled(false);
        su2.setOpaque(false);

        su3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su3.setEnabled(false);
        su3.setOpaque(false);

        su4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su4.setEnabled(false);
        su4.setOpaque(false);

        su5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su5.setEnabled(false);
        su5.setOpaque(false);

        su6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su6.setEnabled(false);
        su6.setOpaque(false);

        ro1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro1.setEnabled(false);
        ro1.setOpaque(false);

        ro2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro2.setEnabled(false);
        ro2.setOpaque(false);

        ro5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro5.setEnabled(false);
        ro5.setOpaque(false);

        ro6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro6.setEnabled(false);
        ro6.setOpaque(false);

        ro3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro3.setEnabled(false);
        ro3.setOpaque(false);

        ro4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro4.setEnabled(false);
        ro4.setOpaque(false);

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("SUBSTITUTE");
        jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jPanel16.setBackground(new java.awt.Color(57, 74, 108));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but1.setForeground(new java.awt.Color(255, 255, 255));
        but1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but1.setText("Select");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(57, 74, 108));
        jPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but2.setForeground(new java.awt.Color(255, 255, 255));
        but2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but2.setText("Select");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(57, 74, 108));
        jPanel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but3.setForeground(new java.awt.Color(255, 255, 255));
        but3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but3.setText("Select");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but3, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(57, 74, 108));
        jPanel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Select");

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        but4.setBackground(new java.awt.Color(57, 74, 108));
        but4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but5.setForeground(new java.awt.Color(255, 255, 255));
        but5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but5.setText("Select");

        javax.swing.GroupLayout but4Layout = new javax.swing.GroupLayout(but4);
        but4.setLayout(but4Layout);
        but4Layout.setHorizontalGroup(
            but4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        but4Layout.setVerticalGroup(
            but4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel33.setBackground(new java.awt.Color(57, 74, 108));
        jPanel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but6.setForeground(new java.awt.Color(255, 255, 255));
        but6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but6.setText("Select");

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but6, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(su1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(pt11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pt12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(su2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(pt13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(su3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(su4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ro4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(but4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ro5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(su5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(su6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ro6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(ro1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ro2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ro3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(but4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pt26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:

        MatchDao matchDao = new MatchDao();
        Player pIn = Controller.panMatchSet.ChestMap.get(chestNo);
        Player pOut = Controller.panMatchSet.ChestMap.get(outChestNo);
        String score = Controller.panMatchSet.homeScore + " : " + Controller.panMatchSet.opponentScore;
        int id = 0;
        if (pIn.equals(su1ChestNo)
                || pIn.equals(su2ChestNo)
                || pIn.equals(su3ChestNo)
                || pIn.equals(su4ChestNo)
                || pIn.equals(su5ChestNo)
                || pIn.equals(su6ChestNo)) {

            id = matchDao.updateSubstitutionPoint2(score, position, ms.getId());

        } else {
            id = matchDao.updateSubstitution(pOut.getId(), score, position, ms.getId());

        }

        if (id != 0) {
            for (Map.Entry<Integer, Player> entry : initialPositionMap.entrySet()) {
                Player player = entry.getValue();
                if (player.getChestNo().equals(pOut.getChestNo())) {
                    initialPositionMap.put(entry.getKey(), pOut);
                }
            }
        }

    }//GEN-LAST:event_jLabel3MouseClicked

    public void setInPlyerHighlight(JLabel lbl) {
        for (Map.Entry<JLabel, JPanel> entry : mapInPlayerLabel.entrySet()) {

            if (lbl == entry.getKey()) {
                mapInPlayerLabel.get(entry.getKey()).setBackground(Color.RED);

            } else {
                mapInPlayerLabel.get(entry.getKey()).setBackground(Color.WHITE);
            }

        }
    }

    public void setOutPlyerHighlight(JLabel lbl) {
        for (Map.Entry<JLabel, JPanel> entry : mapOutPlayerLabel.entrySet()) {

            if (lbl == entry.getKey()) {
                mapOutPlayerLabel.get(entry.getKey()).setBackground(Color.GREEN);

            } else {
                mapOutPlayerLabel.get(entry.getKey()).setBackground(Color.WHITE);
            }

        }
    }

    private void lblIn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIn2MouseClicked
        // TODO add your handling code here:
        position = 2;
        chestNo = lblIn2.getText();
        setInPlyerHighlight(lblIn2);
    }//GEN-LAST:event_lblIn2MouseClicked

    private void lblIn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIn3MouseClicked
        // TODO add your handling code here:
        position = 3;
        chestNo = lblIn3.getText();
        setInPlyerHighlight(lblIn3);
    }//GEN-LAST:event_lblIn3MouseClicked

    private void lblIn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIn4MouseClicked
        // TODO add your handling code here:
        position = 4;
        chestNo = lblIn4.getText();
        setInPlyerHighlight(lblIn4);
    }//GEN-LAST:event_lblIn4MouseClicked

    private void lblIn5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIn5MouseClicked
        // TODO add your handling code here:
        position = 5;
        chestNo = lblIn5.getText();
        setInPlyerHighlight(lblIn5);
    }//GEN-LAST:event_lblIn5MouseClicked

    private void lblIn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIn6MouseClicked
        // TODO add your handling code here:
        position = 6;
        chestNo = lblIn6.getText();
        setInPlyerHighlight(lblIn6);
    }//GEN-LAST:event_lblIn6MouseClicked

    private void lblOut1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut1MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut1.getText();
        setOutPlyerHighlight(lblOut1);
    }//GEN-LAST:event_lblOut1MouseClicked

    private void lblOut2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut2MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut2.getText();
        setOutPlyerHighlight(lblOut2);
    }//GEN-LAST:event_lblOut2MouseClicked

    private void lblOut3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut3MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut3.getText();
        setOutPlyerHighlight(lblOut3);
    }//GEN-LAST:event_lblOut3MouseClicked

    private void lblOut4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut4MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut4.getText();
        setOutPlyerHighlight(lblOut4);
    }//GEN-LAST:event_lblOut4MouseClicked

    private void lblOut5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut5MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut5.getText();
        setOutPlyerHighlight(lblOut5);
    }//GEN-LAST:event_lblOut5MouseClicked

    private void lblOut6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut6MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut6.getText();
        setOutPlyerHighlight(lblOut6);
    }//GEN-LAST:event_lblOut6MouseClicked

    private void lblOut7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut7MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut7.getText();
        setOutPlyerHighlight(lblOut7);
    }//GEN-LAST:event_lblOut7MouseClicked

    private void lblOut8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOut8MouseClicked
        // TODO add your handling code here:
        outChestNo = lblOut8.getText();
        setOutPlyerHighlight(lblOut8);
    }//GEN-LAST:event_lblOut8MouseClicked

    private void lblIn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIn1MouseClicked
        // TODO add your handling code here:
        position = 1;
        chestNo = lblIn1.getText();
        setInPlyerHighlight(lblIn1);
    }//GEN-LAST:event_lblIn1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel but1;
    private javax.swing.JLabel but2;
    private javax.swing.JLabel but3;
    private javax.swing.JPanel but4;
    private javax.swing.JLabel but5;
    private javax.swing.JLabel but6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JLabel lblIn1;
    private javax.swing.JLabel lblIn2;
    private javax.swing.JLabel lblIn3;
    private javax.swing.JLabel lblIn4;
    private javax.swing.JLabel lblIn5;
    private javax.swing.JLabel lblIn6;
    private javax.swing.JLabel lblIn7;
    private javax.swing.JLabel lblOut1;
    private javax.swing.JLabel lblOut2;
    private javax.swing.JLabel lblOut3;
    private javax.swing.JLabel lblOut4;
    private javax.swing.JLabel lblOut5;
    private javax.swing.JLabel lblOut6;
    private javax.swing.JLabel lblOut7;
    private javax.swing.JLabel lblOut8;
    private javax.swing.JPanel panIn1;
    private javax.swing.JPanel panIn2;
    private javax.swing.JPanel panIn3;
    private javax.swing.JPanel panIn4;
    private javax.swing.JPanel panIn5;
    private javax.swing.JPanel panIn6;
    private javax.swing.JPanel panIn7;
    private javax.swing.JPanel panOut1;
    private javax.swing.JPanel panOut2;
    private javax.swing.JPanel panOut3;
    private javax.swing.JPanel panOut4;
    private javax.swing.JPanel panOut5;
    private javax.swing.JPanel panOut6;
    private javax.swing.JPanel panOut7;
    private javax.swing.JPanel panOut8;
    public javax.swing.JTextField pt11;
    public javax.swing.JTextField pt12;
    public javax.swing.JTextField pt13;
    public javax.swing.JTextField pt14;
    public javax.swing.JTextField pt15;
    public javax.swing.JTextField pt16;
    private javax.swing.JTextField pt21;
    private javax.swing.JTextField pt22;
    private javax.swing.JTextField pt23;
    private javax.swing.JTextField pt24;
    private javax.swing.JTextField pt25;
    private javax.swing.JTextField pt26;
    private javax.swing.JTextField ro1;
    private javax.swing.JTextField ro2;
    private javax.swing.JTextField ro3;
    private javax.swing.JTextField ro4;
    private javax.swing.JTextField ro5;
    private javax.swing.JTextField ro6;
    public javax.swing.JTextField su1;
    public javax.swing.JTextField su2;
    public javax.swing.JTextField su3;
    public javax.swing.JTextField su4;
    public javax.swing.JTextField su5;
    public javax.swing.JTextField su6;
    // End of variables declaration//GEN-END:variables
}
