/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.training.bean;

import java.util.List;

/**
 *
 * @author #dabbu
 */
public class BatchMatch {
    
    public int batchId;
    public String team1Name;
    public String team2Name;
    public List<Trainee> team1List;
    public List<Trainee> team2List;

    public String getTeam1Name() {
        return team1Name;
    }

    public void setTeam1Name(String team1Name) {
        this.team1Name = team1Name;
    }

    public String getTeam2Name() {
        return team2Name;
    }

    public void setTeam2Name(String team2Name) {
        this.team2Name = team2Name;
    }

    public List<Trainee> getTeam1List() {
        return team1List;
    }

    public void setTeam1List(List<Trainee> team1List) {
        this.team1List = team1List;
    }

    public List<Trainee> getTeam2List() {
        return team2List;
    }

    public void setTeam2List(List<Trainee> team2List) {
        this.team2List = team2List;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

     
    
    
    
    
}
