package Dao.Utenti;

import Model.Utenti.Utente;

import java.util.ArrayList;

public interface IUtentiDao {

    Utente findById(String id);
    Utente findByUsername(String usr);
    ArrayList<Utente> findAll();
    int add(Object utente, String pwd);
    int update(Object utente);
    boolean controlloCredenziali(String usr, String password);
    boolean controlloCredenziali(String email);
    int removeByUsername(String usr);
}
