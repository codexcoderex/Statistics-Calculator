package statistics;

public class TestsAlgorithm {
    public static double calculateStandardError(double stdDev, int count) {
        try {
            if (count <= 1) throw new ArithmeticException("Sample size too small for standard error.");
            
            return stdDev / Math.sqrt(count);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    public static double calculateTTest(double sampleMean, double sampleStdDev, int sampleSize, double popMean) {
        try {
            if (sampleSize <= 1) throw new ArithmeticException("Sample size too small for t-test.");

            return (sampleMean - popMean) / (sampleStdDev / Math.sqrt(sampleSize));
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }


    public static double calculateConfidenceInterval(double stdDev, int count, double confidenceLevel) {
        try {
            if (count <= 1) throw new ArithmeticException("Sample size too small for confidence interval.");
            double z = 1.96;
            return z * (stdDev / Math.sqrt(count));
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    // Z-Score for a raw value
    public static double calculateZScore(double x, double popMean, double popStdDev) {
        try {
            return (x - popMean) / popStdDev;
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    // Z-Test for a sample mean
    public static double calculateZTest(double sampleMean, double popMean, double popStdDev, int sampleSize) {
        try {
            return (sampleMean - popMean) / (popStdDev / Math.sqrt(sampleSize));
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    public static double getCriticalTValue(double confidence, int df) {
        if (df <= 0) return Double.NaN;
        if (confidence == 0.95) {
            if (df <= 30) return 2.045; // simplified lookup
            return 1.96;
        }
        return 1.96; // fallback
    }
}
