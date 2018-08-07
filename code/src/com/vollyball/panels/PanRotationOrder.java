/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.PlayerPosition;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanRotationOrder extends javax.swing.JPanel {

    TeamDao td = new TeamDao();
    DefaultTableModel modelSelectedPlayer;
    int focusOn;
    Color cG = Color.ORANGE;
    Color cL = Color.ORANGE;
    LinkedHashMap<String, Player> playerMap;

    /**
     * Creates new form PanRotationOrder
     */
    public PanRotationOrder(int matchId, int teamId) {
        initComponents();
        pos1.requestFocus();
        playerMap = new LinkedHashMap<>();
        modelSelectedPlayer = (DefaultTableModel) tbPlayers.getModel();
        List<Player> playerList = td.getTeamPlayers(teamId);
        List<Integer> selectedPlayers = td.getMatchPlayers(matchId, teamId);

        int j = 0;
        for (Player player : playerList) {
            playerMap.put(player.getChestNo(), player);
            if (selectedPlayers.contains(player.getId())) {
                Object[] row = {player.getName(), player.getChestNo(), PlayerPosition.getNameById(player.getPosition()).getName()};
                modelSelectedPlayer.addRow(row);
                if (!Controller.panMatchSet.initialPositionMap.isEmpty()) {
                    for (int i = 1; i <= 7; i++) {
                        if (Controller.panMatchSet.initialPositionMap.get(i).getChestNo().equals(player.getChestNo())) {
                            for (int k = 0; k < tbPlayers.getRowCount(); k++) {
                                if (player.getChestNo().equals((String) tbPlayers.getValueAt(k, 1))) {
                                    modelSelectedPlayer.removeRow(k);
                                }
                            }
                        }
                    }
                }
            }

        }

        if (!Controller.panMatchSet.initialPositionMap.isEmpty()) {

            pos1.setText(Controller.panMatchSet.initialPositionMap.get(1).getChestNo());
            pos2.setText(Controller.panMatchSet.initialPositionMap.get(2).getChestNo());
            pos3.setText(Controller.panMatchSet.initialPositionMap.get(3).getChestNo());
            pos4.setText(Controller.panMatchSet.initialPositionMap.get(4).getChestNo());
            pos5.setText(Controller.panMatchSet.initialPositionMap.get(5).getChestNo());
            pos6.setText(Controller.panMatchSet.initialPositionMap.get(6).getChestNo());
            libero.setText(Controller.panMatchSet.initialPositionMap.get(7).getChestNo());
        }

        tbPlayers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    String selectedChest = null;

                    int selectedRow = tbPlayers.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {

                        selectedChest = (String) tbPlayers.getValueAt(selectedRow, 1);

                    }
                    String text = "";

                    if (selectedChest != null) {
                        modelSelectedPlayer.removeRow(selectedRow);

                        switch (focusOn) {
                            case 1:
                                text = pos1.getText();
                                pos1.setText(selectedChest);
                                pos2.requestFocus();

                                break;
                            case 2:
                                text = pos2.getText();
                                pos2.setText(selectedChest);
                                pos3.requestFocus();
                                break;
                            case 3:
                                text = pos3.getText();
                                pos3.setText(selectedChest);
                                pos4.requestFocus();
                                break;
                            case 4:
                                text = pos4.getText();
                                pos4.setText(selectedChest);
                                pos5.requestFocus();
                                break;
                            case 5:
                                text = pos5.getText();
                                pos5.setText(selectedChest);
                                pos6.requestFocus();
                                break;
                            case 6:
                                text = pos6.getText();
                                pos6.setText(selectedChest);
                                libero.requestFocus();
                                break;
                            case 7:
                                text = libero.getText();
                                libero.setText(selectedChest);
                                pos1.requestFocus();
                                break;
                            default:
                                text = pos1.getText();
                                pos1.setText(selectedChest);
                                pos2.requestFocus();
                                break;
                        }
                        if (!text.equals("")) {
                            Player p = playerMap.get(text);
                            Object[] row = {p.getName(), p.getChestNo(), "Server"};
                            modelSelectedPlayer.addRow(row);
                        }
                    }

                }
            }
        });

    }

    public void refresh() {
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
        pos4 = new javax.swing.JTextField();
        pos3 = new javax.swing.JTextField();
        pos2 = new javax.swing.JTextField();
        pos5 = new javax.swing.JTextField();
        pos6 = new javax.swing.JTextField();
        pos1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlayers = new javax.swing.JTable(){ private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };};
            jPanel3 = new javax.swing.JPanel();
            jPanel4 = new javax.swing.JPanel();
            jLabel2 = new javax.swing.JLabel();
            libero = new javax.swing.JTextField();
            jPanel5 = new javax.swing.JPanel();
            jLabel3 = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            jLabel4 = new javax.swing.JLabel();

            jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

            pos4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            pos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            pos4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            pos4.setCaretColor(new java.awt.Color(255, 255, 255));
            pos4.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    pos4FocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    pos4FocusLost(evt);
                }
            });

            pos3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            pos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            pos3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            pos3.setCaretColor(new java.awt.Color(255, 255, 255));
            pos3.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    pos3FocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    pos3FocusLost(evt);
                }
            });

            pos2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            pos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            pos2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            pos2.setCaretColor(new java.awt.Color(255, 255, 255));
            pos2.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    pos2FocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    pos2FocusLost(evt);
                }
            });

            pos5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            pos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            pos5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            pos5.setCaretColor(new java.awt.Color(255, 255, 255));
            pos5.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    pos5FocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    pos5FocusLost(evt);
                }
            });

            pos6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            pos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            pos6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            pos6.setCaretColor(new java.awt.Color(255, 255, 255));
            pos6.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    pos6FocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    pos6FocusLost(evt);
                }
            });

            pos1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            pos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            pos1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            pos1.setCaretColor(new java.awt.Color(255, 255, 255));
            pos1.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    pos1FocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    pos1FocusLost(evt);
                }
            });

            jPanel2.setBackground(new java.awt.Color(54, 78, 108));

            jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
            jLabel1.setForeground(new java.awt.Color(255, 255, 255));
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("ROTATION");

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(40, Short.MAX_VALUE))
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(20, Short.MAX_VALUE))
            );

            tbPlayers.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
            tbPlayers.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Name", "Chest No.", "Position"
                }
            ));
            tbPlayers.setRowHeight(25);
            jScrollPane1.setViewportView(tbPlayers);

            jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

            jPanel4.setBackground(new java.awt.Color(54, 78, 108));
            jPanel4.setForeground(new java.awt.Color(255, 255, 255));

            jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel2.setText("LIBERO");

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel2)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            libero.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
            libero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
            libero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
            libero.setCaretColor(new java.awt.Color(255, 255, 255));
            libero.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    liberoFocusGained(evt);
                }
                public void focusLost(java.awt.event.FocusEvent evt) {
                    liberoFocusLost(evt);
                }
            });

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(libero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE))
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20)
                    .addComponent(libero, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(20, 20, 20))
            );

            jPanel5.setBackground(new java.awt.Color(54, 78, 108));
            jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jLabel3.setBackground(new java.awt.Color(255, 255, 255));
            jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
            jLabel3.setForeground(new java.awt.Color(255, 255, 255));
            jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel3.setText("SAVE");
            jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    jLabel3MouseClicked(evt);
                }
            });

            javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
            jPanel5.setLayout(jPanel5Layout);
            jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
            );
            jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            );

            jPanel6.setBackground(new java.awt.Color(54, 78, 108));
            jPanel6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

            jLabel4.setBackground(new java.awt.Color(255, 255, 255));
            jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel4.setText("CANCEL");

            javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
            jPanel6.setLayout(jPanel6Layout);
            jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
            );
            jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(81, 81, 81))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }// </editor-fold>//GEN-END:initComponents

    private void pos1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos1FocusGained
        // TODO add your handling code here:
        focusOn = 1;
        pos1.setBorder(BorderFactory.createLineBorder(cG));

    }//GEN-LAST:event_pos1FocusGained

    private void pos1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos1FocusLost
        // TODO add your handling code here:
        pos1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos1FocusLost

    private void pos2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos2FocusGained
        // TODO add your handling code here:
        focusOn = 2;
        pos2.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos2FocusGained

    private void pos2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos2FocusLost
        // TODO add your handling code here:
        pos2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos2FocusLost

    private void pos3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos3FocusGained
        // TODO add your handling code here:
        focusOn = 3;
        pos3.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos3FocusGained

    private void pos3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos3FocusLost
        // TODO add your handling code here:
        pos3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos3FocusLost

    private void pos4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos4FocusGained
        // TODO add your handling code here:
        focusOn = 4;
        pos4.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos4FocusGained

    private void pos4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos4FocusLost
        // TODO add your handling code here:
        pos4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos4FocusLost

    private void pos5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos5FocusGained
        // TODO add your handling code here:
        focusOn = 5;
        pos5.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos5FocusGained

    private void pos5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos5FocusLost
        // TODO add your handling code here:
        pos5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos5FocusLost

    private void pos6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos6FocusGained
        // TODO add your handling code here:
        focusOn = 6;
        pos6.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos6FocusGained

    private void pos6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos6FocusLost
        // TODO add your handling code here:
        pos6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos6FocusLost

    private void liberoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_liberoFocusGained
        // TODO add your handling code here:
        focusOn = 7;
        libero.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_liberoFocusGained

    private void liberoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_liberoFocusLost
        // TODO add your handling code here:
        libero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_liberoFocusLost

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:

        Controller.panMatchSet.initialPositionMap.put(1, playerMap.get(pos1.getText()));
        Controller.panMatchSet.initialPositionMap.put(2, playerMap.get(pos2.getText()));
        Controller.panMatchSet.initialPositionMap.put(3, playerMap.get(pos3.getText()));
        Controller.panMatchSet.initialPositionMap.put(4, playerMap.get(pos4.getText()));
        Controller.panMatchSet.initialPositionMap.put(5, playerMap.get(pos5.getText()));
        Controller.panMatchSet.initialPositionMap.put(6, playerMap.get(pos6.getText()));
        Controller.panMatchSet.initialPositionMap.put(7, playerMap.get(libero.getText()));
        Controller.panMatchSet.rallyPositionMap.putAll(Controller.panMatchSet.initialPositionMap);
        Controller.panMatchSet.setRotationDialog.close();
        Controller.panMatchSet.setRotations();

    }//GEN-LAST:event_jLabel3MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField libero;
    private javax.swing.JTextField pos1;
    private javax.swing.JTextField pos2;
    private javax.swing.JTextField pos3;
    private javax.swing.JTextField pos4;
    private javax.swing.JTextField pos5;
    private javax.swing.JTextField pos6;
    private javax.swing.JTable tbPlayers;
    // End of variables declaration//GEN-END:variables
}
