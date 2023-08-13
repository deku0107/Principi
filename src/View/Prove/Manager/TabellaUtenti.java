package View.Prove.Manager;

import Dao.Utenti.UtenteAcquirenteDao;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TabellaUtenti extends JPanel {
     public TabellaUtenti(){
         setLayout(new BorderLayout());

         List<RigaUtente> righe= new ArrayList<>();
         List<Utente> utenti= UtenteAcquirenteDao.getInstance().findAll();
         for(Utente utente:utenti){
             UtenteAcquirente u = (UtenteAcquirente) utente;
             RigaUtente rigaUtente= new RigaUtente();
             rigaUtente.setIdUtente(Integer.parseInt(u.getId()));
             rigaUtente.setNome(u.getNome());
             rigaUtente.setCognome(u.getCognome());
             rigaUtente.setUsername(u.getUsername());
             rigaUtente.setEmail(u.getEmail());
             rigaUtente.setTelefono(u.getTelefono());
             rigaUtente.setStato( u.getStato());
             righe.add(rigaUtente);
         }


         UtentiTableModel utentiTableModel= new UtentiTableModel(righe);
         JTable table= new JTable(utentiTableModel);
         JScrollPane jScrollPane= new JScrollPane(table);
         add(jScrollPane, BorderLayout.CENTER);

     }
}
