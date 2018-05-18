package bio.gcat.abcdtool.sequences.reader;

import org.biojava.nbio.core.exceptions.CompoundNotFoundException;
import org.biojava.nbio.core.sequence.BasicSequence;
import org.biojava.nbio.core.sequence.compound.NucleotideCompound;
import org.biojava.nbio.core.sequence.template.Sequence;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Reads a fasta file and returns a biojava compatible sequence.
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 */
public class FastFastaLoader {
  File file;

  public FastFastaLoader(File file) {
    this.file = file;
  }

  public Map<String, Sequence<NucleotideCompound>> fastaEntries() {
    ModifiedDNACompoundSet compoundSet = ModifiedDNACompoundSet.getDNACompoundSet();

    int a = 0;

    for (a = 0; a <= 25; a++) { //this is needed to be able to analyze the sequences with unknown bases
      char c = (char) (a + 'A');
      if (!(c == 'A' || c == 'T' || c == 'G' || c == 'C')) {
        compoundSet.addCompound("" + c, "" + c);
      }
    }
    try {
      BufferedReader rd = new BufferedReader(new FileReader(file));
      String inputLine = rd.readLine();
      Map<String, Sequence<NucleotideCompound>> map = new HashMap<>();

      while (inputLine != null) {
        if (inputLine.startsWith(">")) { // Recognized a header?
          // val read = readUntilNextHeader(rd);

          String read = rd.readLine();
          StringBuilder readSequence = new StringBuilder().append(read);
          while (read != null && !read.startsWith(">")) {

            readSequence.append(read);
            read = rd.readLine();
          }
          //val read = rd.readLine() // ... also read the sequence.
          BasicSequence seq = null;
          try {
            seq = new BasicSequence(readSequence.toString(), compoundSet);
          } catch (CompoundNotFoundException e) {
            e.printStackTrace();
          }
          map.put(inputLine.trim(), seq);
          inputLine = rd.readLine(); // Next header...
        }

      }

      rd.close();
      return map;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}






