/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerScores;
import com.vollyball.controller.Controller;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.CreatePlayerDialog;
import com.vollyball.dialog.DialogAllScoreChart;
import com.vollyball.enums.PlayerPosition;
import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.DeleteButtonRenderer;
import com.vollyball.renderer.EditButtonRenderer;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.TableHeaderRenderer;
import com.vollyball.renderer.ViewButtonRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nishant.vibhute
 */
public class PanBestScorer extends javax.swing.JPanel {

    DefaultTableModel model;
    JTable tbReport;
    TeamDao td = new TeamDao();
    DefaultTableModel dm;
    Map<String, Player> playerNameMap = new HashMap<String, Player>();

    ReportDao reportDao = new ReportDao();
    List<Player> playerList;
    CompetitionBean cb;
    TableRowSorter<TableModel> sorter;
    LinkedHashMap<String, Integer> teamchestPlayerMap = new LinkedHashMap<>();

    /**
     * Creates new form PanBestScorer
     */
    public PanBestScorer(final CompetitionBean cb, List<Player> playerList) {
        initComponents();
        createTable();
        this.playerList = playerList;
        this.cb = cb;
//        cmbPlayer.addItem("All");
//        playerNameMap.put("All", null);
        for (Player p : playerList) {
//            cmbPlayer.addItem(p.getName());
            playerNameMap.put(p.getName(), p);

        }
        setRow(null);
        tbReport.setSelectionBackground(Color.WHITE);
        tbReport.setSelectionForeground(Color.BLACK);
        tbReport.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!e.getValueIsAdjusting()) {

                    String selectedName = null, teamName = "";

                    int matchesPlayed = 0;
                    int id = 0;
                    int selectedRow = tbReport.getSelectedRow();
                    int selectedCol = tbReport.getSelectedColumn();
//                    tbReport.clearSelection();

//                    if (selectedRow < 0) {
//
//                    } else {
//                        int modelRow= tbReport.convertRowIndexToModel(selectedRow);
//                    }
                    if (selectedRow >= 0) {
                        if (selectedCol == 11) {
                            for (int i = 0; i <= selectedRow; i++) {
                                selectedName = (String) tbReport.getValueAt(selectedRow, 1);
                                teamName = (String) tbReport.getValueAt(selectedRow, 2);
                                matchesPlayed = (int) tbReport.getValueAt(selectedRow, 3);
                            }
                            if (selectedName != null) {
                                DialogAllScoreChart createDialogPanMatchWiseReport = new DialogAllScoreChart();
                                createDialogPanMatchWiseReport.init(cb.getId(), playerNameMap.get(selectedName).getId(), selectedName, matchesPlayed, teamName, 0, 0);
                                createDialogPanMatchWiseReport.show();
                            }
                        } else if (selectedCol == 6) {
                            String key = tbReport.getValueAt(selectedRow, 3) + "-" + tbReport.getValueAt(selectedRow, 2);
                            id = teamchestPlayerMap.get(key);
                            Controller.createPlayerDialog = new CreatePlayerDialog();
                            Controller.createPlayerDialog.setValues(id);
                            Controller.createPlayerDialog.init();
                            Controller.createPlayerDialog.show();

                        } else if (selectedCol == 7) {
                            String key = tbReport.getValueAt(selectedRow, 3) + "-" + tbReport.getValueAt(selectedRow, 2);
                            id = teamchestPlayerMap.get(key);
                            int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure want to delete \""+tbReport.getValueAt(selectedRow,1)+"\"?", "Warning", dialogButton);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                int count = td.deletePlayer(id);
//                                int count = 0;
                                if (count != 0) {
                                    setRow(null);
                                    JOptionPane.showMessageDialog(null, "Player Deleted Successfully");
                                                                    } else {
                                    JOptionPane.showMessageDialog(null, "Not Able to Delete Player");
                                }
                            }
                        }
                        tbReport.clearSelection();
                    }
                }
            }
        });

    }
    
    public void setRow(Player player) {
        for (int i = dm.getRowCount() - 1; i >= 0; i--) {
            dm.removeRow(i);
        }

        if (player == null) {
            int i = 1;
            List<Player> playerList = td.getAlPlayers(cb.getId());
            for (Player p : playerList) {
                teamchestPlayerMap.put(p.getTeamName() + "-" + p.getChestNo(), p.getId());
                Object[] row = {i, p.getName(),p.getChestNo(), p.getTeamName(), PlayerPosition.getNameById(p.getPosition()), p.isCaptain()==true?"Captain":"", new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            }
        } else {
            int i = 0;
            Object[] row = {i, player.getName(),player.getChestNo(), player.getTeamName(), PlayerPosition.getNameById(player.getPosition()), player.isCaptain()==true?"Captain":"", new JPanel(), new JPanel()};
            dm.addRow(row);
            i++;            
        }
    }

    public void setRowForScore(Player player) {
        List<PlayerScores> playerScoresList = new ArrayList<>();

        for (int i = dm.getRowCount() - 1; i >= 0; i--) {
            dm.removeRow(i);
        }

        if (player == null) {
            for (Player p : playerList) {
                PlayerScores playerScore = reportDao.getPlayerScores(cb.getId(), p, 0);
                playerScoresList.add(playerScore);
            }

            Collections.sort(playerScoresList, new Comparator<PlayerScores>() {
                @Override
                public int compare(PlayerScores c1, PlayerScores c2) {

                    return Double.compare(c2.getAttemptRate(), c1.getAttemptRate());
                }
            });

            int i = 0;
            for (PlayerScores p : playerScoresList) {
                teamchestPlayerMap.put(p.getTeamName() + "-" + p.getChestNo(), p.getId());
                Object[] row = {p.getChestNo(), p.getPlayerName(), p.getTeamName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc(), new JPanel(), new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            }
        } else {

            PlayerScores playerScore = reportDao.getPlayerScores(cb.getId(), player, 0);
            playerScoresList.add(playerScore);

            int i = 0;
            for (PlayerScores p : playerScoresList) {
                Object[] row = {p.getChestNo(), p.getPlayerName(), p.getTeamName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc(), new JPanel(), new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            }

        }
    }

    public void createTable() {
        dm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        dm.setDataVector(new Object[][]{},
                new Object[]{"Sr. No.", "Player Name","Chest No.", "Team Name","Position","Captain" , "Edit", "Delete"});

        tbReport = new JTable(dm) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        sorter = new TableRowSorter<TableModel>(tbReport.getModel());
        tbReport.setRowSorter(sorter);
        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 14));
        JScrollPane scroll = new JScrollPane(tbReport);

        Color heading = new Color(204, 204, 204);
        model = (DefaultTableModel) tbReport.getModel();
        JTableHeader tbheader = tbReport.getTableHeader();

        tbheader.setOpaque(false);
        tbheader.setPreferredSize(new Dimension(100, 45));
        tbheader.setDefaultRenderer(new TableHeaderRenderer(tbReport));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbReport.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(0).setWidth(12);

        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
   

        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbReport.getColumnModel().getColumn(6).setCellRenderer(editButtonRenderer);

        DeleteButtonRenderer deleteButtonRenderer = new DeleteButtonRenderer();
        tbReport.getColumnModel().getColumn(7).setCellRenderer(deleteButtonRenderer);

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        tbReport.setRowHeight(35);
//        resizeColumns();
        panReport.add(scroll, BorderLayout.CENTER);
    }
    float[] columnWidthPercentage = {5.0f, 23.0f, 9.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

    private void resizeColumns() {
        int tW = tbReport.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbReport.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    private void newFilter() {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(txtFilter.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panSkillReports = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txtFilter = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        PlayerLabel1 = new javax.swing.JLabel();
        panReport = new javax.swing.JPanel();

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txtFilter.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtFilter.setForeground(new java.awt.Color(153, 153, 153));
        txtFilter.setText("Search Player");
        txtFilter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFilterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFilterFocusLost(evt);
            }
        });
        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFilterKeyTyped(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(57, 74, 108));
        jLabel1.setText("Players");

        PlayerLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        PlayerLabel1.setForeground(new java.awt.Color(255, 255, 255));
        PlayerLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/button (1).png"))); // NOI18N
        PlayerLabel1.setToolTipText("New Player");
        PlayerLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addGap(612, 612, 612)
                .addComponent(PlayerLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(PlayerLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFilter))))
                .addGap(8, 8, 8))
        );

        panReport.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panSkillReportsLayout = new javax.swing.GroupLayout(panSkillReports);
        panSkillReports.setLayout(panSkillReportsLayout);
        panSkillReportsLayout.setHorizontalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addGroup(panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panSkillReportsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panSkillReportsLayout.setVerticalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1075, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panSkillReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 630, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void PlayerLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayerLabel1MouseClicked
        // TODO add your handling code here:
        Controller.createPlayerDialog = new CreatePlayerDialog();
        Controller.createPlayerDialog.init();
        Controller.createPlayerDialog.show();

    }//GEN-LAST:event_PlayerLabel1MouseClicked

    private void txtFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyTyped
        // TODO add your handling code here:
        newFilter();
    }//GEN-LAST:event_txtFilterKeyTyped

    private void txtFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterFocusGained
        // TODO add your handling code here:
        
            txtFilter.setText("");
            txtFilter.setForeground(Color.BLACK);
        
    }//GEN-LAST:event_txtFilterFocusGained

    private void txtFilterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterFocusLost
        // TODO add your handling code here:
        if (txtFilter.getText().isEmpty()) {
            txtFilter.setForeground(Color.GRAY);
            txtFilter.setText("Search Player");
        }
        
        
    }//GEN-LAST:event_txtFilterFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PlayerLabel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel panReport;
    private javax.swing.JPanel panSkillReports;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
