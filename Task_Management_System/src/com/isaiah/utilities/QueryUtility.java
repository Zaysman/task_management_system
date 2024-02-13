/*
 * The purpose of this utility class is to handle 
 */

package com.isaiah.utilities;

import com.isaiah.objects.*;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;



public class QueryUtility {


	
	public static boolean createUser(User user) {
		boolean isSuccessful = false;

		try(Connection conn = DatabaseUtility.getTaskManagementConnection()) {
			
			PreparedStatement preparedStatement = conn.prepareStatement("insert into user_login (username, password) values (?, ?);");
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
						
			int rowsAffected = preparedStatement.executeUpdate();
			
			
			//check the number of rows affected.
			if(rowsAffected > 0) {
				System.out.println("Record inserted successfully");
				isSuccessful = true;
			} else {
				System.out.println("Failed to insert record.");
				
			}
			
			
		} catch(SQLException sqle) {
			System.err.println("An issue has occurred adding a user to the DB:" + sqle.getMessage());
			System.err.println("Printing Stack Trace\n" );
			sqle.printStackTrace();
		}
		
		
		return isSuccessful;
	}
	
	
	public static LinkedList<User> getAllUsers() {
		LinkedList<User> users = new LinkedList<>();

		try(Connection conn = DatabaseUtility.getTaskManagementConnection()) {

			Statement st =  conn.createStatement();
			ResultSet rs = st.executeQuery("select * from user_login;");
			int numCols = rs.getMetaData().getColumnCount();

			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				users.add(user);
			}


		} catch (SQLException sqle) {
			System.err.println("An issue has occured with retrieving all users from the DB:" + sqle.getMessage());
			System.err.println("Printing Stack Trace\n" );
			sqle.printStackTrace();
		}



		return users;
	}

	public static User getUserById(int id) {
		User user = new User();

		try(Connection conn = DatabaseUtility.getTaskManagementConnection()) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from user_login where user_id= " + id);

			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
			}


		} catch (SQLException sqle) {
			System.err.println("An issue has occured with retrieving user by id from the DB: " + sqle.getMessage());
			System.err.println("Printing Stack Trace" );
			sqle.printStackTrace();
		}



		return user;
	}
	
	public static User getUserByUserName(String username) {
		User user = new User();
		
		try(Connection conn = DatabaseUtility.getTaskManagementConnection()) {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from user_login where username= " + username);

			while(rs.next()) {
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
			}


		} catch (SQLException sqle) {
			System.err.println("An issue has occured with retrieving user by username from the DB: " + sqle.getMessage());
			System.err.println("Printing Stack Trace" );
			sqle.printStackTrace();
		}
		
		
		return user;
	}

	public static boolean updateUserById(int id, User update) {
		boolean isSuccessful = false;
		
		try(Connection conn = DatabaseUtility.getTaskManagementConnection()) {
			PreparedStatement pSt = conn.prepareStatement("UPDATE user_login SET username = ? where id = ?");
			
			pSt.setString(1, update.getUsername());
			pSt.setInt(2, id);
			
			int rowsAffected = pSt.executeUpdate();
			
			if(rowsAffected > 0) {
				isSuccessful = true;
			} else {
				isSuccessful = false;
			}
			
		
			
		}catch(SQLException sqle) {
			System.err.println("An issue has occurred updating a user in the DB: " + sqle.getMessage());
			System.err.println("Printing Stack Trace\n" );
			sqle.printStackTrace();
		}
		
		
		return isSuccessful;
	}
	
	public static boolean updateUser(User update) {
		
		return updateUserById(update.getId(), update);
		
	}
	
	public static boolean deleteUserById(int id) {
		boolean isSuccessful = false;
		
		try(Connection conn = DatabaseUtility.getTaskManagementConnection()) {
			
			PreparedStatement pst = conn.prepareStatement("Delete From user_login where user_id = ?");
			pst.setInt(1, id);
			
			int rowsAffected = pst.executeUpdate();
			
			if(rowsAffected > 0) {
				isSuccessful = true;
			} else {
				isSuccessful = false;
			}
			
			
		} catch(SQLException sqle) {
			System.err.println("An issue has occurred deleting a user from the DB: " + sqle.getMessage());
			System.err.println("Printing Stack Trace\n" );
			sqle.printStackTrace();
		}
		
		return isSuccessful;
	}
	
	public static boolean deleteUser(User delete) {
		
		return deleteUserById(delete.getId());
	}
	
	
	
	

}
