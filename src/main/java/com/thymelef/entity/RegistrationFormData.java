package com.thymelef.entity;


import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;


public class RegistrationFormData {
	
	private String employeeFirstName;
	private String lastName;
	private long mobileNumber;
	private String gmailId;
	private String gender;
	private String address;
	private Date joiningDate; 
	private String Designation;
	private String Department;
	private String type;
	
	private long basicSalary;
	private long medicalAllowance;
	private long HouseRentAllAllowance;
	private long persnolAllowance;
	private long transportingAllowance;
	private long insurance;
	private long professionalTax;
	
	private int paidLeave;
	private int annualEave;
	private int sickLeave;
	private int WFHLeave;
	
	private long bankAccountNumber;
	private String bankName;
	private String IFSC_Code;
	private String branchName;
	private String bankAddress;
	public RegistrationFormData(String employeeFirstName, String lastName, long mobileNumber, String gmailId,
			String gender, String address, Date joiningDate, String designation, String department, String type,
			long basicSalary, long medicalAllowance, long houseRentAllAllowance, long persnolAllowance,
			long transportingAllowance, long insurance, long professionalTax, int paidLeave, int annualEave,
			int sickLeave, int wFHLeave, long bankAccountNumber, String bankName, String iFSC_Code, String branchName,
			String bankAddress) {
		super();
		this.employeeFirstName = employeeFirstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.gmailId = gmailId;
		this.gender = gender;
		this.address = address;
		this.joiningDate = joiningDate;
		Designation = designation;
		Department = department;
		this.type = type;
		this.basicSalary = basicSalary;
		this.medicalAllowance = medicalAllowance;
		HouseRentAllAllowance = houseRentAllAllowance;
		this.persnolAllowance = persnolAllowance;
		this.transportingAllowance = transportingAllowance;
		this.insurance = insurance;
		this.professionalTax = professionalTax;
		this.paidLeave = paidLeave;
		this.annualEave = annualEave;
		this.sickLeave = sickLeave;
		WFHLeave = wFHLeave;
		this.bankAccountNumber = bankAccountNumber;
		this.bankName = bankName;
		IFSC_Code = iFSC_Code;
		this.branchName = branchName;
		this.bankAddress = bankAddress;
	}
	public RegistrationFormData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getGmailId() {
		return gmailId;
	}
	public void setGmailId(String gmailId) {
		this.gmailId = gmailId;
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
	public Date getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation) {
		Designation = designation;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(long basicSalary) {
		this.basicSalary = basicSalary;
	}
	public long getMedicalAllowance() {
		return medicalAllowance;
	}
	public void setMedicalAllowance(long medicalAllowance) {
		this.medicalAllowance = medicalAllowance;
	}
	public long getHouseRentAllAllowance() {
		return HouseRentAllAllowance;
	}
	public void setHouseRentAllAllowance(long houseRentAllAllowance) {
		HouseRentAllAllowance = houseRentAllAllowance;
	}
	public long getPersnolAllowance() {
		return persnolAllowance;
	}
	public void setPersnolAllowance(long persnolAllowance) {
		this.persnolAllowance = persnolAllowance;
	}
	public long getTransportingAllowance() {
		return transportingAllowance;
	}
	public void setTransportingAllowance(long transportingAllowance) {
		this.transportingAllowance = transportingAllowance;
	}
	public long getInsurance() {
		return insurance;
	}
	public void setInsurance(long insurance) {
		this.insurance = insurance;
	}
	public long getProfessionalTax() {
		return professionalTax;
	}
	public void setProfessionalTax(long professionalTax) {
		this.professionalTax = professionalTax;
	}
	public int getPaidLeave() {
		return paidLeave;
	}
	public void setPaidLeave(int paidLeave) {
		this.paidLeave = paidLeave;
	}
	public int getAnnualEave() {
		return annualEave;
	}
	public void setAnnualEave(int annualEave) {
		this.annualEave = annualEave;
	}
	public int getSickLeave() {
		return sickLeave;
	}
	public void setSickLeave(int sickLeave) {
		this.sickLeave = sickLeave;
	}
	public int getWFHLeave() {
		return WFHLeave;
	}
	public void setWFHLeave(int wFHLeave) {
		WFHLeave = wFHLeave;
	}
	public long getBankAccountNumber() {
		return bankAccountNumber;
	}
	public void setBankAccountNumber(long bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIFSC_Code() {
		return IFSC_Code;
	}
	public void setIFSC_Code(String iFSC_Code) {
		IFSC_Code = iFSC_Code;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	@Override
	public String toString() {
		return "RegistrationFormData [employeeFirstName=" + employeeFirstName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", gmailId=" + gmailId + ", gender=" + gender + ", address="
				+ address + ", joiningDate=" + joiningDate + ", Designation=" + Designation + ", Department="
				+ Department + ", type=" + type + ", basicSalary=" + basicSalary + ", medicalAllowance="
				+ medicalAllowance + ", HouseRentAllAllowance=" + HouseRentAllAllowance + ", persnolAllowance="
				+ persnolAllowance + ", transportingAllowance=" + transportingAllowance + ", insurance=" + insurance
				+ ", professionalTax=" + professionalTax + ", paidLeave=" + paidLeave + ", annualEave=" + annualEave
				+ ", sickLeave=" + sickLeave + ", WFHLeave=" + WFHLeave + ", bankAccountNumber=" + bankAccountNumber
				+ ", bankName=" + bankName + ", IFSC_Code=" + IFSC_Code + ", branchName=" + branchName
				+ ", bankAddress=" + bankAddress + "]";
	}
	
	
	

}
