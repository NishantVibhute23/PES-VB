/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.panel;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.controller.Controller;
import com.vollyball.dialog.CreateCompetitionDialog;
import com.vollyball.panels.PanCompetitionReportHome;
import com.vollyball.renderer.EditButtonRenderer;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.TableHeaderRenderer;
import com.vollyball.training.bean.Trainee;
import com.vollyball.training.dao.BatchDao;
import com.vollyball.training.dialog.CreateBatchMatchDailog;
import com.vollyball.training.dialog.CreateTraineeDailog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
 * @author #dabbu
 */
public class PanBatchTraineeList extends javax.swing.JPanel {

    int batchId;
    DefaultTableModel model;
    JTable tbReport;
    TableRowSorter<TableModel> sorter;
    LinkedHashMap<String, Trainee> traineeMap = new LinkedHashMap<String, Trainee>();

    /**
     * Creates new form PanBatchTraineeListl
     */
    public PanBatchTraineeList(int batchId) {
        initComponents();
        this.batchId = batchId;
        createTable();
        setRow(batchId);
    }

    public void setRow(int batchId) {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        List<Trainee> traineeList = new ArrayList<>();
        BatchDao bd = new BatchDao();
        traineeList = bd.getTraineeList(batchId);
        int i = 0;
        for (Trainee t : traineeList) {
            traineeMap.put(t.getName(), t);
            Object[] row = {i + 1, t.getName(), new JPanel()};
            model.addRow(row);
            i++;
        }

    }

    public void createTable() {
        model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        };

        model.setDataVector(new Object[][]{},
                new Object[]{"SR No.", "Trainee Name", "Action"});

        tbReport = new JTable(model) {
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
        tbReport.getColumnModel().getColumn(0).setWidth(3);

        tbReport.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbReport.getColumnModel().getColumn(2).setCellRenderer(editButtonRenderer);

        Color ivory = new Color(255, 255, 255);
        tbReport.setOpaque(true);
        tbReport.setFillsViewportHeight(true);
        tbReport.setBackground(ivory);
        tbReport.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = tbReport.getSelectedRow();
                    int selectedCol = tbReport.getSelectedColumn();

                    if (selectedRow >= 0) {
                        if (selectedCol == 2) {
                            Trainee trainee = traineeMap.get(tbReport.getValueAt(selectedRow, 1));
                            Controller.createTraineeDailog = new CreateTraineeDailog();
                            Controller.createTraineeDailog.setTrainee(trainee);
                            Controller.createTraineeDailog.init();
                            Controller.createTraineeDailog.show();
                        }
                    }
                    tbReport.clearSelection();
                }
            }
        });

        tbReport.setRowHeight(30);
        tbReport.setSelectionBackground(Color.WHITE);
        tbReport.setSelectionForeground(Color.BLACK);

        resizeColumns();
        panReport.add(scroll, BorderLayout.CENTER);
    }
    float[] columnWidthPercentage = {10.0f, 40.0f, 10.0f};

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

        panReport = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblReportHeading = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblNewTeam = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblNewTeam1 = new javax.swing.JLabel();

        panReport.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lblReportHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblReportHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReportHeading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReportHeading.setText("TRAINEE SEARCH");

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNewTeam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam.setText("Add Trainee");
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

        jPanel3.setBackground(new java.awt.Color(57, 74, 108));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNewTeam1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam1.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam1.setText("Match Schedule");
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
            .addComponent(lblNewTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
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
                    .addComponent(txtFilter)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReport, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblNewTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewTeamMouseClicked
        // TODO add your handling code here:
        Controller.createTraineeDailog = new CreateTraineeDailog();
        Controller.createTraineeDailog.setValues(batchId);
        Controller.createTraineeDailog.init();
        Controller.createTraineeDailog.show();


    }//GEN-LAST:event_lblNewTeamMouseClicked

    private void txtFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyTyped
        // TODO add your handling code here:
        newFilter();
    }//GEN-LAST:event_txtFilterKeyTyped

    private void lblNewTeam1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewTeam1MouseClicked
        // TODO add your handling code here:
        CreateBatchMatchDailog createBatchMatchDailog = new CreateBatchMatchDailog();
        createBatchMatchDailog.setValues(batchId);
        createBatchMatchDailog.init();
        createBatchMatchDailog.show();
    }//GEN-LAST:event_lblNewTeam1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblNewTeam;
    private javax.swing.JLabel lblNewTeam1;
    public javax.swing.JLabel lblReportHeading;
    private javax.swing.JPanel panReport;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
