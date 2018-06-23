package bio.gcat.abcdtool;

import bio.gcat.abcdtool.analysis.NPletCalc;
import bio.gcat.abcdtool.sequences.generator.RandomSeqStringGenerator;
import org.junit.jupiter.api.Test;

public class NPletCalcTest {

  @Test
  public void test1() {
    RandomSeqStringGenerator gen = new RandomSeqStringGenerator();
    String seq = gen.rndCondSeqString(10000);
    NPletCalc c = new NPletCalc(seq, 1);
    System.out.println(c);
  }

  @Test
  public void test2() {
    RandomSeqStringGenerator gen = new RandomSeqStringGenerator();
    String seq = gen.rndCondSeqString(10000);
    NPletCalc c = new NPletCalc(seq, 2);
    System.out.println(c);
  }

  @Test
  public void test10() {
    RandomSeqStringGenerator gen = new RandomSeqStringGenerator();
    String seq = gen.rndCondSeqString(250000000);
    NPletCalc c = new NPletCalc(seq, 10);
    System.out.println(c);
  }

  @Test
  public void test5() {
    RandomSeqStringGenerator gen = new RandomSeqStringGenerator();
    String seq = gen.rndCondSeqString(250000);
    NPletCalc c = new NPletCalc(seq, 5);
    System.out.println(c);
  }
}
