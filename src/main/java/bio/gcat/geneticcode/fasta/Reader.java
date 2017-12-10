package bio.gcat.geneticcode.fasta;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystem;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;

import static java.lang.Thread.sleep;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class Reader {

    public static void main(String[] args) throws IOException {
        String filePath = "";
        try {
          filePath  = args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            filePath = "files/demo.fasta";
            System.out.printf("The File path should be passed as an argument in the settings, defaulting to files/demo.fasta");
        }
        long sTime = System.currentTimeMillis();
        FastFastaLoader fastaFile = new FastFastaLoader(new File(filePath));
        Map<String, Sequence<NucleotideCompound>> entries =
                fastaFile.fastaEntries();
        double msec = (System.currentTimeMillis() - sTime) / (1.0 * entries.size());
        System.out.println("|samples| = " + entries.size() + " (" + msec + " ms/read)");


        Set<Map.Entry<String, Sequence<NucleotideCompound>>> g = entries.entrySet();
        for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {

//            Map.Entry<String, Sequence<NucleotideCompound>> map = g.iterator().next();
            String name = map.getKey();


            int written = 0; //learned the hard way to set a maximum
            String sequence = map.getValue().getSequenceAsString();
//       String sequence = randomString(250000);
            Output output = new Output(name);
            // for(int j = 1; j<=20 ; j++){
            int[] toCheck = {1, 10, 100, 10000}; // First one always takes longest, probably becuase it caches some results for later values

            for (int j : toCheck) {
                long timeNow = System.currentTimeMillis();
                Analysis analysis = new Analysis(sequence, j);
                output.addAnalysis(analysis);
                System.out.println("done with " + j + "it took : " + (System.currentTimeMillis() - timeNow) + "ms");

            }
            if (written > 3) {//TODO : safety measure for now,
                break;
            } else {
                written++;
                File file = new File(name + File.separator + new Timestamp(System.currentTimeMillis()) + "output.html");
                file.getParentFile().mkdirs();

                PrintWriter out = new PrintWriter(file);
                out.println(output.toHTML());
                out.close();

                File fileTex = new File(name + File.separator + new Timestamp(System.currentTimeMillis()) + "table.tex");
                file.getParentFile().mkdirs();

                PrintWriter tex = new PrintWriter(fileTex);
                tex.println(output.outputAsLatexTable());
                tex.close();

            }
            System.out.println("the calculation took " + ((System.currentTimeMillis() - sTime) / 1000) + "seconds");
//        System.out.printf("Frequency A %f, G %f, C %f, T %f",frequencyA,frequencyG,frequencyC,frequencyT);
        }
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


}
