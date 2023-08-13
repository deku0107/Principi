package View.Prove.Amministratore;

import javax.swing.*;

public class DemoCancellazioneProduttore {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CancellazioneProduttorePanel panel = new CancellazioneProduttorePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
