package com.thymelef.helper;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.entity.SalaryMonth;

public class SalaryReleaseAllEmp {
	
	public static Employee SalaryRelease(Employee employee,YearMonth month,List<DateDetails> presentEmployee)
	{
			
			try {
			if(employee.getType().equals("USER"))
			{
			
			EmployeeSalary employeeSalary=employee.getEmployeeSalary();
			
			Employee returnEmployee=MonthlySalaryCalculate.NetSalary(presentEmployee, employeeSalary,employee,month);
			return returnEmployee;
			}
			}catch (Exception e) {
				e.printStackTrace();
			}

			return employee;
	}
	
	public static boolean monthSalaryReleaseCheack(Employee employee,YearMonth salaryMonth)
	{
		
		for(int i=0; i<employee.getSalaryMonths().size(); i++) {
		if(employee.getSalaryMonths().get(i).getSalaryDate().equals(salaryMonth)) {
			return false;
		}
		}
		return true;
	}
	
	public static long totalReleaseSalaryTotal(List<Employee> employee)
	{
		long netAmout=0;
		
		for(int i=0; i<employee.size(); i++)
		{
			netAmout += employee.get(i).getSalaryMonths().getLast().getNetSalary();
		}
		return netAmout;
	}
	
	public static long totalReleaseSalaryNetAmoutTotal(List<SalaryMonth> salaryMonths)
	{
		long netAmout=0;
		
		for(int i=0; i<salaryMonths.size(); i++)
		{
			netAmout += salaryMonths.get(i).getNetSalary();
		}
		return netAmout;
	}
	
	public static List<SalaryMonth> totalReleaseSalarySalaryMonth(List<Employee> employee)
	{

		List<SalaryMonth> salaryReleaseEmployeeDetailsList=new ArrayList<SalaryMonth>();
		
		for(int i=0; i<employee.size(); i++)
		{
			salaryReleaseEmployeeDetailsList.add(employee.get(i).getSalaryMonths().getLast());	
		}
		return salaryReleaseEmployeeDetailsList;
	}
	
	
}
