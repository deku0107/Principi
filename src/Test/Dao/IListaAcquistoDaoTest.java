package Test.Dao;

import Dao.ListaDiAcquisto.ListaAcquistoDao;
import Dao.Prodotti.ProdottoDao;
import Dao.Prodotti.ServizioDao;
import Dao.Utenti.UtenteAcquirenteDao;
import Model.ListaDiAcquisto;
import Model.Prodotti.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class IListaAcquistoDaoTest {

    private ListaDiAcquisto listaDiAcquisto;

    @Before
    public void setUp(){

        listaDiAcquisto= new ListaDiAcquisto();
        listaDiAcquisto.setNome("Lista di prova test");

        Articolo articolo= ProdottoDao.getInstance().findArticolo("411");
        Articolo articolo1=ServizioDao.getInstance().findArticolo("205");

        listaDiAcquisto.setLista(articolo);
        listaDiAcquisto.setLista(articolo1);

        listaDiAcquisto.setId(String.valueOf(ListaAcquistoDao.getInstance().addLista(listaDiAcquisto, UtenteAcquirenteDao.getInstance().findById("1"))));

    }




    @After
    public  void tearDown(){

        ListaAcquistoDao.getInstance().removeLista(listaDiAcquisto);

    }


    @Test
    public void popolaListaTest(){
        ListaAcquistoDao.getInstance().updateLista(listaDiAcquisto);

        ListaDiAcquisto listaDiAcquisto1= ListaAcquistoDao.getInstance().getLista(UtenteAcquirenteDao.getInstance().findById("1"), "Lista di prova test" );
        Assert.assertTrue(listaDiAcquisto.getId().equalsIgnoreCase(listaDiAcquisto1.getId()));
        Assert.assertEquals(listaDiAcquisto.getLista().size(), listaDiAcquisto1.getLista().size());

        Articolo articolo1=ServizioDao.getInstance().findArticolo("205");
        listaDiAcquisto.setLista(articolo1);


        ListaAcquistoDao.getInstance().updateLista(listaDiAcquisto);
        listaDiAcquisto1= ListaAcquistoDao.getInstance().getLista(UtenteAcquirenteDao.getInstance().findById("1"), "Lista di prova test" );
        Assert.assertEquals(listaDiAcquisto.getLista().size(), listaDiAcquisto1.getLista().size());

        ListaAcquistoDao.getInstance().setPagamento(true, listaDiAcquisto);
        listaDiAcquisto.setPagamento(true);

        Assert.assertTrue( ListaAcquistoDao.getInstance().getLista(UtenteAcquirenteDao.getInstance().findById("1"), "Lista di prova test" ).isPagata());


    }
}
