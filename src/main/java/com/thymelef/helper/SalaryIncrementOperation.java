package com.thymelef.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.dao.EmployeeRepo;
import com.thymelef.dao.EmployeeSalaryRepo;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeSalary;

@Component
public class SalaryIncrementOperation {

	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	EmployeeSalaryRepo employeeSalaryRepo;

	public List<EmployeeSalary> increment(long amount, String payment, String department, String designation, String role) {
		List<EmployeeSalary> employeeSalaryUpdationList=new ArrayList<EmployeeSalary>();
	
		if (payment.equals("Less 10000")) {
			
			try {
			List<EmployeeSalary> empSalary=employeeSalaryRepo.findByBasicSalaryLessThan(10000l,department,designation,role);
			empSalary.forEach((emp)->{
				long basicSalary=emp.getBasicSalary();
				emp.setBasicSalary(basicSalary+amount);
				employeeSalaryRepo.save(emp);
				employeeSalaryUpdationList.add(emp);
			});
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return employeeSalaryUpdationList;
		}
		
		if (payment.equals("40k > 80k")) {
			try {
				List<EmployeeSalary> empSalary=employeeSalaryRepo.findByBasicSalaryBetween(40000l,80000l,department,designation,role);
				empSalary.forEach((emp)->{
					long basicSalary=emp.getBasicSalary();
					emp.setBasicSalary(basicSalary+amount);
					employeeSalaryRepo.save(emp);
					employeeSalaryUpdationList.add(emp);
				});
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				return employeeSalaryUpdationList;
		}
		
		if (payment.equals("Up 10000")) {
			try {
				List<EmployeeSalary> empSalary=employeeSalaryRepo.findByBasicSalaryMoreThan(10000l,department,designation,role);
				empSalary.forEach((emp)->{
					long basicSalary=emp.getBasicSalary();
					emp.setBasicSalary(basicSalary+amount);
					employeeSalaryRepo.save(emp);
					employeeSalaryUpdationList.add(emp);
				});
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
				return employeeSalaryUpdationList;
			
		}
		
		if (payment.equals("80k < 1.50 lakh")) {
			try {
				List<EmployeeSalary> empSalary=employeeSalaryRepo.findByBasicSalaryBetween(80000l,150000l,department,designation,role);
				empSalary.forEach((emp)->{
					long basicSalary=emp.getBasicSalary();
					emp.setBasicSalary(basicSalary+amount);
					employeeSalaryRepo.save(emp);
					employeeSalaryUpdationList.add(emp);
				});
				}catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		if (payment.equals("all")) {
			try {
				List<EmployeeSalary> empSalary=employeeSalaryRepo.findAll();
				empSalary.forEach((emp)->{
					long basicSalary=emp.getBasicSalary();
					emp.setBasicSalary(basicSalary+amount);
					employeeSalaryRepo.save(emp);
					employeeSalaryUpdationList.add(emp);
				});
				}catch(Exception e)
				{
					e.printStackTrace();
				}
		}
		return employeeSalaryUpdationList;

	}
}
