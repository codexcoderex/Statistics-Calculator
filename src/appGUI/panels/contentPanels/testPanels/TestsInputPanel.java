package appGUI.panels.contentPanels.testPanels;

import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class TestsInputPanel extends JPanel {

    private JTextField sampleMeanField;
    private JTextField sampleStdDevField;
    private JTextField sampleCountField;
    private JTextField popMeanField;
    private JTextField popStdDevField;
    private JTextField confidenceLevelField;
    private JTextField rawValueField;

    public TestsInputPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        sampleMeanField = new JTextField(10);
        sampleStdDevField = new JTextField(10);
        sampleCountField = new JTextField(10);
        popMeanField = new JTextField(10);
        popStdDevField = new JTextField(10);
        confidenceLevelField = new JTextField(10);
        rawValueField = new JTextField(10);

        add(styledInputPanel("Sample Mean:", sampleMeanField));
        add(Box.createVerticalStrut(10));
        add(styledInputPanel("Sample StdDev:", sampleStdDevField));
        add(Box.createVerticalStrut(10));
        add(styledInputPanel("Sample Count:", sampleCountField));
        add(Box.createVerticalStrut(10));
        add(styledInputPanel("Population Mean:", popMeanField));
        add(Box.createVerticalStrut(10));
        add(styledInputPanel("Population StdDev:", popStdDevField));
        add(Box.createVerticalStrut(10));
        add(styledInputPanel("Confidence Level:", confidenceLevelField));
        add(Box.createVerticalStrut(10));
        add(styledInputPanel("Raw Value (for z-score):", rawValueField));
    }

    // Helper to create a styled input panel like the basic stats panels
    private JPanel styledInputPanel(String label, JTextField field) {
        PanelDesign panel = new PanelDesign();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setOpaque(false);
        JLabel jLabel = new JLabel(label);
        jLabel.setPreferredSize(new Dimension(160, 60));
        jLabel.setFont(jLabel.getFont().deriveFont(Font.BOLD, 14f));
        field.setMaximumSize(new Dimension(120, 30));
        panel.add(Box.createHorizontalStrut(10));
        panel.add(jLabel);
        panel.add(Box.createHorizontalStrut(10));
        panel.add(field);
        panel.add(Box.createHorizontalGlue());
        return panel;
    }

    // Optionally, add getters for retrieving input values
    public String getSampleMean() { return sampleMeanField.getText(); }
    public String getSampleStdDev() { return sampleStdDevField.getText(); }
    public String getSampleCount() { return sampleCountField.getText(); }
    public String getPopMean() { return popMeanField.getText(); }
    public String getPopStdDev() { return popStdDevField.getText(); }
    public String getConfidenceLevel() { return confidenceLevelField.getText(); }
    public String getRawValue() { return rawValueField.getText(); }
}