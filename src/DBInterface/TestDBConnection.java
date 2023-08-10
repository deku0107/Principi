package DBInterface;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        IDbConnection connection = DBConnection.getInstance();
        try {
            ResultSet rs = connection.executeQuery("SELECT nome, cognome, username, email FROM utente");
            while(rs.next()){
                System.out.print(rs.getString("nome") + " ");
                System.out.print(rs.getString("cognome") + " ");
                System.out.print(rs.getString("username") + " ");
                System.out.println(rs.getString("email") + " ");
            }
        }catch (SQLException e){
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }finally {
            connection.close();
        }
    }
}
