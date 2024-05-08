package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemTm {
    private String inventoryId;
    private String itemId;
    private String description;
    private String color;
    private String size;
    private String price;
    private String qtyOnHand;
}
