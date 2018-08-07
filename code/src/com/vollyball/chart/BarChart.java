/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.chart;

import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author Supriya
 */
public class BarChart {

    public static JFreeChart createChart(CategoryDataset dataset, String xaxisName, String yaxisName, boolean showLegends) {

        JFreeChart barChart = ChartFactory.createBarChart(
                "",
                yaxisName,
                xaxisName,
                dataset,
                PlotOrientation.VERTICAL,
                showLegends, false, false);

        CategoryPlot plot = (CategoryPlot) barChart.getPlot();
        plot.getRangeAxis().setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        plot.setNoDataMessage("No data available");
        BarRenderer r = (BarRenderer) barChart.getCategoryPlot().getRenderer();
        r.setSeriesPaint(0, Color.blue);

        return barChart;
    }
}
