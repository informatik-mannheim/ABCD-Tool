package bio.gcat.geneticcode.fasta;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompound;
import org.biojava.nbio.core.sequence.compound.AminoAcidCompoundSet;
import org.biojava.nbio.core.sequence.compound.DNACompoundSet;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.io.FastaReader;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;
import org.biojava.nbio.core.sequence.io.FileProxyProteinSequenceCreator;
import org.biojava.nbio.core.sequence.io.GenericFastaHeaderParser;
import org.biojava.nbio.core.sequence.template.Sequence;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class FastLoadDemo {

    public static void main(String[] args) throws IOException {
        String filePath = "files/demo.fasta";
        long sTime = System.currentTimeMillis();
        FastFastaLoader fastaFile = new FastFastaLoader(new File(filePath));
        Map<String, Sequence<NucleotideCompound>> entries =
                fastaFile.fastaEntries();
        double msec = (System.currentTimeMillis() - sTime) / (1.0 * entries.size());
   //     doSth(entries.get(">BC047343.2 Homo sapiens cDNA clone IMAGE:5177205"));
        System.out.println("|samples| = " + entries.size() + " (" + msec + " ms/read)");

      //  File file = new File(filePath);
        //LinkedHashMap<String, DNASequence> sequence;
          //sequence = FastaReaderHelper.readFastaDNASequence(file);



        String s = entries.entrySet().iterator().next().getValue().getSequenceAsString();
        //String s = randomString();
        Map<Character, Integer> frequencies = getFrequencies(s);
        Map<Element,Integer> frequenciesDuplet = getFrequencies(s,2);
        Map<Element,Integer> frequenciesTriplet = getFrequencies(s,3);
        double frequencyA = (double) frequencies.get('A')/s.length();
        double frequencyG = (double) frequencies.get('G')/s.length();
        double frequencyC = (double) frequencies.get('C')/s.length();
        double frequencyT = (double) frequencies.get('T')/s.length();
     //  double frequencyA1 = (double) frequenciesDuplet.get(new Element('A',0))/(s.length()/2);
        //double frequencyA2 = (double) frequenciesDuplet.get(new Element('A',1))/(s.length()/2);


        Output.getAnalyzedTupels().add(frequenciesDuplet);
        Output.getAnalyzedTupels().add(frequenciesTriplet);

        //Output.getAsTable();
        System.out.println(Output.outputAsLatexTable());
        System.out.printf("Frequency A %f, G %f, C %f, T %f",frequencyA,frequencyG,frequencyC,frequencyT);
    }

    private static String randomString(int length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double number = Math.random();
            if (number < 0.30) {
                s.append("A");
            } else if (number < 0.6) {
                s.append("T");
            } else if (number < 0.8) {
                s.append("G");
            } else {
                s.append("C");
            }
        }
        return s.toString();
    }


    static Map<Character, Integer> getFrequencies(String sequence) {

        Map<Character, Integer> frequencies = new HashMap<>();
        for (char i : sequence.toCharArray()) {
            frequencies.put(i, frequencies.getOrDefault(i, 0) + 1);
        }
        return frequencies;
    }

    static Map<Element, Integer> getFrequencies(String sequence, int tupel) {

        Map<Element, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < sequence.length(); i = i + tupel) {
            for (int j=0; j < tupel; j++) {

                Element e = new Element(sequence.charAt((i+j)%sequence.length()),j);// this will add a few wrong values if the length is not dividable by the tupel-length. That doesn' make a difference because of the sheer number of values
                frequencies.put(e, frequencies.getOrDefault(e, 0) + 1);
            }
        }
        return frequencies;
    }
}
