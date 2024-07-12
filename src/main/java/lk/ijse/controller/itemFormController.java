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
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.InventoryBO;
import lk.ijse.bo.ItemBO;
import lk.ijse.dto.ItemDTO;
import lk.ijse.entity.Inventory;
import lk.ijse.entity.Item;
import lk.ijse.tm.ItemTm;
import lk.ijse.dao.custom.impl.InventoryDAOImpl;
import lk.ijse.dao.custom.impl.ItemDAOImpl;

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

    ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.ITEM);
    InventoryBO inventoryBO = (InventoryBO) BOFactory.getBoFactory().getBo(BOFactory.BoType.INVENTORY);

    public void initialize(){
        setCellValueFactory();
        loadAllItem();
        getInventoryIds();
        setSize();
        setTableAction();

    }
    private void setTableAction() {
        tblItem.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection) -> {
            txtItemId.setText(newSelection.getItemId());
            txtDescription.setText(newSelection.getDescription());
            txtBrand.setText(newSelection.getBrand());
            cmbSize.setValue(newSelection.getSize());
            txtPrice.setText(String.valueOf(newSelection.getPrice()));
            txtQtyOnHand.setText(String.valueOf(newSelection.getQtyOnHand()));
            cmbInventoryId.setValue(newSelection.getInventoryId());
        });
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
            List<String> idList =  inventoryBO.getIds();
            obList.addAll(idList);
            cmbInventoryId.setItems(obList);
        }catch(SQLException e){
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllItem() {
        ObservableList<ItemTm> obList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> itemList = itemBO.getAll();
            for (ItemDTO item : itemList){
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
        }catch (SQLException | ClassNotFoundException e){
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
    void btnSaveOnAction(ActionEvent event) {
        String itemId = txtItemId.getText();
        String description = txtDescription.getText();
        String brand = txtBrand.getText();
        String size = (String) cmbSize.getValue();
        double price = Double.parseDouble(txtPrice.getText());
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        String inventoryId = (String) cmbInventoryId.getValue();

        //Item item = new Item(itemId,description,brand,size,price,qtyOnHand,inventoryId);

        try {
            boolean isAdded = itemBO.save(new ItemDTO(itemId,description,brand,size,price,qtyOnHand,inventoryId));
            if (isAdded){
                new Alert(Alert.AlertType.CONFIRMATION,"Item added!").show();
                loadAllItem();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("DashBoard form");
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
            boolean isDeleted = itemBO.delete(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Item deleted!").show();

            }
        } catch (SQLException | ClassNotFoundException e) {
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

       //Item item = new Item(itemId, description,brand,size,price,qtyOnHand,inventoryId);

        try {
            boolean isUpdated = itemBO.update(new ItemDTO(itemId,description,brand,size,price,qtyOnHand, inventoryId));
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "item updated!").show();
                loadAllItem();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbInventoryOnAction(ActionEvent event) {
        String inventoryId = cmbInventoryId.getValue();
        try {
            Inventory inventory = inventoryDAOImpl.searchById(inventoryId);
            cmbInventoryId.setValue(inventory.getInventoryId());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbSizeOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String itemId = txtItemId.getText();

       Item item = itemBO.searchById(itemId);
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
    }@FXML
    void txtIdOnKeyReleased(KeyEvent event) {
        CustomerRegex.setTextColor(CustomerTextField.ID,txtItemId);
    }


}


