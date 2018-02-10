package bio.gcat.abcdtool;

import bio.gcat.abcdtool.output.Output;
import bio.gcat.abcdtool.sequences.generatesequence.RandomStringGenerator;
import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
//            name = "NRandomCo 250 mio";
//            sequence = new RandomStringGenerator().randomCovarianceString(250000000,true);

//            File fileTex = new File("Output/randomCo.fasta");
//            fileTex.getParentFile().mkdirs();

//            PrintWriter tex = new PrintWriter(fileTex);
//            tex.println(">"+name);
//            for(int i =0;i<(sequence.length()/20)-1;i++){
//                tex.println(sequence.substring(i*20,(i+1)*20));
//            }
//            tex.close();


//            analyzeN(name, sequence);
            System.out.println(sequence.length());


            analyze(name, sequence);
//            analyzeRandom();

        }
        System.out.println("the calculation took " + ((System.currentTimeMillis() - sTime) / 1000) + "seconds");
    }

    public static void analyzeRandom() throws IOException {
        for (int j = 0; j < 1; j++) {
            String tempName = "RandomCo250Mio  " + j;
            System.out.println("start" + tempName);
            String tempString = new RandomStringGenerator().randomCovarianceString(250000000, true);

            System.out.println("end" + tempName);
            analyze(tempName, tempString);
        }
        for (int j = 0; j < 1; j++) {
            String tempName = "Random250mio " + j;
            System.out.println("start" + tempName);
            String tempString = new RandomStringGenerator().randomString(250000000);
            analyze(tempName, tempString);
            System.out.println("end" + tempName);
        }
    }

    public static void analyzeN(String name, String sequence) throws IOException {
        //create analyses for each 1/N piece
        for (int n = 1; n < 10; n++) {
            for (int i = 0; i < n; i++) {
                String nameN = name + " split" + n + " part " + (i + 1);
                int length = sequence.length();
                int begin = (length / n) * i;
                int end = (length / n) * (i + 1);
//                System.out.println("begin : "+begin + "end : "+end);
                String sequenceN = sequence.substring(begin, end);
                analyze(nameN, sequenceN);
            }
        }

    }

    public static void analyze(String name, String sequence) throws IOException {
        name = name ;
        Output output = new Output(name);
        for (int j = 1; j <= 20; j++) {//NOT 0
//            int[] toCheck = {1, 10, 100, 10000};
//            for (int j : toCheck) {
            System.out.println("analyzing " + j);
//            System.out.println("first string length = " + sequence.length());
//            String tempsequence = createSequence(sequence, j);
//            String tempsequence = createFirstNSequence(sequence, j);
            Analysis analysis = new Analysis(sequence, j);

            System.out.println("analysis ended" + j);
            output.addAnalysis(analysis);
            analysis.setSequence(null); // clear up some memory -running out of heap space otherwise
//            System.out.println("done with " + j + "it took : " + (System.currentTimeMillis() - timeNow) + "ms");
        }
        output.createOutputs();


    }
    private static String createFirstNSequence(String sequence, int j) {
//        sequence= sequence.replaceAll("N","");
        int stringlength = (int) (sequence.length() / ((double) 20 / j));


        return sequence.substring(0,  stringlength);
//
    }
    private static String createSequence(String sequence, int j) {
//        return sequence.substring(0,sequence.length()/(20/j));
        int stringlength = (int) (sequence.length() / ((double) 20 / j));

        String sequ = "";

//        do {
//            int startIndex = (int) (Math.random() * (stringlength));
//            if (startIndex + stringlength < sequence.length()) {
//                sequ = sequence.substring(startIndex, startIndex + stringlength);
//            }
//        } while (sequ.equals(""));
//
//        return sequ;
        StringBuilder temp = new StringBuilder();
        int sequenceLength = sequence.length();
        int position = (int) (Math.random() * (sequenceLength - stringlength));
        System.out.println("new String length = " + stringlength);
        return sequence.substring(position, position + stringlength);
//       for(int i =0;i<stringlength/20;i++ ) {
//           int position = (int)(Math.random()*(sequenceLength-stringlength));
//           temp.append(sequence.substring(position,position+stringlength));
//       }
//        System.out.println( "finished string of length" + stringlength);
//       return temp.toString();
    }
}





