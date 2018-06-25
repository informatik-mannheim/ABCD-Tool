# README


## Usage
ABCD tool comes with a set of tools which are described in more 
detail below. They are:

 * `bio.gcat.abcdtool.main.Analyzer` is the main program for analyzing
a single sequence.
 * `bio.gcat.abcdtool.main.BatchAnalyze` analyzes a batch of sequences.
 * `bio.gcat.abcdtool.main.WriteRndFastaFile` writes random 
 DNA sequences to a fasta file.

Each tool is Java class that can be executed. For instance, type
`java -cp abcd-tool-1.1.0-jar-with-dependencies.jar 
bio.gcat.abcdtool.main.Analyzer` in your command shell 
to run the n-plets analyzer.

## `bio.gcat.abcdtool.main.Analyzer`
Analyzing n-plets in a DNA sequence. Options:

 Source of the sequence:
 * `-f,--fasta <fasta filename>`     sequence in fasta format
 * `-c,--rndCond`                    random sequence with conditional
                                     probabilities like in human chr. 1.
 * `-C,--rndCondFile <fasta filename>`   random sequence with conditional
                                     probabilities like in fasta file
 * `-i,--rndInit`                    random sequence with probabilities
                                     like in human chr. 1.
 * `-s,--size <integer>`             length of the random sequence;
                                     default is 100,000                                                                          
 
 Method for analysis: 
 * `-n,--normal`                     regular n-plets analysis (default
                                     method)
 * `-p,--part`                       partition based n-plets analysis


## `bio.gcat.abcdtool.main.BatchAnalyze`
Analyzing n-plets in a DNA sequence stored in directories. Options:
 * `-d,--dir <directory path>`   directory with fasta files or subdirectories
 * `-n,--normal`                 regular n-plets analysis (default method)
 * `-p,--part`                   partition based n-plets analysis

### `bio.gcat.abcdtool.main.WriteRndFastaFile`
Create a fasta files with a random sequence. Options:

 * `-c,--rndCond`       random sequence with conditional probabilities
                        like in human chr. 1.
 * `-i,--rndInit`       random sequence with probabilities like in human                        chr. 1.
 * `-o,--output <string>`   output file name; default is rnd.fasta
 * `-s,--size <integer>`    length of the random sequence; default is 100,000

## For developers

Clone the repository with
`git clone --recurse-submodules <url>`
 
JFreeChart is included as a submodule. Run
`git submodule update --init --recursive`
to init this submodule. This has to be done only once.