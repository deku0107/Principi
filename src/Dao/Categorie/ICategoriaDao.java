package Dao.Categorie;

import Model.Categoria;
import Model.Prodotti.Articolo;

import java.util.ArrayList;

public interface ICategoriaDao {

    int addCategoria(Object categoria);
    Categoria findCategoria(String id);
    ArrayList<Categoria> findCategoriaByName(String nome);
    ArrayList<Categoria> findAll();
    ArrayList<Categoria> findChild(Categoria i);

    int update(Object categoria);
    int remove(String id);

}
