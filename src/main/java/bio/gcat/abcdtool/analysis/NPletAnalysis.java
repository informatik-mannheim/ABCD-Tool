package bio.gcat.abcdtool.analysis;

import bio.gcat.abcdtool.sequences.reader.Element;

import java.util.*;

/**
 * TODO documentation
 * Refactored into NPletAnalysis class, so we can have
 * the nPletSize size saved instead of recalculating it.
 *
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 */
public class NPletAnalysis {
  /**
   * Full genetic sequence.
   */
  private String sequence;

  /**
   * Size of the n-plet
   * TODO confirm by Ali
   */
  private int nPletSize;

  /**
   * length of the sequence without the unknown bases
   */
  private int sequenceLength;

  /**
   * a map containing an Element as a key and
   * the frequency of that Element in the sequence.
   */
  private Map<Element, Integer> frequenciesMap;

  /**
   * formatted in this way:
   * PN(A1), PN(T1),PN(G1),PN(C1),PN(A2),...
   */
  private int[] frequencies;

  /**
   * TODO docs
   *
   * @param sequence
   * @param nPletSize
   */
  public NPletAnalysis(String sequence, int nPletSize) {
    if (sequence.length() / 2 < nPletSize) {
      throw new IllegalArgumentException("n-plet size is too big (probably).");
    }
    this.sequence = sequence;
    this.nPletSize = nPletSize;
    this.frequencies = calculateFrequencyArray();
    this.frequenciesMap = calculateFrequenciesFast(frequencies);
  }

  public String getSequence() {
    return sequence;
  }

  public int getSequenceLength() {
    return sequenceLength;
  }

  /**
   * Calculate the maximum of an NPletAnalysis (for each of the nucleotides,
   * at a certain nPletSize length)
   *
   * @return an array with the maximum of {A,T,G,C}
   */
  public double[] getMaxima() {
    //ATGC
    double[] maxima = {0, 0, 0, 0};
    for (int i = 0; i < frequencies.length; i++) {
      if (frequencies[i] > maxima[i % maxima.length]) {
        maxima[i] = frequencies[i];
      }
    }
    return maxima;
  }

  /**
   * Calculate the minimum of an NPletAnalysis (for each of the nucleotides,
   * at a certain nPletSize length)
   *
   * @return an array with the minimum of {A,T,G,C}
   */
  public double[] getMinima() {
    double[] minima = {Integer.MAX_VALUE, Integer.MAX_VALUE,
            Integer.MAX_VALUE, Integer.MAX_VALUE};
    for (int i = 0; i < frequencies.length; i++) {
      if (frequencies[i] < minima[i % minima.length]) {
        minima[i] = frequencies[i];
      }
    }
    return minima;
  }

  /**
   * Calculate the median of an NPletAnalysis (for each of the nucleotides,
   * at a certain nPletSize length)
   *
   * @return an array with the median of {A,T,G,C}
   */
  public double[] getMedian() {
    double[] median = {0, 0, 0, 0};
    double[][] valuesArrays = new double[4][frequencies.length / 4];
    for (int i = 0; i < frequencies.length; i++) {
      valuesArrays[i % 4][i / 4] = frequencies[i];
    }
    for (int i = 0; i < valuesArrays.length; i++) {
      double[] values = valuesArrays[i];
      Arrays.sort(values);
      double medianHere;
      if (valuesArrays.length % 2 == 0)
        medianHere = (int) ((values[values.length / 2] + values[values.length / 2 - 1]) / 2);//TODO: rethink the casting
      else
        medianHere = values[values.length / 2];

      median[i] = medianHere;
    }
    return median;
  }

  /**
   * Calculate the average of an NPletAnalysis (for each of the nucleotides,
   * at a certain nPletSize length)
   *
   * @return an array with the average of {A,T,G,C}
   */
  public double[] getAverage() {
    double[] average = {0, 0, 0, 0};
    double[][] valuesArrays = new double[4][frequencies.length / 4];
    for (int i = 0; i < frequencies.length; i++) {
      valuesArrays[i % 4][i / 4] = frequencies[i];
    }
    for (int i = 0; i < valuesArrays.length; i++) {
      double[] values = valuesArrays[i];
      double sum = 0;
      double averageHere = 0;
      for (double j : values) {
        sum += j;
      }
      averageHere = sum / values.length;
      average[i] = averageHere;
    }
    return average;
  }

