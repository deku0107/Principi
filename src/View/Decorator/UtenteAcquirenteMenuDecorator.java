package View.Decorator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class UtenteAcquirenteMenuDecorator extends CustomMenuDecorator{

    public UtenteAcquirenteMenuDecorator(MenuHome menuHome){this.guestMenu=menuHome;}

    @Override
    public List<JButton> getPulsanti() {
        // ha tutte le funzioni del guest
        pulsanti.addAll(this.guestMenu.getPulsanti());
        pulsanti.remove(pulsanti.size()-1);
        pulsanti.remove(pulsanti.size()-1);



        // + le funzioni del cliente
        JButton liste = new JButton("Le mie liste");
        //action command, listener...

        liste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                finestra.mostraListe();
            }
        });
        pulsanti.add(liste);

        JButton logout= new JButton("Logout");
        //action command
        pulsanti.add(logout);

        JButton settings= new JButton("Impostazioni");
        //action command
        pulsanti.add(settings);



        return pulsanti;
    }
}
