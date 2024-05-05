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
import lk.ijse.model.Salary;

import lk.ijse.model.tm.SalaryTm;
import lk.ijse.repository.SalaryRepo;


import java.sql.SQLException;
import java.util.List;

public class EmployeeSalaryFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeID;

    @FXML
    private TableColumn<?, ?> colSalaryID;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmployeeID;

    @FXML
    private TextField txtSalaryID;

    public void initialize() {
        setCellValueFactory();
        loadAllEmployee();

    }

    private void loadAllEmployee() {
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<Salary> salaryList = SalaryRepo.getAll();
            for (Salary salary : salaryList) {
                SalaryTm tm = new SalaryTm(
                        salary.getEmployeeID(),
                        salary.getSalaryID(),
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

    private void setCellValueFactory() {
        colEmployeeID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colSalaryID.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtEmployeeID.setText("");
        txtSalaryID.setText("");
        txtDate.setText("");
        txtAmount.setText("");

    }

    //@FXML
   /* void btnUpdateOnaction(ActionEvent event) {
        String id = txtEmployeeID.getText();
        String name = txtSalaryID.getText();
        String email= txtDate.getText();
        String contact = txtAmount.getText();


        //Salary salary = new Salary(employeeID,salaryID,date,amount);

        try {
            boolean Update = SalaryRepo.update(salary);
            if(Update) {
                new Alert(Alert.AlertType.CONFIRMATION, "Salary updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }*/
    }



