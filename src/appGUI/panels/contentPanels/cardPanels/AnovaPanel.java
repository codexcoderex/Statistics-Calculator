package appGUI.panels.contentPanels.cardPanels;
import appGUI.panels.contentPanels.basicStatsPanels.*;

import javax.swing.*;
import java.awt.*;

public class AnovaPanel extends JPanel {

    GraphPanel graphPanel = new GraphPanel();

    public AnovaPanel() {
        setLayout(new BorderLayout()); 

        JPanel chartAndInput = new JPanel();
        chartAndInput.setLayout(new BoxLayout(chartAndInput, BoxLayout.Y_AXIS));
        chartAndInput.setOpaque(false);

        JPanel lrow1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 40));
        lrow1.setOpaque(false);
        lrow1.setPreferredSize(new Dimension(800,340));
        lrow1.add(graphPanel);

        JPanel lrow2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        lrow2.setOpaque(false);
        lrow2.setPreferredSize(new Dimension(800,120));

        chartAndInput.add(lrow1);
        chartAndInput.add(lrow2);

        add(chartAndInput, BorderLayout.CENTER);
    }
}
