/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels.report;

import com.vollyball.panels.*;
import com.vollyball.bean.MatchBean;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerScores;
import com.vollyball.bean.TeamScores;
import com.vollyball.bean.TeamSkillScore;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dao.TeamReportDao;
import com.vollyball.renderer.TableHeaderRendererForReport;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Supriya
 */
public class PanMatchReportConsolidated extends javax.swing.JPanel {

    int cb;
    int matchId;
    MatchDao matchDao = new MatchDao();
    TeamDao td = new TeamDao();
    ReportDao reportDao = new ReportDao();
    DefaultTableModel model;
    List<MatchSet> lstMatchSetTeam1 = new ArrayList<>();
    List<MatchSet> lstMatchSetTeam2 = new ArrayList<>();
    LinkedHashMap<Integer, Integer> evalId = new LinkedHashMap<>();
    int evaluationteamId, evaluationteamId2;
    int team1id, team2id;
    RallyDao rd = new RallyDao();
    int team1wonBy = 0, team2wonBy = 0;
    TeamReportDao trd = new TeamReportDao();
    Map<String, Player> playerNameMap = new HashMap<String, Player>();
//    JTable team1PlayerTable2;
//    JTable team2PlayerTable;

    /**
     * Creates new form PanMatchReportConsolidated
     *
     * @param cb
     * @param matchId
     */
    public PanMatchReportConsolidated(int cb, int matchId) {
        this.cb = cb;
        this.matchId = matchId;
        initComponents();
        MatchBean team = matchDao.getMatchesById(Controller.competitionId, matchId);
        match.setText(team.getTeam1name() + " vs " + team.getTeam2name());
        phase.setText(team.getPhase());
        matchDate.setText(team.getDate());
        team1label.setText(team.getTeam1name());
        team2label.setText(team.getTeam2name());

        team1id = team.getTeam1();
        team2id = team.getTeam2();

        evaluationteamId = reportDao.getTeamEvaluationIdBYMatch(team1id, matchId);
        evalId.put(team.getTeam1(), evaluationteamId);
        evaluationteamId2 = reportDao.getTeamEvaluationIdBYMatch(team2id, matchId);
        evalId.put(team.getTeam2(), evaluationteamId2);
        setRowTeam1(team1id);
        setRowTeam2(team2id);
        setTeamSSDataSkillWise();
        setTeamNSSDataSkillWise();
        teamsWin();
        teamsLoss();
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

        TeamScores team1SuccessScores = trd.getTeamSuccessReportSkillwise(cb, matchId, team1id);
        TeamScores team1ErrorScores = trd.getTeamErrorReportSkillwise(cb, matchId, team1id);
        team1Sum.setText(team.getTeam1name());
        team1SumSerSuccess.setText(team1SuccessScores.getServiceRatePerc());
        team1SumAttackSuccess.setText(team1SuccessScores.getAttackRatePerc());
        team1SumBlkSuccess.setText(team1SuccessScores.getBlockRatePerc());
        team1SumRcpSuccess.setText(team1SuccessScores.getReceptionRatePerc());
        team1SumSetSuccess.setText(team1SuccessScores.getSetRatePerc());
        team1SumDefSuccess.setText(team1SuccessScores.getDefenceRatePerc());

        //team 1 scoring and non scoring success data
        t1ssSuccessSerRate.setText(team1SuccessScores.getServiceRatePerc());
        t1ssSuccessAttkRate.setText(team1SuccessScores.getAttackRatePerc());
        t1ssSuccessBlkRate.setText(team1SuccessScores.getBlockRatePerc());
        t1nssSuccessDefRate.setText(team1SuccessScores.getDefenceRatePerc());
        t1nssSuccessSetRate.setText(team1SuccessScores.getSetRatePerc());
        t1nssSuccessRepRate.setText(team1SuccessScores.getReceptionRatePerc());

        team1SumSerError.setText(team1ErrorScores.getServiceRatePerc());
        team1SumAttackError.setText(team1ErrorScores.getAttackRatePerc());
        team1SumBlkError.setText(team1ErrorScores.getBlockRatePerc());
        team1SumRcpError.setText(team1ErrorScores.getReceptionRatePerc());
        team1SumSetError.setText(team1ErrorScores.getSetRatePerc());
        team1SumDefError.setText(team1ErrorScores.getDefenceRatePerc());

        //team 1 scoring and non scoring error data
        t1ssErrorSerRate.setText(team1ErrorScores.getServiceRatePerc());
        t1ssErrorAttkRate.setText(team1ErrorScores.getAttackRatePerc());
        t1ssErrorBlkRate.setText(team1ErrorScores.getBlockRatePerc());
        t1nssErrorRepRate.setText(team1ErrorScores.getReceptionRatePerc());
        t1nssErrorSetRate.setText(team1ErrorScores.getSetRatePerc());
        t1nssErrorDefRate.setText(team1ErrorScores.getDefenceRatePerc());

        TeamScores team2SuccessScores = trd.getTeamSuccessReportSkillwise(cb, matchId, team2id);
        TeamScores team2ErrorScores = trd.getTeamErrorReportSkillwise(cb, matchId, team2id);

        team2Sum.setText(team.getTeam2name());
        team2SumSerSuccess.setText(team2SuccessScores.getServiceRatePerc());
        team2SumAttackSuccess.setText(team2SuccessScores.getAttackRatePerc());
        team2SumBlkSuccess.setText(team2SuccessScores.getBlockRatePerc());
        team2SumRcpSuccess.setText(team2SuccessScores.getReceptionRatePerc());
        team2SumSetSuccess.setText(team2SuccessScores.getSetRatePerc());
        team2SumDefSuccess.setText(team2SuccessScores.getDefenceRatePerc());

        //team2 scoring and non scoring success data
        t2ssSuccessSerRate.setText(team2SuccessScores.getServiceRatePerc());
        t2ssSuccessAttkRate.setText(team2SuccessScores.getAttackRatePerc());
        t2ssSuccessBlkRate.setText(team2SuccessScores.getBlockRatePerc());
        t2nssSuccessDefRate.setText(team2SuccessScores.getDefenceRatePerc());
        t2nssSuccessSetRate.setText(team2SuccessScores.getSetRatePerc());
        t2nssSuccessRepRate.setText(team2SuccessScores.getReceptionRatePerc());

        team2SumSerError.setText(team2ErrorScores.getServiceRatePerc());
        team2SumAttackError.setText(team2ErrorScores.getAttackRatePerc());
        team2SumBlkError.setText(team2ErrorScores.getBlockRatePerc());
        team2SumRcpError.setText(team2ErrorScores.getReceptionRatePerc());
        team2SumSetError.setText(team2ErrorScores.getSetRatePerc());
        team2SumDefError.setText(team2ErrorScores.getDefenceRatePerc());

        //team 2 scoring and non scoring error data
        t2ssErrorSerRate.setText(team2ErrorScores.getServiceRatePerc());
        t2ssErrorAttkRate.setText(team2ErrorScores.getAttackRatePerc());
        t2ssErrorBlkRate.setText(team2ErrorScores.getBlockRatePerc());
        t2nssErrorRepRate.setText(team2ErrorScores.getReceptionRatePerc());
        t2nssErrorSetRate.setText(team2ErrorScores.getSetRatePerc());
        t2nssErrorDefRate.setText(team2ErrorScores.getDefenceRatePerc());

        team1SSName.setText(team.getTeam1name());
        team2SSName.setText(team.getTeam2name());
        team1NSSName.setText(team.getTeam1name());
        team2NSSName.setText(team.getTeam2name());
        team1WLName.setText(team.getTeam1name());
        team2WLName.setText(team.getTeam2name());

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader header = tbSetDetails.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRendererForReport(tbSetDetails));
        tbSetDetails.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JTableHeader header1 = tbTeam1Win.getTableHeader();
        header1.setDefaultRenderer(new TableHeaderRendererForReport(tbTeam1Win));
        tbTeam1Win.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JTableHeader header2 = tbTeam1Loss.getTableHeader();
        header2.setDefaultRenderer(new TableHeaderRendererForReport(tbTeam1Loss));
        tbTeam1Loss.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbTeam1Loss.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        JTableHeader header3 = tbTeam2Win.getTableHeader();
        header3.setDefaultRenderer(new TableHeaderRendererForReport(tbTeam2Win));
        tbTeam2Win.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JTableHeader header4 = tbTeam2Loss.getTableHeader();
        header4.setDefaultRenderer(new TableHeaderRendererForReport(tbTeam2Loss));
        tbTeam2Loss.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

        JTableHeader header5 = team1PlayerTable.getTableHeader();
        header5.setDefaultRenderer(new TableHeaderRendererForReport(team1PlayerTable));
        team1PlayerTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
//        team1PlayerTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        team1PlayerTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        team1PlayerTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        team1PlayerTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        team1PlayerTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        team1PlayerTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        Color ivory = new Color(255, 255, 255);
        team1PlayerTable.setOpaque(true);
        team1PlayerTable.setFillsViewportHeight(true);
        team1PlayerTable.setBackground(ivory);

        JTableHeader header6 = team2PlayerTable.getTableHeader();
        header6.setDefaultRenderer(new TableHeaderRendererForReport(team2PlayerTable));
        team2PlayerTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
//        team2PlayerTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        team2PlayerTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        team2PlayerTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        team2PlayerTable.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        team2PlayerTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        team2PlayerTable.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        team2PlayerTable.setOpaque(true);
        team2PlayerTable.setFillsViewportHeight(true);
        team2PlayerTable.setBackground(ivory);

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
            int timeoutCount = matchDao.getTimeOutCount(ms.getId());

