package bio.gcat.abcdtool.analysis;

import bio.gcat.abcdtool.sequences.BaseEnum;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class NPletCalc {

  /**
   * Full genetic sequence.
   */
  private String sequence;

  /**
   * Size of the n-plet.
   */
  private int nPletSize;

  private long nPletCount;

  private long[][] freqs;

  public NPletCalc(String sequence, int nPletSize) {
    this.sequence = sequence;
    this.nPletSize = nPletSize;
    freqs = new long[BaseEnum.values().length][nPletSize];
    nPletCount = sequence.length() / nPletSize;
    calc();
  }

  public double getRelativeFrequency(char base, int pos) {
    return (1.0 * getFrequency(base, pos)) / getNPletCount();
  }

  public long getFrequency(char base, int pos) {
    checkPosRange(pos);
    int idx = BaseEnum.baseToIndex(base);
    return freqs[idx][pos];
  }

  public long getNPletCount() {
    return nPletCount;
  }

  public double getSkew(char b1, char b2, int pos) {
    double freqB1 = (double) getFrequency(b1, pos);
    double freqB2 = (double) getFrequency(b2, pos);
    return (freqB1 - freqB2) / (freqB1 + freqB2);
  }

  /**
   * For R.
   *
   * @param b1
   * @param b2
   * @return
   */
  public double[] getSkewsR(String b1, String b2) {
    char rb1 = b1.charAt(0);
    char rb2 = b2.charAt(0);
    double[] res = new double[nPletSize];
    for (int k = 0; k < res.length; k++) {
      res[k] = getSkew(rb1, rb2, k);
    }
    return res;
  }

  public String toString() {
    StringBuffer sb = new StringBuffer();
    for (BaseEnum base : BaseEnum.values()) {
      sb.append(base.getBase() + ": ");
      for (int p = 0; p < nPletSize; p++) {
        sb.append(getRelativeFrequency(base.getBase(), p) + "; ");
      }
      sb.append("\n");
    }
    sb.append("\nskew_A~T: ");
    for (int p = 0; p < nPletSize; p++) {
      sb.append(getSkew('A', 'T', p) + "; ");
    }
    sb.append("\nskew_C~G: ");
    for (int p = 0; p < nPletSize; p++) {
      sb.append(getSkew('C', 'G', p) + "; ");
    }
    return sb.toString();
  }

  private void calc() {
    long i = 0;
    for (char base : sequence.toCharArray()) {
      int pos = (int) (i % nPletSize); // Position in n-plet.
      switch (base) {
        case 'A':
        case 'a':
          freqs[BaseEnum.A.getIndex()][pos]++;
          break;
        case 'T':
        case 't':
        case 'U':
        case 'u':
          freqs[BaseEnum.T.getIndex()][pos]++;
          break;
        case 'C':
        case 'c':
          freqs[BaseEnum.C.getIndex()][pos]++;
          break;
        case 'G':
        case 'g':
          freqs[BaseEnum.G.getIndex()][pos]++;
          break;
        default:
          freqs[BaseEnum.UNKNOWN.getIndex()][pos]++;
      }
      i++;
    }
  }

  private void checkPosRange(int pos) {
    if (pos < 0 || pos > nPletSize) {
      throw new IllegalArgumentException("Position must be between 0 and " +
              (nPletSize - 1));
    }
  }
}
