package nr.data_manager;

import nr.data_access_layer.entity_database_strategy.EntityDatabaseStrategy;
import nr.data_model.entities.Entity;

import java.util.List;

public class DataManager<T extends Entity> {

    EntityDatabaseStrategy<T> entityDatabaseStrategy;

    public List<T> getEntities() {
        return null;
    }

    public void saveEntity(T entity) {

    }

    public void editEntity(T entity) {

    }

    public void deleteEntity(T entity) {

    }


}
