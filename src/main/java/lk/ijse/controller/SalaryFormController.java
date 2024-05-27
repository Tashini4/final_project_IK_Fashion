package lk.ijse.controller;

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
import lk.ijse.model.Employee;
import lk.ijse.model.Salary;
import lk.ijse.model.tm.SalaryTm;
import lk.ijse.repository.EmployeeRepo;
import lk.ijse.repository.ItemRepo;
import lk.ijse.repository.SalaryRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class SalaryFormController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtSalaryId;

    public void initialize(){
        loadAllSalary();
        setCellValueFactory();
        getEmployeeIds();
        setTableAction();
    }
    private void setTableAction() {
        tblSalary.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            txtSalaryId.setText(newSelection.getSalaryId());
            txtDate.setText(newSelection.getDate());
            txtAmount.setText(newSelection.getAmount());
            cmbEmployeeId.setValue(newSelection.getEmployeeId());

        });
    }

    private void getEmployeeIds() {
        ObservableList<String>  obList = FXCollections.observableArrayList();
        try {
            List<String> idList = EmployeeRepo.getIds();

            for (String id : idList){
                obList.add(id);
            }
            cmbEmployeeId.setItems(obList);
        }catch (SQLException e){
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching Items IDs: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setCellValueFactory() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
    }

    private void loadAllSalary() {
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<Salary> salaryList = SalaryRepo.getAll();
            for (Salary salary : salaryList){
                SalaryTm tm = new SalaryTm(
                        salary.getSalaryId(),
                        salary.getSalaryDate(),
                        salary.getSalaryAmount(),
                        salary.getEmployeeId()
                );
                obList.add(tm);
            }
            tblSalary.setItems(obList);
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard from");

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFeilds();

    }

    private void clearFeilds() {
        txtSalaryId.setText("");
        txtDate.setText("");
        txtAmount.setText("");
        cmbEmployeeId.setValue("");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String salaryId = txtSalaryId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();
        String employeeId = (String) cmbEmployeeId.getValue();

        Salary salary = new Salary(salaryId,date,amount,employeeId);

        try {
            boolean Save = SalaryRepo.save(salary);
            if (Save){
                new Alert(Alert.AlertType.CONFIRMATION,"Salary saved!").show();
                loadAllSalary();
                clearFeilds();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String salaryId = txtSalaryId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();
        String employeeId = (String) cmbEmployeeId.getValue();

        Salary salary = new Salary(salaryId,date,amount,employeeId);

        try {
            boolean Update = SalaryRepo.update(salary);
            if (Update){
                new Alert(Alert.AlertType.CONFIRMATION,"Salary updated!").show();
                loadAllSalary();
                clearFeilds();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getValue();

        try {
            Employee employee = EmployeeRepo.searchById(id);
            cmbEmployeeId.setValue(employee.getEmployeeId());
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtSalaryId.getText();

        Salary salary = SalaryRepo.searchById(id);
        if (salary != null) {
            txtSalaryId.setText(salary.getSalaryId());
            txtDate.setText(salary.getSalaryDate());
            txtAmount.setText(salary.getSalaryAmount());
            cmbEmployeeId.setValue(salary.getEmployeeId());


        } else {
            new Alert(Alert.AlertType.INFORMATION, "salary not found!").show();
        }
    }

    @FXML
    public void txtSalaryIdOnKeyRelesed(KeyEvent keyEvent) {
        CustomerRegex.setTextColor(CustomerTextField.ID,txtSalaryId);
    }
}
