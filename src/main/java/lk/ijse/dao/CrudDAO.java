package lk.ijse.dao;

import lk.ijse.dto.CustomerDTO;
import lk.ijse.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T>  {
    public List<T> getAll() throws SQLException, ClassNotFoundException ;

    public  boolean update(T dto) throws SQLException, ClassNotFoundException ;

    public T searchById(String id) throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException ;

    public  boolean save(T dto) throws SQLException, ClassNotFoundException ;
    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

}
