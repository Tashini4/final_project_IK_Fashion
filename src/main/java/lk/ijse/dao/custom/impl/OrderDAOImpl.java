package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.Order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOImpl implements OrderDAO {

    public  String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT MAX(CAST(SUBSTRING(orderId, 2) AS UNSIGNED)) AS HighestOrderId FROM orders");
        if (resultSet.next()) {
            String orderId = resultSet.getString(1);
            return orderId;
        }
        return null;
    }

    public  String getPayCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT paymentId FROM payments ORDER BY paymentId LIMIT 1");
        if (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            return paymentId;
        }
        return null;
    }

    public  boolean save(Order order) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO orders VALUES(?, ?, ?,?)",order.getOrderId(),order.getOrderDate(),order.getCustomerId(),
                order.getPaymentId());
    }

    public  Map<String, Integer> GetDailyOrderCounts() throws SQLException, ClassNotFoundException {

            /*String sql = "SELECT orderDate, COUNT(OrderId) AS orderCount FROM orders GROUP BY orderDate ";
            Map<String, Integer> orderDetails = new HashMap<>();

            PreparedStatement pvsm = DbConnection.getConnection().prepareStatement(sql);
            Map<String, Integer> orderDetails = new HashMap<>();
*/
        Map<String, Integer> orderDetails = new HashMap<>();
            ResultSet resultSet =  SQLUtil.execute("SELECT orderDate, COUNT(OrderId) AS orderCount FROM orders GROUP BY orderDate ");

            while (resultSet.next()) {

                orderDetails.put(
                        resultSet.getString("OrderDate"),
                        resultSet.getInt("OrderCount")
                );
            }

            return orderDetails;
        }

    public  Map<String, Integer> GetDailyIncome() throws SQLException, ClassNotFoundException {
        //String sql = "SELECT orderDate, SUM(orderId) AS totalIncome FROM orders GROUP BY orderDate";
        Map<String, Integer> orderDetails = new HashMap<>();

       // PreparedStatement pvsm = DbConnection.getConnection().prepareStatement(sql);
        ResultSet resultSet = SQLUtil.execute("SELECT orderDate, SUM(orderId) AS totalIncome FROM orders GROUP BY orderDate");

        while (resultSet.next()) {
            orderDetails.put(
                    resultSet.getString("orderDate"),
                    resultSet.getInt("totalIncome")
            );
        }

        return orderDetails;
    }
}




