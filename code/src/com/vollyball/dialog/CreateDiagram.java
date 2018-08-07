/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.dialog;

import com.vollyball.bean.VollyCourtCoordinateBean;
import com.vollyball.panels.PanVolleyCourt;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author nishant.vibhute
 */
public class CreateDiagram {

    private JFrame parentFrame;
    private JDialog dialog;
    String chestNum;
    VollyCourtCoordinateBean v;
    String type = "", from = "";
    List<VollyCourtCoordinateBean> vlist = new ArrayList<VollyCourtCoordinateBean>();

    public void init() {
        try {
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

    public void setValues(VollyCourtCoordinateBean v, String chestNum, String from) {
        this.v = v;
        this.chestNum = chestNum;
        this.from = from;
        type = "single";
    }

    public void setValues(List<VollyCourtCoordinateBean> v, String from) {
        this.vlist = v;
        this.chestNum = chestNum;
        this.from = from;
        type = "multiple";
    }

    protected Container createPane() {
        PanVolleyCourt panMatch = new PanVolleyCourt();
        if (type.equalsIgnoreCase("single")) {
            panMatch.setValues(v, chestNum, from);
        } else {
            panMatch.setValues(vlist, from);
        }
        return panMatch;
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
