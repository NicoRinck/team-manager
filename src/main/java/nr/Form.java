package nr;

import nr.data_model.entities.EntityAttributes;

public interface Form<T extends EntityAttributes> {
    T showForm();
}
