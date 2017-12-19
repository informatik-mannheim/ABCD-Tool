package bio.gcat.abcdtool;

public class RandomStringGenerator {
    //AGCT
    private static int[][] covariance = {
            {30, 28, 21, 21},
            {26, 32, 23, 19},
            {32, 9, 31, 28},
            {19, 31, 24, 26}};
    private static int[][] coVarianceCG={
            {21,38,27,14},
            {18,37,31,15},
            {20,23,36,21},
            {10,37,32,22}
    };

    public static String randomCovarianceString(int length, boolean cgIsland) {
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
            int[] theseValues = cgIsland ? coVarianceCG[currentIndex] : covariance[currentIndex];
            if (number*100 < theseValues[0]) {
                s.append("A");
            } else if (number*100 < theseValues[0]+theseValues[1]) {
                s.append("G");
            } else if (number*100 < theseValues[0]+theseValues[1]+theseValues[2]) {
                s.append("C");
            } else {
                s.append("T");
            }

        }
        return s.toString();
    }

    public static String randomString(int length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double number = Math.random();
            if (number < 0.30) {
                s.append("A");
            } else if (number < 0.6) {
                s.append("T");
            } else if (number < 0.8) {
                s.append("G");
            } else {
                s.append("C");
            }
        }
        return s.toString();
    }
}
