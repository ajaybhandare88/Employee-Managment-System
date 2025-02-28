package com.thymelef.helper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.thymelef.dao.EmployeeRepo;
import com.thymelef.entity.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GmailThrowRegisterDataGet implements AuthenticationSuccessHandler{

	@Autowired
	EmployeeRepo employeeRepo;
	
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();
		
		
		Employee emp=new Employee();
		Random random=new Random();
		
		BCryptPasswordEncoder p=new BCryptPasswordEncoder();
		
		
		
		OAuth2AuthenticationToken oauth2AuthenticationToken =(OAuth2AuthenticationToken)authentication;
		String providername=oauth2AuthenticationToken.getAuthorizedClientRegistrationId();
		
		if(providername.equalsIgnoreCase("google"))
		{
			emp.setEmployeeName(user.getAttribute("name"));
			emp.setEmployeeLastName(user.getAttribute("family_name"));
			emp.setGmailId(user.getAttribute("email"));
			emp.setPassword(p.encode("pass"));
//			emp.setMobileNumber();
			emp.setGender(null);
			emp.setAddress(null);
			emp.setJoiningDate(null);
			emp.setDepartment(null);
			emp.setDesignation(null);
			emp.setExitDate(null);
			emp.setType("USER");
			emp.setActive(true);
			emp.setProfilePic(user.getAttribute("picture"));
			emp.setProvider("google");
			emp.setId(1000000 + random.nextInt(9000000));
			
			Employee employee=this.employeeRepo.findByGmailId(user.getAttribute("email").toString());
			
			if(employee==null)
			{
				employeeRepo.save(emp);
				System.out.println("saved user data in database");
			}
			
			
			
			new DefaultRedirectStrategy().sendRedirect(request, response, "/employee/dashbord");
			
			
			
		}
		else if(providername.equalsIgnoreCase("github"))
		{
			System.out.println("jay ho github");
			user.getAttributes().forEach((key,value)->{
				System.out.println(key+" => "+value);
			});
		}
		
		
		
	}
	
	

}
