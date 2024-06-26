package com.isaiah.main;

import com.isaiah.utilities.*;
import com.isaiah.objects.*;
import java.sql.*;

import java.util.LinkedList;
import java.util.List;


public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		//Connection conn = DatabaseUtility.getTaskManagementConnection();
		try(Connection conn = DatabaseUtility.getTaskManagementConnection()) {
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery("Select * from user_login");
			int numColumns = rs.getMetaData().getColumnCount();
			
			
			System.out.printf("ID \tusername \tpassword\n");
			while(rs.next()) {
				for(int i = 1; i <= numColumns; i++) {
					System.out.print(rs.getObject(i) + "\t");
				}
				System.out.println();
			}
			
			
		}catch (SQLException sqle) {
			System.err.println("An issue occurred getting the database connection");
			System.exit(-1);
		}
		
		User testUser = QueryUtility.getUserById(1);
		
		System.out.println("testUser toString:\n" +
				testUser.toString());
		
		LinkedList<User> userList = QueryUtility.getAllUsers();
		for(User u: userList) {
			System.out.println(u.toString());
		}
		
		

	}

}
