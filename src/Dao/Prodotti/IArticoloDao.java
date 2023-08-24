package Dao.Prodotti;

import Model.Prodotti.Articolo;

import java.util.ArrayList;

public interface IArticoloDao {

    int addArticolo(Object articolo);
    Articolo findArticolo(String id);
    ArrayList<Articolo> findArticoloName(String nome);
    int update(Object articolo);
    int remove(String id);
}
