package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import lk.ijse.model.Register;
import lk.ijse.model.tm.RegisterTm;

import lk.ijse.repository.RegisterRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RegisterFormController {

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<RegisterTm> tblRegister;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtRegisterId;

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("User Id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("Position"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));

    }

    private void loadAllCustomers() {
        ObservableList<RegisterTm> obList = FXCollections.observableArrayList();

        try {
            List<Register> registerList = RegisterRepo.getAll();
            for (Register register : registerList) {
                RegisterTm tm = new RegisterTm(
                        register.getRegisterId(),
                        register.getRegisterName(),
                        register.getRegisterPosition(),
                        register.getRegisterPassword()
                );
                obList.add(tm);
            }
            tblRegister.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtRegisterId.setText("");
        txtName.setText("");
        txtPosition.setText("");
        txtPassword.setText("");
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtRegisterId.getText();

        try {
            boolean Delete = RegisterRepo.delete(id);
            if (Delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String registerId = txtRegisterId.getText();
        String name = txtName.getText();
        String position = txtPosition.getText();
        String password = txtPassword.getText();


        Register register = new Register(registerId, name, position, password);

        try {
            boolean Save = RegisterRepo.save(register);
            if (Save) {
                new Alert(Alert.AlertType.CONFIRMATION, " User saved!").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String registerId = txtRegisterId.getText();
        String name = txtName.getText();
        String position = txtPosition.getText();
        String password = txtPassword.getText();


        Register register = new Register(registerId, name, position, password);

        try {
            boolean Update = RegisterRepo.update(register);
            if (Update) {
                new Alert(Alert.AlertType.CONFIRMATION, "User updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtRegisterId.getText();

        Register register = RegisterRepo.searchById(id);
        if (register != null) {
            txtRegisterId.setText(register.getRegisterId());
            txtName.setText(register.getRegisterName());
            txtPosition.setText(register.getRegisterPosition());
            txtPassword.setText(register.getRegisterPassword());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "register not found!").show();
        }

    }
}


