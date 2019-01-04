/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.util;

import com.vollyball.bean.DigPoints;
import com.vollyball.bean.Player;
import com.vollyball.bean.RallyEvaluation;
import com.vollyball.controller.Controller;
import com.vollyball.dao.RallyDao;
import com.vollyball.enums.PlayerPosition;
import com.vollyball.panels.ImagePanel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nishant.vibhute
 */
public class DetailUtils {

    public static String getDirection(String pan1, String pan2) {
        String line = "";
        DigPoints dp = ImagePanel.getPoints(pan1, pan2);
        int x1 = dp.getX1();
        int y1 = dp.getY1();
        int x2 = dp.getX2();
        int y2 = dp.getY2();
        double slope = (double) (y2 - y1) / (x2 - x1);
        slope = Math.abs(slope);
        if (slope == 0 || Double.isInfinite(slope)) {
            line = "SL";
        } else {
            line = "C";
        }
        return line;
    }

    public static String getServeTactics(String pan1, String pan2) {
        String tactics = "";
        List<String> corners = new ArrayList<>();
        corners.add("O1A");
        corners.add("O1B");
        corners.add("O1D");
        corners.add("O2A");
        corners.add("O2C");
        corners.add("O2D");
        corners.add("O4B");
        corners.add("O4C");
        corners.add("O4D");
        corners.add("O5A");
        corners.add("O5B");
        corners.add("O5C");
        if (corners.contains(pan2)) {
            tactics = "DC";
        }
        return tactics;
    }

    public static String getAttackingTactics(String pan1, String pan2) {
        String tactics = "";
        List<String> corners = new ArrayList<>();
        List<String> nel = new ArrayList<>();
        List<String> nsl = new ArrayList<>();
        List<String> blockTouch = new ArrayList<>();

        corners.add("O1A");
        corners.add("O1B");
//        corners.add("O1D");
        corners.add("O2A");
        corners.add("O2B");
//        corners.add("O2D");
        corners.add("O4A");
        corners.add("O4B");
//        corners.add("O4D");
        corners.add("O5A");
        corners.add("O5B");
//        corners.add("O5C");

//        nel.add("O1A");
//        nel.add("O1B");
        nel.add("O6A");
        nel.add("O6B");
//        nel.add("O5A");
//        nel.add("O5B");

//        nsl.add("O2A");
        nsl.add("O2D");
        nsl.add("O11A");
        nsl.add("O11D");
        nsl.add("O1D");
//        nsl.add("O4B");
//        nsl.add("O4C");
        nsl.add("O51B");
        nsl.add("O51C");
        nsl.add("O5C");

        blockTouch.add("O2D");
        blockTouch.add("O2C");
        blockTouch.add("O3D");
        blockTouch.add("O3C");
        blockTouch.add("O4D");
        blockTouch.add("O4C");

        if (corners.contains(pan2)) {
            tactics = "DC";
        } else if (nel.contains(pan2)) {
            tactics = "NEL";
        } else if (nsl.contains(pan2)) {
            tactics = "NSL";
        } else if (blockTouch.contains(pan2)) {
            tactics = "BT";
        }
        return tactics;
    }

    public static String getToZone(String pan) {
        String zone = "";
        zone = pan.substring(1, 3);
        List<String> backList = new ArrayList<>();
        backList.add("12");
        backList.add("52");
        backList.add("62");
        if (backList.contains(zone)) {
            zone = "BOC";
        } else {
            zone = pan.substring(1, 2);

            if (zone.equalsIgnoreCase("L")) {
                zone = "LOC";
            } else if (zone.equalsIgnoreCase("R")) {
                zone = "ROC";
            }
        }
        return zone;
    }

    public static String getFromZone(String pan) {
        String zone = "";
        zone = pan.substring(1, 2);
        return zone;
    }

    public static String getPosition(Player player) {
        String pos = "";
        switch (player.getPosition()) {
            case 1:
                pos = "S";
                break;
            case 2:
                pos = "L";
                break;
            case 3:
                pos = "U";
                break;
            case 4:
                pos = "OH";
                break;
            case 5:
                pos = "MB";
                break;
            case 6:
                pos = "S";
                break;

        }
        return pos;
    }

    public static String getReceiverPosition(String pan2) {
        int zone;
        String pos = "";

        if (isNumeric(pan2.substring(1, 2))) {
            zone = Integer.parseInt(pan2.substring(1, 2));
            Player player = Controller.panEvaluationRally.rallyPositionMapOpp.get(zone);

            switch (player.getPosition()) {
                case 1:
                    pos = "S";
                    break;
                case 2:
                    pos = "L";
                    break;
                case 3:
                    pos = "U";
                    break;
                case 4:
                    pos = "OH";
                    break;
                case 5:
                    pos = "MB";
                    break;
                case 6:
                    pos = "S";
                    break;
            }
        }
        return pos;
    }

    public static String getScore(int rallyNum, int evaluationId) {

        String score = "0 : 0";
        RallyDao rallyDao = new RallyDao();
        RallyEvaluation re = rallyDao.getRallyScore(rallyNum, evaluationId);
        if (re.getId() != 0) {
            score = re.getHomeScore() + " : " + re.getOpponentScore();
        }
        return score;
    }

    public static String getServeInSituation(int rallyId, int evaluationId, int evaluationTeamId) {
        String situation = "RC";
        RallyDao rallyDao = new RallyDao();
        RallyEvaluation re = rallyDao.getRallyScore(rallyId, evaluationId);
        if (re.getId() != 0) {
            if (re.getStartby() == evaluationTeamId) {
                situation = "RC";
            } else {
                situation = "SC";
            }
        }
        return situation;
    }

    public static String getOpponentSetterPosition() {
        String position = "";
        for (Map.Entry<Integer, Player> entry : Controller.panEvaluationRally.rallyPositionMapOpp.entrySet()) {
            Player p = entry.getValue();
            int pos = 0;
            if (p.getPosition() == PlayerPosition.SETTER.getId()) {
                pos = entry.getKey();
            }
            switch (pos) {
                case 1:
                    position = "RR";
                    break;
                case 2:
                    position = "FR";
                    break;
                case 3:
                    position = "FM";
                    break;
                case 4:
                    position = "FL";
                    break;
                case 5:
                    position = "RL";
                    break;
                case 6:
                    position = "RM";
                    break;
            }

        }
        return position;
    }

    public static String getHomeSetterPosition() {
        String position = "";
        for (Map.Entry<Integer, Player> entry : Controller.panEvaluationRally.rallyPositionMap.entrySet()) {
            Player p = entry.getValue();
            int pos = 0;
            if (p.getPosition() == PlayerPosition.SETTER.getId()) {
                pos = entry.getKey();
            }
            switch (pos) {
                case 1:
                    position = "RR";
                    break;
                case 2:
                    position = "FR";
                    break;
                case 3:
                    position = "FM";
                    break;
                case 4:
                    position = "FL";
                    break;
                case 5:
                    position = "RL";
                    break;
                case 6:
                    position = "RM";
                    break;
            }

        }
        return position;
    }

    public static String getBlockDefendedCourt(String pan) {
        String court;
        court = pan.substring(0, 1);

        if (court.equalsIgnoreCase("H")) {
            court = "H";
        } else if (court.equalsIgnoreCase("O")) {
            court = "Opp";
        }
        return court;

    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
