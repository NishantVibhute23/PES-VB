/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerReportBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.DialogMatchChart;
import com.vollyball.dialog.DialogPanMatchReportDetails;
import com.vollyball.enums.Skill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Supriya
 */
public class PanMatchDetails extends javax.swing.JPanel {

    LinkedHashMap<Integer, PlayerReportBean> playerId = new LinkedHashMap<Integer, PlayerReportBean>();
    String skillName;
    int skillId;
    int evaluationteamId, evaluationteamId2;
    PanTableSkillWiseReport panTableSkillWiseReport = new PanTableSkillWiseReport();
    PanMatchBestTeamScorer panMatchBestTeamScorer;
    TeamDao td = new TeamDao();
    MatchDao matchDao = new MatchDao();
    ReportDao reportDao = new ReportDao();
    int team1id, team2id;
    PanMatchSkillWisePlayerReport panMatchSkillWisePlayerReport = new PanMatchSkillWisePlayerReport();
    List<JPanel> panMenuList = new ArrayList<>();
    LinkedHashMap<String, List<Integer>> teamEval = new LinkedHashMap<>();
    List<Player> playerList;
    int cb;
    String team1Name, team2Name, team1SC, team2SC;
    LinkedHashMap<Integer, Integer> evalId = new LinkedHashMap<>();

    List<MatchSet> lstMatchSetTeam1 = new ArrayList<>();
    List<MatchSet> lstMatchSetTeam2 = new ArrayList<>();
    int team1wonBy = 0, team2wonBy = 0;
    RallyDao rd = new RallyDao();
    int matchId;
    String selecteditem = "Both";

    /**
     * Creates new form PanMatchDetails
     */
    public PanMatchDetails(int cb, int matchId) {
        initComponents();
        ((JLabel) cmbTeams.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        this.cb = cb;
        this.matchId = matchId;
        panMenuList.add(panScorer);
        panMenuList.add(panServer);
        panMenuList.add(panAttacker);
        panMenuList.add(panBlocker);
        panMenuList.add(panSetter);
        panMenuList.add(panReceiver);
        panMenuList.add(panDefender);

        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);

        team1id = team.getTeam1();
        team2id = team.getTeam2();
        team1Name = team.getTeam1name();
        team2Name = team.getTeam2name();
        team1SC = team.getTeam1ShortCode();
        team2SC = team.getTeam2ShortCode();
        lblDate.setText("Date : " + team.getDate());

        playerList = td.getTeamPlayers(team1id);
        playerList.addAll(td.getTeamPlayers(team2id));

        evaluationteamId = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evalId.put(team.getTeam1(), evaluationteamId);
        evaluationteamId2 = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);
        evalId.put(team.getTeam2(), evaluationteamId2);

        for (int i = 1; i <= 5; i++) {
            MatchSet ms1 = matchDao.getMatchSet(i, evaluationteamId);
            if (ms1.getId() != 0) {
                lstMatchSetTeam1.add(ms1);
            }
            MatchSet ms2 = matchDao.getMatchSet(i, evaluationteamId2);
            if (ms2.getId() != 0) {
                lstMatchSetTeam2.add(ms2);
            }
        }

        if (lstMatchSetTeam1.size() > lstMatchSetTeam2.size()) {
            showScoreBoard(lstMatchSetTeam1, 1);
        } else if (lstMatchSetTeam1.size() < lstMatchSetTeam2.size()) {
            showScoreBoard(lstMatchSetTeam2, 2);
        } else {
            showScoreBoard(lstMatchSetTeam1, 1);
        }

        lblMatch.setText("Match : " + team1Name + " (" + team.getTeam1ShortCode() + ") vs " + team2Name + " (" + team.getTeam2ShortCode() + ")");
        lblPhase.setText("Phase : " + team.getPhase());
        cmbTeams.addItem(team.getTeam1name());
        cmbTeams.addItem(team.getTeam2name());
        panMatchBestTeamScorer = new PanMatchBestTeamScorer(cb, playerList, evalId, team1id, team2id, matchId);

        team1NameSet1.setText(team.getTeam1ShortCode());
        team2NameSet1.setText(team.getTeam2ShortCode());
        team1NameSet2.setText(team.getTeam1ShortCode());
        team2NameSet2.setText(team.getTeam2ShortCode());
        team1NameSet3.setText(team.getTeam1ShortCode());
        team2NameSet3.setText(team.getTeam2ShortCode());
        team1NameSet4.setText(team.getTeam1ShortCode());
        team2NameSet4.setText(team.getTeam2ShortCode());
        team1NameSet5.setText(team.getTeam1ShortCode());
        team2NameSet5.setText(team.getTeam2ShortCode());

