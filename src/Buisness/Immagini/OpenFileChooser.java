package Buisness.Immagini;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class OpenFileChooser {

    private File file;

    public OpenFileChooser(){

        try{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new ImgFileFilter());
        int n = fileChooser.showOpenDialog(null);
        if (n == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            BufferedReader read = new BufferedReader(new FileReader(file));
            String line = read.readLine();
            read.close();
        }
    } catch (Exception ex) {
        ex.printStackTrace();

    }

    }

    public File getFile() {
        return file;
    }
}


class ImgFileFilter extends FileFilter {

    private final String[] okFileExtensions = new String[] { "jpg", "jpeg", "png", "gif" };

    public boolean accept(File file) {
        if (file.isDirectory()) return true;
        for (String extension : okFileExtensions) {
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public String getDescription() {
        return "Immagini";
    }




}

