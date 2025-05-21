package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class VariabilityPanel extends JPanel {
    private JLabel[] labelDataArr = new JLabel[4];

    public VariabilityPanel(){
        setLayout(new GridLayout(2, 2, 10, 10)); // 2x2 grid, 10px gaps
        setOpaque(false);
        setPreferredSize(new Dimension(220, 200));

        // Names for each sub-panel
        String[] names = {
            "<html><div style='text-align:center;'>Population<br>Variance</div></html>",
            "<html><div style='text-align:center;'>Sample<br>Variance</div></html>",
            "<html><div style='text-align:center;'>Population<br>SD</div></html>",
            "<html><div style='text-align:center;'>Sample<br>SD</div></html>"
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

    public void setVariability(double[] data) {
        if (data == null || data.length == 0) {
            labelDataArr[0].setText("N/A");
            labelDataArr[1].setText("N/A");
            labelDataArr[2].setText("N/A");
            labelDataArr[3].setText("N/A");
            return;
        }
        labelDataArr[0].setText(String.format("%.2f", statistics.BasicAlgorithm.calculateVariance(data)));
        labelDataArr[1].setText(String.format("%.2f", statistics.BasicAlgorithm.calculateSampleVariance(data)));
        labelDataArr[2].setText(String.format("%.2f", statistics.BasicAlgorithm.calculateStandardDeviation(data)));
        labelDataArr[3].setText(String.format("%.2f", statistics.BasicAlgorithm.calculateSampleStandardDeviation(data)));
    }

}
