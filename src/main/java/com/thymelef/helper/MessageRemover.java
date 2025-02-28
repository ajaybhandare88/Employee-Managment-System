package com.thymelef.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpSession;

@Component
public class MessageRemover {
	
	public static void remover()
	{
		
		try {
			HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
			session.removeAttribute("sucess");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
