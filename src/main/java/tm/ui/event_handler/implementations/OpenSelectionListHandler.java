package tm.ui.event_handler.implementations;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.StageStyle;
import tm.data_model.entities.Entity;
import tm.ui.components.EntityList;
import tm.ui.components.ListItemStrategy;

import java.util.ArrayList;
import java.util.Optional;

public class OpenSelectionListHandler<T extends Entity> {

    private final static int DIALOG_WIDTH = 400;
    private final ObservableList<T> entities;
    private final SelectedEntitiesHandler<T> selectedEntitiesHandler;
    private final ListItemStrategy<T> listItemStrategy;

    public OpenSelectionListHandler(ObservableList<T> observableList, ListItemStrategy<T> listItemStrategy, boolean multipleSelections) {
        selectedEntitiesHandler = new SelectedEntitiesHandler<>(multipleSelections);
        this.listItemStrategy = listItemStrategy;
        this.entities = observableList;
    }

    private Dialog<Boolean> initDialog() {
        Dialog<Boolean> dialog = new Dialog<>();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.getDialogPane().setPrefWidth(DIALOG_WIDTH);
        ButtonType okButton = new ButtonType("Ok");
        dialog.getDialogPane().getButtonTypes().add(okButton);
        dialog.setResultConverter(ButtonType -> {
            if (ButtonType == okButton) {
                return true;
            }
            return false;
        });
        EntityList<T> entityList = new EntityList<>(entities,listItemStrategy, selectedEntitiesHandler);
        entityList.getListView().setMaxWidth(DIALOG_WIDTH);
        dialog.getDialogPane().setContent(entityList.getComponent());
        return dialog;
    }

    public ArrayList<T> getEntitiesFromSelectionList() {
        final ArrayList<T> selectedEntities = new ArrayList<>();
        Dialog<Boolean> dialog = initDialog();
        Optional<Boolean> accepted = dialog.showAndWait();
        accepted.ifPresent(aBoolean -> {
            if (aBoolean) {
                if (selectedEntitiesHandler.hasSelectedElements()) {
                    selectedEntities.addAll(selectedEntitiesHandler.getSelectedEntities());
                }
            }
        });
        return selectedEntities;
    }
}
