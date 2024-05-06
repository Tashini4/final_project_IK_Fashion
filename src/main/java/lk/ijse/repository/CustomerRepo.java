package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customers";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        List<Customer> cuslist = new ArrayList<>();

        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);

            Customer customer = new Customer(id,name,email,contact,address);
            cuslist.add(customer);
        }
        return cuslist;
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customers SET customerName = ?, customerAddress = ?, customerContact = ? , customerEmail = ? WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, customer.getName());
        pvsm.setObject(2, customer.getAddress());
        pvsm.setObject(3, customer.getContact());
        pvsm.setObject(4, customer.getEmail());
        pvsm.setObject(5, customer.getId());

        return pvsm.executeUpdate() > 0;
    }

    public static Customer searchById(String id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email= resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);


            Customer customer = new Customer(cus_id, name,email,contact,address);

            return customer;
        }

        return null;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM customers WHERE customerId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }


    public int getCustomer() throws SQLException {
        String sql = "SELECT COUNT(*) AS customerCount FROM customers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getInt("customerCount");
        }
        return 0;
    }
    public static boolean save(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers VALUES(?, ?, ?, ? , ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, customer.getId());
        pvsm.setObject(2, customer.getName());
        pvsm.setObject(3, customer.getEmail());
        pvsm.setObject(4, customer.getContact());
        pvsm.setObject(5, customer.getAddress());


        return pvsm.executeUpdate() > 0;
    }

}
