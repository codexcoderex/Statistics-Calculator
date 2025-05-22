import javax.swing.*;

import com.formdev.flatlaf.intellijthemes.FlatGrayIJTheme;

import appGUI.frame.MainFrame;


public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatGrayIJTheme());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            MainFrame.createAndShowGUI();
        });
    }
} 