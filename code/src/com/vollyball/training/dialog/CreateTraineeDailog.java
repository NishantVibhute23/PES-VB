/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.dialog;

import com.vollyball.dialog.CreateCompetitionDialog;
import com.vollyball.dialog.CreateMatchDialog;
import com.vollyball.panels.PanNewCompetition;
import com.vollyball.training.bean.Trainee;
import com.vollyball.training.panel.PanNewTrainee;
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
public class CreateTraineeDailog {

    private JFrame parentFrame;
    private JDialog dialog;
    int batchId;
    Trainee trainee;

    public void setValues(int batchId) {
        this.batchId = batchId;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
        this.batchId = batchId;
    }

    public void init() {
        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            this.dialog = new JDialog(this.parentFrame, "New Trainee", true);

            this.dialog.setResizable(false);
            this.dialog.getContentPane().add(createPane());
            this.dialog.pack();
//            this.dialog.setSize(470, 560);

            Dimension Size = Toolkit.getDefaultToolkit().getScreenSize();
            this.dialog.setLocation(new Double((Size.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(), new Double((Size.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());

        } catch (Exception ex) {

            Logger.getLogger(CreateMatchDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Container createPane() {
        PanNewTrainee pnt;
        if (batchId != 0) {
            pnt = new PanNewTrainee(batchId);
        } else if (trainee != null) {
            pnt = new PanNewTrainee(trainee);
        } else {
            pnt = new PanNewTrainee();
        }
        return pnt;
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
