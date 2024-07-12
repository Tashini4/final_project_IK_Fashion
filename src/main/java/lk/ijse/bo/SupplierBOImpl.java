package lk.ijse.bo;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.dto.CustomerDTO;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = new SupplierDAOImpl();
    @Override
    public List<SupplierDTO> getAll() throws SQLException, ClassNotFoundException {

        List<Supplier> supplierList = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOSList = new ArrayList<>();
        for (Supplier supplier : supplierList){
            supplierDTOSList.add(new SupplierDTO(supplier.getSupplierId(),supplier.getSupplierName(),
                    supplier.getSupplierEmail(),supplier.getSupplierAddress(),supplier.getSupplierContact()));
        }
        return supplierDTOSList;
    }

    @Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }
    @Override

    public  boolean save(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getSupplierId(),supplierDTO.getSupplierName(),supplierDTO.getSupplierEmail(),supplierDTO.getSupplierAddress(),
                supplierDTO.getSupplierContact()));
    }
    @Override

    public  boolean update(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getSupplierName(),
                supplierDTO.getSupplierEmail(),supplierDTO.getSupplierAddress(),supplierDTO.getSupplierContact(),supplierDTO.getSupplierId()));
    }
}
