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
public enum HomeOpponent {

    HOME(1, "Home"),
    OPPONENT(2, "Opponent");

    int id;
    String name;

    private HomeOpponent(int id, String name) {
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

}
