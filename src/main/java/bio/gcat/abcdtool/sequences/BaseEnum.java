package bio.gcat.abcdtool.sequences;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public enum BaseEnum {
  A('A', 0), T('T', 1), U('U', 1), C('C', 2), G('G', 3), UNKNOWN('N', 4);

  private char base;
  private int idx;

  BaseEnum(char base, int idx) {
    this.base = base;
    this.idx = idx;
  }

  public char getBase() {
    return this.base;
  }

  public int getIndex() {
    return this.idx;
  }

  public static int baseToIndex(char base) {
    int idx = BaseEnum.UNKNOWN.idx;
    switch (base) {
      case 'A':
      case 'a':
        idx = BaseEnum.A.idx;
        break;
      case 'T':
      case 't':
      case 'U':
      case 'u':
        idx = BaseEnum.T.idx;
        break;
      case 'C':
      case 'c':
        idx = BaseEnum.C.idx;
        break;
      case 'G':
      case 'g':
        idx = BaseEnum.G.idx;
    }
    return idx;
  }
}

