package Dao;

import DBInterface.command.DbOperationeExecutor;
import DBInterface.command.IDbOperation;
import DBInterface.command.ReadOperation;


import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilityDao {

    private static final UtilityDao i = new UtilityDao();

    public static UtilityDao getInstance() {
        return i;
    }


    public int getMax(String table, String idName) {
        ResultSet rs;
        DbOperationeExecutor executor = new DbOperationeExecutor();
        IDbOperation readOp = new ReadOperation("SELECT max(" + idName + ") as i FROM mydb." + table + ";");

        rs = executor.executeOperation(readOp);
        int max;
        try {
            rs.next();
            if (rs.getRow() == 1) {
                max = rs.getInt("i");
                return max;
            }
        } catch (SQLException e) {
            System.out.println("SQL exception:  " + e.getMessage());
            System.out.println("SQL state:  " + e.getSQLState());
            System.out.println("Vendor Error:  " + e.getErrorCode());
        } catch (NullPointerException e) {
            System.out.println("Result set " + e.getMessage());
        }

        return -1;

    }

}
