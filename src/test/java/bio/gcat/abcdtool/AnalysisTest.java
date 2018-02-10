package bio.gcat.abcdtool;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;
import org.junit.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnalysisTest {

    @Test
    public void getFrequenciesComparison() {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        FastFastaLoader fastaFile = new FastFastaLoader(new File("../../files/demo.fasta"));
        Map<String, Sequence<NucleotideCompound>> entries =
                fastaFile.fastaEntries();


        Set<Map.Entry<String, Sequence<NucleotideCompound>>> g = entries.entrySet();
        for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {
            String sequence = map.getValue().getSequenceAsString();
            Analysis analysis = new Analysis(sequence, 100);

            Map<Element, Integer> map1 = analysis.calculateFrequencies();
            Map<Element, Integer> map2 =
                    analysis.calculateFrequenciesFast(analysis.calculateFrequencyArray());
            for (Element e : map1.keySet()) {
                if (Math.abs(map1.get(e) - map2.get(e)) == 1) {
                    System.out.println(e);
                }

                assertEquals(map1.get(e), map2.get(e), 1);
            }
        }
    }

    @Test
    public void calculateFrequencies() {
    }

    @Test
    public void calculateFrequenciesFast() {
    }

    @org.junit.Before
    public void setUp() throws Exception {
        sequence = "AATTGGCCAA";
        a = new Analysis(sequence, 1);
        b = new Analysis(sequence, 2);

    }

    Analysis a;
    Analysis b;
    String sequence;

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void getSequence() {
        assertEquals(a.getSequence(), sequence);
    }

    @org.junit.Test
    public void getSequenceLength() {
        assertEquals(a.getSequenceLength(), sequence.length());
    }

    @org.junit.Test
    public void getMaxima() {
        assertEquals(a.getMaxima()[0], 4, 0.1);

        assertEquals(b.getMaxima()[0], 2, 0.1);

    }

    @org.junit.Test
    public void getMinima() {
    }

    @org.junit.Test
    public void getMedian() {
    }

    @org.junit.Test
    public void getAverage() {
    }

    @org.junit.Test
    public void calculateFrequencyArray() {
        int[] frequenciesA = {4, 2, 2, 2};
        int[] frequenciesB = {2, 1, 1, 1, 2, 1, 1, 1};
        assertTrue(Arrays.equals(frequenciesA, a.calculateFrequencyArray()));
        assertTrue(Arrays.equals(frequenciesB, b.calculateFrequencyArray()));
//        assertEquals(frequenciesB,b.calculateFrequencyArray());

    }

    @org.junit.Test
    public void getFrequencies() {
        int[] frequenciesA = {4, 2, 2, 2};
        int[] frequenciesB = {2, 1, 1, 1, 2, 1, 1, 1};
        assertEquals(a.calculateFrequenciesFast(frequenciesA), a.getFrequencies());
        assertEquals(b.calculateFrequenciesFast(frequenciesB), b.getFrequencies());
    }

    @org.junit.Test
    public void calculateFrequencies1() {
    }

    @org.junit.Test
    public void calculateFrequenciesFast1() {
    }

    @org.junit.Test
    public void getTupel() {
        assertEquals(a.getTupel(), 1);
        assertEquals(b.getTupel(), 2);
    }

    @org.junit.Test
    public void getFrequencies1() {
    }

    @org.junit.Test
    public void setSequence() {
    }

    @org.junit.Test
    public void getFrequency() {
    }
}
