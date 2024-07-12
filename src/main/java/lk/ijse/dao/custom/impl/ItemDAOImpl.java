package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderDetail;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override

    public List<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM items");

        List<Item> itemlist = new ArrayList<>();

        while (resultSet.next()) {
            String itemId = resultSet.getString(1);
            String description = resultSet.getString(2);
            String brand = resultSet.getString(3);
            String size = resultSet.getString(4);
            double price = Double.parseDouble(resultSet.getString(5));
            int qtyOnHand = Integer.parseInt(resultSet.getString(6));
            String inventoryId = resultSet.getString(7);

            Item item = new Item(itemId, description, brand, size, price, qtyOnHand, inventoryId);
            itemlist.add(item);
        }
        return itemlist;
    }

    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO items VALUES(?, ?, ?, ? , ?, ? ,?)", item.getItemId(),
                item.getDescription(), item.getBrand(), item.getSize(), item.getPrice(), item.getQtyOnHand(), item.getInventoryId());
    }

    @Override

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM items WHERE itemId = ?", id);
    }

    @Override

    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE items SET Description = ?, brand = ? , size = ? , price = ?, qtyOnHand = ?, inventoryId = ? WHERE itemId = ?",
                item.getDescription(), item.getBrand(), item.getSize(), item.getPrice(), item.getQtyOnHand(), item.getInventoryId(), item.getItemId());
    }
@Override

    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT itemId FROM items");
        while (resultSet.next()) {
            String id = resultSet.getString("itemId");
            idList.add(id);
        }
        return idList;
    }
    @Override

    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SQLUtil.execute("SELECT * FROM items WHERE itemId = ?");

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
    @Override

    public  boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;

    }
@Override
    public boolean updateQty(String itemId, int qty) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE items SET qtyOnHand = qtyOnHand - ? WHERE itemId= ?",qty,itemId);
    }
    }



