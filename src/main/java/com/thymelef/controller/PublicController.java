package com.thymelef.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymelef.dao.EmployeeRepo;
import com.thymelef.entity.BankAccount;
import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.entity.LeaveDetails;
import com.thymelef.entity.LeavesTypes;
import com.thymelef.entity.RegistrationFormData;
import com.thymelef.entity.SalaryMonth;
import com.thymelef.messagesBox.RegistrationMessage;
import com.thymelef.services.BankAccountDetailsService;
import com.thymelef.services.DateDetailsServicesImp;
import com.thymelef.services.EmployeeServicesImp;

import jakarta.persistence.ManyToOne;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@Controller
public class PublicController {

	@Autowired
	EmployeeRepo employeeRepo;


	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping("/home")
	public String homePage() {
		
		return "Home";
	}

	@RequestMapping("/about")
	public String aboutPage() {

		return "About";
	}

	@RequestMapping("/details")
	public String deatilsPage() {
		
		return "Details";
	}

	@RequestMapping("/contact")
	public String contactPage() {
		

		Employee emp = new Employee();
		Random random = new Random();

		emp.setId(1000000 + random.nextInt(9000000));
		emp.setEmployeeName("Ganesh");
		emp.setEmployeeLastName("Bhagwat");
		emp.setMobileNumber(9834993972l);
		emp.setGender("Male");
		emp.setGmailId("ajay@gmail.com");
		emp.setAddress("jalna");
		emp.setJoiningDate(new Date(2025-02-11));
		emp.setDepartment("hii");
		emp.setDesignation("hr");
		emp.setExitDate(null);
		emp.setType("ADMIN");
		emp.setActive(true);
		emp.setProvider("self");
		emp.setPassword(bCryptPasswordEncoder.encode("pass"));
		emp.setResign(null);
		
		BankAccount account=new BankAccount();
		account.setBankAccountNumber(40382062054l);
		account.setBankName("state bank of india");
		account.setBranchName("jalna");
		account.setIFSC_Code("SBIN0016436");
		account.setBankAddress("jalna");
		account.setEmployee(emp);
		
		emp.setBankAccount(account);

		LeavesTypes leavesTypes=new LeavesTypes();
		leavesTypes.setAnnualEave(5);
		leavesTypes.setPaidLeave(5);
		leavesTypes.setSickLeave(5);
		leavesTypes.setWFHLeave(5);
		leavesTypes.setEmployee(emp);
		
		emp.setLeavesTypes(leavesTypes);
		
		
		List<DateDetails> dateDetails = new ArrayList<DateDetails>();
		emp.setDateDetails(dateDetails);

		List<SalaryMonth> salaryMonth = new ArrayList<SalaryMonth>();
		emp.setSalaryMonths(salaryMonth);
		
		List<LeaveDetails> leaveDetails= new ArrayList<LeaveDetails>();
		emp.setLeaveDetails(leaveDetails);
		
		

		EmployeeSalary employeeSalary = new EmployeeSalary();
		employeeSalary.setBasicSalary(15000);
		employeeSalary.setMedicalAllowance(15000);
		employeeSalary.setHouseRentAllAllowance(15000);
		employeeSalary.setPersnolAllowance(15000);
		employeeSalary.setTransportingAllowance(15000);
		employeeSalary.setInsurance(15000);
		employeeSalary.setProfessionalTax(15000);
		employeeSalary.setEmployee(emp);

		emp.setEmployeeSalary(employeeSalary);
		
		try {
			employeeRepo.save(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Contact";
	}
	

	@RequestMapping("/signin")
	public String loginPage() {
		return "Login";
	}

	
	
	
}
