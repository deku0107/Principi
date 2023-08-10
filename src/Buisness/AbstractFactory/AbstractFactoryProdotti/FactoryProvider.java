package Buisness.AbstractFactory.AbstractFactoryProdotti;

public class FactoryProvider {

    public enum FactoryType{PRODOTTO, PRODOTTO_COMPOSITO, SERVIZIO}

    public static AbstractFactory getFactory(FactoryType tipo){
        if(tipo==null)
            return null;
        switch (tipo){
            case PRODOTTO: return new ProdottoFactory();
            case PRODOTTO_COMPOSITO: return  new ProdottoCompositoFactory();
            case SERVIZIO: return  new ServizioFactory();
            default: return null;
        }
    }
}
