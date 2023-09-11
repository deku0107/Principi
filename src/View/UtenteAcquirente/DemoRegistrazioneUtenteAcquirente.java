package View.UtenteAcquirente;

import javax.swing.*;

public class DemoRegistrazioneUtenteAcquirente {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        RegistrazioneUtenteAcquirentePanel panel = new RegistrazioneUtenteAcquirentePanel();
        frame.add(panel);
        frame.setVisible(true);
    }
}
