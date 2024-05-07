package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static List<Supplier> getAll() throws SQLException {
        String sql = "SELECT * FROM suppliers";

        PreparedStatement pvsm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

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

    public static boolean delete(String id) throws SQLException {
        String sql = "DELETE FROM suppliers WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, id);

        return pvsm.executeUpdate() > 0;
    }

    public static boolean save(Supplier supplier) throws SQLException {
        String sql = "INSERT INTO suppliers VALUES(?, ?, ?, ? ,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, supplier.getSupplierId());
        pvsm.setObject(2,  supplier.getSupplierName());
        pvsm.setObject(3,  supplier.getSupplierEmail());
        pvsm.setObject(4,  supplier.getSupplierAddress());
        pvsm.setObject(5,  supplier.getSupplierContact());


        return pvsm.executeUpdate() > 0;
    }

    public static boolean update(Supplier supplier) throws SQLException {
        String sql = "UPDATE suppliers SET supplierName = ?, supplierEmail = ?, supplierAddress = ? , supplierContact = ?  WHERE supplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        pvsm.setObject(1, supplier.getSupplierName());
        pvsm.setObject(2, supplier.getSupplierEmail());
        pvsm.setObject(3,supplier.getSupplierAddress());
        pvsm.setObject(4, supplier.getSupplierContact());
        pvsm.setObject(5, supplier.getSupplierId());

        return pvsm.executeUpdate() > 0;
    }
}

