package Buisness;

import Dao.Prodotti.ArticoloCompositoDao;
import Dao.Prodotti.ProdottoDao;
import Dao.Prodotti.ServizioDao;
import Model.Categoria;
import Model.Prodotti.*;
import Model.Produttore;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ArticoloBuisness {


    private static ArticoloBuisness instance= new ArticoloBuisness();
    public static ArticoloBuisness getInstance(){return  instance;}


    public List<Articolo> getProdotti(){
        return ProdottoDao.getInstance().findArticolo();
    }

    public JComboBox getProdottiBox(){
        JComboBox comboBox= new JComboBox();
        List<Articolo> articoli= getProdotti();
        System.out.println("Dimensione categorie "+articoli.size());

        for(Articolo a: articoli){
            comboBox.addItem(a.getNome());
        }
        return comboBox;
    }

    public List<Articolo> getServizi(){
        return ServizioDao.getInstance().findArticolo();
    }

    public List<Articolo> getProdottiCompositi(){
        return ArticoloCompositoDao.getInstance().findArticolo();
    }

    public int addServizio(String nome, Float prezzo){
        Servizio servizio = new Servizio();
        servizio.setNome(nome);
        servizio.setPrezzo(prezzo);
        return ServizioDao.getInstance().addArticolo(servizio);
    }

    public int addProdotto(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione){
        Prodotto prodotto= new Prodotto();
        prodotto.setNome(nome);
        prodotto.setPrezzo(prezzo);
        prodotto.setCategoria(categoria);
        prodotto.setScaffale(scaffale);
        prodotto.setCorsia(corsia);
        prodotto.setProduttore(produttore);
        prodotto.setDescrizione(descrizione);
        return ProdottoDao.getInstance().addArticolo(prodotto);
    }

    public int addProdottoComposito(String nome, float prezzo, Categoria categoria, int scaffale, int corsia, Produttore produttore, String descrizione, List<Object> composizione, List<Integer> quantita ){

        ArticoloComposito articoloComposito= new ArticoloComposito();
        articoloComposito.setNome(nome);
        articoloComposito.setPrezzo(prezzo);
        articoloComposito.setCategoria(categoria);
        articoloComposito.setScaffale(scaffale);
        articoloComposito.setCorsia(corsia);
        articoloComposito.setProduttore(produttore);
        articoloComposito.setDescrizione(descrizione);
        ArrayList<Composizione> composizioneArrayList= new ArrayList<>();
        int i=0;
        for(Object c:  composizione ){
            Articolo articolo=(Articolo) c;
            Composizione comp = new Composizione();
            comp.setArticolo(articolo);
            comp.setQuantita(quantita.get(i));
            i++;
            composizioneArrayList.add(comp);
        }
        articoloComposito.setComposizione(composizioneArrayList);

        return ArticoloCompositoDao.getInstance().addArticolo(articoloComposito);

    }


}
