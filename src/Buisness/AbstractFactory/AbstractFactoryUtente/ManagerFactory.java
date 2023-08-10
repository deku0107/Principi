package Buisness.AbstractFactory.AbstractFactoryUtente;

import Dao.Utenti.ManagerDao;
import Model.Utenti.Utente;

public class ManagerFactory implements AbstractFactoryUtente {

    private final String username;

    public ManagerFactory(String username) {
        this.username = username;
    }

    @Override
    public Utente crea() {
        return ManagerDao.getInstance().findByUsername(username);
    }
}
