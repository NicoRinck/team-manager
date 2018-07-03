package nr.data_model;

import nr.data_model.Entity;

import java.util.List;

public class EntityList<T extends Entity> {

    private List<T> entityList;

    public EntityList(List<T> entityList) {
        this.entityList = entityList;

    }

    public void showEntities() {
        for (T t : entityList) {
            System.out.println(t.toString());
        }
    }


}
