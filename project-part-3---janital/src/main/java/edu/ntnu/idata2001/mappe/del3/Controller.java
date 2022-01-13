package edu.ntnu.idata2001.mappe.del3;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Controller for the postalcodeApp scene.
 *
 * @author janitalillevikroyseth
 * @version 14.05.2021
 */
public class Controller {

    /**
     * A searchbar where the user can search after postalcodes.
     */
    @FXML
    private TextField searchField;

    /**
     * Table which displays the postalcodes.
     */
    @FXML
    private TableView<PostalCode> tableView;

    /**
     * The postalregistry being displayed in this app.
     */
    private PostalCodeRegistry postalCodeRegistry;

    /**
     * The registry list wrapped in an observable list.
     */
    private ObservableList<PostalCode> listWrapper;

    /**
     * Parser that imports and exports files.
     */
    private TextFileParser textFileParser;

    /**
     * Initializes the controller and sets information in
     * the tableview.
     */
    public void initialize() {
        this.postalCodeRegistry = new PostalCodeRegistryDaoDb();
        this.listWrapper = FXCollections.observableArrayList(this.postalCodeRegistry.getRegistry());
        textFileParser = new TextFileParser();
        this.searchPostalCodes();
        this.searchField.setFocusTraversable(false);
        this.setTableView();
    }

    /**
     * Displays the dialog for adding new postalcodes. If the user
     * confirms, a new instances of postalcode will be added to registry.
     */
    @FXML
    private void addPostalCode() {
        PostalCodeDetailsDialog postalCodeDetailsDialog =
                new PostalCodeDetailsDialog();
        Optional<PostalCode> result = postalCodeDetailsDialog.showAndWait();

        if (result.isPresent()) {
            PostalCode postalCode = result.get();
            try {
                this.postalCodeRegistry.addPostalCode(postalCode);
            } catch (AddingExistingElementException e) {
                warningDialog("Postnummer kunne ikke bli lagt til i registert",
                        "Postnummeret er allerede i listen.");
            }
            this.updateObservableList();
        }
    }

    /**
     * If a postalcode instance is selected a dialog for editing
     * postalcodes will be displayed. If the user confirms the
     * postalcode instances information will be changed.
     * If no postalcode instance is selected a
     * warning dialog will inform the user of this.
     */
    @FXML
    private void editPostalCode() {
        if (this.tableView.getSelectionModel()
                .getSelectedItem() == null) {
            warningDialog("Ingen postnummer er valgt!",
                    "Ingen postnummer valgt.\nVennligst velg et postnummer.");
        } else {
            PostalCodeDetailsDialog postalCodeDetailsDialog =
                    new PostalCodeDetailsDialog(this.tableView
                            .getSelectionModel().getSelectedItem());

            Optional<PostalCode> result = postalCodeDetailsDialog.showAndWait();
            if (result.isPresent()) {
                PostalCode postalCode = result.get();
                this.postalCodeRegistry.changePostalCode(postalCode);
                this.updateObservableList();
            }
        }
    }

    /**
     * If a postalcode instance is selected, a confirmation dialog
     * will be displayed and await confirmation to remove the selected
     * postalcode instance. If no postalcode instance is selected a
     * warning dialog will inform the user of this.
     */
    @FXML
    private void deletePostalCode() {
        if (this.tableView.getSelectionModel().getSelectedItem() == null) {
            warningDialog("Ingen postnummer er valgt!",
                    "Ingen postnummer valgt.\nVennligst velg et postnummer.");
        } else {
            if (removeConfirmationDialog()) {
                this.postalCodeRegistry.removePostalCode(this.tableView.
                        getSelectionModel().getSelectedItem());
                this.updateObservableList();
            }
        }
    }

