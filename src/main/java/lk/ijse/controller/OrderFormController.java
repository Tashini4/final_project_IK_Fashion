package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.model.*;
import lk.ijse.model.tm.CartTm;
import lk.ijse.repository.CustomerRepo;
import lk.ijse.repository.ItemRepo;
import lk.ijse.repository.OrderRepo;
import lk.ijse.repository.PlaceOrderRepo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderFormController {

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnNewCustomer;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

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
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<CartTm> tblPlaceOrder;

    @FXML
    private TextField txtQty;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void initialize() {
        getCurrentOrderId();
        setDate();
        getCustomerIds();
        getItemIds();
        setCellValueFactory();
        getCurrentPaymentId();

        addHoverHandlers(btnAddToCart);
        addHoverHandlers(btnBack);
        addHoverHandlers(btnPlaceOrder);
        addHoverHandlers(btnNewCustomer);

        cmbCustomerId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                cmbItemId.requestFocus();
            }
        });
        cmbItemId.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                txtQty.requestFocus();
            }
        });
        tblPlaceOrder.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txtQty.requestFocus();
            }
        });
    }


    private void addHoverHandlers(Button button) {
        button.setOnMouseEntered(event -> {
            button.setStyle("-fx-background-color: #27f802; -fx-text-fill: white;");
        });
        button.setOnMouseExited(event -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: black;");
        });
    }



    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
        lblPaymentDate.setText(String.valueOf(now));
    }

    private void getCurrentOrderId() {
        try {
            String currentId = OrderRepo.getCurrentId();

            String nextOrderId = generateNextOrderId(currentId);

            lblOrderId.setText(nextOrderId);
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private String generateNextOrderId(String currentId) {
        if (currentId != null){
            String[] splist = currentId.split("0");
            int idNum = Integer.parseInt(splist[1]);
            return "0" + ++idNum;
        }
        return "0001";
    }

    private void getCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList =  CustomerRepo.getIds();

            for (String id : idList){
                obList.add(id);
            }
            cmbCustomerId.setItems(obList);
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }

    private void getItemIds() {
        ObservableList<String>  obList = FXCollections.observableArrayList();
        try {
            List<String> idList = ItemRepo.getIds();

            for (String id : idList){
                obList.add(id);
            }
            cmbItemId.setItems(obList);
        }catch (SQLException e){
            showAlert(Alert.AlertType.ERROR, "Error occurred while fetching Products IDs: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String message) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void setCellValueFactory() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }


    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String itemId = cmbItemId.getValue();
        String description = lblDescription.getText();
        int unitPrice = Integer.parseInt(lblUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        int total = unitPrice * qty;

        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);
        btnRemove.setOnAction((e)->{
            ButtonType yes = new ButtonType("yes",ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no",ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION,"Are you sure to remove?",yes,no).showAndWait();

            if (type.orElse(no) == yes){
                CartTm selectedIndex = (CartTm) tblPlaceOrder.getSelectionModel().getSelectedItem();
                obList.remove(selectedIndex);
                 tblPlaceOrder.refresh();
                 calculateNetTotal();
            }
        });
        for (int i=0; i<tblPlaceOrder.getItems().size();i++){
            if (itemId.equals(colItemId.getCellData(i))){
                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;
                tm.setQty(qty);
                tm.setTotal(total);

                tblPlaceOrder.refresh();
                calculateNetTotal();
                return;
            }
        }
        CartTm tm = new CartTm(itemId,description,unitPrice,qty,total,btnRemove);
        obList.add(tm);

        tblPlaceOrder.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }

    private void calculateNetTotal() {
        int netTotal = 0;
        for (int i = 0;i<tblPlaceOrder.getItems().size();i++){
            netTotal += (int) colTotal.getCellData(i);
        }
        lblPaymentAmount.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/stockForm.fxml"));
        Parent rootNode = loader.load();

        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnNewOnAction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customerForm.fxml"));
        Parent rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws SQLException {
        String orderId = lblOrderId.getText();
        Date date = Date.valueOf(LocalDate.now());
        String customerId = cmbCustomerId.getValue();
        String paymentId = lblPaymentId.getText();
        int amount = Integer.parseInt(lblPaymentAmount.getText());

        var order = new Order(orderId, date, customerId, paymentId);
        List<OrderItem> odList = new ArrayList<>();

        for (int i = 0; i < tblPlaceOrder.getItems().size(); i++) {
            CartTm tm = obList.get(i);
            OrderItem od= new OrderItem(
                    tm.getItemId(),
                    orderId,
                    tm.getQty(),
                    tm.getUnitPrice(),
                    tm.getTotal()
            );
            odList.add(od);
        }
        Payment payment = new Payment(paymentId,amount,date);
        PlaceOrder po = new PlaceOrder(order,odList,payment);

        boolean isPlaced = PlaceOrderRepo.placeOrder(po);

        if (isPlaced){
            obList.clear();
            tblPlaceOrder.setItems(obList);
            lblOrderId.setText("");
            lblPaymentId.setText("");
            lblPaymentAmount.setText("");
            lblDescription.setText("");
            lblUnitPrice.setText("");
            lblQtyOnHand.setText("");
            txtQty.clear();
            getCurrentOrderId();
            getCurrentPaymentId();

            new Alert(Alert.AlertType.CONFIRMATION,"Order Placed!").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Order Placed Unsucessfully!").show();
        }
    }

    private void getCurrentPaymentId() {
        try {
            String currentId = OrderRepo.getPayCurrentId();

            String nextPayId = generateNextPay(currentId);
            lblPaymentId.setText(nextPayId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextPay(String currentId) {
        if (currentId != null && currentId.startsWith("P")) {
            try {
                int idNum = Integer.parseInt(currentId.substring(1)) + 1;
                return "P" + String.format("%03d", idNum);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid current payment ID format");
            }
        }
        return "P001";
    }



    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
        try {
            Customer customer = CustomerRepo.searchById(id);

            lblCustomerName.setText(customer.getCustomerName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemIdOnAction(ActionEvent event) {
        String id = String.valueOf(cmbItemId.getValue());
        try {
            Item item = ItemRepo.searchById(id);

            lblDescription.setText(item.getDescription());
            lblUnitPrice.setText(String.valueOf(item.getPrice()));
            lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
