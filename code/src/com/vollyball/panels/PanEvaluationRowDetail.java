package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.controller.Controller;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.Skill;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationRowDetail extends javax.swing.JPanel {

    LinkedHashMap<JLabel, JPanel> mapSkillComponent = new LinkedHashMap<JLabel, JPanel>();
    LinkedHashMap<JLabel, JPanel> mapPlayerComponent = new LinkedHashMap<JLabel, JPanel>();
    LinkedHashMap<JLabel, JPanel> mapScoreComponent = new LinkedHashMap<JLabel, JPanel>();
    LinkedHashMap<String, JLabel> mapSkillLabel = new LinkedHashMap<String, JLabel>();
    LinkedHashMap<String, JLabel> mapPlayerLabel = new LinkedHashMap<String, JLabel>();
    LinkedHashMap<Integer, JLabel> mapScoreLabel = new LinkedHashMap<Integer, JLabel>();
    private BufferedImage image;
    ImagePanel panel;

    TeamDao teamDao = new TeamDao();
    String skill = "";
    String chestNo = "";
    int score = 0;
    JTextField txtRallyRow = new JTextField();
    public LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<String, Player> ChestMap = new LinkedHashMap<String, Player>();

//    List<JLabel> playerLabelList = new ArrayList<>();
    List<Integer> pixel = new ArrayList<>();
    PanEvaluationRally p;
    boolean isNew = true;
    boolean isLast = true;
    boolean isSelected = false;
    boolean isFirst = true;
    SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
    String command = "";
    List<JLabel> playerLabelList;
    String fromPlace, toPlace;

    RallyEvaluationSkillScore rallyEvaluationSkillScore = new RallyEvaluationSkillScore();

    /**
     * Creates new form PanEvaluationRowDetail
     */
    public PanEvaluationRowDetail(final PanEvaluationRally p, boolean isFirst) {
        initComponents();
        this.p = p;
        this.isFirst = isFirst;
        mapSkillComponent.put(lblService, panService);
        mapSkillComponent.put(lblAttack, panAttack);
        mapSkillComponent.put(lblBlock, panBlock);
        mapSkillComponent.put(lblSet, panSet);
        mapSkillComponent.put(lblReception, panReception);
        mapSkillComponent.put(lblDefence, panDefence);
        mapSkillComponent.put(lblOp, panOp);
        mapSkillComponent.put(lblTF, panTF);
        mapSkillLabel.put(Skill.Service.getType(), lblService);
        mapSkillLabel.put(Skill.Attack.getType(), lblAttack);
        mapSkillLabel.put(Skill.Block.getType(), lblBlock);
        mapSkillLabel.put(Skill.Set.getType(), lblSet);
        mapSkillLabel.put(Skill.Reception.getType(), lblReception);
        mapSkillLabel.put(Skill.Defence.getType(), lblDefence);
        mapSkillLabel.put(Skill.OP.getType(), lblOp);
        mapSkillLabel.put(Skill.TF.getType(), lblTF);

        mapPlayerComponent.put(lbl1, pan1);
        mapPlayerComponent.put(lbl2, pan2);
        mapPlayerComponent.put(lbl3, pan3);
        mapPlayerComponent.put(lbl4, pan4);
        mapPlayerComponent.put(lbl5, pan5);
        mapPlayerComponent.put(lbl6, pan6);
        mapPlayerComponent.put(lbl7, pan7);
        mapPlayerComponent.put(lbl8, pan8);
        mapPlayerComponent.put(lbl9, pan9);
        mapPlayerComponent.put(lbl10, pan10);
        mapPlayerComponent.put(lbl11, pan11);
        mapPlayerComponent.put(lbl12, pan12);
        mapPlayerComponent.put(lbl13, pan13);
        mapPlayerComponent.put(lbl14, pan14);
        mapPlayerComponent.put(lbl15, pan15);

        mapScoreComponent.put(lblRate1, panRate1);
        mapScoreComponent.put(lblRate2, panRate2);
        mapScoreComponent.put(lblRate3, panRate3);
        mapScoreComponent.put(lblRate4, panRate4);
        mapScoreComponent.put(lblRate5, panRate5);
        mapScoreLabel.put(1, lblRate1);
        mapScoreLabel.put(2, lblRate2);
        mapScoreLabel.put(3, lblRate3);
        mapScoreLabel.put(4, lblRate4);
        mapScoreLabel.put(5, lblRate5);

        panel = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage());
        panCourt.add(panel, BorderLayout.CENTER);

        playerLabelList = new ArrayList<JLabel>(mapPlayerComponent.keySet());
        for (JLabel label : playerLabelList) {
            label.setVisible(false);

        }

        int i = 0;
        for (Player pl : Controller.panMatchSet.playerList) {
            mapPlayerLabel.put(pl.getChestNo(), playerLabelList.get(i));
            playerLabelList.get(i).setText(pl.getChestNo());
            playerLabelList.get(i).setVisible(true);
            i++;
        }
        panCourt.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                pixel.add(x);
                pixel.add(y);

                if (pixel.size() > 2) {
                    panel.drawImage(pixel.get(0), pixel.get(1), pixel.get(2), pixel.get(3));
                    panCourt.removeAll();
                    panCourt.add(panel, BorderLayout.CENTER);
                    panCourt.validate();
                    panCourt.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        for (Map.Entry<JLabel, JPanel> entry : mapPlayerComponent.entrySet()) {
            entry.getKey().addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    selectPlayer((JLabel) e.getSource());
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });
        }

        for (Map.Entry<JLabel, JPanel> entry : mapScoreComponent.entrySet()) {
            entry.getKey().addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    selectScore((JLabel) e.getSource());

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });
        }
    }

    public void save() {
        if (isFirst && isNew) {
            isFirst = false;
            Date time = new Date();
            p.startTime = formatterTime.format(time);
            p.lblRallyStartTime.setText(p.startTime);
        }

        if (skill.equalsIgnoreCase("op+") || skill.equalsIgnoreCase("tf-")) {

            if (skill.equalsIgnoreCase("tf-")) {
                mapScoreComponent.get(lblRate1).setBackground(new Color(255, 11, 0));
                score = 1;
            } else {
                mapScoreComponent.get(lblRate5).setBackground(new Color(255, 11, 0));
                score = 5;
            }

            rallyEvaluationSkillScore.setSkill(skill);
            rallyEvaluationSkillScore.setSkillId(Skill.getIdByName(skill).getId());
            rallyEvaluationSkillScore.setScore(score);
            rallyEvaluationSkillScore.setChestNo("0");
            rallyEvaluationSkillScore.setPlayerId(0);
            isSelected = true;

        } else if (!skill.equals("") && !chestNo.equals("") && score != 0) {
            rallyEvaluationSkillScore.setSkill(skill);
            rallyEvaluationSkillScore.setSkillId(Skill.getIdByName(skill).getId());
            rallyEvaluationSkillScore.setScore(score);
            rallyEvaluationSkillScore.setChestNo(chestNo);
            rallyEvaluationSkillScore.setPlayerId(Controller.panMatchSet.ChestMap.get(chestNo).getId());
            isSelected = true;
        }

        if (isSelected) {
            p.rallyEvaluation.getRallyEvaluationSkillScore().add(rallyEvaluationSkillScore);
            switch (score) {
                case 1:
                    Controller.panMatchSet.opponentScore++;
                    p.lblResult.setText(Controller.panMatchSet.homeScore + " : " + Controller.panMatchSet.opponentScore);
                    Date time = new Date();
                    p.endTime = formatterTime.format(time);
                    p.lblRallyEndTime.setText(p.endTime);
                    p.save();
                    break;
                case 5:
                    if (skill.equals(Skill.Service.getType()) || skill.equals(Skill.Attack.getType()) || skill.equals(Skill.Block.getType()) || skill.equals(Skill.OP.getType())) {
                        Controller.panMatchSet.homeScore++;
                        p.lblResult.setText(Controller.panMatchSet.homeScore + " : " + Controller.panMatchSet.opponentScore);
                        Date time1 = new Date();
                        p.endTime = formatterTime.format(time1);
                        p.lblRallyEndTime.setText(p.endTime);
                        p.save();
                    } else {
                        if (isNew || isLast) {
                            isLast = false;
                            p.panCompListValue.add(false);
                        }
                    }
                    break;
                default:
                    if (isNew || isLast) {
                        isLast = false;
                        p.panCompListValue.add(false);
                    }
                    break;
            }
        }
    }

    public void setValues(RallyEvaluationSkillScore rallyEvaluationSkillScore1) {
        skill = Skill.getNameById(rallyEvaluationSkillScore1.getSkillId()).getType();
        chestNo = rallyEvaluationSkillScore1.getPlayerId() == 0 ? "" : Controller.panMatchSet.playerMap.get(rallyEvaluationSkillScore1.getPlayerId()).getChestNo();
        p.currentPanRow.txtRate.setText("" + rallyEvaluationSkillScore1.getScore());
        p.currentPanRow.txtSkill.setText(skill);
        p.currentPanRow.txtPlayer.setText(chestNo);
        score = rallyEvaluationSkillScore1.getScore();
        JLabel lblScore = mapScoreLabel.get(score);
        if (lblScore == lblRate1) {
            mapScoreComponent.get(lblScore).setBackground(new Color(255, 11, 0));
        } else if (lblScore == lblRate2) {
            mapScoreComponent.get(lblScore).setBackground(new Color(255, 144, 0));
        } else if (lblScore == lblRate3) {
            mapScoreComponent.get(lblScore).setBackground(new Color(255, 255, 0));
        } else if (lblScore == lblRate4) {
            mapScoreComponent.get(lblScore).setBackground(new Color(157, 242, 0));
        } else if (lblScore == lblRate5) {
            mapScoreComponent.get(lblScore).setBackground(new Color(3, 129, 0));
        }

        mapSkillComponent.get(mapSkillLabel.get(skill)).setBackground(Color.ORANGE);
        if (!chestNo.equals("")) {
            mapPlayerComponent.get(mapPlayerLabel.get(chestNo)).setBackground(Color.ORANGE);
        }
    }

    public void dig(String txt1, String txt2) {
        panel.dig(txt1, txt2);
    }

    public void selectScore(JLabel j) {
        JLabel lblScore = j;
        p.currentPanRow.txtRate.setText(lblScore.getText());
        score = Integer.parseInt(lblScore.getText());
        for (Map.Entry<JLabel, JPanel> entry : mapScoreComponent.entrySet()) {

            if (lblScore == entry.getKey()) {
                if (lblScore == lblRate1) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(255, 11, 0));
                } else if (lblScore == lblRate2) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(255, 144, 0));
                } else if (lblScore == lblRate3) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(255, 255, 0));
                } else if (lblScore == lblRate4) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(157, 242, 0));
                } else if (lblScore == lblRate5) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(3, 129, 0));
                }

            } else {
                mapScoreComponent.get(entry.getKey()).setBackground(Color.WHITE);
            }

        }

        save();

    }

    public void selectSkill(JLabel j) {
        JLabel lblSkill = j;
        p.currentPanRow.txtSkill.setText(lblSkill.getText());
        skill = lblSkill.getText();
        for (Map.Entry<JLabel, JPanel> entry : mapSkillComponent.entrySet()) {

            if (lblSkill == entry.getKey()) {
                mapSkillComponent.get(entry.getKey()).setBackground(Color.ORANGE);

            } else {
                mapSkillComponent.get(entry.getKey()).setBackground(Color.WHITE);
            }

        }
        save();
    }

    public void selectPlayer(JLabel j) {
        JLabel txtPlayer = j;
        p.currentPanRow.txtPlayer.setText(txtPlayer.getText());
        chestNo = txtPlayer.getText();
        for (Map.Entry<JLabel, JPanel> entry : mapPlayerComponent.entrySet()) {

            if (txtPlayer == entry.getKey()) {
                mapPlayerComponent.get(entry.getKey()).setBackground(Color.ORANGE);
            } else {
                mapPlayerComponent.get(entry.getKey()).setBackground(Color.WHITE);
            }

        }
        save();
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
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
        panCourt = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        panRate1 = new javax.swing.JPanel();
        lblRate1 = new javax.swing.JLabel();
        panRate2 = new javax.swing.JPanel();
        lblRate2 = new javax.swing.JLabel();
        panRate3 = new javax.swing.JPanel();
        lblRate3 = new javax.swing.JLabel();
        panRate4 = new javax.swing.JPanel();
        lblRate4 = new javax.swing.JLabel();
        panRate5 = new javax.swing.JPanel();
        lblRate5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        panDefence = new javax.swing.JPanel();
        lblDefence = new javax.swing.JLabel();
        panService = new javax.swing.JPanel();
        lblService = new javax.swing.JLabel();
        panTF = new javax.swing.JPanel();
        lblTF = new javax.swing.JLabel();
        panOp = new javax.swing.JPanel();
        lblOp = new javax.swing.JLabel();
        panAttack = new javax.swing.JPanel();
        lblAttack = new javax.swing.JLabel();
        panBlock = new javax.swing.JPanel();
        lblBlock = new javax.swing.JLabel();
        panReception = new javax.swing.JPanel();
        lblReception = new javax.swing.JLabel();
        panSet = new javax.swing.JPanel();
        lblSet = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
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
        pan9 = new javax.swing.JPanel();
        lbl9 = new javax.swing.JLabel();
        pan8 = new javax.swing.JPanel();
        lbl8 = new javax.swing.JLabel();
        pan7 = new javax.swing.JPanel();
        lbl7 = new javax.swing.JLabel();
        pan12 = new javax.swing.JPanel();
        lbl12 = new javax.swing.JLabel();
        pan11 = new javax.swing.JPanel();
        lbl11 = new javax.swing.JLabel();
        pan10 = new javax.swing.JPanel();
        lbl10 = new javax.swing.JLabel();
        pan15 = new javax.swing.JPanel();
        lbl15 = new javax.swing.JLabel();
        pan14 = new javax.swing.JPanel();
        lbl14 = new javax.swing.JLabel();
        pan13 = new javax.swing.JPanel();
        lbl13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        txtDig = new javax.swing.JTextField();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        panCourt.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        panCourt.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panRate1.setBackground(new java.awt.Color(255, 255, 255));
        panRate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate1.setText("1");
        lblRate1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate1Layout = new javax.swing.GroupLayout(panRate1);
        panRate1.setLayout(panRate1Layout);
        panRate1Layout.setHorizontalGroup(
            panRate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate1Layout.setVerticalGroup(
            panRate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate2.setBackground(new java.awt.Color(255, 255, 255));
        panRate2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate2.setText("2");
        lblRate2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate2Layout = new javax.swing.GroupLayout(panRate2);
        panRate2.setLayout(panRate2Layout);
        panRate2Layout.setHorizontalGroup(
            panRate2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate2Layout.setVerticalGroup(
            panRate2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate3.setBackground(new java.awt.Color(255, 255, 255));
        panRate3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate3.setText("3");
        lblRate3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate3Layout = new javax.swing.GroupLayout(panRate3);
        panRate3.setLayout(panRate3Layout);
        panRate3Layout.setHorizontalGroup(
            panRate3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate3Layout.setVerticalGroup(
            panRate3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate4.setBackground(new java.awt.Color(255, 255, 255));
        panRate4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate4.setText("4");
        lblRate4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate4Layout = new javax.swing.GroupLayout(panRate4);
        panRate4.setLayout(panRate4Layout);
        panRate4Layout.setHorizontalGroup(
            panRate4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate4Layout.setVerticalGroup(
            panRate4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate5.setBackground(new java.awt.Color(255, 255, 255));
        panRate5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate5.setText("5");
        lblRate5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate5Layout = new javax.swing.GroupLayout(panRate5);
        panRate5.setLayout(panRate5Layout);
        panRate5Layout.setHorizontalGroup(
            panRate5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate5Layout.setVerticalGroup(
            panRate5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(57, 74, 108));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RATE");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panRate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panDefence.setBackground(new java.awt.Color(255, 255, 255));
        panDefence.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDefence.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblDefence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDefence.setText("Defence");
        lblDefence.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDefence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDefenceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDefenceLayout = new javax.swing.GroupLayout(panDefence);
        panDefence.setLayout(panDefenceLayout);
        panDefenceLayout.setHorizontalGroup(
            panDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panDefenceLayout.setVerticalGroup(
            panDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefence, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        panService.setBackground(new java.awt.Color(255, 255, 255));
        panService.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblService.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblService.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblService.setText("Service");
        lblService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblServiceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panServiceLayout = new javax.swing.GroupLayout(panService);
        panService.setLayout(panServiceLayout);
        panServiceLayout.setHorizontalGroup(
            panServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panServiceLayout.setVerticalGroup(
            panServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblService, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        panTF.setBackground(new java.awt.Color(255, 255, 255));
        panTF.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTF.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTF.setText("TF-");
        lblTF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTFMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panTFLayout = new javax.swing.GroupLayout(panTF);
        panTF.setLayout(panTFLayout);
        panTFLayout.setHorizontalGroup(
            panTFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panTFLayout.setVerticalGroup(
            panTFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTF, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        panOp.setBackground(new java.awt.Color(255, 255, 255));
        panOp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblOp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOp.setText("OP+");
        lblOp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOpLayout = new javax.swing.GroupLayout(panOp);
        panOp.setLayout(panOpLayout);
        panOpLayout.setHorizontalGroup(
            panOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panOpLayout.setVerticalGroup(
            panOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOp, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        panAttack.setBackground(new java.awt.Color(255, 255, 255));
        panAttack.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAttack.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblAttack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttack.setText("Attack");
        lblAttack.setToolTipText("");
        lblAttack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAttack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAttackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAttackLayout = new javax.swing.GroupLayout(panAttack);
        panAttack.setLayout(panAttackLayout);
        panAttackLayout.setHorizontalGroup(
            panAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panAttackLayout.setVerticalGroup(
            panAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttack, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        panBlock.setBackground(new java.awt.Color(255, 255, 255));
        panBlock.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblBlock.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblBlock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlock.setText("Block");
        lblBlock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBlockMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panBlockLayout = new javax.swing.GroupLayout(panBlock);
        panBlock.setLayout(panBlockLayout);
        panBlockLayout.setHorizontalGroup(
            panBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panBlockLayout.setVerticalGroup(
            panBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlock, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        panReception.setBackground(new java.awt.Color(255, 255, 255));
        panReception.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblReception.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblReception.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReception.setText("Reception");
        lblReception.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblReception.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReceptionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panReceptionLayout = new javax.swing.GroupLayout(panReception);
        panReception.setLayout(panReceptionLayout);
        panReceptionLayout.setHorizontalGroup(
            panReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReception, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panReceptionLayout.setVerticalGroup(
            panReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReception, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        panSet.setBackground(new java.awt.Color(255, 255, 255));
        panSet.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSet.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSet.setText("Set");
        lblSet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSetLayout = new javax.swing.GroupLayout(panSet);
        panSet.setLayout(panSetLayout);
        panSetLayout.setHorizontalGroup(
            panSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSetLayout.setVerticalGroup(
            panSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSet, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(57, 74, 108));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("SKILL");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panReception, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panDefence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(57, 74, 108));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("PLAYERS");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        pan1.setBackground(new java.awt.Color(255, 255, 255));

        lbl1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText(" ");
        lbl1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan1Layout = new javax.swing.GroupLayout(pan1);
        pan1.setLayout(pan1Layout);
        pan1Layout.setHorizontalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan1Layout.setVerticalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan2.setBackground(new java.awt.Color(255, 255, 255));

        lbl2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan2Layout = new javax.swing.GroupLayout(pan2);
        pan2.setLayout(pan2Layout);
        pan2Layout.setHorizontalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pan3.setBackground(new java.awt.Color(255, 255, 255));

        lbl3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan3Layout = new javax.swing.GroupLayout(pan3);
        pan3.setLayout(pan3Layout);
        pan3Layout.setHorizontalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan3Layout.setVerticalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan4.setBackground(new java.awt.Color(255, 255, 255));

        lbl4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan4Layout = new javax.swing.GroupLayout(pan4);
        pan4.setLayout(pan4Layout);
        pan4Layout.setHorizontalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan4Layout.setVerticalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan5.setBackground(new java.awt.Color(255, 255, 255));

        lbl5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan5Layout = new javax.swing.GroupLayout(pan5);
        pan5.setLayout(pan5Layout);
        pan5Layout.setHorizontalGroup(
            pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan5Layout.setVerticalGroup(
            pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan6.setBackground(new java.awt.Color(255, 255, 255));

        lbl6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan6Layout = new javax.swing.GroupLayout(pan6);
        pan6.setLayout(pan6Layout);
        pan6Layout.setHorizontalGroup(
            pan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan6Layout.setVerticalGroup(
            pan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan9.setBackground(new java.awt.Color(255, 255, 255));

        lbl9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan9Layout = new javax.swing.GroupLayout(pan9);
        pan9.setLayout(pan9Layout);
        pan9Layout.setHorizontalGroup(
            pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan9Layout.setVerticalGroup(
            pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan8.setBackground(new java.awt.Color(255, 255, 255));

        lbl8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan8Layout = new javax.swing.GroupLayout(pan8);
        pan8.setLayout(pan8Layout);
        pan8Layout.setHorizontalGroup(
            pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan8Layout.setVerticalGroup(
            pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan7.setBackground(new java.awt.Color(255, 255, 255));

        lbl7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan7Layout = new javax.swing.GroupLayout(pan7);
        pan7.setLayout(pan7Layout);
        pan7Layout.setHorizontalGroup(
            pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan7Layout.setVerticalGroup(
            pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan12.setBackground(new java.awt.Color(255, 255, 255));

        lbl12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan12Layout = new javax.swing.GroupLayout(pan12);
        pan12.setLayout(pan12Layout);
        pan12Layout.setHorizontalGroup(
            pan12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan12Layout.setVerticalGroup(
            pan12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan11.setBackground(new java.awt.Color(255, 255, 255));

        lbl11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan11Layout = new javax.swing.GroupLayout(pan11);
        pan11.setLayout(pan11Layout);
        pan11Layout.setHorizontalGroup(
            pan11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl11, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan11Layout.setVerticalGroup(
            pan11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl11, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan10.setBackground(new java.awt.Color(255, 255, 255));

        lbl10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan10Layout = new javax.swing.GroupLayout(pan10);
        pan10.setLayout(pan10Layout);
        pan10Layout.setHorizontalGroup(
            pan10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl10, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan10Layout.setVerticalGroup(
            pan10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl10, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan15.setBackground(new java.awt.Color(255, 255, 255));

        lbl15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan15Layout = new javax.swing.GroupLayout(pan15);
        pan15.setLayout(pan15Layout);
        pan15Layout.setHorizontalGroup(
            pan15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pan15Layout.setVerticalGroup(
            pan15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pan14.setBackground(new java.awt.Color(255, 255, 255));

        lbl14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan14Layout = new javax.swing.GroupLayout(pan14);
        pan14.setLayout(pan14Layout);
        pan14Layout.setHorizontalGroup(
            pan14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl14, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan14Layout.setVerticalGroup(
            pan14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pan13.setBackground(new java.awt.Color(255, 255, 255));

        lbl13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan13Layout = new javax.swing.GroupLayout(pan13);
        pan13.setLayout(pan13Layout);
        pan13Layout.setHorizontalGroup(
            pan13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl13, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan13Layout.setVerticalGroup(
            pan13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl13, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(pan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(pan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addComponent(pan13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addComponent(pan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addComponent(pan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 99, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(panCourt, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panCourt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        txtDig.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDigFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(txtDig, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
            .addComponent(txtDig)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblDefenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDefenceMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblDefenceMouseClicked

    private void lblServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblServiceMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblServiceMouseClicked

    private void lblTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTFMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblTFMouseClicked

    private void lblOpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblOpMouseClicked

    private void lblAttackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttackMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblAttackMouseClicked

    private void lblBlockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBlockMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblBlockMouseClicked

    private void lblReceptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReceptionMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblReceptionMouseClicked

    private void lblSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblSetMouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        switch (c) {
            case 's':
                selectSkill(lblService);
                command = "";
                break;
            case 'a':
                selectSkill(lblAttack);
                command = "";
                break;
            case 'b':
                selectSkill(lblBlock);
                command = "";
                break;
            case 'd':
                selectSkill(lblDefence);
                command = "";
                break;
            case 'r':
                selectSkill(lblReception);
                command = "";
                break;
            case 'e':
                selectSkill(lblSet);
                command = "";
                break;
            case '-':
                checkPrev(command);
                selectScore(lblRate1);
                command = "";
                break;
            case '/':
                checkPrev(command);
                selectScore(lblRate2);
                command = "";
                break;
            case '.':
                checkPrev(command);
                selectScore(lblRate3);
                command = "";
                break;
            case '*':
                checkPrev(command);
                selectScore(lblRate4);
                command = "";
                break;
            case '+':
                checkPrev(command);
                selectScore(lblRate5);
                command = "";
                break;
            case 'h':
                checkPrev(command);
                command = "";
                command = command + "" + c;
                break;
            case 'o':
                checkPrev(command);
                command = "";
                command = command + "" + c;
                break;
            default:
                command = command + "" + c;
                break;
        }

    }//GEN-LAST:event_jTextField1KeyPressed

    private void txtDigFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDigFocusLost
        // TODO add your handling code here:
        String val = txtDig.getText();

        String p[] = val.split("-");
        panel.dig(p[0], p[1]);

    }//GEN-LAST:event_txtDigFocusLost

    public void checkPrev(String command) {

        if (command.startsWith("h")) {
            fromPlace = command;
        } else if (command.startsWith("o")) {
            toPlace = command;
            panel.dig(fromPlace, toPlace);
        } else {
            int i = 0;
            for (Player pl : Controller.panMatchSet.playerList) {
                if (pl.getChestNo().equals(command)) {
                    selectPlayer(playerLabelList.get(i));
                }
                i++;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTextField1;
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
    private javax.swing.JLabel lblAttack;
    private javax.swing.JLabel lblBlock;
    private javax.swing.JLabel lblDefence;
    private javax.swing.JLabel lblOp;
    private javax.swing.JLabel lblRate1;
    private javax.swing.JLabel lblRate2;
    private javax.swing.JLabel lblRate3;
    private javax.swing.JLabel lblRate4;
    private javax.swing.JLabel lblRate5;
    private javax.swing.JLabel lblReception;
    private javax.swing.JLabel lblService;
    private javax.swing.JLabel lblSet;
    private javax.swing.JLabel lblTF;
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
    private javax.swing.JPanel panAttack;
    private javax.swing.JPanel panBlock;
    private javax.swing.JPanel panCourt;
    private javax.swing.JPanel panDefence;
    private javax.swing.JPanel panOp;
    private javax.swing.JPanel panRate1;
    private javax.swing.JPanel panRate2;
    private javax.swing.JPanel panRate3;
    private javax.swing.JPanel panRate4;
    private javax.swing.JPanel panRate5;
    private javax.swing.JPanel panReception;
    private javax.swing.JPanel panService;
    private javax.swing.JPanel panSet;
    private javax.swing.JPanel panTF;
    private javax.swing.JTextField txtDig;
    // End of variables declaration//GEN-END:variables

}

class ImagePanel extends JPanel {

    private Image img;
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    LinkedHashMap<String, JPanel> panGrid = new LinkedHashMap<String, JPanel>();

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());

    }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        this.setLayout(new GridLayout(8, 5));

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 5; j++) {
            }
        }

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 5; j++) {

                if (i == 1) {
                    String panCode = "OB";
                    if (j == 1) {
                        panCode = panCode + "1";
                    } else if (j == 2) {
                        panCode = panCode + "2";
                    } else if (j == 3) {
                        panCode = panCode + "3";
                    } else if (j == 4) {
                        panCode = panCode + "4";
                    } else if (j == 5) {
                        panCode = panCode + "5";
                    }
                    addPanel(panCode);
                }

                if (i == 2) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "L3";
                    } else if (j == 2) {
                        panCode = panCode + "7";
                    } else if (j == 3) {
                        panCode = panCode + "8";
                    } else if (j == 4) {
                        panCode = panCode + "9";
                    } else if (j == 5) {
                        panCode = panCode + "R3";
                    }
                    addPanel(panCode);
                }

                if (i == 3) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "L2";
                    } else if (j == 2) {
                        panCode = panCode + "4";
                    } else if (j == 3) {
                        panCode = panCode + "5";
                    } else if (j == 4) {
                        panCode = panCode + "6";
                    } else if (j == 5) {
                        panCode = panCode + "R2";
                    }
                    addPanel(panCode);
                }

                if (i == 4) {
                    String panCode = "O";
                    if (j == 1) {
                        panCode = panCode + "L1";
                    } else if (j == 2) {
                        panCode = panCode + "1";
                    } else if (j == 3) {
                        panCode = panCode + "2";
                    } else if (j == 4) {
                        panCode = panCode + "3";
                    } else if (j == 5) {
                        panCode = panCode + "R1";
                    }
                    addPanel(panCode);
                }

                if (i == 5) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L1";
                    } else if (j == 2) {
                        panCode = panCode + "1";
                    } else if (j == 3) {
                        panCode = panCode + "2";
                    } else if (j == 4) {
                        panCode = panCode + "3";
                    } else if (j == 5) {
                        panCode = panCode + "R1";
                    }
                    addPanel(panCode);
                }

                if (i == 6) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L2";
                    } else if (j == 2) {
                        panCode = panCode + "4";
                    } else if (j == 3) {
                        panCode = panCode + "5";
                    } else if (j == 4) {
                        panCode = panCode + "6";
                    } else if (j == 5) {
                        panCode = panCode + "R2";
                    }
                    addPanel(panCode);
                }

                if (i == 7) {
                    String panCode = "H";
                    if (j == 1) {
                        panCode = panCode + "L3";
                    } else if (j == 2) {
                        panCode = panCode + "7";
                    } else if (j == 3) {
                        panCode = panCode + "8";
                    } else if (j == 4) {
                        panCode = panCode + "9";
                    } else if (j == 5) {
                        panCode = panCode + "R3";
                    }
                    addPanel(panCode);

                }

                if (i == 8) {
                    String panCode = "HB";
                    if (j == 1) {
                        panCode = panCode + "1";
                    } else if (j == 2) {
                        panCode = panCode + "2";
                    } else if (j == 3) {
                        panCode = panCode + "3";
                    } else if (j == 4) {
                        panCode = panCode + "4";
                    } else if (j == 5) {
                        panCode = panCode + "5";
                    }
                    addPanel(panCode);
                }

            }
        }
        for (Map.Entry<String, JPanel> entry : panGrid.entrySet()) {
            String string = entry.getKey();
            System.out.println(string);

        }
    }

    public void addPanel(String panCode) {
        JPanel p = new JPanel();
        p.setSize(66, 66);
        p.setLayout(new GridLayout(2, 2));
        p.setOpaque(false);
        p.setBorder(BorderFactory.createDashedBorder(Color.pink));
        String code = panCode;
        for (int k = 1; k <= 2; k++) {
            for (int l = 1; l <= 2; l++) {

                if (k == 1) {
                    if (l == 1) {
                        code = panCode + "A";
                    } else {
                        code = panCode + "B";
                    }
                }
                if (k == 2) {
                    if (l == 1) {
                        code = panCode + "C";
                    } else {
                        code = panCode + "D";
                    }
                }

                JPanel pin = new JPanel();
                pin.setSize(33, 33);
                pin.setBorder(BorderFactory.createDashedBorder(Color.LIGHT_GRAY));
                pin.setOpaque(false);
                p.add(pin);
                panGrid.put(code, pin);

            }
        }
        this.add(p);
    }

    public void dig(String txt1, String txt2) {
        JPanel p1 = panGrid.get(txt1);
        JPanel p2 = panGrid.get(txt2);

        this.x1 = (int) (p1.getParent().getLocation().getX() + (p1.getWidth() / 2));
        this.y1 = (int) (p1.getParent().getLocation().getY() + (p1.getHeight() / 2));
        this.x2 = (int) (p2.getParent().getLocation().getX() + (p2.getWidth() / 2));
        this.y2 = (int) (p2.getParent().getLocation().getY() + (p2.getHeight() / 2));
        repaint();
    }

    public void drawImage(int x1, int y1, int x2, int y2) {
        this.img = img;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
//        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
//        setPreferredSize(size);
//        setMinimumSize(size);
//        setMaximumSize(size);
//        setSize(size);
//        setLayout(null);
        repaint();
    }

    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);

        if (x1 != 0 && y1 != 0 && x2 != 0 && y2 != 0) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            Point sw = new Point(x1, y1);
            Point ne = new Point(x2, y2);
            g2.setPaint(Color.GREEN);
            g2.draw(new Line2D.Double(sw, ne));

        }
    }

}
