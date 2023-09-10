package Buisness.Utente;

import Buisness.SessionManager;
import Dao.Prodotti.CommentoDao;
import Model.Commento;
import Model.Prodotti.Articolo;
import Model.Utenti.UtenteAcquirente;

public class CommentoBuisness {

    private static final CommentoBuisness i= new CommentoBuisness();
    public static CommentoBuisness getInstance(){return i;}


    public int addCommento(String text, int i, Object articolo){
        if (articolo instanceof Articolo a) {
            UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
            if (isCommentatoUtente(a)){
                CommentoDao.getInstance().eliminaCommento(CommentoDao.getInstance().getCommento(a.getId(), utenteAcquirente.getId() ));
            }
            Commento commento = new Commento();
            commento.setOpenText(text);
            commento.setPunteggio(i);
            commento.setArticolo(a);
            return CommentoDao.getInstance().commento(commento);
        }
        return -1;
    }

    public boolean isCommentatoUtente(Object a){
        if (a instanceof Articolo articolo){
            UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
            Commento c= CommentoDao.getInstance().getCommento(articolo.getId(), utenteAcquirente.getId() );
            return c != null;

        }
        return true;
    }

    public String getCommentatoUtente(Object a){
        if (a instanceof Articolo articolo){
            UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
            Commento c= CommentoDao.getInstance().getCommento(articolo.getId(), utenteAcquirente.getId() );
            return c.getOpenText();

        }
        return null;
    }

    public int getCommentatoUtenteValutazione(Object a){
        if (a instanceof Articolo articolo){
            UtenteAcquirente utenteAcquirente= (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
            Commento c= CommentoDao.getInstance().getCommento(articolo.getId(), utenteAcquirente.getId() );
            return c.getPunteggioIntero();

        }
        return 0;
    }
}
