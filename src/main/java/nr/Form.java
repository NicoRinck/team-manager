package nr;

import nr.data_model.entities.EntityAttributes;

import java.util.Optional;

public interface Form<T extends EntityAttributes> {

    Optional<T> showCreateAttributesForm();
    Optional<T> showEditAttributesForm(T entityAttributes);
}
