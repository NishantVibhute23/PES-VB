/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.panels.PanNewMatch;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author nishant.vibhute
 */
public class CreateMatchDialog {

    private JFrame parentFrame;
    private JDialog dialog;
    int matchId;

    public void setValues(int matchId) {
        this.matchId = matchId;
    }

    public void init() {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            if (matchId == 0) {
                this.dialog = new JDialog(this.parentFrame, "Match", true);
            } else {
                this.dialog = new JDialog(this.parentFrame, "Match", true);
            }

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
        PanNewMatch panMatch = null;
        if (matchId == 0) {
            panMatch = new PanNewMatch();
        } else {
            try {
                panMatch = new PanNewMatch(matchId);
            } catch (ParseException ex) {
                Logger.getLogger(CreateMatchDialog.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return panMatch;
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
