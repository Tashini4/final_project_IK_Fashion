/*package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;
import lk.ijse.model.Order;
import lk.ijse.model.tm.OrderTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {
    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO Order VALUES (?,?,?,?)";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pvsm.setString(1,order.getOrderId());
        pvsm.setString(2,order.getCustomerId());
        pvsm.setString(3,order.getPaymentId());
        pvsm.setString(4, String.valueOf(order.getOrderDate()));

        return pvsm.executeUpdate() > 0;

    }

    public static List<OrderTm> getAll() throws SQLException {
        String sql = "SELECT * FROM cust";

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
    }
}*/
