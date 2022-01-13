package edu.ntnu.idata2001.mappedel2;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        stage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("scenes/Patientregistry.fxml"));
            stage.setTitle("Patientregistry");
            stage.setScene(new Scene(root, 900, 600));
            stage.getScene().getStylesheets().add(getClass()
                    .getResource("/stylesheets/Standard.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Stage getStage() {
        return stage;
    }
}