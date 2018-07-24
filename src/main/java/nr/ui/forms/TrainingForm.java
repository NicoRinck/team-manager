package nr.ui.forms;

import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import nr.data_converter.user_input_converter.TrainingAttributesInputConverter;
import nr.data_model.entities.appointment.TrainingAttributes;
import nr.ui.components.form_components.AddressComponent;
import nr.ui.components.form_components.FormComponent;

import java.time.LocalDate;
import java.util.Optional;

public class TrainingForm extends DialogForm<TrainingAttributes> {

    private final GridPane gridPane = new GridPane();
    private final DatePicker datePicker = new DatePicker();
    private final TextField hours = new TextField();
    private final TextField minutes = new TextField();
    private final TextField minimalDuration = new TextField();
    private final TextField maximalDuration = new TextField();
    private AddressComponent addressComponent;
    private final Label dateTimeError = new Label();
    private final Label durationError= new Label();

    public TrainingForm() {
        initGrid();
        dateTimeError.setStyle("-fx-text-fill: red");
        durationError.setStyle("-fx-text-fill: red");
    }

    private void setVBoxContent() {
        vBox.getChildren().clear();
        vBox.getChildren().addAll(gridPane, addressComponent.getComponent());
    }

    private void initGrid() {
        this.gridPane.setVgap(10);
        this.gridPane.setHgap(10);
        initTimeFields();
        datePicker.setValue(LocalDate.now());
        gridPane.add(new Label("Datum: "), 0, 0);
        gridPane.add(datePicker, 1, 0);
        gridPane.add(new Label("Uhrzeit: "), 0, 1);
        gridPane.add(getTimeComponent(), 1, 1);
        gridPane.add(new Label("minimale Dauer: "), 0, 2);
        gridPane.add(minimalDuration, 1, 2);
        gridPane.add(new Label("maximale Dauer: "), 0, 3);
        gridPane.add(maximalDuration, 1, 3);
        gridPane.add(durationError,1,4);
    }

    private HBox getTimeComponent() {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(hours, new Label(":"), minutes, dateTimeError);
        return hBox;
    }

    private void initTimeFieldValidation(TextField textField, int maxCharacters, int upperBound) {
        FormComponent.forceNumericInput(textField);
        limitTextfieldCharacter(textField, maxCharacters);
        limitTextfieldToUpperBound(textField, upperBound);
    }

    private void initTimeFields() {
        hours.setPrefColumnCount(2);
        minutes.setPrefColumnCount(2);
        initTimeFieldValidation(hours, 2,24);
        initTimeFieldValidation(minutes,2,60);
        minimalDuration.setMaxWidth(65);
        maximalDuration.setMaxWidth(65);
        initTimeFieldValidation(minimalDuration, 4,1001);
        initTimeFieldValidation(maximalDuration,4,1001);
    }

    private void initForm() {
        initDialog();
        setVBoxContent();
    }

    @Override
    Optional<TrainingAttributes> getConvertedInput() {
        TrainingAttributesInputConverter inputConverter = new TrainingAttributesInputConverter();
        if (!inputConverter.setDateTime(datePicker.getValue(),hours.getText().trim(), minutes.getText().trim())) {
            FormComponent.markInvalidFields(hours,null,"");
            FormComponent.markInvalidFields(minutes,null,"");
            FormComponent.markInvalidFields(datePicker,dateTimeError,"Datum liegt in der Vergangenheit!");
        }
        if (!inputConverter.setDuration(minimalDuration.getText(), maximalDuration.getText())) {
            FormComponent.markInvalidFields(minimalDuration,null,"");
            FormComponent.markInvalidFields(maximalDuration,durationError,"angegebener Zeitraum ist ungültig!");
        }
        addressComponent.getFormComponentValue().ifPresent(inputConverter::setAddress);
        return inputConverter.convertInputToEntity();
    }

    @Override
    public Optional<TrainingAttributes> showCreateAttributesForm() {
        this.addressComponent = new AddressComponent();
        initForm();
        return showForm();
    }

    @Override
    public Optional<TrainingAttributes> showEditAttributesForm(TrainingAttributes entityAttributes) {
        this.addressComponent = new AddressComponent(entityAttributes.getAddress());
        initForm();
        return showForm();
    }

    private static void limitTextfieldCharacter(final TextField textField, final int maxCharacters) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > maxCharacters) {
                textField.deleteNextChar();
            }
        });
    }

    private static void limitTextfieldToUpperBound(final TextField textField, final int upperBound) {
        textField.textProperty().addListener(((observable, oldValue, newValue) -> {
            if (!newValue.equals("") && Integer.valueOf(newValue) >= upperBound) {
                textField.setText(upperBound-1 +"");
            }
        }));
    }
}
