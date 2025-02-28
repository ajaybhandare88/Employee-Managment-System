package com.thymelef.helper;

import java.io.IOException;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;


public class GetIdUser {

	public static int userid(Authentication authentication)throws Exception
	{
		
		if(authentication instanceof OAuth2AuthenticationToken)
		{
			
			String providerName=((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
			System.out.println(providerName);
			
			
		}
		System.out.println(authentication.getName());
			return Integer.parseInt(authentication.getName());	
	}
	
	
	
}
