package lk.ijse.dao;

import lk.ijse.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtil {
    public static <T>T execute(String sql, Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pvsm.setObject(i + 1, args[i]);

        }
        if (sql.startsWith("SELECT")){
            return (T) pvsm.executeQuery();
        }else{
            return (T) (Boolean) (pvsm.executeUpdate()>0);
        }
    }
}
