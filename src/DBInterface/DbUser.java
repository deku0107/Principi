package DBInterface;

public class DbUser {

    private static DbUser dbUser = new DbUser();
    private String schemaName;
    private String password;
    private String userName;
    private String url;

    private DbUser() {
        schemaName = "mydb";
        password = "wakandaforever";
        userName = "root";
       // url="jdbc:mysql://database-myshop.cupvd5piuera.us-east-1.rds.amazonaws.com/";
       url="jdbc:mysql://localhost/";
    }

    public static DbUser getInstance() {
        return dbUser;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public String getUrl(){return url;}
}
