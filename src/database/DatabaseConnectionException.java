package database;

public class DatabaseConnectionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseConnectionException() {
		System.err.println("Connesione fallita!");
	}
}
