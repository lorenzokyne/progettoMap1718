package database;

public class NoValueException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoValueException() {
		System.err.println("Assenza del valore nel resultSet");
	}
}
