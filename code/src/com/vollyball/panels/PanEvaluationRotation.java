/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.DialogPanEvaluation;
import com.vollyball.enums.PlayerPosition;
import com.vollyball.util.CommonUtil;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationRotation extends javax.swing.JPanel {

    TeamDao td = new TeamDao();
    LinkedHashMap<String, Player> playerMapHome;
    LinkedHashMap<String, Player> playerMapOpp;
    DefaultTableModel modelSelectedPlayerHome, modelSelectedPlayerOpp;
    int focusOnHome;
    int focusOnOpp;
    Color cG = Color.ORANGE;
    Color cL = Color.ORANGE;
    int set, matchEvaluationTeamId;
    public LinkedHashMap<Integer, Player> initialHomePositionMap;
    public LinkedHashMap<Integer, Player> initialOppPositionMap;
    MatchDao matchDao = new MatchDao();
    String type, evaluatorName;
    int matchId;
    int homeTeamId;
    int oppTeamId;

    /**
     * Creates new form PanEvaluationRotation
     */
    public PanEvaluationRotation(int homeTeamId, int oppTeamId, int matchId, String homeTeam, String oppteam, int set, int matchEvaluationTeamId, String type, String evaluatorName) {
        initComponents();
        this.set = set;
        this.matchEvaluationTeamId = matchEvaluationTeamId;
        this.type = type;
        this.evaluatorName = evaluatorName;
        lblHome.setText(homeTeam);
        lblOpp.setText(oppteam);
        setHomePlayers(homeTeamId, matchId);
        setOppPlayers(oppTeamId, matchId);
        this.matchId = matchId;
        this.homeTeamId = homeTeamId;
        this.oppTeamId = oppTeamId;
        

    }

    public void setHomePlayers(int homeTeamId, int matchId) {
        playerMapHome = new LinkedHashMap<>();
        modelSelectedPlayerHome = (DefaultTableModel) tbPlayersHome.getModel();
        List<Player> playerListHome = td.getTeamPlayers(homeTeamId);
        List<Integer> selectedPlayersHome = td.getMatchPlayers(matchId, homeTeamId);
        for (Player player : playerListHome) {
            playerMapHome.put(player.getChestNo(), player);
            if (selectedPlayersHome.contains(player.getId())) {
                Object[] row = {player.getName(), player.getChestNo(), PlayerPosition.getNameById(player.getPosition()).getName()};
                modelSelectedPlayerHome.addRow(row);
                if (type.equals("Update")) {
                    if (!Controller.panMatchSet.initialPositionMap.isEmpty()) {
                        for (int i = 1; i <= 7; i++) {
                            if (Controller.panMatchSet.initialPositionMap.get(i).getChestNo().equals(player.getChestNo())) {
                                for (int k = 0; k < tbPlayersHome.getRowCount(); k++) {
                                    if (player.getChestNo().equals((String) tbPlayersHome.getValueAt(k, 1))) {
                                        modelSelectedPlayerHome.removeRow(k);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (type.equals("Update")) {
            txtEvaluatorName.setText(evaluatorName);
            if (!Controller.panMatchSet.initialPositionMap.isEmpty()) {

                pos1Home.setText(Controller.panMatchSet.initialPositionMap.get(1).getChestNo());
                pos2Home.setText(Controller.panMatchSet.initialPositionMap.get(2).getChestNo());
                pos3Home.setText(Controller.panMatchSet.initialPositionMap.get(3).getChestNo());
                pos4Home.setText(Controller.panMatchSet.initialPositionMap.get(4).getChestNo());
                pos5Home.setText(Controller.panMatchSet.initialPositionMap.get(5).getChestNo());
                pos6Home.setText(Controller.panMatchSet.initialPositionMap.get(6).getChestNo());
                liberoHome.setText(Controller.panMatchSet.initialPositionMap.get(7).getChestNo());
            }
        }
        tbPlayersHome.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedChest = null;
                    String selectedPos = null;
                    int selectedRow = tbPlayersHome.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {
                        selectedChest = (String) tbPlayersHome.getValueAt(selectedRow, 1);
                        selectedPos = (String) tbPlayersHome.getValueAt(selectedRow, 2);
                    }
                    String text = "";
                    if (selectedChest != null) {
                        modelSelectedPlayerHome.removeRow(selectedRow);
                        switch (focusOnHome) {
                            case 1:
                                text = pos1Home.getText();
                                pos1Home.setText(selectedChest);
                                pos1Home.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos2Home.requestFocus();
                                break;
                            case 2:
                                text = pos2Home.getText();
                                pos2Home.setText(selectedChest);
                                pos2Home.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos3Home.requestFocus();
                                break;
                            case 3:
                                text = pos3Home.getText();
                                pos3Home.setText(selectedChest);
                                pos3Home.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos4Home.requestFocus();
                                break;
                            case 4:
                                text = pos4Home.getText();
                                pos4Home.setText(selectedChest);
                                pos4Home.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos5Home.requestFocus();
                                break;
                            case 5:
                                text = pos5Home.getText();
                                pos5Home.setText(selectedChest);
                                pos5Home.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos6Home.requestFocus();
                                break;
                            case 6:
                                text = pos6Home.getText();
                                pos6Home.setText(selectedChest);
                                pos6Home.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                liberoHome.requestFocus();
                                break;
                            case 7:
                                text = liberoHome.getText();
                                liberoHome.setText(selectedChest);
                                liberoHome.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos1Home.requestFocus();
                                break;
                            default:
                                text = pos1Home.getText();
                                pos1Home.setText(selectedChest);
                                pos1Home.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos2Home.requestFocus();
                                break;
                        }
                        if (!text.equals("")) {
                            Player p = playerMapHome.get(text);
                            Object[] row = {p.getName(), p.getChestNo(), PlayerPosition.getNameById(p.getPosition()).getName()};
                            modelSelectedPlayerHome.addRow(row);
                        }
                    }

                }
            }
        });
    }

    public void setOppPlayers(int oppTeamId, int matchId) {
        playerMapOpp = new LinkedHashMap<>();
        modelSelectedPlayerOpp = (DefaultTableModel) tbPlayersOpp.getModel();
        List<Player> playerListOpp = td.getTeamPlayers(oppTeamId);
        List<Integer> selectedPlayersOpp = td.getMatchPlayers(matchId, oppTeamId);
        for (Player player : playerListOpp) {
            playerMapOpp.put(player.getChestNo(), player);
            if (selectedPlayersOpp.contains(player.getId())) {
                Object[] row = {player.getName(), player.getChestNo(), PlayerPosition.getNameById(player.getPosition()).getName()};
                modelSelectedPlayerOpp.addRow(row);
                if (type.equals("Update")) {
                    if (!Controller.panMatchSet.initialPositionMapOpp.isEmpty()) {
                        for (int i = 1; i <= 7; i++) {
                            if (Controller.panMatchSet.initialPositionMapOpp.get(i).getChestNo().equals(player.getChestNo())) {
                                for (int k = 0; k < tbPlayersOpp.getRowCount(); k++) {
                                    if (player.getChestNo().equals((String) tbPlayersOpp.getValueAt(k, 1))) {
                                        modelSelectedPlayerOpp.removeRow(k);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (type.equals("Update")) {
            if (!Controller.panMatchSet.initialPositionMapOpp.isEmpty()) {
                pos1Opp.setText(Controller.panMatchSet.initialPositionMapOpp.get(1).getChestNo());
                pos2Opp.setText(Controller.panMatchSet.initialPositionMapOpp.get(2).getChestNo());
                pos3Opp.setText(Controller.panMatchSet.initialPositionMapOpp.get(3).getChestNo());
                pos4Opp.setText(Controller.panMatchSet.initialPositionMapOpp.get(4).getChestNo());
                pos5Opp.setText(Controller.panMatchSet.initialPositionMapOpp.get(5).getChestNo());
                pos6Opp.setText(Controller.panMatchSet.initialPositionMapOpp.get(6).getChestNo());
                liberoOpp.setText(Controller.panMatchSet.initialPositionMapOpp.get(7).getChestNo());
            }
        }
        tbPlayersOpp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedChest = null;
                    String selectedPos = null;
                    int selectedRow = tbPlayersOpp.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {
                        selectedChest = (String) tbPlayersOpp.getValueAt(selectedRow, 1);
                        selectedPos = (String) tbPlayersOpp.getValueAt(selectedRow, 2);
                    }
                    String text = "";
                    if (selectedChest != null) {
                        modelSelectedPlayerOpp.removeRow(selectedRow);
                        switch (focusOnOpp) {
                            case 1:
                                text = pos1Opp.getText();
                                pos1Opp.setText(selectedChest);
                                pos1Opp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos2Opp.requestFocus();

                                break;
                            case 2:
                                text = pos2Opp.getText();
                                pos2Opp.setText(selectedChest);
                                pos2Opp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos3Opp.requestFocus();
                                break;
                            case 3:
                                text = pos3Opp.getText();
                                pos3Opp.setText(selectedChest);
                                pos3Opp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos4Opp.requestFocus();
                                break;
                            case 4:
                                text = pos4Opp.getText();
                                pos4Opp.setText(selectedChest);
                                pos4Opp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos5Opp.requestFocus();
                                break;
                            case 5:
                                text = pos5Opp.getText();
                                pos5Opp.setText(selectedChest);
                                pos5Opp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos6Opp.requestFocus();
                                break;
                            case 6:
                                text = pos6Opp.getText();
                                pos6Opp.setText(selectedChest);
                                pos6Opp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                liberoOpp.requestFocus();
                                break;
                            case 7:
                                text = liberoOpp.getText();
                                liberoOpp.setText(selectedChest);
                                liberoOpp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos1Opp.requestFocus();
                                break;
                            default:
                                text = pos1Opp.getText();
                                pos1Opp.setText(selectedChest);
                                pos1Opp.setForeground(PlayerPosition.getIdByName(selectedPos).getColor());
                                pos2Opp.requestFocus();
                                break;
                        }
                        if (!text.equals("")) {
                            Player p = playerMapOpp.get(text);
                            Object[] row = {p.getName(), p.getChestNo(), PlayerPosition.getNameById(p.getPosition()).getName()};
                            modelSelectedPlayerOpp.addRow(row);
                        }
                    }

                }
            }
        });
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPlayersOpp = new javax.swing.JTable(){ private static final long serialVersionUID = 1L;

            public boolean isCellEditable(int row, int column) {
                return false;
            };};
            jPanel2 = new javax.swing.JPanel();
            pos4Opp = new javax.swing.JTextField();
            pos3Opp = new javax.swing.JTextField();
            pos2Opp = new javax.swing.JTextField();
            pos5Opp = new javax.swing.JTextField();
            pos6Opp = new javax.swing.JTextField();
            pos1Opp = new javax.swing.JTextField();
            jPanel3 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();
            jPanel4 = new javax.swing.JPanel();
            jPanel5 = new javax.swing.JPanel();
            jLabel2 = new javax.swing.JLabel();
            liberoOpp = new javax.swing.JTextField();
            lblOpp = new javax.swing.JLabel();
            jPanel6 = new javax.swing.JPanel();
            jScrollPane2 = new javax.swing.JScrollPane();
            tbPlayersHome = new javax.swing.JTable(){ private static final long serialVersionUID = 1L;

                public boolean isCellEditable(int row, int column) {
                    return false;
                };};
                jPanel7 = new javax.swing.JPanel();
                pos4Home = new javax.swing.JTextField();
                pos3Home = new javax.swing.JTextField();
                pos2Home = new javax.swing.JTextField();
                pos5Home = new javax.swing.JTextField();
                pos6Home = new javax.swing.JTextField();
                pos1Home = new javax.swing.JTextField();
                jPanel8 = new javax.swing.JPanel();
                jLabel3 = new javax.swing.JLabel();
                jPanel9 = new javax.swing.JPanel();
                jPanel10 = new javax.swing.JPanel();
                jLabel4 = new javax.swing.JLabel();
                liberoHome = new javax.swing.JTextField();
                lblHome = new javax.swing.JLabel();
                jPanel11 = new javax.swing.JPanel();
                jLabel7 = new javax.swing.JLabel();
                jPanel12 = new javax.swing.JPanel();
                jLabel5 = new javax.swing.JLabel();
                txtEvaluatorName = new javax.swing.JTextField();
                jPanel13 = new javax.swing.JPanel();
                jPanel14 = new javax.swing.JPanel();
                jLabel6 = new javax.swing.JLabel();
                jPanel15 = new javax.swing.JPanel();
                jLabel8 = new javax.swing.JLabel();

                setBackground(new java.awt.Color(0, 0, 0));

                jPanel1.setBackground(new java.awt.Color(255, 255, 255));

                tbPlayersOpp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
                tbPlayersOpp.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Name", "Chest No.", "Position"
                    }
                ));
                tbPlayersOpp.setRowHeight(25);
                jScrollPane1.setViewportView(tbPlayersOpp);

                jPanel2.setBackground(new java.awt.Color(255, 255, 255));
                jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

                pos4Opp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos4Opp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos4Opp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos4Opp.setCaretColor(new java.awt.Color(255, 255, 255));
                pos4Opp.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos4OppMouseClicked(evt);
                    }
                });
                pos4Opp.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos4OppFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos4OppFocusLost(evt);
                    }
                });

                pos3Opp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos3Opp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos3Opp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos3Opp.setCaretColor(new java.awt.Color(255, 255, 255));
                pos3Opp.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos3OppMouseClicked(evt);
                    }
                });
                pos3Opp.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos3OppFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos3OppFocusLost(evt);
                    }
                });

                pos2Opp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos2Opp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos2Opp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos2Opp.setCaretColor(new java.awt.Color(255, 255, 255));
                pos2Opp.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos2OppMouseClicked(evt);
                    }
                });
                pos2Opp.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos2OppFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos2OppFocusLost(evt);
                    }
                });

                pos5Opp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos5Opp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos5Opp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos5Opp.setCaretColor(new java.awt.Color(255, 255, 255));
                pos5Opp.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos5OppMouseClicked(evt);
                    }
                });
                pos5Opp.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos5OppFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos5OppFocusLost(evt);
                    }
                });

                pos6Opp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos6Opp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos6Opp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos6Opp.setCaretColor(new java.awt.Color(255, 255, 255));
                pos6Opp.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos6OppMouseClicked(evt);
                    }
                });
                pos6Opp.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos6OppFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos6OppFocusLost(evt);
                    }
                });

                pos1Opp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos1Opp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos1Opp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos1Opp.setCaretColor(new java.awt.Color(255, 255, 255));
                pos1Opp.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos1OppMouseClicked(evt);
                    }
                });
                pos1Opp.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos1OppFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos1OppFocusLost(evt);
                    }
                });

                jPanel3.setBackground(new java.awt.Color(54, 78, 108));

                jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel1.setText("ROTATION");

                javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
                jPanel3.setLayout(jPanel3Layout);
                jPanel3Layout.setHorizontalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel3Layout.setVerticalGroup(
                    jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6))
                );

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pos5Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos6Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pos4Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos3Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos1Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos2Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel2Layout.setVerticalGroup(
                    jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos4Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos3Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos2Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos5Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos6Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos1Opp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                );

                jPanel4.setBackground(new java.awt.Color(255, 255, 255));
                jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

                jPanel5.setBackground(new java.awt.Color(54, 78, 108));
                jPanel5.setForeground(new java.awt.Color(255, 255, 255));

                jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel2.setText("LIBERO");

                javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
                jPanel5.setLayout(jPanel5Layout);
                jPanel5Layout.setHorizontalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel5Layout.setVerticalGroup(
                    jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6))
                );

                liberoOpp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                liberoOpp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                liberoOpp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                liberoOpp.setCaretColor(new java.awt.Color(255, 255, 255));
                liberoOpp.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        liberoOppMouseClicked(evt);
                    }
                });
                liberoOpp.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        liberoOppFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        liberoOppFocusLost(evt);
                    }
                });

                javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
                jPanel4.setLayout(jPanel4Layout);
                jPanel4Layout.setHorizontalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(liberoOpp, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel4Layout.setVerticalGroup(
                    jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(liberoOpp, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                );

                lblOpp.setBackground(new java.awt.Color(255, 255, 255));
                lblOpp.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                lblOpp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblOpp.setText("jLabel5");

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblOpp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblOpp, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                );

                jPanel6.setBackground(new java.awt.Color(255, 255, 255));

                tbPlayersHome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
                tbPlayersHome.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "Name", "Chest No.", "Position"
                    }
                ));
                tbPlayersHome.setRowHeight(25);
                jScrollPane2.setViewportView(tbPlayersHome);

                jPanel7.setBackground(new java.awt.Color(255, 255, 255));
                jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

                pos4Home.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos4Home.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos4Home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos4Home.setCaretColor(new java.awt.Color(255, 255, 255));
                pos4Home.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos4HomeMouseClicked(evt);
                    }
                });
                pos4Home.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos4HomeFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos4HomeFocusLost(evt);
                    }
                });

                pos3Home.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos3Home.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos3Home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos3Home.setCaretColor(new java.awt.Color(255, 255, 255));
                pos3Home.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos3HomeMouseClicked(evt);
                    }
                });
                pos3Home.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos3HomeFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos3HomeFocusLost(evt);
                    }
                });

                pos2Home.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos2Home.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos2Home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos2Home.setCaretColor(new java.awt.Color(255, 255, 255));
                pos2Home.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos2HomeMouseClicked(evt);
                    }
                });
                pos2Home.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos2HomeFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos2HomeFocusLost(evt);
                    }
                });

                pos5Home.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos5Home.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos5Home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos5Home.setCaretColor(new java.awt.Color(255, 255, 255));
                pos5Home.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos5HomeMouseClicked(evt);
                    }
                });
                pos5Home.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos5HomeFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos5HomeFocusLost(evt);
                    }
                });

                pos6Home.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos6Home.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos6Home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos6Home.setCaretColor(new java.awt.Color(255, 255, 255));
                pos6Home.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos6HomeMouseClicked(evt);
                    }
                });
                pos6Home.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos6HomeFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos6HomeFocusLost(evt);
                    }
                });

                pos1Home.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                pos1Home.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                pos1Home.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                pos1Home.setCaretColor(new java.awt.Color(255, 255, 255));
                pos1Home.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        pos1HomeMouseClicked(evt);
                    }
                });
                pos1Home.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        pos1HomeFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        pos1HomeFocusLost(evt);
                    }
                });

                jPanel8.setBackground(new java.awt.Color(54, 78, 108));

                jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel3.setText("ROTATION");

                javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
                jPanel8.setLayout(jPanel8Layout);
                jPanel8Layout.setHorizontalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel8Layout.setVerticalGroup(
                    jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6))
                );

                javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
                jPanel7.setLayout(jPanel7Layout);
                jPanel7Layout.setHorizontalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(pos5Home, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos6Home, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(pos4Home, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pos3Home, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pos1Home, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos2Home, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel7Layout.setVerticalGroup(
                    jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos4Home, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos3Home, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos2Home, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pos5Home, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos6Home, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pos1Home, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))
                );

                jPanel9.setBackground(new java.awt.Color(255, 255, 255));
                jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(54, 78, 108)));

                jPanel10.setBackground(new java.awt.Color(54, 78, 108));
                jPanel10.setForeground(new java.awt.Color(255, 255, 255));

                jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel4.setText("LIBERO");

                javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
                jPanel10.setLayout(jPanel10Layout);
                jPanel10Layout.setHorizontalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel10Layout.setVerticalGroup(
                    jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6))
                );

                liberoHome.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                liberoHome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                liberoHome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
                liberoHome.setCaretColor(new java.awt.Color(255, 255, 255));
                liberoHome.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        liberoHomeMouseClicked(evt);
                    }
                });
                liberoHome.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        liberoHomeFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        liberoHomeFocusLost(evt);
                    }
                });

                javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
                jPanel9.setLayout(jPanel9Layout);
                jPanel9Layout.setHorizontalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(liberoHome, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                jPanel9Layout.setVerticalGroup(
                    jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(liberoHome, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                );

                lblHome.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
                lblHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblHome.setText("jLabel5");

                javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
                jPanel6.setLayout(jPanel6Layout);
                jPanel6Layout.setHorizontalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lblHome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 12, Short.MAX_VALUE))))
                );
                jPanel6Layout.setVerticalGroup(
                    jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblHome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                jPanel11.setBackground(new java.awt.Color(57, 74, 108));

                jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                jLabel7.setText("Set Rotation Order");

                javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
                jPanel11.setLayout(jPanel11Layout);
                jPanel11Layout.setHorizontalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel11Layout.setVerticalGroup(
                    jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addContainerGap())
                );

                jPanel12.setBackground(new java.awt.Color(255, 255, 255));

                jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
                jLabel5.setText("Name Of the Evaluator : ");

                javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
                jPanel12.setLayout(jPanel12Layout);
                jPanel12Layout.setHorizontalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEvaluatorName, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel12Layout.setVerticalGroup(
                    jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEvaluatorName, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                        .addContainerGap())
                );

                jPanel13.setBackground(new java.awt.Color(255, 255, 255));

                jPanel14.setBackground(new java.awt.Color(57, 74, 108));
                jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
                jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jPanel14MouseClicked(evt);
                    }
                });

                jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel6.setText("SAVE");
                jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel6MouseClicked(evt);
                    }
                });
                jLabel6.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyPressed(java.awt.event.KeyEvent evt) {
                        jLabel6KeyPressed(evt);
                    }
                });

                javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
                jPanel14.setLayout(jPanel14Layout);
                jPanel14Layout.setHorizontalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                );
                jPanel14Layout.setVerticalGroup(
                    jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addGap(0, 0, 0))
                );

                jPanel15.setBackground(new java.awt.Color(57, 74, 108));
                jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

                jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
                jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                jLabel8.setText("CANCEL");
                jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        jLabel8MouseClicked(evt);
                    }
                });

                javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
                jPanel15.setLayout(jPanel15Layout);
                jPanel15Layout.setHorizontalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                );
                jPanel15Layout.setVerticalGroup(
                    jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
                jPanel13.setLayout(jPanel13Layout);
                jPanel13Layout.setHorizontalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(337, 337, 337)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                jPanel13Layout.setVerticalGroup(
                    jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13))
                );

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                );
            }// </editor-fold>//GEN-END:initComponents

    private void pos4OppFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos4OppFocusGained
        focusOnOpp = 4;
        pos4Opp.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos4OppFocusGained

    private void pos4OppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos4OppFocusLost
        pos4Opp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos4OppFocusLost

    private void pos3OppFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos3OppFocusGained
        focusOnOpp = 3;
        pos3Opp.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos3OppFocusGained

    private void pos3OppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos3OppFocusLost
        pos3Opp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos3OppFocusLost

    private void pos2OppFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos2OppFocusGained
        focusOnOpp = 2;
        pos2Opp.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos2OppFocusGained

    private void pos2OppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos2OppFocusLost
        pos2Opp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos2OppFocusLost

    private void pos5OppFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos5OppFocusGained
        focusOnOpp = 5;
        pos5Opp.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos5OppFocusGained

    private void pos5OppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos5OppFocusLost
        pos5Opp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos5OppFocusLost

    private void pos6OppFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos6OppFocusGained
        focusOnOpp = 6;
        pos6Opp.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos6OppFocusGained

    private void pos6OppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos6OppFocusLost
        pos6Opp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos6OppFocusLost

    private void pos1OppFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos1OppFocusGained
        focusOnOpp = 1;
        pos1Opp.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos1OppFocusGained

    private void pos1OppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos1OppFocusLost
        pos1Opp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos1OppFocusLost

    private void liberoOppFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_liberoOppFocusGained
        focusOnOpp = 7;
        liberoOpp.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_liberoOppFocusGained

    private void liberoOppFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_liberoOppFocusLost
        liberoOpp.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_liberoOppFocusLost

    private void pos4HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos4HomeFocusGained
        focusOnHome = 4;
        pos4Home.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos4HomeFocusGained

    private void pos4HomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos4HomeFocusLost
        pos4Home.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos4HomeFocusLost

    private void pos3HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos3HomeFocusGained
        focusOnHome = 3;
        pos3Home.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos3HomeFocusGained

    private void pos3HomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos3HomeFocusLost
        pos3Home.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos3HomeFocusLost

    private void pos2HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos2HomeFocusGained
        focusOnHome = 2;
        pos2Home.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos2HomeFocusGained

    private void pos2HomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos2HomeFocusLost
        pos2Home.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos2HomeFocusLost

    private void pos5HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos5HomeFocusGained
        focusOnHome = 5;
        pos5Home.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos5HomeFocusGained

    private void pos5HomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos5HomeFocusLost
        pos5Home.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos5HomeFocusLost

    private void pos6HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos6HomeFocusGained
        focusOnHome = 6;
        pos6Home.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos6HomeFocusGained

    private void pos6HomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos6HomeFocusLost
        pos6Home.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos6HomeFocusLost

    private void pos1HomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos1HomeFocusGained
        focusOnHome = 1;
        pos1Home.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_pos1HomeFocusGained

    private void pos1HomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pos1HomeFocusLost
        pos1Home.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_pos1HomeFocusLost

    private void liberoHomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_liberoHomeFocusGained
        focusOnHome = 7;
        liberoHome.setBorder(BorderFactory.createLineBorder(cG));
    }//GEN-LAST:event_liberoHomeFocusGained

    public void removeHomePlayerFromPosition(java.awt.event.MouseEvent evt) {
        JTextField txt = (JTextField) evt.getSource();
        if (evt.getClickCount() == 2) {
            Player p = playerMapHome.get(txt.getText());
            txt.setText("");
            Object[] row = {p.getName(), p.getChestNo(), PlayerPosition.getNameById(p.getPosition()).getName()};
            modelSelectedPlayerHome.addRow(row);
        }
    }

    public void removeOppPlayerFromPosition(java.awt.event.MouseEvent evt) {
        JTextField txt = (JTextField) evt.getSource();
        if (evt.getClickCount() == 2) {
            Player p = playerMapOpp.get(txt.getText());
            txt.setText("");
            Object[] row = {p.getName(), p.getChestNo(), PlayerPosition.getNameById(p.getPosition()).getName()};
            modelSelectedPlayerOpp.addRow(row);
        }
    }

    private void liberoHomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_liberoHomeFocusLost
        liberoHome.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }//GEN-LAST:event_liberoHomeFocusLost

    private void pos1HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos1HomeMouseClicked
        removeHomePlayerFromPosition(evt);
    }//GEN-LAST:event_pos1HomeMouseClicked

    private void pos2HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos2HomeMouseClicked
        removeHomePlayerFromPosition(evt);
    }//GEN-LAST:event_pos2HomeMouseClicked

    private void pos3HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos3HomeMouseClicked
        removeHomePlayerFromPosition(evt);
    }//GEN-LAST:event_pos3HomeMouseClicked

    private void pos4HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos4HomeMouseClicked
        removeHomePlayerFromPosition(evt);
    }//GEN-LAST:event_pos4HomeMouseClicked

    private void pos5HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos5HomeMouseClicked
        removeHomePlayerFromPosition(evt);
    }//GEN-LAST:event_pos5HomeMouseClicked

    private void pos6HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos6HomeMouseClicked
        removeHomePlayerFromPosition(evt);
    }//GEN-LAST:event_pos6HomeMouseClicked

    private void liberoHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_liberoHomeMouseClicked
        removeHomePlayerFromPosition(evt);
    }//GEN-LAST:event_liberoHomeMouseClicked

    private void pos1OppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos1OppMouseClicked
        removeOppPlayerFromPosition(evt);
    }//GEN-LAST:event_pos1OppMouseClicked

    private void pos2OppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos2OppMouseClicked
        removeOppPlayerFromPosition(evt);
    }//GEN-LAST:event_pos2OppMouseClicked

    private void pos3OppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos3OppMouseClicked
        removeOppPlayerFromPosition(evt);
    }//GEN-LAST:event_pos3OppMouseClicked

    private void pos4OppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos4OppMouseClicked
        removeOppPlayerFromPosition(evt);
    }//GEN-LAST:event_pos4OppMouseClicked

    private void pos5OppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos5OppMouseClicked
        removeOppPlayerFromPosition(evt);
    }//GEN-LAST:event_pos5OppMouseClicked

    private void pos6OppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pos6OppMouseClicked
        removeOppPlayerFromPosition(evt);
    }//GEN-LAST:event_pos6OppMouseClicked

    private void liberoOppMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_liberoOppMouseClicked
        removeOppPlayerFromPosition(evt);
    }//GEN-LAST:event_liberoOppMouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        initialHomePositionMap = new LinkedHashMap<>();
        initialHomePositionMap.put(1, playerMapHome.get(pos1Home.getText()));
        initialHomePositionMap.put(2, playerMapHome.get(pos2Home.getText()));
        initialHomePositionMap.put(3, playerMapHome.get(pos3Home.getText()));
        initialHomePositionMap.put(4, playerMapHome.get(pos4Home.getText()));
        initialHomePositionMap.put(5, playerMapHome.get(pos5Home.getText()));
        initialHomePositionMap.put(6, playerMapHome.get(pos6Home.getText()));
        initialHomePositionMap.put(7, playerMapHome.get(liberoHome.getText()));

        initialOppPositionMap = new LinkedHashMap<>();
        initialOppPositionMap.put(1, playerMapOpp.get(pos1Opp.getText()));
        initialOppPositionMap.put(2, playerMapOpp.get(pos2Opp.getText()));
        initialOppPositionMap.put(3, playerMapOpp.get(pos3Opp.getText()));
        initialOppPositionMap.put(4, playerMapOpp.get(pos4Opp.getText()));
        initialOppPositionMap.put(5, playerMapOpp.get(pos5Opp.getText()));
        initialOppPositionMap.put(6, playerMapOpp.get(pos6Opp.getText()));
        initialOppPositionMap.put(7, playerMapOpp.get(liberoOpp.getText()));

        MatchSet ms = new MatchSet();
        ms.setMatchEvaluationTeamId(matchEvaluationTeamId);
        ms.setSetNo(set);
        for (Map.Entry<Integer, Player> entry : initialHomePositionMap.entrySet()) {
            SetRotationOrder sro = new SetRotationOrder();
            sro.setPosition(entry.getKey());
            sro.setPlayerId(entry.getValue().getId());
            ms.getRotationOrder().add(sro);
        }

        for (Map.Entry<Integer, Player> entry : initialOppPositionMap.entrySet()) {
            SetRotationOrder sro = new SetRotationOrder();
            sro.setPosition(entry.getKey());
            sro.setPlayerId(entry.getValue().getId());
            ms.getRotationOrderOpp().add(sro);
        }

        ms.setEvaluator(txtEvaluatorName.getText());
        ms.setStart_time("00:00");
        ms.setEnd_time("00:00");
        ms.setDate(CommonUtil.getDate());

        int id = 0;
        if (type.equals("Update")) {
            id = matchDao.updateRotationOrder(ms);
        } else {
            id = matchDao.insertMatchSet(ms);
        }
        if (id == 0) {
            JOptionPane.showMessageDialog(this, "Failed to Save");
        } else {
            JOptionPane.showMessageDialog(this, "Saved Successfully");
            if (type.equals("Update")) {
                Controller.panMatchSet.setRotationDialog.close();
                Controller.panMatchSet.setValues();
            } else {
                 Controller.panMatchEvaluationHome.objRotationOrder.close();
             DialogPanEvaluation   obj = new DialogPanEvaluation();
            obj.setSetFields(set, this.matchId, this.homeTeamId, this.oppTeamId, 1, matchEvaluationTeamId);
            obj.init();
            obj.show();
               
            }
        }

    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        Controller.panMatchEvaluationHome.objRotationOrder.close();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jLabel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel6KeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblOpp;
    private javax.swing.JTextField liberoHome;
    private javax.swing.JTextField liberoOpp;
    private javax.swing.JTextField pos1Home;
    private javax.swing.JTextField pos1Opp;
    private javax.swing.JTextField pos2Home;
    private javax.swing.JTextField pos2Opp;
    private javax.swing.JTextField pos3Home;
    private javax.swing.JTextField pos3Opp;
    private javax.swing.JTextField pos4Home;
    private javax.swing.JTextField pos4Opp;
    private javax.swing.JTextField pos5Home;
    private javax.swing.JTextField pos5Opp;
    private javax.swing.JTextField pos6Home;
    private javax.swing.JTextField pos6Opp;
    private javax.swing.JTable tbPlayersHome;
    private javax.swing.JTable tbPlayersOpp;
    private javax.swing.JTextField txtEvaluatorName;
    // End of variables declaration//GEN-END:variables
}
