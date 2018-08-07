/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vollyball.chart;

import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.StatisticalLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author nishant.vibhute
 */
public class LineChart {

    public static JFreeChart LineChartExample(DefaultCategoryDataset dataset, String heading) {

        // Create dataset DefaultCategoryDataset dataset = dataset;
        // Create chart
        CategoryAxis domain = new CategoryAxis();
        ValueAxis range = new NumberAxis();
        StatisticalLineAndShapeRenderer renderer
                = new StatisticalLineAndShapeRenderer(true, true);
        CategoryPlot plot = new CategoryPlot(dataset, domain, range, renderer);
        plot.getRangeAxis().setLabel("Success Rate");
        plot.getDomainAxis().setLabel("Match");
        JFreeChart chart = new JFreeChart(
                heading, JFreeChart.DEFAULT_TITLE_FONT, plot, false);
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator("{2}",
                        NumberFormat.getNumberInstance()));
        renderer.setBaseItemLabelGenerator(
                new StandardCategoryItemLabelGenerator("{2}",
                        NumberFormat.getNumberInstance()));
        renderer.setBaseItemLabelsVisible(true);
        renderer.setSeriesShape(0, new Rectangle2D.Double(0, 0, 0, 0));

        return chart;
    }
}
