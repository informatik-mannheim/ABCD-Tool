package bio.gcat.abcdtool;

import java.util.*;

//Refactored into Analysis class, so we can have the tupel size saved instead of recalculating it
public class Analysis {
    /**
     * the full genetic sequence
     */
    private String sequence;

    public String getSequence() {
        return sequence;
    }

    /**
     * the size of the n-plet
     */
    private int tupel;

    public int getSequenceLength() {

        return sequenceLength;
    }

    /**
     * length of the sequence without the unknown bases
     */
    private int sequenceLength;
    /**
     * a map containing an Element as a key and the frequency of that Element in the sequence
     */
    private Map<Element, Integer> frequenciesMap;

    /**
     * formatted in this way:
     * PN(A1), PN(T1),PN(G1),PN(C1),PN(A2),...
     */
    private int[] frequencies;


    public Analysis(String sequence, int tupel) {
        if (sequence.length() / 2 < tupel) {
            throw new IllegalArgumentException("the tupel is too big (probably)");
        }
        this.sequence = sequence;
        this.tupel = tupel;
        this.frequencies = calculateFrequencyArray();
        this.frequenciesMap = calculateFrequenciesFast(frequencies);
    }

    /**
     * calculate the maximum of an Analysis (for each of the nucleotides, at a certain tupel length)
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
        /**
         * calculate the minimum of an Analysis (for each of the nucleotides, at a certain tupel length)
         * @return an array with the minimum of {A,T,G,C}
         */}

    public double[] getMinima() {
        double[] minima = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] < minima[i % minima.length]) {
                minima[i] = frequencies[i];
            }
        }
        return minima;

    }

    /**
     * calculate the median of an Analysis (for each of the nucleotides, at a certain tupel length)
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
     * calculate the average of an Analysis (for each of the nucleotides, at a certain tupel length)
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
     * counts the relative frequencies much faster than using a HashMap
     * <p>
     * the array is formatted in this way:
     * PN(A1), PN(T1),PN(G1),PN(C1),PN(A2),...
     *
     * @return int[] with the Frequencies
     */
    public int[] calculateFrequencyArray() {
        char[] bases = {'A', 'T', 'G', 'C'};
        int differentKeys = bases.length; //Nucleotides
        int[] map = new int[differentKeys * tupel];
        int counter = 0;
        char[] sequenceArray = sequence.toCharArray();

        for (char c : sequenceArray) {
            int pos = 0;
            boolean skipper = false;
            switch (c) {
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
            map[(counter % tupel) * differentKeys + pos] = map[(counter % tupel) * differentKeys + pos] + 1;
            counter = (counter + 1);
        }
        return map;
    }


    public Map<Element, Integer> getFrequencies() {
        return frequenciesMap;
    }


    public Map<Element, Integer> calculateFrequencies() {

        Map<Element, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < sequence.length(); i = i + tupel) {
            for (int j = 0; j < tupel; j++) {

                Element e = new Element(sequence.charAt((i + j) % sequence.length()), j);// this will add a few wrong values if the length is not dividable by the tupel-length. That doesn' make a difference because of the sheer number of values
                if (e.getBase() == 'A' || e.getBase() == 'T' || e.getBase() == 'G' || e.getBase() == 'C') {
                    frequencies.put(e, frequencies.getOrDefault(e, 0) + 1);
                }

            }
        }
        return frequencies;
    }

    /**
     * this leverages an array in the form of
     * frequencyA1,frequencyT1,frequencyG1,frequencyC1,frequencyA2,frequencyT2, frequencyG2,frequencyC2
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

    public int getTupel() {
        return tupel;
    }

    public double[] getFrequencies(char a) {
        List<Double> frequencies = new ArrayList<>();
        for (Element e : frequenciesMap.keySet()) {
            if (e.getBase() == a) {
                frequencies.add((double) frequenciesMap.get(e) / (sequenceLength/tupel));
            }
        }
        double[] values = frequencies.stream().mapToDouble(d -> d).toArray(); //identity function, Java unboxes automatically to get the double value

        return values;
//        return frequencies;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public double getFrequency(Element e) {
        double divident = sequenceLength / getTupel();

        return (double)getFrequencies().getOrDefault(e,0) / divident;

    }

}
