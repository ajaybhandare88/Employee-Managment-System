package com.thymelef.controller;


import java.sql.Date;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeDataUpdateAdminPage;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.entity.EmployeeSalaryUpdatAdminPage;
import com.thymelef.entity.RegistrationFormData;
import com.thymelef.helper.CurrentMonthAndYear;
import com.thymelef.helper.EmpTodayInOutGet;
import com.thymelef.helper.GetIdUser;
import com.thymelef.helper.LeavesDateOperation;
import com.thymelef.helper.MonthlySalaryCalculate;
import com.thymelef.helper.PresentAbsentFilter;
import com.thymelef.helper.WorkingHourOpration;
import com.thymelef.messagesBox.RegistrationMessage;
import com.thymelef.services.DateDetailsServicesImp;
import com.thymelef.services.EmployeeSalaryDetailsImp;
import com.thymelef.services.EmployeeServicesImp;
import com.thymelef.services.LeavesTypeImp;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/employee")
public class AdminController {
	
	@Autowired
	private EmployeeServicesImp employeeServicesImp;
	
	@Autowired
	private DateDetailsServicesImp dateDetailsServicesImp;
	
	@Autowired
	private EmployeeSalaryDetailsImp employeeSalaryDetailsImp;
	
	@Autowired
	private LeavesTypeImp leavesTypeImp;
	
