package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {
    private String itemId;
    private String description;
    private String brand;
    private String size;
    private double price;
    private int qtyOnHand;
    private String inventoryId;
}
