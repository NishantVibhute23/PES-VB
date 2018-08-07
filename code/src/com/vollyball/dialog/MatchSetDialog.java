/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.controller.Controller;
import com.vollyball.panels.PanMatchSet;
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
public class MatchSetDialog {

    private JFrame parentFrame;
    private JDialog dialog;

    int setNum;
    int matchId;
    int teamEvaluateId;
    int opponentId;
    int evaluationType;
    int matchEvaluationTeamId;

    public void init() {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            this.dialog = new JDialog(this.parentFrame, "SET " + this.setNum, true);

            this.dialog.setResizable(false);
            this.dialog.getContentPane().add(createPane());
            this.dialog.pack();
//            this.dialog.setSize(1024, 750);

            Dimension Size = Toolkit.getDefaultToolkit().getScreenSize();
            this.dialog.setLocation(new Double((Size.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(), new Double((Size.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());
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
        Controller.panMatchSet = new PanMatchSet(setNum, matchId, teamEvaluateId, opponentId, evaluationType, matchEvaluationTeamId);

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
