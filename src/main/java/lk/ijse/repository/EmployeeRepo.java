package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employees";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

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

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM employees WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, id);

        return pvsm.executeUpdate() > 0;
    }

    public static boolean save(Employee employee) throws SQLException {
        String sql = "INSERT INTO employees VALUES(?, ?, ?, ? ,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, employee.getEmployeeId());
        pvsm.setObject(2, employee.getEmployeeName());
        pvsm.setObject(3, employee.getEmployeeEmail());
        pvsm.setObject(4, employee.getEmployeeContact());
        pvsm.setObject(5, employee.getEmployeeAddress());
        pvsm.setObject(6, employee.getEmployeeGender());


        return pvsm.executeUpdate() > 0;
    }

    public static boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employees SET employeeName = ?, employeeAddress = ?, employeeContact = ? , employeeEmail = ? , employeeGender = ? WHERE employeeId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, employee.getEmployeeName());
        pvsm.setObject(2, employee.getEmployeeAddress());
        pvsm.setObject(3, employee.getEmployeeContact());
        pvsm.setObject(4, employee.getEmployeeEmail());
        pvsm.setObject(5, employee.getEmployeeGender());
        pvsm.setObject(6, employee.getEmployeeId());


        return pvsm.executeUpdate() > 0;
    }
    public static List<String> getIds() throws SQLException {
        String sql = "SELECT employeeId FROM employees";
        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        List<String> idList = new ArrayList<>();
        ResultSet resultSet = pvsm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
    public static Employee searchById(String id) throws SQLException {
        String sql = "SELECT * FROM employees WHERE employeeId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, id);
        ResultSet resultSet = pvsm.executeQuery();
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
