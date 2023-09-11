package Model.Prodotti;

import Model.Categoria;
import Model.Produttore;

public class Prodotto extends Articolo{

    private Produttore produttore;
    private Categoria categoria;
    private int scaffale;
    private int corsia;
    private int quantita;





    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public Produttore getProduttore() {
        if (produttore==null)
            return new Produttore();
        return produttore;
    }

    public void setProduttore(Produttore produttore) {
        this.produttore = produttore;
    }

    public Categoria getCategoria() {
        if(categoria==null)
            return new Categoria();
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getScaffale() {
        return scaffale;
    }

    public void setScaffale(int scaffale) {
        this.scaffale = scaffale;
    }

    public int getCorsia() {
        return corsia;
    }

    public void setCorsia(int corsia) {
        this.corsia = corsia;
    }
}




