package statistics;

import java.util.Arrays;

public class BasicAlgorithm {
    // This class includes methods for calculating mean, median, mode, variance, standard deviation, and etc.

    // Method for parsing input from a text area
    public static class ParseResult {
        public final double[] numbers;
        public final java.util.List<String> invalids;

        public ParseResult(double[] numbers, java.util.List<String> invalids) {
            this.numbers = numbers;
            this.invalids = invalids;
        }
    }

    public static ParseResult parseInputWithInvalids(String input) {
        java.util.List<Double> nums = new java.util.ArrayList<>();
        java.util.List<String> invalids = new java.util.ArrayList<>();
        for (String s : input.trim().split("[,\\s]+")) {
            if (s.isEmpty()) continue;
            try {
                Double d = Double.parseDouble(s);
                nums.add(d);
            } catch (NumberFormatException e) {
                invalids.add(s);
            }
        }
        double[] arr = nums.stream().mapToDouble(Double::doubleValue).toArray();
        return new ParseResult(arr, invalids);
    }

    // Method for calculating n number of data points in a dataset
    public static int calculateCount(double[] data) {
        int count = data.length;
        return count;
    }

    // Method for calculating the total sum of a dataset
    public static double calculateSum(double[] data) {
        double total = 0;
        for (double d : data) total += d;
        return total;
    }
    
    // Method for calculating mean 
    public static double calculateMean(double[] data) {
        double sum = 0;
        for (double num : data) {
            sum += num;
        }
        return sum / data.length;
    }
    
    // Method for calculating median
    public static double calculateMedian(double[] data) {
        double[] sorted = data.clone();
        Arrays.sort(sorted);
        int n = sorted.length;

        // If n is even, return the average of the two middle numbers
        // If n is odd, return the middle number
        return (n % 2 == 0) ? (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0 : sorted[n / 2]; 
    }
    
    // Method for calculating mode
    public static double calculateMode(double[] data) {
        // Using a HashMap to count occurrences of each number
        java.util.HashMap<Double, Integer> countMap = new java.util.HashMap<>();
        int maxCount = 0;
        double mode = data[0];

        for (double num : data) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
            if (count > maxCount) {
                maxCount = count;
                mode = num;
            }
        }
        return mode;
    }
    
    // Method for calculating geometric mean
    public static double calculateGeometricMean(double[] data) {
        double product = 1.0;

        for (double d : data) {
            if (d <= 0) return Double.NaN; 
            product *= d;
        }
        return Math.pow(product, 1.0 / data.length);
    }

    // Method for calculating min
    public static double calculateMin(double[] data) {
        double min = data[0];
        for (double d : data) if (d < min) min = d;
        return min;
    }

    // Method for calculating max
    public static double calculateMax(double[] data) {
        double max = data[0];
        for (double d : data) if (d > max) max = d;
        return max;
    }

    // Method for calculating range
    public static Double calculateRange(double[] data) {
        if (data == null || data.length == 0) return null;
        return calculateMax(data) - calculateMin(data);
    }

    // Method for calculating variance
    public static double calculateVariance(double[] data) {
        double m = calculateMean(data);
        double sum = 0;
        for (double d : data) sum += Math.pow(d - m, 2);
        return sum / data.length;
    }

    // Method for calculating standard deviation
    public static double calculateStandardDeviation(double[] data) {
        return Math.sqrt(calculateVariance(data));
    }

    // Method for calculating sample variance
    public static double calculateSampleVariance(double[] data) {
        double m = calculateMean(data);
        double sum = 0;
        for (double d : data) sum += Math.pow(d - m, 2);
        return sum / (data.length - 1); // Sample variance uses n-1
    }

    // Method for calculating sample standard deviation
    public static double calculateSampleStandardDeviation(double[] data) {
        return Math.sqrt(calculateSampleVariance(data));
    }


}