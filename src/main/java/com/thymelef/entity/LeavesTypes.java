package com.thymelef.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class LeavesTypes {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int paidLeave;
	private int annualEave;
	private int sickLeave;
	private int WFHLeave;
	
	
	@OneToOne
	@JsonBackReference
	private Employee employee;


	public LeavesTypes(int id, int paidLeave, int annualEave, int sickLeave, int wFHLeave, Employee employee) {
		super();
		this.id = id;
		this.paidLeave = paidLeave;
		this.annualEave = annualEave;
		this.sickLeave = sickLeave;
		WFHLeave = wFHLeave;
		this.employee = employee;
	}

	public LeavesTypes() {
		super();
		// TODO Auto-generated constructor stub
	}





	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	@Override
	public String toString() {
		return "LeavesTypes [id=" + id + ", paidLeave=" + paidLeave + ", annualEave=" + annualEave + ", sickLeave="
				+ sickLeave + ", WFHLeave=" + WFHLeave + ", employee=" + employee + "]";
	}
	
	

}
