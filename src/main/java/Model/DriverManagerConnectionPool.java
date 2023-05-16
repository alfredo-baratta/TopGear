package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool  {

	private static List<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	
	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		String ip = "localhost";
		String port = "3306";
		String db = "topgear";
		String username = "root";
		String password = "password";

		newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/topgear", "root", "password");

		newConnection.setAutoCommit(false);
		return newConnection;
	}


	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
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
		if(connection != null) freeDbConnections.add(connection);
	}
}

/*package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerConnectionPool {
	private static final String URL = "jdbc:mysql://localhost:3306/topgear";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "password";
	private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private static Connection connection;
	
	static {
	    try {
	        Class.forName(DRIVER_CLASS_NAME);
	        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
	    } catch (ClassNotFoundException | SQLException e) {
	        System.err.println("Error connecting to the database: " + e.getMessage());
	    }
	}

	public static Connection getConnection() {
	    return connection;
	}

	public DriverManagerConnectionPool() {}
}*/


