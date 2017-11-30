package bio.gcat.chargaff

import scala.collection.immutable.HashMap
import bio.gcat.chargaff.{NonTerminal => NT}
import bio.gcat.chargaff.{Terminal => T}

/**
  * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
  */
class Rules {

  val rules = HashMap(
    NT('s') -> List((List(NT('a')), 1.0)),
    NT('a') -> List(
      (List(T('A')), 0.1),
      (List(T('A'), NT('r'), T('T')), 0.2),
      (List(T('T'), NT('r'), T('A')), 0.2),
      (List(T('C'), NT('r'), T('G')), 0.1),
      (List(T('G'), NT('r'), T('C')), 0.1),
      (List(NT('f')), 0.3)
    ),
    NT('t') -> List(
      (List(T('T')), 0.1),
      (List(T('A'), NT('r'), T('T')), 0.2),
      (List(T('T'), NT('r'), T('A')), 0.2),
      (List(T('C'), NT('r'), T('G')), 0.1),
      (List(T('G'), NT('r'), T('C')), 0.1),
      (List(NT('f')), 0.3)
    ),
    NT('c') -> List(
      (List(T('C')), 0.1),
      (List(T('A'), NT('r'), T('T')), 0.2),
      (List(T('T'), NT('r'), T('A')), 0.2),
      (List(T('C'), NT('r'), T('G')), 0.1),
      (List(T('G'), NT('r'), T('C')), 0.1),
      (List(NT('f')), 0.3)
    ),
    NT('g') -> List(
      (List(T('G')), 0.1),
      (List(T('A'), NT('r'), T('T')), 0.2),
      (List(T('T'), NT('r'), T('A')), 0.2),
      (List(T('C'), NT('r'), T('G')), 0.1),
      (List(T('G'), NT('r'), T('C')), 0.1),
      (List(NT('f')), 0.3)
    ),
    NT('f') -> List(
      (List(NT('a'), NT('t')), 0.3),
      (List(NT('t'), NT('a')), 0.3),
      (List(NT('c'), NT('g')), 0.2),
      (List(NT('g'), NT('c')), 0.2)
    ),
    NT('r') -> List(
      (List(NT('a')), 0.4),
      (List(NT('t')), 0.4),
      (List(NT('c')), 0.1),
      (List(NT('g')), 0.1)
    )
  )

  def rule(c: NonTerminal): List[Symbol] = {
    val substPairList: List[Tuple2[List[Symbol], Double]] = rules(c)
    if (substPairList != null) {
      val rnd = Math.random()

      def pick(su: List[Tuple2[List[Symbol], Double]],
               prob: Double): List[Symbol] = {
        val p = prob + su.head._2
        if (p > rnd) {
          su.head._1
        } else {
          pick(su.tail, p)
        }
      }

      return pick(substPairList, 0)
    }
    throw new RuntimeException("Grammar error")
  }
}

object RULE1 extends Rules {
  override val rules = HashMap(
    NT('s') -> List((List(NT('r')), 1.0)),
    NT('r') -> List(
      (List(T('A'), NT('r'), T('T')), 0.15),
      (List(T('T'), NT('r'), T('A')), 0.15),
      (List(T('C'), NT('r'), T('G')), 0.10),
      (List(T('G'), NT('r'), T('C')), 0.10),
      (List(T('A')), 0.025),
      (List(T('T')), 0.025),
      (List(T('C')), 0.025),
      (List(T('G')), 0.025),
      (List(NT('r'), NT('r')), 0.4)
    )
  )
}