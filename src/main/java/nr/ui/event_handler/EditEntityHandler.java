package nr.ui.event_handler;

import nr.data_model.entities.Entity;

public interface EditEntityHandler<T extends Entity> {

    void editEntity(T entity);
}
