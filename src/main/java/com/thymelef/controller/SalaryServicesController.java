package com.thymelef.controller;

import java.net.http.HttpRequest;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.entity.SalaryMonth;
import com.thymelef.helper.MonthlySalaryCalculate;
import com.thymelef.helper.PresentAbsentFilter;
import com.thymelef.helper.SalaryIncrementOperation;
import com.thymelef.helper.SalaryReleaseAllEmp;
import com.thymelef.messagesBox.RegistrationMessage;
import com.thymelef.services.DateDetailsServicesImp;
import com.thymelef.services.EmployeeServicesImp;
import com.thymelef.services.SalaryMonthServiceImp;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/salary")
public class SalaryServicesController {
	
	@Autowired
	EmployeeServicesImp employeeServicesImp;
	
	@Autowired
	DateDetailsServicesImp dateDetailsServicesImp;
	
	@Autowired
	SalaryMonthServiceImp salaryMonthServiceImp;
	
	@Autowired
	SalaryIncrementOperation incrementOperation;

	@RequestMapping("/slip")
	public String paySlip()
	{
		return "AdminPages/PaySlipView";
	}
	
	@RequestMapping("/generateSalary")
	public String monthlySalaryGenerate(@RequestParam("salaryMonth") YearMonth salaryMonth,@RequestParam("employeeId") int id,Model model)
	{
		Employee employee=employeeServicesImp.findById(id);
		if(employee==null || salaryMonth.isAfter(YearMonth.now()))
		{
			System.out.println("id not exist or date select current and before");
			RegistrationMessage message=new RegistrationMessage("Please check employee id and date select befor compare as current date", "danger");
			model.addAttribute("sucess", message);
			return "AdminPages/PaySlipView";
		}
		List<DateDetails> dateDetails=dateDetailsServicesImp.getOnlyPresent(id, salaryMonth.getYear(), salaryMonth.getMonthValue());
		List<DateDetails> notPresent=dateDetailsServicesImp.NotPresentDetails(id, salaryMonth.getYear(), salaryMonth.getMonthValue());
		EmployeeSalary employeeSalary=employee.getEmployeeSalary();
		
		try {
			for(int i=0; i<employee.getSalaryMonths().size(); i++) {
				if(employee.getSalaryMonths().get(i).getSalaryDate().equals(salaryMonth)) {
				model.addAttribute("SalarySlip", employee.getSalaryMonths().get(i));
				model.addAttribute("employee", employee);
				model.addAttribute("bankDetails", employee.getBankAccount());
				model.addAttribute("presentSize", dateDetails.size());
				model.addAttribute("absent", notPresent.size());
				model.addAttribute("totalWorkingDay", dateDetails.size()+notPresent.size());
				model.addAttribute("month", salaryMonth);
				RegistrationMessage message=new RegistrationMessage("This month salary already generated", "success");
				model.addAttribute("sucess", message);
				return "AdminPages/PaySlipView";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Employee returnEmployee=MonthlySalaryCalculate.NetSalary(dateDetails, employeeSalary,employee,salaryMonth);
		employeeServicesImp.saveEmployee(returnEmployee);
		model.addAttribute("SalarySlip", returnEmployee.getSalaryMonths().getLast());
		model.addAttribute("employee", employee);
		model.addAttribute("bankDetails", employee.getBankAccount());
		model.addAttribute("presentSize", dateDetails.size());
		model.addAttribute("absent", notPresent.size());
		model.addAttribute("totalWorkingDay", dateDetails.size()+notPresent.size());
		model.addAttribute("month", salaryMonth);
		return "AdminPages/PaySlipView";
	}
	
	@RequestMapping("/salaryRelease")
	public String salaryReleasePage()
	{
		return"AdminPages/SalaryRelease";
	}
	
	@RequestMapping("/generateSalaryAllEmployee")
	public String salaryReleaseAll(@RequestParam("releaseMonth") YearMonth releaseMonth,Model model)
	{
		List<Employee> allEmployee=employeeServicesImp.findAllEmployee();
		List<Employee> salaryReleaseEmployee=new ArrayList<Employee>();
		
		
		if( allEmployee==null || releaseMonth.isAfter(YearMonth.now()))
		{
			RegistrationMessage message=new RegistrationMessage("Date select befor compare as current date", "danger");
			model.addAttribute("sucess", message);
			return "AdminPages/SalaryRelease";
		}
		
		for(int i=0; i<allEmployee.size(); i++)
		{
			boolean result=SalaryReleaseAllEmp.monthSalaryReleaseCheack(allEmployee.get(i), releaseMonth);
			if(result && allEmployee.get(i).getType().equals("USER")) {
			
			try {
		List<DateDetails> presentEmployee=dateDetailsServicesImp.getOnlyPresent(allEmployee.get(i).getId(), releaseMonth.getYear(), releaseMonth.getMonthValue());
			Employee employee=SalaryReleaseAllEmp.SalaryRelease(allEmployee.get(i), releaseMonth, presentEmployee);
			salaryReleaseEmployee.add(employee);
			employeeServicesImp.saveEmployee(employee);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		}
		try {
		long totalNetReleaseSalaryAmout=SalaryReleaseAllEmp.totalReleaseSalaryTotal(salaryReleaseEmployee);
		model.addAttribute("totalNetReleaseSalaryAmout", totalNetReleaseSalaryAmout);
		List<SalaryMonth> totalReleaseSalaryEmployeeList=SalaryReleaseAllEmp.totalReleaseSalarySalaryMonth(salaryReleaseEmployee);
		model.addAttribute("totalReleaseSalaryEmployeeList", totalReleaseSalaryEmployeeList);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		List<SalaryMonth> SalaryReleaseEmployee=salaryMonthServiceImp.SalaryMonthListSearchBy(releaseMonth);
		long netSalaryTotalAllEmployee=SalaryReleaseAllEmp.totalReleaseSalaryNetAmoutTotal(SalaryReleaseEmployee);
		model.addAttribute("netSalaryTotalAllEmployee",netSalaryTotalAllEmployee);
		model.addAttribute("SalaryReleaseEmployee", SalaryReleaseEmployee.size());
		model.addAttribute("salaryReleaseEmpQuentity", salaryReleaseEmployee.size());
		model.addAttribute("salaryReleaseEmpList", salaryReleaseEmployee);
		model.addAttribute("SalaryReleaseEmployeeList", SalaryReleaseEmployee);
		model.addAttribute("SearchEmpMonthAndYear", releaseMonth);
		model.addAttribute("Month", releaseMonth);
		return"AdminPages/SalaryRelease";
	}
	
	@RequestMapping("/salaryIncrement")
	public String salaryIncrement()
	{
		return "AdminPages/SalaryIncrementPage";
	}
	
	@RequestMapping("/SalaryIncrement")
	public String increment(@RequestParam("amount") long amount,
							@RequestParam("payment") String payment,
							@RequestParam("Department") String dipartment,
							@RequestParam("Designation") String designation,
							@RequestParam("type") String type,
							Model model)
	{
		List<EmployeeSalary> salaryUpdationList=incrementOperation.increment(amount, payment, dipartment, designation, type);
	
		if(salaryUpdationList.size()<=0)
		{
			RegistrationMessage message=new RegistrationMessage("Not found any employee in thid criteria", "danger");
			model.addAttribute("sucess", message);
			model.addAttribute("totalIncrementAmount", "0");
			return "AdminPages/SalaryIncrementPage";
		}
		long totalIncrementAmount=amount*salaryUpdationList.size();
		RegistrationMessage message=new RegistrationMessage("Salary updation successfully", "success");
		model.addAttribute("sucess", message);
		model.addAttribute("updationEmpSize", salaryUpdationList.size());
		model.addAttribute("updationEmpList", salaryUpdationList);
		model.addAttribute("totalIncrementAmount", totalIncrementAmount);
		
		
		return "AdminPages/SalaryIncrementPage";
	}
}
