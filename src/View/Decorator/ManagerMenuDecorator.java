package View.Decorator;

import ActionListener.LogOutListener;
import ActionListener.ManagerListener.ManagerListener;

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

        JButton commenti = new JButton("Visualizza commenti");
        commenti.setActionCommand("commenti");
        commenti.addActionListener(new ManagerListener(null, guestMenu.getFinestra()));
        //action command, listener...
        pulsanti.add(commenti);

        JButton invioEmail = new JButton("invio E-Mail");
        invioEmail.setActionCommand("invio_email");
        invioEmail.addActionListener(new ManagerListener(null, guestMenu.getFinestra()));
        //action command, listener...
        pulsanti.add(invioEmail);

        JButton gestioneClientela = new JButton("Gestione Clientela");
        gestioneClientela.setActionCommand("gestione_clientela");
        gestioneClientela.addActionListener(new ManagerListener(null, guestMenu.getFinestra()));
        //action command, listener...
        pulsanti.add(gestioneClientela);

        JButton logout= new JButton("Logout");
        logout.setActionCommand("logout");
        logout.addActionListener(new LogOutListener(guestMenu.getFinestra()));
        //action command
        pulsanti.add(logout);

        JButton settings= new JButton("Impostazioni");
        settings.setActionCommand("impostazioni");
        settings.addActionListener(new ManagerListener(null, guestMenu.getFinestra()));
        //action command
        pulsanti.add(settings);



        return pulsanti;
    }
}
