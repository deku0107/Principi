package Dao.Utenti;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
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
        return null;
    }

    @Override
    public Utente findByUsername(String usr) {
        return null;
    }

    @Override
    public ArrayList<Utente> findAll() {
        return null;
    }

    @Override
    public int add(Object utente, String pwd) {
        return 0;
    }

    @Override
    public int update(Object utente) {
        return 0;
    }


    @Override
    public boolean controlloCredenziali(String usr, String password) {
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
        return 0;
    }
}
