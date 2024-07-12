package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.CustomerDAO;
import lk.ijse.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO{
   CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
   @Override
    public List<CustomerDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Customer> customerList = customerDAO.getAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : customerList){
            customerDTOList.add(new CustomerDTO(customer.getCustomerId(),customer.getCustomerName(),customer.getCustomerEmail(),
                    customer.getCustomerContact(),customer.getCustomerAddress()));
        }
        return customerDTOList;
    }
   @Override
    public  boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),customerDTO.getCustomerEmail(),customerDTO.getCustomerContact(),
                customerDTO.getCustomerAddress()));
    }
    @Override
    public Customer searchById(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.searchById(id);
    }
   @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
       return customerDAO.delete(id);
    }
   @Override
    public  List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new ArrayList<>();
        List<String> ids = customerDAO.getIds();
        for (String getIds : ids){
            customerIds.add(getIds);
        }
        return customerIds;
    }
    /*public int getCustomer() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(*) AS customer_count FROM customers");

        if (resultSet.next()) {
            return resultSet.getInt("customer_count");
        }
        return 0;
    }*/
    @Override
    public  boolean save(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCustomerId(),customerDTO.getCustomerName(),customerDTO.getCustomerEmail(),customerDTO.getCustomerContact(),
                customerDTO.getCustomerAddress()));
    }

}
