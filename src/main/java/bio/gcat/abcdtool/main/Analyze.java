package bio.gcat.abcdtool.main;

import bio.gcat.abcdtool.analysis.NPletsAnalysis;
import bio.gcat.abcdtool.sequences.generator.ConditionalProbabilities;
import bio.gcat.abcdtool.sequences.generator.RandomSeqStringGenerator;
import bio.gcat.abcdtool.sequences.reader.FastFastaLoader;
import org.apache.commons.cli.*;
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

    long sTime = System.currentTimeMillis();
    Options options = new Options(); // Command line options.

    OptionGroup sourceOptions = new OptionGroup();
    sourceOptions.addOption(Option.builder("f")
            .longOpt("fasta")
            .required()
            .hasArg()
            .desc("sequence in fasta format")
            .argName("fasta filename")
            .build());
    sourceOptions.addOption(Option.builder("i")
            .longOpt("rndInit")
            .desc("random sequence with probabilities like in human chr. 1.")
            .build());
    sourceOptions.addOption(Option.builder("c")
            .longOpt("rndCond")
            .desc("random sequence with conditional probabilities like in human chr. 1.")
            .build());
    sourceOptions.addOption(Option.builder("C")
            .longOpt("rndCondFile")
            .desc("random sequence with conditional probabilities like in fasta file")
            .hasArg()
            .argName("fasta filename")
            .build());
    options.addOptionGroup(sourceOptions);

    options.addOption(Option.builder("s")
            .longOpt("size")
            .required(false)
            .hasArg()
            .desc("length of the random sequence; default is 100,000")
            .argName("integer")
            .type(Long.class)
            .build());

    OptionGroup methodOptions = new OptionGroup();
    methodOptions.addOption(Option.builder("n")
            .longOpt("normal")
            .desc("regular n-plets analysis (default method)")
            .build());
    methodOptions.addOption(Option.builder("p")
            .longOpt("part")
            .desc("partition based n-plets analysis")
            .build());
    options.addOptionGroup(methodOptions);

        /*
    List<Integer> tupleSizes = Arrays.asList(1, 2, 3, 4, 5,
            6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 50);
    */
    List<Integer> tupleSizes = Arrays.asList(1, 3, 25);

    // Create the parser:
    CommandLineParser parser = new DefaultParser();
    try {
      // parse the command line arguments
      CommandLine line = parser.parse(options, args);

      String sizeAsString = line.hasOption("size") ?
              line.getOptionValue("size") : "100000";
      long size = Long.valueOf(sizeAsString);
      String method = "normal";
      if (line.hasOption("normal")) {
        method = "method";
      }
      if (line.hasOption("part")) {
        method = "part";
      }

      if (line.hasOption("fasta")) {
        System.out.println("Method: sequence like in Fasta file");
        String filePath = line.getOptionValue("fasta");
        Map<String, Sequence<NucleotideCompound>> entries =
                loadFastaFile(filePath);
        Set<Map.Entry<String, Sequence<NucleotideCompound>>> g = entries.entrySet();
        for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {
          String sequence = map.getValue().getSequenceAsString();
          String seqId = map.getKey();
          analyze(seqId, sequence, tupleSizes, method);
        }
        // ----------------------------------------
      } else if (line.hasOption("randomInitial")) {
        System.out.println("Method: random initial; Human Chr. 1 ");
        String seqId = "RndInit";
        String sequence = new RandomSeqStringGenerator().randomSeqString(size);
        analyze(seqId, sequence, tupleSizes, method);
        // ----------------------------------------
      } else if (line.hasOption("randomConditionalFromFile")) {
        String filePath = line.getOptionValue("randomConditionalFromFile");
        System.out.println("Method: random conditional from file " +
                filePath);
        Map<String, Sequence<NucleotideCompound>> entries =
                loadFastaFile(filePath);
        Set<Map.Entry<String, Sequence<NucleotideCompound>>> g =
                entries.entrySet();
        for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {
          String sequence = map.getValue().getSequenceAsString();
          double[][] condProbabilities =
                  new ConditionalProbabilities().
                          createConditionalProbabilityMatrix(sequence);
          sequence = new RandomSeqStringGenerator().
                  rndCondSeqString(size, condProbabilities);
          String seqId = map.getKey() + "-RandomCond";
          analyze(seqId, sequence, tupleSizes, method);
        }
        // ----------------------------------------
      } else {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("Analyzer",
                "Analyzing n-plets in a DNA sequence.",
                options, "More infos at www.gcat.bio.");
        return;
      }
    } catch (ParseException exp) {
      // Oops, something went wrong:
      System.err.println("Parsing failed.  Reason: " + exp.getMessage());
    }
    System.out.println("Done in " + ((System.currentTimeMillis() - sTime) / 1000) + " seconds.");
  }

  private static void analyze(String seqId, String sequence,
                              List<Integer> tupleSizes,
                              String method) {
    switch (method) {
      case "normal":
        System.out.println("Id: " + seqId + ", size: " + sequence.length());
        NPletsAnalysis npa = new NPletsAnalysis(seqId, sequence, tupleSizes);
        npa.analyze();
        break;
      case "part":
        analyzeN(seqId, sequence, tupleSizes);
        break;
    }
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

  private static Map<String, Sequence<NucleotideCompound>>
  loadFastaFile(String filePath) {
    long sTime = System.currentTimeMillis();
    FastFastaLoader fastaFile = new FastFastaLoader(new File(filePath));
    Map<String, Sequence<NucleotideCompound>> entries =
            fastaFile.fastaEntries();
    double msec = (System.currentTimeMillis() - sTime) / (1.0 * entries.size());
    System.out.println("|entries| = " + entries.size() + " (" + msec + " ms/read)");
    return entries;
  }
}