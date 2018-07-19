package bio.gcat.abcdtool.sequences.generator;

import bio.gcat.abcdtool.sequences.BaseEnum;

/**
 * Creates a matrix of conditional probabilities.
 * TODO This is quite a simple helper class -> move into another
 * class?
 * TODO static?
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class ConditionalProbabilities {

  /**
   * Creates a matrix of conditional probabilities P(N | M) where
   * N and M are the bases A, T(U), C, G and N. N is an unknown base.
   * The matrix has 5 rows and columns. The row and column labels are
   * A, T(U), C, G and N. N are the rows, M the columns.
   * @param sequence
   * @return Matrix.
   */
  public double[][] createConditionalProbabilityMatrix(String sequence) {
    if (sequence.length() < 2) {
      throw new IllegalArgumentException("Sequence must have at least two bases.");
    }

    int[][] table = new int[5][5]; // 4 bases + unknown.
    char[] sequenceArray = sequence.toCharArray();
    for (int i = 0; i < sequenceArray.length - 1; i++) {
      char currentBase = sequenceArray[i];
      char nextBase = sequenceArray[i + 1];
      int firstIdx = BaseEnum.baseToIndex(currentBase);
      int secondIdx = BaseEnum.baseToIndex(nextBase);

      table[firstIdx][secondIdx]++;
    }

    double[][] frequency = new double[5][5];
    for (int i = 0; i < frequency.length; i++) {
      int[] column = table[i];
      int sum = 0;
      for (int value : column) {
        sum += value;
      }
      for (int j = 0; j < column.length; j++) {
        frequency[i][j] = (double) column[j] / sum;
      }
    }
    return frequency;
  }
}
