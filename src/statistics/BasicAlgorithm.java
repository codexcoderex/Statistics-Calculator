package statistics;

import java.util.Arrays;

public class BasicAlgorithm {
    // This class includes methods for calculating mean, median, mode, variance, standard deviation, and etc.
    // Each method demonstrates a fundamental statistical calculation, providing reusable static utilities for basic statistics.
    // Exception handling is implemented in each method using try-catch-finally blocks to ensure robustness and prevent runtime errors from propagating.

    // Method for parsing input from a text area
    // This method splits the input string by commas or whitespace, attempts to parse each token as a double,
    // and collects both valid numbers and invalid (non-numeric) entries. It returns a ParseResult object containing both.
    // This feature is useful for user input validation and feedback in GUI applications.
    public static class ParseResult {
        public final double[] numbers;
        public final java.util.List<String> invalids;

        public ParseResult(double[] numbers, java.util.List<String> invalids) {
            this.numbers = numbers;
            this.invalids = invalids;
        }
    }

    public static ParseResult parseInputWithInvalids(String input) {
        // Method for parsing input from a text area
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return new ParseResult(new double[0], new java.util.ArrayList<>());
        }
    }

    // Method for calculating n number of data points in a dataset
    // This method simply returns the length of the data array, representing the sample size (n).
    // Useful for descriptive statistics and further calculations that require the number of observations.
    public static int calculateCount(double[] data) {
        // Method for calculating n number of data points in a dataset
        try {
            int count = data.length;
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Method for calculating the total sum of a dataset
    // Iterates through the data array and accumulates the sum of all elements.
    // The sum is a foundational value for calculating mean and other aggregate statistics.
    public static double calculateSum(double[] data) {
        // Method for calculating the total sum of a dataset
        double total = 0;
        try {
            for (double d : data) total += d;
            return total;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    // Method for calculating mean 
    // The mean (average) is calculated by dividing the sum of all data points by the number of data points.
    // This is a measure of central tendency, commonly used in statistics to represent a typical value.
    public static double calculateMean(double[] data) {
        // Method for calculating mean 
        double sum = 0;
        try {
            for (double num : data) {
                sum += num;
            }
            return sum / data.length;
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        } 
    }
    
    // Method for calculating median
    // The median is the middle value in a sorted dataset. If the dataset has an even number of elements,
    // it returns the average of the two middle values. Sorting is required to find the correct position.
    // Median is less sensitive to outliers compared to the mean.
    public static double calculateMedian(double[] data) {
        // Method for calculating median
        try {
            double[] sorted = data.clone();
            Arrays.sort(sorted);
            int n = sorted.length;
            // If n is even, return the average of the two middle numbers
            // If n is odd, return the middle number
            return (n % 2 == 0) ? (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0 : sorted[n / 2]; 
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        } 
    }
    
    // Method for calculating mode
    // The mode is the value that appears most frequently in the dataset. This method uses a HashMap to count occurrences.
    // If multiple values have the same highest frequency, the first encountered is returned.
    // Mode is useful for categorical or discrete data analysis.
    public static double calculateMode(double[] data) {
        // Method for calculating mode
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        } 
    }
    
    // Method for calculating geometric mean
    // The geometric mean is calculated by multiplying all data points and taking the nth root (where n is the number of data points).
    // It is only defined for positive numbers and is useful for datasets involving rates or ratios.
    public static double calculateGeometricMean(double[] data) {
        // Method for calculating geometric mean
        double product = 1.0;
        try {
            for (double d : data) {
                if (d <= 0) return Double.NaN; 
                product *= d;
            }
            return Math.pow(product, 1.0 / data.length);
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        } 
    }

    // Method for calculating min
    // Finds and returns the smallest value in the dataset. Useful for range and outlier detection.
    public static double calculateMin(double[] data) {
        // Method for calculating min
        try {
            double min = data[0];
            for (double d : data) if (d < min) min = d;
            return min;
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        } 
    }

    // Method for calculating max
    // Finds and returns the largest value in the dataset. Useful for range and outlier detection.
    public static double calculateMax(double[] data) {
        // Method for calculating max
        try {
            double max = data[0];
            for (double d : data) if (d > max) max = d;
            return max;
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        } 
    }

    // Method for calculating range
    // The range is the difference between the maximum and minimum values in the dataset.
    // It provides a simple measure of data spread or dispersion.
    public static Double calculateRange(double[] data) {
        // Method for calculating range
        try {
            if (data == null || data.length == 0) return null;
            return calculateMax(data) - calculateMin(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method for calculating variance
    // Variance measures the average squared deviation from the mean, indicating data spread.
    // This method calculates population variance (divides by n).
    public static double calculateVariance(double[] data) {
        // Method for calculating variance
        try {
            double m = calculateMean(data);
            double sum = 0;
            for (double d : data) sum += Math.pow(d - m, 2);
            return sum / data.length;
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    // Method for calculating standard deviation
    // Standard deviation is the square root of variance, providing a measure of spread in the same units as the data.
    // This method calculates population standard deviation.
    public static double calculateStandardDeviation(double[] data) {
        // Method for calculating standard deviation
        try {
            return Math.sqrt(calculateVariance(data));
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    // Method for calculating sample variance
    // Sample variance divides the sum of squared deviations by (n-1) instead of n, correcting bias in small samples.
    // This is important for inferential statistics.
    public static double calculateSampleVariance(double[] data) {
        // Method for calculating sample variance
        try {
            double m = calculateMean(data);
            double sum = 0;
            for (double d : data) sum += Math.pow(d - m, 2);
            return sum / (data.length - 1); // Sample variance uses n-1
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }

    // Method for calculating sample standard deviation
    // Sample standard deviation is the square root of the sample variance, used for estimating population spread from a sample.
    public static double calculateSampleStandardDeviation(double[] data) {
        // Method for calculating sample standard deviation
        try {
            return Math.sqrt(calculateSampleVariance(data));
        } catch (Exception e) {
            e.printStackTrace();
            return Double.NaN;
        }
    }
}