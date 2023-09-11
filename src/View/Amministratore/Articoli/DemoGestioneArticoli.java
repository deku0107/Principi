package View.Amministratore.Articoli;

import javax.swing.*;

public class DemoGestioneArticoli {
    public static void main(String[] args) {
        JFrame frame= new JFrame();
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        InserimentoArticoliPanel gestioneArticoliPanel= new InserimentoArticoliPanel();
        frame.add(gestioneArticoliPanel);
        frame.setVisible(true);
    }
}
