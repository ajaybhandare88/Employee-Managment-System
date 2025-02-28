package com.thymelef.entity;

import java.time.Month;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class EmployeeSalary {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private long basicSalary;
	private long medicalAllowance;
	private long HouseRentAllAllowance;
	private long persnolAllowance;
	private long transportingAllowance;
	private long insurance;
	private long professionalTax;
	
	@OneToOne
	@JsonBackReference
	private Employee employee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "EmployeeSalary [id=" + id + ", basicSalary=" + basicSalary + ", medicalAllowance=" + medicalAllowance
				+ ", HouseRentAllAllowance=" + HouseRentAllAllowance 
				+ ", persnolAllowance=" + persnolAllowance + ", transportingAllowance=" + transportingAllowance
				+ ", insurance=" + insurance + ", professionalTax="
				+ professionalTax + ", employee="
				+ employee + "]";
	}
	
	
	

}
