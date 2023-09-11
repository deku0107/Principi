package View.UtenteAcquirente;

import Buisness.ArticoloBuisness;
import Buisness.SessionManager;
import Buisness.Utente.UtenteBusiness;
import Model.Utenti.UtenteAcquirente;

public class DemoCatalogoUtenteAcquirente {
    public static void main(String[] args) {
        UtenteAcquirente utenteAcquirente= UtenteBusiness.getInstance().getUtenteAcquirente("1");
        SessionManager.getSession().put(SessionManager.LOGGED_USER, utenteAcquirente);
        System.out.println("quantita articoli " + ArticoloBuisness.getInstance().getCatalogo().size());
    }
}
