package com.thymelef.entity;

public class EmployeeSalaryUpdatAdminPage {
	
	private long basicSalary;
	private long medicalAllowance;
	private long HouseRentAllAllowance;
	private long persnolAllowance;
	private long transportingAllowance;
	private long insurance;
	private long professionalTax;
	
	public EmployeeSalaryUpdatAdminPage(long basicSalary, long medicalAllowance, long houseRentAllAllowance,
			long persnolAllowance, long transportingAllowance, long insurance, long professionalTax) {
		super();
		this.basicSalary = basicSalary;
		this.medicalAllowance = medicalAllowance;
		HouseRentAllAllowance = houseRentAllAllowance;
		this.persnolAllowance = persnolAllowance;
		this.transportingAllowance = transportingAllowance;
		this.insurance = insurance;
		this.professionalTax = professionalTax;
	}
	public EmployeeSalaryUpdatAdminPage() {
		super();
		// TODO Auto-generated constructor stub
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
	@Override
	public String toString() {
		return "EmployeeSalaryUpdatAdminPage [basicSalary=" + basicSalary + ", medicalAllowance=" + medicalAllowance
				+ ", HouseRentAllAllowance=" + HouseRentAllAllowance + ", persnolAllowance=" + persnolAllowance
				+ ", transportingAllowance=" + transportingAllowance + ", insurance=" + insurance + ", professionalTax="
				+ professionalTax + "]";
	}
	
	

}
