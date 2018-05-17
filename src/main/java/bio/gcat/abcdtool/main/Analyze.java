package bio.gcat.abcdtool.main;

import bio.gcat.abcdtool.analysis.NPletsAnalysis;
import bio.gcat.abcdtool.sequences.generator.ConditionalProbabilities;
import bio.gcat.abcdtool.sequences.generator.RandomSeqStringGenerator;
import bio.gcat.abcdtool.sequences.reader.FastFastaLoader;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Analyze class for command line execution of the ABCD tool.
 *
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class Analyze {
  public static void main(String[] args) throws IOException {
    if (args.length < 1) {
      System.out.println("usage: args");
      System.out.println("1.: <file> (valid path)");
      System.out.println("2.: random|randomconditional|normal|N (normal is default)");
      System.out.println("Info:");
      System.out.println("<file>: a dna sequence in FastA format");
      System.out.println("random: complete random sequence according to " +
              "base distribution in homo sapiens, chr. 1");
      System.out.println("randomconditional: random sequence with conditional " +
              "probabilities as calculated in the sequence (param 1)");
      System.out.println("normal: regular analysis (default)");
      System.out.println("N: Split analysis where sequence is " +
              "split into N parts.");
      System.exit(0);
    }
    String filePath = args[0];
    System.out.println("Using Fasta file " + args[0]);
    long sTime = System.currentTimeMillis();
    FastFastaLoader fastaFile = new FastFastaLoader(new File(filePath));
    Map<String, Sequence<NucleotideCompound>> entries =
            fastaFile.fastaEntries();
    double msec = (System.currentTimeMillis() - sTime) / (1.0 * entries.size());
    System.out.println("|entries| = " + entries.size() + " (" + msec + " ms/read)");

    /*
    List<Integer> tupleSizes = Arrays.asList(1, 2, 3, 4, 5,
            6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 50);
    */
    List<Integer> tupleSizes = Arrays.asList(1, 3, 25);
    long size = 100000; // 250000000;

    Set<Map.Entry<String, Sequence<NucleotideCompound>>> g = entries.entrySet();
    for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {
      String sequence = map.getValue().getSequenceAsString();
      String seqId = map.getKey();
      System.out.println("Id: " + seqId + ", size: " + sequence.length());

      if (args.length >= 2) {
        if (args[1].equalsIgnoreCase("rndInit")) {
          System.out.println("Method: random initial; Human Chr. 1 ");
          seqId = "RndInit-" + sequence;
          sequence = new RandomSeqStringGenerator().randomSeqString(size);
          NPletsAnalysis npa = new NPletsAnalysis(seqId, sequence, tupleSizes);
          npa.analyze();
        }
        if (args[1].equalsIgnoreCase("rndCond")) {
          System.out.println("Method: random conditional; Human Chr. 1 ");
          seqId = "RndCond-" + seqId;
          sequence = new RandomSeqStringGenerator().rndCondSeqString(size, true);
          NPletsAnalysis npa = new NPletsAnalysis(seqId, sequence, tupleSizes);
          npa.analyze();
        }
        if (args[1].equalsIgnoreCase("rndCondFile")) {
          System.out.println("Method: random conditional like in Fasta file");
          double[][] condProbabilities =
                  new ConditionalProbabilities().
                          createConditionalProbabilityMatrix(sequence);
          sequence = new RandomSeqStringGenerator().
                  rndCondSeqString(sequence.length(), condProbabilities);
          NPletsAnalysis npa = new NPletsAnalysis(seqId + "random", sequence, tupleSizes);
          npa.analyze();
        }
        if (args[1].equalsIgnoreCase("normal")) {
          System.out.println("Method: sequence like in Fasta file");
          NPletsAnalysis npa = new NPletsAnalysis(seqId, sequence, tupleSizes);
          npa.analyze();
        }
        if (args[1].equalsIgnoreCase("N")) {
          System.out.println("Method: N with sequence like in Fasta file");
          analyzeN(seqId, sequence, tupleSizes);
        }
      } else {
        // TODO redundant
        System.out.println("Method: sequence like in Fasta file");
        NPletsAnalysis npa = new NPletsAnalysis(seqId, sequence, tupleSizes);
        npa.analyze();
      }
    }
    System.out.println("Done in " + ((System.currentTimeMillis() - sTime) / 1000) + " seconds.");
  }

  /**
   * Analyze the sequence by splitting it into 1/n chunks
   *
   * @param name
   * @param sequence
   * @throws IOException
   */
  public static void analyzeN(String name, String sequence,
                              List<Integer> tupleSizes) {
    // Create analyses for each 1/N piece:
    for (int n = 1; n <= 4; n++) {
      for (int i = 0; i < n; i++) {
        String nameN = name + " split" + n + " part " + (i + 1);
        int length = sequence.length();
        int begin = (length / n) * i;
        int end = (length / n) * (i + 1);
        // System.out.println("begin : "+begin + "end : "+end);
        String sequenceN = sequence.substring(begin, end);
        NPletsAnalysis npa = new NPletsAnalysis(nameN, sequenceN, tupleSizes);
        npa.analyze();
      }
    }
  }
}





