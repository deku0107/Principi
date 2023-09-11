package Buisness.Immagini;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageDisplay {

    private JLabel imageLabel;
    public ImageDisplay(JLabel label, String path) {

            imageLabel=label;

        System.out.println("Path image dispaly " + path);

        if ( path!=null){
            BufferedImage image = loadImage(path); // Carica l'immagine

            if (image != null) {
                // Ridimensiona l'immagine
                int width = 200; // Larghezza desiderata
                int height = 200; // Altezza desiderata
                Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                imageLabel.setIcon(icon);
            }
            }else{
            imageLabel.setText("Immagine");
        }


    }

    // Metodo per caricare un'immagine (sostituisci con il tuo percorso all'immagine)
    private static BufferedImage loadImage(String path) {
        try {
            System.out.println("Nome del file " + "src/Resources/Immagini/" + path);
            return ImageIO.read(new File("src/Resources/Immagini/" + path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

