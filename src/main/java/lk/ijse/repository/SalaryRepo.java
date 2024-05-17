package lk.ijse.repository;

import lk.ijse.db.DbConnection;

import lk.ijse.model.Salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryRepo {
    public static List<Salary> getAll() throws SQLException {
        String sql = "SELECT * FROM salary";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        List<Salary> salaryList = new ArrayList<>();

        while (resultSet.next()) {
            String salaryId = resultSet.getString(1);
            String date = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String employeeId = resultSet.getString(4);


            Salary salary = new Salary(salaryId, date, amount, employeeId);
            salaryList.add(salary);
        }
        return salaryList;
    }

    public static boolean update(Salary salary) throws SQLException {
        String sql = "UPDATE salary SET salaryDate = ?, salaryAmount = ? ,employeeId = ? WHERE salaryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(2, salary.getSalaryDate());
        pvsm.setObject(3, salary.getSalaryAmount());
        pvsm.setObject(4, salary.getEmployeeId());
        pvsm.setObject(1, salary.getSalaryId());

        return pvsm.executeUpdate() > 0;
    }

    public static boolean save(Salary salary) throws SQLException {
        String sql = "INSERT INTO salary VALUES (?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(2, salary.getSalaryDate());
        pvsm.setObject(3, salary.getSalaryAmount());
        pvsm.setObject(4, salary.getEmployeeId());
        pvsm.setObject(1, salary.getSalaryId());


        return pvsm.executeUpdate() > 0;
    }

    public static Salary searchById(String id) throws SQLException {
        String sql = "SELECT * FROM salary WHERE salaryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, id);

        ResultSet resultSet = pvsm.executeQuery();
        if (resultSet.next()) {
            String salaryId = resultSet.getString(1);
            String date = resultSet.getString(2);
            String amount = resultSet.getString(3);
            String employeeId = resultSet.getString(4);


            Salary salary = new Salary(salaryId, date, amount, employeeId);

            return salary;
        }

        return null;
    }
}








