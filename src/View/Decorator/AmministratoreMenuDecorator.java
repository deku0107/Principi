package View.Decorator;

import ActionListener.AmministratoreListener.AmministratoreListener;
import ActionListener.LogOutListener;

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



        // + le funzioni dell'amministratore
        JButton manager = new JButton("Gestione Manager");
        manager.setActionCommand("manager");
        manager.addActionListener(new AmministratoreListener(null));
        //action command, listener...
        pulsanti.add(manager);

        JButton catalogo = new JButton("Inserimento prodotti");
        catalogo.setActionCommand("gestione_catalogo");
        catalogo.addActionListener(new AmministratoreListener(null));
        pulsanti.add(catalogo);

        JButton categoria = new JButton("Gestione Categorie");
        categoria.setActionCommand("gestione_categorie");
        categoria.addActionListener(new AmministratoreListener(null));
        pulsanti.add(categoria);

        JButton produttore= new JButton("Gestione Produttori");
        produttore.setActionCommand("gestione_produttori");
        produttore.addActionListener(new AmministratoreListener(null));
        pulsanti.add(produttore);

        JButton puntiVendita= new JButton("Gestione Punti Vendita");
        puntiVendita.setActionCommand("gestione_punti_vendita");
        puntiVendita.addActionListener(new AmministratoreListener(null));
        pulsanti.add(puntiVendita);

        JButton logout= new JButton("Logout");
        logout.setActionCommand("logout");
        logout.addActionListener(new LogOutListener(guestMenu.getFinestra()));
        //action command
        pulsanti.add(logout);

        JButton settings= new JButton("Impostazioni");
        settings.setActionCommand("impostazioni");
        settings.addActionListener(new AmministratoreListener(null));
        //action command
        pulsanti.add(settings);

        return pulsanti;
    }
}
