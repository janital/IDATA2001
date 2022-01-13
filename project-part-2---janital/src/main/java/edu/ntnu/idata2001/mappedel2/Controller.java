package edu.ntnu.idata2001.mappedel2;

import com.opencsv.exceptions.CsvException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * The controller for the application.
 * Handles all actions, information and events related to the GUI.
 *
 * @author janitalillevikroyseth
 * @version 14.04.2021
 */
public class Controller {

    /**
     * Searchbar for the user to search for patients.
     */
    @FXML
    private TextField searchBar;

    /**
     * The patient registry which contains the list over patients.
     */
    private PatientRegistry patients;

    /**
     * An ObservableList used to "wrap" the real patient registry to create
     * a link between the TableView and PatientRegistry.
     */
    private ObservableList<Patient> patientsListWrapper;

    /**
     * The TableView displaying the patient registry.
     */
    @FXML
    private TableView<Patient> patientTableView;

    /**
     * Csv file parser, used to parse csv files.
     */
    private final CSVFileParser csvFileParser = new CSVFileParser();

    /**
     * Initializing necessary objects for the application.
     */
    public void initialize() {
        getPatientsListWrapper();
        this.patients = new PatientRegistryDaoDb();
        this.setPatientTableView();
        findPatient();
        Main.getStage().onCloseRequestProperty().addListener(e -> this.patients.close());
        updateObservableList();
    }

    /**
     * Displays the dialog for receiving information to create a new patient.
     * If the user confirms after adding input, a new instance of patient will
     * be created and added to the patient registry.
     */
    @FXML
    private void addPatient() {
        PatientDetailsDialog patientDetailsDialog = new PatientDetailsDialog();
        String stylesheet = Main.getStage().getScene().getStylesheets().get(0);
        patientDetailsDialog.getDialogPane().getStylesheets().add(stylesheet);
        Optional<Patient> result = patientDetailsDialog.showAndWait();

        if (result.isPresent()) {
            Patient patient = result.get();
            try {
                this.patients.addPatient(patient);
            } catch (AddPatientToRegistryException e) {
                warningDialog("The patient is already in the registry!",
                        patient.getFirstName() + " " + patient.getLastName()
                        + " is already in the registry.");
            }
            updateObservableList();
        }
    }

    /**
     * Displays the dialog for editing information of the selected patient.
     * If the user confirms after adding input, the patient's fields will be
     * changed accordingly. If no patient is selected a warning dialog will
     * displayed.
     */
    @FXML
    private void editPatient() {
        if (this.patientTableView.getSelectionModel()
                .getSelectedItem() == null) {
            warningDialog("No patient selected!",
                    ("No patient selected.\n" + "Please select a patient."));
        } else {
            PatientDetailsDialog patientDetailsDialog =
                    new PatientDetailsDialog(this.patientTableView
                            .getSelectionModel().getSelectedItem());
            String stylesheet = searchBar.getScene().getStylesheets().get(0);
            patientDetailsDialog.getDialogPane().getStylesheets().add(stylesheet);
            Optional<Patient> result = patientDetailsDialog.showAndWait();
            if (result.isPresent()) {
                Patient patient = result.get();
                this.patients.changePatientsName(patient);
                updateObservableList();
            }
        }
    }

    /**
     * Deletes a patient from the registry. Displays confirmation dialog first.
     * If a patient is not selected, a warning dialog will be displayed.
     */
    @FXML
    private void deletePatient() {
        if (this.patientTableView.getSelectionModel().getSelectedItem() == null) {
            warningDialog("No patient selected!", ("No patient was selected.\n"
                    + "Please select a patient."));
        } else {
            if (removeConfirmationDialog()) {
                this.patients.removePatient(this.patientTableView.
                        getSelectionModel().getSelectedItem());
                updateObservableList();
                this.patientTableView.setItems(this.patientsListWrapper);
            }
        }
    }

    /**
     * S
     */
    @FXML
    private void setDiagnosis() {
        if (this.patientTableView.getSelectionModel().getSelectedItem() == null) {
            warningDialog("No patient selected!", ("No patient was selected.\n"
                    + "Please select a patient."));
        } else {
            if (removeConfirmationDialog()) {
                this.patients.removePatient(this.patientTableView.
                        getSelectionModel().getSelectedItem());
                updateObservableList();
                this.patientTableView.setItems(this.patientsListWrapper);
            }
        }
    }

