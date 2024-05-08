package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRepo {

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pvsm.executeQuery();
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public static String getPayCurrentId() throws SQLException {
        String sql = "SELECT paymentId FROM payments ORDER BY paymentId LIMIT 1";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pvsm.executeQuery();
        if(resultSet.next()) {
            String paymentId = resultSet.getString(1);
            return paymentId;
        }
        return null;
    }

    public static boolean save(Order order) throws SQLException {
        String sql = "INSERT INTO orders VALUES(?, ?, ?,?)";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pvsm.setString(1, order.getOrderId());
        pvsm.setString(2, order.getCustomerId());
        pvsm.setString(3, order.getPaymentId());
        pvsm.setDate(4, order.getOrderDate());

        return pvsm.executeUpdate() > 0;
    }
}


