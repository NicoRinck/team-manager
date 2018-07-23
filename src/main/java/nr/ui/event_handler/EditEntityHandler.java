package nr.ui.event_handler;

import nr.data_model.entities.Entity;
import nr.ui.views.EntityDetailView;

public interface EditEntityHandler<T extends Entity> {

    void editEntity(T entity, EntityDetailView<T> detailView);
}
