package database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import data.DiscreteAttribute;
import database.TableSchema.Column;

public class TableData {

	static DbAccess db;

	public TableData(DbAccess db) {
		this.db = db;
	}

	public List<Example> getDistinctTransazioni(String table) throws SQLException, EmptySetException {
		List<Example> temp = new ArrayList<Example>();
		Connection conn = db.getConnection();
		DatabaseMetaData meta = conn.getMetaData();
		try {
			Statement s = conn.createStatement();
			String query = "SELECT * " + "FROM " + table;
			ResultSet res = s.executeQuery(query);
			boolean pieno = res.next();
			if (!pieno) {
				throw new EmptySetException();
			}
			while (pieno) {
				TableSchema tb = new TableSchema(db, table);
				Example app = new Example();
				try {
					for (int i = 0; i < tb.getNumberOfAttributes(); i++) {
						Object obj;
						if (tb.getColumn(i).isNumber()) {
							obj = (res.getDouble(tb.getColumn(i).getColumnName()));
						} else {
							obj = (res.getString(tb.getColumn(i).getColumnName()));
						}
						// System.out.println(obj.getClass().getName());
						app.add(obj);
					}
					temp.add(app);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				pieno = res.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public Set<Object> getDistinctColumnValues(String table, Column column) throws SQLException {
		Set<Object> temp = new TreeSet<Object>();
		Connection conn = db.getConnection();
		Statement s = conn.createStatement();
		String query = "SELECT " + column.getColumnName() + " FROM " + table + " ORDER BY " + column.getColumnName();
		ResultSet res = s.executeQuery(query);
		while (res.next()) {
			if (column.isNumber()) {
				temp.add(res.getDouble(column.getColumnName()));
			} else {
				temp.add(res.getString(column.getColumnName()));
			}
		}
		return temp;
	}

	public Object getAggregateColumnValue(String table, Column column, QUERY_TYPE aggregate)
			throws SQLException, NoValueException {
		Double temp = null;
		try {
			db.initConnection();
			Connection conn = db.getConnection();
			conn.isValid(10);
			Statement s = conn.createStatement();
			String query = "SELECT " + aggregate.toString() + "(" + column.getColumnName() + ")as "
					+ column.getColumnName() + " FROM " + table;
			ResultSet res = s.executeQuery(query);
			if (res.next()) {
				temp = res.getDouble(column.getColumnName());
			} else {
				throw new NoValueException();
			}
		} catch (DatabaseConnectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return temp;
		// to do
	}
}
