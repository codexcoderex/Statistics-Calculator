package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class MinMaxPanel extends JPanel {
    private JLabel minLabelData;
    private JLabel maxLabelData;

    public MinMaxPanel() {
        setLayout(new GridLayout(2, 1, 10, 10));
        setOpaque(false);
        setPreferredSize(new Dimension(220, 200));

        String[] labels = {"Minimum of Dataset", "Maximum of Dataset"};
        JLabel[] dataLabels = new JLabel[2];

        for (int i = 0; i < 2; i++) {
            PanelDesign panel = new PanelDesign();
            panel.setBackground(new Color(0xE3F2FD));
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            panel.setOpaque(false);

            JLabel dataLabel = new JLabel("0");
            dataLabel.setFont(new Font("Arial", Font.BOLD, 30));
            dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(Box.createVerticalGlue());
            panel.add(dataLabel);
            panel.add(Box.createVerticalStrut(4));
            panel.add(label);
            panel.add(Box.createVerticalGlue());

            add(panel);
            dataLabels[i] = dataLabel;
        }

        minLabelData = dataLabels[0];
        maxLabelData = dataLabels[1];
    }

    public void setMinMax(double[] data) {
        if (data == null || data.length == 0) {
            minLabelData.setText("NaN");
            maxLabelData.setText("NaN");
            return;
        }
        double min = statistics.BasicAlgorithm.calculateMin(data);
        double max = statistics.BasicAlgorithm.calculateMax(data);
        minLabelData.setText(String.format("%.2f", min));
        maxLabelData.setText(String.format("%.2f", max));
    }
}
