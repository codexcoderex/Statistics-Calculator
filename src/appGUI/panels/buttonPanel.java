package appGUI.panels;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class buttonPanel extends JPanel {
    public buttonPanel(JPanel contentPanel, CardLayout cardLayout) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(100, 0));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Vertical layout for buttons

        // Load icons (replace with your actual icon file paths)
        ImageIcon rawBasicStatsIcon = scaleIcon("src/icons/basicICON.png", 80, 80);
        ImageIcon paddedBasicStatsIcon = padIconCenter(rawBasicStatsIcon, 100, 100); // centered
        ImageIcon basicStatsRolloverIcon = scaleIcon("src/icons/basicICON.png", 100, 100);
        
        // Create buttons with icons only (no text)
        JButton basicStatsButton = new JButton(paddedBasicStatsIcon);
        basicStatsButton.setRolloverIcon(basicStatsRolloverIcon);
        

        // Set same size for all buttons
        Dimension buttonSize = new Dimension(100, 100);
        basicStatsButton.setMaximumSize(buttonSize);
        basicStatsButton.setMinimumSize(buttonSize);
        basicStatsButton.setPreferredSize(buttonSize);

        // Remove button borders and backgrounds for a flat icon look
        basicStatsButton.setBorderPainted(false);
        basicStatsButton.setFocusPainted(false);
        basicStatsButton.setContentAreaFilled(false);

        // Center buttons horizontally
        basicStatsButton.setHorizontalAlignment(SwingConstants.CENTER);
        basicStatsButton.setVerticalAlignment(SwingConstants.CENTER);
        basicStatsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

       
        add(Box.createVerticalGlue());
        add(basicStatsButton);
        add(Box.createVerticalGlue());

        // Button actions
        basicStatsButton.addActionListener(e -> cardLayout.show(contentPanel, "basicStats"));
       
    }

    // Helper method to scale an icon
    private ImageIcon scaleIcon(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(img);
    }

    // Helper method to pad an icon to center it inside a larger transparent square
    private ImageIcon padIconCenter(ImageIcon icon, int targetWidth, int targetHeight) {
        Image image = icon.getImage();
        BufferedImage padded = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = padded.createGraphics();

        int x = (targetWidth - image.getWidth(null)) / 2;
        int y = (targetHeight - image.getHeight(null)) / 2;
        g2d.drawImage(image, x, y, null);
        g2d.dispose();

        return new ImageIcon(padded);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setOpaque(false);

        int shadowWidth = 6;
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the panel background 
        g2.setColor(Color.WHITE); 
        g2.fillRect(0, 0, panelWidth - shadowWidth, panelHeight);

        // Draw the right-edge shadow
        g2.setPaint(new GradientPaint(
            panelWidth - shadowWidth, 0, new Color(0, 0, 0, 60),
            panelWidth, 0, new Color(0, 0, 0, 0)
        ));
        g2.fillRect(panelWidth - shadowWidth, 0, shadowWidth, panelHeight);

        g2.dispose();
    }
}
