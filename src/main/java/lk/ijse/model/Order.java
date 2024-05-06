package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String orderId;
    private String customerId;
    private String paymentId;
    private Date orderDate;


}
