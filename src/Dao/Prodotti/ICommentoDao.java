package Dao.Prodotti;

import Model.Commento;
import Model.PuntoVendita;
import Model.Utenti.UtenteAcquirente;

import java.util.ArrayList;

public interface ICommentoDao {

    ArrayList<Commento> getCommenti(UtenteAcquirente utenteAcquirente);
    ArrayList<Commento> getCommenti(PuntoVendita puntoVendita);
    int commento(Object commento);
    void modificaCommento(Object commento);
    int eliminaCommento(Object commento);
    int risposta(String risposta, Commento commento, String idM);
    String getRisposta(Commento commento);
    String getRispostaId(Commento commento);
    int deleteRisposta(String id);
}
