package Dao.ListaDiAcquisto;


import Model.ListaDiAcquisto;
import Model.Utenti.UtenteAcquirente;

import java.util.ArrayList;

public interface IListaAcquistoDao {

    ArrayList<ListaDiAcquisto> getLista(UtenteAcquirente utenteAcquirente);
    ListaDiAcquisto getLista(UtenteAcquirente utenteAcquirente, String nome);
    int addLista(ListaDiAcquisto listaDiAcquisto, UtenteAcquirente utenteAcquirente);
    int removeLista(ListaDiAcquisto listaDiAcquisto);
    int updateLista(ListaDiAcquisto listaDiAcquisto);
    int setPagamento(boolean b, ListaDiAcquisto listaDiAcquisto);
    ArrayList<String> getNomi(UtenteAcquirente utenteAcquirente);
}