    @FXML
    private void removeDiagnosis() {
        if (this.patientTableView.getSelectionModel().getSelectedItem() == null) {
            warningDialog("No patient selected!", ("No patient was selected.\n"
                    + "Please select a patient."));
        } else {
            if (removeConfirmationDialog()) {
                this.patients.removePatient(this.patientTableView.
                        getSelectionModel().getSelectedItem());
                updateObservableList();
                this.patientTableView.setItems(this.patientsListWrapper);
            }
        }
    }

    /**
     * Exits the application depending on the input on the confirmation dialog.
     */
    @FXML
    private void exitApplication() {
        ImageView confirmationIcon = new ImageView(new Image(getClass()
                .getResource("/Icons/confirmation.png").toExternalForm()));
        confirmationIcon.setPreserveRatio(true);
        confirmationIcon.setFitWidth(75);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Exit application");
        alert.setGraphic(confirmationIcon);
        alert.setContentText("Are you sure you want to exit this application?");
        String stylesheet = searchBar.getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        }
    }

    /**
     * Sets up table holding the patient registry list with appropriate columns.
     */
    private void setPatientTableView() {
        TableColumn<Patient, String> firstNameColum =
                new TableColumn<>("First name");
        firstNameColum.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));

        TableColumn<Patient, String> lastNameColumn =
                new TableColumn<>("Last name");
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));

        TableColumn<Patient, String> socialSecurityColumn =
                new TableColumn<>("Social security");
        socialSecurityColumn.setCellValueFactory(
                new PropertyValueFactory<>("socialSecurity"));

        TableColumn<Patient, String> diagnosisColumn = new TableColumn<>("Diagnosis");
        diagnosisColumn.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

        TableColumn<Patient, String> generalPractitionerColumn =
                new TableColumn<>("General practitioner");
        generalPractitionerColumn.setCellValueFactory(
                new PropertyValueFactory<>("generalPractitioner"));

        this.patientTableView.setPlaceholder(new Label
                ("Please load patientregistry to edit patients"));
        this.patientTableView.setItems(getPatientsListWrapper());
        this.patientTableView.getColumns().addAll(
                firstNameColum,
                lastNameColumn,
                socialSecurityColumn,
                diagnosisColumn,
                generalPractitionerColumn
        );
    }

    /**
     * Displays the filechooser and waits for a file to be selected.
     * If a file is selected the file will be parsed from the file
     * to patient instances and added to the patient registry.
     */
    @FXML
    private void importFromCSV() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter =
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showOpenDialog(Main.getStage());
        if (selectedFile != null) {
            try {
                csvFileParser.importPatientsFromCSV(selectedFile.getPath(), this.patients);

                //----------IF PATIENTS WILL NOT LOAD FROM CSV-------//
                List<CsvException> exceptionList = csvFileParser.getExceptions();
                if(!exceptionList.isEmpty()) {
                    String linesNotEntered = "";
                    for (CsvException exception : exceptionList) {
                        linesNotEntered += exception.getLineNumber() + " ";
                    }
                    warningDialog("Some patients couldn't load!",
                            "The patients at line \n" + linesNotEntered
                                    + "could not be read properly by the parser");
                }
                //---------------------------------------------------//

                this.updateObservableList();
            } catch (IOException e) {
                warningDialog("Invalid file format",
                        "The file provided was not \n"
                                + "recognized. Please proved a .csv file");
                fileChooser.showSaveDialog(Main.getStage());
            } catch (AddPatientToRegistryException e){
                warningDialog("Some of the given patients are already in the registry!",
                        "Some of the patients in the file provided had the same social security number. \n"
                                + "Please go over you file and check for mistakes before you try again.");
            } catch (Exception e) {
                warningDialog("Something went wrong...",
                        "The file could not parse properly. \n"
                                + "Some or all patients might be missing from the registry");
            }
        }
    }

    /**
     * Exports the patient registry to csv file.
     */
    @FXML
    private void exportToCSV() {
        if (this.patients.iterator().hasNext()) {
            FileChooser fileChooser = new FileChooser();
            File newFile = fileChooser.showSaveDialog(searchBar.getScene().getWindow());
            try {

                if (newFile != null) {
                    if (newFile.getName().length() > 4) {
                        String substring = newFile.getName().substring(newFile.getName().length() - 4);
                        if (!substring.equals(".csv")) {
                            csvFileParser.exportPatientsToCSV(newFile.getPath() + ".csv", this.patients);
                        } else {
                            csvFileParser.exportPatientsToCSV(newFile.getPath(), this.patients);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            warningDialog("No patients in registry!",
                    ("There are no patients in this registry. "
                    + "Please add patients before you export"));
        }
    }

    /**
     * Displays the about dialog that shows information about this application.
     */
    @FXML
    private void showAboutDialog(ActionEvent actionEvent) {
        ImageView informationIcon = new ImageView(new Image(getClass().
                getResource("/Icons/information.png").toExternalForm()));
        informationIcon.setPreserveRatio(true);
        informationIcon.setFitWidth(75);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog - About");
        alert.setHeaderText("Patient Registry");
        alert.getDialogPane().setGraphic(informationIcon);
        String stylesheet = searchBar.getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.setContentText("A supercalifragilisticexpialidocious \n"
                + "application by Janita Røyseth \n"
                + "version: 1.05.2021");

        alert.showAndWait();
    }

    /**
     * Returns an ObservableList holding the patient registry to display.
     *
     * @return ObsevableList holding the patient registry to display.
     */
    private ObservableList<Patient> getPatientsListWrapper() {
        patientsListWrapper = FXCollections.observableArrayList();
        return patientsListWrapper;
    }

    /**
     * Updates the ObervableArray wrapped around the patient registry.
     *
     * with the current content of the patient registry list.
     */
    private void updateObservableList() {
        this.patientsListWrapper.clear();
        for(Patient patient : this.patients) {
            this.patientsListWrapper.add(patient);
        }
    }

    /**
     * Displays a confirmation dialog asking of the user want to remove the selected patient.
     *
     * @return <code>true</code> if the user confirms to remove the patient.
     */
    private boolean removeConfirmationDialog() {
        boolean removeConfirmation = false;

        ImageView confirmationIcon = new ImageView(new Image(getClass().
                getResource("/Icons/confirmation.png").toExternalForm()));
        confirmationIcon.setPreserveRatio(true);
        confirmationIcon.setFitWidth(75);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String stylesheet = searchBar.getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.setTitle("Removal Confirmation");
        alert.getDialogPane().setGraphic(confirmationIcon);
        alert.setHeaderText("Removing " + this.patientTableView
                .getSelectionModel().getSelectedItem().getFirstName() + " "
                + this.patientTableView.getSelectionModel()
                .getSelectedItem().getLastName() + " "
                + "from patient registry!");
        alert.setContentText("Are you sure you want to remove this patient from the registry?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            removeConfirmation = (result.get() == ButtonType.OK);
        }
        return removeConfirmation;
    }

    /**
     * Displays a warning dialog informing the user that no patient was selected.
     */
    private void warningDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ImageView warningIcon = new ImageView(new Image(getClass()
                .getResource("/Icons/warning.png").toExternalForm()));
        warningIcon.setPreserveRatio(true);
        warningIcon.setFitWidth(75);
        alert.getDialogPane().setGraphic(warningIcon);
        alert.setTitle("Warning");
        alert.setHeaderText(title);
        alert.setContentText(content);
        String stylesheet = searchBar.getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.showAndWait();
    }

    /**
     * Searches for patients with values corresponding to the input
     * given in the searchbox, displays these values in the tableview.
     */
    private void findPatient() {
        ObservableList<Patient> patientsFound = FXCollections.observableArrayList();

        searchBar.textProperty().addListener((obsVal, oldVal, newVal) -> {
            if (newVal != null || !newVal.isBlank()) {
                patientTableView.getItems().clear();
                patientsFound.setAll(this.patients.findPatients(newVal));
                patientTableView.setItems(patientsFound);
            } else {
                patientTableView.getItems().clear();
                updateObservableList();
                patientTableView.setItems(patientsListWrapper);
            }
        });
    }

    /**
     * Switches out the current theme with the standard theme.
     */
    @FXML
    private void setStandardTheme() {
        searchBar.getScene().getStylesheets().remove(0);
        searchBar.getScene().getStylesheets().add(
                getClass().getResource("/stylesheets/Standard.css").toExternalForm());
    }

    /**
     * Switches out the current theme with the dark theme.
     */
    @FXML
    private void setDarkTheme() {
        searchBar.getScene().getStylesheets().remove(0);
        searchBar.getScene().getStylesheets().add(
                getClass().getResource("/stylesheets/Darktheme.css").toExternalForm());
    }

    /**
     * Switches out the current theme with the neon theme.
     */
    @FXML
    private void setNeonTheme() {
        searchBar.getScene().getStylesheets().remove(0);
        searchBar.getScene().getStylesheets().add(
                getClass().getResource("/stylesheets/Neontheme.css").toExternalForm());
    }
}
