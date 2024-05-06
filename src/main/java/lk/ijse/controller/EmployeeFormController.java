package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class EmployeeFormController {

    public AnchorPane root;
    @FXML
    private BarChart<?, ?> chartEmployee;

    @FXML
    private Label lblInactiveEmployee;
    @FXML
    private AnchorPane rootNode;


    @FXML
    private Label lblPresentEmployee;

    @FXML
    private Label lblTotalEmployee;

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/addEmployeeForm.fxml"));
        root.getChildren().setAll(node);
    }

    @FXML
    void btnEmployeeSalaryOnAction(ActionEvent event) throws IOException {
        Node node = FXMLLoader.load(getClass().getResource("/view/employeeSalaryForm.fxml"));
        root.getChildren().setAll(node);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employeeForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("employee Form");
        stage.centerOnScreen();

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/view/dashboardForm.fxml"));
        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard Form");



    }

}
