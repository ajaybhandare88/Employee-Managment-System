package com.thymelef.dao;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thymelef.entity.SalaryMonth;


public interface SalaryMonthRepo extends JpaRepository<SalaryMonth, Integer>{

	
	@Query("select sm.netSalary from SalaryMonth as sm inner join sm.employee AS e where e.id=:employeeId and sm.salaryDate=:date")
	public long getNetSalary(@Param("employeeId") int employeeId,@Param("date") YearMonth date);
	
	@Query("from SalaryMonth As sm Where sm.salaryDate=:date")
	public List<SalaryMonth> salaryMonthSearchDateWise(@Param("date") YearMonth date);
	
}
