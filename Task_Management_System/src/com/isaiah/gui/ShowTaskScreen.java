package com.isaiah.gui;

import javax.swing.JFrame;
import javax.swing.text.MaskFormatter;

import com.isaiah.objects.*;
import com.isaiah.enums.taskStatusEnum;
import com.isaiah.utilities.QueryUtility;


import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.FlowLayout;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.awt.Color;

public class ShowTaskScreen {

	private static Task currTask;
	private static User currUser;
	private static JTextField taskNameTxtFld;
	private static JTextField taskLocationTxtFld;
	private static JFrame frame;

	public static void start() {
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	public static void start(Task task) {
		currTask = task;
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	public static void setUser(User user) {
		currUser = user;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private static void createAndShowGUI() {
		frame = new JFrame("Task Screen");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(671, 518);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JLabel taskScreenLbl = new JLabel("Task Screen");
		taskScreenLbl.setFont(new Font("Tahoma", Font.PLAIN, 24));
		taskScreenLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(taskScreenLbl);

		JPanel taskNamePnl = new JPanel();
		frame.getContentPane().add(taskNamePnl);
		taskNamePnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel taskNameLbl = new JLabel("TaskName:");
		taskNamePnl.add(taskNameLbl);

		taskNameTxtFld = new JTextField();
		taskNameTxtFld.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//get current caret position
				int caretPos = taskNameTxtFld.getCaretPosition();

				//get text from textField
				String text = taskNameTxtFld.getText();

				//Append new character at the caret position
				text = text.substring(0, caretPos) + e.getActionCommand() + text.substring(caretPos);

				//Update textfield with the modified text
				taskNameTxtFld.setText(text);

				//Move the caret position to after the newly inserted character
				taskNameTxtFld.setCaretPosition(caretPos + 1);

			}

		});
		taskNamePnl.add(taskNameTxtFld);
		taskNameTxtFld.setColumns(10);

		JPanel taskStatusPnl = new JPanel();
		frame.getContentPane().add(taskStatusPnl);
		taskStatusPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel taskStatusLbl = new JLabel("Task Status:");
		taskStatusPnl.add(taskStatusLbl);

		String[] taskStatusValues = {taskStatusEnum.INPROGRESS.getStatusString(), taskStatusEnum.COMPLETED.getStatusString(), taskStatusEnum.FAILED.getStatusString(), taskStatusEnum.TORESCHEDULE.getStatusString(), taskStatusEnum.ARCHIVED.getStatusString()};


		JComboBox taskStatusComboBox = new JComboBox(taskStatusValues);
		taskStatusComboBox.setSelectedIndex(0);
		taskStatusPnl.add(taskStatusComboBox);

		JPanel taskDescPnl = new JPanel();
		frame.getContentPane().add(taskDescPnl);

		JLabel TaskDescLbl = new JLabel("Task Description: ");
		taskDescPnl.add(TaskDescLbl);

		JTextArea taskDescTxtArea = new JTextArea(3, 20);
		//taskDescTxtArea.setColumns(20);
		//taskDescTxtArea.setRows(3);
		taskDescTxtArea.setLineWrap(true);
		taskDescPnl.add(taskDescTxtArea);

		JPanel taskLocationPnl = new JPanel();
		frame.getContentPane().add(taskLocationPnl);
		taskLocationPnl.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel taskLocationLbl = new JLabel("Task Location:");
		taskLocationPnl.add(taskLocationLbl);

		taskLocationTxtFld = new JTextField();
		taskLocationTxtFld.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//get current caret position
				int caretPos = taskLocationTxtFld.getCaretPosition();

				//get text from textField
				String text = taskLocationTxtFld.getText();

				//Append new character at the caret position
				text = text.substring(0, caretPos) + e.getActionCommand() + text.substring(caretPos);

				//Update textfield with the modified text
				taskLocationTxtFld.setText(text);

				//Move the caret position to after the newly inserted character
				taskLocationTxtFld.setCaretPosition(caretPos + 1);

			}

		});

		taskLocationPnl.add(taskLocationTxtFld);
		taskLocationTxtFld.setColumns(10);

		JPanel taskStartTimePnl = new JPanel();
		frame.getContentPane().add(taskStartTimePnl);

		JLabel taskStartTimeLbl = new JLabel("Task Start Time:");
		taskStartTimePnl.add(taskStartTimeLbl);


		//create a SimpleDateFormat to define the date format.
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");		

		
		JFormattedTextField taskStartTimeFormattedField = new JFormattedTextField(dateTimeFormatter);
		try {
			MaskFormatter formatter = new MaskFormatter("##-##-####");
            formatter.setPlaceholderCharacter('_'); // Placeholder character for empty slots
			
			taskStartTimeFormattedField.setText("yyyy-MM-dd HH:mm:ss");
			taskStartTimeFormattedField.setColumns(16);
			
			taskStartTimeFormattedField.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//Get the formatted text from the formatted text field
					String formattedText = taskStartTimeFormattedField.getText();
					
					//Update the text field with the formatted Text
					taskStartTimeFormattedField.setText(formattedText);
				}
			});
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		taskStartTimePnl.add(taskStartTimeFormattedField);

		JPanel taskEndTimePnl = new JPanel();
		frame.getContentPane().add(taskEndTimePnl);

		JLabel taskEndTimeLbl = new JLabel("Task End Time:");
		taskEndTimePnl.add(taskEndTimeLbl);

		JFormattedTextField taskEndTimeFormattedField = new JFormattedTextField(dateTimeFormatter);
		taskEndTimeFormattedField.setText("yyyy-MM-dd HH:mm:ss");
		taskEndTimeFormattedField.setColumns(16);
		taskEndTimePnl.add(taskEndTimeFormattedField);

		JButton taskScreenBtn = new JButton("Submit");
		taskScreenBtn.setBackground(Color.GREEN);
		taskScreenBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskScreenBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If currTask is null, then we need to add a new Task to the TaskList as well as add it to the database for the user
				if(currTask == null) {
					Task taskToAdd = new Task();
					System.out.println("Task Id AutoIncrementValue: " + QueryUtility.getTaskIdAutoIncrementVal());
					taskToAdd.setTask_id(QueryUtility.getTaskIdAutoIncrementVal());
					taskToAdd.setOwner_user_id(currUser.getId());
					taskToAdd.setTask_name(taskNameTxtFld.getText());
					taskToAdd.setTask_status((String) taskStatusComboBox.getSelectedItem());
					taskToAdd.setTask_description(taskDescTxtArea.getText());
					taskToAdd.setTask_location(taskLocationTxtFld.getText());

					try {
						taskToAdd.setTask_start_time(LocalDateTime.parse(taskStartTimeFormattedField.getText(), dateTimeFormatter));
						taskToAdd.setTask_end_time(LocalDateTime.parse(taskEndTimeFormattedField.getText(), dateTimeFormatter));
					} catch(Exception ex) {
						ex.printStackTrace();
					}
					QueryUtility.createTask(taskToAdd); //add task to DB
					//TaskscreenGui.populateTaskListModel(); //repopulate the taskmodel.
					TaskscreenGui.getTaskListModel().addElement(taskToAdd);
					TaskscreenGui.getTaskJList().clearSelection(); //clear the selection on the JList.
					TaskscreenGui.getTaskJList().repaint(); //repaint the JList
					
				} else { //If currTask is not null then change the fields of currTask
					currTask.setTask_name(taskNameTxtFld.getText());
					currTask.setTask_status((String) taskStatusComboBox.getSelectedItem());
					currTask.setTask_description(taskDescTxtArea.getText());
					currTask.setTask_location(taskLocationTxtFld.getText());
					currTask.setTask_start_time(LocalDateTime.parse(taskStartTimeFormattedField.getText(), dateTimeFormatter));
					currTask.setTask_end_time(LocalDateTime.parse(taskEndTimeFormattedField.getText(), dateTimeFormatter));
					
					QueryUtility.updateTask(currTask); //Update the Task in the DB
					TaskscreenGui.getTaskJList().repaint(); //repaint the JList

				}





				//TaskscreenGui.populateTaskListModel();
				frame.dispose();
			}
		});
		frame.getContentPane().add(taskScreenBtn);

		frame.setVisible(true);


		//If currTask is not null, then we'll populate the taskscreen with the information with the task info
		if(currTask != null) {
			//for task name
			taskNameTxtFld.setText(currTask.getTask_name());

			//For task status
			if(currTask.getTask_status().equals(taskStatusEnum.INPROGRESS.getStatusString())) {
				taskStatusComboBox.setSelectedIndex(0);
			} else if(currTask.getTask_status().equals(taskStatusEnum.COMPLETED.getStatusString())) {
				taskStatusComboBox.setSelectedIndex(1);
			} else if(currTask.getTask_status().equals(taskStatusEnum.FAILED.getStatusString())) {
				taskStatusComboBox.setSelectedIndex(2);
			} else if(currTask.getTask_status().equals(taskStatusEnum.TORESCHEDULE.getStatusString())) {
				taskStatusComboBox.setSelectedIndex(3);
			} else if(currTask.getTask_status().equals(taskStatusEnum.ARCHIVED.getStatusString())) {
				taskStatusComboBox.setSelectedIndex(4);
			}

			//For task description
			taskDescTxtArea.setText(currTask.getTask_description());

			//For Task Location
			taskLocationTxtFld.setText(currTask.getTask_location());

			//For Task Start time
			//taskStartTimeFormattedField.setText(currTask.getTask_start_time().getYear() + "-" + currTask.getTask_start_time().getMonthValue() + "-" + currTask.getTask_start_time().getDayOfMonth() + " " + currTask.getTask_start_time().getHour() + ":" + currTask.getTask_start_time().getMinute() + ":" + currTask.getTask_start_time().getSecond());
			taskStartTimeFormattedField.setText(currTask.getTask_start_time().format(dateTimeFormatter));


			//For Task End Time
			//taskEndTimeFormattedField.setText(currTask.getTask_end_time().getYear() + "-" + currTask.getTask_end_time().getMonthValue() + "-" + currTask.getTask_end_time().getDayOfMonth() + " " + currTask.getTask_end_time().getHour() + ":" + currTask.getTask_end_time().getMinute() + ":" + currTask.getTask_end_time().getSecond());	
			taskEndTimeFormattedField.setText(currTask.getTask_end_time().format(dateTimeFormatter));
		}

	}

}
