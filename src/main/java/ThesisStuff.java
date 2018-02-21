import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * This file is used to generate the Lyx Code to include the tables in my Thesis
 * this is of VERY limited use for anyone not writing my thesis
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


   static String values = " randomNC_003070.9 Arabidopsis thaliana chromosome 1 sequence : 3.739949590112184E-4\n" +
            "randomNC_003071.7 Arabidopsis thaliana chromosome 2 sequence : 2.259881637313544E-4\n" +
            "randomNC_003076.8 Arabidopsis thaliana chromosome 5 sequence : -0.001622675863623929\n" +
            "randomNC_003075.7 Arabidopsis thaliana chromosome 4 sequence : 0.0029476210812645087\n" +
            "randomNC_003074.8 Arabidopsis thaliana chromosome 3 sequence : 0.00239382975066745\n" +
            "randomAP008207.2 Oryza sativa Japonica Group DNA, chromosome 1, complete sequence, cultivar Nipponbare : 1.421416428448533E-4\n" +
            "randomAP008210.2 Oryza sativa Japonica Group DNA, chromosome 4, complete sequence, cultivar Nipponbare : 0.0015960433089157477\n" +
            "randomAP008215.2 Oryza sativa Japonica Group DNA, chromosome 9, complete sequence, cultivar Nipponbare : 9.180009921566476E-4\n" +
            "randomAP008212.2 Oryza sativa Japonica Group DNA, chromosome 6, complete sequence, cultivar Nipponbare : 4.920322966266047E-4\n" +
            "randomAP008217.2 Oryza sativa Japonica Group DNA, chromosome 11, complete sequence, cultivar Nipponbare : 4.72856276919909E-4\n" +
            "randomAP008209.2 Oryza sativa Japonica Group DNA, chromosome 3, complete sequence, cultivar Nipponbare : -7.519039957387023E-4\n" +
            "randomAP008208.2 Oryza sativa Japonica Group DNA, chromosome 2, complete sequence, cultivar Nipponbare : 4.435206029120175E-4\n" +
            "randomAP008213.2 Oryza sativa Japonica Group DNA, chromosome 7, complete sequence, cultivar Nipponbare : -0.0010881172086491416\n" +
            "randomAP008216.2 Oryza sativa Japonica Group DNA, chromosome 10, complete sequence, cultivar Nipponbare : -0.0012781940131322\n" +
            "randomAP008214.2 Oryza sativa Japonica Group DNA, chromosome 8, complete sequence, cultivar Nipponbare : -2.0872820745317593E-4\n" +
            "randomAP008211.2 Oryza sativa Japonica Group DNA, chromosome 5, complete sequence, cultivar Nipponbare : -0.0010299701106712981\n" +
            "randomAP008218.2 Oryza sativa Japonica Group DNA, chromosome 12, complete sequence, cultivar Nipponbare : 7.084708957665277E-4\n" +
            "randomNC_000020.11 Homo sapiens chromosome 20, GRCh38.p7 Primary Assembly : -0.005445235067984197\n" +
            "randomNC_000014.9 Homo sapiens chromosome 14, GRCh38.p7 Primary Assembly : -0.004510555552029085\n" +
            "randomNC_000006.12 Homo sapiens chromosome 6, GRCh38.p7 Primary Assembly : -2.5604899514098224E-4\n" +
            "randomNC_000019.10 Homo sapiens chromosome 19, GRCh38.p7 Primary Assembly : -0.004742836456795968\n" +
            "randomNC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly : -0.0013007096735060738\n" +
            "randomNC_000024.10 Homo sapiens chromosome Y, GRCh38.p7 Primary Assembly : -0.004549878205151735\n" +
            "randomNC_000004.12 Homo sapiens chromosome 4, GRCh38.p7 Primary Assembly : -5.848225684490859E-4\n" +
            "randomNC_000016.10 Homo sapiens chromosome 16, GRCh38.p7 Primary Assembly : -0.004943942080278483\n" +
            "randomNC_000022.11 Homo sapiens chromosome 22, GRCh38.p7 Primary Assembly : 1.9348556995312793E-4\n" +
            "randomNC_000021.9 Homo sapiens chromosome 21, GRCh38.p7 Primary Assembly : -0.0012220908168164134\n" +
            "randomNC_000015.10 Homo sapiens chromosome 15, GRCh38.p7 Primary Assembly : -9.089402740176845E-4\n" +
            "randomNC_000018.10 Homo sapiens chromosome 18, GRCh38.p7 Primary Assembly : -0.0023960796584469417\n" +
            "randomNC_000007.14 Homo sapiens chromosome 7, GRCh38.p7 Primary Assembly : -0.0018154345137025977\n" +
            "randomNC_000023.11 Homo sapiens chromosome X, GRCh38.p7 Primary Assembly : -0.001792622153418913\n" +
            "randomNC_000005.10 Homo sapiens chromosome 5, GRCh38.p7 Primary Assembly : -0.0023427150694890733\n" +
            "randomNC_000008.11 Homo sapiens chromosome 8, GRCh38.p7 Primary Assembly : 2.0331403722709953E-4\n" +
            "randomNC_000017.11 Homo sapiens chromosome 17, GRCh38.p7 Primary Assembly : -0.0014468490100500414\n" +
            "randomNC_000011.10 Homo sapiens chromosome 11, GRCh38.p7 Primary Assembly : -9.961928033291473E-4\n" +
            "randomNC_000003.12 Homo sapiens chromosome 3, GRCh38.p7 Primary Assembly : -0.001184420806992464\n" +
            "randomNC_000013.11 Homo sapiens chromosome 13, GRCh38.p7 Primary Assembly : -0.0016929731086489468\n" +
            "randomNC_000010.11 Homo sapiens chromosome 10, GRCh38.p7 Primary Assembly : -0.0020406536207819787\n" +
            "randomNC_000002.12 Homo sapiens chromosome 2, GRCh38.p7 Primary Assembly : -0.0013531562112170429\n" +
            "randomNC_000012.12 Homo sapiens chromosome 12, GRCh38.p7 Primary Assembly : -0.001570101443929117\n" +
            "randomNC_000009.12 Homo sapiens chromosome 9, GRCh38.p7 Primary Assembly : -5.983667103944744E-4\n" +
            "randomBX284604.4 Caenorhabditis elegans chromosome IV : -0.0016199224509857463\n" +
            "randomBX284606.5 Caenorhabditis elegans chromosome X : 5.994744731990653E-4\n" +
            "randomBX284602.5 Caenorhabditis elegans chromosome II : 7.860688330376257E-4\n" +
            "randomBX284601.5 Caenorhabditis elegans chromosome I : -0.0018339104919506198\n" +
            "randomBX284605.5 Caenorhabditis elegans chromosome V : -0.0010742933749095874\n" +
            "randomBX284603.4 Caenorhabditis elegans chromosome III : 0.0021750950377839606\n" +
            "randomCM008970.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 9, whole genome shotgun sequence : -0.00349856956069868\n" +
            "randomCM008972.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 11, whole genome shotgun sequence : -0.001099762978391494\n" +
            "randomCM008977.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 16, whole genome shotgun sequence : 0.0014941759271656179\n" +
            "randomCM008969.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 8, whole genome shotgun sequence : -0.00173466662841278\n" +
            "randomCM008971.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 10, whole genome shotgun sequence : -0.0017692458547095983\n" +
            "randomCM008978.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 17, whole genome shotgun sequence : -0.00459277154534234\n" +
            "randomCM008966.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 5, whole genome shotgun sequence : 0.0011711139897391852\n" +
            "randomCM008963.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 2, whole genome shotgun sequence : 0.0016195762068876443\n" +
            "randomCM008965.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 4, whole genome shotgun sequence : 0.0027001252552519748\n" +
            "randomCM008964.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 3, whole genome shotgun sequence : 0.0028145771425668055\n" +
            "randomCM008962.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 1, whole genome shotgun sequence : 5.9545513613330345E-6\n" +
            "randomCM008967.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 6, whole genome shotgun sequence : 3.192048727600985E-4\n" +
            "randomCM008968.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 7, whole genome shotgun sequence : -0.002633900910151794\n" +
            "randomCM008976.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 15, whole genome shotgun sequence : -0.0037664385385711204\n" +
            "randomCM008973.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 12, whole genome shotgun sequence : 0.002997140896680599\n" +
            "randomU03843.1 Chlamydomonas reinhardtii complete mitochondrial genome : 0.0043718361711919005\n" +
            "randomCM008975.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 14, whole genome shotgun sequence : 0.002941876851321551\n" +
            "randomCM008974.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 13, whole genome shotgun sequence : -0.005403710522922295";
