/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.controller;

import com.vollyball.bean.CompetitionBean;
import com.vollyball.bean.UserBean;
import com.vollyball.dialog.CreateCompetitionDialog;
import com.vollyball.dialog.CreateMatchDialog;
import com.vollyball.dialog.CreatePlayerDialog;
import com.vollyball.dialog.CreateSettingDialog;
import com.vollyball.dialog.CreateTeamDialog;
import com.vollyball.dialog.CreateUserDialog;
import com.vollyball.frames.FrmDashboard;
import com.vollyball.frames.FrmLogin;
import com.vollyball.frames.FrmRegister;
import com.vollyball.panels.PanActivate;
import com.vollyball.panels.PanCompetitionList;
import com.vollyball.panels.PanCompetitionReportHome;
import com.vollyball.panels.PanComptitionHome;
import com.vollyball.panels.PanEvaluation;
import com.vollyball.panels.PanEvaluationRally;
import com.vollyball.panels.PanMatchEvaluationHome;
import com.vollyball.panels.PanMatchReport;
import com.vollyball.panels.PanMatches;
import com.vollyball.panels.PanNewCompetition;
import com.vollyball.panels.PanPESVBHome;
import com.vollyball.panels.PanTeamBestScorer;
import com.vollyball.panels.PanTeams;
import com.vollyball.panels.PanUserDetails;
import com.vollyball.panels.panLoading;
import com.vollyball.training.dialog.CreateBatchDialog;
import com.vollyball.training.dialog.CreateTraineeDailog;
import com.vollyball.training.panel.PanBatchList;
import com.vollyball.training.panel.PanBatchTraineeList;
import com.vollyball.util.CommonUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author nishant.vibhute
 */
public class Controller {

    public static FrmRegister frmMain;
    public static FrmDashboard frmDashBoard;
    public static PanActivate panActivate;
    public static PanUserDetails panUserDetails;
    public static JPanel panDetails;
    public static panLoading panLoading;
    public static PanNewCompetition panNewCompetition;
    public static PanComptitionHome panComptitionHome;
    public static PanCompetitionReportHome panCompetitionReportHome;
    public static PanTeams panTeams;
    public static PanMatches panMatches;
    public static PanMatchEvaluationHome panMatchEvaluationHome;
    public static PanEvaluation panMatchSet;
    public static LinkedHashMap<Integer, String> stepCompleted = new LinkedHashMap<Integer, String>();
    public static UserBean userBean = new UserBean();
    public static int competitionId;
    public static PanCompetitionList panCompetitionList;
    public static CreateMatchDialog matchDialog;
    public static PanMatchReport panMatchReport;
    public static CreateTeamDialog teamDialog;
    public static PanTeamBestScorer panTeamBestScorer;
    public static CreateCompetitionDialog createCompetitionDialog;
    public static CreateUserDialog createUserDialog;
    public static CreateSettingDialog createSettingDialog;
    public static PanBatchList panBatchList;
    public static CreateBatchDialog batchDialog;
    public static CreatePlayerDialog createPlayerDialog;
    public static PanBatchTraineeList panBatchTraineeList;
    public static PanPESVBHome panPESVBHome;
    public static PanEvaluationRally panEvaluationRally;
    public static CreateTraineeDailog createTraineeDailog;
    public static String databaseIpAdd;
    public static CompetitionBean competitionBean;
    
   
    public static void main(String args[]) {

        new CommonUtil();
        new FrmLogin();
//        String path = System.getProperty("user.home") + File.separator + "Documents";
//        path += File.separator + CommonUtil.getResourceProperty("folder.name");
////        File file = new File(CommonUtil.getResourceProperty("db.name"));
//        File file = new File(path + File.separator + CommonUtil.getResourceProperty("db.name"));

    }

}
