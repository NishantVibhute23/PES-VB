/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.dialog;

import com.vollyball.dialog.CreateMatchDialog;
import com.vollyball.panels.PanNewTeam;
import com.vollyball.training.panel.PanEditBatch;
import com.vollyball.training.panel.PanNewBatch;
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
 * @author #dabbu
 */
public class CreateBatchDialog {

    private JFrame parentFrame;
    private JDialog dialog;
    int batchId;

    public void init() {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            this.dialog = new JDialog(this.parentFrame, "New Team", true);

            this.dialog.setResizable(false);
            this.dialog.getContentPane().add(createPane());
            this.dialog.pack();
            if (batchId == 0) {
                this.dialog.setSize(642, 814);
            } else {
                this.dialog.setSize(374, 553);
            }

            Dimension Size = Toolkit.getDefaultToolkit().getScreenSize();
            this.dialog.setLocation(new Double((Size.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(), new Double((Size.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());
        } catch (Exception ex) {

            Logger.getLogger(CreateMatchDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Container createPane() throws ParseException {

        if (batchId == 0) {
            PanNewBatch panBatch = new PanNewBatch();
            return panBatch;
        } else {
            PanEditBatch panBatch = new PanEditBatch(batchId);
            return panBatch;
        }

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

    public void setValues(int id) {
        this.batchId = id;
    }
}
