package appGUI.panels.contentPanels.testPanels;

import appGUI.panels.PanelDesign;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TestsOutputPanel extends JPanel {
    private final JLabel[] dataLabels = new JLabel[6];

    private static final String[] LABELS = {
        "Standard Error", "T-Test", 
        "Z-Score", "Z-Test",  "Cohen's d", "Confidence Interval"
    };

    public TestsOutputPanel() {
        setLayout(new GridLayout(3, 2, 16, 12)); // horizontal gap, vertical gap
        setOpaque(false);
        setBorder(new EmptyBorder(15, 18, 27, 18)); // top, left, bottom, right

        for (int i = 0; i < LABELS.length; i++) {
            add(createCellPanel(LABELS[i], i));
        }
    }

    private JPanel createCellPanel(String label, int i) {
        PanelDesign panel = new PanelDesign();
        panel.setBackground(new Color(0xE3F2FD));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        JLabel dataLabel = new JLabel("0");

        if (i == LABELS.length - 1) {
            dataLabel.setFont(new Font("Arial", Font.BOLD, 36)); // Smaller font for CI
        } else {
            dataLabel.setFont(new Font("Arial", Font.BOLD, 90)); // Default font
        }
        dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Arial", Font.BOLD, 15));
        labelComponent.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalGlue());
        panel.add(dataLabel);
        panel.add(Box.createVerticalStrut(4));
        panel.add(labelComponent);
        panel.add(Box.createVerticalGlue());

        dataLabels[i] = dataLabel;
        return panel;
    }

    // Method to update the outputs
    public void setTestOutputs(
            double standardError, double tTest, double zScore, 
            double zTest, double cohensD, double[] confInterval
    ) {
        String[] values = {
            format(standardError), format(tTest), format(zScore), format(zTest), format(cohensD), format(confInterval)
        };
        for (int i = 0; i < dataLabels.length; i++) {
            dataLabels[i].setText(values[i]);
        }
    }

    private String format(double val) {
        if (Double.isNaN(val)) return "NaN";
        return String.format("%.4f", val);
    }

    private String format(double[] range) {
    return (range.length == 2 && !Double.isNaN(range[0]) && !Double.isNaN(range[1]))
        ? String.format("[%.4f, %.4f]", range[0], range[1])
        : "[NaN, NaN]";
    }   
}