package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryDTO {
    private String inventoryId;
    private int qty;
    private double costPrice;
    private double sellingPrice;
    private String supplierId;
}
