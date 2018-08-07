/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class RallyEvaluation {

    int id;
    int rallyNum;
    int homeScore;
    int opponentScore;
    String startTime;
    String endTime;
    int matchEvaluationId;
    int op;
    int tf;
    String scoreSubtracted;
    int startby = 0;
    int wonby = 0;
    List<RallyEvaluationSkillScore> rallyEvaluationSkillScore = new ArrayList<>();
    public LinkedHashMap<Integer, Player> rallyPositionMap = new LinkedHashMap<Integer, Player>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRallyNum() {
        return rallyNum;
    }

    public void setRallyNum(int rallyNum) {
        this.rallyNum = rallyNum;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getOpponentScore() {
        return opponentScore;
    }

    public void setOpponentScore(int opponentScore) {
        this.opponentScore = opponentScore;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public List<RallyEvaluationSkillScore> getRallyEvaluationSkillScore() {
        return rallyEvaluationSkillScore;
    }

    public void setRallyEvaluationSkillScore(List<RallyEvaluationSkillScore> rallyEvaluationSkillScore) {
        this.rallyEvaluationSkillScore = rallyEvaluationSkillScore;
    }

    public int getMatchEvaluationId() {
        return matchEvaluationId;
    }

    public void setMatchEvaluationId(int matchEvaluationId) {
        this.matchEvaluationId = matchEvaluationId;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }

    public int getTf() {
        return tf;
    }

    public void setTf(int tf) {
        this.tf = tf;
    }

    public String getScoreSubtracted() {
        return scoreSubtracted;
    }

    public void setScoreSubtracted(String scoreSubtracted) {
        this.scoreSubtracted = scoreSubtracted;
    }

    public LinkedHashMap<Integer, Player> getRallyPositionMap() {
        return rallyPositionMap;
    }

    public void setRallyPositionMap(LinkedHashMap<Integer, Player> rallyPositionMap) {
        this.rallyPositionMap = rallyPositionMap;
    }

    public int getStartby() {
        return startby;
    }

    public void setStartby(int startby) {
        this.startby = startby;
    }

    public int getWonby() {
        return wonby;
    }

    public void setWonby(int wonby) {
        this.wonby = wonby;
    }

}
