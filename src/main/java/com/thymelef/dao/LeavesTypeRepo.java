package com.thymelef.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thymelef.entity.LeavesTypes;

public interface LeavesTypeRepo extends JpaRepository<LeavesTypes, Integer> {

	@Query("from LeavesTypes As l inner join l.employee as e Where e.id=:employeeId")
	
	public LeavesTypes getLeavesByEmployeeId(@Param("employeeId") int employeeId );
	 
	
}
