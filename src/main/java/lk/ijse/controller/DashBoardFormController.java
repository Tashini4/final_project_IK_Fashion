package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dao.custom.OrderDAO;
import lk.ijse.db.DbConnection;
import lk.ijse.dao.custom.impl.OrderDAOImpl;


import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class DashBoardFormController {

    @FXML
    private BarChart<String , Number> ChartOrder;

    @FXML
    private BarChart<String , Number> ChartTotalIncome;

    @FXML
    private Label lb;

    @FXML
    private Label lblCustomerCount;
    private int customerCount;

    @FXML
    private Label lblEmployeeCount;
    private int employeeCount;

    @FXML
    private Label lblItemCount;
    private int itemCount;

    @FXML
    private Label lblOrderCount;
    private int orderCount;

    @FXML
    private Label lblUserCount;
    private int userCount;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtSearch;


    OrderDAO orderDAO = new OrderDAOImpl();
    public void initialize(){
        try{
            customerCount = getCustomerCount();
            orderCount = getOrderCount();
            itemCount = getItemCount();
            userCount = getUserCount();
            employeeCount = getEmployeeCount();

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
        setCustomerCount(customerCount);
        setOrderCount(orderCount);
        setItemCount(itemCount);
        setUserCount(userCount);
        setEmployeeCount(employeeCount);

        addChartEventHandlers();
        addValueToOrderChart();
        addValueToTotalIncomeChart();

    }
    private void addValueToOrderChart() {

        try {
            Map<String, Integer> orderCounts = orderDAO.GetDailyOrderCounts();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Map.Entry<String, Integer> entry : orderCounts.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            ChartOrder.getData().add(series);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void addValueToTotalIncomeChart() {
        try {
            Map<String, Integer> incomeCounts = orderDAO.GetDailyIncome(); // Assuming this method exists
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            for (Map.Entry<String, Integer> entry : incomeCounts.entrySet()) {
                series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }
            ChartTotalIncome.getData().add(series);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    private void addChartEventHandlers() {
        ChartOrder.setOnMouseClicked(this::handleChartOrderClick);
        ChartTotalIncome.setOnMouseClicked(this::handleChartTotalIncomeClick);
    }

    private void handleChartOrderClick(MouseEvent mouseEvent) {
        System.out.println("Order chart clicked");
    }

    private void handleChartTotalIncomeClick(MouseEvent mouseEvent) {
        System.out.println(" Total Income chart clicked");
    }

    private int getEmployeeCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS employee_count FROM employees";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if (resultSet.next()){
            return resultSet.getInt("employee_count");
        }
        return 0;
    }


    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));
    }

    private int getUserCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS user_count FROM users";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if (resultSet.next()){
            return resultSet.getInt("user_count");
        }
        return 0;
    }


    private void setUserCount(int userCount) {
        lblUserCount.setText(String.valueOf(userCount));
    }

    private int getItemCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS item_count FROM items";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if (resultSet.next()){
            return resultSet.getInt("item_count");
        }
        return 0;
    }


    private void setItemCount(int itemCount) {
        lblItemCount.setText(String.valueOf(itemCount));
    }

    private int getOrderCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS order_count FROM orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if (resultSet.next()){
            return resultSet.getInt("order_count");
        }
        return 0;
    }

    private void setOrderCount(int orderCount) {
        lblOrderCount.setText(String.valueOf(orderCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customers";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pvsm = connection.prepareStatement(sql);
        ResultSet resultSet = pvsm.executeQuery();

        if (resultSet.next()){
            return resultSet.getInt("customer_count");
        }
        return 0;
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }


    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employeeForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/frontForm.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("front from");
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registerForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {
       AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/itemForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {}
    @FXML
    void btnSalaryOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/salaryForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }
    @FXML
    void btnSupplierOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        rootNode.getChildren().setAll(anchorPane);
    }
    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/orderForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Order form");
        stage.centerOnScreen();
    }
}


