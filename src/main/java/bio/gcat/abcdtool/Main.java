package bio.gcat.abcdtool;

import bio.gcat.abcdtool.output.Output;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        System.out.println("running file " + args[0]);
        long sTime = System.currentTimeMillis();
        FastFastaLoader fastaFile = new FastFastaLoader(new File(filePath));
        Map<String, Sequence<NucleotideCompound>> entries =
                fastaFile.fastaEntries();
        double msec = (System.currentTimeMillis() - sTime) / (1.0 * entries.size());
        System.out.println("|samples| = " + entries.size() + " (" + msec + " ms/read)");


        Set<Map.Entry<String, Sequence<NucleotideCompound>>> g = entries.entrySet();
        for (Map.Entry<String, Sequence<NucleotideCompound>> map : g) {
            String sequence = map.getValue().getSequenceAsString();

            String name = map.getKey();

//double[][] covariance = new CreateCovariance().createCovarianceMatrix(sequence);

            /// RANDOMNESS
//            name = "Random 250 mio";
//            sequence = new RandomStringGenerator().randomString(250000000);
//
//            File fileTex = new File("Output/random.fasta");
//            fileTex.getParentFile().mkdirs();
//
//            PrintWriter tex = new PrintWriter(fileTex);
//            tex.println(sequence);
//            tex.close();

            Output output = new Output(name);
            for (int j = 1; j <= 20; j++) {
//            int[] toCheck = {1, 10, 100, 10000};
//            for (int j : toCheck) {

                long timeNow = System.currentTimeMillis();
                Analysis analysis = new Analysis(sequence, j);
                output.addAnalysis(analysis);
                System.out.println("done with " + j + "it took : " + (System.currentTimeMillis() - timeNow) + "ms");
            }
            output.createOutputs();


        }
        System.out.println("the calculation took " + ((System.currentTimeMillis() - sTime) / 1000) + "seconds");
    }
}





