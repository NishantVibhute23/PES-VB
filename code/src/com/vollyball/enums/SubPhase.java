/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.enums;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Supriya
 */
public enum SubPhase {
 
    
    SemiFinal1(1,"1",3),
SemiFinal2(2,"2",3),
QuarterFinal1(3,"1",4),
QuarterFinal2(4,"2",4),
QuarterFinal3(5,"3",4),
QuarterFinal4(6,"4",4),
PreQuarterFinal1(7,"1",5),
PreQuarterFinal2(8,"2",5),
PreQuarterFinal3(9,"3",5),
PreQuarterFinal4(10,"4",5),
PreQuarterFinal5(11,"5",5),
PreQuarterFinal6(12,"6",5),
PreQuarterFinal7(13,"7",5),
PreQuarterFinal8(14,"8",5),
PoolA(15,"A",6),
PoolB(16,"B",6),
PoolC(17,"C",6),
PoolD(18,"D",6),
PoolE(19,"E",6),
PoolF(20,"F",6),
PoolG(21,"G",6),
PoolH(22,"H",6),
PoolI(23,"I",6),
PoolJ(24,"J",6),
PoolK(25,"K",6),
PoolL(26,"L",6),
PoolM(27,"M",6),
PoolN(28,"N",6),
PoolO(29,"O",6),
PoolP(30,"P",6),
PoolQ(31,"Q",6),
PoolR(32,"R",6),
PoolS(33,"S",6),
PoolT(34,"T",6),
PoolU(35,"U",6),
PoolV(36,"V",6),
PoolW(37,"W",6),
PoolX(38,"X",6),
PoolY(39,"Y",6),
PoolZ(40,"Z",6);

    
    int id;
    String name;
    int phaseId;

    private SubPhase(int id, String name,int phaseId) {
        this.id = id;
        this.name = name;
        this.phaseId= phaseId;
    }

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
    
    
     public static List<SubPhase> getTypeByPhaseId(int pahseId) {
        List<SubPhase> list = new ArrayList<>();
        for (SubPhase e : values()) {
            if (e.phaseId == pahseId) {
                list.add(e);
            }
        }
        return list;
    }

}
