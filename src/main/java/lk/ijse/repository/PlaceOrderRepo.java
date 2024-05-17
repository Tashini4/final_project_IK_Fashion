package lk.ijse.repository;

import javafx.scene.control.Alert;
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
                        boolean isOrderItemSaved = OrderDetailRepo.save(po.getOdList());
                        if (isOrderItemSaved) {

                            connection.commit();
                            return true;
                        } new Alert(Alert.AlertType.ERROR, "orderdetail not update").show();
                    } new Alert(Alert.AlertType.ERROR, "Item not update").show();
                } new Alert(Alert.AlertType.ERROR, "order not update").show();
            } new Alert(Alert.AlertType.ERROR, "Payament not update").show();
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




