/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.bean;

/**
 *
 * @author #dabbu
 */
public class Trainee {
    
    int id;
    String name;
    String chestNo;
    int traineeBatchId;
    int position;
    String batchName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChestNo() {
        return chestNo;
    }

    public void setChestNo(String chestNo) {
        this.chestNo = chestNo;
    }

    public int getTraineeBatchId() {
        return traineeBatchId;
    }

    public void setTraineeBatchId(int traineeBatchId) {
        this.traineeBatchId = traineeBatchId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }
    
    
    
}
