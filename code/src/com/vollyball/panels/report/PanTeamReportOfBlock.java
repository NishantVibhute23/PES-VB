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
public class PanTeamReportOfBlock extends javax.swing.JPanel {

    String datatr = "";

    ReportDao reportDao = new ReportDao();
    int cb;
    int matchId;
    MatchDao matchDao = new MatchDao();
    int evaluationteamId, evaluationteamId2;
    int team1id, team2id;
    boolean zoomer = true;
    int zoomFactor = 0;
    String html = "<html><head>\n"
            + "<style type=\"text/css\">\n"
            + "table {width: 100%}\n"
            + "td, th {background-color: white;text-align: center;height: 8px;font-family: Arial; font-size: 10pt;}\n"
            + "</style>\n"
            + "</head><body><div style=\"background-color: black\"><table >";

    /**
     * Creates new form PanTeamReportOFAttack
     */
    public PanTeamReportOfBlock(int cb, int matchId) {
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
        String header = htmlOverview + "<tr><th rowspan=3 colspan =2></th><th colspan=12>Defence System</th></tr>"
                + "<tr><th colspan=3>No Block</th>"
                + "<th colspan=3>Single</th>"
                + "<th colspan=3>Double</th>"
                + "<th colspan=3>Triple</th></tr><tr>"
                + "<th width=18>A</th><th width=18>+(%)</th><th width=18>-(%)</th>"
                + "<th width=18>A</th><th width=18>+(%)</th><th width=18>-(%)</th>"
                + "<th width=18>A</th><th width=18>+(%)</th><th width=18>-(%)</th>"
                + "<th width=18>A</th><th width=18>+(%)</th><th width=18>-(%)</th></tr>";
        createComplexOverviewTableData(29, "Type of Attack");
        createComplexOverviewTableData(31, "Tempo");
        createComplexOverviewTableData(28, "Blocking Tactics");
        createComplexOverviewTableData(33, "Blocking Zone");
        createComplexOverviewTableData(36, "Blocker");
        createComplexOverviewTableData(35, "Complex");
        createComplexOverviewTableData(30, "Attack Combination");

        htmlOverview = header + datatr + "</table></div></html>";
        JLabel lbl = new JLabel();
        lbl.setText(htmlOverview);
        panComplex.add(lbl, BorderLayout.CENTER);

    }

    public void createComplexOverviewTableData(int criteriaid, String type) {

        int headerId = 37;
        List<SkillDescCriteriaPoint> lst = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(criteriaid);

        for (int i = 0; i < lst.size(); i++) {
            datatr = datatr + "<tr>";
            if (i == 0) {
                datatr = datatr + "<td width=62 rowspan=" + lst.size() + ">" + type + "</td>";

            }
            datatr = datatr + "<td  width=60>" + lst.get(i).getAbbreviation() + "</td>";

            SuccessFailure sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "NB", Skill.Block.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "SGL", Skill.Block.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "DBL", Skill.Block.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), headerId, "TPL", Skill.Block.getId(), evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";

        }
    }

    public void createServiceSuccessTable() {
        datatr = "";
        String htmlAttackBlock = html;
        String header = htmlAttackBlock + "<tr><th width=75>Type of Block</th>"
                + "<th width=52></th>"
                + "<th width=75>Attack Type</th>"
                + "<th width=75>Tempo</th>"
                + "<th width=75>Block Zone</th>"
                + "<th width=75>Complex</th>"
                + "<th width=75>Attack Combination</th>"
                + "<th width=75>Block Tactics</th></tr>";

        createDefenceSuccessTableData(37);
        htmlAttackBlock = header + datatr + "</table></div></html>";
        JLabel lbl = new JLabel();

        lbl.setText(htmlAttackBlock);
        panServiceSuccess.add(lbl, BorderLayout.CENTER);
    }

    public void createDefenceSuccessTableData(int skillDescIdIn) {

        List<SkillDescCriteriaPoint> lstTempo = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescIdIn);
        for (int j = 0; j < lstTempo.size(); j++) {

            datatr = datatr + "<tr><td rowspan=" + 3 + ">" + lstTempo.get(j).getAbbreviation() + "</td>";
            datatr = datatr + "<td>S</td>"
                    + "<td>" + getValue(29, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(31, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(33, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(35, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(30, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(28, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";

            datatr = datatr + "<td>F+</td>"
                    + "<td>" + getValue(29, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(31, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(33, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(35, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(30, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(28, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";

            datatr = datatr + "<td>F</td>"
                    + "<td>" + getValue(29, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(31, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(33, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(35, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(30, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td>"
                    + "<td>" + getValue(28, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn) + "</td></tr>";
        }

    }

    public String getValue(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId) {
        String value = reportDao.getSkillSuccessForTeam(skillDescCriteriaId, Skill.Block.getId(), evaluationteamId, rowSkillDesc, rowSkillDescId, rating);
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
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panComplex.setBackground(new java.awt.Color(255, 255, 255));
        panComplex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panComplex.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Service : Overview");

        panServiceSuccess.setBackground(new java.awt.Color(255, 255, 255));
        panServiceSuccess.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panServiceSuccess.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Service Success");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(panComplex, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .addComponent(panServiceSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(451, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 843, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panServiceSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel panComplex;
    private javax.swing.JPanel panServiceSuccess;
    // End of variables declaration//GEN-END:variables

}
