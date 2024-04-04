package com.isaiah.gui;

import com.isaiah.objects.*;
import com.isaiah.utilities.QueryUtility;

import com.isaiah.gui.ShowTaskScreen;

import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.*;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;




public class TaskscreenGui {
	private static final ButtonGroup taskRdoBtnGroup = new ButtonGroup();
	private static User currUser;
	private static int selectedTaskIndex;
	private static Task selectedTask;
	private static TaskListModel taskListModel = new TaskListModel();
	private static TaskListModelV2 taskListModelV2 = new TaskListModelV2();
	private static JList taskJList;


	public static TaskListModelV2 getTaskListModel() {
		//return taskListModel;
		return taskListModelV2;
	}

	public static JList getTaskJList() {
		return taskJList;
	}

	public static void start() {
		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	public static void start(User user) {
		currUser = user;
		SwingUtilities.invokeLater(() -> createAndShowGUI());
		ShowTaskScreen.setUser(currUser);
	}


	public static void populateTaskListModel() {
		//taskListModel = new TaskListModel(QueryUtility.getTasksByUser(currUser));
		//taskListModelV2.populateTasksFromDBWithUserID(currUser.getId());
		taskListModelV2 = new TaskListModelV2(QueryUtility.getTasksByUser(currUser));
	}

	public static void populateTaskListModelByStatus(String status) {

		if(status.equals("All")) {
			//taskListModelV2 = new TaskListModelV2(QueryUtility.getTasksByUser(currUser));
			taskListModelV2.setTaskList(QueryUtility.getTasksByUser(currUser));
		} else {
			//taskListModelV2 = new TaskListModelV2(QueryUtility.getTasksByStatusForUser(currUser, status));
			taskListModelV2.setTaskList(QueryUtility.getTasksByStatusForUser(currUser, status));
		}
		
		
		
		System.out.println("Number of Elements in taskList after changing radio btn: " + taskListModelV2.getSize());
		//taskJList.clearSelection();
		//taskJList.repaint();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Taskscreen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1109, 707);

		//int panelMinWidth = frame.getWidth() / 3;


		// Add your Swing components and layout here

		//frame.pack();
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		JPanel taskTypePnl = new JPanel();
		taskTypePnl.setBorder(new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(taskTypePnl);
		taskTypePnl.setMinimumSize(new Dimension((int)(frame.getWidth() * .15), frame.getHeight()));
		taskTypePnl.setMaximumSize(new Dimension((int)(frame.getWidth() * .15), frame.getHeight()));
		taskTypePnl.setLayout(new BoxLayout(taskTypePnl, BoxLayout.Y_AXIS));

		JLabel taskTypeLbl = new JLabel("Task Type");
		taskTypeLbl.setBorder(new EmptyBorder(0, 0, 80, 0));
		taskTypeLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		taskTypeLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskTypePnl.add(taskTypeLbl);

		JRadioButton allTasksRdoBtn = new JRadioButton("All");
		taskRdoBtnGroup.add(allTasksRdoBtn);
		allTasksRdoBtn.setSelected(true);
		allTasksRdoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		allTasksRdoBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		taskTypePnl.add(allTasksRdoBtn);

		JRadioButton inProgRdoBtn = new JRadioButton("In Progress");
		taskRdoBtnGroup.add(inProgRdoBtn);
		inProgRdoBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		inProgRdoBtn.setAlignmentX(0.5f);
		taskTypePnl.add(inProgRdoBtn);

		JRadioButton completedRdoBtn = new JRadioButton("Completed");
		taskRdoBtnGroup.add(completedRdoBtn);
		completedRdoBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		completedRdoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskTypePnl.add(completedRdoBtn);

		JRadioButton failedRdoBtn = new JRadioButton("Failed");
		taskRdoBtnGroup.add(failedRdoBtn);
		failedRdoBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		failedRdoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskTypePnl.add(failedRdoBtn);

		JRadioButton toRescheduleRdoBtn = new JRadioButton("To Reschedule");
		taskRdoBtnGroup.add(toRescheduleRdoBtn);
		toRescheduleRdoBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		toRescheduleRdoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskTypePnl.add(toRescheduleRdoBtn);

		JRadioButton archivedRdoBtn = new JRadioButton("Archived");
		taskRdoBtnGroup.add(archivedRdoBtn);
		archivedRdoBtn.setBorder(new EmptyBorder(0, 0, 20, 0));
		archivedRdoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskTypePnl.add(archivedRdoBtn);

		ItemListener itemListener = new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange() == ItemEvent.SELECTED) {
					JRadioButton selectedRdoBtn = (JRadioButton) e.getItem();
					System.out.println("Selected: " + selectedRdoBtn.getText());
					populateTaskListModelByStatus(selectedRdoBtn.getText());
					taskJList.repaint();
				}
			}
		};

		allTasksRdoBtn.addItemListener(itemListener);
		inProgRdoBtn.addItemListener(itemListener);
		completedRdoBtn.addItemListener(itemListener);
		failedRdoBtn.addItemListener(itemListener);
		toRescheduleRdoBtn.addItemListener(itemListener);
		archivedRdoBtn.addItemListener(itemListener);



		JPanel tasksPnl = new JPanel();
		frame.getContentPane().add(tasksPnl);
		tasksPnl.setMinimumSize(new Dimension((int)(frame.getWidth() * .6), frame.getHeight()));
		tasksPnl.setMaximumSize(new Dimension((int)(frame.getWidth() * .6), frame.getHeight()));
		tasksPnl.setLayout(new BoxLayout(tasksPnl, BoxLayout.Y_AXIS));

