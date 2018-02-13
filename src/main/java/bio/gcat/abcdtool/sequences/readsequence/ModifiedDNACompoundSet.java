package bio.gcat.abcdtool.sequences.readsequence;

import org.biojava.nbio.core.sequence.compound.DNACompoundSet;

public class ModifiedDNACompoundSet extends DNACompoundSet {

    public void addCompound(String compound, String complement){
        this.addNucleotideCompound(compound,complement);

    }
    public static ModifiedDNACompoundSet getDNACompoundSet(){
        return new ModifiedDNACompoundSet();
    }
}
