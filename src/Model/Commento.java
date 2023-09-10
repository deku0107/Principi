package Model;

import Model.Prodotti.Articolo;

public class Commento {

    public enum Punteggio { SCARSO, MEDIOCRE, BUONO, ECCELLENTE, PERFETTO }

    private int id;
    private Articolo articolo;
    private Punteggio punteggio;
    private String openText;
    private boolean letto;
    //private boolean risposto;
    private Risposta risposta;
    private Data data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Punteggio getPunteggio() {
        return punteggio;
    }
    public int  getPunteggioIntero() {

        if (this.punteggio==Punteggio.SCARSO){
            return 1;
        }
        if (this.punteggio==Punteggio.MEDIOCRE){
            return 2;
        }
        if (this.punteggio==Punteggio.BUONO){
            return 3;
        }
        if (this.punteggio==Punteggio.ECCELLENTE){
            return 4;
        }
        if (this.punteggio==Punteggio.PERFETTO){
            return 5;
        }
        return -1;
    }


    public void setPunteggio(Punteggio punteggio) {
        this.punteggio = punteggio;
    }

    public void setPunteggio(int i) {
        if (i==1){
            this.punteggio=Punteggio.SCARSO;
        }
        if (i==2){
            this.punteggio=Punteggio.MEDIOCRE;
        }
        if (i==3){
            this.punteggio=Punteggio.BUONO;
        }
        if (i==4){
            this.punteggio=Punteggio.ECCELLENTE;
        }
        if (i==5){
            this.punteggio=Punteggio.PERFETTO;
        }

    }

    public String getOpenText() {
        return openText;
    }

    public void setOpenText(String openText) {
        this.openText = openText;
    }

    public boolean isLetto() {
        return letto;
    }

    public void setLetto(boolean letto) {
        this.letto = letto;
    }

    public Risposta getRisposta() {
        return risposta;
    }

    public void setRisposta(Risposta risposta) {
        this.risposta = risposta;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(Articolo articolo) {
        this.articolo = articolo;
    }
}
