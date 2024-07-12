package lk.ijse.bo;

import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO{
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException ;

    public  boolean delete(String id) throws SQLException, ClassNotFoundException;

    public  boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;

    public  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException ;
}