  /**
   * Counts the relative frequencies much faster than using a HashMap
   * <p>
   * the array is formatted in this way:
   * PN(A1), PN(T1), PN(G1), PN(C1), PN(A2),...
   *
   * @return int[] with the frequencies.
   */
  public int[] calculateFrequencyArray() {
    char[] bases = {'A', 'T', 'G', 'C'};
    int differentKeys = bases.length; //Nucleotides
    int[] map = new int[differentKeys * nPletSize];
    int counter = 0;
    char[] sequenceArray = sequence.toCharArray();

    for (char base : sequenceArray) {
      int pos = 0;
      boolean skipper = false;
      switch (base) {
        case 'A':
          pos = 0;
          break;
        case 'T':
          pos = 1;
          break;
        case 'G':
          pos = 2;
          break;
        case 'C':
          pos = 3;
          break;
        default:
          counter++;  //for example N or R or M etc
          skipper = true;
      }
      if (skipper) {
        continue;
      } else {
        sequenceLength++;
      }
      map[(counter % nPletSize) * differentKeys + pos] =
              map[(counter % nPletSize) * differentKeys + pos] + 1;
      counter = (counter + 1);
    }
    return map;
  }

  /**
   * TODO docs
   *
   * @return
   */
  public Map<Element, Integer> getFrequencies() {
    return frequenciesMap;
  }

  public Map<Element, Integer> calculateFrequencies() {

    Map<Element, Integer> frequencies = new HashMap<>();
    for (int i = 0; i < sequence.length(); i = i + nPletSize) {
      for (int j = 0; j < nPletSize; j++) {

        Element e = new Element(sequence.charAt((i + j) % sequence.length()), j);// this will add a few wrong values if the length is not dividable by the nPletSize-length. That doesn' make a difference because of the sheer number of values
        if (e.getBase() == 'A' || e.getBase() == 'T' || e.getBase() == 'G' || e.getBase() == 'C') {
          frequencies.put(e, frequencies.getOrDefault(e, 0) + 1);
        }

      }
    }
    return frequencies;
  }

  /**
   * This leverages an array in the form of
   * frequencyA1,frequencyT1,frequencyG1,frequencyC1,frequencyA2,
   * requencyT2, frequencyG2,frequencyC2
   *
   * @return
   */
  public Map<Element, Integer> calculateFrequenciesFast(int[] map) {

    Map<Element, Integer> frequencies = new HashMap<>();
    char[] bases = {'A', 'T', 'G', 'C'};
    for (int i = 0; i < map.length; i++) {
      char base = bases[i % bases.length];
      Element e = new Element(base, i / bases.length);
      frequencies.put(e, map[i]);
    }
    return frequencies;
  }

  public int getnPletSize() {
    return nPletSize;
  }

  /**
   * Return all frequencies of a specific base in all nPletSize sizes
   * and all positions.
   *
   * @param a the base that should be analyzed
   * @return a double array returning all the frequencies of base
   * in every position of every nPletSize size.
   */
  public double[] getFrequencies(char a) {
    List<Double> frequencies = new ArrayList<>();
    for (Element e : frequenciesMap.keySet()) {
      if (e.getBase() == a) {
        frequencies.add((double) frequenciesMap.get(e) / ((double) sequenceLength / nPletSize));
      }
    }
    double[] values = frequencies.stream().mapToDouble(d -> d).toArray(); //identity function, Java unboxes automatically to get the double value

    return values;
  }

  /**
   * @param e
   * @return relative probability
   */
  public double getRelativeProbability(Element e) {
    double divident = sequenceLength / getnPletSize();
    return (double) getFrequencies().getOrDefault(e, 0) / divident;
  }
}
