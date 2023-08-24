package Dao.Produttore;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Model.Produttore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProduttoreDAO implements IProduttoreDAO{

    private static final ProduttoreDAO instance=new ProduttoreDAO();
    public static ProduttoreDAO getInstance(){return instance;}
    private static ResultSet rs;
    private static int rowCount;


    @Override
    public Produttore find(String id) {

        String sql="SELECT * FROM mydb.produttore where idproduttore='"+id+"';";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            rs.next();
            if(rs.getRow()==1){
                Produttore produttore = new Produttore();
                produttore.setNome(rs.getString("nome"));
                produttore.setSitoWeb(rs.getString(("sito")));
                produttore.setCitta(rs.getString(("citta")));
                produttore.setNazione(rs.getString(("nazione")));
                produttore.setId(id);

                return produttore;
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
    public ArrayList<Produttore> findAll() {
        String sql="SELECT * FROM mydb.produttore;";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<Produttore> produttori= new ArrayList<>();
            while (rs.next()){
                Produttore produttore = new Produttore();
                produttore.setId(rs.getString("idproduttore"));
                produttore.setNome(rs.getString("nome"));
                produttore.setSitoWeb(rs.getString(("sito")));
                produttore.setCitta(rs.getString(("citta")));
                produttore.setNazione(rs.getString(("nazione")));

                produttori.add(produttore);

            }
            return produttori;

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
    public int add(Produttore produttore) {

        String sql="INSERT INTO `mydb`.`produttore` (`nome`, `sito`, `citta`, `nazione`) VALUES ('"+produttore.getNome()+"', '"+produttore.getSitoWeb()+"', '"+produttore.getCitta()+"', '"+produttore.getNazione()+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int remove(Produttore produttore) {
        String sql="DELETE FROM `mydb`.`produttore` WHERE (`idproduttore` = '"+produttore.getId()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int update(Produttore produttore) {

        String sql="UPDATE `mydb`.`produttore` SET `nome` = '"+produttore.getNome()+"', `sito` = '"+produttore.getSitoWeb()+"', `citta` = '"+produttore.getCitta()+"', `nazione` = '"+produttore.getNazione()+"' WHERE (`idproduttore` = '"+produttore.getId()+"');";
        System.out.println(sql);
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }
}
