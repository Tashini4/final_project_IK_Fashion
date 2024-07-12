package lk.ijse.bo;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    public  boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException ;

    public Customer searchById(String id) throws SQLException, ClassNotFoundException ;
    public  boolean delete(String id) throws SQLException, ClassNotFoundException;
    public  List<String> getIds() throws SQLException, ClassNotFoundException;
    public  boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    /*public int getCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS customer_count FROM customers");

        if (resultSet.next()) {
            return resultSet.getInt("customer_count");
        }
        return 0;
    }*/
}
