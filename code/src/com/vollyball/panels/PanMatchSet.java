/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.bean.SetTimeout;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.CreateRallyRotationDialog;
import com.vollyball.dialog.SetRotationDialog;
import com.vollyball.dialog.SetSubstituteSelectPlayerDialog;
import com.vollyball.enums.HomeOpponent;
import com.vollyball.enums.Skill;
import com.vollyball.util.CommonUtil;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;
import uk.co.caprica.vlcj.runtime.x.LibXUtil;

/**
 *
 * @author nishant.vibhute
 */
public class PanMatchSet extends javax.swing.JPanel {

    public int currentRally = 0;
    int setNum;
    int matchId;
    public int teamEvaluateId;
    public int opponentId;
    public int matchEvaluationId = 0;
    public int rallyNumNext = 0, totalRallies = 0;
    public SetRotationDialog setRotationDialog;
    public LinkedHashMap<Integer, RallyEvaluation> rallyMap = new LinkedHashMap<Integer, RallyEvaluation>();
    public LinkedHashMap<Integer, PanRallyLiveEvaluation> panRallyMap = new LinkedHashMap<Integer, PanRallyLiveEvaluation>();
    public LinkedHashMap<Integer, Player> initialPositionMap;
    public LinkedHashMap<Integer, Player> rallyPositionMap;

    public LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<String, Player> ChestMap = new LinkedHashMap<String, Player>();
    MatchDao matchDao = new MatchDao();
    TeamDao teamDao = new TeamDao();
    RallyDao rallyDao = new RallyDao();
    PanRallyLiveEvaluation panRallyCurrent;
    int totalRally = 0;

    List<Player> playerList;
    public int homeScore = 0, opponentScore = 0;
    String currentScore;
    String startTime, endTime;
    int op = 0, tf = 0;
    int evaluationType, matchEvaluationTeamId;

    /**
     * Creates new form PanMatchSet
     *
     * @param setNum
     * @param matchId
     * @param teamEvaluateId
     * @param opponentId
     */
    public PanMatchSet(int setNum, int matchId, int teamEvaluateId, int opponentId, int evaluationType, int matchEvaluationTeamId) {

        initComponents();

//        Setup s1 = new Setup();
//        s1.executeQuery("delete from VollyCourtCoordinates", SetupEnum.Database, 155);
//        s1.insertVollyCoordinate(100);
        setBackNextInVisible();
        if (evaluationType == 2) {
            initializePlayer();
        }
        playerList = teamDao.getTeamPlayers(teamEvaluateId);
        panRallyList.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setNum = setNum;
        this.matchId = matchId;
        this.teamEvaluateId = teamEvaluateId;
        this.opponentId = opponentId;
        this.evaluationType = evaluationType;
        this.matchEvaluationTeamId = matchEvaluationTeamId;
        initialPositionMap = new LinkedHashMap<>();
        rallyPositionMap = new LinkedHashMap<>();

        MatchSet ms = matchDao.getMatchSet(setNum, matchEvaluationTeamId);

//        List<Player> playerListL = teamDao.getTeamPlayers(matchEvaluationTeamId);
        for (Player p : playerList) {
            playerMap.put(p.getId(), p);
            ChestMap.put(p.getChestNo(), p);
        }

        if (ms.getId() != 0) {
            this.matchEvaluationId = ms.getId();
            lblDate.setText(CommonUtil.ConvertDateFromDbToNormal(ms.getDate()));
            lblTime.setText(ms.getStart_time());
            String evaluationName = Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getEvaluationTeamId());
            String opponentName = Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getOpponentTeamId());
            lblevaluationName.setText(evaluationName);
            lblopponentName.setText(opponentName);
            lblTimeOutEvalTeam.setText(evaluationName);
            lblTimeOutOppTeam.setText(opponentName);
            txtEvaluator.setText(ms.getEvaluator());
            lblScore.setText(ms.getHomeScore() + " - " + ms.getOpponentScore());
            homeScore = ms.getHomeScore();
            opponentScore = ms.getOpponentScore();
            op = ms.getOp();
            tf = ms.getTf();
            lblOp.setText("" + op);
            lblTf.setText("" + tf);
            lblWonBy.setText(Controller.panMatchEvaluationHome.getTeamsMap().get(ms.getWon_by()));

            for (SetRotationOrder s : ms.getRotationOrder()) {
                initialPositionMap.put(s.getPosition(), playerMap.get(s.getPlayerId()));
            }

            for (SetTimeout s : ms.getSetTimeout()) {
                setTimeOut(s);
            }

            for (SetSubstitution s : ms.getSetSubstitutions()) {
                String cNo = s.getSubstitutePlayerId() == 0 ? "" : playerMap.get(s.getSubstitutePlayerId()).getChestNo();
                String p1 = s.getPoint1();
                String p2 = s.getPoint2();
                switch (s.getPosition()) {
                    case 1:
                        su1.setText(cNo);
                        pt11.setText(p1);
                        pt21.setText(p2);
                        break;
                    case 2:
                        su2.setText(cNo);
                        pt12.setText(p1);
                        pt22.setText(p2);
                        break;
                    case 3:
                        su3.setText(cNo);
                        pt13.setText(p1);
                        pt23.setText(p2);
                        break;
                    case 4:
                        su4.setText(cNo);
                        pt14.setText(p1);
                        pt24.setText(p2);
                        break;
                    case 5:
                        su5.setText(cNo);
                        pt15.setText(p1);
                        pt25.setText(p2);
                        break;
                    case 6:
                        su6.setText(cNo);
                        pt16.setText(p1);
                        pt26.setText(p2);
                        break;
                }
            }

            pos1.setText(initialPositionMap.get(1).getChestNo());
            pos2.setText(initialPositionMap.get(2).getChestNo());
            pos3.setText(initialPositionMap.get(3).getChestNo());
            pos4.setText(initialPositionMap.get(4).getChestNo());
            pos5.setText(initialPositionMap.get(5).getChestNo());
            pos6.setText(initialPositionMap.get(6).getChestNo());
            libero.setText(initialPositionMap.get(7).getChestNo());

            ro1.setText(initialPositionMap.get(1).getChestNo());
            ro2.setText(initialPositionMap.get(2).getChestNo());
            ro3.setText(initialPositionMap.get(3).getChestNo());
            ro4.setText(initialPositionMap.get(4).getChestNo());
            ro5.setText(initialPositionMap.get(5).getChestNo());
            ro6.setText(initialPositionMap.get(6).getChestNo());

            List<RallyEvaluation> rallies = rallyDao.getRalliesList(matchEvaluationId);

            totalRallies = rallies.size();
            for (RallyEvaluation rally : rallies) {
                PanRallyBut pnBut = new PanRallyBut();
                pnBut.setRally(rally.getRallyNum(), matchEvaluationId, initialPositionMap, evaluationType);
                panRallyList.add(pnBut);
                currentRally++;
            }
            rallyPositionMap = rallyDao.getLatestRallyRotationOrder(matchEvaluationId, ms.getEvaluationTeamId());
            if (totalRallies == 0) {
                butNext.setText("START");
            } else {
                showRallyList();
            }
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
            Date date = new Date();
            lblDate.setText(formatter.format(date));
            lblTime.setText(formatterTime.format(date));
            String evaluationName = Controller.panMatchEvaluationHome.getTeamsMap().get(teamEvaluateId);
            String opponentName = Controller.panMatchEvaluationHome.getTeamsMap().get(opponentId);
            lblevaluationName.setText(evaluationName);
            lblopponentName.setText(opponentName);
            lblTimeOutEvalTeam.setText(evaluationName);
            lblTimeOutOppTeam.setText(opponentName);
//            panNext.setVisible(false);
        }
