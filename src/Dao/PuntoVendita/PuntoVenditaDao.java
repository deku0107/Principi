package Dao.PuntoVendita;

import Buisness.SessionManager;
import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Dao.Utenti.ManagerDao;
import Model.PuntoVendita;
import Model.Utenti.Manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PuntoVenditaDao implements IPuntoVenditaDAO{

    private static final PuntoVenditaDao instance= new PuntoVenditaDao();
    public static PuntoVenditaDao getInstance(){return instance;}
    private static ResultSet rs;
    private static int rowCount;

    @Override
    public PuntoVendita findById(String id) {
        String sql ="SELECT * FROM mydb.punto_vendita where idpunto_vendita='"+id+"';";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                PuntoVendita puntoVendita = new PuntoVendita();
                puntoVendita.setId(rs.getString("idpunto_vendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setEmail(rs.getString(("email")));
                puntoVendita.setTelefono(rs.getString(("telefono")));
                puntoVendita.setIndirizzo(rs.getString(("indirizzo")));
                puntoVendita.setCitta(rs.getString(("citta")));
                puntoVendita.setManager(ManagerDao.getInstance().findById(rs.getString("manager")));

                return puntoVendita;
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
    public PuntoVendita findByUser(String id) {
        String sql = "SELECT id_punto_vendita FROM mydb.utente_acquirente where id='"+id+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                return findById(rs.getString("id_punto_vendita"));
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
    public PuntoVendita findByManager(String id) {
        String sql ="SELECT * FROM mydb.punto_vendita where manager='"+id+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                PuntoVendita puntoVendita = new PuntoVendita();
                puntoVendita.setId(rs.getString("idpunto_vendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setEmail(rs.getString(("email")));
                puntoVendita.setTelefono(rs.getString(("telefono")));
                puntoVendita.setIndirizzo(rs.getString(("indirizzo")));
                puntoVendita.setCitta(rs.getString(("citta")));
                puntoVendita.setManager(ManagerDao.getInstance().findById(rs.getString("manager")));

                return puntoVendita;
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
    public ArrayList<PuntoVendita> findByName(String name) {

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.punto_vendita where nome='"+name+"';");
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<PuntoVendita> puntiVendita= new ArrayList<>();
            while (rs.next()){
                PuntoVendita puntoVendita = new PuntoVendita();
                puntoVendita.setId(rs.getString("idpunto_vendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setEmail(rs.getString(("email")));
                puntoVendita.setTelefono(rs.getString(("telefono")));
                puntoVendita.setIndirizzo(rs.getString(("indirizzo")));
                puntoVendita.setCitta(rs.getString(("citta")));
                puntoVendita.setManager(ManagerDao.getInstance().findById(rs.getString("manager")));

                puntiVendita.add(puntoVendita);

            }
            return puntiVendita;

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
    public ArrayList<PuntoVendita> findAll() {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.punto_vendita;");
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<PuntoVendita> puntiVendita= new ArrayList<>();
            while (rs.next()){
                PuntoVendita puntoVendita = new PuntoVendita();
                puntoVendita.setId(rs.getString("idpunto_vendita"));
                puntoVendita.setNome(rs.getString("nome"));
                puntoVendita.setEmail(rs.getString(("email")));
                puntoVendita.setTelefono(rs.getString(("telefono")));
                puntoVendita.setIndirizzo(rs.getString(("indirizzo")));
                puntoVendita.setCitta(rs.getString(("citta")));
                puntoVendita.setManager(ManagerDao.getInstance().findById(rs.getString("manager")));

                puntiVendita.add(puntoVendita);

            }
            return puntiVendita;

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
    public int add(PuntoVendita puntoVendita) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        String sql="INSERT INTO `mydb`.`punto_vendita` ( `nome`,  `email`, `telefono`, `indirizzo`, `citta`, `manager`) VALUES ('"+puntoVendita.getNome()+"', '"+puntoVendita.getEmail()+"', '"+puntoVendita.getTelefono()+"', '"+puntoVendita.getIndirizzo()+"', '"+puntoVendita.getCitta()+"', '"+ puntoVendita.getManager().getId()+"');";
        System.out.println(sql);
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int update(PuntoVendita puntoVendita) {

        String sql = "UPDATE `mydb`.`punto_vendita` SET `nome` = '"+puntoVendita.getNome()+"', `email` = '"+puntoVendita.getEmail()+"', `telefono` = '"+puntoVendita.getTelefono()+"', `indirizzo` = '"+puntoVendita.getIndirizzo()+"', `citta` = '"+puntoVendita.getCitta()+"' WHERE (`idpunto_vendita` = '"+puntoVendita.getId()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int remove(PuntoVendita puntoVendita) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation("DELETE FROM`mydb`.`punto_vendita` WHERE idpunto_vendita = '"+puntoVendita.getId()+"';");
        rowCount = executor.updateOperation(writeOp);
        return rowCount;
    }

    @Override
    public int UpdateManager(PuntoVendita puntoVendita, Manager m) {
        String sql = "UPDATE `mydb`.`punto_vendita` SET `manager` = '"+m.getId()+"' WHERE (`idpunto_vendita` = '"+puntoVendita.getId()+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }



}
