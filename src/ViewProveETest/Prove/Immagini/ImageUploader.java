package ViewProveETest.Prove.Immagini;

import DBInterface.DBConnection;
import org.apache.commons.io.IOUtils;

import javax.sql.rowset.serial.SerialBlob;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageUploader {

    private static ResultSet rs;
    private static int rowCount;

    public static void main(String[] args)  {


        try {
            File file= new File("src/Resources/Logo.jpg");
            FileInputStream inputStream = new FileInputStream(file);
            System.out.println(inputStream.readAllBytes().length);
            System.out.println(file.length());

            byte[] bytes = inputStream.readAllBytes();

            // Create a BLOB object from the byte array
            SerialBlob blob = new SerialBlob(bytes);

            // Close the input stream
            inputStream.close();

            PreparedStatement preparedStatement=DBConnection.getInstance().getConn().prepareStatement("INSERT INTO `mydb`.`immagine` (`immagine`) VALUES (?);");
            preparedStatement.setBlob(1, blob);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            DBConnection.getInstance().getConn().close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


    }
}
