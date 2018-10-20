/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.util;

import com.vollyball.dao.ReportDao;
import java.awt.Color;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class CommonUtil {

//    public static String propertyUrl = System.getProperty("user.dir");
    static InputStream input = null;
    static Properties prop = new Properties();

    public CommonUtil() {

        try {

//            InputStream input = CommonUtil.class.getClassLoader().getResourceAsStream("resource.properties");
            input = getClass().getResourceAsStream("/com/vollyball/properties/resource.properties");
//            input = new FileInputStream(propertyUrl + "\\src\\com\\vollyball\\properties\\resource.properties");
            prop.load(input);

        } catch (Exception ex) {

        }
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String getResourceProperty(String name) {
        String value = prop.getProperty(name);
        return value;

    }

    public static String ConvertDateFromNormalToDB(String from) {
        DateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = originalFormat.parse(from);
        } catch (ParseException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        String formattedDate = targetFormat.format(date);  // 20120821
        return formattedDate;
    }

    public static String ConvertDateFromDbToNormal(String from) {
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = originalFormat.parse(from);
        } catch (ParseException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        String formattedDate = targetFormat.format(date);  // 20120821
        return formattedDate;
    }

    public static Color getColorONScore(String score) {
        Color color = Color.BLACK;

        switch (score) {
            case "1":
                color = Color.RED;
                break;
            case "2":
            case "3":
            case "4":
                color = Color.ORANGE;
                break;
            case "5":
                color = Color.GREEN;
                break;
        }
        return color;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Integer.parseInt(str);
        } catch (Exception nfe) {
            return false;
        }
        return true;
    }

    public static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        // 1. Convert Map to List of Map
        List<Map.Entry<String, Integer>> list
                = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        // 2. Sort list with Collections.sort(), provide a custom Comparator
        //    Try switch the o1 o2 position for a different order
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                    Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // 3. Loop the sorted list and put it into a new insertion order Map LinkedHashMap
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
         //classic iterator example
         for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext(); ) {
         Map.Entry<String, Integer> entry = it.next();
         sortedMap.put(entry.getKey(), entry.getValue());
         }*/
        return sortedMap;
    }

    public static <K, V> void printMap(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value : " + entry.getValue());
        }
    }

    public static String getValue(int skillDescCriteriaId, int rating, String rowSkillDesc, int rowSkillDescId, int evaluationteamId, int skillid) {
        ReportDao reportDao = new ReportDao();
        String value = "";
        Map<String, Integer> sortedMap = reportDao.getSkillSuccessForTeam(skillDescCriteriaId, skillid, evaluationteamId, rowSkillDesc, rowSkillDescId, rating);

        if (sortedMap.get(sortedMap.keySet().toArray()[0]) != 0) {
            value = sortedMap.keySet().toArray()[0].toString();
        }

        return value;
    }

    public static String getDate() {
        Date now = new Date();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        return mysqlDateString;
    }

    public static boolean isUserSubscritionExpire(Date subDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Now use today date.
        c.add(Calendar.DATE, 5); // Adding 5 days
        String output = sdf.format(c.getTime());
        System.out.println(output);
        return false;
    }

    public static boolean getDaysLeft(String createdOn) {
        try {

            DateFormat formatter;
            Date joinedOn, currentDate;
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            joinedOn = (Date) formatter.parse(createdOn);
            currentDate = new Date();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(joinedOn); // Now use today date.
            c.add(Calendar.DATE, 7); // Adding 7 days

            Date expiry = c.getTime();

            if (currentDate.compareTo(expiry) <= 0) {
                return true;
            } else {
                return false;
            }

        } catch (ParseException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
