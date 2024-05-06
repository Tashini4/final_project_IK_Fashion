package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashBoardFormController {

    @FXML
    private Label lb;

    @FXML
    private Label lblCustomerCount;
    private  int customerCount;

    @FXML
    private Label lblEmployeeCount;
    private int employeeCount;

    @FXML
    private Label lblItemCount;
    private int itemCount;

    @FXML
    private Label lblOrderCount;
    private int  orderCount;

    @FXML
    private Label lblSalesCount;
    private int salesCount;

    @FXML
    private Label lblUserCount;
    private  int userCount;

    @FXML
    private AnchorPane rootNode;

    @FXML
    public TextField txtSearch;

    public void initialize() {
        try {
            customerCount = getCustomerCount();
            orderCount = getOrderCount();
            salesCount = getSalesCount();
            itemCount = getItemCount();
            userCount = getUserCount();
            employeeCount = getEmployeeCouunt();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        setCustomerCount(customerCount);
        setOrderCount(orderCount);
        setSalesCount(salesCount);
        setItemCount(itemCount);
        setUserCount(userCount);
        setEmployeeCount(employeeCount);
    }

    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));
    }

    private int getEmployeeCouunt() throws SQLException {
        String sql = "SELECT COUNT(*) AS employee_count FROM employees";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("employee_count");
        }
        return 0;
    }

    private void setUserCount(int userCount) {
        lblUserCount.setText(String.valueOf(userCount));
    }

    private int getUserCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS user_count FROM users";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("user_count");
        }
        return 0;
    }

    private void setItemCount(int itemCount) {
        lblItemCount.setText(String.valueOf(itemCount));
    }

    private int getItemCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS item_count FROM items";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("item_count");
        }
        return 0;
    }

    private void setSalesCount(int salesCount) {
        lblSalesCount.setText(String.valueOf(salesCount));
    }

    private int getSalesCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS sales_count FROM orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("order_count");
        }
        return 0;
    }

    private int getOrderCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS order_count FROM orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("order_count");
        }
        return 0;
    }

    private void setOrderCount(int orderCount) {
        lblOrderCount.setText(String.valueOf(orderCount));
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if(resultSet.next()){
            return resultSet.getInt("customer_count");
        }
        return 0;
    }


    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }
    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employeeForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Employee Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }


    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/frontForm.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("frontForm");
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registerForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/stockForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("stockForm");
        stage.centerOnScreen();

    }

}
