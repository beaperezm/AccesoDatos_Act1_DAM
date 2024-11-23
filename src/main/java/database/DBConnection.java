package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;


    private void createConnection() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%s/%s", SchemaDB.HOST, SchemaDB.PORT, SchemaDB.DBNAME);
        connection = DriverManager.getConnection(url, SchemaDB.USER, SchemaDB.PASS);
    }
    public Connection getConnection() throws SQLException {
        if(connection == null) {
            createConnection();
        }
        return connection;
    }
}
