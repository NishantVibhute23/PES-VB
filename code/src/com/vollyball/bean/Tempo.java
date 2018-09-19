/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.bean;

/**
 *
 * @author guruprasad.tiwari
 */
public class Tempo {

    public SuccessFailure high;
    public SuccessFailure medium;
    public SuccessFailure low;

    public SuccessFailure getHigh() {
        return high;
    }

    public void setHigh(SuccessFailure high) {
        this.high = high;
    }

    public SuccessFailure getMedium() {
        return medium;
    }

    public void setMedium(SuccessFailure medium) {
        this.medium = medium;
    }

    public SuccessFailure getLow() {
        return low;
    }

    public void setLow(SuccessFailure low) {
        this.low = low;
    }

}
