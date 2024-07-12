package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.ItemDAO;
import lk.ijse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderDetail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBO {

    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    @Override
    public List<ItemDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Item> itemList = itemDAO.getAll();
        List<ItemDTO> itemDTOList = new ArrayList<>();
        for (Item item : itemList) {
            itemDTOList.add(new ItemDTO(item.getItemId(), item.getDescription(), item.getBrand(), item.getSize(), item.getPrice(), item.getQtyOnHand(), item.getInventoryId()));
        }
        return itemDTOList;
    }
    @Override
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item( itemDTO.getItemId(),
                itemDTO.getDescription(), itemDTO.getBrand(), itemDTO.getSize(), itemDTO.getPrice(), itemDTO.getQtyOnHand(), itemDTO.getInventoryId()));
    }
    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }
    @Override
    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item( item.getDescription(), item.getBrand(), item.getSize(), item.getPrice(), item.getQtyOnHand(), item.getInventoryId(), item.getItemId()));
    }
    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> itemIds = new ArrayList<>();
        List<String> ids = itemDAO.getIds();
        for (String getIds : ids) {
            itemIds.add(getIds);
        }
        return itemIds;
    }
    @Override
    public Item searchById(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.searchById(id);
    }

    public boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        for (OrderDetail od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;

    }
    @Override
    public boolean updateQty(String itemId, int qty) throws SQLException, ClassNotFoundException {

        return itemDAO.updateQty(itemId, qty);
    }

}
