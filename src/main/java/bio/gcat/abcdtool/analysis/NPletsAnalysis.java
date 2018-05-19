package bio.gcat.abcdtool.analysis;

import bio.gcat.abcdtool.output.Output;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Calculates the distribution of n-plets for a sequence.
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class NPletsAnalysis {

  private static Logger logger = LoggerFactory.getLogger(NPletsAnalysis.class);
  private String seqId;
  private String seq;
  private List<Integer> tupleSizes;

  /**
   *
   * @param seqId Sequence ID
   * @param seq Sequence of nucleotides
   * @param nPletSizes  Size of the n-plets
   */
  public NPletsAnalysis(String seqId, String seq, List<Integer> nPletSizes) {
    this.seqId = seqId;
    this.seq = seq;
    this.tupleSizes = nPletSizes;
  }

  public String getSeqId() {
    return seqId;
  }

  public String getSequence() {
    return seq;
  }

  public List<Integer> getNPletSizes() {
    return tupleSizes;
  }

  public void analyze() {
    // seqId = seqId+"shortened" ;
    Output output = new Output(seqId);

    for (int nPletSize : getNPletSizes()) {
      logger.trace("Analyzing n-plet size " + nPletSize + " ...");

      //TODO: REMOVE THIS FOR THE ANALYSIS WITHOUT REMOVING N
      // sequence = StringUtils.stripStart(sequence,"N"); //remove first sequenceN

      NPletAnalysis analysis = new NPletAnalysis(seq, nPletSize);
      logger.trace("size: " + analysis.getSequenceLength());
      logger.trace("Analyzing n-plet size " + nPletSize + " done.");
      output.addAnalysis(analysis);
      // TODO really needed? Seems to be a bug.
      // Clear up some memory - running out of heap space otherwise:
      // analysis.setSequence(null);
      // logger.info("done with " + j + "it took : " + (System.currentTimeMillis() - timeNow) + "ms");
    }
    try {
      output.createOutputs();
    } catch (IOException e) {
      System.err.println("Could not write output!");
      e.printStackTrace();
    }
  }
}
