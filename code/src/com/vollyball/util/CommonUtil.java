/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.util;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nishant.vibhute
 */
public class CommonUtil {

    public static String propertyUrl = System.getProperty("user.dir");
    static InputStream input = null;
    static Properties prop = new Properties();

    public CommonUtil() {

        try {

//            input = new FileInputStream(path + "WEB-INF\\classes\\com\\pritient\\properties\\resource.properties");
            input = new FileInputStream(propertyUrl + "\\src\\com\\vollyball\\properties\\resource.properties");
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

}
