package Model;

import Model.Prodotti.Articolo;
import Model.Utenti.UtenteAcquirente;

import java.util.ArrayList;

public class ListaDiAcquisto {

    private ArrayList<Articolo> lista;
    private UtenteAcquirente utenteAcquirente;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

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
