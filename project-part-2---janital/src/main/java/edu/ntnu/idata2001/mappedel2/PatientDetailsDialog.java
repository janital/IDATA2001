package edu.ntnu.idata2001.mappedel2;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class PatientDetailsDialog extends Dialog<Patient> {

    /**
     * The modes of the dialog. <code>NEW</code> is used when a
     * new patient is to be added.
     * <code>EDIT</code> is used when an existing patient is edited.
     */
    public enum Mode {
        NEW, EDIT
    }

    /**
     * The mode of the dialog. NEW if new patient, EDIT if editing
     * existing patient.
     */
    private final Mode mode;

    /**
     * Holds the patient to be edited.
     */
    private Patient patient = null;

    /**
     * Creates an instance of PatientDetailsDialog and creates a new
     * instance of patient from the given information.
     */
    public PatientDetailsDialog() {
        super();
        this.mode = Mode.NEW;
        createContent();

    }

    /**
     * Creates an instance of PatientsDetailsDialog used to change the
     * information of the selected patient.
     *
     * @param patient selected patient who's information will be changed
     */
    public PatientDetailsDialog(Patient patient) {
        super();
        this.mode = Mode.EDIT;
        this.patient = patient;
        createContent();
    }

    /**
     * Creates the content of the patient details dialog.
     */
    private void createContent() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField firstName = new TextField();
        firstName.setPromptText("First name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last name");

        TextField socialSecurity = new TextField();
        socialSecurity.setPromptText("Social security");

        TextField diagnosis = new TextField();
        diagnosis.setPromptText("Diagnosis");

        ComboBox<String> generalPractitioner = new ComboBox<>();
        generalPractitioner.setPromptText("Select a generalpractitioner");
        generalPractitioner.getItems().addAll("Maren G. Skake", "Dr. Klø",
                "Matti Ompa", "Rally McBil", "Edel Gran", "Pøl Asset", "Synne Sikke");


        grid.add(new Label("First name:"), 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(new Label("Last name:"), 0, 1);
        grid.add(lastName, 1, 1);
        grid.add(new Label("Social security:"), 0, 2);
        grid.add(socialSecurity, 1, 2);
        grid.add(new Label("Diagnosis:"), 0, 3);
        grid.add(diagnosis, 1, 3);
        grid.add(new Label("Generalpractitioner"), 0, 4);
        grid.add(generalPractitioner, 1, 4);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        final Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            //Continue the dialog if one of the specified conditions happen.
            if (firstName.getText().isBlank() || lastName.getText().isBlank() || generalPractitioner.getSelectionModel().getSelectedItem() == null) {
                String title = "All fields need to be filled!";
                String context = "Some fields are missing information.\n"
                        + "Please fill all fields.";
                inputErrorDialog(title, context);
                event.consume();
            } else if (mode == Mode.NEW && !checkValidBirthdate(socialSecurity.getText())) {
                String title = "Not a valid social security number!";
                String context = "The social security number does not contain\n"
                        + " a valid birthdate. Please try again!";
                inputErrorDialog(title, context);
                event.consume();
            }
        });

        if ((mode == Mode.EDIT)) {
            //Custom setup of dialog for EDIT mode
            setTitle("Patient Details - Edit");
            ImageView editPatientIcon = new ImageView(new Image(getClass().getResource("/Icons/editpatient.png").toExternalForm()));
            editPatientIcon.setPreserveRatio(true);
            editPatientIcon.setFitWidth(50);
            getDialogPane().setGraphic(editPatientIcon);
            getDialogPane().setHeaderText("Edit patient "
                    + this.patient.getFirstName() + " "
                    + this.patient.getLastName() + "'s information"
            );
            firstName.setText(this.patient.getFirstName());
            lastName.setText(this.patient.getLastName());
            socialSecurity.setText(this.patient.getSocialSecurity());
            socialSecurity.setDisable(true);
            diagnosis.setText(this.patient.getDiagnosis());
            generalPractitioner.getSelectionModel().select(this.patient.getGeneralPractitioner());
        } else if (mode == Mode.NEW) {
            //Custom setup of dialog for NEW mode
            setTitle("Patient Details - Add");
            ImageView addPatientIcon = new ImageView(new Image(getClass()
                    .getResource("/Icons/addpatient.png").toExternalForm()));
            addPatientIcon.setPreserveRatio(true);
            addPatientIcon.setFitWidth(50);
            getDialogPane().setGraphic(addPatientIcon);
            getDialogPane().setHeaderText("Add new patient to registry");
            socialSecurityParser(socialSecurity);
        }

        //Convert result into Patient when OK is pressed.
        setResultConverter((ButtonType button) -> {
            Patient result = null;
            if (button == ButtonType.OK) {
                if (mode == Mode.NEW) {
                    try {
                        result = new Patient(
                                firstName.getText(),
                                lastName.getText(),
                                socialSecurity.getText(),
                                generalPractitioner.getSelectionModel().getSelectedItem(),
                                diagnosis.getText()
                        );
                    } catch (IllegalArgumentException e) {
                        String title = "All fields need to be filled!";
                        String context = "Some fields are missing information.\n"
                                + "Please fill all fields.";
                        inputErrorDialog(title, context);
                    }
                } else if (mode == Mode.EDIT) {
                    this.patient.setFirstName(firstName.getText());
                    this.patient.setLastName(lastName.getText());
                    this.patient.setDiagnosis(diagnosis.getText());
                    this.patient.setGeneralPractitioner(generalPractitioner.getSelectionModel().getSelectedItem());
                    result = this.patient;
                }
            } else if (button == ButtonType.CANCEL) {
                close();
            }
            return result;
        });
    }

    /**
     * Displays a warning dialog with given information about the issue.
     *
     * @param title short description of the issue.
     * @param context the context of the issue.
     */
    private void inputErrorDialog(String title, String context) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        ImageView warningIcon = new ImageView(new Image(getClass()
                .getResource("/Icons/warning.png").toExternalForm()));
        warningIcon.setPreserveRatio(true);
        warningIcon.setFitWidth(75);
        alert.getDialogPane().setGraphic(warningIcon);
        alert.setTitle("Information");
        alert.setHeaderText(title);
        alert.setContentText(context);
        String stylesheet = getDialogPane().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.showAndWait();
    }

    /**
     * Parses the input from the textfield to long int.
     *
     * @param textField the textfield to parse input from.
     */
    private void socialSecurityParser(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (newValue.length() > 0 && newValue.length() <= 11) {
                    Long.parseLong(newValue);
                } else if (newValue.length() >= 12) {
                    textField.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                textField.setText(oldValue);
            }
        });
    }

    /**
     * Checks to see if the first 6 digits in the social security number is
     * a valid date.
     *
     * @param socialSecurity social security number to be checked.
     * @return <code>true</code> if the social security number contains a valid date.
     * <code>false</code> if the social security number does not contain a valid date.
     */
    private boolean checkValidBirthdate(String socialSecurity) {
        boolean isValid = false;
        StringBuilder string = new StringBuilder(socialSecurity);

        string.insert(4, 19);

        DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
        dateFormat.setLenient(false);
        if (socialSecurity.length() == 11) {
            try {
                dateFormat.parse(string.substring(0));
                isValid = true;
            } catch (ParseException e) {
                isValid = false;
            }
        }

        return isValid;
    }
}