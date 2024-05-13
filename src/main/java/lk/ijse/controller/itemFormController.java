package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lk.ijse.model.Item;
import lk.ijse.model.tm.ItemTm;
import lk.ijse.repository.ItemRepo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class itemFormController {

    @FXML
    private ComboBox<String> cmbInventoryId;

    @FXML
    private ComboBox<String> cmbSize;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colInventoryId;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ItemTm> tblItem;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQtyOnHand;

    public void initialize(){
        setCellValueFactory();
        loadAllItem();
        getInventoryIds();
        setSize();

    }

    private void setSize() {
        ObservableList<String> size = FXCollections.observableArrayList();

        size.add("S");
        size.add("M");
        size.add("L");
        size.add("XL");
        size.add("XXL");
        size.add("3XL");
        size.add("4XL");
        size.add("5XL");
        size.add("6XL");

        cmbSize.setItems(size);
    }

    private void getInventoryIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList =  ItemRepo.getIds();

            for (String id : idList){
                obList.add(id);
            }
            cmbInventoryId.setItems(obList);
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }

    private void loadAllItem() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<Item> itemList = ItemRepo.getAll();
            for (Item item : itemList){
                ItemTm tm = new ItemTm(
                        item.getItemId(),
                        item.getDescription(),
                        item.getBrand(),
                        item.getSize(),
                        item.getPrice(),
                        item.getQtyOnHand(),
                        item.getInventoryId()
                );
                obList.add(tm);
            }
            tblItem.setItems(obList);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String description = txtDescription.getText();
        String brand = txtBrand.getText();
        String size = (String) cmbSize.getValue();
        double price = Double.parseDouble(txtPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        String inventoryId = (String) cmbInventoryId.getValue();

        Item item = new Item(itemId,description,brand,size,price,qtyOnHand,inventoryId);

        try {
            boolean isAdded = ItemRepo.add(item);
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Item added!").show();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/stockForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Stock Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearField();
    }

    private void clearField() {
        txtItemId.setText("");
        txtDescription.setText("");
        txtBrand.setText("");
        cmbSize.setValue("");
        txtPrice.setText("");
        txtQtyOnHand.setText("");
        cmbInventoryId.setValue("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtItemId.getText();

        try {
            boolean isDeleted = ItemRepo.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String description = txtDescription.getText();
        String brand = txtBrand.getText();
        String size = (String) cmbSize.getValue();
        double price = Double.parseDouble(txtPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        String inventoryId = (String) cmbInventoryId.getValue();

       Item item = new Item(itemId, description,brand,size,price,qtyOnHand,inventoryId);

        try {
            boolean isUpdated = ItemRepo.update(item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbInventoryOnAction(ActionEvent event) {
        String inventoryId = cmbInventoryId.getValue();
        try {
            Item item = ItemRepo.searchById(inventoryId);

           cmbInventoryId.setValue(item.getInventoryId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbSizeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        String itemId = txtItemId.getText();

       Item item = ItemRepo.searchById(itemId);
        if (item != null) {
            txtItemId.setText(item.getItemId());
            txtDescription.setText(item.getDescription());
            txtBrand.setText(item.getBrand());
            cmbSize.setValue(item.getSize());
            txtPrice.setText(String.valueOf(item.getPrice()));
            txtQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
            cmbInventoryId.setValue(item.getInventoryId());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "item not found!").show();
        }
    }
}


