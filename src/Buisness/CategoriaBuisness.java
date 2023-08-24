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

    public int getSelectedIndex(Object o){
        if(o instanceof Categoria){
            int i =0;
            for (Categoria c: getCategorie()){
                if(c.getId().equalsIgnoreCase(((Categoria) o).getId())){
                    return i;
                }
                i++;
            }
        }
        return 0;
    }

    public String getNome(Object o){
        if (o instanceof Categoria){
            return ((Categoria) o).getNome();
        }
        return null;
    }

    public String getDescrizione(Object o){
        if (o instanceof Categoria){
            return ((Categoria) o).getDescrizione();
        }
        return null;
    }

    public String getCategoria(Object o){
        if (o instanceof Categoria){
            return ((Categoria) o).getCategoriaPadre().getId();
        }
        return null;
    }

    public String getCategoriaId(Object o){
        if (o instanceof Categoria){
            return ((Categoria) o).getId();
        }
        return null;
    }

    public int add(Object categoria){
        Categoria c= (Categoria) categoria;

        return CategoriaDao.getInstance().addCategoria(c);

    }

    public int update(Object categoria){
        Categoria c=(Categoria) categoria;

        return CategoriaDao.getInstance().update(c);

    }

    public int remove(int i){

        return CategoriaDao.getInstance().remove(String.valueOf(i));

    }

    public void setNome(Object o, String nome){
        if (o instanceof Categoria){
            ((Categoria) o).setNome(nome);
        }

    }

    public void setId(Object o, String id){
        if (o instanceof Categoria){
            ((Categoria) o).setId(id);
        }

    }

    public void setDescrizione(Object o, String d){
        if (o instanceof Categoria){
            ((Categoria) o).setDescrizione(d);
        }

    }

    public void setCategoriaPadre(Object o, Object p){
        if (o instanceof Categoria && p instanceof Categoria){
            ((Categoria) o).setCategoriaPadre((Categoria) p);
        }

    }
}
