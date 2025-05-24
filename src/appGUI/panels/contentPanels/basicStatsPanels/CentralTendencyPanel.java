package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class CentralTendencyPanel extends JPanel {
    private JLabel[] labelDataArr = new JLabel[4];

    public CentralTendencyPanel(){
        setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 grid, 10px gaps
        setOpaque(false);
        setPreferredSize(new Dimension(220, 200));

        // Names for each sub-panel
        String[] names = {
            "Mean",
            "Median",
            "Mode",
            "<html><div style='text-align:center;'>Geometric<br>Mean</div></html>"
        };

        for (int i = 0; i < 4; i++) {
            PanelDesign subPanel = new PanelDesign();
            subPanel.setBackground(new Color(0xE3F2FD));
            subPanel.setLayout(new BorderLayout());

            JLabel label = new JLabel(names[i]);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0)); 


            JLabel labelData = new JLabel("0");
            labelData.setFont(new Font("Arial", Font.BOLD, 22));
            labelData.setHorizontalAlignment(SwingConstants.CENTER);
            labelData.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));

            labelDataArr[i] = labelData; // Store reference

            subPanel.add(label, BorderLayout.NORTH);
            subPanel.add(labelData, BorderLayout.CENTER);

            add(subPanel);
        }
    }

    // Call this to update the stats
    public void setCentralTendency(double[] data) {
       if (data == null || data.length == 0) {
        for (JLabel label : labelDataArr) {
            label.setText("N/A");
            label.setFont(new Font("Arial", Font.BOLD, 22)); // Reset to default font size
        }
        return;
        }

        double[] results = {
            statistics.BasicAlgorithm.calculateMean(data),
            statistics.BasicAlgorithm.calculateMedian(data),
            statistics.BasicAlgorithm.calculateMode(data),
            statistics.BasicAlgorithm.calculateGeometricMean(data)
        };

        for (int i = 0; i < results.length; i++) {
            double value = results[i];
            String formatted = String.format("%.2f", value);
            labelDataArr[i].setText(formatted);

            if (Math.abs(value) > 99999) {
                labelDataArr[i].setFont(new Font("Arial", Font.BOLD, 16)); // Shrink font
            } else {
                labelDataArr[i].setFont(new Font("Arial", Font.BOLD, 22)); // Default font
            }
        }

    }
}
