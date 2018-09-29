package com.vollyball.panels;

import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.bean.Settings;
import com.vollyball.controller.Controller;
import com.vollyball.dao.SettingDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dialog.DialogReplaceLibero;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillDescCriteriaPoint;
import com.vollyball.enums.SkillsDescCriteria;
import com.vollyball.enums.TempoEnum;
import com.vollyball.util.CommonUtil;
import com.vollyball.util.DetailUtils;
import com.vollyball.util.ShortKeysUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.HierarchyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public class PanEvaluationRowDetail extends javax.swing.JPanel {

    LinkedHashMap<JLabel, JPanel> mapSkillComponent = new LinkedHashMap<>();
    LinkedHashMap<JLabel, JPanel> mapPlayerComponent = new LinkedHashMap<>();
    LinkedHashMap<JLabel, JPanel> mapScoreComponent = new LinkedHashMap<>();
    LinkedHashMap<String, JLabel> mapSkillLabel = new LinkedHashMap<>();
    LinkedHashMap<String, JLabel> mapPlayerLabel = new LinkedHashMap<>();
    LinkedHashMap<Integer, JLabel> mapScoreLabel = new LinkedHashMap<>();
    ImagePanel panel;
    TeamDao teamDao = new TeamDao();
    String skill = "";
    String chestNo = "";
    int score = 0;
    JTextField txtRallyRow = new JTextField();
//    public LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<>();
//    public LinkedHashMap<String, Player> ChestMap = new LinkedHashMap<>();
    public DialogReplaceLibero dialogReplaceLibero;

//    List<JLabel> playerLabelList = new ArrayList<>();
    List<Integer> pixel = new ArrayList<>();
    PanEvaluationRally p;
    boolean isNew = true;
    boolean isLast = true;
    boolean isSelected = false;
    boolean isFirst = true;
    SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
    String command = "";
    List<JLabel> playerLabelList;
    String fromPlace, toPlace;
    List<String> skillKeys;
    List<String> scoreKeys;
    List<String> diagramKeys;
    List<String> detailsShortcutKeys;
    List<String> diagramPoints = new ArrayList<>();
    LinkedHashMap<PanSkillDescCriteria, List<String>> panPoints = new LinkedHashMap<>();
    LinkedHashMap<Integer, PanSkillDescCriteria> skillDescIdPanMap = new LinkedHashMap<>();
    LinkedHashMap<String, String> pointsShortcut = new LinkedHashMap<>();
    RallyEvaluationSkillScore rallyEvaluationSkillScore = new RallyEvaluationSkillScore();

    /**
     * Creates new form PanEvaluationRowDetail
     */
    public PanEvaluationRowDetail(final PanEvaluationRally p, boolean isFirst) {
        initComponents();
        this.p = p;
        this.isFirst = isFirst;
        mapSkillComponent.put(lblService, panService);
        mapSkillComponent.put(lblAttack, panAttack);
        mapSkillComponent.put(lblBlock, panBlock);
        mapSkillComponent.put(lblSet, panSet);
        mapSkillComponent.put(lblReception, panReception);
        mapSkillComponent.put(lblDefence, panDefence);
        mapSkillComponent.put(lblOp, panOp);
        mapSkillComponent.put(lblTF, panTF);
        mapSkillLabel.put(Skill.Service.getType(), lblService);
        mapSkillLabel.put(Skill.Attack.getType(), lblAttack);
        mapSkillLabel.put(Skill.Block.getType(), lblBlock);
        mapSkillLabel.put(Skill.Set.getType(), lblSet);
        mapSkillLabel.put(Skill.Reception.getType(), lblReception);
        mapSkillLabel.put(Skill.Defence.getType(), lblDefence);
        mapSkillLabel.put(Skill.OP.getType(), lblOp);
        mapSkillLabel.put(Skill.TF.getType(), lblTF);

        mapPlayerComponent.put(lbl1, pan1);
        mapPlayerComponent.put(lbl2, pan2);
        mapPlayerComponent.put(lbl3, pan3);
        mapPlayerComponent.put(lbl4, pan4);
        mapPlayerComponent.put(lbl5, pan5);
        mapPlayerComponent.put(lbl6, pan6);
        mapPlayerComponent.put(lbl7, pan7);
        mapPlayerComponent.put(lbl8, pan8);
        mapPlayerComponent.put(lbl9, pan9);
        mapPlayerComponent.put(lbl10, pan10);
        mapPlayerComponent.put(lbl11, pan11);
        mapPlayerComponent.put(lbl12, pan12);
        mapPlayerComponent.put(lbl13, pan13);
        mapPlayerComponent.put(lbl14, pan14);
        mapPlayerComponent.put(lbl15, pan15);
        if (isFirst) {
            panRLHome.setVisible(true);
            panRLOpp.setVisible(true);
        } else {
            panRLHome.setVisible(false);
            panRLOpp.setVisible(false);
        }

        mapScoreComponent.put(lblRate1, panRate1);
        mapScoreComponent.put(lblRate2, panRate2);
        mapScoreComponent.put(lblRate3, panRate3);
        mapScoreComponent.put(lblRate4, panRate4);
        mapScoreComponent.put(lblRate5, panRate5);
        mapScoreLabel.put(1, lblRate1);
        mapScoreLabel.put(2, lblRate2);
        mapScoreLabel.put(3, lblRate3);
        mapScoreLabel.put(4, lblRate4);
        mapScoreLabel.put(5, lblRate5);

        skillKeys = ShortKeysUtil.skillKeys();
        scoreKeys = ShortKeysUtil.scoreKeys();
        diagramKeys = ShortKeysUtil.diagramKeys();

        panel = new ImagePanel(new ImageIcon("src\\com\\vollyball\\images\\panVollyCourtNewGrid.png").getImage(), p);
        panCourt.add(panel, BorderLayout.CENTER);

        playerLabelList = new ArrayList<JLabel>(mapPlayerComponent.keySet());
        for (JLabel label : playerLabelList) {
            label.setVisible(false);

        }

        int i = 0;
        for (Player pl : Controller.panMatchSet.playerList) {
            mapPlayerLabel.put(pl.getChestNo(), playerLabelList.get(i));
            playerLabelList.get(i).setText(pl.getChestNo());
            playerLabelList.get(i).setVisible(true);
            i++;
        }
        panCourt.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                pixel.add(x);
                pixel.add(y);

                if (pixel.size() > 2) {
                    panel.drawImage(pixel.get(0), pixel.get(1), pixel.get(2), pixel.get(3));
                    panCourt.removeAll();
                    panCourt.add(panel, BorderLayout.CENTER);
                    panCourt.validate();
                    panCourt.repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        for (Map.Entry<JLabel, JPanel> entry : mapPlayerComponent.entrySet()) {
            entry.getKey().addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    selectPlayer((JLabel) e.getSource());
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });
        }

        for (Map.Entry<JLabel, JPanel> entry : mapScoreComponent.entrySet()) {
            entry.getKey().addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    selectScore((JLabel) e.getSource());

                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }

            });
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                txtInput.requestFocus();
            }
        });
