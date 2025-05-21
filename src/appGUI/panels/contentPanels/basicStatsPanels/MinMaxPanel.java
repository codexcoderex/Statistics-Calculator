package appGUI.panels.contentPanels.basicStatsPanels;
import appGUI.panels.PanelDesign;

import javax.swing.*;
import java.awt.*;

public class MinMaxPanel extends JPanel {
    private JLabel minLabelData;
    private JLabel maxLabelData;

    public MinMaxPanel() {
        setLayout(new GridLayout(2, 0, 10, 10));
        setOpaque(false);
        setPreferredSize(new Dimension(220, 200));

        String[] labels = {"Minimum of Dataset", "Maximum of Dataset"};
        JLabel[] dataLabels = new JLabel[2];

        for (int i = 0; i < 2; i++) {
            PanelDesign panel = new PanelDesign();
            panel.setBackground(new Color(0xE3F2FD));
            panel.setLayout(new GridBagLayout());

            JLabel dataLabel = new JLabel("0");
            dataLabel.setFont(new Font("Arial", Font.BOLD, 30));
            dataLabel.setHorizontalAlignment(SwingConstants.CENTER);

            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.BOLD, 15));
            label.setHorizontalAlignment(SwingConstants.CENTER);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            panel.add(dataLabel, gbc);

            gbc.gridy = 1;
            panel.add(label, gbc);

            add(panel);
            dataLabels[i] = dataLabel;
        }

        minLabelData = dataLabels[0];
        maxLabelData = dataLabels[1];
    }

    public void setMinMax(double[] data) {
        if (data == null || data.length == 0) {
            minLabelData.setText("N/A");
            maxLabelData.setText("N/A");
            return;
        }
        double min = statistics.BasicAlgorithm.calculateMin(data);
        double max = statistics.BasicAlgorithm.calculateMax(data);
        minLabelData.setText(String.format("%.2f", min));
        maxLabelData.setText(String.format("%.2f", max));
    }
}
