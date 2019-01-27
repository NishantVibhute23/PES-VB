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
 * @author nishant.vibhute
 */
public enum SkillsDescCriteria {

    ServiceA(1, " Type & Techniques of Service", 1, 1),
    ServiceB(2, " Serve Tactics", 1, 0),
    ServiceC(3, " Direction of Service", 1, 0),
    ServiceD(4, " Serve From Zone", 1, 0),
    ServiceE(5, " Serve To Zone", 1, 0),
    ServiceF(6, " Reception formation", 1, 1),
    ServiceG(7, " Receiver Position", 1, 0),
    ServiceH(8, " Score at the time of ace serve", 1, 0),
    ServiceI(9, " Serve in situation", 1, 0),
    ServiceJ(10, " Opponent Setter Position", 1, 0),
    AttackA(11, " Type & Techniques of attack", 2, 1),
    AttackB(12, " Attack Combination", 2, 1),
    AttackC(13, " Attacking Tactics", 2, 0),
    AttackD(14, " Attack on Tempo", 2, 1),
    AttackE(15, " Attacking From Zone", 2, 0),
    AttackF(16, "Attacking To Zone", 2, 0),
    AttackG(17, " Attack in phase", 2, 1),
    AttackH(18, " Attackers position", 2, 0),
    AttackI(19, "No. of Blockers", 2, 1),
    AttackJ(20, " Opponent Defence Formation", 2, 1),
    AttackK(21, " Attack Defended Zone", 2, 0),
    AttackL(22, " Defender Role", 2, 0),
    AttackM(23, " Score at time of attack", 2, 0),
    AttackN(24, " Setter position", 2, 0),
    AttackO(25, "Direction of Attack", 2, 0),
    AttackP(26, "Type of Block", 2, 1),
    BlockA(27, " Type of Block", 3, 1),
    BlockB(28, "Technique of Block", 3, 1),
    BlockC(29, "Block on Type of Attack", 3, 1),
    BlockD(30, " Block on Combination of attack", 3, 1),
    BlockE(31, " Block on Attacking Tempo", 3, 1),
    BlockF(32, "Opponent Attacking Zone", 3, 0),
    BlockG(33, " Blocking Zone", 3, 0),
    BlockH(34, "Block Deflected ball at Zone", 3, 0),
    BlockI(35, " Blocking in phase", 3, 1),
    BlockJ(36, " Blockers Position", 3, 0),
    BlockK(37, " No. of Blockers", 3, 1),
    BlockL(38, " Defence Formation", 3, 1),
    BlockM(39, " Block Defended court", 3, 0),
    BlockN(40, " Score at time of Block", 3, 0),
    BlockO(41, "Opponent Setter Position", 3, 0),
    SetA(42, " Type of Set", 4, 1),
    SetB(43, " Set Tempo", 4, 1),
    SetC(44, " Reception at", 4, 1),
    SetD(45, "Reception Formation", 4, 1),
    SetE(46, "Parabolla of received ball", 4, 1),
    SetF(47, "Set delivery from Zone", 4, 0),
    SetG(48, "Set delivery to Zone", 4, 0),
    SetH(49, "Combination of attack", 4, 1),
    SetI(50, "No. of Blockers", 4, 1),
    SetJ(51, "Game of phase", 4, 1),
    SetK(52, "Attackers position", 4, 0),
    SetL(53, "Score at the time of set", 4, 0),
    ReceptionA(54, "Type of Serve", 5, 1),
    ReceptionB(55, "Reception Formation", 5, 1),
    ReceptionC(56, "Reception From Zone", 5, 0),
    ReceptionD(57, "Reception To Zone", 5, 0),
    ReceptionE(58, "Receiver Position", 5, 0),
    ReceptionF(59, "Parabola of Received ball for setter", 5, 1),
    ReceptionG(60, "Reception at", 5, 1),
    //    ReceptionH(61, "Type of set", 5, 1),
    //    ReceptionI(62, "Set tempo", 5, 1),
    ReceptionJ(63, "Score at the time of Reception", 5, 0),
    ReceptionK(64, "Setter Position", 5, 0),
    DefenceS(90, "Type of Defended Ball", 6, 1),
    DefenceA(65, "Type of Attack by opponent", 6, 1),
    DefenceB(66, "Attack on Tempo", 6, 1),
    DefenceC(67, "Combination of Attack", 6, 1),
    DefenceD(68, "Blocking at Zone", 6, 0),
    DefenceE(69, "No. of Blockers", 6, 1),
    DefenceF(70, "Block Cover", 6, 1),
    DefenceG(71, "Defence System ", 6, 1),
    DefenceH(72, "Defence Sent From Zone", 6, 0),
    DefenceI(73, "Defence Sent To Zone", 6, 0),
    DefenceJ(74, "Defenders Role", 6, 0),
    DefenceK(75, "Defence Ball At", 6, 1),
    DefenceL(76, "Parabola of Defended Ball for Setter", 6, 1),
    DefenceM(77, " Defence in phase", 6, 1),
    DefenceN(78, " Setter position", 6, 0),
    //    DefenceO(79, " Type of set", 6, 1),
    DefenceP(80, " Score at time of Defence", 6, 0),
    DefenceQ(81, "Direction of Attack", 6, 0),
    ServiceDig(82, "Diagram Points", 1, 0),
    AttackDig(83, "Diagram Points", 2, 0),
    BlockDig(84, "Diagram Points", 3, 0),
    SetDig(85, "Diagram Points", 4, 0),
    ReceptionDig(86, "Diagram Points", 5, 0),
    DefenceDig(87, "Diagram Points", 6, 0),
    BlockQ(88, "Direction Of Block", 3, 0),
    //    SetN(89, "Attack Cover", 4, 1),

