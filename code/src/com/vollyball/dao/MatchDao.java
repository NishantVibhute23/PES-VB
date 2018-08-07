/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.MatchBean;
import com.vollyball.bean.MatchDashboard;
import com.vollyball.bean.MatchSet;
import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.bean.SetRotationOrder;
import com.vollyball.bean.SetSubstitution;
import com.vollyball.bean.SetTimeout;
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
public class MatchDao {
    
    DbUtil db = new DbUtil();
    Connection con;
    DecimalFormat df = new DecimalFormat("##.##%");
    
    public int insertMatch(MatchBean mb) {
        int count = 0;
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.match"));
            ps.setInt(1, mb.getTeam1());
            ps.setInt(2, mb.getTeam2());
            ps.setInt(3, mb.getDayNumber());
            ps.setInt(4, mb.getMatchNumber());
            ps.setString(5, mb.getDate());
            ps.setString(6, mb.getTime());
            ps.setString(7, mb.getPhase());
            ps.setInt(8, mb.getCompId());
            ps.setString(9, mb.getPlace());
            count = ps.executeUpdate();
            
            if (count != 0) {
                
                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.match.id"));
                ResultSet rs = ps1.executeQuery();
                
                while (rs.next()) {
                    id = rs.getInt(1);
                }
                
                if (id != 0) {
                    PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchsetevaluationteam"));
                    ps2.setInt(1, id);
                    ps2.setInt(2, mb.getTeam1());
                    ps2.setInt(3, mb.getTeam2());
                    count = ps2.executeUpdate();
                    
                    PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchsetevaluationteam"));
                    ps3.setInt(1, id);
                    ps3.setInt(2, mb.getTeam2());
                    ps3.setInt(3, mb.getTeam1());
                    count = ps3.executeUpdate();
                    
                }
                
            }
            
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
        
    }
    
