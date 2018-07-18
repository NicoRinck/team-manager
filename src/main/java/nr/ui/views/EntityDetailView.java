package nr.ui.views;

import nr.data_model.entities.Entity;

public interface EntityDetailView<T extends Entity> {

    public void showDetailView(T entity);
}
