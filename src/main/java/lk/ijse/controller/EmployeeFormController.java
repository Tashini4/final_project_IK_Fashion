package lk.ijse.controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.Util.CustomerRegex;
import lk.ijse.Util.CustomerTextField;
import lk.ijse.model.Customer;
import lk.ijse.model.Employee;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.EmployeeRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbGender;

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
    private AnchorPane rootNode;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

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

    public void initialize(){
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
            for (Employee employee : employeeList){
                EmployeeTm tm = new EmployeeTm(
                        employee.getEmployeeId(),
                        employee.getEmployeeName(),
                        employee.getEmployeeEmail(),
                        employee.getEmployeeContact(),
                        employee.getEmployeeAddress(),
                        employee.getEmployeeGender()
                );
                obList.add(tm);
            }
            tblEmployee.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
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
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean Delete = EmployeeRepo.delete(id);
            if (Delete){
                new Alert(Alert.AlertType.CONFIRMATION,"Employee Deleted!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name= txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String gender = cmbGender.getValue();

        Employee employee = new Employee(id,name,email,contact,address,gender);

        try {
            boolean Save = EmployeeRepo.save(employee);
            if(Save){
                new Alert(Alert.AlertType.CONFIRMATION,"employee Saved!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name= txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String gender = cmbGender.getValue();

        Employee employee = new Employee(id,name,email,contact,address,gender);

        try {
            boolean Update = EmployeeRepo.update(employee);
            if(Update){
                new Alert(Alert.AlertType.CONFIRMATION,"employee Update!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();

        Employee employee = EmployeeRepo.searchById(id);
        if (employee != null) {
            txtId.setText(employee.getEmployeeId());
            txtName.setText(employee.getEmployeeName());
            txtEmail.setText(employee.getEmployeeEmail());
            txtContact.setText(employee.getEmployeeContact());
            txtAddress.setText(employee.getEmployeeAddress());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "emoloyee not found!").show();
        }
    }

    @FXML
    void cmbGenderOnAction(ActionEvent event) {

    }
    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {

        CustomerRegex.setTextColor(CustomerTextField.ADDRESS,txtAddress);
    }

    @FXML
    void txtContactOnKeyReleased(KeyEvent event) {

        CustomerRegex.setTextColor(CustomerTextField.CONTACT,txtContact);
    }

    @FXML
    void txtEmailOnKeyReleased(KeyEvent event) {

        CustomerRegex.setTextColor(CustomerTextField.EMAIL,txtEmail);
    }

    @FXML
    void txtIdOnKeyReleased(KeyEvent event) {

        CustomerRegex.setTextColor(CustomerTextField.ID,txtId);
    }

    @FXML
    void txtNameOnKeyReleased(KeyEvent event) {
        CustomerRegex.setTextColor(CustomerTextField.NAME,txtName);

    }

}
