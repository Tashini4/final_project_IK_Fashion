package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.model.Item;
import lk.ijse.model.tm.ItemTm;
import lk.ijse.repository.ItemRepo;

import java.sql.SQLException;
import java.util.List;

public class itemFormController {

    @FXML
    private ComboBox<?> cmbInventoryId;

    @FXML
    private ComboBox<?> cmbSize;

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
        
        
        try {
            boolean Add = ItemRepo.Add(item);
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
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnExitOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbInventoryOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSizeOnAction(ActionEvent event) {

    }

}
