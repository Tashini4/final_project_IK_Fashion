package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.OrderDetailDAO;
import lk.ijse.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.dto.OrderDetailDTO;
import lk.ijse.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailDAO {
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDERDETAIL);

    @Override
    public boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.save(odList);

    }

    @Override

    public boolean saveOrderItem(OrderDetail od) throws SQLException, ClassNotFoundException {
        return orderDetailDAO.saveOrderItem(od);
    }
}

