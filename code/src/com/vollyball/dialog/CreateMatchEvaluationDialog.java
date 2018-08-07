/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.controller.Controller;
import com.vollyball.panels.PanMatchEvaluationHome;
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
public class CreateMatchEvaluationDialog {

    private JFrame parentFrame;
    private JDialog dialog;
    int teamId, oppId;
    int type;
    String homeTeam, oppteam;
    int matchId, matchEvaluationTeamId;

    public void init() {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            this.dialog = new JDialog(this.parentFrame, "New Match", true);

            this.dialog.setResizable(false);
            this.dialog.getContentPane().add(createPane());
            this.dialog.pack();

            Dimension Size = Toolkit.getDefaultToolkit().getScreenSize();
            this.dialog.setLocation(new Double((Size.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(), new Double((Size.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());

        } catch (Exception ex) {

            Logger.getLogger(CreateMatchDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setMatchId(int id, int oppId, int type, String homeTeam, String oppteam, int matchId, int matchEvaluationTeamId) {
        this.teamId = id;
        this.oppId = oppId;
        this.type = type;
        this.homeTeam = homeTeam;
        this.oppteam = oppteam;
        this.matchId = matchId;
        this.matchEvaluationTeamId = matchEvaluationTeamId;
    }

    protected Container createPane() {
        Controller.panMatchEvaluationHome = new PanMatchEvaluationHome(this.teamId, oppId, type, homeTeam, oppteam, matchId, matchEvaluationTeamId);

        return Controller.panMatchEvaluationHome;

    }

    public void show() {
        if (this.dialog == null) {
            init();
        }
        this.dialog.setVisible(true);
    }

    protected void close() {
        this.dialog.dispose();
        this.dialog.setVisible(false);
    }

    public void setFrame(JFrame frame) {
        parentFrame = (JFrame) frame;
    }
}
