/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.controller.Controller;
import com.vollyball.panels.PanEvaluation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;

/**
 *
 * @author nishant.vibhute
 */
public class DialogPanEvaluation {

    private JFrame parentFrame;
    private JDialog dialog;

    String playerName;
    String teamName;
    ChartPanel panel;

    int setNum;
    int matchId;
    int teamEvaluateId;
    int opponentId;
    int evaluationType;
    int matchEvaluationTeamId;

    public void init() {
        try {
            this.panel = panel;

            this.playerName = playerName;
            this.teamName = teamName;
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            this.dialog = new JDialog(this.parentFrame, "Set "+setNum, true);

            this.dialog.setResizable(true);
            this.dialog.getContentPane().add(createPane());
            this.dialog.pack();
//            this.dialog.setSize(418, 505);//

            Dimension Size = Toolkit.getDefaultToolkit().getScreenSize();
            //            this.dialog.setLocation(new Double((Size.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(), new Double((Size.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());

            this.dialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
        } catch (Exception ex) {

            Logger.getLogger(CreateMatchDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSetFields(int setNum, int matchId, int teamEvaluateId, int opponentId, int evaluationType, int matchEvaluationTeamId) {
        this.setNum = setNum;
        this.matchId = matchId;
        this.teamEvaluateId = teamEvaluateId;
        this.opponentId = opponentId;
        this.evaluationType = evaluationType;
        this.matchEvaluationTeamId = matchEvaluationTeamId;
    }

    protected Container createPane() {
        Controller.panMatchSet = new PanEvaluation(setNum, matchId, teamEvaluateId, opponentId, evaluationType, matchEvaluationTeamId);

        return Controller.panMatchSet;
    }

    public void show() {
        if (this.dialog == null) {
            init();
        }
        this.dialog.setVisible(true);
    }

    public void close() {
        this.dialog.dispose();
        this.dialog.setVisible(false);
    }

    public void setFrame(JFrame frame) {
        parentFrame = (JFrame) frame;
    }
}
