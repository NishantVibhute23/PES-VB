/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationSubstitute extends javax.swing.JPanel {

    public LinkedHashMap<Integer, Player> initialPositionMap;
    public LinkedHashMap<Integer, Player> substituePositionMap;
    LinkedHashMap<JLabel, JPanel> mapPlayerLabel = new LinkedHashMap<JLabel, JPanel>();

    public List<JLabel> playerLable = new ArrayList<>();

    RallyDao rallyDao = new RallyDao();
    MatchDao matchDao = new MatchDao();
    String su1ChestNo, su2ChestNo, su3ChestNo, su4ChestNo, su5ChestNo, su6ChestNo;
    int position;
    String chestNo;
    String outChestNo;
    MatchSet ms;
    String focus = "out";
    LinkedHashMap<Integer, Player> latestPositionMap;

    /**
     * Creates new form PanEvaluationSubstitute
     */
    public PanEvaluationSubstitute(int setNum, int matchEvaluationTeamId) {
        initComponents();
        initialPositionMap = new LinkedHashMap<>();
        substituePositionMap = new LinkedHashMap<>();
        lblOut.requestFocus();

        this.ms = matchDao.getMatchSet(setNum, matchEvaluationTeamId);;
        latestPositionMap = rallyDao.getLatestMatchSetRotationOrder(ms.getId());

        playerLable.add(lbl1);
        playerLable.add(lbl2);
        playerLable.add(lbl3);
        playerLable.add(lbl4);
        playerLable.add(lbl5);
        playerLable.add(lbl6);
        playerLable.add(lbl7);
        playerLable.add(lbl8);
        playerLable.add(lbl9);
        playerLable.add(lbl10);
        playerLable.add(lbl11);
        playerLable.add(lbl12);
        playerLable.add(lbl13);
        playerLable.add(lbl14);
        playerLable.add(lbl15);

        mapPlayerLabel.put(lbl1, pan1);
        mapPlayerLabel.put(lbl2, pan2);
        mapPlayerLabel.put(lbl3, pan3);
        mapPlayerLabel.put(lbl4, pan4);
        mapPlayerLabel.put(lbl5, pan5);
        mapPlayerLabel.put(lbl6, pan6);
        mapPlayerLabel.put(lbl7, pan7);
        mapPlayerLabel.put(lbl8, pan8);
        mapPlayerLabel.put(lbl9, pan9);
        mapPlayerLabel.put(lbl10, pan10);
        mapPlayerLabel.put(lbl11, pan11);
        mapPlayerLabel.put(lbl12, pan12);
        mapPlayerLabel.put(lbl13, pan13);
        mapPlayerLabel.put(lbl14, pan14);
        mapPlayerLabel.put(lbl15, pan15);

        lblRallyNum.setText("" + Controller.panMatchSet.currentRally);

        RallyEvaluation re = rallyDao.getRally(Integer.parseInt(lblRallyNum.getText()), ms.getId(), 0);

        lblScore.setText(re.getHomeScore() + " : " + re.getOpponentScore());

        for (Map.Entry<JLabel, JPanel> entry : mapPlayerLabel.entrySet()) {
            entry.getKey().addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    selectPlayer(me);
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
                if (s.getPoint2() == null) {
                    substituePositionMap.put(s.getPosition(), p);
                }
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
            playerLable.get(out).setText(player.getChestNo());
            mapPlayerLabel.get(playerLable.get(out)).setVisible(true);
            out++;

        }
    }

    public void selectPlayer(MouseEvent me) {
        JLabel lbl = (JLabel) me.getSource();

        if (focus.equalsIgnoreCase("OUT")) {
            lblOut.setText(lbl.getText());
            lblIn.requestFocus();
        } else if (focus.equalsIgnoreCase("IN")) {
            lblIn.setText(lbl.getText());
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
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pan1 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        pan2 = new javax.swing.JPanel();
        lbl2 = new javax.swing.JLabel();
        pan3 = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        pan4 = new javax.swing.JPanel();
        lbl4 = new javax.swing.JLabel();
        pan5 = new javax.swing.JPanel();
        lbl5 = new javax.swing.JLabel();
        pan6 = new javax.swing.JPanel();
        lbl6 = new javax.swing.JLabel();
        pan7 = new javax.swing.JPanel();
        lbl7 = new javax.swing.JLabel();
        pan8 = new javax.swing.JPanel();
        lbl8 = new javax.swing.JLabel();
        pan9 = new javax.swing.JPanel();
        lbl9 = new javax.swing.JLabel();
        pan10 = new javax.swing.JPanel();
        lbl10 = new javax.swing.JLabel();
        pan11 = new javax.swing.JPanel();
        lbl11 = new javax.swing.JLabel();
        pan12 = new javax.swing.JPanel();
        lbl12 = new javax.swing.JLabel();
        pan13 = new javax.swing.JPanel();
        lbl13 = new javax.swing.JLabel();
        pan14 = new javax.swing.JPanel();
        lbl14 = new javax.swing.JLabel();
        pan15 = new javax.swing.JPanel();
        lbl15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblScore = new javax.swing.JTextField();
        lblRallyNum = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblOut = new javax.swing.JTextField();
        lblIn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        lblMsg = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();

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
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pan1.setBackground(new java.awt.Color(255, 255, 255));
        pan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText("1");

        javax.swing.GroupLayout pan1Layout = new javax.swing.GroupLayout(pan1);
        pan1.setLayout(pan1Layout);
        pan1Layout.setHorizontalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan1Layout.setVerticalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan2.setBackground(new java.awt.Color(255, 255, 255));
        pan2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setText("1");

        javax.swing.GroupLayout pan2Layout = new javax.swing.GroupLayout(pan2);
        pan2.setLayout(pan2Layout);
        pan2Layout.setHorizontalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan3.setBackground(new java.awt.Color(255, 255, 255));
        pan3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setText("1");

        javax.swing.GroupLayout pan3Layout = new javax.swing.GroupLayout(pan3);
        pan3.setLayout(pan3Layout);
        pan3Layout.setHorizontalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan3Layout.setVerticalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan4.setBackground(new java.awt.Color(255, 255, 255));
        pan4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setText("1");

        javax.swing.GroupLayout pan4Layout = new javax.swing.GroupLayout(pan4);
        pan4.setLayout(pan4Layout);
        pan4Layout.setHorizontalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan4Layout.setVerticalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan5.setBackground(new java.awt.Color(255, 255, 255));
        pan5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl5.setText("1");

        javax.swing.GroupLayout pan5Layout = new javax.swing.GroupLayout(pan5);
        pan5.setLayout(pan5Layout);
        pan5Layout.setHorizontalGroup(
            pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan5Layout.setVerticalGroup(
            pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan6.setBackground(new java.awt.Color(255, 255, 255));
        pan6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl6.setText("1");

        javax.swing.GroupLayout pan6Layout = new javax.swing.GroupLayout(pan6);
        pan6.setLayout(pan6Layout);
        pan6Layout.setHorizontalGroup(
            pan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan6Layout.setVerticalGroup(
            pan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan7.setBackground(new java.awt.Color(255, 255, 255));
        pan7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl7.setText("1");

        javax.swing.GroupLayout pan7Layout = new javax.swing.GroupLayout(pan7);
        pan7.setLayout(pan7Layout);
        pan7Layout.setHorizontalGroup(
            pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan7Layout.setVerticalGroup(
            pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan8.setBackground(new java.awt.Color(255, 255, 255));
        pan8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl8.setText("1");

        javax.swing.GroupLayout pan8Layout = new javax.swing.GroupLayout(pan8);
        pan8.setLayout(pan8Layout);
        pan8Layout.setHorizontalGroup(
            pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan8Layout.setVerticalGroup(
            pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan9.setBackground(new java.awt.Color(255, 255, 255));
        pan9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl9.setText("1");

        javax.swing.GroupLayout pan9Layout = new javax.swing.GroupLayout(pan9);
        pan9.setLayout(pan9Layout);
        pan9Layout.setHorizontalGroup(
            pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl9, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan9Layout.setVerticalGroup(
            pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl9, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan10.setBackground(new java.awt.Color(255, 255, 255));
        pan10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl10.setText("1");

        javax.swing.GroupLayout pan10Layout = new javax.swing.GroupLayout(pan10);
        pan10.setLayout(pan10Layout);
        pan10Layout.setHorizontalGroup(
            pan10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl10, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan10Layout.setVerticalGroup(
            pan10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl10, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan11.setBackground(new java.awt.Color(255, 255, 255));
        pan11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl11.setText("1");

        javax.swing.GroupLayout pan11Layout = new javax.swing.GroupLayout(pan11);
        pan11.setLayout(pan11Layout);
        pan11Layout.setHorizontalGroup(
            pan11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl11, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan11Layout.setVerticalGroup(
            pan11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl11, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan12.setBackground(new java.awt.Color(255, 255, 255));
        pan12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setText("1");

        javax.swing.GroupLayout pan12Layout = new javax.swing.GroupLayout(pan12);
        pan12.setLayout(pan12Layout);
        pan12Layout.setHorizontalGroup(
            pan12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl12, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan12Layout.setVerticalGroup(
            pan12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl12, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan13.setBackground(new java.awt.Color(255, 255, 255));
        pan13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl13.setText("1");

        javax.swing.GroupLayout pan13Layout = new javax.swing.GroupLayout(pan13);
        pan13.setLayout(pan13Layout);
        pan13Layout.setHorizontalGroup(
            pan13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl13, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan13Layout.setVerticalGroup(
            pan13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl13, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan14.setBackground(new java.awt.Color(255, 255, 255));
        pan14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl14.setText("1");

        javax.swing.GroupLayout pan14Layout = new javax.swing.GroupLayout(pan14);
        pan14.setLayout(pan14Layout);
        pan14Layout.setHorizontalGroup(
            pan14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan14Layout.setVerticalGroup(
            pan14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl14, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        pan15.setBackground(new java.awt.Color(255, 255, 255));
        pan15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbl15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl15.setText("1");

        javax.swing.GroupLayout pan15Layout = new javax.swing.GroupLayout(pan15);
        pan15.setLayout(pan15Layout);
        pan15Layout.setHorizontalGroup(
            pan15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl15, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        pan15Layout.setVerticalGroup(
            pan15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl15, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Score :");

        lblScore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblRallyNum.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lblRallyNumFocusLost(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Rally No :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRallyNum)
                    .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRallyNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("OUT");

        lblOut.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblOut.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblOut.setCaretColor(new java.awt.Color(255, 255, 255));
        lblOut.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblOut.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lblOutFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lblOutFocusLost(evt);
            }
        });

        lblIn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblIn.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblIn.setCaretColor(new java.awt.Color(255, 255, 255));
        lblIn.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblIn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lblInFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lblInFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("IN");

        lblMsg.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblMsg.setText("Select OUT Player");

        jPanel5.setBackground(new java.awt.Color(57, 74, 108));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("SAVE");
        jLabel6.setToolTipText("");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(lblOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(lblIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(jLabel5))))
                            .addComponent(lblMsg))
                        .addGap(257, 257, 257)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(304, 304, 304)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(lblIn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(lblMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblOutFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblOutFocusGained
        // TODO add your handling code here:
        focus = "out";
        lblMsg.setText("Select OUT Player");
        lblOut.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
    }//GEN-LAST:event_lblOutFocusGained

    private void lblInFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblInFocusGained
        // TODO add your handling code here:
        focus = "in";
        lblMsg.setText("Select IN Player");
        lblIn.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
    }//GEN-LAST:event_lblInFocusGained

    private void lblOutFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblOutFocusLost
        // TODO add your handling code here:
        lblOut.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }//GEN-LAST:event_lblOutFocusLost

    private void lblInFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblInFocusLost
        // TODO add your handling code here:
        lblIn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }//GEN-LAST:event_lblInFocusLost

    private void lblRallyNumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lblRallyNumFocusLost
        // TODO add your handling code here:
        RallyEvaluation re = rallyDao.getRally(Integer.parseInt(lblRallyNum.getText()), ms.getId(), 0);
        lblScore.setText(re.getHomeScore() + " : " + re.getOpponentScore());
    }//GEN-LAST:event_lblRallyNumFocusLost

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:

        Player pOut = Controller.panMatchSet.ChestMap.get(lblOut.getText());
        Player pIn = Controller.panMatchSet.ChestMap.get(lblIn.getText());

        int position = 0, i = 0;
        for (Map.Entry<Integer, Player> entry : substituePositionMap.entrySet()) {
            Integer key = entry.getKey();
            Player value = entry.getValue();
            position++;
            if (pOut.getChestNo().equals(value.getChestNo())) {
                break;
            }
            i++;
        }

        SetSubstitution s = ms.getSetSubstitutions().get(i);
        int id = 0;
        if (s.getPoint1() == null) {
            id = matchDao.updateSubstitution(pIn.getId(), lblScore.getText(), position, ms.getId(), Integer.parseInt(lblRallyNum.getText()));
        } else {
            id = matchDao.updateSubstitutionPoint2(lblScore.getText(), position, ms.getId(), Integer.parseInt(lblRallyNum.getText()));
        }

        if (id != 0) {
//            latestPositionMap
            for (Map.Entry<Integer, Player> entry : latestPositionMap.entrySet()) {
                Integer key = entry.getKey();
                Player value = entry.getValue();

                if (pOut.getChestNo().equals(value.getChestNo())) {
                    latestPositionMap.put(key, pIn);
                    break;
                }
            }

            rallyDao.updateLatestOrder(latestPositionMap, ms.getId());
            Controller.panMatchSet.rallyPositionMap.putAll(latestPositionMap);
            Controller.panMatchSet.dialogEvaluationSubstitute.close();

        }
    }//GEN-LAST:event_jLabel6MouseClicked

//    public void setInPlyerHighlight(JLabel lbl) {
    //        for (Map.Entry<JLabel, JPanel> entry : mapInPlayerLabel.entrySet()) {
    //
    //            if (lbl == entry.getKey()) {
    //                mapInPlayerLabel.get(entry.getKey()).setBackground(Color.RED);
    //
    //            } else {
    //                mapInPlayerLabel.get(entry.getKey()).setBackground(Color.WHITE);
    //            }
    //
    //        }
    //    }
    //
    //    public void setOutPlyerHighlight(JLabel lbl) {
    //        for (Map.Entry<JLabel, JPanel> entry : mapOutPlayerLabel.entrySet()) {
    //
    //            if (lbl == entry.getKey()) {
    //                mapOutPlayerLabel.get(entry.getKey()).setBackground(Color.GREEN);
    //
    //            } else {
    //                mapOutPlayerLabel.get(entry.getKey()).setBackground(Color.WHITE);
    //            }
    //
    //        }
    //    }
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JTextField lblIn;
    private javax.swing.JLabel lblMsg;
    private javax.swing.JTextField lblOut;
    private javax.swing.JTextField lblRallyNum;
    private javax.swing.JTextField lblScore;
    private javax.swing.JPanel pan1;
    private javax.swing.JPanel pan10;
    private javax.swing.JPanel pan11;
    private javax.swing.JPanel pan12;
    private javax.swing.JPanel pan13;
    private javax.swing.JPanel pan14;
    private javax.swing.JPanel pan15;
    private javax.swing.JPanel pan2;
    private javax.swing.JPanel pan3;
    private javax.swing.JPanel pan4;
    private javax.swing.JPanel pan5;
    private javax.swing.JPanel pan6;
    private javax.swing.JPanel pan7;
    private javax.swing.JPanel pan8;
    private javax.swing.JPanel pan9;
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
