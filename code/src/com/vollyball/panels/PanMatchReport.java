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
import com.vollyball.dialog.CreateSelectTeamDialog;
import com.vollyball.dialog.DialogMatchDetails;
import com.vollyball.enums.EvaluationType;
import com.vollyball.enums.Phase;
import com.vollyball.renderer.ColumnGroup;
import com.vollyball.renderer.EditButtonRenderer;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.LiveButtonRenderer;
import com.vollyball.renderer.PostButtonRenderer;
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
        model = new DefaultTableModel();

        model.setDataVector(new Object[][]{},
                new Object[]{"SR No.", "Match", "Won By", "Date", "Time", "Phase", "Place", "Day Number", "Match Number", "Report", "LIVE", "POST", "Action"});

        tbMatch = new JTable(model) {

            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };

        tbMatch.setFont(new java.awt.Font("Times New Roman", 0, 14));
        TableColumnModel cm = tbMatch.getColumnModel();
        ColumnGroup g_name = new ColumnGroup("EVALUATION");

        g_name.add(cm.getColumn(10));
        g_name.add(cm.getColumn(11));

        GroupableTableHeader header1 = (GroupableTableHeader) tbMatch.getTableHeader();
        header1.addColumnGroup(g_name);

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

        PostButtonRenderer postButtonRenderer = new PostButtonRenderer();
        tbMatch.getColumnModel().getColumn(11).setCellRenderer(postButtonRenderer);

        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbMatch.getColumnModel().getColumn(12).setCellRenderer(editButtonRenderer);

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
                            DialogMatchDetails createDialogPanMatchWiseReport = new DialogMatchDetails();
                            createDialogPanMatchWiseReport.init(cb.getId(), id);

                            tbMatch.clearSelection();
                            createDialogPanMatchWiseReport.show();
                        } else if (selectedCol == 10) {
                            id = matchIdmap.get((int) tbMatch.getValueAt(selectedRow, 0));
                            CreateSelectTeamDialog obj = new CreateSelectTeamDialog();
                            tbMatch.clearSelection();
                            obj.setValues(id, EvaluationType.LIVE.getId());
                            obj.init();
                            obj.show();
                        } else if (selectedCol == 11) {
                            id = matchIdmap.get((int) tbMatch.getValueAt(selectedRow, 0));
                            CreateSelectTeamDialog obj = new CreateSelectTeamDialog();
                            tbMatch.clearSelection();
                            obj.setValues(id, EvaluationType.POST.getId());
                            obj.init();
                            obj.show();
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

    float[] columnWidthPercentage = {5.0f, 25.0f, 10.0f, 8.0f, 8.0f, 14.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};

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
            Object[] row = {i, match.getMatch(), "", match.getDate(), match.getTime(), match.getPhase(), match.getPlace(), match.getDayNumber(), match.getMatchNumber(), new JPanel(), new JPanel(), new JPanel(), new JPanel()};
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
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        cmbTeam1 = new javax.swing.JComboBox();
        cmbTeam2 = new javax.swing.JComboBox();
        cmbPhase = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        panMatchList.setBackground(new java.awt.Color(255, 255, 255));
        panMatchList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panMatchList.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("New Match");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        cmbTeam1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbTeam1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));

        cmbTeam2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbTeam2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));
        cmbTeam2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTeam2ActionPerformed(evt);
            }
        });

        cmbPhase.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        cmbPhase.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select" }));

        jPanel3.setBackground(new java.awt.Color(57, 74, 108));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SEARCH");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Team 1");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Team 2");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Phase");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("VS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cmbTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(cmbTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTeam2)
                    .addComponent(cmbPhase)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTeam1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMatchList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panMatchList, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmbTeam2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTeam2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTeam2ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:

        String team1 = (String) cmbTeam1.getSelectedItem();
        String team2 = (String) cmbTeam2.getSelectedItem();
        String phase = (String) cmbPhase.getSelectedItem();

        int teamNo1 = team1.equalsIgnoreCase("Select") ? 0 : teamMap.get(team1);
        int teamNo2 = team2.equalsIgnoreCase("Select") ? 0 : teamMap.get(team2);

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

    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        Controller.matchDialog = new CreateMatchDialog();
        Controller.matchDialog.init();
        Controller.matchDialog.show();
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbPhase;
    private javax.swing.JComboBox cmbTeam1;
    private javax.swing.JComboBox cmbTeam2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel panMatchList;
    // End of variables declaration//GEN-END:variables
}
