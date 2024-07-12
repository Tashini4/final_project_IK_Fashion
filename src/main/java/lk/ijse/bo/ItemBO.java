package lk.ijse.bo;

import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Item;
import lk.ijse.entity.OrderDetail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO{
    public List<ItemDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public boolean update(ItemDTO item) throws SQLException, ClassNotFoundException;

    public List<String> getIds() throws SQLException, ClassNotFoundException;

    public Item searchById(String id) throws SQLException, ClassNotFoundException ;
    public boolean update1(List<OrderDetail> odList) throws SQLException, ClassNotFoundException ;

    public boolean updateQty(String itemId, int qty) throws SQLException, ClassNotFoundException;
}
