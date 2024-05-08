package lk.ijse.model.tm;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderTm {
    private String orderId;
    private Date  orderDate;
    private String customerId;
    private String paymentId;
}
