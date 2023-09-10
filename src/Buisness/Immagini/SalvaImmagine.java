package Buisness.Immagini;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SalvaImmagine {

    String path;
    public SalvaImmagine(File file, String nome){

        File destinationeFolder= new File("src/Resources/Immagini/");



        try {
            // Copiare il file nell'immagine nella cartella di destinazione con il nuovo nome
            boolean check=false;
            int i=1;
            while (!check){
                File tm= new File(destinationeFolder+nome);
                if (tm.exists()){
                    nome=nome.concat("("+i+")");
                    i++;
                }else {
                    check=true;
                }

            }

            File newFile= new File(destinationeFolder, nome);
            Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Immagine salvata con successo con il nuovo nome nella cartella di destinazione.");
            path= destinationeFolder.getPath()+"\\"+nome;
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPath() {
        return path;
    }
}
