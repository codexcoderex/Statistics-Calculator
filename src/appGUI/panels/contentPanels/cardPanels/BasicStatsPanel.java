package appGUI.panels.contentPanels.cardPanels;
import javax.swing.*;
import java.awt.*;

import appGUI.panels.contentPanels.basicStatsPanels.*;

public class BasicStatsPanel extends JPanel {
    public BasicStatsPanel() {

        SampleSizePanel sampleSizePanel = new SampleSizePanel();
        TotalSumPanel totalSumPanel = new TotalSumPanel();
        MinMaxPanel minMaxPanel = new MinMaxPanel();
        SampleRangePanel sampleRangePanel = new SampleRangePanel();
        CentralTendencyPanel centralTendencyPanel = new CentralTendencyPanel();
        VariabilityPanel variabilityPanel = new VariabilityPanel();
        GraphPanel graphPanel = new GraphPanel();

        setLayout(new BorderLayout());

        // The independent panel for chart and input
        JPanel chartAndInput = new JPanel();
        chartAndInput.setLayout(new BoxLayout(chartAndInput, BoxLayout.Y_AXIS));
        chartAndInput.setOpaque(false);
        
        JPanel lrow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lrow1.setOpaque(false);
        lrow1.setMaximumSize(new Dimension(1280,421));

        lrow1.add(graphPanel);

        // InputPanel
        JPanel lrow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lrow2.setOpaque(false);
        lrow2.setMaximumSize(new Dimension(1280,200));

        lrow2.add(new BasicInputPanel(
            sampleSizePanel, 
            totalSumPanel, 
            minMaxPanel, 
            sampleRangePanel, 
            centralTendencyPanel, 
            variabilityPanel, 
            graphPanel
        )); 
        
        // --- Add logo at the top of chartAndInput ---
        ImageIcon logoIcon = new ImageIcon("src/icons/logoICON.png"); // Use your logo path
        Image logoImg = logoIcon.getImage().getScaledInstance(427, 147, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));

        // Wrap the label in a right-aligned panel
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        logoPanel.setMaximumSize(new Dimension(940, 147));
        logoPanel.setOpaque(false);
        logoPanel.add(logoLabel);

        chartAndInput.add(Box.createVerticalStrut(10)); // Top margin
        chartAndInput.add(logoPanel);
        chartAndInput.add(Box.createVerticalStrut(30));

        // Add rows to the chartAndInput panel
        chartAndInput.add(lrow1);
        chartAndInput.add(lrow2);

        // The main content panel of statistics
        JPanel basicStats = new JPanel();
        basicStats.setLayout(new BoxLayout(basicStats, BoxLayout.Y_AXIS));
        basicStats.setOpaque(false);
        basicStats.setPreferredSize(new Dimension(480, 0));
 

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 40));
        row1.setOpaque(false);
        row1.add(sampleSizePanel);
        row1.add(totalSumPanel);
        row1.setMaximumSize(new Dimension(530, 240));

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        row2.setOpaque(false);
        row2.add(minMaxPanel);
        row2.add(sampleRangePanel);
        row2.setMaximumSize(new Dimension(530, 200));

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        row3.setOpaque(false);
        row3.add(centralTendencyPanel);
        row3.add(variabilityPanel);
        row3.setMaximumSize(new Dimension(530, 200));

        basicStats.add(Box.createVerticalStrut(147));
        basicStats.add(row1);
        basicStats.add(Box.createVerticalStrut(10));
        basicStats.add(row2);
        basicStats.add(Box.createVerticalStrut(10));
        basicStats.add(row3);

        // Add the independent panel and the main content to the layout
        add(chartAndInput, BorderLayout.CENTER);
        add(basicStats, BorderLayout.EAST);
    }
}
