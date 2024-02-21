/*
 * User object to hold data from DB
 */

/*
 * Table info:
 * create table user_login (
 * user_id int auto_increment primary key, 
 * username varchar(25), 
 * password varchar(25)
);

 */

package com.isaiah.objects;

public class User {

	private int id;
	private String username, password;

	
	public User() {
		this(-1, "default username", "default password");
	}
	
	public User(String username, String password) {
		this(-1, username, password);
	}
	
	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	
	
}
