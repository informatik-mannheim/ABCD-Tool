package bio.gcat.abcdtool.analysis;

import bio.gcat.abcdtool.output.Output;

import java.io.IOException;
import java.util.List;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class NPletsAnalysis {

  private String name;
  private String seq;
  private List<Integer> tupleSizes;

  public NPletsAnalysis(String name, String seq, List<Integer> nPletSizes) {
    this.name = name;
    this.seq = seq;
    this.tupleSizes = nPletSizes;
  }

  public String getName() {
    return name;
  }

  public String getSequence() {
    return seq;
  }

  public List<Integer> getNPletSizes() {
    return tupleSizes;
  }

  public void analyze() {
    // name = name+"shortened" ;
    Output output = new Output(name);
    for (int nPletSize : getNPletSizes()) {
      System.out.println("Analyzing n-plet size " + nPletSize + " ...");

      //TODO: REMOVE THIS FOR THE ANALYSIS WITHOUT REMOVING N
      // sequence = StringUtils.stripStart(sequence,"N"); //remove first sequenceN

      NPletAnalysis analysis = new NPletAnalysis(seq, nPletSize);
      System.out.println(analysis.getSequenceLength());
      System.out.println("Analyzing n-plet size " + nPletSize + " done.");
      output.addAnalysis(analysis);
      // TODO really needed?
      // Clear up some memory - running out of heap space otherwise:
      analysis.setSequence(null);
      // System.out.println("done with " + j + "it took : " + (System.currentTimeMillis() - timeNow) + "ms");
    }
    try {
      output.createOutputs();
    } catch (IOException e) {
      System.err.println("Could not write output!");
      e.printStackTrace();
    }
  }
}
