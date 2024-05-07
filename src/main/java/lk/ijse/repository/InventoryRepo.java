package lk.ijse.repository;


import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.Inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryRepo {
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT inventoryId FROM inventory";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = pvsm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static Inventory searchById(String id) throws SQLException {
        String sql = "SELECT * FROM inventory WHERE inventoryId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, id);
        ResultSet resultSet = pvsm.executeQuery();
        if (resultSet.next()) {
            String inventoryId = resultSet.getString(1);
            String qty = resultSet.getString(2);
            String costPrice = resultSet.getString(3);
            String sellingPrice = resultSet.getString(4);
            String  supplierId = resultSet.getString(5);

            return new Inventory(inventoryId,qty,costPrice,sellingPrice,supplierId);
        }
        return null;
    }
    }



