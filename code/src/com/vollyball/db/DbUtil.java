/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.db;

import com.vollyball.controller.Controller;
import com.vollyball.util.CommonUtil;
import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class DbUtil {

    String userpath = System.getProperty("user.home") + File.separator + "Documents";
    String foldername = CommonUtil.getResourceProperty("folder.name");
    String dbName = CommonUtil.getResourceProperty("db.name");
    String dbUrl;

    String url;
    Connection con;

    public void createFolder() {
        File customDir = new File(userpath + File.separator + foldername);

        if (customDir.exists()) {
            dbUrl = userpath + File.separator + foldername + File.separator + dbName;
        } else if (customDir.mkdirs()) {
            dbUrl = userpath + File.separator + foldername + File.separator + dbName;
        } else {
            System.out.println(customDir + " was not created");
        }
    }

    public boolean checkMysqlInstalled() {
        try {
            String DATABASE_URL = "jdbc:mysql://" + Controller.databaseIpAdd + ":3306/";
            DriverManager.setLoginTimeout(5);
            con = DriverManager.getConnection(DATABASE_URL, "root", "root");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void grantPermission() {

        Statement statement = null;
        try {
            String DATABASE_URL = "jdbc:mysql://" + Controller.databaseIpAdd + ":3306/";
            DriverManager.setLoginTimeout(23);
            con = DriverManager.getConnection(DATABASE_URL, "root", "root");
            String sql_stmt = "CREATE USER 'root'@'%' IDENTIFIED BY 'root';\n"
                    + "GRANT ALL PRIVILEGES ON vollyball.* TO 'root'@'%' WITH GRANT OPTION;";
            statement = con.createStatement();
            statement.execute(sql_stmt);

        } catch (SQLException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public boolean checkDatabaseExist() {
        try {
//            url = "jdbc:mysql://127.9.126.2:3306/pritienterprises";
            String DATABASE_URL = "jdbc:mysql://" + Controller.databaseIpAdd + ":3306/";
            url = DATABASE_URL + "" + dbName;
            DriverManager.setLoginTimeout(5);
            Class.forName("com.mysql.jdbc.Driver");
//            this.con = DriverManager.getConnection(url, "adminGnXBLDP", "dt78KgjZGwUD");
            this.con = DriverManager.getConnection(url, "root", "root");
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean createMysqlDatabase() {
        Statement statement = null;
        try {
            String DATABASE_URL = "jdbc:mysql://" + Controller.databaseIpAdd + ":3306/";
            DriverManager.setLoginTimeout(23);
            con = DriverManager.getConnection(DATABASE_URL, "root", "root");
            String sql_stmt = "CREATE DATABASE IF NOT EXISTS " + dbName + ";";
            statement = con.createStatement();
            statement.executeUpdate(sql_stmt);
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean createNewDatabase() {
        createFolder();
        boolean isCreated = false;
        Connection conn = null;
        String url = "jdbc:sqlite:" + dbUrl;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                isCreated = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isCreated = false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return isCreated;
    }

    public Connection getConnection() {
//        dbUrl = userpath + File.separator + foldername + File.separator + dbName;
//        Connection c = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:" + dbUrl);
//        } catch (Exception e) {
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//        }
//        return c;

        try {
//            url = "jdbc:mysql://127.9.126.2:3306/pritienterprises";
            String DATABASE_URL = "jdbc:mysql://" + Controller.databaseIpAdd + ":3306/";
            url = DATABASE_URL + "" + dbName + "?autoReconnect=true&useSSL=false";
            Class.forName("com.mysql.jdbc.Driver");
//            this.con = DriverManager.getConnection(url, "adminGnXBLDP", "dt78KgjZGwUD");
            this.con = DriverManager.getConnection(url, "root", "root");
            return con;

        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
