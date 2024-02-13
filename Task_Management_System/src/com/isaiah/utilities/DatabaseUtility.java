/*
 * The purpose of this utility class is to handle connecting to the database
 */

package com.isaiah.utilities;

import java.sql.Connection;
import java.sql.DriverManager;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import java.sql.SQLException;


public class DatabaseUtility {
	
	final static private Path DATABASE_PROPERTIES_FILE_PATH = Paths.get("properties/db.properties");
	final static private String dbUrl = "jdbc:mysql://localhost:3306/";
	final static private String dbName = "task_management";

	
	public static Connection getTaskManagementConnection() {
		
		Connection conn = null;
		
		Properties props = getDBProperties();
		
		
		try {
			conn = DriverManager.getConnection(dbUrl+dbName, props.getProperty("username"), props.getProperty("password"));
			
		} catch(SQLException sqle) {
			System.err.println("An issue has occured with getting the connection to the DB: " + sqle.getMessage());
			System.err.println("Printing Stack Trace");
			sqle.printStackTrace();
			
		}
		
		
		return conn;
	}
	
	private static Properties getDBProperties() {
		
		Properties DBProperties = new Properties();
		
		try(InputStream input = new FileInputStream("properties/db.properties")) {
			
			DBProperties.load(input);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
			
		}
		
		
		return DBProperties;
	}
	
	
}
