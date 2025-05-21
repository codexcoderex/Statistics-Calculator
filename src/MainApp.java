import javax.swing.*;

import appGUI.frame.MainFrame;


public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame.createAndShowGUI();
        });
    }
} 