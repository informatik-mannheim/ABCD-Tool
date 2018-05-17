package bio.gcat.abcdtool.main;

import java.io.File;
import java.io.IOException;


public class BatchAnalyzer {
  /**
   * this runs all FastA files that are passed to it,
   * if a folder is passed, it will run our program on all the FastA files in the folder
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

            System.out.println("running folder" + file.getAbsolutePath());
//                        Thread t = new Thread(() -> runFolder(file));
//                        t.run();
            runFolder(file);
          }


        }

      } else if (f.isFile()) {
        if (!f.getName().endsWith(".fasta")) {
          continue;
        }


        System.out.println("running " + s);
        f = null;
//                Thread t = new Thread(() -> {
//                    try {
//
//                        System.out.println("running  now : " + s);
//                        Analyze.main(new String[]{s});
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                });
//                t.run();
        try {
//
          System.out.println("running  now : " + s);
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
