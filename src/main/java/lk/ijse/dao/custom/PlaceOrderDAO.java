package lk.ijse.dao.custom;

import javafx.scene.control.Alert;
import lk.ijse.dao.custom.impl.ItemDAOImpl;
import lk.ijse.dao.custom.impl.OrderDAOImpl;
import lk.ijse.dao.custom.impl.OrderDetailDAOImpl;
import lk.ijse.dao.custom.impl.PaymentDAOImpl;
import lk.ijse.db.DbConnection;
import lk.ijse.entity.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderDAO {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        ItemDAO itemDAO = new ItemDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        OrderDetailDAO orderDetailDAO = new OrderDetailDAOImpl();


        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPaymentUpdated = PaymentDAOImpl.save(po.getPayment());
            if (isPaymentUpdated) {
                boolean isOrderSaved = OrderDAOImpl.save(po.getOrder());
                if (isOrderSaved) {
                    boolean isQtyUpdated = itemDAO.update1(po.getOdList());
                    if (isQtyUpdated) {
                        boolean isOrderItemSaved = orderDetailDAO.save(po.getOdList());
                        if (isOrderItemSaved) {

                            connection.commit();
                            return true;
                        } //new Alert(Alert.AlertType.ERROR, "orderdetail not update").show();
                    }// new Alert(Alert.AlertType.ERROR, "Item not update").show();
                }// new Alert(Alert.AlertType.ERROR, "order not update").show();
            } //new Alert(Alert.AlertType.ERROR, "Payament not update").show();
            connection.rollback();
            return false;
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}




