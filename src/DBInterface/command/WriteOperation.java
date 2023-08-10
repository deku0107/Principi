package DBInterface.command;

import DBInterface.DBConnection;
import DBInterface.IDbConnection;

import java.sql.ResultSet;

public class WriteOperation implements IDbOperation {
    private IDbConnection conn = DBConnection.getInstance();
    private String sql;


    public WriteOperation(String sql) {
        this.sql = sql;
    }



    @Override
    public ResultSet execute() {
        ResultSet rs = conn.executeQuery(sql);
        return rs;
    }

    @Override
    public String getSql() {
        return sql;
    }


}
