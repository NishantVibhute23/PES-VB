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
public class SetSubstitution {

    int id;
    int position;
    int rotation_player_id;
    int substitutePlayerId;
    String point1;
    String point2;
    int match_evaluation_id;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getRotation_player_id() {
        return rotation_player_id;
    }

    public void setRotation_player_id(int rotation_player_id) {
        this.rotation_player_id = rotation_player_id;
    }

    public int getSubstitutePlayerId() {
        return substitutePlayerId;
    }

    public void setSubstitutePlayerId(int substitutePlayerId) {
        this.substitutePlayerId = substitutePlayerId;
    }

    public String getPoint1() {
        return point1;
    }

    public void setPoint1(String point1) {
        this.point1 = point1;
    }

    public String getPoint2() {
        return point2;
    }

    public void setPoint2(String point2) {
        this.point2 = point2;
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
