package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SalaryDTO {
    private String salaryId;
    private String salaryDate;
    private String salaryAmount;
    private  String employeeId;

}
