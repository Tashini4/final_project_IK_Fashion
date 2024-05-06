package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderTm {
    private String itemId;
    private String description;
    private String unitPrice;
    private String qty;
    private String total;
    private String action;
}
