package Buisness;

import Dao.Produttore.ProduttoreDAO;
import Model.Produttore;

import javax.swing.*;
import java.util.List;

public class ProduttoreBuisness {

    private static final ProduttoreBuisness instance= new ProduttoreBuisness();
    public static ProduttoreBuisness getInstance(){return instance;}


    public List<Produttore> getProduttori(){
        return ProduttoreDAO.getInstance().findAll();

    }

    public JComboBox getproduttoreBox(){
        JComboBox comboBox= new JComboBox();
        List<Produttore> produttori= ProduttoreBuisness.getInstance().getProduttori();
        for(Produttore p:produttori){
            comboBox.addItem(p.getNome());
        }
        return comboBox;
    }

    public int addProduttore(String nome, String sito, String citta, String nazione){
        Produttore produttore= new Produttore();
        produttore.setCitta(citta);
        produttore.setSitoWeb(sito);
        produttore.setNome(nome);
        produttore.setNazione(nazione);
        return ProduttoreDAO.getInstance().add(produttore);

    }

    public int removeProduttore(String id){

        Produttore produttore= new Produttore();
        produttore.setId(id);
        return ProduttoreDAO.getInstance().remove(produttore);

    }
}
