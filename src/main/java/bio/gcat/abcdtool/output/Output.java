package bio.gcat.abcdtool.output;

import bio.gcat.abcdtool.analysis.NPletAnalysis;
import bio.gcat.abcdtool.sequences.reader.Element;
import bio.gcat.abcdtool.analysis.Statistics;
import bio.gcat.abcdtool.gatherfiles.GenbankSequence;

import java.awt.*;
import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jfree.graphics2d.svg.SVGGraphics2D;
import org.jfree.graphics2d.svg.SVGUtils;

/**
 * The different ways to output our analyses.
 * TODO split into more than one class for better reading?
 *
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 */
public class Output {
  private List<NPletAnalysis> analyses;
  /**
   * TODO docs
   */
  private List<Map<Element, Integer>> analyzedTupels;
  private String seqId;

  /**
   * @param seqId
   */
  public Output(String seqId) {
    this.seqId = seqId;
    analyzedTupels = new ArrayList<>();
    analyses = new ArrayList<>();
  }

  public String getShortName() {
    return GenbankSequence.getSpecies(seqId);
  }

  public String getSeqId() {
    return seqId;
  }

  public void addAnalysis(NPletAnalysis analysis) {
    analyzedTupels.add(analysis.getFrequencies());
    analyses.add(analysis);
  }

  public List<NPletAnalysis> getAnalyses() {
    return analyses;
  }

  public List<Map<Element, Integer>> getAnalyzedTupels() {
    return analyzedTupels;
  }

  public String[][] getAsTable() {

  /* only needed if lengths in input are not in ascending order
        analyzedTupels.sort(new Comparator<Map<Element, Integer>>() {
            @Override
            public int compare(Map<Element, Integer> o1, Map<Element, Integer> o2) {
                return o1.entrySet().size() - o2.entrySet().size();
            }
        });
        */

    char[] elements = {'A', 'T', 'G', 'C'};
    int lengthOfLongestSequence = analyzedTupels.get(analyzedTupels.size() - 1).size(); //gets the different values of the longest n-plet
    String[][] table = new String[analyzedTupels.size() * 2][lengthOfLongestSequence];
    for (int column = 0; column < table.length; column++) {
      int sequenceLength = analyses.get(column / 2).getSequenceLength();
      for (int row = 0; row < table[0].length; row++) {
        if (column % 2 == 0) {
          if (row < analyzedTupels.get(column / 2).size()) {
            table[column][row] = "P_{" + (analyses.get(column / 2).getnPletSize()) + "}(" + elements[row % 4] + "_{" + (row / 4 + 1) + "})=";
          }
        } else {
          Element current = new Element(elements[row % 4], row / 4);
          double relativeFrequency = ((double) analyzedTupels.get(column / 2).getOrDefault(current, 0));
          int tupelLength = analyses.get(column / 2).getnPletSize();
          relativeFrequency = relativeFrequency / ((double) sequenceLength / (double) tupelLength);
          relativeFrequency = analyses.get(column / 2).getRelativeProbability(current);
          if (relativeFrequency == 0) {
            table[column][row] = "";
          } else {

            double rounded = Math.floor(10000 * relativeFrequency + 0.5) / 10000;

            table[column][row] = rounded + "";
          }
        }
      }
    }
    return table;
  }

  public String outputAsLatexTable() {
    String[][] table = getAsTable();
    StringBuilder output = new StringBuilder();
    output.append("\\begin{tabular}{");

    for (int column = 0; column < table.length; column++) {
      output.append("|c");
    }
    output.append("|}\n\t\\hline\n");
    for (int row = 0; row < table[0].length; row++) {
      output.append("\t");
      for (int column = 0; column < table.length; column++) {
        if (table[column][row] != null) {
          if (column % 2 == 0) {
            output.append("$");
            output.append(table[column][row]);
            output.append("$");
          } else {

            output.append(table[column][row]);
          }
        }
        if (column != table.length - 1) {
          output.append(" & ");
        }
      }
      output.append(" \\\\\n" +
              "\t\\hline \n");
    }
    output.append("\\end{tabular} ");

    return output.toString();
  }

