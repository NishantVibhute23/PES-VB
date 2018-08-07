package com.vollyball.enums;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author nishant.vibhute
 */
public enum SetupEnum {

    Database(1, "Database Created"),
    User(2, "user"),
    Competition(3, "Competition Created"),
    Teams(4, "Teams"),
    Players(5, "Players"),
    Pool(6, "Pool Created"),
    Matches(7, "Matches Created"),
    MatchesEvaluationTeam(8, "Matches Evaluation Team Created"),
    MSkills(9, "m_skills"),
    MRating(10, "m_rating"),
    MSkillDescCriteria(11, "m_skill_desc_criteria"),
    MSkillDetails(12, "m_skill_details"),
    MSkillDescCriteriaPoint(13, "m_skill_desc_criteria_point"),
    MatchEvaluationSet(14, "match_evaluation"),
    Rally(15, "rally"),
    RallyDetails(16, "rally_details"),
    RallyDetailsCriteria(17, "rally_details_criteria"),
    ROTATIONORDER(18, "Rotation Order"),
    SUBSTITUTION(19, "Substituition"),
    PLUSMINUS(20, "Plus Minus"),
    TIMEOUT(21, "Time out"),
    RALLYROTATIONORDER(22, "Rally Rotation Order"),
    InsertRating(23, "Ratings Inserted"),
    InsertSkills(24, "Skills Inserted"),
    InsertSkillDetails(25, "Skill details Inserted"),
    InsertUser(26, "User Inserted"),
    MatchPlayers(27, "Match Player"),
    VollyCoordinate(28, "Volly Coordinate"),
    InsertVollyCoordinate(29, "Insert Volly Coordinate");

    int step;

    String value;

    SetupEnum(int step, String value) {
        this.step = step;
        this.value = value;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
