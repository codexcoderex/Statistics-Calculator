package appGUI.panels.contentPanels.cardPanels;
import java.awt.*;
import javax.swing.*;

import appGUI.panels.contentPanels.testPanels.TestsInputPanel;

public class TestStatsPanel extends JPanel {
    public TestStatsPanel() { 
        setLayout(new BorderLayout());
        setOpaque(false);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setOpaque(false);

        JPanel lrow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lrow1.setOpaque(false);
        lrow1.setMaximumSize(new Dimension(1280,421));
        lrow1.add(new TestsInputPanel());

        inputPanel.add(lrow1);
        add(Box.createVerticalStrut(30)); // Optional: top margin
        add(inputPanel, BorderLayout.CENTER);
    }
}