  /**
   * from https://stackoverflow.com/a/34738509 (also on web archive)
   *
   * @return
   */
  public String toHTML() {
    String[][] data = getAsTable();
    StringBuilder sb = new StringBuilder();

    sb.append(minMaxAverageTable()); // new part of output
    String[] colors = {"red", "lightblue", "yellow", "green"};
    sb.append("<table>\n");
    for (int i = 0; i < getAnalyzedTupels().size(); i++) {
      int sizeOfTheFragment = analyses.get(i).getnPletSize();
      sb.append("\t\t<th>" + sizeOfTheFragment + "</th>\n");
      sb.append("\t\t<th/>\n");
    }
    for (int row = 0; row < data[0].length; row++) {
      String color = colors[row % 4];
      sb.append("\t<tr bgcolor=\"" + color + "\">\n");
      for (int col = 0; col < data.length; col++) {
        sb.append("\t\t<td>" + data[col][row] + "</td>\n");
      }
      sb.append("\t</tr>\n");
    }
    sb.append("</table>");
    return sb.toString();
  }

  /**
   * this was an Idea to add some general data to the top of the HTML table
   *
   * @return
   */
  private String minMaxAverageTable() {
    //TODO: Think about how to get the output
    return "";
  }

  /**
   * create a barchart, was not useful in our project
   */
  @Deprecated
  public void toBarChart() {
    final Barchart demo = new Barchart("A Chart", this, 'A');
    demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
    demo.setVisible(true);
  }

  /**
   * created a scatterplot of all the bases of an analysis, the boxwhiskerplot was proven to be more useful
   *
   * @return {@link #toBoxWhiskerPlot()} ()}
   */
  @Deprecated
  public Scatterplot[] toScatterPlot() {
    final Scatterplot scatterplotA = new Scatterplot("A Chart", this, 'A');

    final Scatterplot scatterplotT = new Scatterplot("T Chart", this, 'T');

    final Scatterplot scatterplotG = new Scatterplot("G Chart", this, 'G');


    final Scatterplot scatterplotC = new Scatterplot("C Chart", this, 'C');

    return new Scatterplot[]{scatterplotA, scatterplotT, scatterplotG, scatterplotC};
  }

  /**
   * create a Box Whisker plot for all the bases of an analysis
   *
   * @return
   */
  private BoxWhiskerPlot[] toBoxWhiskerPlot() {
    String speciesAndChromosome = getTitleName();
    final BoxWhiskerPlot boxWhiskerPlotA = new BoxWhiskerPlot(speciesAndChromosome + " A", this, 'A');

    final BoxWhiskerPlot boxWhiskerPlotT = new BoxWhiskerPlot(speciesAndChromosome + " T", this, 'T');

    final BoxWhiskerPlot boxWhiskerPlotG = new BoxWhiskerPlot(speciesAndChromosome + " G", this, 'G');

    final BoxWhiskerPlot boxWhiskerPlotC = new BoxWhiskerPlot(speciesAndChromosome + " C", this, 'C');

    return new BoxWhiskerPlot[]{boxWhiskerPlotA, boxWhiskerPlotT, boxWhiskerPlotG, boxWhiskerPlotC};
  }

  private String getTitleName() {
    String species = getShortName();
    final Pattern lastIntPattern = Pattern.compile("[^0-9]+([0-9]+)$");
    String input = "...";
    Matcher matcher = lastIntPattern.matcher(input);
    if (matcher.find()) {
      String someNumberStr = matcher.group(1);
      int lastNumberInt = Integer.parseInt(someNumberStr);
      return species + "Chromosome " + someNumberStr;
    }
    return species;

  }

  /**
   * Some OS have problems with different letters.
   *
   * @param name
   * @return
   */
  public static String prepareNameForWriting(String name) {

    name = name.replaceAll(":|>|<", "");
    return name;
    //char[] ILLEGAL_CHARACTERS = { '\n', '\r', '\t', '\0', '\f', '`', '?', '*',  '<', '>', '|', '\"', ':'};
  }

