package Dao.Utenti;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Model.Data;
import Model.Prodotti.Prodotto;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UtenteAcquirenteDao implements IUtentiDao {

    private static final UtenteAcquirenteDao instance = new UtenteAcquirenteDao();
    private static ResultSet rs;
    private static int rowCount;
    public static UtenteAcquirenteDao getInstance() {
        return instance;
    }


    @Override
    public UtenteAcquirente findById(String id) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.utente_acquirente where id = '"+id+"';");
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                UtenteAcquirente utente = new UtenteAcquirente();
                utente.setId(rs.getString("id"));
                utente.setUsername(rs.getString("username"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString(("email")));
                utente.setTelefono(rs.getString(("telefono")));
                utente.setIndirizzo(rs.getString(("indirizzo")));
                utente.setCitta(rs.getString(("citta")));
                Data data=new Data();
                data.setData(rs.getString("data_nascita"));
                utente.setDataDiNascita(data);
               // utente.setStato(rs.getString("stato"));


                return utente;
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }

        return null;
    }

    @Override
    public UtenteAcquirente findByUsername(String usr) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.utente_acquirente where username = '"+usr+"';");
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                UtenteAcquirente utente = new UtenteAcquirente();
                utente.setId(rs.getString("id"));
                utente.setUsername(rs.getString("username"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString(("email")));
                utente.setTelefono(rs.getString(("telefono")));
                utente.setIndirizzo(rs.getString(("indirizzo")));
                utente.setCitta(rs.getString(("citta")));
                Data data=new Data();
                data.setData(rs.getString("data_nascita"));
                utente.setDataDiNascita(data);
                // utente.setStato(rs.getString("stato"));


                return utente;
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }

        return null;
    }

    @Override
    public ArrayList<Utente> findAll() {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.utente_acquirente;");
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<Utente> utenteAcquirente= new ArrayList<>();
           while (rs.next())
            {
                UtenteAcquirente utente = new UtenteAcquirente();
                utente.setId(rs.getString("id"));
                utente.setUsername(rs.getString("username"));
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString(("email")));
                utente.setTelefono(rs.getString(("telefono")));
                utente.setIndirizzo(rs.getString(("indirizzo")));
                utente.setCitta(rs.getString(("citta")));
                // utente.setStato(rs.getString("stato"));

                utenteAcquirente.add(utente);
            }

           return utenteAcquirente;
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }

        return null;
    }

    @Override
    public int add(Object utente, String pwd) {
        if (!(utente instanceof UtenteAcquirente utenteAcquirente)) {
            System.out.println("L'oggetto passato non appartiene alla classe UtenteAcquirente");
            return -2;
        }

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation("INSERT INTO `mydb`.`utente_acquirente` (`username`, `nome`, `cognome`, `email`, `password`, `telefono`, `indirizzo`, `citta`, `data_nascita`) VALUES ('"+utenteAcquirente.getUsername()+"', '"+utenteAcquirente.getNome()+"', '"+utenteAcquirente.getCognome()+"', '"+utenteAcquirente.getEmail()+"', '"+pwd+"', '"+utenteAcquirente.getTelefono()+"', '"+utenteAcquirente.getIndirizzo()+"', '"+utenteAcquirente.getCitta()+"', '"+ utenteAcquirente.getDataDiNascita().getDataDB()+"');");
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int update(Object utente) {
        if (!(utente instanceof UtenteAcquirente utenteAcquirente)) {
            System.out.println("L'oggetto passato non appartiene alla classe Utente Acquirente");
            return -2;
        }

      String sql = "UPDATE `mydb`.`utente_acquirente` SET `username` = '"+utenteAcquirente.getUsername()+"', `nome` = '"+utenteAcquirente.getNome()+"', `cognome` = '"+utenteAcquirente.getCognome()+"', `email` = '"+utenteAcquirente.getEmail()+"', `telefono` = '"+utenteAcquirente.getTelefono()+"', `indirizzo` = '"+utenteAcquirente.getIndirizzo()+"', `citta` = '"+utenteAcquirente.getCitta()+"', `data_nascita` = '"+utenteAcquirente.getDataDiNascita().getDataDB()+"' WHERE (`username` = '"+utenteAcquirente.getUsername()+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;

    }


    public boolean utenteBloccato(String username) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT stato FROM mydb.utente_acquirente where username = '"+username+"';");
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                return "bloccato".equalsIgnoreCase(rs.getString("stato"));
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return false;
    }


    public boolean esistenzaUtente(String username) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT id FROM mydb.utente_acquirente where username = '"+username+"';");
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                return true;
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean controlloCredenziali(String usr, String password) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT username FROM mydb.utente_acquirente where username = '"+usr+"' and password = '"+password+"';");
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                System.out.println("Credenziali ok");
                return true;
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        System.out.println("Credenziali errate");
        return false;

    }

    @Override
    public boolean controlloCredenziali(String email) {
        String sql= "SELECT COUNT(*) as c FROM mydb.utente_acquirente WHERE email='"+email+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);

        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if (rs.getRow() == 1) {
                int count = rs.getInt("c");
                return count > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int removeByUsername(String usr){
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation("DELETE FROM`mydb`.`utente_acquirente` WHERE username = '"+usr+"';");
        rowCount = executor.updateOperation(writeOp);
        return rowCount;

    }
}
