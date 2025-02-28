package com.thymelef.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.thymelef.entity.Employee;
import com.thymelef.entity.EmployeeSalary;
import com.thymelef.helper.GetIdUser;
import com.thymelef.services.EmployeeServicesImp;


@ControllerAdvice
public class AdviceController {

	@Autowired
	private EmployeeServicesImp employeeServicesImp;
	
	@ModelAttribute
	public void loogedUserDetails(Model model,Authentication authentication) throws Exception
	{
		if(authentication==null)
		{
			return;
		}
		
		int EmployeeId=GetIdUser.userid(authentication);
		Employee employee=this.employeeServicesImp.findById(EmployeeId);
		EmployeeSalary employeeSalary=employee.getEmployeeSalary();
		model.addAttribute("LoginUserSalaryDetails", employeeSalary);
		model.addAttribute("LoginUser", employee);
	}
	
}
