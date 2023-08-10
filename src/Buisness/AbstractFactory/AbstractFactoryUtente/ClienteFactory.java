package Buisness.AbstractFactory.AbstractFactoryUtente;

import Dao.Utenti.UtenteAcquirenteDao;
import Model.Utenti.Utente;

public class ClienteFactory implements AbstractFactoryUtente {

    private final String username;

    public ClienteFactory(String username) {
        this.username=username;
    }

    @Override
    public Utente crea() {
        return UtenteAcquirenteDao.getInstance().findByUsername(username);
    }
}