  public void prepareNameForWriting() {
    seqId = prepareNameForWriting(seqId);
  }

  public void createOutputs() throws IOException {
    prepareNameForWriting();
//        createScatterplots();
    createHTMLOutput();
//       createTexOutput();
    createBoxWhisker();
//        createStandardErrorFile();
    createFileA();
    createFileAllProbabilities();
    createSkewFile();
    createExcelOutput();
    createStandardDevFile();
    createMinMaxFile();
//        this.toBarChart();
  }

  /**
   * creates or appends a file that lists the content of adenine for every analyzed species.
   *
   * @throws FileNotFoundException
   */
  private void createFileA() throws FileNotFoundException {
    File f = new File(getOutputPathAfile() + "A.txt");
    if (!f.exists()) {
      f.getParentFile().mkdirs();
    }
    PrintWriter out = new PrintWriter(new FileOutputStream(
            f,
            true));
    double frequency = this.getAnalyses().get(0).getFrequencies().get(new Element('A', 0));
    frequency = frequency / this.analyses.get(0).getSequenceLength();
    out.println(this.getSeqId() + " : " + frequency);
    out.close();

  }

  /**
   * similar to createFileA. This one gives the relative frequency of all bases in a sequence
   *
   * @throws FileNotFoundException
   */
  private void createFileAllProbabilities() throws FileNotFoundException {
    File f = new File(getOutputPathAfile() + "ATGC.txt");
    if (!f.exists()) {
      f.getParentFile().mkdirs();
    }
    PrintWriter out = new PrintWriter(new FileOutputStream(
            f,
            true));
    double frequencyA = this.getAnalyses().get(0).getFrequencies().get(new Element('A', 0));
    double frequencyT = this.getAnalyses().get(0).getFrequencies().get(new Element('T', 0));
    double frequencyG = this.getAnalyses().get(0).getFrequencies().get(new Element('G', 0));
    double frequencyC = this.getAnalyses().get(0).getFrequencies().get(new Element('C', 0));
    frequencyA = frequencyA / this.analyses.get(0).getSequenceLength();
    frequencyT = frequencyT / this.analyses.get(0).getSequenceLength();
    frequencyG = frequencyG / this.analyses.get(0).getSequenceLength();
    frequencyC = frequencyC / this.analyses.get(0).getSequenceLength();
    out.println(this.getSeqId() + "A : " + frequencyA);
    out.println(this.getSeqId() + "T : " + frequencyT);
    out.println(this.getSeqId() + "G : " + frequencyG);
    out.println(this.getSeqId() + "C : " + frequencyC);
    out.close();

  }

  /**
   * writes the difference between the minimum and maximum probability of a sequence in the tupel sizes
   *
   * @throws FileNotFoundException
   */
  private void createMinMaxFile() throws FileNotFoundException {
    File f = new File(getOutputPathAfile() + "minMax.txt");
    if (!f.exists()) {
      f.getParentFile().mkdirs();
    }
    PrintWriter out = new PrintWriter(new FileOutputStream(
            f,
            true));
//        double frequencyA = this.getAnalyses().get(0).getFrequencies().get(new Element('A', 0));
//        double minA=0;
    double[] minMax = Statistics.getMaximumandMinimumFrequency(this.getAnalyses(), 'A');
    out.println(this.getSeqId() + " : " + this.getAnalyses().get(0).getSequenceLength() + ": size,min,max : " + minMax[0] + " : " + minMax[1]);
    out.close();

  }

