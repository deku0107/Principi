package Model.Utenti;

import Model.ListaDiAcquisto;

import java.util.ArrayList;

public class UtenteAcquirente extends Utente {

    private enum Stato{ATTIVO, BLOCCATO, ELIMINATO}
    private Stato stato;
    private ArrayList<ListaDiAcquisto> listeDiAcquisto;



    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public ArrayList<ListaDiAcquisto> getListeDiAcquisto() {
        return listeDiAcquisto;
    }

    public void setListeDiAcquisto(ArrayList<ListaDiAcquisto> listeDiAcquisto) {
        this.listeDiAcquisto = listeDiAcquisto;
    }
}
