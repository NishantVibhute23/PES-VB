/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.Player;
import com.vollyball.bean.PlayerScores;
import com.vollyball.bean.PlayerSkillScore;
import com.vollyball.bean.TeamScores;
import com.vollyball.bean.TeamSkillScore;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author #dabbu
 */
public class TeamReportDao {

    DbUtil db = new DbUtil();
    Connection con;
    DecimalFormat df = new DecimalFormat("##.##%");

    public TeamScores getTeamSuccessReportSkillwise(int competitionId, int matchId, int teamId) {
        TeamScores t = new TeamScores();

        try {
            this.con = db.getConnection();
            PreparedStatement ps;

            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("team.skillwisereport.success.summary"));
            ps.setInt(1, competitionId);
            ps.setInt(2, matchId);
            ps.setInt(3, teamId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setTeamName(rs.getString(2));
                t.setTotalService(rs.getInt(3));
                t.setBestService(rs.getInt(4));

                t.setTotalAttack(rs.getInt(5));
                t.setBestAttack(rs.getInt(6));

                t.setTotalBlock(rs.getInt(7));
                t.setBestBlock(rs.getInt(8));

                t.setTotalSet(rs.getInt(9));
                t.setBestSet(rs.getInt(10));

                t.setTotalReception(rs.getInt(11));
                t.setBestReception(rs.getInt(12));

                t.setTotalDefence(rs.getInt(13));
                t.setBestDefence(rs.getInt(14));
            }
            if (t.getId() == 0) {
                t.setTotalService(0);
                t.setBestService(0);

                t.setTotalAttack(0);
                t.setBestAttack(0);

                t.setTotalBlock(0);
                t.setBestBlock(0);

                t.setTotalSet(0);
                t.setBestSet(0);

                t.setTotalReception(0);
                t.setBestReception(0);

                t.setTotalDefence(0);
                t.setBestDefence(0);

            }
            t.setServiceRate(t.getTotalService() == 0 ? 0 : (double) t.getBestService() / (double) t.getTotalService());
            t.setServiceRatePerc(t.getServiceRate() == 0 ? "0%" : df.format(t.getServiceRate()));

            t.setAttackRate(t.getTotalAttack() == 0 ? 0 : (double) t.getBestAttack() / (double) t.getTotalAttack());
            t.setAttackRatePerc(t.getAttackRate() == 0 ? "0%" : df.format(t.getAttackRate()));

            t.setBlockRate(t.getTotalBlock() == 0 ? 0 : (double) t.getBestBlock() / (double) t.getTotalBlock());
            t.setBlockRatePerc(t.getBlockRate() == 0 ? "0%" : df.format(t.getBlockRate()));

            t.setSetRate(t.getTotalSet() == 0 ? 0 : (double) t.getBestSet() / (double) t.getTotalSet());
            t.setSetRatePerc(t.getSetRate() == 0 ? "0%" : df.format(t.getSetRate()));

            t.setReceptionRate(t.getTotalReception() == 0 ? 0 : (double) t.getBestReception() / (double) t.getTotalReception());
            t.setReceptionRatePerc(t.getReceptionRate() == 0 ? "0%" : df.format(t.getReceptionRate()));

            t.setDefenceRate(t.getTotalDefence() == 0 ? 0 : (double) t.getBestDefence() / (double) t.getTotalDefence());
            t.setDefenceRatePerc(t.getDefenceRate() == 0 ? "0%" : df.format(t.getDefenceRate()));

            t.setTotalAttempt(t.getTotalService() + t.getTotalAttack() + t.getTotalBlock() + t.getTotalSet() + t.getTotalReception() + t.getTotalDefence());
            t.setBestAttempt(t.getBestService() + t.getBestAttack() + t.getBestBlock() + t.getBestSet() + t.getBestReception() + t.getBestDefence());

            t.setAttemptRate(t.getTotalAttempt() == 0 ? 0 : (double) t.getBestAttempt() / (double) t.getTotalAttempt());
            t.setAttemptRatePerc(t.getAttemptRate() == 0 ? "0%" : df.format(t.getAttemptRate()));

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return t;

    }

