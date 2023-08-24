package ViewProveETest.Decorator;

import ViewProveETest.Home;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuestMenu extends MenuHome{



    public GuestMenu(Home finestra){
        this.finestra = finestra;
        JButton browse = new JButton("Sfoglia catalogo");
        browse.setActionCommand("browse");

        JButton signup= new JButton("Registrati");
        signup.setActionCommand("signup");

        JButton login= new JButton("Login");
        signup.setActionCommand("login");
        // .... agganciate i listener

        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestra.mostraCatalogo();
            }
        });

        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestra.mostraLogin();
            }
        });

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestra.mostraRegistrazione();
            }
        });
        pulsanti.add(browse);
        pulsanti.add(login);
        pulsanti.add(signup);

    }

    public Home getFinestra() {
        return finestra;
    }
}
