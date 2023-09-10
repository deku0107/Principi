package Buisness;

import Buisness.Utente.UtenteBusiness;
import Dao.PuntoVendita.OffertaDAO;
import Dao.PuntoVendita.PuntoVenditaDao;
import Model.PuntoVendita;
import Model.Utenti.Manager;
import Model.Utenti.UtenteAcquirente;

import javax.swing.*;
import java.util.List;

public class PuntoVenditaBuisness {

    private static final PuntoVenditaBuisness instance= new PuntoVenditaBuisness();
    public static PuntoVenditaBuisness getInstance(){return instance;}

    public int addPuntoVendita(Object p){
        PuntoVendita puntoVendita= (PuntoVendita) p;


        System.out.println("Nome " + puntoVendita.getNome());
        System.out.println("Email " + puntoVendita.getEmail());
        System.out.println("Telefono " + puntoVendita.getTelefono());
        System.out.println("Indirizzo " + puntoVendita.getIndirizzo());
        System.out.println("Citta " + puntoVendita.getCitta());
        System.out.println("Manager id " + puntoVendita.getManager().getId());

        return PuntoVenditaDao.getInstance().add(puntoVendita);

    }

    public List<PuntoVendita> getPuntivendita(){
        return PuntoVenditaDao.getInstance().findAll();

    }
    public PuntoVendita getPuntovendita(String id){
        return PuntoVenditaDao.getInstance().findById(id);

    }

    public int remove(String id){
        PuntoVendita p=new PuntoVendita();
        p.setId(id);
        return PuntoVenditaDao.getInstance().remove(p);

    }
    public int removeOfferta(String idArticolo, String idPuntoVendita){
        return OffertaDAO.getInstance().remove(idArticolo, idPuntoVendita);

    }

    public JComboBox getPuntoVenditaBox(){
        JComboBox comboBox= new JComboBox();
        List<PuntoVendita> puntoVendita= PuntoVenditaDao.getInstance().findAll();
        System.out.println("Dimensione manager "+puntoVendita.size());

        for(PuntoVendita m: puntoVendita){
            comboBox.addItem(m.getNome());

        }
        return comboBox;

    }

    public PuntoVendita findBySessioneManager() {
        Manager manager = (Manager) SessionManager.getSession().get(SessionManager.LOGGED_MANAGER);
        String id= manager.getId();
       return PuntoVenditaDao.getInstance().findByManager(id);
    }

    public PuntoVendita findBySessioneUser() {
        UtenteAcquirente utenteAcquirente = (UtenteAcquirente) SessionManager.getSession().get(SessionManager.LOGGED_USER);
        String id= utenteAcquirente.getId();
        return PuntoVenditaDao.getInstance().findByUser(id);
    }

    public PuntoVendita getNull(){
        return new PuntoVendita();

    }

    public int update(Object o){
        if (o instanceof PuntoVendita){
            return PuntoVenditaDao.getInstance().update((PuntoVendita) o);
        }
        return -1;
    }

    public int updateQuantita(String idArticolo, String idPuntoVendita, int i) {

    return OffertaDAO.getInstance().updateQuantita(idArticolo, idPuntoVendita, i);
    }

    public int updateQuantita(String idArticolo, String idPuntoVendita) {

        return OffertaDAO.getInstance().updateQuantita(idArticolo, idPuntoVendita);
    }

    public String getNome(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getNome();
        }
        return null;
    }

    public void setNome(Object p, String n){
        if(p instanceof PuntoVendita){
            ((PuntoVendita) p).setNome(n);
        }

    }

    public String getId(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getId();
        }
        return null;
    }

    public void setId(Object p, String n){
        if(p instanceof PuntoVendita){
            ((PuntoVendita) p).setId(n);
        }

    }

    public String getEmail(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getEmail();
        }
        return null;
    }

    public void setEmail(Object p, String n){
        if(p instanceof PuntoVendita){
            ((PuntoVendita) p).setEmail(n);
        }

    }

    public String getTelefono(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getTelefono();
        }
        return null;
    }

    public void setTelefono(Object p, String n){
        if(p instanceof PuntoVendita){
            ((PuntoVendita) p).setTelefono(n);
        }

    }
    public String getIndirizzo(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getIndirizzo();
        }
        return null;
    }

    public void setIndirizzo(Object p, String n){
        if(p instanceof PuntoVendita){
            ((PuntoVendita) p).setIndirizzo(n);
        }

    }

    public String getCitta(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getCitta();
        }
        return null;
    }

    public void setCitta(Object p, String n){
        if(p instanceof PuntoVendita){
            ((PuntoVendita) p).setCitta(n);
        }

    }

    public String getManagerId(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getManager().getId();
        }
        return null;
    }

    public void setManager(Object p, Object m){
        if(p instanceof PuntoVendita && m instanceof Manager){
            ((PuntoVendita) p).setManager((Manager) m);
        }

    }

    public String getManagerName(Object p){
        if(p instanceof PuntoVendita){
            return ((PuntoVendita) p).getManager().getNome();
        }
        return null;
    }


    public int removeQuantita(String idA, String idPV) {

        return OffertaDAO.getInstance().removeQuantita(idA, idPV);
    }
}

