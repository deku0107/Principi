package Dao.PuntoVendita;

import Model.PuntoVendita;
import Model.Utenti.Manager;

import java.util.ArrayList;

public interface IPuntoVenditaDAO {

    PuntoVendita findById(String id);
    ArrayList<PuntoVendita> findByName(String name);
    ArrayList<PuntoVendita> findAll();
    int add (PuntoVendita puntoVendita);
    int update (PuntoVendita puntoVendita);
    int remove(PuntoVendita puntoVendita);
    int UpdateManager(PuntoVendita puntoVendita, Manager m);
}
