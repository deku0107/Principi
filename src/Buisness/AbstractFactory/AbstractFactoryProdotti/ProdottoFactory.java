package Buisness.AbstractFactory.AbstractFactoryProdotti;

import Dao.Prodotti.ProdottoDao;
import Model.Prodotti.Articolo;

public class ProdottoFactory implements AbstractFactory {
    @Override
    public Articolo crea(String id) {
        ProdottoDao articoloDao=ProdottoDao.getInstance();

        return articoloDao.findArticolo(id);
    }

}
