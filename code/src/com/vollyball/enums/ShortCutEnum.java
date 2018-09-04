package com.vollyball.enums;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author guruprasad.tiwari
 */
public enum ShortCutEnum {

    ShortCutEnum1(1, "S", "S", "Setter", 1),
    ShortCutEnum2(2, "A", "OH", "Outside Hitter/Ace Hitter", 1),
    ShortCutEnum3(3, "M", "MB", "Middle Blocker", 1),
    ShortCutEnum4(4, "U", "U", "Universal Player", 1),
    ShortCutEnum5(5, "LB", "LB", "Libero", 1),
    ShortCutEnum6(6, "H", "H", "Home", 2),
    ShortCutEnum7(7, "O", "Opp", "Opponent", 2),
    ShortCutEnum8(8, "5R", "5R", "5 Men Reception", 3),
    ShortCutEnum9(9, "4R", "4R", "4 Men Reception", 3),
    ShortCutEnum10(10, "3R", "3R", "3 Men Reception", 3),
    ShortCutEnum11(11, "2R", "2R", "2 Men Reception", 3),
    ShortCutEnum12(12, "55", "5C", "Five Man Attack Combination", 4),
    ShortCutEnum13(13, "44", "4C", "Four Man Attack Combination", 4),
    ShortCutEnum14(14, "33", "3C", "Three Man Attack Combination", 4),
    ShortCutEnum15(15, "22", "2C", "Two Man Attack Combination", 4),
    ShortCutEnum16(16, "T1", "T1", "Tempo 1", 5),
    ShortCutEnum17(17, "T2", "T2", "Tempo 2", 5),
    ShortCutEnum18(18, "T3", "T3", "Tempo 3", 5),
    ShortCutEnum19(19, "Q1", "K1", "Complex 1 phase of the game", 6),
    ShortCutEnum20(20, "Q2", "K2", "Complex 2 phase of the game", 6),
    ShortCutEnum21(21, "Q3", "TP", "Transition phase of the game", 6),
    ShortCutEnum22(22, "AA", "OOH", "Opponent Outside Hitter", 7),
    ShortCutEnum23(23, "MM", "OMB", "Opponent Middle Block", 7),
    ShortCutEnum24(24, "UU", "OU", "Opponent Universal", 7),
    ShortCutEnum25(25, "SS", "OS", "Opponent Setter", 7),
    ShortCutEnum26(26, "E1", "SGL", "One Block", 8),
    ShortCutEnum27(27, "E2", "DBL", "Two Block", 8),
    ShortCutEnum28(28, "E3", "TPL", "Three Block", 8),
    ShortCutEnum29(29, "D1", "ODF", "1-2-3", 9),
    ShortCutEnum30(30, "D11", "ODF", "1-3-2", 9),
    ShortCutEnum31(31, "D2", "ODF", "2-1-2", 9),
    ShortCutEnum32(32, "D22", "ODF", "2-0-4", 9),
    ShortCutEnum33(33, "D3", "ODF", "3-1-3", 9),
    ShortCutEnum34(34, "D33", "ODF", "3-0-3 ", 9),
    ShortCutEnum35(35, "D333", "ODF", "3-2-1   ", 9),
    ShortCutEnum36(36, "A1", "IN", "Inward Attack", 10),
    ShortCutEnum37(37, "A2", "OT", "Outward Attack", 10),
    ShortCutEnum38(38, "A3", "BT", "Body Turn Attack", 10),
    ShortCutEnum39(39, "A4", "OL", "One Leg Attack", 10),
    ShortCutEnum40(40, "A5", "D", "Drop", 10),
    ShortCutEnum41(41, "A6", "BC", "Block to Back Court Attack", 10),
    ShortCutEnum42(42, "B1", "CB", "Commit Block", 11),
    ShortCutEnum43(43, "B2", "RR", "Read and React Block", 11),
    ShortCutEnum44(44, "B3", "ZB", "Zone Block", 11),
    ShortCutEnum45(45, "F", "F", "Favourable", 12),
    ShortCutEnum46(46, "F1", "SF", "Semi-Favourable", 12),
    ShortCutEnum47(47, "F0", "NF", "Non-Favourable", 12),
    ShortCutEnum48(48, "S1", "JF", "Jump Float", 13),
    ShortCutEnum49(49, "S2", "JS", "Jump Spin", 13),
    ShortCutEnum50(50, "S3", "JP", "Jump Power", 13),
    ShortCutEnum51(51, "S4", "SF", "Standing Float", 13),
    ShortCutEnum52(52, "S5", "SS", "Standing Spin", 13),
    ShortCutEnum53(53, "W1", "JS", "Jump Set", 14),
    ShortCutEnum54(54, "W2", "RB", "Raising ball", 14),
    ShortCutEnum55(55, "W3", "FP", "Full Push Ball", 14),
    ShortCutEnum56(56, "W4", "HP", "Half Push Ball", 14),
    ShortCutEnum57(57, "W5", "BC", "Back Court", 14),
    ShortCutEnum58(58, "N1", "ON", "Over Net favour setter", 15),
    ShortCutEnum59(59, "N2", "CN", "Close to Net favour setter", 15),
    ShortCutEnum60(60, "N3", "AN", "Away from Net non-favour setter", 15),
    ShortCutEnum61(61, "N4", "LT", "Low trajectory non-favour setter", 15),
    ShortCutEnum62(62, "LO", "LO", "Line open block", 16),
    ShortCutEnum63(63, "LK", "LC", "line close block", 16),
    ShortCutEnum64(64, "K", "Kill", "Aggressive Block Score direct point", 17),
    ShortCutEnum65(65, "D", "SF", "Soft/Defensive Block", 17),
    ShortCutEnum66(66, "LI", "LI", "Line to Inward block", 17),
    ShortCutEnum67(67, "CB", "CB", "Crosscourt Block", 17);

    int shortCutId, headingId;
    String code, abbr, longForm;

    ShortCutEnum(int shortCutId, String code, String abbr, String longForm, int headingId) {
        this.shortCutId = shortCutId;
        this.headingId = headingId;
        this.code = code;
        this.abbr = abbr;
        this.longForm = longForm;
    }

    public int getShortCutId() {
        return shortCutId;
    }

    public void setShortCutId(int shortCutId) {
        this.shortCutId = shortCutId;
    }

    public int getHeadingId() {
        return headingId;
    }

    public void setHeadingId(int headingId) {
        this.headingId = headingId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getLongForm() {
        return longForm;
    }

    public void setLongForm(String longForm) {
        this.longForm = longForm;
    }

    public static ShortCutEnum getBydId(int id) {
        for (ShortCutEnum e : values()) {
            if (e.shortCutId == id) {
                return e;
            }
        }
        return null;
    }

    public static List<ShortCutEnum> getByHeadingId(int id) {
        List<ShortCutEnum> list = new ArrayList<>();
        for (ShortCutEnum e : values()) {
            if (e.getHeadingId() == id) {
                list.add(e);
            }
        }
        return list;
    }
    
     public static String getLongFormById(int id){
        int id1;
        String lg = null;
        for (ShortCutEnum dir : ShortCutEnum.values()) {
           id1=dir.getShortCutId();
           if(id1==id){
               lg=dir.getLongForm();
           }
        }
        return lg;
    }

}