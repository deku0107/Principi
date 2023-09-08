package Dao.Prodotti;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Dao.Categorie.CategoriaDao;
import Dao.UtilityDao;
import Model.Categoria;
import Model.Prodotti.Articolo;
import Model.Prodotti.Prodotto;
import Model.Produttore;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdottoDao implements IArticoloDao {

    private static final ProdottoDao instance = new ProdottoDao();
    private static ResultSet rs;
    private static int rowCount;
    public static ProdottoDao getInstance() {
        return instance;
    }

    @Override
    public int addArticolo(Object articolo) {


        if (!(articolo instanceof Prodotto prodotto)) {
            System.out.println("L'oggetto passato non appartiene alla classe Prodotto");
            return -2;
        }
        String sql="INSERT INTO `mydb`.`articolo` (`nome`, `prezzo`, `produttore`, `categoria`, `descrizione`, `corsia`, `scaffale`) VALUES ('"+prodotto.getNome()+"', '"+prodotto.getPrezzo()+"', '"+prodotto.getProduttore().getId()+"', '"+prodotto.getCategoria().getId()+"', '"+prodotto.getDescrizione()+"', '"+prodotto.getCorsia()+"', '"+prodotto.getScaffale()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;
        int id = UtilityDao.getInstance().getMax("articolo", "idarticolo");

        sql= "INSERT INTO `mydb`.`immagine` (`articolo`, `path`) VALUES ('"+id+"', '"+((Prodotto) articolo).getImmagine().getPath()+"');";
        writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -2;
        return rowCount;
    }

    @Override
    public Articolo findArticolo(String id) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'prodotto' and idarticolo = '"+id+"';");
        rs = executor.executeOperation(readOp);

        try {
            rs.next();
            if (rs.getRow()==1){
                Prodotto prodotto = new Prodotto();
                prodotto.setId(rs.getString("idarticolo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                Produttore produttore= new Produttore();
                produttore.setId(rs.getString("produttore"));
                prodotto.setProduttore(produttore);
                Categoria categoria= new Categoria();
                categoria.setId(rs.getString("categoria"));
                prodotto.setCategoria(categoria);
                prodotto.setDescrizione(rs.getString("descrizione"));

                return prodotto;

            }
            return null;

        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Articolo> findArticolo(Categoria categoria) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'prodotto' and categoria = '"+categoria.getId()+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                Produttore produttore= new Produttore();
                produttore.setId(rs.getString("produttore"));
                prodotto.setProduttore(produttore);
                prodotto.setCategoria(categoria);
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

    public Articolo findArticolo(String id,String c) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'prodotto' and categoria = '"+c+"' and idarticolo = '"+id+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                Produttore produttore= new Produttore();
                produttore.setId(rs.getString("produttore"));
                prodotto.setProduttore(produttore);
                Categoria categoria= CategoriaDao.getInstance().findCategoria(rs.getString("categoria"));
                prodotto.setCategoria(categoria);
                prodotto.setDescrizione(rs.getString("descrizione"));

                return prodotto;

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

    public ArrayList<Articolo> findArticolo(Produttore produttore) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'prodotto' and produttore = '"+produttore.getId()+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                prodotto.setProduttore(produttore);
                Categoria categoria= new Categoria();
                categoria.setId(rs.getString("categoria"));
                prodotto.setCategoria(categoria);
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

    public ArrayList<Articolo> findArticolo() {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo='prodotto';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                Prodotto prodotto = new Prodotto();
                prodotto.setId(rs.getString("idarticolo"));
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                String tmp = rs.getString("produttore");
                if(tmp==null){
                    prodotto.setProduttore(null);
                }else{
                    Produttore produttore = new Produttore();  //produttoredao.findproduttore
                    produttore.setId(tmp);
                    prodotto.setProduttore(produttore);
                }
                Categoria categoria= new Categoria();
                categoria.setId(rs.getString("categoria"));
                prodotto.setCategoria(categoria);
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
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'prodotto' and nome = '"+nome+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                Prodotto prodotto = new Prodotto();
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                Produttore produttore= new Produttore();
                produttore.setId(rs.getString("produttore"));
                prodotto.setProduttore(produttore);
                Categoria categoria= new Categoria();
                categoria.setId(rs.getString("categoria"));
                prodotto.setCategoria(categoria);
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
        if (!(articolo instanceof Prodotto prodotto)) {
            System.out.println("L'oggetto passato non appartiene alla classe Prodotto");
            return -2;
        }


        System.out.println(prodotto.getNome());
        System.out.println(prodotto.getPrezzo());
        System.out.println(prodotto.getProduttore().getId());
        System.out.println(prodotto.getCategoria().getId());
        System.out.println(prodotto.getDescrizione());
        System.out.println(prodotto.getId());
        String sql="UPDATE `mydb`.`articolo` SET `nome` = '"+prodotto.getNome()+"', `prezzo` = '"+prodotto.getPrezzo()+"', `produttore` = '"+prodotto.getProduttore().getId()+"', `categoria` = '"+prodotto.getCategoria().getId()+"', `descrizione` = '"+prodotto.getDescrizione()+"' WHERE (`idarticolo` = '"+prodotto.getId()+"');";
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

    public int setPosizione(String idArticolo,  int corsia, int scaffale) {

        String sql = "UPDATE `mydb`.`articolo` SET `corsia` = '"+corsia+"', `scaffale` = '"+scaffale+"' WHERE (`idarticolo` = '"+idArticolo+"') ;";

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }
}
