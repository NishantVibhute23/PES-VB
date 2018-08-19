/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.dao;

import com.vollyball.db.DbUtil;
import com.vollyball.training.bean.Batch;
import com.vollyball.training.bean.Trainee;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
                            ps2.setInt(3, 0);
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

}
