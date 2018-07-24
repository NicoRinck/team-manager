package nr.ui.forms;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import nr.data_model.entities.EntityAttributes;

import java.util.Optional;

public abstract class DialogForm<T extends EntityAttributes> implements Form<T> {

    Dialog<T> dialog;
    int countButtonCalls = 0;

    final ButtonType okButton = new ButtonType("Speichern", ButtonBar.ButtonData.APPLY);
    final ButtonType exitButton = new ButtonType("Abbrechen", ButtonBar.ButtonData.CANCEL_CLOSE);
    boolean active = true;
    final VBox vBox = new VBox();

    public DialogForm() {}

    private void initVBox() {
        vBox.setPrefWidth(510);
        vBox.setSpacing(20);
        dialog.getDialogPane().setContent(vBox);
    }

    void initDialog() {
        countButtonCalls = 0;
        dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UTILITY);
        setResultConverter();
        initVBox();
        initButtons();
    }

    @Override
    public abstract Optional<T> showCreateAttributesForm();

    @Override
    public abstract Optional<T> showEditAttributesForm(T entityAttributes);

    abstract Optional<T> getConvertedInput();

    private void initButtons() {
        dialog.getDialogPane().getButtonTypes().add(exitButton);
        dialog.getDialogPane().getButtonTypes().add(okButton);
    }

    void setResultConverter() {
        dialog.setResultConverter((ButtonType) -> {
            System.out.println(ButtonType);
            countButtonCalls++;
            if (ButtonType == okButton) {
                countButtonCalls = -1;
                final Optional<T> convertedInput = getConvertedInput();
                if (convertedInput.isPresent()) {
                    return convertedInput.get();
                }
            } else {
                if (validButtonCall(countButtonCalls)) {
                    this.active = false;
                }
            }
            return null;
        });
    }

    Optional<T> showForm() {
        this.active = true;
        Optional<T> optional = Optional.empty();
        while (!optional.isPresent() && active) {
            optional = dialog.showAndWait();
        }
        return optional;
    }

    static boolean validButtonCall(int buttonCounter) {
        return buttonCounter == 1;
    }
}
