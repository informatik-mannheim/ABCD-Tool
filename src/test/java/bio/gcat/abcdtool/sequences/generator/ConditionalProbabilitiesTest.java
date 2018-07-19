package bio.gcat.abcdtool.sequences.generator;

import org.junit.Test;

import static bio.gcat.abcdtool.sequences.BaseEnum.A;
import static bio.gcat.abcdtool.sequences.BaseEnum.C;
import static bio.gcat.abcdtool.sequences.BaseEnum.T;
import static org.junit.Assert.assertEquals;

public class ConditionalProbabilitiesTest {

  @Test(expected = IllegalArgumentException.class)
  public void condProabiltiesTestEmpty() {
    ConditionalProbabilities cp = new ConditionalProbabilities();
    double[][] condProb = cp.createConditionalProbabilityMatrix("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void condProabiltiesTestSingle() {
    ConditionalProbabilities cp = new ConditionalProbabilities();
    double[][] condProb = cp.createConditionalProbabilityMatrix("A");
  }

  @Test
  public void condProabiltiesTestTwo() {
    String sequence = "AA";
    ConditionalProbabilities cp = new ConditionalProbabilities();
    double[][] condProb = cp.createConditionalProbabilityMatrix(sequence);
    assertEquals(1, condProb[A.getIndex()][A.getIndex()], 0.001);
    for (int i = 1; i < 5; i++) {
      for (int j = 0; i < 5; i++) {
        assertEquals(Double.NaN, condProb[i][j], 0.001);
      }
    }
  }

  @Test
  public void condProabiltiesTestMany1() {
    String sequence = "AAATTT";
    // A->A: 2 or 2/3
    // A->T: 1 or 1/3
    // T->T: 2 or 2/2

    ConditionalProbabilities cp = new ConditionalProbabilities();
    double[][] condProb = cp.createConditionalProbabilityMatrix(sequence);
    assertEquals(2.0/3.0, condProb[A.getIndex()][A.getIndex()], 0.001);
    assertEquals(1.0/3.0, condProb[A.getIndex()][T.getIndex()], 0.001);
    assertEquals(2.0/2.0, condProb[T.getIndex()][T.getIndex()], 0.001);
  }

  @Test
  public void condProabiltiesTestMany2() {
    String sequence = "ACCCCAAACC";
    ConditionalProbabilities cp = new ConditionalProbabilities();
    double[][] condProb = cp.createConditionalProbabilityMatrix(sequence);
    assertEquals(0.5, condProb[A.getIndex()][A.getIndex()], 0.001);
    assertEquals(0.5, condProb[A.getIndex()][C.getIndex()], 0.001);
    assertEquals(0.2, condProb[C.getIndex()][A.getIndex()], 0.001);
    assertEquals(0.8, condProb[C.getIndex()][C.getIndex()], 0.001);

    // TODO what is this good for?
    int f = 0;
    int TUPEL = 3;
    int POSITION = 2;
    for (int i = 1; i < 10; i++) {
      if (sequence.charAt(i - 1) == 'A' && (i - 1) % TUPEL == POSITION) {
        f++;
      }
    }
    assertEquals(1, f);
  }
}
