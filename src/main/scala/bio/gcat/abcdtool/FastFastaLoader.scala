package bio.gcat.abcdtool

import java.io._
import java.util

import org.biojava.nbio.core.sequence.BasicSequence
import org.biojava.nbio.core.sequence.compound.{NucleotideCompound, DNACompoundSet}
import org.biojava.nbio.core.sequence.template.Sequence

/**
  * A loader for FASTA files that contains a large number of entries.
  * This substitutes the FASTA reader in Biojava as this loader
  * uses a LinkedHashMap which is very slow when having many entries.
  *
  * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
  */
class FastFastaLoader(val inputStream: InputStream) {

  def this(file: File) {
    this(new FileInputStream(file))
  }

  // TODO only DNA is supported yet.
  val compoundSet = ModifiedDNACompoundSet.getDNACompoundSet()



  /**
    * Map of FASTA entries. Keys are a header string, values
    * are a sequence.
    */
  val fastaEntries = {
    compoundSet.addCompound("M","M")
    compoundSet.addCompound("R","R")
    val rd = new BufferedReader(new InputStreamReader(inputStream))
    var inputLine = rd.readLine()
    val map = new util.HashMap[String, Sequence[NucleotideCompound]]()

    while (inputLine != null) {
      if (inputLine.startsWith(">")) { // Recognized a header?
       // val read = readUntilNextHeader(rd);

        var read = rd.readLine()
        var readSequence = new StringBuilder().append(read)
        while(read != null && !read.startsWith(">")){

          readSequence.append(read);
          read = rd.readLine()
        }
        //val read = rd.readLine() // ... also read the sequence.
      val seq = new BasicSequence(readSequence.toString(), compoundSet)
        map.put(inputLine.trim, seq)
        inputLine = read // Next header...
      }

    }

    rd.close()
    map
  }
}
