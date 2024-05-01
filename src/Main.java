

//Author VS

import javax.swing.*;

//Creates Main frame that displays the Application's "Main Menu's" panel.
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Auto_Racing");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            new SceneSwitcher(frame);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
