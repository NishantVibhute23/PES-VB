/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.MatchDashboard;
import com.vollyball.bean.Team;
import com.vollyball.chart.BarChart;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Supriya
 */
public class PanDashboard extends javax.swing.JPanel {

    TeamDao td = new TeamDao();
    MatchDao md = new MatchDao();
    DefaultTableModel model;

    /**
     * Creates new form PanDashboard
     */
    public PanDashboard(final CompetitionBean cb) {
        initComponents();

        List<Team> teamList = td.getTeams(cb.getId());
        List<MatchDashboard> teamRankList = md.getTeamMatchesRegister(teamList);

        Collections.sort(teamRankList,
                new Comparator<MatchDashboard>() {
            @Override
            public int compare(MatchDashboard c1, MatchDashboard c2
            ) {
                return Double.compare(c2.getTotalWin(), c1.getTotalWin());
            }
        }
        );
        Color heading = new Color(57, 74, 108);

        model = (DefaultTableModel) tbMatchesPlayed.getModel();
        JTableHeader header = tbMatchesPlayed.getTableHeader();

        header.setBackground(heading);
        header.setFont(new Font("Times New Roman", Font.BOLD, 14));

        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 40));
        header.setDefaultRenderer(new TableHeaderRenderer(tbMatchesPlayed));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        tbMatchesPlayed.setShowHorizontalLines(false);
        tbMatchesPlayed.setShowVerticalLines(false);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        tbMatchesPlayed.getColumnModel()
                .getColumn(0).setCellRenderer(centerRenderer);
        tbMatchesPlayed.getColumnModel()
                .getColumn(1).setCellRenderer(centerRenderer);
        tbMatchesPlayed.getColumnModel()
                .getColumn(2).setCellRenderer(centerRenderer);
        tbMatchesPlayed.getColumnModel()
                .getColumn(3).setCellRenderer(centerRenderer);
        tbMatchesPlayed.getColumnModel()
                .getColumn(4).setCellRenderer(centerRenderer);
        tbMatchesPlayed.getColumnModel()
                .getColumn(5).setCellRenderer(centerRenderer);
        tbMatchesPlayed.getColumnModel()
                .getColumn(6).setCellRenderer(centerRenderer);
        tbMatchesPlayed.getColumnModel()
                .getColumn(7).setCellRenderer(centerRenderer);
        Color ivory = new Color(255, 255, 255);

        tbMatchesPlayed.setOpaque(
                true);
        tbMatchesPlayed.setFillsViewportHeight(
                true);
        tbMatchesPlayed.setBackground(ivory);
        tbMatchesPlayed.setRowSelectionAllowed(false);
        tbMatchesPlayed.setRowHeight(
                35);
        int i = 0;

        for (MatchDashboard m : teamRankList) {

            String rank = "";
            if (m.getSuccessRate() == 0) {
                rank = "-";

            } else {
                i++;
                rank = "" + i;
            }
            Object[] row = {rank, m.getTeamName(), m.getTotalMatches(), m.getMatchesPlayed(), m.getTotalWin(), m.getTotalLoss(), m.getTotalNoResult(), m.getSuccessRatePerc()};
            model.addRow(row);

        }

        DefaultCategoryDataset datasetBlock = new DefaultCategoryDataset();

        for (MatchDashboard p : teamRankList) {
            datasetBlock.addValue(p.getSuccessRate(), "One", "" + p.getTeamName());

        }
        JFreeChart chartFreeBlock = BarChart.createChart(datasetBlock, "Success Rate", "Teams", false);
        BarRenderer r = (BarRenderer) chartFreeBlock.getCategoryPlot().getRenderer();

        r.setSeriesPaint(0, new Color(57, 74, 108));
        ChartPanel panel = new ChartPanel(chartFreeBlock);
        panel.setBackground(Color.WHITE);
        panChart.add(panel, BorderLayout.CENTER);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMatchesPlayed = new javax.swing.JTable(){
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
                Component returnComp = super.prepareRenderer(renderer, row, column);
                Color alternateColor = new Color(240,240,240);
                Color whiteColor = Color.WHITE;
                if (!returnComp.getBackground().equals(getSelectionBackground())){
                    Color bg = (row % 2 == 0 ? alternateColor : whiteColor);
                    returnComp .setBackground(bg);
                    bg = null;
                }
                return returnComp;
            }};
            panChart = new javax.swing.JPanel();
            jPanel3 = new javax.swing.JPanel();
            jLabel1 = new javax.swing.JLabel();

            setBackground(new java.awt.Color(255, 255, 255));

            jPanel1.setBackground(new java.awt.Color(255, 255, 255));

            jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

            tbMatchesPlayed.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
            tbMatchesPlayed.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Rank", "<html><center>Team<br/>Name</center></html>", "<html>Total<br/>Matches</html>", "<html><center>Matches<br/>Played</center></html>", "win", "Loss", "<html><center>No<br/>Result</center></html>", "<html><center>Success<br/>Rate</center></html>"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
            jScrollPane1.setViewportView(tbMatchesPlayed);

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addContainerGap())
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
            );

            panChart.setBackground(new java.awt.Color(255, 255, 255));
            panChart.setLayout(new java.awt.BorderLayout());

            jPanel3.setBackground(new java.awt.Color(255, 255, 255));

            jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
            jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jLabel1.setText("Team Reports");

            javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panChart, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE))
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            );
        }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panChart;
    private javax.swing.JTable tbMatchesPlayed;
    // End of variables declaration//GEN-END:variables
}
