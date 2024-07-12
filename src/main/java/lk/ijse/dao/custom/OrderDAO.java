package lk.ijse.dao.custom;

import lk.ijse.dao.CrudDAO;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dto.OrderDTO;
import lk.ijse.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface OrderDAO extends CrudDAO<Order> {
    public String getCurrentId() throws SQLException, ClassNotFoundException;

    public String getPayCurrentId() throws SQLException, ClassNotFoundException;

    public boolean save(Order order) throws SQLException, ClassNotFoundException;

    public Map<String, Integer> GetDailyOrderCounts() throws SQLException, ClassNotFoundException;


    public  Map<String, Integer> GetDailyIncome() throws SQLException, ClassNotFoundException;
}