  /**
   * Writes the standard deviation for every sequence into a file.
   * The deviation is calculated of all the relative frequencies in all the tupel sizes
   *
   * @throws FileNotFoundException
   */
  private void createStandardDevFile() throws FileNotFoundException {
    File standarddev = new File(getOutputPathAfile() + "StandardDev.txt");
    if (!standarddev.exists()) {
      standarddev.getParentFile().mkdirs();
    }
    PrintWriter out = new PrintWriter(new FileOutputStream(
            standarddev,
            true));
    for (NPletAnalysis a : analyses) {
      if (a.getnPletSize() == 1) {
        continue;
      }
      double[] valuesA = a.getFrequencies('A');
      double[] valuesT = a.getFrequencies('T');
      double[] valuesG = a.getFrequencies('G');
      double[] valuesC = a.getFrequencies('C');
//            System.out.println("Output number of values = " + values.length);
      Statistics statisticsA = new Statistics(valuesA);
      Statistics statisticsT = new Statistics(valuesT);
      Statistics statisticsG = new Statistics(valuesG);
      Statistics statisticsC = new Statistics(valuesC);
//            System.out.println("Output, Standard error : " + standardErrorOfTheMean);
      DecimalFormat df = new DecimalFormat("#");
      df.setMaximumFractionDigits(15);


      out.println(this.getSeqId() + " " + "A" + String.format("%03d", a.getnPletSize()) + " : " + df.format(statisticsA.getMean()) + " : " + df.format(statisticsA.getStdDev()));
      out.println(this.getSeqId() + " " + "T" + String.format("%03d", a.getnPletSize()) + " : " + df.format(statisticsT.getMean()) + " : " + df.format(statisticsT.getStdDev()));
      out.println(this.getSeqId() + " " + "G" + String.format("%03d", a.getnPletSize()) + " : " + df.format(statisticsG.getMean()) + " : " + df.format(statisticsG.getStdDev()));
      out.println(this.getSeqId() + " " + "C" + String.format("%03d", a.getnPletSize()) + " : " + df.format(statisticsC.getMean()) + " : " + df.format(statisticsC.getStdDev()));
    }
    out.close();
  }

  /**
   * Writes the standard error of A for all tupel sizes >1 into it's own files
   *
   * @throws FileNotFoundException
   */
  private void createStandardErrorFile() throws FileNotFoundException {
    File f = new File(getOutputPathAfile() + "StandardErrorA.txt");
    if (!f.exists()) {
      f.getParentFile().mkdirs();
    }
    PrintWriter out = new PrintWriter(new FileOutputStream(
            f,
            true));
    for (NPletAnalysis a : analyses) {
      if (a.getnPletSize() == 1) {
        continue;
      }
      double[] values = a.getFrequencies('A');
//            System.out.println("Output number of values = " + values.length);
      Statistics statistics = new Statistics(values);
      double standardErrorOfTheMean = statistics.getStandardErrorOfTheMean();
      double mean = statistics.getMean();
//            System.out.println("Output, Standard error : " + standardErrorOfTheMean);
      DecimalFormat df = new DecimalFormat("#");
      df.setMaximumFractionDigits(15);


      out.println(this.getSeqId() + " " + String.format("%03d", a.getnPletSize()) + " : " + df.format(mean) + " : " + df.format(standardErrorOfTheMean));
    }
    out.close();

  }

  /**
   * writes the AT and GC skew for the sequence into the file.
   *
   * @throws FileNotFoundException
   */
  private void createSkewFile() throws FileNotFoundException { //todo: refactor?
    File fileAT = new File(getOutputPathAfile() + "ATSkew.csv");
    File fileGC = new File(getOutputPathAfile() + "GCSkew.csv");
    if (!fileAT.exists()) {
      fileAT.getParentFile().mkdirs();
    }
    if (!fileGC.exists()) {
      fileGC.getParentFile().mkdirs();
    }
    PrintWriter outGC = new PrintWriter(new FileOutputStream(
            fileGC,
            true));
    PrintWriter outAT = new PrintWriter(new FileOutputStream(
            fileAT,
            true));

    double frequencyA = this.getAnalyses().get(0).getFrequencies().get(new Element('A', 0));
    double frequencyT = this.getAnalyses().get(0).getFrequencies().get(new Element('T', 0));
    double frequencyG = this.getAnalyses().get(0).getFrequencies().get(new Element('G', 0));
    double frequencyC = this.getAnalyses().get(0).getFrequencies().get(new Element('C', 0));

    double frequencyAT = (frequencyA - frequencyT) / (frequencyA + frequencyT);
    double frequencyGC = (frequencyC - frequencyG) / (frequencyC + frequencyG);

    outAT.println(this.getSeqId() + ";" + frequencyAT);
    outAT.close();

    outGC.println(this.getSeqId() + ";" + frequencyGC);
    outGC.close();
  }

