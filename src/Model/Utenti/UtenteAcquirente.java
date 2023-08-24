package Model.Utenti;

import Model.ListaDiAcquisto;
import Model.PuntoVendita;

import java.util.ArrayList;

public class UtenteAcquirente extends Utente {

    public static enum Stato{ATTIVO, BLOCCATO, ELIMINATO}
    private Stato stato;
    private PuntoVendita puntoVendita;
    private ArrayList<ListaDiAcquisto> listeDiAcquisto;



    public Stato getStato() {
        return stato;
    }
    public String getStatoString(){
        if(stato==Stato.ATTIVO){
            return "attivo";
        }else if(stato==Stato.BLOCCATO){
            return "bloccato";
        }else{return "eliminato";}

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

    public PuntoVendita getPuntoVendita() {
        return puntoVendita;
    }

    public void setPuntoVendita(PuntoVendita puntoVendita) {
        this.puntoVendita = puntoVendita;
    }
}
