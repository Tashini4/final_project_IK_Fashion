package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Item;


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

    public static boolean save(Item item) {
        return false;
    }
}

