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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class FastLoadDemo {

  public static void main(String[] args) throws IOException {
    String filePath = "dna.fasta";
    long sTime = System.currentTimeMillis();
    FastFastaLoader fastaFile = new FastFastaLoader(new File(filePath));
    Map<String, Sequence<NucleotideCompound>> entries =
            fastaFile.fastaEntries();
    double msec = (System.currentTimeMillis() - sTime) / (1.0 * entries.size());
    System.out.println("|samples| = " + entries.size() + " (" + msec + " ms/read)");


    String s = entries.entrySet().iterator().next().getValue().getSequenceAsString();
    // String s = randomString(500000000);

    // for(int j = 1; j<=20 ; j++){
    int[] toCheck = {1, 10, 100, 1000};
    for (int j : toCheck) {
      long timeNow = System.currentTimeMillis();
      Map<Element, Integer> frequencies = getFrequencies(s, j);
      Output.getAnalyzedTupels().add(frequencies);
      System.out.println("done with " + j + "it took : " + (System.currentTimeMillis() - timeNow) + "ms");

    }

    PrintWriter out = new PrintWriter("f" + System.currentTimeMillis() + "-output.html");
    out.println(Output.toHTML());
    out.close();

    PrintWriter tex = new PrintWriter("f" + System.currentTimeMillis() + "-table.tex");
    tex.println(Output.outputAsLatexTable());
    tex.close();


    System.out.println("the calculation took " + ((System.currentTimeMillis() - sTime) / 1000) + "seconds");
//        System.out.printf("Frequency A %f, G %f, C %f, T %f",frequencyA,frequencyG,frequencyC,frequencyT);
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
      for (int j = 0; j < tupel; j++) {

        Element e = new Element(sequence.charAt((i + j) % sequence.length()), j);// this will add a few wrong values if the length is not dividable by the tupel-length. That doesn' make a difference because of the sheer number of values
        if (e.getBase() == 'A' || e.getBase() == 'T' || e.getBase() == 'G' || e.getBase() == 'C') {
          frequencies.put(e, frequencies.getOrDefault(e, 0) + 1);
        }

      }
    }
    return frequencies;
  }
}
