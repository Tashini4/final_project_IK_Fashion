package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetail{
    private String itemId;
    private String orderId;
    private int qty;
    private double unitPrice;
    private double total;
}
