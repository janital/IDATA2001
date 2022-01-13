package edu.ntnu.idata2001.mappedel2.GUIFactory;

import edu.ntnu.idata2001.mappedel2.Patient;
import edu.ntnu.idata2001.mappedel2.PatientRegistryPlain;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainWindow extends Application {

    /**
     * The patient registry which contains the list over patients.
     */
    private PatientRegistryPlain patients;
    private ObservableList<Patient> patientsListWrapper;

    @Override
    public void init() {
        this.patients = new PatientRegistryPlain();
        fillwithtestdata();
        getPatientsListWrapper();
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUIFactory guiFactory = new GUIFactory();

        VBox root = new VBox();

        MenuBar menuBar = guiFactory.getMenuBar();
        ToolBar toolBar = guiFactory.getToolBar();
        AnchorPane tableViewAnchor = new AnchorPane();
        TableView tableView = guiFactory.getTableView(this.patientsListWrapper);

        AnchorPane.setBottomAnchor(tableView, 0.0);
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setTopAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);
        VBox.setVgrow(tableViewAnchor, Priority.ALWAYS);
        tableViewAnchor.getChildren().add(tableView);

        root.getChildren().addAll(menuBar, toolBar, tableViewAnchor);

        stage.setScene(new Scene(root, 900, 600));
        stage.getScene().getStylesheets().add(getClass().getClassLoader().getResource("stylesheets/Standard.css").toExternalForm());
        stage.show();
    }

    /**
     * Returns an ObservableList holding the patient registry to display.
     *
     * @return ObsevableList holding the patient registry to display.
     */
    private ObservableList<Patient> getPatientsListWrapper() {
        //patientsListWrapper = FXCollections.observableArrayList(this.patients.getPatientList());
        return patientsListWrapper;
    }

    /**
     * Fills the tableview with dummy patients.
     */
    private void fillwithtestdata() {
        try {
            patients.addPatient(new Patient("Nina", "Teknologi", "010203 040302", null, null));
            patients.addPatient(new Patient("Ove", "Ralt", "080101 340434", null, null));
        } catch (Exception e) {
            System.err.println("Patients already in registry");
        }
    }

    public void printPatient() {
        for(Patient patient : this.patients) {
            System.out.println(patient);
        }
    }
}
