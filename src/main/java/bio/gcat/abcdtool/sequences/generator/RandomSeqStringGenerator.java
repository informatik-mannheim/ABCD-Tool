package bio.gcat.abcdtool.sequences.generator;

import java.util.HashMap;
import java.util.Map;

public class RandomSeqStringGenerator {
  //AGCT
  // TODO cond. probs?
  private static double[][] covariance = {
          {30, 28, 21, 21},
          {26, 32, 23, 19},
          {32, 9, 31, 28},
          {19, 31, 24, 26}};
  private static double[][] coVarianceCG = {
          {21, 38, 27, 14},
          {18, 37, 31, 15},
          {20, 23, 36, 21},
          {10, 37, 32, 22}
  };
  private static double[][] condProbabilitiesHuman1 = {
          {32.654629485847264, 24.52450263153206, 17.292732418078985, 25.52813546454169},
          {28.77818791311597, 25.963261582628183, 21.08700437255217, 24.171546131703675},
          {34.89394017933991, 4.94258422999162, 25.942695290228157, 34.22078030044031},
          {21.644705609667775, 24.978215563186823, 20.588717164464687, 32.788361662680715}
  };

  /**
   * Creates a random sequence using a conditional probabilities
   * matrix.
   *
   * @param length                   Size of the sequence.
   * @param conditionalProbabilities
   * @return
   */
  public String rndCondSeqString(long length,
                                 double[][] conditionalProbabilities) {
    StringBuilder s = new StringBuilder();
    String digit = randomSeqString(1);
    s.append(digit);
    for (int i = 0; i < length - 1; i++) {
      double rndNumber = Math.random();

      int currentIndex = 0;
      switch (s.charAt(i)) {
        case 'A':
          currentIndex = 0;
          break;
        case 'G':
          currentIndex = 1;
          break;
        case 'C':
          currentIndex = 2;
          break;
        case 'T':
          currentIndex = 3;
          break;

      }
      double[] theseValues = conditionalProbabilities[currentIndex];
      if (rndNumber < theseValues[0]) {
        s.append("A");
      } else if (rndNumber < theseValues[0] + theseValues[1]) {
        s.append("G");
      } else if (rndNumber < theseValues[0] + theseValues[1] + theseValues[2]) {
        s.append("C");
      } else {
        s.append("T");
      }
    }
    return s.toString();
  }

  // TODO : change from boolean to int or enum
  // TODO: gcIsland is not used!
  public String rndCondSeqString(long length, boolean cgIsland) {
    double[][] conditionalProb = new double[4][4];
    for (int i = 0; i < condProbabilitiesHuman1.length; i++) {
      for (int j = 0; j < condProbabilitiesHuman1.length; j++) {
        conditionalProb[i][j] = condProbabilitiesHuman1[i][j] / 100;
      }
    }
    return rndCondSeqString(length, conditionalProb);
  }

  public String randomSeqString(long length) {
    Map<Character, Double> probs = new HashMap<>();
    probs.put('A', 0.291);
    probs.put('T', 0.292);
    probs.put('G', 0.209);
    probs.put('C', 0.208);
    return randomSeqString(length, probs);
  }

  public String randomSeqString(long length, Map<Character, Double> probs) {
    // TODO: Ali, please confirm.
    double sumProbA = probs.get('A');
    double sumProbT = sumProbA + probs.get('T');
    double sumProbG = sumProbT + probs.get('G');

    StringBuilder s = new StringBuilder();
    for (int i = 0; i < length; i++) {
      double rndNumber = Math.random();
      if (rndNumber < sumProbA) {
        s.append("A");
      } else if (rndNumber < sumProbT) {
        s.append("T");
      } else if (rndNumber < sumProbG) {
        s.append("G");
      } else {
        s.append("C");
      }
    }
    return s.toString();
  }
}
