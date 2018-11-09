/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.CreateWaitDialog;
import com.vollyball.dialog.DialogPanEvaluation;
import com.vollyball.dialog.DialogPanEvaluationRotationOrder;
import com.vollyball.dialog.SelectTeamPlayerDialog;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nishant.vibhute
 */
public class PanMatchEvaluationHome extends javax.swing.JPanel {

    LinkedHashMap<Integer, String> teamsMap;
    TeamDao td = new TeamDao();
    DefaultTableModel model, modelSelectedPlayer;
    int selectedTeam, teamId, evaluationType, oppId;
    MatchDao matchDao = new MatchDao();
    int evaluatingTeam = 0, opponentTeam = 0;
    SelectTeamPlayerDialog selectTeamPlayerDialog;
    String homeTeam, oppteam;
    int matchId, matchEvaluationTeamId;
    List<Integer> selectedPlayers;
    DialogPanEvaluation obj;
    DialogPanEvaluationRotationOrder objRotationOrder;
    Thread threadWait;
    public CreateWaitDialog createWaitDialog;
    List<MatchSet> lstMatchSetTeam1 = new ArrayList<>();
    RallyDao rd = new RallyDao();

    /**
     * Creates new form MatchEvaluationHome
     *
     * @param matchId
     */
    public PanMatchEvaluationHome(int teamId, int oppId, int evaluationType, String homeTeam, String oppteam, int matchId, int matchEvaluationTeamId) {
        initComponents();
        this.teamId = teamId;
        this.oppId = oppId;
        this.evaluationType = evaluationType;
        this.homeTeam = homeTeam;
        this.oppteam = oppteam;
        this.matchId = matchId;
        this.matchEvaluationTeamId = matchEvaluationTeamId;
        setTeamName();

        for (int i = 1; i <= 5; i++) {
            MatchSet ms1 = matchDao.getMatchSet(i, matchEvaluationTeamId);
            if (ms1.getId() != 0) {
                lstMatchSetTeam1.add(ms1);
            }
        }

        for (MatchSet ms : lstMatchSetTeam1) {
            String homeScore, oppScore;
            homeScore = "" + ms.getHomeScore();
            oppScore = "" + ms.getOpponentScore();

            int countRally = rd.getRallyCountByEvaluationId(ms.getId());

            if (ms.getSetNo() == 1) {
                lblSetTotalRallies1.setText("Total Rallies : " + countRally);
                lblSetScore1.setText("|  Score : " + homeScore + ":" + oppScore);

                if (ms.getWon_by() != 0) {
                    panSet1.setBackground(new Color(43, 64, 19));
                } else {
                    panSet1.setBackground(new Color(121, 86, 14));
                }
            }

            if (ms.getSetNo() == 2) {
                lblSetTotalRallies2.setText("Total Rallies : " + countRally);
                lblSetScore2.setText("|  Score : " + homeScore + ":" + oppScore);

                if (ms.getWon_by() != 0) {
                    panSet2.setBackground(new Color(43, 64, 19));
                } else {
                    panSet2.setBackground(new Color(121, 86, 14));
                }
            }

            if (ms.getSetNo() == 3) {
                lblSetTotalRallies3.setText("Total Rallies : " + countRally);
                lblSetScore3.setText("|  Score : " + homeScore + ":" + oppScore);

                if (ms.getWon_by() != 0) {
                    panSet3.setBackground(new Color(43, 64, 19));
                } else {
                    panSet3.setBackground(new Color(121, 86, 14));
                }
            }

            if (ms.getSetNo() == 4) {
                lblSetTotalRallies4.setText("Total Rallies : " + countRally);
                lblSetScore4.setText("|  Score : " + homeScore + ":" + oppScore);

                if (ms.getWon_by() != 0) {
                    panSet4.setBackground(new Color(43, 64, 19));
                } else {
                    panSet4.setBackground(new Color(121, 86, 14));
                }
            }

            if (ms.getSetNo() == 5) {
                lblSetTotalRallies5.setText("Total Rallies : " + countRally);
                lblSetScore5.setText("|  Score : " + homeScore + ":" + oppScore);

                if (ms.getWon_by() != 0) {
                    panSet5.setBackground(new Color(43, 64, 19));
                } else {
                    panSet5.setBackground(new Color(121, 86, 14));
                }
            }

        }

    }

