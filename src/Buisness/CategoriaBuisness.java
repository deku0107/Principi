package Buisness;

import Dao.Categorie.CategoriaDao;
import Model.Categoria;

import javax.swing.*;
import java.util.List;

public class CategoriaBuisness {

    private static final CategoriaBuisness instance= new CategoriaBuisness();
    public static  CategoriaBuisness getInstance(){return instance;}


    public List<Categoria> getCategorie(){
        return CategoriaDao.getInstance().findAll();

    }

    public JComboBox getCategorieBox(){
        JComboBox comboBox= new JComboBox();
        List<Categoria> categorie= CategoriaBuisness.getInstance().getCategorie();
        System.out.println("Dimensione categorie "+categorie.size());

        for(Categoria c: categorie){
            comboBox.addItem(c.getNome());

        }
        return comboBox;
    }
}
