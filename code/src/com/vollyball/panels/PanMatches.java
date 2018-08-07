/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

/**
 *
 * @author nishant.vibhute
 */
public class PanMatches extends javax.swing.JPanel {

    public PanMatchListValue panMatchListValue;

    /**
     * public PanCompListValue panCompListValue; Creates new form PanMatches
     */
    public PanMatches() {
        initComponents();
        panMatchListValue = new PanMatchListValue();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
        }

//        panMatchListValue.setBounds(0, 0, 749, 433);
        panMatchContent.add(panMatchListValue, BorderLayout.CENTER);
    }

    public class PanMatchListValue extends JPanel {

        MatchDao matchDao = new MatchDao();
        private JPanel mainList;

        public PanMatchListValue() {
            setLayout(new BorderLayout());
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
            add(new JScrollPane(mainList));

            for (int i = 0; i <= 10; i++) {

            }

            List<MatchBean> matchList = matchDao.getMatches(Controller.competitionId);
            String prevdate = "";
            for (int i = 0; i < matchList.size(); i++) {
                MatchBean mb = matchList.get(i);
                String date = mb.getDate();
                if (prevdate.equals("")) {
                    prevdate = date;
                }

                if (!prevdate.equals(date)) {
                    PanMatchRowDate panDate = new PanMatchRowDate();
                    panDate.setDate(prevdate);
                    GridBagConstraints gbcRow = new GridBagConstraints();
                    gbcRow.gridwidth = GridBagConstraints.REMAINDER;
                    gbcRow.weightx = 1;
                    gbcRow.gridheight = 2;
                    gbcRow.fill = GridBagConstraints.HORIZONTAL;
                    mainList.add(panDate, gbcRow, 0);
                    validate();
                    repaint();
                    prevdate = date;
                }
                PanMatchRow panel = new PanMatchRow();
                panel.setMatch1(mb);
//                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                GridBagConstraints gbcRow1 = new GridBagConstraints();
                gbcRow1.gridwidth = GridBagConstraints.REMAINDER;
                gbcRow1.weightx = 1;
                gbcRow1.gridheight = 2;
                gbcRow1.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(panel, gbcRow1, 0);
                validate();
                repaint();

                if ((i + 1) != matchList.size()) {
                    MatchBean mb2 = matchList.get(i + 1);
                    String date2 = mb2.getDate();
                    if (date.equals(date2)) {
                        panel.setMatch2(mb2);
//                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                        GridBagConstraints gbcRow2 = new GridBagConstraints();
                        gbcRow2.gridwidth = GridBagConstraints.REMAINDER;
                        gbcRow2.weightx = 1;
                        gbcRow2.gridheight = 2;
                        gbcRow2.fill = GridBagConstraints.HORIZONTAL;
                        mainList.add(panel, gbcRow2, 0);
                        validate();
                        repaint();
                        i = i + 1;
                    }
                }

            }
            PanMatchRowDate panDate = new PanMatchRowDate();
            panDate.setDate(prevdate);
            GridBagConstraints gbcRow = new GridBagConstraints();
            gbcRow.gridwidth = GridBagConstraints.REMAINDER;
            gbcRow.weightx = 1;
            gbcRow.gridheight = 2;
            gbcRow.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panDate, gbcRow, 0);
            validate();
            repaint();

        }

//        public void add() {
//            mainList.removeAll();
//            List<CompetitionBean> competitionList = competitionDao.getCompetitionList();
//            for (CompetitionBean cb : competitionList) {
//                PanCompRow panel = new PanCompRow(cb);
////                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
//                GridBagConstraints gbcRow = new GridBagConstraints();
//                gbcRow.gridwidth = GridBagConstraints.REMAINDER;
//                gbcRow.weightx = 1;
//                gbcRow.fill = GridBagConstraints.HORIZONTAL;
//                mainList.add(panel, gbcRow, 0);
//                validate();
//                repaint();
//
//            }
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

        panMatchContent = new javax.swing.JPanel();

        panMatchContent.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMatchContent, javax.swing.GroupLayout.DEFAULT_SIZE, 629, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMatchContent, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel panMatchContent;
    // End of variables declaration//GEN-END:variables
}
