package bio.gcat.abcdtool;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnalysisTest {

    @Test
    public void getFrequenciesComparison() {
        FastFastaLoader fastaFile = new FastFastaLoader(new File("/Users/ali/Documents/Studium/Bachelor Arbeit/FastFasta/files/demo.fasta"));
        Map<String, Sequence<NucleotideCompound>> entries =
                fastaFile.fastaEntries();


        Set<Map.Entry<String, Sequence<NucleotideCompound>>> g = entries.entrySet();
        for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {
            String sequence = map.getValue().getSequenceAsString();
            Analysis analysis = new Analysis(sequence, 10000);

            Map<Element, Integer> map1 = analysis.calculateFrequencies();
            Map<Element, Integer> map2 =
                    analysis.calculateFrequenciesFast(analysis.calculateFrequencyArray());
            for(Element e : map1.keySet()) {
                if(Math.abs(map1.get(e)-map2.get(e))==1){
                    System.out.println(e);
                }

                assertEquals(map1.get(e), map2.get(e),1);
            }
        }
    }

    @Test
    public void calculateFrequencies() {
    }

    @Test
    public void calculateFrequenciesFast() {
    }
}
