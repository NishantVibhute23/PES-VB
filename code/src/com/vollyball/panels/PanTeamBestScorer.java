/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.PlayerScores;
import com.vollyball.bean.Team;
import com.vollyball.controller.Controller;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.CreateTeamDialog;
import com.vollyball.dialog.DialogTamDetail;
import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.EditButtonRenderer;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.ReportButtonRenderer;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
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
public class PanTeamBestScorer extends javax.swing.JPanel {

    DefaultTableModel model;
    JTable tbReport;
    TeamDao td = new TeamDao();
    DefaultTableModel dm;
    Map<String, Team> playerTeamMap = new HashMap<String, Team>();

    ReportDao reportDao = new ReportDao();
    List<Team> teamList;
    CompetitionBean cb;
    TableRowSorter<TableModel> sorter;

    /**
     * Creates new form PanTeamBestScorer
     */
    public PanTeamBestScorer(final CompetitionBean cb, List<Team> teamList) {
        initComponents();
        createTable();
        this.teamList = teamList;
        this.cb = cb;
//        cmbPlayer.addItem("All");
//        playerTeamMap.put("All", null);
        for (Team t : teamList) {
            playerTeamMap.put(t.getName(), t);
//            cmbPlayer.addItem(t.getName());

        }
        setRow(null);

        tbReport.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    String selectedName = null, teamName = "";

                    int matchesPlayed = 0;

                    int selectedRow = tbReport.getSelectedRow();
                    int selectedCol = tbReport.getSelectedColumn();
                    if (selectedRow >= 0) {
                        for (int i = 0; i <= selectedRow; i++) {
                            selectedName = (String) tbReport.getValueAt(selectedRow, 1);
                        }
                        if (selectedName != null) {
                            if (selectedCol == 10) {
                                DialogTamDetail createDialogPanMatchWiseReport = new DialogTamDetail();
                                createDialogPanMatchWiseReport.init(cb.getId(), playerTeamMap.get(selectedName).getId());
                                tbReport.clearSelection();
                                createDialogPanMatchWiseReport.show();
                            }
                            if (selectedCol == 11) {
                                Controller.teamDialog = new CreateTeamDialog();
                                Controller.teamDialog.setValues(cb.getId(), playerTeamMap.get(selectedName).getId());
                                Controller.teamDialog.init();
                                Controller.teamDialog.show();
                            }
                            tbReport.clearSelection();
                        }
                    }
                }
            }
        });
    }

    public void setRow() {

    }

    public void setRowForScore(Team Team) {
        List<PlayerScores> playerScoresList = new ArrayList<>();

        for (int i = dm.getRowCount() - 1; i >= 0; i--) {
            dm.removeRow(i);
        }

        if (Team == null) {
            for (Team p : teamList) {
                PlayerScores playerScore = reportDao.getTeamScores(p);
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
                Object[] row = {i + 1, p.getPlayerName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc(), new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            }
        } else {

            PlayerScores playerScore = reportDao.getTeamScores(Team);
            playerScoresList.add(playerScore);

            int i = 0;
            for (PlayerScores p : playerScoresList) {
                Object[] row = {i + 1, p.getPlayerName(), p.getMatchesPlayed(), p.getServiceRatePerc(), p.getAttackRatePerc(), p.getBlockRatePerc(), p.getSetRatePerc(), p.getReceptionRatePerc(), p.getDefenceRatePerc(), p.getAttemptRatePerc(), new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            }

        }
        validate();
        repaint();
    }

    public void createTable() {
        dm = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };
//        model = new DefaultTableModel() {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                //all cells false
//                return false;
//            }
//        };

        dm.setDataVector(new Object[][]{},
                new Object[]{"SR No.", "Team Name", "<html>Matches<br> Played</html>", "Service", "Attack", "Block", "Set", "Reception", "Defend", "Total", "Report", "Action"});

        tbReport = new JTable(dm) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        sorter = new TableRowSorter<TableModel>(tbReport.getModel());
        tbReport.setRowSorter(sorter);

        tbReport.setFont(new java.awt.Font("Times New Roman", 0, 14));
        TableColumnModel cm = tbReport.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("SuccessRate");
        g_name.add(cm.getColumn(3));
        g_name.add(cm.getColumn(4));
        g_name.add(cm.getColumn(5));
        g_name.add(cm.getColumn(6));
        g_name.add(cm.getColumn(7));
        g_name.add(cm.getColumn(8));
        g_name.add(cm.getColumn(9));

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
        tbReport.getColumnModel().getColumn(0).setWidth(10);

        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);
        ReportButtonRenderer reportButtonRenderer = new ReportButtonRenderer();
        tbReport.getColumnModel().getColumn(10).setCellRenderer(reportButtonRenderer);
        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbReport.getColumnModel().getColumn(11).setCellRenderer(editButtonRenderer);

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        tbReport.setRowHeight(30);
        tbReport.setSelectionBackground(Color.WHITE);
        tbReport.setSelectionForeground(Color.BLACK);

        MouseMotionAdapter mma;
        mma = new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                if (tbReport.columnAtPoint(p) == 10 || tbReport.columnAtPoint(p) == 11) {
                    tbReport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    tbReport.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        };

        // Register the previous MouseMotionAdapter object as a listener
        // to mouse movement events originating from the table.
        tbReport.addMouseMotionListener(mma);

        resizeColumns();
        panReport.add(scroll, BorderLayout.CENTER);
    }
    float[] columnWidthPercentage = {5.0f, 23.0f, 9.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

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
        lblReportHeading = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNewTeam = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        panReport = new javax.swing.JPanel();

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lblReportHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblReportHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReportHeading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReportHeading.setText("TEAM SEARCH");

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNewTeam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam.setText("New Team");
        lblNewTeam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNewTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewTeamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFilterKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, Short.MAX_VALUE)
                .addComponent(lblReportHeading)
                .addGap(34, 34, 34)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblReportHeading, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFilter))
                .addGap(5, 5, 5))
        );

        panReport.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panSkillReportsLayout = new javax.swing.GroupLayout(panSkillReports);
        panSkillReports.setLayout(panSkillReportsLayout);
        panSkillReportsLayout.setHorizontalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panSkillReportsLayout.setVerticalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 874, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panSkillReports, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(0, 0, 0)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblNewTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewTeamMouseClicked
        // TODO add your handling code here:
        Controller.teamDialog = new CreateTeamDialog();
        Controller.teamDialog.init();
        Controller.teamDialog.show();

    }//GEN-LAST:event_lblNewTeamMouseClicked

    private void txtFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyTyped
        // TODO add your handling code here:
        newFilter();
    }//GEN-LAST:event_txtFilterKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblNewTeam;
    public javax.swing.JLabel lblReportHeading;
    private javax.swing.JPanel panReport;
    private javax.swing.JPanel panSkillReports;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
