package bio.gcat.abcdtool.analysis;

import bio.gcat.abcdtool.sequences.readsequence.Element;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.List;
import java.util.Map;

/**
 * a wrapper for apache Maths
 */
public class Statistics {
    double[] data;
    int size;
    DescriptiveStatistics stats;

    public Statistics(double[] data) {
        this.data = data;
        size = data.length;
        stats = new DescriptiveStatistics(data);
    }

    public double getMean() {
        return stats.getMean();
    }

    double getVariance() {
        return stats.getVariance();
    }

    public double getStdDev() {
        return stats.getStandardDeviation();
    }

    public double getStandardErrorOfTheMean() {
        return getStdDev() / Math.sqrt(data.length);
    }


    public double median() {
        return stats.getPercentile(50);
    }

    public static double[] getMaximumandMinimumFrequency(List<Analysis> analyses, char c) {
        double min = 1;
        double max = -1;
        for (Analysis a : analyses) {
            Map<Element, Integer> frequencies = a.getFrequencies();
            for (Element e : frequencies.keySet()) {
                if (e.getBase() == c) {

                    double probability = a.getRelativeProbability(e);
                    if (probability > max) {
                        max = probability;
                    } else if (probability < min) {
                        min = probability;
                    }
                }
            }


        }
        return new double[]{min,max};
    }


}
