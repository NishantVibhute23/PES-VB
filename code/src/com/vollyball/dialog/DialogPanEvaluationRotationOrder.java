/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.controller.Controller;
import com.vollyball.panels.PanEvaluationRotation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author nishant.vibhute
 */
public class DialogPanEvaluationRotationOrder {

    private JFrame parentFrame;
    private JDialog dialog;

    int homeTeamId, oppTeamId, matchId, set, matchEvaluationId;
    String homeTeam, evaluatorName;
    String oppteam;
    String type;

    public void init(int homeTeamId, int oppTeamId, int matchId, String homeTeam, String oppteam, int set, int matchEvaluationId, String type, String evaluatorName) {
        try {
            this.homeTeamId = homeTeamId;
            this.oppTeamId = oppTeamId;
            this.matchId = matchId;
            this.homeTeam = homeTeam;
            this.oppteam = oppteam;
            this.set = set;
            this.matchEvaluationId = matchEvaluationId;
            this.type = type;
            this.evaluatorName = evaluatorName;
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.dialog = new JDialog(this.parentFrame, "Player Rotation", true);

            this.dialog.setResizable(false);
            this.dialog.getContentPane().add(createPane());
            this.dialog.pack();
//            this.dialog.setSize(418, 505);//

            Dimension Size = Toolkit.getDefaultToolkit().getScreenSize();
            this.dialog.setLocation(new Double((Size.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(), new Double((Size.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());

        } catch (Exception ex) {

            Logger.getLogger(CreateMatchDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Container createPane() {
        PanEvaluationRotation panRallyRotation = new PanEvaluationRotation(homeTeamId, oppTeamId, matchId, homeTeam, oppteam, set, matchEvaluationId, type, evaluatorName);

        return panRallyRotation;
    }

    public void show() {
        if (this.dialog == null) {
            init(homeTeamId, oppTeamId, matchId, homeTeam, oppteam, set, matchEvaluationId, type, evaluatorName);
        }
        this.dialog.setVisible(true);
        Controller.panMatchEvaluationHome.createWaitDialog.close();
    }

    public void close() {
        this.dialog.dispose();
        this.dialog.setVisible(false);
    }

    public void setFrame(JFrame frame) {
        parentFrame = (JFrame) frame;
    }
}
