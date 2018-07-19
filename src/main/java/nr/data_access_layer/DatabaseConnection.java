package nr.data_access_layer;

import java.sql.Connection;

public interface DatabaseConnection {

    Connection getConnectionToDB();
    void closeConnection();
}
