/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.bean.SetTimeout;
import com.vollyball.bean.VollyCourtCoordinateBean;
import com.vollyball.controller.Controller;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class RallyDao {

    DbUtil db = new DbUtil();
    Connection con;
    MatchDao matchDao = new MatchDao();

    public int insertRally(RallyEvaluation re) {
        int id = 0;
        int rid = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rally"));
            ps.setInt(1, re.getRallyNum());
            ps.setInt(2, re.getHomeScore());
            ps.setInt(3, re.getOpponentScore());
            ps.setString(4, re.getStartTime());
            ps.setString(5, re.getEndTime());
            ps.setInt(6, re.getMatchEvaluationId());
            ps.setInt(7, re.getStartby());
            ps.setInt(8, re.getWonby());

            id = ps.executeUpdate();

            if (id != 0) {
                PreparedStatement ps4 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.score"));
                ps4.setInt(1, re.getHomeScore());
                ps4.setInt(2, re.getOpponentScore());
                ps4.setInt(3, re.getMatchEvaluationId());
                ps4.executeUpdate();

                PreparedStatement ps5 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.plusminus"));
                ps5.setInt(1, re.getOp());
                ps5.setInt(2, re.getTf());
                ps5.setInt(3, re.getMatchEvaluationId());
                ps5.executeUpdate();

                PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.rally.id"));
                ResultSet rs = ps3.executeQuery();
                while (rs.next()) {
                    rid = rs.getInt(1);
                }

                PreparedStatement ps6 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallyRotationorder"));
                int i = 1;
                for (Map.Entry<Integer, Player> entry : re.rallyPositionMap.entrySet()) {
                    if (entry.getKey() != 7) {
                        Player pl = entry.getValue();
                        ps6.setInt(i, pl.getId());
                        i++;
                    }
                }
                ps6.setInt(7, rid);
                ps6.executeUpdate();

                for (RallyEvaluationSkillScore ress : re.getRallyEvaluationSkillScore()) {
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallydetails"));
                    ps1.setInt(1, ress.getSkillId());
                    ps1.setInt(2, ress.getPlayerId());
                    ps1.setInt(3, ress.getScore());
                    ps1.setInt(4, rid);
                    ps1.setInt(5, ress.getOrderNum());
                    id = ps1.executeUpdate();

                    if (ress.isIsDetailed()) {
                        if (id != 0) {
                            int rallyDetailsId = 0;
                            PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.rally.details.id"));
                            ResultSet rs7 = ps7.executeQuery();
                            while (rs7.next()) {
                                rallyDetailsId = rs7.getInt(1);
                            }

                            for (Map.Entry<Integer, String> entry : ress.getDetailsValues().entrySet()) {
                                int key = entry.getKey();
                                String value = entry.getValue();

                                PreparedStatement ps8 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallydetails.criteria"));
                                ps8.setInt(1, key);
                                ps8.setString(2, value);
                                ps8.setInt(3, rallyDetailsId);
                                ps8.executeUpdate();

                            }

                        }
                    }
                }

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rid;

    }

    public int updateRally(RallyEvaluation re, List<Integer> updated) {

        List<Integer> original = new ArrayList<>();

        int id = 0;
        int rid = re.getId();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.rally"));
            ps.setInt(1, re.getHomeScore());
            ps.setInt(2, re.getOpponentScore());
            ps.setInt(3, re.getStartby());
            ps.setInt(4, re.getWonby());
            ps.setInt(5, re.getId());
            id = ps.executeUpdate();

            if (id != 0) {

                PreparedStatement ps6 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.rallyRotationorder"));
                int j = 1;
                for (Map.Entry<Integer, Player> entry : re.rallyPositionMap.entrySet()) {
                    if (entry.getKey() != 7) {
                        Player pl = entry.getValue();
                        ps6.setInt(j, pl.getId());
                        j++;
                    }
                }
                ps6.setInt(7, re.getId());
                ps6.executeUpdate();

                PreparedStatement ps5 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.plusminus"));
                ps5.setInt(1, re.getOp());
                ps5.setInt(2, re.getTf());
                ps5.setInt(3, re.getMatchEvaluationId());
                ps5.executeUpdate();

                PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetails"));
                ps3.setInt(1, rid);
                ResultSet rs1 = ps3.executeQuery();
                while (rs1.next()) {
                    original.add(rs1.getInt(1));
                }

                ArrayList<Integer> remove = new ArrayList<Integer>(original);
                remove.removeAll(updated);

                for (RallyEvaluationSkillScore ress : re.getRallyEvaluationSkillScore()) {

                    if (ress.getId() == 0) {
                        PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallydetails"));
                        ps1.setInt(1, ress.getSkillId());
                        ps1.setInt(2, ress.getPlayerId());
                        ps1.setInt(3, ress.getScore());
                        ps1.setInt(4, rid);
                        ps1.setInt(5, ress.getOrderNum());
                        ps1.executeUpdate();

                        if (ress.isIsDetailed()) {
                            if (id != 0) {
                                int rallyDetailsId = 0;
                                PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.rally.id"));
                                ResultSet rs7 = ps7.executeQuery();
                                while (rs7.next()) {
                                    rallyDetailsId = rs7.getInt(1);
                                }

                                for (Map.Entry<Integer, String> entry : ress.getDetailsValues().entrySet()) {
                                    int key = entry.getKey();
                                    String value = entry.getValue();

                                    PreparedStatement ps8 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallydetails.criteria"));
                                    ps8.setInt(1, key);
                                    ps8.setString(2, value);
                                    ps8.setInt(3, rallyDetailsId);
                                    ps8.executeQuery();

                                }

                            }
                        }

                    } else {
                        PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.rallydetails"));
                        ps1.setInt(1, ress.getSkillId());
                        ps1.setInt(2, ress.getPlayerId());
                        ps1.setInt(3, ress.getScore());
                        ps1.setInt(4, ress.getOrderNum());
                        ps1.setInt(5, ress.getId());
                        ps1.setInt(6, rid);
                        ps1.executeUpdate();

                        if (ress.isIsDetailed()) {
                            if (id != 0) {
                                int rallyDetailsId = ress.getId();

                                for (Map.Entry<Integer, String> entry : ress.getDetailsValues().entrySet()) {
                                    int key = entry.getKey();
                                    String value = entry.getValue();
                                    int rallyDetailsCriteriaId = 0;
                                    PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetails.criteria.id"));
                                    ps7.setInt(1, rallyDetailsId);
                                    ps7.setInt(2, key);
                                    ResultSet rs7 = ps7.executeQuery();
                                    while (rs7.next()) {
                                        rallyDetailsCriteriaId = rs7.getInt(1);
                                    }

                                    if (rallyDetailsCriteriaId == 0) {
                                        PreparedStatement ps8 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.rallydetails.criteria"));
                                        ps8.setInt(1, key);
                                        ps8.setString(2, value);
                                        ps8.setInt(3, rallyDetailsId);
                                        ps8.executeUpdate();
                                    } else {
                                        PreparedStatement ps8 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.rallydetails.criteria"));
                                        ps8.setInt(1, key);
                                        ps8.setString(2, value);
                                        ps8.setInt(3, rallyDetailsCriteriaId);
                                        ps8.executeUpdate();

                                    }

                                }

                            }
                        }

                    }
                }

                for (int i : remove) {
                    PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("delete.rallydetails.criteria"));
                    ps7.setInt(1, i);
                    ps7.executeUpdate();

                    PreparedStatement ps4 = this.con.prepareStatement(CommonUtil.getResourceProperty("delete.rallydetails"));
                    ps4.setInt(1, i);
                    ps4.executeUpdate();
                }
            }

            if (!re.getScoreSubtracted().equalsIgnoreCase("None")) {

                updateSubstitutionAndTimeoutForRally(re.getId(), re.getMatchEvaluationId(), re.getHomeScore(), re.getOpponentScore());

                List<RallyEvaluation> reList = getRallyGreaterThan(re.getRallyNum(), re.getMatchEvaluationId());
                for (RallyEvaluation rallyEvaluation : reList) {
                    int homeScore = rallyEvaluation.getHomeScore();
                    int opponentScore = rallyEvaluation.getOpponentScore();
                    if (re.getScoreSubtracted().equalsIgnoreCase("Home")) {
                        if (homeScore != 0) {
                            homeScore--;
                        }
                        opponentScore++;
                    } else {
                        homeScore++;
                        if (opponentScore != 0) {
                            opponentScore--;
                        }
                    }
                    this.con = db.getConnection();
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.rally"));
                    ps1.setInt(1, homeScore);
                    ps1.setInt(2, opponentScore);
                    ps1.setInt(3, rallyEvaluation.getStartby());
                    ps1.setInt(4, rallyEvaluation.getWonby());
                    ps1.setInt(5, rallyEvaluation.getId());
                    id = ps1.executeUpdate();

                    if (id != 0) {
                        updateSubstitutionAndTimeoutForRally(rallyEvaluation.getId(), rallyEvaluation.getMatchEvaluationId(), homeScore, opponentScore);
                    }
                }
            }
            updateMatchScore(re.getMatchEvaluationId());
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return rid;

    }

    public void updateMatchScore(int evaluationId) {
        try {
            int homeScore = 0, opponentScore = 0;
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latestrally"));
            ps.setInt(1, evaluationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                homeScore = rs.getInt(2);
                opponentScore = rs.getInt(3);
            }

            PreparedStatement ps4 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.score"));
            ps4.setInt(1, homeScore);
            ps4.setInt(2, opponentScore);
            ps4.setInt(3, evaluationId);
            ps4.executeUpdate();

            if (homeScore >= 5 || opponentScore >= 5) {
                List<Integer> arr = new ArrayList();
                arr.add(homeScore);
                arr.add(opponentScore);
                int max = Collections.max(arr);
                int min = Collections.min(arr);
                if ((max - min) >= 2) {
                    if (max == homeScore) {

                        matchDao.updateMatchSetWonBy(Controller.panMatchSet.teamEvaluateId, Controller.panMatchSet.matchEvaluationId);
                    } else {

                        matchDao.updateMatchSetWonBy(Controller.panMatchSet.opponentId, Controller.panMatchSet.matchEvaluationId);
                    }

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(RallyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateSubstitutionAndTimeoutForRally(int rallyId, int matchEvaluationId, int homeScore, int opponentScore) {
        List<SetTimeout> setTimeout = matchDao.getListOfTimeoutForRally(rallyId, matchEvaluationId);

        if (!setTimeout.isEmpty()) {
            for (SetTimeout s : setTimeout) {
                s.setScoreA(homeScore);
                s.setScoreB(opponentScore);
                matchDao.updateSetTimeout(s);
            }
        }

        List<SetSubstitution> setSubstitutionpoint1 = matchDao.getMatchSetPointsforRally(rallyId, matchEvaluationId, 1);

        if (!setSubstitutionpoint1.isEmpty()) {
            for (SetSubstitution s : setSubstitutionpoint1) {
                String score = homeScore + " : " + opponentScore;
                matchDao.updateSubstitutionPoints(score, s.getId(), 1);
            }
        }

        List<SetSubstitution> setSubstitutionpoint2 = matchDao.getMatchSetPointsforRally(rallyId, matchEvaluationId, 2);

        if (!setSubstitutionpoint2.isEmpty()) {
            for (SetSubstitution s : setSubstitutionpoint2) {
                String score = homeScore + " : " + opponentScore;
                matchDao.updateSubstitutionPoints(score, s.getId(), 2);
            }
        }

    }

    public RallyEvaluation getRally(int rallyNum, int evaluationId, int teamId) {
        RallyEvaluation re = new RallyEvaluation();
        List<RallyEvaluationSkillScore> rallyEvaluationSkillScore = new ArrayList<>();
        List<Player> playerList = new ArrayList<>();
        LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<Integer, Player>();
        TeamDao teamDao = new TeamDao();

        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rally"));
            ps.setInt(1, rallyNum);
            ps.setInt(2, evaluationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                re.setId(rs.getInt(1));
                re.setRallyNum(rs.getInt(2));
                re.setHomeScore(rs.getInt(3));
                re.setOpponentScore(rs.getInt(4));
                re.setStartTime(rs.getString(5));
                re.setEndTime(rs.getString(6));
                re.setMatchEvaluationId(rs.getInt(7));
                re.setStartby(rs.getInt(8));
                re.setWonby(rs.getInt(9));
            }
            if (re.getId() != 0) {
                PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rally.ratationorder"));
                ps2.setInt(1, re.getId());
                ResultSet rs2 = ps2.executeQuery();
                while (rs2.next()) {
                    if (teamId == 0) {
                        re.rallyPositionMap.put(1, Controller.panMatchSet.playerMap.get(rs2.getInt(1)));
                        re.rallyPositionMap.put(2, Controller.panMatchSet.playerMap.get(rs2.getInt(2)));
                        re.rallyPositionMap.put(3, Controller.panMatchSet.playerMap.get(rs2.getInt(3)));
                        re.rallyPositionMap.put(4, Controller.panMatchSet.playerMap.get(rs2.getInt(4)));
                        re.rallyPositionMap.put(5, Controller.panMatchSet.playerMap.get(rs2.getInt(5)));
                        re.rallyPositionMap.put(6, Controller.panMatchSet.playerMap.get(rs2.getInt(6)));
                    } else {
                        playerList = teamDao.getTeamPlayers(teamId);
                        for (Player p : playerList) {
                            playerMap.put(p.getId(), p);

                        }

                        re.rallyPositionMap.put(1, playerMap.get(rs2.getInt(1)));
                        re.rallyPositionMap.put(2, playerMap.get(rs2.getInt(2)));
                        re.rallyPositionMap.put(3, playerMap.get(rs2.getInt(3)));
                        re.rallyPositionMap.put(4, playerMap.get(rs2.getInt(4)));
                        re.rallyPositionMap.put(5, playerMap.get(rs2.getInt(5)));
                        re.rallyPositionMap.put(6, playerMap.get(rs2.getInt(6)));

                    }
                }

                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetails"));
                ps1.setInt(1, re.getId());
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    RallyEvaluationSkillScore ress = new RallyEvaluationSkillScore();
                    LinkedHashMap<Integer, String> detailsMap = new LinkedHashMap<>();
                    ress.setId(rs1.getInt(1));
                    ress.setSkillId(rs1.getInt(2));
                    ress.setPlayerId(rs1.getInt(3));
                    ress.setScore(rs1.getInt(4));
                    ress.setRallyId(rs1.getInt(5));

                    PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetails.criteria"));
                    ps3.setInt(1, ress.getId());
                    ResultSet rs3 = ps3.executeQuery();
                    while (rs3.next()) {
                        detailsMap.put(rs3.getInt(2), rs3.getString(3));
                    }
                    ress.setDetailsValues(detailsMap);
                    rallyEvaluationSkillScore.add(ress);
                }
                re.setRallyEvaluationSkillScore(rallyEvaluationSkillScore);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return re;
    }

    public List<RallyEvaluation> getRallyGreaterThan(int rallyNum, int evaluationId) {

        List<RallyEvaluation> reList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallylistgreaterthan"));
            ps.setInt(1, rallyNum);
            ps.setInt(2, evaluationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RallyEvaluation re = new RallyEvaluation();
                re.setId(rs.getInt(1));
                re.setRallyNum(rs.getInt(2));
                re.setHomeScore(rs.getInt(3));
                re.setOpponentScore(rs.getInt(4));
                re.setStartTime(rs.getString(5));
                re.setEndTime(rs.getString(6));
                re.setMatchEvaluationId(rs.getInt(7));
                reList.add(re);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reList;
    }

    public List<RallyEvaluation> getRalliesList(int matchevaluationId) {
        List<RallyEvaluation> list = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallylist"));
            ps.setInt(1, matchevaluationId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RallyEvaluation r = new RallyEvaluation();
                r.setId(rs.getInt(1));
                r.setRallyNum(rs.getInt(2));
                list.add(r);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public LinkedHashMap<Integer, Player> getLatestRallyRotationOrder(int matchevaluationId, int evaluationTeamId) {
        LinkedHashMap<Integer, Player> rallyPositionMap = new LinkedHashMap<>();
        LinkedHashMap<Integer, Player> playerMap = new LinkedHashMap<Integer, Player>();
        TeamDao teamDao = new TeamDao();
        int rallyId = 0;
        try {
            List<Player> playerListL = teamDao.getTeamPlayers(evaluationTeamId);
            for (Player p : playerListL) {

                playerMap.put(p.getId(), p);
            }

            this.con = db.getConnection();

            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latestrally"));
            ps.setInt(1, matchevaluationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                rallyId = rs.getInt(1);
            }

            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rally.ratationorder"));
            ps1.setInt(1, rallyId);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {

                rallyPositionMap.put(1, playerMap.get(rs1.getInt(1)));
                rallyPositionMap.put(2, playerMap.get(rs1.getInt(2)));
                rallyPositionMap.put(3, playerMap.get(rs1.getInt(3)));
                rallyPositionMap.put(4, playerMap.get(rs1.getInt(4)));
                rallyPositionMap.put(5, playerMap.get(rs1.getInt(5)));
                rallyPositionMap.put(6, playerMap.get(rs1.getInt(6)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(RallyDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return rallyPositionMap;
    }

    public VollyCourtCoordinateBean getCordinates(String type, int from, int to) {
        VollyCourtCoordinateBean v = new VollyCourtCoordinateBean();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.vollycourtcoordinates"));
            ps.setString(1, type);
            ps.setInt(2, from);
            ps.setInt(3, to);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                v.setX1(rs.getInt(1));
                v.setY1(rs.getInt(2));
                v.setX2(rs.getInt(3));
                v.setY2(rs.getInt(4));
                v.setX3(rs.getInt(5));
                v.setY3(rs.getInt(6));
                v.setX4(rs.getInt(7));
                v.setY4(rs.getInt(8));
                v.setSkill(rs.getString(9));
                v.setFrom(rs.getInt(10));
                v.setTo(rs.getInt(11));

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return v;
    }

    public int getRallyCountByEvaluationId(int evalId) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rally.count"));
            ps.setInt(1, evalId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public List<RallyEvaluationSkillScore> getRallyDetailsOfPlayer(int playerId, int skill, int rating, int matchId) {
        List<RallyEvaluationSkillScore> rallyDetailsList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = null;

            if (rating == 0) {
                if (matchId == 0) {
                    ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetialsByPlayerSkill"));
                    ps1.setInt(1, playerId);
                    ps1.setInt(2, skill);
                } else {
                    ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetailsByPlayerSkillMatch"));
                    ps1.setInt(1, playerId);
                    ps1.setInt(2, skill);
                    ps1.setInt(3, matchId);
                }
            } else {
                if (matchId == 0) {
                    ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetialsByPlayerSkillbyrating"));
                    ps1.setInt(1, playerId);
                    ps1.setInt(2, skill);
                    ps1.setInt(3, rating);
                } else {
                    ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetailsByPlayerSkillMatchRating"));
                    ps1.setInt(1, playerId);
                    ps1.setInt(2, skill);
                    ps1.setInt(3, matchId);
                    ps1.setInt(4, rating);
                }
            }
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                RallyEvaluationSkillScore ress = new RallyEvaluationSkillScore();
                LinkedHashMap<Integer, String> detailsMap = new LinkedHashMap<>();
                ress.setId(rs1.getInt(1));
                ress.setSkillId(rs1.getInt(2));
                ress.setPlayerId(rs1.getInt(3));
                ress.setScore(rs1.getInt(4));
                ress.setRallyId(rs1.getInt(5));

                PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.rallydetails.criteria"));
                ps3.setInt(1, ress.getId());
                ResultSet rs3 = ps3.executeQuery();
                while (rs3.next()) {
                    detailsMap.put(rs3.getInt(2), rs3.getString(3));
                }
                ress.setDetailsValues(detailsMap);
                rallyDetailsList.add(ress);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RallyDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rallyDetailsList;
    }

}
