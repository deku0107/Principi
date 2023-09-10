package Dao.PuntoVendita;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Dao.Categorie.CategoriaDao;
import Dao.Prodotti.ArticoloCompositoDao;
import Dao.Prodotti.ProdottoDao;
import Model.Prodotti.Articolo;
import Model.Prodotti.ArticoloComposito;
import Model.Prodotti.Prodotto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

public class OffertaDAO implements IOffertaDAO{


    private static final OffertaDAO i=new OffertaDAO();
    public static OffertaDAO getInstance(){return i;}
    private static ResultSet rs;
    private static int rowCount;

    @Override
    public List<Articolo> getOfferta(String id) {

        String sql="SELECT quantita, articolo FROM mydb.offerta where punto_vendita='"+id+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<Articolo> articoloArrayList= new ArrayList<>();
            while (rs.next()){

                Prodotto a ;
                int q= rs.getInt("quantita");
                System.out.println("Quantita nel offerta dao " + q);
                String tmp=rs.getString("articolo");
                a= (Prodotto) ProdottoDao.getInstance().findArticolo(tmp);
                if(a!=null) {
                    a.setQuantita(q);
                    articoloArrayList.add(a);
                }else {
                    a=(ArticoloComposito) ArticoloCompositoDao.getInstance().findArticolo(tmp);
                    if(a!=null) {
                        a.setQuantita(q);
                        articoloArrayList.add(a);
                    }
                }

            }

            return articoloArrayList;

        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return null;
    }

    public List<Articolo> getOfferta(String id, String categoria) {

        String sql="SELECT quantita, articolo FROM mydb.offerta where punto_vendita='"+id+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);
        try {
            ArrayList<Articolo> articoloArrayList= new ArrayList<>();
            while (rs.next()){

                Prodotto a ;
                int q= rs.getInt("quantita");
                System.out.println("Quantita nel offerta dao " + q);
                String tmp=rs.getString("articolo");
                a= (Prodotto) ProdottoDao.getInstance().findArticolo(tmp, categoria);
                if(a!=null) {
                    a.setQuantita(q);
                    articoloArrayList.add(a);
                }else {
                    a=(ArticoloComposito) ArticoloCompositoDao.getInstance().findArticolo(tmp, categoria);
                    if(a!=null) {
                        a.setQuantita(q);
                        articoloArrayList.add(a);
                    }
                }

            }

            return articoloArrayList;

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
    public int updateQuantita(String idArticolo, String idPuntoVendita, int i) {
        String sql = "UPDATE `mydb`.`offerta` SET `quantita` = '"+i+"' WHERE (`articolo` = '"+idArticolo+"' and `punto_vendita` = '"+idPuntoVendita+"') ;";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }



    public int updateQuantita(String idArticolo, String idPuntoVendita) {
        String sql="UPDATE `mydb`.`offerta` SET `quantita` = `quantita` + '1' WHERE (`articolo` = '"+idArticolo+"' and `punto_vendita` = '"+idPuntoVendita+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }
    @Override
    public int remove(String idArticolo, String idPuntoVendita) {


        System.out.println(idArticolo);
        System.out.println(idPuntoVendita);
        String sql = "DELETE FROM `mydb`.`offerta` WHERE (`articolo` = '"+idArticolo+"' AND `punto_vendita` ='"+idPuntoVendita+"' );";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    @Override
    public int add(String idArticolo, String idPuntoVendita) {
        String sql = "INSERT INTO `mydb`.`offerta` (`articolo`, `punto_vendita`) VALUES ('"+idArticolo+"', '"+idPuntoVendita+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }


    public int removeQuantita(String idA, String idPV) {

        String sql="UPDATE `mydb`.`offerta` SET `quantita` = `quantita` - '1' WHERE (`articolo` = '"+idA+"' and `punto_vendita` = '"+idPV+"');";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }
}
