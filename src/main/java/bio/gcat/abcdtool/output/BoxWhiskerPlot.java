package bio.gcat.abcdtool.output;

import bio.gcat.abcdtool.Analysis;
import bio.gcat.abcdtool.Element;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoxWhiskerPlot extends ApplicationFrame {
    public JFreeChart getChart() {
        return chart;
    }

    private JFreeChart chart;

    public BoxWhiskerPlot(final String title, Output o, char base) {

        super(title);

        final BoxAndWhiskerCategoryDataset dataset = createDataset(o,base);

        final CategoryAxis xAxis = new CategoryAxis("Tupel");
        final NumberAxis yAxis = new NumberAxis("Value");
        yAxis.setAutoRangeIncludesZero(false);
//        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
//        renderer.setFillBox(false);
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
        renderer.setFillBox(true);
        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);
        renderer.setSeriesOutlinePaint(1, Color.BLACK);
        renderer.setUseOutlinePaintForWhiskers(true);
        Font legendFont = new Font("SansSerif", Font.PLAIN, 15);
        renderer.setLegendTextFont(0, legendFont);
        renderer.setLegendTextFont(1, legendFont);
        renderer.setMeanVisible(false);
        renderer.setToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);

         chart = new JFreeChart(
                title ,
                new Font("SansSerif", Font.BOLD, 14),
                plot,
                true
        );


        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(450, 270));
        setContentPane(chartPanel);

    }

    private BoxAndWhiskerCategoryDataset createDataset(Output o, char base) {


        final DefaultBoxAndWhiskerCategoryDataset dataset
                = new DefaultBoxAndWhiskerCategoryDataset();
        final List<Double> list = new ArrayList<>();
        // add some values...




        String baseString = "" + base;
        List<Analysis> analyses = o.getAnalyses();


        for (Analysis a : analyses) {
            int sequenceLength = 0; // we cant just take the string length because of all the unknown bases
            for (Element e : a.getFrequencies().keySet()) {
                sequenceLength += a.getFrequencies().get(e);
            }
            double divident = sequenceLength / a.getTupel();
            for (Element e : a.getFrequencies().keySet()) {
                if (e.getBase() == base) {
                    double value = (double)a.getFrequencies().get(e) / divident;
                    if(value <0.1 ){
                        System.out.println("zero at" + e + "frequency is " + a.getFrequencies().get(e)
                        + "the value is " + value);
                    }
                    list.add(value);
                }
            }
            dataset.add(list, "Series 1",a.getTupel());

        }


        return dataset;
    }
}
