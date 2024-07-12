package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SupplierDTO {
    private  String supplierId;
    private  String supplierName;
    private  String supplierEmail;
    private  String supplierAddress;
    private  String supplierContact;
}
