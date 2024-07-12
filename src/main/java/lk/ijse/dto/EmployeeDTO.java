package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private  String employeeId;
    private  String employeeName;
    private  String employeeEmail;
    private  String employeeContact;
    private  String employeeAddress;
    private  String employeeGender;
}

