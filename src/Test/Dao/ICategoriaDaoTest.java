package Test.Dao;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;
import Dao.Categorie.CategoriaDao;
import Dao.Categorie.ICategoriaDao;
import Dao.Prodotti.ArticoloCompositoDao;
import Dao.Prodotti.ProdottoDao;
import Dao.Prodotti.ServizioDao;
import Model.Categoria;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ICategoriaDaoTest {

    @Before
    public void setUp(){
        ICategoriaDao categoriaDao= CategoriaDao.getInstance();

        Categoria categoria= new Categoria();
        categoria.setNome("Test");
        categoria.setDescrizione("Test di ICategoriaDao");
        categoria.setCategoriaPadre(null);

        categoriaDao.addCategoria(categoria);

    }

    @After
    public void tearDown(){
        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(idcategoria) as i FROM mydb.categoria;");

        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if (rs.getRow() == 1) {
                max=rs.getInt("i");
                 CategoriaDao.getInstance().remove(String.valueOf(max));
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
    public void findById(){

        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(idcategoria) as i FROM mydb.categoria;");

        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if (rs.getRow() == 1) {
                max=rs.getInt("i");
                Categoria categoria= new Categoria();
                categoria.setNome("Test");
                categoria.setDescrizione("Test di ICategoriaDao");
                categoria.setCategoriaPadre(null);

                Categoria cat;
                cat=CategoriaDao.getInstance().findCategoria(String.valueOf(max));


                Assert.assertTrue("Test".equalsIgnoreCase(cat.getNome().trim()) );
                Assert.assertTrue("Test di ICategoriaDao".trim().equalsIgnoreCase(cat.getDescrizione().trim()) );
                Assert.assertTrue("1".equalsIgnoreCase(cat.getCategoriaPadre().getId()));


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
