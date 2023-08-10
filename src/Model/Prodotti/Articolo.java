package Model.Prodotti;

import Model.Immagine;
import java.util.ArrayList;

public class Articolo {

    private String nome;
    private float prezzo;
    private String descrizione;
    private ArrayList<Immagine> immagini;
    private String id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }


    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public ArrayList<Immagine> getImmagini() {
        return immagini;
    }

    public void setImmagini(ArrayList<Immagine> immagini) {
        this.immagini = immagini;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
