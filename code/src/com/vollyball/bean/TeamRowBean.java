/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author nishant.vibhute
 */
public class TeamRowBean {

    JTextField srNo;
    JTextField name;
    JTextField chestnum;
    JComboBox position;
    JCheckBox captain;

    public TeamRowBean(JTextField name,
            JTextField chestnum,
            JComboBox position, JCheckBox captain, JTextField srNo) {
        this.name = name;
        this.chestnum = chestnum;
        this.position = position;
        this.captain = captain;
        this.srNo = srNo;

    }

    public JTextField getSrNo() {
        return srNo;
    }

    public void setSrNo(JTextField srNo) {
        this.srNo = srNo;
    }

    public JTextField getName() {
        return name;
    }

    public void setName(JTextField name) {
        this.name = name;
    }

    public JTextField getChestnum() {
        return chestnum;
    }

    public void setChestnum(JTextField chestnum) {
        this.chestnum = chestnum;
    }

    public JComboBox getPosition() {
        return position;
    }

    public void setPosition(JComboBox position) {
        this.position = position;
    }

    public JCheckBox getCaptain() {
        return captain;
    }

    public void setCaptain(JCheckBox captain) {
        this.captain = captain;
    }

}
