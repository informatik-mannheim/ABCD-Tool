package bio.gcat.abcdtool;

import bio.gcat.abcdtool.analysis.NPletCalc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NPletCalcTest {

  @Test
  public void testEmpty() {
    String seq = "";
    NPletCalc c = new NPletCalc(seq, 1);
    assertEquals(Double.NaN, c.getSkew('A', 'T', 0), 0.01);
    assertEquals(Double.NaN, c.getSkew('C', 'G', 0), 0.01);
  }

  @Test
  public void testAT() {
    String seq = "AT";
    NPletCalc c = new NPletCalc(seq, 1);
    assertEquals(0, c.getSkew('A', 'T', 0), 0.01);
    assertEquals(Double.NaN, c.getSkew('C', 'G', 0), 0.01);
  }

  @Test
  public void testAU() {
    String seq = "AU";
    NPletCalc c = new NPletCalc(seq, 1);
    assertEquals(0, c.getSkew('A', 'T', 0), 0.01);
    assertEquals(0, c.getSkew('A', 'U', 0), 0.01);
    assertEquals(Double.NaN, c.getSkew('C', 'G', 0), 0.01);
  }

  @Test
  public void testCG() {
    String seq = "CG";
    NPletCalc c = new NPletCalc(seq, 1);
    assertEquals(Double.NaN, c.getSkew('A', 'T', 0), 0.01);
    assertEquals(0, c.getSkew('C', 'G', 0), 0.01);
  }


  @Test
  public void testATCG() {
    String seq = "ATCG";
    NPletCalc c = new NPletCalc(seq, 1);
    assertEquals(0, c.getSkew('A', 'T', 0), 0.01);
    assertEquals(0, c.getSkew('C', 'G', 0), 0.01);
  }

  @Test
  public void testATCGA2Lowercase() {
    // k = 1: a c t
    // -> AT = (1-1)/(1+1) = 0; CG = (1-0)/(1+0) = 1
    // k = 2:  t a c
    // -> AT = (1-1)/(1+1) = 0; CG = (1-0)/(1+0) = 1
    String seq = "atcatc";
    NPletCalc c = new NPletCalc(seq, 2);
    assertEquals(0, c.getSkew('A', 'T', 0), 0.01);
    assertEquals(0, c.getSkew('A', 'T', 1), 0.01);

    assertEquals(1, c.getSkew('C', 'G', 0), 0.01);
    assertEquals(1, c.getSkew('C', 'G', 1), 0.01);
  }

  @Test
  public void testATCGA3() {
    // k = 1: a  a  c  c  g
    // -> AT = (2-0)/(2+0) = 1; CG = (2-1)/(2+1) = 1/3
    // k = 2:  t  t  g  a  t
    // -> AT = (1-3)/(1+3) = -1/2; CG = (0-1)/0+1) = -1
    // k = 3:   g  c  c  t  a
    // -> AT = (1-1)/(1+1) = 0; CG = (2-1)/2+1) = 1/3
    String seq = "atgatccgccatgta";
    NPletCalc c = new NPletCalc(seq, 3);
    assertEquals(1, c.getSkew('A', 'T', 0), 0.01);
    assertEquals(-1.0 / 2, c.getSkew('A', 'T', 1), 0.01);
    assertEquals(0, c.getSkew('A', 'T', 2), 0.01);

    assertEquals(1.0 / 3, c.getSkew('C', 'G', 0), 0.01);
    assertEquals(-1, c.getSkew('C', 'G', 1), 0.01);
    assertEquals(1.0 / 3, c.getSkew('C', 'G', 2), 0.01);
  }
}
