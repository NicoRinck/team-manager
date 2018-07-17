package nr.ui.event_handler;

import nr.data_model.entities.Entity;
import nr.ui.components.EntityList;

public interface AddEntityToListHandler<T extends Entity> {

    void addEntityToList(EntityList<T> entityList);
}
