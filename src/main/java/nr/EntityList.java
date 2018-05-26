package nr;

import java.util.List;

public class EntityList<T extends Entity> {

    private List<T> entityList;

    public EntityList(List<T> entityList) {
        this.entityList = entityList;

    }


}
