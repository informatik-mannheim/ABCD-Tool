package bio.gcat.abcdtool.sequences.generatesequence;

public class RandomStringGenerator implements RandomString {
    //AGCT
    private static double[][] covariance = {
            {30, 28, 21, 21},
            {26, 32, 23, 19},
            {32, 9, 31, 28},
            {19, 31, 24, 26}};
    private static double[][] coVarianceCG={
            {21,38,27,14},
            {18,37,31,15},
            {20,23,36,21},
            {10,37,32,22}
    };
    private static double[][] condProbabiltiesHuman1 ={
            {32.654629485847264, 24.52450263153206, 17.292732418078985, 25.52813546454169},
            {28.77818791311597, 25.963261582628183, 21.08700437255217, 24.171546131703675},
            {34.89394017933991, 4.94258422999162, 25.942695290228157, 34.22078030044031},
            {21.644705609667775, 24.978215563186823, 20.588717164464687, 32.788361662680715}
    };

    /**
     * creates a random sequence using a conditional probabilitiesMatrix
     * @param length
     * @param conditionalProbabilities
     * @return
     */
    public String randomConditionalProbabiliteisString(int length, double[][] conditionalProbabilities){
        StringBuilder s = new StringBuilder();
        String digit = randomString(1);
        s.append(digit);
        for (int i = 0; i < length; i++) {
            double number = Math.random();

            int currentIndex = 0;
            switch (s.charAt(i)) {
                case 'A':
                    currentIndex = 0;
                    break;
                case 'G':
                    currentIndex = 1;
                    break;
                case 'C':
                    currentIndex = 2;
                    break;
                case 'T':
                    currentIndex = 3;
                    break;

            }
             double[] theseValues = conditionalProbabilities[currentIndex];
            if (number < theseValues[0]) {
                s.append("A");
            } else if (number < theseValues[0]+theseValues[1]) {
                s.append("G");
            } else if (number < theseValues[0]+theseValues[1]+theseValues[2]) {
                s.append("C");
            } else {
                s.append("T");
            }

        }
        return s.toString();
    }

    public String randomConditionalProbabiliteisString(int length, boolean cgIsland) { // TOOO : change from boolean to int or enum
        double[][]conditionalProb= new double[4][4];
        for (int i = 0; i< condProbabiltiesHuman1.length; i++){
            for(int j = 0; j< condProbabiltiesHuman1.length; j++){
                conditionalProb[i][j]= condProbabiltiesHuman1[i][j]/100;
            }
        }
        return randomConditionalProbabiliteisString(length, conditionalProb);
//        StringBuilder s = new StringBuilder();
//        String digit = randomString(1);
//        s.append(digit);
//        for (int i = 0; i < length; i++) {
//            double number = Math.random();
//
//            int currentIndex = 0;
//            switch (s.charAt(i)) {
//                case 'A':
//                    currentIndex = 0;
//                    break;
//                case 'G':
//                    currentIndex = 1;
//                    break;
//                case 'C':
//                    currentIndex = 2;
//                    break;
//                case 'T':
//                    currentIndex = 3;
//                    break;
//
//            }
//            double[] theseValues = cgIsland ? condProbabiltiesHuman1[currentIndex] : condProbabiltiesHuman1[currentIndex];
//            if (number*100 < theseValues[0]) {
//                s.append("A");
//            } else if (number*100 < theseValues[0]+theseValues[1]) {
//                s.append("G");
//            } else if (number*100 < theseValues[0]+theseValues[1]+theseValues[2]) {
//                s.append("C");
//            } else {
//                s.append("T");
//            }
//
//        }
//        return s.toString();
    }

    @Override
    public String randomString(int length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double number = Math.random();
            if (number < 0.291) {
                s.append("A");
            } else if (number < 0.583) {
                s.append("T");
            } else if (number < 0.792) {
                s.append("G");
            } else {
                s.append("C");
            }
        }
        return s.toString();
    }
}
