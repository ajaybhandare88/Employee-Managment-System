package com.thymelef.entity;

import org.springframework.web.multipart.MultipartFile;

public class EditEmployeeData {
	
	private int id;
	private String first_name;
	private String email_id;
	private String Gender;
	private long mobile_number;
	private String Department;
	private String Designation;
	private MultipartFile profile;
	
	

	public EditEmployeeData(int id, String first_name, String email_id, String gender, long mobile_number,
			String department, String designation, MultipartFile profile) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.email_id = email_id;
		Gender = gender;
		this.mobile_number = mobile_number;
		Department = department;
		Designation = designation;
		this.profile = profile;
	}

	public EditEmployeeData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public long getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(long mobile_number) {
		this.mobile_number = mobile_number;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public MultipartFile getProfile() {
		return profile;
	}

	public void setProfile(MultipartFile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "EditEmployeeData [id=" + id + ", first_name=" + first_name + ", email_id=" + email_id + ", Gender="
				+ Gender + ", mobile_number=" + mobile_number + ", Department=" + Department + ", Designation="
				+ Designation + ", profile=" + profile + "]";
	}

	

	
	
	
}
