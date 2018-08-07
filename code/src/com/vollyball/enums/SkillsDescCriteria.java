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

    ServiceA(1, " Type & Techniques of Service", 1),
    ServiceB(2, " Serve Tactics", 1),
    ServiceC(3, " Direction of Service", 1),
    ServiceD(4, " Serve From Zone", 1),
    ServiceE(5, " Serve To Zone", 1),
    ServiceF(6, " Reception formation", 1),
    ServiceG(7, " Receiver Position", 1),
    ServiceH(8, " Score at the time of ace serve", 1),
    ServiceI(9, " Serve in situation", 1),
    ServiceJ(10, " Opponent Setter Position", 1),
    AttackA(11, " Type & Techniques of attack", 2),
    AttackB(12, " Attack Combination", 2),
    AttackC(13, " Attacking Tactics", 2),
    AttackD(14, " Attack on Tempo", 2),
    AttackE(15, " Attacking From Zone", 2),
    AttackF(16, "Attacking To Zone", 2),
    AttackG(17, " Attack in phase", 2),
    AttackH(18, " Attackers position", 2),
    AttackI(19, "Block", 2),
    AttackJ(20, " Opponent Defence Formation", 2),
    AttackK(21, " Attack Defended Zone", 2),
    AttackL(22, " Defender Role", 2),
    AttackM(23, " Score at time of attack", 2),
    AttackN(24, " Attack in situation", 2),
    AttackO(25, " Setter position", 2),
    BlockA(26, " Type  of Block", 3),
    BlockB(27, " Blocking Tactics", 3),
    BlockC(28, "Block on Type of Attack", 3),
    BlockD(29, " Block on Combination of attack", 3),
    BlockE(30, " Block on Attacking Tempo", 3),
    BlockF(31, "Opponent Attacking Zone", 3),
    BlockG(32, " Blocking Zone", 3),
    BlockH(33, "Block Reflected Zone", 3),
    BlockI(34, " Blocking in phase", 3),
    BlockJ(35, " Blockers Position", 3),
    BlockK(36, " No of Blockers", 3),
    BlockL(37, " Defence Formation", 3),
    BlockM(38, " Block Defended court", 3),
    BlockN(39, " Score at time of Block", 3),
    BlockO(40, " Block in situation", 3),
    BlockP(41, "Opponent Setter Position", 3),
    SetA(42, " Type of Set", 4),
    SetB(43, " Set Tempo", 4),
    SetC(44, " Reception at", 4),
    SetD(45, "Reception Formation", 4),
    SetE(46, "Parabolla of received ball", 4),
    SetF(47, "Set delivery from Zone", 4),
    SetG(48, "Set delivery to Zone", 4),
    SetH(49, " Combination of attack", 4),
    SetI(50, " Type of Attack", 4),
    SetJ(51, " Type of Blockers", 4),
    SetK(52, " Game of phase", 4),
    SetL(53, " Attackers position", 4),
    SetM(54, " Score at the time of set", 4),
    SetN(55, " Opponent Setter Position", 4),
    ReceptionA(56, " Type of Serve", 5),
    ReceptionB(57, " Reception Formation", 5),
    ReceptionC(58, "Reception From Zone", 5),
    ReceptionD(59, "Reception To Zone", 5),
    ReceptionE(60, " Receiver Position", 5),
    ReceptionF(61, "Parabola of Received ball for setter", 5),
    ReceptionG(62, " Reception at", 5),
    ReceptionH(63, " Type of set", 5),
    ReceptionI(64, " Set tempo", 5),
    ReceptionJ(65, " Score at the time of Reception", 5),
    ReceptionK(66, " Setter Position", 5),
    DefenceA(67, " Type of Attack by opponent", 6),
    DefenceB(68, "Attack on Tempo", 6),
    DefenceC(69, " Combination of Attack", 6),
    DefenceD(70, " Blocking at Zone", 6),
    DefenceE(71, " Type of Block", 6),
    DefenceF(72, "Block Cover", 6),
    DefenceG(73, "Defence Formation ", 6),
    DefenceH(74, "Defence Sent From Zone", 6),
    DefenceI(75, "Defence Sent To Zone", 6),
    DefenceJ(76, "Defenders Role", 6),
    DefenceK(77, "Defence Ball At", 6),
    DefenceL(78, "Parabola of Defended Ball for Setter", 6),
    DefenceM(79, " Defence in phase", 6),
    DefenceN(80, " Setter position", 6),
    DefenceO(81, " Type of set", 6),
    DefenceP(82, " Set Tempo", 6),
    DefenceQ(83, " Score at time of Defence", 6);

    int id;
    String type;
    int skillId;

    private SkillsDescCriteria(int id, String type, int skillId) {
        this.id = id;
        this.type = type;
        this.skillId = skillId;
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

}
