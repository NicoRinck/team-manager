package nr.ui.event_handler;

import javafx.scene.control.ListCell;
import nr.data_model.entities.Entity;

public class OpenDetailsHandler<T extends Entity> implements ListItemClickedHandler<T> {

    T entity;

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public void handleEvent(ListCell<T> listCell) {
        System.out.println(listCell.getItem());
    }
}
