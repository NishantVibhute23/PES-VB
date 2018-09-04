/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.panels.PanEvaluationReplaceLiberoHome;
import com.vollyball.panels.PanEvaluationReplaceLiberoOpp;
import com.vollyball.panels.PanEvaluationRowDetail;
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
public class DialogReplaceLibero {

    private JFrame parentFrame;
    private JDialog dialog;
    PanEvaluationRowDetail p;
    int type;

    public void init(PanEvaluationRowDetail p, int type) {
        try {
            this.p = p;
            this.type = type;
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

    protected Container createPane() {
        if (type == 1) {
            PanEvaluationReplaceLiberoHome panMatch = new PanEvaluationReplaceLiberoHome(p);
            return panMatch;
        } else {
            PanEvaluationReplaceLiberoOpp panMatch = new PanEvaluationReplaceLiberoOpp(p);
            return panMatch;
        }
    }

    public void show() {
        if (this.dialog == null) {
            init(p, type);
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
