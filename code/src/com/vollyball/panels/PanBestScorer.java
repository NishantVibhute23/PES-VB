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
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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

    /**
     * Creates new form PanBestScorer
     */
    public PanBestScorer(final CompetitionBean cb, List<Player> playerList) {
        initComponents();
        createTable();
        this.playerList = playerList;
        this.cb = cb;
        cmbPlayer.addItem("All");
        playerNameMap.put("All", null);
        for (Player p : playerList) {
            cmbPlayer.addItem(p.getName());
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
                    tbReport.clearSelection();
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
                        } else if (selectedCol == 12) {
                            id = (int) tbReport.getValueAt(selectedRow, 0);
                            Controller.createPlayerDialog = new CreatePlayerDialog();
                            Controller.createPlayerDialog.setValues(id);
                            Controller.createPlayerDialog.init();
                            Controller.createPlayerDialog.show();

                        } else if (selectedCol == 13) {
                            id = (int) tbReport.getValueAt(selectedRow, 0);
                            int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure ?", "Warning", dialogButton);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                int count = td.deletePlayer(id);
                                if (count != 0) {
                                    JOptionPane.showMessageDialog(null, "Player Deleted Successfully");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Not Able to Deleted Player");
                                }
                            }
                        }
                    }
                }
            }
        });

    }

    public void setRow(Player player) {
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
                Object[] row = {i + 1, p.getPlayerName(), p.getTeamName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc(), new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            }
        } else {

            PlayerScores playerScore = reportDao.getPlayerScores(cb.getId(), player, 0);
            playerScoresList.add(playerScore);

            int i = 0;
            for (PlayerScores p : playerScoresList) {
                Object[] row = {i + 1, p.getPlayerName(), p.getTeamName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc(), new JPanel(), new JPanel()};
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
                new Object[]{"SNo.", "Player Name", "<html>Team<br> Name</html>", "<html>Matches<br> Played</html>", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total", "View", "Edit", "Delete"});

        tbReport = new JTable(dm) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 14));
        TableColumnModel cm = tbReport.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));
        g_name.add(cm.getColumn(9));
        g_name.add(cm.getColumn(10));

        GroupableTableHeader header = (GroupableTableHeader) tbReport.getTableHeader();
        header.addColumnGroup(g_name);

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
        tbReport.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);

        ViewButtonRenderer viewButtonRenderer = new ViewButtonRenderer();
        tbReport.getColumnModel().getColumn(11).setCellRenderer(viewButtonRenderer);

        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbReport.getColumnModel().getColumn(12).setCellRenderer(editButtonRenderer);

        DeleteButtonRenderer deleteButtonRenderer = new DeleteButtonRenderer();
        tbReport.getColumnModel().getColumn(13).setCellRenderer(deleteButtonRenderer);

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        tbReport.setRowHeight(30);
        resizeColumns();
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
        lblReportHeading = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblSearch = new javax.swing.JLabel();
        cmbPlayer = new javax.swing.JComboBox();
        panReport = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        PlayerLabel1 = new javax.swing.JLabel();

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lblReportHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblReportHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReportHeading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReportHeading.setText("BEST PLAYER");

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSearch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSearch.setForeground(new java.awt.Color(255, 255, 255));
        lblSearch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSearch.setText("SEARCH");
        lblSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSearchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        cmbPlayer.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(294, Short.MAX_VALUE)
                .addComponent(lblReportHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(cmbPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbPlayer)
                    .addComponent(lblReportHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panReport.setLayout(new java.awt.BorderLayout());

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(57, 74, 108));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        PlayerLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        PlayerLabel1.setForeground(new java.awt.Color(255, 255, 255));
        PlayerLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerLabel1.setText("New Player");
        PlayerLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PlayerLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PlayerLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PlayerLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panSkillReportsLayout = new javax.swing.GroupLayout(panSkillReports);
        panSkillReports.setLayout(panSkillReportsLayout);
        panSkillReportsLayout.setHorizontalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 956, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panSkillReportsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panSkillReportsLayout.setVerticalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 976, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panSkillReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSearchMouseClicked
        // TODO add your handling code here:
        setRow(playerNameMap.get(cmbPlayer.getSelectedItem()));
    }//GEN-LAST:event_lblSearchMouseClicked

    private void PlayerLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PlayerLabel1MouseClicked
        // TODO add your handling code here:
        Controller.createPlayerDialog = new CreatePlayerDialog();
        Controller.createPlayerDialog.init();
        Controller.createPlayerDialog.show();

    }//GEN-LAST:event_PlayerLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel PlayerLabel1;
    private javax.swing.JComboBox cmbPlayer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    public javax.swing.JLabel lblReportHeading;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JPanel panReport;
    private javax.swing.JPanel panSkillReports;
    // End of variables declaration//GEN-END:variables
}
