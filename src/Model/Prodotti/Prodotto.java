package Model.Prodotti;

import Model.Categoria;
import Model.Produttore;

public class Prodotto extends Articolo{

    private Produttore produttore;
    private Categoria categoria;

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
}
