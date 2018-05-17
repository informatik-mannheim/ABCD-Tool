package bio.gcat.abcdtool.output;

        /* ===========================================================
         * JFreeChart : a free chart library for the Java(tm) platform
         * ===========================================================
         *
         * (C) Copyright 2000-2004, by Object Refinery Limited and Contributors.
         *
         * Project Info:  http://www.jfree.org/jfreechart/index.html
         *
         * This library is free software; you can redistribute it and/or modify it under the terms
         * of the GNU Lesser General Public License as published by the Free Software Foundation;
         * either version 2.1 of the License, or (at your option) any later version.
         *
         * This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
         * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
         * See the GNU Lesser General Public License for more details.
         *
         * You should have received a copy of the GNU Lesser General Public License along with this
         * library; if not, write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330,
         * Boston, MA 02111-1307, USA.
         *
         * [Java is a trademark or registered trademark of Sun Microsystems, Inc.
         * in the United States and other countries.]
         *
         * -------------------
         * LineChartDemo1.java
         * -------------------
         * (C) Copyright 2002-2004, by Object Refinery Limited and Contributors.
         *
         * Original Author:  David Gilbert (for Object Refinery Limited);
         * Contributor(s):   -;
         *
         * $Id: LineChartDemo1.java,v 1.27 2004/05/27 09:10:42 mungady Exp $
         *
         * Changes
         * -------
         * 08-Apr-2002 : Version 1 (DG);
         * 30-May-2002 : Modified to display values on the chart (DG);
         * 25-Jun-2002 : Removed redundant import (DG);
         * 11-Oct-2002 : Fixed errors reported by Checkstyle (DG);
         *
         */


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import bio.gcat.abcdtool.analysis.NPletAnalysis;
import bio.gcat.abcdtool.sequences.reader.Element;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This class creates a visualisation of the average values for each Nucleotide
 */
public class Barchart extends ApplicationFrame {

    /**
     * @param title the frame title.
     */
    public Barchart(final String title) {
        super(title);
        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    public Barchart(String title, Output o, char base) {
        super(title);
        final CategoryDataset dataset = createDataset(o, base);
        final JFreeChart chart = createChartOutput(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);

    }

    /**
     * auto formatted
     *
     * @param dataset
     * @return
     */
    private JFreeChart createChartOutput(CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createLineChart(
                "XY Series Demo",
                "X",
                "Y",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        return chart;
    }

    /**
     *
     * @param o
     * @param base
     * @return
     */
    private CategoryDataset createDataset(Output o, char base) {
        List<NPletAnalysis> analyses = o.getAnalyses();
        String[] tupelLengths = new String[analyses.size()];
        for (int i = 0; i < tupelLengths.length; i++) {
            tupelLengths[i] = "" + analyses.get(i).getnPletSize();
        }

        int sequenceLength=0;
        for(Element e : analyses.get(0).getFrequencies().keySet()){
            sequenceLength+= analyses.get(0).getFrequencies().get(e);

        }
        final String series = "" + base;
        int index = 0;
        switch (base) { //TODO: maybe refactor this so we can use a function call instead to get all values from the output just by passing the base?
            case 'A':
                index = 0;
                break;
            case 'T':
                index = 1;
                break;
            case 'G':
                index = 2;
                break;
            case 'C':
                index = 3;
                break;
        }
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 0;
        for (NPletAnalysis a : analyses) {
            double value = a.getAverage()[index] / (sequenceLength / a.getnPletSize()); //TODO: create a graph for sth other than A
            dataset.addValue(value, series, tupelLengths[i++]);
        }
        return dataset;
    }


    /**
     * Creates a sample dataset.
     *
     * @return The dataset.
     */
    private CategoryDataset createDataset() {

        // row keys...
        final String series1 = "First";
        final String series2 = "Second";
        final String series3 = "Third";

        // column keys...
        final String type1 = "Type 1";
        final String type2 = "Type 2";
        final String type3 = "Type 3";
        final String type4 = "Type 4";
        final String type5 = "Type 5";
        final String type6 = "Type 6";
        final String type7 = "Type 7";
        final String type8 = "Type 8";

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(1.0, series1, type1);
        dataset.addValue(4.0, series1, type2);
        dataset.addValue(3.0, series1, type3);
        dataset.addValue(5.0, series1, type4);
        dataset.addValue(5.0, series1, type5);
        dataset.addValue(7.0, series1, type6);
        dataset.addValue(7.0, series1, type7);
        dataset.addValue(8.0, series1, type8);

        dataset.addValue(5.0, series2, type1);
        dataset.addValue(7.0, series2, type2);
        dataset.addValue(6.0, series2, type3);
        dataset.addValue(8.0, series2, type4);
        dataset.addValue(4.0, series2, type5);
        dataset.addValue(4.0, series2, type6);
        dataset.addValue(2.0, series2, type7);
        dataset.addValue(1.0, series2, type8);

        dataset.addValue(4.0, series3, type1);
        dataset.addValue(3.0, series3, type2);
        dataset.addValue(2.0, series3, type3);
        dataset.addValue(3.0, series3, type4);
        dataset.addValue(6.0, series3, type5);
        dataset.addValue(3.0, series3, type6);
        dataset.addValue(4.0, series3, type7);
        dataset.addValue(3.0, series3, type8);

        return dataset;

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset a dataset.
     * @return The chart.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
                "Line Chart Demo 1",       // chart title
                "Type",                    // domain axis label
                "Value",                   // range axis label
                dataset,                   // data
                PlotOrientation.VERTICAL,  // orientation
                true,                      // include legend
                true,                      // tooltips
                false                      // urls
        );


        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);
        //    legend.setShapeScaleX(1.5);
        //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);


        // ****************************************************************************
        // * JFREECHART DEVELOPER GUIDE                                               *
        // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
        // * to purchase from Object Refinery Limited:                                *
        // *                                                                          *
        // * http://www.object-refinery.com/jfreechart/guide.html                     *
        // *                                                                          *
        // * Sales are used to provide funding for the JFreeChart project - please    *
        // * support us so that we can continue developing free software.             *
        // ****************************************************************************

        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);

        renderer.setSeriesStroke(
                0, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{10.0f, 6.0f}, 0.0f
                )
        );
        renderer.setSeriesStroke(
                1, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{6.0f, 6.0f}, 0.0f
                )
        );
        renderer.setSeriesStroke(
                2, new BasicStroke(
                        2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                        1.0f, new float[]{2.0f, 6.0f}, 0.0f
                )
        );
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;
    }

    /**
     * Starting point for the demonstration application.
     *
     * @param args ignored.
     */
    public static void main(final String[] args) {

        final Barchart demo = new Barchart("Line Chart Demo");
        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }

}




