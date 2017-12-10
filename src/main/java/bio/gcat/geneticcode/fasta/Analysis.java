package bio.gcat.geneticcode.fasta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Refactored into Analysis class, so we can have the tupel size saved instead of recalculating it
public class Analysis {
    private String sequence;
    private int tupel;
    private Map<Element, Integer> frequenciesMap;
    private int[] frequencies;


    public Analysis(String sequence, int tupel) {
        this.sequence = sequence;
        this.tupel = tupel;
        this.frequencies = calculateFrequencyArray();
        this.frequenciesMap = calculateFrequenciesFast(frequencies);
    }
public int[] getMaxima(){
        //ATGC
    int[] maxima = {0,0,0,0};
    for(int i = 0;i<frequencies.length;i++){
        if(frequencies[i]>maxima[i%maxima.length]){
            maxima[i]= frequencies[i];
        }
    }
    return maxima;
}
public int[] getMinima(){
    int[] minima = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
    for(int i = 0;i<frequencies.length;i++){
        if(frequencies[i]<minima[i%minima.length]){
            minima[i]= frequencies[i];
        }
    }
    return minima;

}
public int[] getMedian(){
     int[] median = {0,0,0,0};
     int[][] valuesArrays = new int[4][frequencies.length/4];
     for(int i = 0;i<valuesArrays.length;i++) {
         int[] values = valuesArrays[i];
         Arrays.sort(values);
         int medianHere;
         if (valuesArrays.length % 2 == 0)
             medianHere = (int)(((double) values[values.length / 2] + (double) values[values.length / 2 - 1]) / 2);//TODO: rethink the casting
         else
             medianHere =values[values.length / 2];

         median[i] = medianHere;
     }
     return median;
}
    public int[] getAverage(){
        int[] average = {0,0,0,0};
        int[][] valuesArrays = new int[4][frequencies.length/4];
        for(int i = 0;i<valuesArrays.length;i++) {
            int[] values = valuesArrays[i];
            int sum=0;
            int averageHere = 0;
            for(int j : values){
                sum+=j;
            }
            averageHere = sum/values.length;
            average[i]=averageHere;

        }
        return average;
    }

    private int[] calculateFrequencyArray() {
        int differentKeys = 4; //Nucleotides
        int[] map = new int[differentKeys * tupel];
        int counter = 0;
        char[] sequenceArray = sequence.toCharArray();

        loop:
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
            }
            map[(counter % tupel) * differentKeys + pos]++;
            counter = (counter + 1);
        }
        return map;
    }

  /*  public Map<Character, Integer> getFrequencies() {

        Map<Character, Integer> frequencies = new HashMap<>();
        for (char i : sequence.toCharArray()) {
            frequencies.put(i, frequencies.getOrDefault(i, 0) + 1);
        }
        return frequencies;
    }*/


  public Map<Element,Integer> getFrequencies(){
      return frequenciesMap;
  }


    //TODO: create one of these for all possible characters, not just ATGC (for the zipping)
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
}
