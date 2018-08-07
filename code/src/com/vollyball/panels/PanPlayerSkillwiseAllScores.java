/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.PlayerSkillScore;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.dialog.DialogAllScoreGraph;
import com.vollyball.dialog.DialogBarChart;
import com.vollyball.enums.Skill;
import com.vollyball.renderer.TableHeaderRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
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
public class PanPlayerSkillwiseAllScores extends javax.swing.JPanel {

    ReportDao reportDao = new ReportDao();
    DefaultTableModel model, modelMatch;
    DefaultTableCellRenderer centerRenderer;
    List<PlayerSkillScore> playerDetails;
    String playerName, teamName;
    String skillName;
    int attempt;
    RallyDao rd = new RallyDao();
    LinkedHashMap<String, Integer> matchMap = new LinkedHashMap<>();

    /**
     * Creates new form PanPlayerSkillwiseAllScores
     */
    public PanPlayerSkillwiseAllScores(int compId, final String playerName, final int playerId, final String teamName, final Skill sk) {
        initComponents();
        this.playerName = playerName;
        this.teamName = teamName;
        this.skillName = sk.getType();

        centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        model = (DefaultTableModel) tbOverall.getModel();
        modelMatch = (DefaultTableModel) tbMatchwise.getModel();
        PlayerSkillScore pservice = reportDao.getPlayerSkillWiseScoreReport(compId, playerId, sk.getId(), 0, 0);
        playerDetails = reportDao.getPlayerSkillWiseAllScoreReportbyMatch(compId, playerId, sk.getId());
        this.attempt = pservice.getTotalAttempt();
        Object[] row = {pservice.getMatchId(), pservice.getTotalAttempt(), pservice.getOne(), pservice.getTwo(), pservice.getThree(), pservice.getFour(), pservice.getFive()};
        model.addRow(row);
        lblName.setText(playerName);
        lblSkill.setText(sk.getType());
        lblTeamName.setText(teamName);

        int i = 0;
        for (PlayerSkillScore pb : playerDetails) {

            matchMap.put(pb.getMatchName() + "-" + pb.getPhase(), pb.getMatchId());
            i++;
            Object[] rowMatch = {i, pb.getMatchName(), pb.getPhase(), pb.getTotalAttempt(), pb.getOne(), pb.getTwo(), pb.getThree(), pb.getFour(), pb.getFive()};
            modelMatch.addRow(rowMatch);
        }

        Color heading = new Color(204, 204, 204);
        JTableHeader header = tbOverall.getTableHeader();
        header.setBackground(heading);
        header.setOpaque(false);
        header.setPreferredSize(new Dimension(100, 31));
        header.setDefaultRenderer(new TableHeaderRenderer(tbOverall));

        JTableHeader header1 = tbMatchwise.getTableHeader();
        header1.setBackground(heading);
        header1.setOpaque(false);
        header1.setPreferredSize(new Dimension(100, 30));
        header1.setDefaultRenderer(new TableHeaderRenderer(tbMatchwise));
        resizeColumnsMatch();
        resizeColumnsOverall();

        tbOverall.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    DialogAllScoreGraph obj = new DialogAllScoreGraph();
                    obj.init(playerId, sk, playerName, teamName, 0, "All", "All");
                    obj.show();
                }
            }
        });

        tbMatchwise.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {

                    String selectedName = null, phase = "";

                    String name = "";

                    int selectedRow = tbMatchwise.getSelectedRow();
                    for (int i = 0; i <= selectedRow; i++) {

                        selectedName = (String) tbMatchwise.getValueAt(selectedRow, 1);
                        phase = (String) tbMatchwise.getValueAt(selectedRow, 2);
                        name = selectedName + "-" + phase;
                    }
                    if (!name.equals("")) {
                        int matchId = matchMap.get(name);
                        DialogAllScoreGraph obj = new DialogAllScoreGraph();
                        obj.init(playerId, sk, playerName, phase, matchId, selectedName, phase);
                        obj.show();
                    }

                }
            }
        });
    }

    float[] columnWidthPercentageMatch = {5.0f, 30.0f, 25.0f, 10.0f, 8.0f, 8.0f, 8.0f, 8.0f, 8.0f};

    private void resizeColumnsMatch() {
        int tW = tbMatchwise.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbMatchwise.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            column.setCellRenderer(centerRenderer);
            int pWidth = Math.round(columnWidthPercentageMatch[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    float[] columnWidthPercentageOverall = {40.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f};

    private void resizeColumnsOverall() {
        int tW = tbOverall.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbOverall.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            column.setCellRenderer(centerRenderer);
            int pWidth = Math.round(columnWidthPercentageOverall[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void printComponenet(final Component comp) {

        PageFormat documentPageFormat = new PageFormat();
        documentPageFormat.setOrientation(PageFormat.LANDSCAPE);

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Score Report");

        pj.setPrintable(new Printable() {
            public int print(Graphics g, PageFormat format, int page_index)
                    throws PrinterException {
                if (page_index > 0) {
                    return Printable.NO_SUCH_PAGE;
                }

                format.setOrientation(PageFormat.LANDSCAPE);
                // get the bounds of the component
                Dimension dim = comp.getSize();
                double cHeight = dim.getHeight();
                double cWidth = dim.getWidth();

                // get the bounds of the printable area
                double pHeight = format.getImageableHeight();
                double pWidth = format.getImageableWidth();

                double pXStart = format.getImageableX();
                double pYStart = format.getImageableY();

                double xRatio = pWidth / cWidth;
                double yRatio = pHeight / cHeight;

                Graphics2D g2 = (Graphics2D) g;
                g2.translate(pXStart, pYStart);
                g2.scale(xRatio, yRatio);
                comp.printAll(g2);

                return Printable.PAGE_EXISTS;
            }
        }, documentPageFormat);
        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
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

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMatchwise = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        showGraph = new javax.swing.JPanel();
        lblGraph = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblHeading = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbOverall = new javax.swing.JTable();
        lblSkill = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTeamName = new javax.swing.JLabel();
        panPrint = new javax.swing.JPanel();
        lblPrint = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbMatchwise.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbMatchwise.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SR No.", "MATCH", "PHASE", "TOTAL ATTEMPT", "ONE", "TWO", "THREE", "FOUR", "FIVE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHeading.setText("OVERALL : ");

        tbOverall.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        tbOverall.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MATCHES PLAYED", "TOTAL ATTEMPT", "ONE", "TWO", "THREE", "FOUR", "FIVE"
            }
        ));
        tbOverall.setRowHeight(28);
        jScrollPane1.setViewportView(tbOverall);

        lblSkill.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSkill.setText("jLabel5");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblHeading)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSkill, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHeading)
                    .addComponent(lblSkill))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        lblName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Player Name : ");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Team Name : ");

        lblTeamName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblTeamName.setForeground(new java.awt.Color(255, 255, 255));
        lblTeamName.setText("jLabel4");

        panPrint.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrint.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/printer.png"))); // NOI18N
        lblPrint.setToolTipText("Print This Page");
        lblPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrintMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPrintLayout = new javax.swing.GroupLayout(panPrint);
        panPrint.setLayout(panPrintLayout);
        panPrintLayout.setHorizontalGroup(
            panPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );
        panPrintLayout.setVerticalGroup(
            panPrintLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTeamName, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panPrint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTeamName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(panPrint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void lblGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblGraphMouseClicked
        // TODO add your handling code here:

        DialogBarChart obj = new DialogBarChart();
        obj.init(playerDetails, playerName, teamName, skillName, attempt);
        obj.show();
    }//GEN-LAST:event_lblGraphMouseClicked

    private void showGraphMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showGraphMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_showGraphMouseClicked

    private void lblPrintMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrintMouseClicked
        // TODO add your handling code here:
        panPrint.setVisible(false);

        printComponenet(this);
        panPrint.setVisible(true);
    }//GEN-LAST:event_lblPrintMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGraph;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPrint;
    private javax.swing.JLabel lblSkill;
    private javax.swing.JLabel lblTeamName;
    private javax.swing.JPanel panPrint;
    private javax.swing.JPanel showGraph;
    private javax.swing.JTable tbMatchwise;
    private javax.swing.JTable tbOverall;
    // End of variables declaration//GEN-END:variables
}
