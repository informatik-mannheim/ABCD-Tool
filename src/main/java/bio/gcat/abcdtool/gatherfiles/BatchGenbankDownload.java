package bio.gcat.abcdtool.gatherfiles;


import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used to download all the files from a text format.
 * The structure of the file format should be like this:
 * <p>
 * Oryza sativa Japonica Group DNA, chromosome 12, complete sequence, cultivar: Nipponbare
 * https://www.ncbi.nlm.nih.gov/nuccore/AP008218.2
 * <p>
 * So the name and a link to the file in its own line, as many as desired.
 * @author Ali Karpuzoglu (ali.karpuzoglu@gmail.com)
 */
public class BatchGenbankDownload {
  public static void main(String[] args) throws IOException {
    File names = new File(args[0]);
    BufferedReader br = new BufferedReader(new FileReader(names));
    String line;
    List<GenbankSequence> sequences = new ArrayList<>();
    while ((line = br.readLine()) != null) {
      if (line.trim().equals("")) {
        continue;
      }
      // print the line.
      if (line.startsWith("http")) {
        GenbankSequence s = sequences.get(sequences.size() - 1);
        s.setUrl(line);
        String[] lineSplit = line.split("/");
        s.setId(lineSplit[lineSplit.length - 1]);
      } else {
        GenbankSequence s = new GenbankSequence();
        s.setName(line);
        sequences.add(s);
      }
      System.out.println(line);
    }
    br.close();
    System.out.println(sequences);
    for (int i = 0; i < sequences.size(); i++) { //later for all sequences
      System.out.println("downloading file " + sequences.get(i).getName());
      downloadFileFromGenBank(sequences.get(i));
    }
  }

  /**
   * this uses the genbank API to download the file passed in the parameter
   *
   * @param sequence
   */
  static void downloadFileFromGenBank(GenbankSequence sequence) {
    String urlString = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nuccore&id=" + sequence.getId() + "&rettype=fasta&retmode=text";
    File dstFile = null;
    File dstFolderFile = null;
    String path = "DownloadedFiles" + File.separator + sequence.getSpecies() + File.separator + sequence.getName() + ".fasta"; //TODO:Add timestamp?
    // Check the directory for existence:
    String dstFolder = path.substring(0, path.lastIndexOf(File.separator));
    if (!(dstFolder.endsWith(File.separator) || dstFolder.endsWith("/"))) {
      dstFolder += File.separator;
    }
    // Creates the destination folder if doesn't not exists:
    dstFile = new File(path);
    dstFolderFile = new File(dstFolder);
    if (!dstFile.exists()) {
      dstFolderFile.mkdirs();
    }

    try {
      URL url = new URL(urlString);
      FileUtils.copyURLToFile(url, dstFile);
    } catch (Exception e) {
      System.err.println(e);
      e.printStackTrace();
    }
  }
}
