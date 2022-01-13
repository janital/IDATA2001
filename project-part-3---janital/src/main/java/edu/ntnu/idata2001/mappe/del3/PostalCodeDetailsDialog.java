package edu.ntnu.idata2001.mappe.del3;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Represents a dialog which recieves and displays information about
 * postalcodes.
 *
 * @author janitalillevikroyseth
 * @version 14.05.2021
 */
public class PostalCodeDetailsDialog extends Dialog<PostalCode> {

    /**
     * The modes of the dialog. <code>NEW</code> is used when a
     * new postalcode is to be added.
     * <code>EDIT</code> is used when an existing postalcode is edited.
     */
    public enum Mode {
        NEW, EDIT
    }

    /**
     * The mode of the dialog. NEW if new postalcode, EDIT if editing
     * existing postalcode.
     */
    private final Mode mode;

    /**
     * Holds the postalcode to be edited if any.
     */
    private PostalCode postalCode = null;

    /**
     * Creates an instance of PostalCodeDetailsDialog and creates a new
     * instance of PostalCode from the given information.
     */
    public PostalCodeDetailsDialog() {
        super();
        this.mode = Mode.NEW;
        createContent();

    }

    /**
     * Creates an instance of PostalCodeDetailsDialog used to change the
     * information of the selected postalcode.
     *
     * @param postalCode selected postalcode where information will be changed
     */
    public PostalCodeDetailsDialog(PostalCode postalCode) {
        super();
        this.mode = Mode.EDIT;
        this.postalCode = postalCode;
        createContent();
    }

    /**
     * Creates the content of the postalcode details dialog.
     */
    private void createContent() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField code = new TextField();
        code.setPromptText("Postnummer");

        TextField city = new TextField();
        city.setPromptText("Poststed");

        TextField municipalityKey = new TextField();
        municipalityKey.setPromptText("Kommunenummer");

        TextField municipality = new TextField();
        municipality.setPromptText("Kommune");

        ComboBox<String> category = new ComboBox<>();
        category.setPromptText("Velg en poststed kategori");
        category.getItems().addAll(
                "Både gateadresser og postbokser",
                "Flere bruksområder (felles)",
                "Gateadresser (og stedsadresser), dvs. grønne postkasser",
                "Postbokser",
                "Servicepostnummer");
        category.setMaxWidth(200);


        grid.add(new Label("Postnummer:"), 0, 0);
        grid.add(code, 1, 0);
        grid.add(new Label("Poststed"), 0, 1);
        grid.add(city, 1, 1);
        grid.add(new Label("Kommune nr.:"), 0, 2);
        grid.add(municipalityKey, 1, 2);
        grid.add(new Label("Kommune:"), 0, 3);
        grid.add(municipality, 1, 3);
        grid.add(new Label("Poststed kategori:"), 0, 4);
        grid.add(category, 1, 4);

        getDialogPane().setContent(grid);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        final Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.addEventFilter(ActionEvent.ACTION, event -> {
            //Continue the dialog if one of the specified conditions happen.
            if (code.getText().isBlank() || city.getText().isBlank()
                    || municipalityKey.getText().isBlank() || municipality.getText().isBlank()
                    || category.getSelectionModel().getSelectedItem() == null) {
                String title = "Alle felt må fylles ut!";
                String context = "Noen felt mangler informasjon.\n"
                        + "Vennligst fyll ut alle felt og prøv igjen.";
                inputErrorDialog(title, context);
                event.consume();
            }
        });

        if ((mode == Mode.EDIT)) {
            //Custom setup of dialog for EDIT mode
            ImageView editIcon = new ImageView(new Image(getClass().getResource("/edit.png").toExternalForm()));
            editIcon.setFitWidth(50);
            editIcon.setPreserveRatio(true);
            getDialogPane().setGraphic(editIcon);
            getDialogPane().getStylesheets().add(Main.getStage().getScene().getStylesheets().get(0));
            setTitle("Postnummer - Endre");
            getDialogPane().setHeaderText("Endre postnummer");
            parseIntegersFromTextField(code);
            parseIntegersFromTextField(municipalityKey);
            code.setText(this.postalCode.getCode());
            code.setFocusTraversable(false);
            code.setMouseTransparent(true);
            city.setText(this.postalCode.getCity());
            municipalityKey.setText(this.postalCode.getMunicipalityKey());
            municipality.setText(this.postalCode.getMunicipality());
            category.getSelectionModel().select(this.postalCode.getCategory());
        } else if (mode == Mode.NEW) {
            //Custom setup of dialog for NEW mode
            ImageView addIcon = new ImageView(new Image(getClass().getResource("/add.png").toExternalForm()));
            addIcon.setFitWidth(50);
            addIcon.setPreserveRatio(true);
            getDialogPane().setGraphic(addIcon);
            getDialogPane().getStylesheets().add(Main.getStage().getScene().getStylesheets().get(0));
            setTitle("Postnummer - Legg til");
            getDialogPane().setHeaderText("Legg til nytt postnummer");
            parseIntegersFromTextField(code);
            parseIntegersFromTextField(municipalityKey);
        }

        //Convert result into Patient when OK is pressed.
        setResultConverter((ButtonType button) -> {
            PostalCode result = null;
            if (button == ButtonType.OK) {
                if (mode == Mode.NEW) {
                    try {
                        result = new PostalCode(
                                code.getText(),
                                city.getText(),
                                municipalityKey.getText(),
                                municipality.getText(),
                                String.valueOf(category.getSelectionModel().getSelectedItem().charAt(0))
                        );
                    } catch (IllegalArgumentException e) {
                        String title = "Alle felt må fylles ut!";
                        String context = "Noen felt mangler informasjon.\n"
                                + "Vennligst fyll ut alle felt og prøv igjen.";
                        inputErrorDialog(title, context);
                    }
                } else if (mode == Mode.EDIT) {
                    this.postalCode.setCity(city.getText());
                    this.postalCode.setMunicipalityKey(municipalityKey.getText());
                    this.postalCode.setMunicipality(municipality.getText());
                    this.postalCode.setCategory(String.valueOf(category.getSelectionModel().getSelectedItem().charAt(0)));
                    result = this.postalCode;
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
        alert.setTitle("Informasjon");
        ImageView warningIcon = new ImageView(new Image(getClass().getResource("/warning.png").toExternalForm()));
        warningIcon.setPreserveRatio(true);
        warningIcon.setFitWidth(50);
        alert.setGraphic(warningIcon);
        String stylesheet = Main.getStage().getScene().getStylesheets().get(0);
        alert.getDialogPane().getStylesheets().add(stylesheet);
        alert.setHeaderText(title);
        alert.setContentText(context);
        alert.showAndWait();
    }

    /**
     * Parses the integers from the textfield and sets the old value
     * of the textfield when a non-integer is attempted to be set.
     *
     * @param textField the textfield to parse from.
     */
    private void parseIntegersFromTextField(TextField textField) {
        textField.textProperty().addListener((obsVal, oldVal, newVal) -> {
            try {
                if (newVal.length() > 0 && newVal.length() <= 4) {
                    Integer.parseInt(newVal);
                } else if (newVal.length() >= 4) {
                    textField.setText(oldVal);
                }
            } catch (NumberFormatException e) {
                textField.setText(oldVal);
            }
        });
    }
}