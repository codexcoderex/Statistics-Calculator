package appGUI.panels.contentPanels.cardPanels;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import statistics.BasicAlgorithm;

import java.awt.*;
import java.awt.event.*;

public class TestStatsPanel extends JPanel {
    private JTextField meanField, stdDevField, countField, popMeanField, popStdDevField, confLevelField, rawValueField;
    private JLabel resultLabel;

    public TestStatsPanel() {
    
       // Create main panel with GridLayout and gaps between components
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 5, 3));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding around the panel

        // Add components to main panel
        mainPanel.add(new JLabel("Sample Mean:"));
        meanField = new JTextField();
        mainPanel.add(meanField);

        mainPanel.add(new JLabel("Sample Std Dev:"));
        stdDevField = new JTextField();
        mainPanel.add(stdDevField);

        mainPanel.add(new JLabel("Sample Count:"));
        countField = new JTextField();
        mainPanel.add(countField);

        mainPanel.add(new JLabel("Population Mean:"));
        popMeanField = new JTextField();
        mainPanel.add(popMeanField);

        mainPanel.add(new JLabel("Population Std Dev:"));
        popStdDevField = new JTextField();
        mainPanel.add(popStdDevField);

        mainPanel.add(new JLabel("Confidence Level (e.g. 0.95):"));
        confLevelField = new JTextField("0.95");
        mainPanel.add(confLevelField);

        mainPanel.add(new JLabel("Raw Value (for Z-score):"));
        rawValueField = new JTextField();
        mainPanel.add(rawValueField);

        resultLabel = new JLabel("Result: ");
        mainPanel.add(resultLabel);
        mainPanel.add(new JLabel(""));  // filler

        JButton seButton = new JButton("Standard Error");
        seButton.addActionListener(e -> runStandardError());
        mainPanel.add(seButton);

        JButton tTestButton = new JButton("T-Test");
        tTestButton.addActionListener(e -> runTTest());
        mainPanel.add(tTestButton);

        JButton zScoreButton = new JButton("Z-Score");
        zScoreButton.addActionListener(e -> runZScore());
        mainPanel.add(zScoreButton);

        JButton zTestButton = new JButton("Z-Test");
        zTestButton.addActionListener(e -> runZTest());
        mainPanel.add(zTestButton);

        JButton ciButton = new JButton("Confidence Interval");
        ciButton.addActionListener(e -> runConfidenceInterval());
        
        mainPanel.add(ciButton);

        // Add the panel to the frame's content pane
        add(mainPanel, BorderLayout.CENTER);
        
    }
    // Statistical Tests Methods
    // Standard Error, T-Test, Z-Score, Z-Test, Confidence Interval
    // Each method parses the input fields, performs the calculation, and updates the result label
    
    private void runStandardError() {
        try {
            double stdDev = Double.parseDouble(stdDevField.getText());
            int count = Integer.parseInt(countField.getText());
            if (count <= 1) throw new ArithmeticException("Sample size too small for standard error.");
            double se = stdDev / Math.sqrt(count);
            resultLabel.setText("Standard Error: " + String.format("%.6f", se));
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    private void runTTest() {
        try {
            double mean = Double.parseDouble(meanField.getText());
            double stdDev = Double.parseDouble(stdDevField.getText());
            int count = Integer.parseInt(countField.getText());
            double popMean = Double.parseDouble(popMeanField.getText());
            double t = (mean - popMean) / (stdDev / Math.sqrt(count));
            resultLabel.setText("T-value: " + String.format("%.6f", t));
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    private void runZScore() {
        try {
            double x = Double.parseDouble(rawValueField.getText());
            double mean = Double.parseDouble(meanField.getText());
            double stdDev = Double.parseDouble(stdDevField.getText());
            if (stdDev == 0) throw new ArithmeticException("Standard deviation cannot be zero.");
            double z = (x - mean) / stdDev;
            resultLabel.setText("Z-score: " + String.format("%.6f", z));
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    private void runZTest() {
        try {
            double mean = Double.parseDouble(meanField.getText());
            int count = Integer.parseInt(countField.getText());
            double popMean = Double.parseDouble(popMeanField.getText());
            double popStdDev = Double.parseDouble(popStdDevField.getText());
            if (popStdDev == 0) throw new ArithmeticException("Population standard deviation cannot be zero.");
            double z = (mean - popMean) / (popStdDev / Math.sqrt(count));
            resultLabel.setText("Z-value: " + String.format("%.6f", z));
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }

    private void runConfidenceInterval() {
        try {
            double mean = Double.parseDouble(meanField.getText());
            double stdDev = Double.parseDouble(stdDevField.getText());
            int count = Integer.parseInt(countField.getText());
            double conf = Double.parseDouble(confLevelField.getText());
            int df = count - 1;
            if (df <= 0) throw new ArithmeticException("Degrees of freedom must be positive.");
            double margin = BasicAlgorithm.getCriticalTValue(conf, df) * (stdDev / Math.sqrt(count));
            resultLabel.setText(String.format("CI: [%.4f, %.4f]", mean - margin, mean + margin));
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
        }
    }
}