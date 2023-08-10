package Buisness.AbstractFactory.AbstractFactoryProdotti;


import Dao.Prodotti.ArticoloCompositoDao;
import Model.Prodotti.Articolo;
import Model.Prodotti.Prodotto;

public class ProdottoCompositoFactory implements AbstractFactory {
    @Override
    public Articolo crea(String id) {
        ArticoloCompositoDao articoloDao=ArticoloCompositoDao.getInstance();

        return articoloDao.findArticoloById(id);
    }


}
