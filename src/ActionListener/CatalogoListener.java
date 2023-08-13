package ActionListener;

import Buisness.SessionManager;
import Model.Prodotti.Articolo;
import View.CarrelloPanel;
import View.GridBagLayoutPanel;
import View.Home;
import View.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CatalogoListener implements ActionListener {

    private GridBagLayoutPanel gridBagLayoutPanel;
    List<Articolo> articoloList= (List<Articolo>) SessionManager.getSession().get(SessionManager.CARRELLO);

    public CatalogoListener(GridBagLayoutPanel gridBagLayoutPanel){
        this.gridBagLayoutPanel=gridBagLayoutPanel;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(SessionManager.getSession().get(SessionManager.GUEST)!=null) {
            //caso in cui l'utente non ha fatto il login
            Object[] options = {"login",
                    "signup",
                    "annulla"};
            int n = JOptionPane.showOptionDialog(null,
                    "Per poter fare acquisti deve prima fare il login ",
                    "Attenzione",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[2]);
            System.out.println(n);
            if(n==0){
                new Main().getHome().mostraLogin();
            }else if(n==1){
                new Main().getHome().mostraRegistrazione();
            }

        }else if(e.getActionCommand().equalsIgnoreCase("+lista")){

            articoloList.add(gridBagLayoutPanel.getArticolo());
            System.out.println(articoloList.size());
            CarrelloPanel.getInstance().update();
            }else if(e.getActionCommand().equalsIgnoreCase("ordina")){

            }
        }
    }

