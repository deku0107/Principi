package ViewProveETest.Prove;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class MyPanel {

    private static final String[] STRINGS = {"Hello, world!", "This is a test.","Hello, world!", "This is a test.","Hello, world!", "This is a test.","Hello, world!", "This is a test.","Hello, world!", "This is a test.","Hello, world!", "This is a test.","Hello, world!", "This is a test."};

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("My Panel");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Create the JPanel.
                JPanel panel = new JPanel(new GridLayout(0, 1));
                for (String string : STRINGS) {
                    JLabel label = new JLabel(string);
                    panel.add(label);
                }

                // Add the JPanel to the JFrame.
                frame.add(panel, BorderLayout.CENTER);

                // Pack the JFrame and make it visible.
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

}