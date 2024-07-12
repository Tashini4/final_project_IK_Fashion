package lk.ijse.bo;

import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO{

    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException;


    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException ;


    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public  List<String> getIds() throws SQLException, ClassNotFoundException ;

    public Employee searchById(String id) throws SQLException, ClassNotFoundException ;
}