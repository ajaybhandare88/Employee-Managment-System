package com.thymelef.services;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.dao.DateDetailsRepo;
import com.thymelef.dao.EmployeeRepo;
import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.helper.WorkingHourOpration;

@Component
public class DateDetailsServicesImp {

	@Autowired
	private DateDetailsRepo dateDetailsRepo;

	@Autowired
	private EmployeeRepo employeeRepo;
	

	public DateDetails save(DateDetails dateDetails) {
		return this.dateDetailsRepo.save(dateDetails);
	}

	public java.util.List<DateDetails> DetailsByDateWise(int employeeId, Date startDate, Date endDate) {
		List<DateDetails> dateDetails = dateDetailsRepo.findByDetailsCreatedBetween(employeeId, startDate, endDate);
		return dateDetails;
	}
	
	public java.util.List<DateDetails> DetailsByDateWise( Date startDate, Date endDate) {
		List<DateDetails> dateDetails = dateDetailsRepo.findByDetailsCreatedBetween(startDate, endDate);
		return dateDetails;
	}
	
	//present employee month data get
	public List<DateDetails> getOnlyPresent(int empId, int year, int month) {
		List<DateDetails> dateDeatilsData = dateDetailsRepo.ifPresent(empId, year, month);
		return dateDeatilsData;
	}
	
	//Notpresent employee month data get
	public List<DateDetails> NotPresentDetails(int empId, int year, int month) {
		List<DateDetails> dateDeatilsData = dateDetailsRepo.ifNotPresent(empId, year, month);
		return dateDeatilsData;
	}

	public DateDetails getDateDetailsByDate(int employeeId, Date date) {

		return dateDetailsRepo.findUsingDate(employeeId, date);
	}

	public List<DateDetails> lastTenResult(int employeeId) {
		List<DateDetails> result = dateDetailsRepo.lastTenResult(employeeId);
		return result;
	}

	public boolean currentDateDataPresent(int employeeID, Date date) {
		boolean result = false;
		try {
			DateDetails currentDateData = this.dateDetailsRepo.currentDateDataGet(employeeID, date);

			if (currentDateData != null) {
				result = true;
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean inPunching(int employeeID,Date date) {
		boolean result=false;
		
		DateDetails d = new DateDetails();
		LocalTime time = LocalTime.now();
		Employee emp = employeeRepo.findById(employeeID);

		d.setDate(date);
		d.setInTime(time);
		d.setPresent(true);
		d.setLeaves(false);
		d.setEmployee(emp);
		
		try {
				dateDetailsRepo.save(d);
				result=true;
		}catch (Exception e) {
				e.printStackTrace();
		}
	
		return result;
	}
	
	
	public boolean outPunching(int employeeID,Date date) {
		boolean result=false;
		
		DateDetails d = new DateDetails();
		LocalTime time = LocalTime.now();
		Employee emp = employeeRepo.findById(employeeID);
		DateDetails currentDateData = this.dateDetailsRepo.currentDateDataGet(employeeID, date);
		currentDateData.setOutTime(time);
		Duration workingDuration = Duration.between(currentDateData.getInTime(), currentDateData.getOutTime());
		currentDateData.setHalfDay(WorkingHourOpration.halfDay(workingDuration));
		currentDateData.setOverTime(WorkingHourOpration.overTime(workingDuration));
		currentDateData.setDayTotal(WorkingHourOpration.dayTotal(workingDuration, emp));
		
		try {
				dateDetailsRepo.save(currentDateData);
				result=true;
		}catch (Exception e) {
				e.printStackTrace();
		}
	
		return result;
	}
	
	public List<DateDetails> getDateDetailsByDate(Date date) {

		List<DateDetails> listOfcurrentDateData=dateDetailsRepo.findUsingByDate(date);
		return listOfcurrentDateData;
	}
	
	public List<DateDetails> currentMonthData(int month)
	{
		try {
		List<DateDetails> allCurrentMonthEmployee=dateDetailsRepo.currentMonthData(month);
		return allCurrentMonthEmployee;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
