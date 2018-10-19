/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.CompetitionDao;
import com.vollyball.dialog.CreateCompetitionDialog;
import com.vollyball.renderer.EditButtonRenderer;
import com.vollyball.renderer.TableHeaderRenderer;
import com.vollyball.renderer.ViewButtonRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
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
 * @author Supriya
 */
public class PanCompetitionList extends javax.swing.JPanel {

    public PanCompListValue panCompListValue;

    JTable tbComp;
    DefaultTableModel model;
    CompetitionDao competitionDao = new CompetitionDao();
    LinkedHashMap<String, CompetitionBean> compMap = new LinkedHashMap<String, CompetitionBean>();

    /**
     * Creates new form PanCompetitionList
     */
    public PanCompetitionList() {
        initComponents();
//        panCompListValue = new PanCompListValue();
//        Dimension dim = panListContent.getSize();
//
////        try {
////            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
////        } catch (Exception ex) {
////        }
////        panCompListValue.setBounds(0, 0, dim.width, dim.height);
//        panListContent.add(panCompListValue, BorderLayout.CENTER);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };

        model.setDataVector(new Object[][]{},
                new Object[]{"SR No.", "Competition Name ", "Venue", "Start Date", "End Date", "Age Group", "View", "Action"});

        tbComp = new JTable(model);

        tbComp.setFont(new java.awt.Font("Times New Roman", 0, 13));

        JScrollPane scroll = new JScrollPane(tbComp);

        Color heading = new Color(204, 204, 204);
        Color ivory = new Color(255, 255, 255);
        JTableHeader header = tbComp.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRenderer(tbComp));
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 45));
        header.setBackground(heading);
        header.setFont(new java.awt.Font("Times New Roman", Font.BOLD, 14));
//        header.setDefaultRenderer(new TableHeaderRenderer(tbAllPlayers));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbComp.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbComp.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

        tbComp.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbComp.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbComp.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        tbComp.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        ViewButtonRenderer viewButtonRenderer = new ViewButtonRenderer();
        tbComp.getColumnModel().getColumn(6).setCellRenderer(viewButtonRenderer);

        EditButtonRenderer editButtonRenderer = new EditButtonRenderer();
        tbComp.getColumnModel().getColumn(7).setCellRenderer(editButtonRenderer);

        tbComp.setRowHeight(40);

        tbComp.setOpaque(true);
        tbComp.setFillsViewportHeight(true);
        tbComp.setBackground(ivory);
        tbComp.setSelectionBackground(Color.WHITE);
        tbComp.setSelectionForeground(Color.BLACK);
        tbComp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int id = 0;
                    int selectedRow = tbComp.getSelectedRow();
                    int selectedCol = tbComp.getSelectedColumn();
                    tbComp.clearSelection();
                    if (selectedRow >= 0) {
                        if (selectedCol == 6) {
                            CompetitionBean comp = compMap.get(tbComp.getValueAt(selectedRow, 1));
Controller.competitionBean = comp;
                            Controller.panCompetitionReportHome = new PanCompetitionReportHome(comp);
                            Controller.competitionId = comp.getId();
                            Dimension dim = Controller.frmDashBoard.panContent.getSize();
                            Controller.panCompetitionList.setVisible(false);
                            Controller.frmDashBoard.panContent.removeAll();
//        Controller.panComptitionHome.setBounds(0, 0, 800, 686);
                            Controller.frmDashBoard.panContent.add(Controller.panCompetitionReportHome, BorderLayout.CENTER);
     
                            
                        } else if (selectedCol == 7) {
                            id = (int) tbComp.getValueAt(selectedRow, 0);
                            Controller.createCompetitionDialog = new CreateCompetitionDialog();

                            Controller.createCompetitionDialog.setValues(id);
                            Controller.createCompetitionDialog.init();
                            Controller.createCompetitionDialog.show();
                        }

                    }
                }
            }
        });
        resizeColumns();

        List<CompetitionBean> competitionList = competitionDao.getCompetitionList();
        int i = 0;
        for (CompetitionBean comp : competitionList) {
            i++;
            compMap.put(comp.getName(), comp);
            Object[] row = {i, comp.getName(), comp.getVenue(), comp.getStartDate(), comp.getEndDate(), comp.getAgeGroup(), new JPanel(), new JPanel()};
            model.addRow(row);
        }
        panListContent.add(scroll, BorderLayout.CENTER);
        validate();
        repaint();

    }

    float[] columnWidthPercentage = {5.0f, 25.0f, 25.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};

    private void resizeColumns() {
        int tW = tbComp.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbComp.getColumnModel();
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
        List<CompetitionBean> competitionList = competitionDao.getCompetitionList();
        int i = 0;
        for (CompetitionBean comp : competitionList) {
            i++;
            compMap.put(comp.getName(), comp);
            Object[] row = {i, comp.getName(), comp.getVenue(), comp.getStartDate(), comp.getEndDate(), comp.getAgeGroup(), new JPanel(), new JPanel()};
            model.addRow(row);
        }

        validate();
        repaint();
    }

    public class PanCompListValue extends JPanel {

        CompetitionDao competitionDao = new CompetitionDao();
        private JPanel mainList;

        public PanCompListValue() {
            setLayout(new BorderLayout());
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
            add(new JScrollPane(mainList));

            List<CompetitionBean> competitionList = competitionDao.getCompetitionList();
            for (CompetitionBean cb : competitionList) {
                JPanel panel = new PanCompRow(cb);
//                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                GridBagConstraints gbcRow = new GridBagConstraints();
                gbcRow.gridwidth = GridBagConstraints.REMAINDER;
                gbcRow.weightx = 1;
                gbcRow.gridheight = 2;
                gbcRow.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(panel, gbcRow, 0);

            }

        }

        public void add() {
            mainList.removeAll();
            List<CompetitionBean> competitionList = competitionDao.getCompetitionList();
            for (CompetitionBean cb : competitionList) {
                PanCompRow panel = new PanCompRow(cb);
//                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                GridBagConstraints gbcRow = new GridBagConstraints();
                gbcRow.gridwidth = GridBagConstraints.REMAINDER;
                gbcRow.weightx = 1;
                gbcRow.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(panel, gbcRow, 0);
                Controller.frmDashBoard.validate();
                Controller.frmDashBoard.repaint();

            }
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

        panListContent = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblNewCompetition = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        panListContent.setBackground(new java.awt.Color(255, 255, 255));
        panListContent.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(0, 102, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(0));

        lblNewCompetition.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblNewCompetition.setForeground(new java.awt.Color(255, 255, 255));
        lblNewCompetition.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNewCompetition.setText("New ");
        lblNewCompetition.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNewCompetitionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewCompetition, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNewCompetition, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(57, 74, 108));
        jLabel2.setText("Competition");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 794, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panListContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panListContent, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblNewCompetitionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNewCompetitionMouseClicked
        // TODO add your handling code here:
        Controller.createCompetitionDialog = new CreateCompetitionDialog();
        Controller.createCompetitionDialog.init();
        Controller.createCompetitionDialog.show();
    }//GEN-LAST:event_lblNewCompetitionMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblNewCompetition;
    private javax.swing.JPanel panListContent;
    // End of variables declaration//GEN-END:variables
}
