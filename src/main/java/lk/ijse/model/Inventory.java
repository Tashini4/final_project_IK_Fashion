package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
    private String inventoryId;
    private String qty;
    private String costPrice;
    private String sellingPrice;
    private String supplierId;
}
