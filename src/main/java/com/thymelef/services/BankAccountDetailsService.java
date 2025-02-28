package com.thymelef.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.dao.BankDetailsRepo;
import com.thymelef.entity.BankAccount;

@Component
public class BankAccountDetailsService {
	
	@Autowired
	BankDetailsRepo bankDetailsRepo;
	
	public BankAccount findById(int BankAccountId)
	{
		return bankDetailsRepo.findById(BankAccountId);
	}
	
	public BankAccount getBankDeatilsByEmpId(int EmployeeId)
	{
		return this.bankDetailsRepo.findByAccountDetailsEmployeeId(EmployeeId);
	}
	
	public boolean saveNewBankAccount(BankAccount account)
	{
		boolean result=false;
		try {
			bankDetailsRepo.save(account);
			result=true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
