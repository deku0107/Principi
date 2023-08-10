package DBInterface;

import java.sql.ResultSet;

public interface IDbConnection {
    ResultSet executeQuery(String sqlStatement);
    int executeUpdate(String sqlStatement);
    void close();
}