    public List<MatchBean> getMatches(int id) {
        List<MatchBean> matchList = new ArrayList<>();
        try {
            
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matches"));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                MatchBean mb = new MatchBean();
                mb.setTeam1name(rs.getString(1));
                mb.setTeam2name(rs.getString(2));
                mb.setDate(rs.getString(3));
                mb.setTeam1(rs.getInt(4));
                mb.setTeam2(rs.getInt(5));
                mb.setId(rs.getInt(6));
                mb.setMatch(mb.getTeam1name() + " vs " + mb.getTeam2name());
                
                mb.setTime(rs.getString(7));
                mb.setDayNumber(rs.getInt(8));
                mb.setMatchNumber(rs.getInt(9));
                mb.setPhase(rs.getString(10));
                mb.setPlace(rs.getString(11));
                matchList.add(mb);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchList;
    }
    
    public MatchBean getMatchesById(int id, int matchId) {
        MatchBean mb = new MatchBean();
        try {
            
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchesbyid"));
            ps.setInt(1, id);
            ps.setInt(2, matchId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                mb.setTeam1name(rs.getString(1));
                mb.setTeam2name(rs.getString(2));
                mb.setDate(rs.getString(3));
                mb.setTeam1(rs.getInt(4));
                mb.setTeam2(rs.getInt(5));
                mb.setId(rs.getInt(6));
                mb.setTeam1ShortCode(rs.getString(7));
                mb.setTeam2ShortCode(rs.getString(8));
                mb.setPhase(rs.getString(9));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mb;
    }
    
    public int insertMatchPlayers(int matchId, int teamid, List<Player> players) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("delete.matchPlayers"));
            ps1.setInt(1, matchId);
            ps1.setInt(2, teamid);
            ps1.executeUpdate();
            
            for (Player id : players) {
                PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchplayers"));
                ps.setInt(1, matchId);
                ps.setInt(2, teamid);
                ps.setInt(3, id.getId());
                
                count = ps.executeUpdate();
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }
    
    public int isTeamSelected(int matchId) {
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.isMatchSelected"));
            ps.setInt(1, matchId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int saveMatchSet(MatchSet ms) {
        int id = 0;
        int mid = 0;
        
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset"));
            ps.setInt(1, ms.getMatchEvaluationTeamId());
            
            ps.setInt(2, ms.getSetNo());
            ps.setInt(3, ms.getWon_by());
            ps.setString(4, ms.getStart_time());
            ps.setString(5, ms.getEnd_time());
            ps.setString(6, ms.getEvaluator());
            ps.setString(7, ms.getDate());
            id = ps.executeUpdate();
            
            if (id != 0) {
                
                PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.matchset.id"));
                ResultSet rs = ps3.executeQuery();
                
                while (rs.next()) {
                    mid = rs.getInt(1);
                }
                
                for (SetRotationOrder s : ms.getRotationOrder()) {
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.rotationorder"));
                    ps1.setInt(1, s.getPosition());
                    ps1.setInt(2, s.getPlayerId());
                    ps1.setInt(3, mid);
                    ps1.executeUpdate();
                }
                
                for (SetSubstitution s : ms.getSetSubstitutions()) {
                    if (s.getPosition() != 7) {
                        PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.substitution"));
                        ps1.setInt(1, s.getPosition());
                        ps1.setInt(2, s.getRotation_player_id());
                        ps1.setInt(3, mid);
                        ps1.executeUpdate();
                    }
                }
                
                PreparedStatement ps5 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.plusminus"));
                ps5.setInt(1, 0);
                ps5.setInt(2, 0);
                ps5.setInt(3, mid);
                ps5.executeUpdate();
                
            }
            
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return mid;
        
    }
    
    public MatchSet getMatchSet(int setId, int matchId) {
        
        MatchSet ms = new MatchSet();
        
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset"));
            ps.setInt(1, matchId);
            ps.setInt(2, setId);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ms.setId(rs.getInt(1));
                ms.setMatchEvaluationTeamId(rs.getInt(2));
                ms.setEvaluationTeamId(rs.getInt(3));
                ms.setOpponentTeamId(rs.getInt(4));
                ms.setSetNo(rs.getInt(5));
                ms.setHomeScore(rs.getInt(6));
                ms.setOpponentScore(rs.getInt(7));
                ms.setWon_by(rs.getInt(8));
                ms.setStart_time(rs.getString(9));
                ms.setEnd_time(rs.getString(10));
                ms.setEvaluator(rs.getString(11));
                ms.setDate(rs.getString(12));
            }
            
            if (ms.getId() != 0) {
                List<SetRotationOrder> rotationOrder = new ArrayList<>();
                
                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.rotationorder"));
                ps1.setInt(1, ms.getId());
                ResultSet rs1 = ps1.executeQuery();
                
                while (rs1.next()) {
                    SetRotationOrder s = new SetRotationOrder();
                    s.setId(rs1.getInt(1));
                    s.setPosition(rs1.getInt(2));
                    s.setPlayerId(rs1.getInt(3));
                    s.setMatch_evaluation_id(rs1.getInt(4));
                    rotationOrder.add(s);
                    
                }
                ms.setRotationOrder(rotationOrder);
                List<SetSubstitution> setSubstitutions = new ArrayList<>();
                PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.substitution"));
                ps2.setInt(1, ms.getId());
                ResultSet rs2 = ps2.executeQuery();
                
                while (rs2.next()) {
                    SetSubstitution s = new SetSubstitution();
                    s.setId(rs2.getInt(1));
                    s.setPosition(rs2.getInt(2));
                    s.setRotation_player_id(rs2.getInt(3));
                    s.setMatch_evaluation_id(rs2.getInt(4));
                    s.setSubstitutePlayerId(rs2.getInt(5));
                    s.setPoint1(rs2.getString(6));
                    s.setPoint2(rs2.getString(7));
                    setSubstitutions.add(s);
                    
                }
                ms.setSetSubstitutions(setSubstitutions);
                
                PreparedStatement ps6 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.plusminus"));
                ps6.setInt(1, ms.getId());
                ResultSet rs6 = ps6.executeQuery();
                
                while (rs6.next()) {
                    ms.setOp(rs6.getInt(1));
                    ms.setTf(rs6.getInt(2));
                    
                }
                
                List<SetTimeout> setTimeout = new ArrayList<>();
                PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.timeout"));
                ps7.setInt(1, ms.getId());
                ResultSet rs7 = ps7.executeQuery();
                
                while (rs7.next()) {
                    SetTimeout s = new SetTimeout();
                    s.setPosition(rs7.getInt(1));
                    s.setTeam(rs7.getString(2));
                    s.setScoreA(rs7.getInt(3));
                    s.setScoreB(rs7.getInt(4));
                    setTimeout.add(s);
                    
                }
                ms.setSetTimeout(setTimeout);
                
            }
            
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return ms;
        
    }
    
    public int updateSubstitution(int subPlayerId, String score, int position, int matchEvaluationId) {
        int id = 0;
        try {
            int rallyId = getLatestRally(matchEvaluationId);
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.substitution.point1"));
            ps1.setInt(1, subPlayerId);
            ps1.setString(2, score);
            ps1.setInt(3, rallyId);
            ps1.setInt(4, position);
            ps1.setInt(5, matchEvaluationId);
            
            id = ps1.executeUpdate();
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int updateSubstitutionPoint2(String score, int position, int matchEvaluationId) {
        int id = 0;
        try {
            int rallyId = getLatestRally(matchEvaluationId);
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.substitution.point2"));
            ps1.setString(1, score);
            ps1.setInt(2, rallyId);
            ps1.setInt(3, position);
            ps1.setInt(4, matchEvaluationId);
            
            id = ps1.executeUpdate();
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int getTimeOutCount(int matchEvaluationId) {
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.timeout.count"));
            ps1.setInt(1, matchEvaluationId);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                id = rs1.getInt(1);
            }
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int insertTimeout(SetTimeout st) {
        int id = 0;
        try {
            int rallyId = getLatestRally(st.getMatchEvalId());
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.matchset.timeout"));
            ps1.setInt(1, st.getPosition());
            ps1.setString(2, st.getTeam());
            ps1.setInt(3, st.getScoreA());
            ps1.setInt(4, st.getScoreB());
            ps1.setInt(5, st.getMatchEvalId());
            ps1.setInt(6, rallyId);
            id = ps1.executeUpdate();
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int getMatchEvaluationTeamId(int matchId, int evaluationTeamId) {
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchEvaluationTeamid"));
            ps1.setInt(1, evaluationTeamId);
            ps1.setInt(2, matchId);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                id = rs1.getInt(1);
            }
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int getLatestRally(int matchevaluationId) {
        int id = 0;
        
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latestrally"));
            ps.setInt(1, matchevaluationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
        
    }
    
    public List<SetTimeout> getListOfTimeoutForRally(int rallyId, int matchEvaluationId) {
        List<SetTimeout> setTimeout = new ArrayList<>();
        try {
            
            this.con = db.getConnection();
            PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.timeoutforrally"));
            ps7.setInt(1, matchEvaluationId);
            ps7.setInt(2, rallyId);
            ResultSet rs7 = ps7.executeQuery();
            
            while (rs7.next()) {
                SetTimeout s = new SetTimeout();
                s.setId(rs7.getInt(1));
                s.setPosition(rs7.getInt(2));
                s.setTeam(rs7.getString(3));
                s.setScoreA(rs7.getInt(4));
                s.setScoreB(rs7.getInt(5));
                setTimeout.add(s);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setTimeout;
    }
    
    public int updateSetTimeout(SetTimeout s) {
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.timeout"));
            ps1.setInt(1, s.getScoreA());
            ps1.setInt(2, s.getScoreB());
            ps1.setInt(3, s.getId());
            id = ps1.executeUpdate();
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public List<SetSubstitution> getMatchSetPointsforRally(int rallyId, int matchEvaluationId, int point) {
        List<SetSubstitution> setSubstitutions = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps2;
            
            if (point == 1) {
                ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.substitution.point1.forrally"));
            } else {
                ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.matchset.substitution.point2.forrally"));
            }
            ps2.setInt(1, matchEvaluationId);
            ps2.setInt(2, rallyId);
            ResultSet rs2 = ps2.executeQuery();
            
            while (rs2.next()) {
                SetSubstitution s = new SetSubstitution();
                s.setId(rs2.getInt(1));
                s.setPosition(rs2.getInt(2));
                s.setRotation_player_id(rs2.getInt(3));
                s.setMatch_evaluation_id(rs2.getInt(4));
                s.setSubstitutePlayerId(rs2.getInt(5));
                s.setPoint1(rs2.getString(6));
                s.setPoint2(rs2.getString(7));
                setSubstitutions.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setSubstitutions;
    }
    
    public int updateSubstitutionPoints(String score, int subid, int point) {
        int id = 0;
        try {
            
            this.con = db.getConnection();
            PreparedStatement ps1;
            if (point == 1) {
                ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.substitution.point1.score"));
            } else {
                ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchset.substitution.point2.score"));
            }
            ps1.setString(1, score);
            ps1.setInt(2, subid);
            id = ps1.executeUpdate();
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public int updateMatchSetWonBy(int wonby, int evalId) {
        int id = 0;
        try {
            
            this.con = db.getConnection();
            PreparedStatement ps1;
            ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchsetWonby"));
            ps1.setInt(1, wonby);
            ps1.setInt(2, evalId);
            id = ps1.executeUpdate();
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public RallyEvaluation getLatestRallyDetails(int matchevaluationId) {
        RallyEvaluation re = new RallyEvaluation();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latestrally"));
            ps.setInt(1, matchevaluationId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                re.setId(rs.getInt(1));
                re.setStartby(rs.getInt(4));
                re.setWonby(rs.getInt(5));
            }
            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return re;
    }
    
    public List<MatchBean> searchMatches(int id, int team1, int team2, String phase) {
        List<MatchBean> matchList = new ArrayList<>();
        try {
            
            this.con = db.getConnection();
            
            String query = CommonUtil.getResourceProperty("search.matches");
            if (team1 != 0) {
                query = query + " and team1=" + team1;
            }
            if (team2 != 0) {
                query = query + " and team2=" + team2;
            }
            if (!phase.equalsIgnoreCase("Select")) {
                query = query + " and phase='" + phase + "'";
            }
            query = query + " order by date asc; ";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                MatchBean mb = new MatchBean();
                mb.setTeam1name(rs.getString(1));
                mb.setTeam2name(rs.getString(2));
                mb.setDate(rs.getString(3));
                mb.setTeam1(rs.getInt(4));
                mb.setTeam2(rs.getInt(5));
                mb.setId(rs.getInt(6));
                mb.setMatch(mb.getTeam1name() + " vs " + mb.getTeam2name());
                
                mb.setTime(rs.getString(7));
                mb.setDayNumber(rs.getInt(8));
                mb.setMatchNumber(rs.getInt(9));
                mb.setPhase(rs.getString(10));
                mb.setPlace(rs.getString(11));
                matchList.add(mb);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return matchList;
    }
    
    public List<Integer> getSetNadWonBy(int matchId) {
        List<Integer> setWonBy = new ArrayList<>();
        try {
            
            this.con = db.getConnection();
            PreparedStatement ps7 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.setandwonbymatch"));
            ps7.setInt(1, matchId);
            
            ResultSet rs7 = ps7.executeQuery();
            
            while (rs7.next()) {
                
                setWonBy.add(rs7.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return setWonBy;
    }
    
    public int updateMatchWonBy(int teamId, int matchId) {
        int id = 0;
        try {
            
            this.con = db.getConnection();
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("update.matchWonBy"));
            ps1.setInt(1, teamId);
            ps1.setInt(2, matchId);
            
            id = ps1.executeUpdate();
            this.db.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }
    
    public List<MatchDashboard> getTeamMatchesRegister(List<Team> teamList) {
        
        List<MatchDashboard> matchList = new ArrayList<>();
        
        for (Team t : teamList) {
            try {
                this.con = db.getConnection();
                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.reagistertotal"));
                ps1.setInt(1, t.getId());
                ps1.setInt(2, t.getId());
                ps1.setInt(3, t.getId());
                ResultSet rs = ps1.executeQuery();
                
                while (rs.next()) {
                    MatchDashboard m = new MatchDashboard();
                    m.setTeamId(rs.getInt(1));
                    m.setTeamName(t.getName());
                    m.setTotalMatches(rs.getInt(2));
                    PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.team.matches.played"));
                    ps2.setInt(1, rs.getInt(1));
                    
                    ResultSet rs1 = ps2.executeQuery();
                    while (rs1.next()) {
                        m.setMatchesPlayed(rs1.getInt(1));
                        
                    }
                    
                    PreparedStatement ps3 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.teamwonloss"));
                    ps3.setInt(1, m.getTeamId());
                    ps3.setInt(2, m.getTeamId());
                    ps3.setInt(3, m.getTeamId());
                    ps3.setInt(4, m.getTeamId());
                    ResultSet rs2 = ps3.executeQuery();
                    
                    while (rs2.next()) {
                        m.setTotalWin(rs2.getInt(1));
                        m.setTotalLoss(rs2.getInt(2));
                        m.setTotalNoResult(m.getMatchesPlayed() - (m.getTotalWin() + m.getTotalLoss()));
                        
                        if (m.getMatchesPlayed() != 0) {
                            m.setSuccessRate(CommonUtil.round((double) m.getTotalWin() / (double) m.getMatchesPlayed() * 100, 2));
                            m.setSuccessRatePerc(df.format(((double) m.getTotalWin() / (double) m.getMatchesPlayed())));
                        } else {
                            m.setSuccessRate(0);
                            m.setSuccessRatePerc(df.format(m.getSuccessRate()));
                        }
                    }
                    
                    matchList.add(m);
                }
                this.db.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(MatchDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return matchList;
    }
}
