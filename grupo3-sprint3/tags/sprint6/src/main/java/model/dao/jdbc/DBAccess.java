package model.dao.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Abstract Class for Database Access Using JDBC.<br>
 * 
 * It has a static instance to avoid creating multiple connections to the
 * database.<br>
 * 
 * There are default methods for updating and selecting data from the database
 * given a query, as well as abstract methods to be implemented by specific
 * database access classes that inherit this class.
 * 
 * @version 1.5.0
 * 
 * @author Ricardo Gonçalves
 * @author Caio Shimada
 *
 */
public abstract class DBAccess {

	private Connection con;
	private MysqlDataSource dataSource = new MysqlDataSource();
	private Statement stmt;
	private ResultSet rs;
	static protected DBAccess instance;

	/**
	 * The constructor that will set the database info in the Datasource used to
	 * connect to the database.
	 * 
	 * @author Ricardo Gonçalves
	 * 
	 * @param host     a string of the IP of the host of the database
	 * @param user     a string of the username of the database
	 * @param password a string of the password of the database
	 * @param port     an int of the port used to connect to the database
	 * @param dbName   a string of the name of the database name
	 * @throws SQLException
	 */
	protected DBAccess(String host, String user, String password, int port, String dbName) throws SQLException {
		dataSource.setDatabaseName(dbName);
		dataSource.setServerName(host);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setPort(port);
		dataSource.setServerTimezone("UTC");
	}

	/**
	 * Returns the connection to be used in the update and select methods
	 * 
	 * @author Ricardo Gonçalves
	 * 
	 * @return the DataSource object with the connection
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	/**
	 * Changes database to tests database
	 * 
	 * @author Caio Shimada
	 */
	public void setTestDatabase() {
		this.dataSource.setDatabaseName(this.dataSource.getDatabaseName()+"_tests");
	}

	public abstract Object find(String field, String value) throws SQLException;

	public abstract <T> ArrayList<T> getAll() throws SQLException;

	public abstract void insert(Object object) throws SQLException;

	public abstract void update(Object object, int id) throws SQLException;

	public abstract void delete(String field, String value) throws SQLException;

	/**
	 * Given a query, it selects the data and returns it in a ArrayList of Hashmaps
	 * 
	 * @author Ricardo Gonçalves
	 * 
	 * @param query a string of the query to be executed
	 * @return an ArrayList of Maps of the data found in the format Map<String,
	 *         String>
	 * @throws SQLException
	 */
	public ArrayList<Map<String, String>> select(String query) throws SQLException {
		ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
		con = this.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		List<String> columns = new ArrayList<String>(rsmd.getColumnCount());
		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
			columns.add(rsmd.getColumnLabel(i));
		}
		while (rs.next()) {
			Map<String, String> row = new HashMap<String, String>(columns.size());
			for (String col : columns) {
				row.put(col, rs.getString(col));
			}
			data.add(row);
		}
		rs.close();
		stmt.close();
		con.close();
		return data;
	}

	/**
	 * Used to execute Update, Insert and Delete queries given a query.
	 * 
	 * @author Ricardo Gonçalves
	 * 
	 * @param query a string of the query to be executed
	 * @return an int representing the number of rows affected
	 * @throws SQLException
	 */
	public int update(String query) throws SQLException {
		int updatedRows = 0;
		con = this.getConnection();
		stmt = con.createStatement();
		updatedRows = stmt.executeUpdate(query);
		stmt.close();
		con.close();
		return updatedRows;
	}
}