package com.isaiah.objects;

import javax.swing.AbstractListModel;

import com.isaiah.utilities.QueryUtility;

import java.util.List;
import java.util.LinkedList;


public class TaskListModelV2 extends AbstractListModel<String> {
	
	private List<Task> tasks; //List containing the Task Objects
	
	public TaskListModelV2() {
		this.tasks = new LinkedList<>();
	}
	
	public TaskListModelV2(List<Task> taskList) {
		this.tasks = taskList;
	}
	
	public void populateTasksFromDBWithUserID(int id) {
		this.tasks = QueryUtility.getTasksByOwnerId(id);
	}
	
	public List<Task> getTasks() {
		return this.tasks;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return tasks.size();
	}

	@Override
	public String getElementAt(int index) {
		// TODO Auto-generated method stub
		return tasks.get(index).getTask_name();
	}
	
	
	public Task getTaskAt(int index) {
		if(index >=0 && index < tasks.size()) {
			return tasks.get(index);
		}
		else {
			return null;
		}
	}
	
	public void addElement(Task task) {
		tasks.add(task);
		int index = tasks.size() - 1;
		fireIntervalAdded(this, index, index);
		System.out.println("TaskListModel after adding element");
		printTasksStatusToConsole(); //add method to print in console status of tasks list.
	}
	
	public void removeElementAtIndex(int index) {
		System.out.println("index at which the element is to be removed: " + index);
		if(index >= 0 && index < tasks.size()) {
			tasks.remove(index);
			fireIntervalRemoved(this, index, index);
			System.out.println("TaskListModel after removing element");
			printTasksStatusToConsole(); //add method to print in console status of tasks list
		}
 	}
	
	public void setElement(int index, Task task) {
		if(index >=0 && index < tasks.size()) {
			tasks.set(index, task);
		}
	}
	
	public void setTaskList(List<Task> newTaskList) {
		this.tasks = newTaskList;
		fireContentsChanged(this, 0, tasks.size() -1);
	}
	
	public void printTasksStatusToConsole() {
		System.out.println("Tasks size: " + tasks.size());
		for(Task t: tasks) {
			System.out.println(t.toString());
		}
	}

}

	
