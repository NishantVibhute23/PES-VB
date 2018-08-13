/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author #dabbu
 */
public class TrainingBatch {
    int id;
    String name;
    String shortCode;
    String trainer;
    String medicalOffice;
    String analyzer;
    List<Trainee> traineeList = new ArrayList<>();

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

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }


    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getMedicalOffice() {
        return medicalOffice;
    }

    public void setMedicalOffice(String medicalOffice) {
        this.medicalOffice = medicalOffice;
    }

    public String getAnalyzer() {
        return analyzer;
    }

    public void setAnalyzer(String analyzer) {
        this.analyzer = analyzer;
    }

    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public void setTraineeList(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }
    
    
    
}
