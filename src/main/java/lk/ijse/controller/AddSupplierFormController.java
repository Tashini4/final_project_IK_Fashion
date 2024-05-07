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

import lk.ijse.model.Supplier;
import lk.ijse.model.tm.SupplierTm;

import lk.ijse.repository.SupplierRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddSupplierFormController {

    @FXML
    private TableColumn<?, ?> colSupplierAddress;

    @FXML
    private TableColumn<?, ?> colSupplierContact;

    @FXML
    private TableColumn<?, ?> colSupplierEmail;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<SupplierTm> tblSupplier;

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
        loadAllSupplier();
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<Supplier> supplierList = SupplierRepo.getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getSupplierName(),
                        supplier.getSupplierEmail(),
                        supplier.getSupplierAddress(),
                        supplier.getSupplierContact()
                        );
                obList.add(tm);
            }
            tblSupplier.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSupplierAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSupplierContact.setCellValueFactory(new PropertyValueFactory<>("contact"));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/stockForm.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Stock Form");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtContact.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean Delete = SupplierRepo.delete(id);
            if(Delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier deleted!").show();
                loadAllSupplier();
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
        String address = txtAddress.getText();
        String contact = txtContact.getText();



        Supplier supplier = new Supplier(id, name, email , address,contact );

        try {
            boolean Save= SupplierRepo.save(supplier);
            if (Save) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier saved!").show();
                loadAllSupplier();
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
        String address = txtAddress.getText();
        String contact = txtContact.getText();




        Supplier supplier = new Supplier(id, name,email,address,contact);

        try {
            boolean Update = SupplierRepo.update(supplier);
            if(Update) {
                new Alert(Alert.AlertType.CONFIRMATION, "supplier updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

}
