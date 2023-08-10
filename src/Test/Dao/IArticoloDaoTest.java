package Test.Dao;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import Dao.Prodotti.ArticoloCompositoDao;
import Dao.Prodotti.IArticoloDao;
import Dao.Prodotti.ProdottoDao;
import Dao.Prodotti.ServizioDao;
import Model.Categoria;
import Model.Prodotti.*;
import Model.Produttore;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class IArticoloDaoTest {


    @Before
    public void setUp(){

        IArticoloDao articoloDao= ProdottoDao.getInstance();

        Prodotto prodotto = new Prodotto();
        prodotto.setNome("Nome prodotto test");
        prodotto.setPrezzo(150.50f);
        Produttore produttore= new Produttore();
        produttore.setId("1");
        prodotto.setProduttore(produttore);
        Categoria categoria= new Categoria();
        categoria.setId("1");
        prodotto.setCategoria(categoria);
        prodotto.setDescrizione("Descrizone del prodotto inserito nel test");
        System.out.println("Inserimento nel database");
        articoloDao.addArticolo(prodotto);


        ArticoloCompositoDao articoloCompositoDao= ArticoloCompositoDao.getInstance();

        ArticoloComposito articoloComposito= new ArticoloComposito();
        articoloComposito.setNome("Nome articolo composito prova");
        articoloComposito.setPrezzo(200.50f);
        articoloComposito.setDescrizione("Descrizione articolo composito");



        Composizione composizione= new Composizione();
        Articolo articolo= new Articolo();
        articolo.setId("1");
        composizione.setArticolo(articolo);
        composizione.setQuantita(5);
        articoloComposito.setComposizione(composizione);

        System.out.println("Composizione 1 " + articoloComposito.getComposizione().size() + " + " + articoloComposito.getComposizione().get(0).getArticolo().getId());

        Articolo articolo1= new Articolo();
        articolo1.setId("3");
        Composizione composizione1=new Composizione();
        composizione1.setArticolo(articolo1);
        composizione1.setQuantita(10);
        articoloComposito.setComposizione(composizione1);

        System.out.println("Composizione 2 " + articoloComposito.getComposizione().size() + " + " + articoloComposito.getComposizione().get(1).getArticolo().getId());

       articoloCompositoDao.addArticolo(articoloComposito);




        Servizio servizio= new Servizio();
        servizio.setNome("Nome servizio test");
        servizio.setDescrizione("Descrizione servizio test");
        servizio.setPrezzo(20.00f);
        ServizioDao.getInstance().addArticolo(servizio);




    }

    @After
    public  void tearDown(){
        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(idarticolo) as i FROM mydb.articolo;");

        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if (rs.getRow() == 1) {
                max=rs.getInt("i")-2;
                ProdottoDao.getInstance().remove(String.valueOf(max));

                ArticoloCompositoDao.getInstance().remove(String.valueOf(max+1));

                ServizioDao.getInstance().remove(String.valueOf(max+2));


            }
        } catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }


    }

    @Test
    public void findByIdtest(){

        //test ricerca per id dell' articolo 1

        ProdottoDao articoloDao = new ProdottoDao();
        ArrayList<Articolo> articolo=  articoloDao.findArticolo("1");
        Prodotto prodotto = (Prodotto) articolo.get(0);
        Assert.assertTrue("1".equalsIgnoreCase(prodotto.getId().trim()));
        Assert.assertTrue("test".equalsIgnoreCase( prodotto.getNome().trim()));
        Assert.assertEquals(10.99f, prodotto.getPrezzo(), 0.0);
        Assert.assertEquals("1", prodotto.getProduttore().getId());
        Assert.assertEquals("1", prodotto.getCategoria().getId());
        Assert.assertTrue("Test per dao".equalsIgnoreCase(prodotto.getDescrizione().trim()));


        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(idarticolo) as i FROM mydb.articolo;");

        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if (rs.getRow() == 1) {
                max=rs.getInt("i")-2;

                //test ricerca per id dell' articolo
                articolo.clear();
                articolo= ProdottoDao.getInstance().findArticolo(String.valueOf(max));
                Assert.assertEquals(1, articolo.size());
                prodotto = (Prodotto) articolo.get(0);
                Assert.assertTrue(String.valueOf(max).equalsIgnoreCase(prodotto.getId().trim()));
                Assert.assertTrue("Nome prodotto test".equalsIgnoreCase( prodotto.getNome().trim()));
                Assert.assertEquals(150.50f, prodotto.getPrezzo(), 0.0);
                Assert.assertEquals("1", prodotto.getProduttore().getId());
                Assert.assertEquals("1", prodotto.getCategoria().getId());
                Assert.assertTrue("Descrizone del prodotto inserito nel test".equalsIgnoreCase(prodotto.getDescrizione().trim()));


                //test ricerca per id dell' articolo composito
                articolo.clear();
                articolo=ArticoloCompositoDao.getInstance().findArticolo(String.valueOf(max+1));
                Assert.assertEquals(1, articolo.size());
                ArticoloComposito articoloComposito=(ArticoloComposito) articolo.get(0);
                Assert.assertTrue("Nome articolo composito prova".equalsIgnoreCase(articoloComposito.getNome()));
                Assert.assertEquals(200.50f,articoloComposito.getPrezzo(), 0.0);
                Assert.assertTrue("Descrizione articolo composito".equalsIgnoreCase(articoloComposito.getDescrizione()));
                Assert.assertEquals(2, articoloComposito.getComposizione().size());
                Assert.assertEquals(1, Integer.parseInt(articoloComposito.getComposizione().get(0).getArticolo().getId()));
                Assert.assertEquals(3, Integer.parseInt(articoloComposito.getComposizione().get(1).getArticolo().getId()));
                Assert.assertEquals(5, articoloComposito.getComposizione().get(0).getQuantita());
                Assert.assertEquals(10, articoloComposito.getComposizione().get(1).getQuantita());




              //test ricerca per id del servizio
                articolo.clear();
                articolo=ServizioDao.getInstance().findArticolo(String.valueOf(max+2));
                Assert.assertEquals(1, articolo.size());
                Servizio servizio= (Servizio) articolo.get(0);
                Assert.assertTrue("Nome servizio test".equalsIgnoreCase(servizio.getNome()));
                Assert.assertEquals(20.00f,servizio.getPrezzo(), 0.0);
                Assert.assertTrue("Descrizione servizio test".equalsIgnoreCase(servizio.getDescrizione()));



            }
        } catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
    }

    @Test
    public void updateTest(){

        Prodotto prodotto = new Prodotto();
        prodotto.setNome("Nome prodotto test modificato");
        prodotto.setPrezzo(1500.50f);
        Produttore produttore= new Produttore();
        produttore.setId("1");
        prodotto.setProduttore(produttore);
        Categoria categoria= new Categoria();
        categoria.setId("1");
        prodotto.setCategoria(categoria);
        prodotto.setDescrizione("Descrizone del prodotto inserito nel test modificato");




        ArticoloComposito articoloComposito= new ArticoloComposito();
        articoloComposito.setNome("Nome articolo composito prova modifica");
        articoloComposito.setPrezzo(20.50f);
        articoloComposito.setDescrizione("Descrizione articolo composito modificato");



        Composizione composizione= new Composizione();
        Articolo articolo= new Articolo();
        articolo.setId("1");
        composizione.setArticolo(articolo);
        composizione.setQuantita(0);
        articoloComposito.setComposizione(composizione);

        System.out.println("Composizione 1 " + articoloComposito.getComposizione().size() + " + " + articoloComposito.getComposizione().get(0).getArticolo().getId());

        Articolo articolo1= new Articolo();
        articolo1.setId("3");
        Composizione composizione1=new Composizione();
        composizione1.setArticolo(articolo1);
        composizione1.setQuantita(100);
        articoloComposito.setComposizione(composizione1);






        Servizio servizio= new Servizio();
        servizio.setNome("Nome servizio test modifica");
        servizio.setDescrizione("Descrizione servizio test modificata");
        servizio.setPrezzo(2000.00f);





        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(idarticolo) as i FROM mydb.articolo;");

        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if (rs.getRow() == 1) {
                max=rs.getInt("i")-2;

                prodotto.setId(String.valueOf(max));
                articoloComposito.setId(String.valueOf(max+1));
                servizio.setId(String.valueOf(max+2));

                ProdottoDao.getInstance().update(prodotto);
                ArticoloCompositoDao.getInstance().update(articoloComposito);
                ArticoloCompositoDao.getInstance().updateComposizione(articoloComposito);
                ServizioDao.getInstance().update(servizio);



                //test aggiornamento articolo
                ArrayList<Articolo> articoli= ProdottoDao.getInstance().findArticolo(String.valueOf(max));
                Assert.assertEquals(1, articoli.size());
                prodotto = (Prodotto) articoli.get(0);
                Assert.assertTrue(String.valueOf(max).equalsIgnoreCase(prodotto.getId().trim()));
                Assert.assertEquals("Nome prodotto test modificato", (prodotto.getNome()));
                Assert.assertEquals(1500.50f, prodotto.getPrezzo(), 0.0);
                Assert.assertEquals("1", prodotto.getProduttore().getId());
                Assert.assertEquals("1", prodotto.getCategoria().getId());
                Assert.assertTrue("Descrizone del prodotto inserito nel test modificato".equalsIgnoreCase(prodotto.getDescrizione().trim()));


                //test aggiornamento articolo composito
                articoli.clear();
                articoli=ArticoloCompositoDao.getInstance().findArticolo(String.valueOf(max+1));
                Assert.assertEquals(1, articoli.size());
                ArticoloComposito articoloComposito1=(ArticoloComposito) articoli.get(0);
                Assert.assertTrue("Nome articolo composito prova modifica".equalsIgnoreCase(articoloComposito1.getNome()));
                Assert.assertEquals(20.50f,articoloComposito1.getPrezzo(), 0.0);
                Assert.assertTrue("Descrizione articolo composito modificato".equalsIgnoreCase(articoloComposito1.getDescrizione()));
                Assert.assertEquals(1, articoloComposito1.getComposizione().size());
                Assert.assertEquals(3, Integer.parseInt(articoloComposito1.getComposizione().get(0).getArticolo().getId()));
                Assert.assertEquals(100, articoloComposito1.getComposizione().get(0).getQuantita());





                //test aggiornamento servizio
                articoli.clear();
                articoli=ServizioDao.getInstance().findArticolo(String.valueOf(max+2));
                Assert.assertEquals(1, articoli.size());
                Servizio servizio1= (Servizio) articoli.get(0);
                Assert.assertTrue("Nome servizio test modifica".equalsIgnoreCase(servizio1.getNome()));
                Assert.assertEquals(2000.00f,servizio1.getPrezzo(), 0.0);
                Assert.assertTrue("Descrizione servizio test modificata".equalsIgnoreCase(servizio1.getDescrizione()));



            }
        } catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }
    }

}
