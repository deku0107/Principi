package Buisness.Bridge.PDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.util.ArrayList;

public class PdfBoxAPI implements PdfAPI{
    @Override
    public void creaPdf(ArrayList<String> text, String outfile) {

        try (PDDocument doc = new PDDocument())
        {
            PDPage page = new PDPage();
            doc.addPage(page);


            PDFont font = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
            {
                contents.beginText();
                contents.setFont(font, 12);
                contents.newLineAtOffset(100, 700);
                contents.setLeading(14.5f);
                for (String s:text) {
                    contents.showText(s);
                    contents.newLine();
                }
                contents.endText();
            }

            doc.save(outfile);
            System.out.println("Posizione file" + outfile);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}

