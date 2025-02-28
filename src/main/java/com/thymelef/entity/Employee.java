package com.thymelef.entity;


import java.sql.Date;
import java.util.List;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Employee {
	@Id
	@Column(unique = true)
	private int id;
	private String employeeName;
	private String employeeLastName;
	@Column(unique = true)
	private String gmailId;
	private String password;
	private long mobileNumber;
	private String gender;
	private String address;
	private java.sql.Date joiningDate;
	private String Department;
	private String Designation;
	private String exitDate;
	private String type;
	private boolean isActive;
	private String profilePic;
	private String provider;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "employee")
	@JsonManagedReference
	private List<DateDetails> dateDetails;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "employee")
	@JsonManagedReference
	private List<SalaryMonth> salaryMonths;
	
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "employee")
	@JsonManagedReference
	private EmployeeSalary employeeSalary;
	
	@OneToMany(cascade = CascadeType.ALL ,mappedBy = "employee")
	@JsonManagedReference
	private List<LeaveDetails> leaveDetails;
	
	
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "employee")
	@JsonManagedReference
	private LeavesTypes LeavesTypes;
	
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "employee")
	@JsonManagedReference
	private Resign resign;
	
	@OneToOne(cascade = CascadeType.ALL ,mappedBy = "employee")
	private BankAccount bankAccount;

	public Employee(int id, String employeeName, String employeeLastName, String gmailId, String password,
			long mobileNumber, String gender, String address, Date joiningDate, String department, String designation,
			String exitDate, String type, boolean isActive, String profilePic, String provider,
			List<DateDetails> dateDetails, List<SalaryMonth> salaryMonths, EmployeeSalary employeeSalary,
			List<LeaveDetails> leaveDetails, com.thymelef.entity.LeavesTypes leavesTypes, Resign resign,
			BankAccount bankAccount) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.employeeLastName = employeeLastName;
		this.gmailId = gmailId;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.address = address;
		this.joiningDate = joiningDate;
		Department = department;
		Designation = designation;
		this.exitDate = exitDate;
		this.type = type;
		this.isActive = isActive;
		this.profilePic = profilePic;
		this.provider = provider;
		this.dateDetails = dateDetails;
		this.salaryMonths = salaryMonths;
		this.employeeSalary = employeeSalary;
		this.leaveDetails = leaveDetails;
		LeavesTypes = leavesTypes;
		this.resign = resign;
		this.bankAccount = bankAccount;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public java.sql.Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(java.sql.Date joiningDate) {
		this.joiningDate = joiningDate;
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

	public String getExitDate() {
		return exitDate;
	}

	public void setExitDate(String exitDate) {
		this.exitDate = exitDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public List<DateDetails> getDateDetails() {
		return dateDetails;
	}

	public void setDateDetails(List<DateDetails> dateDetails) {
		this.dateDetails = dateDetails;
	}

	public List<SalaryMonth> getSalaryMonths() {
		return salaryMonths;
	}

	public void setSalaryMonths(List<SalaryMonth> salaryMonths) {
		this.salaryMonths = salaryMonths;
	}

	public EmployeeSalary getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(EmployeeSalary employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public List<LeaveDetails> getLeaveDetails() {
		return leaveDetails;
	}

	public void setLeaveDetails(List<LeaveDetails> leaveDetails) {
		this.leaveDetails = leaveDetails;
	}

	public LeavesTypes getLeavesTypes() {
		return LeavesTypes;
	}

	public void setLeavesTypes(LeavesTypes leavesTypes) {
		LeavesTypes = leavesTypes;
	}

	public Resign getResign() {
		return resign;
	}

	public void setResign(Resign resign) {
		this.resign = resign;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", employeeName=" + employeeName + ", employeeLastName=" + employeeLastName
				+ ", gmailId=" + gmailId + ", password=" + password + ", mobileNumber=" + mobileNumber + ", gender="
				+ gender + ", address=" + address + ", joiningDate=" + joiningDate + ", Department=" + Department
				+ ", Designation=" + Designation + ", exitDate=" + exitDate + ", type=" + type + ", isActive="
				+ isActive + ", profilePic=" + profilePic + ", provider=" + provider + ", dateDetails=" + dateDetails
				+ ", salaryMonths=" + salaryMonths + ", employeeSalary=" + employeeSalary + ", leaveDetails="
				+ leaveDetails + ", LeavesTypes=" + LeavesTypes + ", resign=" + resign + ", bankAccount=" + bankAccount
				+ "]";
	}
	
	

	
	
	
	
}
