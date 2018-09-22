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
public class PlayerPositionBean {

    public LinkedHashMap<Integer, Player> pos = new LinkedHashMap<Integer, Player>();
    int wonby;
    int rotationCount = 0;
    int successPerc = 0;
    int lossPerc = 0;
    int totalSuccess = 0;
    int totalLoss = 0;

    public LinkedHashMap<Integer, Player> getPos() {
        return pos;
    }

    public void setPos(LinkedHashMap<Integer, Player> pos) {
        this.pos = pos;
    }

    public int getWonby() {
        return wonby;
    }

    public void setWonby(int wonby) {
        this.wonby = wonby;
    }

    public int getRotationCount() {
        return rotationCount;
    }

    public void setRotationCount(int rotationCount) {
        this.rotationCount = rotationCount;
    }

    public int getSuccessPerc() {
        return successPerc;
    }

    public void setSuccessPerc(int successPerc) {
        this.successPerc = successPerc;
    }

    public int getLossPerc() {
        return lossPerc;
    }

    public void setLossPerc(int lossPerc) {
        this.lossPerc = lossPerc;
    }

    public int getTotalSuccess() {
        return totalSuccess;
    }

    public void setTotalSuccess(int totalSuccess) {
        this.totalSuccess = totalSuccess;
    }

    public int getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(int totalLoss) {
        this.totalLoss = totalLoss;
    }

}
