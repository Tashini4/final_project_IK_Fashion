package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    private String itemId;
    private String orderId;
    private int qty;
    private int unitPrice;
    private int total;
}
