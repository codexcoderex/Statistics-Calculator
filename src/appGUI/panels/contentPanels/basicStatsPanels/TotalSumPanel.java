package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class TotalSumPanel extends PanelDesign {
    private JLabel labelData;


    public TotalSumPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        add(Box.createVerticalGlue());

        labelData = new JLabel("0"); 
        labelData.setFont(new Font("Arial", Font.BOLD, 50));
        labelData.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelData);

        JLabel labelSampleSize = new JLabel("Total Sum of Dataset");
        labelSampleSize.setFont(new Font("Arial", Font.BOLD, 15));
        labelSampleSize.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(labelSampleSize);

        add(Box.createVerticalGlue());

        setPreferredSize(new Dimension(220, 200));
    }

    // Method to update the total sum display
    public void setTotalSum(double[] data) {
        double sum = statistics.BasicAlgorithm.calculateSum(data);
        labelData.setText(String.format("%.1f", sum));
    }
}