	@RequestMapping("/dashbord")
	public String adminDashbord(Authentication authentication,Model model) throws Exception
	{
		
		List<Employee> allEmployee=employeeServicesImp.findAllEmployee();
		List<Employee> newJoiningEmp=employeeServicesImp.newJoiningEmployee(Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentMonth())));
		List<DateDetails> currentMonthData=dateDetailsServicesImp.currentMonthData(YearMonth.now().getMonthValue());
		
		List<DateDetails> onlyPresentMonthWise=PresentAbsentFilter.presentEmployeeQuentity(currentMonthData);
		
		try {
		String averagePresenti=LeavesDateOperation.avrageOfPresenti(onlyPresentMonthWise.size(), currentMonthData.size());
		model.addAttribute("average", averagePresenti);
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("month", YearMonth.now());
		model.addAttribute("allEmployeeSize", allEmployee.size());
		model.addAttribute("newJoiningEmp", newJoiningEmp.size());
		model.addAttribute("newJoiningEmpList", newJoiningEmp);
		
		
		
		try {
		GetIdUser.userid(authentication);
		return "AdminPages/Dashbord";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "AdminPages/Dashbord";
	}
	
	@RequestMapping("/dashbordCustumMonth")
	public String DashbordDataCustomeDate(@RequestParam("date") YearMonth month,Model model)
	{
		
		List<Employee> allEmployee=employeeServicesImp.findAllEmployee();
		try {
		List<Employee> newJoiningEmp=employeeServicesImp.newJoiningEmployee(month.getMonthValue());
		model.addAttribute("newJoiningEmp", newJoiningEmp.size());
		model.addAttribute("newJoiningEmpList", newJoiningEmp);
		}catch (Exception e) {
			e.printStackTrace();
		}
		List<DateDetails> currentMonthData=dateDetailsServicesImp.currentMonthData(month.getMonthValue());
		
		List<DateDetails> onlyPresentMonthWise=PresentAbsentFilter.presentEmployeeQuentity(currentMonthData);
		
		try {
		String averagePresenti=LeavesDateOperation.avrageOfPresenti(onlyPresentMonthWise.size(), currentMonthData.size());
		model.addAttribute("average", averagePresenti);
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("month", month);
		model.addAttribute("allEmployeeSize", allEmployee.size());
		
		return "AdminPages/Dashbord";
	}
	
	@RequestMapping("/Services")
	public String adminProfile(Authentication authentication,Model model) throws Exception
	{
		GetIdUser.userid(authentication);
		return "AdminPages/EmployeeProfile";
	}
	
	@RequestMapping("/search")
	public String searchEmployee(@RequestParam("id") int id,Model model)
	{
		
		Employee employee=employeeServicesImp.findById(id);
		
		if(employee==null)
		{
			model.addAttribute("isFound", "Employee Not Found");
		}
		List<DateDetails> presentEmployee = dateDetailsServicesImp.getOnlyPresent(id,
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentYear())),
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentMonth())));
		List<DateDetails> absentEmployee = dateDetailsServicesImp.NotPresentDetails(id,
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentYear())),
				Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentMonth())));
		
		
		model.addAttribute("SearchEmpDetails", employee);
		try {
			model.addAttribute("SearchEmpSalaryDetails", employee.getEmployeeSalary());
			model.addAttribute("SearchEmpRemainingLeaves", employee.getLeavesTypes());
			model.addAttribute("SearchEmpBankingDetails", employee.getBankAccount());
			}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("SearchEmpAvragePresenti", LeavesDateOperation.avrageOfPresenti(presentEmployee.size(),presentEmployee.size()+absentEmployee.size())+" %");
		
		try {
			model.addAttribute("SearchEmpTodayInTime", EmpTodayInOutGet.todayInOut(employee).getInTime().format(CurrentMonthAndYear.timeFormatter()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			model.addAttribute("SearchEmpTodayOutTime", EmpTodayInOutGet.todayInOut(employee).getOutTime().format(CurrentMonthAndYear.timeFormatter()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("SearchEmpPresentDayList", presentEmployee.reversed());
		model.addAttribute("SearchEmpAbsentDayList", absentEmployee.reversed());
		model.addAttribute("SearchEmpPresentDaySize", presentEmployee.size());
		model.addAttribute("SearchEmpAbsentDaySize", absentEmployee.size());
		model.addAttribute("SearchEmpMonthAndYear", Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentMonth()))+"-"+Integer.parseInt(CurrentMonthAndYear.localDateAndTime().format(CurrentMonthAndYear.currentYear())));
		
		
		return "AdminPages/EmployeeProfile";
	}
	
	
	@RequestMapping("/searchDate")
	public String getDataToDateFromeDate(@RequestParam("employeeId") int employeeId,@RequestParam("toDate") Date toDate,@RequestParam("fromDate")Date fromDate,Model model)
	{
		Employee employee=employeeServicesImp.findById(employeeId);
		List<DateDetails> dateDetails = dateDetailsServicesImp.DetailsByDateWise(employeeId, fromDate, toDate);
		
		int presentEmpSize=PresentAbsentFilter.presentEmployeeQuentity(dateDetails).size();
		int absentEmpSize=PresentAbsentFilter.absentEmployeeQuentity(dateDetails).size();
		
		model.addAttribute("SearchEmpDetails", employee);
		model.addAttribute("SearchEmpSalaryDetails", employee.getEmployeeSalary());
		model.addAttribute("SearchEmpRemainingLeaves", employee.getLeavesTypes());
		model.addAttribute("SearchEmpBankingDetails", employee.getBankAccount());
		model.addAttribute("SearchEmpAvragePresenti", LeavesDateOperation.avrageOfPresenti(presentEmpSize,presentEmpSize+absentEmpSize)+" %");
		try {
			model.addAttribute("SearchEmpTodayInTime", EmpTodayInOutGet.todayInOut(employee).getInTime().format(CurrentMonthAndYear.timeFormatter()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		try {
			model.addAttribute("SearchEmpTodayOutTime", EmpTodayInOutGet.todayInOut(employee).getOutTime().format(CurrentMonthAndYear.timeFormatter()));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		model.addAttribute("SearchEmpPresentDayList", PresentAbsentFilter.presentEmployeeQuentity(dateDetails).reversed());
		model.addAttribute("SearchEmpAbsentDayList", PresentAbsentFilter.absentEmployeeQuentity(dateDetails).reversed());
		model.addAttribute("SearchEmpPresentDaySize", presentEmpSize);
		model.addAttribute("SearchEmpAbsentDaySize", absentEmpSize);
		model.addAttribute("SearchEmpDateBetween", fromDate+" to "+toDate);
		
		
		return "AdminPages/EmployeeProfile";
	}
	
	@RequestMapping("/deactivate")
	public String accountDeActivate(@RequestParam("employeeId")int id,Model model)
	{
		Employee employee=employeeServicesImp.findById(id);
		employee.setActive(false);
		try {
			employeeServicesImp.saveEmployee(employee);
			RegistrationMessage sms=new RegistrationMessage("Account De-activate successfully", "success");
			model.addAttribute("sucess", sms);
		} catch (Exception e) {
			e.printStackTrace();
			RegistrationMessage sms=new RegistrationMessage("Account De-activate unsccessfully", "danger");
			model.addAttribute("sucess", sms);
		}
		return "redirect:/admin/employee/search?id="+id;
	}
	
	@RequestMapping("/ReActive")
	public String accountReActivate(@RequestParam("employeeId")int id,Model model)
	{
		Employee employee=employeeServicesImp.findById(id);
		employee.setActive(true);
		try {
			employeeServicesImp.saveEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/employee/search?id="+id;
	}
	
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public String UpdateEmployeeDetails(@ModelAttribute EmployeeDataUpdateAdminPage data)
	{
		employeeServicesImp.updateEmployeeDetailsByAdmin(data);
		return "redirect:/admin/employee/search?id="+data.getEmployeeId();
	}
	
	@RequestMapping(value = "/salaryUpdate",method = RequestMethod.POST)
	public String updateSalaryDetails(@ModelAttribute EmployeeSalaryUpdatAdminPage employeeSalaryDeatils,@RequestParam("id") int id)
	{
		
		boolean result=employeeSalaryDetailsImp.updateSalaryDetails(employeeSalaryDeatils,id);
		return "redirect:/admin/employee/search?id="+id;
	}
	
	
	@RequestMapping(value = "/leavesUpdate",method = RequestMethod.POST)
	public String updateLeavesDetails(@RequestParam("id") int id,@RequestParam("annualeave") int annualeave,@RequestParam("sickLeave") int sickLeave,@RequestParam("paidLeave") int paidLeave)
	{
		boolean result=leavesTypeImp.updateLeavesTypes(id, annualeave, sickLeave, paidLeave);
		return "redirect:/admin/employee/search?id="+id;
	}
	
	@RequestMapping("/newadd")
	public String newEmployeeAddPage()
	{
		return "AdminPages/NewEmployeeAdd";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String newEmployeeForm(@ModelAttribute RegistrationFormData formData,Model model,HttpSession httpSession)
	{
		Employee employee=employeeServicesImp.saveEmployee(formData);
		if(employee!=null) {
			RegistrationMessage sms = new RegistrationMessage("New employee add successfully", "success");
//			httpSession.setAttribute("sucess",sms);
			model.addAttribute("sucess", sms);
			return "redirect:/admin/employee/search?id="+employee.getId();
		}
		RegistrationMessage sms = new RegistrationMessage("Please try again", "danger");
		model.addAttribute("sucess", sms);
		return "redirect:/admin/employee/newadd";
	}
	
	@RequestMapping("/all")
	public String viewAllEmployee(Model model)
	{
		List<Employee> ListOfEmployee=employeeServicesImp.findAllEmployee();
		model.addAttribute("ListOfEmployee", ListOfEmployee);
		return "AdminPages/AllEmployee";	
	}
	
	@RequestMapping("/P&A")
	public String PresentAbsentPage(Model model)
	{
		Date currentDate=CurrentMonthAndYear.formatedCurrentDMY();
		
		try {
		List<DateDetails> currentDateDeatils=dateDetailsServicesImp.getDateDetailsByDate(currentDate);
		List<DateDetails> presentEmployee=PresentAbsentFilter.presentEmployeeQuentity(currentDateDeatils);
		List<DateDetails> absentEmployee=PresentAbsentFilter.absentEmployeeQuentity(presentEmployee);
		List<DateDetails> latePunchingList=WorkingHourOpration.latePunching(presentEmployee);
		List<DateDetails> allOverTimeEmployee=WorkingHourOpration.overTimeEmployeeOnly(presentEmployee);
		
		model.addAttribute("OverTimeEmployeeList", allOverTimeEmployee);
		model.addAttribute("SearchEmpPresentDayList", presentEmployee.reversed());
		model.addAttribute("SearchEmpAbsentDayList", absentEmployee.reversed());
		model.addAttribute("latePunching", latePunchingList);
		
		model.addAttribute("OverTimeEmployeesize", allOverTimeEmployee.size());
		model.addAttribute("latePunchingList", latePunchingList.size());
		model.addAttribute("SearchEmpAvragePresenti", LeavesDateOperation.avrageOfPresenti(presentEmployee.size(),presentEmployee.size()+absentEmployee.size())+" %");
		model.addAttribute("PresentList", presentEmployee.size());
		model.addAttribute("AbsentList", absentEmployee.size());
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("date", "Current Day Data");
		return "AdminPages/PresentAbsentEmployee";
	}
	
	@RequestMapping("/currentDateData")
	public String getDataToDate(@RequestParam("toDate") Date toDate,@RequestParam("fromDate")Date fromDate,Model model)
	{
		List<DateDetails> dateDetails = dateDetailsServicesImp.DetailsByDateWise(fromDate, toDate);
		
		try {
		int presentEmpSize=PresentAbsentFilter.presentEmployeeQuentity(dateDetails).size();
		int absentEmpSize=PresentAbsentFilter.absentEmployeeQuentity(dateDetails).size();
		List<DateDetails> latePunchingList=WorkingHourOpration.latePunching(PresentAbsentFilter.presentEmployeeQuentity(dateDetails));
		List<DateDetails> allOverTimeEmployee=WorkingHourOpration.overTimeEmployeeOnly(PresentAbsentFilter.presentEmployeeQuentity(dateDetails));
		
		model.addAttribute("OverTimeEmployeeList", allOverTimeEmployee);
		model.addAttribute("SearchEmpAvragePresenti", LeavesDateOperation.avrageOfPresenti(presentEmpSize,presentEmpSize+absentEmpSize)+" %");
		model.addAttribute("SearchEmpPresentDayList", PresentAbsentFilter.presentEmployeeQuentity(dateDetails).reversed());
		model.addAttribute("SearchEmpAbsentDayList", PresentAbsentFilter.absentEmployeeQuentity(dateDetails).reversed());
		model.addAttribute("latePunching", latePunchingList);
		
		model.addAttribute("OverTimeEmployeesize", allOverTimeEmployee.size());
		model.addAttribute("PresentList", presentEmpSize);
		model.addAttribute("AbsentList", absentEmpSize);
		model.addAttribute("latePunchingList", latePunchingList.size());
		}catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("date",fromDate+" To "+toDate);
		return "AdminPages/PresentAbsentEmployee";
	}
	
	@RequestMapping("/searchById&Name")
	public String searchByIdAndName(@RequestParam("search") String data,Model model)
	{
		try {
		int id=Integer.parseInt(data);
		List<Employee> employee=employeeServicesImp.searchById(id);
		model.addAttribute("ListOfEmployee", employee);
		return "AdminPages/AllEmployee";
		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		try {
		List<Employee> employee=employeeServicesImp.findByName(data);
		model.addAttribute("ListOfEmployee", employee);
		return "AdminPages/AllEmployee"; 
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/admin/employee/all"; 
	}
	
	@RequestMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id,Model model)
	{
		boolean result=employeeServicesImp.deleteEmployee(id);
		if(result)
		{
			RegistrationMessage sms = new RegistrationMessage("Employee permantely deleted successfully", "success");
			model.addAttribute("sucess", sms);
			return "AdminPages/EmployeeProfile";
		}
		RegistrationMessage sms = new RegistrationMessage("Employee deleted unsuccessfully", "danger");
		model.addAttribute("sucess", sms);
		return "redirect:/admin/employee/search";
	}
	
	
}
