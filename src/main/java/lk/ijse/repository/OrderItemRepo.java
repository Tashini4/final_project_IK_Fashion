package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.OrderItem;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemRepo {
    public static boolean save(List<OrderItem> odList) throws SQLException {
        for (OrderItem od : odList) {
            boolean isSaved = saveOrderItem(od);
            if(!isSaved) {
                return false;
            }
        }
        return true;

    }

    private static boolean saveOrderItem(OrderItem od) throws SQLException {
        String sql = "INSERT INTO OrderItem (itemId, orderId, qty,unitPrice,total) VALUES (?, ?, ?, ?)";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pvsm.setString(1, od.getItemId());
        pvsm.setString(2, od.getOrderId());
        pvsm.setInt(3, od.getQty());
        pvsm.setDouble(4, od.getUnitPrice());
        pvsm.setDouble(4, od.getTotal());



        return pvsm.executeUpdate() > 0;
    }
    }

