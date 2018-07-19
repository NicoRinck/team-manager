package nr.ui.event_handler;

import nr.data_model.entities.Entity;

public interface AddEntityToListHandler<T extends Entity> {

    void addEntityToList();
}
