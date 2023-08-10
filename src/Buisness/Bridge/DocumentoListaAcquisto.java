package Buisness.Bridge;

import Model.ListaDiAcquisto;
import Model.Prodotti.Articolo;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DocumentoListaAcquisto extends Documento{
    private ListaDiAcquisto lista;

    public DocumentoListaAcquisto(ListaDiAcquisto lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void invia(String indirizzo) {

        List<Articolo> prodotti = lista.getLista();
        String text = "";

        Iterator<Articolo> i = prodotti.iterator();
        while(i.hasNext()) {
            Articolo p = i.next();
            text += p.getNome()+", ";
        }

        try {
            File tempFile = File.createTempFile("src/Resources/PDF/myshop", ".pdf");
            System.out.println(tempFile);
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
