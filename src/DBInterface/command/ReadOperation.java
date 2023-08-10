package DBInterface.command;

import DBInterface.DBConnection;
import DBInterface.IDbConnection;

import java.sql.ResultSet;

public class ReadOperation implements IDbOperation{
    private IDbConnection conn = DBConnection.getInstance();
    private String sql;

    public ReadOperation(String sql) {
        this.sql = sql;
    }


    @Override
    public ResultSet execute() {
        return conn.executeQuery(sql);
    }

    @Override
    public String getSql() {
        return sql;
    }
}
