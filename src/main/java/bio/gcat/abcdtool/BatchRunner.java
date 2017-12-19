package bio.gcat.abcdtool;

import java.io.File;
import java.io.IOException;



public class BatchRunner {
    /**
     * this runs all files that are passed to it,
     * if a folder is passed, it will run our program on all the files in the folder
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
                            Main.main(new String[]{file.getAbsolutePath()});

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

                        Main.main(new String[]{s});
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                t.run();
            }
        }
    }
}
