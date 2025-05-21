package appGUI.frame;
import javax.swing.*;
import java.awt.*;
import appGUI.panels.buttonPanel;
import appGUI.panels.contentPanels.cardPanels.AnovaPanel;
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
        rightPanel.add(new AnovaPanel(), "anova"); 

        leftPanel = new buttonPanel(rightPanel, cardLayout);
    }

    public void setupLayout() {
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }
}


