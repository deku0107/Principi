package DBInterface;

import java.sql.*;


public class DBConnection implements IDbConnection{

    private static final DbUser dbUser= DbUser.getInstance();
    private static final DBConnection instance= new DBConnection();
    private static Connection conn;
    private static Statement statement;
    private static ResultSet rs;
    private static int rowCount;

    private DBConnection(){
        conn=null;
        statement=null;
        rs= null;
        rowCount=0;
        try {
            Class<?> cls= Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Ho caricato la classe " + cls.getName());
        }catch (ClassNotFoundException e) {
            System.out.println("Non trovo il driver JDBC " + e.getMessage());
        }
    }

    public static DBConnection getInstance(){

        try {
          conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+ dbUser.getSchemaName() + "?serverTimezone=UTC", dbUser.getUserName(), dbUser.getPassword());

        }catch (SQLException e) {
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }
        return instance;
    }

    @Override
    public ResultSet executeQuery(String sqlStatement) {
        try {
            statement=conn.createStatement();
            rs=statement.executeQuery(sqlStatement);
        }catch (SQLException e) {
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }
        return rs;
    }

    @Override
    public int executeUpdate(String sqlStatement) {
        try {
            statement=conn.createStatement();
            rowCount=statement.executeUpdate(sqlStatement);
        }catch (SQLException e) {
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        }

        return rowCount;
    }


    @Override
    public void close() {
        if(rs!=null){
            try {
                rs.close();
            }catch (SQLException e ){
                System.out.println("SQL exception:  " + e.getMessage());
                System.out.println("SQL state:  " + e.getSQLState());
                System.out.println("Vendor Error:  " + e.getErrorCode());
            }
            rs = null;
        }
        if(statement!=null){
            try {
                statement.close();
            }catch (SQLException e ){
                System.out.println("SQL exception:  " + e.getMessage());
                System.out.println("SQL state:  " + e.getSQLState());
                System.out.println("Vendor Error:  " + e.getErrorCode());
            }
            statement = null;
        }
        if(conn!=null){
            try {
                conn.close();
            }catch (SQLException e ){
                System.out.println("SQL exception:  " + e.getMessage());
                System.out.println("SQL state:  " + e.getSQLState());
                System.out.println("Vendor Error:  " + e.getErrorCode());
            }
        }
    }
}
