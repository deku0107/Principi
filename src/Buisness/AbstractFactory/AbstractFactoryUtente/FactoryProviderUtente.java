package Buisness.AbstractFactory.AbstractFactoryUtente;

public class FactoryProviderUtente {

    public enum FactoryType{CLIENTE, MANAGER, AMMINISTRATORE}

    public static AbstractFactoryUtente getFactory(FactoryType tipo, String username){
        if(tipo==null)
            return null;
        switch (tipo){
            case CLIENTE: return new ClienteFactory(username);
            case MANAGER: return  new ManagerFactory(username);
            case AMMINISTRATORE: return  new AmministratoreFactory(username);
            default: return null;
        }
    }
}
