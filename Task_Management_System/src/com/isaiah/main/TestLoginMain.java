package com.isaiah.main;

import java.util.Scanner;
import com.isaiah.objects.User;
import com.isaiah.utilities.*;


public class TestLoginMain {

	static Scanner input = new Scanner(System.in);
	//static User user = new User();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String usernameInput, passwordInput;
		
		//prompt user for username
		System.out.print("Enter your username: ");
		usernameInput = input.next();
		//System.out.println();
		
		System.out.print("Enter your password: ");
		passwordInput = input.next();
		System.out.println();
		
		System.out.println("Username Input: " + usernameInput + "\n" +
						   "Password Input: " + passwordInput + "\n");
		
		User user = QueryUtility.getUserByUsername(usernameInput);
		
		System.out.println("Data From DB:");
		System.out.println("Id: "+ user.getId() + "\n" +
						   "Username: " + user.getUsername() + "\n" +
						   "Password: " + user.getPassword());
		
		System.out.println("Passwords Matched: " + passwordInput.equals(user.getPassword()));
		

	}

}
