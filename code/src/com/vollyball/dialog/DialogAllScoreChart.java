/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.panels.PanAllScoreDetials;
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
public class DialogAllScoreChart {

    private JFrame parentFrame;
    private JDialog dialog;
    int compId, playerId, matchesPlayed, matchId;
    String playerName;
    String teamName;
    int teamId;

    public void init(int compId, int playerId, String playerName, int matchesPlayed, String teamName, int matchId, int teamId) {
        try {
            this.compId = compId;
            this.playerId = playerId;
            this.matchesPlayed = matchesPlayed;
            this.playerName = playerName;
            this.teamName = teamName;
            this.matchId = matchId;
            this.teamId = teamId;
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            this.dialog = new JDialog(this.parentFrame, "New Match", true);

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
        PanAllScoreDetials panMatch = new PanAllScoreDetials(compId, playerId, playerName, matchesPlayed, teamName, matchId, teamId);

        return panMatch;
    }

    public void show() {
        if (this.dialog == null) {
            init(compId, playerId, playerName, matchesPlayed, teamName, matchId, teamId);
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
