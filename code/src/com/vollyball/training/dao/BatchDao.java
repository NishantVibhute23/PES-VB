/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.dao;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.db.DbUtil;
import com.vollyball.training.bean.Batch;
import com.vollyball.training.bean.BatchMatch;
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

    public Batch getBatchDetails(int batchId) {
        Batch b = new Batch();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.batchDeatil.byId"));
            ps.setInt(1, batchId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                b.setId(rs.getInt(1));
                b.setName(rs.getString(2));
                b.setTrainer(rs.getString(3));
                b.setMedicalOffice(rs.getString(4));
                b.setAnalyzer(rs.getString(5));
                b.setVenue(rs.getString(6));
                b.setStartDate(rs.getString(7));
                b.setEndDate(rs.getString(8));
                b.setAgeGroup(rs.getString(9));

            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public int updateBatch(Batch batch) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.batch"));
            ps.setString(1, batch.getName());
            ps.setString(2, batch.getTrainer());
            ps.setString(3, batch.getMedicalOffice());
            ps.setString(4, batch.getAnalyzer());
            ps.setString(5, batch.getVenue());
            ps.setString(6, batch.getStartDate());
            ps.setString(7, batch.getEndDate());
            ps.setString(8, batch.getAgeGroup());
            ps.setInt(9, batch.getId());
            count = ps.executeUpdate();

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;
    }

    public List<Trainee> getTraineeList(int batchId) {
        List<Trainee> traineeList = new ArrayList<>();
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.traineelist"));
            ps.setInt(1, batchId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Trainee t = new Trainee();
                t.setId(rs.getInt(1));
                t.setName(rs.getString(2));
                t.setBatchId(rs.getInt(3));
                traineeList.add(t);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return traineeList;

    }

    public int insertTrainee(Trainee trainee) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.trainee"));
            ps.setString(1, trainee.getName());
            ps.setInt(2, trainee.getBatchId());
            count = ps.executeUpdate();

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;

    }

    public int updateTrainee(Trainee trainee) {
        int count = 0;
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("update.trainee"));
            ps.setString(1, trainee.getName());
            ps.setInt(2, trainee.getBatchId());
            ps.setInt(3, trainee.getId());

            count = ps.executeUpdate();

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;

    }
    public int insertBatchMatch(BatchMatch bm) {
        int count = 0;

        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.batchTeam"));
            ps.setString(1, bm.getTeam1Name());
            ps.setInt(2, bm.getBatchId());
            PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.batchTeam"));
            ps1.setString(1, bm.getTeam2Name());
            ps1.setInt(2, bm.getBatchId());
            count = ps.executeUpdate();
            count = ps1.executeUpdate();

            if (count == 1) {
                insertBatchMatchPlayer(bm);
            }

            db.closeConnection(con);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return count;

    }

    public int insertBatchMatchPlayer(BatchMatch bm) {
        int count = 0;
        int teamId[] = new int[10];
        try {
            this.con = db.getConnection();
            PreparedStatement ps = this.con.prepareStatement(CommonUtil.getResourceProperty("get.batchTeam"));
            ps.setInt(1, bm.getBatchId());
            ResultSet rs = ps.executeQuery();

            int i = 0;
            while (rs.next()) {
                teamId[i] = rs.getInt(1);
                i++;
            }
            if (teamId.length != 0) {
                for (Trainee trainee : bm.getTeam1List()) {
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.batchMatchTeam"));
                    if (trainee.getId() != 0) {
                        ps1.setInt(1, teamId[0]);
                        ps1.setInt(2, trainee.getId());
                        ps1.setInt(3, trainee.getPosition());
                        count = ps1.executeUpdate();
                    }

                }
                for (Trainee trainee : bm.getTeam2List()) {
                    PreparedStatement ps1 = this.con.prepareStatement(CommonUtil.getResourceProperty("insert.batchMatchTeam"));
                    if (trainee.getId() != 0) {
                        ps1.setInt(1, teamId[1]);
                        ps1.setInt(2, trainee.getId());
                        ps1.setInt(3, trainee.getPosition());
                        count = ps1.executeUpdate();
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
