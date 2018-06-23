package bio.gcat.abcdtool.analysis;

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
    freqs = new long[BASES.values().length][nPletSize];
    nPletCount = sequence.length() / nPletSize;
    calc();
  }

  public double getRelativeFrequency(char base, int pos) {
    return (1.0 * getFrequency(base, pos)) / getNPletCount();
  }

  public long getFrequency(char base, int pos) {
    checkPosRange(pos);
    int idx = baseToIndex(base);
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
    for (BASES base : BASES.values()) {
      sb.append(base.base + ": ");
      for (int p = 0; p < nPletSize; p++) {
        sb.append(getRelativeFrequency(base.base, p) + "; ");
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
          freqs[BASES.A.idx][pos]++;
          break;
        case 'T':
        case 't':
          freqs[BASES.T.idx][pos]++;
          break;
        case 'C':
        case 'c':
          freqs[BASES.C.idx][pos]++;
          break;
        case 'G':
        case 'g':
          freqs[BASES.G.idx][pos]++;
          break;
        default:
          freqs[BASES.UNKNOWN.idx][pos]++;
      }
      i++;
    }
  }

  private int baseToIndex(char base) {
    int idx = BASES.UNKNOWN.idx;
    switch (base) {
      case 'A':
        idx = BASES.A.idx;
        break;
      case 'T':
        idx = BASES.T.idx;
        break;
      case 'C':
        idx = BASES.C.idx;
        break;
      case 'G':
        idx = BASES.G.idx;
    }
    return idx;
  }

  private void checkPosRange(int pos) {
    if (pos < 0 || pos > nPletSize) {
      throw new IllegalArgumentException("Position must be between 0 and " +
              (nPletSize - 1));
    }
  }

  private enum BASES {
    A('A', 0), T('T', 1), C('C', 2), G('G', 3), UNKNOWN('N', 4);

    private char base;
    private int idx;

    BASES(char base, int idx) {
      this.base = base;
      this.idx = idx;
    }
  }
}
