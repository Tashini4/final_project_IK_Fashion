package lk.ijse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {

    private  String employeeId;
    private  String employeeName;
    private  String employeeEmail;
    private  String employeeContact;
    private  String employeeAddress;
    private  String employeeGender;
}

