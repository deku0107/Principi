package Model;

import Model.Utenti.Manager;

public class Risposta {
    private String testo;
    private Commento commento;
    private Manager manager;



    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Commento getCommento() {
        return commento;
    }

    public void setCommento(Commento commento) {
        this.commento = commento;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}


