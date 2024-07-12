package lk.ijse.bo;

import lk.ijse.dto.RegisterDTO;
import lk.ijse.entity.Register;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RegisterBO extends SuperBO{
    public List<RegisterDTO> getAll() throws SQLException, ClassNotFoundException ;

    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public boolean save(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException ;

    public boolean update(RegisterDTO registerDTO) throws SQLException, ClassNotFoundException ;

    public Register searchById(String id) throws SQLException, ClassNotFoundException ;
}
