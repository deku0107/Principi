package Test.Dao;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import Dao.Utenti.IUtentiDao;
import Dao.Utenti.ManagerDao;
import Dao.Utenti.UtenteAcquirenteDao;
import Model.Data;
import Model.Utenti.Manager;
import Model.Utenti.Utente;
import Model.Utenti.UtenteAcquirente;
import org.junit.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IUtentiDaoTest {

    @Before
    public void setUp(){
        IUtentiDao utenteDao = UtenteAcquirenteDao.getInstance();
        UtenteAcquirente u = new UtenteAcquirente();
        u.setNome("Valentino");
        u.setCognome("Rossi");
        u.setUsername("vr46");
        u.setTelefono("3333333333");
        u.setEmail("vale@vr49.it");
        Data data= new Data();
        data.setData("2001-7-4");
        u.setDataDiNascita(data);
        u.setIndirizzo("via");
        u.setCitta("Lecce");
        utenteDao.add(u, "46");


        utenteDao = ManagerDao.getInstance();
        Manager m = new Manager();
        m.setNome("Valentino");
        m.setCognome("Rossi");
        m.setUsername("vr46");
        m.setTelefono("3333333333");
        m.setEmail("vale@vr49.it");

        data= new Data();
        data.setData("2001-7-4");
        m.setDataDiNascita(data);



        data= new Data();
        data.setData("2022-1-1");
        m.setInizioIncarico(data);


        data= new Data();
        data.setData("2023-1-1");
        m.setFineIncarico(data);

        m.setIndirizzo("via");
        m.setCitta("Lecce");
        utenteDao.add(m, "46");
    }

    @After
    public void tearDown(){
        IUtentiDao utenteDao = UtenteAcquirenteDao.getInstance();
        utenteDao.removeByUsername("vr46");

        utenteDao= ManagerDao.getInstance();
        utenteDao.removeByUsername("vr46");
    }

    @Test
    public void findByUsernameTest(){
        IUtentiDao utenteDao= UtenteAcquirenteDao.getInstance();
        Utente utente= utenteDao.findByUsername("vr46");

        Assert.assertEquals("Valentino", utente.getNome());
        Assert.assertEquals("Rossi", utente.getCognome());
        Assert.assertEquals("vr46", utente.getUsername());
        Assert.assertEquals("3333333333", utente.getTelefono());
        Assert.assertEquals("vale@vr49.it", utente.getEmail());
        Assert.assertEquals("via", utente.getIndirizzo());
        Assert.assertEquals("Lecce", utente.getCitta());
        Assert.assertEquals("2001-7-4", utente.getDataDiNascita().getDataDB());
    }

    @Test
    public void findByIdManagerTest(){
        IUtentiDao utenteDao= ManagerDao.getInstance();
        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(idmanager) as i FROM mydb.manager;");

        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if(rs.getRow()==1){
                max= rs.getInt("i");
                Manager utente= ManagerDao.getInstance().findById(String.valueOf(max));

                Assert.assertEquals("Valentino", utente.getNome());
                Assert.assertEquals("Rossi", utente.getCognome());
                Assert.assertEquals("vr46", utente.getUsername());
                Assert.assertEquals("3333333333", utente.getTelefono());
                Assert.assertEquals("vale@vr49.it", utente.getEmail());
                Assert.assertEquals("via", utente.getIndirizzo());
                Assert.assertEquals("Lecce", utente.getCitta());
                System.out.println("Nascita " + utente.getDataDiNascita().getDataDB());
                Assert.assertEquals("2001-7-4", utente.getDataDiNascita().getDataDB());

                Assert.assertEquals("2022-1-1", utente.getInizioIncarico().getDataDB());
                Assert.assertEquals("2023-1-1", utente.getFineIncarico().getDataDB());
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }


    }
    @Test
    public void findByIdTest(){
        IUtentiDao utenteDao= UtenteAcquirenteDao.getInstance();
        Utente utente= utenteDao.findById("1");

        Assert.assertEquals("Francesco", utente.getNome());
        Assert.assertEquals("Grassi", utente.getCognome());
        Assert.assertEquals("Franco", utente.getUsername());
        Assert.assertEquals("3294375999", utente.getTelefono());
        Assert.assertEquals("fragrassi825@gmail.com", utente.getEmail());
        Assert.assertEquals("via di prova", utente.getIndirizzo());
        Assert.assertEquals("Lecce", utente.getCitta());
        Assert.assertEquals("2001-7-4", utente.getDataDiNascita().getDataDB());
    }

    @Test
    public void findAllTest(){
        UtenteAcquirenteDao ud= UtenteAcquirenteDao.getInstance();
        ArrayList<Utente> utenti = ud.findAll();

        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT count(id) as i FROM mydb.utente_acquirente;");
        rs = executor.executeOperation(readOp);
        int righe;
        try {
            rs.next();
            if(rs.getRow()==1){
                righe= rs.getInt("i");
                Assert.assertEquals(righe, utenti.size());
                for( Utente u : utenti){
                    findByUsernameTest();
                }
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }


    }


 /*  @Test
    public void updateByIdTest(){

        IUtenteDao utenteDao = UtenteDao.getInstance();
        Utente u = new Utente();
        u.setNome("Valentina");
        u.setCognome("Rosso");
        u.setUserName("vr46");

        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation lastIdOp = new ReadOperation("SELECT max(idutente) as m from mydb.utente;");
        ResultSet rs = executor.executeOperation(lastIdOp);
        int id;
        try {
            rs.next();
            if(rs.getRow()==1){
                id= rs.getInt("m");
                UtenteDao ud= UtenteDao.getInstance();
                u.setIdUtente(id);
                int rc= ud.update(u);
                Assert.assertEquals(1, rc);
            }
        }catch (SQLException e ){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }

        int rc= utenteDao.update(u);
        u=utenteDao.findByUsername("vr46");
        Assert.assertEquals(1,rc);
        Assert.assertTrue(u.getNome().equalsIgnoreCase("Valentina"));

    }
    */

   @Test
    public void updateByUsernameTest(){

        IUtentiDao utenteDao = UtenteAcquirenteDao.getInstance();
       UtenteAcquirente u = new UtenteAcquirente();
       u.setNome("Valentina");
       u.setCognome("Rossa");
       u.setUsername("vr46");
       u.setTelefono("3333333334");
       u.setEmail("vale@vr49.com");
       Data data= new Data();
       data.setData("2001-7-5");
       u.setDataDiNascita(data);
       u.setIndirizzo("via 1");
       u.setCitta("Lecce s");
       utenteDao.update(u);

       utenteDao= UtenteAcquirenteDao.getInstance();
       Utente utente= utenteDao.findByUsername("vr46");
       Assert.assertEquals("Valentina", utente.getNome());
       Assert.assertEquals("Rossa", utente.getCognome());
       Assert.assertEquals("vr46", utente.getUsername());
       Assert.assertEquals("3333333334", utente.getTelefono());
       Assert.assertEquals("vale@vr49.com", utente.getEmail());
       Assert.assertEquals("via 1", utente.getIndirizzo());
       Assert.assertEquals("Lecce s", utente.getCitta());
       Assert.assertEquals("2001-7-5", utente.getDataDiNascita().getDataDB());


    }


}