    public TeamScores getTeamErrorReportSkillwise(int competitionId, int matchId, int teamId) {
        TeamScores t = new TeamScores();

        try {
            this.con = db.getConnection();
            PreparedStatement ps;

            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("team.skillwisereport.error.summary"));
            ps.setInt(1, competitionId);
            ps.setInt(2, matchId);
            ps.setInt(3, teamId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setTeamName(rs.getString(2));
                t.setTotalService(rs.getInt(3));
                t.setBestService(rs.getInt(4));

                t.setTotalAttack(rs.getInt(5));
                t.setBestAttack(rs.getInt(6));

                t.setTotalBlock(rs.getInt(7));
                t.setBestBlock(rs.getInt(8));

                t.setTotalSet(rs.getInt(9));
                t.setBestSet(rs.getInt(10));

                t.setTotalReception(rs.getInt(11));
                t.setBestReception(rs.getInt(12));

                t.setTotalDefence(rs.getInt(13));
                t.setBestDefence(rs.getInt(14));
            }
            if (t.getId() == 0) {
                t.setTotalService(0);
                t.setBestService(0);

                t.setTotalAttack(0);
                t.setBestAttack(0);

                t.setTotalBlock(0);
                t.setBestBlock(0);

                t.setTotalSet(0);
                t.setBestSet(0);

                t.setTotalReception(0);
                t.setBestReception(0);

                t.setTotalDefence(0);
                t.setBestDefence(0);

            }
            t.setServiceRate(t.getTotalService() == 0 ? 0 : (double) t.getBestService() / (double) t.getTotalService());
            t.setServiceRatePerc(t.getServiceRate() == 0 ? "0%" : df.format(t.getServiceRate()));

            t.setAttackRate(t.getTotalAttack() == 0 ? 0 : (double) t.getBestAttack() / (double) t.getTotalAttack());
            t.setAttackRatePerc(t.getAttackRate() == 0 ? "0%" : df.format(t.getAttackRate()));

            t.setBlockRate(t.getTotalBlock() == 0 ? 0 : (double) t.getBestBlock() / (double) t.getTotalBlock());
            t.setBlockRatePerc(t.getBlockRate() == 0 ? "0%" : df.format(t.getBlockRate()));

            t.setSetRate(t.getTotalSet() == 0 ? 0 : (double) t.getBestSet() / (double) t.getTotalSet());
            t.setSetRatePerc(t.getSetRate() == 0 ? "0%" : df.format(t.getSetRate()));

            t.setReceptionRate(t.getTotalReception() == 0 ? 0 : (double) t.getBestReception() / (double) t.getTotalReception());
            t.setReceptionRatePerc(t.getReceptionRate() == 0 ? "0%" : df.format(t.getReceptionRate()));

            t.setDefenceRate(t.getTotalDefence() == 0 ? 0 : (double) t.getBestDefence() / (double) t.getTotalDefence());
            t.setDefenceRatePerc(t.getDefenceRate() == 0 ? "0%" : df.format(t.getDefenceRate()));

            t.setTotalAttempt(t.getTotalService() + t.getTotalAttack() + t.getTotalBlock() + t.getTotalSet() + t.getTotalReception() + t.getTotalDefence());
            t.setBestAttempt(t.getBestService() + t.getBestAttack() + t.getBestBlock() + t.getBestSet() + t.getBestReception() + t.getBestDefence());

            t.setAttemptRate(t.getTotalAttempt() == 0 ? 0 : (double) t.getBestAttempt() / (double) t.getTotalAttempt());
            t.setAttemptRatePerc(t.getAttemptRate() == 0 ? "0%" : df.format(t.getAttemptRate()));

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return t;

    }

