/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.TeamScores;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

/**
 *
 * @author #dabbu
 */
public class TeamReportDao {
     DbUtil db = new DbUtil();
    Connection con;
    DecimalFormat df = new DecimalFormat("##.##%");
    
    public TeamScores getTeamSuccessReportSkillwise(int competitionId,int matchId, int teamId) {
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
    
     public TeamScores getTeamErrorReportSkillwise(int competitionId,int matchId, int teamId) {
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
    
}
