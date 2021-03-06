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
import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.DeleteButtonRenderer;
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
import static java.time.zone.ZoneRulesProvider.refresh;
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
//                            if (selectedCol == 10) {
//                                DialogTamDetail createDialogPanMatchWiseReport = new DialogTamDetail();
//                                createDialogPanMatchWiseReport.init(cb.getId(), playerTeamMap.get(selectedName).getId());
//                                tbReport.clearSelection();
//                                createDialogPanMatchWiseReport.show();
//                            }
                            if (selectedCol == 8) {
                                Controller.teamDialog = new CreateTeamDialog();
                                Controller.teamDialog.setValues(cb.getId(), playerTeamMap.get(selectedName).getId());
                                Controller.teamDialog.init();
                                Controller.teamDialog.show();
                            }
                            if (selectedCol == 9) {
                            int id = (int) playerTeamMap.get(selectedName).getId();
                            
              
                             int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure want to delete \""+selectedName+"\" ?", "Warning", dialogButton);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                int count = td.deleteTeam(id);
                                                       if (count != 0) {
                                                           setRow(null);
                                    JOptionPane.showMessageDialog(null, "Team Deleted Successfully");
                                    
                                    
                                } else {
                                    JOptionPane.showMessageDialog(null, "Not Able to Delete Team");
                                }
                            }
                            }
                            tbReport.clearSelection();
                        }
                    }
                }
            }
        });
    }

    public void setRow(Team Team) {
       List<Team> teamList = td.getTeams(cb.getId());
        
        for (int i = dm.getRowCount() - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        
        if (Team == null) {
                      

            int i = 0;
            for (Team p : teamList) {
                
                Team t = td.getteamDetail(p.getId());
                Object[] row = {i + 1, t.getName(), t.getShortCode(), t.getCoach(), t.getTrainer(), t.getAsstCoach(), t.getAnalyzer(), t.getMedicalOffice(), new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            }
        } else {

            Team t = td.getteamDetail(Team.getId());
            int i = 0;
          
                Object[] row = {i + 1, t.getName(), t.getShortCode(), t.getCoach(), t.getTrainer(), t.getAsstCoach(), t.getAnalyzer(), t.getMedicalOffice(), new JPanel(), new JPanel()};
                dm.addRow(row);
                i++;
            

        }
        validate();
        repaint();

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
                new Object[]{"SR No.", "Team Name","Shortcut Code","Coach","Trainer","Asst. Coach","Analyzer","Medical Officer","Edit","Delete"});

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
        tbReport.getColumnModel().getColumn(0).setWidth(10);

        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbReport.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        

        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbReport.getColumnModel().getColumn(8).setCellRenderer(editButtonRenderer);
        DeleteButtonRenderer deleteButtonRenderer = new DeleteButtonRenderer();
        tbReport.getColumnModel().getColumn(9).setCellRenderer(deleteButtonRenderer);

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);

        tbReport.setRowHeight(35);
        tbReport.setSelectionBackground(Color.WHITE);
        tbReport.setSelectionForeground(Color.BLACK);

        MouseMotionAdapter mma;
        mma = new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                if (tbReport.columnAtPoint(p) == 8 ) {
                    tbReport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    tbReport.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        };

        // Register the previous MouseMotionAdapter object as a listener
        // to mouse movement events originating from the table.
        tbReport.addMouseMotionListener(mma);

//        resizeColumns();
        panReport.add(scroll, BorderLayout.CENTER);
    }
    
    
    
    public void createTableforScores() {
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
    float[] columnWidthPercentage = {5.0f, 23.0f, 9.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f,8.0f};

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
        lblNewTeam = new javax.swing.JLabel();
        panReport = new javax.swing.JPanel();

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txtFilter.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtFilter.setForeground(new java.awt.Color(153, 153, 153));
        txtFilter.setText("Search Team");
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
        jLabel1.setText("Teams");

        lblNewTeam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/button (1).png"))); // NOI18N
        lblNewTeam.setToolTipText("New Team");
        lblNewTeam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNewTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewTeamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 518, Short.MAX_VALUE)
                .addComponent(lblNewTeam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblNewTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFilter, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(8, 8, 8))
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
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
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
            .addGap(0, 301, Short.MAX_VALUE)
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

    private void txtFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterFocusGained
        // TODO add your handling code here:
         txtFilter.setText("");
            txtFilter.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtFilterFocusGained

    private void txtFilterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFilterFocusLost
        // TODO add your handling code here:
         if (txtFilter.getText().isEmpty()) {
            txtFilter.setForeground(Color.GRAY);
            txtFilter.setText("Search Team");
        }
    }//GEN-LAST:event_txtFilterFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JLabel lblNewTeam;
    private javax.swing.JPanel panReport;
    private javax.swing.JPanel panSkillReports;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
