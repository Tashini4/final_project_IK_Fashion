package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;
import lk.ijse.model.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterRepo {
    public static List<Register> getAll() throws SQLException {
        String sql = "SELECT * FROM register";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        List<Register> registerList = new ArrayList<>();

        while (resultSet.next()) {
            String userId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String position = resultSet.getString(3);
            String password = resultSet.getString(4);


            Register register = new Register(userId, name, position, password);
            registerList.add(register);
        }
        return registerList;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM register WHERE registerId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setString(1, id);

        return pvsm.executeUpdate() > 0;
    }

    public static boolean save(Register register) throws SQLException {
        String sql = "INSERT INTO register (registerId, registerName, registerPosition, registerPassword) VALUES(?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);

        pvsm.setString(1, register.getRegisterId());
        pvsm.setString(2, register.getRegisterName());
        pvsm.setString(3, register.getRegisterPosition());
        pvsm.setString(4, register.getRegisterPassword());

        return pvsm.executeUpdate() > 0;
    }

    public static boolean update(Register register) throws SQLException {
        String sql = "UPDATE register SET registerName = ?, registerPosition = ?, registerPassword = ? WHERE registerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);

        pvsm.setString(1, register.getRegisterName());
        pvsm.setString(2, register.getRegisterPosition());
        pvsm.setString(3, register.getRegisterPassword());
        pvsm.setString(4, register.getRegisterId());

        return pvsm.executeUpdate() > 0;
    }
    public static Register searchById(String id) throws SQLException {
        String sql = "SELECT * FROM register WHERE registerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1,id);

        ResultSet resultSet = pvsm.executeQuery();
        if (resultSet.next()) {
            String registerId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String position = resultSet.getString(3);
            String password = resultSet.getString(4);


            Register register = new Register(registerId,name,position,password);

            return register;
        }

        return null;
    }
}