//        panRallyNew = new PanRallyNew();
//        panRallyList.add(panRallyNew);
        setScore();
    }

    public void setBackNextInVisible() {
        lblBack.setVisible(false);
        lblNext.setVisible(false);
    }

    public void setBackNextVisible() {
        lblBack.setVisible(true);
        lblNext.setVisible(true);
    }

    public void setScore() {
        currentScore = homeScore + " - " + opponentScore;
        lblScore.setText(currentScore);
    }

    public LinkedHashMap<Integer, RallyEvaluation> getRallyMap() {
        return rallyMap;
    }

    public LinkedHashMap<Integer, PanRallyLiveEvaluation> getPanRallyMap() {
        return panRallyMap;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblevaluationName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblopponentName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblWonBy = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextField12 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        pt11 = new javax.swing.JTextField();
        pt12 = new javax.swing.JTextField();
        pt13 = new javax.swing.JTextField();
        pt14 = new javax.swing.JTextField();
        pt15 = new javax.swing.JTextField();
        pt16 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        pt21 = new javax.swing.JTextField();
        pt22 = new javax.swing.JTextField();
        pt23 = new javax.swing.JTextField();
        pt24 = new javax.swing.JTextField();
        pt25 = new javax.swing.JTextField();
        pt26 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        su1 = new javax.swing.JTextField();
        su2 = new javax.swing.JTextField();
        su3 = new javax.swing.JTextField();
        su4 = new javax.swing.JTextField();
        su5 = new javax.swing.JTextField();
        su6 = new javax.swing.JTextField();
        ro1 = new javax.swing.JTextField();
        ro2 = new javax.swing.JTextField();
        ro5 = new javax.swing.JTextField();
        ro6 = new javax.swing.JTextField();
        ro3 = new javax.swing.JTextField();
        ro4 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        but1 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        but2 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        but3 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        but4 = new javax.swing.JPanel();
        but5 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        but6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jTextField17 = new javax.swing.JTextField();
        tm1 = new javax.swing.JTextField();
        tm2 = new javax.swing.JTextField();
        tm3 = new javax.swing.JTextField();
        tm4 = new javax.swing.JTextField();
        tm5 = new javax.swing.JTextField();
        tm6 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        ScoreA1 = new javax.swing.JTextField();
        ScoreA2 = new javax.swing.JTextField();
        ScoreA3 = new javax.swing.JTextField();
        ScoreA4 = new javax.swing.JTextField();
        ScoreA5 = new javax.swing.JTextField();
        ScoreA6 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        ScoreB1 = new javax.swing.JTextField();
        ScoreB2 = new javax.swing.JTextField();
        ScoreB3 = new javax.swing.JTextField();
        ScoreB4 = new javax.swing.JTextField();
        ScoreB5 = new javax.swing.JTextField();
        ScoreB6 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        lblTimeOutEvalTeam = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        lblTimeOutOppTeam = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        pos4 = new javax.swing.JTextField();
        pos3 = new javax.swing.JTextField();
        pos2 = new javax.swing.JTextField();
        pos5 = new javax.swing.JTextField();
        pos6 = new javax.swing.JTextField();
        pos1 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        lblrotateleft = new javax.swing.JLabel();
        lblrotateright = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        lblSetRotationOrder = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        libero = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField44 = new javax.swing.JTextField();
        lblOp = new javax.swing.JTextField();
        jTextField43 = new javax.swing.JTextField();
        lblTf = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        lblRallyHeading = new javax.swing.JLabel();
        lblNext = new javax.swing.JLabel();
        lblBack = new javax.swing.JLabel();
        panButton = new javax.swing.JPanel();
        panNext = new javax.swing.JPanel();
        butNext = new javax.swing.JLabel();
        panRallyShow = new javax.swing.JPanel();
        panRallyList = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        txtEvaluator = new javax.swing.JTextField();
        panShowRallyInput = new javax.swing.JPanel();
        panPlayer = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(54, 78, 108));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Score :");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Evaluating Team :");

        lblevaluationName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblevaluationName.setForeground(new java.awt.Color(251, 205, 1));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Opponent Team :");

        lblopponentName.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblopponentName.setForeground(new java.awt.Color(251, 205, 1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Won By :");

        lblScore.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblScore.setForeground(new java.awt.Color(251, 205, 1));
        lblScore.setText(" ");

        lblWonBy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblWonBy.setForeground(new java.awt.Color(251, 205, 1));
        lblWonBy.setText(" ");
        lblWonBy.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblevaluationName, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblopponentName, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(lblWonBy, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel1)
                    .addComponent(lblevaluationName)
                    .addComponent(jLabel2)
                    .addComponent(lblopponentName)
                    .addComponent(jLabel7)
                    .addComponent(lblScore)
                    .addComponent(lblWonBy))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel6.setForeground(new java.awt.Color(54, 78, 108));
        jPanel6.setEnabled(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField12.setText("RO");
        jTextField12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField12.setEnabled(false);
        jTextField12.setOpaque(false);

        jTextField22.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setText("SU");
        jTextField22.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField22.setEnabled(false);
        jTextField22.setOpaque(false);

        jTextField29.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setText("PT");
        jTextField29.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField29.setEnabled(false);
        jTextField29.setOpaque(false);

        pt11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt11.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt11.setEnabled(false);
        pt11.setOpaque(false);

        pt12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pt12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt12.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt12.setEnabled(false);
        pt12.setOpaque(false);

        pt13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt13.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt13.setEnabled(false);
        pt13.setOpaque(false);

        pt14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt14.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt14.setEnabled(false);
        pt14.setOpaque(false);

        pt15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt15.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt15.setEnabled(false);
        pt15.setOpaque(false);

        pt16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt16.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt16.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt16.setEnabled(false);
        pt16.setOpaque(false);

        jTextField36.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField36.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField36.setText("PT");
        jTextField36.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField36.setEnabled(false);
        jTextField36.setOpaque(false);

        pt21.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt21.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt21.setEnabled(false);
        pt21.setOpaque(false);

        pt22.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt22.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt22.setEnabled(false);
        pt22.setOpaque(false);

        pt23.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt23.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt23.setEnabled(false);
        pt23.setOpaque(false);

        pt24.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt24.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt24.setEnabled(false);
        pt24.setOpaque(false);

        pt25.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt25.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt25.setEnabled(false);
        pt25.setOpaque(false);

        pt26.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        pt26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pt26.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pt26.setEnabled(false);
        pt26.setOpaque(false);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        su1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su1.setEnabled(false);
        su1.setOpaque(false);

        su2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su2.setEnabled(false);
        su2.setOpaque(false);

        su3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su3.setEnabled(false);
        su3.setOpaque(false);

        su4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su4.setEnabled(false);
        su4.setOpaque(false);

        su5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su5.setEnabled(false);
        su5.setOpaque(false);

        su6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        su6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        su6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        su6.setEnabled(false);
        su6.setOpaque(false);

        ro1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro1.setEnabled(false);
        ro1.setOpaque(false);

        ro2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro2.setEnabled(false);
        ro2.setOpaque(false);

        ro5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro5.setEnabled(false);
        ro5.setOpaque(false);

        ro6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro6.setEnabled(false);
        ro6.setOpaque(false);

        ro3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro3.setEnabled(false);
        ro3.setOpaque(false);

        ro4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ro4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ro4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ro4.setEnabled(false);
        ro4.setOpaque(false);

        jLabel25.setBackground(new java.awt.Color(255, 255, 255));
        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("SUBSTITUTE");
        jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        jPanel16.setBackground(new java.awt.Color(57, 74, 108));
        jPanel16.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but1.setForeground(new java.awt.Color(255, 255, 255));
        but1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but1.setText("Select");
        but1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but1, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        jPanel25.setBackground(new java.awt.Color(57, 74, 108));
        jPanel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but2.setForeground(new java.awt.Color(255, 255, 255));
        but2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but2.setText("Select");
        but2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(57, 74, 108));
        jPanel27.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but3.setForeground(new java.awt.Color(255, 255, 255));
        but3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but3.setText("Select");
        but3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but3, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel29.setBackground(new java.awt.Color(57, 74, 108));
        jPanel29.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Select");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        but4.setBackground(new java.awt.Color(57, 74, 108));
        but4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but5.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but5.setForeground(new java.awt.Color(255, 255, 255));
        but5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but5.setText("Select");
        but5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout but4Layout = new javax.swing.GroupLayout(but4);
        but4.setLayout(but4Layout);
        but4Layout.setHorizontalGroup(
            but4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        but4Layout.setVerticalGroup(
            but4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel33.setBackground(new java.awt.Color(57, 74, 108));
        jPanel33.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        but6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        but6.setForeground(new java.awt.Color(255, 255, 255));
        but6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        but6.setText("Select");
        but6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                but6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but6, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(but6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt21, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt22, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt24, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt25, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pt26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(su1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(pt11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pt12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(su2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(pt13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pt16, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(su3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(su4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ro4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(but4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ro5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(su5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(su6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ro6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(ro1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ro2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ro3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(but4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ro6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(su6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pt16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pt26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pt25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField17.setEditable(false);
        jTextField17.setBackground(new java.awt.Color(255, 255, 255));
        jTextField17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField17.setText("TM");
        jTextField17.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField17.setEnabled(false);

        tm1.setEditable(false);
        tm1.setBackground(new java.awt.Color(255, 255, 255));
        tm1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tm1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tm1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tm1.setEnabled(false);

        tm2.setEditable(false);
        tm2.setBackground(new java.awt.Color(255, 255, 255));
        tm2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tm2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tm2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tm2.setEnabled(false);

        tm3.setEditable(false);
        tm3.setBackground(new java.awt.Color(255, 255, 255));
        tm3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tm3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tm3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tm3.setEnabled(false);

        tm4.setEditable(false);
        tm4.setBackground(new java.awt.Color(255, 255, 255));
        tm4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tm4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tm4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tm4.setEnabled(false);

        tm5.setEditable(false);
        tm5.setBackground(new java.awt.Color(255, 255, 255));
        tm5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tm5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tm5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tm5.setEnabled(false);

        tm6.setEditable(false);
        tm6.setBackground(new java.awt.Color(255, 255, 255));
        tm6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        tm6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tm6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tm6.setEnabled(false);
        tm6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tm6ActionPerformed(evt);
            }
        });

        jTextField51.setEditable(false);
        jTextField51.setBackground(new java.awt.Color(255, 255, 255));
        jTextField51.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField51.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField51.setText("A");
        jTextField51.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField51.setEnabled(false);

        ScoreA1.setEditable(false);
        ScoreA1.setBackground(new java.awt.Color(255, 255, 255));
        ScoreA1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreA1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreA1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreA1.setEnabled(false);

        ScoreA2.setEditable(false);
        ScoreA2.setBackground(new java.awt.Color(255, 255, 255));
        ScoreA2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreA2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreA2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreA2.setEnabled(false);

        ScoreA3.setEditable(false);
        ScoreA3.setBackground(new java.awt.Color(255, 255, 255));
        ScoreA3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreA3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreA3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreA3.setEnabled(false);

        ScoreA4.setEditable(false);
        ScoreA4.setBackground(new java.awt.Color(255, 255, 255));
        ScoreA4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreA4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreA4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreA4.setEnabled(false);

        ScoreA5.setEditable(false);
        ScoreA5.setBackground(new java.awt.Color(255, 255, 255));
        ScoreA5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreA5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreA5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreA5.setEnabled(false);

        ScoreA6.setEditable(false);
        ScoreA6.setBackground(new java.awt.Color(255, 255, 255));
        ScoreA6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreA6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreA6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreA6.setEnabled(false);

        jTextField58.setEditable(false);
        jTextField58.setBackground(new java.awt.Color(255, 255, 255));
        jTextField58.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jTextField58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField58.setText("B");
        jTextField58.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField58.setEnabled(false);

        ScoreB1.setEditable(false);
        ScoreB1.setBackground(new java.awt.Color(255, 255, 255));
        ScoreB1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreB1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreB1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreB1.setEnabled(false);

        ScoreB2.setEditable(false);
        ScoreB2.setBackground(new java.awt.Color(255, 255, 255));
        ScoreB2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreB2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreB2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreB2.setEnabled(false);

        ScoreB3.setEditable(false);
        ScoreB3.setBackground(new java.awt.Color(255, 255, 255));
        ScoreB3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreB3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreB3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreB3.setEnabled(false);

        ScoreB4.setEditable(false);
        ScoreB4.setBackground(new java.awt.Color(255, 255, 255));
        ScoreB4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreB4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreB4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreB4.setEnabled(false);

        ScoreB5.setEditable(false);
        ScoreB5.setBackground(new java.awt.Color(255, 255, 255));
        ScoreB5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreB5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreB5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreB5.setEnabled(false);

        ScoreB6.setEditable(false);
        ScoreB6.setBackground(new java.awt.Color(255, 255, 255));
        ScoreB6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        ScoreB6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        ScoreB6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        ScoreB6.setEnabled(false);

        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("TIMEOUT");
        jTextField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField1.setEnabled(false);
        jTextField1.setOpaque(false);

        jPanel17.setBackground(new java.awt.Color(57, 74, 108));
        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTimeOutEvalTeam.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTimeOutEvalTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblTimeOutEvalTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimeOutEvalTeam.setText("SET");
        lblTimeOutEvalTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimeOutEvalTeamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(lblTimeOutEvalTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTimeOutEvalTeam, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(57, 74, 108));
        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTimeOutOppTeam.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTimeOutOppTeam.setForeground(new java.awt.Color(255, 255, 255));
        lblTimeOutOppTeam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimeOutOppTeam.setText("SET");
        lblTimeOutOppTeam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTimeOutOppTeamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(lblTimeOutOppTeam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(lblTimeOutOppTeam, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tm1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField1))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(tm2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tm3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tm4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tm5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(tm6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreB1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreB2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreB3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreB4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreB5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreA1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreA2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreA3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreA4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(ScoreA5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScoreA6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ScoreB6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreA1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreA2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreA3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreA4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreA5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreA6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreB1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreB2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreB3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreB4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreB5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ScoreB6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        pos4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        pos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos4.setEnabled(false);
        pos4.setOpaque(false);

        pos3.setEditable(false);
        pos3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos3.setEnabled(false);
        pos3.setOpaque(false);

        pos2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos2.setEnabled(false);
        pos2.setOpaque(false);

        pos5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        pos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos5.setEnabled(false);
        pos5.setOpaque(false);

        pos6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos6.setEnabled(false);
        pos6.setOpaque(false);

        pos1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pos1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        pos1.setEnabled(false);
        pos1.setOpaque(false);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        lblrotateleft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrotateleft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/arrow_rotate_anticlockwise.png"))); // NOI18N
        lblrotateleft.setToolTipText("Rotate Left");
        lblrotateleft.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblrotateleftMouseClicked(evt);
            }
        });

        lblrotateright.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrotateright.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/arrow_rotate_clockwise.png"))); // NOI18N
        lblrotateright.setToolTipText("Rotate Right");
        lblrotateright.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblrotaterightMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(lblrotateleft, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblrotateright, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblrotateleft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblrotateright, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(54, 78, 108));
        jPanel10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSetRotationOrder.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSetRotationOrder.setForeground(new java.awt.Color(255, 255, 255));
        lblSetRotationOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSetRotationOrder.setText("SET");
        lblSetRotationOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetRotationOrderMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetRotationOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSetRotationOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTextField5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText(" LIEBRO");
        jTextField5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField5.setEnabled(false);
        jTextField5.setOpaque(false);

        libero.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        libero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        libero.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        libero.setEnabled(false);
        libero.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField5))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pos1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(libero)))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pos4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pos2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pos6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pos5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pos1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(libero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTextField3.setBackground(new java.awt.Color(0, 0, 0));
        jTextField3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("ROTATION ORDER");
        jTextField3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField3.setEnabled(false);
        jTextField3.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField3))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField44.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField44.setText("OP +");
        jTextField44.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField44.setEnabled(false);
        jTextField44.setOpaque(false);
        jTextField44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField44ActionPerformed(evt);
            }
        });

        lblOp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblOp.setText("0");
        lblOp.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblOp.setEnabled(false);
        lblOp.setOpaque(false);

        jTextField43.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField43.setText("TF -");
        jTextField43.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField43.setEnabled(false);
        jTextField43.setOpaque(false);

        lblTf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lblTf.setText("0");
        lblTf.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        lblTf.setEnabled(false);
        lblTf.setOpaque(false);

        jTextField2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(" PLUS & MINUS POINT");
        jTextField2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField2.setEnabled(false);
        jTextField2.setOpaque(false);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField2)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField44)
                    .addComponent(lblOp, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTf)
                    .addComponent(jTextField43)))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField43, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOp, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jPanel7.setBackground(new java.awt.Color(57, 74, 108));
        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblRallyHeading.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyHeading.setForeground(new java.awt.Color(255, 255, 255));
        lblRallyHeading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRallyHeading.setText("RALLY");

        lblNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/arrownext.png"))); // NOI18N
        lblNext.setToolTipText("Next");
        lblNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNextMouseClicked(evt);
            }
        });

        lblBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/arrowback.png"))); // NOI18N
        lblBack.setToolTipText("Prev");
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lblBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRallyHeading, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblNext))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRallyHeading, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBack, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblNext, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );

        panButton.setBackground(new java.awt.Color(255, 255, 255));
        panButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panNext.setBackground(new java.awt.Color(57, 74, 108));
        panNext.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        butNext.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        butNext.setForeground(new java.awt.Color(255, 255, 255));
        butNext.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        butNext.setText("START");
        butNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butNextMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panNextLayout = new javax.swing.GroupLayout(panNext);
        panNext.setLayout(panNextLayout);
        panNextLayout.setHorizontalGroup(
            panNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butNext, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        panNextLayout.setVerticalGroup(
            panNextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butNext, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panButtonLayout = new javax.swing.GroupLayout(panButton);
        panButton.setLayout(panButtonLayout);
        panButtonLayout.setHorizontalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panButtonLayout.setVerticalGroup(
            panButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panButtonLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(panNext, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        panRallyShow.setBackground(new java.awt.Color(255, 255, 255));
        panRallyShow.setLayout(new java.awt.BorderLayout());

        panRallyList.setBackground(new java.awt.Color(255, 255, 255));
        panRallyList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panRallyShow.add(panRallyList, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panRallyShow, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRallyShow, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Name of Evaluator");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Date :");

        lblDate.setBackground(new java.awt.Color(255, 255, 255));
        lblDate.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        lblTime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Time :");

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtEvaluator.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtEvaluator.setBorder(null);
        txtEvaluator.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtEvaluator, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtEvaluator, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(21, 21, 21)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(5, 5, 5))
        );

        panShowRallyInput.setBackground(new java.awt.Color(255, 255, 255));
        panShowRallyInput.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        panPlayer.setBackground(new java.awt.Color(255, 255, 255));
        panPlayer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panPlayer.setLayout(new java.awt.BorderLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("LIVE");
        panPlayer.add(jLabel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout panShowRallyInputLayout = new javax.swing.GroupLayout(panShowRallyInput);
        panShowRallyInput.setLayout(panShowRallyInputLayout);
        panShowRallyInputLayout.setHorizontalGroup(
            panShowRallyInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panShowRallyInputLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        panShowRallyInputLayout.setVerticalGroup(
            panShowRallyInputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panShowRallyInputLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panShowRallyInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panShowRallyInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField44ActionPerformed

    private void lblSetRotationOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetRotationOrderMouseClicked
        // TODO add your handling code here:
        setRotationDialog = new SetRotationDialog();
        setRotationDialog.setMatchId(this.matchId);
        setRotationDialog.setTeamEvaluteId(this.teamEvaluateId);
        setRotationDialog.init();
        setRotationDialog.show();

    }//GEN-LAST:event_lblSetRotationOrderMouseClicked

    private void lblrotateleftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrotateleftMouseClicked
        // TODO add your handling code here:
        String temp = pos1.getText();
        pos1.setText(pos6.getText());
        pos6.setText(pos5.getText());
        pos5.setText(pos4.getText());
        pos4.setText(pos3.getText());
        pos3.setText(pos2.getText());
        pos2.setText(temp);

        String temp1 = ro1.getText();
        ro1.setText(ro6.getText());
        ro6.setText(ro5.getText());
        ro5.setText(ro4.getText());
        ro4.setText(ro3.getText());
        ro3.setText(ro2.getText());
        ro2.setText(temp1);

    }//GEN-LAST:event_lblrotateleftMouseClicked

    private void lblrotaterightMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblrotaterightMouseClicked
        // TODO add your handling code here:
        String temp = pos1.getText();
        pos1.setText(pos2.getText());
        pos2.setText(pos3.getText());
        pos3.setText(pos4.getText());
        pos4.setText(pos5.getText());
        pos5.setText(pos6.getText());
        pos6.setText(temp);

        String temp1 = ro1.getText();
        ro1.setText(ro2.getText());
        ro2.setText(ro3.getText());
        ro3.setText(ro4.getText());
        ro4.setText(ro5.getText());
        ro5.setText(ro6.getText());
        ro6.setText(temp1);
    }//GEN-LAST:event_lblrotaterightMouseClicked

    private void butNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butNextMouseClicked
        // TODO add your handling code here:
        Object o = evt.getSource();
        String type = ((JLabel) o).getText();
        switch (type) {
            case "EXIT":
                Controller.panMatchEvaluationHome.obj.close();
                break;
            case "NEW":
                newRally();
                break;
            case "START":
                if (txtEvaluator.getText().equals("")) {
                    JOptionPane.showMessageDialog(this, "Please Enter Evaluator Name", "Error", 2);
                } else if (initialPositionMap.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please Set Rotation Order", "Error", 2);
                } else {

                    panNext.setVisible(true);
//                    currentRally++;
                    if (this.matchEvaluationId == 0) {
                        MatchSet ms = new MatchSet();
                        ms.setMatchEvaluationTeamId(matchEvaluationTeamId);
                        ms.setEvaluationTeamId(teamEvaluateId);
                        ms.setOpponentTeamId(opponentId);
                        ms.setStart_time(lblTime.getText());
                        ms.setEvaluator(txtEvaluator.getText());
                        ms.setSetNo(setNum);
                        ms.setEnd_time("00:00");
                        ms.setDate(CommonUtil.ConvertDateFromNormalToDB(lblDate.getText()));

                        for (Map.Entry<Integer, Player> entry : initialPositionMap.entrySet()) {
                            SetRotationOrder sro = new SetRotationOrder();
                            sro.setPosition(entry.getKey());
                            sro.setPlayerId(entry.getValue().getId());
                            ms.getRotationOrder().add(sro);
                        }

                        for (Map.Entry<Integer, Player> entry : initialPositionMap.entrySet()) {
                            SetSubstitution sro = new SetSubstitution();
                            sro.setPosition(entry.getKey());
                            sro.setRotation_player_id(entry.getValue().getId());
                            ms.getSetSubstitutions().add(sro);
                        }

                        matchEvaluationId = matchDao.saveMatchSet(ms);
                    }
//                    PanRallyBut pnBut = new PanRallyBut();
//                    pnBut.setRally(currentRally, matchEvaluationId, positionMap);
//                    panRallyList.add(pnBut);
                    rallyPositionMap.putAll(initialPositionMap);
                    CreateRallyRotationDialog obj = new CreateRallyRotationDialog();
                    obj.init(obj);
                    obj.show();

                    panRallyShow.removeAll();
                    int rallynum = currentRally + 1;
                    panRallyCurrent = new PanRallyLiveEvaluation(rallynum, matchEvaluationId, rallyPositionMap, evaluationType);
                    panRallyShow.add(panRallyCurrent);
                    Controller.panMatchSet.validate();
                    Controller.panMatchSet.repaint();
                    panNext.setVisible(true);
                    butNext.setText("SAVE");
                }
                break;
            case "UPDATE":
                List<Integer> updated = new ArrayList<>();
                RallyEvaluation rallyUpdate = new RallyEvaluation();
                rallyUpdate.setId(panRallyCurrent.id);
                rallyUpdate.setRallyNum(panRallyCurrent.rallyNum);
//                rallyUpdate.setHomeScore();
                rallyUpdate.setMatchEvaluationId(matchEvaluationId);
                rallyUpdate.setRallyPositionMap(rallyPositionMap);

                if (panRallyCurrent.panListRow.size() != 0) {
                    for (int i = 0; i < panRallyCurrent.panListRow.size(); i++) {
                        try {
                            PanRallyEvaluationRow panRallyEvaluationRow = panRallyCurrent.panListRow.get(i);
                            if (!panRallyEvaluationRow.txtSkill.getText().isEmpty()) {
                                RallyEvaluationSkillScore rs = new RallyEvaluationSkillScore();
                                updated.add(panRallyEvaluationRow.id);
                                rs.setId(panRallyEvaluationRow.id);
                                rs.setSkill(panRallyEvaluationRow.txtSkill.getText());
                                rs.setSkillId(Skill.getIdByName(panRallyEvaluationRow.txtSkill.getText()).getId());
                                rs.setChestNo(String.valueOf(panRallyEvaluationRow.txtChestNum.getText()));
                                rs.setPlayerId(panRallyEvaluationRow.playerId);
                                rs.setScore(Integer.parseInt(String.valueOf(panRallyEvaluationRow.cmbScore.getSelectedItem())));
                                rs.setOrderNum(i + 1);
                                if (panRallyEvaluationRow.isDetailed) {
                                    rs.setIsDetailed(true);
                                    rs.setDetailsValues(panRallyEvaluationRow.getDetailsValues());
                                }
                                rallyUpdate.getRallyEvaluationSkillScore().add(rs);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(PanRallyLiveEvaluation.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                    PanRallyEvaluationRow panRallyEvaluationRowStart = panRallyCurrent.panListRow.get(0);
                    PanRallyEvaluationRow panRallyEvaluationRowEnd = panRallyCurrent.panListRow.get(panRallyCurrent.panListRow.size() - 1);
                    if (panRallyEvaluationRowStart.txtSkill.getText().equals(Skill.Service.getType())) {
                        rallyUpdate.setStartby(HomeOpponent.HOME.getId());
                    } else {
                        rallyUpdate.setStartby(HomeOpponent.OPPONENT.getId());
                    }

                    if (Integer.parseInt(String.valueOf(panRallyEvaluationRowEnd.cmbScore.getSelectedItem())) == 5) {
                        rallyUpdate.setWonby(HomeOpponent.HOME.getId());
                    } else {
                        rallyUpdate.setWonby(HomeOpponent.OPPONENT.getId());
                    }

                    int updatedScore = Integer.parseInt(String.valueOf(panRallyCurrent.panListRow.get(panRallyCurrent.panListRow.size() - 1).cmbScore.getSelectedItem()));
                    String updatedSkill = panRallyCurrent.panListRow.get(panRallyCurrent.panListRow.size() - 1).txtSkill.getText();

                    int updatedSkillid = Skill.getIdByName(panRallyCurrent.panListRow.get(panRallyCurrent.panListRow.size() - 1).txtSkill.getText()).getId();
                    if (panRallyCurrent.oldSkillid == Skill.OP.getId() || panRallyCurrent.oldSkillid == Skill.TF.getId()) {
                        if (panRallyCurrent.oldSkillid == Skill.OP.getId() && updatedSkillid != Skill.OP.getId()) {
                            if (updatedSkillid == Skill.TF.getId()) {
                                tf++;
                            }
                            op--;
                        }

                        if (panRallyCurrent.oldSkillid == Skill.TF.getId() && updatedSkillid != Skill.TF.getId()) {
                            if (updatedSkillid == Skill.OP.getId()) {
                                op++;
                            }
                            tf--;
                        }
                    } else {
                        if (updatedSkillid == Skill.TF.getId()) {
                            tf++;
                        }
                        if (updatedSkillid == Skill.OP.getId()) {
                            op++;
                        }
                    }

                    if (panRallyCurrent.oldRallyEndScore != updatedScore) {

                        switch (updatedScore) {

                            case 1:

                                if (panRallyCurrent.homeScore != 0) {
                                    panRallyCurrent.homeScore--;
                                }
                                panRallyCurrent.opponentScore++;
                                rallyUpdate.setScoreSubtracted("Home");

                                break;
                            case 5:
                                if (updatedSkill.equals(Skill.Service.getType()) || updatedSkill.equals(Skill.Attack.getType()) || updatedSkill.equals(Skill.Block.getType()) || updatedSkill.equals(Skill.OP.getType())) {
                                    panRallyCurrent.homeScore++;
                                    if (panRallyCurrent.opponentScore != 0) {
                                        panRallyCurrent.opponentScore--;
                                    }
                                    rallyUpdate.setScoreSubtracted("Opponent");
                                } else {
                                    JOptionPane.showMessageDialog(panRallyCurrent, "Rally Not End");
                                }
                                break;
                            default:
                                JOptionPane.showMessageDialog(panRallyCurrent, "Rally Not End");
                                break;
                        }
                        rallyUpdate.setHomeScore(panRallyCurrent.homeScore);
                        rallyUpdate.setOpponentScore(panRallyCurrent.opponentScore);
                        rallyUpdate.setOp(op);
                        rallyUpdate.setTf(tf);

//                    JOptionPane.showMessageDialog(panRallyCurrent, "New score of rally : \nHome Score = " + panRallyCurrent.homeScore + "\nOpp Score =" + panRallyCurrent.opponentScore);
                    } else {
//                    JOptionPane.showMessageDialog(panRallyCurrent, "Same Score");
                        rallyUpdate.setHomeScore(panRallyCurrent.homeScore);
                        rallyUpdate.setOpponentScore(panRallyCurrent.opponentScore);
                        rallyUpdate.setScoreSubtracted("None");
                        rallyUpdate.setOp(op);
                        rallyUpdate.setTf(tf);
                    }

                    int id = rallyDao.updateRally(rallyUpdate, updated);
                    if (id != 0) {
                        JOptionPane.showMessageDialog(panRallyCurrent, "Updated Successfully");

                        Controller.panMatchSet.panRallyCurrent = new PanRallyLiveEvaluation(rallyNumNext, matchEvaluationId, rallyPositionMap, evaluationType);
                        Controller.panMatchSet.panNext.setVisible(true);
                        Controller.panMatchSet.panRallyShow.removeAll();
                        Controller.panMatchSet.panRallyShow.add(Controller.panMatchSet.panRallyCurrent);

                        MatchSet ms = matchDao.getMatchSet(setNum, matchEvaluationTeamId);
                        lblScore.setText(ms.getHomeScore() + " - " + ms.getOpponentScore());
                        homeScore = ms.getHomeScore();
                        opponentScore = ms.getOpponentScore();
                        op = ms.getOp();
                        tf = ms.getTf();
                        lblOp.setText("" + op);
                        lblTf.setText("" + tf);

                        if (homeScore >= 25 || opponentScore >= 25) {
                            List<Integer> arr = new ArrayList();
                            arr.add(homeScore);
                            arr.add(opponentScore);
                            int max = Collections.max(arr);
                            int min = Collections.min(arr);
                            if ((max - min) >= 2) {

                                showRallyList();

                                if (max == homeScore) {
                                    lblWonBy.setText(lblevaluationName.getText());
                                    matchDao.updateMatchSetWonBy(teamEvaluateId, matchEvaluationId);
                                } else {
                                    lblWonBy.setText(lblopponentName.getText());
                                    matchDao.updateMatchSetWonBy(opponentId, matchEvaluationId);
                                }
                                setScore();
                                JOptionPane.showMessageDialog(Controller.panMatchSet, "Set Won By : " + lblWonBy.getText() + "\n Score is := " + homeScore + " : " + opponentScore);
                                Controller.panMatchSet.validate();
                                Controller.panMatchSet.repaint();
                            }
                        }

                        for (SetTimeout s : ms.getSetTimeout()) {
                            setTimeOut(s);
                        }

                        for (SetSubstitution s : ms.getSetSubstitutions()) {
                            String cNo = s.getSubstitutePlayerId() == 0 ? "" : playerMap.get(s.getSubstitutePlayerId()).getChestNo();
                            String p1 = s.getPoint1();
                            String p2 = s.getPoint2();
                            switch (s.getPosition()) {
                                case 1:
                                    su1.setText(cNo);
                                    pt11.setText(p1);
                                    pt21.setText(p2);
                                    break;
                                case 2:
                                    su2.setText(cNo);
                                    pt12.setText(p1);
                                    pt22.setText(p2);
                                    break;
                                case 3:
                                    su3.setText(cNo);
                                    pt13.setText(p1);
                                    pt23.setText(p2);
                                    break;
                                case 4:
                                    su4.setText(cNo);
                                    pt14.setText(p1);
                                    pt24.setText(p2);
                                    break;
                                case 5:
                                    su5.setText(cNo);
                                    pt15.setText(p1);
                                    pt25.setText(p2);
                                    break;
                                case 6:
                                    su6.setText(cNo);
                                    pt16.setText(p1);
                                    pt26.setText(p2);
                                    break;
                            }
                        }

                        validate();
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(panRallyCurrent, "Failed to save Rally");
                    }
                } else {
                    JOptionPane.showMessageDialog(panRallyCurrent, "Rally cannot be blank");
                }
                break;

            case "SAVE":
                if (!panRallyCurrent.panListRow.get(0).txtSkill.getText().equals("")) {
                    RallyEvaluation rallyInsert = new RallyEvaluation();
                    rallyInsert.setRallyNum(panRallyCurrent.rallyNum);
                    rallyInsert.setHomeScore(homeScore);
                    rallyInsert.setOpponentScore(opponentScore);
                    rallyInsert.setStartTime(panRallyCurrent.startTime);
                    rallyInsert.setEndTime(panRallyCurrent.endTime);
                    rallyInsert.setMatchEvaluationId(matchEvaluationId);
                    rallyInsert.setRallyPositionMap(rallyPositionMap);

                    for (int i = 0; i < panRallyCurrent.panListRow.size(); i++) {
                        try {
                            PanRallyEvaluationRow panRallyEvaluationRow = panRallyCurrent.panListRow.get(i);
                            if (!panRallyEvaluationRow.txtSkill.getText().isEmpty()) {
                                RallyEvaluationSkillScore rs = new RallyEvaluationSkillScore();
                                rs.setSkill(panRallyEvaluationRow.txtSkill.getText());

                                if (panRallyEvaluationRow.txtSkill.getText().equals(Skill.OP.getType())) {
                                    op++;
                                    lblOp.setText("" + op);
                                    rs.setChestNo("0");
                                    rs.setPlayerId(0);
                                } else if (panRallyEvaluationRow.txtSkill.getText().equals(Skill.TF.getType())) {
                                    tf++;
                                    lblTf.setText("" + tf);
                                    rs.setChestNo("0");
                                    rs.setPlayerId(0);
                                } else {
                                    rs.setChestNo(String.valueOf(panRallyEvaluationRow.txtChestNum.getText()));
                                    rs.setPlayerId(panRallyEvaluationRow.playerId);
                                }
                                rallyInsert.setOp(op);
                                rallyInsert.setTf(tf);
                                rs.setSkillId(Skill.getIdByName(panRallyEvaluationRow.txtSkill.getText()).getId());
                                rs.setOrderNum(i + 1);
                                rs.setScore(Integer.parseInt(String.valueOf(panRallyEvaluationRow.cmbScore.getSelectedItem())));

                                if (panRallyEvaluationRow.isDetailed) {
                                    rs.setIsDetailed(true);
                                    rs.setDetailsValues(panRallyEvaluationRow.getDetailsValues());
                                }

                                rallyInsert.getRallyEvaluationSkillScore().add(rs);
                            }
                        } catch (Exception ex) {
                            Logger.getLogger(PanRallyLiveEvaluation.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    PanRallyEvaluationRow panRallyEvaluationRowStart = panRallyCurrent.panListRow.get(0);
                    PanRallyEvaluationRow panRallyEvaluationRowEnd = panRallyCurrent.panListRow.get(panRallyCurrent.panListRow.size() - 1);
                    if (panRallyEvaluationRowStart.txtSkill.getText().equals(Skill.Service.getType())) {
                        rallyInsert.setStartby(HomeOpponent.HOME.getId());
                    } else {
                        rallyInsert.setStartby(HomeOpponent.OPPONENT.getId());
                    }

                    if (Integer.parseInt(String.valueOf(panRallyEvaluationRowEnd.cmbScore.getSelectedItem())) == 5) {
                        rallyInsert.setWonby(HomeOpponent.HOME.getId());
                    } else {
                        rallyInsert.setWonby(HomeOpponent.OPPONENT.getId());
                    }
                    int idInserted = rallyDao.insertRally(rallyInsert);
                    if (idInserted != 0) {
                        totalRallies++;
                        currentRally++;
                        PanRallyBut pnBut = new PanRallyBut();
                        pnBut.setRally(currentRally, matchEvaluationId, initialPositionMap, evaluationType);
                        panRallyList.add(pnBut);
                        validate();
                        repaint();

                        if (homeScore >= 25 || opponentScore >= 25) {
                            List<Integer> arr = new ArrayList();
                            arr.add(homeScore);
                            arr.add(opponentScore);
                            int max = Collections.max(arr);
                            int min = Collections.min(arr);
                            if ((max - min) >= 2) {

                                showRallyList();

                                if (max == homeScore) {
                                    lblWonBy.setText(lblevaluationName.getText());
                                    matchDao.updateMatchSetWonBy(teamEvaluateId, matchEvaluationId);
                                } else {
                                    lblWonBy.setText(lblopponentName.getText());
                                    matchDao.updateMatchSetWonBy(opponentId, matchEvaluationId);
                                }
                                setScore();
                                JOptionPane.showMessageDialog(Controller.panMatchSet, "Set Won By : " + lblWonBy.getText() + "\n Score is := " + homeScore + " : " + opponentScore);

                                List<Integer> wonBy = matchDao.getSetNadWonBy(matchId);

                                int team1WonBy = Collections.frequency(wonBy, teamEvaluateId);
                                int team2wonBy = Collections.frequency(wonBy, opponentId);

                                if (team1WonBy == 0 && team2wonBy >= 3) {
                                    matchDao.updateMatchWonBy(opponentId, matchId);
                                } else if (team2wonBy == 0 && team1WonBy >= 3) {
                                    matchDao.updateMatchWonBy(teamEvaluateId, matchId);
                                } else {
                                    if (team2wonBy != 0 && team1WonBy != 0) {
                                        if (team2wonBy >= 3) {
                                            matchDao.updateMatchWonBy(opponentId, matchId);
                                        } else if (team1WonBy >= 3) {
                                            matchDao.updateMatchWonBy(teamEvaluateId, matchId);
                                        }
                                    }
                                }

                                Controller.panMatchSet.validate();
                                Controller.panMatchSet.repaint();
                            } else {
                                setScore();
                                showRallyList();
                            }
                        } else {
                            setScore();
                            showRallyList();
                        }
                    } else {
                        JOptionPane.showMessageDialog(panRallyCurrent, "Failed to save Rally");
                    }
                } else {
                    JOptionPane.showMessageDialog(panRallyCurrent, "Please Insert Skill");
                }
                break;

        }
    }//GEN-LAST:event_butNextMouseClicked

    public void showRallyList() {
        panRallyShow.removeAll();
        if (homeScore >= 25 || opponentScore >= 25) {
            List<Integer> arr = new ArrayList();
            arr.add(homeScore);
            arr.add(opponentScore);
            int max = Collections.max(arr);
            int min = Collections.min(arr);
            if ((max - min) >= 2) {
                butNext.setText("EXIT");
            } else {
                butNext.setText("NEW");
            }
        } else {
            butNext.setText("NEW");
        }
        lblRallyHeading.setText("RALLY");
        panRallyShow.add(panRallyList);
        this.validate();
        this.repaint();
    }

    private void lblEndMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void but1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but1MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su1.getText().equals("")) {
            Player p = ChestMap.get(ro1.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 1, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 1, matchEvaluationId);
            rallyPositionMap.put(1, initialPositionMap.get(1));
            Controller.panMatchSet.pt21.setText(score);
        }
    }//GEN-LAST:event_but1MouseClicked

    private void but2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but2MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su2.getText().equals("")) {
            Player p = ChestMap.get(ro2.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 2, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 2, matchEvaluationId);
            rallyPositionMap.put(2, initialPositionMap.get(2));
            Controller.panMatchSet.pt22.setText(score);
        }
    }//GEN-LAST:event_but2MouseClicked

    private void but3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but3MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su3.getText().equals("")) {
            Player p = ChestMap.get(ro3.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 3, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 3, matchEvaluationId);
            rallyPositionMap.put(3, initialPositionMap.get(3));
            Controller.panMatchSet.pt23.setText(score);
        }
    }//GEN-LAST:event_but3MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su4.getText().equals("")) {
            Player p = ChestMap.get(ro5.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 4, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 4, matchEvaluationId);
            rallyPositionMap.put(4, initialPositionMap.get(4));
            Controller.panMatchSet.pt24.setText(score);
        }

    }//GEN-LAST:event_jLabel28MouseClicked

    private void but5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but5MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su5.getText().equals("")) {
            Player p = ChestMap.get(ro5.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 5, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 5, matchEvaluationId);
            rallyPositionMap.put(5, initialPositionMap.get(5));
            Controller.panMatchSet.pt25.setText(score);
        }
    }//GEN-LAST:event_but5MouseClicked

    private void but6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_but6MouseClicked
        // TODO add your handling code here:
        String score = homeScore + " - " + opponentScore;
        if (su6.getText().equals("")) {
            Player p = ChestMap.get(ro6.getText());
            SetSubstituteSelectPlayerDialog obj = new SetSubstituteSelectPlayerDialog();
            obj.setData(playerList, 6, p.getId(), matchEvaluationId, score);
            obj.init();
            obj.show();
        } else {
            matchDao.updateSubstitutionPoint2(score, 6, matchEvaluationId);
            rallyPositionMap.put(6, initialPositionMap.get(6));
            Controller.panMatchSet.pt26.setText(score);
        }
    }//GEN-LAST:event_but6MouseClicked

    private void lblTimeOutEvalTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimeOutEvalTeamMouseClicked
        // TODO add your handling code here:
        setTimeout(lblTimeOutEvalTeam.getText());
    }//GEN-LAST:event_lblTimeOutEvalTeamMouseClicked

    private void lblTimeOutOppTeamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTimeOutOppTeamMouseClicked
        // TODO add your handling code here:
        setTimeout(lblTimeOutOppTeam.getText());
    }//GEN-LAST:event_lblTimeOutOppTeamMouseClicked

    private void tm6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tm6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tm6ActionPerformed

    private void lblNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNextMouseClicked
        // TODO add your handling code here:
        rallyNumNext++;
        if (rallyNumNext <= totalRallies) {
            Controller.panMatchSet.panRallyCurrent = new PanRallyLiveEvaluation(rallyNumNext, matchEvaluationId, rallyPositionMap, evaluationType);
            Controller.panMatchSet.panNext.setVisible(true);
            Controller.panMatchSet.panRallyShow.removeAll();
            Controller.panMatchSet.panRallyShow.add(Controller.panMatchSet.panRallyCurrent);
            Controller.panMatchSet.setBackNextVisible();

//        if (rallyNumNext == totalRallies) {
//            lblNext.setVisible(false);
//            lblBack.setVisible(true);
//        } else if (rallyNumNext == 1) {
//            lblNext.setVisible(true);
//            lblBack.setVisible(false);
//        }
            Controller.panMatchSet.validate();
            Controller.panMatchSet.repaint();
        } else {
            rallyNumNext--;
            JOptionPane.showMessageDialog(panRallyCurrent, "End Of the Rally");
        }

    }//GEN-LAST:event_lblNextMouseClicked

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        // TODO add your handling code here:
        rallyNumNext--;
        if (rallyNumNext > 0) {
            Controller.panMatchSet.panRallyCurrent = new PanRallyLiveEvaluation(rallyNumNext, matchEvaluationId, rallyPositionMap, evaluationType);
            Controller.panMatchSet.panNext.setVisible(true);
            Controller.panMatchSet.panRallyShow.removeAll();
            Controller.panMatchSet.panRallyShow.add(Controller.panMatchSet.panRallyCurrent);
            Controller.panMatchSet.setBackNextVisible();
//            if (rallyNumNext == totalRallies) {
//                lblNext.setVisible(false);
//                lblBack.setVisible(true);
//            } else if (rallyNumNext == 1) {
//                lblNext.setVisible(true);
//                lblBack.setVisible(false);
//            }

            Controller.panMatchSet.validate();
            Controller.panMatchSet.repaint();
        } else {
            rallyNumNext++;
            JOptionPane.showMessageDialog(panRallyCurrent, "Start Of the Rally");
        }
    }//GEN-LAST:event_lblBackMouseClicked

    public void setTimeout(String teamName) {
        int pos = matchDao.getTimeOutCount(matchEvaluationId);
        pos++;

        SetTimeout st = new SetTimeout();
        st.setPosition(pos);
        st.setTeam(teamName);
        st.setScoreA(homeScore);
        st.setScoreB(opponentScore);
        st.setMatchEvalId(matchEvaluationId);

        int count = matchDao.insertTimeout(st);
        setTimeOut(st);

    }

    public void setTimeOut(SetTimeout st) {
        String tm = st.getTeam();
        int scoreA = st.getScoreA();
        int scoreB = st.getScoreB();
        switch (st.getPosition()) {
            case 1:
                tm1.setText(tm);
                ScoreA1.setText("" + scoreA);
                ScoreB1.setText("" + scoreB);
                break;
            case 2:
                tm2.setText(tm);
                ScoreA2.setText("" + scoreA);
                ScoreB2.setText("" + scoreB);
                break;
            case 3:
                tm3.setText(tm);
                ScoreA3.setText("" + scoreA);
                ScoreB3.setText("" + scoreB);
                break;
            case 4:
                tm4.setText(tm);
                ScoreA4.setText("" + scoreA);
                ScoreB4.setText("" + scoreB);
                break;
            case 5:
                tm5.setText(tm);
                ScoreA5.setText("" + scoreA);
                ScoreB5.setText("" + scoreB);
                break;
            case 6:
                tm6.setText(tm);
                ScoreA6.setText("" + scoreA);
                ScoreB6.setText("" + scoreB);
                break;
        }
    }

    public void setRotaionOrders() {
        pos1.setText(initialPositionMap.get(1).getChestNo());
        pos2.setText(initialPositionMap.get(2).getChestNo());
        pos3.setText(initialPositionMap.get(3).getChestNo());
        pos4.setText(initialPositionMap.get(4).getChestNo());
        pos5.setText(initialPositionMap.get(5).getChestNo());
        pos6.setText(initialPositionMap.get(6).getChestNo());
    }

    public void setCombo(java.awt.event.FocusEvent evt) {
        JComboBox cmb = (JComboBox) evt.getSource();
        cmb.removeAllItems();
        cmb.addItem("");
        for (Player p : playerList) {
            boolean exist = false;
            for (Map.Entry<Integer, Player> entry : initialPositionMap.entrySet()) {
                Player pl = entry.getValue();
                if (p.getId() == pl.getId()) {
                    exist = true;
                }
            }
            if (!exist) {
                cmb.addItem(p.getChestNo());
            }
        }
    }

    public void newRally() {
//        setScore();
        int rallynum = currentRally + 1;
        panRallyShow.removeAll();
        validate();
        repaint();
        RallyEvaluation re = matchDao.getLatestRallyDetails(matchEvaluationId);

        if (re.getStartby() != HomeOpponent.HOME.getId() && re.getWonby() == HomeOpponent.HOME.getId()) {
            Player temp = rallyPositionMap.get(1);
            rallyPositionMap.put(1, rallyPositionMap.get(2));
            rallyPositionMap.put(2, rallyPositionMap.get(3));
            rallyPositionMap.put(3, rallyPositionMap.get(4));
            rallyPositionMap.put(4, rallyPositionMap.get(5));
            rallyPositionMap.put(5, rallyPositionMap.get(6));
            rallyPositionMap.put(6, temp);
        }

        CreateRallyRotationDialog obj = new CreateRallyRotationDialog();
        obj.init(obj);
        obj.show();

        panRallyCurrent = new PanRallyLiveEvaluation(rallynum, matchEvaluationId, rallyPositionMap, evaluationType);
        panRallyShow.add(panRallyCurrent);

        butNext.setText("SAVE");

    }

    public void setRotations() {
        pos1.setText(initialPositionMap.get(1).getChestNo());
        pos2.setText(initialPositionMap.get(2).getChestNo());
        pos3.setText(initialPositionMap.get(3).getChestNo());
        pos4.setText(initialPositionMap.get(4).getChestNo());
        pos5.setText(initialPositionMap.get(5).getChestNo());
        pos6.setText(initialPositionMap.get(6).getChestNo());
        libero.setText(initialPositionMap.get(7).getChestNo());

        ro1.setText(initialPositionMap.get(1).getChestNo());
        ro2.setText(initialPositionMap.get(2).getChestNo());
        ro3.setText(initialPositionMap.get(3).getChestNo());
        ro4.setText(initialPositionMap.get(4).getChestNo());
        ro5.setText(initialPositionMap.get(5).getChestNo());
        ro6.setText(initialPositionMap.get(6).getChestNo());

    }

    public void initializePlayer() {
        panPlayer.setBackground(Color.BLACK);
        registerLibrary();
        final Canvas videoSurface = new Canvas();
        final List<String> vlcArgs = new ArrayList<String>();
        configureParameters(vlcArgs);
        final EmbeddedMediaPlayer mediaPlayer = createPlayer(vlcArgs, videoSurface);
        panPlayer.add(videoSurface, BorderLayout.CENTER);
//        mediaPlayer.playMedia("E:\\A Happy Child - CBSE Poem Class -1.mp4");
        PlayerControlsPanel p1 = new PlayerControlsPanel(mediaPlayer);
        panPlayer.add(p1, BorderLayout.SOUTH);
    }

    private void registerLibrary() {
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "E:\\VLC\\VLC64");
        Native
                .loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class
                );
        LibXUtil.initialise();
    }

    /**
     * * Configure VLC parameters
     */
    private void configureParameters(final List<String> vlcArgs) {
        vlcArgs.add("--no-plugins-cache");
        vlcArgs.add("--no-video-title-show");
        vlcArgs.add("--no-snapshot-preview"); // Important, if this parameter would not be set on Windows, the app won't work
        if (RuntimeUtil.isWindows()) {
            vlcArgs.add("--plugin-path=D:\\vlc-2.2.1\\plugins");
        }
    }

    private EmbeddedMediaPlayer createPlayer(final List<String> vlcArgs, final Canvas videoSurface) {
        EmbeddedMediaPlayerComponent mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        EmbeddedMediaPlayer embeddedMediaPlayer = mediaPlayerComponent.getMediaPlayer();
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(vlcArgs.toArray(new String[vlcArgs.size()]));
        mediaPlayerFactory.setUserAgent("vlcj test player");
        embeddedMediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(videoSurface));
        embeddedMediaPlayer.setPlaySubItems(true);
        return embeddedMediaPlayer;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ScoreA1;
    private javax.swing.JTextField ScoreA2;
    private javax.swing.JTextField ScoreA3;
    private javax.swing.JTextField ScoreA4;
    private javax.swing.JTextField ScoreA5;
    private javax.swing.JTextField ScoreA6;
    private javax.swing.JTextField ScoreB1;
    private javax.swing.JTextField ScoreB2;
    private javax.swing.JTextField ScoreB3;
    private javax.swing.JTextField ScoreB4;
    private javax.swing.JTextField ScoreB5;
    private javax.swing.JTextField ScoreB6;
    private javax.swing.JLabel but1;
    private javax.swing.JLabel but2;
    private javax.swing.JLabel but3;
    private javax.swing.JPanel but4;
    private javax.swing.JLabel but5;
    private javax.swing.JLabel but6;
    protected javax.swing.JLabel butNext;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField43;
    private javax.swing.JTextField jTextField44;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JLabel lblBack;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblNext;
    private javax.swing.JTextField lblOp;
    public static javax.swing.JLabel lblRallyHeading;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblSetRotationOrder;
    private javax.swing.JTextField lblTf;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTimeOutEvalTeam;
    private javax.swing.JLabel lblTimeOutOppTeam;
    private javax.swing.JLabel lblWonBy;
    private javax.swing.JLabel lblevaluationName;
    private javax.swing.JLabel lblopponentName;
    private javax.swing.JLabel lblrotateleft;
    private javax.swing.JLabel lblrotateright;
    private javax.swing.JTextField libero;
    public javax.swing.JPanel panButton;
    public javax.swing.JPanel panNext;
    private javax.swing.JPanel panPlayer;
    public javax.swing.JPanel panRallyList;
    public javax.swing.JPanel panRallyShow;
    public javax.swing.JPanel panShowRallyInput;
    private javax.swing.JTextField pos1;
    private javax.swing.JTextField pos2;
    private javax.swing.JTextField pos3;
    private javax.swing.JTextField pos4;
    private javax.swing.JTextField pos5;
    private javax.swing.JTextField pos6;
    public javax.swing.JTextField pt11;
    public javax.swing.JTextField pt12;
    public javax.swing.JTextField pt13;
    public javax.swing.JTextField pt14;
    public javax.swing.JTextField pt15;
    public javax.swing.JTextField pt16;
    private javax.swing.JTextField pt21;
    private javax.swing.JTextField pt22;
    private javax.swing.JTextField pt23;
    private javax.swing.JTextField pt24;
    private javax.swing.JTextField pt25;
    private javax.swing.JTextField pt26;
    private javax.swing.JTextField ro1;
    private javax.swing.JTextField ro2;
    private javax.swing.JTextField ro3;
    private javax.swing.JTextField ro4;
    private javax.swing.JTextField ro5;
    private javax.swing.JTextField ro6;
    public javax.swing.JTextField su1;
    public javax.swing.JTextField su2;
    public javax.swing.JTextField su3;
    public javax.swing.JTextField su4;
    public javax.swing.JTextField su5;
    public javax.swing.JTextField su6;
    private javax.swing.JTextField tm1;
    private javax.swing.JTextField tm2;
    private javax.swing.JTextField tm3;
    private javax.swing.JTextField tm4;
    private javax.swing.JTextField tm5;
    private javax.swing.JTextField tm6;
    private javax.swing.JTextField txtEvaluator;
    // End of variables declaration//GEN-END:variables
}
