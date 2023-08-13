package View.Prove.Amministratore;

import javax.swing.*;

public class DemoCreazioneProduttore {
    public static void main(String[] args) {
    JFrame frame= new JFrame();
    frame.setSize(1200, 400);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    GestioneProduttorePanel gestioneProduttorePanel= new GestioneProduttorePanel();
    frame.add(gestioneProduttorePanel);
    frame.setVisible(true);
}
}
