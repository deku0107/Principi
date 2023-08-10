package View.Decorator;

import javax.swing.*;
import java.util.List;

public class AmministratoreMenuDecorator extends CustomMenuDecorator{

    public AmministratoreMenuDecorator(MenuHome menuHome){this.guestMenu=menuHome;}


    @Override
    public List<JButton> getPulsanti() {
        // ha tutte le funzioni del guest
        pulsanti.addAll(this.guestMenu.getPulsanti());
        pulsanti.remove(pulsanti.size()-1);
        pulsanti.remove(pulsanti.size()-1);



        // + le funzioni del cliente
        JButton manager = new JButton("Gestione Manager");
        //action command, listener...
        pulsanti.add(manager);

        JButton logout= new JButton("Logout");
        //action command
        pulsanti.add(logout);

        JButton settings= new JButton("Impostazioni");
        //action command
        pulsanti.add(settings);

        return pulsanti;
    }
}
