package View.Decorator;

import javax.swing.*;
import java.util.List;
import ActionListener.*;
import ActionListener.UtenteAcquirenteListener.UtenteAcquirenteListener;

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
        liste.setActionCommand("liste");
        //action command, listener...

        liste.addActionListener(new UtenteAcquirenteListener(null, guestMenu.getFinestra()));
        pulsanti.add(liste);

        JButton comment= new JButton("Lascia un commento");
        comment.setActionCommand("commento");
        comment.addActionListener(new UtenteAcquirenteListener(null, guestMenu.getFinestra()));
        pulsanti.add(comment);



        JButton logout= new JButton("Logout");
        logout.addActionListener(new LogOutListener(guestMenu.getFinestra()));
        //action command
        pulsanti.add(logout);

        JButton settings= new JButton("Impostazioni");
        settings.setActionCommand("impostazioni");
        settings.addActionListener(new UtenteAcquirenteListener(null, guestMenu.getFinestra()));
        //action command
        pulsanti.add(settings);



        return pulsanti;
    }
}
