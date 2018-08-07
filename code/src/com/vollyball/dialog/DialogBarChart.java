/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.bean.PlayerSkillScore;
import com.vollyball.panels.PanBarChart;
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
public class DialogBarChart {

    private JFrame parentFrame;
    private JDialog dialog;
    List<PlayerSkillScore> playerDetails;
    String playerName, teamName;
    String skillName;
    int attempt;

    public void init(List<PlayerSkillScore> playerDetails, String playerName, String teamName, String Skill, int attempt) {
        try {
            this.playerDetails = playerDetails;
            this.playerName = playerName;
            this.teamName = teamName;
            this.skillName = Skill;
            this.attempt = attempt;

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
        PanBarChart panMatch = new PanBarChart(playerDetails, playerName, teamName, skillName, attempt);

        return panMatch;
    }

    public void show() {
        if (this.dialog == null) {
            init(playerDetails, playerName, teamName, skillName, attempt);
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
