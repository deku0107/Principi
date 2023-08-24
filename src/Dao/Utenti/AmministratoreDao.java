package Dao.Utenti;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Model.Data;
import Model.Utenti.Amministratore;
import Model.Utenti.Manager;
import Model.Utenti.Utente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AmministratoreDao implements IUtentiDao{

    private static final AmministratoreDao instance = new AmministratoreDao();
    private static ResultSet rs;
    private static int rowCount;
    public static AmministratoreDao getInstance() {
        return instance;
    }


    @Override
    public Utente findById(String id) {
        String sql="SELECT * FROM mydb.amministratore where idamministratore='"+id+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                Amministratore utente = new Amministratore();

                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString(("email")));
                utente.setTelefono(rs.getString(("telefono")));
                utente.setUsername(rs.getString("username"));

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
    public Utente findByUsername(String usr) {
        String sql="SELECT * FROM mydb.amministratore where username='"+usr+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                Amministratore utente = new Amministratore();

                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString(("email")));
                utente.setTelefono(rs.getString(("telefono")));
                utente.setUsername(rs.getString("username"));

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
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.amministratore;");
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<Utente> utenti= new ArrayList<>();
            while (rs.next()){
                Amministratore utente=new Amministratore();
                utente.setNome(rs.getString("nome"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString(("email")));
                utente.setTelefono(rs.getString(("telefono")));
                utente.setUsername(rs.getString("username"));
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
        if (!(utente instanceof Amministratore)) {
            System.out.println("L'oggetto passato non appartiene alla classe Amministratore");
            return -2;
        }
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation("INSERT INTO `mydb`.`amministratore` (`nome`, `cognome`, `email`, `password`, `telefono`, `username`) VALUES ('"+((Amministratore) utente).getNome()+"', '"+((Amministratore) utente).getCognome()+"', '"+((Amministratore) utente).getEmail()+"', '"+pwd+"', '"+((Amministratore) utente).getTelefono()+"', '"+((Amministratore) utente).getUsername()+"');");
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int update(Object utente) {
        if (!(utente instanceof Amministratore amministratore)) {
            System.out.println("L'oggetto passato non appartiene alla classe Amministratore");
            return -2;
        }

        String sql = "UPDATE `mydb`.`amministratore` SET `username` = '"+amministratore.getUsername()+"', `nome` = '"+amministratore.getNome()+"', `cognome` = '"+amministratore.getCognome()+"', `email` = '"+amministratore.getEmail()+"', `telefono` = '"+amministratore.getTelefono()+"' WHERE (`username` = '"+amministratore.getUsername()+"');";

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
        IDbOperation readOp = new ReadOperation("SELECT username FROM mydb.amministratore where username = '"+usr+"' and password = '"+password+"';");
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
        String sql= "SELECT COUNT(*) as c FROM mydb.amministratore WHERE email='"+email+"';";
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
        IDbOperation writeOp = new WriteOperation("DELETE FROM`mydb`.`amministratore` WHERE username = '"+usr+"';");
        rowCount = executor.updateOperation(writeOp);
        return rowCount;
    }

    @Override
    public Utente findByEmail(String email) {
        return null;
    }
}
