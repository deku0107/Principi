package Buisness.AbstractFactory.AbstractFactoryUtente;

import Dao.Utenti.AmministratoreDao;
import Model.Utenti.Utente;

public class AmministratoreFactory implements AbstractFactoryUtente {

    private final String username;

    public AmministratoreFactory(String username) {
        this.username = username;
    }

    @Override
    public Utente crea() {
        return AmministratoreDao.getInstance().findByUsername(username);
    }
}
