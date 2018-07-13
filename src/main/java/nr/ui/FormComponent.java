package nr.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public abstract class FormComponent<T> {

    protected final int amountOfErrorFields;
    protected Label[] errorLabels;

    public FormComponent(int amountOfErrorFields) {
        this.amountOfErrorFields = amountOfErrorFields;
        this.errorLabels = new Label[amountOfErrorFields];
        initErrorLabels();
    }

    private void initErrorLabels() {
        for (int i = 0; i < this.amountOfErrorFields; i++) {
            Label label = new Label();
            label.setStyle("-fx-text-fill: red");
            errorLabels[i] = label;
        }
    }

    /*protected void installEventHandler(Control... controls) {
        for (final Control control: controls) {
            control.setOnMouseClicked(e -> {
                control.setStyle("-fx-border-color: red");
            });
            control.setOnMouseExited(e -> {
                control.setStyle("-fx-border-color: gray");
            });
        }
    }*/

    static void markInvalidFields(Control field, Label errorLabel, String errorMessage) {
        field.setStyle("-fx-border-color: red");
        if (!errorMessage.equals("")) {
            errorLabel.setText(errorMessage);
        }
    }

    static void forceNumericInput(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    abstract Optional<T> getComponentValue();

    abstract Node getComponent();

}
