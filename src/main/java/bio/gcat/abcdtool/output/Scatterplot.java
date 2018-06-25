package bio.gcat.abcdtool.output;

import java.awt.Color;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import bio.gcat.abcdtool.analysis.NPletAnalysis;
import bio.gcat.abcdtool.sequences.reader.Element;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * TODO Clarify author.
 *
 * @author imssbora
 */
public class Scatterplot extends JFrame {
  public JFreeChart getChart() {
    return chart;
  }

  private JFreeChart chart;

  public Scatterplot(String title, Output o, char base) {
    super(title);

    // Create dataset
    XYDataset dataset = createDataset(o, base);
    String xAxis = "" + base;
    // Create chart
    chart = ChartFactory.createScatterPlot(
            title, xAxis, "frequency", dataset, PlotOrientation.VERTICAL, false, false, false);


    //Changes background color
    XYPlot plot = (XYPlot) chart.getPlot();
    plot.setBackgroundPaint(new Color(255, 228, 196));

//////////
    plot.setBackgroundPaint(Color.lightGray);
    plot.setRangeGridlinePaint(Color.white);

    // customise the range axis...
    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
    rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
    rangeAxis.setAutoRangeIncludesZero(true);
    //////////

    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  private XYDataset createDataset(Output o, char base) {
    XYSeriesCollection dataset = new XYSeriesCollection();

    //Boys (Age,weight) series
    String baseString = "" + base;
    XYSeries series1 = new XYSeries(baseString);
    List<NPletAnalysis> analyses = o.getAnalyses();


    for (NPletAnalysis a : analyses) {
      int sequenceLength = 0; // we cant just take the string length because of all the unknown bases
      for (Element e : a.getFrequencies().keySet()) {
        sequenceLength += a.getFrequencies().get(e);
      }
      double divident = sequenceLength / a.getnPletSize();
      for (Element e : a.getFrequencies().keySet()) {
        if (e.getBase() == base) {
          double value = (double) a.getFrequencies().get(e) / divident;

          series1.add(a.getnPletSize(), value);
        }
      }

    }

    dataset.addSeries(series1);


    return dataset;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      Scatterplot example = new Scatterplot("Scatter Chart Example ", new Output("a"), 'A');
      example.setSize(800, 400);
      example.setLocationRelativeTo(null);
      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      example.setVisible(true);
    });
  }
}