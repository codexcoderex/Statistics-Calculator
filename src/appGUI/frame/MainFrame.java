package appGUI.frame;

import javax.swing.*;

public class MainFrame {
    public static void createAndShowGUI() {
        // Create the frame
        JFrame frame = new JFrame("Barebones Statistics GUI");

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add your MainPanel to the frame
        frame.setContentPane(new MainGUI());
        

        // Set frame size and make it visible
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null); // Center the window, it is relative to the screen
        frame.setVisible(true);
        frame.setResizable(false);
    }

}