package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inventory {
    private String inventoryId;
    private int qty;
    private int costPrice;
    private int sellingPrice;
    private String supplierId;
}
