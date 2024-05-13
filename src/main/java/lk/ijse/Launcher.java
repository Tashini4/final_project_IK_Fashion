package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static  Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //this.stage=stage;
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboardForm.fxml"))));
        stage.setTitle("Front Form");
        stage.centerOnScreen();
        stage.show();
    }
}
