package tm.ui.event_handler.implementations;

import javafx.scene.control.ListCell;
import tm.data_model.entities.Entity;
import tm.ui.event_handler.ListItemClickedHandler;

import java.util.ArrayList;

public class SelectedEntitiesHandler<T extends Entity> implements ListItemClickedHandler<T> {

    private final boolean multipleSelections;
    private final ArrayList<T> selectedEntities = new ArrayList<>();

    public SelectedEntitiesHandler(boolean multipleSelections) {
        this.multipleSelections = multipleSelections;
    }

    public boolean hasSelectedElements() {
        return selectedEntities.size()>0;
    }

    @Override
    public void handleEvent(ListCell<T> entity) {
        selectedEntities.add(entity.getItem());
        entity.setStyle("-fx-background-color: #79bd57");
    }

    public ArrayList<T> getSelectedEntities() {
        if (multipleSelections) {
            return selectedEntities;
        }
        ArrayList<T> selectLatest = new ArrayList<>();
        if (selectedEntities.size() > 0) {
            selectLatest.add(selectedEntities.get(selectedEntities.size()-1));
            return selectLatest;
        }
        return null;
    }
}
