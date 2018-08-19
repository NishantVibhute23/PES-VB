/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.bean;


import javax.swing.JTextField;

/**
 *
 * @author #dabbu
 */
public class BatchRowBean {
    JTextField srNo;
    JTextField name;

    public BatchRowBean(JTextField name,JTextField srNo) {
        this.srNo = srNo;
        this.name = name;
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
    
    

    
}
