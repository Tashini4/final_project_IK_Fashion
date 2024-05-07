package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.Employee;
import lk.ijse.model.Salary;
import lk.ijse.model.tm.SalaryTm;
import lk.ijse.repository.EmployeeRepo;
import lk.ijse.repository.SalaryRepo;

import java.sql.SQLException;
import java.util.List;

public class EmployeeSalaryFormController {

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
    private AnchorPane rootNode;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtSalaryId;


    public void initialize() {

        getEmployeeIds();
    }




    @FXML
    void btnClearOnAction(ActionEvent event) {
            clearFields();
    }

    private void clearFields() {
        txtSalaryId.setText("");
        txtDate.setText("");
        txtAmount.setText("");
        cmbEmployeeId.setValue("");
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String employeeId = (String) cmbEmployeeId.getValue();
        String salaryId = txtSalaryId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();



        Salary salary = new Salary(employeeId,salaryId,date,amount);

        try {
            boolean Save= SalaryRepo.save(salary);
            if (Save) {
                new Alert(Alert.AlertType.CONFIRMATION, "salary saved!").show();
                loadAllEmployee();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllEmployee() {
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<Salary> salaryList = SalaryRepo.getAll();
            for (Salary salary : salaryList) {
                SalaryTm tm = new SalaryTm(
                        salary.getEmployeeId(),
                        salary.getSalaryId(),
                        salary.getDate(),
                        salary.getAmount()
                );

                obList.add(tm);
            }

            tblSalary.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String employeeId = (String) cmbEmployeeId.getValue();
        String salaryId = txtSalaryId.getText();
        String date = txtDate.getText();
        String amount = txtAmount.getText();



        Salary salary = new Salary(employeeId,salaryId,date,amount);

        try {
            boolean update= SalaryRepo.update(salary);
            if (update) {
                new Alert(Alert.AlertType.CONFIRMATION, "salary updated!").show();
                loadAllEmployee();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getEmployeeIds() {
        try {
            List<String> idList = EmployeeRepo.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbEmployeeId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getValue();
        try {
            Employee employee = EmployeeRepo.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
