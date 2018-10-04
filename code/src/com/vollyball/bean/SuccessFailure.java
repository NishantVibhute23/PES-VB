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
public class SuccessFailure {

    public int success;
    public int failure;
    public int totalAttempt;

    public double successRate;
    public double failureRate;

    public String successPerc;
    public String failurePerc;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public String getSuccessPerc() {
        return successPerc;
    }

    public void setSuccessPerc(String successPerc) {
        this.successPerc = successPerc;
    }

    public String getFailurePerc() {
        return failurePerc;
    }

    public void setFailurePerc(String failurePerc) {
        this.failurePerc = failurePerc;
    }

    public double getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(double successRate) {
        this.successRate = successRate;
    }

    public double getFailureRate() {
        return failureRate;
    }

    public void setFailureRate(double failureRate) {
        this.failureRate = failureRate;
    }

    public int getTotalAttempt() {
        return totalAttempt;
    }

    public void setTotalAttempt(int totalAttempt) {
        this.totalAttempt = totalAttempt;
    }

}
