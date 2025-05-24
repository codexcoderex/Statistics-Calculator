package appGUI.panels.contentPanels.cardPanels;

import appGUI.panels.contentPanels.testPanels.TestsInputPanel;
import appGUI.panels.contentPanels.testPanels.TestsOutputPanel;

import javax.swing.*;
import java.awt.*;

public class TestStatsPanel extends JPanel {
    public TestStatsPanel() {
        setLayout(new BorderLayout());

       
        // Create input and output panels
        TestsOutputPanel outputPanel = new TestsOutputPanel();
        TestsInputPanel inputPanel = new TestsInputPanel(outputPanel);
     

        // Optionally set preferred sizes for better layout control
        inputPanel.setPreferredSize(new Dimension(600, 500));
        outputPanel.setPreferredSize(new Dimension(500, 500));

        // Add to left and right
        add(inputPanel, BorderLayout.WEST);
        add(outputPanel, BorderLayout.CENTER);
    }
}