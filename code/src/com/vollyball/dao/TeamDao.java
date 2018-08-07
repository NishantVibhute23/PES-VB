/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.Player;
import com.vollyball.bean.Team;
import com.vollyball.db.DbUtil;
import com.vollyball.enums.TrueFalse;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class TeamDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertTeam(Team team) {
        int count = 0;
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.team"));
            ps.setString(1, team.getName());
            ps.setString(2, team.getCoach());
            ps.setString(3, team.getAsstCoach());
            ps.setString(4, team.getTrainer());
            ps.setString(5, team.getMedicalOffice());
            ps.setString(6, team.getAnalyzer());
            ps.setInt(7, team.getCompId());
            ps.setString(8, team.getShortCode());
            count = ps.executeUpdate();

            if (count != 0) {
                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.team.id"));
                ResultSet rs = ps1.executeQuery();

                while (rs.next()) {
                    id = rs.getInt(1);
                }

                if (id != 0) {
                    for (Player p : team.getPlayerList()) {
                        if (p.getName() != null && !p.getName().equals("")) {
                            PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.player"));
                            ps2.setString(1, p.getName());
                            ps2.setString(2, p.getChestNo());
                            ps2.setInt(3, id);
                            ps2.setInt(4, p.getPosition());
                            ps2.setInt(5, p.isCaptain() == true ? TrueFalse.TRUE.getId() : TrueFalse.FALSE.getId());
                            ps2.executeUpdate();
                        }
                    }
                }

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;

    }

    public List<Team> getTeams(int id) {
        List<Team> teamList = new ArrayList<>();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.teams"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Team t = new Team();
                t.setId(rs.getInt(1));
                t.setName(rs.getString(2));
                teamList.add(t);

            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teamList;
    }

    public List<Player> getTeamPlayers(int id) {
        List<Player> playerList = new ArrayList<>();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.players"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Player t = new Player();
                t.setId(rs.getInt(1));
                t.setName(rs.getString(2));
                t.setChestNo(rs.getString(3));
                t.setPosition(rs.getInt(4));
                t.setTeamName(rs.getString(5));
                t.setTeamId(id);
                playerList.add(t);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerList;
    }

    public List<Integer> getMatchPlayers(int id, int teamId) {
        List<Integer> playerList = new ArrayList<>();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchplayers"));
            ps.setInt(1, teamId);
            ps.setInt(2, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                playerList.add(rs.getInt(1));

            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerList;
    }

    public List<Player> getAlPlayers(int compId) {
        List<Player> playerList = new ArrayList<>();

        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.all.players"));
            ps.setInt(1, compId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Player p = new Player();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setChestNo(rs.getString(3));
                p.setTeamName(rs.getString(5));
                playerList.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return playerList;

    }

    public Team getteamDetail(int teamId) {
        Team t = new Team();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.detail"));
            ps.setInt(1, teamId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                t.setId(rs.getInt(1));
                t.setName(rs.getString(2));
                t.setShortCode(rs.getString(3));
                t.setCompId(rs.getInt(4));
                t.setCoach(rs.getString(5));
                t.setAsstCoach(rs.getString(6));
                t.setTrainer(rs.getString(7));
                t.setMedicalOffice(rs.getString(8));
                t.setAnalyzer(rs.getString(9));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public Player getPlayerById(int id) {
        Player p = new Player();
        try {

            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.playerDetailById"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setPosition(rs.getInt(3));
                p.setCaptain(rs.getInt(4) == 0 ? false : true);
                p.setChestNo(rs.getString(5));
                p.setTeamId(rs.getInt(6));
                p.setTeamName(rs.getString(7) + " (" + rs.getString(8) + ")");
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
