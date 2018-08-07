/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.PlayerPosition;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author nishant.vibhute
 */
public class PanSelectPlayers extends javax.swing.JPanel {

    LinkedHashMap<String, Integer> teamsMap;
    LinkedHashMap<String, Integer> playerMap;
    TeamDao td = new TeamDao();
    DefaultTableModel model, modelSelectedPlayer;
    int selectedTeam, matchId;
    MatchDao matchDao = new MatchDao();
    int teamId;

    /**
     * Creates new form PanSelectPlayers
     */
    public PanSelectPlayers(int matchId, int teamId) {
        initComponents();

        this.matchId = matchId;
        this.teamId = teamId;

        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        teamsMap = new LinkedHashMap<>();
        playerMap = new LinkedHashMap<>();
        model = (DefaultTableModel) tbAllPlayers.getModel();
        modelSelectedPlayer = (DefaultTableModel) tbSelectedPlayers.getModel();

        teamsMap.put(team.getTeam1name(), team.getTeam1());
        teamsMap.put(team.getTeam2name(), team.getTeam2());

        Color heading = new Color(204, 204, 204);
        Color ivory = new Color(255, 255, 255);
        JTableHeader header = tbAllPlayers.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRenderer(tbAllPlayers));
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 35));
        header.setBackground(heading);
//        header.setDefaultRenderer(new TableHeaderRenderer(tbAllPlayers));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbAllPlayers.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbAllPlayers.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbAllPlayers.getColumnModel().getColumn(1).setPreferredWidth(20);
        tbAllPlayers.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        tbAllPlayers.setOpaque(true);
        tbAllPlayers.setFillsViewportHeight(true);
        tbAllPlayers.setBackground(ivory);

        JTableHeader headerSelectedPlayers = tbSelectedPlayers.getTableHeader();
        headerSelectedPlayers.setDefaultRenderer(new TableHeaderRenderer(tbSelectedPlayers));
        headerSelectedPlayers.setOpaque(false);
        headerSelectedPlayers.setPreferredSize(new Dimension(100, 35));
        headerSelectedPlayers.setBackground(heading);
