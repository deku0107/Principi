package View.Manager.GestioneClientela;

import Buisness.PuntoVenditaBuisness;
import Buisness.SessionManager;
import Buisness.Utente.UtenteBusiness;


import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;


public class GestioneUtentiPanel extends JPanel {

    private final JPanel mainList;

    public GestioneUtentiPanel(){
        mainList= new JPanel(new GridBagLayout());
        setLayout(new BorderLayout());

        List utenti;
        if(SessionManager.getSession().get(SessionManager.LOGGED_ADMIN)!=null){
            utenti= UtenteBusiness.getInstance().getManager();
            popola(utenti);
        }else if(SessionManager.getSession().get(SessionManager.LOGGED_MANAGER)!=null) {
            utenti = UtenteBusiness.getInstance().getUtentiAcquirenti(PuntoVenditaBuisness.getInstance().findBySessioneManager());
            popola(utenti);
        }

    }

    private void popola(List<Object> utenti){

        int i=1;
        for(Object utente:utenti){
            rigaGestioneUtentiPanel rigaGestioneUtentiPanel = new rigaGestioneUtentiPanel();
            rigaGestioneUtentiPanel.updateData(utente);
            JPanel panel = new JPanel();

            panel.add(rigaGestioneUtentiPanel);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = i++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            mainList.add(panel, gbc, 0);
        }

        JScrollPane scrollPane= new JScrollPane(mainList);
        add(scrollPane, BorderLayout.CENTER);
    }
}
