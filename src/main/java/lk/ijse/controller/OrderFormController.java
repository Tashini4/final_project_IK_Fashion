/*package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.tm.OrderTm;
import lk.ijse.repository.OrderRepo;

import java.sql.SQLException;
import java.util.List;

public class OrderFormController {

    @FXML
    private ComboBox<?> cmbCustomerId;

    @FXML
    private ComboBox<?> cmbItemId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblPaymentAmount;

    @FXML
    private Label lblPaymentDate;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<OrderTm> tblPlaceOrder;

    @FXML
    private TextField txtQty;

    private void initialize(){
        SetCellValueFactory();
        loadAllOrder();
    }

    private void loadAllOrder() {
        ObservableList<OrderTm> obList = FXCollections.observableArrayList();

        try {
            List<OrderTm> ps = OrderRepo.getAll();
            for (Order order : ps) {
                OrderTm tm = new OrderTm(
                        order.getItemId(),
                        order.getDescription(),
                        order.getUnitPrice(),
                        order.getQty(),
                        order.getTotal(),
                        order.getAction()

                );

                obList.add(tm);
            }

            tblPlaceOrder.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void SetCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }



    @FXML
    void btnAddToCartOnAction(ActionEvent event) {


    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {

    }

}*/
