package bio.gcat.abcdtool.sequences.generatesequence;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class createConditionalProbabiltiesTest {

    @Test
    public void createCondProabiltiesTest() {
        String sequence = "ACCCCAAACC";
        double[][] condProb = new createConditionalProbabilities().createConditionalProbbabilityMatrix("ACCCCAAACC");
        assertEquals(condProb[0][0], 0.5, 0.001);
        assertEquals(condProb[0][2], 0.5, 0.001);
        assertEquals(condProb[2][0], 0.2, 0.001);
        assertEquals(condProb[2][2], 0.8, 0.001);

        int f = 0;
        int TUPEL = 3;
        int POSITION = 2;
        for (int i = 1; i < 10; i++) {
            if (sequence.charAt(i - 1) == 'A' && (i - 1) % TUPEL == POSITION) {
                f++;
            }
        }
        System.out.println(f);
    }
}
