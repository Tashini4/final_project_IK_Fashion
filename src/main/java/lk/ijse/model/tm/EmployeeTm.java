package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeTm {
    private  String id;
    private  String name;

    private  String email;
    private  String contact;

    private  String address;
    private  String gender;
}
