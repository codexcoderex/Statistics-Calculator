package appGUI.frame;
import javax.swing.*;
import java.awt.*;
import appGUI.panels.LeftPanel;
import appGUI.panels.contentPanels.cardPanels.BasicStatsPanel;

public class MainGUI extends JPanel {

    private JPanel leftPanel;
    private JPanel rightPanel; 
    private CardLayout cardLayout;

    public MainGUI() {
        initializeComponents();
        setupLayout();
    }

    public void initializeComponents() {
        cardLayout = new CardLayout();
        rightPanel = new JPanel(cardLayout);

        rightPanel.add(new BasicStatsPanel(), "basicStats");
        rightPanel.add(new JPanel(), "anova"); // Replace with new AnovaPanel() when you have it
        rightPanel.add(new JPanel(), "regression"); // Replace with new RegressionPanel() when you have it

        leftPanel = new LeftPanel(rightPanel, cardLayout);
    }

    public void setupLayout() {
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}


