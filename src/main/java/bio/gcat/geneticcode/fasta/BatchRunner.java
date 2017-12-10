package bio.gcat.geneticcode.fasta;

import java.io.File;
import java.io.IOException;



public class BatchRunner {
    /**
     * this runs all files that are passed to it,
     * if a folder is passed, it will run our program on all the files in the folder
     * TODO: Decide whether we should run multiple threads or not.
     * TODO: check if there could be a memory problem - Maybe
     * @param args Folder or file names.
     */
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        for (String s : args) {
            File f = new File(s);

            if (f.isDirectory()) {
                File[] files = f.listFiles();
                for (File file : files) {
                    Thread t = new Thread(() -> {
                        try {
                            Reader.main(new String[]{file.getAbsolutePath()});

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                    t.run();
                }

            } else if (f.isFile()) {
                f = null;
                Thread t = new Thread(() -> {
                    try {

                        Reader.main(new String[]{s});
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                t.run();
            }
        }
    }
}
