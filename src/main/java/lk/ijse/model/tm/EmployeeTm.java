package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeTm {
    private  String employeeId;
    private  String employeeName;

    private  String employeeEmail;
    private  String employeeContact;

    private  String employeeAddress;
    private  String employeeGender;
}
