package com.thymelef.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.thymelef.entity.DateDetails;
import com.thymelef.entity.LeaveDetails;

public interface LeaveDetailsRepo extends JpaRepository<LeaveDetails, Integer>{

	@Query("FROM LeaveDetails AS l inner join l.employee AS e WHERE e.id=:employeeId And l.fromLeaveDate<= :fromDate And l.toLeaveDate >= :toDate")
	public List<LeaveDetails> findByLeaveDetailsBetween(@Param("employeeId") int employeeId,@Param("fromDate") Date startDate,@Param("toDate") Date endtDate);
	
}
