/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.bean;

import javax.swing.JComboBox;

/**
 *
 * @author #dabbu
 */
public class MatchRowBean {

    JComboBox position;
    JComboBox trainee;

    public MatchRowBean(JComboBox trainee, JComboBox position) {
        this.position = position;
        this.trainee = trainee;
    }

    public JComboBox getPosition() {
        return position;
    }

    public void setPosition(JComboBox position) {
        this.position = position;
    }

    public JComboBox getTrainee() {
        return trainee;
    }

    public void setTrainee(JComboBox trainee) {
        this.trainee = trainee;
    }

}
