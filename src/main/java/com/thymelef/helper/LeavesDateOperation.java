package com.thymelef.helper;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.dao.LeavesTypeRepo;
import com.thymelef.entity.DateDetails;
import com.thymelef.entity.LeaveDetails;
import com.thymelef.entity.LeavesTypes;

@Component
public class LeavesDateOperation {
	
	@Autowired
	LeavesTypeRepo leavesTypeRepo;
	
	public static int fromDateToDateCalculate(LeaveDetails details)
	{
		
		Period period = Period.between((LocalDate)details.getFromLeaveDate().toLocalDate(),(LocalDate)details.getToLeaveDate().toLocalDate());
        int daysBetween = period.getDays() + period.getMonths() * 30 + period.getYears() * 365;
		
        return daysBetween;
	}
	
	public static String avrageOfPresenti(int presentDay,int days)
	{
		double presentDayInDouble=presentDay;
		double lastTenDayInDouble=days;
		
		double result =  (presentDayInDouble / lastTenDayInDouble )* 100 ;
	    DecimalFormat format = new DecimalFormat("0.00");
	    String resultInString=format.format(result);
        return resultInString;
	}
	
	public static String getAvrage(int days,int presentDays)
	{
		
//		Period period = Period.between((LocalDate)fromDate.toLocalDate(),(LocalDate)toDate.toLocalDate());
//        int daysBetween = period.getDays() + period.getMonths() * 30 + period.getYears() * 365;

        
        double presentDaysInDouble=presentDays;
        double daysBetweenInDouble=days;
		

        double result =(presentDaysInDouble / daysBetweenInDouble) * 100;
        DecimalFormat format = new DecimalFormat("0.00");
        String resultInString=format.format(result);
        
        return resultInString;
	}

	
	
}
