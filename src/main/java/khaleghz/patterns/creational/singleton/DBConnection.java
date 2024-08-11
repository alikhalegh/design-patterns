package khaleghz.patterns.creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnection {

    private static DBConnection instance = null;

    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test_db";
    private static final String DB_USERNAME = "";
    private static final String DB_PASSWORD = "";
    private final Connection connection;

    private DBConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USERNAME, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Failed to create in memory database connection", e);
        }
    }

    public static DBConnection getInstance() {

        // lazy load and thread-safe
        if (Objects.isNull(instance)) {
            synchronized (DBConnection.class) {
                if (Objects.isNull(instance)) {
                    instance = new DBConnection();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
