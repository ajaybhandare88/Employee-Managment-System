package com.thymelef.helper;

import java.sql.Date;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.entity.SalaryMonth;
import com.thymelef.services.EmployeeServicesImp;

public class MonthlySalaryCalculate {
	
	
	public static Employee NetSalary(List<DateDetails> dateDetails,EmployeeSalary salaryDetails,Employee employee,YearMonth date)
	{
		long BasicSalary=0;
		
		for(int i=0; i<dateDetails.size(); i++)
		{
			BasicSalary+=dateDetails.get(i).getDayTotal();
		}
		
		long GrossSalary=BasicSalary+salaryDetails.getHouseRentAllAllowance()
					  +salaryDetails.getInsurance()
					  +salaryDetails.getMedicalAllowance()
					  +salaryDetails.getPersnolAllowance()
					  +salaryDetails.getTransportingAllowance();
		
		long ProffessionalTax=salaryDetails.getProfessionalTax();
		
		long NetSalary=GrossSalary-ProffessionalTax;
		
		List<SalaryMonth> salaryMonthList=new ArrayList<SalaryMonth>();
		
		SalaryMonth salaryMonth=new SalaryMonth();
		salaryMonth.setSalaryDate(date);
		salaryMonth.setBasicSalary(BasicSalary);
		salaryMonth.setMedicalAllowance(salaryDetails.getMedicalAllowance());
		salaryMonth.setLeavesDeduction(0);
		salaryMonth.setOtherDeduction(0);
		salaryMonth.setOverTimeTotal(0);
		salaryMonth.setHRA(salaryDetails.getHouseRentAllAllowance());
		salaryMonth.setTransportingAllowance(salaryDetails.getTransportingAllowance());
		salaryMonth.setInsurance(salaryDetails.getInsurance());
		salaryMonth.setTDS(0);
		salaryMonth.setProvidentFund(0);
		salaryMonth.setProfessionalTax(ProffessionalTax);
		salaryMonth.setGrossEarning(GrossSalary);
		salaryMonth.setNetSalary(NetSalary);
		salaryMonth.setEmployee(employee);
		salaryMonthList.add(salaryMonth);
		
		try {
			employee.setSalaryMonths(salaryMonthList);
			return employee;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return employee;
	}

}