    AttackR(91, "Ball Reflected Zone", 2, 0),
    AttackS(92, "Attack Approach Run from", 2, 0),
    AttackT(93, "Attack Approach Run to", 2, 0),
    AttackU(94, "Type of approach run", 2, 1),
    DefenceT(95, "Attack Cover", 6, 1);

    int id;
    String type;
    int skillId;
    int view;

    private SkillsDescCriteria(int id, String type, int skillId, int view) {
        this.id = id;
        this.type = type;
        this.skillId = skillId;
        this.view = view;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public static List<SkillsDescCriteria> getTypeBySkill(int skillId) {
        List<SkillsDescCriteria> list = new ArrayList<>();
        for (SkillsDescCriteria e : values()) {
            if (e.skillId == skillId) {
                list.add(e);
            }
        }
        return list;
    }

    public static SkillsDescCriteria getTypeByName(String name) {

        for (SkillsDescCriteria e : values()) {
            if (e.type.equals(name)) {
                return e;
            }
        }
        return null;
    }

    public static List<SkillsDescCriteria> getSkillDescCriteriaBySkillandView(int skillId) {
        List<SkillsDescCriteria> list1 = new ArrayList<>();
        List<SkillsDescCriteria> list2 = new ArrayList<>();
        for (SkillsDescCriteria e : values()) {
            if (e.skillId == skillId && e.view == 1) {
                list1.add(e);
            }
            if (e.skillId == skillId && e.view == 0) {
                list2.add(e);
            }
        }
        list1.addAll(list2);
        return list1;
    }

    public static List<SkillsDescCriteria> getViewableSkillDescCriteriaBySkill(int skillId) {
        List<SkillsDescCriteria> list1 = new ArrayList<>();
        List<SkillsDescCriteria> list2 = new ArrayList<>();
        for (SkillsDescCriteria e : values()) {
            if (e.skillId == skillId && e.view == 1) {
                list1.add(e);
            }
//            if (e.skillId == skillId && e.view == 0) {
//                list2.add(e);
//            }
        }
        list1.addAll(list2);
        return list1;
    }

}
