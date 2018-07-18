package nr.ui.event_handler;

import javafx.scene.control.ListCell;
import nr.data_model.entities.Entity;
import nr.ui.views.EntityDetailView;

public class OpenDetailsHandler<T extends Entity> implements ListItemClickedHandler<T> {

    private ListCell<T> listCell;
    private final EntityDetailView entityDetailView;

    public OpenDetailsHandler(EntityDetailView entityDetailView) {
        this.entityDetailView = entityDetailView;
    }

    @Override
    public void handleEvent(ListCell<T> listCell) {
        this.listCell = listCell;
        System.out.println(listCell.getItem());
    }
}
