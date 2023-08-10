package Dao.Prodotti;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Model.Prodotti.Articolo;
import Model.Prodotti.Servizio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServizioDao implements IArticoloDao{
    private static final ServizioDao instance = new ServizioDao();
    private static ResultSet rs;
    private static int rowCount;
    public static ServizioDao getInstance() {
        return instance;
    }

    @Override
    public int addArticolo(Object articolo) {
        if (!(articolo instanceof Servizio servizio)) {
            System.out.println("L'oggetto passato non appartiene alla classe Servizio");
            return -2;
        }
        String sql="INSERT INTO `mydb`.`articolo` (`nome`, `prezzo`, `tipo`, `descrizione`) VALUES ('"+servizio.getNome()+"', '"+servizio.getPrezzo()+"', 'servizio', '"+servizio.getDescrizione()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;
        return rowCount;
    }

    @Override
    public ArrayList<Articolo> findArticolo(String id) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'servizio' and idarticolo = '"+id+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                Servizio prodotto = new Servizio();
                prodotto.setId(rs.getString("idarticolo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                articoli.add(prodotto);

            }
            return articoli;

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
    public ArrayList<Articolo> findArticoloName(String nome) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'servizio' and nome = '"+nome+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                Servizio prodotto = new Servizio();
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setDescrizione(rs.getString("descrizione"));
                articoli.add(prodotto);

            }
            return articoli;

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
    public int update(Object articolo) {
        if (!(articolo instanceof Servizio servizio)) {
            System.out.println("L'oggetto passato non appartiene alla classe Servizio");
            return -2;
        }

        String sql="UPDATE `mydb`.`articolo` SET `nome` = '"+servizio.getNome()+"', `prezzo` = '"+servizio.getPrezzo()+"', `descrizione` = '"+servizio.getDescrizione()+"' WHERE (`idarticolo` = '"+servizio.getId()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int remove(String id) {
        String sql="DELETE FROM `mydb`.`articolo` WHERE (`idarticolo` = '"+ id+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount = executor.updateOperation(writeOp);
        return rowCount;
    }
}
