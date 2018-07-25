package tm.data_access_layer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DatabaseConnection implements DatabaseConnection {

    private Connection conn;

    public H2DatabaseConnection() {
        try {
            buildConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (conn != null)
                try {
                    conn.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
        }
    }

    private void buildConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");

        conn = DriverManager.getConnection("jdbc:h2:~/.team-manager/teamManagerDB", "", "");
    }

    @Override
    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnectionToDB() {
        return conn;
    }

}
