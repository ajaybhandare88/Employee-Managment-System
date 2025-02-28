package com.thymelef.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.Employee;


public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
	public Employee findById(int id);
	public Employee findByGmailId(String gmail);
	
	@Query("from Employee As e Where e.employeeName=:name")
	public List<Employee> findByEmployeeName(@Param("name")String name);
	
	@Query("from Employee As e Where e.id=:id")
	public List<Employee> findByEmployeeId(@Param("id")int id);
	
	@Query("from Employee As e where month(e.joiningDate)=:dMonth")
	public List<Employee> newJoin(@Param("dMonth") int dMonth);

	@Query("from Employee As e Where e.type=:USER")
	public List<Employee> findAllUserTypeEmployee(@Param("USER") String user);
	
	
//	@Query("from Employee as e where e.joiningDate=:joiningdate")
//	public List<Employee> findByJoiningDateCreatedBetween(@Param("joiningdate") Date date);
	
//	@Query("FROM Employee AS e WHERE e.id=:employeeId and e.joiningDate BETWEEN :startDate AND :endDate")
//	public List<Employee> findByJoiningDateCreatedBetween(@Param("employeeId") int employeeId,@Param("startDate") Date startDate,@Param("endDate") Date endtDate);
}
