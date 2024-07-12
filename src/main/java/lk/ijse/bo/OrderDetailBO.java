package lk.ijse.bo;

import lk.ijse.dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBO{

    public boolean save(List<OrderDetailDTO> odList) throws SQLException, ClassNotFoundException;

    public boolean saveOrderItem(OrderDetailDTO od) throws SQLException, ClassNotFoundException;
}
