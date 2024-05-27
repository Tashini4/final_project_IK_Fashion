package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderRepo {

    public static String getCurrentId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(orderId, 2) AS UNSIGNED)) AS HighestOrderId FROM orders";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pvsm.executeQuery();
        if (resultSet.next()) {
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
        if (resultSet.next()) {
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
        pvsm.setDate(2, order.getOrderDate());
        pvsm.setString(3, order.getCustomerId());
        pvsm.setString(4, order.getPaymentId());

        return pvsm.executeUpdate() > 0;
    }

    public static Map<String, Integer> GetDailyOrderCounts() throws SQLException {

            String sql = "SELECT orderDate, COUNT(OrderId) AS orderCount FROM orders GROUP BY orderDate ";
            Map<String, Integer> orderDetails = new HashMap<>();

            PreparedStatement pvsm = DbConnection.getConnection().prepareStatement(sql);

            ResultSet resultSet = pvsm.executeQuery();

            while (resultSet.next()) {

                orderDetails.put(
                        resultSet.getString("OrderDate"),
                        resultSet.getInt("OrderCount")
                );
            }

            return orderDetails;
        }

    public static Map<String, Integer> GetDailyIncome() throws SQLException {
        String sql = "SELECT orderDate, SUM(orderId) AS totalIncome FROM orders GROUP BY orderDate";
        Map<String, Integer> orderDetails = new HashMap<>();

        PreparedStatement pvsm = DbConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        while (resultSet.next()) {
            orderDetails.put(
                    resultSet.getString("orderDate"),
                    resultSet.getInt("totalIncome")
            );
        }

        return orderDetails;
    }
}




