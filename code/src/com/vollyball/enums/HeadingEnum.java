package com.vollyball.enums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author guruprasad.tiwari
 */
public enum HeadingEnum {

    HeadinEnum1(1, "Player Position"),
    HeadinEnum2(2, "Court Side"),
    HeadinEnum3(3, "Reception Formation"),
    HeadinEnum4(4, "Attack Combination"),
    HeadinEnum5(5, "Attack Tempo"),
    HeadinEnum6(6, "Complex Phase"),
    HeadinEnum7(7, "Opponent Player"),
    HeadinEnum8(8, "No. of Blocker "),
    HeadinEnum9(9, "Defence Formation"),
    HeadinEnum10(10, "Technique of Attack"),
    HeadinEnum11(11, "Technique of Block"),
    HeadinEnum12(12, "Ball Parabola"),
    HeadinEnum13(13, "Type Service"),
    HeadinEnum14(14, "Type of Set"),
    HeadinEnum15(15, "Ball at Net"),
    HeadinEnum16(16, "Block and Defence Tactic"),
    HeadinEnum17(17, "Type of Block");

    String longForm;
    int id;

    HeadingEnum(int id, String longForm) {
        this.longForm = longForm;
        this.id = id;
    }

    public String getLongForm() {
        return longForm;
    }

    public void setLongForm(String longForm) {
        this.longForm = longForm;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
