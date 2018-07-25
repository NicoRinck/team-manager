package tm.data_converter;

import com.google.gson.Gson;
import tm.data_model.entities.Entity;

public class JsonConverter<T extends Entity> {

    private static final Gson GSON = new Gson();

    public T getEntityFromJson(String json, Class<T> classToken) {
        return GSON.fromJson(json,classToken);
    }

    public String getJsonFromEntity(T entity) {
        return GSON.toJson(entity);
    }
}
