package Buisness.AbstractFactory.AbstractFactoryProdotti;

import Model.Prodotti.Articolo;

public interface AbstractFactory {

    Articolo crea(String id);  //prodotti o servizi


}