//        headerSelectedPlayers.setDefaultRenderer(new TableHeaderRenderer(tbSelectedPlayers));
        DefaultTableCellRenderer centerRendererSelectedPlayers = new DefaultTableCellRenderer();
        centerRendererSelectedPlayers.setHorizontalAlignment(JLabel.CENTER);
        tbSelectedPlayers.getColumnModel().getColumn(0).setCellRenderer(centerRendererSelectedPlayers);
        tbSelectedPlayers.getColumnModel().getColumn(1).setCellRenderer(centerRendererSelectedPlayers);
        tbSelectedPlayers.getColumnModel().getColumn(1).setPreferredWidth(20);
        tbSelectedPlayers.getColumnModel().getColumn(2).setCellRenderer(centerRendererSelectedPlayers);
        tbSelectedPlayers.setOpaque(true);
        tbSelectedPlayers.setFillsViewportHeight(true);
        tbSelectedPlayers.setBackground(ivory);

        tbAllPlayers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedName = null;
                    String selectedChest = null;
                    String selectedPosition = null;

                    int selectedRow = tbAllPlayers.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {
                        selectedName = (String) tbAllPlayers.getValueAt(selectedRow, 0);
                        selectedChest = (String) tbAllPlayers.getValueAt(selectedRow, 1);
                        selectedPosition = (String) tbAllPlayers.getValueAt(selectedRow, 2);

                    }
                    if (selectedName != null) {
                        Object[] row = {selectedName, selectedChest, selectedPosition};
                        modelSelectedPlayer.addRow(row);
                        model.removeRow(selectedRow);
                        int rows = tbSelectedPlayers.getRowCount();
                        lblSelectCount.setText("Selected : " + rows);
                    }
                }
            }
        });

        tbSelectedPlayers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedName = null;
                    String selectedChest = null;
                    String selectedPosition = null;

                    int selectedRow = tbSelectedPlayers.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {
                        selectedName = (String) tbSelectedPlayers.getValueAt(selectedRow, 0);
                        selectedChest = (String) tbSelectedPlayers.getValueAt(selectedRow, 1);
                        selectedPosition = (String) tbSelectedPlayers.getValueAt(selectedRow, 2);

                    }
                    if (selectedName != null) {
                        Object[] row = {selectedName, selectedChest, selectedPosition};
                        model.addRow(row);
                        modelSelectedPlayer.removeRow(selectedRow);
                        int rows = tbSelectedPlayers.getRowCount();
                        lblSelectCount.setText("Selected : " + rows);
                    }
                }
            }
        });
        setValues();
    }

    public void setValues() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);

        }

        for (int i = modelSelectedPlayer.getRowCount() - 1; i >= 0; i--) {
            modelSelectedPlayer.removeRow(i);
        }

        List<Player> playerList = td.getTeamPlayers(teamId);
        List<Integer> selectedPlayers = td.getMatchPlayers(this.matchId, teamId);
        selectedTeam = teamId;
        int i = 0;
        for (Player player : playerList) {
            playerMap.put(player.getName(), player.getId());
            if (selectedPlayers.contains(player.getId())) {
                Object[] row = {player.getName(), player.getChestNo(), PlayerPosition.getNameById(player.getPosition()).getName()};
                modelSelectedPlayer.addRow(row);
            } else {
                Object[] row = {player.getName(), player.getChestNo(), PlayerPosition.getNameById(player.getPosition()).getName()};
                model.addRow(row);
            }
        }
        int rows = tbSelectedPlayers.getRowCount();
        lblSelectCount.setText("Selected : " + rows);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSelectedPlayers = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAllPlayers = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        butSave = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblSelectCount = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        butCancel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(57, 74, 108)));

        tbSelectedPlayers.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tbSelectedPlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Chest No.", "Position"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSelectedPlayers.setRowHeight(25);
        jScrollPane1.setViewportView(tbSelectedPlayers);

        tbAllPlayers.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbAllPlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Name", "Chest No.", "Position"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbAllPlayers.setRowHeight(25);
        jScrollPane2.setViewportView(tbAllPlayers);

        jPanel12.setBackground(new java.awt.Color(58, 74, 108));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        butSave.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        butSave.setForeground(new java.awt.Color(255, 255, 255));
        butSave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        butSave.setText("SAVE");
        butSave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butSaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butSave, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butSave, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Select Players");

        lblSelectCount.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSelectCount.setText("Selected : 0");

        jPanel13.setBackground(new java.awt.Color(58, 74, 108));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        butCancel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        butCancel.setForeground(new java.awt.Color(255, 255, 255));
        butCancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        butCancel.setText("CANCEL");
        butCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butCancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 148, Short.MAX_VALUE)
                                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSelectCount, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(lblSelectCount))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        jPanel1.setBackground(new java.awt.Color(58, 74, 108));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Select Evaluating Team");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void butSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butSaveMouseClicked
        // TODO add your handling code here:

        if (tbSelectedPlayers.getRowCount() < 7) {
            JOptionPane.showMessageDialog(this, "Please select Atleast 7 Number of Players", "Error", 2);
        } else {
            List<Integer> players = new ArrayList<>();
            for (int i = 0; i < tbSelectedPlayers.getRowCount(); i++) {
                players.add(playerMap.get(tbSelectedPlayers.getValueAt(i, 0)));
            }

//            int count = matchDao.insertMatchPlayers(this.matchId, selectedTeam, players);
//            if (count != 0) {
//
////                JOptionPane.showMessageDialog(this, "Players Selected Succe");
//                Controller.panMatchEvaluationHome.setTeamName();
//                Controller.panMatchEvaluationHome.selectTeamPlayerDialog.close();
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to Select Players");
//            }
        }
    }//GEN-LAST:event_butSaveMouseClicked

    private void butCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butCancelMouseClicked
        // TODO add your handling code here:
        Controller.panMatchEvaluationHome.selectTeamPlayerDialog.close();
    }//GEN-LAST:event_butCancelMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel butCancel;
    private javax.swing.JLabel butSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblSelectCount;
    private javax.swing.JTable tbAllPlayers;
    private javax.swing.JTable tbSelectedPlayers;
    // End of variables declaration//GEN-END:variables
}
