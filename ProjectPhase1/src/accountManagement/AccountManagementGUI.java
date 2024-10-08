package accountManagement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AccountManagementGUI extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Welcome to Group 47 project phase 1");
        StackPane root = new StackPane();
        root.getChildren().add(label);
        
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Hello Admin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//add name to its own gui then username and password gui.