package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Item;
import lk.ijse.model.OrderDetail;


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
            String brand = resultSet.getString(3);
            String size = resultSet.getString(4);
            double price = Double.parseDouble(resultSet.getString(5));
            int qtyOnHand = Integer.parseInt(resultSet.getString(6));
            String inventoryId = resultSet.getString(7);

            Item item = new Item(itemId,description,brand,size,price,qtyOnHand,inventoryId);
            itemlist.add(item);
        }
        return itemlist;
    }
    public static boolean add(Item item) throws SQLException {
        String sql = "INSERT INTO items VALUES(?, ?, ?, ? , ?, ? ,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, item.getItemId());
        pvsm.setObject(2, item.getDescription());
        pvsm.setObject(3, item.getBrand());
        pvsm.setObject(4, item.getSize());
        pvsm.setObject(5, item.getPrice());
        pvsm.setObject(6, item.getQtyOnHand());
        pvsm.setObject(7, item.getInventoryId());

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
        String sql = "UPDATE items SET Description = ?, brand = ? , size = ? , price = ?, qtyOnHand = ?, inventoryId = ? WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, item.getItemId());
        pvsm.setObject(2, item.getDescription());
        pvsm.setObject(3, item.getBrand());
        pvsm.setObject(4, item.getSize());
        pvsm.setObject(5, item.getPrice());
        pvsm.setObject(6, item.getQtyOnHand());
        pvsm.setObject(7, item.getInventoryId());

        return pvsm.executeUpdate() > 0;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT itemId FROM items";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pvsm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString("itemId");
            idList.add(id);
        }
        return idList;
    }

    public static Item searchById(String id) throws SQLException {
        String sql = "SELECT * FROM items WHERE itemId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setString(1, id);
        ResultSet resultSet= pvsm.executeQuery();

        if (resultSet.next()) {
            String itemId = resultSet.getString("itemId");
            String description = resultSet.getString("description");
            String brand = resultSet.getString("brand");
            String size = resultSet.getString("size");
            double price = Double.parseDouble(resultSet.getString("price"));
            int qtyOnHand = Integer.parseInt(resultSet.getString("qtyOnHand"));
            String inventoryId = resultSet.getString("inventoryId");

            Item item = new Item(itemId,description,brand,size,price,qtyOnHand,inventoryId);

            return item;
        }
        return null;
    }

    public static boolean update1(List<OrderDetail> odList) throws SQLException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;

    }

    private static boolean updateQty(String itemId, int qty) throws SQLException {
        String sql = "UPDATE items SET qty_on_hand = qty_on_hand - ? WHERE itemId= ?";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pvsm.setInt(1, qty);
        pvsm.setString(2, itemId);

        return pvsm.executeUpdate() > 0;
    }
    }



