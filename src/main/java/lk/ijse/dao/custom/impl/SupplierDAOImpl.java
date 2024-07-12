package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.SupplierDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dto.SupplierDTO;
import lk.ijse.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public  List<Supplier> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM suppliers");
        List<Supplier> supplierList = new ArrayList<>();

        while(resultSet.next()){
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String address = resultSet.getString(4);
            String contact = resultSet.getString(5);


            Supplier supplier = new Supplier(id,name,email,address,contact);
            supplierList.add(supplier);
        }
        return supplierList;
    }
@Override
    public  boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM suppliers WHERE supplierId = ?",id);
    }
@Override
    public  boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO suppliers VALUES(?, ?, ?, ? ,?)",supplier.getSupplierId(),supplier.getSupplierName(),supplier.getSupplierEmail(),
                supplier.getSupplierAddress(),supplier.getSupplierContact());
    }
@Override
    public  boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute( "UPDATE suppliers SET supplierName = ?, supplierEmail = ?, supplierAddress = ? , supplierContact = ?  WHERE supplierId = ?",
                supplier.getSupplierName(),supplier.getSupplierEmail(),supplier.getSupplierAddress(),supplier.getSupplierContact(),supplier.getSupplierId());
    }
}

