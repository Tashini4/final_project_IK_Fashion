package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static boolean save(Payment payment) throws SQLException {
        String sql = "INSERT INTO payments VALUES(?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1,payment.getPaymentId());
        pvsm.setObject(2,payment.getPaymentAmount());
        pvsm.setObject(3, payment.getPaymentDate());


        return pvsm.executeUpdate() > 0;
    }
    public static Payment searchById(String id) throws SQLException {
        String sql = "SELECT * FROM payments WHERE paymentId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1,id);
        ResultSet resultSet = pvsm.executeQuery();
        if (resultSet.next()){
            String paymentId = resultSet.getString(1);
            int Amount = Integer.parseInt(resultSet.getString(2));
            Date date = Date.valueOf(resultSet.getString(3));


            Payment payment = new Payment(paymentId,Amount,date);
            return payment;

        }
        return null;
    }
    public static List<Payment> getAll() throws SQLException {
        String sql = "SELECT * FROM payments";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pvsm.executeQuery();

        List<Payment> paymentList = new ArrayList<>();

        while (resultSet.next()) {
            String paymentId = resultSet.getString(1);
            int Amount = Integer.parseInt(resultSet.getString(2));
            Date date = Date.valueOf(resultSet.getString(3));

            Payment payment = new Payment(paymentId,Amount,date);
            paymentList.add(payment);
        }
        return paymentList;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT paymentId FROM payments";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pvsm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

}
