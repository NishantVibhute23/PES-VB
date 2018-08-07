/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.controller;

import com.vollyball.bean.UserBean;
import com.vollyball.dialog.CreateCompetitionDialog;
import com.vollyball.dialog.CreateMatchDialog;
import com.vollyball.dialog.CreateTeamDialog;
import com.vollyball.frames.FrmDashboard;
import com.vollyball.frames.FrmLogin;
import com.vollyball.frames.FrmRegister;
import com.vollyball.panels.PanActivate;
import com.vollyball.panels.PanCompetitionList;
import com.vollyball.panels.PanCompetitionReportHome;
import com.vollyball.panels.PanComptitionHome;
import com.vollyball.panels.PanMatchEvaluationHome;
import com.vollyball.panels.PanMatchReport;
import com.vollyball.panels.PanMatchSet;
import com.vollyball.panels.PanMatches;
import com.vollyball.panels.PanNewCompetition;
import com.vollyball.panels.PanTeamBestScorer;
import com.vollyball.panels.PanTeams;
import com.vollyball.panels.PanUserDetails;
import com.vollyball.panels.panLoading;
import com.vollyball.util.CommonUtil;
import java.io.File;
import java.util.LinkedHashMap;
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
    public static PanMatchSet panMatchSet;
    public static LinkedHashMap<Integer, String> stepCompleted = new LinkedHashMap<Integer, String>();
    public static UserBean userBean = new UserBean();
    public static int competitionId;
    public static PanCompetitionList panCompetitionList;
    public static CreateMatchDialog matchDialog;
    public static PanMatchReport panMatchReport;
    public static CreateTeamDialog teamDialog;
    public static PanTeamBestScorer panTeamBestScorer;
    public static CreateCompetitionDialog createCompetitionDialog;

    public static void main(String args[]) {

        new CommonUtil();

        File file = new File(CommonUtil.getResourceProperty("db.name"));
        if (!file.exists()) {
            frmMain = new FrmRegister();
        } else {
            new FrmLogin();
        }
    }

}
