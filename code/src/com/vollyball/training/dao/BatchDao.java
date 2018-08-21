/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.dao;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.db.DbUtil;
import com.vollyball.training.bean.Batch;
import com.vollyball.training.bean.Trainee;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author #dabbu
 */
public class BatchDao {

    DbUtil db = new DbUtil();
    Connection con;

    public int insertBatch(Batch batch) {
        int count = 0;
        int id = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.batch"));
            ps.setString(1, batch.getName());
            ps.setString(2, batch.getTrainer());
            ps.setString(3, batch.getMedicalOffice());
            ps.setString(4, batch.getAnalyzer());
            ps.setString(5, batch.getVenue());
            ps.setString(6, batch.getStartDate());
            ps.setString(7, batch.getEndDate());
            ps.setString(8, batch.getAgeGroup());
            count = ps.executeUpdate();

            if (count != 0) {
                PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("get.latest.batch.id"));
                ResultSet rs = ps1.executeQuery();

                while (rs.next()) {
                    id = rs.getInt(1);
                }

                if (id != 0) {
                    for (Trainee t : batch.getTraineeList()) {
                        if (t.getName() != null && !t.getName().equals("")) {
                            PreparedStatement ps2 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.trainee"));
                            ps2.setString(1, t.getName());
                            ps2.setInt(2, id);
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

    public List<Batch> getBatchList() {
        List<Batch> batchList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.batchlist"));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Batch b = new Batch();
                b.setId(rs.getInt(1));
                b.setName(rs.getString(2));
                b.setTrainer(rs.getString(3));
                b.setVenue(rs.getString(4));
                b.setStartDate(rs.getString(5));
                b.setEndDate(rs.getString(6));
                b.setAgeGroup(rs.getString(7));
//                b.setIsDeleted(rs.getInt(8));
                batchList.add(b);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return batchList;
    }

}
