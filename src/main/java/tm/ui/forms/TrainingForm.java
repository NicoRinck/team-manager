package tm.ui.forms;

import javafx.geometry.Pos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import tm.data_converter.user_input_converter.TrainingAttributesInputConverter;
import tm.data_model.entities.appointment.TrainingAttributes;
import tm.ui.components.form_components.AddressComponent;
import tm.ui.components.form_components.DurationComponent;
import tm.ui.components.form_components.FormComponent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class TrainingForm extends DialogForm<TrainingAttributes> {

    private final GridPane gridPane = new GridPane();
    private final DatePicker datePicker = new DatePicker();
    private final TextField hours = new TextField();
    private final TextField minutes = new TextField();
    private DurationComponent durationComponent;
    private AddressComponent addressComponent;
    private final Label dateTimeError = new Label();

    public TrainingForm() {
        initGrid();
        dateTimeError.setStyle("-fx-text-fill: red");
    }

    private void setVBoxContent() {
        vBox.getChildren().clear();
        vBox.getChildren().addAll(gridPane, durationComponent.getComponent(), addressComponent.getComponent());
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
    }

    private HBox getTimeComponent() {
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hours.setText(Integer.toString(LocalTime.now().getHour()));
        minutes.setText(Integer.toString(LocalTime.now().getMinute()));
        hBox.getChildren().addAll(hours, new Label(":"), minutes, dateTimeError);
        return hBox;
    }

    private void initTimeFields() {
        hours.setPrefColumnCount(2);
        minutes.setPrefColumnCount(2);
        initTimeFieldValidation(hours, 2,24);
        initTimeFieldValidation(minutes,2,60);
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
        durationComponent.getFormComponentValue().ifPresent(inputConverter::setDuration);
        addressComponent.getFormComponentValue().ifPresent(inputConverter::setAddress);
        return inputConverter.convertInputToEntity();
    }

    @Override
    public Optional<TrainingAttributes> showCreateAttributesForm() {
        this.addressComponent = new AddressComponent();
        this.durationComponent = new DurationComponent();
        initForm();
        this.dialog.setTitle("Trainingseinheit erstellen");
        return showForm();
    }

    @Override
    public Optional<TrainingAttributes> showEditAttributesForm(TrainingAttributes entityAttributes) {
        this.addressComponent = new AddressComponent(entityAttributes.getAddress());
        this.durationComponent = new DurationComponent(entityAttributes.getDuration());
        initForm();
        dialog.setTitle("Trainingseinheit bearbeiten");
        return showForm();
    }
}
