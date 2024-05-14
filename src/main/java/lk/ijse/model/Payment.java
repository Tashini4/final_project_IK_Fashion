package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Payment {
    private String paymentId;
    private double paymentAmount;
    private Date paymentDate;
}
