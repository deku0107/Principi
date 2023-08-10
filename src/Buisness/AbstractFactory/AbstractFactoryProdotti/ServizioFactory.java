package Buisness.AbstractFactory.AbstractFactoryProdotti;


import Dao.Prodotti.ServizioDao;
import Model.Prodotti.Articolo;

public class ServizioFactory implements AbstractFactory {
    @Override
    public Articolo crea(String id) {
        ServizioDao articoloDao=ServizioDao.getInstance();

        return articoloDao.findArticolo(id).get(0);
    }


}