		JLabel tasksLbl = new JLabel("Tasks");
		tasksLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		tasksLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tasksPnl.add(tasksLbl);



		populateTaskListModel();
		taskJList = new JList<>(taskListModelV2);
		System.out.println("Number of elements in JList: " + taskJList.getModel().getSize());
		//tasksPnl.add(taskJList);

		JScrollPane taskScrollPane = new JScrollPane(taskJList);

		taskJList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) { //Ensure selection is not in the middle of adjusting
					try {
						//Get the selected task
						selectedTaskIndex = taskJList.getSelectedIndex();
						selectedTask = taskListModelV2.getTaskAt(selectedTaskIndex);
						//selectedTask = (Task) taskJList.getSelectedValue();
						System.out.println("SelectedTask name: " + selectedTask.getTask_name() + "\n" +
								"Selected Index: " + selectedTaskIndex);
						System.out.println(selectedTask.toString());
						
					} catch(NullPointerException npe) {
						System.out.println("Caught a NullPointerException inside the JList Selection Listener");

					}
				}
			}

		});

		tasksPnl.add(taskScrollPane);




		JPanel taskOptionPnl = new JPanel();
		taskOptionPnl.setBorder(new MatteBorder(0, 5, 0, 0, (Color) new Color(0, 0, 0)));
		frame.getContentPane().add(taskOptionPnl);
		taskOptionPnl.setMinimumSize(new Dimension((int)(frame.getWidth() * .25), frame.getHeight()));
		taskOptionPnl.setMaximumSize(new Dimension((int)(frame.getWidth() * .25), frame.getHeight()));
		taskOptionPnl.setLayout(new BoxLayout(taskOptionPnl, BoxLayout.Y_AXIS));

		JLabel taskOptionLbl = new JLabel("Task Options");
		taskOptionLbl.setBorder(new EmptyBorder(0, 0, 80, 0));
		taskOptionLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		taskOptionLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskOptionPnl.add(taskOptionLbl);

		JButton viewTaskBtn = new JButton("View Task");
		viewTaskBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ShowTaskScreen.start(null);
				if(selectedTask != null) {
					ShowTaskScreen.setUser(currUser);
					ShowTaskScreen.start(selectedTask);
				}
			}
		});



		viewTaskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskOptionPnl.add(viewTaskBtn);

		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		taskOptionPnl.add(rigidArea);

		JButton newTaskBtn = new JButton("Add New Task");
		newTaskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		newTaskBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//ShowTaskScreen.start();
				ShowTaskScreen.start(null);
			}
		});
		taskOptionPnl.add(newTaskBtn);

		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		taskOptionPnl.add(rigidArea_1);

		JButton deleteTaskBtn = new JButton("Delete Task");
		deleteTaskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		deleteTaskBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedTask != null) {
					QueryUtility.deleteTask(selectedTask); //Update the DB by deleting the task
					taskListModelV2.removeElementAtIndex(selectedTaskIndex); //remove the element at the selected index
					taskJList.clearSelection(); //clear the selection
					selectedTask = new Task(); //set Task to a new Task Object to avoid the NullPointerException
					taskJList.repaint(); //repaint the JList with the modified taskListModel
					System.out.println("JList should repaint.");
				}
			}
		});

		taskOptionPnl.add(deleteTaskBtn);

		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		taskOptionPnl.add(rigidArea_2);

		JButton completeTaskBtn = new JButton("Complete Task");
		completeTaskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskOptionPnl.add(completeTaskBtn);

		completeTaskBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				//update the task's status in the app
				selectedTask.setTask_status(2); //Passing 2 to this method will set the task status to Completed
				QueryUtility.updateTask(selectedTask); //Update the task in the DB.
				taskJList.repaint();//repaint the task list


			}

		});

		JButton rescheduleTaskBtn = new JButton("Reschedule Task");
		rescheduleTaskBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				selectedTask.setTask_status(4); //Passing 4 to this method will set the task status to Reschedule
				QueryUtility.updateTask(selectedTask); //Update task in the DB
				taskJList.repaint();//repaint the task list
			}
		});

		Component rigidArea_3 = Box.createRigidArea(new Dimension(20, 20));
		taskOptionPnl.add(rigidArea_3);

		JButton failTaskBtn = new JButton("Fail Task");
		failTaskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskOptionPnl.add(failTaskBtn);

		failTaskBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectedTask.setTask_status(3);
				QueryUtility.updateTask(selectedTask); //Update the task in the DB
				taskJList.repaint();//repaint the task list

			}

		});

		Component rigidArea_5 = Box.createRigidArea(new Dimension(20, 20));
		taskOptionPnl.add(rigidArea_5);
		rescheduleTaskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskOptionPnl.add(rescheduleTaskBtn);

		Component rigidArea_4 = Box.createRigidArea(new Dimension(20, 20));
		taskOptionPnl.add(rigidArea_4);

		JButton archiveTaskBtn = new JButton("Archive Task");
		archiveTaskBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		taskOptionPnl.add(archiveTaskBtn);

		archiveTaskBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedTask.setTask_status(5);
				QueryUtility.updateTask(selectedTask);
				taskJList.repaint();
			}
		});


		frame.setVisible(true);
	}




}