    /**
     * Imports postalcodes from the chosen file.
     */
    @FXML
    private void importFromFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter =
                new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(filter);
        File selectedFile = fileChooser.showOpenDialog(Main.getStage());
        if (selectedFile != null) {
            try {
                this.textFileParser.importPostalCodesFromTextFile(
                        selectedFile.getPath(), this.postalCodeRegistry);
                this.updateObservableList();
            } catch (IOException e) {
                warningDialog("Noe gikk galt..",
                        "Noe uforutsett gikk galt, prøv igjen");
                this.updateObservableList();
            } catch (AddingExistingElementException e) {
                warningDialog("Element i filen finnes allerede",
                        ("Elementer i filen finnes allerede og ble ikke lagt til"));
                this.updateObservableList();
            } catch (Exception e) {
                warningDialog("Feil format",
                        ("Filen var ikke i riktig format "
                        + "til å kunne lastes opp. Noen element kan"
                        + " mangle fra listen."));
                this.updateObservableList();
            }
        }
    }

    /**
     * Exports the elements in the tableview to tab separated
     * text file.
     */
    @FXML
    private void exportToFile() {
        if (this.postalCodeRegistry.iterator().hasNext()) {
            FileChooser fileChooser = new FileChooser();
            File newFile = fileChooser.showSaveDialog(Main.getStage());
            try {
                if (newFile != null) {
                    if (newFile.getName().length() > 4) {
                        String substring = newFile.getName()
                                .substring(newFile.getName().length() - 4);
                        if (!substring.equals(".txt")) {
                            textFileParser.exportPostalCodesToTextFile(
                                    newFile.getPath() + ".txt",
                                    this.postalCodeRegistry);
                        } else {
                            textFileParser.exportPostalCodesToTextFile(
                                    newFile.getPath(), this.postalCodeRegistry);
                        }
                    }
                }
            } catch (IOException e) {
                warningDialog("Noe gikk galt...",
                        "Noe uforutsett gikk galt, prøv igjen");
            }
        }
    }

    /**
     * Exits the application depending on the input on the confirmation dialog.
     */
    @FXML
    private void exitApplication() {
        ImageView confirmationIcon = new ImageView(new Image(getClass()
                .getResource("/confirmation.png").toExternalForm()));
        confirmationIcon.setPreserveRatio(true);
        confirmationIcon.setFitWidth(50);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Avslutt");
        alert.setHeaderText("Avslutt programmet");
        alert.setGraphic(confirmationIcon);
        String stylesheet = Main.getStage().getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.setContentText("Er du sikker på at du vil avslutte programmet?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            if (result.get() == ButtonType.OK) {
                Platform.exit();
            }
        }
    }

    /**
     * Displays the about dialog that shows information about this application.
     */
    @FXML
    private void showAboutDialog() {
        ImageView informationIcon = new ImageView(new Image(getClass().
                getResource("/information.png").toExternalForm()));
        informationIcon.setPreserveRatio(true);
        informationIcon.setFitWidth(50);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasjon - Om Applikasjonen");
        alert.setHeaderText("Postnummerregister");
        alert.getDialogPane().setGraphic(informationIcon);
        String stylesheet = Main.getStage().getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.setContentText("En supercalifragilisticexpialidocious \n"
                + "applikasjon av Janita Røyseth \n"
                + "versjon: 14.05.2021");

        alert.showAndWait();
    }

    /**
     * Displays the about dialog with tips to how to use the application.
     */
    @FXML
    private void showTipsDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasjon - Tips");
        alert.setHeaderText("Tips for å bruke applikasjon");
        ImageView informationIcon = new ImageView(new Image(getClass().
                getResource("/information.png").toExternalForm()));
        informationIcon.setPreserveRatio(true);
        informationIcon.setFitWidth(50);
        alert.getDialogPane().setGraphic(informationIcon);
        String stylesheet = Main.getStage().getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.setContentText("For å søke i applikasjonen kan du søker "
                + "etter deler og hele postnummer. "
        + "Du kan også spesifisere hva du vil søke etter på denne måten: \n"
        + "\n \"Postnummer: 6014\""
        + "\n \"Poststed: Ålesund\""
        + "\n \"Kommunenummer: 1507\""
        + "\n \"Kommune: Ålesund\"");

        alert.showAndWait();
    }

    /**
     * Sets the tableview with appropriate columns.
     */
    private void setTableView() {
        TableColumn<PostalCode, Integer> codeColumn =
                new TableColumn<>("Postnummer");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<PostalCode, String> cityColumn =
                new TableColumn<>("Poststed");
        cityColumn.setCellValueFactory(
                new PropertyValueFactory<>("city"));

        TableColumn<PostalCode, Integer> municipalityKeyColumn =
                new TableColumn<>("Kommunenummer");
        municipalityKeyColumn.setCellValueFactory(
                new PropertyValueFactory<>("municipalityKey"));

        TableColumn<PostalCode, String> municipalityColumn =
                new TableColumn<>("Kommune");
        municipalityColumn.setCellValueFactory(
                new PropertyValueFactory<>("municipality"));

        TableColumn<PostalCode, String> categoryColumn =
                new TableColumn<>("Kategori");
        categoryColumn.setCellValueFactory(
                new PropertyValueFactory<>("category"));
        categoryColumn.setMinWidth(200);

        this.tableView.setItems(this.listWrapper);
        this.tableView.getColumns().addAll(
                codeColumn,
                cityColumn,
                municipalityKeyColumn,
                municipalityColumn,
                categoryColumn
        );
    }

    /**
     * Updates the observable list.
     */
    private void updateObservableList() {
        this.listWrapper.setAll(this.postalCodeRegistry.getRegistry());
    }

    /**
     * Iterates over the postalcode registry and displays the matching
     * postalcodes with the input in the searchfield.
     */
    private void searchPostalCodes() {
        ObservableList<PostalCode> postalCodesFound =
                FXCollections.observableArrayList();

        searchField.textProperty().addListener((obsVal, oldVal, newVal) -> {
            if (!newVal.isBlank()) {

                if (newVal.toUpperCase().contains("POSTNUMMER:")) {
                    postalCodesFound.setAll(this.postalCodeRegistry
                            .findPostalCodesByCode(
                                    newVal.replaceAll(" ", "").substring(11)));
                    this.tableView.setItems(postalCodesFound);
                } else if (newVal.toUpperCase().contains("POSTSTED:")) {
                    postalCodesFound.setAll(this.postalCodeRegistry
                            .findPostalCodesByCity(
                                    newVal.replaceAll(" ", "").substring(9)));
                    this.tableView.setItems(postalCodesFound);
                } else if (newVal.toUpperCase().contains("KOMMUNENUMMER:")) {
                    postalCodesFound.setAll(this.postalCodeRegistry
                            .findPostalCodesByMunicipalityKey(
                                    newVal.replaceAll(" ", "").substring(14)));
                    this.tableView.setItems(postalCodesFound);
                } else if (newVal.toUpperCase().contains("KOMMUNE:")) {
                    postalCodesFound.setAll(this.postalCodeRegistry
                            .findPostalCodesByMunicipality(
                                    newVal.replaceAll(" ", "").substring(8)));
                    this.tableView.setItems(postalCodesFound);
                } else {
                    postalCodesFound.setAll(
                            this.postalCodeRegistry.findPostalCodes(newVal));
                    this.tableView.setItems(postalCodesFound);
                }
            } else {
                tableView.setItems(this.listWrapper);
            }
        });
    }

    /**
     * Displays a warning dialog with the given information.
     *
     * @param title the title of the warning.
     * @param content the context of the warning.
     */
    private void warningDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advarsel");
        ImageView warningIcon = new ImageView(
                new Image(getClass().getResource(
                        "/warning.png").toExternalForm()));
        warningIcon.setFitWidth(50);
        warningIcon.setPreserveRatio(true);
        alert.setGraphic(warningIcon);
        alert.setHeaderText(title);
        alert.setContentText(content);
        String stylesheet = Main.getStage().getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation dialog when a postalcode is attempted to
     * be removed from the registry. Returns whether the removal of the
     * postalcode was intended or not.
     *
     * @return whether the postal code is to removed or not.
     */
    private boolean removeConfirmationDialog() {
        boolean removeConfirmation = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ImageView confirmationIcon = new ImageView(
                new Image(getClass().getResource(
                        "/confirmation.png").toExternalForm()));
        confirmationIcon.setFitWidth(50);
        confirmationIcon.setPreserveRatio(true);
        alert.setGraphic(confirmationIcon);
        String stylesheet = Main.getStage().getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.setTitle("Slett postnummer");
        alert.setHeaderText("Slett postnummeret: " + this.tableView
                .getSelectionModel().getSelectedItem().getCode() + " "
                + this.tableView.getSelectionModel().getSelectedItem().getCity()
                + "\nfra registeret?");
        alert.setContentText("Vil du slette dette postnummeret "
                + "fra postnummer registeret?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            removeConfirmation = (result.get() == ButtonType.OK);
        }
        return removeConfirmation;
    }
}
