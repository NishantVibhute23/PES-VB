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
public enum Skill {

    Service(1, "Service"),
    Attack(2, "Attack"),
    Block(3, "Block"),
    Set(4, "Set"),
    Reception(5, "Reception"),
    Defence(6, "Defence"),
    OP(7, "OP+"),
    TF(8, "TF-");

    int id;

    String type;

    Skill(int id, String type) {
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

    public static Skill getIdByName(String name) {
        for (Skill e : values()) {
            if (e.type.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static Skill getNameById(int id) {
        for (Skill e : values()) {
            if (e.id == id) {
                return e;
            }
        }
        return null;
    }

}
