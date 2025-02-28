package com.thymelef.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;

public interface DateDetailsRepo extends JpaRepository<DateDetails, Integer> {
	
	@Query("FROM DateDetails AS d inner join d.employee AS e WHERE e.id=:employeeId and d.date BETWEEN :startDate AND :endDate")
	public List<DateDetails> findByDetailsCreatedBetween(@Param("employeeId") int employeeId,@Param("startDate") Date startDate,@Param("endDate") Date endtDate);
	
	@Query("FROM DateDetails AS d WHERE d.date BETWEEN :startDate AND :endDate")
	public List<DateDetails> findByDetailsCreatedBetween(@Param("startDate") Date startDate,@Param("endDate") Date endtDate);
	
	
	@Query("from DateDetails as dd inner join dd.employee AS e where e.id=:employeeId and year(dd.date)=:dYear and month(dd.date)=:dMonth and dd.present = false")
	public List<DateDetails> ifNotPresent(@Param("employeeId") int employeeId,@Param("dYear") int dYear,@Param("dMonth") int dMonth);
	
	@Query("from DateDetails as d inner join d.employee AS e where e.id=:employeeId and year(d.date)=:dYear and month(d.date)=:dMonth and d.present = true")
	public List<DateDetails> ifPresent(@Param("employeeId") int employeeId,@Param("dYear") int dYear,@Param("dMonth") int dMonth);
	
	@Query("from DateDetails as d inner join d.employee As e where e.id=:employeeId and d.date=:date")
	public DateDetails findUsingDate(@Param("employeeId") int employeeId,@Param("date") Date date);
	
	@Query("FROM DateDetails as d inner join d.employee As e where e.id=:employeeId ORDER BY d.date DESC LIMIT 10")
	public List<DateDetails> lastTenResult(@Param("employeeId") int id);
	
	@Query("From DateDetails as d inner join d.employee as e where e.id=:employeeId and d.date=:currentDate")
	public DateDetails currentDateDataGet(@Param("employeeId") int employeeId,@Param("currentDate") Date date);
	
	@Query("from DateDetails as d where d.date=:date")
	public List<DateDetails> findUsingByDate(@Param("date") Date date);
	
	@Query("from DateDetails As d where month(d.date)=:dMonth")
	public List<DateDetails> currentMonthData(@Param("dMonth") int dMonth);
	
}
