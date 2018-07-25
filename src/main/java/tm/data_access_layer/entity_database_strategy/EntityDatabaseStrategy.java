package tm.data_access_layer.entity_database_strategy;

import tm.data_model.entities.Entity;

import java.util.List;

public interface EntityDatabaseStrategy<T extends Entity> {

    List<T> getEntities();
    void saveEntity(T entity);
    void editEntity(T entity);
    void deleteEntity(T entity);
}
