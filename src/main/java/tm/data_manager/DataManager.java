package tm.data_manager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tm.data_access_layer.entity_database_strategy.EntityDatabaseStrategy;
import tm.data_model.entities.Entity;

public class DataManager<T extends Entity> {

    private final EntityDatabaseStrategy<T> entityDatabaseStrategy;
    private final ObservableList<T> observableList;

    public DataManager(EntityDatabaseStrategy<T> entityDatabaseStrategy) {
        this.entityDatabaseStrategy = entityDatabaseStrategy;
        observableList = FXCollections.observableArrayList(entityDatabaseStrategy.getEntities());
    }

    public ObservableList<T> getEntities() {
        return this.observableList;
    }

    public void saveEntity(T entity) {
        if (!observableList.contains(entity) && entity != null) {
            this.observableList.add(entity);
            entityDatabaseStrategy.saveEntity(entity);
        }
    }

    public void editEntity(T entity) {
        entityDatabaseStrategy.editEntity(entity);
    }

    public void deleteEntity(T entity) {
        observableList.remove(entity);
        entityDatabaseStrategy.deleteEntity(entity);
    }


}
