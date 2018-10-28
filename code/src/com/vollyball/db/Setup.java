/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.db;

import com.vollyball.bean.Settings;
import com.vollyball.bean.UserBean;
import com.vollyball.controller.Controller;
import com.vollyball.dao.SettingDao;
import com.vollyball.enums.HeadingEnum;
import com.vollyball.enums.Rating;
import com.vollyball.enums.SetupEnum;
import com.vollyball.enums.ShortCutEnum;
import com.vollyball.enums.Skill;
import com.vollyball.enums.SkillDescCriteriaPoint;
import com.vollyball.enums.SkillDetails;
import com.vollyball.enums.SkillsDescCriteria;
import com.vollyball.enums.VollyCourtCoordinate;
import com.vollyball.frames.FrmRegister;
import com.vollyball.util.CommonUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class Setup extends Thread {

    DbUtil db = new DbUtil();

    @Override
    public void run() {
        String resp = setupDb();
        if (resp.equalsIgnoreCase("success")) {
            createUserTable(52);
            createCompetationTable(54);
            createTeamTable(56);
            createPlayersTable(58);
            createPoolTable(60);
            createMatchesTable(62);
            createMSkillsTable(64);
            createMRatingTable(66);
            createMatchEvaluationTable(68);
            createMSkillDescCriteriaTable(70);
            createMSkillDetailsTable(72);
            createMSkillDescCriteriaPointTable(74);
            createMatchEvaluationSetTable(76);
            createRallyTable(78);
            createRallyDetailsTable(79);
            createRallyDetailsCriteriaTable(80);
            createsetRotationOrderTable(81);
            createsetsubstitutionTable(82);
            createsetplusminusTable(83);
            createsettimeoutTable(84);
            createRallyRotationOrder(85);
            createMatchPlayer(86);
            createVollyCoordinate(87);
            creatematchevaluationsetlatestrotation(88);
            insertRatings(89);
            insertSkills(90);
            insertSkillDetails(91);
            insertSkillDescCriteria(92);
            insertSkillDescCriteriaPoint(93);
            insertUser(92);
            insertVollyCoordinate(93);
            createBatchTable(94);
            createTraineeTable(95);
            createSettingTable(96);
            insertDefaultSettings(97);
            createBatchTeamTable(99);
            createBatchMatchPlayerTable(100);

            FrmRegister.lblStatus.setText("Done");
            FrmRegister.lblFinish.setVisible(true);
            FrmRegister.lblHeader.setText("");
            Controller.panLoading.lblSpinner.setIcon(null);
            Controller.panLoading.lblSpinnerHead.setText("Finished");
        }

    }

    public String setupDb() {
//        boolean isCreated = db.createNewDatabase();
        boolean isCreated = db.createMysqlDatabase();
        if (isCreated) {
            db.grantPermission();
            Controller.stepCompleted.put(SetupEnum.Database.getStep(), SetupEnum.Database.getValue());
            FrmRegister.pgrStatus.setValue(70);
        }
        return "success";
    }

    public void createUserTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.user"), SetupEnum.User, status);
    }

    public void createCompetationTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.competition"), SetupEnum.Competition, status);
    }

    public void createTeamTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.team"), SetupEnum.Teams, status);
    }

    public void createPlayersTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.players"), SetupEnum.Players, status);
    }

    public void createPoolTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.pool"), SetupEnum.Pool, status);
    }

    public void createMatchesTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.matches"), SetupEnum.Matches, status);
    }

    public void createMatchEvaluationTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.matchevaluationteam"), SetupEnum.MatchEvaluationSet, status);
    }

    public void createMSkillsTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskills"), SetupEnum.MSkills, status);
    }

    public void createMRatingTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mrating"), SetupEnum.MRating, status);
    }

    public void createMSkillDescCriteriaTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskilldesccriteria"), SetupEnum.MSkillDescCriteria, status);
    }

    public void createMSkillDetailsTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskilldetails"), SetupEnum.MSkillDetails, status);
    }

    public void createMSkillDescCriteriaPointTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.mskilldesccriteriapoint"), SetupEnum.MSkillDescCriteriaPoint, status);
    }

    public void createMatchEvaluationSetTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.matchevaluationset"), SetupEnum.MatchEvaluationSet, status);
    }

    public void createRallyTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.rally"), SetupEnum.Rally, status);
    }

    public void createMatchPlayer(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.matchplayers"), SetupEnum.Rally, status);
    }

    public void createRallyDetailsTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.rallydetails"), SetupEnum.RallyDetails, status);
    }

    public void createRallyDetailsCriteriaTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.rallydetailscriteria"), SetupEnum.RallyDetailsCriteria, status);
    }

    public void createsetRotationOrderTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.setRotationOrder"), SetupEnum.ROTATIONORDER, status);
    }

    public void createsetsubstitutionTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.setsubstitution"), SetupEnum.SUBSTITUTION, status);
    }

    public void createsetplusminusTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.setplusminus"), SetupEnum.PLUSMINUS, status);
    }

    public void createsettimeoutTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.settimeout"), SetupEnum.TIMEOUT, status);
    }

    public void createRallyRotationOrder(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.rallyRotationorder"), SetupEnum.RALLYROTATIONORDER, status);
    }

    public void createVollyCoordinate(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.vollycoordinate"), SetupEnum.VollyCoordinate, status);
    }

    public void creatematchevaluationsetlatestrotation(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.matchevaluationsetlatestrotation"), SetupEnum.VollyCoordinate, status);
    }

    public void createBatchTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.batch"), SetupEnum.Batch, status);
    }

    public void createTraineeTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.trainee"), SetupEnum.Trainee, status);
    }

    public void createSettingTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.settings"), SetupEnum.Setting, status);
    }

    public void createBatchTeamTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.batchTeam"), SetupEnum.BatchMatch, status);
    }

    public void createBatchMatchPlayerTable(int status) {
        executeQuery(CommonUtil.getResourceProperty("create.batchMatchPlayer"), SetupEnum.BatchMatchPlayer, status);
    }

    public void insertRatings(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.ratings");
        for (Rating dir : Rating.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setString(2, dir.getType());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }
        int total = Rating.values().length;
        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertRating.getStep(), SetupEnum.InsertRating.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }

    public void insertSkillDescCriteria(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.skilldeccriteria");
        for (SkillsDescCriteria dir : SkillsDescCriteria.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setString(2, dir.getType());
                preparedStmt.setInt(3, dir.getSkillId());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }
        int total = SkillsDescCriteria.values().length;
        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertSkillDescCriteria.getStep(), SetupEnum.InsertSkillDescCriteria.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }

    
    public void insertSkillDescCriteriaPoint(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.skilldeccriteriapoint");
        for (SkillDescCriteriaPoint dir : SkillDescCriteriaPoint.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setString(2, dir.getType());
                preparedStmt.setString(3, dir.getAbbreviation());
                  preparedStmt.setInt(4, dir.getSkillDescCriteriaId());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }
        int total = SkillDescCriteriaPoint.values().length;
        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertSkillDescCriteriaPoint.getStep(), SetupEnum.InsertSkillDescCriteriaPoint.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }
    
    
    
    
    public void insertSkills(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.skills");
        for (Skill dir : Skill.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setString(2, dir.getType());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }

        int total = Skill.values().length;

        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertSkills.getStep(), SetupEnum.InsertSkills.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }

    }

    public void insertSkillDetails(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.skillsdetails");
        for (SkillDetails dir : SkillDetails.values()) {
            Connection conn = db.getConnection();
            int resp = 0;
            try {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, dir.getId());
                preparedStmt.setInt(2, dir.getSkillId());
                preparedStmt.setInt(3, dir.getRatingId());
                preparedStmt.setString(4, dir.getName());
                preparedStmt.setString(5, dir.getDescription());
                resp = preparedStmt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                db.closeConnection(conn);
            }
            count = count + resp;
        }

        int total = SkillDetails.values().length;

        if (total == count) {
            Controller.stepCompleted.put(SetupEnum.InsertSkillDetails.getStep(), SetupEnum.InsertSkillDetails.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }

    }

    public void insertUser(int status) {
        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.user");

        Connection conn = db.getConnection();
        int resp = 0;
        try {
            UserBean ub = Controller.userBean;
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, ub.getUserName());
            preparedStmt.setString(2, ub.getEmailId());
            preparedStmt.setString(3, ub.getCode());
            preparedStmt.setInt(4, ub.getIsValid());
            preparedStmt.setString(5, "");
            preparedStmt.setString(6, ub.getPassword());
            resp = preparedStmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(conn);
        }

        if (resp != 0) {
            Controller.stepCompleted.put(SetupEnum.InsertUser.getStep(), SetupEnum.InsertUser.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }

    public void insertVollyCoordinate(int status) {

        int count = 0;
        String query = CommonUtil.getResourceProperty("insert.vollycoordinate");

        Connection conn = db.getConnection();
        int resp = 0;
        try {

            for (VollyCourtCoordinate v : VollyCourtCoordinate.values()) {
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, v.getId());
                preparedStmt.setString(2, v.getSkill());
                preparedStmt.setInt(3, v.getFrom());
                preparedStmt.setInt(4, v.getTo());
                preparedStmt.setInt(5, v.getX1());
                preparedStmt.setInt(6, v.getY1());
                preparedStmt.setInt(7, v.getX2());
                preparedStmt.setInt(8, v.getY2());
                preparedStmt.setInt(9, v.getX3());
                preparedStmt.setInt(10, v.getY3());
                preparedStmt.setInt(11, v.getX4());
                preparedStmt.setInt(12, v.getY4());
                resp = preparedStmt.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(conn);
        }

        if (resp != 0) {
            Controller.stepCompleted.put(SetupEnum.InsertVollyCoordinate.getStep(), SetupEnum.InsertVollyCoordinate.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }

    public void insertDefaultSettings(int status) {
        SettingDao sd = new SettingDao();
        Map<Integer, List<Settings>> settingMap = new LinkedHashMap<>();
        for (HeadingEnum dir : HeadingEnum.values()) {
            List<Settings> settingList = new ArrayList<>();
            List<ShortCutEnum> list = ShortCutEnum.getByHeadingId(dir.getId());
            for (ShortCutEnum sce : list) {
                Settings set = new Settings();
                set.setHeadingId(sce.getHeadingId());
                set.setShortCutId(sce.getShortCutId());
                set.setCode(sce.getCode());
                set.setAbbr(sce.getAbbr());
                settingList.add(set);

            }
            settingMap.put(dir.getId(), settingList);

        }
        int id = sd.insertSettings(settingMap);
        if (id != 0) {
            Controller.stepCompleted.put(SetupEnum.InsertSettings.getStep(), SetupEnum.InsertSettings.getValue());
            FrmRegister.pgrStatus.setValue(status);
        }
    }

    public boolean executeQuery(String query, SetupEnum enumvalue, int status) {
        Connection conn = db.getConnection();
        Statement stmt;
        boolean resp = false;
        try {
            stmt = conn.createStatement();
            resp = stmt.execute(query);
            Controller.stepCompleted.put(enumvalue.getStep(), enumvalue.getValue());
            FrmRegister.pgrStatus.setValue(status);
        } catch (SQLException ex) {
            Logger.getLogger(Setup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            db.closeConnection(conn);
        }
        return resp;
    }

}
