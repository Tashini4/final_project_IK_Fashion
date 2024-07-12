package lk.ijse.bo;

import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.RegisterDAO;
import lk.ijse.dao.custom.impl.RegisterDAOImpl;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.entity.Register;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegisterBOImpl implements RegisterBO {

    RegisterDAO registerDAO = (RegisterDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REGISTER);
    @Override
    public List<RegisterDTO> getAll() throws SQLException, ClassNotFoundException {
       List<Register> registerList = registerDAO.getAll();
       List<RegisterDTO> registerDTOList = new ArrayList<>();
       for (Register register : registerList){
           registerDTOList.add(new RegisterDTO(register.getRegisterId(),register.getRegiterName(),register.getRegisterPosition(),
                   register.getRegisterPassword()));
       }
       return registerDTOList;
    }
    @Override

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return registerDAO.delete(id);
    }
    @Override

    public boolean save(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException {
        return registerDAO.save(new Register(registerDTO.getRegisterId(),registerDTO.getRegiterName(),
                registerDTO.getRegisterPosition(),registerDTO.getRegisterPassword()));
    }
    @Override

    public boolean update(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException {
        return registerDAO.update(new Register(registerDTO.getRegiterName(),registerDTO.getRegisterPosition(),
                registerDTO.getRegisterPassword(),registerDTO.getRegisterId()));
    }
    @Override

    public Register searchById(String id) throws SQLException, ClassNotFoundException {

        return registerDAO.searchById(id);
    }
}
