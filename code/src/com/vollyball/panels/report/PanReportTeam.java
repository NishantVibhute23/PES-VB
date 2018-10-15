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
public class PanReportTeam extends javax.swing.JPanel {

    LinkedHashMap<JLabel, JPanel> mapMenu = new LinkedHashMap<JLabel, JPanel>();
    int cb;
    int matchId;
    List<Component> panListToPrint = new ArrayList<>();
    String name = "Service";

    /**
     * Creates new form PanReportTeam
     */
    public PanReportTeam(int cb, int matchId) {
        initComponents();
        this.cb = cb;
        this.matchId = matchId;
        mapMenu.put(lblService, panService);
        mapMenu.put(lblAttack, panAttack);
        mapMenu.put(lblBlock, panBlock);
        mapMenu.put(lblSet, panSet);
        mapMenu.put(lblReception, panReception);
        mapMenu.put(lblDefence, panDefence);

        PanTeamReportOfService panTeamReportOfService = new PanTeamReportOfService(cb, matchId);
        PanTeamReportOfAttack panTeamReportOFAttack = new PanTeamReportOfAttack(cb, matchId);
        PanTeamReportOfBlock panTeamReportOfBlock = new PanTeamReportOfBlock(cb, matchId);
        PanTeamReportOfReception panTeamReportOfReception = new PanTeamReportOfReception(cb, matchId);
        PanTeamReportOfSet panTeamReportOfSet = new PanTeamReportOfSet(cb, matchId);
        PanTeamReportOfDefence panTeamReportOfDefence = new PanTeamReportOfDefence(cb, matchId);

        panListToPrint.add(panTeamReportOfService);
        panListToPrint.add(panTeamReportOFAttack);
        panListToPrint.add(panTeamReportOfBlock);
        panListToPrint.add(panTeamReportOfReception);
        panListToPrint.add(panTeamReportOfSet);
        panListToPrint.add(panTeamReportOfDefence);

        panView.add(panTeamReportOfService, BorderLayout.CENTER);
        lblService.setForeground(Color.BLACK);
        panService.setBackground(Color.WHITE);

    }

