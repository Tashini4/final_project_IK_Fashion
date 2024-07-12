package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.InventoryDAO;
import lk.ijse.dao.custom.impl.InventoryDAOImpl;
import lk.ijse.entity.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventoryBoImpl implements InventoryBO {
    InventoryDAO inventoryDAO = (InventoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.INVENTORY);

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> inventoryIds = new ArrayList<>();
        List<String> ids = inventoryDAO.getIds();
        for (String getIds : ids) {
            inventoryIds.add(getIds);
        }
        return inventoryIds;
    }

    @Override
    public Inventory searchById(String id) throws SQLException, ClassNotFoundException {
        return inventoryDAO.searchById(id);
    }
}
