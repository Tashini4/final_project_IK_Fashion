package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SalaryDAO;
import lk.ijse.dao.custom.impl.SalaryDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.SalaryDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO{

    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SALARY);
    @Override
    public List<SalaryDTO> getAll() throws SQLException, ClassNotFoundException {
        List<Salary> salaryList = salaryDAO.getAll();
        List<SalaryDTO> salaryDTOList = new ArrayList<>();
        for (Salary salary : salaryList) {
            salaryDTOList.add(new SalaryDTO(salary.getSalaryId(), salary.getSalaryDate(), salary.getSalaryAmount(), salary.getEmployeeId()));
        }
        return salaryDTOList;
    }
    @Override
    public boolean update(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(salaryDTO.getSalaryDate(), salaryDTO.getSalaryAmount(), salaryDTO.getEmployeeId(), salaryDTO.getSalaryId()));
    }
    @Override
    public boolean save(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(salaryDTO.getSalaryId(), salaryDTO.getSalaryDate(), salaryDTO.getSalaryAmount(), salaryDTO.getEmployeeId()));
    }
    @Override
    public Salary searchById(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.searchById(id);
    }
}
