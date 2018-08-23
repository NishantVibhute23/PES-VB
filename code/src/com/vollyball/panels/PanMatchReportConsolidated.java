/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.panels;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.TeamScores;
import com.vollyball.controller.Controller;
import com.vollyball.dao.MatchDao;
import com.vollyball.dao.RallyDao;
import com.vollyball.dao.ReportDao;
import com.vollyball.dao.TeamDao;
import com.vollyball.dao.TeamReportDao;
import com.vollyball.renderer.GroupableTableHeader;
import com.vollyball.renderer.TableHeaderRenderer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

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
        team1Sum.setText(team1SuccessScores.getTeamName());
        team1SumSerSuccess.setText(team1SuccessScores.getServiceRatePerc());
        team1SumAttackSuccess.setText(team1SuccessScores.getAttackRatePerc());
        team1SumBlkSuccess.setText(team1SuccessScores.getBlockRatePerc());
        team1SumRcpSuccess.setText(team1SuccessScores.getReceptionRatePerc());
        team1SumSetSuccess.setText(team1SuccessScores.getSetRatePerc());
        team1SumDefSuccess.setText(team1SuccessScores.getDefenceRatePerc());
        
        team1SumSerError.setText(team1ErrorScores.getServiceRatePerc());
        team1SumAttackError.setText(team1ErrorScores.getAttackRatePerc());
        team1SumBlkError.setText(team1ErrorScores.getBlockRatePerc());
        team1SumRcpError.setText(team1ErrorScores.getReceptionRatePerc());
        team1SumSetError.setText(team1ErrorScores.getSetRatePerc());
        team1SumDefError.setText(team1ErrorScores.getDefenceRatePerc());

        TeamScores team2SuccessScores = trd.getTeamSuccessReportSkillwise(cb, matchId, team2id);
        TeamScores team2ErrorScores = trd.getTeamErrorReportSkillwise(cb, matchId, team1id);

        team2Sum.setText(team2SuccessScores.getTeamName());
        team2SumSerSuccess.setText(team2SuccessScores.getServiceRatePerc());
        team2SumAttackSuccess.setText(team2SuccessScores.getAttackRatePerc());
        team2SumBlkSuccess.setText(team2SuccessScores.getBlockRatePerc());
        team2SumRcpSuccess.setText(team2SuccessScores.getReceptionRatePerc());
        team2SumSetSuccess.setText(team2SuccessScores.getSetRatePerc());
        team2SumDefSuccess.setText(team2SuccessScores.getDefenceRatePerc());
        
        team2SumSerError.setText(team2ErrorScores.getServiceRatePerc());
        team2SumAttackError.setText(team2ErrorScores.getAttackRatePerc());
        team2SumBlkError.setText(team2ErrorScores.getBlockRatePerc());
        team2SumRcpError.setText(team2ErrorScores.getReceptionRatePerc());
        team2SumSetError.setText(team2ErrorScores.getSetRatePerc());
        team2SumDefError.setText(team2ErrorScores.getDefenceRatePerc());
        
        team1SSName.setText(team.getTeam1name());
        team2SSName.setText(team.getTeam2name());
        team1NSSName.setText(team.getTeam1name());
        team2NSSName.setText(team.getTeam2name());
        team1WLName.setText(team.getTeam1name());
        team2WLName.setText(team.getTeam2name());
        
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        JTableHeader header = tbSetDetails.getTableHeader();
        header.setDefaultRenderer(new TableHeaderRenderer(tbSetDetails));
        tbSetDetails.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbSetDetails.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        JTableHeader header1 = tbTeam1Win.getTableHeader();
        header1.setDefaultRenderer(new TableHeaderRenderer(tbTeam1Win));
        tbTeam1Win.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTeam1Win.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JTableHeader header2 = tbTeam1Loss.getTableHeader();
        header2.setDefaultRenderer(new TableHeaderRenderer(tbTeam1Loss));
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
        header3.setDefaultRenderer(new TableHeaderRenderer(tbTeam2Win));
        tbTeam2Win.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTeam2Win.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

        JTableHeader header4 = tbTeam2Loss.getTableHeader();
        header4.setDefaultRenderer(new TableHeaderRenderer(tbTeam2Loss));
        tbTeam2Loss.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);
        tbTeam2Loss.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);
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
                tbSetDetails.setValueAt(countRally, 0, 2);

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
            } else if (i == 2) {
                tbSetDetails.setValueAt(countRally, 1, 2);
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
            } else if (i == 3) {
                tbSetDetails.setValueAt(countRally, 2, 2);
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
            } else if (i == 4) {
                tbSetDetails.setValueAt(countRally, 3, 2);
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
            } else if (i == 5) {
                tbSetDetails.setValueAt(countRally, 4, 2);
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
    
    public void teamPlayerCreateTable(){
        
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
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField122 = new javax.swing.JTextField();
        jTextField123 = new javax.swing.JTextField();
        jTextField124 = new javax.swing.JTextField();
        jTextField55 = new javax.swing.JTextField();
        jTextField56 = new javax.swing.JTextField();
        jTextField57 = new javax.swing.JTextField();
        jTextField58 = new javax.swing.JTextField();
        jTextField59 = new javax.swing.JTextField();
        jTextField60 = new javax.swing.JTextField();
        jTextField61 = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jTextField65 = new javax.swing.JTextField();
        jTextField66 = new javax.swing.JTextField();
        jTextField67 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        team1SSName = new javax.swing.JTextField();
        team2SSName = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jPanel45 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        jTextField62 = new javax.swing.JTextField();
        jTextField63 = new javax.swing.JTextField();
        jTextField64 = new javax.swing.JTextField();
        jTextField68 = new javax.swing.JTextField();
        jTextField69 = new javax.swing.JTextField();
        jTextField70 = new javax.swing.JTextField();
        jTextField71 = new javax.swing.JTextField();
        jTextField72 = new javax.swing.JTextField();
        jTextField73 = new javax.swing.JTextField();
        jTextField77 = new javax.swing.JTextField();
        jTextField78 = new javax.swing.JTextField();
        jTextField79 = new javax.swing.JTextField();
        jTextField80 = new javax.swing.JTextField();
        jTextField242 = new javax.swing.JTextField();
        jTextField243 = new javax.swing.JTextField();
        jTextField244 = new javax.swing.JTextField();
        jTextField81 = new javax.swing.JTextField();
        jTextField82 = new javax.swing.JTextField();
        jTextField83 = new javax.swing.JTextField();
        jTextField84 = new javax.swing.JTextField();
        jTextField85 = new javax.swing.JTextField();
        jTextField86 = new javax.swing.JTextField();
        jTextField87 = new javax.swing.JTextField();
        jPanel46 = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jTextField88 = new javax.swing.JTextField();
        jTextField89 = new javax.swing.JTextField();
        jTextField90 = new javax.swing.JTextField();
        jTextField91 = new javax.swing.JTextField();
        jTextField92 = new javax.swing.JTextField();
        jTextField93 = new javax.swing.JTextField();
        jTextField94 = new javax.swing.JTextField();
        jTextField95 = new javax.swing.JTextField();
        jTextField96 = new javax.swing.JTextField();
        jTextField97 = new javax.swing.JTextField();
        jTextField98 = new javax.swing.JTextField();
        jTextField99 = new javax.swing.JTextField();
        jTextField100 = new javax.swing.JTextField();
        jTextField101 = new javax.swing.JTextField();
        jTextField245 = new javax.swing.JTextField();
        jTextField246 = new javax.swing.JTextField();
        jTextField247 = new javax.swing.JTextField();
        jTextField102 = new javax.swing.JTextField();
        jTextField103 = new javax.swing.JTextField();
        jTextField104 = new javax.swing.JTextField();
        jTextField105 = new javax.swing.JTextField();
        jTextField106 = new javax.swing.JTextField();
        jTextField107 = new javax.swing.JTextField();
        jTextField108 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jPanel47 = new javax.swing.JPanel();
        jTextField10 = new javax.swing.JTextField();
        jTextField109 = new javax.swing.JTextField();
        jTextField110 = new javax.swing.JTextField();
        jTextField111 = new javax.swing.JTextField();
        jTextField112 = new javax.swing.JTextField();
        jTextField113 = new javax.swing.JTextField();
        jTextField114 = new javax.swing.JTextField();
        jTextField115 = new javax.swing.JTextField();
        jTextField116 = new javax.swing.JTextField();
        jTextField117 = new javax.swing.JTextField();
        jTextField118 = new javax.swing.JTextField();
        jTextField119 = new javax.swing.JTextField();
        jTextField120 = new javax.swing.JTextField();
        jTextField121 = new javax.swing.JTextField();
        jTextField248 = new javax.swing.JTextField();
        jTextField249 = new javax.swing.JTextField();
        jTextField250 = new javax.swing.JTextField();
        jTextField251 = new javax.swing.JTextField();
        jTextField252 = new javax.swing.JTextField();
        jTextField253 = new javax.swing.JTextField();
        jTextField254 = new javax.swing.JTextField();
        jTextField255 = new javax.swing.JTextField();
        jTextField256 = new javax.swing.JTextField();
        jTextField257 = new javax.swing.JTextField();
        jTextField258 = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        jTextField11 = new javax.swing.JTextField();
        jTextField259 = new javax.swing.JTextField();
        jTextField260 = new javax.swing.JTextField();
        jTextField261 = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        team1NSSName = new javax.swing.JTextField();
        team2NSSName = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jPanel49 = new javax.swing.JPanel();
        jTextField262 = new javax.swing.JTextField();
        jTextField263 = new javax.swing.JTextField();
        jTextField264 = new javax.swing.JTextField();
        jTextField265 = new javax.swing.JTextField();
        jTextField266 = new javax.swing.JTextField();
        jTextField267 = new javax.swing.JTextField();
        jTextField268 = new javax.swing.JTextField();
        jTextField269 = new javax.swing.JTextField();
        jTextField270 = new javax.swing.JTextField();
        jTextField271 = new javax.swing.JTextField();
        jTextField272 = new javax.swing.JTextField();
        jTextField273 = new javax.swing.JTextField();
        jTextField274 = new javax.swing.JTextField();
        jTextField275 = new javax.swing.JTextField();
        jTextField276 = new javax.swing.JTextField();
        jTextField277 = new javax.swing.JTextField();
        jTextField278 = new javax.swing.JTextField();
        jTextField279 = new javax.swing.JTextField();
        jTextField280 = new javax.swing.JTextField();
        jTextField281 = new javax.swing.JTextField();
        jTextField282 = new javax.swing.JTextField();
        jTextField283 = new javax.swing.JTextField();
        jTextField284 = new javax.swing.JTextField();
        jTextField285 = new javax.swing.JTextField();
        jTextField286 = new javax.swing.JTextField();
        jPanel50 = new javax.swing.JPanel();
        jTextField287 = new javax.swing.JTextField();
        jTextField288 = new javax.swing.JTextField();
        jTextField289 = new javax.swing.JTextField();
        jTextField290 = new javax.swing.JTextField();
        jTextField291 = new javax.swing.JTextField();
        jTextField292 = new javax.swing.JTextField();
        jTextField293 = new javax.swing.JTextField();
        jTextField294 = new javax.swing.JTextField();
        jTextField295 = new javax.swing.JTextField();
        jTextField296 = new javax.swing.JTextField();
        jTextField297 = new javax.swing.JTextField();
        jTextField298 = new javax.swing.JTextField();
        jTextField299 = new javax.swing.JTextField();
        jTextField300 = new javax.swing.JTextField();
        jTextField301 = new javax.swing.JTextField();
        jTextField302 = new javax.swing.JTextField();
        jTextField303 = new javax.swing.JTextField();
        jTextField304 = new javax.swing.JTextField();
        jTextField305 = new javax.swing.JTextField();
        jTextField306 = new javax.swing.JTextField();
        jTextField307 = new javax.swing.JTextField();
        jTextField308 = new javax.swing.JTextField();
        jTextField309 = new javax.swing.JTextField();
        jTextField310 = new javax.swing.JTextField();
        jTextField311 = new javax.swing.JTextField();
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
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
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
        match.setText("Maharashtra vs Andhra Pradesh");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Phase  : ");

        phase.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phase.setText("Semi Final");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Date    :");

        matchDate.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        matchDate.setText("22/08/2018");

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

        jTextField21.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField21.setText("4");
        jTextField21.setBorder(null);

        jTextField22.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField22.setText("4");
        jTextField22.setBorder(null);

        jTextField23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField23.setText("4");
        jTextField23.setBorder(null);

        jTextField24.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField24.setText("4");
        jTextField24.setBorder(null);
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jTextField25.setBackground(new java.awt.Color(204, 204, 204));
        jTextField25.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField25.setText("-");
        jTextField25.setBorder(null);

        jTextField26.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField26.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField26.setText("4");
        jTextField26.setBorder(null);

        jTextField27.setBackground(new java.awt.Color(204, 204, 204));
        jTextField27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField27.setText("<");
        jTextField27.setBorder(null);

        jTextField28.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField28.setText("4");
        jTextField28.setBorder(null);

        jTextField29.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField29.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField29.setText("40%");
        jTextField29.setBorder(null);

        jTextField30.setBackground(new java.awt.Color(204, 204, 204));
        jTextField30.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField30.setText("Succ %");
        jTextField30.setBorder(null);

        jTextField32.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField32.setText("4");
        jTextField32.setBorder(null);
        jTextField32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField32ActionPerformed(evt);
            }
        });

        jTextField33.setBackground(new java.awt.Color(204, 204, 204));
        jTextField33.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField33.setText("@");
        jTextField33.setBorder(null);

        jTextField34.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField34.setText("4");
        jTextField34.setBorder(null);

        jTextField122.setBackground(new java.awt.Color(204, 204, 204));
        jTextField122.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField122.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField122.setText("Err %");
        jTextField122.setBorder(null);

        jTextField123.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField123.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField123.setText("40%");
        jTextField123.setBorder(null);

        jTextField124.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField124.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField124.setText("40%");
        jTextField124.setBorder(null);

        jTextField55.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField55.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField55.setText("4");
        jTextField55.setBorder(null);

        jTextField56.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField56.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField56.setText("4");
        jTextField56.setBorder(null);

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

        jTextField59.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField59.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField59.setText("4");
        jTextField59.setBorder(null);

        jTextField60.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField60.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField60.setText("4");
        jTextField60.setBorder(null);

        jTextField61.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField61.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField61.setText("40%");
        jTextField61.setBorder(null);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jTextField21)
                    .addComponent(jTextField22))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField25)
                    .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField23))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField27, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField26)
                    .addComponent(jTextField28))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField32)
                    .addComponent(jTextField33, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField34))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField55, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField57, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField56))
                .addGap(0, 0, 0)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField60, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jTextField59)
                    .addComponent(jTextField58))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField61)
                    .addComponent(jTextField30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField123)
                    .addComponent(jTextField122, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jTextField124)))
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
                        .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField56, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField123, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField124, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField55, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField60, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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

        jTextField66.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField66.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField66.setText("10");
        jTextField66.setBorder(null);

        jTextField67.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField67.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField67.setText("5");
        jTextField67.setBorder(null);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField7)
            .addComponent(jTextField65, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(jTextField66, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTextField67)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField66, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField67)
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
            .addComponent(team1SSName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(team2SSName)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jTextField62.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField62.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField62.setText("4");
        jTextField62.setBorder(null);

        jTextField63.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField63.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField63.setText("4");
        jTextField63.setBorder(null);

        jTextField64.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField64.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField64.setText("4");
        jTextField64.setBorder(null);

        jTextField68.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField68.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField68.setText("4");
        jTextField68.setBorder(null);
        jTextField68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField68ActionPerformed(evt);
            }
        });

        jTextField69.setBackground(new java.awt.Color(204, 204, 204));
        jTextField69.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField69.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField69.setText("-");
        jTextField69.setBorder(null);

        jTextField70.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField70.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField70.setText("4");
        jTextField70.setBorder(null);

        jTextField71.setBackground(new java.awt.Color(204, 204, 204));
        jTextField71.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField71.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField71.setText("<");
        jTextField71.setBorder(null);

        jTextField72.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField72.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField72.setText("4");
        jTextField72.setBorder(null);

        jTextField73.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField73.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField73.setText("40%");
        jTextField73.setBorder(null);

        jTextField77.setBackground(new java.awt.Color(204, 204, 204));
        jTextField77.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField77.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField77.setText("Succ %");
        jTextField77.setBorder(null);

        jTextField78.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField78.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField78.setText("4");
        jTextField78.setBorder(null);
        jTextField78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField78ActionPerformed(evt);
            }
        });

        jTextField79.setBackground(new java.awt.Color(204, 204, 204));
        jTextField79.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField79.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField79.setText("@");
        jTextField79.setBorder(null);

        jTextField80.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField80.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField80.setText("4");
        jTextField80.setBorder(null);

        jTextField242.setBackground(new java.awt.Color(204, 204, 204));
        jTextField242.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField242.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField242.setText("Err %");
        jTextField242.setBorder(null);

        jTextField243.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField243.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField243.setText("40%");
        jTextField243.setBorder(null);

        jTextField244.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField244.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField244.setText("40%");
        jTextField244.setBorder(null);

        jTextField81.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField81.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField81.setText("4");
        jTextField81.setBorder(null);

        jTextField82.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField82.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField82.setText("4");
        jTextField82.setBorder(null);

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

        jTextField85.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField85.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField85.setText("4");
        jTextField85.setBorder(null);

        jTextField86.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField86.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField86.setText("4");
        jTextField86.setBorder(null);

        jTextField87.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField87.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField87.setText("40%");
        jTextField87.setBorder(null);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField31, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField62, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jTextField63))
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField69)
                    .addComponent(jTextField68, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jTextField64))
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField71, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jTextField70)
                    .addComponent(jTextField72))
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField78)
                    .addComponent(jTextField79, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jTextField80))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField81, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jTextField83, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField82))
                .addGap(0, 0, 0)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField86, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jTextField85)
                    .addComponent(jTextField84))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField87)
                    .addComponent(jTextField77, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField73, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField243)
                    .addComponent(jTextField242, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(jTextField244)))
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
                        .addComponent(jTextField62, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField63, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jTextField69, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField68, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addComponent(jTextField71, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField70, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField72, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jTextField78, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField82, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField85, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField73, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField243, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jTextField80, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel45Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField244, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField81, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField86, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField87, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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

        jTextField89.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField89.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField89.setText("4");
        jTextField89.setBorder(null);

        jTextField90.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField90.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField90.setText("4");
        jTextField90.setBorder(null);

        jTextField91.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField91.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField91.setText("4");
        jTextField91.setBorder(null);

        jTextField92.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField92.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField92.setText("4");
        jTextField92.setBorder(null);
        jTextField92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField92ActionPerformed(evt);
            }
        });

        jTextField93.setBackground(new java.awt.Color(204, 204, 204));
        jTextField93.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField93.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField93.setText("-");
        jTextField93.setBorder(null);

        jTextField94.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField94.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField94.setText("4");
        jTextField94.setBorder(null);

        jTextField95.setBackground(new java.awt.Color(204, 204, 204));
        jTextField95.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField95.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField95.setText("<");
        jTextField95.setBorder(null);

        jTextField96.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField96.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField96.setText("4");
        jTextField96.setBorder(null);

        jTextField97.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField97.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField97.setText("40%");
        jTextField97.setBorder(null);

        jTextField98.setBackground(new java.awt.Color(204, 204, 204));
        jTextField98.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField98.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField98.setText("Succ %");
        jTextField98.setBorder(null);

        jTextField99.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField99.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField99.setText("4");
        jTextField99.setBorder(null);
        jTextField99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField99ActionPerformed(evt);
            }
        });

        jTextField100.setBackground(new java.awt.Color(204, 204, 204));
        jTextField100.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField100.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField100.setText("@");
        jTextField100.setBorder(null);

        jTextField101.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField101.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField101.setText("4");
        jTextField101.setBorder(null);

        jTextField245.setBackground(new java.awt.Color(204, 204, 204));
        jTextField245.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField245.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField245.setText("Err %");
        jTextField245.setBorder(null);

        jTextField246.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField246.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField246.setText("40%");
        jTextField246.setBorder(null);

        jTextField247.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField247.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField247.setText("40%");
        jTextField247.setBorder(null);

        jTextField102.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField102.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField102.setText("4");
        jTextField102.setBorder(null);

        jTextField103.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField103.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField103.setText("4");
        jTextField103.setBorder(null);

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

        jTextField106.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField106.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField106.setText("4");
        jTextField106.setBorder(null);

        jTextField107.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField107.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField107.setText("4");
        jTextField107.setBorder(null);

        jTextField108.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField108.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField108.setText("40%");
        jTextField108.setBorder(null);

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField88, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jTextField89)
                    .addComponent(jTextField90))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField93)
                    .addComponent(jTextField92, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField91))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField95, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField94)
                    .addComponent(jTextField96))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField99)
                    .addComponent(jTextField100, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField101))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField102, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jTextField104, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField103))
                .addGap(0, 0, 0)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField107, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jTextField106)
                    .addComponent(jTextField105))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField108)
                    .addComponent(jTextField98, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jTextField97, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField246)
                    .addComponent(jTextField245, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jTextField247)))
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
                        .addComponent(jTextField89, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField90, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jTextField93, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField92, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField91, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jTextField95, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField94, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField96, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jTextField99, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField103, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField106, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField97, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField246, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jTextField101, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel46Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField247, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField102, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField107, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField108, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 0, 0)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 1445, Short.MAX_VALUE)
                .addContainerGap())
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

        jTextField110.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField110.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField110.setText("4");
        jTextField110.setBorder(null);

        jTextField111.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField111.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField111.setText("4");
        jTextField111.setBorder(null);

        jTextField112.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField112.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField112.setText("4");
        jTextField112.setBorder(null);

        jTextField113.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField113.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField113.setText("4");
        jTextField113.setBorder(null);
        jTextField113.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField113ActionPerformed(evt);
            }
        });

        jTextField114.setBackground(new java.awt.Color(204, 204, 204));
        jTextField114.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField114.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField114.setText("-");
        jTextField114.setBorder(null);

        jTextField115.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField115.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField115.setText("4");
        jTextField115.setBorder(null);

        jTextField116.setBackground(new java.awt.Color(204, 204, 204));
        jTextField116.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField116.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField116.setText("<");
        jTextField116.setBorder(null);

        jTextField117.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField117.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField117.setText("4");
        jTextField117.setBorder(null);

        jTextField118.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField118.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField118.setText("40%");
        jTextField118.setBorder(null);

        jTextField119.setBackground(new java.awt.Color(204, 204, 204));
        jTextField119.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField119.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField119.setText("Succ %");
        jTextField119.setBorder(null);

        jTextField120.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField120.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField120.setText("4");
        jTextField120.setBorder(null);
        jTextField120.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField120ActionPerformed(evt);
            }
        });

        jTextField121.setBackground(new java.awt.Color(204, 204, 204));
        jTextField121.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField121.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField121.setText("@");
        jTextField121.setBorder(null);

        jTextField248.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField248.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField248.setText("4");
        jTextField248.setBorder(null);

        jTextField249.setBackground(new java.awt.Color(204, 204, 204));
        jTextField249.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField249.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField249.setText("Err %");
        jTextField249.setBorder(null);

        jTextField250.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField250.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField250.setText("40%");
        jTextField250.setBorder(null);

        jTextField251.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField251.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField251.setText("40%");
        jTextField251.setBorder(null);

        jTextField252.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField252.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField252.setText("4");
        jTextField252.setBorder(null);

        jTextField253.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField253.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField253.setText("4");
        jTextField253.setBorder(null);

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

        jTextField256.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField256.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField256.setText("4");
        jTextField256.setBorder(null);

        jTextField257.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField257.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField257.setText("4");
        jTextField257.setBorder(null);

        jTextField258.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField258.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField258.setText("40%");
        jTextField258.setBorder(null);

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField109, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jTextField110)
                    .addComponent(jTextField111))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField114)
                    .addComponent(jTextField113, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField112))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField116, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField115)
                    .addComponent(jTextField117))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField120)
                    .addComponent(jTextField121, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField248))
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField252, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField254, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField253))
                .addGap(0, 0, 0)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField257, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField256)
                    .addComponent(jTextField255))
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField258)
                    .addComponent(jTextField119, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jTextField118, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField250)
                    .addComponent(jTextField249, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jTextField251)))
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
                        .addComponent(jTextField110, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField111, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jTextField114, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField113, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField112, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addComponent(jTextField116, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField115, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField117, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jTextField120, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField253, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField256, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField118, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField250, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jTextField248, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel47Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField251, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField252, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField257, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField258, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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

        jTextField260.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField260.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField260.setText("10");
        jTextField260.setBorder(null);

        jTextField261.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField261.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField261.setText("5");
        jTextField261.setBorder(null);

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField11)
            .addComponent(jTextField259, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(jTextField260, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jTextField261)
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField259, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField260, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTextField261)
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
            .addComponent(team1NSSName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(team2NSSName)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jTextField264.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField264.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField264.setText("4");
        jTextField264.setBorder(null);

        jTextField265.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField265.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField265.setText("4");
        jTextField265.setBorder(null);

        jTextField266.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField266.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField266.setText("4");
        jTextField266.setBorder(null);

        jTextField267.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField267.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField267.setText("4");
        jTextField267.setBorder(null);
        jTextField267.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField267ActionPerformed(evt);
            }
        });

        jTextField268.setBackground(new java.awt.Color(204, 204, 204));
        jTextField268.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField268.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField268.setText("-");
        jTextField268.setBorder(null);

        jTextField269.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField269.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField269.setText("4");
        jTextField269.setBorder(null);

        jTextField270.setBackground(new java.awt.Color(204, 204, 204));
        jTextField270.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField270.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField270.setText("<");
        jTextField270.setBorder(null);

        jTextField271.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField271.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField271.setText("4");
        jTextField271.setBorder(null);

        jTextField272.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField272.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField272.setText("40%");
        jTextField272.setBorder(null);

        jTextField273.setBackground(new java.awt.Color(204, 204, 204));
        jTextField273.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField273.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField273.setText("Succ %");
        jTextField273.setBorder(null);

        jTextField274.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField274.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField274.setText("4");
        jTextField274.setBorder(null);
        jTextField274.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField274ActionPerformed(evt);
            }
        });

        jTextField275.setBackground(new java.awt.Color(204, 204, 204));
        jTextField275.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField275.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField275.setText("@");
        jTextField275.setBorder(null);

        jTextField276.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField276.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField276.setText("4");
        jTextField276.setBorder(null);

        jTextField277.setBackground(new java.awt.Color(204, 204, 204));
        jTextField277.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField277.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField277.setText("Err %");
        jTextField277.setBorder(null);

        jTextField278.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField278.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField278.setText("40%");
        jTextField278.setBorder(null);

        jTextField279.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField279.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField279.setText("40%");
        jTextField279.setBorder(null);

        jTextField280.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField280.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField280.setText("4");
        jTextField280.setBorder(null);

        jTextField281.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField281.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField281.setText("4");
        jTextField281.setBorder(null);

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

        jTextField284.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField284.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField284.setText("4");
        jTextField284.setBorder(null);

        jTextField285.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField285.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField285.setText("4");
        jTextField285.setBorder(null);

        jTextField286.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField286.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField286.setText("40%");
        jTextField286.setBorder(null);

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField263, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jTextField264, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField265))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField268)
                    .addComponent(jTextField267, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField266))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField270, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField269)
                    .addComponent(jTextField271))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField274)
                    .addComponent(jTextField275, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jTextField276))
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField280, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField282, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField281))
                .addGap(0, 0, 0)
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField285, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jTextField284)
                    .addComponent(jTextField283))
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField286)
                    .addComponent(jTextField273, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(jTextField272, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField278)
                    .addComponent(jTextField277, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jTextField279)))
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
                        .addComponent(jTextField264, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField265, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jTextField268, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField267, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField266, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel49Layout.createSequentialGroup()
                        .addComponent(jTextField270, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField269, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField271, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jTextField274, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField281, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField284, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField272, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField278, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jTextField276, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel49Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField279, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField280, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField285, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField286, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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

        jTextField289.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField289.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField289.setText("4");
        jTextField289.setBorder(null);

        jTextField290.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField290.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField290.setText("4");
        jTextField290.setBorder(null);

        jTextField291.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField291.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField291.setText("4");
        jTextField291.setBorder(null);

        jTextField292.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField292.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField292.setText("4");
        jTextField292.setBorder(null);
        jTextField292.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField292ActionPerformed(evt);
            }
        });

        jTextField293.setBackground(new java.awt.Color(204, 204, 204));
        jTextField293.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField293.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField293.setText("-");
        jTextField293.setBorder(null);

        jTextField294.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField294.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField294.setText("4");
        jTextField294.setBorder(null);

        jTextField295.setBackground(new java.awt.Color(204, 204, 204));
        jTextField295.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField295.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField295.setText("<");
        jTextField295.setBorder(null);

        jTextField296.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField296.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField296.setText("4");
        jTextField296.setBorder(null);

        jTextField297.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField297.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField297.setText("40%");
        jTextField297.setBorder(null);

        jTextField298.setBackground(new java.awt.Color(204, 204, 204));
        jTextField298.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField298.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField298.setText("Succ %");
        jTextField298.setBorder(null);

        jTextField299.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField299.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField299.setText("4");
        jTextField299.setBorder(null);
        jTextField299.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField299ActionPerformed(evt);
            }
        });

        jTextField300.setBackground(new java.awt.Color(204, 204, 204));
        jTextField300.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField300.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField300.setText("@");
        jTextField300.setBorder(null);

        jTextField301.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField301.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField301.setText("4");
        jTextField301.setBorder(null);

        jTextField302.setBackground(new java.awt.Color(204, 204, 204));
        jTextField302.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField302.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField302.setText("Err %");
        jTextField302.setBorder(null);

        jTextField303.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField303.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField303.setText("40%");
        jTextField303.setBorder(null);

        jTextField304.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField304.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField304.setText("40%");
        jTextField304.setBorder(null);

        jTextField305.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField305.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField305.setText("4");
        jTextField305.setBorder(null);

        jTextField306.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField306.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField306.setText("4");
        jTextField306.setBorder(null);

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

        jTextField309.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField309.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField309.setText("4");
        jTextField309.setBorder(null);

        jTextField310.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField310.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField310.setText("4");
        jTextField310.setBorder(null);

        jTextField311.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField311.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField311.setText("40%");
        jTextField311.setBorder(null);

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createSequentialGroup()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField288, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(jTextField289)
                    .addComponent(jTextField290))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField293)
                    .addComponent(jTextField292, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField291))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField295, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField294)
                    .addComponent(jTextField296))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField299)
                    .addComponent(jTextField300, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jTextField301))
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField305, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jTextField307, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField306))
                .addGap(0, 0, 0)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField310, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(jTextField309)
                    .addComponent(jTextField308))
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField311)
                    .addComponent(jTextField298, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(jTextField297, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField303)
                    .addComponent(jTextField302, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jTextField304)))
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
                        .addComponent(jTextField289, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField290, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jTextField293, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField292, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField291, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jTextField295, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField294, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jTextField296, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(jTextField299, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField306, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField309, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField297, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField303, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, 0)
                                .addComponent(jTextField301, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField304, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField305, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField310, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField311, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
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
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(10, 10, 10)
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
        team1SumSerSuccess.setText("20%");
        team1SumSerSuccess.setBorder(null);

        team2SumSerSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSerSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSerSuccess.setText("20%");
        team2SumSerSuccess.setBorder(null);

        team1SumSerError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumSerError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumSerError.setText("20%");
        team1SumSerError.setBorder(null);

        jTextField173.setBackground(new java.awt.Color(204, 204, 204));
        jTextField173.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField173.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField173.setText("ERROR");
        jTextField173.setBorder(null);

        team2SumSerError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSerError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSerError.setText("20%");
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
        team1SumAttackSuccess.setText("20%");
        team1SumAttackSuccess.setBorder(null);

        team2SumAttackSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumAttackSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumAttackSuccess.setText("20%");
        team2SumAttackSuccess.setBorder(null);

        team1SumAttackError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumAttackError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumAttackError.setText("20%");
        team1SumAttackError.setBorder(null);

        jTextField180.setBackground(new java.awt.Color(204, 204, 204));
        jTextField180.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField180.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField180.setText("ERROR");
        jTextField180.setBorder(null);

        team2SumAttackError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumAttackError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumAttackError.setText("20%");
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
        team1SumBlkSuccess.setText("20%");
        team1SumBlkSuccess.setBorder(null);

        team2SumBlkSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumBlkSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumBlkSuccess.setText("20%");
        team2SumBlkSuccess.setBorder(null);

        team1SumBlkError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumBlkError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumBlkError.setText("20%");
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
        team2SumBlkError.setText("20%");
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
        team1SumRcpSuccess.setText("20%");
        team1SumRcpSuccess.setBorder(null);

        team2SumRcpSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumRcpSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumRcpSuccess.setText("20%");
        team2SumRcpSuccess.setBorder(null);

        team1SumRcpError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumRcpError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumRcpError.setText("20%");
        team1SumRcpError.setBorder(null);

        jTextField194.setBackground(new java.awt.Color(204, 204, 204));
        jTextField194.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField194.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField194.setText("ERROR");
        jTextField194.setBorder(null);

        team2SumRcpError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumRcpError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumRcpError.setText("20%");
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
        team1SumSetSuccess.setText("20%");
        team1SumSetSuccess.setBorder(null);

        team2SumSetSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSetSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSetSuccess.setText("20%");
        team2SumSetSuccess.setBorder(null);

        team1SumSetError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumSetError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumSetError.setText("20%");
        team1SumSetError.setBorder(null);

        jTextField201.setBackground(new java.awt.Color(204, 204, 204));
        jTextField201.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField201.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField201.setText("ERROR");
        jTextField201.setBorder(null);

        team2SumSetError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumSetError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumSetError.setText("20%");
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
        team1SumDefSuccess.setText("20%");
        team1SumDefSuccess.setBorder(null);

        team2SumDefSuccess.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumDefSuccess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumDefSuccess.setText("20%");
        team2SumDefSuccess.setBorder(null);

        team1SumDefError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1SumDefError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team1SumDefError.setText("20%");
        team1SumDefError.setBorder(null);

        jTextField208.setBackground(new java.awt.Color(204, 204, 204));
        jTextField208.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTextField208.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField208.setText("ERROR");
        jTextField208.setBorder(null);

        team2SumDefError.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team2SumDefError.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        team2SumDefError.setText("20%");
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
                {"I", "10:00", "43", "4", "25 : 23"},
                {"II", "10:00", "43", "4", "25 : 23"},
                {"III", "10:00", "43", "4", "25 : 23"},
                {"IV", "10:00", "43", "4", "25 : 23"},
                {"V", "10:00", "43", "4", "25 : 23"}
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
                {"I", "25", "8", "8", "8", "8"},
                {"II", "25", "8", "8", "8", "8"},
                {"III", "25", "8", "8", "8", "8"},
                {"IV", "25", "8", "8", "8", "8"},
                {"V", "25", "8", "8", "8", "8"}
            },
            new String [] {
                "SET No", "Tot Pt.", "SRV", "BLK", "ATK", "OP +"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false
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
                {"I", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"II", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"III", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"IV", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"V", "25", "8", "8", "8", "8", "8", "8", "8"}
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

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        team1PlayerTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        team1PlayerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"01 L", "Player 1", null, null, null, null, null},
                {"02", "Player 2", null, null, null, null, null},
                {"03", "Player 3", null, null, null, null, null},
                {"04", "Player 4", null, null, null, null, null},
                {"05", "Player 5", null, null, null, null, null},
                {"06 L", "Player 6", null, null, null, null, null},
                {"07", "Player 7", null, null, null, null, null},
                {"08", "Player 8", null, null, null, null, null},
                {"09", "Player 9", null, null, null, null, null},
                {"10", "Player 10", null, null, null, null, null},
                {"11", "Player 11", null, null, null, null, null},
                {"12", "Player 12", null, null, null, null, null},
                {"13", "Player 13", null, null, null, null, null},
                {"14", "Player 14", null, null, null, null, null}
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
        jScrollPane3.setViewportView(team1PlayerTable);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                {"I", "25", "8", "8", "8", "8"},
                {"II", "25", "8", "8", "8", "8"},
                {"III", "25", "8", "8", "8", "8"},
                {"IV", "25", "8", "8", "8", "8"},
                {"V", "25", "8", "8", "8", "8"}
            },
            new String [] {
                "SET No", "Tot Pt.", "SRV", "BLK", "ATK", "OP +"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false
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
                {"I", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"II", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"III", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"IV", "25", "8", "8", "8", "8", "8", "8", "8"},
                {"V", "25", "8", "8", "8", "8", "8", "8", "8"}
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
                {"01 L", "Player 1", null, null, null, null, null},
                {"02", "Player 2", null, null, null, null, null},
                {"03", "Player 3", null, null, null, null, null},
                {"04", "Player 4", null, null, null, null, null},
                {"05", "Player 5", null, null, null, null, null},
                {"06", "Player 6", null, null, null, null, null},
                {"07", "Player 7", null, null, null, null, null},
                {"08 L", "Player 8", null, null, null, null, null},
                {"09", "Player 9", null, null, null, null, null},
                {"10", "Player 10", null, null, null, null, null},
                {"11", "Player 11", null, null, null, null, null},
                {"12", "Player 12", null, null, null, null, null},
                {"13", "Player 13", null, null, null, null, null},
                {"14", "Player 14", null, null, null, null, null}
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
        team1WonPointsLabel.setText("3");

        team2label.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team2label.setText("Andhra Pradesh");

        team2WonPointLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        team2WonPointLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        team2WonPointLabel.setText("2");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE))
            .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 1000, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panMatchReport1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField32ActionPerformed

    private void jTextField31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField31ActionPerformed

    private void jTextField68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField68ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField68ActionPerformed

    private void jTextField78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField78ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField78ActionPerformed

    private void jTextField88ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField88ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField88ActionPerformed

    private void jTextField92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField92ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField92ActionPerformed

    private void jTextField99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField99ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField99ActionPerformed

    private void jTextField109ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField109ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField109ActionPerformed

    private void jTextField113ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField113ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField113ActionPerformed

    private void jTextField120ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField120ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField120ActionPerformed

    private void jTextField263ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField263ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField263ActionPerformed

    private void jTextField267ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField267ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField267ActionPerformed

    private void jTextField274ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField274ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField274ActionPerformed

    private void jTextField288ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField288ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField288ActionPerformed

    private void jTextField292ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField292ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField292ActionPerformed

    private void jTextField299ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField299ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField299ActionPerformed

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
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField100;
    private javax.swing.JTextField jTextField101;
    private javax.swing.JTextField jTextField102;
    private javax.swing.JTextField jTextField103;
    private javax.swing.JTextField jTextField104;
    private javax.swing.JTextField jTextField105;
    private javax.swing.JTextField jTextField106;
    private javax.swing.JTextField jTextField107;
    private javax.swing.JTextField jTextField108;
    private javax.swing.JTextField jTextField109;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField110;
    private javax.swing.JTextField jTextField111;
    private javax.swing.JTextField jTextField112;
    private javax.swing.JTextField jTextField113;
    private javax.swing.JTextField jTextField114;
    private javax.swing.JTextField jTextField115;
    private javax.swing.JTextField jTextField116;
    private javax.swing.JTextField jTextField117;
    private javax.swing.JTextField jTextField118;
    private javax.swing.JTextField jTextField119;
    private javax.swing.JTextField jTextField120;
    private javax.swing.JTextField jTextField121;
    private javax.swing.JTextField jTextField122;
    private javax.swing.JTextField jTextField123;
    private javax.swing.JTextField jTextField124;
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
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField242;
    private javax.swing.JTextField jTextField243;
    private javax.swing.JTextField jTextField244;
    private javax.swing.JTextField jTextField245;
    private javax.swing.JTextField jTextField246;
    private javax.swing.JTextField jTextField247;
    private javax.swing.JTextField jTextField248;
    private javax.swing.JTextField jTextField249;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField250;
    private javax.swing.JTextField jTextField251;
    private javax.swing.JTextField jTextField252;
    private javax.swing.JTextField jTextField253;
    private javax.swing.JTextField jTextField254;
    private javax.swing.JTextField jTextField255;
    private javax.swing.JTextField jTextField256;
    private javax.swing.JTextField jTextField257;
    private javax.swing.JTextField jTextField258;
    private javax.swing.JTextField jTextField259;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField260;
    private javax.swing.JTextField jTextField261;
    private javax.swing.JTextField jTextField262;
    private javax.swing.JTextField jTextField263;
    private javax.swing.JTextField jTextField264;
    private javax.swing.JTextField jTextField265;
    private javax.swing.JTextField jTextField266;
    private javax.swing.JTextField jTextField267;
    private javax.swing.JTextField jTextField268;
    private javax.swing.JTextField jTextField269;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField270;
    private javax.swing.JTextField jTextField271;
    private javax.swing.JTextField jTextField272;
    private javax.swing.JTextField jTextField273;
    private javax.swing.JTextField jTextField274;
    private javax.swing.JTextField jTextField275;
    private javax.swing.JTextField jTextField276;
    private javax.swing.JTextField jTextField277;
    private javax.swing.JTextField jTextField278;
    private javax.swing.JTextField jTextField279;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField280;
    private javax.swing.JTextField jTextField281;
    private javax.swing.JTextField jTextField282;
    private javax.swing.JTextField jTextField283;
    private javax.swing.JTextField jTextField284;
    private javax.swing.JTextField jTextField285;
    private javax.swing.JTextField jTextField286;
    private javax.swing.JTextField jTextField287;
    private javax.swing.JTextField jTextField288;
    private javax.swing.JTextField jTextField289;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField290;
    private javax.swing.JTextField jTextField291;
    private javax.swing.JTextField jTextField292;
    private javax.swing.JTextField jTextField293;
    private javax.swing.JTextField jTextField294;
    private javax.swing.JTextField jTextField295;
    private javax.swing.JTextField jTextField296;
    private javax.swing.JTextField jTextField297;
    private javax.swing.JTextField jTextField298;
    private javax.swing.JTextField jTextField299;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField300;
    private javax.swing.JTextField jTextField301;
    private javax.swing.JTextField jTextField302;
    private javax.swing.JTextField jTextField303;
    private javax.swing.JTextField jTextField304;
    private javax.swing.JTextField jTextField305;
    private javax.swing.JTextField jTextField306;
    private javax.swing.JTextField jTextField307;
    private javax.swing.JTextField jTextField308;
    private javax.swing.JTextField jTextField309;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField310;
    private javax.swing.JTextField jTextField311;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField55;
    private javax.swing.JTextField jTextField56;
    private javax.swing.JTextField jTextField57;
    private javax.swing.JTextField jTextField58;
    private javax.swing.JTextField jTextField59;
    private javax.swing.JTextField jTextField60;
    private javax.swing.JTextField jTextField61;
    private javax.swing.JTextField jTextField62;
    private javax.swing.JTextField jTextField63;
    private javax.swing.JTextField jTextField64;
    private javax.swing.JTextField jTextField65;
    private javax.swing.JTextField jTextField66;
    private javax.swing.JTextField jTextField67;
    private javax.swing.JTextField jTextField68;
    private javax.swing.JTextField jTextField69;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField70;
    private javax.swing.JTextField jTextField71;
    private javax.swing.JTextField jTextField72;
    private javax.swing.JTextField jTextField73;
    private javax.swing.JTextField jTextField77;
    private javax.swing.JTextField jTextField78;
    private javax.swing.JTextField jTextField79;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField80;
    private javax.swing.JTextField jTextField81;
    private javax.swing.JTextField jTextField82;
    private javax.swing.JTextField jTextField83;
    private javax.swing.JTextField jTextField84;
    private javax.swing.JTextField jTextField85;
    private javax.swing.JTextField jTextField86;
    private javax.swing.JTextField jTextField87;
    private javax.swing.JTextField jTextField88;
    private javax.swing.JTextField jTextField89;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextField90;
    private javax.swing.JTextField jTextField91;
    private javax.swing.JTextField jTextField92;
    private javax.swing.JTextField jTextField93;
    private javax.swing.JTextField jTextField94;
    private javax.swing.JTextField jTextField95;
    private javax.swing.JTextField jTextField96;
    private javax.swing.JTextField jTextField97;
    private javax.swing.JTextField jTextField98;
    private javax.swing.JTextField jTextField99;
    private javax.swing.JLabel match;
    private javax.swing.JLabel matchDate;
    private javax.swing.JPanel panMatchReport1;
    private javax.swing.JLabel phase;
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
