package bio.gcat.abcdtool.sequences.generator;

import java.util.Arrays;

/**
 * Creates a matrix of conditional probabilities.
 * TODO This is quite a simple helper class -> move into another
 * class?
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 */
public class ConditionalProbabilities {

  /**
   * Creates a matrix of conditional probabilities.
   * @param sequence
   * @return
   */
  public double[][] createConditionalProbabilityMatrix(String sequence) {
    // TODO more documentation. Src copied?
    int[][] table = new int[4][4];
    char[] sequenceArray = sequence.toCharArray();
    for (int i = 0; i < sequenceArray.length - 1; i++) {
      char current = sequenceArray[i];
      char next = sequenceArray[i + 1];
      int first = getPosition(current);
      int second = getPosition(next);

      if (first == -1 || second == -1) {
        continue;
      }
      table[first][second]++;
    }

    double[][] frequency = new double[4][4];
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
    System.out.println(Arrays.toString(frequency[0]));
    System.out.println(Arrays.toString(frequency[1]));
    System.out.println(Arrays.toString(frequency[2]));
    System.out.println(Arrays.toString(frequency[3]));
    return frequency;
  }

  /**
   * only works for AGCT (not for the tables we had in our Output)
   *
   * @param c a char
   * @return A=0, G=1, C=2,T=3
   */
  private int getPosition(char c) {
    if (c - 'A' == 0) {
      return 0;
    }
    if (c - 'G' == 0) {
      return 1;
    }
    if (c - 'C' == 0) {
      return 2;
    }
    if (c - 'T' == 0) {
      return 3;
    }
    return -1; // I would throw an exception but that would take 5 years
  }
}
