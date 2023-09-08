package Test.Buisness;

import Buisness.Bridge.PDF.DocumentoListaAcquisto;
import Buisness.Bridge.PDF.PdfBoxAPI;
import Model.ListaDiAcquisto;
import Model.Prodotti.Articolo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PDFTest {

    @Test
    public void pdfTest(){

        Articolo a1= new Articolo();
        a1.setNome("A1");
        a1.setPrezzo(10F);

        Articolo a2= new Articolo();
        a2.setNome("A2");
        a2.setPrezzo(20F);

        Articolo a3= new Articolo();
        a3.setNome("A3");
        a3.setPrezzo(30F);


        ArrayList<Articolo> articoloList= new ArrayList<>();
        articoloList.add(a1);
        articoloList.add(a2);
        articoloList.add(a3);


        ListaDiAcquisto listaDiAcquisto= new ListaDiAcquisto();
        listaDiAcquisto.setNome("Lista1");
        listaDiAcquisto.setLista(articoloList);

        System.out.println("Lista 1 = " + listaDiAcquisto.getLista().get(0).getNome());
        System.out.println("Lista 2 = " + listaDiAcquisto.getLista().get(1).getNome());
        System.out.println("Lista 3 = " + listaDiAcquisto.getLista().get(2).getNome());
        DocumentoListaAcquisto documentoListaAcquisto= new DocumentoListaAcquisto(listaDiAcquisto, new PdfBoxAPI());
        documentoListaAcquisto.esegui("fragrassi825@gmail.com");

    }
}
