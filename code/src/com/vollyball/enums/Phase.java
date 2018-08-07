/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.enums;

/**
 *
 * @author Supriya
 */
public enum Phase {
    
    FINAL(1, "Final"),
    THIRDPLACE(2, "Third PlACE"),
    SEMIFINAL(3, "Semi Final"),
    QUARTERFINAL(4, "Quarter Final"),
    PREQUARTERFINAL(5, "PreQuarter Final"),
    POOL(6, "Pool");
    
    int id;
    String name;

    private Phase(int id, String name) {
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


    public static Phase getIdByName(String name) {
        for (Phase e : values()) {
            if (e.name.equals(name)) {
                return e;
            }
        }
        return null;
    }
}
