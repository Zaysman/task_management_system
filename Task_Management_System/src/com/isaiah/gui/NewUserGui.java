package com.isaiah.gui;

import com.isaiah.objects.User;
import com.isaiah.utilities.QueryUtility;

import javax.swing.*;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.TextField;
import java.awt.FlowLayout;
import java.awt.Color;



public class NewUserGui {
	private static JTextField passwordTxtFld;
	private static JTextField usernameTxtFld;
	
	private static JFrame frame;
	
	public static void start() {
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}
	
	public static void stop() {
		frame.dispose();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	private static void createAndShowGUI() {
		//System.out.println("Creating New User Gui");
		
		frame = new JFrame("New User");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(423, 326);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JLabel newUserPgPnl = new JLabel("New User Sign Up");
		newUserPgPnl.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(newUserPgPnl);
		
		Panel usernamePnl = new Panel();
		frame.getContentPane().add(usernamePnl);
		usernamePnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel usernameLbl = new JLabel("Username:");
		usernamePnl.add(usernameLbl);
		
		usernameTxtFld = new JTextField();
		usernamePnl.add(usernameTxtFld);
		usernameTxtFld.setColumns(10);
		
		usernamePnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, usernamePnl.getPreferredSize().height));
		
		
		Panel passwordPnl = new Panel();
		frame.getContentPane().add(passwordPnl);
		passwordPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel passwordLbl = new JLabel("Password:");
		passwordPnl.add(passwordLbl);
		
		passwordTxtFld = new JTextField();
		passwordPnl.add(passwordTxtFld);
		passwordTxtFld.setColumns(10);
		passwordPnl.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{passwordLbl, passwordTxtFld}));
		
		
		passwordPnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, usernamePnl.getPreferredSize().height));
		
		JButton addNewUserBtn = new JButton("Create New User");
		addNewUserBtn.setForeground(Color.WHITE);
		addNewUserBtn.setBackground(Color.GREEN);
		addNewUserBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(addNewUserBtn);
		
		
		
		JLabel resultMsgLbl = new JLabel("");
		resultMsgLbl.setForeground(Color.RED);
		resultMsgLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(resultMsgLbl);
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{newUserPgPnl, usernamePnl, usernameLbl, usernameTxtFld, passwordPnl, passwordLbl, passwordTxtFld, addNewUserBtn, resultMsgLbl}));
		
		frame.setVisible(true);
		
		addNewUserBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//get user details
				User newUser = new User(usernameTxtFld.getText(), passwordTxtFld.getText());
				
				//try to add user to the db
				if(QueryUtility.createUser(newUser)) {
					System.out.println("User was created successfully.");
					resultMsgLbl.setForeground(Color.GREEN);
					resultMsgLbl.setText("User Created Successfully. Now Login");
					
				} else {
					resultMsgLbl.setForeground(Color.RED);
					resultMsgLbl.setText("The User wasn't created Successfully. Try using a different username.");
				}
				
			}
		});
		
		
		
	}

}
