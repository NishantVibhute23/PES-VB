package com.vollyball.dialog;


import com.vollyball.bean.RallyEvaluationSkillScore;
import com.vollyball.panels.PanEvaluationRallyDetailsEdit;
import com.vollyball.panels.PanEvaluationRowDetail;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nishant.vibhute
 */
public class DialogPAnEvaluationRallyRowEdit {
     private JFrame parentFrame;
    private JDialog dialog;

    String skill;String chestNo;String score;
    RallyEvaluationSkillScore rallyEvaluationSkillScore;
    PanEvaluationRowDetail p;
    public void init(String skill,String chestNo,String score,RallyEvaluationSkillScore rallyEvaluationSkillScore,PanEvaluationRowDetail p) {
        try {
            this.skill = skill;
            this.chestNo = chestNo;
            this.score = score;
            this.rallyEvaluationSkillScore=rallyEvaluationSkillScore;
            this.p=p;
           

//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            this.dialog = new JDialog(this.parentFrame, "New Match", true);

            this.dialog.setResizable(true);
            this.dialog.getContentPane().add(createPane());
            this.dialog.pack();
            this.dialog.setSize(450, 470);//

            Dimension Size = Toolkit.getDefaultToolkit().getScreenSize();
            this.dialog.setLocation(new Double((Size.getWidth() / 2) - (dialog.getWidth() / 2)).intValue(), new Double((Size.getHeight() / 2) - (dialog.getHeight() / 2)).intValue());

        } catch (Exception ex) {

            Logger.getLogger(CreateMatchDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected Container createPane() {
        PanEvaluationRallyDetailsEdit panMatch = new PanEvaluationRallyDetailsEdit( skill, chestNo, score,rallyEvaluationSkillScore,p);

        return panMatch;
    }

    public void show() {
        if (this.dialog == null) {
            init( skill, chestNo, score,rallyEvaluationSkillScore,p);
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
