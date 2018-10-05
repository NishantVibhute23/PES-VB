/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels.report;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.SuccessFailure;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillDescCriteriaPoint;
import com.vollyball.panels.ImagePanel;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author nishant.vibhute
 */
public class PanTeamReportOfReception extends javax.swing.JPanel {

    String datatr = "";
    ImagePanel imgTPL, imgDBL, imgSGL, imgNB;

    ReportDao reportDao = new ReportDao();
    int cb;
    int matchId;
    MatchDao matchDao = new MatchDao();
    int evaluationteamId, evaluationteamId2;
    int team1id, team2id;

    String html = "<html><head>\n"
            + "<style type=\"text/css\">\n"
            + "table {width: 100%}\n"
            + "td, th {background-color: white;text-align: center;height: 14px}\n"
            + "</style>\n"
            + "</head><body><div style=\"background-color: black\"><table >";

    /**
     * Creates new form PanTeamReportOFAttack
     */
    public PanTeamReportOfReception(int cb, int matchId) {
        initComponents();

        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();
        evaluationteamId = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evaluationteamId2 = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);

        createComplexOverviewTable();
        createAttackBlockOverviewTable();
    }

    public void createComplexOverviewTable() {
        datatr = "";
        String htmlOverview = html;
        String header = htmlOverview + "<tr><th rowspan=3 colspan =2></th><th colspan=12>Reception Formation</th></tr><tr><th colspan=3>2M</th>"
                + "<th colspan=3>3M</th>"
                + "<th colspan=3>4m</th>"
                + "<th colspan=3>5m</th></tr><tr>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th></tr>";
        createComplexOverviewTableData(54, "Type of Service");
        createComplexOverviewTableData(56, "Reception At Zone");
        createComplexOverviewTableData(60, "Reception At");
        createComplexOverviewTableData(59, "Parabola");
        createComplexOverviewTableData(58, "Receiver");
        htmlOverview = header + datatr + "</table></div></html>";
        JLabel lbl = new JLabel();
        lbl.setText(htmlOverview);
        panComplex.add(lbl, BorderLayout.CENTER);

    }

    public void createComplexOverviewTableData(int criteriaid, String type) {

        List<SkillDescCriteriaPoint> lst = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(criteriaid);

        for (int i = 0; i < lst.size(); i++) {
            datatr = datatr + "<tr>";
            if (i == 0) {
                datatr = datatr + "<td width=40 rowspan=" + lst.size() + ">" + type + "</td>";

            }
            datatr = datatr + "<td  width=60>" + lst.get(i).getAbbreviation() + "</td>";
            SuccessFailure sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), 55, "2", 5, evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), 55, "3", 5, evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), 55, "4", 5, evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), 55, "5", 5, evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
        }
    }

    public void createAttackBlockOverviewTable() {
        datatr = "";
        String htmlAttackBlock = html;
        String header = htmlAttackBlock + "<tr><th width=70>Reception Formation</th>"
                + "<th width=32></th>"
                + "<th width=55>Type of Service</th>"
                + "<th width=30>Reception At</th>"
                + "<th width=40>Zone</th>"
                + "<th width=40>Parabola</th>"
                + "<th width=40>Receiver</th></tr>";

        createAttackBlockOverviewTableData(55);
        htmlAttackBlock = header + datatr + "</table></div></html>";
        JLabel lbl = new JLabel();

        lbl.setText(htmlAttackBlock);
        panAttackBlock.add(lbl, BorderLayout.CENTER);
    }

    public void createAttackBlockOverviewTableData(int skillDescIdIn) {
        List<SkillDescCriteriaPoint> lstTempo = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescIdIn);
        for (int j = 0; j < lstTempo.size(); j++) {

            datatr = datatr + "<tr><td rowspan=" + 3 + ">" + lstTempo.get(j).getAbbreviation() + "</td>";
            datatr = datatr + "<td>S</td>"
                    + "<td>" + getValue(54, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(60, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(56, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(59, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(58, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";

            datatr = datatr + "<td>F+</td>"
                    + "<td>" + getValue(54, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(60, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(56, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(59, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(58, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";
            datatr = datatr + "<td>F</td>"
                    + "<td>" + getValue(54, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(60, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(56, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(59, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(58, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";
        }

    }

    public String getValue(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId) {
        String value = reportDao.getSkillSuccessForTeam(skillDescCriteriaId, Skill.Reception.getId(), evaluationteamId, rowSkillDesc, rowSkillDescId, rating);
        return value;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panComplex = new javax.swing.JPanel();
        panAttackBlock = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panTPL = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanDBL = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        PanSBL = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        PanNB = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panComplex.setBackground(new java.awt.Color(255, 255, 255));
        panComplex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panComplex.setLayout(new java.awt.BorderLayout());

        panAttackBlock.setBackground(new java.awt.Color(255, 255, 255));
        panAttackBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panAttackBlock.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Reception : Overview");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Reception Success");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panTPL.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("2M");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panTPL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panTPL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout PanDBLLayout = new javax.swing.GroupLayout(PanDBL);
        PanDBL.setLayout(PanDBLLayout);
        PanDBLLayout.setHorizontalGroup(
            PanDBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanDBLLayout.setVerticalGroup(
            PanDBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("3M");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanDBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanDBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout PanSBLLayout = new javax.swing.GroupLayout(PanSBL);
        PanSBL.setLayout(PanSBLLayout);
        PanSBLLayout.setHorizontalGroup(
            PanSBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanSBLLayout.setVerticalGroup(
            PanSBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("4M");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanSBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanSBL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout PanNBLayout = new javax.swing.GroupLayout(PanNB);
        PanNB.setLayout(PanNBLayout);
        PanNBLayout.setHorizontalGroup(
            PanNBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanNBLayout.setVerticalGroup(
            PanNBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("5M");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanNB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PanNB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Abbreavations:");

        jLabel4.setText("  A      : Total Attempt in number");

        jLabel5.setText("+(%) : Successful attempt in Percentage");

        jLabel6.setText("-(%)  : Failure attempt in Percentage");

        jLabel7.setText("S : Successful");

        jLabel8.setText("F+ : Favourable");

        jLabel9.setText("F : Failure");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Diagramtic Presentation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)))
                    .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panAttackBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(885, 885, 885))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panAttackBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(169, 169, 169)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanDBL;
    private javax.swing.JPanel PanNB;
    private javax.swing.JPanel PanSBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel panAttackBlock;
    private javax.swing.JPanel panComplex;
    private javax.swing.JPanel panTPL;
    // End of variables declaration//GEN-END:variables
}
