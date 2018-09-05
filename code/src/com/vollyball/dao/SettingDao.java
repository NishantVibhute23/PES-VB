/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.Settings;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class SettingDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertSettings(Map<Integer, List<Settings>> settingMap) {
        int count = 0;
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.setting"));

            for (Map.Entry<Integer, List<Settings>> entry : settingMap.entrySet()) {
//                Integer key = entry.getKey();
                List<Settings> value = entry.getValue();
                for (Settings settings : value) {
                    ps.setInt(1, settings.getHeadingId());
                    ps.setInt(2, settings.getShortCutId());
                    ps.setString(3, settings.getCode());
                    ps.setString(4, settings.getAbbr());
                    count = ps.executeUpdate();
                }
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public Map<Integer, List<Settings>> getSettings() {
        Map<Integer, List<Settings>> settingMap = new HashMap<>();

        try {
            this.con = db.getConnection();
            PreparedStatement ps, ps1;
            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.headingId"));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<Settings> settingList = new ArrayList<>();
                ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.setting.fromHeadingId"));
                ps1.setInt(1, rs.getInt(1));
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    Settings set = new Settings();
                    set.setHeadingId(rs1.getInt(1));
                    set.setShortCutId(rs1.getInt(2));
                    set.setCode(rs1.getString(3));
                    set.setAbbr(rs1.getString(4));
                    settingList.add(set);
                }
                settingMap.put(rs.getInt(1), settingList);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return settingMap;
    }

    public int updateSettings(Map<Integer, List<Settings>> settingMap) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps;
            for (Map.Entry<Integer, List<Settings>> entry : settingMap.entrySet()) {
                Integer key = entry.getKey();
                List<Settings> value = entry.getValue();
                for (Settings settings : value) {
                    ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.setting"));
                    ps.setString(1, settings.getCode());
                    ps.setInt(2, settings.getShortCutId());
                    count = ps.executeUpdate();
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public Settings getCodeForId(int shortCutId) {
        Settings set = new Settings();

        try {
            this.con = db.getConnection();
            PreparedStatement ps;
            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.code.for.shortCutId"));
            ps.setInt(1, shortCutId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set.setShortCutId(rs.getInt(1));
                set.setCode(rs.getString(2));
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return set;
    }

    public int updateSettings(List<Settings> settingList) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps;
            ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.setting"));
            for (Settings settings : settingList) {
                ps.setString(1, settings.getCode());
                ps.setInt(2, settings.getShortCutId());
                count = ps.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
