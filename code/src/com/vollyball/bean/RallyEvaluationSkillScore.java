/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import java.util.LinkedHashMap;

/**
 *
 * @author nishant.vibhute
 */
public class RallyEvaluationSkillScore {

    int id;
    String skill;
    String chestNo;
    int score;
    int skillId;
    int playerId;
    int rallyId;
    boolean isDetailed;
    LinkedHashMap<Integer, String> detailsValues = new LinkedHashMap<Integer, String>();
    int orderNum;

    public int getRallyId() {
        return rallyId;
    }

    public void setRallyId(int rallyId) {
        this.rallyId = rallyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getChestNo() {
        return chestNo;
    }

    public void setChestNo(String chestNo) {
        this.chestNo = chestNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public LinkedHashMap<Integer, String> getDetailsValues() {
        return detailsValues;
    }

    public void setDetailsValues(LinkedHashMap<Integer, String> detailsValues) {
        this.detailsValues = detailsValues;
    }

    public boolean isIsDetailed() {
        return isDetailed;
    }

    public void setIsDetailed(boolean isDetailed) {
        this.isDetailed = isDetailed;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

}
