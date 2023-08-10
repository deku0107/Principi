package Model;

public class Commento {

    public enum Punteggio { SCARSO, MEDIOCRE, BUONO, ECCELLENTE }

    private int id;
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

    public void setPunteggio(Punteggio punteggio) {
        this.punteggio = punteggio;
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
}
