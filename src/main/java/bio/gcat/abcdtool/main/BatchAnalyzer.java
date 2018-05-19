package bio.gcat.abcdtool.main;

import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;

/**
 * TODO documentation.
 *
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class BatchAnalyzer {

  private static String method;

  /**
   * This runs all FastA files that are passed to it.
   * If a folder is passed, it will run Analyzer on all the FastA files
   * found recursively in folder.
   *
   * @param args "-d" <Folder or file name>.
   */
  public static void main(String[] args) {

    Options options = new Options(); // Command line options.

    options.addOption(Option.builder("d")
            .longOpt("dir")
            .required()
            .hasArg()
            .desc("directory with fasta files or subdirectories")
            .argName("directory path")
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

    // Create the parser:
    CommandLineParser parser = new DefaultParser();
    try {
      // parse the command line arguments
      CommandLine line = parser.parse(options, args);

      method = "normal";
      if (line.hasOption("normal")) {
        method = "normal";
      }
      if (line.hasOption("part")) {
        method = "part";
      }

      if (line.hasOption("dir")) {
        String fileDirPath = line.getOptionValue("dir");
        System.out.println("Scanning " + fileDirPath);
        File fileDir = new File(fileDirPath);

        if (fileDir.isDirectory()) {
          runFolder(fileDir);
        } else {
          runFile(fileDir);
        }
      } else {
        printHelp(options);
        return;
      }
    } catch (ParseException exp) {
      // Oops, something went wrong:
      System.err.println("Parsing failed.  Reason: " + exp.getMessage());
      printHelp(options);
    }
  }

  /**
   * Scan the folder recursively and perform analysis on
   * fasta files.
   *
   * @param folder
   */
  private static void runFolder(File folder) {
    for (File f : folder.listFiles()) {
      if (f.isDirectory()) {
        runFolder(f);
      } else {
        runFile(f);
      }
    }
  }

  /**
   * Check if the file is a fasta file and if so, perform
   * analysis on it.
   *
   * @param file A file (not necessarily a fasta file)
   */
  private static void runFile(File file) {
    if (file.getName().endsWith(".fasta")) {
      System.out.println("Running " + file.getName());
      String[] args = new String[]{
              "--fasta", file.getAbsolutePath(),
              "--" + method
      };
      try {
        Analyzer.main(args);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void printHelp(Options options) {
    HelpFormatter formatter = new HelpFormatter();
    formatter.printHelp("java -cp <abcd-tool-*-jar-with-dependencies.jar> bio.gcat.abcdtool.main.BatchAnalyze <options>",
            "Analyzing n-plets in a DNA sequence stored in directories. Options:",
            options, "More infos at http://www.gcat.bio.");
  }
}
