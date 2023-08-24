package Dao.PuntoVendita;

import Model.Prodotti.Articolo;

import java.util.List;

public interface  IOffertaDAO {

    public List<Articolo> getOfferta(String id);

    public int updateQuantita(String idArticolo, String idPuntoVendita, int i);

    public int remove(String idArticolo, String idPuntoVendita);

    public int add(String idArticolo, String idPuntoVendita);


}
