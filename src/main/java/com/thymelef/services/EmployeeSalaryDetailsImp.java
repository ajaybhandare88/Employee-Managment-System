package com.thymelef.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.dao.EmployeeSalaryRepo;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.entity.EmployeeSalaryUpdatAdminPage;

@Component
public class EmployeeSalaryDetailsImp {
	
	@Autowired
	private EmployeeSalaryRepo employeeSalaryRepo;
	
	public boolean updateSalaryDetails(EmployeeSalaryUpdatAdminPage employeeSalaryDetails,int EmployeeId)
	{
		
		EmployeeSalary empSalary=this.employeeSalaryRepo.findByEmployeeId(EmployeeId);
		empSalary.setBasicSalary(employeeSalaryDetails.getBasicSalary());
		empSalary.setMedicalAllowance(employeeSalaryDetails.getMedicalAllowance());
		empSalary.setHouseRentAllAllowance(employeeSalaryDetails.getHouseRentAllAllowance());
		empSalary.setPersnolAllowance(employeeSalaryDetails.getPersnolAllowance());
		empSalary.setTransportingAllowance(employeeSalaryDetails.getTransportingAllowance());
		empSalary.setInsurance(employeeSalaryDetails.getInsurance());
		empSalary.setProfessionalTax(employeeSalaryDetails.getProfessionalTax());
		
		try {
			this.employeeSalaryRepo.save(empSalary);
			return true;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

}
