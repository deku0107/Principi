package View.Decorator;

import javax.swing.*;
import java.util.List;

public class ManagerMenuDecorator extends CustomMenuDecorator{

    public ManagerMenuDecorator(MenuHome menuHome){this.guestMenu=menuHome;}


    @Override
    public List<JButton> getPulsanti() {
        // ha tutte le funzioni del guest
        pulsanti.addAll(this.guestMenu.getPulsanti());
        pulsanti.remove(pulsanti.size()-1);
        pulsanti.remove(pulsanti.size()-1);

        // + le funzioni del manager
        JButton magazzino = new JButton("Gestione Magazzino");
        //action command, listener...
        pulsanti.add(magazzino);

        JButton commenti = new JButton("Visualizza commenti");
        //action command, listener...
        pulsanti.add(commenti);

        JButton invioEmail = new JButton("invio E-Mail");
        //action command, listener...
        pulsanti.add(invioEmail);

        JButton logout= new JButton("Logout");
        //action command
        pulsanti.add(logout);

        JButton settings= new JButton("Impostazioni");
        //action command
        pulsanti.add(settings);



        return pulsanti;
    }
}
