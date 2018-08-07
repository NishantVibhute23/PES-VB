/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

/**
 *
 * @author nishant.vibhute
 */
public class SetRotationOrder {

    int id;
    int position;
    int playerId;
    int match_evaluation_id;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getMatch_evaluation_id() {
        return match_evaluation_id;
    }

    public void setMatch_evaluation_id(int match_evaluation_id) {
        this.match_evaluation_id = match_evaluation_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
