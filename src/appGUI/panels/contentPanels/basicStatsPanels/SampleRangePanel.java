package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class SampleRangePanel extends PanelDesign {
    private JLabel labelData;

    public SampleRangePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        add(Box.createVerticalGlue());

        labelData = new JLabel("0");
        labelData.setFont(new Font("Arial", Font.BOLD, 50)); 
        labelData.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelData);

        JLabel labelSampleSize = new JLabel("Range of Sample");
        labelSampleSize.setFont(new Font("Arial", Font.BOLD, 15)); 
        labelSampleSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelSampleSize);

        add(Box.createVerticalGlue());

        setPreferredSize(new Dimension(220, 200));
    }

    public void setSampleRange(double[] data) {
         if (data == null || data.length == 0) {
            labelData.setText("N/A");
            return;
        }
        double range = statistics.BasicAlgorithm.calculateRange(data);
        labelData.setText(String.format("%.1f", range));
    }
}