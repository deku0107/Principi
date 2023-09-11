package Model;

import java.io.File;

public class Immagine {

    private File file;
    private String path;



    public Immagine (){


    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getPath() {

        if (path==null)
            return null;
        return path.replace("src/Resources/Immagini/", "");
    }

    public String getRootPath(){
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}


