package bio.gcat.abcdtool.sequences.generatesequence;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class CreateCovarianceTest {

    @Test
    public void createCovarianceMatrix() {
        String sequence = "ACCCCAAACC";
        double[][] covariance = new CreateCovariance().createCovarianceMatrix("ACCCCAAACC");
        assertEquals(covariance[0][0], 0.5, 0.001);
        assertEquals(covariance[0][2], 0.5, 0.001);
        assertEquals(covariance[2][0], 0.2, 0.001);
        assertEquals(covariance[2][2], 0.8, 0.001);

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
