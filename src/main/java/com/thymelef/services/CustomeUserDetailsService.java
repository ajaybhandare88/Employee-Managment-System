package com.thymelef.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


import com.thymelef.dao.EmployeeRepo;
import com.thymelef.entity.Employee;
import com.thymelef.helper.EmployeeDetails;


public class CustomeUserDetailsService implements UserDetailsService{
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Employee employee=this.employeeRepo.findById(Integer.parseInt(username));
		
		EmployeeDetails details=new EmployeeDetails(employee);
		return details;
	}

}
