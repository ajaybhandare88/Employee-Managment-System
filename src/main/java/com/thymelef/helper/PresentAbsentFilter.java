package com.thymelef.helper;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import com.thymelef.entity.DateDetails;

public class PresentAbsentFilter {
	
	public static List<DateDetails> presentEmployeeQuentity(List<DateDetails> dateDetails)
	{
		List<DateDetails> present=new ArrayList<DateDetails>();
		
		for(int i=0; i<dateDetails.size(); i++)
		{
			
			if(dateDetails.get(i).isPresent()==true)
			{
			present.add(dateDetails.get(i));
			}
		}
		return present;
	}
	
	public static List<DateDetails> absentEmployeeQuentity(List<DateDetails> dateDetails)
	{
		List<DateDetails> absent=new ArrayList<DateDetails>();
		
		for(int i=0; i<dateDetails.size(); i++)
		{
			if(dateDetails.get(i).isLeaves()==true)
			{
				absent.add(dateDetails.get(i));
			}
		}
		
		return absent;
	}
	
	
	public static int totalEarningAroundFilterDates(List<DateDetails> dateDetails)
	{
		int earning=0;
		
		for(int i=0; i<dateDetails.size(); i++)
		{
			
				earning+=dateDetails.get(i).getDayTotal();
				System.out.println(dateDetails.get(i).getDayTotal());
		}
		
		return earning;
	}
	
	public static List<DateDetails> presentEmployeeQuentityMonthWise(List<DateDetails> dateDetails,YearMonth month)
	{
		List<DateDetails> present=new ArrayList<DateDetails>();
		
		for(int i=0; i<dateDetails.size(); i++)
		{
			
			if(dateDetails.get(i).isPresent()==true && dateDetails.get(i).getDate().equals(month))
			{
			present.add(dateDetails.get(i));
			}
		}
		return present;
	}

}
