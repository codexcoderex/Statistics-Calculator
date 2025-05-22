package statistics;

public class TestsAlgorithm {
    double calculateStandardError(double stdDev, int count) {
        try {
            if (count <= 1) throw new ArithmeticException("Sample size too small for standard error.");
            
            return stdDev / Math.sqrt(count);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    double calculateTTest(double sampleMean, double sampleStdDev, int sampleSize, double popMean) {
    try {
        if (sampleSize <= 1) throw new ArithmeticException("Sample size too small for t-test.");

        return (sampleMean - popMean) / (sampleStdDev / Math.sqrt(sampleSize));
    } catch (Exception e) {
        e.printStackTrace();
        return Double.NaN;
    }
}


    double calculateConfidenceInterval(double stdDev, int count, double confidenceLevel) {
        try {
            if (count <= 1) throw new ArithmeticException("Sample size too small for confidence interval.");
            double z = 1.96; // For 95% confidence level
            return z * (stdDev / Math.sqrt(count));
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

}
