/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.PlayerReportBean;
import com.vollyball.chart.LineChart;
import com.vollyball.dao.ReportDao;
import com.vollyball.dialog.CreateTeamPlayerReportDialog;
import com.vollyball.dialog.DialogPanChart;
import com.vollyball.enums.Skill;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author nishant.vibhute
 */
public class PanPlayerDetails extends javax.swing.JPanel {

    DefaultTableModel modelOverall, modelMatchwise;
    ReportDao reportDao = new ReportDao();
    String type, skillName;
    int skillId, compId;
    LinkedHashMap<Integer, PlayerReportBean> mapDetails = new LinkedHashMap<Integer, PlayerReportBean>();
    PlayerReportBean prb;
    List<PlayerReportBean> pbList;

    /**
     * Creates new form PanPlayerDetails
     */
    public PanPlayerDetails(final PlayerReportBean prb, String SkillName, final int skillId, final int compId, String type) {
        initComponents();
        this.skillId = skillId;
        this.compId = compId;
        this.prb = prb;
        this.skillName = skillName;
        lblName.setText(prb.getName());
        lblSkill.setText(SkillName);
        modelOverall = (DefaultTableModel) tbOverall.getModel();
        Object[] row = {prb.getTotal(), prb.getSuccess(), prb.getSuccessrate()};
        modelOverall.addRow(row);

        Color heading = new Color(204, 204, 204);
        modelOverall = (DefaultTableModel) tbOverall.getModel();
        JTableHeader header = tbOverall.getTableHeader();
        header.setBackground(heading);
//
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 31));
        header.setDefaultRenderer(new TableHeaderRenderer(tbOverall));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tbOverall.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        tbOverall.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbOverall.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        modelMatchwise = (DefaultTableModel) tbMatchwise.getModel();

        JTableHeader headerMatchwise = tbMatchwise.getTableHeader();
        headerMatchwise.setBackground(heading);
//
        headerMatchwise.setOpaque(false);
        headerMatchwise.setPreferredSize(new Dimension(100, 31));
        headerMatchwise.setDefaultRenderer(new TableHeaderRenderer(tbMatchwise));
        tbMatchwise.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbMatchwise.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbMatchwise.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbMatchwise.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        pbList = reportDao.getMatchWisePlayerReportList(skillId, compId, prb.getId(), type);
        int i = 0;
        for (PlayerReportBean pb : pbList) {
            mapDetails.put(i, pb);
            Object[] rowMatch = {pb.getName(), pb.getTotal(), pb.getSuccess(), pb.getSuccessrate()};
            modelMatchwise.addRow(rowMatch);
            i++;
        }

        Color ivory = new Color(255, 255, 255);
        tbMatchwise.setOpaque(true);
        tbMatchwise.setFillsViewportHeight(true);
        tbMatchwise.setBackground(ivory);

        if (type.equals("team")) {
            tbMatchwise.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()) {
                        String selectedName = null;

                        int row = tbMatchwise.getSelectedRow();
                        for (int i = 0; i <= row; i++) {
                            selectedName = (String) tbMatchwise.getValueAt(row, 0);

                        }

                        if (selectedName != null) {
                            CreateTeamPlayerReportDialog createDialogPanMatchWiseReport = new CreateTeamPlayerReportDialog();
                            createDialogPanMatchWiseReport.setValues(prb, skillName, skillId, compId, mapDetails.get(row).getId());
                            createDialogPanMatchWiseReport.init();
                            createDialogPanMatchWiseReport.show();
                        }
                    }
                }
            });
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

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        lblSkill = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOverall = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMatchwise = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        showGraph = new javax.swing.JPanel();
        lblGraph = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        lblName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("jLabel1");

        lblSkill.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblSkill.setForeground(new java.awt.Color(244, 195, 1));
        lblSkill.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSkill.setText("jLabel5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265)
                .addComponent(lblSkill, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSkill, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("OVERALL");

        tbOverall.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tbOverall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TOTAL ATTEMPT", "SUCCESSFUL ATTEMPT", "SUCCESS RATE"
            }
        ));
        tbOverall.setRowHeight(28);
        jScrollPane1.setViewportView(tbOverall);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbMatchwise.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATCH", "TOTAL ATTEMPT", "SUCCESSFUL ATTEMPT", "SUCCESS RATE"
            }
        ));
        tbMatchwise.setRowHeight(30);
        jScrollPane2.setViewportView(tbMatchwise);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("MATCHES PLAYED");

        showGraph.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        showGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showGraphMouseClicked(evt);
            }
        });

        lblGraph.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/chart_curve.png"))); // NOI18N
        lblGraph.setToolTipText("Show Graph");
        lblGraph.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblGraphMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout showGraphLayout = new javax.swing.GroupLayout(showGraph);
        showGraph.setLayout(showGraphLayout);
        showGraphLayout.setHorizontalGroup(
            showGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showGraphLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
        );
        showGraphLayout.setVerticalGroup(
            showGraphLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(showGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 988, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(showGraph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void showGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showGraphMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_showGraphMouseClicked

    private void lblGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGraphMouseClicked
        // TODO add your handling code here:

        String series1 = "Matches";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (PlayerReportBean pb : pbList) {
            dataset.addValue(pb.getSuccessr(), series1, pb.getName());
        }
        JFreeChart jcChart = LineChart.LineChartExample(dataset, Skill.getNameById(skillId).getType());
        ChartPanel panel = new ChartPanel(jcChart);
        DialogPanChart obj = new DialogPanChart();
        obj.init(prb.getName(), prb.getTeamName(), panel);
        obj.show();
    }//GEN-LAST:event_lblGraphMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGraph;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSkill;
    private javax.swing.JPanel showGraph;
    private javax.swing.JTable tbMatchwise;
    private javax.swing.JTable tbOverall;
    // End of variables declaration//GEN-END:variables
}
