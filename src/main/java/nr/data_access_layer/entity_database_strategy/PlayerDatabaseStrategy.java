package nr.data_access_layer.entity_database_strategy;

import nr.data_access_layer.DatabaseConnection;
import nr.data_model.entities.player.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PlayerDatabaseStrategy implements EntityDatabaseStrategy<Player> {

    private final DatabaseConnection databaseConnection;

    public PlayerDatabaseStrategy(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
        this.initTable(databaseConnection);
    }

    private void initTable(DatabaseConnection databaseConnection) {
        try {
            Statement statement = databaseConnection.getConnectionToDB().createStatement();
            statement.execute("create table if not exists player (id int primary key ,json CLOB)");


          /*  statement.execute("Insert into player values (1,'test1')");
            statement.execute("Insert into player values (2,'test2')");
            statement.execute("Insert into player values (3,'test3')");
            statement.execute("Insert into player values (4,'test4')");*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Player> getEntities() {
        Connection connection = databaseConnection.getConnectionToDB();
        ResultSet resultSet;

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM player");
            while(resultSet.next()) {
                System.out.println(resultSet.getString("string"));
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public void saveEntity(Player entity) {

    }

    @Override
    public void deleteEntity(Player entity) {

    }


}
