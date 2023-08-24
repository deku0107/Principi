package ActionListener.ManagerListener;

import Buisness.Utente.UtenteBusiness;
import ViewProveETest.Home;
import ViewProveETest.Prove.Manager.GestioneClientela.GestioneUtentiPanel;
import ViewProveETest.Prove.Manager.GestioneClientela.invioEmailPanel;
import ViewProveETest.Prove.Manager.GestioneClientela.rigaGestioneUtentiPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerListener implements ActionListener {

    private final JPanel panel;
    private final Home home;

    public ManagerListener(JPanel panel, Home home){
        this.panel=panel;

        this.home = home;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getActionCommand().equalsIgnoreCase("commenti"))
            rispostaCommenti();
        if(e.getActionCommand().equalsIgnoreCase("invio_email"))
            invioEmail();
        if(e.getActionCommand().equalsIgnoreCase("gestione_clientela"))
            gestioneClientela();
        if(e.getActionCommand().equalsIgnoreCase("impostazioni"))
            impostazioni();
        if(e.getActionCommand().equalsIgnoreCase("modifica_stato"))
            modificaStato();
    }




    private void rispostaCommenti(){
        System.out.println("Risposta commenti manager");

    }

    private void invioEmail(){
        System.out.println("Invio email  manager");
        home.getNord().removeAll();
        home.getNord().add(home.getBack());

        home.getCentro().removeAll();
        home.getCentro().add(new invioEmailPanel());

        home.repaint();
        home.validate();

    }

    private void gestioneClientela(){
        System.out.println("Gestione clientela manager");

        home.getNord().removeAll();
        home.getNord().add(home.getBack());

        home.getCentro().removeAll();
        home.getCentro().add(new GestioneUtentiPanel());

        home.repaint();
        home.validate();

    }

    private void impostazioni(){
        System.out.println("Impostazioni manager");

    }

    private void modificaStato(){

        String[] scelta ={"Si", "No"};
        int j= JOptionPane.showConfirmDialog(null,"Sei sicuro di voler confermare la scelta?");

        if(j==0) {
            rigaGestioneUtentiPanel myPanel = (rigaGestioneUtentiPanel) panel;
            String stato = (String) myPanel.getComboBox().getSelectedItem();


            int i = UtenteBusiness.getInstance().updateStato(myPanel.getUtente(), stato);

            if (i > 0) {
                JOptionPane.showMessageDialog(null, "Modifica avvenuta");
            } else {
                JOptionPane.showMessageDialog(null, "Modifica non avvenuta");
            }

        }
    }
}
