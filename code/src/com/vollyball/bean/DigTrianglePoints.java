/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import java.awt.Color;

/**
 *
 * @author nishant.vibhute
 */
public class DigTrianglePoints {

    String from;
    String toA;
    String toB;
    Color color;

    public DigTrianglePoints(String from, String toA, String toB, Color color) {
        this.from = from;
        this.toA = toA;
        this.toB = toB;
        this.color = color;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getToA() {
        return toA;
    }

    public void setToA(String toA) {
        this.toA = toA;
    }

    public String getToB() {
        return toB;
    }

    public void setToB(String toB) {
        this.toB = toB;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
