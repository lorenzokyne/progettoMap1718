package database;

import java.sql.ResultSet;

public class EmptySetException extends Exception {
	public EmptySetException() {
		System.err.println("Restituisco emptySet");
	}
}