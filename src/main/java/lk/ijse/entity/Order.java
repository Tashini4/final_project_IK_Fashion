package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String orderId;
    private Date orderDate;
    private String customerId;
    private String paymentId;



}
