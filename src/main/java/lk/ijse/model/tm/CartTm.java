package lk.ijse.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CartTm {
    private String itemId;
    private String description;
    private int qty;
    private double unitPrice;
    private double total;
    private JFXButton btnRemove;
}
