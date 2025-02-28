package com.thymelef.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long bankAccountNumber;
	private String bankName;
	private String IFSC_Code;
	private String branchName;
	private String bankAddress;
	
	@OneToOne
	private Employee employee;

	public BankAccount(int id, long bankAccountNumber, String bankName, String iFSC_Code, String branchName,
			String bankAddress, Employee employee) {
		super();
		this.id = id;
		this.bankAccountNumber = bankAccountNumber;
		this.bankName = bankName;
		IFSC_Code = iFSC_Code;
		this.branchName = branchName;
		this.bankAddress = bankAddress;
		this.employee = employee;
	}

	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", bankAccountNumber=" + bankAccountNumber + ", bankName=" + bankName
				+ ", IFSC_Code=" + IFSC_Code + ", branchName=" + branchName + ", bankAddress=" + bankAddress
				+ ", employee=" + employee + "]";
	}
	
	
	
	
	

}
