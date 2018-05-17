package bio.gcat.abcdtool.main;

import java.io.File;
import java.io.IOException;


public class BatchAnalyzer {
  /**
   * This runs all FastA files that are passed to it.
   * If a folder is passed, it will run Analyze on all the FastA files
   * found in the sub (!) folders.
   *
   * @param args Folder or file names.
   */
  public static void main(String[] args) {
    for (String s : args) {
      File f = new File(s);

      if (f.isDirectory()) {
        File[] files = f.listFiles();
        for (File file : files) {
          if (file.isDirectory()) {
            System.out.println("Running folder" + file.getAbsolutePath());
            runFolder(file);
          }
        }
      } else if (f.isFile()) {
        if (!f.getName().endsWith(".fasta")) {
          continue;
        }

        System.out.println("Running " + s);
        f = null;
        try {
          Analyze.main(new String[]{s});
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public static void runFolder(File folder) {
    try {
      for (File f : folder.listFiles()) {
        if (f.isFile() && f.getName().endsWith(".fasta")) {

          System.out.println("running now : " + f.getAbsolutePath());
          Analyze.main(new String[]{f.getAbsolutePath()});
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
