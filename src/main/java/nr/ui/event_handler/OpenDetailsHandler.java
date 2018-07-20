package nr.ui.event_handler;

import javafx.scene.control.ListCell;
import nr.data_manager.DataManager;
import nr.data_model.entities.Entity;
import nr.ui.views.EntityDetailView;

public class OpenDetailsHandler<T extends Entity> implements ListItemClickedHandler<T> {

    private ListCell<T> listCell;
    private final EntityDetailView entityDetailView;
    private final DataManager<T> dataManager;

    public OpenDetailsHandler(EntityDetailView entityDetailView, DataManager<T> dataManager) {
        this.entityDetailView = entityDetailView;
        this.dataManager = dataManager;
    }

    @Override
    public void handleEvent(ListCell<T> listCell) {
        this.listCell = listCell;

        System.out.println(listCell.getItem());
    }
}
