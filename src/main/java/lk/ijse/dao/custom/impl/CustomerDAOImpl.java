package lk.ijse.dao.custom.impl;

import javafx.fxml.FXML;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public  List<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customers");

        List<Customer> customerlist = new ArrayList<>();

        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);

            Customer customer = new Customer(id,name,email,contact,address);
            customerlist.add(customer);
        }
        return customerlist;
    }
@Override
    public  boolean update(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE customers SET customerName = ?, customerEmail = ?, customerContact = ? , customerAddress = ? WHERE customerId = ?",customer.getCustomerName(),
                customer.getCustomerEmail(),customer.getCustomerContact(),customer.getCustomerAddress(),customer.getCustomerId());
    }
@Override
    public  Customer searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customers WHERE customerId = ?");
        if (resultSet.next()) {
            String cus_id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email= resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);


            Customer customer = new Customer(cus_id, name,email,contact,address);

            return customer;
        }

        return null;
    }
@Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customers WHERE customerId = ?",id);
    }
@Override
    public  List<String> getIds() throws SQLException, ClassNotFoundException {

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM customers");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }



    public int getCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS customer_count FROM customers");

        if (resultSet.next()) {
            return resultSet.getInt("customer_count");
        }
        return 0;
    }
    @Override
    public  boolean save(Customer customer) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "INSERT INTO customers VALUES(?, ?, ?, ? , ?)",customer.getCustomerId(),customer.getCustomerName(),
                customer.getCustomerEmail(),customer.getCustomerContact(),customer.getCustomerAddress());
    }

}
