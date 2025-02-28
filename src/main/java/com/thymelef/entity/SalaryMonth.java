package com.thymelef.entity;

import java.sql.Date;
import java.time.Month;
import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class SalaryMonth {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private YearMonth salaryDate;
	private long basicSalary;
	private long medicalAllowance;
	private long leavesDeduction;
	private long otherDeduction;
	private long overTimeTotal;
	private long HRA;
	private long transportingAllowance;
	private long insurance;
	private long TDS;
	private long providentFund;
	private long professionalTax;
	private long grossEarning;
	private long netSalary;

	@ManyToOne
	@JsonBackReference
	private Employee employee;

	public SalaryMonth(int id, YearMonth salaryDate, long basicSalary, long medicalAllowance, long leavesDeduction,
			long otherDeduction, long overTimeTotal, long hRA, long transportingAllowance, long insurance, long tDS,
			long providentFund, long professionalTax, long grossEarning, long netSalary, Employee employee) {
		super();
		this.id = id;
		this.salaryDate = salaryDate;
		this.basicSalary = basicSalary;
		this.medicalAllowance = medicalAllowance;
		this.leavesDeduction = leavesDeduction;
		this.otherDeduction = otherDeduction;
		this.overTimeTotal = overTimeTotal;
		HRA = hRA;
		this.transportingAllowance = transportingAllowance;
		this.insurance = insurance;
		TDS = tDS;
		this.providentFund = providentFund;
		this.professionalTax = professionalTax;
		this.grossEarning = grossEarning;
		this.netSalary = netSalary;
		this.employee = employee;
	}

	public SalaryMonth() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public YearMonth getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(YearMonth salaryDate) {
		this.salaryDate = salaryDate;
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

	public long getLeavesDeduction() {
		return leavesDeduction;
	}

	public void setLeavesDeduction(long leavesDeduction) {
		this.leavesDeduction = leavesDeduction;
	}

	public long getOtherDeduction() {
		return otherDeduction;
	}

	public void setOtherDeduction(long otherDeduction) {
		this.otherDeduction = otherDeduction;
	}

	public long getOverTimeTotal() {
		return overTimeTotal;
	}

	public void setOverTimeTotal(long overTimeTotal) {
		this.overTimeTotal = overTimeTotal;
	}

	public long getHRA() {
		return HRA;
	}

	public void setHRA(long hRA) {
		HRA = hRA;
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

	public long getTDS() {
		return TDS;
	}

	public void setTDS(long tDS) {
		TDS = tDS;
	}

	public long getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(long providentFund) {
		this.providentFund = providentFund;
	}

	public long getProfessionalTax() {
		return professionalTax;
	}

	public void setProfessionalTax(long professionalTax) {
		this.professionalTax = professionalTax;
	}

	public long getGrossEarning() {
		return grossEarning;
	}

	public void setGrossEarning(long grossEarning) {
		this.grossEarning = grossEarning;
	}

	public long getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(long netSalary) {
		this.netSalary = netSalary;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "SalaryMonth [id=" + id + ", salaryDate=" + salaryDate + ", basicSalary=" + basicSalary
				+ ", medicalAllowance=" + medicalAllowance + ", leavesDeduction=" + leavesDeduction
				+ ", otherDeduction=" + otherDeduction + ", overTimeTotal=" + overTimeTotal + ", HRA=" + HRA
				+ ", transportingAllowance=" + transportingAllowance + ", insurance=" + insurance + ", TDS=" + TDS
				+ ", providentFund=" + providentFund + ", professionalTax=" + professionalTax + ", grossEarning="
				+ grossEarning + ", netSalary=" + netSalary + ", employee=" + employee + "]";
	}

	

	
	
	
}
