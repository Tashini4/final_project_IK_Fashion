package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Item;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {

    public static List<Item> getAll() throws SQLException {
        String sql = "SELECT * FROM items";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        List<Item> itemlist = new ArrayList<>();

        while(resultSet.next()){
            String itemId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String color = resultSet.getString(3);
            String size = resultSet.getString(4);
            String price = resultSet.getString(5);
            String inventoryId = resultSet.getString(6);

            Item item = new Item(itemId,description,color,size,price,inventoryId);
            itemlist.add(item);
        }
        return itemlist;
    }
    public static boolean add(Item item) throws SQLException {
        String sql = "INSERT INTO items VALUES(?, ?, ?, ? , ?, ?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, item.getItemId());
        pvsm.setObject(2, item.getDescription());
        pvsm.setObject(3, item.getColor());
        pvsm.setObject(4, item.getSize());
        pvsm.setObject(5, item.getPrice());
        pvsm.setObject(6, item.getInventoryId());

        return pvsm.executeUpdate() > 0;
    }

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM items WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, id);

        return pvsm.executeUpdate() > 0;
    }

    public static boolean update(Item item) throws SQLException {
        String sql = "UPDATE items SET itemDescription = ?, color = ? , size = ? , price = ?, inventoryId = ? WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, item.getItemId());
        pvsm.setObject(2, item.getDescription());
        pvsm.setObject(3, item.getColor());
        pvsm.setObject(4, item.getSize());
        pvsm.setObject(5, item.getPrice());
        pvsm.setObject(6, item.getInventoryId());

        return pvsm.executeUpdate() > 0;
    }
}

