package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InventoryDAO extends CrudDAO<Inventory> {
    public List<String> getIds() throws SQLException, ClassNotFoundException ;
    public Inventory searchById(String id) throws SQLException, ClassNotFoundException ;
}
