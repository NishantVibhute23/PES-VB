/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.MatchBean;
import com.vollyball.bean.Team;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.CreateMatchDialog;
import com.vollyball.enums.EvaluationType;
import com.vollyball.enums.Phase;
import com.vollyball.panels.report.PanReportHome;
import com.vollyball.renderer.DeleteButtonRenderer;
import com.vollyball.renderer.EditButtonRenderer;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.LiveButtonRenderer;
import com.vollyball.renderer.ReportButtonRenderer;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
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
public class PanMatchReport extends javax.swing.JPanel {

    MatchDao matchDao = new MatchDao();
    DefaultTableModel model;
    LinkedHashMap<Integer, Integer> matchIdmap = new LinkedHashMap<>();
    JTable tbMatch;
    TeamDao teamDao = new TeamDao();
    LinkedHashMap<String, Integer> teamMap = new LinkedHashMap<>();
    CompetitionBean cb;

    /**
     * Creates new form PanMatchReport
     */
    public PanMatchReport(final CompetitionBean cb) {
        initComponents();
        this.cb = cb;
        ((JLabel) cmbTeam1.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) cmbTeam2.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        ((JLabel) cmbPhase.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        model.setDataVector(new Object[][]{},
                new Object[]{"SR No.", "Match", "Won By", "Date", "Time", "Phase", "Place", "Day Number", "Match Number", "Report", "Evaluate", "Edit", "Delete"});

        tbMatch = new JTable(model) {

            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbMatch.setFont(new java.awt.Font("Times New Roman", 0, 14));
        TableColumnModel cm = tbMatch.getColumnModel();
//        ColumnGroup g_name = new ColumnGroup("EVALUATION");
//
//        g_name.add(cm.getColumn(10));
//        g_name.add(cm.getColumn(11));
//
        GroupableTableHeader header1 = (GroupableTableHeader) tbMatch.getTableHeader();
//        header1.addColumnGroup(g_name);

        JScrollPane scroll = new JScrollPane(tbMatch);

        Color heading = new Color(57, 74, 108);
        Color ivory = new Color(255, 255, 255);
        JTableHeader header = tbMatch.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRenderer(tbMatch));
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 45));
        header.setBackground(heading);
        header1.setBackground(heading);

        tbMatch.setBorder(BorderFactory.createLineBorder(new Color(57, 74, 108), 1));

//        header.setDefaultRenderer(new TableHeaderRenderer(tbAllPlayers));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbMatch.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tbMatch.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tbMatch.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbMatch.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

        tbMatch.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        ReportButtonRenderer reportButtonRenderer = new ReportButtonRenderer();
        tbMatch.getColumnModel().getColumn(9).setCellRenderer(reportButtonRenderer);

        LiveButtonRenderer liveButtonRenderer = new LiveButtonRenderer();
        tbMatch.getColumnModel().getColumn(10).setCellRenderer(liveButtonRenderer);

//        PostButtonRenderer postButtonRenderer = new PostButtonRenderer();
//        tbMatch.getColumnModel().getColumn(11).setCellRenderer(postButtonRenderer);
        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbMatch.getColumnModel().getColumn(11).setCellRenderer(editButtonRenderer);
         DeleteButtonRenderer deleteButtonRenderer = new DeleteButtonRenderer();
        tbMatch.getColumnModel().getColumn(12).setCellRenderer(deleteButtonRenderer);

        tbMatch.setRowHeight(35);

        tbMatch.setOpaque(true);
        tbMatch.setFillsViewportHeight(true);
        tbMatch.setBackground(ivory);
        tbMatch.setSelectionBackground(Color.WHITE);
        tbMatch.setSelectionForeground(Color.BLACK);
        MouseMotionAdapter mma;
        mma = new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                Point p = e.getPoint();
                if (tbMatch.columnAtPoint(p) == 9 || tbMatch.columnAtPoint(p) == 10 || tbMatch.columnAtPoint(p) == 11) {
                    tbMatch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                } else {
                    tbMatch.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        };
        tbMatch.addMouseMotionListener(mma);

        tbMatch.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int id = 0;
                    tbMatch.setSelectionBackground(Color.WHITE);
                    tbMatch.setSelectionForeground(Color.BLACK);

