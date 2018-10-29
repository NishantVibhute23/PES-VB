package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.enums.HomeOpponent;
import com.vollyball.enums.Skill;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
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
            mainList.remove(i);
            validate();
            repaint();
        }

        public void addToPosition(boolean first) {
            int atRow = 0;
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
        jPanel3 = new javax.swing.JPanel();
        lblAction = new javax.swing.JLabel();

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

        jPanel3.setBackground(new java.awt.Color(0, 102, 102));

        lblAction.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblAction.setForeground(new java.awt.Color(255, 255, 255));
        lblAction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAction.setText("SAVE");
        lblAction.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblActionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAction, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
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
                            .addComponent(panRallyList, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
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
                        .addComponent(panRallyList, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addToFirst;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAction;
    public javax.swing.JLabel lblRallyEndTime;
    private javax.swing.JLabel lblRallyNum;
    public javax.swing.JLabel lblRallyStartTime;
    public javax.swing.JLabel lblResult;
    public javax.swing.JPanel panEvalDetail;
    public javax.swing.JPanel panRallyList;
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
