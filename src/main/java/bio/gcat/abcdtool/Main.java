package bio.gcat.abcdtool;

import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Set;


/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class Main {
    // private static final char[] ILLEGAL_CHARACTERS = { '\n', '\r', '\t', '\0', '\f', '`', '?', '*',  '<', '>', '|', '\"', ':'};


    public static void main(String[] args) throws IOException {
        String filePath = args[0];

        long sTime = System.currentTimeMillis();
        FastFastaLoader fastaFile = new FastFastaLoader(new File(filePath));
        Map<String, Sequence<NucleotideCompound>> entries =
                fastaFile.fastaEntries();
        double msec = (System.currentTimeMillis() - sTime) / (1.0 * entries.size());
        System.out.println("|samples| = " + entries.size() + " (" + msec + " ms/read)");


        Set<Map.Entry<String, Sequence<NucleotideCompound>>> g = entries.entrySet();
        for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {

            String name = map.getKey();
            //   String name = "Random Covariance";


            int written = 0; //learned the hard way to set a maximum
             String sequence = map.getValue().getSequenceAsString();
            //   String sequence = RandomStringGenerator.randomCovarianceString(250000000,false);

            Output output = new Output(name);
            for (int j = 1; j <= 20; j++) {
//            int[] toCheck = {1, 10, 100, 10000}; // First one always takes longest, probably becuase it caches some results for later values
//            for (int j : toCheck) {

                long timeNow = System.currentTimeMillis();
                Analysis analysis = new Analysis(sequence, j);
                output.addAnalysis(analysis);
                System.out.println("done with " + j + "it took : " + (System.currentTimeMillis() - timeNow) + "ms");

            }
            if (written > 3) {//TODO : safety measure for now
                //break;
            } else {
                written++;
//                for (char illegalChar : ILLEGAL_CHARACTERS) {
//                    name = name.replaceAll(String.valueOf(illegalChar), "");
//                }
              name=  name.replaceAll(":|>|<", "");
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
        }
    }


}
