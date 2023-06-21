package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

    private static List<Connection> freeDbConnections;
    private static final int MAX_CONNECTIONS = 10; // Numero massimo di connessioni consentite

    static {
        freeDbConnections = new LinkedList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found: " + e.getMessage());
        }
    }

    private static synchronized Connection createDBConnection() throws SQLException {
        Connection newConnection = null;
        try {
            newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/topgear", "root", "");
            newConnection.setAutoCommit(true);
        } catch (SQLException e) {
        	System.out.println("DB error: " + e.getMessage());
        } finally {
            if (newConnection != null) {
                newConnection.close();
            }
        }
        return newConnection;
    }

    public static synchronized Connection getConnection() throws SQLException {
        Connection connection;

        if (!freeDbConnections.isEmpty()) {
            connection = freeDbConnections.remove(0);

            try {
                if (connection.isClosed()) {
                    connection = getConnection();
                }
            } catch (SQLException e) {
                connection.close();
                connection = getConnection();
            }
        } else {
            connection = createDBConnection();
        }

        return connection;
    }

    public static synchronized void releaseConnection(Connection connection) throws SQLException {
        if (connection != null) {
            if (freeDbConnections.size() < MAX_CONNECTIONS) {
                freeDbConnections.add(connection);
            } else {
                connection.close();
            }
        }
    }
}
