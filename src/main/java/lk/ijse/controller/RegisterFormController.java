package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import lk.ijse.Util.CustomerRegex;
import lk.ijse.Util.CustomerTextField;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.RegisterBO;
import lk.ijse.dto.RegisterDTO;
import lk.ijse.entity.Register;
import lk.ijse.tm.RegisterTm;

import lk.ijse.dao.custom.impl.RegisterDAOImpl;

import java.io.IOException;
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
    private TableColumn<?, ?> colRegisterId;

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

    RegisterBO registerBO = (RegisterBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.REGISTER);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        setTableAction();
    }
    private void setTableAction() {
        tblRegister.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            txtRegisterId.setText(newSelection.getRegisterId());
            txtName.setText(newSelection.getRegisterName());
            txtPosition.setText(newSelection.getRegisterPosition());
            txtPassword.setText(newSelection.getRegisterPassword());

        });
    }

    private void setCellValueFactory() {
        colRegisterId.setCellValueFactory(new PropertyValueFactory<>("registerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("registerName"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("registerPosition"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("registerPassword"));

    }

    private void loadAllCustomers() {
        ObservableList<RegisterTm> obList = FXCollections.observableArrayList();

        try {
            List<RegisterDTO> registerList = registerBO.getAll();
            for (RegisterDTO register : registerList) {
                RegisterTm tm = new RegisterTm(
                        register.getRegisterId(),
                        register.getRegiterName(),
                        register.getRegisterPosition(),
                        register.getRegisterPassword()
                );
                obList.add(tm);
            }
            tblRegister.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtRegisterId.getText();

        try {
            boolean Delete = registerBO.delete(id);
            if (Delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "User Deleted").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String registerId = txtRegisterId.getText();
        String name = txtName.getText();
        String position = txtPosition.getText();
        String password = txtPassword.getText();


        //Register register = new Register(registerId, name, position, password);

        try {
            boolean Save = registerBO.save(new RegisterDTO(registerId,name,position,password));
            if (Save) {
                new Alert(Alert.AlertType.CONFIRMATION, " User saved!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String registerId = txtRegisterId.getText();
        String name = txtName.getText();
        String position = txtPosition.getText();
        String password = txtPassword.getText();


       // Register register = new Register(registerId, name, position, password);

        try {
            boolean Update = registerBO.update(new RegisterDTO(registerId,name,position,password));
            if (Update) {
                new Alert(Alert.AlertType.CONFIRMATION, "User updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtRegisterId.getText();

        Register register = registerBO.searchById(id);
        if (register != null) {
            txtRegisterId.setText(register.getRegisterId());
            txtName.setText(register.getRegiterName());
            txtPosition.setText(register.getRegisterPosition());
            txtPassword.setText(register.getRegisterPassword());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "register not found!").show();
        }

    }
    @FXML
    void txtIdOnKeyReleased(KeyEvent event) {

        CustomerRegex.setTextColor(CustomerTextField.ID,txtRegisterId);
    }
    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        CustomerRegex.setTextColor(CustomerTextField.NAME,txtName);

    }
    @FXML
    void txtPasswordOnKeyReleased(KeyEvent event){
        CustomerRegex.setTextColor(CustomerTextField.PASSWORD,txtPassword);
}
}


