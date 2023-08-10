package DBInterface.command;

import DBInterface.DBConnection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DbOperationeExecutor {

    private DBConnection c = DBConnection.getInstance();
    private final List<IDbOperation> dbOperationList= new ArrayList<>();

    public ResultSet executeOperation(IDbOperation dbOperation) {
        dbOperationList.add(dbOperation);
        return dbOperation.execute();
    }

    public int updateOperation(IDbOperation dbOperation) {
        return c.executeUpdate(dbOperation.getSql());
    }
}
