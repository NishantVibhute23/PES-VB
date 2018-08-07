/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

/**
 *
 * @author nishant.vibhute
 */
public class ArrowBean {

    int x3;
    int y3;
    int x4;
    int y4;
    Path2D path;
    Color color;
    Ellipse2D[] marks;

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public int getX4() {
        return x4;
    }

    public void setX4(int x4) {
        this.x4 = x4;
    }

    public int getY4() {
        return y4;
    }

    public void setY4(int y4) {
        this.y4 = y4;
    }

    public Path2D getPath() {
        return path;
    }

    public void setPath(Path2D path) {
        this.path = path;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
