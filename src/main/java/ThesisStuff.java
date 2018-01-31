import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * This file is used to generate the Lyx Code to include the tables in my Thesis
 */
public class ThesisStuff {
    static String entry = "\\begin_layout Standard\n" +
            "\\begin_inset Float figure\n" +
            "wide false\n" +
            "sideways false\n" +
            "status open\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\\begin_inset Float figure\n" +
            "wide false\n" +
            "sideways false\n" +
            "status open\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\\begin_inset Graphics\n" +
            "\tfilename /Users/ali/Documents/Studium/Bachelor Arbeit/FastFasta/Output/2018-01-15/Homo sapiens/NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly/BoxWhisker/A ChartSVGBoxWhisker.svg\n" +
            "\tscale 45\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset Caption Standard\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "Nucleotide A\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset space \\hrulefill{}\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset Float figure\n" +
            "wide false\n" +
            "sideways false\n" +
            "status open\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\\begin_inset Graphics\n" +
            "\tfilename /Users/ali/Documents/Studium/Bachelor Arbeit/FastFasta/Output/2018-01-15/Homo sapiens/NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly/BoxWhisker/T ChartSVGBoxWhisker.svg\n" +
            "\tscale 45\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset Caption Standard\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "Nucleotide T\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\\begin_inset Float figure\n" +
            "wide false\n" +
            "sideways false\n" +
            "status open\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\\begin_inset Graphics\n" +
            "\tfilename /Users/ali/Documents/Studium/Bachelor Arbeit/FastFasta/Output/2018-01-15/Homo sapiens/NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly/BoxWhisker/G ChartSVGBoxWhisker.svg\n" +
            "\tscale 45\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset Caption Standard\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "Nucleotide G\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset space \\hrulefill{}\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset Float figure\n" +
            "wide false\n" +
            "sideways false\n" +
            "status open\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\\begin_inset Graphics\n" +
            "\tfilename /Users/ali/Documents/Studium/Bachelor Arbeit/FastFasta/Output/2018-01-15/Homo sapiens/NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly/BoxWhisker/C ChartSVGBoxWhisker.svg\n" +
            "\tscale 45\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\begin_inset Caption Standard\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "Nucleotide C\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "\\begin_inset Caption Standard\n" +
            "\n" +
            "\\begin_layout Plain Layout\n" +
            "NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n" +
            "\n" +
            "\\end_inset\n" +
            "\n" +
            "\n" +
            "\\end_layout\n";


    static String files =
            "Arabidopsis thaliana/NC_003070.9 Arabidopsis thaliana chromosome 1 sequence:\n" +
            "Arabidopsis thaliana/NC_003071.7 Arabidopsis thaliana chromosome 2 sequence:\n" +
            "Arabidopsis thaliana/NC_003074.8 Arabidopsis thaliana chromosome 3 sequence:\n" +
            "Arabidopsis thaliana/NC_003075.7 Arabidopsis thaliana chromosome 4 sequence:\n" +
            "Arabidopsis thaliana/NC_003076.8 Arabidopsis thaliana chromosome 5 sequence:\n" +
            "Caenorhabditis elegans/BX284601.5 Caenorhabditis elegans chromosome I:\n" +
            "Caenorhabditis elegans/BX284602.5 Caenorhabditis elegans chromosome II:\n" +
            "Caenorhabditis elegans/BX284603.4 Caenorhabditis elegans chromosome III:\n" +
            "Caenorhabditis elegans/BX284604.4 Caenorhabditis elegans chromosome IV:\n" +
            "Caenorhabditis elegans/BX284605.5 Caenorhabditis elegans chromosome V:\n" +
            "Caenorhabditis elegans/BX284606.5 Caenorhabditis elegans chromosome X:\n" +
            "Chlamydomonas reinhardtii/U03843.1 Chlamydomonas reinhardtii complete mitochondrial genome:\n" +
            "Homo sapiens/NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000002.12 Homo sapiens chromosome 2, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000003.12 Homo sapiens chromosome 3, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000004.12 Homo sapiens chromosome 4, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000005.10 Homo sapiens chromosome 5, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000006.12 Homo sapiens chromosome 6, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000007.14 Homo sapiens chromosome 7, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000008.11 Homo sapiens chromosome 8, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000009.12 Homo sapiens chromosome 9, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000010.11 Homo sapiens chromosome 10, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000011.10 Homo sapiens chromosome 11, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000012.12 Homo sapiens chromosome 12, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000013.11 Homo sapiens chromosome 13, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000014.9 Homo sapiens chromosome 14, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000015.10 Homo sapiens chromosome 15, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000016.10 Homo sapiens chromosome 16, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000017.11 Homo sapiens chromosome 17, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000018.10 Homo sapiens chromosome 18, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000019.10 Homo sapiens chromosome 19, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000020.11 Homo sapiens chromosome 20, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000021.9 Homo sapiens chromosome 21, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000022.11 Homo sapiens chromosome 22, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000023.11 Homo sapiens chromosome X, GRCh38.p7 Primary Assembly:\n" +
            "Homo sapiens/NC_000024.10 Homo sapiens chromosome Y, GRCh38.p7 Primary Assembly:\n" +
            "Oryza sativa Japonica/AP008207.2 Oryza sativa Japonica Group DNA, chromosome 1, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008208.2 Oryza sativa Japonica Group DNA, chromosome 2, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008209.2 Oryza sativa Japonica Group DNA, chromosome 3, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008210.2 Oryza sativa Japonica Group DNA, chromosome 4, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008211.2 Oryza sativa Japonica Group DNA, chromosome 5, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008212.2 Oryza sativa Japonica Group DNA, chromosome 6, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008213.2 Oryza sativa Japonica Group DNA, chromosome 7, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008214.2 Oryza sativa Japonica Group DNA, chromosome 8, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008215.2 Oryza sativa Japonica Group DNA, chromosome 9, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008216.2 Oryza sativa Japonica Group DNA, chromosome 10, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008217.2 Oryza sativa Japonica Group DNA, chromosome 11, complete sequence, cultivar Nipponbare:\n" +
            "Oryza sativa Japonica/AP008218.2 Oryza sativa Japonica Group DNA, chromosome 12, complete sequence, cultivar Nipponbare:\n" ;

    public static void main(String[] args) throws FileNotFoundException {
        String[] names = files.split("\n");
        File f = new File("Output/lyxImport.txt");
        if(!f.exists()){
            f.getParentFile().mkdirs();
        }

        PrintWriter out = new PrintWriter(new FileOutputStream(
                f,
                true ));
out.println();
        for (String s : names) {
            System.out.println(s);
            String entryTemp = entry.replaceAll("Homo sapiens/NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly", s.replaceAll(":",""));
            entryTemp = entryTemp.replaceAll("NC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly", s.split("/")[1]);
            out.append(entryTemp);
        }
        out.close();
    }
}
