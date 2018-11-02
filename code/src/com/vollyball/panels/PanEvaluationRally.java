package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.bean.Team;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.enums.HomeOpponent;
import com.vollyball.enums.Skill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationRally extends javax.swing.JPanel {

    RallyDao rallyDao = new RallyDao();
    PanEvaluationRowDetail panEvaluationRowDetail;
    GridBagConstraints gbc;
    int i = 0;
    public PanCompListValue panCompListValue = new PanCompListValue();
    int k;
    LinkedHashMap<PanEvaluationRallyRowText, PanEvaluationRowDetail> panRallyRow = new LinkedHashMap<>();
    PanEvaluationRallyRowText currentPanRow;
    int teamEvaluateId;
    int opponentId;
    RallyEvaluation rallyEvaluation = new RallyEvaluation();
    String startTime, endTime;
    int rallyNum;

    List<JTextField> rallyPos = new ArrayList<>();
    MatchDao matchDao = new MatchDao();
    public List<PanEvaluationRallyRowText> panListRow = new ArrayList<>();
    int id;
    int oldRallyEndScore = 0;
    int oldSkillid = 0;
    int homeScore, opponentScore;
    boolean isInserted = false;

    public LinkedHashMap<Integer, Player> rallyPositionMap = new LinkedHashMap<Integer, Player>();
    public LinkedHashMap<Integer, Player> rallyPositionMapOpp = new LinkedHashMap<Integer, Player>();
    TeamDao teamDao = new TeamDao();
    boolean isManuallyStartBy = false;

    /**
     * Creates new form PanEvaluationRallyRow
     */
    public PanEvaluationRally(int teamEvaluateId, int opponentId, int rallyNum) {
        initComponents();

        rallyPositionMap = rallyDao.getLatestMatchSetRotationOrder(Controller.panMatchSet.matchEvaluationId);
        rallyPositionMapOpp = rallyDao.getLatestMatchSetRotationOrderOpp(Controller.panMatchSet.matchEvaluationId);

        if (rallyPositionMap.isEmpty()) {
            rallyPositionMap.putAll(Controller.panMatchSet.initialPositionMap);
        }
        if (rallyPositionMapOpp.isEmpty()) {
            rallyPositionMapOpp.putAll(Controller.panMatchSet.initialPositionMapOpp);
        }
//        rallyPos.add(rallyPos1);
//        rallyPos.add(rallyPos2);
//        rallyPos.add(rallyPos3);
//        rallyPos.add(rallyPos4);
//        rallyPos.add(rallyPos5);
//        rallyPos.add(rallyPos6);
        this.rallyNum = rallyNum;
        this.teamEvaluateId = teamEvaluateId;
        this.opponentId = opponentId;

        Team t = teamDao.getteamDetail(teamEvaluateId);
        lblHomePlus.setText(t.getShortCode());
        lblHomeStartBy.setText(t.getShortCode());
        t = teamDao.getteamDetail(opponentId);
        lblOppPlus.setText(t.getShortCode());
        lblOppStartBy.setText(t.getShortCode());
        panSave.setVisible(false);

        setRotationForRally();
        panRallyList.add(panCompListValue, BorderLayout.CENTER);
        panCompListValue.add(true);
        lblRallyNum.setText("" + rallyNum);
        lblAction.setText("SAVE");

    }

    public void addToPosition(boolean first) {
        panCompListValue.addToPosition(first);
    }

    public void removePosition() {
        panCompListValue.removeRow();
        
        
    }

    public PanEvaluationRally(RallyEvaluation rallyEvaluation) {
        initComponents();
        lblAction.setText("UPDATE");
        isInserted = true;
        this.rallyEvaluation = rallyEvaluation;
        this.rallyNum = rallyEvaluation.getRallyNum();
        this.id = rallyEvaluation.getId();
        homeScore = rallyEvaluation.getHomeScore();
        opponentScore = rallyEvaluation.getOpponentScore();
        lblRallyNum.setText("" + rallyEvaluation.getRallyNum());
        lblRallyStartTime.setText("" + rallyEvaluation.getStartTime());
        lblRallyEndTime.setText("" + rallyEvaluation.getEndTime());
        lblResult.setText(rallyEvaluation.getHomeScore() + " : " + rallyEvaluation.getOpponentScore());
        panRallyList.add(panCompListValue, BorderLayout.CENTER);

        oldRallyEndScore = rallyEvaluation.getRallyEvaluationSkillScore().get(rallyEvaluation.getRallyEvaluationSkillScore().size() - 1).getScore();
        oldSkillid = rallyEvaluation.getRallyEvaluationSkillScore().get(rallyEvaluation.getRallyEvaluationSkillScore().size() - 1).getSkillId();
//        if (rallyEvaluation.getRallyPositionMap().size() > 0) {
//            rallyPos1.setText(rallyEvaluation.getRallyPositionMap().get(1).getChestNo());
//            rallyPos2.setText(rallyEvaluation.getRallyPositionMap().get(2).getChestNo());
//            rallyPos3.setText(rallyEvaluation.getRallyPositionMap().get(3).getChestNo());
//            rallyPos4.setText(rallyEvaluation.getRallyPositionMap().get(4).getChestNo());
//            rallyPos5.setText(rallyEvaluation.getRallyPositionMap().get(5).getChestNo());
//            rallyPos6.setText(rallyEvaluation.getRallyPositionMap().get(6).getChestNo());
//        }

        this.rallyPositionMap.putAll(rallyEvaluation.getRallyPositionMap());
        this.rallyPositionMapOpp.putAll(rallyEvaluation.getRallyPositionMapOpp());
        panCompListValue.addRallies(rallyEvaluation.getRallyEvaluationSkillScore());

    }

    public void setRotationForRally() {
        LinkedHashMap<Integer, Player> latestPositionMap = rallyDao.getLatestMatchSetRotationOrder(Controller.panMatchSet.matchEvaluationId);
        LinkedHashMap<Integer, Player> latestPositionMapOpp = rallyDao.getLatestMatchSetRotationOrderOpp(Controller.panMatchSet.matchEvaluationId);

        RallyEvaluation re = matchDao.getLatestRallyDetails(Controller.panMatchSet.matchEvaluationId);

        if (re.getStartby() == HomeOpponent.HOME.getId() && re.getWonby() == HomeOpponent.OPPONENT.getId()) {

            Player temp = latestPositionMapOpp.get(1);
            latestPositionMapOpp.put(1, latestPositionMapOpp.get(2));
            latestPositionMapOpp.put(2, latestPositionMapOpp.get(3));
            latestPositionMapOpp.put(3, latestPositionMapOpp.get(4));
            Player playerP = null;
            int i = 0;
            if (latestPositionMapOpp.get(5).getChestNo().equals(Controller.panMatchSet.initialPositionMapOpp.get(7).getChestNo())) {
                for (Map.Entry<Integer, Player> entrySub : Controller.panMatchSet.initialPositionMapOpp.entrySet()) {
                    boolean found = false;
                    Integer integer = entrySub.getKey();
                    Player player = entrySub.getValue();
                    if (integer != 7) {
                        for (Map.Entry<Integer, Player> entryRally : latestPositionMapOpp.entrySet()) {
                            if (player.getChestNo().equals(entryRally.getValue().getChestNo())) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            playerP = player;
                        }
                    }
                    i++;
                }
                if (playerP != null) {
                    latestPositionMapOpp.put(4, playerP);
                }
            } else {
                latestPositionMapOpp.put(4, latestPositionMap.get(5));
            }
            latestPositionMapOpp.put(4, latestPositionMapOpp.get(5));
            latestPositionMapOpp.put(5, latestPositionMapOpp.get(6));
            latestPositionMapOpp.put(6, temp);

        } else if (re.getStartby() == HomeOpponent.OPPONENT.getId() && re.getWonby() == HomeOpponent.HOME.getId()) {
            Player temp = latestPositionMap.get(1);
            latestPositionMap.put(1, latestPositionMap.get(2));
            latestPositionMap.put(2, latestPositionMap.get(3));
            latestPositionMap.put(3, latestPositionMap.get(4));
            Player playerP = null;
            int i = 0;
            if (latestPositionMap.get(5).getChestNo().equals(Controller.panMatchSet.initialPositionMap.get(7).getChestNo())) {
                for (Map.Entry<Integer, Player> entrySub : Controller.panMatchSet.substituePositionMap.entrySet()) {
                    boolean found = false;
                    Integer integer = entrySub.getKey();
                    Player player = entrySub.getValue();
                    if (integer != 7) {
                        for (Map.Entry<Integer, Player> entryRally : latestPositionMap.entrySet()) {
                            if (player.getChestNo().equals(entryRally.getValue().getChestNo())) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            playerP = player;
                        }
                    }
                    i++;
                }
                if (playerP != null) {
                    latestPositionMap.put(4, playerP);
                }
            } else {
                latestPositionMap.put(4, latestPositionMap.get(5));
            }
            latestPositionMap.put(5, latestPositionMap.get(6));
            latestPositionMap.put(6, temp);
        }

//        int i = 0;
//        for (Map.Entry<Integer, Player> entry : latestPositionMap.entrySet()) {
//            rallyPos.get(i).setText("" + entry.getValue().getChestNo());
//            if (Controller.panMatchSet.initialPositionMap.get(7).getChestNo().equals(entry.getValue().getChestNo())) {
//                rallyPos.get(i).setForeground(Color.red);
//            } else {
//                rallyPos.get(i).setForeground(Color.BLACK);
//            }
//            i++;
//        }
        rallyPositionMap.putAll(latestPositionMap);
        rallyPositionMapOpp.putAll(latestPositionMapOpp);
        this.rallyPositionMap.putAll(latestPositionMap);
        this.rallyPositionMapOpp.putAll(latestPositionMapOpp);
    }

    public void update() {
        List<Integer> updated = new ArrayList<>();
        RallyEvaluation rallyUpdate = new RallyEvaluation();
        rallyUpdate.setId(id);
        rallyUpdate.setRallyNum(rallyNum);
//                rallyUpdate.setHomeScore();
        rallyUpdate.setMatchEvaluationId(rallyEvaluation.getMatchEvaluationId());
        rallyUpdate.setRallyPositionMap(rallyEvaluation.getRallyPositionMap());
        int i = 0;
        boolean performUpdate = true;
        if (panListRow.size() > 0) {

            for (PanEvaluationRallyRowText panRowText : panListRow) {

                PanEvaluationRowDetail pan = panRallyRow.get(panRowText);
                RallyEvaluationSkillScore rallyEvaluationSkillScore = pan.getRallyEvaluationSkillScore();
                try {

                    RallyEvaluationSkillScore rs = rallyEvaluationSkillScore;
                    updated.add(rallyEvaluationSkillScore.getId());
                    rs.setId(rallyEvaluationSkillScore.getId());
                    rs.setSkill(rallyEvaluationSkillScore.getSkill());

//                    if (rallyEvaluationSkillScore.getSkill().equals(Skill.OP.getType())) {
//                        Controller.panMatchSet.op++;
//                        Controller.panMatchSet.lblOp.setText("" + Controller.panMatchSet.op);
//
//                    } else if (rallyEvaluationSkillScore.getSkill().equals(Skill.TF.getType())) {
//                        Controller.panMatchSet.tf++;
//                        Controller.panMatchSet.lblTf.setText("" + Controller.panMatchSet.tf);
//
//                    }
                    rs.setChestNo(rallyEvaluationSkillScore.getChestNo());
                    rs.setPlayerId(rallyEvaluationSkillScore.getPlayerId());
                    rallyUpdate.setOp(Controller.panMatchSet.op);
                    rallyUpdate.setTf(Controller.panMatchSet.tf);
                    rs.setSkillId(rallyEvaluationSkillScore.getSkillId());
                    rs.setOrderNum(i + 1);
                    i++;
                    rs.setScore(rallyEvaluationSkillScore.getScore());

                    if (rallyEvaluationSkillScore.getDetailsValues().size() > 0) {
                        rs.setIsDetailed(true);
                        rs.setDetailsValues(rallyEvaluationSkillScore.getDetailsValues());
                    }
                    rallyUpdate.getRallyEvaluationSkillScore().add(rs);
                } catch (Exception ex) {
                    Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                }

            }
            RallyEvaluationSkillScore panRallyEvaluationRowStart = panRallyRow.get(panListRow.get(0)).getRallyEvaluationSkillScore();
            RallyEvaluationSkillScore panRallyEvaluationRowEnd = panRallyRow.get(panListRow.get(panListRow.size() - 1)).getRallyEvaluationSkillScore();

            if (panRallyEvaluationRowStart.getSkill().equals(Skill.Service.getType())) {
                rallyUpdate.setStartby(HomeOpponent.HOME.getId());
            } else {
                rallyUpdate.setStartby(HomeOpponent.OPPONENT.getId());
            }

            if (Integer.parseInt(String.valueOf(panRallyEvaluationRowEnd.getScore())) == 5) {
                rallyUpdate.setWonby(HomeOpponent.HOME.getId());
            } else {
                rallyUpdate.setWonby(HomeOpponent.OPPONENT.getId());
            }

            int updatedScore = panRallyRow.get(panListRow.get(panListRow.size() - 1)).getRallyEvaluationSkillScore().getScore();
            String updatedSkill = panRallyRow.get(panListRow.get(panListRow.size() - 1)).getRallyEvaluationSkillScore().getSkill();

            int updatedSkillid = panRallyRow.get(panListRow.get(panListRow.size() - 1)).getRallyEvaluationSkillScore().getSkillId();
            if (oldSkillid == Skill.OP.getId() || oldSkillid == Skill.TF.getId()) {
                if (oldSkillid == Skill.OP.getId() && updatedSkillid != Skill.OP.getId()) {
                    if (updatedSkillid == Skill.TF.getId()) {
                        Controller.panMatchSet.tf++;
                    }
                    Controller.panMatchSet.op--;
                }

                if (oldSkillid == Skill.TF.getId() && updatedSkillid != Skill.TF.getId()) {
                    if (updatedSkillid == Skill.OP.getId()) {
                        Controller.panMatchSet.op++;
                    }
                    Controller.panMatchSet.tf--;
                }
            } else {
                if (updatedSkillid == Skill.TF.getId()) {
                    Controller.panMatchSet.tf++;
                }
                if (updatedSkillid == Skill.OP.getId()) {
                    Controller.panMatchSet.op++;
                }
            }

            if (oldRallyEndScore != updatedScore) {

                switch (updatedScore) {

                    case 1:

                        if (homeScore != 0) {
                            homeScore--;
                        }
                        opponentScore++;
                        rallyUpdate.setScoreSubtracted("Home");

                        break;
                    case 5:
                        if (updatedSkill.equals(Skill.Service.getType()) || updatedSkill.equals(Skill.Attack.getType()) || updatedSkill.equals(Skill.Block.getType()) || updatedSkill.equals(Skill.OP.getType())) {
                            homeScore++;
                            if (opponentScore != 0) {
                                opponentScore--;
                            }
                            rallyUpdate.setScoreSubtracted("Opponent");
                        } else {
                            performUpdate = false;
                            JOptionPane.showMessageDialog(this, "Rally Not End");
                        }
                        break;
                    default:
                        performUpdate = false;
                        JOptionPane.showMessageDialog(this, "Rally Not End");
                        break;
                }
                rallyUpdate.setHomeScore(this.homeScore);
                rallyUpdate.setOpponentScore(this.opponentScore);
                rallyUpdate.setOp(Controller.panMatchSet.op);
                rallyUpdate.setTf(Controller.panMatchSet.tf);

//                    JOptionPane.showMessageDialog(panRallyCurrent, "New score of rally : \nHome Score = " + panRallyCurrent.homeScore + "\nOpp Score =" + panRallyCurrent.opponentScore);
            } else {
//                    JOptionPane.showMessageDialog(panRallyCurrent, "Same Score");
                rallyUpdate.setHomeScore(homeScore);
                rallyUpdate.setOpponentScore(opponentScore);
                rallyUpdate.setScoreSubtracted("None");
                rallyUpdate.setOp(Controller.panMatchSet.op);
                rallyUpdate.setTf(Controller.panMatchSet.tf);
            }
            int id = 0;
            if (performUpdate) {
                id = rallyDao.updateRally(rallyUpdate, updated);
                if (id != 0) {
                    Controller.panMatchSet.setScoreAfterUpdate();
                    JOptionPane.showMessageDialog(this, "Updated Successfully");

                } else {
                    JOptionPane.showMessageDialog(this, "Failed to save Rally");
                }
            }

        }
    }

    public void save() {
        boolean insert = false;
        RallyEvaluation rallyInsert = new RallyEvaluation();
        rallyInsert.setRallyNum(rallyNum);
        rallyInsert.setHomeScore(Controller.panMatchSet.homeScore);
        rallyInsert.setOpponentScore(Controller.panMatchSet.opponentScore);
        rallyInsert.setStartTime(startTime);
        rallyInsert.setEndTime(endTime);
        rallyInsert.setMatchEvaluationId(Controller.panMatchSet.matchEvaluationId);
        rallyInsert.setRallyPositionMap(rallyPositionMap);
        rallyInsert.setRallyPositionMapOpp(rallyPositionMapOpp);
        for (PanEvaluationRallyRowText panRowText : panListRow) {

            PanEvaluationRowDetail pan = panRallyRow.get(panRowText);
            RallyEvaluationSkillScore rallyEvaluationSkillScore = pan.getRallyEvaluationSkillScore();
            
                         
            
            try {
                RallyEvaluationSkillScore rs = rallyEvaluationSkillScore;
                
                rs.setSkill(rallyEvaluationSkillScore.getSkill());
                if (rallyEvaluationSkillScore.getSkill().equals(Skill.OP.getType())) {
                    Controller.panMatchSet.op++;
                    Controller.panMatchSet.lblOp.setText("" + Controller.panMatchSet.op);
                } else if (rallyEvaluationSkillScore.getSkill().equals(Skill.TF.getType())) {
                    Controller.panMatchSet.tf++;
                    Controller.panMatchSet.lblTf.setText("" + Controller.panMatchSet.tf);
                }
                rs.setChestNo(rallyEvaluationSkillScore.getChestNo());
                rs.setPlayerId(rallyEvaluationSkillScore.getPlayerId());
                rallyInsert.setOp(Controller.panMatchSet.op);
                rallyInsert.setTf(Controller.panMatchSet.tf);
                rs.setSkillId(rallyEvaluationSkillScore.getSkillId());
                rs.setOrderNum(i + 1);
                rs.setScore(rallyEvaluationSkillScore.getScore());

                if (rallyEvaluationSkillScore.getDetailsValues().size() > 0) {
                    rs.setIsDetailed(true);
                    rs.setDetailsValues(rallyEvaluationSkillScore.getDetailsValues());
                }
                rallyInsert.getRallyEvaluationSkillScore().add(rs);

            } catch (Exception ex) {
                Logger.getLogger(this
                        .getName()).log(Level.SEVERE, null, ex);
            }
            insert= true;
            }
        
        RallyEvaluationSkillScore panRallyEvaluationRowStart = rallyEvaluation.getRallyEvaluationSkillScore().get(0);
        RallyEvaluationSkillScore panRallyEvaluationRowEnd = rallyEvaluation.getRallyEvaluationSkillScore().get(rallyEvaluation.getRallyEvaluationSkillScore().size() - 1);

        if (panRallyEvaluationRowStart.getSkill().equals(Skill.Service.getType())) {
            rallyInsert.setStartby(HomeOpponent.HOME.getId());
        } else {
            rallyInsert.setStartby(HomeOpponent.OPPONENT.getId());
        }

        if (Integer.parseInt(String.valueOf(panRallyEvaluationRowEnd.getScore())) == 5) {
            rallyInsert.setWonby(HomeOpponent.HOME.getId());
        } else {
            rallyInsert.setWonby(HomeOpponent.OPPONENT.getId());
        }
        int idInserted = rallyDao.insertRally(rallyInsert);
        if (idInserted != 0) {
            Controller.panMatchSet.next();
            Controller.panMatchSet.currentRally++;
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save Rally");

        }

    }

    public class PanCompListValue extends JPanel {

        private JPanel mainList;
        JScrollPane s;

        public PanCompListValue() {
            k = 0;
            setLayout(new BorderLayout());
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            JPanel p = new JPanel();
            p.setBackground(new Color(57, 74, 108));
            mainList.add(p, gbc);
            mainList.setBackground(new Color(57, 74, 108));
            s = new JScrollPane(mainList);
            s.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            s.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
            s.getVerticalScrollBar().setBackground(Color.red);
            s.setBackground(Color.WHITE);
            s.setBorder(null);
            s.setSize(110, 500);
            add(s, BorderLayout.CENTER);
        }

        public void removeRow() {
            int i = 0;
            for (PanEvaluationRallyRowText p : panListRow) {
                if (p.isAddClicked) {
                    p.isAddClicked = false;
                    panListRow.remove(i);
                    panRallyRow.remove(p);
                    k--;
                    break;
                }
                i++;
            }
            if(panListRow.size()==0)
            {
               panStartBy.setVisible(true);
            }else{
                panStartBy.setVisible(false);
            }
            
            mainList.remove(i);
            validate();
            repaint();
        }

        public void addToPosition(boolean first) {
            int atRow = 0;
            
            if(first)
            {
                panStartBy.setVisible(true);
            }else{
                panStartBy.setVisible(false);
            }
            PanEvaluationRallyRowText panel = new PanEvaluationRallyRowText(PanEvaluationRally.this);
            currentPanRow = panel;
            panEvalDetail.removeAll();
            PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(PanEvaluationRally.this, first);
            panRallyRow.put(panel, panEvaluationRowDetail);
            panEvalDetail.add(panEvaluationRowDetail, BorderLayout.CENTER);
            panEvalDetail.validate();
            panEvalDetail.repaint();
            GridBagConstraints gbcRow = new GridBagConstraints();
            gbcRow.gridwidth = GridBagConstraints.REMAINDER;
            gbcRow.weightx = 1;
            gbcRow.gridheight = 2;
            gbcRow.fill = GridBagConstraints.HORIZONTAL;
            int i = 0;
            if (!first) {
                for (PanEvaluationRallyRowText p : panListRow) {
                    if (p.isAddClicked) {
                        atRow = i + 1;
                        p.isAddClicked = false;
                        panListRow.add(atRow, panel);
                        break;
                    }
                    i++;
                }
            } else {
                panListRow.add(atRow, panel);
            }
            mainList.add(panel, gbcRow, atRow);
            validate();
            repaint();
            JScrollBar vertical = s.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
            k++;
        }

        public void add(boolean isFirst) {

            PanEvaluationRallyRowText panel = new PanEvaluationRallyRowText(PanEvaluationRally.this);
            currentPanRow = panel;
            panEvalDetail.removeAll();
            PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(PanEvaluationRally.this, isFirst);
            if(isFirst)
            {
                panStartBy.setVisible(true);
            }else{
                panStartBy.setVisible(false);
            }
            panEvalDetail.add(panEvaluationRowDetail, BorderLayout.CENTER);
            panEvalDetail.validate();
            panEvalDetail.repaint();
            GridBagConstraints gbcRow = new GridBagConstraints();
            gbcRow.gridwidth = GridBagConstraints.REMAINDER;
            gbcRow.weightx = 1;
            gbcRow.gridheight = 2;
            gbcRow.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbcRow, k);
            validate();
            repaint();
            JScrollBar vertical = s.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
            panRallyRow.put(panel, panEvaluationRowDetail);
            k++;

            for (Map.Entry<PanEvaluationRallyRowText, PanEvaluationRowDetail> entry : panRallyRow.entrySet()) {
                PanEvaluationRallyRowText jPanel = entry.getKey();
                if (jPanel != panel) {
                    jPanel.panShow.setBackground(new Color(57, 74, 108));
                    jPanel.txtPlayer.setForeground(Color.WHITE);
                    jPanel.txtSkill.setForeground(Color.WHITE);
                    jPanel.txtRate.setForeground(Color.WHITE);

                }
            }
            panListRow.add(panel);

        }

        public void addRallies(List<RallyEvaluationSkillScore> rallyEvaluationSkillScore) {

            for (RallyEvaluationSkillScore rallyEvaluationSkillScore1 : rallyEvaluationSkillScore) {
                panStartBy.setVisible(false);
                panWonBy.setVisible(false);
                
                
                PanEvaluationRallyRowText panel = new PanEvaluationRallyRowText(PanEvaluationRally.this);
                currentPanRow = panel;
                panEvalDetail.removeAll();
                PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(PanEvaluationRally.this, false);
                panEvaluationRowDetail.setValues(rallyEvaluationSkillScore1);
                panEvalDetail.add(panEvaluationRowDetail, BorderLayout.CENTER);
                panEvalDetail.validate();
                panEvalDetail.repaint();
                GridBagConstraints gbcRow = new GridBagConstraints();
                gbcRow.gridwidth = GridBagConstraints.REMAINDER;
                gbcRow.weightx = 1;
                gbcRow.gridheight = 2;
                gbcRow.fill = GridBagConstraints.HORIZONTAL;
                mainList.add(panel, gbcRow, k);
                validate();
                repaint();
                JScrollBar vertical = s.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());
                panRallyRow.put(panel, panEvaluationRowDetail);
                k++;
                panListRow.add(panel);
                for (Map.Entry<PanEvaluationRallyRowText, PanEvaluationRowDetail> entry : panRallyRow.entrySet()) {
                    PanEvaluationRallyRowText jPanel = entry.getKey();
                    if (jPanel != panel) {
                        jPanel.panShow.setBackground(new Color(57, 74, 108));
                        jPanel.txtPlayer.setForeground(Color.WHITE);
                        jPanel.txtSkill.setForeground(Color.WHITE);
                        jPanel.txtRate.setForeground(Color.WHITE);

                    }
                }

            }

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

        panRallyList = new javax.swing.JPanel();
        panEvalDetail = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        lblRallyStartTime = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        lblRallyEndTime = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        lblResult = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        lblRallyNum = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        addToFirst = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panSave = new javax.swing.JPanel();
        lblAction = new javax.swing.JLabel();
        panWonBy = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblHomePlus = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblOppPlus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panStartBy = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblHomeStartBy = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lblOppStartBy = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        panRallyList.setBackground(new java.awt.Color(57, 74, 108));
        panRallyList.setLayout(new java.awt.BorderLayout());

        panEvalDetail.setBackground(new java.awt.Color(255, 255, 255));
        panEvalDetail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 5));
        panEvalDetail.setLayout(new java.awt.BorderLayout());

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setText("Start time : ");

        lblRallyStartTime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyStartTime.setText("00:00:00");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel23.setText("End time : ");

        lblRallyEndTime.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyEndTime.setText("00:00:00");

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Result : ");

        lblResult.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblResult.setText("00 : 00");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Rally : ");

        lblRallyNum.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblRallyNum.setText("0");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRallyNum, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRallyStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRallyEndTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRallyStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRallyEndTime, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRallyNum, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        jPanel2.setBackground(new java.awt.Color(57, 74, 108));

        addToFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vollyball/images/icons8-plus-20.png"))); // NOI18N
        addToFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToFirstMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ACTIONS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addToFirst)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(addToFirst, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panSave.setBackground(new java.awt.Color(0, 102, 102));

        lblAction.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAction.setForeground(new java.awt.Color(255, 255, 255));
        lblAction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAction.setText("SAVE");
        lblAction.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblAction.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblActionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSaveLayout = new javax.swing.GroupLayout(panSave);
        panSave.setLayout(panSaveLayout);
        panSaveLayout.setHorizontalGroup(
            panSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSaveLayout.setVerticalGroup(
            panSaveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        panWonBy.setBackground(new java.awt.Color(102, 102, 102));
        panWonBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel4.setBackground(new java.awt.Color(0, 102, 204));

        lblHomePlus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHomePlus.setForeground(new java.awt.Color(255, 255, 255));
        lblHomePlus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHomePlus.setText("jLabel1");
        lblHomePlus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblHomePlus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHomePlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomePlusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHomePlus, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblHomePlus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(204, 0, 51));

        lblOppPlus.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblOppPlus.setForeground(new java.awt.Color(255, 255, 255));
        lblOppPlus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOppPlus.setText("jLabel2");
        lblOppPlus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblOppPlus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOppPlus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOppPlusMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOppPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOppPlus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("End By :");

        javax.swing.GroupLayout panWonByLayout = new javax.swing.GroupLayout(panWonBy);
        panWonBy.setLayout(panWonByLayout);
        panWonByLayout.setHorizontalGroup(
            panWonByLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panWonByLayout.createSequentialGroup()
                .addGroup(panWonByLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panWonByLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panWonByLayout.setVerticalGroup(
            panWonByLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panWonByLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panWonByLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panStartBy.setBackground(new java.awt.Color(102, 102, 102));
        panStartBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel6.setBackground(new java.awt.Color(0, 102, 204));

        lblHomeStartBy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHomeStartBy.setForeground(new java.awt.Color(255, 255, 255));
        lblHomeStartBy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHomeStartBy.setText("jLabel1");
        lblHomeStartBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblHomeStartBy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHomeStartBy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeStartByMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblHomeStartBy, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblHomeStartBy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel7.setBackground(new java.awt.Color(204, 0, 51));

        lblOppStartBy.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblOppStartBy.setForeground(new java.awt.Color(255, 255, 255));
        lblOppStartBy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOppStartBy.setText("jLabel2");
        lblOppStartBy.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblOppStartBy.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOppStartBy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOppStartByMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOppStartBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOppStartBy, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Service By :");

        javax.swing.GroupLayout panStartByLayout = new javax.swing.GroupLayout(panStartBy);
        panStartBy.setLayout(panStartByLayout);
        panStartByLayout.setHorizontalGroup(
            panStartByLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panStartByLayout.createSequentialGroup()
                .addGroup(panStartByLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panStartByLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panStartByLayout.setVerticalGroup(
            panStartByLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panStartByLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panStartByLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panEvalDetail, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(panStartBy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panRallyList, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(panWonBy, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panEvalDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panRallyList, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(panStartBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panWonBy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addToFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToFirstMouseClicked
        // TODO add your handling code here:

        addToPosition(true);
    }//GEN-LAST:event_addToFirstMouseClicked

    private void lblActionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActionMouseClicked
        // TODO add your handling code here:

        if (lblAction.getText().equals("SAVE")) {
            save();
        } else {
            update();
        }

    }//GEN-LAST:event_lblActionMouseClicked

    private void lblHomePlusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomePlusMouseClicked
        // TODO add your handling code here:
        Controller.panMatchSet.homeScore++;
        
        
        setEndData("Home");
        
        save();
    }//GEN-LAST:event_lblHomePlusMouseClicked

    private void lblOppPlusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOppPlusMouseClicked
        // TODO add your handling code here:
        Controller.panMatchSet.opponentScore++;
         setEndData("opp");
        save();
    }//GEN-LAST:event_lblOppPlusMouseClicked

    private void lblHomeStartByMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeStartByMouseClicked
        // TODO add your handling code here:
         setStartData("Home");
    }//GEN-LAST:event_lblHomeStartByMouseClicked

    private void lblOppStartByMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOppStartByMouseClicked
        // TODO add your handling code here:
         setStartData("opp");
    }//GEN-LAST:event_lblOppStartByMouseClicked

    public void setStartData(String type)
    {
        PanEvaluationRallyRowText panel = new PanEvaluationRallyRowText(PanEvaluationRally.this);
         PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(PanEvaluationRally.this, true);
            panRallyRow.put(panel, panEvaluationRowDetail);
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
        int score = type.equalsIgnoreCase("Home")?5:1;
        
        if(panListRow.size()==0)
        {
            panListRow.add(panel);
            
        }
        isManuallyStartBy = true;
        
        PanEvaluationRowDetail pan = panRallyRow.get(panListRow.get(0));
        RallyEvaluationSkillScore rallyEvaluationSkillScore = pan.getRallyEvaluationSkillScore();
        rallyEvaluationSkillScore.setSkill(type.equals("Home")?Skill.Service.getType():Skill.Reception.getType());
        rallyEvaluationSkillScore.setSkillId(type.equals("Home")?Skill.Service.getId():Skill.Reception.getId());
        rallyEvaluationSkillScore.setScore(0);
        rallyEvaluationSkillScore.setChestNo("0");
        rallyEvaluationSkillScore.setPlayerId(0);
        Date time = new Date();
                    startTime = formatterTime.format(time);
                    lblRallyStartTime.setText(startTime);
                    rallyEvaluation.getRallyEvaluationSkillScore().add(rallyEvaluationSkillScore);
          
    }
    
    
    public void setEndData(String type)
    {
        PanEvaluationRallyRowText panel = new PanEvaluationRallyRowText(PanEvaluationRally.this);
         PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(PanEvaluationRally.this, true);
            panRallyRow.put(panel, panEvaluationRowDetail);
        SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
        int score = type.equalsIgnoreCase("Home")?5:1;
        
        if(isManuallyStartBy)
        {
           panListRow.add(panel);
        }
        
        PanEvaluationRowDetail pan = panRallyRow.get(panListRow.get(panListRow.size()-1));
        RallyEvaluationSkillScore rallyEvaluationSkillScore = pan.getRallyEvaluationSkillScore();
        rallyEvaluationSkillScore.setSkill(Skill.NE.getType());
        rallyEvaluationSkillScore.setSkillId(Skill.NE.getId());
        rallyEvaluationSkillScore.setScore(score);
        rallyEvaluationSkillScore.setChestNo("0");
        rallyEvaluationSkillScore.setPlayerId(0);
        Date time = new Date();
                    endTime = formatterTime.format(time);
                    lblRallyEndTime.setText(endTime);
                    rallyEvaluation.getRallyEvaluationSkillScore().add(rallyEvaluationSkillScore);
          
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addToFirst;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JLabel lblAction;
    private javax.swing.JLabel lblHomePlus;
    private javax.swing.JLabel lblHomeStartBy;
    private javax.swing.JLabel lblOppPlus;
    private javax.swing.JLabel lblOppStartBy;
    public javax.swing.JLabel lblRallyEndTime;
    private javax.swing.JLabel lblRallyNum;
    public javax.swing.JLabel lblRallyStartTime;
    public javax.swing.JLabel lblResult;
    public javax.swing.JPanel panEvalDetail;
    public javax.swing.JPanel panRallyList;
    private javax.swing.JPanel panSave;
    private javax.swing.JPanel panStartBy;
    private javax.swing.JPanel panWonBy;
    // End of variables declaration//GEN-END:variables

    public int getTeamEvaluateId() {
        return teamEvaluateId;
    }

    public void setTeamEvaluateId(int teamEvaluateId) {
        this.teamEvaluateId = teamEvaluateId;
    }

    public int getOpponentId() {
        return opponentId;
    }

    public void setOpponentId(int opponentId) {
        this.opponentId = opponentId;
    }

}