  /**
   * write the plots into a file
   *
   * @throws IOException
   */
  private void createBoxWhisker() throws IOException {
    BoxWhiskerPlot[] plots = this.toBoxWhiskerPlot();
    for (BoxWhiskerPlot w : plots) {
      //toSVG oder PDF
      SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
      Rectangle r = new Rectangle(0, 0, 600, 400);
      w.getChart().draw(g2, r);
      File f = new File(getOutputPath(seqId, "BoxWhisker") + w.getTitle() + "SVGBoxWhisker.svg");
      if (!f.exists()) {
        f.getParentFile().mkdirs();
      }
      SVGUtils.writeToSVG(f, g2.getSVGElement());
    }
  }

  /**
   * write the table as a LaTeX table into a file
   *
   * @throws FileNotFoundException
   */
  private void createTexOutput() throws FileNotFoundException {
    File fileTex = new File(getOutputPath(seqId, "Latex") + "table.tex");
    fileTex.getParentFile().mkdirs();

    PrintWriter tex = new PrintWriter(fileTex);
    tex.println(this.outputAsLatexTable());
    tex.close();
  }

  /**
   * write the HTML output to a file
   *
   * @throws FileNotFoundException
   */
  private void createHTMLOutput() throws FileNotFoundException {
    File file = new File(getOutputPath(seqId, "HTML") + "output.html");
    file.getParentFile().mkdirs();

    PrintWriter out = new PrintWriter(file);
    out.println(this.toHTML());
    out.close();
  }

  /**
   * create an excel table as a output
   *
   * @throws FileNotFoundException
   */
  private void createExcelOutput() throws FileNotFoundException {
//        File file = new File(getOutputPath(seqId, "Excel") + "output.xls");
//        file.getParentFile().mkdirs();

    Excel e = new Excel(getAsTable(), analyses);
    e.writeFile(getOutputPath(seqId, "Excel") + "output.xls");
  }

  /**
   * write the scatterplots to a file
   *
   * @throws IOException
   */
  private void createScatterplots() throws IOException {
    Scatterplot[] plots = this.toScatterPlot();
    for (Scatterplot s : plots) {
      //toSVG oder PDF
      SVGGraphics2D g2 = new SVGGraphics2D(600, 400);
      Rectangle r = new Rectangle(0, 0, 600, 400);
      s.getChart().draw(g2, r);
      File f = new File(getOutputPath(seqId, "Scatterplot") + s.getTitle() + "SVGScatterPlot.svg");
      if (!f.exists()) {
        f.getParentFile().mkdirs();
      }
      SVGUtils.writeToSVG(f, g2.getSVGElement());
    }
  }

  /**
   * returns an output path depending on the output type and species seqId
   *
   * @param name seqId of the species
   * @param type type of the output
   * @return
   */
  public String getOutputPath(String name, String type) {
    String speciesName = GenbankSequence.getSpecies(name);
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
    String date = new Timestamp(System.currentTimeMillis()).toLocalDateTime().toLocalDate().toString();
    String path = "Output" + File.separator + date + File.separator + speciesName + File.separator +
            name + File.separator + type + File.separator;
    return path;
  }

  /**
   * since this output is not relative to just a species, it returns the output path for the current day
   *
   * @return
   */
  public static String getOutputPathAfile() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
    String date = new Timestamp(System.currentTimeMillis()).toLocalDateTime().toLocalDate().toString();
    String path = "Output" + File.separator + date + File.separator;
    return path;
  }
}
