package nr.ui.event_handler.implementations;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import nr.data_manager.DataManager;
import nr.data_model.entities.Entity;

import java.util.Optional;

public class DeleteEntityHandler<T extends Entity> {

    private final DataManager<T> dataManager;

    public DeleteEntityHandler(DataManager<T> dataManager) {
        this.dataManager = dataManager;
    }

    public void deleteEntity(T entity) {
        if (deletionConfirmed()) {
            dataManager.deleteEntity(entity);
        }
    }

    private boolean deletionConfirmed() {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.initStyle(StageStyle.UTILITY);
        confirmationDialog.setHeaderText("Entität löschen!");
        confirmationDialog.setTitle("Bestätigung");
        confirmationDialog.setContentText("Möchten sie diese Entität wirklich löschen?");

        Optional<ButtonType> result = confirmationDialog.showAndWait();
        if (result.isPresent()) {
            return result.get() == ButtonType.OK;
        }
        return false;
    }
}
