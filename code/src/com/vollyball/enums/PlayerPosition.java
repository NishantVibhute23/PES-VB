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
public enum PlayerPosition {

    SELECT(0, "Select"),
    SERVER(1, "Server"),
    LIBERO(2, "Libero"),
    UNIVERSAL(3, "Universal"),
    OUTSIDEHITTER(4, "Outside Hitter"),
    MIDDLEBLOCKER(5, "Middle Blocker"),
    SETTER(6, "Setter");

    int id;
    String name;

    private PlayerPosition(int id, String name) {
        this.id = id;
        this.name = name;
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