        scoreTeam1.setText(team1Name + " (" + team.getTeam1ShortCode() + ")  : ");
        scoreTeam2.setText(team2Name + " (" + team.getTeam2ShortCode() + ")  : ");

        panSkillReports.add(panMatchBestTeamScorer, BorderLayout.CENTER);
        changeColor(panScorer);

    }

    public void showScoreBoard(List<MatchSet> lstMatchSetTeam, int team) {

        int i = 0;
        for (MatchSet ms : lstMatchSetTeam) {
            i++;
            String homeScore, oppScore;
            if (team == 1) {
                homeScore = "" + ms.getHomeScore();
                oppScore = "" + ms.getOpponentScore();
            } else {
                homeScore = "" + ms.getOpponentScore();
                oppScore = "" + ms.getHomeScore();
            }

            int countRally = rd.getRallyCountByEvaluationId(ms.getId());

            if (i == 1) {
                rallySet1.setText("Total Rallies : " + countRally);
                team1Set1.setText(homeScore);
                team2Set1.setText(oppScore);

                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }

            } else if (i == 2) {
                rallySet2.setText("Total Rallies : " + countRally);
                team1Set2.setText(homeScore);
                team2Set2.setText(oppScore);
                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            } else if (i == 3) {
                rallySet3.setText("Total Rallies : " + countRally);
                team1Set3.setText(homeScore);
                team2Set3.setText(oppScore);
                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            } else if (i == 4) {
                rallySet4.setText("Total Rallies : " + countRally);
                team1Set4.setText(homeScore);
                team2Set4.setText(oppScore);
                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            } else if (i == 5) {
                rallySet5.setText("Total Rallies : " + countRally);
                team1Set5.setText(homeScore);
                team2Set5.setText(oppScore);
                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            }

        }

        scoreTeam1.setText(team1Name + " (" + team1SC + ")  : " + team1wonBy);
        scoreTeam2.setText(team2Name + " (" + team2SC + ")  : " + team2wonBy);

        if (team1wonBy > team2wonBy) {
            wonBy.setText("Won By : " + team1Name);
        } else if (team1wonBy < team2wonBy) {
            wonBy.setText("Won By : " + team2Name);
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

        jPanel1 = new javax.swing.JPanel();
        lblMatch = new javax.swing.JLabel();
        lblPhase = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        panDataSheet = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        panPrint1 = new javax.swing.JPanel();
        lblPrint1 = new javax.swing.JLabel();
        panDataSheet1 = new javax.swing.JPanel();
        lblChart = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scoreTeam1 = new javax.swing.JLabel();
        scoreTeam2 = new javax.swing.JLabel();
        wonBy = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        rallySet1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        team2NameSet1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        team1NameSet1 = new javax.swing.JLabel();
        team1Set1 = new javax.swing.JLabel();
        team2Set1 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        rallySet2 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        team2NameSet2 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        team1NameSet2 = new javax.swing.JLabel();
        team1Set2 = new javax.swing.JLabel();
        team2Set2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        rallySet3 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        team2NameSet3 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        team1NameSet3 = new javax.swing.JLabel();
        team1Set3 = new javax.swing.JLabel();
        team2Set3 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        rallySet4 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        team2NameSet4 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        team1NameSet4 = new javax.swing.JLabel();
        team1Set4 = new javax.swing.JLabel();
        team2Set4 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        rallySet5 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        team2NameSet5 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        team1NameSet5 = new javax.swing.JLabel();
        team1Set5 = new javax.swing.JLabel();
        team2Set5 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        panServer = new javax.swing.JPanel();
        lblServer = new javax.swing.JLabel();
        panAttacker = new javax.swing.JPanel();
        lblAttacker = new javax.swing.JLabel();
        panBlocker = new javax.swing.JPanel();
        lblBlocker = new javax.swing.JLabel();
        panDefender = new javax.swing.JPanel();
        lblDefender = new javax.swing.JLabel();
        panReceiver = new javax.swing.JPanel();
        lblReceiver = new javax.swing.JLabel();
        panSetter = new javax.swing.JPanel();
        lblSetter = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        cmbTeams = new javax.swing.JComboBox<>();
        panScorer = new javax.swing.JPanel();
        lblScorer = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        panSkillReports = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        lblMatch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblMatch.setForeground(new java.awt.Color(255, 255, 255));
        lblMatch.setText("Match : ");

        lblPhase.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblPhase.setForeground(new java.awt.Color(255, 255, 255));
        lblPhase.setText("Phase : ");

        lblDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Date : ");

        panDataSheet.setBackground(new java.awt.Color(153, 153, 153));
        panDataSheet.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("DATA SHEET");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDataSheetLayout = new javax.swing.GroupLayout(panDataSheet);
        panDataSheet.setLayout(panDataSheetLayout);
        panDataSheetLayout.setHorizontalGroup(
            panDataSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panDataSheetLayout.setVerticalGroup(
            panDataSheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panPrint1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblPrint1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblPrint1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPrint1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/printer.png"))); // NOI18N
        lblPrint1.setToolTipText("Print This Page");
        lblPrint1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblPrint1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panPrint1Layout = new javax.swing.GroupLayout(panPrint1);
        panPrint1.setLayout(panPrint1Layout);
        panPrint1Layout.setHorizontalGroup(
            panPrint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
        );
        panPrint1Layout.setVerticalGroup(
            panPrint1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblPrint1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panDataSheet1.setBackground(new java.awt.Color(153, 153, 153));
        panDataSheet1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblChart.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblChart.setForeground(new java.awt.Color(51, 51, 51));
        lblChart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChart.setText("CHART");
        lblChart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblChartMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDataSheet1Layout = new javax.swing.GroupLayout(panDataSheet1);
        panDataSheet1.setLayout(panDataSheet1Layout);
        panDataSheet1Layout.setHorizontalGroup(
            panDataSheet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblChart, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );
        panDataSheet1Layout.setVerticalGroup(
            panDataSheet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panDataSheet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panDataSheet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panPrint1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panDataSheet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panDataSheet, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panPrint1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblPhase, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        scoreTeam1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        scoreTeam1.setText("Team 1:");

        scoreTeam2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        scoreTeam2.setText("Team 2:");

        wonBy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        wonBy.setText("Won By :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scoreTeam1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(213, 213, 213)
                .addComponent(scoreTeam2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(wonBy, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(scoreTeam2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(wonBy, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(scoreTeam1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(57, 74, 108));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SET 1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rallySet1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallySet1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rallySet1.setText("Total Rallies : 0");
        rallySet1.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet1, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(57, 74, 108));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2NameSet1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team2NameSet1.setForeground(new java.awt.Color(255, 255, 255));
        team2NameSet1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2NameSet1.setText("Team 2");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel7.setBackground(new java.awt.Color(57, 74, 108));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1NameSet1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team1NameSet1.setForeground(new java.awt.Color(255, 255, 255));
        team1NameSet1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1NameSet1.setText("Team 1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        team1Set1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team1Set1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1Set1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2Set1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team2Set1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2Set1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(team1Set1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(team2Set1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1Set1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(team2Set1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel10.setBackground(new java.awt.Color(57, 74, 108));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SET 2");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rallySet2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallySet2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rallySet2.setText("Total Rallies : 0");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(57, 74, 108));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2NameSet2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team2NameSet2.setForeground(new java.awt.Color(255, 255, 255));
        team2NameSet2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2NameSet2.setText("Team 2");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel13.setBackground(new java.awt.Color(57, 74, 108));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1NameSet2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team1NameSet2.setForeground(new java.awt.Color(255, 255, 255));
        team1NameSet2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1NameSet2.setText("Team 1");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet2, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        team1Set2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team1Set2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1Set2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2Set2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team2Set2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2Set2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(team1Set2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(team2Set2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1Set2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(team2Set2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel15.setBackground(new java.awt.Color(57, 74, 108));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SET 3");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rallySet3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallySet3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rallySet3.setText("Total Rallies : 0");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel17.setBackground(new java.awt.Color(57, 74, 108));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2NameSet3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team2NameSet3.setForeground(new java.awt.Color(255, 255, 255));
        team2NameSet3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2NameSet3.setText("Team 2");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(57, 74, 108));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1NameSet3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team1NameSet3.setForeground(new java.awt.Color(255, 255, 255));
        team1NameSet3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1NameSet3.setText("Team 1");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        team1Set3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team1Set3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1Set3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2Set3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team2Set3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2Set3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(team1Set3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(team2Set3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1Set3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(team2Set3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel20.setBackground(new java.awt.Color(57, 74, 108));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SET 4");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rallySet4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallySet4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rallySet4.setText("Total Rallies : 0");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(57, 74, 108));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2NameSet4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team2NameSet4.setForeground(new java.awt.Color(255, 255, 255));
        team2NameSet4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2NameSet4.setText("Team 2");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(57, 74, 108));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1NameSet4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team1NameSet4.setForeground(new java.awt.Color(255, 255, 255));
        team1NameSet4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1NameSet4.setText("Team 1");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        team1Set4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team1Set4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1Set4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2Set4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team2Set4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2Set4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(team1Set4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(team2Set4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1Set4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(team2Set4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel25.setBackground(new java.awt.Color(57, 74, 108));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("SET 5");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        rallySet5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rallySet5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        rallySet5.setText("Total Rallies : 0");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rallySet5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(57, 74, 108));
        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2NameSet5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team2NameSet5.setForeground(new java.awt.Color(255, 255, 255));
        team2NameSet5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2NameSet5.setText("Team 2");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet5, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team2NameSet5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel28.setBackground(new java.awt.Color(57, 74, 108));
        jPanel28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1NameSet5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        team1NameSet5.setForeground(new java.awt.Color(255, 255, 255));
        team1NameSet5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1NameSet5.setText("Team 1");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NameSet5, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        team1Set5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team1Set5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1Set5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2Set5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        team2Set5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2Set5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel24Layout.createSequentialGroup()
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(team1Set5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(team2Set5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1Set5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(team2Set5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panServer.setBackground(new java.awt.Color(255, 255, 255));
        panServer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblServer.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblServer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblServer.setText("BEST SERVER");
        lblServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblServerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panServerLayout = new javax.swing.GroupLayout(panServer);
        panServer.setLayout(panServerLayout);
        panServerLayout.setHorizontalGroup(
            panServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panServerLayout.setVerticalGroup(
            panServerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblServer, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panAttacker.setBackground(new java.awt.Color(255, 255, 255));
        panAttacker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblAttacker.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblAttacker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttacker.setText("BEST ATTACKER");
        lblAttacker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAttackerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAttackerLayout = new javax.swing.GroupLayout(panAttacker);
        panAttacker.setLayout(panAttackerLayout);
        panAttackerLayout.setHorizontalGroup(
            panAttackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        );
        panAttackerLayout.setVerticalGroup(
            panAttackerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panBlocker.setBackground(new java.awt.Color(255, 255, 255));
        panBlocker.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblBlocker.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblBlocker.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlocker.setText("BEST BLOCKER");
        lblBlocker.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBlockerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panBlockerLayout = new javax.swing.GroupLayout(panBlocker);
        panBlocker.setLayout(panBlockerLayout);
        panBlockerLayout.setHorizontalGroup(
            panBlockerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlocker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panBlockerLayout.setVerticalGroup(
            panBlockerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlocker, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panDefender.setBackground(new java.awt.Color(255, 255, 255));
        panDefender.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblDefender.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblDefender.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDefender.setText("BEST DEFENDER");
        lblDefender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDefenderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDefenderLayout = new javax.swing.GroupLayout(panDefender);
        panDefender.setLayout(panDefenderLayout);
        panDefenderLayout.setHorizontalGroup(
            panDefenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panDefenderLayout.setVerticalGroup(
            panDefenderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefender, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panReceiver.setBackground(new java.awt.Color(255, 255, 255));
        panReceiver.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblReceiver.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblReceiver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReceiver.setText("BEST RECEIVER");
        lblReceiver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReceiverMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panReceiverLayout = new javax.swing.GroupLayout(panReceiver);
        panReceiver.setLayout(panReceiverLayout);
        panReceiverLayout.setHorizontalGroup(
            panReceiverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panReceiverLayout.setVerticalGroup(
            panReceiverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        panSetter.setBackground(new java.awt.Color(255, 255, 255));
        panSetter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblSetter.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblSetter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetter.setText("BEST SETTER");
        lblSetter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSetterLayout = new javax.swing.GroupLayout(panSetter);
        panSetter.setLayout(panSetterLayout);
        panSetterLayout.setHorizontalGroup(
            panSetterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSetterLayout.setVerticalGroup(
            panSetterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetter, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel33.setBackground(new java.awt.Color(57, 74, 108));
        jPanel33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbTeams.setBackground(new java.awt.Color(0, 0, 0));
        cmbTeams.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbTeams.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BOTH" }));
        cmbTeams.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbTeamsItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(cmbTeams, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cmbTeams, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        panScorer.setBackground(new java.awt.Color(255, 255, 255));
        panScorer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblScorer.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        lblScorer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScorer.setText("BEST SCORER");
        lblScorer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblScorerMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panScorerLayout = new javax.swing.GroupLayout(panScorer);
        panScorer.setLayout(panScorerLayout);
        panScorerLayout.setHorizontalGroup(
            panScorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScorer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panScorerLayout.setVerticalGroup(
            panScorerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblScorer, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panAttacker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panBlocker, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSetter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panDefender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panReceiver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panScorer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panScorer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panAttacker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panBlocker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSetter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReceiver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDefender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panSkillReports.setBackground(new java.awt.Color(255, 255, 255));
        panSkillReports.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSkillReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lblServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblServerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Service.getId());
        panMatchSkillWisePlayerReport.lblReportHeading.setText(lblServer.getText());
        skillName = lblServer.getText();
        changeColor(panServer);
    }//GEN-LAST:event_lblServerMouseClicked

    private void lblAttackerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttackerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Attack.getId());
        panMatchSkillWisePlayerReport.lblReportHeading.setText(lblAttacker.getText());
        skillName = lblAttacker.getText();
        changeColor(panAttacker);
    }//GEN-LAST:event_lblAttackerMouseClicked

    private void lblBlockerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBlockerMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Block.getId());
        panMatchSkillWisePlayerReport.lblReportHeading.setText(lblBlocker.getText());
        skillName = lblBlocker.getText();
        changeColor(panBlocker);
    }//GEN-LAST:event_lblBlockerMouseClicked

    private void lblDefenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDefenderMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Defence.getId());
        panMatchSkillWisePlayerReport.lblReportHeading.setText(lblDefender.getText());
        skillName = lblDefender.getText();
        changeColor(panDefender);
    }//GEN-LAST:event_lblDefenderMouseClicked

    private void lblReceiverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReceiverMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Reception.getId());
        panMatchSkillWisePlayerReport.lblReportHeading.setText(lblReceiver.getText());
        skillName = lblReceiver.getText();
        changeColor(panReceiver);
    }//GEN-LAST:event_lblReceiverMouseClicked

    private void lblSetterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetterMouseClicked
        // TODO add your handling code here:
        setPlayerReport(Skill.Set.getId());
        panMatchSkillWisePlayerReport.lblReportHeading.setText(lblSetter.getText());
        skillName = lblSetter.getText();
        changeColor(panSetter);
    }//GEN-LAST:event_lblSetterMouseClicked

    private void lblScorerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScorerMouseClicked
        // TODO add your handling code here:

        changeColor(panScorer);
        panSkillReports.remove(panMatchSkillWisePlayerReport);
        panSkillReports.add(panMatchBestTeamScorer, BorderLayout.CENTER);
        this.validate();
        this.repaint();
    }//GEN-LAST:event_lblScorerMouseClicked

    private void cmbTeamsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbTeamsItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            panSkillReports.remove(panMatchBestTeamScorer);
            selecteditem = String.valueOf(evt.getItem());
            int t1, t2;
            if (selecteditem.equalsIgnoreCase(team1Name)) {
                t1 = team1id;
                t2 = 0;
            } else if (selecteditem.equalsIgnoreCase(team2Name)) {
                t1 = 0;
                t2 = team2id;
            } else {
                t1 = team1id;
                t2 = team2id;
            }
            panSkillReports.remove(panMatchSkillWisePlayerReport);
            panMatchBestTeamScorer = new PanMatchBestTeamScorer(cb, playerList, evalId, t1, t2, matchId);
            panSkillReports.add(panMatchBestTeamScorer, BorderLayout.CENTER);
            changeColor(panScorer);
            this.validate();
            this.repaint();
        }
    }//GEN-LAST:event_cmbTeamsItemStateChanged

    private void lblPrint1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblPrint1MouseClicked
        // TODO add your handling code here:
        panPrint1.setVisible(false);
        panDataSheet.setVisible(false);

        printComponenet(this);
        panPrint1.setVisible(true);
        panDataSheet.setVisible(true);
    }//GEN-LAST:event_lblPrint1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:

        DialogPanMatchReportDetails createDialogPanMatchWiseReport = new DialogPanMatchReportDetails();
        createDialogPanMatchWiseReport.init(matchId);
        createDialogPanMatchWiseReport.show();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void lblChartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblChartMouseClicked
        // TODO add your handling code here:
        DialogMatchChart createDialogPanMatchWiseReport = new DialogMatchChart();
        createDialogPanMatchWiseReport.init(cb, matchId);
        createDialogPanMatchWiseReport.show();
    }//GEN-LAST:event_lblChartMouseClicked

    public void setPlayerReport(int skillid) {
        panSkillReports.remove(panMatchBestTeamScorer);
        panSkillReports.add(panMatchSkillWisePlayerReport, BorderLayout.CENTER);
        this.validate();
        this.repaint();

        int t1, t2;
        if (selecteditem.equalsIgnoreCase(team1Name)) {
            t1 = team1id;
            t2 = 0;
        } else if (selecteditem.equalsIgnoreCase(team2Name)) {
            t1 = 0;
            t2 = team2id;
        } else {
            t1 = team1id;
            t2 = team2id;
        }
        panMatchSkillWisePlayerReport.setTeamReport(skillid, skillName, cb, playerList, evalId, t1, t2, matchId);
    }

    public void changeColor(JPanel panIn) {
        for (JPanel pan : panMenuList) {
            if (pan == panIn) {
                pan.setBackground(new Color(204, 204, 204));
            } else {
                pan.setBackground(Color.WHITE);
            }
        }
    }

    public void printComponenet(final Component comp) {

        PageFormat documentPageFormat = new PageFormat();
        documentPageFormat.setOrientation(PageFormat.LANDSCAPE);

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Score Report_-_" + lblMatch.getText());

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbTeams;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblAttacker;
    private javax.swing.JLabel lblBlocker;
    private javax.swing.JLabel lblChart;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblDefender;
    private javax.swing.JLabel lblMatch;
    private javax.swing.JLabel lblPhase;
    private javax.swing.JLabel lblPrint1;
    private javax.swing.JLabel lblReceiver;
    private javax.swing.JLabel lblScorer;
    private javax.swing.JLabel lblServer;
    private javax.swing.JLabel lblSetter;
    private javax.swing.JPanel panAttacker;
    private javax.swing.JPanel panBlocker;
    private javax.swing.JPanel panDataSheet;
    private javax.swing.JPanel panDataSheet1;
    private javax.swing.JPanel panDefender;
    private javax.swing.JPanel panPrint1;
    private javax.swing.JPanel panReceiver;
    private javax.swing.JPanel panScorer;
    private javax.swing.JPanel panServer;
    private javax.swing.JPanel panSetter;
    private javax.swing.JPanel panSkillReports;
    private javax.swing.JLabel rallySet1;
    private javax.swing.JLabel rallySet2;
    private javax.swing.JLabel rallySet3;
    private javax.swing.JLabel rallySet4;
    private javax.swing.JLabel rallySet5;
    private javax.swing.JLabel scoreTeam1;
    private javax.swing.JLabel scoreTeam2;
    private javax.swing.JLabel team1NameSet1;
    private javax.swing.JLabel team1NameSet2;
    private javax.swing.JLabel team1NameSet3;
    private javax.swing.JLabel team1NameSet4;
    private javax.swing.JLabel team1NameSet5;
    private javax.swing.JLabel team1Set1;
    private javax.swing.JLabel team1Set2;
    private javax.swing.JLabel team1Set3;
    private javax.swing.JLabel team1Set4;
    private javax.swing.JLabel team1Set5;
    private javax.swing.JLabel team2NameSet1;
    private javax.swing.JLabel team2NameSet2;
    private javax.swing.JLabel team2NameSet3;
    private javax.swing.JLabel team2NameSet4;
    private javax.swing.JLabel team2NameSet5;
    private javax.swing.JLabel team2Set1;
    private javax.swing.JLabel team2Set2;
    private javax.swing.JLabel team2Set3;
    private javax.swing.JLabel team2Set4;
    private javax.swing.JLabel team2Set5;
    private javax.swing.JLabel wonBy;
    // End of variables declaration//GEN-END:variables
}
