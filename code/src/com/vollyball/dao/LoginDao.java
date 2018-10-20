/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dao;

import com.vollyball.bean.UserBean;
import com.vollyball.db.DbUtil;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nishant.vibhute
 */
public class LoginDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int checkLogin(String name, String password) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("check.login"));
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(con);
        }
        return count;

    }

    public UserBean getUserDetails(String name) {
        UserBean ub = new UserBean();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.userDetails"));
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ub.setId(rs.getInt(1));
                ub.setUserName(rs.getString(2));
                ub.setEmailId(rs.getString(3));
                ub.setKeyCode(rs.getString(4));
                ub.setIsValid(rs.getInt(5));
                ub.setMacAddress(rs.getString(6));
                ub.setCreatedOn(rs.getString(7));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(con);
        }
        return ub;

    }

    public int updateStatus(UserBean ub) {

        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.validity"));
            ps.setInt(1, ub.getIsValid());
            ps.setInt(2, ub.getId());
            count = ps.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            db.closeConnection(con);
        }
        return count;

    }

}