    public void printComponenet(final List<Component> comp, String name) {

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
        panService = new javax.swing.JPanel();
        lblService = new javax.swing.JLabel();
        panAttack = new javax.swing.JPanel();
        lblAttack = new javax.swing.JLabel();
        panBlock = new javax.swing.JPanel();
        lblBlock = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        panReception = new javax.swing.JPanel();
        lblReception = new javax.swing.JLabel();
        panSet = new javax.swing.JPanel();
        lblSet = new javax.swing.JLabel();
        panDefence = new javax.swing.JPanel();
        lblDefence = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panReport = new javax.swing.JPanel();
        panView = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        panService.setBackground(new java.awt.Color(57, 74, 108));
        panService.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblService.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblService.setForeground(new java.awt.Color(255, 255, 255));
        lblService.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblService.setText("Service");
        lblService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblServiceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panServiceLayout = new javax.swing.GroupLayout(panService);
        panService.setLayout(panServiceLayout);
        panServiceLayout.setHorizontalGroup(
            panServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panServiceLayout.setVerticalGroup(
            panServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblService, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panAttack.setBackground(new java.awt.Color(57, 74, 108));
        panAttack.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblAttack.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAttack.setForeground(new java.awt.Color(255, 255, 255));
        lblAttack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttack.setText("Attack");
        lblAttack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAttackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAttackLayout = new javax.swing.GroupLayout(panAttack);
        panAttack.setLayout(panAttackLayout);
        panAttackLayout.setHorizontalGroup(
            panAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panAttackLayout.setVerticalGroup(
            panAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttack, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panBlock.setBackground(new java.awt.Color(57, 74, 108));
        panBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblBlock.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblBlock.setForeground(new java.awt.Color(255, 255, 255));
        lblBlock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlock.setText("Block");
        lblBlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBlockMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panBlockLayout = new javax.swing.GroupLayout(panBlock);
        panBlock.setLayout(panBlockLayout);
        panBlockLayout.setHorizontalGroup(
            panBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panBlockLayout.setVerticalGroup(
            panBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlock, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(57, 74, 108));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel11.setBackground(new java.awt.Color(57, 74, 108));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PRINT All");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(192, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panReception.setBackground(new java.awt.Color(57, 74, 108));
        panReception.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblReception.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblReception.setForeground(new java.awt.Color(255, 255, 255));
        lblReception.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReception.setText("Reception");
        lblReception.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReceptionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panReceptionLayout = new javax.swing.GroupLayout(panReception);
        panReception.setLayout(panReceptionLayout);
        panReceptionLayout.setHorizontalGroup(
            panReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReception, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
        );
        panReceptionLayout.setVerticalGroup(
            panReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReception, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panSet.setBackground(new java.awt.Color(57, 74, 108));
        panSet.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblSet.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSet.setForeground(new java.awt.Color(255, 255, 255));
        lblSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSet.setText("Set");
        lblSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSetLayout = new javax.swing.GroupLayout(panSet);
        panSet.setLayout(panSetLayout);
        panSetLayout.setHorizontalGroup(
            panSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSetLayout.setVerticalGroup(
            panSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSet, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panDefence.setBackground(new java.awt.Color(57, 74, 108));
        panDefence.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        lblDefence.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDefence.setForeground(new java.awt.Color(255, 255, 255));
        lblDefence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDefence.setText("Defence");
        lblDefence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDefenceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDefenceLayout = new javax.swing.GroupLayout(panDefence);
        panDefence.setLayout(panDefenceLayout);
        panDefenceLayout.setHorizontalGroup(
            panDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panDefenceLayout.setVerticalGroup(
            panDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefence, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panReception, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panDefence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        panReport.setLayout(new java.awt.BorderLayout());

        panView.setLayout(new java.awt.BorderLayout());
        panReport.add(panView, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setViewportView(panReport);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PRINT");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(694, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblServiceMouseClicked
        // TODO add your handling code here:
        changeColor(evt);
        panView.removeAll();
        PanTeamReportOfService panTeamReportOfService = new PanTeamReportOfService(cb, matchId);
        panView.add(panTeamReportOfService, BorderLayout.CENTER);
        validate();
        repaint();
    }//GEN-LAST:event_lblServiceMouseClicked

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

    private void lblAttackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttackMouseClicked
        // TODO add your handling code here:
        changeColor(evt);
        panView.removeAll();
        PanTeamReportOfAttack panTeamReportOFAttack = new PanTeamReportOfAttack(cb, matchId);
        panView.add(panTeamReportOFAttack, BorderLayout.CENTER);
        setPrint(panTeamReportOFAttack, "Attack");
        validate();
        repaint();
    }//GEN-LAST:event_lblAttackMouseClicked

    public void setPrint(JPanel pan, String name) {
        panListToPrint = new ArrayList<>();
        panListToPrint.add(pan);
        this.name = name;
    }

    private void lblBlockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBlockMouseClicked
        // TODO add your handling code here:
        changeColor(evt);
        panView.removeAll();
        PanTeamReportOfBlock panTeamReportOfBlock = new PanTeamReportOfBlock(cb, matchId);
        panView.add(panTeamReportOfBlock, BorderLayout.CENTER);

        setPrint(panTeamReportOfBlock, "Block");
        validate();
        repaint();
    }//GEN-LAST:event_lblBlockMouseClicked

    private void lblReceptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReceptionMouseClicked
        changeColor(evt);
        panView.removeAll();
        PanTeamReportOfReception panTeamReportOfReception = new PanTeamReportOfReception(cb, matchId);
        panView.add(panTeamReportOfReception, BorderLayout.CENTER);
        setPrint(panTeamReportOfReception, "Reception");
        validate();
        repaint();
    }//GEN-LAST:event_lblReceptionMouseClicked

    private void lblSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetMouseClicked
        changeColor(evt);
        panView.removeAll();
        PanTeamReportOfSet panTeamReportOfSet = new PanTeamReportOfSet(cb, matchId);
        panView.add(panTeamReportOfSet, BorderLayout.CENTER);
        setPrint(panTeamReportOfSet, "Set");
        validate();
        repaint();
    }//GEN-LAST:event_lblSetMouseClicked

    private void lblDefenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDefenceMouseClicked
        changeColor(evt);
        panView.removeAll();
        PanTeamReportOfDefence panTeamReportOfDefence = new PanTeamReportOfDefence(cb, matchId);
        panView.add(panTeamReportOfDefence, BorderLayout.CENTER);
        setPrint(panTeamReportOfDefence, "Defence");
        validate();
        repaint();
    }//GEN-LAST:event_lblDefenceMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        printComponenet(panListToPrint, name);
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        printComponenet(panListToPrint, name);
    }//GEN-LAST:event_jLabel5MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAttack;
    private javax.swing.JLabel lblBlock;
    private javax.swing.JLabel lblDefence;
    private javax.swing.JLabel lblReception;
    private javax.swing.JLabel lblService;
    private javax.swing.JLabel lblSet;
    private javax.swing.JPanel panAttack;
    private javax.swing.JPanel panBlock;
    private javax.swing.JPanel panDefence;
    private javax.swing.JPanel panReception;
    private javax.swing.JPanel panReport;
    private javax.swing.JPanel panService;
    private javax.swing.JPanel panSet;
    private javax.swing.JPanel panView;
    // End of variables declaration//GEN-END:variables
}
