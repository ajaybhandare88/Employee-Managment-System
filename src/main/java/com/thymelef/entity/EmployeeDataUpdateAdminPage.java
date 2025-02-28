package com.thymelef.entity;

public class EmployeeDataUpdateAdminPage {
	
	private int employeeId;
	private String employeeName;
	private String employeeLastName;
	private String gmailId;
	private long mobileNumber;
	private String address;
	private String Department;
	private String Designation;
	private String type;
	public EmployeeDataUpdateAdminPage(int employeeId, String employeeName, String employeeLastName, String gmailId,
			long mobileNumber, String address, String department, String designation, String type) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeLastName = employeeLastName;
		this.gmailId = gmailId;
		this.mobileNumber = mobileNumber;
		this.address = address;
		Department = department;
		Designation = designation;
		this.type = type;
	}
	public EmployeeDataUpdateAdminPage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getGmailId() {
		return gmailId;
	}
	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "EmployeeDataUpdateAdminPage [employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", employeeLastName=" + employeeLastName + ", gmailId=" + gmailId + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + ", Department=" + Department + ", Designation=" + Designation + ", type="
				+ type + "]";
	}
	
	

}