    public TeamSkillScore getTeamSkillWiseScoreReport(int compId, int skillId, int matchId, int teamId, int evId) {
        TeamSkillScore skill = new TeamSkillScore();
        try {
            PreparedStatement ps, ps1, ps2;
            this.con = db.getConnection();
            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.skillwisescore.ofmatch"));
            ps.setInt(1, compId);
            ps.setInt(2, teamId);
            ps.setInt(3, matchId);
            ps.setInt(4, skillId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                skill.setTeamId(rs.getInt(1));
                skill.setTeamName(rs.getString(2));
                skill.setTotalAttempt(rs.getInt(3));
                skill.setOne(rs.getInt(4));
                skill.setTwo(rs.getInt(5));
                skill.setThree(rs.getInt(6));
                skill.setFour(rs.getInt(7));
                skill.setFive(rs.getInt(8));

            }

            ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("team.ss.opdata.score"));
            ps1.setInt(1, 7);
            ps1.setInt(2, evId);
            ResultSet rs1 = ps1.executeQuery();

            while (rs1.next()) {
                skill.setOp(rs1.getInt(2));
            }
            ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("team.nss.tfdata.score"));
            ps2.setInt(1, 8);
            ps2.setInt(2, evId);
            ResultSet rs2 = ps2.executeQuery();

            while (rs2.next()) {
                skill.setTf(rs2.getInt(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return skill;
    }

    public Map<String, List<TeamScores>> getTeamSetWiseScoreReport(int setId, int evId) {
        Map<String, List<TeamScores>> tsMap = new HashMap<>();
        List<TeamScores> tsl = new ArrayList<>();

        try {
            PreparedStatement ps;
            this.con = db.getConnection();
            for (int skillId = 1; skillId <= 8; skillId++) {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("team.setwise.win.score"));
                ps.setInt(1, skillId);
                ps.setInt(2, evId);
                ps.setInt(3, setId);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    TeamScores teamScores = new TeamScores();
                    teamScores.setSetno(rs.getString(1));
                    teamScores.setTotalAttempt(rs.getInt(3));
                    tsl.add(teamScores);
                }

            }
            tsMap.put("" + setId, tsl);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tsMap;
    }

    public Map<String, List<TeamScores>> getTeamSetWiseScoreReportLoss(int setId, int evId) {
        Map<String, List<TeamScores>> tsMap = new HashMap<>();
        List<TeamScores> tsl = new ArrayList<>();

        try {
            PreparedStatement ps;
            this.con = db.getConnection();
            for (int skillId = 1; skillId <= 8; skillId++) {
                ps = this.con.prepareStatement(CommonUtil.getResourceProperty("team.setwise.loss.score"));
                ps.setInt(1, skillId);
                ps.setInt(2, evId);
                ps.setInt(3, setId);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    TeamScores teamScores = new TeamScores();
                    teamScores.setSetno(rs.getString(1));
                    teamScores.setTotalAttempt(rs.getInt(3));
                    tsl.add(teamScores);
                }

            }
            tsMap.put("" + setId, tsl);
        } catch (SQLException ex) {
            Logger.getLogger(ReportDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tsMap;
    }

    public Map<PlayerScores, List<Integer>> getTeamPlayers(int id) {
        Map<PlayerScores, List<Integer>> playerMap = new HashMap<>();
        List<Integer> setList;
        try {

            this.con = db.getConnection();
            PreparedStatement ps, ps1;
            ResultSet rs, rs1;
            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.players"));
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                PlayerScores p = new PlayerScores();
                setList = new ArrayList<>();
                p.setId(rs.getInt(1));
                p.setPlayerName(rs.getString(2));
                if (rs.getInt(4) == 2) {
                    p.setChestNo(rs.getString(3) + "L");
                } else {
                    p.setChestNo(rs.getString(3));
                }
                p.setTeamName(rs.getString(5));

                for (int i = 1; i <= 5; i++) {
                    ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("player.setwise.data"));
                    ps1.setInt(1, rs.getInt(1));
                    ps1.setInt(2, i);
                    rs1 = ps1.executeQuery();
                    while (rs1.next()) {
                        setList.add(rs1.getInt(3));
                    }
                }
                playerMap.put(p, setList);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerMap;
    }

}
