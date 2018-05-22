package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbAccess {
	private String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
	private final String DBMS = "jdbc:mysql";
	private final String SERVER = "localhost";
	private final String DATABASE = "mapdb";
	private final String PORT = "3306";
	private final String USER_ID = "mapuser";
	private final String PASSWORD = "map";
	private Connection conn;

	public void initConnection() throws DatabaseConnectionException {
		try {
			Class.forName(DRIVER_CLASS_NAME).newInstance();
			String temp = DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE;
			temp+="?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			try {
				conn = DriverManager.getConnection(temp, USER_ID, PASSWORD);
				//closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DatabaseConnectionException();
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			initConnection();
		} catch (DatabaseConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
