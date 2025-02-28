package com.thymelef.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thymelef.dao.LeavesTypeRepo;
import com.thymelef.entity.Employee;
import com.thymelef.entity.LeavesTypes;

@Component
public class LeavesTypeImp {
	
	@Autowired
	LeavesTypeRepo leavesTypeRepo;
	
	public LeavesTypes getLeavesType(int id)
	{
		return leavesTypeRepo.getLeavesByEmployeeId(id);
		
	}
	
	public boolean updateLeavesTypes(int id,int annual,int sick,int paid)
	{
		LeavesTypes leavesTypes=leavesTypeRepo.getLeavesByEmployeeId(id);
		leavesTypes.setAnnualEave(0+annual);
		leavesTypes.setSickLeave(0+sick);
		leavesTypes.setPaidLeave(0+paid);
		
		try {
			leavesTypeRepo.save(leavesTypes);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public LeavesTypes updateData(LeavesTypes leavesTypes)
	{
		return leavesTypeRepo.save(leavesTypes);
	}

}
