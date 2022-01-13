package edu.ntnu.idata2001.mappedel2.GUIFactory;

import edu.ntnu.idata2001.mappedel2.Patient;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GUIFactory {

    /**
     * The menubar created in this factory.
     */
    private MenuBar menuBar;

    /**
     * The tableview created in this factory.
     */
    private TableView<Patient> tableView;

    /**
     * The toolbar created in this factory.
     */
    private ToolBar toolBar;

    /**
     * Returns menubar with menus file, edit and help.
     *
     * @return menubar with menus file, edit and help.
     */
    public MenuBar getMenuBar() {
        if (this.menuBar == null) {
            this.menuBar = new MenuBar();

            Menu file = new Menu("File");
            MenuItem importFromCSV = new MenuItem("Import from CSV");
            MenuItem exportFromCSV = new MenuItem("Export from CSV");
            SeparatorMenuItem separator = new SeparatorMenuItem();
            MenuItem exit = new MenuItem("Exit");

            file.getItems().addAll(importFromCSV, exportFromCSV, separator, exit);

            Menu edit = new Menu("Edit");
            MenuItem addNewPatient = new MenuItem("Add new patient");
            MenuItem editSelectedPatient = new MenuItem("Edit selected patient");
            MenuItem deleteSelectedPatient = new MenuItem("Delete selected patient");

            edit.getItems().addAll(addNewPatient, editSelectedPatient, deleteSelectedPatient);

            Menu help = new Menu("Help");
            MenuItem about = new MenuItem("About");

            help.getItems().addAll(about);

            this.menuBar.getMenus().addAll(file, edit, help);
        }

        return this.menuBar;
    }

    /**
     * Returns a tableview that displays patients, with columns for firstname, lastname,
     * socialsecurity number, diagnosis and general practitioner.
     *
     * @param patientList the patientlist to be displayed in this tableview.
     * @return tableview displaying the given patientlist.
     */
    public TableView<Patient> getTableView(ObservableList patientList) {
        if (tableView == null) {
            tableView = new TableView<>();

            TableColumn<Patient, String> firstNameColum = new TableColumn<>("First name");
            firstNameColum.setCellValueFactory(new PropertyValueFactory<>("firstName"));

            TableColumn<Patient, String> lastNameColumn = new TableColumn<>("Last name");
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

            TableColumn<Patient, String> socialSecurityColumn = new TableColumn<>("Social security");
            socialSecurityColumn.setCellValueFactory(new PropertyValueFactory<>("socialSecurity"));

            TableColumn<Patient, String> diagnosisColumn = new TableColumn<>("Diagnosis");
            diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

            TableColumn<Patient, String> generalPractitionerColumn = new TableColumn<>("General practitioner");
            generalPractitionerColumn.setCellValueFactory(new PropertyValueFactory<>("generalPractitionersName"));

            tableView.setItems(patientList);
            tableView.getColumns().addAll(
                    firstNameColum,
                    lastNameColumn,
                    socialSecurityColumn,
                    diagnosisColumn,
                    generalPractitionerColumn
            );

            this.tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        }

        return this.tableView;
    }

    /**
     * Returns a toolbar with buttons to add, edit and delete patients.
     *
     * @return toolbar with buttons to add, edit and delete patients.
     */
    public ToolBar getToolBar() {
        if (this.toolBar == null) {
            this.toolBar = new ToolBar();

            Button addPatientButton = new Button();
            ImageView addPatientIcon = new ImageView(new Image(getClass().getResource("/Icons/addpatient.png").toExternalForm()));
            addPatientIcon.setPreserveRatio(true);
            addPatientIcon.setFitWidth(45);
            addPatientButton.setGraphic(addPatientIcon);

            Button editSelectedPatientButton = new Button();
            ImageView editSelectedPatientIcon = new ImageView(new Image(getClass().
                    getResource("/Icons/editpatient.png").toExternalForm()));
            editSelectedPatientIcon.setPreserveRatio(true);
            editSelectedPatientIcon.setFitWidth(45);
            editSelectedPatientButton.setGraphic(editSelectedPatientIcon);

            Button deleteSelectedPatientButton = new Button();
            ImageView deleteSelectedPatientIcon = new ImageView(new Image(getClass().getClassLoader().
                    getResource("/Icons/deletepatient.png").toExternalForm()));
            deleteSelectedPatientIcon.setPreserveRatio(true);
            deleteSelectedPatientIcon.setFitWidth(45);
            deleteSelectedPatientButton.setGraphic(deleteSelectedPatientIcon);

            this.toolBar.getItems().addAll(addPatientButton, editSelectedPatientButton, deleteSelectedPatientButton);
        }

        return this.toolBar;
    }
}
