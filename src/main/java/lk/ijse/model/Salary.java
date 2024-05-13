package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Salary {
    private  String employeeId;
    private String salaryId;
    private String salaryDate;
    private String salaryAmount;

}
