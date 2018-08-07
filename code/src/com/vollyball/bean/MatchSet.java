/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nishant.vibhute
 */
public class MatchSet {

    int id;
    int matchEvaluationTeamId;
    int evaluationTeamId;
    int opponentTeamId;
    int setNo;
    int homeScore;
    int opponentScore;
    int won_by;
    String start_time;
    String end_time;
    String evaluator;
    String date;
    int op;
    int tf;
    int rallyCount;

    List<SetRotationOrder> rotationOrder = new ArrayList<>();
    List<SetSubstitution> setSubstitutions = new ArrayList<>();
    List<SetTimeout> setTimeout = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchEvaluationTeamId() {
        return matchEvaluationTeamId;
    }

    public void setMatchEvaluationTeamId(int matchEvaluationTeamId) {
        this.matchEvaluationTeamId = matchEvaluationTeamId;
    }

    public int getEvaluationTeamId() {
        return evaluationTeamId;
    }

    public void setEvaluationTeamId(int evaluationTeamId) {
        this.evaluationTeamId = evaluationTeamId;
    }

    public int getOpponentTeamId() {
        return opponentTeamId;
    }

    public void setOpponentTeamId(int opponentTeamId) {
        this.opponentTeamId = opponentTeamId;
    }

    public int getSetNo() {
        return setNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
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

    public int getWon_by() {
        return won_by;
    }

    public void setWon_by(int won_by) {
        this.won_by = won_by;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getEvaluator() {
        return evaluator;
    }

    public void setEvaluator(String evaluator) {
        this.evaluator = evaluator;
    }

    public List<SetRotationOrder> getRotationOrder() {
        return rotationOrder;
    }

    public void setRotationOrder(List<SetRotationOrder> rotationOrder) {
        this.rotationOrder = rotationOrder;
    }

    public List<SetSubstitution> getSetSubstitutions() {
        return setSubstitutions;
    }

    public void setSetSubstitutions(List<SetSubstitution> setSubstitutions) {
        this.setSubstitutions = setSubstitutions;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public List<SetTimeout> getSetTimeout() {
        return setTimeout;
    }

    public void setSetTimeout(List<SetTimeout> setTimeout) {
        this.setTimeout = setTimeout;
    }

    public int getRallyCount() {
        return rallyCount;
    }

    public void setRallyCount(int rallyCount) {
        this.rallyCount = rallyCount;
    }

}
