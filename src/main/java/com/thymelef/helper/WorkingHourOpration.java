package com.thymelef.helper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;

public class WorkingHourOpration {
	
	public static boolean halfDay(Duration duration)
	{
		boolean result=false;
		
		if(duration.toHours() <= 3)
		{
			result=true;
			return result;
		}
		
		return result;
	}
	
	public static int overTime(Duration duration)
	{
		int overTime=0;
		
		if(duration.toHours() > 8) {
		overTime=(int) (duration.toHours()-8);
		return overTime;
		}
		return overTime;
	}

	public static int dayTotal(Duration duration,Employee emp)
	{
		int total=0;
		
		int oneHour=((int)emp.getEmployeeSalary().getBasicSalary() / 28 ) / 8;
		total=oneHour*(int)duration.toHours();
		return total;
	}
	
//	late punching days
	public static List<DateDetails> latePunching(List<DateDetails> date)
	{
		List<DateDetails> latePunchDayData=new ArrayList<DateDetails>();
		for(int i=0; i<date.size(); i++)
		{
			if(date.get(i).getInTime().isAfter(LocalTime.parse("10:00:00.000000"))
					)
			{
				latePunchDayData.add(date.get(i));
			}
		}
		return latePunchDayData;
	}
	
//	Overtime employee sending only
	public static List<DateDetails> overTimeEmployeeOnly(List<DateDetails> date)
	{
		List<DateDetails> overTimeEmp=new ArrayList<DateDetails>();
		for(int i=0; i<date.size(); i++)
		{
			
			if(date.get(i).getOverTime()>0)
			{
				overTimeEmp.add(date.get(i));
			}
		}
		return overTimeEmp;
	}
	
//	HalfDay working employee sending
	public static List<DateDetails> halfDayWorkingEmployee(List<DateDetails> date)
	{
		List<DateDetails> HalfDayWorkingEmp=new ArrayList<DateDetails>();
		for(int i=0; i<date.size(); i++)
		{
			
			if(date.get(i).isHalfDay()==true)
			{
				HalfDayWorkingEmp.add(date.get(i));
			}
		}
		return HalfDayWorkingEmp;
	}
	
}
