/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.panel;

import com.vollyball.enums.PlayerPosition;
import com.vollyball.training.bean.BatchMatch;
import com.vollyball.training.bean.MatchRowBean;
import com.vollyball.training.bean.Trainee;
import com.vollyball.training.dao.BatchDao;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author #dabbu
 */
public class PanBatchMatch extends javax.swing.JPanel {

    List<JComboBox> position = new ArrayList<>();
    List<JComboBox> traineeCombo = new ArrayList<>();
    List<JLabel> lblMinusList = new ArrayList<>();
    BatchDao bd = new BatchDao();
    int batchId;
    int i = 0, m = 6;
    List<JPanel> rowPanels = new ArrayList<>();
    LinkedHashMap<String, Integer> traineeMap;
    List<MatchRowBean> traineeRow = new ArrayList<>();
    List<MatchRowBean> traineeRow1 = new ArrayList<>();
    List<Trainee> team1List;
    List<Trainee> team2List;

    /**
     * Creates new form PanBatchMatch
     */
    public PanBatchMatch(int batchId) {
        initComponents();
        this.batchId = batchId;

        position.add(comboTeam1Pos1);
        position.add(comboTeam1Pos2);
        position.add(comboTeam1Pos3);
        position.add(comboTeam1Pos4);
        position.add(comboTeam1Pos5);
        position.add(comboTeam1Pos6);
        position.add(comboTeam2Pos1);
        position.add(comboTeam2Pos2);
        position.add(comboTeam2Pos3);
        position.add(comboTeam2Pos4);
        position.add(comboTeam2Pos5);
        position.add(comboTeam2Pos6);

        traineeCombo.add(comboTeam1Trainee1);
        traineeCombo.add(comboTeam1Trainee2);
        traineeCombo.add(comboTeam1Trainee3);
        traineeCombo.add(comboTeam1Trainee4);
        traineeCombo.add(comboTeam1Trainee5);
        traineeCombo.add(comboTeam1Trainee6);
        traineeCombo.add(comboTeam2Trainee1);
        traineeCombo.add(comboTeam2Trainee2);
        traineeCombo.add(comboTeam2Trainee3);
        traineeCombo.add(comboTeam2Trainee4);
        traineeCombo.add(comboTeam2Trainee5);
        traineeCombo.add(comboTeam2Trainee6);

        List<Trainee> trainees = bd.getTraineeList(batchId);
//        traineeMap = new LinkedHashMap<>();
        comboTeam1Trainee1.addItem("Select");
        comboTeam1Trainee2.addItem("Select");
        comboTeam1Trainee3.addItem("Select");
        comboTeam1Trainee4.addItem("Select");
        comboTeam1Trainee5.addItem("Select");
        comboTeam1Trainee6.addItem("Select");
        comboTeam2Trainee1.addItem("Select");
        comboTeam2Trainee2.addItem("Select");
        comboTeam2Trainee3.addItem("Select");
        comboTeam2Trainee4.addItem("Select");
        comboTeam2Trainee5.addItem("Select");
        comboTeam2Trainee6.addItem("Select");

        lblMinusList.add(lblSub);
        lblMinusList.add(lblSub1);
        lblMinusList.add(lblSub2);
        lblMinusList.add(lblSub3);
        lblMinusList.add(lblSub4);

        traineeRow.add(new MatchRowBean(comboTeam1Trainee1, comboTeam1Pos1));
        traineeRow.add(new MatchRowBean(comboTeam1Trainee2, comboTeam1Pos2));
        traineeRow.add(new MatchRowBean(comboTeam1Trainee3, comboTeam1Pos3));
        traineeRow.add(new MatchRowBean(comboTeam1Trainee4, comboTeam1Pos4));
        traineeRow.add(new MatchRowBean(comboTeam1Trainee5, comboTeam1Pos5));
        traineeRow.add(new MatchRowBean(comboTeam1Trainee6, comboTeam1Pos6));
        traineeRow1.add(new MatchRowBean(comboTeam2Trainee1, comboTeam2Pos1));
        traineeRow1.add(new MatchRowBean(comboTeam2Trainee2, comboTeam2Pos2));
        traineeRow1.add(new MatchRowBean(comboTeam2Trainee3, comboTeam2Pos3));
        traineeRow1.add(new MatchRowBean(comboTeam2Trainee4, comboTeam2Pos4));
        traineeRow1.add(new MatchRowBean(comboTeam2Trainee5, comboTeam2Pos5));
        traineeRow1.add(new MatchRowBean(comboTeam2Trainee6, comboTeam2Pos6));

        rowPanels.add(row1);
        rowPanels.add(row2);
        rowPanels.add(row3);
        rowPanels.add(row4);
        rowPanels.add(row5);

        for (int i = 0; i < position.size(); i++) {
            ((JLabel) position.get(i).getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            for (PlayerPosition pp : PlayerPosition.values()) {
                position.get(i).addItem(pp.getName());
            }

            position.get(i).addItemListener(new ItemListener() {
                // Listening if a new items of the combo box has been selected.
                public void itemStateChanged(ItemEvent event) {
                    ((JComboBox) event.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
            });

        }
        traineeMap = new LinkedHashMap<>();
        for (int i = 0; i < traineeCombo.size(); i++) {
            ((JLabel) traineeCombo.get(i).getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
            for (Trainee trainee : trainees) {
                traineeMap.put(trainee.getName(), trainee.getId());
                traineeCombo.get(i).addItem(trainee.getName());
            }

            traineeCombo.get(i).addItemListener(new ItemListener() {
                // Listening if a new items of the combo box has been selected.
                public void itemStateChanged(ItemEvent event) {
                    ((JComboBox) event.getSource()).setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
            });

        }

        for (JPanel p : rowPanels) {
            p.setVisible(false);
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

        jPanel2 = new javax.swing.JPanel();
        lblNewTeam = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtteam1Name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtteam2Name = new javax.swing.JTextField();
        panAddPlayer = new javax.swing.JPanel();
        lblAddNew = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblNewTeam1 = new javax.swing.JLabel();
        row = new javax.swing.JPanel();
        comboTeam1Trainee1 = new javax.swing.JComboBox<>();
        comboTeam1Pos1 = new javax.swing.JComboBox<>();
        comboTeam2Trainee1 = new javax.swing.JComboBox<>();
        comboTeam2Pos1 = new javax.swing.JComboBox<>();
        row1 = new javax.swing.JPanel();
        comboTeam1Trainee2 = new javax.swing.JComboBox<>();
        comboTeam1Pos2 = new javax.swing.JComboBox<>();
        comboTeam2Trainee2 = new javax.swing.JComboBox<>();
        comboTeam2Pos2 = new javax.swing.JComboBox<>();
        lblSub = new javax.swing.JLabel();
        row3 = new javax.swing.JPanel();
        comboTeam1Trainee4 = new javax.swing.JComboBox<>();
        comboTeam1Pos4 = new javax.swing.JComboBox<>();
        comboTeam2Trainee4 = new javax.swing.JComboBox<>();
        comboTeam2Pos4 = new javax.swing.JComboBox<>();
        lblSub2 = new javax.swing.JLabel();
        row2 = new javax.swing.JPanel();
        comboTeam1Trainee3 = new javax.swing.JComboBox<>();
        comboTeam1Pos3 = new javax.swing.JComboBox<>();
        comboTeam2Trainee3 = new javax.swing.JComboBox<>();
        comboTeam2Pos3 = new javax.swing.JComboBox<>();
        lblSub1 = new javax.swing.JLabel();
        row4 = new javax.swing.JPanel();
        comboTeam1Trainee5 = new javax.swing.JComboBox<>();
        comboTeam1Pos5 = new javax.swing.JComboBox<>();
        comboTeam2Trainee5 = new javax.swing.JComboBox<>();
        comboTeam2Pos5 = new javax.swing.JComboBox<>();
        lblSub3 = new javax.swing.JLabel();
        row5 = new javax.swing.JPanel();
        comboTeam1Trainee6 = new javax.swing.JComboBox<>();
        comboTeam1Pos6 = new javax.swing.JComboBox<>();
        comboTeam2Trainee6 = new javax.swing.JComboBox<>();
        comboTeam2Pos6 = new javax.swing.JComboBox<>();
        lblSub4 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNewTeam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam.setText("Schedule Match");
        lblNewTeam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel1.setText("Team 1");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel3.setText("Team 2");

        panAddPlayer.setBackground(new java.awt.Color(102, 102, 102));
        panAddPlayer.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAddNew.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAddNew.setForeground(new java.awt.Color(255, 255, 255));
        lblAddNew.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAddNew.setText("ADD");
        lblAddNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAddNewMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAddPlayerLayout = new javax.swing.GroupLayout(panAddPlayer);
        panAddPlayer.setLayout(panAddPlayerLayout);
        panAddPlayerLayout.setHorizontalGroup(
            panAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panAddPlayerLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lblAddNew)
                .addContainerGap(35, Short.MAX_VALUE))
        );
        panAddPlayerLayout.setVerticalGroup(
            panAddPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAddNew, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(57, 74, 108));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNewTeam1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam1.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam1.setText("SAVE");
        lblNewTeam1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNewTeam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewTeam1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        row.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout rowLayout = new javax.swing.GroupLayout(row);
        row.setLayout(rowLayout);
        rowLayout.setHorizontalGroup(
            rowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rowLayout.createSequentialGroup()
                .addComponent(comboTeam1Trainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam1Pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(comboTeam2Trainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam2Pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rowLayout.setVerticalGroup(
            rowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rowLayout.createSequentialGroup()
                .addGroup(rowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTeam1Pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTeam1Trainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTeam2Trainee1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboTeam2Pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        row1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub.setToolTipText("Remove Row");
        lblSub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSubMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout row1Layout = new javax.swing.GroupLayout(row1);
        row1.setLayout(row1Layout);
        row1Layout.setHorizontalGroup(
            row1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row1Layout.createSequentialGroup()
                .addComponent(comboTeam1Trainee2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam1Pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(comboTeam2Trainee2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam2Pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        row1Layout.setVerticalGroup(
            row1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(row1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboTeam1Pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam1Trainee2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Trainee2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblSub, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        row3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSub2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub2.setToolTipText("Remove Row");
        lblSub2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout row3Layout = new javax.swing.GroupLayout(row3);
        row3.setLayout(row3Layout);
        row3Layout.setHorizontalGroup(
            row3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row3Layout.createSequentialGroup()
                .addComponent(comboTeam1Trainee4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam1Pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(comboTeam2Trainee4, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam2Pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        row3Layout.setVerticalGroup(
            row3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboTeam1Pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam1Trainee4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Trainee4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        row2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSub1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub1.setToolTipText("Remove Row");
        lblSub1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout row2Layout = new javax.swing.GroupLayout(row2);
        row2.setLayout(row2Layout);
        row2Layout.setHorizontalGroup(
            row2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row2Layout.createSequentialGroup()
                .addComponent(comboTeam1Trainee3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam1Pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(comboTeam2Trainee3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam2Pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        row2Layout.setVerticalGroup(
            row2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboTeam1Pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam1Trainee3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Trainee3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        row4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSub3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub3.setToolTipText("Remove Row");
        lblSub3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout row4Layout = new javax.swing.GroupLayout(row4);
        row4.setLayout(row4Layout);
        row4Layout.setHorizontalGroup(
            row4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row4Layout.createSequentialGroup()
                .addComponent(comboTeam1Trainee5, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam1Pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(comboTeam2Trainee5, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam2Pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSub3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        row4Layout.setVerticalGroup(
            row4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(comboTeam1Pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam1Trainee5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Trainee5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(comboTeam2Pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblSub3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        row5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSub4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSub4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-minus-20.png"))); // NOI18N
        lblSub4.setToolTipText("Remove Row");
        lblSub4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSub4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout row5Layout = new javax.swing.GroupLayout(row5);
        row5.setLayout(row5Layout);
        row5Layout.setHorizontalGroup(
            row5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row5Layout.createSequentialGroup()
                .addComponent(comboTeam1Trainee6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam1Pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(comboTeam2Trainee6, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comboTeam2Pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        row5Layout.setVerticalGroup(
            row5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(row5Layout.createSequentialGroup()
                .addGroup(row5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(row5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboTeam1Pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTeam1Trainee6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTeam2Trainee6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboTeam2Pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtteam1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtteam2Name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(406, 406, 406)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(row1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(row2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(row3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(row4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(row5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 911, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtteam1Name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtteam2Name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(row, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(row1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(row2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(row3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(row4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(row5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 30, Short.MAX_VALUE)
                .addComponent(panAddPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblAddNewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAddNewMouseClicked
        // TODO add your handling code here:
        if (i != 0) {
            lblMinusList.get(i - 1).setVisible(false);
        }
        rowPanels.get(i).setVisible(true);
        i++;
        m++;
        if (i == 5) {
            panAddPlayer.setVisible(false);
        }
    }//GEN-LAST:event_lblAddNewMouseClicked

    public void hidePanel(int j) {
        if (j != 0) {
            lblMinusList.get(j - 1).setVisible(true);
        }
        panAddPlayer.setVisible(true);
        rowPanels.get(j).setVisible(false);
        i--;
        m--;
    }

    private void lblNewTeam1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewTeam1MouseClicked
        // TODO add your handling code here:
        String mssg=validateTrainee();
        if (!mssg.isEmpty()) {
            JOptionPane.showMessageDialog(this, mssg);
        } else {
            team1List = new ArrayList<>();
            team2List = new ArrayList<>();
            for (MatchRowBean tr : traineeRow) {
                addPlayer1((String) tr.getTrainee().getSelectedItem(), PlayerPosition.getIdByName(String.valueOf(tr.getPosition().getSelectedItem())).getId());
            }
            for (MatchRowBean tr : traineeRow1) {
                addPlayer2((String) tr.getTrainee().getSelectedItem(), PlayerPosition.getIdByName(String.valueOf(tr.getPosition().getSelectedItem())).getId());
            }
            BatchMatch bm = new BatchMatch();
            bm.setBatchId(batchId);
            bm.setTeam1Name(txtteam1Name.getText());
            bm.setTeam2Name(txtteam2Name.getText());

            bm.setTeam1List(team1List);
            bm.setTeam2List(team2List);
            int count = bd.insertBatchMatch(bm);
            if (count != 0) {
                JOptionPane.showMessageDialog(this, "Batch Match  Scheduled Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Schedule Match");
            }
        }


    }//GEN-LAST:event_lblNewTeam1MouseClicked

    public void addPlayer1(String name, int position) {
        Trainee t = new Trainee();
        if (name.equalsIgnoreCase("select")) {
            t.setId(0);
        } else {
            t.setId(traineeMap.get(name));
        }
        t.setPosition(position);
        team1List.add(t);
    }

    public void addPlayer2(String name, int position) {
        Trainee t = new Trainee();
        if (name.equalsIgnoreCase("select")) {
            t.setId(0);
        } else {
            t.setId(traineeMap.get(name));
        }
        t.setPosition(position);
        team2List.add(t);
    }

    public String validateTrainee() {
        String msg = "";
        if (txtteam1Name.getText().equals("")) {
            msg = "Enter Team 1 Name";
        }
        if (txtteam2Name.getText().equals("")) {
            msg = msg + "Enter Team 2 Name";
        }
        return msg;

    }

    private void lblSubMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSubMouseClicked
        // TODO add your handling code here:
        hidePanel(0);
    }//GEN-LAST:event_lblSubMouseClicked

    private void lblSub1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub1MouseClicked
        // TODO add your handling code here:
        hidePanel(1);
    }//GEN-LAST:event_lblSub1MouseClicked

    private void lblSub2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub2MouseClicked
        // TODO add your handling code here:
        hidePanel(2);
    }//GEN-LAST:event_lblSub2MouseClicked

    private void lblSub3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub3MouseClicked
        // TODO add your handling code here:
        hidePanel(3);
    }//GEN-LAST:event_lblSub3MouseClicked

    private void lblSub4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSub4MouseClicked
        // TODO add your handling code here:
        hidePanel(4);
    }//GEN-LAST:event_lblSub4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboTeam1Pos1;
    private javax.swing.JComboBox<String> comboTeam1Pos2;
    private javax.swing.JComboBox<String> comboTeam1Pos3;
    private javax.swing.JComboBox<String> comboTeam1Pos4;
    private javax.swing.JComboBox<String> comboTeam1Pos5;
    private javax.swing.JComboBox<String> comboTeam1Pos6;
    private javax.swing.JComboBox<String> comboTeam1Trainee1;
    private javax.swing.JComboBox<String> comboTeam1Trainee2;
    private javax.swing.JComboBox<String> comboTeam1Trainee3;
    private javax.swing.JComboBox<String> comboTeam1Trainee4;
    private javax.swing.JComboBox<String> comboTeam1Trainee5;
    private javax.swing.JComboBox<String> comboTeam1Trainee6;
    private javax.swing.JComboBox<String> comboTeam2Pos1;
    private javax.swing.JComboBox<String> comboTeam2Pos2;
    private javax.swing.JComboBox<String> comboTeam2Pos3;
    private javax.swing.JComboBox<String> comboTeam2Pos4;
    private javax.swing.JComboBox<String> comboTeam2Pos5;
    private javax.swing.JComboBox<String> comboTeam2Pos6;
    private javax.swing.JComboBox<String> comboTeam2Trainee1;
    private javax.swing.JComboBox<String> comboTeam2Trainee2;
    private javax.swing.JComboBox<String> comboTeam2Trainee3;
    private javax.swing.JComboBox<String> comboTeam2Trainee4;
    private javax.swing.JComboBox<String> comboTeam2Trainee5;
    private javax.swing.JComboBox<String> comboTeam2Trainee6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAddNew;
    private javax.swing.JLabel lblNewTeam;
    private javax.swing.JLabel lblNewTeam1;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSub1;
    private javax.swing.JLabel lblSub2;
    private javax.swing.JLabel lblSub3;
    private javax.swing.JLabel lblSub4;
    private javax.swing.JPanel panAddPlayer;
    private javax.swing.JPanel row;
    private javax.swing.JPanel row1;
    private javax.swing.JPanel row2;
    private javax.swing.JPanel row3;
    private javax.swing.JPanel row4;
    private javax.swing.JPanel row5;
    private javax.swing.JTextField txtteam1Name;
    private javax.swing.JTextField txtteam2Name;
    // End of variables declaration//GEN-END:variables
}
