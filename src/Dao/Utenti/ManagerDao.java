package Dao.Utenti;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Model.Data;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManagerDao implements IUtentiDao
{


    private static final ManagerDao instance = new ManagerDao();
    private static ResultSet rs;
    private static int rowCount;
    public static ManagerDao getInstance() {
        return instance;
    }


    @Override
    public Manager findById(String id) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.manager where idmanager = '"+id+"';");
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                Manager utente = new Manager();
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
                data=new Data();
                data.setData(rs.getString("inizio_incarico"));
                utente.setInizioIncarico(data);
                data=new Data();
                data.setData(rs.getString("fine_incarico"));
                utente.setFineIncarico(data);
                utente.setStipendio(rs.getFloat("salario"));

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
    public Manager findByUsername(String usr) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.manager where username ='"+usr+"';");
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                Manager utente = new Manager();
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
                data=new Data();
                data.setData(rs.getString("inizio_incarico"));
                utente.setInizioIncarico(data);
                data=new Data();
                data.setData(rs.getString("fine_incarico"));
                utente.setFineIncarico(data);
                utente.setStipendio(rs.getFloat("salario"));

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
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.manager;");
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<Utente> utenti= new ArrayList<>();
           while (rs.next()){
                Manager utente = new Manager();
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString(("email")));
                utente.setTelefono(rs.getString(("telefono")));
                utente.setIndirizzo(rs.getString(("indirizzo")));
                utente.setCitta(rs.getString(("citta")));
                Data data=new Data();
                data.setData(rs.getString("data_nascita"));
                utente.setDataDiNascita(data);
                data.setData(rs.getString("inizio_inarico"));
                utente.setInizioIncarico(data);
                data.setData(rs.getString("fine_incarico"));
                utente.setFineIncarico(data);
                utente.setStipendio(rs.getFloat("salario"));

                utenti.add(utente);

            }
           return utenti;

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
        if (!(utente instanceof Manager manager)) {
            System.out.println("L'oggetto passato non appartiene alla classe Manager");
            return -2;
        }
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation("INSERT INTO `mydb`.`manager` (`username`, `nome`, `cognome`, `email`, `password`, `telefono`, `indirizzo`, `citta`, `data_nascita`, `inizio_incarico`, `fine_incarico`, `salario`) VALUES ('"+manager.getUsername()+"', '"+manager.getNome()+"', '"+manager.getCognome()+"', '"+manager.getEmail()+"', '"+pwd+"', '"+manager.getTelefono()+"', '"+manager.getIndirizzo()+"', '"+manager.getCitta()+"', '"+ manager.getDataDiNascita().getDataDB()+"', '"+manager.getInizioIncarico().getDataDB()+"', '"+manager.getFineIncarico().getDataDB()+"', '"+manager.getStipendio()+"');");
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int update(Object utente) {
        if (!(utente instanceof Manager manager)) {
            System.out.println("L'oggetto passato non appartiene alla classe Manager");
            return -2;
        }

        String sql = "UPDATE `mydb`.`manager` SET `username` = '"+manager.getUsername()+"', `nome` = '"+manager.getNome()+"', `cognome` = '"+manager.getCognome()+"', `email` = '"+manager.getEmail()+"', `telefono` = '"+manager.getTelefono()+"', `indirizzo` = '"+manager.getIndirizzo()+"', `citta` = '"+manager.getCitta()+"', `data_nascita` = '"+manager.getDataDiNascita().getDataDB()+"', `inizio_incarico` = '"+manager.getInizioIncarico().getDataDB()+"', `fine_incarico` = '"+manager.getFineIncarico().getDataDB()+"', `salario` = '"+manager.getStipendio()+"' WHERE (`username` = '"+manager.getUsername()+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }


    @Override
    public boolean controlloCredenziali(String usr, String password) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT username FROM mydb.manager where username = '"+usr+"' and password = '"+password+"';");
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
        String sql= "SELECT COUNT(*) as c FROM mydb.manager WHERE email='"+email+"';";
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

    @Override
    public int removeByUsername(String usr) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation("DELETE FROM`mydb`.`manager` WHERE username = '"+usr+"';");
        rowCount = executor.updateOperation(writeOp);
        return rowCount;
    }
}
