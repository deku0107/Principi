package View.UtenteAcquirente;

import javax.swing.*;

public class DemoModificaUtenteAcquirente {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ModificaUtenteAcquirentePanel panel = new ModificaUtenteAcquirentePanel("184");
        frame.add(panel);
        frame.setVisible(true);
    }
}
