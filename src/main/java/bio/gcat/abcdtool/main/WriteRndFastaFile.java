package bio.gcat.abcdtool.main;

import bio.gcat.abcdtool.sequences.generator.RandomSeqStringGenerator;
import bio.gcat.abcdtool.sequences.writer.FastaFileWriter;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Generates a fasta file with random bases.
 *
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class WriteRndFastaFile {

  private static Logger logger;

  /**
   * @param args Command line options. See -help for help.
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {

    System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
    logger = LoggerFactory.getLogger(WriteRndFastaFile.class);

    Options options = new Options(); // Command line options.

    OptionGroup sourceOptions = new OptionGroup();
    sourceOptions.addOption(Option.builder("i")
            .longOpt("rndInit")
            .desc("random sequence with probabilities like in human chr. 1.")
            .build());
    sourceOptions.addOption(Option.builder("c")
            .longOpt("rndCond")
            .desc("random sequence with conditional probabilities like in human chr. 1.")
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
    options.addOption(Option.builder("o")
            .longOpt("output")
            .required(false)
            .hasArg()
            .desc("output file name; default is rnd.fasta")
            .argName("string")
            .type(String.class)
            .build());

    long size = 100000;
    RandomSeqStringGenerator gen = new RandomSeqStringGenerator();
    String seq = null;
    String method = null;
    String outputFile = null;

    // Create the parser:
    CommandLineParser parser = new DefaultParser();
    try {
      // parse the command line arguments
      CommandLine line = parser.parse(options, args);

      String sizeAsString = line.hasOption("size") ?
              line.getOptionValue("size") : "100000";
      size = Long.valueOf(sizeAsString);

      outputFile = line.hasOption("output") ?
              line.getOptionValue("output") : "rnd.fasta";

      if (line.hasOption("rndInit")) {
        logger.info("Method: random initial; Human Chr. 1 ");
        seq = gen.randomSeqString(size);
        method = "random";
      } else if (line.hasOption("rndCond")) {
        logger.info("Method: random conditional; Human Chr. 1 ");
        seq = gen.rndCondSeqString(size);
        method = "cond. random";
      } else {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("java -cp <abcd-tool-*-jar-with-dependencies.jar> bio.gcat.abcdtool.main.WriteRndFastaFile <options>",
                "Create a fasta files with a random sequence. Options:",
                options, "More infos at http://www.gcat.bio.");
        return;
      }
    } catch (ParseException exp) {
      // Oops, something went wrong:
      System.err.println("Parsing failed.  Reason: " + exp.getMessage());
    }

    FastaFileWriter ffw = new FastaFileWriter();
    String headertext = method + " human chr1";
    ffw.writeSequence(seq, outputFile, headertext);
  }
}