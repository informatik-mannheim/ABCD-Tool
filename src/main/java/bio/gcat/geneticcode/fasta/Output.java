package bio.gcat.geneticcode.fasta;

import java.util.*;

public class Output {
    private  List<Map<Element, Integer>> analyzedTupels ;
    private List<Analysis> analyses ;



    public Output() {
       analyzedTupels= new ArrayList<>() ;
       analyses= new ArrayList<>();
    }

    public  List<Map<Element, Integer>> getAnalyzedTupels() {
        return analyzedTupels;
    }


    public  String[][] getAsTable() {

/* only needed if lengths in input are not in ascending order
        analyzedTupels.sort(new Comparator<Map<Element, Integer>>() {
            @Override
            public int compare(Map<Element, Integer> o1, Map<Element, Integer> o2) {
                return o1.entrySet().size() - o2.entrySet().size();
            }
        });*/

        int sequenceLength = 0;
        for (Element e : analyzedTupels.get(0).keySet()) {
            sequenceLength += analyzedTupels.get(0).get(e);
        }
        char[] elements = {'A', 'T', 'G', 'C'};
        int lengthOfLongestSequence = analyzedTupels.get(analyzedTupels.size() - 1).size(); //gets the different values of the longest n-plet
        String[][] table = new String[analyzedTupels.size() * 2][lengthOfLongestSequence];
        for (int column = 0; column < table.length; column++) {
            for (int row = 0; row < table[0].length; row++) {
                if (column % 2 == 0) {
                    if (row < analyzedTupels.get(column / 2).size()) {


                        table[column][row] = "P_{" + (analyses.get(column/2).getTupel()) + "}(" + elements[row % 4] + "_{" + (row / 4 + 1) + "})=";
                    }
                } else {
                    Element current = new Element(elements[row % 4], row / 4);
                    double relativeFrequency = ((double)analyzedTupels.get(column / 2).getOrDefault(current, 0));
                    int tupelLength = analyses.get(column/2).getTupel();
                    relativeFrequency = relativeFrequency / (sequenceLength/tupelLength);

                    if (relativeFrequency == 0) {
                        table[column][row] = "";
                    } else {

                        double rounded = Math.floor(1000 * relativeFrequency + 0.5) / 1000;

                        table[column][row] = rounded +"";
                    }
                }

            }
        }


        return table;


    }


    public  String outputAsLatexTable() {
        String[][] table = getAsTable();
        StringBuilder output = new StringBuilder();
        output.append("\\begin{tabular}{");
        for (int column = 0; column < table.length; column++) {
            output.append("|c");
        }
        output.append("|}\n\t\\hline\n");
        for (int row = 0; row < table[0].length; row++) {
            output.append("\t");
            for (int column = 0; column < table.length; column++) {
                if (table[column][row] != null) {
                    if(column%2 ==0){
                        output.append("$");
                        output.append(table[column][row]);
                        output.append("$");
                    }else{

                        output.append(table[column][row]);
                    }
                }

                if (column != table.length - 1) {
                    output.append(" & ");
                }

            }
            output.append(" \\\\\n" +
                    "\t\\hline \n");
        }
        output.append("\\end{tabular} ");

        return output.toString();
    }

    /**
     * from https://stackoverflow.com/a/34738509 (also on web archive)
     * @return
     */
    public  String toHTML(){
        String[][] data = getAsTable();
        StringBuilder sb = new StringBuilder();

        sb.append(minMaxAverageTable()); // new part of output

        sb.append("<table>\n");
        for(int i = 0; i < getAnalyzedTupels().size(); i++){
            int sizeOfTheFragment = analyses.get(i).getTupel();
            sb.append("\t\t<th>" + sizeOfTheFragment + "</th>\n");
            sb.append("\t\t<th/>\n");
        }
        for(int row = 0; row < data[0].length; row++){
            sb.append("\t<tr>\n");
            for(int col = 0; col < data.length; col++){
                sb.append("\t\t<td>" + data[col][row] + "</td>\n");
            }
            sb.append("\t</tr>\n");
        }
        sb.append("</table>");
        return sb.toString();
    }

    private String minMaxAverageTable() {
        //TODO: Think about how to get the output
        return "";
    }

    public  String toBarChart(){
        String[][] data = getAsTable();
        StringBuilder sb = new StringBuilder();
        return null;
    }


    public void addAnalysis(Analysis analysis) {
        analyzedTupels.add(analysis.getFrequencies());
        analyses.add(analysis);
    }
}
