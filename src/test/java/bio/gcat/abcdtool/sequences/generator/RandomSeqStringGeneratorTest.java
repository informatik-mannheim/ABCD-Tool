package bio.gcat.abcdtool.sequences.generator;

import org.junit.Test;

import static java.lang.Double.NaN;
import static org.junit.Assert.assertEquals;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class RandomSeqStringGeneratorTest {

  public static double[][] cp1 = {
          {0.3670490, 0.2460897, 0.1657977, 0.22106361, 0},
          {0.2602564, 0.2961538, 0.1769231, 0.26666667, 0},
          {0.3759398, 0.3402256, 0.2030075, 0.08082707, 0},
          {0.3615520, 0.2328042, 0.2222222, 0.18342152, 0},
          {NaN, NaN, NaN, NaN, NaN}
  };

  public static double[][] cpOnlyA = {
          {1, 0, 0, 0, 0},
          {1, 0, 0, 0, 0},
          {1, 0, 0, 0, 0},
          {1, 0, 0, 0, 0},
          {NaN, NaN, NaN, NaN, NaN}
  };

  public static double[][] cpOnlyC = {
          {0, 0, 1, 0, 0},
          {0, 0, 1, 0, 0},
          {0, 0, 1, 0, 0},
          {0, 0, 1, 0, 0},
          {NaN, NaN, NaN, NaN, NaN}
  };

  @Test
  public void genOnlyA() {
    RandomSeqStringGenerator rsg = new RandomSeqStringGenerator();

    String seq = rsg.rndCondSeqString(10, cpOnlyA);
    assertEquals(10, seq.length());
    String subseq = seq.substring(1, seq.length());
    assertEquals("AAAAAAAAA", subseq);
  }

  @Test
  public void genOnlyC() {
    RandomSeqStringGenerator rsg = new RandomSeqStringGenerator();

    String seq = rsg.rndCondSeqString(10, cpOnlyC);
    assertEquals(10, seq.length());
    String subseq = seq.substring(1, seq.length());
    assertEquals("CCCCCCCCC", subseq);
  }

  @Test
  public void gen1() {
    RandomSeqStringGenerator rsg = new RandomSeqStringGenerator();

    String seq = rsg.rndCondSeqString(20, cp1);
    assertEquals(20, seq.length());
    // System.out.println(seq);
  }
}
