package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.PlaceOrder;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            boolean isPaymentUpdated = PaymentRepo.save(po.getPayment());
            if (isPaymentUpdated) {
                boolean isOrderSaved = OrderRepo.save(po.getOrder());
                if (isOrderSaved) {
                    boolean isQtyUpdated = ItemRepo.update1(po.getOdList());
                    if (isQtyUpdated) {
                        boolean isOrderItemSaved = OrderItemRepo.save(po.getOdList());
                        if (isOrderItemSaved) {

                            connection.commit();
                            return true;
                        }
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}




