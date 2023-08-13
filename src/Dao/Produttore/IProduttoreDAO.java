package Dao.Produttore;

import Model.Produttore;

import java.util.ArrayList;

public interface IProduttoreDAO {

    Produttore find(String id);
    ArrayList<Produttore> findAll();
    int add(Produttore produttore);
    int remove(Produttore produttore);
    int update(Produttore produttore);
}
