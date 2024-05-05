package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.repository.UserRepo;

import static lk.ijse.repository.UserRepo.saveUser;

public class RegisterFormController {

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnOnRegisterAction(ActionEvent event) {
        String userId = txtUserID.getText();
        String userName = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            boolean save = UserRepo.saveUser(userId, userName, password);
            if (save) {
                new Alert(Alert.AlertType.CONFIRMATION, "new user saved!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void saveUser(ActionEvent actionEvent) {
    }
}




