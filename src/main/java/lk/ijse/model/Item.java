package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private String inventoryId;
    private String itemId;
    private String description;
    private String color;
    private String size;
    private String price;;

}
