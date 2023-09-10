package Buisness.Utente;

import Buisness.Bridge.PDF.DocumentoListaAcquisto;
import Buisness.Bridge.PDF.PdfBoxAPI;
import Buisness.SessionManager;
import Dao.ListaDiAcquisto.ListaAcquistoDao;
import Model.ListaDiAcquisto;
import Model.Prodotti.Articolo;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListaBuisness {

    private static ListaBuisness instance=new ListaBuisness();
    public static ListaBuisness getInstance(){return instance;}


    public boolean isListaAcquisto(Object o){
        return o instanceof ListaDiAcquisto;

    }

    public List<Articolo> getLista(ListaDiAcquisto o){
        if (isListaAcquisto(o)){
            return o.getLista();
        }
        return null;

    }

    public String getNome(ListaDiAcquisto o){
        if (isListaAcquisto(o)){
            return o.getNome();
        }
        return null;

    }


    public boolean checkNome(String nome){

        ArrayList<String> nomi= ListaAcquistoDao.getInstance().getNomi((UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER));

        boolean check= true;
        for (String s: nomi){
            if (s.equalsIgnoreCase(nome)) {
                check = false;
                break;
            }
        }

        return check;
    }

    public void add(String listNome, List<Object> articoloList) {
        ListaDiAcquisto listaDiAcquisto= new ListaDiAcquisto();
        listaDiAcquisto.setNome(listNome);
        for (Object o : articoloList){
            if (o instanceof  Articolo){
                listaDiAcquisto.setLista((Articolo) o);
            }
        }
        int i= ListaAcquistoDao.getInstance().addLista(listaDiAcquisto, (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER));
        listaDiAcquisto.setId(String.valueOf(i));
        ListaAcquistoDao.getInstance().updateLista(listaDiAcquisto);

        email(listaDiAcquisto);
    }


    public void add(String listNome, List<Object> articoloList, boolean b) {
        ListaDiAcquisto listaDiAcquisto= new ListaDiAcquisto();
        listaDiAcquisto.setNome(listNome);
        for (Object o : articoloList){
            if (o instanceof  Articolo){
                listaDiAcquisto.setLista((Articolo) o);
            }
        }
        int i= ListaAcquistoDao.getInstance().addLista(listaDiAcquisto, (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER));
        listaDiAcquisto.setId(String.valueOf(i));
        ListaAcquistoDao.getInstance().updateLista(listaDiAcquisto);
        if (b){
            ListaAcquistoDao.getInstance().setPagamento(b, listaDiAcquisto);
        }

        email(listaDiAcquisto);
    }

    public JComboBox<String> getComboBox(){
        JComboBox<String> jComboBox= new JComboBox<>();

        ArrayList<ListaDiAcquisto>  listaDiAcquistoArrayList= ListaAcquistoDao.getInstance().getLista((UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER));

        for (ListaDiAcquisto listaDiAcquisto: listaDiAcquistoArrayList){
            jComboBox.addItem(listaDiAcquisto.getNome());
        }

        return jComboBox;
    }


    public void updateLista(List articolo, int i){
        ListaDiAcquisto  listaDiAcquisto= ListaAcquistoDao.getInstance().getLista((UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER)).get(i);
        for (Object o : articolo){
            if (o instanceof  Articolo){
                listaDiAcquisto.setLista((Articolo) o);
            }
        }

        ListaAcquistoDao.getInstance().updateLista(listaDiAcquisto);


    }

    public void pagata(ListaDiAcquisto listaDiAcquisto){

        ListaAcquistoDao.getInstance().setPagamento(true, listaDiAcquisto);
    }


    private void email(ListaDiAcquisto listaDiAcquisto) {
        DocumentoListaAcquisto documentoListaAcquisto= new DocumentoListaAcquisto(listaDiAcquisto, new PdfBoxAPI());

        UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        documentoListaAcquisto.esegui(utenteAcquirente.getEmail());

    }


    public List getSingleArticolo() {
        UtenteAcquirente utenteAcquirente=(UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER) ;
            return ListaAcquistoDao.getInstance().getSingleArticolo(utenteAcquirente);

    }
}
