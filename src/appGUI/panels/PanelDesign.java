package appGUI.panels;

import javax.swing.*;
import java.awt.*;

public class PanelDesign extends JPanel {
    public PanelDesign() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int arc = 30;
        int shadowOffset = 2;
        int shadowAlpha = 55; // 0-255, lower is more transparent

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw drop shadow 
        g2.setColor(new Color(0, 0, 0, shadowAlpha));
        g2.fillRoundRect(
            shadowOffset, shadowOffset,
            getWidth() - shadowOffset, getHeight() - shadowOffset,
            arc, arc
        );

        // Draw white panel background (full size)
        g2.setColor(Color.white);
        g2.fillRoundRect(0, 0, getWidth() - shadowOffset, getHeight() - shadowOffset, arc, arc);

        // Draw border (optional)
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRoundRect(0, 0, getWidth() - shadowOffset - 1, getHeight() - shadowOffset - 1, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
}