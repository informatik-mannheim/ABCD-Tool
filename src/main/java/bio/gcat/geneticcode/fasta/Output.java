package bio.gcat.geneticcode.fasta;

import java.util.*;

public class Output {
    private static List<Map<Element, Integer>> analyzedTupels = new ArrayList<>();

    public static List<Map<Element, Integer>> getAnalyzedTupels() {
        return analyzedTupels;
    }


    public static String[][] getAsTable() {


        analyzedTupels.sort(new Comparator<Map<Element, Integer>>() {
            @Override
            public int compare(Map<Element, Integer> o1, Map<Element, Integer> o2) {
                return o1.entrySet().size() - o2.entrySet().size();
            }
        });

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


                        table[column][row] = "P_" + (column / 2 + 1) + "(" + elements[row % 4] + "_" + (row / 4 + 1) + ")=";
                    }
                } else {
                    Element current = new Element(elements[row % 4], row / 4);
                    double relativeFrequency = ((double)analyzedTupels.get(column / 2).getOrDefault(current, 0));
                    int tupelLength = analyzedTupels.get(column/2).size()/4;
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


    public static String outputAsLatexTable() {
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
    public static String toHTML(){
        String[][] data = getAsTable();
        StringBuilder sb = new StringBuilder();
        sb.append("<table>\n");
        for(int i = 0; i < getAnalyzedTupels().size(); i++){
            int sizeOfTheFragment = getAnalyzedTupels().get(i).size()/4;
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









}