            if (ms.getSetNo() == 1) {
                tbSetDetails.setValueAt(countRally, 0, 2);
                tbSetDetails.setValueAt(timeoutCount, 0, 3);
                tbSetDetails.setValueAt(homeScore + ":" + oppScore, 0, 4);

                String start_time[] = ms.getStart_time().split(":");
                String end_time[] = ms.getEnd_time().split(":");
                int timeout_min = Integer.parseInt(end_time[0]) - Integer.parseInt(start_time[0]);
                int timeout_sec = Integer.parseInt(end_time[1]) - Integer.parseInt(start_time[1]);
                tbSetDetails.setValueAt(timeout_min + ":" + timeout_sec, 0, 1);
                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            } else if (ms.getSetNo() == 2) {
                tbSetDetails.setValueAt(countRally, 1, 2);
                tbSetDetails.setValueAt(timeoutCount, 1, 3);
                tbSetDetails.setValueAt(homeScore + ":" + oppScore, 1, 4);
                String start_time[] = ms.getStart_time().split(":");
                String end_time[] = ms.getEnd_time().split(":");
                int timeout_min = Integer.parseInt(end_time[0]) - Integer.parseInt(start_time[0]);
                int timeout_sec = Integer.parseInt(end_time[1]) - Integer.parseInt(start_time[1]);
                tbSetDetails.setValueAt(timeout_min + ":" + timeout_sec, 1, 1);

                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            } else if (ms.getSetNo() == 3) {
                tbSetDetails.setValueAt(countRally, 2, 2);
                tbSetDetails.setValueAt(timeoutCount, 2, 3);
                tbSetDetails.setValueAt(homeScore + ":" + oppScore, 2, 4);
                String start_time[] = ms.getStart_time().split(":");
                String end_time[] = ms.getEnd_time().split(":");
                int timeout_min = Integer.parseInt(end_time[0]) - Integer.parseInt(start_time[0]);
                int timeout_sec = Integer.parseInt(end_time[1]) - Integer.parseInt(start_time[1]);
                tbSetDetails.setValueAt(timeout_min + ":" + timeout_sec, 2, 1);

                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            } else if (ms.getSetNo() == 4) {
                tbSetDetails.setValueAt(countRally, 3, 2);
                tbSetDetails.setValueAt(timeoutCount, 3, 3);
                tbSetDetails.setValueAt(homeScore + ":" + oppScore, 3, 4);
                String start_time[] = ms.getStart_time().split(":");
                String end_time[] = ms.getEnd_time().split(":");
                int timeout_min = Integer.parseInt(end_time[0]) - Integer.parseInt(start_time[0]);
                int timeout_sec = Integer.parseInt(end_time[1]) - Integer.parseInt(start_time[1]);
                tbSetDetails.setValueAt(timeout_min + ":" + timeout_sec, 3, 1);

                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            } else if (ms.getSetNo() == 5) {
                tbSetDetails.setValueAt(countRally, 4, 2);
                tbSetDetails.setValueAt(timeoutCount, 4, 3);
                tbSetDetails.setValueAt(homeScore + ":" + oppScore, 4, 4);

                String start_time[] = ms.getStart_time().split(":");
                String end_time[] = ms.getEnd_time().split(":");
                int timeout_min = Integer.parseInt(end_time[0]) - Integer.parseInt(start_time[0]);
                int timeout_sec = Integer.parseInt(end_time[1]) - Integer.parseInt(start_time[1]);
                tbSetDetails.setValueAt(timeout_min + ":" + timeout_sec, 4, 1);
                if (ms.getWon_by() == team1id) {
                    team1wonBy++;
                } else if (ms.getWon_by() == team2id) {
                    team2wonBy++;
                }
            }

        }
        team1WonPointsLabel.setText("" + team1wonBy);
        team2WonPointLabel.setText("" + team2wonBy);
    }

    DefaultTableModel team1Playermodel;

    public void setRowTeam1(int teamId) {
        resizeColumns();
        team1Playermodel = (DefaultTableModel) team1PlayerTable.getModel();
        Map<PlayerScores, List<Integer>> playerSetDetails = new HashMap<>();
        for (int i = team1Playermodel.getRowCount() - 1; i >= 0; i--) {
            team1Playermodel.removeRow(i);
        }

        playerSetDetails = trd.getTeamPlayers(teamId, evaluationteamId);
        for (Map.Entry<PlayerScores, List<Integer>> entry : playerSetDetails.entrySet()) {
            PlayerScores key = entry.getKey();
            List<Integer> value = entry.getValue();
            Object[] row = {key.getChestNo(), key.getPlayerName(), value.get(0), value.get(1), value.get(2), value.get(3), value.get(4)};
            team1Playermodel.addRow(row);
        }

//        for (Player p : playerList) {
//            playerNameMap.put(p.getName(), p);
//            Object[] row = {p.getChestNo(), p.getName(), "0", "0", "0", "0", "0"};
//            team1Playermodel.addRow(row);
//            i++;
//        }
    }
    float[] columnWidthPercentage = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    private void resizeColumns() {
        int tW = team1PlayerTable.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = team1PlayerTable.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    DefaultTableModel team2Playermodel;

    float[] columnWidthPercentage1 = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    private void resizeColumns1() {
        int tW = team2PlayerTable.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = team2PlayerTable.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage1[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    public void setRowTeam2(int teamId) {
        resizeColumns1();
        team2Playermodel = (DefaultTableModel) team2PlayerTable.getModel();
        Map<PlayerScores, List<Integer>> playerSetDetails = new HashMap<>();
        for (int i = team2Playermodel.getRowCount() - 1; i >= 0; i--) {
            team2Playermodel.removeRow(i);
        }

        playerSetDetails = trd.getTeamPlayers(teamId, evaluationteamId2);
        for (Map.Entry<PlayerScores, List<Integer>> entry : playerSetDetails.entrySet()) {
            PlayerScores key = entry.getKey();
            List<Integer> value = entry.getValue();
            Object[] row = {key.getChestNo(), key.getPlayerName(), value.get(0), value.get(1), value.get(2), value.get(3), value.get(4)};
            team2Playermodel.addRow(row);
        }

//        for (Player p : playerList) {
//            playerNameMap.put(p.getName(), p);
//            Object[] row = {p.getChestNo(), p.getName(), "0", "0", "0", "0", "0"};
//            team2Playermodel.addRow(row);
//            i++;
//        }
    }

    TeamSkillScore tss = new TeamSkillScore();

    public void setTeamSSDataSkillWise() {
        List<TeamSkillScore> skillSSData;
        skillSSData = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            tss = trd.getTeamSkillWiseScoreReport(cb, i, matchId, team1id, evaluationteamId);
            skillSSData.add(tss);
        }
        t1sstotalSer.setText("" + skillSSData.get(0).getTotalAttempt());
        t1ssr1Ser.setText("" + skillSSData.get(0).getOne());
        t1ssr2Ser.setText("" + skillSSData.get(0).getTwo());
        t1ssr3Ser.setText("" + skillSSData.get(0).getThree());
        t1ssr4Ser.setText("" + skillSSData.get(0).getFour());
        t1ssr5Ser.setText("" + skillSSData.get(0).getFive());

        t1sstotalAttk.setText("" + skillSSData.get(1).getTotalAttempt());
        t1ssr1Attk.setText("" + skillSSData.get(1).getOne());
        t1ssr2Attk.setText("" + skillSSData.get(1).getTwo());
        t1ssr3Attk.setText("" + skillSSData.get(1).getThree());
        t1ssr4Attk.setText("" + skillSSData.get(1).getFour());
        t1ssr5Attk.setText("" + skillSSData.get(1).getFive());

        t1sstotalBlk.setText("" + skillSSData.get(2).getTotalAttempt());
        t1ssr1Blk.setText("" + skillSSData.get(2).getOne());
        t1ssr2Blk.setText("" + skillSSData.get(2).getTwo());
        t1ssr3Blk.setText("" + skillSSData.get(2).getThree());
        t1ssr4Blk.setText("" + skillSSData.get(2).getFour());
        t1ssr5Blk.setText("" + skillSSData.get(2).getFive());

        t1ssOP.setText("" + skillSSData.get(0).getOp());

//        t1ssrOp.setText(""+skillData.get(7));
        skillSSData = new ArrayList<>();
        for (int i = 1; i < 4; i++) {
            tss = trd.getTeamSkillWiseScoreReport(cb, i, matchId, team2id, evaluationteamId2);
            skillSSData.add(tss);
        }
        t2sstotalSer.setText("" + skillSSData.get(0).getTotalAttempt());
        t2ssr1Ser.setText("" + skillSSData.get(0).getOne());
        t2ssr2Ser.setText("" + skillSSData.get(0).getTwo());
        t2ssr3Ser.setText("" + skillSSData.get(0).getThree());
        t2ssr4Ser.setText("" + skillSSData.get(0).getFour());
        t2ssr5Ser.setText("" + skillSSData.get(0).getFive());

        t2sstotalAttk.setText("" + skillSSData.get(1).getTotalAttempt());
        t2ssr1Attk.setText("" + skillSSData.get(1).getOne());
        t2ssr2Attk.setText("" + skillSSData.get(1).getTwo());
        t2ssr3Attk.setText("" + skillSSData.get(1).getThree());
        t2ssr4Attk.setText("" + skillSSData.get(1).getFour());
        t2ssr5Attk.setText("" + skillSSData.get(1).getFive());

        t2sstotalBlk.setText("" + skillSSData.get(2).getTotalAttempt());
        t2ssr1Blk.setText("" + skillSSData.get(2).getOne());
        t2ssr2Blk.setText("" + skillSSData.get(2).getTwo());
        t2ssr3Blk.setText("" + skillSSData.get(2).getThree());
        t2ssr4Blk.setText("" + skillSSData.get(2).getFour());
        t2ssr5Blk.setText("" + skillSSData.get(2).getFive());

        t2ssOP.setText("" + skillSSData.get(0).getOp());

    }

    public void setTeamNSSDataSkillWise() {
        List<TeamSkillScore> skillNSSData;
        skillNSSData = new ArrayList<>();
        for (int i = 4; i < 7; i++) {
            tss = trd.getTeamSkillWiseScoreReport(cb, i, matchId, team1id, evaluationteamId);
            skillNSSData.add(tss);
        }
        t1nsstotalSet.setText("" + skillNSSData.get(0).getTotalAttempt());
        t1nssr1Set.setText("" + skillNSSData.get(0).getOne());
        t1nssr2Set.setText("" + skillNSSData.get(0).getTwo());
        t1nssr3Set.setText("" + skillNSSData.get(0).getThree());
        t1nssr4Set.setText("" + skillNSSData.get(0).getFour());
        t1nssr5Set.setText("" + skillNSSData.get(0).getFive());

        t1nsstotalRep.setText("" + skillNSSData.get(1).getTotalAttempt());
        t1nssr1Rep.setText("" + skillNSSData.get(1).getOne());
        t1nssr2Rep.setText("" + skillNSSData.get(1).getTwo());
        t1nssr3Rep.setText("" + skillNSSData.get(1).getThree());
        t1nssr4Rep.setText("" + skillNSSData.get(1).getFour());
        t1nssr5Rep.setText("" + skillNSSData.get(1).getFive());

        t1nsstotalDef.setText("" + skillNSSData.get(2).getTotalAttempt());
        t1nssr1Def.setText("" + skillNSSData.get(2).getOne());
        t1nssr2Def.setText("" + skillNSSData.get(2).getTwo());
        t1nssr3Def.setText("" + skillNSSData.get(2).getThree());
        t1nssr4Def.setText("" + skillNSSData.get(2).getFour());
        t1nssr5Def.setText("" + skillNSSData.get(2).getFive());

        t1nssTF.setText("" + skillNSSData.get(0).getTf());
        skillNSSData = new ArrayList<>();
        for (int i = 4; i < 7; i++) {
            tss = trd.getTeamSkillWiseScoreReport(cb, i, matchId, team2id, evaluationteamId2);
            skillNSSData.add(tss);
        }
        t2nsstotalSet.setText("" + skillNSSData.get(0).getTotalAttempt());
        t2nssr1Set.setText("" + skillNSSData.get(0).getOne());
        t2nssr2Set.setText("" + skillNSSData.get(0).getTwo());
        t2nssr3Set.setText("" + skillNSSData.get(0).getThree());
        t2nssr4Set.setText("" + skillNSSData.get(0).getFour());
        t2nssr5Set.setText("" + skillNSSData.get(0).getFive());

        t2nsstotalRep.setText("" + skillNSSData.get(1).getTotalAttempt());
        t2nssr1Rep.setText("" + skillNSSData.get(1).getOne());
        t2nssr2Rep.setText("" + skillNSSData.get(1).getTwo());
        t2nssr3Rep.setText("" + skillNSSData.get(1).getThree());
        t2nssr4Rep.setText("" + skillNSSData.get(1).getFour());
        t2nssr5Rep.setText("" + skillNSSData.get(1).getFive());

        t2nsstotalDef.setText("" + skillNSSData.get(2).getTotalAttempt());
        t2nssr1Def.setText("" + skillNSSData.get(2).getOne());
        t2nssr2Def.setText("" + skillNSSData.get(2).getTwo());
        t2nssr3Def.setText("" + skillNSSData.get(2).getThree());
        t2nssr4Def.setText("" + skillNSSData.get(2).getFour());
        t2nssr5Def.setText("" + skillNSSData.get(2).getFive());

        t2nssTF.setText("" + skillNSSData.get(0).getTf());

    }

    DefaultTableModel team1WinTablemodel;
    DefaultTableModel team2WinTablemodel;

    public void teamsWin() {
        Map<String, List<TeamScores>> team1Map = new HashMap<>();
        List<Map<String, List<TeamScores>>> team1Win = new ArrayList<>();
        Map<String, List<TeamScores>> team2Map = new HashMap<>();
        List<Map<String, List<TeamScores>>> team2Win = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            team1Map = trd.getTeamSetWiseScoreReport(i, evaluationteamId);
            team1Win.add(team1Map);
        }
        resizeColumnsTeam1Win();
        team1WinTablemodel = (DefaultTableModel) tbTeam1Win.getModel();
        for (int i = team1WinTablemodel.getRowCount() - 1; i >= 0; i--) {
            team1WinTablemodel.removeRow(i);
        }

        for (Map<String, List<TeamScores>> map : team1Win) {
            for (Map.Entry<String, List<TeamScores>> entry : map.entrySet()) {
                int total = entry.getValue().get(0).getTotalAttempt()
                        + entry.getValue().get(1).getTotalAttempt()
                        + entry.getValue().get(2).getTotalAttempt()
                        + entry.getValue().get(6).getTotalAttempt();
                Object[] row = {entry.getKey().toString(), total,
                    entry.getValue().get(0).getTotalAttempt(),
                    entry.getValue().get(2).getTotalAttempt(),
                    entry.getValue().get(1).getTotalAttempt(),
                    entry.getValue().get(6).getTotalAttempt()};
                team1WinTablemodel.addRow(row);

            }

        }

        for (int i = 1; i <= 5; i++) {
            team2Map = trd.getTeamSetWiseScoreReport(i, evaluationteamId2);
            team2Win.add(team2Map);
        }
        resizeColumnsTeam2Win();
        team2WinTablemodel = (DefaultTableModel) tbTeam2Win.getModel();
        for (int i = team2WinTablemodel.getRowCount() - 1; i >= 0; i--) {
            team2WinTablemodel.removeRow(i);
        }

        for (Map<String, List<TeamScores>> map : team2Win) {
            for (Map.Entry<String, List<TeamScores>> entry : map.entrySet()) {
                int total = entry.getValue().get(0).getTotalAttempt()
                        + entry.getValue().get(1).getTotalAttempt()
                        + entry.getValue().get(2).getTotalAttempt()
                        + entry.getValue().get(6).getTotalAttempt();
                Object[] row = {entry.getKey().toString(), total,
                    entry.getValue().get(0).getTotalAttempt(),
                    entry.getValue().get(2).getTotalAttempt(),
                    entry.getValue().get(1).getTotalAttempt(),
                    entry.getValue().get(6).getTotalAttempt()};
                team2WinTablemodel.addRow(row);

            }

        }

    }
    float[] ColumnsTeam1WinWidthPercentage1 = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    private void resizeColumnsTeam1Win() {
        int tW = tbTeam1Win.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbTeam1Win.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(ColumnsTeam1WinWidthPercentage1[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }
    float[] ColumnsTeam2WinWidthPercentage1 = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    private void resizeColumnsTeam2Win() {
        int tW = tbTeam2Win.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbTeam2Win.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(ColumnsTeam1WinWidthPercentage1[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }

    DefaultTableModel team1LossTablemodel;
    DefaultTableModel team2LossTablemodel;

    public void teamsLoss() {
        Map<String, List<TeamScores>> team1Map = new HashMap<>();
        List<Map<String, List<TeamScores>>> team1Loss = new ArrayList<>();
        Map<String, List<TeamScores>> team2Map = new HashMap<>();
        List<Map<String, List<TeamScores>>> team2Loss = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            team1Map = trd.getTeamSetWiseScoreReportLoss(i, evaluationteamId);
            team1Loss.add(team1Map);
        }
        resizeColumnsTeam1Loss();
        team1LossTablemodel = (DefaultTableModel) tbTeam1Loss.getModel();
        for (int i = team1LossTablemodel.getRowCount() - 1; i >= 0; i--) {
            team1LossTablemodel.removeRow(i);
        }

        for (Map<String, List<TeamScores>> map : team1Loss) {
            for (Map.Entry<String, List<TeamScores>> entry : map.entrySet()) {
                int total = entry.getValue().get(0).getTotalAttempt()
                        + entry.getValue().get(1).getTotalAttempt()
                        + entry.getValue().get(2).getTotalAttempt()
                        + entry.getValue().get(4).getTotalAttempt()
                        + entry.getValue().get(3).getTotalAttempt()
                        + entry.getValue().get(5).getTotalAttempt()
                        + entry.getValue().get(7).getTotalAttempt();
                Object[] row = {entry.getKey().toString(),
                    total, entry.getValue().get(0).getTotalAttempt(),
                    entry.getValue().get(2).getTotalAttempt(),
                    entry.getValue().get(1).getTotalAttempt(),
                    entry.getValue().get(4).getTotalAttempt(),
                    entry.getValue().get(3).getTotalAttempt(),
                    entry.getValue().get(5).getTotalAttempt(),
                    entry.getValue().get(7).getTotalAttempt()};
                team1LossTablemodel.addRow(row);

            }

        }

        for (int i = 1; i <= 5; i++) {
            team2Map = trd.getTeamSetWiseScoreReportLoss(i, evaluationteamId2);
            team2Loss.add(team2Map);
        }
        resizeColumnsTeam2Loss();
        team2LossTablemodel = (DefaultTableModel) tbTeam2Loss.getModel();
        for (int i = team2LossTablemodel.getRowCount() - 1; i >= 0; i--) {
            team2LossTablemodel.removeRow(i);
        }

        for (Map<String, List<TeamScores>> map : team2Loss) {
            for (Map.Entry<String, List<TeamScores>> entry : map.entrySet()) {
                int total = entry.getValue().get(0).getTotalAttempt()
                        + entry.getValue().get(1).getTotalAttempt()
                        + entry.getValue().get(2).getTotalAttempt()
                        + entry.getValue().get(3).getTotalAttempt()
                        + entry.getValue().get(4).getTotalAttempt()
                        + entry.getValue().get(5).getTotalAttempt()
                        + entry.getValue().get(7).getTotalAttempt();
                Object[] row = {entry.getKey().toString(), total, entry.getValue().get(0).getTotalAttempt(),
                    entry.getValue().get(2).getTotalAttempt(),
                    entry.getValue().get(1).getTotalAttempt(),
                    entry.getValue().get(4).getTotalAttempt(),
                    entry.getValue().get(3).getTotalAttempt(),
                    entry.getValue().get(5).getTotalAttempt(),
                    entry.getValue().get(7).getTotalAttempt()};
                team2LossTablemodel.addRow(row);

            }

        }

    }
    float[] ColumnsTeam1LossWidthPercentage1 = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    private void resizeColumnsTeam1Loss() {
        int tW = tbTeam1Loss.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbTeam1Loss.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(ColumnsTeam1LossWidthPercentage1[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }
    float[] ColumnsTeam2LossWidthPercentage1 = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};

    private void resizeColumnsTeam2Loss() {
        int tW = tbTeam2Loss.getPreferredSize().width;
        TableColumn column;
        TableColumnModel jTableColumnModel = tbTeam2Loss.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(ColumnsTeam2LossWidthPercentage1[i] * tW);
            column.setPreferredWidth(pWidth);
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

        panMatchReport1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        match = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        phase = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        matchDate = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        t1sstotalSer = new javax.swing.JTextField();
        t2sstotalSer = new javax.swing.JTextField();
        t2ssr1Ser = new javax.swing.JTextField();
        t1ssr1Ser = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        t1ssr2Ser = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        t2ssr2Ser = new javax.swing.JTextField();
        t1ssSuccessSerRate = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        t1ssr3Ser = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        t2ssr3Ser = new javax.swing.JTextField();
        jTextField122 = new javax.swing.JTextField();
        t1ssErrorSerRate = new javax.swing.JTextField();
        t2ssErrorSerRate = new javax.swing.JTextField();
        t2ssr4Ser = new javax.swing.JTextField();
        t1ssr4Ser = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        t1ssr5Ser = new javax.swing.JTextField();
        t2ssr5Ser = new javax.swing.JTextField();
        t2ssSuccessSerRate = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        t1ssOP = new javax.swing.JTextField();
        t2ssOP = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        team1SSName = new javax.swing.JTextField();
        team2SSName = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jPanel45 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        t1sstotalAttk = new javax.swing.JTextField();
        t2sstotalAttk = new javax.swing.JTextField();
        t2ssr1Attk = new javax.swing.JTextField();
        t1ssr1Attk = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        t1ssr2Attk = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        t2ssr2Attk = new javax.swing.JTextField();
        t1ssSuccessAttkRate = new javax.swing.JTextField();
        jTextField77 = new javax.swing.JTextField();
        t1ssr3Attk = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        t2ssr3Attk = new javax.swing.JTextField();
        jTextField242 = new javax.swing.JTextField();
        t1ssErrorAttkRate = new javax.swing.JTextField();
        t2ssErrorAttkRate = new javax.swing.JTextField();
        t2ssr4Attk = new javax.swing.JTextField();
        t1ssr4Attk = new javax.swing.JTextField();
        jTextField83 = new javax.swing.JTextField();
        jTextField84 = new javax.swing.JTextField();
        t1ssr5Attk = new javax.swing.JTextField();
        t2ssr5Attk = new javax.swing.JTextField();
        t2ssSuccessAttkRate = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jTextField88 = new javax.swing.JTextField();
        t1sstotalBlk = new javax.swing.JTextField();
        t2sstotalBlk = new javax.swing.JTextField();
        t2ssr1Blk = new javax.swing.JTextField();
        t1ssr1Blk = new javax.swing.JTextField();
        jTextField93 = new javax.swing.JTextField();
        t1ssr2Blk = new javax.swing.JTextField();
        jTextField95 = new javax.swing.JTextField();
        t2ssr2Blk = new javax.swing.JTextField();
        t1ssSuccessBlkRate = new javax.swing.JTextField();
        jTextField98 = new javax.swing.JTextField();
        t1ssr3Blk = new javax.swing.JTextField();
        jTextField100 = new javax.swing.JTextField();
        t2ssr3Blk = new javax.swing.JTextField();
        jTextField245 = new javax.swing.JTextField();
        t1ssErrorBlkRate = new javax.swing.JTextField();
        t2ssErrorBlkRate = new javax.swing.JTextField();
        t2ssr4Blk = new javax.swing.JTextField();
        t1ssr4Blk = new javax.swing.JTextField();
        jTextField104 = new javax.swing.JTextField();
        jTextField105 = new javax.swing.JTextField();
        t1ssr5Blk = new javax.swing.JTextField();
        t2ssr5Blk = new javax.swing.JTextField();
        t2ssSuccessBlkRate = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jTextField10 = new javax.swing.JTextField();
        jTextField109 = new javax.swing.JTextField();
        t1nsstotalRep = new javax.swing.JTextField();
        t2nsstotalRep = new javax.swing.JTextField();
        t2nssr1Rep = new javax.swing.JTextField();
        t1nssr1Rep = new javax.swing.JTextField();
        jTextField114 = new javax.swing.JTextField();
        t1nssr2Rep = new javax.swing.JTextField();
        jTextField116 = new javax.swing.JTextField();
        t2nssr2Rep = new javax.swing.JTextField();
        t1nssSuccessRepRate = new javax.swing.JTextField();
        jTextField119 = new javax.swing.JTextField();
        t1nssr3Rep = new javax.swing.JTextField();
        jTextField121 = new javax.swing.JTextField();
        t2nssr3Rep = new javax.swing.JTextField();
        jTextField249 = new javax.swing.JTextField();
        t1nssErrorRepRate = new javax.swing.JTextField();
        t2nssErrorRepRate = new javax.swing.JTextField();
        t2nssr4Rep = new javax.swing.JTextField();
        t1nssr4Rep = new javax.swing.JTextField();
        jTextField254 = new javax.swing.JTextField();
        jTextField255 = new javax.swing.JTextField();
        t1nssr5Rep = new javax.swing.JTextField();
        t2nssr5Rep = new javax.swing.JTextField();
        t2nssSuccessRepRate = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jTextField259 = new javax.swing.JTextField();
        t1nssTF = new javax.swing.JTextField();
        t2nssTF = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        team1NSSName = new javax.swing.JTextField();
        team2NSSName = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jPanel49 = new javax.swing.JPanel();
        jTextField262 = new javax.swing.JTextField();
        jTextField263 = new javax.swing.JTextField();
        t1nsstotalSet = new javax.swing.JTextField();
        t2nsstotalSet = new javax.swing.JTextField();
        t2nssr1Set = new javax.swing.JTextField();
        t1nssr1Set = new javax.swing.JTextField();
        jTextField268 = new javax.swing.JTextField();
        t1nssr2Set = new javax.swing.JTextField();
        jTextField270 = new javax.swing.JTextField();
        t2nssr2Set = new javax.swing.JTextField();
        t1nssSuccessSetRate = new javax.swing.JTextField();
        jTextField273 = new javax.swing.JTextField();
        t1nssr3Set = new javax.swing.JTextField();
        jTextField275 = new javax.swing.JTextField();
        t2nssr3Set = new javax.swing.JTextField();
        jTextField277 = new javax.swing.JTextField();
        t1nssErrorSetRate = new javax.swing.JTextField();
        t2nssErrorSetRate = new javax.swing.JTextField();
        t2nssr4Set = new javax.swing.JTextField();
        t1nssr4Set = new javax.swing.JTextField();
        jTextField282 = new javax.swing.JTextField();
        jTextField283 = new javax.swing.JTextField();
        t1nssr5Set = new javax.swing.JTextField();
        t2nssr5Set = new javax.swing.JTextField();
        t2nssSuccessSetRate = new javax.swing.JTextField();
        jPanel50 = new javax.swing.JPanel();
        jTextField287 = new javax.swing.JTextField();
        jTextField288 = new javax.swing.JTextField();
        t1nsstotalDef = new javax.swing.JTextField();
        t2nsstotalDef = new javax.swing.JTextField();
        t2nssr1Def = new javax.swing.JTextField();
        t1nssr1Def = new javax.swing.JTextField();
        jTextField293 = new javax.swing.JTextField();
        t1nssr2Def = new javax.swing.JTextField();
        jTextField295 = new javax.swing.JTextField();
        t2nssr2Def = new javax.swing.JTextField();
        t1nssSuccessDefRate = new javax.swing.JTextField();
        jTextField298 = new javax.swing.JTextField();
        t1nssr3Def = new javax.swing.JTextField();
        jTextField300 = new javax.swing.JTextField();
        t2nssr3Def = new javax.swing.JTextField();
        jTextField302 = new javax.swing.JTextField();
        t1nssErrorDefRate = new javax.swing.JTextField();
        t2nssErrorDefRate = new javax.swing.JTextField();
        t2nssr4Def = new javax.swing.JTextField();
        t1nssr4Def = new javax.swing.JTextField();
        jTextField307 = new javax.swing.JTextField();
        jTextField308 = new javax.swing.JTextField();
        t1nssr5Def = new javax.swing.JTextField();
        t2nssr5Def = new javax.swing.JTextField();
        t2nssSuccessDefRate = new javax.swing.JTextField();
        jPanel26 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        jTextField15 = new javax.swing.JTextField();
        team1Sum = new javax.swing.JTextField();
        team2Sum = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        jTextField168 = new javax.swing.JTextField();
        jTextField169 = new javax.swing.JTextField();
        team1SumSerSuccess = new javax.swing.JTextField();
        team2SumSerSuccess = new javax.swing.JTextField();
        team1SumSerError = new javax.swing.JTextField();
        jTextField173 = new javax.swing.JTextField();
        team2SumSerError = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        jTextField175 = new javax.swing.JTextField();
        jTextField176 = new javax.swing.JTextField();
        team1SumAttackSuccess = new javax.swing.JTextField();
        team2SumAttackSuccess = new javax.swing.JTextField();
        team1SumAttackError = new javax.swing.JTextField();
        jTextField180 = new javax.swing.JTextField();
        team2SumAttackError = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jTextField182 = new javax.swing.JTextField();
        jTextField183 = new javax.swing.JTextField();
        team1SumBlkSuccess = new javax.swing.JTextField();
        team2SumBlkSuccess = new javax.swing.JTextField();
        team1SumBlkError = new javax.swing.JTextField();
        jTextField187 = new javax.swing.JTextField();
        team2SumBlkError = new javax.swing.JTextField();
        jPanel34 = new javax.swing.JPanel();
        jTextField189 = new javax.swing.JTextField();
        jTextField190 = new javax.swing.JTextField();
        team1SumRcpSuccess = new javax.swing.JTextField();
        team2SumRcpSuccess = new javax.swing.JTextField();
        team1SumRcpError = new javax.swing.JTextField();
        jTextField194 = new javax.swing.JTextField();
        team2SumRcpError = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        jTextField196 = new javax.swing.JTextField();
        jTextField197 = new javax.swing.JTextField();
        team1SumSetSuccess = new javax.swing.JTextField();
        team2SumSetSuccess = new javax.swing.JTextField();
        team1SumSetError = new javax.swing.JTextField();
        jTextField201 = new javax.swing.JTextField();
        team2SumSetError = new javax.swing.JTextField();
        jPanel36 = new javax.swing.JPanel();
        jTextField203 = new javax.swing.JTextField();
        jTextField204 = new javax.swing.JTextField();
        team1SumDefSuccess = new javax.swing.JTextField();
        team2SumDefSuccess = new javax.swing.JTextField();
        team1SumDefError = new javax.swing.JTextField();
        jTextField208 = new javax.swing.JTextField();
        team2SumDefError = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSetDetails = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        team1WLName = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbTeam1Win = new javax.swing.JTable();
        jPanel24 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbTeam1Loss = new javax.swing.JTable();
        team1playerDetailsPan = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        team1PlayerTable = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        team2WLName = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbTeam2Win = new javax.swing.JTable();
        jPanel44 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tbTeam2Loss = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        team2PlayerTable = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        team1label = new javax.swing.JLabel();
        team1WonPointsLabel = new javax.swing.JLabel();
        team2label = new javax.swing.JLabel();
        team2WonPointLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1000, 1513));

        panMatchReport1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Match : ");

        match.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        match.setText("-");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Phase  : ");

        phase.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phase.setText("-");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Date    :");

        matchDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        matchDate.setText("-");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(match, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(phase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(matchDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(match, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(phase, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(matchDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField4.setBackground(new java.awt.Color(204, 204, 204));
        jTextField4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("SERVICE");
        jTextField4.setBorder(null);

        jTextField20.setBackground(new java.awt.Color(204, 204, 204));
        jTextField20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField20.setText("Tot Pt.");
        jTextField20.setBorder(null);
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        t1sstotalSer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1sstotalSer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1sstotalSer.setText("0");
        t1sstotalSer.setBorder(null);

        t2sstotalSer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2sstotalSer.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2sstotalSer.setText("0");
        t2sstotalSer.setBorder(null);

        t2ssr1Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr1Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr1Ser.setText("0");
        t2ssr1Ser.setBorder(null);

        t1ssr1Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr1Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr1Ser.setText("0");
        t1ssr1Ser.setBorder(null);
        t1ssr1Ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ssr1SerActionPerformed(evt);
            }
        });

        jTextField25.setBackground(new java.awt.Color(204, 204, 204));
        jTextField25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setText("-");
        jTextField25.setBorder(null);

        t1ssr2Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr2Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr2Ser.setText("0");
        t1ssr2Ser.setBorder(null);

        jTextField27.setBackground(new java.awt.Color(204, 204, 204));
        jTextField27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField27.setText("<");
        jTextField27.setBorder(null);

        t2ssr2Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr2Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr2Ser.setText("0");
        t2ssr2Ser.setBorder(null);

        t1ssSuccessSerRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssSuccessSerRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssSuccessSerRate.setText("0%");
        t1ssSuccessSerRate.setBorder(null);

        jTextField30.setBackground(new java.awt.Color(204, 204, 204));
        jTextField30.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField30.setText("Succ %");
        jTextField30.setBorder(null);

        t1ssr3Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr3Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr3Ser.setText("0");
        t1ssr3Ser.setBorder(null);
        t1ssr3Ser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ssr3SerActionPerformed(evt);
            }
        });

        jTextField33.setBackground(new java.awt.Color(204, 204, 204));
        jTextField33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField33.setText("@");
        jTextField33.setBorder(null);

        t2ssr3Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr3Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr3Ser.setText("0");
        t2ssr3Ser.setBorder(null);

        jTextField122.setBackground(new java.awt.Color(204, 204, 204));
        jTextField122.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField122.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField122.setText("Err %");
        jTextField122.setBorder(null);

        t1ssErrorSerRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssErrorSerRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssErrorSerRate.setText("0%");
        t1ssErrorSerRate.setBorder(null);

        t2ssErrorSerRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssErrorSerRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssErrorSerRate.setText("0%");
        t2ssErrorSerRate.setBorder(null);

        t2ssr4Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr4Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr4Ser.setText("0");
        t2ssr4Ser.setBorder(null);

        t1ssr4Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr4Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr4Ser.setText("0");
        t1ssr4Ser.setBorder(null);

        jTextField57.setBackground(new java.awt.Color(204, 204, 204));
        jTextField57.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField57.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField57.setText(">");
        jTextField57.setBorder(null);

        jTextField58.setBackground(new java.awt.Color(204, 204, 204));
        jTextField58.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField58.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField58.setText("+");
        jTextField58.setBorder(null);

        t1ssr5Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr5Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr5Ser.setText("0");
        t1ssr5Ser.setBorder(null);

        t2ssr5Ser.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr5Ser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr5Ser.setText("0");
        t2ssr5Ser.setBorder(null);

        t2ssSuccessSerRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssSuccessSerRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssSuccessSerRate.setText("0%");
        t2ssSuccessSerRate.setBorder(null);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField20)
                    .addComponent(t1sstotalSer)
                    .addComponent(t2sstotalSer))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField25)
                    .addComponent(t1ssr1Ser)
                    .addComponent(t2ssr1Ser))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField27)
                    .addComponent(t1ssr2Ser)
                    .addComponent(t2ssr2Ser))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1ssr3Ser)
                    .addComponent(jTextField33)
                    .addComponent(t2ssr3Ser))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssr4Ser)
                    .addComponent(jTextField57, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1ssr4Ser))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssr5Ser)
                    .addComponent(t1ssr5Ser)
                    .addComponent(jTextField58))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssSuccessSerRate)
                    .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1ssSuccessSerRate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1ssErrorSerRate)
                    .addComponent(jTextField122)
                    .addComponent(t2ssErrorSerRate)))
            .addComponent(jTextField4)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1sstotalSer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2sstotalSer, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1ssr1Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2ssr1Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1ssr2Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2ssr2Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField122, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField57, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField58, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1ssr3Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssr4Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssr5Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssSuccessSerRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssErrorSerRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(t2ssr3Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2ssErrorSerRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssr4Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssr5Ser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssSuccessSerRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, 0))
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField7.setBackground(new java.awt.Color(204, 204, 204));
        jTextField7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField7.setText("OP +");
        jTextField7.setBorder(null);

        jTextField65.setBackground(new java.awt.Color(204, 204, 204));
        jTextField65.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField65.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField65.setText("Tot Pt.");
        jTextField65.setBorder(null);

        t1ssOP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssOP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssOP.setText("0");
        t1ssOP.setBorder(null);

        t2ssOP.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssOP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssOP.setText("0");
        t2ssOP.setBorder(null);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField7)
            .addComponent(jTextField65, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(t1ssOP, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(t2ssOP)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(t1ssOP, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(t2ssOP)
                .addGap(0, 0, 0))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1SSName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SSName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SSName.setText("Maharashtra");
        team1SSName.setBorder(null);

        team2SSName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SSName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SSName.setText("Andhra Pradesh");
        team2SSName.setBorder(null);

        jTextField1.setBackground(new java.awt.Color(204, 204, 204));
        jTextField1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("TEAM");
        jTextField1.setBorder(null);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1SSName, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(team2SSName, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(jTextField1)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(team1SSName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(team2SSName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField8.setBackground(new java.awt.Color(204, 204, 204));
        jTextField8.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField8.setText("ATTACK");
        jTextField8.setBorder(null);

        jTextField31.setBackground(new java.awt.Color(204, 204, 204));
        jTextField31.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField31.setText("Tot Pt.");
        jTextField31.setBorder(null);
        jTextField31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField31ActionPerformed(evt);
            }
        });

        t1sstotalAttk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1sstotalAttk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1sstotalAttk.setText("0");
        t1sstotalAttk.setBorder(null);

        t2sstotalAttk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2sstotalAttk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2sstotalAttk.setText("0");
        t2sstotalAttk.setBorder(null);

        t2ssr1Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr1Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr1Attk.setText("0");
        t2ssr1Attk.setBorder(null);

        t1ssr1Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr1Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr1Attk.setText("0");
        t1ssr1Attk.setBorder(null);
        t1ssr1Attk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ssr1AttkActionPerformed(evt);
            }
        });

        jTextField69.setBackground(new java.awt.Color(204, 204, 204));
        jTextField69.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField69.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField69.setText("-");
        jTextField69.setBorder(null);

        t1ssr2Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr2Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr2Attk.setText("0");
        t1ssr2Attk.setBorder(null);

        jTextField71.setBackground(new java.awt.Color(204, 204, 204));
        jTextField71.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField71.setText("<");
        jTextField71.setBorder(null);

        t2ssr2Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr2Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr2Attk.setText("0");
        t2ssr2Attk.setBorder(null);

        t1ssSuccessAttkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssSuccessAttkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssSuccessAttkRate.setText("0%");
        t1ssSuccessAttkRate.setBorder(null);

        jTextField77.setBackground(new java.awt.Color(204, 204, 204));
        jTextField77.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField77.setText("Succ %");
        jTextField77.setBorder(null);

        t1ssr3Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr3Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr3Attk.setText("0");
        t1ssr3Attk.setBorder(null);
        t1ssr3Attk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ssr3AttkActionPerformed(evt);
            }
        });

        jTextField79.setBackground(new java.awt.Color(204, 204, 204));
        jTextField79.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField79.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField79.setText("@");
        jTextField79.setBorder(null);

        t2ssr3Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr3Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr3Attk.setText("0");
        t2ssr3Attk.setBorder(null);

        jTextField242.setBackground(new java.awt.Color(204, 204, 204));
        jTextField242.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField242.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField242.setText("Err %");
        jTextField242.setBorder(null);

        t1ssErrorAttkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssErrorAttkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssErrorAttkRate.setText("0%");
        t1ssErrorAttkRate.setBorder(null);

        t2ssErrorAttkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssErrorAttkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssErrorAttkRate.setText("0%");
        t2ssErrorAttkRate.setBorder(null);

        t2ssr4Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr4Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr4Attk.setText("0");
        t2ssr4Attk.setBorder(null);

        t1ssr4Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr4Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr4Attk.setText("0");
        t1ssr4Attk.setBorder(null);

        jTextField83.setBackground(new java.awt.Color(204, 204, 204));
        jTextField83.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField83.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField83.setText(">");
        jTextField83.setBorder(null);

        jTextField84.setBackground(new java.awt.Color(204, 204, 204));
        jTextField84.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField84.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField84.setText("+");
        jTextField84.setBorder(null);

        t1ssr5Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr5Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr5Attk.setText("0");
        t1ssr5Attk.setBorder(null);

        t2ssr5Attk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr5Attk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr5Attk.setText("0");
        t2ssr5Attk.setBorder(null);

        t2ssSuccessAttkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssSuccessAttkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssSuccessAttkRate.setText("0%");
        t2ssSuccessAttkRate.setBorder(null);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1sstotalAttk)
                    .addComponent(t2sstotalAttk)
                    .addComponent(jTextField31))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField69)
                    .addComponent(t1ssr1Attk)
                    .addComponent(t2ssr1Attk))
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField71)
                    .addComponent(t1ssr2Attk)
                    .addComponent(t2ssr2Attk))
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1ssr3Attk)
                    .addComponent(jTextField79)
                    .addComponent(t2ssr3Attk))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssr4Attk)
                    .addComponent(jTextField83, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1ssr4Attk))
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssr5Attk)
                    .addComponent(t1ssr5Attk)
                    .addComponent(jTextField84))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssSuccessAttkRate)
                    .addComponent(jTextField77, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1ssSuccessAttkRate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1ssErrorAttkRate)
                    .addComponent(jTextField242)
                    .addComponent(t2ssErrorAttkRate)))
            .addComponent(jTextField8)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1sstotalAttk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2sstotalAttk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1ssr1Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2ssr1Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jTextField71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1ssr2Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2ssr2Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField77, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField242, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField79, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField83, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField84, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1ssr3Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssr4Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssr5Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssSuccessAttkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssErrorAttkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(t2ssr3Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2ssErrorAttkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssr4Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssr5Attk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssSuccessAttkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, 0))
        );

        jPanel46.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField9.setBackground(new java.awt.Color(204, 204, 204));
        jTextField9.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField9.setText("BLOCK");
        jTextField9.setBorder(null);

        jTextField88.setBackground(new java.awt.Color(204, 204, 204));
        jTextField88.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField88.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField88.setText("Tot Pt.");
        jTextField88.setBorder(null);
        jTextField88.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField88ActionPerformed(evt);
            }
        });

        t1sstotalBlk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1sstotalBlk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1sstotalBlk.setText("0");
        t1sstotalBlk.setBorder(null);

        t2sstotalBlk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2sstotalBlk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2sstotalBlk.setText("0");
        t2sstotalBlk.setBorder(null);

        t2ssr1Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr1Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr1Blk.setText("0");
        t2ssr1Blk.setBorder(null);

        t1ssr1Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr1Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr1Blk.setText("0");
        t1ssr1Blk.setBorder(null);
        t1ssr1Blk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ssr1BlkActionPerformed(evt);
            }
        });

        jTextField93.setBackground(new java.awt.Color(204, 204, 204));
        jTextField93.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField93.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField93.setText("-");
        jTextField93.setBorder(null);

        t1ssr2Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr2Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr2Blk.setText("0");
        t1ssr2Blk.setBorder(null);

        jTextField95.setBackground(new java.awt.Color(204, 204, 204));
        jTextField95.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField95.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField95.setText("<");
        jTextField95.setBorder(null);

        t2ssr2Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr2Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr2Blk.setText("0");
        t2ssr2Blk.setBorder(null);

        t1ssSuccessBlkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssSuccessBlkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssSuccessBlkRate.setText("0%");
        t1ssSuccessBlkRate.setBorder(null);

        jTextField98.setBackground(new java.awt.Color(204, 204, 204));
        jTextField98.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField98.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField98.setText("Succ %");
        jTextField98.setBorder(null);

        t1ssr3Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr3Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr3Blk.setText("0");
        t1ssr3Blk.setBorder(null);
        t1ssr3Blk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1ssr3BlkActionPerformed(evt);
            }
        });

        jTextField100.setBackground(new java.awt.Color(204, 204, 204));
        jTextField100.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField100.setText("@");
        jTextField100.setBorder(null);

        t2ssr3Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr3Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr3Blk.setText("0");
        t2ssr3Blk.setBorder(null);

        jTextField245.setBackground(new java.awt.Color(204, 204, 204));
        jTextField245.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField245.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField245.setText("Err %");
        jTextField245.setBorder(null);

        t1ssErrorBlkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssErrorBlkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssErrorBlkRate.setText("0%");
        t1ssErrorBlkRate.setBorder(null);

        t2ssErrorBlkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssErrorBlkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssErrorBlkRate.setText("0%");
        t2ssErrorBlkRate.setBorder(null);

        t2ssr4Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr4Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr4Blk.setText("0");
        t2ssr4Blk.setBorder(null);

        t1ssr4Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr4Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr4Blk.setText("0");
        t1ssr4Blk.setBorder(null);

        jTextField104.setBackground(new java.awt.Color(204, 204, 204));
        jTextField104.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField104.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField104.setText(">");
        jTextField104.setBorder(null);

        jTextField105.setBackground(new java.awt.Color(204, 204, 204));
        jTextField105.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField105.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField105.setText("+");
        jTextField105.setBorder(null);

        t1ssr5Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1ssr5Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1ssr5Blk.setText("0");
        t1ssr5Blk.setBorder(null);

        t2ssr5Blk.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssr5Blk.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssr5Blk.setText("0");
        t2ssr5Blk.setBorder(null);

        t2ssSuccessBlkRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2ssSuccessBlkRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2ssSuccessBlkRate.setText("0%");
        t2ssSuccessBlkRate.setBorder(null);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField88)
                    .addComponent(t1sstotalBlk)
                    .addComponent(t2sstotalBlk))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField93)
                    .addComponent(t1ssr1Blk)
                    .addComponent(t2ssr1Blk))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField95)
                    .addComponent(t1ssr2Blk)
                    .addComponent(t2ssr2Blk))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1ssr3Blk)
                    .addComponent(jTextField100)
                    .addComponent(t2ssr3Blk))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssr4Blk)
                    .addComponent(jTextField104, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1ssr4Blk))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssr5Blk)
                    .addComponent(t1ssr5Blk)
                    .addComponent(jTextField105))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2ssSuccessBlkRate)
                    .addComponent(jTextField98, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1ssSuccessBlkRate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1ssErrorBlkRate)
                    .addComponent(jTextField245)
                    .addComponent(t2ssErrorBlkRate)))
            .addComponent(jTextField9)
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jTextField88, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1sstotalBlk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2sstotalBlk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jTextField93, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1ssr1Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2ssr1Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jTextField95, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1ssr2Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2ssr2Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField98, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField245, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField100, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField104, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField105, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1ssr3Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssr4Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssr5Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssSuccessBlkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1ssErrorBlkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(t2ssr3Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2ssErrorBlkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssr4Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssr5Blk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2ssSuccessBlkRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel17.setBackground(new java.awt.Color(102, 102, 102));
        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel17.setForeground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("  Scoring Skill");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 824, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel41.setBackground(new java.awt.Color(102, 102, 102));
        jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel41.setForeground(new java.awt.Color(51, 51, 51));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("  Non Scoring Skill");

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel47.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField10.setBackground(new java.awt.Color(204, 204, 204));
        jTextField10.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField10.setText("RECEPTION");
        jTextField10.setToolTipText("");
        jTextField10.setBorder(null);

        jTextField109.setBackground(new java.awt.Color(204, 204, 204));
        jTextField109.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField109.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField109.setText("Tot Pt.");
        jTextField109.setBorder(null);
        jTextField109.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField109ActionPerformed(evt);
            }
        });

        t1nsstotalRep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nsstotalRep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nsstotalRep.setText("0");
        t1nsstotalRep.setBorder(null);

        t2nsstotalRep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nsstotalRep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nsstotalRep.setText("0");
        t2nsstotalRep.setBorder(null);

        t2nssr1Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr1Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr1Rep.setText("0");
        t2nssr1Rep.setBorder(null);

        t1nssr1Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr1Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr1Rep.setText("0");
        t1nssr1Rep.setBorder(null);
        t1nssr1Rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1nssr1RepActionPerformed(evt);
            }
        });

        jTextField114.setBackground(new java.awt.Color(204, 204, 204));
        jTextField114.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField114.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField114.setText("-");
        jTextField114.setBorder(null);

        t1nssr2Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr2Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr2Rep.setText("0");
        t1nssr2Rep.setBorder(null);

        jTextField116.setBackground(new java.awt.Color(204, 204, 204));
        jTextField116.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField116.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField116.setText("<");
        jTextField116.setBorder(null);

        t2nssr2Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr2Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr2Rep.setText("0");
        t2nssr2Rep.setBorder(null);

        t1nssSuccessRepRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssSuccessRepRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssSuccessRepRate.setText("0%");
        t1nssSuccessRepRate.setBorder(null);

        jTextField119.setBackground(new java.awt.Color(204, 204, 204));
        jTextField119.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField119.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField119.setText("Succ %");
        jTextField119.setBorder(null);

        t1nssr3Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr3Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr3Rep.setText("0");
        t1nssr3Rep.setBorder(null);
        t1nssr3Rep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1nssr3RepActionPerformed(evt);
            }
        });

        jTextField121.setBackground(new java.awt.Color(204, 204, 204));
        jTextField121.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField121.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField121.setText("@");
        jTextField121.setBorder(null);

        t2nssr3Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr3Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr3Rep.setText("0");
        t2nssr3Rep.setBorder(null);

        jTextField249.setBackground(new java.awt.Color(204, 204, 204));
        jTextField249.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField249.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField249.setText("Err %");
        jTextField249.setBorder(null);

        t1nssErrorRepRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssErrorRepRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssErrorRepRate.setText("0%");
        t1nssErrorRepRate.setBorder(null);

        t2nssErrorRepRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssErrorRepRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssErrorRepRate.setText("0%");
        t2nssErrorRepRate.setBorder(null);

        t2nssr4Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr4Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr4Rep.setText("0");
        t2nssr4Rep.setBorder(null);

        t1nssr4Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr4Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr4Rep.setText("0");
        t1nssr4Rep.setBorder(null);

        jTextField254.setBackground(new java.awt.Color(204, 204, 204));
        jTextField254.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField254.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField254.setText(">");
        jTextField254.setBorder(null);

        jTextField255.setBackground(new java.awt.Color(204, 204, 204));
        jTextField255.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField255.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField255.setText("+");
        jTextField255.setBorder(null);

        t1nssr5Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr5Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr5Rep.setText("0");
        t1nssr5Rep.setBorder(null);

        t2nssr5Rep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr5Rep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr5Rep.setText("0");
        t2nssr5Rep.setBorder(null);

        t2nssSuccessRepRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssSuccessRepRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssSuccessRepRate.setText("0%");
        t2nssSuccessRepRate.setBorder(null);

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField109)
                    .addComponent(t1nsstotalRep)
                    .addComponent(t2nsstotalRep))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField114)
                    .addComponent(t1nssr1Rep)
                    .addComponent(t2nssr1Rep))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField116)
                    .addComponent(t1nssr2Rep)
                    .addComponent(t2nssr2Rep))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1nssr3Rep)
                    .addComponent(jTextField121)
                    .addComponent(t2nssr3Rep))
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssr4Rep)
                    .addComponent(jTextField254, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1nssr4Rep))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssr5Rep)
                    .addComponent(t1nssr5Rep)
                    .addComponent(jTextField255))
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssSuccessRepRate)
                    .addComponent(jTextField119, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1nssSuccessRepRate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1nssErrorRepRate)
                    .addComponent(jTextField249)
                    .addComponent(t2nssErrorRepRate)))
            .addComponent(jTextField10)
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jTextField109, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nsstotalRep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nsstotalRep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jTextField114, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nssr1Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nssr1Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jTextField116, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nssr2Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nssr2Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField119, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField249, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField121, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField254, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField255, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1nssr3Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssr4Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssr5Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssSuccessRepRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssErrorRepRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(t2nssr3Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2nssErrorRepRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssr4Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssr5Rep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssSuccessRepRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, 0))
        );

        jPanel48.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField11.setBackground(new java.awt.Color(204, 204, 204));
        jTextField11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField11.setText("TF -");
        jTextField11.setBorder(null);

        jTextField259.setBackground(new java.awt.Color(204, 204, 204));
        jTextField259.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField259.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField259.setText("Tot Pt.");
        jTextField259.setBorder(null);

        t1nssTF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssTF.setText("0");
        t1nssTF.setBorder(null);

        t2nssTF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssTF.setText("0");
        t2nssTF.setBorder(null);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField259, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(t1nssTF, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(t2nssTF)
            .addComponent(jTextField11)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField259, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(t1nssTF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(t2nssTF)
                .addGap(0, 0, 0))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1NSSName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1NSSName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1NSSName.setText("Maharashtra");
        team1NSSName.setBorder(null);

        team2NSSName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2NSSName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2NSSName.setText("Andhra Pradesh");
        team2NSSName.setBorder(null);

        jTextField14.setBackground(new java.awt.Color(204, 204, 204));
        jTextField14.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField14.setText("TEAM");
        jTextField14.setBorder(null);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(team1NSSName, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(team2NSSName, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addComponent(jTextField14)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(team1NSSName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(team2NSSName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel49.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField262.setBackground(new java.awt.Color(204, 204, 204));
        jTextField262.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField262.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField262.setText("SET");
        jTextField262.setBorder(null);

        jTextField263.setBackground(new java.awt.Color(204, 204, 204));
        jTextField263.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField263.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField263.setText("Tot Pt.");
        jTextField263.setBorder(null);
        jTextField263.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField263ActionPerformed(evt);
            }
        });

        t1nsstotalSet.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nsstotalSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nsstotalSet.setText("0");
        t1nsstotalSet.setBorder(null);

        t2nsstotalSet.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nsstotalSet.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nsstotalSet.setText("0");
        t2nsstotalSet.setBorder(null);

        t2nssr1Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr1Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr1Set.setText("0");
        t2nssr1Set.setBorder(null);

        t1nssr1Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr1Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr1Set.setText("0");
        t1nssr1Set.setBorder(null);
        t1nssr1Set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1nssr1SetActionPerformed(evt);
            }
        });

        jTextField268.setBackground(new java.awt.Color(204, 204, 204));
        jTextField268.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField268.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField268.setText("-");
        jTextField268.setBorder(null);

        t1nssr2Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr2Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr2Set.setText("0");
        t1nssr2Set.setBorder(null);

        jTextField270.setBackground(new java.awt.Color(204, 204, 204));
        jTextField270.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField270.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField270.setText("<");
        jTextField270.setBorder(null);

        t2nssr2Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr2Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr2Set.setText("0");
        t2nssr2Set.setBorder(null);

        t1nssSuccessSetRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssSuccessSetRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssSuccessSetRate.setText("0%");
        t1nssSuccessSetRate.setBorder(null);

        jTextField273.setBackground(new java.awt.Color(204, 204, 204));
        jTextField273.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField273.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField273.setText("Succ %");
        jTextField273.setBorder(null);

        t1nssr3Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr3Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr3Set.setText("0");
        t1nssr3Set.setBorder(null);
        t1nssr3Set.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1nssr3SetActionPerformed(evt);
            }
        });

        jTextField275.setBackground(new java.awt.Color(204, 204, 204));
        jTextField275.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField275.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField275.setText("@");
        jTextField275.setBorder(null);

        t2nssr3Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr3Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr3Set.setText("0");
        t2nssr3Set.setBorder(null);

        jTextField277.setBackground(new java.awt.Color(204, 204, 204));
        jTextField277.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField277.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField277.setText("Err %");
        jTextField277.setBorder(null);

        t1nssErrorSetRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssErrorSetRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssErrorSetRate.setText("0%");
        t1nssErrorSetRate.setBorder(null);

        t2nssErrorSetRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssErrorSetRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssErrorSetRate.setText("0%");
        t2nssErrorSetRate.setBorder(null);

        t2nssr4Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr4Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr4Set.setText("0");
        t2nssr4Set.setBorder(null);

        t1nssr4Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr4Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr4Set.setText("0");
        t1nssr4Set.setBorder(null);

        jTextField282.setBackground(new java.awt.Color(204, 204, 204));
        jTextField282.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField282.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField282.setText(">");
        jTextField282.setBorder(null);

        jTextField283.setBackground(new java.awt.Color(204, 204, 204));
        jTextField283.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField283.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField283.setText("+");
        jTextField283.setBorder(null);

        t1nssr5Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr5Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr5Set.setText("0");
        t1nssr5Set.setBorder(null);

        t2nssr5Set.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr5Set.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr5Set.setText("0");
        t2nssr5Set.setBorder(null);

        t2nssSuccessSetRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssSuccessSetRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssSuccessSetRate.setText("0%");
        t2nssSuccessSetRate.setBorder(null);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField263, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(t1nsstotalSet)
                    .addComponent(t2nsstotalSet))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField268)
                    .addComponent(t1nssr1Set)
                    .addComponent(t2nssr1Set))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField270)
                    .addComponent(t1nssr2Set)
                    .addComponent(t2nssr2Set))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1nssr3Set)
                    .addComponent(jTextField275)
                    .addComponent(t2nssr3Set))
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssr4Set)
                    .addComponent(jTextField282, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1nssr4Set))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssr5Set)
                    .addComponent(t1nssr5Set)
                    .addComponent(jTextField283))
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssSuccessSetRate)
                    .addComponent(jTextField273, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1nssSuccessSetRate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1nssErrorSetRate)
                    .addComponent(jTextField277)
                    .addComponent(t2nssErrorSetRate)))
            .addComponent(jTextField262)
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addComponent(jTextField262, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jTextField263, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nsstotalSet, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nsstotalSet, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jTextField268, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nssr1Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nssr1Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jTextField270, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nssr2Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nssr2Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField273, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField277, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField275, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField282, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField283, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel49Layout.createSequentialGroup()
                                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1nssr3Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssr4Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssr5Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssSuccessSetRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssErrorSetRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(t2nssr3Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel49Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2nssErrorSetRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssr4Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssr5Set, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssSuccessSetRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, 0))
        );

        jPanel50.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextField287.setBackground(new java.awt.Color(204, 204, 204));
        jTextField287.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField287.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField287.setText("DEFENCE");
        jTextField287.setBorder(null);

        jTextField288.setBackground(new java.awt.Color(204, 204, 204));
        jTextField288.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField288.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField288.setText("Tot Pt.");
        jTextField288.setBorder(null);
        jTextField288.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField288ActionPerformed(evt);
            }
        });

        t1nsstotalDef.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nsstotalDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nsstotalDef.setText("0");
        t1nsstotalDef.setBorder(null);

        t2nsstotalDef.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nsstotalDef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nsstotalDef.setText("0");
        t2nsstotalDef.setBorder(null);

        t2nssr1Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr1Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr1Def.setText("0");
        t2nssr1Def.setBorder(null);

        t1nssr1Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr1Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr1Def.setText("0");
        t1nssr1Def.setBorder(null);
        t1nssr1Def.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1nssr1DefActionPerformed(evt);
            }
        });

        jTextField293.setBackground(new java.awt.Color(204, 204, 204));
        jTextField293.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField293.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField293.setText("-");
        jTextField293.setBorder(null);

        t1nssr2Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr2Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr2Def.setText("0");
        t1nssr2Def.setBorder(null);

        jTextField295.setBackground(new java.awt.Color(204, 204, 204));
        jTextField295.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField295.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField295.setText("<");
        jTextField295.setBorder(null);

        t2nssr2Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr2Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr2Def.setText("0");
        t2nssr2Def.setBorder(null);

        t1nssSuccessDefRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssSuccessDefRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssSuccessDefRate.setText("0%");
        t1nssSuccessDefRate.setBorder(null);

        jTextField298.setBackground(new java.awt.Color(204, 204, 204));
        jTextField298.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField298.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField298.setText("Succ %");
        jTextField298.setBorder(null);

        t1nssr3Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr3Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr3Def.setText("0");
        t1nssr3Def.setBorder(null);
        t1nssr3Def.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t1nssr3DefActionPerformed(evt);
            }
        });

        jTextField300.setBackground(new java.awt.Color(204, 204, 204));
        jTextField300.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField300.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField300.setText("@");
        jTextField300.setBorder(null);

        t2nssr3Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr3Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr3Def.setText("0");
        t2nssr3Def.setBorder(null);

        jTextField302.setBackground(new java.awt.Color(204, 204, 204));
        jTextField302.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField302.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField302.setText("Err %");
        jTextField302.setBorder(null);

        t1nssErrorDefRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssErrorDefRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssErrorDefRate.setText("0%");
        t1nssErrorDefRate.setBorder(null);

        t2nssErrorDefRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssErrorDefRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssErrorDefRate.setText("0%");
        t2nssErrorDefRate.setBorder(null);

        t2nssr4Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr4Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr4Def.setText("0");
        t2nssr4Def.setBorder(null);

        t1nssr4Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr4Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr4Def.setText("0");
        t1nssr4Def.setBorder(null);

        jTextField307.setBackground(new java.awt.Color(204, 204, 204));
        jTextField307.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField307.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField307.setText(">");
        jTextField307.setBorder(null);

        jTextField308.setBackground(new java.awt.Color(204, 204, 204));
        jTextField308.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField308.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField308.setText("+");
        jTextField308.setBorder(null);

        t1nssr5Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t1nssr5Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t1nssr5Def.setText("0");
        t1nssr5Def.setBorder(null);

        t2nssr5Def.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssr5Def.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssr5Def.setText("0");
        t2nssr5Def.setBorder(null);

        t2nssSuccessDefRate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        t2nssSuccessDefRate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t2nssSuccessDefRate.setText("0%");
        t2nssSuccessDefRate.setBorder(null);

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField288)
                    .addComponent(t1nsstotalDef)
                    .addComponent(t2nsstotalDef))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField293)
                    .addComponent(t1nssr1Def)
                    .addComponent(t2nssr1Def))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField295)
                    .addComponent(t1nssr2Def)
                    .addComponent(t2nssr2Def))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1nssr3Def)
                    .addComponent(jTextField300)
                    .addComponent(t2nssr3Def))
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssr4Def)
                    .addComponent(jTextField307, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1nssr4Def))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssr5Def)
                    .addComponent(t1nssr5Def)
                    .addComponent(jTextField308))
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t2nssSuccessDefRate)
                    .addComponent(jTextField298, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(t1nssSuccessDefRate, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t1nssErrorDefRate)
                    .addComponent(jTextField302)
                    .addComponent(t2nssErrorDefRate)))
            .addComponent(jTextField287)
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addComponent(jTextField287, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jTextField288, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nsstotalDef, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nsstotalDef, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jTextField293, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nssr1Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nssr1Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jTextField295, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t1nssr2Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(t2nssr2Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField298, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField302, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField300, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField307, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField308, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t1nssr3Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssr4Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssr5Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssSuccessDefRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t1nssErrorDefRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(t2nssr3Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t2nssErrorDefRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssr4Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssr5Def, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t2nssSuccessDefRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        jTextField15.setBackground(new java.awt.Color(204, 204, 204));
        jTextField15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField15.setText("TEAM");
        jTextField15.setBorder(null);

        team1Sum.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1Sum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1Sum.setText("Maharashtra");
        team1Sum.setBorder(null);

        team2Sum.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2Sum.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2Sum.setText("Andhra Pradesh");
        team2Sum.setBorder(null);

        jTextField168.setBackground(new java.awt.Color(204, 204, 204));
        jTextField168.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField168.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField168.setText("SERVICE");
        jTextField168.setBorder(null);

        jTextField169.setBackground(new java.awt.Color(204, 204, 204));
        jTextField169.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField169.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField169.setText("SUCCESS");
        jTextField169.setBorder(null);

        team1SumSerSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumSerSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumSerSuccess.setText("0%");
        team1SumSerSuccess.setBorder(null);

        team2SumSerSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSerSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSerSuccess.setText("0%");
        team2SumSerSuccess.setBorder(null);

        team1SumSerError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumSerError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumSerError.setText("0%");
        team1SumSerError.setBorder(null);
        team1SumSerError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                team1SumSerErrorActionPerformed(evt);
            }
        });

        jTextField173.setBackground(new java.awt.Color(204, 204, 204));
        jTextField173.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField173.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField173.setText("ERROR");
        jTextField173.setBorder(null);

        team2SumSerError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSerError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSerError.setText("0%");
        team2SumSerError.setBorder(null);

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField168, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField169)
                            .addComponent(team1SumSerSuccess)
                            .addComponent(team2SumSerSuccess))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField173)
                            .addComponent(team1SumSerError)
                            .addComponent(team2SumSerError))))
                .addGap(0, 0, 0))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jTextField168, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jTextField169, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumSerSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addComponent(jTextField173, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumSerError, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(team2SumSerSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(team2SumSerError)))
        );

        jTextField175.setBackground(new java.awt.Color(204, 204, 204));
        jTextField175.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField175.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField175.setText("ATTACK");
        jTextField175.setBorder(null);

        jTextField176.setBackground(new java.awt.Color(204, 204, 204));
        jTextField176.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField176.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField176.setText("SUCCESS");
        jTextField176.setBorder(null);

        team1SumAttackSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumAttackSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumAttackSuccess.setText("0%");
        team1SumAttackSuccess.setBorder(null);

        team2SumAttackSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumAttackSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumAttackSuccess.setText("0%");
        team2SumAttackSuccess.setBorder(null);

        team1SumAttackError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumAttackError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumAttackError.setText("0%");
        team1SumAttackError.setBorder(null);

        jTextField180.setBackground(new java.awt.Color(204, 204, 204));
        jTextField180.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField180.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField180.setText("ERROR");
        jTextField180.setBorder(null);

        team2SumAttackError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumAttackError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumAttackError.setText("0%");
        team2SumAttackError.setBorder(null);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField175, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField176)
                            .addComponent(team1SumAttackSuccess)
                            .addComponent(team2SumAttackSuccess))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField180)
                            .addComponent(team1SumAttackError)
                            .addComponent(team2SumAttackError))))
                .addGap(0, 0, 0))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jTextField175, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jTextField176, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumAttackSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jTextField180, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumAttackError, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(team2SumAttackSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(team2SumAttackError)))
        );

        jPanel33.setBackground(new java.awt.Color(255, 255, 255));

        jTextField182.setBackground(new java.awt.Color(204, 204, 204));
        jTextField182.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField182.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField182.setText("BLOCK");
        jTextField182.setBorder(null);

        jTextField183.setBackground(new java.awt.Color(204, 204, 204));
        jTextField183.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField183.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField183.setText("SUCCESS");
        jTextField183.setBorder(null);

        team1SumBlkSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumBlkSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumBlkSuccess.setText("0%");
        team1SumBlkSuccess.setBorder(null);

        team2SumBlkSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumBlkSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumBlkSuccess.setText("0%");
        team2SumBlkSuccess.setBorder(null);

        team1SumBlkError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumBlkError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumBlkError.setText("0%");
        team1SumBlkError.setBorder(null);

        jTextField187.setBackground(new java.awt.Color(204, 204, 204));
        jTextField187.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField187.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField187.setText("ERROR");
        jTextField187.setBorder(null);
        jTextField187.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField187ActionPerformed(evt);
            }
        });

        team2SumBlkError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumBlkError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumBlkError.setText("0%");
        team2SumBlkError.setBorder(null);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField182, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField183)
                            .addComponent(team1SumBlkSuccess)
                            .addComponent(team2SumBlkSuccess))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField187)
                            .addComponent(team1SumBlkError)
                            .addComponent(team2SumBlkError))))
                .addGap(0, 0, 0))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jTextField182, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jTextField183, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumBlkSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel33Layout.createSequentialGroup()
                        .addComponent(jTextField187, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumBlkError, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(team2SumBlkSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(team2SumBlkError))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));

        jTextField189.setBackground(new java.awt.Color(204, 204, 204));
        jTextField189.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField189.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField189.setText("RECEPTION");
        jTextField189.setBorder(null);

        jTextField190.setBackground(new java.awt.Color(204, 204, 204));
        jTextField190.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField190.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField190.setText("SUCCESS");
        jTextField190.setBorder(null);

        team1SumRcpSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumRcpSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumRcpSuccess.setText("0%");
        team1SumRcpSuccess.setBorder(null);

        team2SumRcpSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumRcpSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumRcpSuccess.setText("0%");
        team2SumRcpSuccess.setBorder(null);

        team1SumRcpError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumRcpError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumRcpError.setText("0%");
        team1SumRcpError.setBorder(null);

        jTextField194.setBackground(new java.awt.Color(204, 204, 204));
        jTextField194.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField194.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField194.setText("ERROR");
        jTextField194.setBorder(null);

        team2SumRcpError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumRcpError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumRcpError.setText("0%");
        team2SumRcpError.setBorder(null);

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField189, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField190)
                            .addComponent(team1SumRcpSuccess)
                            .addComponent(team2SumRcpSuccess))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField194)
                            .addComponent(team1SumRcpError)
                            .addComponent(team2SumRcpError))))
                .addGap(0, 0, 0))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addComponent(jTextField189, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jTextField190, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumRcpSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(jTextField194, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumRcpError, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(team2SumRcpSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(team2SumRcpError))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));

        jTextField196.setBackground(new java.awt.Color(204, 204, 204));
        jTextField196.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField196.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField196.setText("SET");
        jTextField196.setBorder(null);

        jTextField197.setBackground(new java.awt.Color(204, 204, 204));
        jTextField197.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField197.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField197.setText("SUCCESS");
        jTextField197.setBorder(null);

        team1SumSetSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumSetSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumSetSuccess.setText("0%");
        team1SumSetSuccess.setBorder(null);

        team2SumSetSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSetSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSetSuccess.setText("0%");
        team2SumSetSuccess.setBorder(null);

        team1SumSetError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumSetError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumSetError.setText("0%");
        team1SumSetError.setBorder(null);

        jTextField201.setBackground(new java.awt.Color(204, 204, 204));
        jTextField201.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField201.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField201.setText("ERROR");
        jTextField201.setBorder(null);

        team2SumSetError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSetError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSetError.setText("0%");
        team2SumSetError.setBorder(null);

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField196, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField197)
                            .addComponent(team1SumSetSuccess)
                            .addComponent(team2SumSetSuccess))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField201)
                            .addComponent(team1SumSetError)
                            .addComponent(team2SumSetError))))
                .addGap(0, 0, 0))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addComponent(jTextField196, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jTextField197, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumSetSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel35Layout.createSequentialGroup()
                        .addComponent(jTextField201, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumSetError, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(team2SumSetSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(team2SumSetError))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        jTextField203.setBackground(new java.awt.Color(204, 204, 204));
        jTextField203.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField203.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField203.setText("DEFENCE");
        jTextField203.setToolTipText("");
        jTextField203.setBorder(null);

        jTextField204.setBackground(new java.awt.Color(204, 204, 204));
        jTextField204.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField204.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField204.setText("SUCCESS");
        jTextField204.setBorder(null);

        team1SumDefSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumDefSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumDefSuccess.setText("0%");
        team1SumDefSuccess.setBorder(null);

        team2SumDefSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumDefSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumDefSuccess.setText("0%");
        team2SumDefSuccess.setBorder(null);

        team1SumDefError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumDefError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumDefError.setText("0%");
        team1SumDefError.setBorder(null);

        jTextField208.setBackground(new java.awt.Color(204, 204, 204));
        jTextField208.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField208.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField208.setText("ERROR");
        jTextField208.setBorder(null);

        team2SumDefError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumDefError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumDefError.setText("0%");
        team2SumDefError.setBorder(null);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField203, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField204)
                            .addComponent(team1SumDefSuccess)
                            .addComponent(team2SumDefSuccess))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField208)
                            .addComponent(team1SumDefError)
                            .addComponent(team2SumDefError))))
                .addGap(0, 0, 0))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addComponent(jTextField203, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jTextField204, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumDefSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addComponent(jTextField208, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1SumDefError, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(team2SumDefSuccess, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(team2SumDefError))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(team1Sum)
                    .addComponent(team2Sum, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                    .addComponent(jTextField15))
                .addGap(0, 0, 0)
                .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(team1Sum, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(team2Sum, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel36, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel33, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setForeground(new java.awt.Color(102, 102, 102));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("  Summary of Success and Error");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 647, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbSetDetails.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbSetDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"I", "-", "-", "-", "-"},
                {"II", "-", "-", "-", "-"},
                {"III", "-", "-", "-", "-"},
                {"IV", "-", "-", "-", "-"},
                {"V", "-", "-", "-", "-"}
            },
            new String [] {
                "SET", "DURATION", "TOTAL RALLIES", "TOTAL TIMEOUT", "SCORE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSetDetails.setRowHeight(20);
        tbSetDetails.setShowHorizontalLines(false);
        jScrollPane1.setViewportView(tbSetDetails);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel22.setBackground(new java.awt.Color(204, 204, 204));
        jPanel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1WLName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team1WLName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1WLName.setText("Maharashtra");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel22Layout.createSequentialGroup()
                    .addComponent(team1WLName, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 30, Short.MAX_VALUE)))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
            .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(team1WLName, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel22.setText("Total Win Score");

        tbTeam1Win.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbTeam1Win.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"I", "0", "0", "0", "0", "0"},
                {"II", "0", "0", "0", "0", "0"},
                {"III", "0", "0", "0", "0", "0"},
                {"IV", "0", "0", "0", "0", "0"},
                {"V", "0", "8", "0", "0", "0"}
            },
            new String [] {
                "SET No", "Tot Pt.", "SRV", "BLK", "ATK", "OP +"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTeam1Win.setRowHeight(20);
        tbTeam1Win.setShowHorizontalLines(false);
        jScrollPane6.setViewportView(tbTeam1Win);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Total Loss Score");

        tbTeam1Loss.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbTeam1Loss.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"I", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"II", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"III", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"IV", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"V", "0", "0", "0", "0", "0", "0", "0", "8"}
            },
            new String [] {
                "SET No", "Tot Pt.", "SRV", "BLK", "ATK", "REC", "SET", "DEF", "TF -"
            }
        ));
        tbTeam1Loss.setRowHeight(20);
        tbTeam1Loss.setShowHorizontalLines(false);
        jScrollPane7.setViewportView(tbTeam1Loss);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        team1playerDetailsPan.setBackground(new java.awt.Color(255, 255, 255));

        team1PlayerTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1PlayerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", "-"},
                {"-", "- ", "-", "-", "-", "-", null}
            },
            new String [] {
                "C. No", "Player", "Set 1", "Set 2", "Set 3", "Set 4", "Set 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        team1PlayerTable.setRowHeight(20);
        team1PlayerTable.setShowHorizontalLines(false);
        team1PlayerTable.setShowVerticalLines(false);
        jScrollPane5.setViewportView(team1PlayerTable);

        javax.swing.GroupLayout team1playerDetailsPanLayout = new javax.swing.GroupLayout(team1playerDetailsPan);
        team1playerDetailsPan.setLayout(team1playerDetailsPanLayout);
        team1playerDetailsPanLayout.setHorizontalGroup(
            team1playerDetailsPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        team1playerDetailsPanLayout.setVerticalGroup(
            team1playerDetailsPanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(team1playerDetailsPanLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setText("Players");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(team1playerDetailsPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 514, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(team1playerDetailsPan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(11, 11, 11))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel42.setBackground(new java.awt.Color(204, 204, 204));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team2WLName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team2WLName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2WLName.setText("Andhra Pradesh");

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel42Layout.createSequentialGroup()
                    .addComponent(team2WLName, javax.swing.GroupLayout.PREFERRED_SIZE, 968, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 30, Short.MAX_VALUE)))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
            .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(team2WLName, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
        );

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("Total Win Score");

        tbTeam2Win.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbTeam2Win.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"I", "0", "0", "0", "0", "0"},
                {"II", "0", "0", "0", "0", "0"},
                {"III", "0", "0", "0", "0", "0"},
                {"IV", "0", "0", "0", "0", "0"},
                {"V", "0", "0", "0", "0", "8"}
            },
            new String [] {
                "SET No", "Tot Pt.", "SRV", "BLK", "ATK", "OP +"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTeam2Win.setRowHeight(20);
        tbTeam2Win.setShowHorizontalLines(false);
        jScrollPane8.setViewportView(tbTeam2Win);

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel27.setText("Total Loss Score");

        tbTeam2Loss.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tbTeam2Loss.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"I", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"II", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"III", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"IV", "0", "0", "0", "0", "0", "0", "0", "0"},
                {"V", "0", "0", "0", "0", "0", "0", "0", "8"}
            },
            new String [] {
                "SET No", "Tot Pt.", "SRV", "BLK", "ATK", "REC", "SET", "DEF", "TF -"
            }
        ));
        tbTeam2Loss.setRowHeight(20);
        tbTeam2Loss.setShowHorizontalLines(false);
        jScrollPane9.setViewportView(tbTeam2Loss);

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        team2PlayerTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2PlayerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", "-"},
                {"-", "-", "-", "-", "-", "-", null}
            },
            new String [] {
                "C. No", "Player", "Set 1", "Set 2", "Set 3", "Set 4", "Set 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        team2PlayerTable.setRowHeight(20);
        team2PlayerTable.setShowHorizontalLines(false);
        team2PlayerTable.setShowVerticalLines(false);
        jScrollPane4.setViewportView(team2PlayerTable);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setText("Players");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 514, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        team1label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team1label.setText("Maharashtra");

        team1WonPointsLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team1WonPointsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team1WonPointsLabel.setText("-");

        team2label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team2label.setText("Andhra Pradesh");

        team2WonPointLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team2WonPointLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2WonPointLabel.setText("-");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(team1label, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(team1WonPointsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(team2label, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(team2WonPointLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(team1label)
                    .addComponent(team1WonPointsLabel)
                    .addComponent(team2label)
                    .addComponent(team2WonPointLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panMatchReport1Layout = new javax.swing.GroupLayout(panMatchReport1);
        panMatchReport1.setLayout(panMatchReport1Layout);
        panMatchReport1Layout.setHorizontalGroup(
            panMatchReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panMatchReport1Layout.createSequentialGroup()
                .addGroup(panMatchReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE))
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panMatchReport1Layout.setVerticalGroup(
            panMatchReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMatchReport1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(panMatchReport1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panMatchReport1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Tot Pt : ");

        jLabel12.setText("Total Points");

        jLabel14.setText("SRV : ");

        jLabel15.setText("Service");

        jLabel16.setText("BLK : ");

        jLabel17.setText("Block");

        jLabel19.setText("Attack");

        jLabel29.setText("ATK : ");

        jLabel30.setText("REC : ");

        jLabel31.setText("Reception");

        jLabel32.setText("Defence");

        jLabel33.setText("DEF : ");

        jLabel34.setText("Excellent");

        jLabel35.setText("+ : ");

        jLabel36.setText("> : ");

        jLabel37.setText("Above Average");

        jLabel38.setText("@ : ");

        jLabel39.setText("Average");

        jLabel40.setText("< : ");

        jLabel41.setText("Below Average");

        jLabel42.setText("-  : ");

        jLabel43.setText("Poor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel17))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel19))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel31)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel43))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel40)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel41))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel37))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel39)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(jLabel19)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(jLabel34))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(jLabel37))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel41))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel30)
                        .addComponent(jLabel31))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel42)
                        .addComponent(jLabel43)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panMatchReport1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panMatchReport1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField187ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField187ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField187ActionPerformed

    private void t1ssr1SerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ssr1SerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ssr1SerActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void t1ssr3SerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ssr3SerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ssr3SerActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void t1ssr1AttkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ssr1AttkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ssr1AttkActionPerformed

    private void t1ssr3AttkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ssr3AttkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ssr3AttkActionPerformed

    private void jTextField88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField88ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField88ActionPerformed

    private void t1ssr1BlkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ssr1BlkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ssr1BlkActionPerformed

    private void t1ssr3BlkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1ssr3BlkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1ssr3BlkActionPerformed

    private void jTextField109ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField109ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField109ActionPerformed

    private void t1nssr1RepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1nssr1RepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1nssr1RepActionPerformed

    private void t1nssr3RepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1nssr3RepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1nssr3RepActionPerformed

    private void jTextField263ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField263ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField263ActionPerformed

    private void t1nssr1SetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1nssr1SetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1nssr1SetActionPerformed

    private void t1nssr3SetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1nssr3SetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1nssr3SetActionPerformed

    private void jTextField288ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField288ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField288ActionPerformed

    private void t1nssr1DefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1nssr1DefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1nssr1DefActionPerformed

    private void t1nssr3DefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t1nssr3DefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t1nssr3DefActionPerformed

    private void team1SumSerErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_team1SumSerErrorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_team1SumSerErrorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField100;
    private javax.swing.JTextField jTextField104;
    private javax.swing.JTextField jTextField105;
    private javax.swing.JTextField jTextField109;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField114;
    private javax.swing.JTextField jTextField116;
    private javax.swing.JTextField jTextField119;
    private javax.swing.JTextField jTextField121;
    private javax.swing.JTextField jTextField122;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField168;
    private javax.swing.JTextField jTextField169;
    private javax.swing.JTextField jTextField173;
    private javax.swing.JTextField jTextField175;
    private javax.swing.JTextField jTextField176;
    private javax.swing.JTextField jTextField180;
    private javax.swing.JTextField jTextField182;
    private javax.swing.JTextField jTextField183;
    private javax.swing.JTextField jTextField187;
    private javax.swing.JTextField jTextField189;
    private javax.swing.JTextField jTextField190;
    private javax.swing.JTextField jTextField194;
    private javax.swing.JTextField jTextField196;
    private javax.swing.JTextField jTextField197;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField201;
    private javax.swing.JTextField jTextField203;
    private javax.swing.JTextField jTextField204;
    private javax.swing.JTextField jTextField208;
    private javax.swing.JTextField jTextField242;
    private javax.swing.JTextField jTextField245;
    private javax.swing.JTextField jTextField249;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField254;
    private javax.swing.JTextField jTextField255;
    private javax.swing.JTextField jTextField259;
    private javax.swing.JTextField jTextField262;
    private javax.swing.JTextField jTextField263;
    private javax.swing.JTextField jTextField268;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField270;
    private javax.swing.JTextField jTextField273;
    private javax.swing.JTextField jTextField275;
    private javax.swing.JTextField jTextField277;
    private javax.swing.JTextField jTextField282;
    private javax.swing.JTextField jTextField283;
    private javax.swing.JTextField jTextField287;
    private javax.swing.JTextField jTextField288;
    private javax.swing.JTextField jTextField293;
    private javax.swing.JTextField jTextField295;
    private javax.swing.JTextField jTextField298;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField300;
    private javax.swing.JTextField jTextField302;
    private javax.swing.JTextField jTextField307;
    private javax.swing.JTextField jTextField308;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField83;
    private javax.swing.JTextField jTextField84;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextField93;
    private javax.swing.JTextField jTextField95;
    private javax.swing.JTextField jTextField98;
    private javax.swing.JLabel match;
    private javax.swing.JLabel matchDate;
    private javax.swing.JPanel panMatchReport1;
    private javax.swing.JLabel phase;
    private javax.swing.JTextField t1nssErrorDefRate;
    private javax.swing.JTextField t1nssErrorRepRate;
    private javax.swing.JTextField t1nssErrorSetRate;
    private javax.swing.JTextField t1nssSuccessDefRate;
    private javax.swing.JTextField t1nssSuccessRepRate;
    private javax.swing.JTextField t1nssSuccessSetRate;
    private javax.swing.JTextField t1nssTF;
    private javax.swing.JTextField t1nssr1Def;
    private javax.swing.JTextField t1nssr1Rep;
    private javax.swing.JTextField t1nssr1Set;
    private javax.swing.JTextField t1nssr2Def;
    private javax.swing.JTextField t1nssr2Rep;
    private javax.swing.JTextField t1nssr2Set;
    private javax.swing.JTextField t1nssr3Def;
    private javax.swing.JTextField t1nssr3Rep;
    private javax.swing.JTextField t1nssr3Set;
    private javax.swing.JTextField t1nssr4Def;
    private javax.swing.JTextField t1nssr4Rep;
    private javax.swing.JTextField t1nssr4Set;
    private javax.swing.JTextField t1nssr5Def;
    private javax.swing.JTextField t1nssr5Rep;
    private javax.swing.JTextField t1nssr5Set;
    private javax.swing.JTextField t1nsstotalDef;
    private javax.swing.JTextField t1nsstotalRep;
    private javax.swing.JTextField t1nsstotalSet;
    private javax.swing.JTextField t1ssErrorAttkRate;
    private javax.swing.JTextField t1ssErrorBlkRate;
    private javax.swing.JTextField t1ssErrorSerRate;
    private javax.swing.JTextField t1ssOP;
    private javax.swing.JTextField t1ssSuccessAttkRate;
    private javax.swing.JTextField t1ssSuccessBlkRate;
    private javax.swing.JTextField t1ssSuccessSerRate;
    private javax.swing.JTextField t1ssr1Attk;
    private javax.swing.JTextField t1ssr1Blk;
    private javax.swing.JTextField t1ssr1Ser;
    private javax.swing.JTextField t1ssr2Attk;
    private javax.swing.JTextField t1ssr2Blk;
    private javax.swing.JTextField t1ssr2Ser;
    private javax.swing.JTextField t1ssr3Attk;
    private javax.swing.JTextField t1ssr3Blk;
    private javax.swing.JTextField t1ssr3Ser;
    private javax.swing.JTextField t1ssr4Attk;
    private javax.swing.JTextField t1ssr4Blk;
    private javax.swing.JTextField t1ssr4Ser;
    private javax.swing.JTextField t1ssr5Attk;
    private javax.swing.JTextField t1ssr5Blk;
    private javax.swing.JTextField t1ssr5Ser;
    private javax.swing.JTextField t1sstotalAttk;
    private javax.swing.JTextField t1sstotalBlk;
    private javax.swing.JTextField t1sstotalSer;
    private javax.swing.JTextField t2nssErrorDefRate;
    private javax.swing.JTextField t2nssErrorRepRate;
    private javax.swing.JTextField t2nssErrorSetRate;
    private javax.swing.JTextField t2nssSuccessDefRate;
    private javax.swing.JTextField t2nssSuccessRepRate;
    private javax.swing.JTextField t2nssSuccessSetRate;
    private javax.swing.JTextField t2nssTF;
    private javax.swing.JTextField t2nssr1Def;
    private javax.swing.JTextField t2nssr1Rep;
    private javax.swing.JTextField t2nssr1Set;
    private javax.swing.JTextField t2nssr2Def;
    private javax.swing.JTextField t2nssr2Rep;
    private javax.swing.JTextField t2nssr2Set;
    private javax.swing.JTextField t2nssr3Def;
    private javax.swing.JTextField t2nssr3Rep;
    private javax.swing.JTextField t2nssr3Set;
    private javax.swing.JTextField t2nssr4Def;
    private javax.swing.JTextField t2nssr4Rep;
    private javax.swing.JTextField t2nssr4Set;
    private javax.swing.JTextField t2nssr5Def;
    private javax.swing.JTextField t2nssr5Rep;
    private javax.swing.JTextField t2nssr5Set;
    private javax.swing.JTextField t2nsstotalDef;
    private javax.swing.JTextField t2nsstotalRep;
    private javax.swing.JTextField t2nsstotalSet;
    private javax.swing.JTextField t2ssErrorAttkRate;
    private javax.swing.JTextField t2ssErrorBlkRate;
    private javax.swing.JTextField t2ssErrorSerRate;
    private javax.swing.JTextField t2ssOP;
    private javax.swing.JTextField t2ssSuccessAttkRate;
    private javax.swing.JTextField t2ssSuccessBlkRate;
    private javax.swing.JTextField t2ssSuccessSerRate;
    private javax.swing.JTextField t2ssr1Attk;
    private javax.swing.JTextField t2ssr1Blk;
    private javax.swing.JTextField t2ssr1Ser;
    private javax.swing.JTextField t2ssr2Attk;
    private javax.swing.JTextField t2ssr2Blk;
    private javax.swing.JTextField t2ssr2Ser;
    private javax.swing.JTextField t2ssr3Attk;
    private javax.swing.JTextField t2ssr3Blk;
    private javax.swing.JTextField t2ssr3Ser;
    private javax.swing.JTextField t2ssr4Attk;
    private javax.swing.JTextField t2ssr4Blk;
    private javax.swing.JTextField t2ssr4Ser;
    private javax.swing.JTextField t2ssr5Attk;
    private javax.swing.JTextField t2ssr5Blk;
    private javax.swing.JTextField t2ssr5Ser;
    private javax.swing.JTextField t2sstotalAttk;
    private javax.swing.JTextField t2sstotalBlk;
    private javax.swing.JTextField t2sstotalSer;
    private javax.swing.JTable tbSetDetails;
    private javax.swing.JTable tbTeam1Loss;
    private javax.swing.JTable tbTeam1Win;
    private javax.swing.JTable tbTeam2Loss;
    private javax.swing.JTable tbTeam2Win;
    private javax.swing.JTextField team1NSSName;
    private javax.swing.JTable team1PlayerTable;
    private javax.swing.JTextField team1SSName;
    private javax.swing.JTextField team1Sum;
    private javax.swing.JTextField team1SumAttackError;
    private javax.swing.JTextField team1SumAttackSuccess;
    private javax.swing.JTextField team1SumBlkError;
    private javax.swing.JTextField team1SumBlkSuccess;
    private javax.swing.JTextField team1SumDefError;
    private javax.swing.JTextField team1SumDefSuccess;
    private javax.swing.JTextField team1SumRcpError;
    private javax.swing.JTextField team1SumRcpSuccess;
    private javax.swing.JTextField team1SumSerError;
    private javax.swing.JTextField team1SumSerSuccess;
    private javax.swing.JTextField team1SumSetError;
    private javax.swing.JTextField team1SumSetSuccess;
    private javax.swing.JLabel team1WLName;
    private javax.swing.JLabel team1WonPointsLabel;
    private javax.swing.JLabel team1label;
    private javax.swing.JPanel team1playerDetailsPan;
    private javax.swing.JTextField team2NSSName;
    private javax.swing.JTable team2PlayerTable;
    private javax.swing.JTextField team2SSName;
    private javax.swing.JTextField team2Sum;
    private javax.swing.JTextField team2SumAttackError;
    private javax.swing.JTextField team2SumAttackSuccess;
    private javax.swing.JTextField team2SumBlkError;
    private javax.swing.JTextField team2SumBlkSuccess;
    private javax.swing.JTextField team2SumDefError;
    private javax.swing.JTextField team2SumDefSuccess;
    private javax.swing.JTextField team2SumRcpError;
    private javax.swing.JTextField team2SumRcpSuccess;
    private javax.swing.JTextField team2SumSerError;
    private javax.swing.JTextField team2SumSerSuccess;
    private javax.swing.JTextField team2SumSetError;
    private javax.swing.JTextField team2SumSetSuccess;
    private javax.swing.JLabel team2WLName;
    private javax.swing.JLabel team2WonPointLabel;
    private javax.swing.JLabel team2label;
    // End of variables declaration//GEN-END:variables
}
