package DBInterface.command;

import java.sql.ResultSet;

public interface IDbOperation {
    ResultSet execute();
    String getSql();
}
