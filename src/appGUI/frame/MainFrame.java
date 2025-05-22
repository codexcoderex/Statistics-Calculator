package appGUI.frame;

import javax.swing.*;

public class MainFrame {
    public static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("SigmaStats");

        // Set the app icon (top left)
        ImageIcon appIcon = new ImageIcon("src/icons/smallICON.png"); // Adjust path if needed
        frame.setIconImage(appIcon.getImage());

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add your MainPanel to the frame
        frame.setContentPane(new MainGUI());
        

        // Set frame size and make it visible
        frame.setSize(1920,900); // Set the size of the window
        frame.setLocationRelativeTo(null); // Center the window, it is relative to the screen
        frame.setVisible(true);
        frame.setResizable(false);
    }

}