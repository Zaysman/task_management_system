package com.isaiah.main;

import com.isaiah.objects.*;
import com.isaiah.utilities.QueryUtility;

public class TestDBQueries {
	
	static QueryUtility qUtil;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Testing The QueryUtility Class");

		System.out.println("Testing User Related CRUD Operations");
		User testUser = new User("TestDBQueries", "password");
		
		boolean createUserSuccessful, readUserSuccessful, updateUserSuccessful, deleteUserSuccessful;
		
		createUserSuccessful = qUtil.createUser(testUser);
		
		String testUsername = testUser.getUsername();
		testUser = null;
		
		testUser = qUtil.getUserByUsername(testUsername);
		
		testUser = qUtil.getUser(testUser);
		
		if(testUser != null) {
			readUserSuccessful = true;
		} else {
			readUserSuccessful = false;
		}
		
		
		
		testUser.setUsername("UpdateUsername");
		
		updateUserSuccessful = qUtil.updateUser(testUser);
		
		deleteUserSuccessful = qUtil.deleteUser(testUser);
		
		System.out.println("User DBQueries Test Results:\n" 
		+ "\tcreation: " + createUserSuccessful + "\n"
		+ "\tread: " + readUserSuccessful + "\n"
		+ "\tupdate: " + updateUserSuccessful + "\n"
		+ "\tdelete: " + deleteUserSuccessful);
		
		
		printLineBreak(200);
		
		
		System.out.println("Testing Task Related CRUD Operations");
		
		boolean createTaskSuccessful, readTaskSuccessful, updateTaskSuccessful, deleteTaskSuccessful;
		
		Task testTask = new Task();
		createTaskSuccessful = qUtil.createTask(testTask);
		
		int testTaskId = testTask.getTask_id();
		testTask = null;
		
		testTask = qUtil.getTaskById(testTaskId);
		
		if(testTask != null) {
			readTaskSuccessful = true;
		} else {
			readTaskSuccessful = false;
		}
		
		testTask.setTask_description("This is an updated task description");
		updateTaskSuccessful = qUtil.updateTask(testTask);
		
		deleteTaskSuccessful = qUtil.deleteTask(testTask);
		
		System.out.println("Task DBQueries Test Results:\n" 
				+ "\tcreation: " + createTaskSuccessful + "\n"
				+ "\tread: " + readTaskSuccessful + "\n"
				+ "\tupdate: " + updateTaskSuccessful + "\n"
				+ "\tdelete: " + deleteTaskSuccessful);
		
		


	}

	public static void printLineBreak() {

		for(int i = 0; i < 200; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

	public static void printLineBreak(int dashes) {

		for(int i = 0; i < dashes; i++) {
			System.out.print("-");
		}
		System.out.println();
	}

}
