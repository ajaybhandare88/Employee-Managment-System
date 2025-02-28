package com.thymelef.configuration;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.thymelef.helper.GmailThrowRegisterDataGet;
import com.thymelef.helper.LoginSuccessHandler;
import com.thymelef.services.CustomeUserDetailsService;


@Configuration
@EnableWebSecurity
public class MainConfiguration {
	@Autowired
	GmailThrowRegisterDataGet dataGet;

	@Autowired
	LoginSuccessHandler loginsuccessURL;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(detailsService());
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public UserDetailsService detailsService()
	{
		return new CustomeUserDetailsService();
	}
	
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity httpSecurity)throws Exception
	{
		
		httpSecurity.formLogin(formlogin1->{
			formlogin1.loginPage("/signin");
			formlogin1.loginProcessingUrl("/login");
			// formlogin1.successForwardUrl("/admin/employee/dashbord");
			formlogin1.successHandler(loginsuccessURL);
		
		});
		
		
		httpSecurity.authorizeHttpRequests(request->{
			
			request.requestMatchers("/signup","/signin","/home","/about","/service","/contact","/registrationformSubmit").permitAll() 
			
			.requestMatchers("/employee/**").hasRole("USER")
			.requestMatchers("/admin/**").hasRole("ADMIN");
			
		});
		
		
		httpSecurity.csrf(AbstractHttpConfigurer::disable);
		
		httpSecurity.logout((logoutHandler)->{
			logoutHandler.logoutUrl("/do-logout");
		});
	
		
		httpSecurity.oauth2Login(auth->{
			auth.loginPage("/signup");
			auth.successHandler(dataGet);
			auth.permitAll();
		});
		
		
		return httpSecurity.build();
	}
	
	

}
