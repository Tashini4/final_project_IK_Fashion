package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class orderEmployee {
    private String orderId;
    private String employeeId;
    private String startTime;
    private String endTime;
}
