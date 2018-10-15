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
public class PanTeamReportOfReception extends javax.swing.JPanel {

    String datatr = "";
    ImagePanel img2M, img3M, img4M, img5M;

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
    public PanTeamReportOfReception(int cb, int matchId) {
        initComponents();

        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        team1id = team.getTeam1();
        team2id = team.getTeam2();
        evaluationteamId = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evaluationteamId2 = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);

        createComplexOverviewTable();
        createAttackBlockOverviewTable();

        img2M = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(0, img2M);
        imgPan.put(img2M, pan2M);

        img3M = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(1, img3M);
        imgPan.put(img3M, pan3M);

        img4M = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(2, img4M);
        imgPan.put(img4M, pan4M);

        img5M = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), null);
        imgId.put(3, img5M);
        imgPan.put(img5M, pan5M);

        createDiagram(55);

    }

    public void createDiagram(int skillDescIdIn) {
        List<SkillDescCriteriaPoint> lstTempo = SkillDescCriteriaPoint.getTypeByskillDescCriteriaId(skillDescIdIn);

        for (int j = 0; j < lstTempo.size(); j++) {

            ImagePanel img = imgId.get(j);
            JPanel pan = imgPan.get(img);
            pan.setLayout(new BorderLayout());
            pan.add(img, BorderLayout.CENTER);

            List<DigTrianglePoints> points = new ArrayList();

            String fromValSuc = CommonUtil.getValue(56, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId());
            if (!fromValSuc.isEmpty()) {
                String toValSuc = getValueForDig(57, 5, fromValSuc, 56, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                DigTrianglePoints pointSuc = new DigTrianglePoints("H" + fromValSuc + "A", "H" + toValSuc + "A", "H" + toValSuc + "C", Color.GREEN);
                points.add(pointSuc);
            }
            String fromValFav = CommonUtil.getValue(56, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId());
            if (!fromValFav.isEmpty()) {
                String toValFav = getValueForDig(57, 4, fromValFav, 56, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
                DigTrianglePoints pointFav = new DigTrianglePoints("H" + fromValFav + "A", "H" + toValFav + "A", "H" + toValFav + "C", Color.ORANGE);
                points.add(pointFav);
            }

            String fromValFail = CommonUtil.getValue(56, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId());
            if (!fromValFail.isEmpty()) {
                String toValFail = getValueForDig(57, 1, fromValFail, 56, lstTempo.get(j).getAbbreviation(), skillDescIdIn);
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
                    + "<td>" + CommonUtil.getValue(54, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(60, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(56, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(59, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(58, 5, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td></tr>";

            datatr = datatr + "<td>F+</td>"
                    + "<td>" + CommonUtil.getValue(54, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(60, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(56, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(59, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(58, 4, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td></tr>";
            datatr = datatr + "<td>F</td>"
                    + "<td>" + CommonUtil.getValue(54, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(60, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(56, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(59, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td>"
                    + "<td>" + CommonUtil.getValue(58, 1, lstTempo.get(j).getAbbreviation(), skillDescIdIn, evaluationteamId, Skill.Reception.getId()) + "</td></tr>";
        }

    }

    public String getValueForDig(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId, String colSkillDesc, int colSkillDescId) {
        String value = reportDao.getSkillSuccessForTeamWithBlock(skillDescCriteriaId, Skill.Reception.getId(), evaluationteamId, rowSkillDesc, rowSkillDescId, colSkillDesc, colSkillDescId, rating);
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
        pan2M = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        pan3M = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        pan4M = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        pan5M = new javax.swing.JPanel();
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
        jLabel1.setText("Reception : Overview");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Reception Success");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pan2M.setLayout(new java.awt.BorderLayout());

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
            .addComponent(pan2M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pan2M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pan3MLayout = new javax.swing.GroupLayout(pan3M);
        pan3M.setLayout(pan3MLayout);
        pan3MLayout.setHorizontalGroup(
            pan3MLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pan3MLayout.setVerticalGroup(
            pan3MLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("3M");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan3M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pan3M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pan4MLayout = new javax.swing.GroupLayout(pan4M);
        pan4M.setLayout(pan4MLayout);
        pan4MLayout.setHorizontalGroup(
            pan4MLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pan4MLayout.setVerticalGroup(
            pan4MLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("4M");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan4M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pan4M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pan5MLayout = new javax.swing.GroupLayout(pan5M);
        pan5M.setLayout(pan5MLayout);
        pan5MLayout.setHorizontalGroup(
            pan5MLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pan5MLayout.setVerticalGroup(
            pan5MLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("5M");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan5M, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(pan5M, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel10.setText("Diagramtic Presentation");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panAttackBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
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
                    .addComponent(panComplex, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panAttackBlock, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(311, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel pan2M;
    private javax.swing.JPanel pan3M;
    private javax.swing.JPanel pan4M;
    private javax.swing.JPanel pan5M;
    private javax.swing.JPanel panAttackBlock;
    private javax.swing.JPanel panComplex;
    // End of variables declaration//GEN-END:variables
}
