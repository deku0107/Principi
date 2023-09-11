package Model.Prodotti;

public class Composizione {

    private Articolo articolo;
    private int quantita;
    private String id;



    public Articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(Articolo articolo) {
        this.articolo = articolo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public void setId(String idComposizione) {
        id= idComposizione;
    }

    public String getId(){
        return id;

    }
}


