package com.thymelef.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thymelef.entity.EmployeeSalary;


public interface EmployeeSalaryRepo extends JpaRepository<EmployeeSalary, Integer>{
	
	@Query("From EmployeeSalary As es inner join es.employee As e Where e.id=:employeeId")
	public EmployeeSalary findByEmployeeId(@Param("employeeId") int id);
	
	@Query("from EmployeeSalary As es inner join es.employee As e Where es.basicSalary<=:basicSalary And e.Department=:department And e.Designation=:designation And e.type=:type")
	public List<EmployeeSalary> findByBasicSalaryLessThan(@Param("basicSalary")long basicSalary,@Param("department") String department,@Param("designation") String designation,@Param("type")String type);
	
	@Query("from EmployeeSalary As es inner join es.employee As e Where es.basicSalary>=:basicSalary And e.Department=:department And e.Designation=:designation And e.type=:type")
	public List<EmployeeSalary> findByBasicSalaryMoreThan(@Param("basicSalary")long basicSalary,@Param("department") String department,@Param("designation") String designation,@Param("type")String type);
	
	@Query("from EmployeeSalary As es inner join es.employee As e Where es.basicSalary BETWEEN :start AND :end and e.Department=:department And e.Designation=:designation And e.type=:type")
	public List<EmployeeSalary> findByBasicSalaryBetween(@Param("start")long from,@Param("end")long to,@Param("department") String department,@Param("designation") String designation,@Param("type")String type);
}
