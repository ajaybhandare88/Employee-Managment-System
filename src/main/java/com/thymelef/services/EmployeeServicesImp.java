package com.thymelef.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.nimbusds.oauth2.sdk.ParseException;
import com.thymelef.dao.EmployeeRepo;
import com.thymelef.entity.BankAccount;
import com.thymelef.entity.DateDetails;
import com.thymelef.entity.EditEmployeeData;
import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeDataUpdateAdminPage;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.entity.LeaveDetails;
import com.thymelef.entity.LeavesTypes;
import com.thymelef.entity.RegistrationFormData;
import com.thymelef.entity.SalaryMonth;

@Component
public class EmployeeServicesImp 
{
	private static final int List = 0;

	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public Employee saveEmployee(RegistrationFormData employee) {
		
		//New Employee Save
		Employee emp = new Employee();
		Random random = new Random();

		emp.setId(1000000 + random.nextInt(9000000));
		emp.setEmployeeName(employee.getEmployeeFirstName());
		emp.setEmployeeLastName(employee.getLastName());
		emp.setMobileNumber(employee.getMobileNumber());
		emp.setGender(employee.getGender());
		emp.setGmailId(employee.getGmailId());
		emp.setAddress(employee.getAddress());
		emp.setJoiningDate(employee.getJoiningDate());
		emp.setDepartment(employee.getDepartment());
		emp.setDesignation(employee.getDesignation());
		emp.setExitDate(null);
		emp.setType(employee.getType());
		emp.setActive(true);
		emp.setProvider("self");
		emp.setPassword(bCryptPasswordEncoder.encode("pass"));
		emp.setResign(null);
		
		BankAccount account=new BankAccount();
		account.setBankAccountNumber(employee.getBankAccountNumber());
		account.setBankName(employee.getBankName());
		account.setBranchName(employee.getBranchName());
		account.setIFSC_Code(employee.getIFSC_Code());
		account.setBankAddress(employee.getBankAddress());
		account.setEmployee(emp);
		
		emp.setBankAccount(account);

		LeavesTypes leavesTypes=new LeavesTypes();
		leavesTypes.setAnnualEave(employee.getAnnualEave());
		leavesTypes.setPaidLeave(employee.getPaidLeave());
		leavesTypes.setSickLeave(employee.getSickLeave());
		leavesTypes.setWFHLeave(employee.getWFHLeave());
		leavesTypes.setEmployee(emp);
		
		emp.setLeavesTypes(leavesTypes);
		
		
		List<DateDetails> dateDetails = new ArrayList<DateDetails>();
		emp.setDateDetails(dateDetails);

		List<SalaryMonth> salaryMonth = new ArrayList<SalaryMonth>();
		emp.setSalaryMonths(salaryMonth);
		
		List<LeaveDetails> leaveDetails= new ArrayList<LeaveDetails>();
		emp.setLeaveDetails(leaveDetails);
		
		

		EmployeeSalary employeeSalary = new EmployeeSalary();
		employeeSalary.setBasicSalary(employee.getBasicSalary());
		employeeSalary.setMedicalAllowance(employee.getMedicalAllowance());
		employeeSalary.setHouseRentAllAllowance(employee.getHouseRentAllAllowance());
		employeeSalary.setPersnolAllowance(employee.getPersnolAllowance());
		employeeSalary.setTransportingAllowance(employee.getTransportingAllowance());
		employeeSalary.setInsurance(employee.getInsurance());
		employeeSalary.setProfessionalTax(employee.getProfessionalTax());
		employeeSalary.setEmployee(emp);

		emp.setEmployeeSalary(employeeSalary);
		
		try {
			employeeRepo.save(emp);
			return employeeRepo.save(emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepo.save(employee);
	}
	
	public Employee findById(int id)
	{
		return employeeRepo.findById(id);
	}
	
	public List<Employee> searchById(int id)
	{
		List<Employee> employeeList=employeeRepo.findByEmployeeId(id);
		return employeeList;
	}
	
	public List<Employee> findByName(String name)
	{
		List<Employee> employeeList=employeeRepo.findByEmployeeName(name);
		return employeeList;
	}
	
	public Employee updateEmployeeDetails(EditEmployeeData data)
	{
		Employee employee=employeeRepo.findById(data.getId());
		employee.setEmployeeName(data.getFirst_name());
		employee.setGmailId(data.getEmail_id());
		employee.setGender(data.getGender());
		employee.setMobileNumber(data.getMobile_number());
		employee.setDepartment(data.getDepartment());
		employee.setDesignation(data.getDesignation());
		employee.setProfilePic(data.getProfile().getOriginalFilename().toString());
		
		if(data.getProfile()!=null)
		{
			imageSave(data.getProfile());
		}
		
		return employeeRepo.save(employee);
	}
	
	public Employee updateEmployeeDetailsByAdmin(EmployeeDataUpdateAdminPage data)
	{
		Employee employee=employeeRepo.findById(data.getEmployeeId());
		employee.setEmployeeName(data.getEmployeeName());
		employee.setEmployeeLastName(data.getEmployeeLastName());
		employee.setGmailId(data.getGmailId());
		employee.setMobileNumber(data.getMobileNumber());
		employee.setDepartment(data.getDepartment());
		employee.setDesignation(data.getDesignation());
		employee.setType(data.getType());
		employee.setAddress(data.getAddress());
		
		return employeeRepo.save(employee);
	}
	
	public List<Employee> findAllEmployee()
	{
		List<Employee> AllEmployeeList= employeeRepo.findAllUserTypeEmployee("USER");
		return AllEmployeeList;
	}
		
	

	public boolean imageSave(MultipartFile file)
	{
		boolean result=false;
		try {
			String path="C:\\Users\\sonu\\Desktop\\final\\employee_managment\\src\\main\\resources\\static\\ProfilePics";
			Files.copy(file.getInputStream(),Paths.get(path+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Profile saved sucessfully");
			result=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Employee> newJoiningEmployee(int date)
	{
		try {
		List<Employee> newJoiniEmp=employeeRepo.newJoin(date);
		return newJoiniEmp;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean deleteEmployee(int id)
	{
		try {
			Employee employee=employeeRepo.findById(id);
			if(employee.getType().equals("USER")){
			employeeRepo.delete(employee);
			return true;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
