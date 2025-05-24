package appGUI.panels.contentPanels.testPanels;

import appGUI.panels.PanelDesign;
import statistics.BasicAlgorithm;
import statistics.TestsAlgorithm;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TestsInputPanel extends JPanel {
    private JTextField[] fields;
    private JLabel resultLabel;
    private TestsOutputPanel outputPanel;


    public TestsInputPanel(TestsOutputPanel outputPanel) {
        this.outputPanel = outputPanel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(15, 15, 15, 15));
        setOpaque(false);

        String[] labels = {
            "Sample Mean:",
            "Sample Std Dev:",
            "Sample Count:",
            "Population Mean:",
            "Population Std Dev:",
            "Confidence Level (e.g. 0.95):",
            "Raw Value (for Z-score):"
        };

        fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            fields[i] = (i == 5) ? new JTextField("0.95", 20) : new JTextField(20);
            add(createInputPanel(labels[i], fields[i]));
            if (i < labels.length - 1) add(Box.createVerticalStrut(8));
        }
        add(Box.createVerticalStrut(12));

        // Add listeners
        for (JTextField field : fields) {
            field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
                public void insertUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
                public void removeUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
                public void changedUpdate(javax.swing.event.DocumentEvent e) { updateStats(); }
            });
        }
    }

    private JPanel createInputPanel(String label, JTextField field) {
        PanelDesign panel = new PanelDesign();
        panel.setBackground(new Color(0xE3F2FD));
        panel.setLayout(new GridBagLayout());
        panel.setOpaque(false);

        JLabel jLabel = new JLabel(label);
        jLabel.setFont(new Font("Arial", Font.BOLD, 15));
        jLabel.setPreferredSize(new Dimension(230, 28));
        field.setPreferredSize(new Dimension(0, 28));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 10, 0, 10);
        panel.add(jLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(field, gbc);

        return panel;
    }

    private void updateStats() {
        double[] parsed = new double[7];
        int sampleCount;


        // Loops to check inputs, int as separate to ensure integer
        for (int i = 0; i < parsed.length; i++) {
            try {
                parsed[i] = Double.parseDouble(fields[i].getText().trim());
            } catch (Exception e) {
                parsed[i] = Double.NaN;
            }
        }

        try {
            sampleCount = Integer.parseInt(fields[2].getText().trim());
        } catch (Exception e) {
            sampleCount = -1;
        }

        double sampleMean = parsed[0];
        double sampleSD = parsed[1];
        double populationMean = parsed[3];
        double populationSD = parsed[4];
        double confidenceLevel = parsed[5];
        double rawValue = parsed[6];
        int df = sampleCount - 1;

        // Just if statements to ensure no NaN values are passed to the algorithm
        double standardError = (!Double.isNaN(sampleSD) && sampleCount > 1)
            ? TestsAlgorithm.calculateStandardError(sampleSD, sampleCount) : Double.NaN;

        double tTest = (!Double.isNaN(sampleMean) && !Double.isNaN(sampleSD) && sampleCount > 1 && !Double.isNaN(populationMean))
            ? TestsAlgorithm.calculateTTest(sampleMean, sampleSD, sampleCount, populationMean) : Double.NaN;
        
        double tCritical = (!Double.isNaN(confidenceLevel) && df > 0)
            ? TestsAlgorithm.calculateCriticalTValue(confidenceLevel, df) : Double.NaN;

        double[] confInterval = (!Double.isNaN(sampleSD) && sampleCount > 1 && !Double.isNaN(confidenceLevel))
            ? TestsAlgorithm.calculateConfidenceInterval(sampleMean, sampleSD, sampleCount, tCritical) : new double[] { Double.NaN, Double.NaN };

        double zScore = (!Double.isNaN(rawValue) && !Double.isNaN(populationMean) && !Double.isNaN(populationSD) && populationSD != 0)
            ? TestsAlgorithm.calculateZScore(rawValue, populationMean, populationSD) : Double.NaN;

        double zTest = (!Double.isNaN(sampleMean) && !Double.isNaN(populationMean) && !Double.isNaN(populationSD) && sampleCount > 1)
            ? TestsAlgorithm.calculateZTest(sampleMean, populationMean, populationSD, sampleCount) : Double.NaN;

        double cohensD = (!Double.isNaN(sampleMean) && !Double.isNaN(populationMean) && !Double.isNaN(sampleSD) && sampleSD != 0)
            ? TestsAlgorithm.calculateCohensD(sampleMean, populationMean, sampleSD) : Double.NaN;

        outputPanel.setTestOutputs(
            standardError, tTest, zScore, zTest, cohensD, confInterval
        );

    }

}

