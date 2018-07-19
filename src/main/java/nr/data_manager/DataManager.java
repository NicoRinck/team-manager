package nr.data_manager;

import nr.data_access_layer.entity_database_strategy.EntityDatabaseStrategy;
import nr.data_model.entities.Entity;

import java.util.List;

public class DataManager<T extends Entity> {

    private final EntityDatabaseStrategy<T> entityDatabaseStrategy;
    private final List<T> entityList;

    public DataManager(EntityDatabaseStrategy<T> entityDatabaseStrategy) {
        this.entityDatabaseStrategy = entityDatabaseStrategy;
        entityList = getEntities();
    }

    public List<T> getEntities() {
        return entityDatabaseStrategy.getEntities();
    }

    public void saveEntity(T entity) {
        if (entityList.contains(entity) && entity != null) {
            this.entityList.add(entity);
            entityDatabaseStrategy.saveEntity(entity);
        }
    }

    public void editEntity(T oldEntity) {

    }

    public void deleteEntity(T entity) {

    }


}
