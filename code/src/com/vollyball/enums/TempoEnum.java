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
public enum TempoEnum {

    High("High", 90),
    Medium("Medium", 60),
    Low("Low", 30);

    String name;
    int value;

    private TempoEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static int getTempoByName(String name) {
        for (TempoEnum te : values()) {
            if (te.getName().equalsIgnoreCase(name)) {
                return te.getValue();
            }
        }
        return 0;
    }

}
