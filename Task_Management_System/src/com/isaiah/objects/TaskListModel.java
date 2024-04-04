package com.isaiah.objects;



import javax.swing.AbstractListModel;

import java.util.List;
import java.util.LinkedList;


public class TaskListModel extends AbstractListModel<String>{
	
	private LinkedList<Task> tasks;
	
	public TaskListModel(LinkedList<Task> tasks) {
		this.tasks = tasks;
	}
	
	public TaskListModel() {
		this.tasks = new LinkedList<>();
	}
	
	@Override
	public int getSize() {
		return tasks.size();
	}
	
	@Override
	public String getElementAt(int index) {
		return tasks.get(index).getTask_name();
	}
	
	public Task getTaskAt(int index) {
		return tasks.get(index);
	}
	
	public void addElement(Task task) {
		tasks.add(task);
		int index = tasks.size() - 1;
		fireIntervalAdded(this, index, index);
		printTasks();
	}
	
	public void removeElement(Task task) {
		tasks.remove(task);
		printTasks();
	}
	
	public void removeElementAtIndex(int index) {
		if(index >= 0 && index < tasks.size()) {
			tasks.remove(index);
			fireIntervalRemoved(this, index, index); //notify Listeners about removal
			System.out.println("Task removed at index: " + index);
			printTasks();
		}
	}
	
	public void setElement(int index, Task task) {
		tasks.set(index, task);
	}
	
	public void printTasks() {
		for(Task t : tasks) {
			System.out.println(t.toString());
		}
	}

}
