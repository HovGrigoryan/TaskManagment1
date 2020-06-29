package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionProvider {

    private static DBConnectionProvider instance = new DBConnectionProvider();
    private Connection connection;

    private String dbDriver;
    private String dbUrl;
    private String dbUsername;
    private String dbPassword;

    private DBConnectionProvider() {
        try {
            loadProperties();
            Class.forName(dbDriver);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("C:\\Users\\Hov\\Desktop\\GIT JAVA\\TaskManagment\\src\\main\\resources\\DBConfig.properties"));
        dbUrl = properties.getProperty("db.url");
        dbDriver = properties.getProperty("db.driver");
        dbUsername = properties.getProperty("db.username");
        dbPassword = properties.getProperty("db.password");
    }

    public static DBConnectionProvider getInstance() {
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
