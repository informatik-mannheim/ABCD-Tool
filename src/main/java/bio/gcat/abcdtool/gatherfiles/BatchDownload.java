package bio.gcat.abcdtool.gatherfiles;


import org.apache.commons.io.FileUtils;
import scala.collection.Seq;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class BatchDownload {
    public static void main(String[] args) throws IOException {
        File names = new File(args[0]);
        BufferedReader br = new BufferedReader(new FileReader(names));
        String line;
        ArrayList<Sequence> sequences  = new ArrayList<>();
        while ((line = br.readLine()) != null) {
if(line.trim().equals("")){
    continue;
}
            // print the line.
            if(line.startsWith("http")){
              Sequence s =  sequences.get(sequences.size()-1);
              s.setUrl(line);
              String[] lineSplit = line.split("/");
              s.setId(lineSplit[lineSplit.length-1]);
            }else{
                Sequence s = new Sequence();
                s.setName(line);
                sequences.add(s);
            }
            System.out.println(line);
        }
        br.close();
        System.out.println(sequences);
        for(int i = 0;i<2;i++){ //later for all sequences
            downloadFileFromGenBank(sequences.get(i+18));
        }
        /*TODO: download all the files and put them into the folder: DownloadedSequences/speciesName/speciesChromosome/sequence.fasta and include a file with a link to the file in the folder next to the sequence
        and put a file with all the sequences into an extra file in the DownloadedSequences folder with the sequence List (all links and files)
        */
    }
    static void downloadFileFromGenBank(Sequence sequence){
        String urlString = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=nuccore&id="+ sequence.getId() +  "&rettype=fasta&retmode=text" ;
        File dstFile = null;
        File dstFolderFile = null;
        String path = "DownloadedFiles/"+ sequence.getSpecies() + "/"+sequence.getName() +".fasta"; //TODO:Add timestamp?
// check the directory for existence.
        String dstFolder = path.substring(0,path.lastIndexOf(File.separator));
        if(!(dstFolder.endsWith(File.separator) || dstFolder.endsWith("/"))) {
            dstFolder += File.separator;
        }
// Creates the destination folder if doesn't not exists
        dstFile = new File(path);
        dstFolderFile = new File(dstFolder) ;
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