//        txtInput.requestFocus();
    }

    public void save() {
        if (isFirst && isNew) {
            isFirst = false;
            Date time = new Date();
            p.startTime = formatterTime.format(time);
            p.lblRallyStartTime.setText(p.startTime);
        }

        if (skill.equalsIgnoreCase("op+") || skill.equalsIgnoreCase("tf-")) {
            if (skill.equalsIgnoreCase("tf-")) {
                mapScoreComponent.get(lblRate1).setBackground(new Color(255, 11, 0));
                score = 1;
            } else {
                mapScoreComponent.get(lblRate5).setBackground(new Color(255, 11, 0));
                score = 5;
            }
            rallyEvaluationSkillScore.setSkill(skill);
            rallyEvaluationSkillScore.setSkillId(Skill.getIdByName(skill).getId());
            rallyEvaluationSkillScore.setScore(score);
            rallyEvaluationSkillScore.setChestNo("0");
            rallyEvaluationSkillScore.setPlayerId(0);
            rallyEvaluationSkillScore.setCode(txtInput.getText());
//            if (diagramPoints.size() > 0) {
//                String points = "";
//                for (String point : diagramPoints) {
//                    points = points + "-" + point;
//                }
//
//                int id = getDigramId(skill);
//
////                rallyEvaluationSkillScore.getDetailsValues().put(id, points);
//            }

            isSelected = true;

        } else if (!skill.equals("") && !chestNo.equals("") && score != 0) {
            rallyEvaluationSkillScore.setSkill(skill);
            rallyEvaluationSkillScore.setSkillId(Skill.getIdByName(skill).getId());
            rallyEvaluationSkillScore.setScore(score);
            rallyEvaluationSkillScore.setChestNo(chestNo);
            rallyEvaluationSkillScore.setPlayerId(Controller.panMatchSet.ChestMap.get(chestNo).getId());
            rallyEvaluationSkillScore.setCode(txtInput.getText());
            if (diagramPoints.size() > 0) {
                String points = "";
                for (String point : diagramPoints) {
                    points = points + point + "-";
                }
                int id = getDigramId(skill);
                rallyEvaluationSkillScore.getDetailsValues().put(id, points);

                for (Map.Entry<Integer, PanSkillDescCriteria> entry : skillDescIdPanMap.entrySet()) {
                    int skillDescCriteriaId = entry.getKey();
                    PanSkillDescCriteria panel = entry.getValue();
                    if (skillDescCriteriaId != id) {
                        rallyEvaluationSkillScore.getDetailsValues().put(skillDescCriteriaId, panel.lblOption.getText());
                    }
                }

            }
            isSelected = true;
        }

        if (isSelected) {
            p.rallyEvaluation.getRallyEvaluationSkillScore().add(rallyEvaluationSkillScore);
            switch (score) {
                case 1:
                    if (!p.isInserted) {
                        Controller.panMatchSet.opponentScore++;
                        p.lblResult.setText(Controller.panMatchSet.homeScore + " : " + Controller.panMatchSet.opponentScore);
                    }
                    Date time = new Date();
                    p.endTime = formatterTime.format(time);
                    p.lblRallyEndTime.setText(p.endTime);
                    if (!p.isInserted) {
                        p.save();
                    }
                    break;
                case 5:
                    if (skill.equals(Skill.Service.getType()) || skill.equals(Skill.Attack.getType()) || skill.equals(Skill.Block.getType()) || skill.equals(Skill.OP.getType())) {
                        if (!p.isInserted) {
                            Controller.panMatchSet.homeScore++;
                            p.lblResult.setText(Controller.panMatchSet.homeScore + " : " + Controller.panMatchSet.opponentScore);
                        }
                        Date time1 = new Date();
                        p.endTime = formatterTime.format(time1);
                        p.lblRallyEndTime.setText(p.endTime);
                        if (!p.isInserted) {
                            p.save();
                        }
                    } else {
                        if (isNew || isLast) {
                            isLast = false;
                            if (!p.isInserted) {
                                p.panCompListValue.add(false);
                            }
                        }
                    }
                    break;
                default:
                    if (isNew || isLast) {
                        isLast = false;
                        if (!p.isInserted) {
                            p.panCompListValue.add(false);
                        }
                    }
                    break;
            }
        }
    }

    public int getDigramId(String skill) {
        int id = 0;
        if (skill.equals(Skill.Service.getType())) {
            id = SkillsDescCriteria.ServiceDig.getId();
        } else if (skill.equals(Skill.Attack.getType())) {
            id = SkillsDescCriteria.AttackDig.getId();
        } else if (skill.equals(Skill.Block.getType())) {
            id = SkillsDescCriteria.BlockDig.getId();
        } else if (skill.equals(Skill.Set.getType())) {
            id = SkillsDescCriteria.SetDig.getId();
        } else if (skill.equals(Skill.Defence.getType())) {
            id = SkillsDescCriteria.DefenceDig.getId();
        } else if (skill.equals(Skill.Reception.getType())) {
            id = SkillsDescCriteria.ReceptionDig.getId();
        }
        return id;

    }

    public String getShortcutById(int shortcutId) {
        SettingDao sd = new SettingDao();
        Settings set = sd.getCodeForId(shortcutId);
        return set.getCode();
    }

    public void setValues(RallyEvaluationSkillScore rallyEvaluationSkillScore1) {
        this.rallyEvaluationSkillScore = rallyEvaluationSkillScore1;
        skill = Skill.getNameById(rallyEvaluationSkillScore1.getSkillId()).getType();
        chestNo = rallyEvaluationSkillScore1.getPlayerId() == 0 ? "" : Controller.panMatchSet.playerMap.get(rallyEvaluationSkillScore1.getPlayerId()).getChestNo();
        p.currentPanRow.txtRate.setText("" + rallyEvaluationSkillScore1.getScore());
        p.currentPanRow.txtSkill.setText(skill);
        p.currentPanRow.txtPlayer.setText(chestNo);
        score = rallyEvaluationSkillScore1.getScore();
        txtInput.setText(rallyEvaluationSkillScore1.getCode());
        JLabel lblScore = mapScoreLabel.get(score);
        if (lblScore == lblRate1) {
            mapScoreComponent.get(lblScore).setBackground(new Color(255, 11, 0));
        } else if (lblScore == lblRate2) {
            mapScoreComponent.get(lblScore).setBackground(new Color(255, 144, 0));
        } else if (lblScore == lblRate3) {
            mapScoreComponent.get(lblScore).setBackground(new Color(255, 255, 0));
        } else if (lblScore == lblRate4) {
            mapScoreComponent.get(lblScore).setBackground(new Color(157, 242, 0));
        } else if (lblScore == lblRate5) {
            mapScoreComponent.get(lblScore).setBackground(new Color(3, 129, 0));
        }

        mapSkillComponent.get(mapSkillLabel.get(skill)).setBackground(Color.ORANGE);
        if (!chestNo.equals("")) {
            mapPlayerComponent.get(mapPlayerLabel.get(chestNo)).setBackground(Color.ORANGE);
        }

        if (rallyEvaluationSkillScore1.getDetailsValues().size() > 0) {
            int id = getDigramId(skill);
            String digPoints = rallyEvaluationSkillScore1.getDetailsValues().get(id);
            if (digPoints != null || !digPoints.isEmpty()) {
                String arr[] = digPoints.split("-");
                diagramPoints = new ArrayList<String>(Arrays.asList(arr));
                if (diagramPoints.size() > 2) {
                    if (skill.equals(Skill.Set.getType())) {
                        panel.dig(skill, diagramPoints, TempoEnum.getTempoByName(skillDescIdPanMap.get(43).lblOption.getText()));
                    } else {
                        panel.dig(skill, diagramPoints, 0);
                    }
                }
            }
        }
        if (!skill.equalsIgnoreCase(Skill.OP.getType()) && !skill.equalsIgnoreCase(Skill.TF.getType())) {
            viewComponents(skill);
            for (Map.Entry<Integer, String> entry : rallyEvaluationSkillScore.getDetailsValues().entrySet()) {
                skillDescIdPanMap.get(entry.getKey()).lblOption.setText(entry.getValue());
            }
        }
        panel.refresh();

    }

    public void hierarchyChanged(HierarchyEvent e) {
        JComponent component = (JComponent) e.getSource();

        if ((HierarchyEvent.SHOWING_CHANGED & e.getChangeFlags()) != 0
                && component.isShowing()) {
            // add code here

        }
    }

    public void selectScore(JLabel j) {
        JLabel lblScore = j;
        p.currentPanRow.txtRate.setText(lblScore.getText());
        score = Integer.parseInt(lblScore.getText());
        for (Map.Entry<JLabel, JPanel> entry : mapScoreComponent.entrySet()) {
            if (lblScore == entry.getKey()) {
                if (lblScore == lblRate1) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(255, 11, 0));
                } else if (lblScore == lblRate2) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(255, 144, 0));
                } else if (lblScore == lblRate3) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(255, 255, 0));
                } else if (lblScore == lblRate4) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(157, 242, 0));
                } else if (lblScore == lblRate5) {
                    mapScoreComponent.get(entry.getKey()).setBackground(new Color(3, 129, 0));
                }

            } else {
                mapScoreComponent.get(entry.getKey()).setBackground(Color.WHITE);
            }
        }
        save();
    }

    public void setInitialDetailValues(String skill) {

        if (skill.equals(Skill.Service.getType())) {
            skillDescIdPanMap.get(8).lblOption.setText(DetailUtils.getScore(Controller.panMatchSet.currentRally, Controller.panMatchSet.matchEvaluationId));
            skillDescIdPanMap.get(9).lblOption.setText(DetailUtils.getServeInSituation(Controller.panMatchSet.currentRally, Controller.panMatchSet.matchEvaluationId, Controller.panMatchSet.teamEvaluateId));
            skillDescIdPanMap.get(10).lblOption.setText(DetailUtils.getOpponentSetterPosition());
        } else if (skill.equals(Skill.Attack.getType())) {
            skillDescIdPanMap.get(23).lblOption.setText(DetailUtils.getScore(Controller.panMatchSet.currentRally, Controller.panMatchSet.matchEvaluationId));
            skillDescIdPanMap.get(24).lblOption.setText(DetailUtils.getHomeSetterPosition());
        } else if (skill.equals(Skill.Reception.getType())) {
            skillDescIdPanMap.get(63).lblOption.setText(DetailUtils.getScore(Controller.panMatchSet.currentRally, Controller.panMatchSet.matchEvaluationId));
            skillDescIdPanMap.get(64).lblOption.setText(DetailUtils.getHomeSetterPosition());
        } else if (skill.equals(Skill.Defence.getType())) {
            skillDescIdPanMap.get(80).lblOption.setText(DetailUtils.getScore(Controller.panMatchSet.currentRally, Controller.panMatchSet.matchEvaluationId));
            skillDescIdPanMap.get(78).lblOption.setText(DetailUtils.getHomeSetterPosition());
        } else if (skill.equals(Skill.Set.getType())) {
            skillDescIdPanMap.get(53).lblOption.setText(DetailUtils.getScore(Controller.panMatchSet.currentRally, Controller.panMatchSet.matchEvaluationId));
        } else if (skill.equals(Skill.Block.getType())) {
            skillDescIdPanMap.get(40).lblOption.setText(DetailUtils.getScore(Controller.panMatchSet.currentRally, Controller.panMatchSet.matchEvaluationId));
            skillDescIdPanMap.get(41).lblOption.setText(DetailUtils.getOpponentSetterPosition());
        }

    }

    public void setDetailValuesByDiagram(String skill, List<String> diagramPoints, int k) {

        String pan1 = diagramPoints.get(k);
        String pan2 = diagramPoints.get(k + 1);

        if (skill.equals(Skill.Service.getType())) {

            skillDescIdPanMap.get(2).lblOption.setText(DetailUtils.getServeTactics(pan1, pan2));
            skillDescIdPanMap.get(3).lblOption.setText(DetailUtils.getDirection(pan1, pan2));
            skillDescIdPanMap.get(4).lblOption.setText(DetailUtils.getFromZone(pan1));
            skillDescIdPanMap.get(5).lblOption.setText(DetailUtils.getToZone(pan2));
            skillDescIdPanMap.get(7).lblOption.setText(DetailUtils.getReceiverPosition(pan2));
        } else if (skill.equals(Skill.Attack.getType())) {

            skillDescIdPanMap.get(13).lblOption.setText(DetailUtils.getAttackingTactics(pan1, pan2));
            skillDescIdPanMap.get(25).lblOption.setText(DetailUtils.getDirection(pan1, pan2));
            skillDescIdPanMap.get(15).lblOption.setText(DetailUtils.getFromZone(pan1));
            skillDescIdPanMap.get(16).lblOption.setText(DetailUtils.getToZone(pan2));
            skillDescIdPanMap.get(21).lblOption.setText(DetailUtils.getToZone(pan2));
            skillDescIdPanMap.get(22).lblOption.setText(DetailUtils.getReceiverPosition(pan2));

        } else if (skill.equals(Skill.Reception.getType())) {

            skillDescIdPanMap.get(56).lblOption.setText(DetailUtils.getFromZone(pan2));
            skillDescIdPanMap.get(57).lblOption.setText(DetailUtils.getToZone(pan1));
        } else if (skill.equals(Skill.Defence.getType())) {

            skillDescIdPanMap.get(68).lblOption.setText(DetailUtils.getFromZone(pan1));
            skillDescIdPanMap.get(72).lblOption.setText(DetailUtils.getFromZone(pan1));
            skillDescIdPanMap.get(73).lblOption.setText(DetailUtils.getToZone(pan2));
            skillDescIdPanMap.get(74).lblOption.setText(DetailUtils.getReceiverPosition(pan2));
        } else if (skill.equals(Skill.Set.getType())) {

            skillDescIdPanMap.get(47).lblOption.setText(DetailUtils.getFromZone(pan1));
            skillDescIdPanMap.get(48).lblOption.setText(DetailUtils.getToZone(pan2));
            skillDescIdPanMap.get(52).lblOption.setText(DetailUtils.getReceiverPosition(pan2));

        } else if (skill.equals(Skill.Block.getType())) {

            if (diagramPoints.size() == 2) {
                skillDescIdPanMap.get(32).lblOption.setText(DetailUtils.getFromZone(pan1));
                skillDescIdPanMap.get(33).lblOption.setText(DetailUtils.getToZone(pan2));
                skillDescIdPanMap.get(36).lblOption.setText(DetailUtils.getReceiverPosition(pan2));
            } else {
                skillDescIdPanMap.get(32).lblOption.setText(DetailUtils.getFromZone(pan1));
                skillDescIdPanMap.get(33).lblOption.setText(DetailUtils.getToZone(pan2));
                skillDescIdPanMap.get(36).lblOption.setText(DetailUtils.getReceiverPosition(pan2));
                String pan3 = diagramPoints.get(k + 2);
                skillDescIdPanMap.get(34).lblOption.setText(DetailUtils.getToZone(pan3));
                skillDescIdPanMap.get(39).lblOption.setText(DetailUtils.getBlockDefendedCourt(pan3));
            }

        }

        if (diagramPoints.size() > 0) {
            String points = "";
            for (String point : diagramPoints) {
                points = points + "-" + point;
            }

            int id = getDigramId(skill);

            skillDescIdPanMap.get(id).lblOption.setText(points);
        }

    }

    public void selectSkill(JLabel j) {
        JLabel lblSkill = j;
        p.currentPanRow.txtSkill.setText(lblSkill.getText());
        skill = lblSkill.getText();
        if (!skill.equalsIgnoreCase(Skill.OP.getType()) && !skill.equalsIgnoreCase(Skill.TF.getType())) {
            viewComponents(skill);
            setInitialDetailValues(skill);
        }
        for (Map.Entry<JLabel, JPanel> entry : mapSkillComponent.entrySet()) {
            if (lblSkill == entry.getKey()) {
                mapSkillComponent.get(entry.getKey()).setBackground(Color.ORANGE);
            } else {
                mapSkillComponent.get(entry.getKey()).setBackground(Color.WHITE);
            }
        }
        save();
    }

    public void viewComponents(String skill) {
        panPoints = new LinkedHashMap<>();
        pointsShortcut = new LinkedHashMap<>();
        detailsShortcutKeys = new ArrayList<>();
        panDesc1.removeAll();
        panDesc2.removeAll();

        int skillId = Skill.getIdByName(skill).getId();
        List<SkillsDescCriteria> lst = SkillsDescCriteria.getSkillDescCriteriaBySkillandView(skillId);
        panDesc1.setLayout(new GridLayout(9, 1));
        panDesc2.setLayout(new GridLayout(9, 1));
        int i = 0;
        for (SkillsDescCriteria sdc : lst) {
            i++;
            List<String> pointList = new ArrayList<>();
            PanSkillDescCriteria pan = new PanSkillDescCriteria();
            pan.lblHeading.setText("<HTML>" + sdc.getType() + "</HTML>");
            if (sdc.getView() == 1) {
                pan.lblHeading.setForeground(Color.BLUE);
            }
            List<SkillDescCriteriaPoint> lstPoints = SkillDescCriteriaPoint.getTypeBySkillDescId(sdc.getId());
            pan.setId(sdc.getId());
            skillDescIdPanMap.put(sdc.getId(), pan);
            LinkedHashMap<String, Integer> pointsMap = new LinkedHashMap<>();
            for (SkillDescCriteriaPoint sdcp : lstPoints) {
                if (sdc.getView() == 1) {
                    //get shortcut code from database by passing shortcutId
                    String shortcut = getShortcutById(sdcp.getShortcutId());
                    //------//
                    detailsShortcutKeys.add(shortcut);
                    pointsShortcut.put(shortcut, sdcp.getAbbreviation());
                    pointsMap.put(sdcp.getAbbreviation(), sdcp.getId());
                    pointList.add(shortcut);
                }
            }
            if (i <= 9) {
                panDesc1.add(pan);
            } else if (i <= 18) {
                panDesc2.add(pan);
            }
            pan.setPointsMap(pointsMap);
            panPoints.put(pan, pointList);
        }

        for (int j = i; j < 18; j++) {
            PanSkillDescCriteria pan = new PanSkillDescCriteria();
            if (j <= 8) {
                panDesc1.add(pan);
            } else {
                panDesc2.add(pan);
            }
        }
        validate();
        repaint();

    }

    public void selectPlayer(JLabel j) {
        JLabel txtPlayer = j;
        p.currentPanRow.txtPlayer.setText(txtPlayer.getText());
        chestNo = txtPlayer.getText();
        diagramPoints.add(chestNo);
        String position = "";
        for (Map.Entry<JLabel, JPanel> entry : mapPlayerComponent.entrySet()) {

            if (txtPlayer == entry.getKey()) {

                mapPlayerComponent.get(entry.getKey()).setBackground(Color.ORANGE);
            } else {
                mapPlayerComponent.get(entry.getKey()).setBackground(Color.WHITE);
            }

        }
        if (skill.equals(Skill.Attack.getType())) {
            skillDescIdPanMap.get(18).lblOption.setText(DetailUtils.getPosition(Controller.panMatchSet.ChestMap.get(chestNo)));
        }
        save();
    }

    public boolean isIsNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
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
        panCourt1 = new javax.swing.JPanel();
        panCourt = new javax.swing.JPanel();
        panOppRL = new javax.swing.JPanel();
        panRLOpp = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panHomeRL = new javax.swing.JPanel();
        panRLHome = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        panRate1 = new javax.swing.JPanel();
        lblRate1 = new javax.swing.JLabel();
        panRate2 = new javax.swing.JPanel();
        lblRate2 = new javax.swing.JLabel();
        panRate3 = new javax.swing.JPanel();
        lblRate3 = new javax.swing.JLabel();
        panRate4 = new javax.swing.JPanel();
        lblRate4 = new javax.swing.JLabel();
        panRate5 = new javax.swing.JPanel();
        lblRate5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        panDefence = new javax.swing.JPanel();
        lblDefence = new javax.swing.JLabel();
        panService = new javax.swing.JPanel();
        lblService = new javax.swing.JLabel();
        panTF = new javax.swing.JPanel();
        lblTF = new javax.swing.JLabel();
        panOp = new javax.swing.JPanel();
        lblOp = new javax.swing.JLabel();
        panAttack = new javax.swing.JPanel();
        lblAttack = new javax.swing.JLabel();
        panBlock = new javax.swing.JPanel();
        lblBlock = new javax.swing.JLabel();
        panReception = new javax.swing.JPanel();
        lblReception = new javax.swing.JLabel();
        panSet = new javax.swing.JPanel();
        lblSet = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        pan1 = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        pan2 = new javax.swing.JPanel();
        lbl2 = new javax.swing.JLabel();
        pan3 = new javax.swing.JPanel();
        lbl3 = new javax.swing.JLabel();
        pan4 = new javax.swing.JPanel();
        lbl4 = new javax.swing.JLabel();
        pan5 = new javax.swing.JPanel();
        lbl5 = new javax.swing.JLabel();
        pan6 = new javax.swing.JPanel();
        lbl6 = new javax.swing.JLabel();
        pan9 = new javax.swing.JPanel();
        lbl9 = new javax.swing.JLabel();
        pan8 = new javax.swing.JPanel();
        lbl8 = new javax.swing.JLabel();
        pan7 = new javax.swing.JPanel();
        lbl7 = new javax.swing.JLabel();
        pan12 = new javax.swing.JPanel();
        lbl12 = new javax.swing.JLabel();
        pan11 = new javax.swing.JPanel();
        lbl11 = new javax.swing.JLabel();
        pan10 = new javax.swing.JPanel();
        lbl10 = new javax.swing.JLabel();
        pan15 = new javax.swing.JPanel();
        lbl15 = new javax.swing.JLabel();
        pan14 = new javax.swing.JPanel();
        lbl14 = new javax.swing.JLabel();
        pan13 = new javax.swing.JPanel();
        lbl13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        panDesc1 = new javax.swing.JPanel();
        panDesc2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtInput = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        butReset = new javax.swing.JLabel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        panCourt1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));

        panCourt.setLayout(new java.awt.BorderLayout());

        panOppRL.setBackground(new java.awt.Color(255, 255, 255));
        panOppRL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panRLOpp.setBackground(new java.awt.Color(57, 74, 108));
        panRLOpp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("<HTML><CENTER>L<BR>I<BR>B<BR>E<BR>R<BR>O<BR></CENTER></html>");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panRLOppLayout = new javax.swing.GroupLayout(panRLOpp);
        panRLOpp.setLayout(panRLOppLayout);
        panRLOppLayout.setHorizontalGroup(
            panRLOppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        panRLOppLayout.setVerticalGroup(
            panRLOppLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        );

        jLabel4.setText(" ");

        javax.swing.GroupLayout panOppRLLayout = new javax.swing.GroupLayout(panOppRL);
        panOppRL.setLayout(panOppRLLayout);
        panOppRLLayout.setHorizontalGroup(
            panOppRLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOppRLLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(panRLOpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panOppRLLayout.setVerticalGroup(
            panOppRLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panOppRLLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(panRLOpp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panHomeRL.setBackground(new java.awt.Color(255, 255, 255));
        panHomeRL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panRLHome.setBackground(new java.awt.Color(57, 74, 108));
        panRLHome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<HTML><CENTER>L<BR>I<BR>B<BR>E<BR>R<BR>O<BR></CENTER></html>");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panRLHomeLayout = new javax.swing.GroupLayout(panRLHome);
        panRLHome.setLayout(panRLHomeLayout);
        panRLHomeLayout.setHorizontalGroup(
            panRLHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        panRLHomeLayout.setVerticalGroup(
            panRLHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
        );

        jLabel5.setText(" ");

        javax.swing.GroupLayout panHomeRLLayout = new javax.swing.GroupLayout(panHomeRL);
        panHomeRL.setLayout(panHomeRLLayout);
        panHomeRLLayout.setHorizontalGroup(
            panHomeRLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panHomeRLLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(panRLHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panHomeRLLayout.setVerticalGroup(
            panHomeRLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panHomeRLLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panRLHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        javax.swing.GroupLayout panCourt1Layout = new javax.swing.GroupLayout(panCourt1);
        panCourt1.setLayout(panCourt1Layout);
        panCourt1Layout.setHorizontalGroup(
            panCourt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panCourt1Layout.createSequentialGroup()
                .addComponent(panCourt, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(panCourt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panOppRL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panHomeRL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
        panCourt1Layout.setVerticalGroup(
            panCourt1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panCourt, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(panCourt1Layout.createSequentialGroup()
                .addComponent(panOppRL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panHomeRL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panRate1.setBackground(new java.awt.Color(255, 255, 255));
        panRate1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate1.setText("1");
        lblRate1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate1Layout = new javax.swing.GroupLayout(panRate1);
        panRate1.setLayout(panRate1Layout);
        panRate1Layout.setHorizontalGroup(
            panRate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate1Layout.setVerticalGroup(
            panRate1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate1, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate2.setBackground(new java.awt.Color(255, 255, 255));
        panRate2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate2.setText("2");
        lblRate2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate2Layout = new javax.swing.GroupLayout(panRate2);
        panRate2.setLayout(panRate2Layout);
        panRate2Layout.setHorizontalGroup(
            panRate2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate2Layout.setVerticalGroup(
            panRate2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate3.setBackground(new java.awt.Color(255, 255, 255));
        panRate3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate3.setText("3");
        lblRate3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate3Layout = new javax.swing.GroupLayout(panRate3);
        panRate3.setLayout(panRate3Layout);
        panRate3Layout.setHorizontalGroup(
            panRate3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate3Layout.setVerticalGroup(
            panRate3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate4.setBackground(new java.awt.Color(255, 255, 255));
        panRate4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate4.setText("4");
        lblRate4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate4Layout = new javax.swing.GroupLayout(panRate4);
        panRate4.setLayout(panRate4Layout);
        panRate4Layout.setHorizontalGroup(
            panRate4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate4Layout.setVerticalGroup(
            panRate4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        panRate5.setBackground(new java.awt.Color(255, 255, 255));
        panRate5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblRate5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblRate5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRate5.setText("5");
        lblRate5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout panRate5Layout = new javax.swing.GroupLayout(panRate5);
        panRate5.setLayout(panRate5Layout);
        panRate5Layout.setHorizontalGroup(
            panRate5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );
        panRate5Layout.setVerticalGroup(
            panRate5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblRate5, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
        );

        jPanel12.setBackground(new java.awt.Color(57, 74, 108));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("RATE");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panRate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panRate5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panRate5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        panDefence.setBackground(new java.awt.Color(255, 255, 255));
        panDefence.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblDefence.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblDefence.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDefence.setText("Defence");
        lblDefence.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblDefence.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblDefenceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panDefenceLayout = new javax.swing.GroupLayout(panDefence);
        panDefence.setLayout(panDefenceLayout);
        panDefenceLayout.setHorizontalGroup(
            panDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panDefenceLayout.setVerticalGroup(
            panDefenceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDefence, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        panService.setBackground(new java.awt.Color(255, 255, 255));
        panService.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblService.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblService.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblService.setText("Service");
        lblService.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblService.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblServiceMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panServiceLayout = new javax.swing.GroupLayout(panService);
        panService.setLayout(panServiceLayout);
        panServiceLayout.setHorizontalGroup(
            panServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panServiceLayout.setVerticalGroup(
            panServiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblService, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        panTF.setBackground(new java.awt.Color(255, 255, 255));
        panTF.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblTF.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblTF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTF.setText("TF-");
        lblTF.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTFMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panTFLayout = new javax.swing.GroupLayout(panTF);
        panTF.setLayout(panTFLayout);
        panTFLayout.setHorizontalGroup(
            panTFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panTFLayout.setVerticalGroup(
            panTFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTF, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        panOp.setBackground(new java.awt.Color(255, 255, 255));
        panOp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblOp.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblOp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOp.setText("OP+");
        lblOp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panOpLayout = new javax.swing.GroupLayout(panOp);
        panOp.setLayout(panOpLayout);
        panOpLayout.setHorizontalGroup(
            panOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panOpLayout.setVerticalGroup(
            panOpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOp, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        panAttack.setBackground(new java.awt.Color(255, 255, 255));
        panAttack.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblAttack.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblAttack.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttack.setText("Attack");
        lblAttack.setToolTipText("");
        lblAttack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblAttack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAttackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panAttackLayout = new javax.swing.GroupLayout(panAttack);
        panAttack.setLayout(panAttackLayout);
        panAttackLayout.setHorizontalGroup(
            panAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panAttackLayout.setVerticalGroup(
            panAttackLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblAttack, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        panBlock.setBackground(new java.awt.Color(255, 255, 255));
        panBlock.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblBlock.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblBlock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBlock.setText("Block");
        lblBlock.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblBlock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBlockMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panBlockLayout = new javax.swing.GroupLayout(panBlock);
        panBlock.setLayout(panBlockLayout);
        panBlockLayout.setHorizontalGroup(
            panBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panBlockLayout.setVerticalGroup(
            panBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBlock, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        panReception.setBackground(new java.awt.Color(255, 255, 255));
        panReception.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblReception.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblReception.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReception.setText("Reception");
        lblReception.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblReception.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblReceptionMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panReceptionLayout = new javax.swing.GroupLayout(panReception);
        panReception.setLayout(panReceptionLayout);
        panReceptionLayout.setHorizontalGroup(
            panReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReception, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panReceptionLayout.setVerticalGroup(
            panReceptionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblReception, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        panSet.setBackground(new java.awt.Color(255, 255, 255));
        panSet.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblSet.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        lblSet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSet.setText("Set");
        lblSet.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblSet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panSetLayout = new javax.swing.GroupLayout(panSet);
        panSet.setLayout(panSetLayout);
        panSetLayout.setHorizontalGroup(
            panSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panSetLayout.setVerticalGroup(
            panSetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblSet, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
        );

        jPanel27.setBackground(new java.awt.Color(57, 74, 108));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("SKILL");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panReception, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panAttack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panSet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panOp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panService, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panBlock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panDefence, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panTF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panReception, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panAttack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panBlock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDefence, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panOp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(57, 74, 108));
        jPanel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("PLAYERS");
        jLabel14.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        pan1.setBackground(new java.awt.Color(255, 255, 255));

        lbl1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl1.setText(" ");
        lbl1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan1Layout = new javax.swing.GroupLayout(pan1);
        pan1.setLayout(pan1Layout);
        pan1Layout.setHorizontalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan1Layout.setVerticalGroup(
            pan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan2.setBackground(new java.awt.Color(255, 255, 255));

        lbl2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan2Layout = new javax.swing.GroupLayout(pan2);
        pan2.setLayout(pan2Layout);
        pan2Layout.setHorizontalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan2Layout.setVerticalGroup(
            pan2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pan3.setBackground(new java.awt.Color(255, 255, 255));

        lbl3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan3Layout = new javax.swing.GroupLayout(pan3);
        pan3.setLayout(pan3Layout);
        pan3Layout.setHorizontalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan3Layout.setVerticalGroup(
            pan3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan4.setBackground(new java.awt.Color(255, 255, 255));

        lbl4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan4Layout = new javax.swing.GroupLayout(pan4);
        pan4.setLayout(pan4Layout);
        pan4Layout.setHorizontalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan4Layout.setVerticalGroup(
            pan4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan5.setBackground(new java.awt.Color(255, 255, 255));

        lbl5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan5Layout = new javax.swing.GroupLayout(pan5);
        pan5.setLayout(pan5Layout);
        pan5Layout.setHorizontalGroup(
            pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan5Layout.setVerticalGroup(
            pan5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl5, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan6.setBackground(new java.awt.Color(255, 255, 255));

        lbl6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan6Layout = new javax.swing.GroupLayout(pan6);
        pan6.setLayout(pan6Layout);
        pan6Layout.setHorizontalGroup(
            pan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan6Layout.setVerticalGroup(
            pan6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan9.setBackground(new java.awt.Color(255, 255, 255));

        lbl9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan9Layout = new javax.swing.GroupLayout(pan9);
        pan9.setLayout(pan9Layout);
        pan9Layout.setHorizontalGroup(
            pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan9Layout.setVerticalGroup(
            pan9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl9, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan8.setBackground(new java.awt.Color(255, 255, 255));

        lbl8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan8Layout = new javax.swing.GroupLayout(pan8);
        pan8.setLayout(pan8Layout);
        pan8Layout.setHorizontalGroup(
            pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan8Layout.setVerticalGroup(
            pan8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl8, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan7.setBackground(new java.awt.Color(255, 255, 255));

        lbl7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan7Layout = new javax.swing.GroupLayout(pan7);
        pan7.setLayout(pan7Layout);
        pan7Layout.setHorizontalGroup(
            pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan7Layout.setVerticalGroup(
            pan7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl7, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan12.setBackground(new java.awt.Color(255, 255, 255));

        lbl12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan12Layout = new javax.swing.GroupLayout(pan12);
        pan12.setLayout(pan12Layout);
        pan12Layout.setHorizontalGroup(
            pan12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan12Layout.setVerticalGroup(
            pan12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl12, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan11.setBackground(new java.awt.Color(255, 255, 255));

        lbl11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan11Layout = new javax.swing.GroupLayout(pan11);
        pan11.setLayout(pan11Layout);
        pan11Layout.setHorizontalGroup(
            pan11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl11, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan11Layout.setVerticalGroup(
            pan11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl11, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan10.setBackground(new java.awt.Color(255, 255, 255));

        lbl10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan10Layout = new javax.swing.GroupLayout(pan10);
        pan10.setLayout(pan10Layout);
        pan10Layout.setHorizontalGroup(
            pan10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl10, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan10Layout.setVerticalGroup(
            pan10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl10, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        pan15.setBackground(new java.awt.Color(255, 255, 255));

        lbl15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan15Layout = new javax.swing.GroupLayout(pan15);
        pan15.setLayout(pan15Layout);
        pan15Layout.setHorizontalGroup(
            pan15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pan15Layout.setVerticalGroup(
            pan15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pan14.setBackground(new java.awt.Color(255, 255, 255));

        lbl14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan14Layout = new javax.swing.GroupLayout(pan14);
        pan14.setLayout(pan14Layout);
        pan14Layout.setHorizontalGroup(
            pan14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl14, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan14Layout.setVerticalGroup(
            pan14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pan13.setBackground(new java.awt.Color(255, 255, 255));

        lbl13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lbl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout pan13Layout = new javax.swing.GroupLayout(pan13);
        pan13.setLayout(pan13Layout);
        pan13Layout.setHorizontalGroup(
            pan13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl13, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
        pan13Layout.setVerticalGroup(
            pan13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbl13, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(pan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(pan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addComponent(pan13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addComponent(pan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addComponent(pan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(pan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pan12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pan13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pan15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        panDesc1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panDesc1Layout = new javax.swing.GroupLayout(panDesc1);
        panDesc1.setLayout(panDesc1Layout);
        panDesc1Layout.setHorizontalGroup(
            panDesc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 219, Short.MAX_VALUE)
        );
        panDesc1Layout.setVerticalGroup(
            panDesc1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        panDesc2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panDesc2Layout = new javax.swing.GroupLayout(panDesc2);
        panDesc2.setLayout(panDesc2Layout);
        panDesc2Layout.setHorizontalGroup(
            panDesc2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        panDesc2Layout.setVerticalGroup(
            panDesc2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(panDesc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panDesc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panDesc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panDesc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(panCourt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panCourt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(57, 74, 108));

        txtInput.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInputActionPerformed(evt);
            }
        });
        txtInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtInputKeyPressed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 153));

        butReset.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        butReset.setText("CLEAR");
        butReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                butResetMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butReset, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(butReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtInput)
                .addGap(0, 0, 0)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtInput, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void lblDefenceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblDefenceMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblDefenceMouseClicked
    private void lblServiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblServiceMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblServiceMouseClicked
    private void lblTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTFMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblTFMouseClicked
    private void lblOpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblOpMouseClicked
    private void lblAttackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAttackMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblAttackMouseClicked
    private void lblBlockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBlockMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblBlockMouseClicked
    private void lblReceptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReceptionMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblReceptionMouseClicked
    private void lblSetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSetMouseClicked
        // TODO add your handling code here:
        selectSkill((JLabel) evt.getSource());
    }//GEN-LAST:event_lblSetMouseClicked
    private void txtInputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInputKeyPressed
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (c == '-') {
            String val = txtInput.getText();
            int valIndex = val.lastIndexOf("-") == -1 ? 0 : val.lastIndexOf("-") + 1;
            command = val.substring(valIndex);
            System.out.println(command);

            String a = String.valueOf(command).toUpperCase();

            if (skillKeys.contains(a)) {
                switch (a) {
                    case "Q":
                        selectSkill(lblService);
                        break;
                    case "W":
                        selectSkill(lblReception);
                        break;
                    case "E":
                        selectSkill(lblAttack);
                        break;
                    case "R":
                        selectSkill(lblSet);
                        break;
                    case "T":
                        selectSkill(lblBlock);
                        break;
                    case "Y":
                        selectSkill(lblDefence);
                        break;
                    case "U":
                        selectSkill(lblOp);
                        break;
                    case "I":
                        selectSkill(lblTF);
                        break;
                }
            } else if (scoreKeys.contains(a)) {
                switch (a) {
                    case "Z":
                        selectScore(lblRate1);
                        break;
                    case "X":
                        selectScore(lblRate2);
                        break;
                    case "C":
                        selectScore(lblRate3);
                        break;
                    case "V":
                        selectScore(lblRate4);
                        break;
                    case "B":
                        selectScore(lblRate5);
                        break;
                }
            } else if (diagramKeys.contains(a)) {
                diagramPoints.add(a);
                if (diagramPoints.size() > 1) {

                    if (skill.equals(Skill.Set.getType())) {
                        panel.dig(skill, diagramPoints, TempoEnum.getTempoByName(skillDescIdPanMap.get(43).lblOption.getText()));
                    } else {
                        panel.dig(skill, diagramPoints, 0);
                    }

                    if (CommonUtil.isNumeric(diagramPoints.get(0))) {
                        if (diagramPoints.size() > 2) {
                            setDetailValuesByDiagram(skill, diagramPoints, 1);
                        }
                    } else {
                        setDetailValuesByDiagram(skill, diagramPoints, 0);
                    }

                }
            } else if (detailsShortcutKeys.contains(a)) {

                for (Map.Entry<PanSkillDescCriteria, List<String>> entry : panPoints.entrySet()) {
                    if (entry.getValue().contains(a)) {
                        PanSkillDescCriteria p = entry.getKey();
                        String point = pointsShortcut.get(a);
                        p.lblOption.setText(point);
                        break;
                    }
                }
            } else {
                int i = 0;
                for (Player pl : Controller.panMatchSet.playerList) {
                    if (pl.getChestNo().equals(command)) {
                        selectPlayer(playerLabelList.get(i));
                    }
                    i++;
                }
            }
        }

    }//GEN-LAST:event_txtInputKeyPressed

    public void refresh() {
        panel.setPlayerPosition(p.rallyPositionMap, p.rallyPositionMapOpp);
    }

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:

        dialogReplaceLibero = new DialogReplaceLibero();
        dialogReplaceLibero.init(this, 1);
        dialogReplaceLibero.show();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        dialogReplaceLibero = new DialogReplaceLibero();
        dialogReplaceLibero.init(this, 2);
        dialogReplaceLibero.show();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInputActionPerformed

    private void butResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_butResetMouseClicked
        // TODO add your handling code here:
        PanEvaluationRowDetail panEvaluationRowDetail = new PanEvaluationRowDetail(p, false);
        p.panEvalDetail.removeAll();
        p.panEvalDetail.add(panEvaluationRowDetail, BorderLayout.CENTER);
        p.panEvalDetail.validate();
        p.panEvalDetail.repaint();
        p.currentPanRow.txtPlayer.setText("-");
        p.currentPanRow.txtRate.setText("-");
        p.currentPanRow.txtSkill.setText("-");

    }//GEN-LAST:event_butResetMouseClicked

    public RallyEvaluationSkillScore getRallyEvaluationSkillScore() {
        return rallyEvaluationSkillScore;
    }

    public void setRallyEvaluationSkillScore(RallyEvaluationSkillScore rallyEvaluationSkillScore) {
        this.rallyEvaluationSkillScore = rallyEvaluationSkillScore;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel butReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl10;
    private javax.swing.JLabel lbl11;
    private javax.swing.JLabel lbl12;
    private javax.swing.JLabel lbl13;
    private javax.swing.JLabel lbl14;
    private javax.swing.JLabel lbl15;
    private javax.swing.JLabel lbl2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JLabel lbl7;
    private javax.swing.JLabel lbl8;
    private javax.swing.JLabel lbl9;
    private javax.swing.JLabel lblAttack;
    private javax.swing.JLabel lblBlock;
    private javax.swing.JLabel lblDefence;
    private javax.swing.JLabel lblOp;
    private javax.swing.JLabel lblRate1;
    private javax.swing.JLabel lblRate2;
    private javax.swing.JLabel lblRate3;
    private javax.swing.JLabel lblRate4;
    private javax.swing.JLabel lblRate5;
    private javax.swing.JLabel lblReception;
    private javax.swing.JLabel lblService;
    private javax.swing.JLabel lblSet;
    private javax.swing.JLabel lblTF;
    private javax.swing.JPanel pan1;
    private javax.swing.JPanel pan10;
    private javax.swing.JPanel pan11;
    private javax.swing.JPanel pan12;
    private javax.swing.JPanel pan13;
    private javax.swing.JPanel pan14;
    private javax.swing.JPanel pan15;
    private javax.swing.JPanel pan2;
    private javax.swing.JPanel pan3;
    private javax.swing.JPanel pan4;
    private javax.swing.JPanel pan5;
    private javax.swing.JPanel pan6;
    private javax.swing.JPanel pan7;
    private javax.swing.JPanel pan8;
    private javax.swing.JPanel pan9;
    private javax.swing.JPanel panAttack;
    private javax.swing.JPanel panBlock;
    private javax.swing.JPanel panCourt;
    private javax.swing.JPanel panCourt1;
    private javax.swing.JPanel panDefence;
    private javax.swing.JPanel panDesc1;
    private javax.swing.JPanel panDesc2;
    private javax.swing.JPanel panHomeRL;
    private javax.swing.JPanel panOp;
    private javax.swing.JPanel panOppRL;
    private javax.swing.JPanel panRLHome;
    private javax.swing.JPanel panRLOpp;
    private javax.swing.JPanel panRate1;
    private javax.swing.JPanel panRate2;
    private javax.swing.JPanel panRate3;
    private javax.swing.JPanel panRate4;
    private javax.swing.JPanel panRate5;
    private javax.swing.JPanel panReception;
    private javax.swing.JPanel panService;
    private javax.swing.JPanel panSet;
    private javax.swing.JPanel panTF;
    private javax.swing.JTextField txtInput;
    // End of variables declaration//GEN-END:variables
}
