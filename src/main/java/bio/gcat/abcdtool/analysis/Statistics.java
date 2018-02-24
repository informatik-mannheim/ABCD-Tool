package bio.gcat.abcdtool.analysis;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

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

    public double getStandardErrorOfTheMean(){
        return getStdDev()/Math.sqrt(data.length);
    }


    public double median() {
       return stats.getPercentile(50) ;
    }
}
