package bio.gcat.chargaff

/**
  * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
  */
class Symbol(c: Char)

case class Terminal(t: Char) extends Symbol(t)

case class NonTerminal(n: Char) extends Symbol(n)