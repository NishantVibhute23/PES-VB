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
public class PanTeamReportOfAttack extends javax.swing.JPanel {

    String datatr = "";
    ImagePanel imgTPL, imgDBL, imgSGL, imgNB;
    ReportDao reportDao = new ReportDao();

    int cb;
    int matchId;
    MatchDao matchDao = new MatchDao();

    int evaluationteamId, evaluationteamId2;
    int team1id, team2id;

    LinkedHashMap<Integer, ImagePanel> imgId = new LinkedHashMap<>();
    LinkedHashMap<ImagePanel, JPanel> imgPan = new LinkedHashMap<>();

    String html = "<html><head>\n"
            + "<style type=\"text/css\">\n"
            + "table {width: 100%}\n"
            + "td, th {background-color: white;text-align: center;height: 14px}\n"
            + "</style>\n"
            + "</head><body><div style=\"background-color: black\"><table >";

    /**
     * Creates new form PanTeamReportOFAttack
     */
    public PanTeamReportOfAttack(int cb, int matchId) {
        initComponents();

        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);

//        team1Name.setText(team.getTeam1name());
//        team2Name.setText(team.getTeam2name());
        team1id = team.getTeam1();
        team2id = team.getTeam2();

        evaluationteamId = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);

        evaluationteamId2 = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);

        createComplexOverviewTable();
        createAttackBlockOverviewTable();

        imgSGL = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(0, imgSGL);
        imgPan.put(imgSGL, PanSBL);

        imgDBL = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);

        imgId.put(1, imgDBL);
        imgPan.put(imgDBL, PanDBL);

        imgTPL = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(2, imgTPL);
        imgPan.put(imgTPL, panTPL);

        imgNB = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);

        imgId.put(3, imgNB);
        imgPan.put(imgNB, PanNB);

        createDiagram(19);
    }

    public void createDiagram(int skillDescIdIn) {
        List<SkillDescCriteriaPoint> lstTempo = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescIdIn);

        for (int j = 0; j < lstTempo.size(); j++) {

            ImagePanel img = imgId.get(j);
            JPanel pan = imgPan.get(img);
            pan.setLayout(new BorderLayout());
            pan.add(img, BorderLayout.CENTER);

            List<DigTrianglePoints> points = new ArrayList();

            String fromValSuc = CommonUtil.getValue(15, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Attack.getId());
            if (!fromValSuc.isEmpty()) {
                String toValSuc = getValueForDig(16, 5, fromValSuc, 15, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                if (!toValSuc.isEmpty()) {
                    DigTrianglePoints pointSuc = new DigTrianglePoints("H" + fromValSuc + "A", "O" + toValSuc + "A", "O" + toValSuc + "C", Color.GREEN);
                    points.add(pointSuc);
                }
            }
            String fromValFav = CommonUtil.getValue(15, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Attack.getId());
            if (!fromValFav.isEmpty()) {
                String toValFav = getValueForDig(16, 4, fromValFav, 15, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                if (!toValFav.isEmpty()) {
                    DigTrianglePoints pointFav = new DigTrianglePoints("H" + fromValFav + "A", "O" + toValFav + "A", "O" + toValFav + "C", Color.ORANGE);
                    points.add(pointFav);
                }
            }

            String fromValFail = CommonUtil.getValue(15, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Attack.getId());
            if (!fromValFail.isEmpty()) {
                String toValFail = getValueForDig(16, 1, fromValFail, 15, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                if (!toValFail.isEmpty()) {
                    DigTrianglePoints pointFail = new DigTrianglePoints("H" + fromValFail + "A", "O" + toValFail + "A", "O" + toValFail + "C", Color.RED);
                    points.add(pointFail);
                }
            }

            if (points.size() != 0) {
                img.drawTriangle(points);
            }
        }
    }

    public void createComplexOverviewTable() {
        datatr = "";
        String htmlOverview = html;
        String header = htmlOverview + "<tr><th rowspan=3 colspan =2></th><th colspan=9>Complex</th></tr><tr><th colspan=3>K1</th><th colspan=3>K2</th><th colspan=3>TP</th></tr><tr>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th>"
                + "<th width=40>A</th><th width=40>+(%)</th><th width=40>-(%)</th></tr>";
        createComplexOverviewTableData(14, "Tempo");
        createComplexOverviewTableData(19, "Block");
        createComplexOverviewTableData(11, "Type");
        createComplexOverviewTableData(15, "Zone");
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
                datatr = datatr + "<td width=62 rowspan=" + lst.size() + ">" + type + "</td>";

            }
            datatr = datatr + "<td  width=60>" + lst.get(i).getAbbreviation() + "</td>";

            SuccessFailure sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), 17, "K1", 2, evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), 17, "K2", 2, evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
            sf = reportDao.getAttackComplexReport(lst.get(i).getAbbreviation(), 17, "TP", 2, evaluationteamId, criteriaid);
            datatr = datatr + "<td>" + sf.getTotalAttempt() + "</td><td>" + sf.getSuccessPerc() + "</td><td>" + sf.getFailurePerc() + "</td>";
        }
    }

    public void createAttackBlockOverviewTable() {
        datatr = "";
        String htmlAttackBlock = html;
        String header = htmlAttackBlock + "<tr><th width=75>Tempo</th>"
                + "<th width=52></th>"
                + "<th width=75>Technique</th>"
                + "<th width=75>Zone</th>"
                + "<th width=75>Complex</th>"
                + "<th width=75>Attk. Comb.</th>"
                + "<th width=75>Overall</th></tr>";

        createAttackBlockOverviewTableData(19, 14);
        htmlAttackBlock = header + datatr + "</table></div></html>";
        JLabel lbl = new JLabel();

        lbl.setText(htmlAttackBlock);
        panAttackBlock.add(lbl, BorderLayout.CENTER);

    }

    public void createAttackBlockOverviewTableData(int skillDescId, int skillDescIdIn) {
        List<SkillDescCriteriaPoint> lstBlock = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescId);

        for (int i = 0; i < lstBlock.size(); i++) {
            datatr = datatr + "<tr><td colspan=7>" + lstBlock.get(i).getAbbreviation() + "</td></tr>";

            List<SkillDescCriteriaPoint> lstTempo = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescIdIn);
            for (int j = 0; j < lstTempo.size(); j++) {

                datatr = datatr + "<tr><td rowspan=" + 3 + ">" + lstTempo.get(j).getAbbreviation() + "</td>";
                datatr = datatr + "<td>S</td>"
                        + "<td>" + getValue(11, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(15, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(17, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(12, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td></td></tr>";

                datatr = datatr + "<td>F+</td>"
                        + "<td>" + getValue(11, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(15, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(17, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(12, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td></td></tr>";

                datatr = datatr + "<td>F</td>"
                        + "<td>" + getValue(11, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(15, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(17, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td>" + getValue(12, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, lstBlock.get(i).getAbbreviation(), skillDescId) + "</td>"
                        + "<td></td></tr>";
            }
        }

    }

    public String getValue(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId, String colSkillDesc, int colSkillDescId) {
        String value = reportDao.getSkillSuccessForTeamWithBlock(skillDescCriteriaId, Skill.Attack.getId(), evaluationteamId, rowSkillDesc, rowSkillDescId, colSkillDesc, colSkillDescId, rating);
        return value;
    }

    public String getValueForDig(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId, String colSkillDesc, int colSkillDescId) {
        String value = reportDao.getSkillSuccessForTeamWithBlock(skillDescCriteriaId, Skill.Attack.getId(), evaluationteamId, rowSkillDesc, rowSkillDescId, colSkillDesc, colSkillDescId, rating);
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
        jLabel10 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        panComplex.setBackground(new java.awt.Color(255, 255, 255));
        panComplex.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panComplex.setLayout(new java.awt.BorderLayout());

        panAttackBlock.setBackground(new java.awt.Color(255, 255, 255));
        panAttackBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panAttackBlock.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Attack : Overview");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Successful Combination of Attack");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panTPL.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Triple Block");

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
        jLabel12.setText("Double Block");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanDBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
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
        jLabel13.setText("Single Block");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanSBL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
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
        jLabel14.setText("No Block");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Diagramtic Presentation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(panComplex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(panAttackBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panAttackBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 1169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
