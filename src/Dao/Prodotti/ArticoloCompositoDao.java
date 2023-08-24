package Dao.Prodotti;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import DBInterface.command.WriteOperation;
import Model.Categoria;
import Model.Prodotti.Articolo;
import Model.Prodotti.ArticoloComposito;
import Model.Prodotti.Composizione;
import Model.Prodotti.Prodotto;
import Model.Produttore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticoloCompositoDao implements IArticoloDao{

    private static final ArticoloCompositoDao instance = new ArticoloCompositoDao();
    private static ResultSet rs;
    private static int rowCount;
    public static ArticoloCompositoDao getInstance() {
        return instance;
    }


    @Override
    public int addArticolo(Object articolo) {
        if (!(articolo instanceof ArticoloComposito prodotto)) {
            System.out.println("L'oggetto passato non appartiene alla classe ArticoloComposito");
            return -2;
        }
        String sql="INSERT INTO `mydb`.`articolo` (`nome`,`tipo`, `prezzo`, `produttore`, `categoria`, `descrizione`, `corsia`, `scaffale`) VALUES ('"+prodotto.getNome()+"', 'composito',  '"+prodotto.getPrezzo()+"', '"+prodotto.getProduttore().getId()+"', '"+prodotto.getCategoria().getId()+"', '"+prodotto.getDescrizione()+"', '"+prodotto.getCorsia()+"', '"+prodotto.getScaffale()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        ResultSet rs;
        executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(idarticolo) as i FROM mydb.articolo;");
        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if(rs.getRow()==1){
                max= rs.getInt("i");
                prodotto.setId(String.valueOf(max));
                return addComposizione(prodotto);
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
        return -5;
    }

    public int addComposizione(Composizione composizione, ArticoloComposito articoloComposito) {
        Articolo articolo= composizione.getArticolo();
        System.out.println("Elementi composizone " + articoloComposito.getComposizione().size());
        System.out.println("Id articolo composito: " + articoloComposito.getId()+" Id articolo : " + articolo.getId() + " quantita : "+ composizione.getQuantita());
        System.out.println();
        if(ProdottoDao.getInstance().findArticolo(articolo.getId())==null){
            System.out.println("Articolo non presente in db");
            return -2;
        }
        int quantita = composizione.getQuantita();
        String id = articoloComposito.getId();
        if(id==null){
            System.out.println("Id articolo composito non valido");

            return -3;
        }

        String sql="INSERT INTO `mydb`.`articolo_composito` (`idarticolo_composito`, `articolo_composito_interno`,`quantita_articolo_interno`) VALUES ('"+id+"', '"+articolo.getId()+"', '"+quantita+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -4;
        return rowCount;
    }

    public int addComposizione(ArticoloComposito articoloComposito) {

        int c=0;
        for(Composizione comp: articoloComposito.getComposizione()) {
            c = addComposizione(comp, articoloComposito) + c;
        }
        return c;
    }

    @Override
    public  Articolo findArticolo(String id) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'composito' and idarticolo = '"+id+"';");
        rs = executor.executeOperation(readOp);

        try {
            rs.next();
            if (rs.getRow()==1){
                ArticoloComposito prodotto = new ArticoloComposito();
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
                prodotto.setComposizione(findComposizione(prodotto));

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


    public ArrayList<Articolo> findArticolo() {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'composito';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                ArticoloComposito prodotto = new ArticoloComposito();
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
                //prodotto.setComposizione(findComposizione(prodotto));

                articoli.add(prodotto);

            }

            for(Articolo a:articoli){
                ArticoloComposito articoloComposito= (ArticoloComposito)a;
                articoloComposito.setComposizione(findComposizione(articoloComposito));
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

    public Articolo findArticoloById(String id) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'composito' and idarticolo = '"+id+"';");
        rs = executor.executeOperation(readOp);

        try {
            if (rs.next()){
                ArticoloComposito prodotto = new ArticoloComposito();
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
                prodotto.setComposizione(findComposizione(prodotto));

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
    @Override
    public ArrayList<Articolo> findArticoloName(String nome) {
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT * FROM mydb.articolo where tipo = 'composito' and idarticolo = '"+nome+"';");
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Articolo> articoli= new ArrayList<>();
            while (rs.next()){
                ArticoloComposito prodotto = new ArticoloComposito();
                prodotto.setNome(rs.getString("nome"));
                prodotto.setPrezzo(rs.getFloat("prezzo"));
                Produttore produttore= new Produttore();
                produttore.setId(rs.getString("produttore"));
                prodotto.setProduttore(produttore);
                Categoria categoria= new Categoria();
                categoria.setId(rs.getString("categoria"));
                prodotto.setCategoria(categoria);
                prodotto.setDescrizione(rs.getString("descrizione"));
                prodotto.setComposizione(findComposizione(prodotto));

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

    public ArrayList<Composizione> findComposizione(ArticoloComposito articoloComposito){

        String sql = " SELECT articolo_composito_interno, quantita_articolo_interno FROM mydb.articolo_composito where idarticolo_composito = '"+articoloComposito.getId()+"';";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation(sql);
        rs = executor.executeOperation(readOp);

        try {
            ArrayList<Composizione> composizione= new ArrayList<>();
            while (rs.next()){
                String id = rs.getString("articolo_composito_interno");
                int quantita = rs.getInt("quantita_articolo_interno");


                Composizione c = new Composizione();
                System.out.println("Prova");
                if(ProdottoDao.getInstance().findArticolo(id)==null){
                    if(ArticoloCompositoDao.getInstance().findArticolo(id)==null){
                        if(ServizioDao.getInstance().findArticolo(id)==null){
                            c.setArticolo(null);
                            c.setQuantita(0);
                        }else{
                            //servizio
                            c.setArticolo(ServizioDao.getInstance().findArticolo(id));
                            c.setQuantita(quantita);
                            composizione.add(c);
                        }
                    }else{
                        //articolo composito
                        c.setArticolo(ArticoloCompositoDao.getInstance().findArticolo(id));
                        c.setQuantita(quantita);
                        composizione.add(c);
                    }

                }else {
                    c.setArticolo(ProdottoDao.getInstance().findArticolo(id));
                    c.setQuantita(quantita);
                    composizione.add(c);
                }
            }


            System.out.println("Valore ritorno composizione: ");
            return composizione;

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

        String sql="UPDATE `mydb`.`articolo` SET `nome` = '"+prodotto.getNome()+"', `prezzo` = '"+prodotto.getPrezzo()+"', `produttore` = '"+prodotto.getProduttore().getId()+"', `categoria` = '"+prodotto.getCategoria().getId()+"', `descrizione` = '"+prodotto.getDescrizione()+"' WHERE (`idarticolo` = '"+prodotto.getId()+"');";
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    public int updateComposizione(Composizione composizione, ArticoloComposito articoloComposito) {
       int quantita= composizione.getQuantita();
       String sql;
       String id = composizione.getArticolo().getId();
       if(quantita==0) {
           sql = "DELETE FROM `mydb`.`articolo_composito` WHERE (`articolo_composito_interno` = '"+id+"' and `idarticolo_composito` = '"+articoloComposito.getId()+"' );";
       }else{
           sql="UPDATE `mydb`.`articolo_composito` SET `quantita_articolo_interno` = '"+quantita+"' WHERE (`articolo_composito_interno` = '"+id+"');";

       }
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation writeOp = new WriteOperation(sql);
        rowCount=executor.updateOperation(writeOp);
        if(rowCount<0)
            return -1;

        return rowCount;
    }

    public int updateComposizione(ArticoloComposito articoloComposito) {

        int c=0;
        for(Composizione comp: articoloComposito.getComposizione()) {
            c = updateComposizione(comp, articoloComposito) + c;
        }
        return c;
    }  // array di composizioni

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
