/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class CompetitionDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertCompetition(CompetitionBean cb) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.competition"));
            ps.setString(1, cb.getName());
            ps.setString(2, cb.getVenue());
            ps.setString(3, cb.getStartDate());
            ps.setString(4, cb.getEndDate());
            ps.setString(5, cb.getAgeGroup());
            count = ps.executeUpdate();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            db.closeConnection(con);
        }
        return count;
    }

    public List<CompetitionBean> getCompetitionList() {
        List<CompetitionBean> competitionList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.competitionlist"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CompetitionBean cb = new CompetitionBean();
                cb.setId(rs.getInt(1));
                cb.setName(rs.getString(2));
                cb.setVenue(rs.getString(3));
                cb.setStartDate(rs.getString(4));
                cb.setEndDate(rs.getString(5));
                cb.setAgeGroup(rs.getString(6));
                cb.setIsDeleted(rs.getInt(7));
                competitionList.add(cb);
            }           
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            db.closeConnection(con);
        }
        return competitionList;
    }

    public CompetitionBean getCompetitionById(int compId) {
        CompetitionBean cb = new CompetitionBean();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.competitionlistbyId"));
            ps.setInt(1, compId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cb.setId(rs.getInt(1));
                cb.setName(rs.getString(2));
                cb.setVenue(rs.getString(3));
                cb.setStartDate(rs.getString(4));
                cb.setEndDate(rs.getString(5));
                cb.setAgeGroup(rs.getString(6));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            db.closeConnection(con);
        }
        return cb;
    }

    public int updateCompetition(CompetitionBean cb) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.competition"));
            ps.setString(1, cb.getName());
            ps.setString(2, cb.getVenue());
            ps.setString(3, cb.getStartDate());
            ps.setString(4, cb.getEndDate());
            ps.setString(5, cb.getAgeGroup());
            ps.setInt(6, cb.getId());
            count = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            db.closeConnection(con);
        }
        return count;
    }
    
    public int deleteCompetition(int id) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("delete.competition"));
            ps.setInt(1, id);
           
            count = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally{
            db.closeConnection(con);
        }
        return count;
    }
}
