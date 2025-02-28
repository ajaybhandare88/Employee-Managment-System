package com.thymelef.helper;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.thymelef.entity.Employee;

public class EmployeeDetails implements UserDetails {
	
	private Employee employee;
	
	public EmployeeDetails(Employee employee) {
		super();
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 ArrayList<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
	        
	        SimpleGrantedAuthority role1 = new SimpleGrantedAuthority("ROLE_"+employee.getType());
	        
	        roles.add(role1);
		
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return Integer.toString(employee.getId());
	}

}
