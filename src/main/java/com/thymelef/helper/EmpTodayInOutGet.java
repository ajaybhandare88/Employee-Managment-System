package com.thymelef.helper;

import java.sql.Date;
import java.time.LocalDate;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;

public class EmpTodayInOutGet {
	
	public static DateDetails todayInOut(Employee employee)
	{
		if(employee.getDateDetails().getLast().getDate().equals(CurrentMonthAndYear.formatedCurrentDMY()))
				{
					return employee.getDateDetails().getLast();
				}
		
		return null;
		
	}

}
