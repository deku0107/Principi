package ViewProveETest.Prove.Amministratore.Articoli;

import Buisness.ArticoloBuisness;

import javax.swing.*;

public class DemoModificaArticoli {
    public static void main(String[] args) {
        JFrame frame= new JFrame();
        frame.setSize(1200, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ModificaProdottoPanel gestioneArticoliPanel= new ModificaProdottoPanel(ArticoloBuisness.getInstance().getProdottiCompositi().get(1));
        frame.add(gestioneArticoliPanel);
        frame.setVisible(true);
    }
}
