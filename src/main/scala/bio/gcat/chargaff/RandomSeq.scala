package bio.gcat.chargaff

import java.io.{File, PrintWriter}

/**
  * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
  */
class RandomSeq() {

  def getSequence(rules: Rules, maxDepth: Int) = {

    def build(subst: List[Symbol], depth: Int): String = {
      if (depth <= maxDepth) {
        subst.map { s =>
          s match {
            case NonTerminal(c) => build(rules.rule(NonTerminal(c)), depth + 1)
            case Terminal(c) => c
          }
        }.mkString("")
      } else {
        ""
      }
    }

    build(List(NonTerminal('s')), 1)
  }
}

object RandomSeq {
  val COLSIZE = 80

  def main(args: Array[String]) {
    val rs = new RandomSeq()
    val seq = rs.getSequence(RULE1, 60)
    println(seq.size)
    val pw = new PrintWriter(new File("dna.fasta"))
    pw.println("> Chargaff Grammar;" + seq.size + " bases")
    def write(s: String) {
      if (s.size > COLSIZE) {
        pw.println(s.take(COLSIZE).mkString(""))
        write(s.drop(COLSIZE))
      } else {
        pw.println(s.mkString(""))
      }
    }
    write(seq)
    pw.close()
  }
}


