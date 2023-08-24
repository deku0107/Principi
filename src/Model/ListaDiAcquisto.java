package Model;

import Model.Prodotti.Articolo;
import Model.Utenti.UtenteAcquirente;

import java.util.ArrayList;

public class ListaDiAcquisto {

    private ArrayList<Articolo> lista;
    private UtenteAcquirente utenteAcquirente;

    public ArrayList<Articolo> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Articolo> lista) {
        this.lista = lista;
    }

    public UtenteAcquirente getUtenteAcquirente() {
        return utenteAcquirente;
    }

    public void setUtenteAcquirente(UtenteAcquirente utenteAcquirente) {
        this.utenteAcquirente = utenteAcquirente;
    }
}
