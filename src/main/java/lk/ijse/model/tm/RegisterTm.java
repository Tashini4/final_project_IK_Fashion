package lk.ijse.model.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class RegisterTm {
    private String registerId;

    private String registerName;

    private String registerPosition;

    private String registerPassword;


}
