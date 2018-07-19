package bio.gcat.abcdtool.sequences.writer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Markus Gumbel (m.gumbel@hs-mannheim.de)
 */
public class FastaFileWriter {

  /**
   * @param sequence
   * @param outputFile
   * @param headertext
   */
  public void writeSequence(String sequence, String outputFile,
                            String headertext) throws IOException {
    long size = sequence.length();

    // Format fasta file with 80 bases per row:
    int colSize = 80; // Default number of bases per row.
    long fullLines = size / colSize; // Number of lines.

    FileWriter fw = new FileWriter(outputFile);
    fw.write(">" + headertext + ", size: " + size + "\n");

    for (int i = 0; i < fullLines; i++) {
      int i1 = i * colSize;  // Start range.
      int i2 = i1 + colSize; // End of range.
      String line = sequence.substring(i1, i2);
      fw.write(line + "\n");
    }
    int i3 = (int) (fullLines * colSize);
    String restLine = sequence.substring(i3, sequence.length());
    fw.write(restLine + "\n");
    fw.close();
  }
}
