/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels.report;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nishant.vibhute
 */
public class PanReportMatch extends javax.swing.JPanel {

    PanMatchReportConsolidated panC;
    PanZoneSkillwiseMain panZ;
    PanZoneRotationMain panR;
    PanTeamReportSkill panT;
    int cb, matchId;
    List<JPanel> panListToPrint = new ArrayList<>();
    LinkedHashMap<JLabel, JPanel> mapMenu = new LinkedHashMap<JLabel, JPanel>();
    String name = "Consolidated";

    /**
     * Creates new form PanReportMatch
     */
    public PanReportMatch(int cb, int matchId) {
        initComponents();
        this.cb = cb;
        this.matchId = matchId;

        mapMenu.put(lblRotation, panRotation);
        mapMenu.put(lblmatch, panMatch);
        mapMenu.put(lblTeam, panTeam);
        mapMenu.put(lblSkil1, panSkill1);
        panC = new PanMatchReportConsolidated(cb, matchId);
        panR = new PanZoneRotationMain(cb, matchId);
        panZ = new PanZoneSkillwiseMain(cb, matchId);
        panT = new PanTeamReportSkill(cb, matchId);
        panListToPrint.add(panC);
        panListToPrint.add(panR);
        panListToPrint.add(panZ);
        panListToPrint.add(panT);
        panReport.add(panC, BorderLayout.CENTER);

        lblmatch.setForeground(Color.BLACK);
        panMatch.setBackground(Color.WHITE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        panMatch = new javax.swing.JPanel();
        lblmatch = new javax.swing.JLabel();
        panRotation = new javax.swing.JPanel();
        lblRotation = new javax.swing.JLabel();
        panTeam = new javax.swing.JPanel();
        lblTeam = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        panSkill1 = new javax.swing.JPanel();
        lblSkil1 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panReport = new javax.swing.JPanel();

        panMatch.setBackground(new java.awt.Color(57, 74, 108));
        panMatch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblmatch.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblmatch.setForeground(new java.awt.Color(255, 255, 255));
        lblmatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblmatch.setText("Consolidated");
        lblmatch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblmatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblmatchMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panMatchLayout = new javax.swing.GroupLayout(panMatch);
        panMatch.setLayout(panMatchLayout);
        panMatchLayout.setHorizontalGroup(
            panMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblmatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panMatchLayout.setVerticalGroup(
            panMatchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblmatch, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panRotation.setBackground(new java.awt.Color(57, 74, 108));
        panRotation.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblRotation.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblRotation.setForeground(new java.awt.Color(255, 255, 255));
        lblRotation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRotation.setText("Rotation Performance");
        lblRotation.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblRotation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRotationMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panRotationLayout = new javax.swing.GroupLayout(panRotation);
        panRotation.setLayout(panRotationLayout);
        panRotationLayout.setHorizontalGroup(
            panRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRotation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panRotationLayout.setVerticalGroup(
            panRotationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRotation, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panTeam.setBackground(new java.awt.Color(57, 74, 108));
        panTeam.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblTeam.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTeam.setText("Best Player Performance");
        lblTeam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTeamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panTeamLayout = new javax.swing.GroupLayout(panTeam);
        panTeam.setLayout(panTeamLayout);
        panTeamLayout.setHorizontalGroup(
            panTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panTeamLayout.setVerticalGroup(
            panTeamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(57, 74, 108));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel11.setBackground(new java.awt.Color(57, 74, 108));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(365, 365, 365)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panSkill1.setBackground(new java.awt.Color(57, 74, 108));
        panSkill1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblSkil1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSkil1.setForeground(new java.awt.Color(255, 255, 255));
        lblSkil1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSkil1.setText("Zone wise Skill Performance");
        lblSkil1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSkil1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSkil1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSkill1Layout = new javax.swing.GroupLayout(panSkill1);
        panSkill1.setLayout(panSkill1Layout);
        panSkill1Layout.setHorizontalGroup(
            panSkill1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSkill1Layout.createSequentialGroup()
                .addComponent(lblSkil1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panSkill1Layout.setVerticalGroup(
            panSkill1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSkil1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMatch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panRotation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSkill1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panMatch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRotation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSkill1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panTeam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/printer.png"))); // NOI18N
        jLabel4.setText("Print");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(736, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panReport.setLayout(new java.awt.BorderLayout());
        jScrollPane1.setViewportView(panReport);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(0, 0, 0))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblRotationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRotationMouseClicked
        // TODO add your handling code here:

        changeColor(evt);

        panReport.removeAll();
        panReport.add(panR, BorderLayout.CENTER);
        setPrint(panR, "Rotation Performance");
        validate();
        repaint();

    }//GEN-LAST:event_lblRotationMouseClicked

    private void lblTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTeamMouseClicked
        // TODO add your handling code here:
        changeColor(evt);

        panReport.removeAll();
        panReport.add(panT, BorderLayout.CENTER);
        setPrint(panT, "Best Player Performance");
        validate();
        repaint();
    }//GEN-LAST:event_lblTeamMouseClicked

    private void lblmatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblmatchMouseClicked
        // TODO add your handling code here:
        changeColor(evt);

        panReport.removeAll();
        panReport.add(panC, BorderLayout.CENTER);
        setPrint(panC, "Consolidated");
        validate();
        repaint();
    }//GEN-LAST:event_lblmatchMouseClicked

    public void changeColor(java.awt.event.MouseEvent evt) {
        JLabel lblClicked = (JLabel) evt.getSource();

        for (Map.Entry<JLabel, JPanel> entry : mapMenu.entrySet()) {

            if (entry.getKey() == lblClicked) {
                entry.getKey().setForeground(Color.BLACK);
                entry.getValue().setBackground(Color.WHITE);
            } else {
                entry.getKey().setForeground(Color.WHITE);
                entry.getValue().setBackground(new Color(57, 74, 108));

            }

        }
    }

    private void lblSkil1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSkil1MouseClicked
        // TODO add your handling code here:
        changeColor(evt);

        panReport.removeAll();
        panReport.add(panZ, BorderLayout.CENTER);
        setPrint(panZ, "Zone wise Skill Performance");
        validate();
        repaint();
    }//GEN-LAST:event_lblSkil1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        printComponenet(panListToPrint, name);
    }//GEN-LAST:event_jLabel4MouseClicked

    public void setPrint(JPanel pan, String name) {
        panListToPrint = new ArrayList<>();
        panListToPrint.add(pan);
        this.name = name;
    }

    public void printComponenet(final List<JPanel> comp, String name) {

        PageFormat documentPageFormat = new PageFormat();
        Paper PAPER = new Paper();
        PAPER.setSize(595.4, 841.69);
        PAPER.setImageableArea(36, 36, 523.4, 769.69);
        documentPageFormat.setPaper(PAPER);
        documentPageFormat.setOrientation(PageFormat.PORTRAIT);

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(name);

        Book book = new Book();

        for (Component c : comp) {
            final Component comp1 = c;
            Printable p1 = new Printable() {

                @Override
                public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//                    if (pageIndex > 0) {
//                        return Printable.NO_SUCH_PAGE;
//                    }

//                format.setOrientation(PageFormat.LANDSCAPE);
                    // get the bounds of the component
                    Dimension dim = comp1.getSize();
                    double cHeight = dim.getHeight();
                    double cWidth = dim.getWidth();

                    // get the bounds of the printable area
                    double pHeight = pageFormat.getImageableHeight();
                    double pWidth = pageFormat.getImageableWidth();

                    double pXStart = pageFormat.getImageableX();
                    double pYStart = pageFormat.getImageableY();

                    double xRatio = pWidth / cWidth;
                    double yRatio = pHeight / cHeight;

                    Graphics2D g2 = (Graphics2D) graphics;
                    g2.translate(pXStart, pYStart);
                    g2.scale(xRatio, yRatio);
                    comp1.printAll(g2);

                    return Printable.PAGE_EXISTS;
                }
            };
            book.append(p1, documentPageFormat);
        }

        pj.setPageable(book);

        if (pj.printDialog() == false) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
        }

//
//        if (pj.printDialog() == false) {
//            return;
//        }
//
//        try {
//            pj.print();
//        } catch (PrinterException ex) {
//            // handle exception
//        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRotation;
    private javax.swing.JLabel lblSkil1;
    private javax.swing.JLabel lblTeam;
    private javax.swing.JLabel lblmatch;
    private javax.swing.JPanel panMatch;
    private javax.swing.JPanel panReport;
    private javax.swing.JPanel panRotation;
    private javax.swing.JPanel panSkill1;
    private javax.swing.JPanel panTeam;
    // End of variables declaration//GEN-END:variables
}
