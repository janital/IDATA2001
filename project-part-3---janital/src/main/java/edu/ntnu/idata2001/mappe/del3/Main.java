package edu.ntnu.idata2001.mappe.del3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    /**
     * The stage used for the main scene.
     */
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        stage = primaryStage;
        try {
            root = FXMLLoader.load(getClass().getResource("/postalcodeapp.fxml"));
            stage.setTitle("Postnummerregister");
            stage.setScene(new Scene(root, 1000, 600));
            stage.getScene().getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the stage used for the main scene.
     *
     * @return stage used for the main scene.
     */
    public static Stage getStage() {
        return stage;
    }
}