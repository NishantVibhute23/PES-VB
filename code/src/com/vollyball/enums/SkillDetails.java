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
public enum SkillDetails {

    Service1(1, 1, 1, "Service Poor", ""),
    Service2(2, 1, 2, "Advantage in Pass Reaches to the oppenent Secondary Target", ""),
    Service3(3, 1, 3, "Advantage in Pass Reaches to the oppenent Secondary Target", ""),
    Service4(4, 1, 4, "Free ball to Service side", ""),
    Service5(5, 1, 5, "Ace Serve", ""),
    Attack1(6, 2, 1, "Service Error", ""),
    Attack2(7, 2, 2, "Advantage in Pass Reaches to the oppenent Secondary Target", ""),
    Attack3(8, 2, 3, "Advantage in Dig Reaches to the oppenent Secondary Target", ""),
    Attack4(9, 2, 4, "Free ball to Attack side", ""),
    Attack5(10, 2, 5, "Ace Attack", ""),
    Block1(11, 3, 1, "Block Error", ""),
    Block2(12, 3, 2, "Advantage out Pass Reaches to the oppenent Primary Target. Free ball for opponents. No effective block. Block reflected ball becomes set for opponents", ""),
    Block3(13, 3, 3, "Advantage in Dig Reaches to the Secondary Target of Opponent. Blocked out Ball reaches out of court of blockers Side. No perfect Attack", ""),
    Block4(14, 3, 4, "Free ball to Block side", ""),
    Block5(15, 3, 5, "Kill Block", ""),
    Set1(16, 4, 1, "Set Error", ""),
    Set2(17, 4, 2, "Advantage out organised Group block situation under easy pass or dig. Non effective set for attacker. Free ball situation to the opponents side", ""),
    Set3(18, 4, 3, "Advantage in unorganised Double Block Situation", ""),
    Set4(19, 4, 4, "Organised Single block situation", ""),
    Set5(20, 4, 5, "No Block Situation. Most favourable set for attacker. Best conversion of poor pass into effective set. Setter act as attacker.", ""),
    Reception1(21, 5, 1, "Reception Error", ""),
    Reception2(22, 5, 2, "Advantage in Pass Reaches to the oppenent Secondary Target", ""),
    Reception3(23, 5, 3, "Advantage out Serve reception reaches secondary target 2 and setter does not execute set for desire attack combination. Only high set attack is possible against serve reception.", ""),
    Reception4(24, 5, 4, "Serve reception reaches secondary target 1 and setter has limited scope to execute a set for combinations of attack", ""),
    Reception5(25, 5, 5, "Serve reception reaches primary target and setter has full scope to execute a set for all possible combination of attack. Reception becomes set for setter to attack or imitate attack and perform set. Most favourable situation to organise attack with all possible options.", ""),
    Defence1(26, 6, 1, "Dig Error", ""),
    Defence2(27, 6, 2, "Advantage out Free ball situaion to oppenets. Easy dig reaches to the secondary target. Non effective pass", ""),
    Defence3(28, 6, 3, "Advantage in Easy dig reaches to primary target. Dig become sudden set for attacker", ""),
    Defence4(29, 6, 4, "Received most difficult ball with extraordinary dig action and ball reaches secondary target. Tactical dig becomes set for attackers.", ""),
    Defence5(30, 6, 5, "Received most difficult ball extraordinary dig action and ball reached primary target", "");

    int id;
    int skillId;
    int ratingId;
    String name;
    String description;

    private SkillDetails(int id, int skillId, int ratingId, String name, String description) {
        this.id = id;
        this.skillId = skillId;
        this.ratingId = ratingId;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
