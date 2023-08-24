package Buisness.Utente;

import Buisness.AbstractFactory.AbstractFactoryUtente.AbstractFactoryUtente;
import Buisness.AbstractFactory.AbstractFactoryUtente.FactoryProviderUtente;
import Buisness.PuntoVenditaBuisness;
import Buisness.SessionManager;
import Buisness.UtilityBuisness;
import Dao.PuntoVendita.PuntoVenditaDao;
import Dao.Utenti.AmministratoreDao;
import Dao.Utenti.ManagerDao;
import Dao.Utenti.UtenteAcquirenteDao;
import Model.Data;
import Model.PuntoVendita;
import Model.Utenti.Amministratore;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;

import javax.swing.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class UtenteBusiness {


        private static final UtenteBusiness instance = new UtenteBusiness();

        public static UtenteBusiness getInstance(){
            return instance;
        }
        FactoryProviderUtente.FactoryType tipo;
        String sessione;

        public LoginResult login(String username, String password, FactoryProviderUtente.FactoryType tipo){

            System.out.println("Il tipo " + tipo);


            switch (tipo){
                case AMMINISTRATORE -> {
                    return loginAmministratore(username, password);
                }
                case MANAGER -> {
                    return loginManager(username, password);
                }
                default -> {
                    return loginUtente(username, password);
                }
            }



        }

        private LoginResult loginUtente(String username, String password) {
        LoginResult loginResult= new LoginResult();

        UtenteAcquirenteDao utenteDao= UtenteAcquirenteDao.getInstance();

        //controllare se l'utente esiste

        Utente esistenzaUtente= utenteDao.findByUsername(username);
        if(esistenzaUtente==null){
            loginResult.setResult(LoginResult.Result.UTENTE_NON_ESISTE);
            loginResult.setMessaggio("L'utente non esiste");
            return loginResult;
        }

        //controllare se username e password sono ok

        boolean credenzialiOk=utenteDao.controlloCredenziali(username, password);
        if(!credenzialiOk){
            loginResult.setResult(LoginResult.Result.PASSWORD_ERRATA);
            loginResult.setMessaggio("La password digitata è errata");
            return loginResult;
        }
        //controllare se l'utente è bloccato
        boolean utenteBloccato = utenteDao.utenteBloccato(username);
        if(utenteBloccato){
            loginResult.setResult(LoginResult.Result.UTENTE_BLOCCATO);
            loginResult.setMessaggio("L'utente è bloccato'");
            return loginResult;
        }


            //crea cliente

            tipo = FactoryProviderUtente.FactoryType.CLIENTE;
            sessione= SessionManager.LOGGED_USER;


        AbstractFactoryUtente abstractFactoryUtente =  FactoryProviderUtente.getFactory(tipo, username);
        Utente u = abstractFactoryUtente.crea();
        SessionManager.getSession().remove(SessionManager.GUEST);
        SessionManager.getSession().put(SessionManager.LOGGED_USER, u);

        loginResult.setResult(LoginResult.Result.LOGIN_OK);
        loginResult.setMessaggio("Benvenuto " + u.getNome());

        return loginResult;
    }

        private LoginResult loginManager(String username, String password) {
        LoginResult loginResult= new LoginResult();

        ManagerDao utenteDao= ManagerDao.getInstance();

        //controllare se l'utente esiste

        Utente esistenzaUtente= utenteDao.findByUsername(username);
        if(esistenzaUtente==null){
            loginResult.setResult(LoginResult.Result.UTENTE_NON_ESISTE);
            loginResult.setMessaggio("L'utente non esiste");
            return loginResult;
        }

        //controllare se username e password sono ok

        boolean credenzialiOk=utenteDao.controlloCredenziali(username, password);
        if(!credenzialiOk){
            loginResult.setResult(LoginResult.Result.PASSWORD_ERRATA);
            loginResult.setMessaggio("La password digitata è errata");
            return loginResult;
        }
        //controllare se l'utente è bloccato
      /*
      boolean utenteBloccato = utenteDao.utenteBloccato(username);
        if(utenteBloccato){
            loginResult.setResult(LoginResult.Result.UTENTE_BLOCCATO);
            loginResult.setMessaggio("L'utente è bloccato'");
            return loginResult;
        }

       */


        //crea manager

        tipo = FactoryProviderUtente.FactoryType.MANAGER;
        sessione= SessionManager.LOGGED_MANAGER;

        AbstractFactoryUtente abstractFactoryUtente =  FactoryProviderUtente.getFactory(tipo, username);
        Utente u = abstractFactoryUtente.crea();
        SessionManager.getSession().remove(SessionManager.GUEST);
        SessionManager.getSession().put(SessionManager.LOGGED_MANAGER, u);

        loginResult.setResult(LoginResult.Result.LOGIN_OK);
        loginResult.setMessaggio("Benvenuto " + u.getNome());

        return loginResult;
    }

        private LoginResult loginAmministratore(String username, String password) {

        LoginResult loginResult= new LoginResult();

        AmministratoreDao utenteDao= AmministratoreDao.getInstance();

        //controllare se l'utente esiste

        Utente esistenzaUtente= utenteDao.findByUsername(username);
        if(esistenzaUtente==null){
            loginResult.setResult(LoginResult.Result.UTENTE_NON_ESISTE);
            loginResult.setMessaggio("L'utente non esiste");
            return loginResult;
        }

        //controllare se username e password sono ok

        boolean credenzialiOk=utenteDao.controlloCredenziali(username, password);
        if(!credenzialiOk){
            loginResult.setResult(LoginResult.Result.PASSWORD_ERRATA);
            loginResult.setMessaggio("La password digitata è errata");
            return loginResult;
        }


        //crea amministatore

        tipo = FactoryProviderUtente.FactoryType.AMMINISTRATORE;
        sessione= SessionManager.LOGGED_ADMIN;

        AbstractFactoryUtente abstractFactoryUtente =  FactoryProviderUtente.getFactory(tipo, username);
        Utente u = abstractFactoryUtente.crea();
        SessionManager.getSession().remove(SessionManager.GUEST);
        SessionManager.getSession().put(SessionManager.LOGGED_ADMIN, u);

        loginResult.setResult(LoginResult.Result.LOGIN_OK);
        loginResult.setMessaggio("Benvenuto " + u.getNome());

        return loginResult;
    }

        public RegisterResult registrazione(UtenteAcquirente c, String pwd){

            RegisterResult registerResult= new RegisterResult();

            UtenteAcquirenteDao utenteDao= UtenteAcquirenteDao.getInstance();

            //controllare se l'utente esiste

            boolean esistenzaUtente= utenteDao.esistenzaUtente(c.getUsername());
            if(esistenzaUtente){
                registerResult.setResult(RegisterResult.Result.NOME_UTENTE_ESISTENTE);
                registerResult.setMessaggio("Username non disponibile");
                return registerResult;
            }

            esistenzaUtente= utenteDao.controlloCredenziali(c.getEmail());
            if(esistenzaUtente){
                registerResult.setResult(RegisterResult.Result.MAIL_GIA_REGISTRATA);
                registerResult.setMessaggio("Email già in uso");
                return registerResult;
            }


            int i=utenteDao.add(c, pwd);
            if(i==1){
                registerResult.setResult(RegisterResult.Result.REGISTRAZIONE_OK);
                registerResult.setMessaggio("Registrazione effettuata");
                loginUtente(c.getUsername(), pwd);


            }else{
                registerResult.setResult(RegisterResult.Result.REGISTRAZIONE_FALLITA);
                registerResult.setMessaggio("Registrazione non avvenuta");
            }
            return registerResult;

        }

        public int resetPassword(String email, String psw){

            return UtenteAcquirenteDao.getInstance().update(UtenteAcquirenteDao.getInstance().findByEmail(email), psw);

        }


        public FactoryProviderUtente.FactoryType tipoUtenteId(String id){
            Object u=UtenteAcquirenteDao.getInstance().findById(id);
            if ((u != null)) {
                System.out.println("L'oggetto appartiene alla classe Utente Acquirente");
                return FactoryProviderUtente.FactoryType.CLIENTE;
            }
            u=ManagerDao.getInstance().findById(id);
            if ((u != null)) {
                System.out.println("L'oggetto appartiene alla classe Manager");
                return FactoryProviderUtente.FactoryType.MANAGER;
            }

            u= AmministratoreDao.getInstance().findById(id);
            if ((u instanceof Amministratore)) {
                System.out.println("L'oggetto appartiene alla classe Amministratore");
                return FactoryProviderUtente.FactoryType.AMMINISTRATORE;
            }
                return null;
            }

       public FactoryProviderUtente.FactoryType tipoUtente(String usr){
        Object u=UtenteAcquirenteDao.getInstance().findByUsername(usr);
        if ((u != null)) {
            System.out.println("L'oggetto appartiene alla classe Utente Acquirente");
            return FactoryProviderUtente.FactoryType.CLIENTE;
        }
        u=ManagerDao.getInstance().findByUsername(usr);
        if ((u != null)) {
            System.out.println("L'oggetto appartiene alla classe Manager");
            return FactoryProviderUtente.FactoryType.MANAGER;
        }

        u= AmministratoreDao.getInstance().findByUsername(usr);
        if ((u instanceof Amministratore)) {
            System.out.println("L'oggetto appartiene alla classe Amministratore");
            return FactoryProviderUtente.FactoryType.AMMINISTRATORE;
        }

           System.out.println("sto ritornando null");
        return null;
    }

       public List<Utente> getUtentiAcquirenti(){
            return UtenteAcquirenteDao.getInstance().findAll();
       }

    public List<Utente> getUtentiAcquirenti(PuntoVendita puntoVendita){
        return UtenteAcquirenteDao.getInstance().findAll(puntoVendita);
    }

       public UtenteAcquirente getUtenteAcquirente(String id){
            return UtenteAcquirenteDao.getInstance().findById(id);

       }

       public List<Utente> getManager(){
            return ManagerDao.getInstance().findAll();

       }

       public  Manager getManager(String id){
            return ManagerDao.getInstance().findById(id);

       }

       public JComboBox getManagerBox(){
           JComboBox comboBox= new JComboBox();
           List<Utente> manager= ManagerDao.getInstance().findAll();
           System.out.println("Dimensione manager "+manager.size());

           for(Utente m: manager){
               comboBox.addItem(m.getNome());

           }
           return comboBox;

       }

       public int addManger(List<Object> a){
            Manager manager= new Manager();
            manager.setNome((String) a.get(0));
            manager.setCognome((String) a.get(1));
            //a.get(2) rappresenta il punto vendita, l'id del manager devo metterlo nell punto vendita
            manager.setDataDiNascita(new Data((String) a.get(3)));
            manager.setStipendio(Float.parseFloat((String) a.get(4)));
            manager.setInizioIncarico(new Data((String) a.get(5)));
            manager.setFineIncarico(new Data((String) a.get(6)));
            manager.setUsername((String) a.get(7));
            manager.setEmail((String) a.get(8));
            manager.setTelefono((String) a.get(9));
            manager.setIndirizzo((String) a.get(11));
            manager.setCitta((String) a.get(12));
            int i=ManagerDao.getInstance().add(manager, (String) a.get(10));
            if(i<0)
                return i;
            int id= UtilityBuisness.getInstance().getMax("manager", "idmanager");
            if(id<0)
                return id;

            manager.setId(String.valueOf(id));

            String idPV= PuntoVenditaBuisness.getInstance().getPuntivendita().get((Integer) a.get(2)).getId();//id del punto vendita scelto
            return PuntoVenditaDao.getInstance().UpdateManager(PuntoVenditaDao.getInstance().findById(idPV),manager );

       }

       public int addUtenteAcuirente(List<Object> a){

           UtenteAcquirente utente= new UtenteAcquirente();
           utente.setNome((String) a.get(0));
           utente.setCognome((String) a.get(1));
           String idPV= PuntoVenditaBuisness.getInstance().getPuntivendita().get((Integer) a.get(2)).getId();//id del punto vendita scelto
           System.out.println("Id punto vendita " + idPV);
           utente.setPuntoVendita(PuntoVenditaDao.getInstance().findById(idPV));
           System.out.println("Id punto vendita risultante "+utente.getPuntoVendita().getId());
           utente.setDataDiNascita(new Data((String) a.get(3)));
           utente.setUsername((String) a.get(7));
           utente.setEmail((String) a.get(8));
           utente.setTelefono((String) a.get(9));
           utente.setIndirizzo((String) a.get(11));
           utente.setCitta((String) a.get(12));

           return UtenteAcquirenteDao.getInstance().add(utente, (String) a.get(10));
       }

       public int updateManager(List<Object> a){

           Manager manager= new Manager();
           manager.setNome((String) a.get(0));
           manager.setCognome((String) a.get(1));
           //a.get(2) rappresenta il punto vendita, l'id del manager devo metterlo nell punto vendita
           manager.setDataDiNascita(new Data((String) a.get(3)));
           manager.setStipendio(Float.parseFloat((String) a.get(4)));
           manager.setInizioIncarico(new Data((String) a.get(5)));
           manager.setFineIncarico(new Data((String) a.get(6)));
           manager.setUsername((String) a.get(7));
           manager.setEmail((String) a.get(8));
           manager.setTelefono((String) a.get(9));
           manager.setIndirizzo((String) a.get(11));
           manager.setCitta((String) a.get(12));
           manager.setId((String) a.get(13));

           return ManagerDao.getInstance().update(manager);
       }

       public int removeManager(String id){

           return ManagerDao.getInstance().remove(id);
       }
       public int updataUtenteAcquirente(List<Object> a){
            UtenteAcquirente utente= new UtenteAcquirente();


           utente.setNome((String) a.get(0));
           utente.setCognome((String) a.get(1));
           String idPV= PuntoVenditaBuisness.getInstance().getPuntivendita().get((Integer) a.get(2)).getId();//id del punto vendita scelto
           System.out.println("Id punto vendita " + idPV);
           utente.setPuntoVendita(PuntoVenditaDao.getInstance().findById(idPV));
           System.out.println("Id punto vendita risultante "+utente.getPuntoVendita().getId());
           utente.setDataDiNascita(new Data((String) a.get(3)));
           utente.setUsername((String) a.get(7));
           utente.setEmail((String) a.get(8));
           utente.setTelefono((String) a.get(9));
           utente.setIndirizzo((String) a.get(11));
           utente.setCitta((String) a.get(12));
           utente.setId((String) a.get(13));
           System.out.println("Id utente " + utente.getId());

           return UtenteAcquirenteDao.getInstance().update(utente, (String) a.get(10));
       }

       public int updateStato(Object utente, String stato){

           System.out.println(stato);
           UtenteAcquirente u= (UtenteAcquirente) utente;
           if (stato.equalsIgnoreCase("eliminato")){

               return UtenteAcquirenteDao.getInstance().removeByUsername(u.getUsername());

           }else{
               if(stato.equalsIgnoreCase("attivo")){
                   u.setStato(UtenteAcquirente.Stato.ATTIVO);
               }else{
                   u.setStato(UtenteAcquirente.Stato.BLOCCATO);
               }
               return UtenteAcquirenteDao.getInstance().updateStato(u);
           }

       }


       public ArrayList<String> getEmailClienti(){

           PuntoVendita puntoVendita= PuntoVenditaBuisness.getInstance().findBySessioneManager();
           List<Utente> u = UtenteBusiness.getInstance().getUtentiAcquirenti(puntoVendita);
           ArrayList<String> email= new ArrayList<>();
           for(Utente utente:u)
               email.add(utente.getEmail());

           return email;
       }




    public String getNomeUtente(Object utente){
            if (utente instanceof Utente){
                return ((Utente) utente).getNome();
            }
            return null;

    }

    public String getCognomeUtente(Object utente){
        if (utente instanceof Utente){
            return ((Utente) utente).getCognome();
        }
        return null;

    }

    public String getUsernameUtente(Object utente){
        if (utente instanceof Utente){
            return ((Utente) utente).getUsername();
        }
        return null;

    }

    public String getIdUtente(Object utente){
        if (utente instanceof Utente){
            return ((Utente) utente).getId();
        }
        return null;

    }

    public String getStato(Object utente){
            if (utente instanceof UtenteAcquirente){
                return ((UtenteAcquirente) utente).getStatoString();
            }
            return null;

    }

}


