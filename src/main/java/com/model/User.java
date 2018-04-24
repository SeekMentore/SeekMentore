package com.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

	private static final long serialVersionUID = -8603850515164057242L;
	
	private String userId;
	private String name;
	private Date dateOfBirth;
	private Date hireDate;
	private String workLocation;
	private List<String> pageAccessTypes;
	
	public User() {}
	
	public User(
		String userId,
		String name,
		Date dateOfBirth,
		Date hireDate,
		String workLocation,
		List<String> pageAccessTypes
	) {
		this.userId = userId;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.hireDate = hireDate;
		this.workLocation = workLocation;
		this.pageAccessTypes = pageAccessTypes;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getWorkLocation() {
		return workLocation;
	}
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	public List<String> getPageAccessTypes() {
		return pageAccessTypes;
	}
	public void setPageAccessTypes(List<String> pageAccessTypes) {
		this.pageAccessTypes = pageAccessTypes;
	}
}