static String values2 = "randomNC_003070.9 Arabidopsis thaliana chromosome 1 sequence : 8.797914412031336E-4\n" +
        "randomNC_003071.7 Arabidopsis thaliana chromosome 2 sequence : 4.4548393333585E-4\n" +
        "randomNC_003076.8 Arabidopsis thaliana chromosome 5 sequence : -0.0013073337377238156\n" +
        "randomNC_003075.7 Arabidopsis thaliana chromosome 4 sequence : 0.0027463112096992367\n" +
        "randomNC_003074.8 Arabidopsis thaliana chromosome 3 sequence : 0.002372212850765114\n" +
        "randomAP008207.2 Oryza sativa Japonica Group DNA, chromosome 1, complete sequence, cultivar Nipponbare : 6.352161848206417E-4\n" +
        "randomAP008210.2 Oryza sativa Japonica Group DNA, chromosome 4, complete sequence, cultivar Nipponbare : 0.0014063363861806317\n" +
        "randomAP008215.2 Oryza sativa Japonica Group DNA, chromosome 9, complete sequence, cultivar Nipponbare : 3.071456872603718E-4\n" +
        "randomAP008212.2 Oryza sativa Japonica Group DNA, chromosome 6, complete sequence, cultivar Nipponbare : -1.7842505441080866E-4\n" +
        "randomAP008217.2 Oryza sativa Japonica Group DNA, chromosome 11, complete sequence, cultivar Nipponbare : 2.385855828640109E-6\n" +
        "randomAP008209.2 Oryza sativa Japonica Group DNA, chromosome 3, complete sequence, cultivar Nipponbare : -6.25992368069594E-4\n" +
        "randomAP008208.2 Oryza sativa Japonica Group DNA, chromosome 2, complete sequence, cultivar Nipponbare : 6.2847303958968E-4\n" +
        "randomAP008213.2 Oryza sativa Japonica Group DNA, chromosome 7, complete sequence, cultivar Nipponbare : -0.0010938801474861045\n" +
        "randomAP008216.2 Oryza sativa Japonica Group DNA, chromosome 10, complete sequence, cultivar Nipponbare : -0.0017071008535953585\n" +
        "randomAP008214.2 Oryza sativa Japonica Group DNA, chromosome 8, complete sequence, cultivar Nipponbare : -2.6377454186820237E-4\n" +
        "randomAP008211.2 Oryza sativa Japonica Group DNA, chromosome 5, complete sequence, cultivar Nipponbare : -0.0018252401601307126\n" +
        "randomAP008218.2 Oryza sativa Japonica Group DNA, chromosome 12, complete sequence, cultivar Nipponbare : 7.428471558463993E-4\n" +
        "randomNC_000020.11 Homo sapiens chromosome 20, GRCh38.p7 Primary Assembly : -0.005859529343509282\n" +
        "randomNC_000014.9 Homo sapiens chromosome 14, GRCh38.p7 Primary Assembly : -0.004322476163984329\n" +
        "randomNC_000006.12 Homo sapiens chromosome 6, GRCh38.p7 Primary Assembly : -4.6917884841597295E-4\n" +
        "randomNC_000019.10 Homo sapiens chromosome 19, GRCh38.p7 Primary Assembly : -0.004967379360425534\n" +
        "randomNC_000001.11 Homo sapiens chromosome 1, GRCh38.p7 Primary Assembly : -0.0011717945880124192\n" +
        "randomNC_000024.10 Homo sapiens chromosome Y, GRCh38.p7 Primary Assembly : -0.004399017700734393\n" +
        "randomNC_000004.12 Homo sapiens chromosome 4, GRCh38.p7 Primary Assembly : -4.7123333889124313E-4\n" +
        "randomNC_000016.10 Homo sapiens chromosome 16, GRCh38.p7 Primary Assembly : -0.0048202084654169605\n" +
        "randomNC_000022.11 Homo sapiens chromosome 22, GRCh38.p7 Primary Assembly : 6.576424111812355E-4\n" +
        "randomNC_000021.9 Homo sapiens chromosome 21, GRCh38.p7 Primary Assembly : -0.0014308675156316259\n" +
        "randomNC_000015.10 Homo sapiens chromosome 15, GRCh38.p7 Primary Assembly : -7.624378402734506E-4\n" +
        "randomNC_000018.10 Homo sapiens chromosome 18, GRCh38.p7 Primary Assembly : -0.002969707954273579\n" +
        "randomNC_000007.14 Homo sapiens chromosome 7, GRCh38.p7 Primary Assembly : -0.0018555968537083902\n" +
        "randomNC_000023.11 Homo sapiens chromosome X, GRCh38.p7 Primary Assembly : -0.0018618699700519111\n" +
        "randomNC_000005.10 Homo sapiens chromosome 5, GRCh38.p7 Primary Assembly : -0.0024482706534708653\n" +
        "randomNC_000008.11 Homo sapiens chromosome 8, GRCh38.p7 Primary Assembly : 3.333781253220588E-4\n" +
        "randomNC_000017.11 Homo sapiens chromosome 17, GRCh38.p7 Primary Assembly : -0.001261260996114833\n" +
        "randomNC_000011.10 Homo sapiens chromosome 11, GRCh38.p7 Primary Assembly : -8.533133151002598E-4\n" +
        "randomNC_000003.12 Homo sapiens chromosome 3, GRCh38.p7 Primary Assembly : -0.0010331939465412735\n" +
        "randomNC_000013.11 Homo sapiens chromosome 13, GRCh38.p7 Primary Assembly : -0.0017340898332128475\n" +
        "randomNC_000010.11 Homo sapiens chromosome 10, GRCh38.p7 Primary Assembly : -0.0017075963599217452\n" +
        "randomNC_000002.12 Homo sapiens chromosome 2, GRCh38.p7 Primary Assembly : -0.0014899954252611575\n" +
        "randomNC_000012.12 Homo sapiens chromosome 12, GRCh38.p7 Primary Assembly : -0.0016865297993601666\n" +
        "randomNC_000009.12 Homo sapiens chromosome 9, GRCh38.p7 Primary Assembly : -7.819695907059129E-4\n" +
        "randomBX284604.4 Caenorhabditis elegans chromosome IV : -0.0020086500640641874\n" +
        "randomBX284606.5 Caenorhabditis elegans chromosome X : 0.0012404463725761631\n" +
        "randomBX284602.5 Caenorhabditis elegans chromosome II : 0.0013220073132537721\n" +
        "randomBX284601.5 Caenorhabditis elegans chromosome I : -9.940752043812837E-4\n" +
        "randomBX284605.5 Caenorhabditis elegans chromosome V : -0.0011355167719136266\n" +
        "randomBX284603.4 Caenorhabditis elegans chromosome III : 0.0031604896057953174\n" +
        "randomCM008970.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 9, whole genome shotgun sequence : -0.0029055077439434805\n" +
        "randomCM008972.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 11, whole genome shotgun sequence : -0.004257608317295383\n" +
        "randomCM008977.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 16, whole genome shotgun sequence : 0.0012848660068307163\n" +
        "randomCM008969.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 8, whole genome shotgun sequence : -0.0010930508614202805\n" +
        "randomCM008971.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 10, whole genome shotgun sequence : -0.0014253088487605928\n" +
        "randomCM008978.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 17, whole genome shotgun sequence : -0.004193340271276412\n" +
        "randomCM008966.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 5, whole genome shotgun sequence : 0.0016208245296307476\n" +
        "randomCM008963.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 2, whole genome shotgun sequence : 9.564214532504374E-4\n" +
        "randomCM008965.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 4, whole genome shotgun sequence : 0.002012320521179623\n" +
        "randomCM008964.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 3, whole genome shotgun sequence : 0.0019225276762991981\n" +
        "randomCM008962.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 1, whole genome shotgun sequence : -0.0015398036802777104\n" +
        "randomCM008967.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 6, whole genome shotgun sequence : 4.2460316352618507E-4\n" +
        "randomCM008968.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 7, whole genome shotgun sequence : -0.0023600431390674676\n" +
        "randomCM008976.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 15, whole genome shotgun sequence : -0.005400566658362433\n" +
        "randomCM008973.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 12, whole genome shotgun sequence : 0.0023976828535187205\n" +
        "randomU03843.1 Chlamydomonas reinhardtii complete mitochondrial genome : 0.013682879153731172\n" +
        "randomCM008975.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 14, whole genome shotgun sequence : 0.0033046721924089497\n" +
        "randomCM008974.1 Chlamydomonas reinhardtii strain CC-503 cw92 mt+ chromosome 13, whole genome shotgun sequence : -0.004159316365861513\n";
    public static void main(String[] args) throws FileNotFoundException {
        String[] valuesSplit = values2.split("\n");
for(String s : valuesSplit){

    System.out.println(replaceLast(s, "\\.", ","));
}
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


    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }


}
