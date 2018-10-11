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
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author nishant.vibhute
 */
public class PanTeamReportOfDefence extends javax.swing.JPanel {

    String datatr = "";

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
    public PanTeamReportOfDefence(int cb, int matchId) {
        initComponents();

        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();
        evaluationteamId = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evaluationteamId2 = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);
        createComplexOverviewTable();
        createServiceSuccessTable();

    }

    public void createComplexOverviewTable() {
        datatr = "";
        String htmlOverview = html;
        String header = htmlOverview + "<tr><th rowspan=3 colspan =2></th><th colspan=27>Defence System</th></tr>"
                + "<tr><th colspan=3>1-2-3</th>"
                + "<th colspan=3>1-3-2</th>"
                + "<th colspan=3>2-1-2</th>"
                + "<th colspan=3>2-1-3</th>"
                + "<th colspan=3>2-0-4</th>"
                + "<th colspan=3>3-1-2</th>"
                + "<th colspan=3>3-0-3</th>"
                + "<th colspan=3>2-1-3</th>"
                + "<th colspan=3>NDS</th></tr><tr>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th></tr>";
        createComplexOverviewTableData(69, "Type of Block");
        createComplexOverviewTableData(73, "Zone");
        createComplexOverviewTableData(75, "Defence At");
        createComplexOverviewTableData(76, "Parabola");
        createComplexOverviewTableData(74, "Defender");
        createComplexOverviewTableData(77, "Complex");

        htmlOverview = header + datatr + "</table></div></html>";
        JLabel lbl = new JLabel();
        lbl.setText(htmlOverview);
        panComplex.add(lbl, BorderLayout.CENTER);

    }

    public void createComplexOverviewTableData(int criteriaid, String type) {

        int headerId = 71;
        List<SkillDescCriteriaPoint> lst = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(criteriaid);

        for (int i = 0; i < lst.size(); i++) {
            datatr = datatr + "<tr>";
            if (i == 0) {
                datatr = datatr + "<td width=62 rowspan=" + lst.size() + ">" + type + "</td>";

            }
            datatr = datatr + "<td  width=60>" + lst.get(i).getAbbreviation() + "</td>";

            SuccessFailure sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "1-2-3", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "1-3-2", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "2-1-2", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "2-1-3", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";

            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "2-0-4", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "3-1-2", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "3-0-3", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "2-1-3", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";

            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "Non Organised", Skill.Defence.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";

        }
    }

    public void createServiceSuccessTable() {
        datatr = "";
        String htmlAttackBlock = html;
        String header = htmlAttackBlock + "<tr><th width=75>Defence At</th>"
                + "<th width=52></th>"
                + "<th width=75>Complex</th>"
                + "<th width=75>Type of Block</th>"
                + "<th width=75>Defence Ball At zone</th>"
                + "<th width=75>Ball Parabola</th>"
                + "<th width=75>Defender</th>"
                + "<th width=75>Defence System</th></tr>";

        createDefenceSuccessTableData(75);
        htmlAttackBlock = header + datatr + "</table></div></html>";
        JLabel lbl = new JLabel();

        lbl.setText(htmlAttackBlock);
        panServiceSuccess.add(lbl, BorderLayout.CENTER);
    }

    public void createDefenceSuccessTableData(int skillDescIdIn) {
//        List<SkillDescCriteriaPoint> lstBlock = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(1);

//        for (int i = 0; i < lstBlock.size(); i++) {
//            datatr = datatr + "<tr><td colspan=7>" + lstBlock.get(i).getAbbreviation() + "</td></tr>";
        List<SkillDescCriteriaPoint> lstTempo = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescIdIn);
        for (int j = 0; j < lstTempo.size(); j++) {

            datatr = datatr + "<tr><td rowspan=" + 3 + ">" + lstTempo.get(j).getAbbreviation() + "</td>";
            datatr = datatr + "<td>S</td>"
                    + "<td>" + getValue(77, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(69, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(73, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(76, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(74, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(71, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";

            datatr = datatr + "<td>F+</td>"
                    + "<td>" + getValue(77, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(69, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(73, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(76, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(74, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(71, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";

            datatr = datatr + "<td>F</td>"
                    + "<td>" + getValue(77, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(69, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(73, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(76, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(74, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(71, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";
        }
//        }

    }

    public String getValue(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId) {
        String value = reportDao.getSkillSuccessForTeam(skillDescCriteriaId, Skill.Defence.getId(), evaluationteamId, rowSkillDesc, rowSkillDescId, rating);
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
        jLabel1 = new javax.swing.JLabel();
        panServiceSuccess = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panComplex.setBackground(new java.awt.Color(255, 255, 255));
        panComplex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panComplex.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Service : Overview");

        panServiceSuccess.setBackground(new java.awt.Color(255, 255, 255));
        panServiceSuccess.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panServiceSuccess.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Abbreavations:");

        jLabel4.setText("  A      : Total Attempt in number");

        jLabel5.setText("+(%) : Successful attempt in Percentage");

        jLabel6.setText("-(%)  : Failure attempt in Percentage");

        jLabel7.setText("S : Successful");

        jLabel9.setText("F : Failure");

        jLabel8.setText("F+ : Favourable");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Service Success");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panComplex, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                        .addGap(386, 386, 386))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(panServiceSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 363, Short.MAX_VALUE))
                        .addGap(656, 656, 656)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(panComplex, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panServiceSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(397, 397, 397)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panComplex;
    private javax.swing.JPanel panServiceSuccess;
    // End of variables declaration//GEN-END:variables
}
