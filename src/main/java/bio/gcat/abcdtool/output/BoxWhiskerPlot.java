package bio.gcat.abcdtool.output;

import bio.gcat.abcdtool.analysis.Analysis;
import bio.gcat.abcdtool.sequences.readsequence.Element;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;
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
        System.out.println(title);
        final BoxAndWhiskerCategoryDataset dataset = createDataset(o, base);

        final CategoryAxis xAxis = new CategoryAxis("Tupel");
        final NumberAxis yAxis = new NumberAxis("Frequency");
        yAxis.setAutoRangeIncludesZero(false);
//        final BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
//        renderer.setFillBox(false);
        BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();

        renderer.setFillBox(true);
        renderer.setSeriesPaint(0, Color.LIGHT_GRAY);
        renderer.setSeriesOutlinePaint(0, Color.BLACK);
        renderer.setUseOutlinePaintForWhiskers(true);
        Font legendFont = new Font("SansSerif", Font.PLAIN, 10);
        renderer.setLegendTextFont(0, legendFont);
        renderer.setMeanVisible(true);
        renderer.setBaseSeriesVisibleInLegend(false);

        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
        chart = new JFreeChart(
                title,
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

        // add some values...


        String baseString = "" + base;
        List<Analysis> analyses = o.getAnalyses();


        for (Analysis a : analyses) {
             List<Double> list = new ArrayList<>();
            int sequenceLength = a.getSequenceLength();
            double divident = sequenceLength / a.getTupel();
//            System.out.println(" BW sequence length = "+sequenceLength);
            for (Element e : a.getFrequencies().keySet()) {
                if (e.getBase() == base) {
                    double value = (double) a.getFrequencies().get(e) / divident;
                    value = a.getFrequency(e);
                    if (value < 0.319 || value > 0.3227  ) {
                        System.out.println("zero at" + e + "frequency is " + a.getFrequencies().get(e)
                                + "the value is " + value);
                    }
                    list.add(value);
                }
            }
            dataset.add(list, o.getShortName(), a.getTupel());
            list.clear();
        }


        return dataset;
    }
}
