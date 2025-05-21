package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class SampleSizePanel extends PanelDesign {
    private JLabel labelData;

    public SampleSizePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        add(Box.createVerticalGlue());

        labelData = new JLabel("0");
        labelData.setFont(new Font("Arial", Font.BOLD, 115)); 
        labelData.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelData);

        JLabel labelSampleSize = new JLabel("Sample Size");
        labelSampleSize.setFont(new Font("Arial", Font.BOLD, 15)); 
        labelSampleSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelSampleSize);

        add(Box.createVerticalGlue());

        setPreferredSize(new Dimension(220, 200));
    }

    // Method to update the sample size display
    public void setSampleSize(double[] length) {
        int size = statistics.BasicAlgorithm.calculateCount(length); 
        labelData.setText(String.valueOf(size));
    }
}