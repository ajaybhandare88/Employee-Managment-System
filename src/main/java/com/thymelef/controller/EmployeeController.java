package com.thymelef.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.thymelef.entity.BankAccount;
import com.thymelef.entity.DateDetails;
import com.thymelef.entity.EditEmployeeData;
import com.thymelef.entity.Employee;
import com.thymelef.entity.LeaveDetails;
import com.thymelef.entity.LeavesTypes;
import com.thymelef.entity.Resign;
import com.thymelef.helper.CurrentMonthAndYear;
import com.thymelef.helper.GetIdUser;
import com.thymelef.helper.LeavesDateOperation;
import com.thymelef.helper.PresentAbsentFilter;
import com.thymelef.messagesBox.RegistrationMessage;
import com.thymelef.services.BankAccountDetailsService;
import com.thymelef.services.DateDetailsServicesImp;
import com.thymelef.services.EmployeeServicesImp;
import com.thymelef.services.LeaveDetailsImp;
import com.thymelef.services.LeavesTypeImp;
import com.thymelef.services.SalaryMonthServiceImp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeServicesImp employeeServicesImp;

	@Autowired
	DateDetailsServicesImp dateDetailsServicesImp;

	@Autowired
	SalaryMonthServiceImp salaryMonthimp;

	@Autowired
	LeaveDetailsImp leaveDetailsImp;

	@Autowired
	LeavesTypeImp leavesTypeImp;
	
	@Autowired
	BankAccountDetailsService accountDetailsService;

	@RequestMapping("/dashbord")
	public String dashbordPage(Model model, Authentication emp) throws Exception {

		int EmployeeId1 = GetIdUser.userid(emp);

		List<DateDetails> sizeOfpresentEmployee = dateDetailsServicesImp.getOnlyPresent(EmployeeId1,
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentYear())),
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentMonth())));
		List<DateDetails> sizeOfAbsentEmployee = dateDetailsServicesImp.NotPresentDetails(EmployeeId1,
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentYear())),
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentMonth())));

		DateDetails dateDetails = dateDetailsServicesImp.getDateDetailsByDate(EmployeeId1,
				CurrentMonthAndYear.formatedCurrentDMY());

		long netSalary = salaryMonthimp.findNetSalary(EmployeeId1,YearMonth.now());

		model.addAttribute("defaultMonthData", sizeOfpresentEmployee.size());
		model.addAttribute("defaultMonthDataabsent", sizeOfAbsentEmployee.size());
		model.addAttribute("defaultMonthDataPresentList", sizeOfpresentEmployee);
		model.addAttribute("defaultMonthDataAbsentList", sizeOfAbsentEmployee);
		model.addAttribute("defaultMonthAndYear",
				CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentMonth()) + "-"
						+ CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentYear()));
		model.addAttribute("NetSalary", netSalary);
		model.addAttribute("FormatedCurrentDate", CurrentMonthAndYear.formatedCurrentDMY().toString());
		model.addAttribute("AvragePresenti", LeavesDateOperation.avrageOfPresenti(sizeOfpresentEmployee.size(),sizeOfpresentEmployee.size()+sizeOfAbsentEmployee.size()));
		try {
			model.addAttribute("currentDayDetailsInPunch",
					dateDetails.getInTime().format(CurrentMonthAndYear.timeFormatter()));
		} catch (Exception e) {
			model.addAttribute("currentDayDetailsInPunch", "Not Punch");
		}
		
		try {
		
			model.addAttribute("currentDayDetailsOutPunch",
					dateDetails.getOutTime().format(CurrentMonthAndYear.timeFormatter()));
		} catch (Exception e) {
			model.addAttribute("currentDayDetailsOutPunch", "Not Punch");
		}
		
		return "Employee/Dashbord";

	}

	@RequestMapping("/attendance")
	public String attendancePage(Authentication authentication, Model model) throws Exception {

		int EmployeeId1 = GetIdUser.userid(authentication);

		List<DateDetails> tenLstResult = dateDetailsServicesImp.lastTenResult(EmployeeId1);
		
		model.addAttribute("LastTenDaysData", "Last "+tenLstResult.size()+" Days");
		model.addAttribute("DateWiseData", tenLstResult);
		model.addAttribute("FilterDatePresentEmp", PresentAbsentFilter.presentEmployeeQuentity(tenLstResult).size());
		model.addAttribute("FilterDateAbsentEmp", PresentAbsentFilter.absentEmployeeQuentity(tenLstResult).size());
		model.addAttribute("defaultMonthDataPresentList", PresentAbsentFilter.presentEmployeeQuentity(tenLstResult));
		model.addAttribute("defaultMonthDataAbsentList", PresentAbsentFilter.absentEmployeeQuentity(tenLstResult));
		model.addAttribute("FilterDateTotalEarning", PresentAbsentFilter.totalEarningAroundFilterDates(tenLstResult));
		model.addAttribute("AvragePresenti", LeavesDateOperation.avrageOfPresenti(PresentAbsentFilter.presentEmployeeQuentity(tenLstResult).size(),tenLstResult.size()));
		return "Employee/AttendanceDetailsPage";
	}

	@RequestMapping("/profile")
	public String sidebarPage() throws Exception {
		return "Employee/ProfilePage";
	}

	@RequestMapping(value = "/profileformSubmit", method = RequestMethod.POST)
	public String profileUpdatFormData(@ModelAttribute EditEmployeeData data) {
		employeeServicesImp.updateEmployeeDetails(data);
		return "Employee/Dashbord";
	}

	@RequestMapping("/filterDateWise")
	public String filterDateWise(@RequestParam("fromDate") Date fromDate, @RequestParam("toDate") Date toDate,
			Authentication emp, Model model, HttpServletRequest request) throws Exception {

		int EmployeeId1 = GetIdUser.userid(emp);

		List<DateDetails> dateDetails = dateDetailsServicesImp.DetailsByDateWise(EmployeeId1, fromDate, toDate);

		model.addAttribute("DateWiseData", dateDetails);
		model.addAttribute("SearchDatefrom", fromDate.toString());
		model.addAttribute("SearchDateto", "To    " + toDate.toString());

		model.addAttribute("FilterDatePresentEmp", PresentAbsentFilter.presentEmployeeQuentity(dateDetails).size());
		model.addAttribute("FilterDateAbsentEmp", PresentAbsentFilter.absentEmployeeQuentity(dateDetails).size());

		model.addAttribute("FilterDateTotalEarning", PresentAbsentFilter.totalEarningAroundFilterDates(dateDetails));

		model.addAttribute("defaultMonthDataPresentList", PresentAbsentFilter.presentEmployeeQuentity(dateDetails));
		model.addAttribute("defaultMonthDataAbsentList", PresentAbsentFilter.absentEmployeeQuentity(dateDetails));
		
		model.addAttribute("AvragePresenti", LeavesDateOperation.getAvrage(dateDetails.size(), PresentAbsentFilter.presentEmployeeQuentity(dateDetails).size()));

		
		return "Employee/AttendanceDetailsPage";
	}

	@RequestMapping("/salaryDetails")
	public String salryDetailsPage(Authentication auth,Model model)throws Exception
	{
		
		int employeeId = GetIdUser.userid(auth);
		
		BankAccount bankAccount=accountDetailsService.getBankDeatilsByEmpId(employeeId);
		
		model.addAttribute("loginUserBankDetails", bankAccount);
		
		return "Employee/MonthSalaryDetails";
	}

	@RequestMapping("/leaveApplication")
	public String leaveApplicationPage(Authentication auth, Model model) throws Exception {

		int employeeId = GetIdUser.userid(auth);
		LeavesTypes leavesTypes = leavesTypeImp.getLeavesType(employeeId);

		if (leavesTypes != null) {
			model.addAttribute("leavesType", leavesTypes);
			return "Employee/LeaveApplication";
		}

		return "Employee/LeaveApplication";
	}

	@RequestMapping(value = "/leaveformSubmit", method = RequestMethod.POST)
	public String leaveFormSubmit(@ModelAttribute LeaveDetails leaveApplicationFormData, Authentication auth,
			Model model) throws Exception {

		int EmployeeId1 = GetIdUser.userid(auth);

		Employee employee = employeeServicesImp.findById(EmployeeId1);
		LeavesTypes leavesTypes = leavesTypeImp.getLeavesType(EmployeeId1);
		leaveApplicationFormData.setEmployee(employee);

		boolean result = leaveDetailsImp.saveLeaveDetails(leaveApplicationFormData,
				LeavesDateOperation.fromDateToDateCalculate(leaveApplicationFormData),
				leaveApplicationFormData.getLeaveType(),EmployeeId1);

		if (result == true) {
			RegistrationMessage sms = new RegistrationMessage("your leave request submitted sucessfully", "success");
			model.addAttribute("sucess", sms);
			model.addAttribute("leavesType", leavesTypes);
			return "Employee/LeaveApplication";
		}
		model.addAttribute("leavesType", leavesTypes);
		RegistrationMessage sms = new RegistrationMessage("Leaves Day select around available Leaves ", "danger");
		model.addAttribute("sucess", sms);

		return "Employee/LeaveApplication";
	}

	@RequestMapping("/filterMonth")
	public String filterMonthData(@RequestParam("date") YearMonth date ,
			Authentication emp, Model model) throws Exception {

		int EmployeeId1 = GetIdUser.userid(emp);

		List<DateDetails> sizeOfpresentEmployee = dateDetailsServicesImp.getOnlyPresent(EmployeeId1,
				date.getYear(), date.getMonthValue());
		List<DateDetails> sizeOfAbsentEmployee = dateDetailsServicesImp.NotPresentDetails(EmployeeId1,
				date.getYear(), date.getMonthValue());

		long netSalary = salaryMonthimp.findNetSalary(EmployeeId1, date);

		model.addAttribute("defaultMonthDataPresentList", sizeOfpresentEmployee);
		model.addAttribute("defaultMonthDataAbsentList", sizeOfAbsentEmployee);
		model.addAttribute("sizeOfPresentEmpInMonth", sizeOfpresentEmployee.size());
		model.addAttribute("sizeOfNotPresentEmpInMonth", sizeOfAbsentEmployee.size());
		model.addAttribute("defaultMonthAndYear", date.getMonthValue()+ "-" + date.getYear());
		model.addAttribute("NetSalary", netSalary);
		model.addAttribute("AvragePresenti", LeavesDateOperation.avrageOfPresenti(sizeOfpresentEmployee.size(),sizeOfpresentEmployee.size()+sizeOfAbsentEmployee.size()));
		return "Employee/Dashbord";
	}

	@RequestMapping("resign")
	public String resigPage() {
		return "Employee/ResignPage";
	}

	@RequestMapping(value = "resignFormSubmit", method = RequestMethod.POST)
	public String resignFormData(@ModelAttribute Resign resign,Authentication auth,Model model)throws Exception {
		
		boolean resultIs=false;
		
		int EmployeeId1 = GetIdUser.userid(auth);
		Employee employee = employeeServicesImp.findById(EmployeeId1);
		resign.setEmployee(employee);
		employee.setResign(resign);
		try {
			Employee result=employeeServicesImp.saveEmployee(employee);
			resultIs=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(resultIs)
		{
			RegistrationMessage sms = new RegistrationMessage("your resign request submitted sucessfully", "success");
			model.addAttribute("sucess", sms);
			return "Employee/ResignPage";
		}
		
		RegistrationMessage sms = new RegistrationMessage("Your request not submitted", "danger");
		model.addAttribute("sucess", sms);
		
		return "Employee/ResignPage";
	}
	
	@RequestMapping("BankDetails")
	public String bankDetailsPage(Authentication auth,Model model)throws Exception
	{
		int employeeId=GetIdUser.userid(auth);
		
		BankAccount bankAccount=accountDetailsService.getBankDeatilsByEmpId(employeeId);
		
		model.addAttribute("loginUserBankDetails", bankAccount);
		
		return "Employee/BankPage";
	}
	
	
	@RequestMapping("editeBankDetails")
	public String bankDetailsEditPage(Authentication auth,Model model)throws Exception
	{
		int employeeId=GetIdUser.userid(auth);
		
		BankAccount bankAccount=accountDetailsService.getBankDeatilsByEmpId(employeeId);
		
		model.addAttribute("loginUserBankDetails", bankAccount);
		
		return "Employee/BankAccountDetailsEditPage";
	}
	
	@RequestMapping(value = "updateBankDetails",method = RequestMethod.POST)
	public String updateAddBankDetails(@ModelAttribute BankAccount bankAccount,Authentication auth,Model model)throws Exception
	{
		int employeeId=GetIdUser.userid(auth);
		
		Employee emp = employeeServicesImp.findById(employeeId);
		BankAccount bankAccount1=accountDetailsService.getBankDeatilsByEmpId(employeeId);
		
		if(bankAccount1!=null)
		{
			bankAccount1.setBankAccountNumber(bankAccount.getBankAccountNumber());
			bankAccount1.setBankName(bankAccount.getBankName());
			bankAccount1.setBranchName(bankAccount.getBankName());
			bankAccount1.setIFSC_Code(bankAccount.getIFSC_Code());
			bankAccount1.setBankAddress(bankAccount.getBankAddress());
			bankAccount1.setEmployee(emp);
			
			boolean result=accountDetailsService.saveNewBankAccount(bankAccount1);
			
			if(result)
			{
				RegistrationMessage sms = new RegistrationMessage("Bank record update", "success");
				model.addAttribute("sucess", sms);
				model.addAttribute("loginUserBankDetails", bankAccount1);
				return "Employee/BankPage";
			}
			
		}
		bankAccount.setEmployee(emp);
		boolean result=accountDetailsService.saveNewBankAccount(bankAccount);
		if(result)
		{
			RegistrationMessage sms = new RegistrationMessage("Add new Bank details Succesfully", "success");
			model.addAttribute("sucess", sms);
			model.addAttribute("loginUserBankDetails", bankAccount);
			return "Employee/BankPage";
		}
		RegistrationMessage sms = new RegistrationMessage("Bank details not saved", "danger");
		model.addAttribute("sucess", sms);
		return "Employee/BankPage";
	}
	
	@RequestMapping("request")
	public String requestPage(Model model)
	{
		
		String Timeresult=CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.timeFormatter());
		model.addAttribute("CurrentTime", Timeresult);
		return "Employee/RequestPage";
	}
	
	@RequestMapping("searchEmployee")
	public String getEmployeeForPunching(@RequestParam("id") int id,Model model)throws Exception
	{
		
		Employee emp=this.employeeServicesImp.findById(id);
		boolean result=dateDetailsServicesImp.currentDateDataPresent(id,CurrentMonthAndYear.getCurrentFormatedDate());
		String Timeresult=CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.timeFormatter());
		model.addAttribute("CurrentTime", Timeresult);
		model.addAttribute("PunchingEmployeeDetails", emp);
		model.addAttribute("CurrentDateData", result);
		return "Employee/RequestPage";
	}
	
	
	
	@RequestMapping(value = "InPunch", method = RequestMethod.POST)
	public String inPunchProcess(@RequestParam("employeeId") int employeeId)
	{
		boolean result=dateDetailsServicesImp.inPunching(employeeId,CurrentMonthAndYear.getCurrentFormatedDate());
		
		System.out.println("in punch succesfully");
		
		return"Employee/RequestPage";
	}
	
	
	
	
	@RequestMapping(value = "outPunch", method = RequestMethod.POST)
	public String outPunchProcess(@RequestParam("employeeId") int employeeId)
	{
		boolean result=dateDetailsServicesImp.outPunching(employeeId, CurrentMonthAndYear.getCurrentFormatedDate());
		System.out.println("out punch succesfully");
		return"Employee/RequestPage";
	}
	
	
	
	
}
