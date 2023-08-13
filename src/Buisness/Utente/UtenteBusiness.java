package Buisness.Utente;

import Buisness.AbstractFactory.AbstractFactoryUtente.AbstractFactoryUtente;
import Buisness.AbstractFactory.AbstractFactoryUtente.FactoryProviderUtente;
import Buisness.SessionManager;
import Dao.Utenti.AmministratoreDao;
import Dao.Utenti.ManagerDao;
import Dao.Utenti.UtenteAcquirenteDao;
import Model.Utenti.Amministratore;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;

import javax.swing.*;
import java.nio.charset.Charset;
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

        public  RegisterResult registrazione(UtenteAcquirente c, String pwd){

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

        public PasswordResult resetPsw(String email){
            boolean esistenzaUtente;
            PasswordResult result= new PasswordResult();

            esistenzaUtente= UtenteAcquirenteDao.getInstance().controlloCredenziali(email);
            if(esistenzaUtente){
                //generazione codice
                String cod=getRandomString(5);
                //invio email con codice
                String tmp= JOptionPane.showInputDialog("Inserire il codice inviato per email");
                if(cod.equalsIgnoreCase(tmp)){
                    tmp= JOptionPane.showInputDialog("Inserire la nuova password");
                }

                return result ;
            }
            else{
                result.setResult(PasswordResult.Result.EMAIL_NON_VALIDA);
                result.setMessaggio("Email non valida");
                JOptionPane.showMessageDialog(new JFrame(),result.getMessaggio());
                return result;
            }
            //invio email con codice

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



    static String getRandomString(int i)
    {

        // bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring= new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS = mystring.replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }


}


