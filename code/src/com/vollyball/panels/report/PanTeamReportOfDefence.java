/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels.report;

import com.vollyball.bean.DigTrianglePoints;
import com.vollyball.bean.MatchBean;
import com.vollyball.bean.SuccessFailure;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillDescCriteriaPoint;
import com.vollyball.panels.ImagePanel;
import com.vollyball.util.CommonUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
    LinkedHashMap<Integer, ImagePanel> imgId = new LinkedHashMap<>();
    LinkedHashMap<ImagePanel, JPanel> imgPan = new LinkedHashMap<>();

    ImagePanel imgON, imgCN, imgAN, imgLT;

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

        imgON = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(0, imgON);
        imgPan.put(imgON, panON);

        imgCN = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(1, imgCN);
        imgPan.put(imgCN, panCN);

        imgAN = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(2, imgAN);
        imgPan.put(imgAN, panAN);

        imgLT = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(3, imgLT);
        imgPan.put(imgLT, panLT);

        createDiagram(75);

    }

    public void createDiagram(int skillDescIdIn) {
        List<SkillDescCriteriaPoint> lstTempo = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescIdIn);

        for (int j = 0; j < lstTempo.size(); j++) {

            ImagePanel img = imgId.get(j);
            JPanel pan = imgPan.get(img);
            pan.setLayout(new BorderLayout());
            pan.add(img, BorderLayout.CENTER);

            List<DigTrianglePoints> points = new ArrayList();

            String fromValSuc = CommonUtil.getValue(72, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId());
            if (!fromValSuc.isEmpty()) {
                String toValSuc = getValueForDig(73, 5, fromValSuc, 72, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                DigTrianglePoints pointSuc = new DigTrianglePoints("H" + fromValSuc + "A", "H" + toValSuc + "A", "H" + toValSuc + "C", Color.GREEN);
                points.add(pointSuc);
            }
            String fromValFav = CommonUtil.getValue(72, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId());
            if (!fromValFav.isEmpty()) {
                String toValFav = getValueForDig(73, 4, fromValFav, 72, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                DigTrianglePoints pointFav = new DigTrianglePoints("H" + fromValFav + "A", "H" + toValFav + "A", "H" + toValFav + "C", Color.ORANGE);
                points.add(pointFav);
            }

            String fromValFail = CommonUtil.getValue(72, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId());
            if (!fromValFail.isEmpty()) {
                String toValFail = getValueForDig(73, 1, fromValFail, 72, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                DigTrianglePoints pointFail = new DigTrianglePoints("H" + fromValFail + "A", "H" + toValFail + "A", "H" + toValFail + "C", Color.RED);
                points.add(pointFail);
            }

            if (points.size() != 0) {
                img.drawTriangle(points);
            }
        }
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
                    + "<td>" + CommonUtil.getValue(77, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(69, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(73, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(76, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(74, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(71, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td></tr>";

            datatr = datatr + "<td>F+</td>"
                    + "<td>" + CommonUtil.getValue(77, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(69, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(73, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(76, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(74, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(71, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td></tr>";

            datatr = datatr + "<td>F</td>"
                    + "<td>" + CommonUtil.getValue(77, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(69, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(73, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(76, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(74, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(71, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Defence.getId()) + "</td></tr>";
        }
//        }

    }

    public String getValueForDig(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId, String colSkillDesc, int colSkillDescId) {
        String value = reportDao.getSkillSuccessForTeamWithBlock(skillDescCriteriaId, Skill.Defence.getId(), evaluationteamId, rowSkillDesc, rowSkillDescId, colSkillDesc, colSkillDescId, rating);
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
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panON = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        panCN = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        panAN = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        panLT = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panComplex.setBackground(new java.awt.Color(255, 255, 255));
        panComplex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panComplex.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Defence : Overview");

        panServiceSuccess.setBackground(new java.awt.Color(255, 255, 255));
        panServiceSuccess.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panServiceSuccess.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Defence Success");
        jLabel2.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panON.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("ON");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panON, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panCNLayout = new javax.swing.GroupLayout(panCN);
        panCN.setLayout(panCNLayout);
        panCNLayout.setHorizontalGroup(
            panCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panCNLayout.setVerticalGroup(
            panCNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("CN");
        jLabel12.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panCN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(panCN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panANLayout = new javax.swing.GroupLayout(panAN);
        panAN.setLayout(panANLayout);
        panANLayout.setHorizontalGroup(
            panANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panANLayout.setVerticalGroup(
            panANLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("AN");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panAN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(panAN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout panLTLayout = new javax.swing.GroupLayout(panLT);
        panLT.setLayout(panLTLayout);
        panLTLayout.setHorizontalGroup(
            panLTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panLTLayout.setVerticalGroup(
            panLTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LT");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(panLT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panLT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
            .addComponent(jLabel2)
            .addComponent(panServiceSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panServiceSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel panAN;
    private javax.swing.JPanel panCN;
    private javax.swing.JPanel panComplex;
    private javax.swing.JPanel panLT;
    private javax.swing.JPanel panON;
    private javax.swing.JPanel panServiceSuccess;
    // End of variables declaration//GEN-END:variables
}
