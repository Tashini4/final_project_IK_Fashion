
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.Customer;
import lk.ijse.model.tm.CustomerTm;
import lk.ijse.repository.CustomerRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane rootNode;
    @FXML
    private TableView<CustomerTm> tblCustomer;

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
        loadAllCustomers();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<Customer> customerList = CustomerRepo.getAll();
            for (Customer customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getEmail(),
                        customer.getContact(),
                        customer.getAddress()

                );

                obList.add(tm);
            }

            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @FXML

    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();

        Customer customer = new Customer(id, name, email , contact ,address);

        try {
            boolean Save= CustomerRepo.save(customer);
            if (Save) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String name = txtName.getText();
        String email= txtEmail.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();


        Customer customer = new Customer(id, name,email,contact,address);

        try {
            boolean Update = CustomerRepo.update(customer);
            if(Update) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    void btnSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();

        Customer customer = CustomerRepo.searchById(id);
        if (customer != null) {
            txtId.setText(customer.getId());
            txtName.setText(customer.getName());
            txtEmail.setText(customer.getEmail());
            txtContact.setText(customer.getContact());
            txtAddress.setText(customer.getAddress());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean Delete = CustomerRepo.delete(id);
            if(Delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                loadAllCustomers();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
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

}





