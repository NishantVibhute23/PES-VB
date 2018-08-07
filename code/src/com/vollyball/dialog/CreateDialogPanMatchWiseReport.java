/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.bean.PlayerReportBean;
import com.vollyball.panels.PanPlayerDetails;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author nishant.vibhute
 */
public class CreateDialogPanMatchWiseReport {

    private JFrame parentFrame;
    private JDialog dialog;
    PlayerReportBean prb = new PlayerReportBean();
    String skillName, type;
    int skillId, compId;

    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.dialog = new JDialog(this.parentFrame, "Report", true);

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
        PanPlayerDetails panPlayerDetails = new PanPlayerDetails(prb, skillName, skillId, compId, type);
        return panPlayerDetails;
    }

    public void setValues(PlayerReportBean prb, String skillName, int skillId, int compId, String type) {
        this.prb = prb;
        this.skillName = skillName;
        this.skillId = skillId;
        this.compId = compId;
        this.type = type;
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
