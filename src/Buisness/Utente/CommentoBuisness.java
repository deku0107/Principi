package Buisness.Utente;

import Buisness.PuntoVenditaBuisness;
import Buisness.SessionManager;
import Dao.Prodotti.CommentoDao;
import Dao.Utenti.ManagerDao;
import Dao.Utenti.UtenteAcquirenteDao;
import Model.Commento;
import Model.Prodotti.Articolo;
import Model.PuntoVendita;
import Model.Utenti.Manager;
import Model.Utenti.UtenteAcquirente;

import java.util.ArrayList;

public class CommentoBuisness {

    private static final CommentoBuisness i= new CommentoBuisness();
    public static CommentoBuisness getInstance(){return i;}


    public ArrayList<Commento> getCommenti() {
        Manager m= (Manager) SessionManager.getSession().get(SessionManager.LOGGED_MANAGER);
        if(m!=null){
            return CommentoDao.getInstance().getCommenti(PuntoVenditaBuisness.getInstance().findBySessioneManager());
        }
        return null;
    }

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

    public boolean isCommento(Object o){
        return o instanceof Commento;
    }

    public String getNomeArticoloCommentato(Object o){
        if (isCommento(o)){
            Commento c= (Commento) o;
            return c.getArticolo().getNome();
        }
        return null;
    }

    public String getTesto(Object o){
        if (isCommento(o)){
            Commento c= (Commento) o;
            return c.getOpenText();
        }
        return null;
    }

    public int getValutazione(Object o){
        if (isCommento(o)){
            Commento c= (Commento) o;
            return c.getPunteggioIntero();
        }
        return 0;
    }

    public Object getArticoloCommentato(Object o){
        if (isCommento(o)){
            Commento c= (Commento) o;
            return c.getArticolo();
        }
        return null;

    }

    public String getNomeUtente(Object o){
        if (isCommento(o)){
            Commento c= (Commento) o;
            return UtenteAcquirenteDao.getInstance().findByCommento(String.valueOf(c.getId())).getNome();
        }
        return null;

    }

    public boolean isRisposto(Object o){

        if (isCommento(o)){
            Commento c= (Commento) o;
            return CommentoDao.getInstance().getRisposta(c)!=null;

        }
        return false;
    }

    public String getRisposta(Object o){

        if (isCommento(o)){
            Commento c= (Commento) o;
            return CommentoDao.getInstance().getRisposta(c);

        }
        return null;
    }

    public int risposta(String text, Object o){
        if (isCommento(o)){
            Commento c= (Commento) o;

            Manager manager= (Manager) SessionManager.getSession().get(SessionManager.LOGGED_MANAGER);
            if (manager!=null){
                if (isRisposto(o)){
                    CommentoDao.getInstance().deleteRisposta(CommentoDao.getInstance().getRispostaId(c));
                }
                return CommentoDao.getInstance().risposta(text, c, manager.getId());
            }
        }
        return 0;
    }
}
