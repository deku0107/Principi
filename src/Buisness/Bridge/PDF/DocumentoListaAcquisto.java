package Buisness.Bridge.PDF;

import Buisness.Bridge.Mail.InvioEmail;
import Buisness.Bridge.Mail.MailAPI;
import Model.ListaDiAcquisto;
import Model.Prodotti.Articolo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocumentoListaAcquisto extends Documento{
    private ListaDiAcquisto lista;
    private String path;

    public DocumentoListaAcquisto(ListaDiAcquisto lista, PdfAPI pdfAPI) {
        super(pdfAPI);
        this.lista = lista;
    }

    @Override
    public void esegui(String indirizzo) {

        List<Articolo> prodotti = lista.getLista();
       ArrayList <String> text = new ArrayList<>();
        Float prezzo=0F;

        for (Articolo p : prodotti) {
            text.add(p.getNome() + ", euro: "+ p.getPrezzo());
            prezzo+=p.getPrezzo();
        }

        text.add("Totale da pagare: " +prezzo+" euro");
        try {
            File tempFile = File.createTempFile("myshop"+lista.getNome(), ".pdf", new File("src/Resources/PDF"));
            path=tempFile.getAbsolutePath();
            pdfAPI.creaPdf(text, tempFile.getAbsolutePath());

            if(indirizzo!=null){
                InvioEmail invioEmail= new InvioEmail(new MailAPI());
                invioEmail.invia(indirizzo, lista.getNome(), "Lista d'acquisto da pagare", path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
