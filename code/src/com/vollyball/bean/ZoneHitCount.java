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
public class ZoneHitCount {

    int total = 0;
    int success = 0;
    int failure = 0;
    int successPerc = 0;
    int failPerc = 0;
    boolean isMaxSuccess = false;
    boolean isMaxFail = false;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public int getSuccessPerc() {
        return successPerc;
    }

    public void setSuccessPerc(int successPerc) {
        this.successPerc = successPerc;
    }

    public int getFailPerc() {
        return failPerc;
    }

    public void setFailPerc(int failPerc) {
        this.failPerc = failPerc;
    }

    public boolean isIsMaxSuccess() {
        return isMaxSuccess;
    }

    public void setIsMaxSuccess(boolean isMaxSuccess) {
        this.isMaxSuccess = isMaxSuccess;
    }

    public boolean isIsMaxFail() {
        return isMaxFail;
    }

    public void setIsMaxFail(boolean isMaxFail) {
        this.isMaxFail = isMaxFail;
    }

}
