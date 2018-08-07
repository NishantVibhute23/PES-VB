/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.chart;

import java.awt.Color;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author nishant.vibhute
 */
public class PieChart {

    public static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "", // chart title
                dataset, // data
                true, // include legend
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        plot.setSectionPaint(0, Color.RED);
        plot.setSectionPaint(1, Color.PINK);
        plot.setSectionPaint(2, Color.YELLOW);
        plot.setSectionPaint(3, Color.BLUE);
        plot.setSectionPaint(4, Color.GREEN);
        return chart;

    }
}
