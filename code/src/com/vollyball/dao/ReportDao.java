/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.Complex;
import com.vollyball.bean.MatchBean;
import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerPositionBean;
import com.vollyball.bean.PlayerReportBean;
import com.vollyball.bean.PlayerScores;
import com.vollyball.bean.PlayerSkillScore;
import com.vollyball.bean.SuccessFailure;
import com.vollyball.bean.Team;
import com.vollyball.bean.Tempo;
import com.vollyball.db.DbUtil;
import com.vollyball.enums.SkillZoneWiseReport;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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

                if (rs.getInt(18) == 2) {
                    p.setChestNo(rs.getString(17) + "L");
                } else {
                    p.setChestNo(rs.getString(17));
                }
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

                if (player.getPosition() == 2) {
                    p.setChestNo(player.getChestNo() + "L");
                } else {
                    p.setChestNo(player.getChestNo());
                }

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

    public List<SkillZoneWiseReport> getZoneDetails(int skill, int descId, int matchEvaluationId) {
        List<SkillZoneWiseReport> listZoneDetails = new ArrayList<>();
        try {
            PreparedStatement ps;
            this.con = db.getConnection();
            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.zone.details"));
            ps.setInt(1, skill);
            ps.setInt(2, descId);
            ps.setInt(3, matchEvaluationId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SkillZoneWiseReport skillZoneWiseReport = new SkillZoneWiseReport();
                skillZoneWiseReport.setValue(rs.getString(1));
                skillZoneWiseReport.setRating(rs.getInt(2));
                listZoneDetails.add(skillZoneWiseReport);
            }
            db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listZoneDetails;
    }

    public List<PlayerPositionBean> getRotationOrders(int matchevaluationId, int setNo, LinkedHashMap<Integer, Player> playerMap) {
        List<PlayerPositionBean> list = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.setwise.rotation"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, setNo);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                PlayerPositionBean ppb = new PlayerPositionBean();
                ppb.pos.put(1, playerMap.get(rs1.getInt(1)));
                ppb.pos.put(2, playerMap.get(rs1.getInt(2)));
                ppb.pos.put(3, playerMap.get(rs1.getInt(3)));
                ppb.pos.put(4, playerMap.get(rs1.getInt(4)));
                ppb.pos.put(5, playerMap.get(rs1.getInt(5)));
                ppb.pos.put(6, playerMap.get(rs1.getInt(6)));
                ppb.setWonby(rs1.getInt(7));
                list.add(ppb);
            }
            db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Tempo getAttackReportTempoWise(int matchevaluationId, int skillDescCriteriaId) {
        Tempo tempo = new Tempo();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.attack.tempowise.successFailure"));
            ps.setInt(1, matchevaluationId);
            ps.setInt(2, skillDescCriteriaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs.getInt(1));
                sf.setFailure(rs.getInt(2));
                tempo.setHigh(sf);
                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs.getInt(3));
                sf1.setFailure(rs.getInt(4));
                tempo.setMedium(sf1);
                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs.getInt(5));
                sf2.setFailure(rs.getInt(6));
                tempo.setLow(sf2);
            }
            db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempo;
    }

    public Tempo getBlockReportSkilDescWise(int matchevaluationId, int skillDescCriteriaId) {
        Tempo tempo = new Tempo();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.block.descwise.successFailure"));
            ps.setInt(1, matchevaluationId);
            ps.setInt(2, skillDescCriteriaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs.getInt(1));
                sf.setFailure(rs.getInt(2));
                tempo.setNb(sf);
                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs.getInt(3));
                sf1.setFailure(rs.getInt(4));
                tempo.setSgl(sf1);
                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs.getInt(5));
                sf2.setFailure(rs.getInt(6));
                tempo.setDbl(sf2);
                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs.getInt(7));
                sf3.setFailure(rs.getInt(8));
                tempo.setTpl(sf3);
            }
            db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempo;
    }

    public Tempo getServiceReportSkilDescWise(int matchevaluationId, int skillDescCriteriaId) {
        Tempo tempo = new Tempo();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.service.descwise.successfailure"));
            ps.setInt(1, matchevaluationId);
            ps.setInt(2, skillDescCriteriaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs.getInt(1));
                sf.setFailure(rs.getInt(2));
                tempo.setJf(sf);
                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs.getInt(3));
                sf1.setFailure(rs.getInt(4));
                tempo.setJp(sf1);
                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs.getInt(5));
                sf2.setFailure(rs.getInt(6));
                tempo.setSf(sf2);
                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs.getInt(7));
                sf3.setFailure(rs.getInt(8));
                tempo.setSs(sf3);
            }
            db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempo;
    }

    public List<PlayerScores> getMatchReportForPlayerSkills(int evId, int skillId) {
        List<PlayerScores> list = new ArrayList<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps;
            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchPlayer.successFailure.byplayer"));
            ps.setInt(1, evId);
            ps.setInt(2, skillId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PlayerScores playerScores = new PlayerScores();
                playerScores.setId(rs.getInt(1));
                playerScores.setPlayerName(rs.getString(2));
                playerScores.setTotalAttempt(rs.getInt(3));
                playerScores.setSuccessAttempt(rs.getInt(4));
                playerScores.setFailureAttempt(rs.getInt(5));
                playerScores.setAttemptSuccessRate(playerScores.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) playerScores.getSuccessAttempt() / (double) playerScores.getTotalAttempt() * 100, 2)));
                playerScores.setAttemptSuccessRatePerc(playerScores.getAttemptSuccessRate() == 0 ? "0%" : df.format(playerScores.getAttemptSuccessRate()));
                playerScores.setAttemptFailureRate(playerScores.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) playerScores.getFailureAttempt() / (double) playerScores.getTotalAttempt() * 100, 2)));
                playerScores.setAttemptFailureRatePerc(playerScores.getAttemptFailureRate() == 0 ? "0%" : df.format(playerScores.getAttemptFailureRate()));
                list.add(playerScores);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;

    }

    public Complex getComplexReport(int matchevaluationId, int skillDescCriteriaId) {
        Complex complex = new Complex();

        complex.setK1(getAttackReportSkilDescWiseComplexWiseK1(matchevaluationId, skillDescCriteriaId));
        complex.setK2(getAttackReportSkilDescWiseComplexWiseK2(matchevaluationId, skillDescCriteriaId));
        complex.setTp(getAttackReportSkilDescWiseComplexWiseTp(matchevaluationId, skillDescCriteriaId));

        return complex;
    }

    public Tempo getAttackReportSkilDescWiseComplexWiseK1(int matchevaluationId, int skillDescCriteriaId) {
        Tempo tempo = new Tempo();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k1.1"));
            ps.setInt(1, matchevaluationId);
            ps.setInt(2, skillDescCriteriaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs.getInt(1));
                sf.setFailure(rs.getInt(2));
                sf.setTotalAttempt(rs.getInt(9));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setHigh(sf);
                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs.getInt(3));
                sf1.setFailure(rs.getInt(4));
                sf1.setTotalAttempt(rs.getInt(9));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));

                tempo.setMedium(sf1);
                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs.getInt(5));
                sf2.setFailure(rs.getInt(6));
                sf2.setTotalAttempt(rs.getInt(9));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));

                tempo.setLow(sf2);
                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs.getInt(7));
                sf3.setFailure(rs.getInt(8));
                sf3.setTotalAttempt(rs.getInt(9));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setOdb(sf3);

            }
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k1.2"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs1.getInt(1));
                sf.setFailure(rs1.getInt(2));
                sf.setTotalAttempt(rs1.getInt(9));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setSgl(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs1.getInt(3));
                sf1.setFailure(rs1.getInt(4));
                sf1.setTotalAttempt(rs1.getInt(9));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setDbl(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs1.getInt(5));
                sf2.setFailure(rs1.getInt(6));
                sf2.setTotalAttempt(rs1.getInt(9));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setTpl(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs1.getInt(7));
                sf3.setFailure(rs1.getInt(8));
                sf3.setTotalAttempt(rs1.getInt(9));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setNb(sf3);
            }
            PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k1.3"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs2.getInt(1));
                sf.setFailure(rs2.getInt(2));
                sf.setTotalAttempt(rs2.getInt(17));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setIn(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs2.getInt(3));
                sf1.setFailure(rs2.getInt(4));
                sf1.setTotalAttempt(rs2.getInt(17));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setOt(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs2.getInt(5));
                sf2.setFailure(rs2.getInt(6));
                sf2.setTotalAttempt(rs2.getInt(17));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setBt(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs2.getInt(7));
                sf3.setFailure(rs2.getInt(8));
                sf3.setTotalAttempt(rs2.getInt(17));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setOl(sf3);

                SuccessFailure sf4 = new SuccessFailure();
                sf4.setSuccess(rs2.getInt(9));
                sf4.setFailure(rs2.getInt(10));
                sf4.setTotalAttempt(rs2.getInt(17));
                sf4.setSuccessRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getSuccess() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setSuccessPerc(sf4.getSuccessRate() == 0 ? "0%" : df.format(sf4.getSuccessRate()));
                sf4.setFailureRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getFailure() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setFailurePerc(sf4.getFailureRate() == 0 ? "0%" : df.format(sf4.getFailureRate()));
                tempo.setD(sf4);

                SuccessFailure sf5 = new SuccessFailure();
                sf5.setSuccess(rs2.getInt(11));
                sf5.setFailure(rs2.getInt(12));
                sf5.setTotalAttempt(rs2.getInt(17));
                sf5.setSuccessRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getSuccess() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setSuccessPerc(sf5.getSuccessRate() == 0 ? "0%" : df.format(sf5.getSuccessRate()));
                sf5.setFailureRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getFailure() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setFailurePerc(sf5.getFailureRate() == 0 ? "0%" : df.format(sf5.getFailureRate()));
                tempo.setBc(sf5);

                SuccessFailure sf6 = new SuccessFailure();
                sf6.setSuccess(rs2.getInt(13));
                sf6.setFailure(rs2.getInt(14));
                sf6.setTotalAttempt(rs2.getInt(17));
                sf6.setSuccessRate(sf6.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf6.getSuccess() / (double) sf6.getTotalAttempt() * 100, 2)));
                sf6.setSuccessPerc(sf6.getSuccessRate() == 0 ? "0%" : df.format(sf6.getSuccessRate()));
                sf6.setFailureRate(sf6.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf6.getFailure() / (double) sf6.getTotalAttempt() * 100, 2)));
                sf6.setFailurePerc(sf6.getFailureRate() == 0 ? "0%" : df.format(sf6.getFailureRate()));
                tempo.setR(sf6);

                SuccessFailure sf7 = new SuccessFailure();
                sf7.setSuccess(rs2.getInt(15));
                sf7.setFailure(rs2.getInt(16));
                sf7.setTotalAttempt(rs2.getInt(17));
                sf7.setSuccessRate(sf7.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf7.getSuccess() / (double) sf7.getTotalAttempt() * 100, 2)));
                sf7.setSuccessPerc(sf7.getSuccessRate() == 0 ? "0%" : df.format(sf7.getSuccessRate()));
                sf7.setFailureRate(sf7.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf7.getFailure() / (double) sf7.getTotalAttempt() * 100, 2)));
                sf7.setFailurePerc(sf7.getFailureRate() == 0 ? "0%" : df.format(sf7.getFailureRate()));
                tempo.setBtl(sf7);
            }
            PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k1.4"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs3.getInt(1));
                sf.setFailure(rs3.getInt(2));
                sf.setTotalAttempt(rs3.getInt(13));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setOne(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs3.getInt(3));
                sf1.setFailure(rs3.getInt(4));
                sf1.setTotalAttempt(rs3.getInt(13));
                sf1.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setTwo(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs3.getInt(5));
                sf2.setFailure(rs3.getInt(6));
                sf2.setTotalAttempt(rs3.getInt(13));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setThree(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs3.getInt(7));
                sf3.setFailure(rs3.getInt(8));
                sf3.setTotalAttempt(rs3.getInt(13));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setFour(sf3);

                SuccessFailure sf4 = new SuccessFailure();
                sf4.setSuccess(rs3.getInt(9));
                sf4.setFailure(rs3.getInt(10));
                sf4.setTotalAttempt(rs3.getInt(13));
                sf4.setSuccessRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getSuccess() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setSuccessPerc(sf4.getSuccessRate() == 0 ? "0%" : df.format(sf4.getSuccessRate()));
                sf4.setFailureRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getFailure() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setFailurePerc(sf4.getFailureRate() == 0 ? "0%" : df.format(sf4.getFailureRate()));
                tempo.setFive(sf4);

                SuccessFailure sf5 = new SuccessFailure();
                sf5.setSuccess(rs3.getInt(11));
                sf5.setFailure(rs3.getInt(12));
                sf5.setTotalAttempt(rs3.getInt(13));
                sf5.setSuccessRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getSuccess() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setSuccessPerc(sf5.getSuccessRate() == 0 ? "0%" : df.format(sf5.getSuccessRate()));
                sf5.setFailureRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getFailure() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setFailurePerc(sf5.getFailureRate() == 0 ? "0%" : df.format(sf5.getFailureRate()));
                tempo.setSix(sf5);

            }
            db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempo;
    }

    public Tempo getAttackReportSkilDescWiseComplexWiseK2(int matchevaluationId, int skillDescCriteriaId) {
        Tempo tempo = new Tempo();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k2.1"));
            ps.setInt(1, matchevaluationId);
            ps.setInt(2, skillDescCriteriaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs.getInt(1));
                sf.setFailure(rs.getInt(2));
                sf.setTotalAttempt(rs.getInt(9));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setHigh(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs.getInt(3));
                sf1.setFailure(rs.getInt(4));
                sf1.setTotalAttempt(rs.getInt(9));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setMedium(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs.getInt(5));
                sf2.setFailure(rs.getInt(6));
                sf2.setTotalAttempt(rs.getInt(9));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setLow(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs.getInt(7));
                sf3.setFailure(rs.getInt(8));
                sf3.setTotalAttempt(rs.getInt(9));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setOdb(sf3);
            }
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k2.2"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs1.getInt(1));
                sf.setFailure(rs1.getInt(2));
                sf.setTotalAttempt(rs1.getInt(9));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setSgl(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs1.getInt(3));
                sf1.setFailure(rs1.getInt(4));
                sf1.setTotalAttempt(rs1.getInt(9));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setDbl(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs1.getInt(5));
                sf2.setFailure(rs1.getInt(6));
                sf2.setTotalAttempt(rs1.getInt(9));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setTpl(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs1.getInt(7));
                sf3.setFailure(rs1.getInt(8));
                sf3.setTotalAttempt(rs1.getInt(9));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));

                tempo.setNb(sf3);
            }
            PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k2.3"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs2.getInt(1));
                sf.setFailure(rs2.getInt(2));
                sf.setTotalAttempt(rs2.getInt(17));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setIn(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs2.getInt(3));
                sf1.setFailure(rs2.getInt(4));
                sf1.setTotalAttempt(rs2.getInt(17));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setOt(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs2.getInt(5));
                sf2.setFailure(rs2.getInt(6));
                sf2.setTotalAttempt(rs2.getInt(17));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setBt(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs2.getInt(7));
                sf3.setFailure(rs2.getInt(8));
                sf3.setTotalAttempt(rs2.getInt(17));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setOl(sf3);

                SuccessFailure sf4 = new SuccessFailure();
                sf4.setSuccess(rs2.getInt(9));
                sf4.setFailure(rs2.getInt(10));
                sf4.setTotalAttempt(rs2.getInt(17));
                sf4.setSuccessRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getSuccess() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setSuccessPerc(sf4.getSuccessRate() == 0 ? "0%" : df.format(sf4.getSuccessRate()));
                sf4.setFailureRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getFailure() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setFailurePerc(sf4.getFailureRate() == 0 ? "0%" : df.format(sf4.getFailureRate()));
                tempo.setD(sf4);

                SuccessFailure sf5 = new SuccessFailure();
                sf5.setSuccess(rs2.getInt(11));
                sf5.setFailure(rs2.getInt(12));
                sf5.setTotalAttempt(rs2.getInt(17));
                sf5.setSuccessRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getSuccess() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setSuccessPerc(sf5.getSuccessRate() == 0 ? "0%" : df.format(sf5.getSuccessRate()));
                sf5.setFailureRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getFailure() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setFailurePerc(sf5.getFailureRate() == 0 ? "0%" : df.format(sf5.getFailureRate()));
                tempo.setBc(sf5);

                SuccessFailure sf6 = new SuccessFailure();
                sf6.setSuccess(rs2.getInt(13));
                sf6.setFailure(rs2.getInt(14));
                sf6.setTotalAttempt(rs2.getInt(17));
                sf6.setSuccessRate(sf6.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf6.getSuccess() / (double) sf6.getTotalAttempt() * 100, 2)));
                sf6.setSuccessPerc(sf6.getSuccessRate() == 0 ? "0%" : df.format(sf6.getSuccessRate()));
                sf6.setFailureRate(sf6.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf6.getFailure() / (double) sf6.getTotalAttempt() * 100, 2)));
                sf6.setFailurePerc(sf6.getFailureRate() == 0 ? "0%" : df.format(sf6.getFailureRate()));
                tempo.setR(sf6);

                SuccessFailure sf7 = new SuccessFailure();
                sf7.setSuccess(rs2.getInt(15));
                sf7.setFailure(rs2.getInt(16));
                sf7.setTotalAttempt(rs2.getInt(17));
                sf7.setSuccessRate(sf7.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf7.getSuccess() / (double) sf7.getTotalAttempt() * 100, 2)));
                sf7.setSuccessPerc(sf7.getSuccessRate() == 0 ? "0%" : df.format(sf7.getSuccessRate()));
                sf7.setFailureRate(sf7.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf7.getFailure() / (double) sf7.getTotalAttempt() * 100, 2)));
                sf7.setFailurePerc(sf7.getFailureRate() == 0 ? "0%" : df.format(sf7.getFailureRate()));
                tempo.setBtl(sf7);

            }
            PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.k2.4"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs3.getInt(1));
                sf.setFailure(rs3.getInt(2));
                sf.setTotalAttempt(rs3.getInt(13));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setOne(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs3.getInt(3));
                sf1.setFailure(rs3.getInt(4));
                sf1.setTotalAttempt(rs3.getInt(13));
                sf1.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setTwo(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs3.getInt(5));
                sf2.setFailure(rs3.getInt(6));
                sf2.setTotalAttempt(rs3.getInt(13));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setThree(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs3.getInt(7));
                sf3.setFailure(rs3.getInt(8));
                sf3.setTotalAttempt(rs3.getInt(13));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setFour(sf3);

                SuccessFailure sf4 = new SuccessFailure();
                sf4.setSuccess(rs3.getInt(9));
                sf4.setFailure(rs3.getInt(10));
                sf4.setTotalAttempt(rs3.getInt(13));
                sf4.setSuccessRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getSuccess() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setSuccessPerc(sf4.getSuccessRate() == 0 ? "0%" : df.format(sf4.getSuccessRate()));
                sf4.setFailureRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getFailure() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setFailurePerc(sf4.getFailureRate() == 0 ? "0%" : df.format(sf4.getFailureRate()));
                tempo.setFive(sf4);

                SuccessFailure sf5 = new SuccessFailure();
                sf5.setSuccess(rs3.getInt(11));
                sf5.setFailure(rs3.getInt(12));
                sf5.setTotalAttempt(rs3.getInt(13));
                sf5.setSuccessRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getSuccess() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setSuccessPerc(sf5.getSuccessRate() == 0 ? "0%" : df.format(sf5.getSuccessRate()));
                sf5.setFailureRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getFailure() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setFailurePerc(sf5.getFailureRate() == 0 ? "0%" : df.format(sf5.getFailureRate()));
                tempo.setSix(sf5);

            }
            db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempo;
    }

    public Tempo getAttackReportSkilDescWiseComplexWiseTp(int matchevaluationId, int skillDescCriteriaId) {
        Tempo tempo = new Tempo();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.tp.1"));
            ps.setInt(1, matchevaluationId);
            ps.setInt(2, skillDescCriteriaId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs.getInt(1));
                sf.setFailure(rs.getInt(2));
                sf.setTotalAttempt(rs.getInt(9));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setHigh(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs.getInt(3));
                sf1.setFailure(rs.getInt(4));
                sf1.setTotalAttempt(rs.getInt(9));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setMedium(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs.getInt(5));
                sf2.setFailure(rs.getInt(6));
                sf2.setTotalAttempt(rs.getInt(9));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setLow(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs.getInt(7));
                sf3.setFailure(rs.getInt(8));
                sf3.setTotalAttempt(rs.getInt(9));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setOdb(sf3);
            }
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.tp.2"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs1.getInt(1));
                sf.setFailure(rs1.getInt(2));
                sf.setTotalAttempt(rs1.getInt(9));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setSgl(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs1.getInt(3));
                sf1.setFailure(rs1.getInt(4));
                sf1.setTotalAttempt(rs1.getInt(9));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setDbl(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs1.getInt(5));
                sf2.setFailure(rs1.getInt(6));
                sf2.setTotalAttempt(rs1.getInt(9));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setTpl(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs1.getInt(7));
                sf3.setFailure(rs1.getInt(8));
                sf3.setTotalAttempt(rs1.getInt(9));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setNb(sf3);
            }
            PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.tp.3"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs2 = ps2.executeQuery();
            while (rs2.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs2.getInt(1));
                sf.setFailure(rs2.getInt(2));
                sf.setTotalAttempt(rs2.getInt(17));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setIn(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs2.getInt(3));
                sf1.setFailure(rs2.getInt(4));
                sf1.setTotalAttempt(rs2.getInt(17));
                sf1.setSuccessRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf1.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf1.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf1.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setOt(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs2.getInt(5));
                sf2.setFailure(rs2.getInt(6));
                sf2.setTotalAttempt(rs2.getInt(17));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setBt(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs2.getInt(7));
                sf3.setFailure(rs2.getInt(8));
                sf3.setTotalAttempt(rs2.getInt(17));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setOl(sf3);

                SuccessFailure sf4 = new SuccessFailure();
                sf4.setSuccess(rs2.getInt(9));
                sf4.setFailure(rs2.getInt(10));
                sf4.setTotalAttempt(rs2.getInt(17));
                sf4.setSuccessRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getSuccess() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setSuccessPerc(sf4.getSuccessRate() == 0 ? "0%" : df.format(sf4.getSuccessRate()));
                sf4.setFailureRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getFailure() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setFailurePerc(sf4.getFailureRate() == 0 ? "0%" : df.format(sf4.getFailureRate()));
                tempo.setD(sf4);

                SuccessFailure sf5 = new SuccessFailure();
                sf5.setSuccess(rs2.getInt(11));
                sf5.setFailure(rs2.getInt(12));
                sf5.setTotalAttempt(rs2.getInt(17));
                sf5.setSuccessRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getSuccess() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setSuccessPerc(sf5.getSuccessRate() == 0 ? "0%" : df.format(sf5.getSuccessRate()));
                sf5.setFailureRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getFailure() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setFailurePerc(sf5.getFailureRate() == 0 ? "0%" : df.format(sf5.getFailureRate()));
                tempo.setBc(sf5);

                SuccessFailure sf6 = new SuccessFailure();
                sf6.setSuccess(rs2.getInt(13));
                sf6.setFailure(rs2.getInt(14));
                sf6.setTotalAttempt(rs2.getInt(17));
                sf6.setSuccessRate(sf6.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf6.getSuccess() / (double) sf6.getTotalAttempt() * 100, 2)));
                sf6.setSuccessPerc(sf6.getSuccessRate() == 0 ? "0%" : df.format(sf6.getSuccessRate()));
                sf6.setFailureRate(sf6.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf6.getFailure() / (double) sf6.getTotalAttempt() * 100, 2)));
                sf6.setFailurePerc(sf6.getFailureRate() == 0 ? "0%" : df.format(sf6.getFailureRate()));
                tempo.setR(sf6);

                SuccessFailure sf7 = new SuccessFailure();
                sf7.setSuccess(rs2.getInt(15));
                sf7.setFailure(rs2.getInt(16));
                sf7.setTotalAttempt(rs2.getInt(17));
                sf7.setSuccessRate(sf7.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf7.getSuccess() / (double) sf7.getTotalAttempt() * 100, 2)));
                sf7.setSuccessPerc(sf7.getSuccessRate() == 0 ? "0%" : df.format(sf7.getSuccessRate()));
                sf7.setFailureRate(sf7.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf7.getFailure() / (double) sf7.getTotalAttempt() * 100, 2)));
                sf7.setFailurePerc(sf7.getFailureRate() == 0 ? "0%" : df.format(sf7.getFailureRate()));
                tempo.setBtl(sf7);

            }
            PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.complexwise.descwise.successFailure.tp.4"));
            ps1.setInt(1, matchevaluationId);
            ps1.setInt(2, skillDescCriteriaId);
            ResultSet rs3 = ps3.executeQuery();
            while (rs3.next()) {
                SuccessFailure sf = new SuccessFailure();
                sf.setSuccess(rs3.getInt(1));
                sf.setFailure(rs3.getInt(2));
                sf.setTotalAttempt(rs3.getInt(13));
                sf.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getSuccess() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf.getSuccessRate()));
                sf.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf.getFailure() / (double) sf.getTotalAttempt() * 100, 2)));
                sf.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf.getFailureRate()));
                tempo.setOne(sf);

                SuccessFailure sf1 = new SuccessFailure();
                sf1.setSuccess(rs3.getInt(3));
                sf1.setFailure(rs3.getInt(4));
                sf1.setTotalAttempt(rs3.getInt(13));
                sf1.setSuccessRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getSuccess() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setSuccessPerc(sf.getSuccessRate() == 0 ? "0%" : df.format(sf1.getSuccessRate()));
                sf1.setFailureRate(sf.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf1.getFailure() / (double) sf1.getTotalAttempt() * 100, 2)));
                sf1.setFailurePerc(sf.getFailureRate() == 0 ? "0%" : df.format(sf1.getFailureRate()));
                tempo.setTwo(sf1);

                SuccessFailure sf2 = new SuccessFailure();
                sf2.setSuccess(rs3.getInt(5));
                sf2.setFailure(rs3.getInt(6));
                sf2.setTotalAttempt(rs3.getInt(13));
                sf2.setSuccessRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getSuccess() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setSuccessPerc(sf2.getSuccessRate() == 0 ? "0%" : df.format(sf2.getSuccessRate()));
                sf2.setFailureRate(sf2.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf2.getFailure() / (double) sf2.getTotalAttempt() * 100, 2)));
                sf2.setFailurePerc(sf2.getFailureRate() == 0 ? "0%" : df.format(sf2.getFailureRate()));
                tempo.setThree(sf2);

                SuccessFailure sf3 = new SuccessFailure();
                sf3.setSuccess(rs3.getInt(7));
                sf3.setFailure(rs3.getInt(8));
                sf3.setTotalAttempt(rs3.getInt(13));
                sf3.setSuccessRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getSuccess() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setSuccessPerc(sf3.getSuccessRate() == 0 ? "0%" : df.format(sf3.getSuccessRate()));
                sf3.setFailureRate(sf3.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf3.getFailure() / (double) sf3.getTotalAttempt() * 100, 2)));
                sf3.setFailurePerc(sf3.getFailureRate() == 0 ? "0%" : df.format(sf3.getFailureRate()));
                tempo.setFour(sf3);

                SuccessFailure sf4 = new SuccessFailure();
                sf4.setSuccess(rs3.getInt(9));
                sf4.setFailure(rs3.getInt(10));
                sf4.setTotalAttempt(rs3.getInt(13));
                sf4.setSuccessRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getSuccess() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setSuccessPerc(sf4.getSuccessRate() == 0 ? "0%" : df.format(sf4.getSuccessRate()));
                sf4.setFailureRate(sf4.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf4.getFailure() / (double) sf4.getTotalAttempt() * 100, 2)));
                sf4.setFailurePerc(sf4.getFailureRate() == 0 ? "0%" : df.format(sf4.getFailureRate()));
                tempo.setFive(sf4);

                SuccessFailure sf5 = new SuccessFailure();
                sf5.setSuccess(rs3.getInt(11));
                sf5.setFailure(rs3.getInt(12));
                sf5.setTotalAttempt(rs3.getInt(13));
                sf5.setSuccessRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getSuccess() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setSuccessPerc(sf5.getSuccessRate() == 0 ? "0%" : df.format(sf5.getSuccessRate()));
                sf5.setFailureRate(sf5.getTotalAttempt() == 0 ? 0 : (CommonUtil.round((double) sf5.getFailure() / (double) sf5.getTotalAttempt() * 100, 2)));
                sf5.setFailurePerc(sf5.getFailureRate() == 0 ? "0%" : df.format(sf5.getFailureRate()));
                tempo.setSix(sf5);

            }
            db.closeConnection(con);

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tempo;
    }
}
