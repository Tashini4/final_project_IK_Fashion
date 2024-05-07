package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import lk.ijse.model.Employee;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.repository.EmployeeRepo;

import java.sql.SQLException;
import java.util.List;

public class addEmployeeFormController {
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;


    @FXML
    private ComboBox cmbGender;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<EmployeeTm> tblAddEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();
        setGender();
    }

    private void setGender() {
        ObservableList<String> gender = FXCollections.observableArrayList();

        gender.add("MALE");
        gender.add("FEMALE");

        cmbGender.setItems(gender);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

    }
    private void loadAllEmployee() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        try {
            List<Employee> employeeList = EmployeeRepo.getAll();
            for (Employee employee : employeeList) {
                EmployeeTm tm = new EmployeeTm(
                        employee.getId(),
                        employee.getName(),
                        employee.getEmail(),
                        employee.getContact(),
                        employee.getAddress(),
                        employee.getGender()
                );

                obList.add(tm);
            }

            tblAddEmployee.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean Delete = EmployeeRepo.delete(id);
            if(Delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
                loadAllEmployee();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String gender = (String) cmbGender.getValue();


        Employee employee = new Employee(id, name, email , contact ,address,gender);

        try {
            boolean Save= EmployeeRepo.save(employee);
            if (Save) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
                loadAllEmployee();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email= txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String gender = String.valueOf(cmbGender.getValue());


        Employee employee = new Employee(id, name,email,contact,address,gender);

        try {
            boolean Update = EmployeeRepo.update(employee);
            if(Update) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
private void clearFields() {
    txtId.setText("");
    txtName.setText("");
    txtEmail.setText("");
    txtContact.setText("");
    txtAddress.setText("");
    cmbGender.setValue("");
}


    @FXML
    void cmbGenderOnAction(ActionEvent event) {



    }

}
