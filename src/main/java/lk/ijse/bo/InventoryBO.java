package lk.ijse.bo;

import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InventoryBO extends SuperBO {

    public List<String> getIds() throws SQLException, ClassNotFoundException ;
    public Inventory searchById(String id) throws SQLException, ClassNotFoundException ;
}
