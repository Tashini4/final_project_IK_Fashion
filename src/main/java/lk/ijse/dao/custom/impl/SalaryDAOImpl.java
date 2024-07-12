package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalaryDAO;
import lk.ijse.db.DbConnection;

import lk.ijse.dto.SalaryDTO;
import lk.ijse.entity.Salary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl  implements SalaryDAO {
    @Override
    public  List<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM salary");

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
    @Override
    public  boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE salary SET salaryDate = ?, salaryAmount = ? ,employeeId = ? WHERE salaryId = ?",salary.getSalaryDate(),salary.getSalaryAmount(),
                salary.getEmployeeId(),salary.getSalaryId());
    }
    @Override
    public  boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO salary VALUES (?,?,?,?)",salary.getSalaryId(),
                salary.getSalaryDate(),salary.getSalaryAmount(),salary.getEmployeeId());
    }

    @Override
    public Salary searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM salary WHERE salaryId = ?",id);
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








