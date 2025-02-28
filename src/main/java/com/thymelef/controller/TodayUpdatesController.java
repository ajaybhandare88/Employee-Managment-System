package com.thymelef.controller;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;
import com.thymelef.helper.CurrentMonthAndYear;
import com.thymelef.helper.LeavesDateOperation;
import com.thymelef.helper.PresentAbsentFilter;
import com.thymelef.helper.WorkingHourOpration;
import com.thymelef.messagesBox.RegistrationMessage;
import com.thymelef.services.DateDetailsServicesImp;
import com.thymelef.services.EmployeeServicesImp;

@Controller
@RequestMapping("/admin/today")
public class TodayUpdatesController {
	
	@Autowired
	DateDetailsServicesImp dateDetailsServicesImp;
	
	@Autowired
	EmployeeServicesImp employeeServicesImp;
	
	@RequestMapping("/updates")
	public String todayUpdate(Model model)
	{
		
		List<DateDetails> dateDetails=dateDetailsServicesImp.getDateDetailsByDate(CurrentMonthAndYear.sqlDate());
		
		if(dateDetails==null)
		{
			return "AdminPages/TodayUpdatePage";
		}
		
		List<DateDetails> todayPresentEmployee=PresentAbsentFilter.presentEmployeeQuentity(dateDetails);
		List<DateDetails> todayAbsentEmployee=PresentAbsentFilter.absentEmployeeQuentity(dateDetails);
		
		List<DateDetails> latePunchingEmployee=WorkingHourOpration.latePunching(dateDetails);
		List<DateDetails> overTimeWorkingEmployee=WorkingHourOpration.overTimeEmployeeOnly(dateDetails);
		List<DateDetails> halfDayWorkingTotal=WorkingHourOpration.halfDayWorkingEmployee(dateDetails);
		
		model.addAttribute("todayPresentEmployee",todayPresentEmployee.size());
		model.addAttribute("todayAbsentEmployee", todayAbsentEmployee.size());
		model.addAttribute("latePunchingEmployee", latePunchingEmployee.size());
		model.addAttribute("overTimeWorkingEmployee",overTimeWorkingEmployee.size());
		model.addAttribute("halfDayWorkingTotal",halfDayWorkingTotal.size());
		
		
		return "AdminPages/TodayUpdatePage";
	}
	
	@RequestMapping("/InOutService")
	public String InOutServices()
	{
		return "AdminPages/InOutServices";
	}
	
	@RequestMapping("/searchEmployee")
	public String getEmployeeForPunching(@RequestParam("id") int id,@RequestParam("date") Date date,Model model)throws Exception
	{
		DateDetails inOutTime=dateDetailsServicesImp.getDateDetailsByDate(id, date);
		
		if(inOutTime==null)
		{
			RegistrationMessage message=new RegistrationMessage("Data Not found", "danger");
			model.addAttribute("sucess", message);
			return "AdminPages/InOutServices";
		}
		
		try {
			model.addAttribute("inTime",inOutTime.getInTime().toString());
			model.addAttribute("employee", inOutTime.getEmployee());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			model.addAttribute("outTime", inOutTime.getOutTime().toString());
			model.addAttribute("employeeId", inOutTime.getEmployee().getId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("date", date);
		
		return "AdminPages/InOutServices";
	}
	
	@RequestMapping(value="/updatePunchingTime", method = RequestMethod.POST)
	public String updatePunchingTiming(@RequestParam("intime") LocalTime inTime,
									   @RequestParam("outtime") LocalTime outTime,
									   @RequestParam("id") int id,
									   @RequestParam("date") Date date,Model model)
	{
		DateDetails inOutTime=dateDetailsServicesImp.getDateDetailsByDate(id, date);
		inOutTime.setInTime(inTime);
		inOutTime.setOutTime(outTime);
		try {
		dateDetailsServicesImp.save(inOutTime);
		RegistrationMessage message=new RegistrationMessage("Time Update successfully", "danger");
		model.addAttribute("sucess", message);
		return "redirect:/admin/today/searchEmployee?id="+id+"&date="+date;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/admin/today/searchEmployee?id="+id+"&date="+date;
	}

}
