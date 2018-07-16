package nr.ui.form_components;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import nr.data_model.form_fields.BirthDate;
import nr.data_model.validator.BirthDateValidator;
import nr.ui.form_components.FormComponent;

import java.time.LocalDate;
import java.util.Optional;

public class BirthDateComponent extends FormComponent<BirthDate> {


    private BirthDate birthDate;
    private final HBox hBox = new HBox();
    private final DatePicker datePicker = new DatePicker();

    public BirthDateComponent(BirthDate birthDate) {
        super(1);
        this.birthDate = birthDate;
        this.datePicker.setValue(birthDate.getBirthDate());
        this.initComponent();
    }

    public BirthDateComponent() {
        super(1);
        this.datePicker.setValue(LocalDate.of(2000,1,1));
        this.initComponent();
    }

    private void initComponent() {
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().addAll(new Label("Geburtsdatum: "),datePicker,errorLabels[0]);
    }

    @Override
    public Optional<BirthDate> getComponentValue() {
        LocalDate date = datePicker.getValue();
        if (BirthDateValidator.isValidBirthDate(date)){
            return Optional.of(new BirthDate(date));
        }
        markInvalidFields(datePicker,errorLabels[0], BirthDateValidator.getErrorMessage(date) );
        return Optional.empty();
    }

    @Override
    public Node getComponent() {
        return hBox;
    }
}
