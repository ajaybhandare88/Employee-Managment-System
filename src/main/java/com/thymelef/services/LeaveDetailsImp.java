package com.thymelef.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.StringToLocalDateTimeConverter;
import org.springframework.stereotype.Component;

import com.thymelef.dao.LeaveDetailsRepo;
import com.thymelef.dao.LeavesTypeRepo;
import com.thymelef.entity.LeaveDetails;
import com.thymelef.entity.LeavesTypes;
import com.thymelef.helper.LeavesDateOperation;

@Component
public class LeaveDetailsImp {
 
	@Autowired
	LeaveDetailsRepo detailsRepo;
	
	@Autowired
	LeavesTypeRepo leavesTypeRepo;
	
	public boolean saveLeaveDetails(LeaveDetails details, int Leavesday,String selectedleaveType,int EmployeeId)
	{
			
			LeavesTypes leavesTypes=this.leavesTypeRepo.getLeavesByEmployeeId(details.getEmployee().getId());
			List<LeaveDetails> leavesAvailabelBetweenDays=this.detailsRepo.findByLeaveDetailsBetween(EmployeeId,details.getFromLeaveDate(),details.getToLeaveDate());
			
			
			
			if(selectedleaveType.equals("Paid") && leavesTypes.getPaidLeave() >= Leavesday && leavesAvailabelBetweenDays == null)
			{
				 leavesTypes.setPaidLeave(leavesTypes.getPaidLeave()-Leavesday);
				 this.leavesTypeRepo.save(leavesTypes);
				 this.detailsRepo.save(details);
				 return true;
			}
			else if(selectedleaveType.equals("Sick Leave") && leavesTypes.getSickLeave() >= Leavesday && leavesAvailabelBetweenDays.size() <= 0)
			{
				leavesTypes.setSickLeave(leavesTypes.getSickLeave()-Leavesday);
				this.leavesTypeRepo.save(leavesTypes);
				this.detailsRepo.save(details);
				return true;
			}
			else if(selectedleaveType.equals("Annual Leave") && leavesTypes.getAnnualEave() >= Leavesday && leavesAvailabelBetweenDays == null)
			{
				leavesTypes.setAnnualEave(leavesTypes.getAnnualEave()-Leavesday);
				this.leavesTypeRepo.save(leavesTypes);
				this.detailsRepo.save(details);
				return true;
			}
			else if(selectedleaveType.equals("WFH Leave") && leavesAvailabelBetweenDays.size() <= 0 )
			{
				this.leavesTypeRepo.save(leavesTypes);
				this.detailsRepo.save(details);
				return true;
			}
			
				return false;
	}
	
}
