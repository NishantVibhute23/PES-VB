/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerReportBean;
import com.vollyball.bean.PlayerScores;
import com.vollyball.bean.PlayerSkillScore;
import com.vollyball.bean.Team;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class ReportDao {

    DbUtil db = new DbUtil();
    Connection con;
    DecimalFormat df = new DecimalFormat("##.##%");

    public List<PlayerReportBean> getPlayerReportList(int skill, int compId) {
        List<PlayerReportBean> playerReportList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.skillwiseplayerreport"));
            ps.setInt(1, skill);
            ps.setInt(2, compId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PlayerReportBean cb = new PlayerReportBean();
                cb.setId(rs.getInt(1));
                cb.setName(rs.getString(2));
                cb.setTotal(rs.getInt(3));
                cb.setSuccess(rs.getInt(4));
                cb.setSuccessrate(df.format(((double) rs.getInt(4) / (double) rs.getInt(3))));
                cb.setTeamName(rs.getString(5));
                playerReportList.add(cb);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return playerReportList;
    }

    public List<PlayerReportBean> getMatchWisePlayerReportList(int skill, int compId, int playerId, String type) {
        List<PlayerReportBean> playerReportList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = null;
            if (type.equalsIgnoreCase("player")) {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.skillwiseplayermatchreport"));
            } else {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.skillwiseteammatchreport"));
            }
            ps.setInt(1, skill);
            ps.setInt(2, compId);
            ps.setInt(3, playerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PlayerReportBean cb = new PlayerReportBean();
                cb.setId(rs.getInt(1));
                cb.setName(rs.getString(2));
                cb.setTotal(rs.getInt(3));
                cb.setSuccess(rs.getInt(4));
                cb.setSuccessr(CommonUtil.round((double) rs.getInt(4) / (double) rs.getInt(3) * 100, 2));
                cb.setSuccessrate(df.format(((double) rs.getInt(4) / (double) rs.getInt(3))));
                playerReportList.add(cb);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return playerReportList;
    }

    public PlayerReportBean getMatchWisePlayerReportByMatchList(int skill, int compId, int playerId, int evalId) {
        PlayerReportBean cb = new PlayerReportBean();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = null;

            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.player.match.skillwisereport.by.match"));

            ps.setInt(1, skill);

            ps.setInt(2, playerId);
            ps.setInt(3, evalId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                cb.setId(rs.getInt(1));
                cb.setName(rs.getString(2));
                cb.setTeamName(rs.getString(3));
                cb.setTotal(rs.getInt(4));
                cb.setSuccess(rs.getInt(5));
                cb.setSuccessr(CommonUtil.round((double) rs.getInt(5) / (double) rs.getInt(4) * 100, 2));
                cb.setSuccessrate(df.format(((double) rs.getInt(5) / (double) rs.getInt(4))));

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return cb;
    }

    public List<PlayerReportBean> getTeamReportList(int skill) {
        List<PlayerReportBean> playerReportList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.skillwiseteamreport"));

            ps.setInt(1, skill);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PlayerReportBean cb = new PlayerReportBean();
                cb.setId(rs.getInt(1));
                cb.setName(rs.getString(2));
                cb.setTotal(rs.getInt(3));
                cb.setSuccess(rs.getInt(4));
                cb.setSuccessrate(df.format(((double) rs.getInt(4) / (double) rs.getInt(3))));
                playerReportList.add(cb);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return playerReportList;
    }

    public List<PlayerReportBean> getTeamPlayerReportList(int skill, int compId, int matchId, int teamId) {
        List<PlayerReportBean> playerReportList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.skillwiseplayerMatchTeamreport"));
            ps.setInt(1, skill);
            ps.setInt(2, compId);
            ps.setInt(3, matchId);
            ps.setInt(4, teamId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PlayerReportBean cb = new PlayerReportBean();
                cb.setId(rs.getInt(1));
                cb.setName(rs.getString(2));
                cb.setTotal(rs.getInt(3));
                cb.setSuccess(rs.getInt(4));
                cb.setSuccessrate(df.format(((double) rs.getInt(4) / (double) rs.getInt(3))));
                playerReportList.add(cb);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return playerReportList;
    }

    public PlayerScores getPlayerScores(int competationId, Player player, int evId) {
        PlayerScores p = new PlayerScores();

        try {
            this.con = db.getConnection();
            PreparedStatement ps;
            if (evId == 0) {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.player.scores"));
                ps.setInt(1, competationId);
                ps.setInt(2, player.getId());
            } else {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.player.matchScore"));
                ps.setInt(1, competationId);
                ps.setInt(2, player.getId());
                ps.setInt(3, evId);

            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setPlayerName(rs.getString(2));
                p.setMatchesPlayed(rs.getInt(3));
                p.setTotalService(rs.getInt(4));
                p.setBestService(rs.getInt(5));

                p.setTotalAttack(rs.getInt(6));
                p.setBestAttack(rs.getInt(7));

                p.setTotalBlock(rs.getInt(8));
                p.setBestBlock(rs.getInt(9));

                p.setTotalSet(rs.getInt(10));
                p.setBestSet(rs.getInt(11));

                p.setTotalReception(rs.getInt(12));
                p.setBestReception(rs.getInt(13));

                p.setTotalDefence(rs.getInt(14));
                p.setBestDefence(rs.getInt(15));

                p.setTeamName(rs.getString(16));

            }
            if (p.getId() == 0) {
                p.setId(player.getId());
                p.setPlayerName(player.getName());
                p.setMatchesPlayed(0);
                p.setTotalService(0);
                p.setBestService(0);

                p.setTotalAttack(0);
                p.setBestAttack(0);

                p.setTotalBlock(0);
                p.setBestBlock(0);

                p.setTotalSet(0);
                p.setBestSet(0);

                p.setTotalReception(0);
                p.setBestReception(0);

                p.setTotalDefence(0);
                p.setBestDefence(0);

                p.setTeamName(player.getTeamName());
            }
            p.setServiceRate(p.getTotalService() == 0 ? 0 : (double) p.getBestService() / (double) p.getTotalService());
            p.setServiceRatePerc(p.getServiceRate() == 0 ? "0%" : df.format(p.getServiceRate()));

            p.setAttackRate(p.getTotalAttack() == 0 ? 0 : (double) p.getBestAttack() / (double) p.getTotalAttack());
            p.setAttackRatePerc(p.getAttackRate() == 0 ? "0%" : df.format(p.getAttackRate()));

            p.setBlockRate(p.getTotalBlock() == 0 ? 0 : (double) p.getBestBlock() / (double) p.getTotalBlock());
            p.setBlockRatePerc(p.getBlockRate() == 0 ? "0%" : df.format(p.getBlockRate()));

            p.setSetRate(p.getTotalSet() == 0 ? 0 : (double) p.getBestSet() / (double) p.getTotalSet());
            p.setSetRatePerc(p.getSetRate() == 0 ? "0%" : df.format(p.getSetRate()));

            p.setReceptionRate(p.getTotalReception() == 0 ? 0 : (double) p.getBestReception() / (double) p.getTotalReception());
            p.setReceptionRatePerc(p.getReceptionRate() == 0 ? "0%" : df.format(p.getReceptionRate()));

            p.setDefenceRate(p.getTotalDefence() == 0 ? 0 : (double) p.getBestDefence() / (double) p.getTotalDefence());
            p.setDefenceRatePerc(p.getDefenceRate() == 0 ? "0%" : df.format(p.getDefenceRate()));

            p.setTotalAttempt(p.getTotalService() + p.getTotalAttack() + p.getTotalBlock() + p.getTotalSet() + p.getTotalReception() + p.getTotalDefence());
            p.setBestAttempt(p.getBestService() + p.getBestAttack() + p.getBestBlock() + p.getBestSet() + p.getBestReception() + p.getBestDefence());

            p.setAttemptRate(p.getTotalAttempt() == 0 ? 0 : (double) p.getBestAttempt() / (double) p.getTotalAttempt());
            p.setAttemptRatePerc(p.getAttemptRate() == 0 ? "0%" : df.format(p.getAttemptRate()));

            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.player.matches.played"));
            ps1.setInt(1, p.getId());
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                p.setMatchesPlayed(rs1.getInt(1));
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;

    }

    public PlayerSkillScore getPlayerSkillWiseScoreReport(int compId, int playerId, int skillId, int matchId, int teamId) {
        PlayerSkillScore skill = new PlayerSkillScore();
        try {
            PreparedStatement ps;
            this.con = db.getConnection();
            if (teamId != 0) {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.skillWise.score"));
                ps.setInt(1, compId);
                ps.setInt(2, teamId);
                ps.setInt(3, skillId);
            } else if (matchId == 0) {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.plyer.skillWise.score"));
                ps.setInt(1, compId);
                ps.setInt(2, playerId);
                ps.setInt(3, skillId);
            } else {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.plyer.skillWise.scorebymatch"));
                ps.setInt(1, compId);
                ps.setInt(2, playerId);
                ps.setInt(3, skillId);
                ps.setInt(4, matchId);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                skill.setPlayerId(rs.getInt(1));
                skill.setPlayerName(rs.getString(2));
                skill.setTotalAttempt(rs.getInt(3));
                skill.setOne(rs.getInt(4));
                skill.setTwo(rs.getInt(5));
                skill.setThree(rs.getInt(6));
                skill.setFour(rs.getInt(7));
                skill.setFive(rs.getInt(8));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skill;
    }

    public List<PlayerSkillScore> getPlayerSkillWiseAllScoreReportbyMatch(int compId, int playerId, int skillId) {
        List<PlayerSkillScore> list = new ArrayList<>();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.player.allScoresCountByMatch"));
            ps.setInt(1, skillId);
            ps.setInt(2, compId);
            ps.setInt(3, playerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PlayerSkillScore skill = new PlayerSkillScore();
                skill.setMatchId(rs.getInt(1));
                skill.setMatchName(rs.getString(2));
                skill.setTotalAttempt(rs.getInt(3));
                skill.setFive(rs.getInt(4));
                skill.setFour(rs.getInt(5));
                skill.setThree(rs.getInt(6));
                skill.setTwo(rs.getInt(7));

                skill.setOne(rs.getInt(8));

                skill.setPhase(rs.getString(9));
                list.add(skill);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public PlayerScores getTeamScores(Team team) {
        PlayerScores p = new PlayerScores();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.scores"));
            ps.setInt(1, team.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setPlayerName(rs.getString(2));
                p.setMatchesPlayed(rs.getInt(3));
                p.setTotalService(rs.getInt(4));
                p.setBestService(rs.getInt(5));
                p.setTotalAttack(rs.getInt(6));
                p.setBestAttack(rs.getInt(7));
                p.setTotalBlock(rs.getInt(8));
                p.setBestBlock(rs.getInt(9));
                p.setTotalSet(rs.getInt(10));
                p.setBestSet(rs.getInt(11));
                p.setTotalReception(rs.getInt(12));
                p.setBestReception(rs.getInt(13));
                p.setTotalDefence(rs.getInt(14));
                p.setBestDefence(rs.getInt(15));

            }
            if (p.getId() == 0) {
                p.setId(team.getId());
                p.setPlayerName(team.getName());
                p.setMatchesPlayed(0);
                p.setTotalService(0);
                p.setBestService(0);
                p.setTotalAttack(0);
                p.setBestAttack(0);
                p.setTotalBlock(0);
                p.setBestBlock(0);
                p.setTotalSet(0);
                p.setBestSet(0);
                p.setTotalReception(0);
                p.setBestReception(0);
                p.setTotalDefence(0);
                p.setBestDefence(0);

            }
            p.setServiceRate(p.getTotalService() == 0 ? 0 : (double) p.getBestService() / (double) p.getTotalService());
            p.setServiceRatePerc(p.getServiceRate() == 0 ? "0%" : df.format(p.getServiceRate()));

            p.setAttackRate(p.getTotalAttack() == 0 ? 0 : (double) p.getBestAttack() / (double) p.getTotalAttack());
            p.setAttackRatePerc(p.getAttackRate() == 0 ? "0%" : df.format(p.getAttackRate()));

            p.setBlockRate(p.getTotalBlock() == 0 ? 0 : (double) p.getBestBlock() / (double) p.getTotalBlock());
            p.setBlockRatePerc(p.getBlockRate() == 0 ? "0%" : df.format(p.getBlockRate()));

            p.setSetRate(p.getTotalSet() == 0 ? 0 : (double) p.getBestSet() / (double) p.getTotalSet());
            p.setSetRatePerc(p.getSetRate() == 0 ? "0%" : df.format(p.getSetRate()));

            p.setReceptionRate(p.getTotalReception() == 0 ? 0 : (double) p.getBestReception() / (double) p.getTotalReception());
            p.setReceptionRatePerc(p.getReceptionRate() == 0 ? "0%" : df.format(p.getReceptionRate()));

            p.setDefenceRate(p.getTotalDefence() == 0 ? 0 : (double) p.getBestDefence() / (double) p.getTotalDefence());
            p.setDefenceRatePerc(p.getDefenceRate() == 0 ? "0%" : df.format(p.getDefenceRate()));

            p.setTotalAttempt(p.getTotalService() + p.getTotalAttack() + p.getTotalBlock() + p.getTotalSet() + p.getTotalReception() + p.getTotalDefence());
            p.setBestAttempt(p.getBestService() + p.getBestAttack() + p.getBestBlock() + p.getBestSet() + p.getBestReception() + p.getBestDefence());

            p.setAttemptRate(p.getTotalAttempt() == 0 ? 0 : (double) p.getBestAttempt() / (double) p.getTotalAttempt());
            p.setAttemptRatePerc(p.getAttemptRate() == 0 ? "0%" : df.format(p.getAttemptRate()));

            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.matches.played"));
            ps1.setInt(1, team.getId());

            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                p.setMatchesPlayed(rs1.getInt(1));

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;

    }

    public List<Integer> getTeamEvaluationId(int teamId) {
        List<Integer> id = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.match.evaluationId"));
            ps1.setInt(1, teamId);

            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                id.add(rs1.getInt(1));

            }
            db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public int getTeamEvaluationIdBYMatch(int teamId, int matchId) {
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.match.evaluationId.bymatch"));
            ps1.setInt(1, teamId);
            ps1.setInt(2, matchId);

            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                id = rs1.getInt(1);

            }
            db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public PlayerScores getTeamMatchScores(int id) {
        PlayerScores p = new PlayerScores();

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.match.details"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setPlayerName(rs.getString(2));
                p.setMatchesPlayed(rs.getInt(3));
                p.setTotalService(rs.getInt(4));
                p.setBestService(rs.getInt(5));
                p.setTotalAttack(rs.getInt(6));
                p.setBestAttack(rs.getInt(7));
                p.setTotalBlock(rs.getInt(8));
                p.setBestBlock(rs.getInt(9));
                p.setTotalSet(rs.getInt(10));
                p.setBestSet(rs.getInt(11));
                p.setTotalReception(rs.getInt(12));
                p.setBestReception(rs.getInt(13));
                p.setTotalDefence(rs.getInt(14));
                p.setBestDefence(rs.getInt(15));
            }

            p.setServiceRate(p.getTotalService() == 0 ? 0 : (double) p.getBestService() / (double) p.getTotalService());
            p.setServiceRatePerc(p.getServiceRate() == 0 ? "0%" : df.format(p.getServiceRate()));

            p.setAttackRate(p.getTotalAttack() == 0 ? 0 : (double) p.getBestAttack() / (double) p.getTotalAttack());
            p.setAttackRatePerc(p.getAttackRate() == 0 ? "0%" : df.format(p.getAttackRate()));

            p.setBlockRate(p.getTotalBlock() == 0 ? 0 : (double) p.getBestBlock() / (double) p.getTotalBlock());
            p.setBlockRatePerc(p.getBlockRate() == 0 ? "0%" : df.format(p.getBlockRate()));

            p.setSetRate(p.getTotalSet() == 0 ? 0 : (double) p.getBestSet() / (double) p.getTotalSet());
            p.setSetRatePerc(p.getSetRate() == 0 ? "0%" : df.format(p.getSetRate()));

            p.setReceptionRate(p.getTotalReception() == 0 ? 0 : (double) p.getBestReception() / (double) p.getTotalReception());
            p.setReceptionRatePerc(p.getReceptionRate() == 0 ? "0%" : df.format(p.getReceptionRate()));

            p.setDefenceRate(p.getTotalDefence() == 0 ? 0 : (double) p.getBestDefence() / (double) p.getTotalDefence());
            p.setDefenceRatePerc(p.getDefenceRate() == 0 ? "0%" : df.format(p.getDefenceRate()));

            p.setTotalAttempt(p.getTotalService() + p.getTotalAttack() + p.getTotalBlock() + p.getTotalSet() + p.getTotalReception() + p.getTotalDefence());
            p.setBestAttempt(p.getBestService() + p.getBestAttack() + p.getBestBlock() + p.getBestSet() + p.getBestReception() + p.getBestDefence());

            p.setAttemptRate(p.getTotalAttempt() == 0 ? 0 : (double) p.getBestAttempt() / (double) p.getTotalAttempt());
            p.setAttemptRatePerc(p.getAttemptRate() == 0 ? "0%" : df.format(p.getAttemptRate()));

            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.match.name"));
            ps1.setInt(1, p.getId());

            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                p.setPlayerName(rs1.getString(1));
                p.setMatchPhase(rs1.getString(2));
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return p;

    }

    public MatchBean getMatchNamePhaseById(int id) {
        MatchBean p = new MatchBean();
        try {

            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.match.name"));
            ps1.setInt(1, id);

            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                p.setMatch(rs1.getString(1));
                p.setPhase(rs1.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public List<PlayerScores> getMatchChart(int competationId, int matchid) {
        List<PlayerScores> list = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps;

            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.match.teamDetails"));
            ps.setInt(1, competationId);
            ps.setInt(2, matchid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PlayerScores p = new PlayerScores();
                p.setId(rs.getInt(1));
                p.setTeamName(rs.getString(2));

                p.setTotalService(rs.getInt(3));
                p.setBestService(rs.getInt(4));

                p.setTotalAttack(rs.getInt(5));
                p.setBestAttack(rs.getInt(6));

                p.setTotalBlock(rs.getInt(7));
                p.setBestBlock(rs.getInt(8));

                p.setTotalSet(rs.getInt(9));
                p.setBestSet(rs.getInt(10));

                p.setTotalReception(rs.getInt(11));
                p.setBestReception(rs.getInt(12));

                p.setTotalDefence(rs.getInt(13));
                p.setBestDefence(rs.getInt(14));

                p.setServiceRate(p.getTotalService() == 0 ? 0 : (CommonUtil.round((double) p.getBestService() / (double) p.getTotalService() * 100, 2)));
                p.setServiceRatePerc(p.getServiceRate() == 0 ? "0%" : df.format(p.getServiceRate()));

                p.setAttackRate(p.getTotalAttack() == 0 ? 0 : (CommonUtil.round((double) p.getBestAttack() / (double) p.getTotalAttack() * 100, 2)));
                p.setAttackRatePerc(p.getAttackRate() == 0 ? "0%" : df.format(p.getAttackRate()));

                p.setBlockRate(p.getTotalBlock() == 0 ? 0 : (CommonUtil.round((double) p.getBestBlock() / (double) p.getTotalBlock() * 100, 2)));
                p.setBlockRatePerc(p.getBlockRate() == 0 ? "0%" : df.format(p.getBlockRate()));

                p.setSetRate(p.getTotalSet() == 0 ? 0 : (CommonUtil.round((double) p.getBestSet() / (double) p.getTotalSet() * 100, 2)));
                p.setSetRatePerc(p.getSetRate() == 0 ? "0%" : df.format(p.getSetRate()));

                p.setReceptionRate(p.getTotalReception() == 0 ? 0 : (CommonUtil.round((double) p.getBestReception() / (double) p.getTotalReception() * 100, 2)));
                p.setReceptionRatePerc(p.getReceptionRate() == 0 ? "0%" : df.format(p.getReceptionRate()));

                p.setDefenceRate(p.getTotalDefence() == 0 ? 0 : (CommonUtil.round((double) p.getBestDefence() / (double) p.getTotalDefence() * 100, 2)));
                p.setDefenceRatePerc(p.getDefenceRate() == 0 ? "0%" : df.format(p.getDefenceRate()));

                p.setTotalAttempt(p.getTotalService() + p.getTotalAttack() + p.getTotalBlock() + p.getTotalSet() + p.getTotalReception() + p.getTotalDefence());
                p.setBestAttempt(p.getBestService() + p.getBestAttack() + p.getBestBlock() + p.getBestSet() + p.getBestReception() + p.getBestDefence());

                p.setAttemptRate(p.getTotalAttempt() == 0 ? 0 : (double) p.getBestAttempt() / (double) p.getTotalAttempt());
                p.setAttemptRatePerc(p.getAttemptRate() == 0 ? "0%" : df.format(p.getAttemptRate()));
                list.add(p);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public PlayerSkillScore getTeamSkillWiseScoreReport(int compId, int skillId, int matchId, int teamId) {
        PlayerSkillScore skill = new PlayerSkillScore();
        try {
            PreparedStatement ps;
            this.con = db.getConnection();

            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.skillwisescore.ofmatch"));
            ps.setInt(1, compId);
            ps.setInt(2, teamId);
            ps.setInt(3, matchId);
            ps.setInt(4, skillId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                skill.setPlayerId(rs.getInt(1));
                skill.setPlayerName(rs.getString(2));
                skill.setTotalAttempt(rs.getInt(3));
                skill.setOne(rs.getInt(4));
                skill.setTwo(rs.getInt(5));
                skill.setThree(rs.getInt(6));
                skill.setFour(rs.getInt(7));
                skill.setFive(rs.getInt(8));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skill;
    }

    public List<PlayerScores> getMatchChartForPlayer(List<Player> playerList, int competationId, int matchid, int teamId) {
        List<PlayerScores> list = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps;

            for (Player p1 : playerList) {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.match.teamDetails.byplayer"));
                ps.setInt(1, competationId);
                ps.setInt(2, matchid);
                ps.setInt(3, teamId);
                ps.setInt(4, p1.getId());

                ResultSet rs = ps.executeQuery();
                PlayerScores p = new PlayerScores();
                while (rs.next()) {
                    p.setId(rs.getInt(1));
                    p.setTeamName(rs.getString(2));

                    p.setTotalService(rs.getInt(3));
                    p.setBestService(rs.getInt(4));

                    p.setTotalAttack(rs.getInt(5));
                    p.setBestAttack(rs.getInt(6));

                    p.setTotalBlock(rs.getInt(7));
                    p.setBestBlock(rs.getInt(8));

                    p.setTotalSet(rs.getInt(9));
                    p.setBestSet(rs.getInt(10));

                    p.setTotalReception(rs.getInt(11));
                    p.setBestReception(rs.getInt(12));

                    p.setTotalDefence(rs.getInt(13));
                    p.setBestDefence(rs.getInt(14));

                }

                if (p.getId() == 0) {
                    p.setId(p1.getId());
                    p.setTeamName(p1.getName());

                    p.setTotalService(0);
                    p.setBestService(0);

                    p.setTotalAttack(0);
                    p.setBestAttack(0);

                    p.setTotalBlock(0);
                    p.setBestBlock(0);

                    p.setTotalSet(0);
                    p.setBestSet(0);

                    p.setTotalReception(0);
                    p.setBestReception(0);

                    p.setTotalDefence(0);
                    p.setBestDefence(0);
                }
                p.setServiceRate(p.getTotalService() == 0 ? 0 : (CommonUtil.round((double) p.getBestService() / (double) p.getTotalService() * 100, 2)));
                p.setServiceRatePerc(p.getServiceRate() == 0 ? "0%" : df.format(p.getServiceRate()));

                p.setAttackRate(p.getTotalAttack() == 0 ? 0 : (CommonUtil.round((double) p.getBestAttack() / (double) p.getTotalAttack() * 100, 2)));
                p.setAttackRatePerc(p.getAttackRate() == 0 ? "0%" : df.format(p.getAttackRate()));

                p.setBlockRate(p.getTotalBlock() == 0 ? 0 : (CommonUtil.round((double) p.getBestBlock() / (double) p.getTotalBlock() * 100, 2)));
                p.setBlockRatePerc(p.getBlockRate() == 0 ? "0%" : df.format(p.getBlockRate()));

                p.setSetRate(p.getTotalSet() == 0 ? 0 : (CommonUtil.round((double) p.getBestSet() / (double) p.getTotalSet() * 100, 2)));
                p.setSetRatePerc(p.getSetRate() == 0 ? "0%" : df.format(p.getSetRate()));

                p.setReceptionRate(p.getTotalReception() == 0 ? 0 : (CommonUtil.round((double) p.getBestReception() / (double) p.getTotalReception() * 100, 2)));
                p.setReceptionRatePerc(p.getReceptionRate() == 0 ? "0%" : df.format(p.getReceptionRate()));

                p.setDefenceRate(p.getTotalDefence() == 0 ? 0 : (CommonUtil.round((double) p.getBestDefence() / (double) p.getTotalDefence() * 100, 2)));
                p.setDefenceRatePerc(p.getDefenceRate() == 0 ? "0%" : df.format(p.getDefenceRate()));

                p.setTotalAttempt(p.getTotalService() + p.getTotalAttack() + p.getTotalBlock() + p.getTotalSet() + p.getTotalReception() + p.getTotalDefence());
                p.setBestAttempt(p.getBestService() + p.getBestAttack() + p.getBestBlock() + p.getBestSet() + p.getBestReception() + p.getBestDefence());

                p.setAttemptRate(p.getTotalAttempt() == 0 ? 0 : (double) p.getBestAttempt() / (double) p.getTotalAttempt());
                p.setAttemptRatePerc(p.getAttemptRate() == 0 ? "0%" : df.format(p.getAttemptRate()));
                list.add(p);

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

}
