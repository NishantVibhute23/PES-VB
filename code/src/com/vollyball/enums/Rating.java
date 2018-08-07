/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.enums;

/**
 *
 * @author nishant.vibhute
 */
public enum Rating {

    Poor(1, "Poor"),
    BelowAverage(2, "Below Average"),
    Average(3, "Average"),
    AboveAverage(4, "Above Average"),
    Excellent(5, "Excellent");

    int id;

    String type;

    Rating(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
