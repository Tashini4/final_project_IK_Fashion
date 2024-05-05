package lk.ijse.repository;

import javafx.fxml.FXML;
import lk.ijse.db.DbConnection;
import lk.ijse.model.User.User;


import java.sql.*;

public class UserRepo {
    public static Boolean check(User user) throws SQLException {
        String sql = "SELECT password FROM users where userId = ?";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        pvsm.setObject(1, user.getUserName());
        ResultSet resultSet = pvsm.executeQuery();
        if (resultSet.next()) {
            String pw = resultSet.getNString(1);
            if (user.getPassword().equals(pw)) return true;
        }

        return false;
    }



    @FXML
    public static boolean saveUser(String userId, String userName, String password) throws SQLException {
        String sql = "INSERT INTO users VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);

        pvsm.setObject(1, userId);
        pvsm.setObject(2, userName);
        pvsm.setObject(3, password);

        return pvsm.executeUpdate() > 0;
    }
}





