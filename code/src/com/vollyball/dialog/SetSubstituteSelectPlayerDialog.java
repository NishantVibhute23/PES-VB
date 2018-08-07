/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.bean.Player;
import com.vollyball.panels.PanSubstituteSelectPlayer;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author nishant.vibhute
 */
public class SetSubstituteSelectPlayerDialog {

    private JFrame parentFrame;
    private JDialog dialog;
    int matchId;
    int teamEvaluateId;
    List<Player> playerList;
    int position;
    int roPlayerId;
    int matchEvaluationId;
    String score;

    public void init() {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            this.dialog = new JDialog(this.parentFrame, "Select Player", true);

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

    public void setData(List<Player> playerList, int position, int roPlayerId, int matchEvaluationId, String score) {
        this.playerList = playerList;
        this.position = position;
        this.roPlayerId = roPlayerId;
        this.matchEvaluationId = matchEvaluationId;
        this.score = score;
    }

    protected Container createPane() {
        PanSubstituteSelectPlayer panTeam = new PanSubstituteSelectPlayer(this.playerList, position, roPlayerId, matchEvaluationId, score, this);

        return panTeam;

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
