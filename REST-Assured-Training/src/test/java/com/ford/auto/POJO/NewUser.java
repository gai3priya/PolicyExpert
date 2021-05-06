package com.ford.auto.POJO;

public class NewUser {
	
	private String name;
	private String gender;
	private String email;
	private String status;
	
	//Getter and Setter Methods
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNewUserRecord() {
		return(this.name+ " "+this.gender+ " "+this.email+ " "+this.status);
	}
	
	

}
