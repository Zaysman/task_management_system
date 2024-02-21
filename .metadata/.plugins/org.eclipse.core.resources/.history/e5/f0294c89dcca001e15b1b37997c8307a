/*
 * Object to hold task information from task_info table
 */

/*
Create table task_info (
task_id int auto_increment primary key,
task_instance_id int,
task_name varchar(25),
task_start_time datetime,
task_end_time datetime,
task_due_date datetime,
task_status varchar(25),
task_description varchar(1000),
task_location varchar(100),
owner_user_id int,
unique(task_instance_id)
);

 */

package com.isaiah.objects;

import java.time.*;

public class Task {

	private int task_id, task_instance_id, owner_user_id;
	private String task_name, task_status, task_description, task_location;
	private LocalDateTime task_start_time, task_end_time, task_due_date;
	
	public Task() {
		super();
	}

	public Task(int task_id, int task_instance_id, int owner_user_id, String task_name, String task_status, String task_description, String task_location, LocalDateTime task_start_time, LocalDateTime task_end_time, LocalDateTime task_due_date) {
		this.task_id = task_id;
		this.task_instance_id = task_instance_id;
		this.owner_user_id = owner_user_id;
		this.task_name = task_name;
		this.task_status = task_status;
		this.task_description = task_description;
		this.task_location = task_location;
		this.task_start_time = task_start_time;
		this.task_end_time = task_end_time;
		this.task_due_date = task_due_date;
	}

	public int getTask_id() {
		return task_id;
	}

	public int getTask_instance_id() {
		return task_instance_id;
	}

	public int getOwner_user_id() {
		return owner_user_id;
	}

	public String getTask_name() {
		return task_name;
	}

	public String getTask_status() {
		return task_status;
	}

	public String getTask_description() {
		return task_description;
	}

	public String getTask_location() {
		return task_location;
	}

	public LocalDateTime getTask_start_time() {
		return task_start_time;
	}

	public LocalDateTime getTask_end_time() {
		return task_end_time;
	}

	public LocalDateTime getTask_due_date() {
		return task_due_date;
	}

	public void setTask_id(int task_id) {
		this.task_id = task_id;
	}

	public void setTask_instance_id(int task_instance_id) {
		this.task_instance_id = task_instance_id;
	}

	public void setOwner_user_id(int owner_user_id) {
		this.owner_user_id = owner_user_id;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public void setTask_status(String task_status) {
		this.task_status = task_status;
	}

	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}

	public void setTask_location(String task_location) {
		this.task_location = task_location;
	}

	public void setTask_start_time(LocalDateTime task_start_time) {
		this.task_start_time = task_start_time;
	}

	public void setTask_end_time(LocalDateTime task_end_time) {
		this.task_end_time = task_end_time;
	}

	public void setTask_due_date(LocalDateTime task_due_date) {
		this.task_due_date = task_due_date;
	}

	@Override
	public String toString() {
		return "Task [task_id=" + task_id + ", task_instance_id=" + task_instance_id + ", owner_user_id="
				+ owner_user_id + ", task_name=" + task_name + ", task_status=" + task_status + ", task_description="
				+ task_description + ", task_location=" + task_location + ", task_start_time=" + task_start_time
				+ ", task_end_time=" + task_end_time + ", task_due_date=" + task_due_date + "]";
	}
	


	
	
	
	
	
}
