/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.panel;

import com.vollyball.controller.Controller;
import com.vollyball.renderer.EditButtonRenderer;
import com.vollyball.renderer.TableHeaderRenderer;
import com.vollyball.renderer.ViewButtonRenderer;
import com.vollyball.training.bean.Batch;
import com.vollyball.training.dao.BatchDao;
import com.vollyball.training.dialog.CreateBatchDialog;
import com.vollyball.training.dialog.CreateTraineeDailog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
public class PanBatchList extends javax.swing.JPanel {

    JTable tbBatch;
    DefaultTableModel model;
    BatchDao batchDao = new BatchDao();
    LinkedHashMap<String, Batch> batchMap = new LinkedHashMap<String, Batch>();
    TableRowSorter<TableModel> sorter;

    /**
     * Creates new form PanTrainingBestScorer
     */
    public PanBatchList() {
        initComponents();

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        model.setDataVector(new Object[][]{},
                new Object[]{"SR No.", "Batch Name ", "Trainer", "Venue", "Start Date", "End Date", "Age Group", "View", "Action"});

        tbBatch = new JTable(model);
        sorter = new TableRowSorter<TableModel>(tbBatch.getModel());
        tbBatch.setRowSorter(sorter);
        tbBatch.setFont(new java.awt.Font("Times New Roman", 0, 13));

        JScrollPane scroll = new JScrollPane(tbBatch);
        Color heading = new Color(204, 204, 204);
        Color ivory = new Color(255, 255, 255);
        JTableHeader header = tbBatch.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRenderer(tbBatch));
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 45));
        header.setBackground(heading);
        header.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 14));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbBatch.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbBatch.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbBatch.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbBatch.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbBatch.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbBatch.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        tbBatch.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        ViewButtonRenderer viewButtonRenderer = new ViewButtonRenderer();
        tbBatch.getColumnModel().getColumn(7).setCellRenderer(viewButtonRenderer);

        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbBatch.getColumnModel().getColumn(8).setCellRenderer(editButtonRenderer);

        tbBatch.setRowHeight(40);

        tbBatch.setOpaque(true);
        tbBatch.setFillsViewportHeight(true);
        tbBatch.setBackground(ivory);
        tbBatch.setSelectionBackground(Color.WHITE);
        tbBatch.setSelectionForeground(Color.BLACK);

        tbBatch.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int id = 0;
                    int selectedRow = tbBatch.getSelectedRow();
                    int selectedCol = tbBatch.getSelectedColumn();
//                    tbBatch.clearSelection();
                    if (selectedRow >= 0) {
                        if (selectedCol == 7) {
                            id = (int) tbBatch.getValueAt(selectedRow, 0);
                            Controller.panBatchList.setVisible(false);
                            Controller.frmDashBoard.panContent.removeAll();
                            Dimension dim = Controller.frmDashBoard.panContent.getSize();
                            Controller.panBatchTraineeList = new PanBatchTraineeList(id);
                            Controller.frmDashBoard.panContent.add(Controller.panBatchTraineeList, BorderLayout.CENTER);
                        } else if (selectedCol == 8) {
                            id = (int) tbBatch.getValueAt(selectedRow, 0);
                            Controller.batchDialog = new CreateBatchDialog();
                            Controller.batchDialog.setValues(id);
                            Controller.batchDialog.init();
                            Controller.batchDialog.show();
                        }
                        tbBatch.clearSelection();

                    }
                }
            }
        });
        resizeColumns();

        List<Batch> batchList = batchDao.getBatchList();
        int i = 0;
        for (Batch batch : batchList) {
            i++;
            batchMap.put(batch.getName(), batch);
            Object[] row = {i, batch.getName(), batch.getTrainer(), batch.getVenue(), batch.getStartDate(), batch.getEndDate(), batch.getAgeGroup(), new JPanel(), new JPanel()};
            model.addRow(row);
        }

        panListContent.add(scroll, BorderLayout.CENTER);

    }

    float[] columnWidthPercentage = {5.0f, 25.0f, 20.0f, 20.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};

    private void resizeColumns() {
        int tW = tbBatch.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbBatch.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void refresh() {
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            model.removeRow(i);
        }
        List<Batch> batchList = batchDao.getBatchList();
        int i = 0;
        for (Batch batch : batchList) {
            i++;
            batchMap.put(batch.getName(), batch);
            Object[] row = {i, batch.getName(), batch.getTrainer(), batch.getVenue(), batch.getStartDate(), batch.getEndDate(), batch.getAgeGroup(), new JPanel(), new JPanel()};
            model.addRow(row);
        }

        validate();
        repaint();
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

        panListContent = new javax.swing.JPanel();
        panSkillReports = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        lblReportHeading = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblNewTeam = new javax.swing.JLabel();
        txtFilter = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblNewTeam1 = new javax.swing.JLabel();

        panListContent.setBackground(new java.awt.Color(255, 255, 255));
        panListContent.setLayout(new java.awt.BorderLayout());

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        lblReportHeading.setBackground(new java.awt.Color(255, 255, 255));
        lblReportHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReportHeading.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblReportHeading.setText("BATCH SCORES");

        jPanel3.setBackground(new java.awt.Color(57, 74, 108));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNewTeam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam.setText("New Batch");
        lblNewTeam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNewTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewTeamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        txtFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFilterKeyTyped(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblNewTeam1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblNewTeam1.setForeground(new java.awt.Color(255, 255, 255));
        lblNewTeam1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewTeam1.setText("Add Trainee");
        lblNewTeam1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNewTeam1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewTeam1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
                .addComponent(lblReportHeading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFilter, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(txtFilter)
                        .addContainerGap())
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(lblReportHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10))))
        );

        javax.swing.GroupLayout panSkillReportsLayout = new javax.swing.GroupLayout(panSkillReports);
        panSkillReports.setLayout(panSkillReportsLayout);
        panSkillReportsLayout.setHorizontalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSkillReportsLayout.setVerticalGroup(
            panSkillReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkillReportsLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panListContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panSkillReports, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panListContent, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblNewTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewTeamMouseClicked
        // TODO add your handling code here:
        Controller.batchDialog = new CreateBatchDialog();
        Controller.batchDialog.init();
        Controller.batchDialog.show();

    }//GEN-LAST:event_lblNewTeamMouseClicked

    private void txtFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFilterKeyTyped
        // TODO add your handling code here:
        newFilter();
    }//GEN-LAST:event_txtFilterKeyTyped

    private void lblNewTeam1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewTeam1MouseClicked
        // TODO add your handling code here:
        Controller.createTraineeDailog=new CreateTraineeDailog();
        Controller.createTraineeDailog.init();
        Controller.createTraineeDailog.show();
    }//GEN-LAST:event_lblNewTeam1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblNewTeam;
    private javax.swing.JLabel lblNewTeam1;
    public javax.swing.JLabel lblReportHeading;
    private javax.swing.JPanel panListContent;
    private javax.swing.JPanel panSkillReports;
    private javax.swing.JTextField txtFilter;
    // End of variables declaration//GEN-END:variables
}
