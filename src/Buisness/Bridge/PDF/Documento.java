package Buisness.Bridge.PDF;

public abstract class Documento {
    protected PdfAPI pdfAPI;

    public Documento(PdfAPI pdfAPI) {
        this.pdfAPI = pdfAPI;
    }

    public abstract void esegui(String indirizzo);
}
