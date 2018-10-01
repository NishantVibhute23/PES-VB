/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.enums;

import java.awt.Color;

/**
 *
 * @author nishant.vibhute
 */
public enum PlayerPosition {

    SELECT(0, "Select", Color.WHITE),
    SERVER(1, "Server", Color.ORANGE),
    LIBERO(2, "Libero", Color.BLACK),
    UNIVERSAL(3, "Universal", new Color(192, 159, 1)),
    OUTSIDEHITTER(4, "Outside Hitter", Color.BLUE),
    MIDDLEBLOCKER(5, "Middle Blocker", new Color(29, 152, 68)),
    SETTER(6, "Setter", Color.RED);

    int id;
    String name;
    Color color;

    private PlayerPosition(int id, String name, Color color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static PlayerPosition getIdByName(String name) {
        for (PlayerPosition e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static PlayerPosition getNameById(int id) {
        for (PlayerPosition e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

}
