README

Execution
`bio.gcat.abcdtool.main.Analyzer` is the main program for analyzing
a single sequence.
`bio.gcat.abcdtool.main.BatchAnalyze` analyzes a batch of sequences.

Usage:
`bio.gcat.abcdtool.main.Analyzer` has several options:
`Analyze 
 (-file <seq>|((-rnd <deftype>|-rnd <type> -rndfile <rseq>) <size>) 
 -method <method>`

-file <seq>: uses the Fasta file <seq>.
-rnd generates random sequences. Options are
 * <deftype>: `iHSChr1` or `cHSChr1`
 * <type>: Either `i` like independent or `c` like conditional 
 which requires a Fasta file <rseq> from which the probabilities 
 are calculated.
 * <size>: The length of the generated sequence (number of bases). 

<method> is 
 * nplets
 * N

For developers

Clone the repository with
`git clone --recurse-submodules <url>`
 
JFreeChart is included as a submodule. Run
`git submodule update --init --recursive`
to init this submodule. This has to be done only once.