                    int selectedRow = tbMatch.getSelectedRow();
                    int selectedCol = tbMatch.getSelectedColumn();
                    if (selectedRow >= 0) {
                        if (selectedCol == 9) {
                            id = matchIdmap.get((int) tbMatch.getValueAt(selectedRow, 0));
//                            DialogMatchDetails createDialogPanMatchWiseReport = new DialogMatchDetails();
//                            createDialogPanMatchWiseReport.init(cb.getId(), id);
//
//                            tbMatch.clearSelection();
//                            createDialogPanMatchWiseReport.show();
                            PanReportHome panMatch = new PanReportHome(cb.getId(), id);
                            Controller.panCompetitionReportHome.panData.removeAll();
                            Controller.panCompetitionReportHome.panData.add(panMatch, BorderLayout.CENTER);

                            MatchBean team = matchDao.getMatchesById(cb.getId(), id);
                             Controller.frmDashBoard.lblThird.setText(team.getTeam1name() + " vs " + team.getTeam2name());
                            Controller.frmDashBoard.lblThird.setVisible(true);
                            Controller.frmDashBoard.lblThird.setForeground(Color.ORANGE);
                             Controller.panCompetitionReportHome.panData.validate();
                            Controller.panCompetitionReportHome.panData.repaint();

                        } else if (selectedCol == 10) {
                            id = matchIdmap.get((int) tbMatch.getValueAt(selectedRow, 0));
//                            CreateSelectTeamDialog obj = new CreateSelectTeamDialog();
                            tbMatch.clearSelection();
//                            obj.setValues(id, EvaluationType.LIVE.getId());
//                            obj.init();
//                            obj.show();

                            PanSelectTeam panSelectTeam = new PanSelectTeam(id, EvaluationType.LIVE.getId());

                            Controller.panCompetitionReportHome.panData.removeAll();
                            Controller.panCompetitionReportHome.panData.add(panSelectTeam, BorderLayout.CENTER);

                            MatchBean team = matchDao.getMatchesById(cb.getId(), id);
                            Controller.frmDashBoard.lblThird.setText(team.getTeam1name() + " vs " + team.getTeam2name());
                            Controller.frmDashBoard.lblThird.setForeground(Color.ORANGE);
                            Controller.frmDashBoard.lblThird.setVisible(true);
                            Controller.panCompetitionReportHome.panData.validate();
                            Controller.panCompetitionReportHome.panData.repaint();

                        } //                            else if (selectedCol == 11) {
                        //                            id = matchIdmap.get((int) tbMatch.getValueAt(selectedRow, 0));
                        //                            CreateSelectTeamDialog obj = new CreateSelectTeamDialog();
                        //                            tbMatch.clearSelection();
                        //                            obj.setValues(id, EvaluationType.POST.getId());
                        //                            obj.init();
                        //                            obj.show();
                        //                        }
                        else if (selectedCol == 11) {
                            id = matchIdmap.get((int) tbMatch.getValueAt(selectedRow, 0));
                            Controller.matchDialog =   new CreateMatchDialog();
                            tbMatch.clearSelection();
                            Controller.matchDialog.setValues(id);
                            Controller.matchDialog.init();
                            Controller.matchDialog.show();
                        }
                        else if (selectedCol == 12) {
                            id = (int) matchIdmap.get((int) tbMatch.getValueAt(selectedRow, 0));
                            String name =  (String) tbMatch.getValueAt(selectedRow,1);
                            
                                                                           
                             int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog(null, "Are You Sure want to delete \""+name+"\" ?", "Warning", dialogButton);
                            if (dialogResult == JOptionPane.YES_OPTION) {
                                int count = matchDao.deleteMatch(id);
                                                       if (count != 0) {
                                                 Refresh();          
                                    JOptionPane.showMessageDialog(null, "Match Deleted Successfully");
                                    
                                } else {
                                    JOptionPane.showMessageDialog(null, "Not Able to Delete Match");
                                }
                            }
                        }
                    }
                }
            }
        });

        resizeColumns();
        List<MatchBean> matchList = matchDao.getMatches(cb.getId());

        int i = 0;

        for (MatchBean match : matchList) {
            i++;
            matchIdmap.put(i, match.getId());
            Object[] row = {i, match.getMatch(), "", match.getDate(), match.getTime(), match.getPhase(), match.getPlace(), match.getDayNumber(), match.getMatchNumber(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
            model.addRow(row);
        }

        panMatchList.add(scroll, BorderLayout.CENTER);

        List<Team> team = teamDao.getTeams(cb.getId());

        for (Team t : team) {
            cmbTeam1.addItem(t.getName());
            cmbTeam2.addItem(t.getName());
            teamMap.put(t.getName(), t.getId());
        }

        for (Phase dir : Phase.values()) {
            cmbPhase.addItem(dir.getName());
        }

    }

    float[] columnWidthPercentage = {5.0f, 25.0f, 10.0f, 8.0f, 8.0f, 14.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};

    private void resizeColumns() {
        int tW = tbMatch.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbMatch.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void Refresh() {
        List<MatchBean> matchList = matchDao.getMatches(cb.getId());
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        int i = 0;

        for (MatchBean match : matchList) {
            i++;
            matchIdmap.put(i, match.getId());
            Object[] row = {i, match.getMatch(), "", match.getDate(), match.getTime(), match.getPhase(), match.getPlace(), match.getDayNumber(), match.getMatchNumber(), new JPanel(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
            model.addRow(row);
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

        panMatchList = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cmbTeam1 = new javax.swing.JComboBox();
        cmbTeam2 = new javax.swing.JComboBox();
        cmbPhase = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblNewMatch = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panMatchList.setBackground(new java.awt.Color(255, 255, 255));
        panMatchList.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        cmbTeam1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbTeam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Team 1" }));
        cmbTeam1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTeam1ItemStateChanged(evt);
            }
        });

        cmbTeam2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbTeam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Team 2" }));
        cmbTeam2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTeam2ItemStateChanged(evt);
            }
        });
        cmbTeam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTeam2ActionPerformed(evt);
            }
        });

        cmbPhase.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbPhase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Phase" }));
        cmbPhase.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPhaseItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("VS");

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(57, 74, 108));
        jLabel7.setText("Match");
        jLabel7.setToolTipText("");

        lblNewMatch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewMatch.setForeground(new java.awt.Color(255, 255, 255));
        lblNewMatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewMatch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/button (1).png"))); // NOI18N
        lblNewMatch.setToolTipText("New Match");
        lblNewMatch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNewMatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewMatchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNewMatch)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNewMatch))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbTeam2)
                            .addComponent(cmbPhase)
                            .addComponent(cmbTeam1)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panMatchList, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panMatchList, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTeam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTeam2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTeam2ActionPerformed

    public void search()
    {
        String team1 = (String) cmbTeam1.getSelectedItem();
        String team2 = (String) cmbTeam2.getSelectedItem();
        String phase = (String) cmbPhase.getSelectedItem();

        int teamNo1 = team1.equalsIgnoreCase("Team 1") ? 0 : teamMap.get(team1);
        int teamNo2 = team2.equalsIgnoreCase("Team 2") ? 0 : teamMap.get(team2);

        List<MatchBean> matchList = matchDao.searchMatches(cb.getId(), teamNo1, teamNo2, phase);

        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        int i = 0;

        for (MatchBean match : matchList) {
            i++;
            matchIdmap.put(i, match.getId());
            Object[] row = {i, match.getMatch(), "", match.getDate(), match.getTime(), match.getPhase(), match.getPlace(), match.getDayNumber(), match.getMatchNumber(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
            model.addRow(row);
        }

    }
    
    private void lblNewMatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewMatchMouseClicked
        // TODO add your handling code here:
        Controller.matchDialog = new CreateMatchDialog();
        Controller.matchDialog.init();
        Controller.matchDialog.show();
    }//GEN-LAST:event_lblNewMatchMouseClicked

    private void cmbTeam1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTeam1ItemStateChanged
        // TODO add your handling code here:
        search();
    }//GEN-LAST:event_cmbTeam1ItemStateChanged

    private void cmbTeam2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTeam2ItemStateChanged
        // TODO add your handling code here:
         search();
        
    }//GEN-LAST:event_cmbTeam2ItemStateChanged

    private void cmbPhaseItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPhaseItemStateChanged
        // TODO add your handling code here:
         search();
    }//GEN-LAST:event_cmbPhaseItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbPhase;
    private javax.swing.JComboBox cmbTeam1;
    private javax.swing.JComboBox cmbTeam2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblNewMatch;
    private javax.swing.JPanel panMatchList;
    // End of variables declaration//GEN-END:variables
}
