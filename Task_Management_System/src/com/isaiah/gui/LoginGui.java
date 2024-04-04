package com.isaiah.gui;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.isaiah.objects.User;
import com.isaiah.utilities.*;

import com.isaiah.gui.TaskscreenGui;


public class LoginGui {
	private static JTextField usernameTextField;
	private static JTextField passwordTextfield;
	private static TaskscreenGui taskScreenGui;
	private static NewUserGui newUserGui;
	
	
	public static void start() {
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}
	
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Task Management Login");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(352, 380);
		
		//add swing components and layout here
		
		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(84, 65, 78, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel LoginScreenLabel = new JLabel("Login Screen");
		LoginScreenLabel.setBounds(117, 11, 114, 25);
		LoginScreenLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(LoginScreenLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(84, 123, 78, 25);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(172, 67, 91, 25);
		frame.getContentPane().add(usernameTextField);
		usernameTextField.setColumns(10);
		
		passwordTextfield = new JTextField();
		passwordTextfield.setColumns(10);
		passwordTextfield.setBounds(172, 127, 91, 25);
		frame.getContentPane().add(passwordTextfield);
		
		JButton loginBtn = new JButton("Login");
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		loginBtn.setBackground(new Color(0, 0, 255));
		loginBtn.setBounds(131, 190, 89, 23);
		frame.getContentPane().add(loginBtn);
		
		JLabel ErrorMsgLabel_1 = new JLabel("");
		ErrorMsgLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorMsgLabel_1.setForeground(Color.RED);
		ErrorMsgLabel_1.setBounds(42, 269, 252, 14);
		frame.getContentPane().add(ErrorMsgLabel_1);
		
		JLabel ErrorMsgLabel_2 = new JLabel("");
		ErrorMsgLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		ErrorMsgLabel_2.setForeground(Color.RED);
		ErrorMsgLabel_2.setBounds(42, 284, 252, 14);
		frame.getContentPane().add(ErrorMsgLabel_2);
		
		JButton newUserBtn = new JButton("New User?");
		newUserBtn.setForeground(Color.WHITE);
		newUserBtn.setBackground(Color.GREEN);
		newUserBtn.setBounds(117, 235, 114, 23);
		frame.getContentPane().add(newUserBtn);
		frame.setVisible(true);
		
		
		//Add action listener to the button
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Read text from text fields
				String usernameInput = usernameTextField.getText();
				String passwordInput = passwordTextfield.getText();
				
				//empty password field
				passwordTextfield.setText("");
				
				System.out.println("usernameInput: " + usernameInput + "\n"
						+ "passwordInput: " + passwordInput);
				
				User user = QueryUtility.getUserByUsername(usernameInput);
				
				System.out.println("Data From DB:");
				System.out.println("Id: "+ user.getId() + "\n" +
								   "Username: " + user.getUsername() + "\n" +
								   "Password: " + user.getPassword());
				
				System.out.println("Passwords Matched: " + passwordInput.equals(user.getPassword()));
				
				
				//If no user was found from the db, then print the error message.
				if(user.getId() == -1) {
					ErrorMsgLabel_1.setText("The username or password you entered is ");
					ErrorMsgLabel_2.setText("incorrect. Please try again");
				}
				
				if(user.getId() != -1 && passwordInput.equals(user.getPassword()) == false) {
					ErrorMsgLabel_1.setText("The username or password you entered is ");
					ErrorMsgLabel_2.setText("incorrect. Please try again");
				}
				
				
				//If the sign in details are correct, then we go to the taskscreen and close the login screen.
				if(passwordInput.equals(user.getPassword())) {
					//taskScreenGui.start();
					taskScreenGui.start(user);
					
					frame.dispose();
					if(newUserGui != null) {
						newUserGui.stop();
					}
				}
			}
		});
		
		//add action to new User Button
		newUserBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e1) {
				newUserGui.start();
				
			}
		});
		
	}
}
