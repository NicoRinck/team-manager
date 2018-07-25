package tm.ui.components.form_components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import tm.data_model.form_fields.AppointmentDuration;
import tm.ui.forms.DialogForm;

import java.util.Optional;

public class DurationComponent extends GridFormComponent<AppointmentDuration> {

    private final TextField minimalDuration = new TextField();
    private final TextField maximalDuration = new TextField();

    public DurationComponent() {
        super(2);
        fillGrid();
    }

    public DurationComponent(AppointmentDuration appointmentDuration) {
        super(2);
        this.fillTextFields(appointmentDuration);
        this.fillGrid();
    }


    private void initValidation() {
        minimalDuration.setMaxWidth(65);
        maximalDuration.setMaxWidth(65);
        DialogForm.initTimeFieldValidation(minimalDuration, 4,1001);
        DialogForm.initTimeFieldValidation(maximalDuration,4,1001);
    }

    protected void initGridConstraints() {
        ColumnConstraints column1 = new ColumnConstraints(115);
        column1.setHgrow(Priority.SOMETIMES);
        ColumnConstraints column2 = new ColumnConstraints(72);
        column2.setHgrow(Priority.NEVER);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(80);
        column3.setHgrow(Priority.ALWAYS);
        this.gridPane.getColumnConstraints().addAll(column1,column2,column3);
    }

    private void fillGrid() {
        initGridConstraints();
        initValidation();
        gridPane.add(new Label("minimale Dauer: "), 0, 0);
        gridPane.add(minimalDuration, 1, 0);
        gridPane.add(new Label("maximale Dauer: "), 0, 1);
        gridPane.add(maximalDuration, 1, 1);
    }

    private void fillTextFields(AppointmentDuration appointmentDuration) {
        if (appointmentDuration.getExactTime() == -1) {
            this.minimalDuration.setText(appointmentDuration.getMinimalTime().toString());
            this.maximalDuration.setText(appointmentDuration.getMaximalTime().toString());
        } else {
            this.minimalDuration.setText(appointmentDuration.getMinimalTime().toString());
            this.maximalDuration.setText(appointmentDuration.getMinimalTime().toString());
        }
    }

    @Override
    public Optional<AppointmentDuration> getFormComponentValue() {
        String minimal = minimalDuration.getText();
        String maximal = maximalDuration.getText();

        if (!maximal.equals("") && !minimal.equals("")) {
            int minimalTime = Integer.valueOf(minimal);
            int maximalTime = Integer.valueOf(maximal);
            if (minimalTime == maximalTime) {
                return Optional.of(new AppointmentDuration(minimalTime));
            }
            if (minimalTime < maximalTime) {
                return Optional.of(new AppointmentDuration(minimalTime, maximalTime));
            }
        }
        markInvalidFields(maximalDuration,errorLabels[1], "Zeitraum ist ungültig!");
        markInvalidFields(minimalDuration,null, "");
        return Optional.empty();
    }
}