    public void setTeamName() {
        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        teamsMap = new LinkedHashMap<>();
        teamsMap.put(team.getTeam1(), team.getTeam1name());
        teamsMap.put(team.getTeam2(), team.getTeam2name());

        selectOpponentTeam.setText(oppteam);
        hometeamname.setText(homeTeam);

        selectedPlayers = td.getMatchPlayers(this.matchId, teamId);
        if (selectedPlayers.isEmpty()) {
            List<Player> playerList = td.getTeamPlayers(teamId);
            matchDao.insertMatchPlayers(this.matchId, this.teamId, playerList);
        }

        selectedPlayers = td.getMatchPlayers(this.matchId, oppId);
        if (selectedPlayers.isEmpty()) {
            List<Player> playerList = td.getTeamPlayers(oppId);
            matchDao.insertMatchPlayers(this.matchId, this.oppId, playerList);
        }

    }

    public LinkedHashMap<Integer, String> getTeamsMap() {
        return teamsMap;
    }

    public int getEvaluatingTeam() {
        return evaluatingTeam;
    }

    public int getOpponentTeam() {
        return opponentTeam;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        panSet1 = new javax.swing.JPanel();
        set1 = new javax.swing.JLabel();
        lblSetTotalRallies1 = new javax.swing.JLabel();
        lblSetScore1 = new javax.swing.JLabel();
        panSet2 = new javax.swing.JPanel();
        lblSetScore2 = new javax.swing.JLabel();
        lblSetTotalRallies2 = new javax.swing.JLabel();
        set2 = new javax.swing.JLabel();
        panSet5 = new javax.swing.JPanel();
        lblSetScore5 = new javax.swing.JLabel();
        lblSetTotalRallies5 = new javax.swing.JLabel();
        set5 = new javax.swing.JLabel();
        panSet4 = new javax.swing.JPanel();
        lblSetScore4 = new javax.swing.JLabel();
        lblSetTotalRallies4 = new javax.swing.JLabel();
        set4 = new javax.swing.JLabel();
        panSet3 = new javax.swing.JPanel();
        lblSetScore3 = new javax.swing.JLabel();
        lblSetTotalRallies3 = new javax.swing.JLabel();
        set3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hometeamname = new javax.swing.JLabel();
        selectOpponentTeam = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setForeground(new java.awt.Color(57, 74, 108));

        panSet1.setBackground(new java.awt.Color(57, 74, 108));
        panSet1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        set1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        set1.setForeground(new java.awt.Color(255, 255, 255));
        set1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        set1.setText("Set 1");
        set1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        set1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set1MouseClicked(evt);
            }
        });

        lblSetTotalRallies1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetTotalRallies1.setForeground(new java.awt.Color(255, 255, 255));
        lblSetTotalRallies1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSetTotalRallies1.setText("Total Rallies : 0");

        lblSetScore1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetScore1.setForeground(new java.awt.Color(255, 255, 255));
        lblSetScore1.setText("|  Score : 0-0");

        javax.swing.GroupLayout panSet1Layout = new javax.swing.GroupLayout(panSet1);
        panSet1.setLayout(panSet1Layout);
        panSet1Layout.setHorizontalGroup(
            panSet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet1Layout.createSequentialGroup()
                .addComponent(lblSetTotalRallies1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSetScore1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(set1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSet1Layout.setVerticalGroup(
            panSet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(set1)
                .addGap(0, 0, 0)
                .addGroup(panSet1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSetTotalRallies1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSetScore1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)))
        );

        panSet2.setBackground(new java.awt.Color(57, 74, 108));
        panSet2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSetScore2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetScore2.setForeground(new java.awt.Color(255, 255, 255));
        lblSetScore2.setText("|  Score : 0-0");

        lblSetTotalRallies2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetTotalRallies2.setForeground(new java.awt.Color(255, 255, 255));
        lblSetTotalRallies2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSetTotalRallies2.setText("Total Rallies : 0");
        lblSetTotalRallies2.setToolTipText("");

        set2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        set2.setForeground(new java.awt.Color(255, 255, 255));
        set2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        set2.setText("Set 2");
        set2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        set2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSet2Layout = new javax.swing.GroupLayout(panSet2);
        panSet2.setLayout(panSet2Layout);
        panSet2Layout.setHorizontalGroup(
            panSet2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet2Layout.createSequentialGroup()
                .addComponent(lblSetTotalRallies2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblSetScore2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(set2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSet2Layout.setVerticalGroup(
            panSet2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(set2)
                .addGap(0, 0, 0)
                .addGroup(panSet2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSetTotalRallies2, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblSetScore2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panSet5.setBackground(new java.awt.Color(57, 74, 108));
        panSet5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSetScore5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetScore5.setForeground(new java.awt.Color(255, 255, 255));
        lblSetScore5.setText("|  Score : 0-0");

        lblSetTotalRallies5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetTotalRallies5.setForeground(new java.awt.Color(255, 255, 255));
        lblSetTotalRallies5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSetTotalRallies5.setText("Total Rallies : 0");

        set5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        set5.setForeground(new java.awt.Color(255, 255, 255));
        set5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        set5.setText("Set 5");
        set5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        set5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSet5Layout = new javax.swing.GroupLayout(panSet5);
        panSet5.setLayout(panSet5Layout);
        panSet5Layout.setHorizontalGroup(
            panSet5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet5Layout.createSequentialGroup()
                .addComponent(lblSetTotalRallies5, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSetScore5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(set5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSet5Layout.setVerticalGroup(
            panSet5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet5Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(set5)
                .addGap(0, 0, 0)
                .addGroup(panSet5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSetTotalRallies5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblSetScore5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panSet4.setBackground(new java.awt.Color(57, 74, 108));
        panSet4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSetScore4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetScore4.setForeground(new java.awt.Color(255, 255, 255));
        lblSetScore4.setText("|  Score : 0-0");

        lblSetTotalRallies4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetTotalRallies4.setForeground(new java.awt.Color(255, 255, 255));
        lblSetTotalRallies4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSetTotalRallies4.setText("Total Rallies : 0");

        set4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        set4.setForeground(new java.awt.Color(255, 255, 255));
        set4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        set4.setText("Set 4");
        set4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        set4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSet4Layout = new javax.swing.GroupLayout(panSet4);
        panSet4.setLayout(panSet4Layout);
        panSet4Layout.setHorizontalGroup(
            panSet4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet4Layout.createSequentialGroup()
                .addComponent(lblSetTotalRallies4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSetScore4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(set4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSet4Layout.setVerticalGroup(
            panSet4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(set4)
                .addGap(0, 0, 0)
                .addGroup(panSet4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSetTotalRallies4, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(lblSetScore4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        panSet3.setBackground(new java.awt.Color(57, 74, 108));
        panSet3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSetScore3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetScore3.setForeground(new java.awt.Color(255, 255, 255));
        lblSetScore3.setText("|  Score : 0-0");

        lblSetTotalRallies3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetTotalRallies3.setForeground(new java.awt.Color(255, 255, 255));
        lblSetTotalRallies3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblSetTotalRallies3.setText("Total Rallies : 0");

        set3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        set3.setForeground(new java.awt.Color(255, 255, 255));
        set3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        set3.setText("Set 3");
        set3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        set3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                set3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSet3Layout = new javax.swing.GroupLayout(panSet3);
        panSet3.setLayout(panSet3Layout);
        panSet3Layout.setHorizontalGroup(
            panSet3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet3Layout.createSequentialGroup()
                .addComponent(lblSetTotalRallies3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSetScore3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(set3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSet3Layout.setVerticalGroup(
            panSet3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSet3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(set3)
                .addGap(0, 0, 0)
                .addGroup(panSet3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSetTotalRallies3, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(lblSetScore3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(121, 86, 14));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(57, 74, 108));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        jPanel6.setBackground(new java.awt.Color(43, 64, 19));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Not Evaluated");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Evaluating");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Evaluated");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 335, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panSet1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSet2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSet3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSet4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panSet5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(344, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panSet1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panSet2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panSet3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panSet4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panSet5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Evaluating Team :");
        jLabel1.setToolTipText("");

        hometeamname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        hometeamname.setForeground(new java.awt.Color(255, 255, 255));

        selectOpponentTeam.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        selectOpponentTeam.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Opponent Team :");
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hometeamname, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectOpponentTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hometeamname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(selectOpponentTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void set1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set1MouseClicked
        // TODO add your handling code here:
        showPanMatchSet(1);
    }//GEN-LAST:event_set1MouseClicked


    private void set2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set2MouseClicked
        // TODO add your handling code here:
         showPanMatchSet(2);
    }//GEN-LAST:event_set2MouseClicked

    private void set3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set3MouseClicked
        // TODO add your handling code here:
         showPanMatchSet(3);
    }//GEN-LAST:event_set3MouseClicked

    private void set4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set4MouseClicked
        // TODO add your handling code here:
         showPanMatchSet(4);
    }//GEN-LAST:event_set4MouseClicked

    private void set5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_set5MouseClicked
        // TODO add your handling code here:
         showPanMatchSet(5);
    }//GEN-LAST:event_set5MouseClicked

    public void showPanMatchSet(int set) {

        createWaitDialog = new CreateWaitDialog();
        threadWait = new Thread(createWaitDialog);
        threadWait.start();

        new Thread() {
            public void run() {
                int id = matchDao.getMatchSetId(matchEvaluationTeamId, set);
                if (id == 0) {
                    objRotationOrder = new DialogPanEvaluationRotationOrder();
                    objRotationOrder.init(teamId, oppId, matchId, homeTeam, oppteam, set, matchEvaluationTeamId, "Save", "");
                    objRotationOrder.show();
                } else {
                    obj = new DialogPanEvaluation();
                    obj.setSetFields(set, matchId, teamId, oppId, evaluationType, matchEvaluationTeamId);
                    obj.init();
                    obj.show();
                }
            }
        }.start();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hometeamname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lblSetScore1;
    private javax.swing.JLabel lblSetScore2;
    private javax.swing.JLabel lblSetScore3;
    private javax.swing.JLabel lblSetScore4;
    private javax.swing.JLabel lblSetScore5;
    private javax.swing.JLabel lblSetTotalRallies1;
    private javax.swing.JLabel lblSetTotalRallies2;
    private javax.swing.JLabel lblSetTotalRallies3;
    private javax.swing.JLabel lblSetTotalRallies4;
    private javax.swing.JLabel lblSetTotalRallies5;
    private javax.swing.JPanel panSet1;
    private javax.swing.JPanel panSet2;
    private javax.swing.JPanel panSet3;
    private javax.swing.JPanel panSet4;
    private javax.swing.JPanel panSet5;
    private javax.swing.JLabel selectOpponentTeam;
    private javax.swing.JLabel set1;
    private javax.swing.JLabel set2;
    private javax.swing.JLabel set3;
    private javax.swing.JLabel set4;
    private javax.swing.JLabel set5;
    // End of variables declaration//GEN-END:variables
}
