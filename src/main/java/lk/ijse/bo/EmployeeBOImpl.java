package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.dao.custom.impl.EmployeeDAOImpl;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    @Override
    public List<EmployeeDTO> getAll() throws SQLException, ClassNotFoundException {
       List<Employee> employeeList = employeeDAO.getAll();
       List<EmployeeDTO> employeeDTOList = new ArrayList<>();
       for (Employee employee : employeeList){
           employeeDTOList.add(new EmployeeDTO(employee.getEmployeeId(), employee.getEmployeeName(),
                   employee.getEmployeeEmail(), employee.getEmployeeContact(), employee.getEmployeeAddress(), employee.getEmployeeGender()));
       }
       return employeeDTOList;
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public  boolean save(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getEmployeeId(), employeeDTO.getEmployeeName(),employeeDTO.getEmployeeEmail(),
                employeeDTO.getEmployeeContact(),employeeDTO.getEmployeeAddress(),employeeDTO.getEmployeeGender()));
    }

    @Override
    public  boolean update(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getEmployeeName(),employeeDTO.getEmployeeEmail(),
                employeeDTO.getEmployeeContact(),employeeDTO.getEmployeeAddress(),employeeDTO.getEmployeeGender(),employeeDTO.getEmployeeId()));
    }
    @Override
    public  List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> employeeIds = new ArrayList<>();
        List<String> ids = employeeDAO.getIds();
        for (String getIds : ids){
            employeeIds.add(getIds);
        }
        return employeeIds;
    }
    @Override
    public Employee searchById(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.searchById(id);
    }
}
