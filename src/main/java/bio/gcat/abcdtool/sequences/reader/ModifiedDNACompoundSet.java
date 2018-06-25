package bio.gcat.abcdtool.sequences.reader;

import org.biojava.nbio.core.sequence.compound.DNACompoundSet;

/**
 * This class was needed to read sequences with unknown bases
 * such as N.
 *
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 */
public class ModifiedDNACompoundSet extends DNACompoundSet {

  public void addCompound(String compound, String complement) {
    this.addNucleotideCompound(compound, complement);

  }

  public static ModifiedDNACompoundSet getDNACompoundSet() {
    return new ModifiedDNACompoundSet();
  }
}
