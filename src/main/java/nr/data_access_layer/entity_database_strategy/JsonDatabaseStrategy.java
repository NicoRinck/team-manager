package nr.data_access_layer.entity_database_strategy;

import nr.data_access_layer.DatabaseConnection;
import nr.data_converter.JsonConverter;
import nr.data_model.entities.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class JsonDatabaseStrategy<T extends Entity> implements EntityDatabaseStrategy<T> {

    private final DatabaseConnection databaseConnection;
    private final Class<T> classToken;
    private final String tableName;
    private final HashMap<Integer, T> entityHashMap = new HashMap<>();
    private final JsonConverter<T> jsonConverter = new JsonConverter<>();

    public JsonDatabaseStrategy(DatabaseConnection databaseConnection, Class<T> classToken) {
        this.classToken = classToken;
        this.tableName = classToken.getSimpleName().toLowerCase();
        this.databaseConnection = databaseConnection;
        this.initTable(databaseConnection);
        this.loadPlayers();
    }

    private void initTable(DatabaseConnection databaseConnection) {
        try {
            String createTable = "create table if not exists " + tableName + " (id int primary key ,json varchar);";
            PreparedStatement statement = databaseConnection.getConnectionToDB().prepareStatement(createTable);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getEntities() {
        return new ArrayList<>(entityHashMap.values());
    }

    @Override
    public void saveEntity(T entity) {
        if (entity != null && !entityHashMap.containsValue(entity)) {
            Connection connection = databaseConnection.getConnectionToDB();
            Integer currentIndex = getCurrentIndex();
            String json = jsonConverter.getJsonFromEntity(entity);
            entityHashMap.put(currentIndex, entity);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "insert into " + tableName + " values (?,?)");
                preparedStatement.setInt(1, currentIndex);
                preparedStatement.setString(2, json);
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Integer getCurrentIndex() {
        if (entityHashMap.isEmpty()) {
            return 0;
        }
        return getNextUniqueIndex(entityHashMap);
    }

    private Integer getNextUniqueIndex(HashMap<Integer, T> hashMap) {
        return Collections.max(hashMap.keySet()) + 1;
    }

    @Override
    public void deleteEntity(T entity) {
        Integer indexOfEntity = getIndexOfEntity(entity);
        boolean deletionSuccessful = false;
        if (entity != null && indexOfEntity != -1) {
            deletionSuccessful = deleteFromDatabase(indexOfEntity);
        }
        if (deletionSuccessful) {
            entityHashMap.remove(indexOfEntity,entity);
        }
    }

    private boolean deleteFromDatabase(Integer id) {
        Connection connection = databaseConnection.getConnectionToDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE from " + tableName + " WHERE id = ?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //returns -1 if entity not in HashMap
    private Integer getIndexOfEntity(T entity) {
        if (entityHashMap.containsValue(entity)) {
            for (Map.Entry<Integer, T> entry : entityHashMap.entrySet()) {
                if (entry.getValue() == entity) {
                    return entry.getKey();
                }
            }
        }
        return -1;
    }

    private void loadPlayers() {
        Connection connection = databaseConnection.getConnectionToDB();
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + tableName + ";");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String string = resultSet.getString("json");
                T entity = jsonConverter.getEntityFromJson(string, classToken);
                entityHashMap.put(resultSet.getInt("id"), entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void editEntity(T entity) {
        Integer indexOfEntity = getIndexOfEntity(entity);
        if (indexOfEntity != -1 && entity != null) {
            String json = jsonConverter.getJsonFromEntity(entity);
            editEntityInDatabase(indexOfEntity,json);
        }
    }

    private void editEntityInDatabase(Integer id, String newValue) {
        Connection connection = databaseConnection.getConnectionToDB();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE " + tableName + " SET json = ? WHERE id = ?"
            );
            preparedStatement.setString(1,newValue);
            preparedStatement.setInt(2,id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
