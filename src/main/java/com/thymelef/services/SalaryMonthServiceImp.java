package com.thymelef.services;

import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.dao.SalaryMonthRepo;
import com.thymelef.entity.SalaryMonth;

@Component
public class SalaryMonthServiceImp {
	
	@Autowired
	private SalaryMonthRepo monthRepo;

	public long findNetSalary(int employeeId,YearMonth date)
	{
		long netSalary=0;
		try {
			netSalary=monthRepo.getNetSalary(employeeId,date);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return netSalary;
		
	}
	
	public List<SalaryMonth> SalaryMonthListSearchBy(YearMonth date)
	{
		return monthRepo.salaryMonthSearchDateWise(date);
	}
	
}
