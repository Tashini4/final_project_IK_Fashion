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
import lk.ijse.model.Inventory;
import lk.ijse.model.Item;
import lk.ijse.model.tm.ItemTm;
import lk.ijse.repository.InventoryRepo;
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
    private TableColumn<?, ?> colColor;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colInventoryId;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableView<ItemTm> tblItem;
    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtPrice;
    
    public void initialize(){
        setCellValueFactory();
        loadAllItems();
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
        size.add("XXXL");


        cmbSize.setItems(size);
    }

    private void getInventoryIds() {
        try {
            List<String> idList = InventoryRepo.getIds();
            ObservableList<String> obList = FXCollections.observableArrayList(idList);
            cmbInventoryId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllItems() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();
        
        try{
            List<Item> itemList = ItemRepo.getAll();
            for (Item item : itemList){
                ItemTm tm = new ItemTm(
                        item.getItemId(),
                        item.getDescription(),
                        item.getColor(),
                        item.getSize(),
                        item.getPrice(),
                        item.getInventoryId()
                );
                obList.add(tm);
            }
            tblItem.setItems(obList);
        }catch (SQLException e ){
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colInventoryId.setCellValueFactory(new PropertyValueFactory<>("inventoryId"));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String itemid = txtItemId.getText();
        String description = txtDescription.getText();
        String color = txtColor.getText();
        String size = (String) cmbSize.getValue();
        String price = txtPrice.getText();
        String inventoryId = (String) cmbInventoryId.getValue();

        Item item = new Item(itemid,description,color,size,price,inventoryId);
        
        
        try {
            boolean Add = ItemRepo.add(item);
            if (Add) {
                new Alert(Alert.AlertType.CONFIRMATION, "Added item!").show();
                loadAllItems();
                clearFields();
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
            
        }
    }

    private void clearFields() {
        txtItemId.setText("");
        txtDescription.setText("");
        txtColor.setText("");
        txtPrice.setText("");
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtItemId.getText();

        try {
            boolean Delete = ItemRepo.delete(id);
            if(Delete) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();
                loadAllItems();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/stockForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Stock Form");
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String description = txtDescription.getText();
        String color= txtColor.getText();
        String price= txtPrice.getText();
        String size = (String) cmbSize.getValue();
        String inventoryId = (String) cmbInventoryId.getValue();


        Item item = new Item(itemId,description,color,size,price,inventoryId);

        try {
            boolean Update = ItemRepo.update(item);
            if(Update) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbInventoryOnAction(ActionEvent event) {
        String id = cmbInventoryId.getValue();
        try {
            Inventory inventory = InventoryRepo.searchById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbSizeOnAction(ActionEvent event) {

    }

}
