package com.publica.grupo2sprint3.model.dao;

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

public class DatabaseCollaborator {
	Connection con;
	MysqlDataSource dataSource = new MysqlDataSource();
	private Statement stmt;
	private ResultSet rs;
	static private DatabaseCollaborator instance;
		
	private DatabaseCollaborator(String host, String user, String password, int port, String dbName ) throws SQLException {
		dataSource.setDatabaseName(dbName);
		dataSource.setServerName(host);
		dataSource.setUser(user);
		dataSource.setPassword(password);
		dataSource.setPort(port);
		dataSource.setServerTimezone("UTC");
	}
	
	private Connection getConnection() throws SQLException {
		return this.dataSource.getConnection();
	}
	
	public static DatabaseCollaborator getInstance() {
		if(instance == null) {
			try {
				instance = new DatabaseCollaborator("localhost", "root", null, 3306, "db_sistema_rh");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	/**
	 * This method select all data from table collaborator on database.
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Map<String,String>> select(String query) throws SQLException{
		ArrayList<Map<String,String>> data = new ArrayList<Map<String,String>>(); 
		con = this.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
	    List<String> columns = new ArrayList<String>(rsmd.getColumnCount());
	    for(int i = 1; i <= rsmd.getColumnCount(); i++){
	        columns.add(rsmd.getColumnName(i));
	    }		    
	    while(rs.next()){                
	        Map<String,String> row = new HashMap<String, String>(columns.size());
	        for(String col : columns) {
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
	 * This method updates the table collaborator on database : insert, delete and update a data. 
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int update(String query) throws SQLException{
		int updatedRows = 0;
		con = this.getConnection();
		stmt = con.createStatement();
		updatedRows = stmt.executeUpdate(query);
	    stmt.close();
	    con.close();
	    return updatedRows;
	}
}
