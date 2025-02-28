package com.thymelef.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thymelef.entity.BankAccount;

public interface BankDetailsRepo extends JpaRepository<BankAccount, Integer>{
	
	public BankAccount findById(int id);
	
	@Query("from BankAccount As b inner join b.employee As e Where e.id=:employeeId")
	public BankAccount findByAccountDetailsEmployeeId(@Param("employeeId") int employeeID);
	
}
