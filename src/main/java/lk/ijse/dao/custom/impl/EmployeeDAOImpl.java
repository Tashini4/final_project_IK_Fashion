package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.EmployeeDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.EmployeeDTO;
import lk.ijse.entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public  List<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employees");

        List<Employee> employeeList = new ArrayList<>();

        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);
            String gender = resultSet.getNString(6);

            Employee employee= new Employee(id,name,email,contact,address,gender);
            employeeList.add(employee);
        }
        return employeeList;
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM employees WHERE employeeId = ?",id);
    }

    @Override
    public  boolean save(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO employees VALUES(?, ?, ?, ? ,?,?)",employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeEmail(),
                employee.getEmployeeContact(),employee.getEmployeeAddress(),employee.getEmployeeGender());
    }

    @Override
    public  boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE employees SET employeeName = ?,employeeEmail = ?,  employeeContact = ? , employeeAddress = ? , employeeGender = ? WHERE employeeId = ?",
                employee.getEmployeeName(),employee.getEmployeeEmail(),employee.getEmployeeContact(),employee.getEmployeeAddress(),employee.getEmployeeGender(),employee.getEmployeeId());
    }
    @Override
    public  List<String> getIds() throws SQLException, ClassNotFoundException {
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = SQLUtil.execute("SELECT employeeId FROM employees");
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    @Override
    public  Employee searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employees WHERE employeeId = ?");
        if (resultSet.next()) {
            String employeeId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String contact = resultSet.getString(4);
            String address = resultSet.getString(5);
            String gender = resultSet.getString(6);



            return new Employee(employeeId,name,email,contact,address,gender);
        }
        return null;
    }

}
