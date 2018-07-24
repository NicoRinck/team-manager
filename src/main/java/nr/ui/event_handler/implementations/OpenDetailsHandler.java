package nr.ui.event_handler.implementations;

import javafx.scene.control.ListCell;
import nr.data_model.entities.Entity;
import nr.ui.event_handler.ListItemClickedHandler;
import nr.ui.views.EntityDetailView;

public class OpenDetailsHandler<T extends Entity> implements ListItemClickedHandler<T> {

    private ListCell<T> listCell;
    private final EntityDetailView<T> entityDetailView;

    public OpenDetailsHandler(EntityDetailView<T> entityDetailView) {
        this.entityDetailView = entityDetailView;
    }

    @Override
    public void handleEvent(ListCell<T> listCell) {
        this.listCell = listCell;
        entityDetailView.showDetailView(listCell.getItem());
        listCell.getListView();
    }
}
